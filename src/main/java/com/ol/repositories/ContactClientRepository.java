package com.ol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ol.models.ContactClient;

@Repository
public interface ContactClientRepository extends JpaRepository<ContactClient, Integer>{

}
