package com.techlabs.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.app.entity.ImageData;

public interface StorageRepository extends JpaRepository<ImageData,Integer>{

	ImageData findByName(String fileName);
}
