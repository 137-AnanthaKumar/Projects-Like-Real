package com.example.restapi.mapping;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoAddress extends JpaRepository<AddressForStudentEntity, Integer> {

}
