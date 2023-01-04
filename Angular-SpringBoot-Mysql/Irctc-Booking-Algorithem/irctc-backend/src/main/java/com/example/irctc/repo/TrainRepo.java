package com.example.irctc.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.irctc.model.Train;
@Repository
public interface TrainRepo extends JpaRepository<Train, Integer> {
	
	
	
 @Query(value="SELECT EXISTS(SELECT train_name FROM train WHERE train_name=:trainName ",nativeQuery = true)
 public	Boolean existByTrainName(String trainName);

public Train findByTrainName(String trainName);

@Query(value="SELECT * FROM train WHERE train_no=:trainNo",nativeQuery = true)
public Train getTrainDetails(int trainNo);



}
