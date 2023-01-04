package com.example.irctc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.irctc.dto.response.AddTrainResponse;
import com.example.irctc.model.Train;
import com.example.irctc.services.TrainService;

@RestController
@RequestMapping("/irctc/train")
public class TrainController {
	
	@Autowired
	private TrainService trainService;
	
	@PostMapping("/addnewtrain")
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addNewTrain(@RequestBody Train train) {
		
		
		Train train1=trainService.addNewTrainToDb(train);
	return ResponseEntity.ok(new AddTrainResponse(train1.getTrainNo(),
				train1.getTrainName(),
				train1.getTotalCoach(),
				train1.getTotalFirstclassAcCouch(),
				train1.getTotalFirstclassAcCouchSeats(),
				train1.getTotalSecondClassAc(),
				train1.getTotalSecondClassAcSeats(),
				train1.getTotalThirdClassAc(),
				train1.getTotalThirdClassAcSeats(),
				train1.getTotalSleeperclassSeat(),
				train1.getTotalSleeperclassSeat()));
	}
	
	
	
	@PutMapping("/edittrain/{trainNo}")
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> editTrainDetails(@PathVariable Integer trainNo, @RequestBody Train train){
		String response=trainService.editTrain(trainNo,train);
		System.out.println(response);
		return ResponseEntity.ok(response);
		
	}
	
	
	
	
	@DeleteMapping("/deletetrain/{trainNo}")
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteTrain(@PathVariable Integer trainNo){
		String status=trainService.deleteTrain(trainNo);
		return ResponseEntity.ok(status);
		
	}
	@GetMapping("/gettrain/{trainNo}")
    @PreAuthorize("hasRole('ADMIN')")
	public Train getTrain(@PathVariable int trainNo) {
		Train train=trainService.getTrainDetails(trainNo);
		return train;
		
	}

}
