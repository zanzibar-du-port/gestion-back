package com.ol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ol.models.auth.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
