package com.dream11.fantasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dream11.fantasy.model.ContestPlayerEntity;

@Repository
public interface ContestPlayerEntityRepo extends JpaRepository<ContestPlayerEntity, Integer> {

}
