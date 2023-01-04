package com.example.irctc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.irctc.model.Route;
@Repository
public interface RouteRepo extends JpaRepository<Route, String> {

	boolean existsByRouteId(String routeId);


}
