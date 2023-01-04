package com.example.irctc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.irctc.model.TripScheduler;
@Repository
public interface ScheduledTripsRepo extends JpaRepository<TripScheduler, Long> {
	
	
	//	@Query(value = "SELECT * FROM contest_entity u WHERE u.context_status ='Active' OR u.context_status='Live' ", nativeQuery = true)
//WHERE (date_field BETWEEN '2010-09-29 10:15:55' AND '2010-01-30 14:15:55')
    @Query(value="SELECT * FROM trip_scheduler u WHERE u.status='ACTIVE' AND train_no=:trainNo  ",nativeQuery = true)
	List<TripScheduler> getActiveScheduleForThisTrain(int trainNo);

    @Query(value="SELECT * FROM trip_scheduler u WHERE u.status='ACTIVE' ",nativeQuery = true)
    List<TripScheduler> getActiveSchedules();

}
