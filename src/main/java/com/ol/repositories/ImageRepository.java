package com.ol.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ol.models.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{

	Optional<Image> findByName(String name);
}
