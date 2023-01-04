package com.example.irctc.repo;


import java.util.HashSet;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.irctc.model.Slots;

@Repository
public interface SlotRepo extends JpaRepository<Slots, Long> {

	@Query(value="SELECT coach_no from slots WHERE trip_id=:tripId",nativeQuery=true)
	HashSet<String> getAllCoachNameByTripId(String tripId);

	
	@Query(value="SELECT COUNT(*) from slots WHERE trip_id=:tripId AND slot_status='BOOKED' AND coach_no=:coachNo",nativeQuery=true)
	int getBookedtickets(String tripId, String coachNo);

	@Query(value="SELECT COUNT(*) from slots WHERE trip_id=:tripId AND slot_status='AVAIL' AND coach_no=:coachNo",nativeQuery=true)
    int getAvailtickets(String tripId, String coachNo);

    @Query(value="SELECT coachposition from slots where coach_no=:coachNo AND trip_id=:tripId limit 1;",nativeQuery=true)
	int getPosition(String tripId, String coachNo);

    @Query(value="SELECT slot_id from slots where coach_no=:coachNo AND trip_id=:tripcode AND slot_status='AVAIL' LIMIT :noOfPassenger ",nativeQuery=true)
	List<Long> getAvailAbleSlotID( String coachNo,String tripcode, int noOfPassenger);

    @Query(value="SELECT slot_id from slots where  trip_id=:tripcode AND slot_status='AVAIL' AND coach_type=:classs LIMIT :noOfPassenger ",nativeQuery=true)
    List<Long> getAvailAbleSlotForAll(String tripcode, int noOfPassenger, String classs);

    @Query(value="SELECT coach_type from slots WHERE trip_id=:tripcode",nativeQuery=true)
    HashSet<String> getAllCoachType(String tripcode);

    @Query(value="SELECT * from slots WHERE pass_id=:id",nativeQuery=true)
	 Slots getPassenger(String id) ;
	

}
