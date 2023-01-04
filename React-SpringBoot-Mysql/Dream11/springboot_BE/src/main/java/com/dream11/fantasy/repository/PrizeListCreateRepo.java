package com.dream11.fantasy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dream11.fantasy.model.PrizeListCreate;

@Repository
public interface PrizeListCreateRepo extends JpaRepository<PrizeListCreate, Integer> {

	List<PrizeListCreate> findByContestCode(String contestCode);

}
