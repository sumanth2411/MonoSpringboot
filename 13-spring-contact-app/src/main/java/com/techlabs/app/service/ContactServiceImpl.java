package com.techlabs.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techlabs.app.dto.ContactDetailRequestDto;
import com.techlabs.app.dto.ContactDetailResponseDto;
import com.techlabs.app.dto.ContactRequestDto;
import com.techlabs.app.dto.ContactResponseDto;
import com.techlabs.app.dto.UserRequestDto;
import com.techlabs.app.dto.UserResponseDto;
import com.techlabs.app.entity.Contact;
import com.techlabs.app.entity.ContactDetails;
import com.techlabs.app.entity.ContactType;
import com.techlabs.app.entity.User;
import com.techlabs.app.exception.ContactApiException;
import com.techlabs.app.exception.NoRecordFoundException;
import com.techlabs.app.exception.UserNotFoundException;
import com.techlabs.app.repository.ContactDetailRepository;
import com.techlabs.app.repository.ContactRepository;
import com.techlabs.app.repository.UserRepository;
import com.techlabs.app.Util.PagedResponse;

@Service
public class ContactServiceImpl implements ContactService {
	

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private ContactRepository contactRepository;
	private ContactDetailRepository contactDetailRepository;

	public ContactServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
			ContactRepository contactRepository, ContactDetailRepository contactDetailRepository) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.contactRepository = contactRepository;
		this.contactDetailRepository = contactDetailRepository;
	}

	@Override
	public PagedResponse<UserResponseDto> getAllUsers(int page, int size, String sortBy, String direction) {
		Sort sort = Sort.by(sortBy);
		if (direction.equalsIgnoreCase(Sort.Direction.DESC.name())) {
			sort = sort.descending();
		} else {
			sort = sort.ascending();
		}
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<User> user = userRepository.findAll(pageable);

		List<UserResponseDto> userDto = covertUserToUserResponseDto(user.getContent());
		return new PagedResponse<>(userDto, user.getNumber(), user.getSize(), user.getTotalElements(),
				user.getTotalPages(), user.isLast());

//		List<User> user=userRepository.findAll();
//	  return covertUserToUserResponseDto(user);

	}

	private List<UserResponseDto> covertUserToUserResponseDto(List<User> user) {
		List<UserResponseDto> users = new ArrayList<>();

		for (User userDto : user) {
			UserResponseDto userResponseDto = new UserResponseDto();
			userResponseDto.setEmail(userDto.getEmail());
			userResponseDto.setFirstName(userDto.getFirstName());
			userResponseDto.setLastName(userDto.getLastName());
			userResponseDto.setActive(userDto.isActive());
			userResponseDto.setAdmin(userDto.isAdmin());
			userResponseDto.setUserId(userDto.getUserId());
			users.add(userResponseDto);
		}
		return users;

	}

	@Override
	public UserResponseDto addUser(UserRequestDto userRequestDto) {
		if (userRepository.existsByEmail(userRequestDto.getEmail())) {
			throw new ContactApiException(HttpStatus.BAD_REQUEST, "Email already exists!");
		}
		User user = convertUserRequestDtoToUser(userRequestDto);
		User save = userRepository.save(user);
		user.setUserId(save.getUserId());
		return convertUserToUserResponseDto(user);
	}

	private UserResponseDto convertUserToUserResponseDto(User u) {
		UserResponseDto userResponseDto = new UserResponseDto();
		userResponseDto.setEmail(u.getEmail());
		userResponseDto.setFirstName(u.getFirstName());
		userResponseDto.setLastName(u.getLastName());
		userResponseDto.setUserId(u.getUserId());
		userResponseDto.setAdmin(u.isAdmin());
		userResponseDto.setActive(u.isActive());

		return userResponseDto;
	}

	private User convertUserRequestDtoToUser(UserRequestDto userRequestDto) {
		User user = new User();
		user.setUserId(userRequestDto.getUserId());
		user.setActive(userRequestDto.isActive());
		user.setEmail(userRequestDto.getEmail());
		user.setAdmin(userRequestDto.isAdmin());
		user.setFirstName(userRequestDto.getFirstName());
		user.setLastName(userRequestDto.getLastName());
		user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
		if (userRequestDto.getPassword() != null) {
			user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
		} else {
			throw new IllegalArgumentException("Password cannot be null");
		}
		return user;

	}

	@Override
	public UserResponseDto updateUserProfile(UserRequestDto userRequestDto, long user_id) {
		User user = userRepository.findById(user_id).orElse(null);
		if (user != null) {
			userRequestDto.setUserId(user_id);
			if (userRequestDto.getFirstName() != null && !userRequestDto.getFirstName().isEmpty()) {
				user.setFirstName(userRequestDto.getFirstName());
			}
			if (userRequestDto.getLastName() != null && !userRequestDto.getLastName().isEmpty()) {
				user.setLastName(userRequestDto.getLastName());
			}
			if (userRequestDto.getEmail() != null && !userRequestDto.getEmail().isEmpty()) {
				user.setEmail(userRequestDto.getEmail());
			}

			if (userRequestDto.getPassword() != null && !userRequestDto.getPassword().isEmpty()) {
				user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
			}
			// userRequestDto.setUserId(user_id);
			userRepository.save(user);
			return convertUserToUserResponseDto(user);
		} else {
			throw new UserNotFoundException("user with id: " + user_id + " not found");
		}
	}

	@Override
	public UserResponseDto getUserId(long id) {

		User user = userRepository.findById(id).orElse(null);
		if (user == null) {
			throw new UserNotFoundException("user with id: " + id + " not found");
		}

		return convertUserToUserResponseDto(user);
	}

	@Override
	public String deleteUserId(long id) {
		User user = userRepository.findById(id).orElse(null);
		if (user != null) {

			if (!user.isActive()) {
				throw new UserNotFoundException("user with id: " + id + " is already inactive");
			}
			user.setActive(false);
			userRepository.save(user);
			return "user deleted successfully";
		} else {
			throw new UserNotFoundException("user with id: " + id + " not found");
		}

	}

	@Override
	public ContactResponseDto addContact(ContactRequestDto contactRequestDto) {
		Optional<User> user = userRepository.findByEmail(getEmailFromSecurityContext());
		Contact contact = ConvertContactRequestDtoToContact(contactRequestDto, user.get());
		Contact save = contactRepository.save(contact);
		return convertContactToContactResponseDto(save);

	}

	private ContactResponseDto convertContactToContactResponseDto(Contact save) {
		ContactResponseDto contactResponseDto = new ContactResponseDto();
		contactResponseDto.setFirstName(save.getFirstName());
		contactResponseDto.setLastName(save.getLastName());
		contactResponseDto.setActive(save.isActive());
		contactResponseDto.setContactId(save.getContactId());
		return contactResponseDto;
	}

	private String getEmailFromSecurityContext() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			return userDetails.getUsername();
		}
		return null;
	}

	private Contact ConvertContactRequestDtoToContact(ContactRequestDto contactRequestDto, User user) {
		Contact contact = new Contact();

		contact.setFirstName(contactRequestDto.getFirstName());
		contact.setLastName(contactRequestDto.getLastName());
		contact.setContactId(contactRequestDto.getContactId());
		contact.setUser(user);
		contact.setActive(true);
		return contact;
	}

	@Override
	public PagedResponse<ContactResponseDto> getAllContacts(int page, int size, String sortBy, String direction) {

		Sort sort = Sort.by(sortBy);
		if (direction.equalsIgnoreCase(Sort.Direction.DESC.name())) {
			sort = sort.descending();
		} else {
			sort = sort.ascending();
		}
		User user = userRepository.findByEmail(getEmailFromSecurityContext()).orElse(null);
		if (!user.isActive()) {
			throw new UserNotFoundException("user is inactive");
		}
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<Contact> contact = contactRepository.findByUser(user, pageable);
		List<ContactResponseDto> contactResponseDtos = convertContactToContactResponseDto(contact.getContent());
		return new PagedResponse<>(contactResponseDtos, contact.getNumber(), contact.getSize(),
				contact.getTotalElements(), contact.getTotalPages(), contact.isLast());
	}

	private List<ContactResponseDto> convertContactToContactResponseDto(List<Contact> content) {
		List<ContactResponseDto> contactResponseDtos = new ArrayList<>();
		for (Contact c : content) {
			ContactResponseDto contactResponseDto = new ContactResponseDto();
			contactResponseDto.setFirstName(c.getFirstName());
			contactResponseDto.setLastName(c.getLastName());
			contactResponseDto.setContactId(c.getContactId());
			contactResponseDto.setActive(c.isActive());
			contactResponseDto.setContactDetails(c.getContactDetails());
			contactResponseDtos.add(contactResponseDto);
		}
		return contactResponseDtos;
	}

	@Override
	public ContactResponseDto findContactById(long id) {
		User user = userRepository.findByEmail(getEmailFromSecurityContext()).orElse(null);
		if (!user.isActive()) {
			throw new UserNotFoundException("user is inactive");
		}
		List<Contact> contacts = user.getContacts();
		for (Contact contact : contacts) {
			if (contact.getContactId() == id) {
				return convertContactToContactResponseDto(contact);
			}

		}
		throw new NoRecordFoundException("Contact Id " + id + " not found to for user :" + user.getUserId());

	}

	@Override
	public ContactResponseDto updateContactDetails(long id, ContactRequestDto contactRequestDto) {
		User user = userRepository.findByEmail(getEmailFromSecurityContext()).orElse(null);
		if (!user.isActive()) {
			throw new UserNotFoundException("user is inactive");
		}

		List<Contact> contacts = user.getContacts();
		for (Contact contact : contacts) {
			if (contact.getContactId() == id) {
				contactRequestDto.setContactId(id);
				if (contactRequestDto.getFirstName() != null && !contactRequestDto.getFirstName().isEmpty()) {
					contact.setFirstName(contactRequestDto.getFirstName());
				}
				if (contactRequestDto.getLastName() != null && !contactRequestDto.getLastName().isEmpty()) {
					contact.setLastName(contactRequestDto.getLastName());
				}

				contactRepository.save(contact);
				return convertContactToContactResponseDto(contact);

			}

		}

		throw new NoRecordFoundException("Contact Id " + id + " not found to for user :" + user.getUserId());

	}

	@Override
	public String deleteContactById(long id) {
		User user = userRepository.findByEmail(getEmailFromSecurityContext()).orElse(null);
		if (!user.isActive()) {
			throw new UserNotFoundException("user is inactive");
		}
		List<Contact> contacts = user.getContacts();
		for (Contact contact : contacts) {
			if (contact.getContactId() == id && contact.isActive()) {

				contact.setActive(false);
				contactRepository.save(contact);
				userRepository.save(user);

				return "Contact Deleted Successfully";
			} 

		}
		throw new NoRecordFoundException("Contact Id " + id + " not found to for user :" + user.getUserId());

	}

	@Override
	public ContactDetailResponseDto createContactDetailsforContact(ContactDetailRequestDto contactDetailsRequestDto,
			long contact_id) {
		User user = userRepository.findByEmail(getEmailFromSecurityContext()).orElse(null);
		if (!user.isActive()) {
			throw new UserNotFoundException("User is not in active state ");
		}
		List<Contact> contacts = user.getContacts();
		for (Contact contact : contacts) {
			if (contact.getContactId() == contact_id) {
				ContactDetails contactDetails = convertContactDetailsRequestDtoToContactDetails(
						contactDetailsRequestDto);
				contactDetails.setContact(contact);
				contact.add(contactDetails);
				contactRepository.save(contact);
				return convertContactDetailsToContactResponseDto(contactDetails);
			}
		}
		return null;
	}

	private ContactDetailResponseDto convertContactDetailsToContactResponseDto(ContactDetails contactDetails) {
		ContactDetailResponseDto contactDetailsResponseDto = new ContactDetailResponseDto();
		contactDetailsResponseDto.setContactType(contactDetails.getContactType());
		contactDetailsResponseDto.setContactDetailsId(contactDetails.getContactDetailsId());
		return contactDetailsResponseDto;

	}

	private ContactDetails convertContactDetailsRequestDtoToContactDetails(
			ContactDetailRequestDto contactDetailsRequestDto) {
		ContactDetails contactDetails = new ContactDetails();
		if (contactDetailsRequestDto.getContactType().equals(ContactType.Number)) {
			contactDetails.setContactType(ContactType.Number);
		} else {
			contactDetails.setContactType(ContactType.Email);
		}
		return contactDetails;

	}

	public ContactDetailResponseDto updateContactDetails(long contactId,
	        ContactDetailRequestDto contactDetailsRequestDto, long contactDetailsId) {
	    User user = userRepository.findByEmail(getEmailFromSecurityContext()).orElse(null);
	    if (user == null || !user.isActive()) {
	        throw new UserNotFoundException("User is not in active state ");
	    }

	    List<Contact> contacts = user.getContacts();
	    for (Contact contact : contacts) {
	        if (contact.getContactId() == contactId) {
	            List<ContactDetails> contactDetails = contact.getContactDetails();
	            boolean found = false;
	            for (ContactDetails contactdetail : contactDetails) {
	                if (contactdetail.getContactDetailsId() == contactDetailsId) {
	                    found = true;
	                    if (contactDetailsRequestDto.getContactType() != null) {
	                        contactdetail.setContactType(contactDetailsRequestDto.getContactType());
	                        contactRepository.save(contact);
	                        return convertContactDetailsToContactResponseDto(contactdetail);
	                    }
	                }
	            }
	            if (!found) {
	                throw new NoRecordFoundException("Contact details not found for id: " + contactDetailsId);
	            }
	        }
	    }

	    return null;
	}

	@Override
	public PagedResponse<ContactDetailResponseDto> getAllContactDetails(int page, int size, String sortBy,
			String direction, long contactId) {
		User user = userRepository.findByEmail(getEmailFromSecurityContext()).orElse(null);
		if (!user.isActive()) {
			throw new NoRecordFoundException("User is not in active state ");
		}
		Sort sort = Sort.by(sortBy);
		if (direction.equalsIgnoreCase("desc")) {
			sort = sort.descending();
		} else {
			sort = sort.ascending();
		}

		Contact contact = contactRepository.findById(contactId).orElse(null);
		PageRequest pageRequest = PageRequest.of(page, size, sort);
		Page<ContactDetails> contactDetails = contactDetailRepository.findByContact(contact, pageRequest);

		List<ContactDetailResponseDto> list = convertContactDetailToContactResponseDto(
				contactDetails.getContent());
		return new PagedResponse<>(list, contactDetails.getNumber(), contactDetails.getSize(),
				contactDetails.getTotalElements(), contactDetails.getTotalPages(), contactDetails.isLast());
	}

	private List<ContactDetailResponseDto> convertContactDetailToContactResponseDto(List<ContactDetails> content) {
			List<ContactDetailResponseDto> contactDetailsResonseDto = new ArrayList<>();
			for (ContactDetails c : content) {
				ContactDetailResponseDto dto = new ContactDetailResponseDto();
				dto.setContactType(c.getContactType());
				dto.setContactDetailsId(c.getContactDetailsId());
				contactDetailsResonseDto.add(dto);

			}
			return contactDetailsResonseDto;
	}

	@Override
	public ContactDetailResponseDto getContactDetailsById(long contactId, long contactdetails_id) {
		User user = userRepository.findByEmail(getEmailFromSecurityContext()).orElse(null);
		if (!user.isActive()) {
			throw new NoRecordFoundException("User is not in active state ");
		}
		List<Contact> contacts = user.getContacts();
		for (Contact contact : contacts) {
			if (contact.getContactId() == contactId) {
				  ContactDetails contactDetails = contactDetailRepository.findById(contactdetails_id).orElse(null);
				  if(contactDetails!=null) {
					  return convertContactDetailsToContactResponseDto(contactDetails);
				  }
				  else {
					  throw new NoRecordFoundException("contact details not found for id: "+contactdetails_id);
				  }
			}
				
			}
		
		
		return null;
	}

	@Override
	public String deleteContacDetailstById(long contactdetails_id, long contactId) {
		User user = userRepository.findByEmail(getEmailFromSecurityContext()).orElse(null);
	    
	    if (user == null || !user.isActive()) {
	        throw new NoRecordFoundException("User is not in active state");
	    }
	    
	    List<Contact> contacts = user.getContacts();
	    for (Contact contact : contacts) {
	        if (contact.getContactId() == contactId) {
	            ContactDetails contactDetail = contactDetailRepository.findById(contactdetails_id).orElse(null);
	            if(contactDetail==null) {
	                 throw  new NoRecordFoundException("Contact detail not found with id: " + contactdetails_id);
	            }
	            contact.getContactDetails().remove(contactDetail);
	            contactDetailRepository.delete(contactDetail);  
	            
	            contactRepository.save(contact);
	            
	            return "Contact details deleted successfully";
	        }
	    }
	    
	    throw new NoRecordFoundException("Contact not found with id: " + contactId);
	
	}

}
