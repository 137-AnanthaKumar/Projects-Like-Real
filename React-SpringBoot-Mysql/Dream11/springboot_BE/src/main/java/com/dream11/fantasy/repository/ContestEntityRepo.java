package com.dream11.fantasy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dream11.fantasy.model.ContestEntity;

@Repository
public interface ContestEntityRepo extends JpaRepository<ContestEntity, Integer> {
	
	
	
	@Query(value = "SELECT * FROM contest_entity u WHERE u.context_status ='Active' OR u.context_status='Live' ", nativeQuery = true)
	List<ContestEntity> getAllActiveContest();
	
	@Query(value = "SELECT * FROM contest_entity u WHERE u.context_status ='Active'  ", nativeQuery = true)
	List<ContestEntity> getAllActiveContestoNLY();
	
	
     @Query(value = "SELECT * FROM contest_entity u WHERE u.context_status ='Active' AND contest_code=:contestCode ", nativeQuery = true)
   List<ContestEntity> get(String contestCode);


	ContestEntity findByContestCode(String code);
	

	@Query(value = "SELECT final_winners FROM contest_entity  WHERE contest_code=:contestCode ", nativeQuery = true)
    int getTotalWinners(String contestCode);

	@Query(value = "SELECT * FROM contest_entity u WHERE u.context_status ='Active' ", nativeQuery = true)
    List<ContestEntity> getAllActiveContestOnly();

	@Query(value = "SELECT * FROM contest_entity u WHERE u.context_status ='Live' ", nativeQuery = true)
    List<ContestEntity> getAllLiveContestOnly();

	
}
