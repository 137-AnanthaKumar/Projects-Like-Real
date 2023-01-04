package com.example.irctc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.irctc.model.Passenger;
@Repository
public interface PassengerRepo extends JpaRepository<Passenger, String> {

}
