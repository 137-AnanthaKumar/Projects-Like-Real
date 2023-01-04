package com.example.irctc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.irctc.model.RouteStations;


@Repository
public interface RouteStationsRepo extends JpaRepository<RouteStations, String> {

}
