package com.example.irctc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.irctc.exception.TrainNameAndNoAvailable;
import com.example.irctc.model.Train;
import com.example.irctc.repo.TrainRepo;

@Service
public class TrainService {
	
	@Autowired
	private TrainRepo trainRepo;
	
	public Boolean trainNameStatus(String trainName) {
		Train trainonh=trainRepo.findByTrainName(trainName);

		if(trainonh==null) {
			return true;
		}
		
		return false;
	}

	public Train addNewTrainToDb(Train train) {
		
		Train train2=trainRepo.getTrainDetails(train.getTrainNo());
		
		Train trainonh=trainRepo.findByTrainName(train.getTrainName());
		
		if(train2==null && trainonh ==null) {
			Train train1=trainRepo.save(train);
			return train1;
			}
		else
		 throw new TrainNameAndNoAvailable("TrainNoorTrainNameFound");
		
		
	}

	public String editTrain(Integer trainNo, Train train) {
		Train trainob=trainRepo.getById(trainNo);
		System.out.println("hekk");
		if(trainob !=null) {
			trainob.setTrainName(train.getTrainName());
			trainob.setTotalCoach(train.getTotalCoach());
			trainob.setTotalFirstclassAcCouch(train.getTotalFirstclassAcCouch());
			trainob.setTotalFirstclassAcCouchSeats(train.getTotalFirstclassAcCouchSeats());
			trainob.setTotalSecondClassAc(train.getTotalSecondClassAc());
			trainob.setTotalSecondClassAcSeats(train.getTotalSecondClassAcSeats());
			trainob.setTotalSeeperClass(train.getTotalSeeperClass());
			trainob.setTotalSleeperclassSeat(train.getTotalSleeperclassSeat());
			trainob.setTotalThirdClassAc(train.getTotalThirdClassAc());
			trainob.setTotalThirdClassAcSeats(train.getTotalThirdClassAcSeats());
			trainRepo.save(trainob);
			
			String response="EditedSuccessFully"+trainNo;
			return response;
			
		}
		return "trainNoNotDound";
	}

	public String deleteTrain(Integer trainNo) {
		Train train= trainRepo.getById(trainNo);
		trainRepo.delete(train);
		Boolean sts=trainRepo.existsById(trainNo);
		if(sts) {

			return "deletionFailed";
		}
		return "DELETED";
	}

	public Train getTrainDetails(int trainNo) {
		// TODO Auto-generated method stub
		Train train= trainRepo.getTrainDetails(trainNo);
		if(train==null) {
			throw new TrainNameAndNoAvailable("Not Found With Train No"+trainNo);
		}

		return train;
	}

}
