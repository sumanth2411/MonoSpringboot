package com.techlabs.app.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techlabs.app.entity.Admin;
import com.techlabs.app.entity.User;
import com.techlabs.app.repository.AdminRepository;
import com.techlabs.app.repository.UserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService{

	   private UserRepository userRepository;
	    private AdminRepository adminRepository;

	    public CustomUserDetailsService(UserRepository userRepository,AdminRepository adminRepository) {
	        this.userRepository = userRepository;
	        this.adminRepository=adminRepository;
	    }

	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        User user = userRepository.findByEmail(email).orElse(null);
	        Admin admin = adminRepository.findByEmail(email).orElse(null);

	        if (user == null && admin == null) {
	            throw new UsernameNotFoundException("User not found with username or email: " + email);
	        }

	       
	        UserDetails userDetails;
	        if (admin != null) {
	            Set<GrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
	            userDetails = new org.springframework.security.core.userdetails.User(admin.getEmail(), admin.getPassword(), authorities);
	        } else {
	            Set<GrantedAuthority> authorities = user.getRoles().stream()
	                    .map(role -> new SimpleGrantedAuthority(role.getName()))
	                    .collect(Collectors.toSet());
	            userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	        }

	        return userDetails;
	    }
}
