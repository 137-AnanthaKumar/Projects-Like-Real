package com.dream11.fantasy.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream11.fantasy.dto.JoinContestDto;
import com.dream11.fantasy.model.ContestEntity;
import com.dream11.fantasy.model.ContestPlayerEntity;
import com.dream11.fantasy.model.DreamElevenAccount;
import com.dream11.fantasy.model.MyTeam;
import com.dream11.fantasy.model.MyTeamPlayersEntity;
import com.dream11.fantasy.model.PrizeListCreate;
import com.dream11.fantasy.model.TransAction;
import com.dream11.fantasy.model.UserAccount;
import com.dream11.fantasy.repository.ContestEntityRepo;
import com.dream11.fantasy.repository.ContestPlayerEntityRepo;
import com.dream11.fantasy.repository.DreamElevenAccountRepo;
import com.dream11.fantasy.repository.MyTeamRepo;
import com.dream11.fantasy.repository.PrizeListCreateRepo;
import com.dream11.fantasy.repository.TransActionRepo;
import com.dream11.fantasy.repository.UserAccountRepo;



@Service
public class ContestService {
	
	@Autowired
	private ContestEntityRepo contestRepo;
	
	@Autowired
	private DreamElevenAccountRepo repoForDream;
	
	@Autowired
	private TransActionRepo repoTranc;
	@Autowired
	private PrizeListCreateRepo prizeListCreateRepo;
	
	@Autowired
	private UserAccountRepo userAccountRepo;
	
	@Autowired
	private MyTeamRepo teamRepo;
	
	@Autowired
	private ContestPlayerEntityRepo contestPlayerRepo;

	public ContestEntity addNewContest(ContestEntity contestEntity) {
		ContestEntity newopj=new ContestEntity();
		return contestRepo.save(contestEntity);
	}

  public List<ContestEntity> getAllActiveContest() {
		
		return contestRepo.getAllActiveContestoNLY();
	}







	public String JoiningContest(JoinContestDto dto) {
		ContestEntity newContestObj=contestRepo.getById(dto.getContestId());
		UserAccount userAccountOpj=userAccountRepo.getById(dto.getAccountID());
		long entryBalanse=newContestObj.getEntreFee();
		long currentBalalnse=userAccountOpj.getCurrentAmount();
		
		
	String code=newContestObj.getContestCode();
	int allowedteams=newContestObj.getMaxTeamsPerUser();
	int lengthOfJoined=teamRepo.lengthOfdata(code, dto.getAccountID());

	System.out.println(lengthOfJoined+"  "+allowedteams);
	
	if(allowedteams==lengthOfJoined || allowedteams<lengthOfJoined ) {
		return "Allready Joined";
	}
	
	
	
	else {
		
		
		
		if(entryBalanse<=currentBalalnse && newContestObj.getAvailPlace()>0  ) {
			MyTeam myteam=new MyTeam();
			myteam.setAccountName(userAccountOpj.getAccountName());
			myteam.setTeamStatus("Active");
			myteam.setTeamNumber("T"+(lengthOfJoined+1));
			myteam.setAccountId(dto.getAccountID());
			myteam.setContestId(dto.getContestId());
			myteam.setContestCode(newContestObj.getContestCode());
			int captain=dto.getCaptainId();
			int vc=dto.getViceCaptainId();
			System.out.println("here");
			int[] newArr=new int[dto.getPlayerIdForThisContest().length];
			newArr=dto.getPlayerIdForThisContest();
			
			List<MyTeamPlayersEntity> myPlyers=myteam.getMyPlayers();
			
     for(int playerId:newArr) {
				
				MyTeamPlayersEntity myPlyer=new MyTeamPlayersEntity();
			System.out.println("helo");
			ContestPlayerEntity contestPlayerEntity=contestPlayerRepo.getById(playerId);
			myPlyer.setRole(contestPlayerEntity.getRole());
			myPlyer.setName(contestPlayerEntity.getPlayerName());
				myPlyer.setPlayerId(playerId);
				myPlyer.setPoints(0);
				if(captain==playerId) {//81
					myPlyer.setCaptain(true);
				}
				if(vc==playerId) {//82
					myPlyer.setVc(true);
				}
				myPlyers.add(myPlyer);
				
			}
     
     
			myteam.setMyPlayers(myPlyers);
			teamRepo.save(myteam);
			
			userAccountOpj.setCurrentAmount(userAccountOpj.getCurrentAmount()-entryBalanse);
			userAccountOpj.setTotalamountjoining(userAccountOpj.getTotalamountjoining()+entryBalanse);
			userAccountOpj.setTotalJoinedContest(userAccountOpj.getTotalJoinedContest()+1);
			newContestObj.setAvailPlace(newContestObj.getAvailPlace()-1);
			
			contestRepo.save(newContestObj);
			userAccountRepo.save(userAccountOpj);
			
			DreamElevenAccount dreamAccount=repoForDream.getById(1);
			dreamAccount.setAmount(dreamAccount.getAmount()+entryBalanse);
			repoForDream.save(dreamAccount);
			
			
			
			TransAction transActionOpj=new TransAction();
			transActionOpj.setContestCode(code);
			transActionOpj.setFromAccontNo(dto.getAccountID());
			transActionOpj.setToAccount(987123);
			
			Date date=new Date();
		    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
			 String strDate= formatter.format(date); 
			 transActionOpj.setDate(strDate);
			 
			 long milli = 123456789999l;
			  
		     java.sql.Time time = new java.sql.Time(milli);
		      
		        transActionOpj.setTransActionAmount(entryBalanse);
			 transActionOpj.setTime(time.toString());
			transActionOpj.setTypeOfTracs("USER_TO_DREAM");
			repoTranc.save(transActionOpj);
			
			return "sucess";
		}
		else {
			return "BalanceLOW";
		}
	}
	
		
		
	
	
		
		
		
	}

	public Optional<ContestEntity> getContast(int contastId) {
	
		return contestRepo.findById(contastId);
	}

	public ArrayList<ContestEntity> getAllMyActiveContestInitiate(String accontName) {

		
		List<String> response= teamRepo.GetMyActiveTeam(accontName);
     	System.out.println(response);

		HashSet<String> contestCode=new HashSet<String>();
		System.out.println(contestCode.size());
		for(String code:response) {
			contestCode.add(code);
	   }
		
		
			System.out.println(contestCode);

		ArrayList<ContestEntity> conTestentity=new ArrayList<ContestEntity>();

		for(String code:contestCode) {
			
          System.out.println(code);
			
			ContestEntity entity=contestRepo.findByContestCode(code);
			conTestentity.add(entity);
	}
		
		
		
		
		return conTestentity;
	}

	public List<MyTeam> getAllTeamsForThisContest(String contestCode) {
		return teamRepo.findByContestCode(contestCode);
	}

	public Integer EnableDisAbleContest(int contastId) {
		ContestEntity entity=contestRepo.getById(contastId);
		System.out.println("ohh"+entity.getContestLock());
		if(entity.getContestLock().equalsIgnoreCase("LOCKED")) {
			entity.setContestLock("UNLOCKED");
			contestRepo.save(entity);
			return 1;
		}
		else if(entity.getContestLock().equalsIgnoreCase("UNLOCKED")) {
			entity.setContestLock("LOCKED");
			contestRepo.save(entity);
			return 2;
		}
		else {
			return 3;
		}
		
	}

	public String addPrizeList(List<PrizeListCreate> prizeList, String ide) {
    for(PrizeListCreate opj:prizeList) {
    	System.out.println(opj.getToRank());
    	PrizeListCreate prizeLst=new PrizeListCreate();
    	prizeLst.setFromRank(opj.getFromRank());
    	prizeLst.setToRank(opj.getToRank());
    	prizeLst.setNoOfWinners(opj.getNoOfWinners());
    	prizeLst.setWinningAmount(opj.getWinningAmount());
    	prizeLst.setContestCode(ide);
    	prizeListCreateRepo.save(prizeLst);
			
		}
	return null;
		
	}

	public List<PrizeListCreate> getPrizelist(String contestCode) {
		return prizeListCreateRepo.findByContestCode(contestCode);
	}

	public List<ContestEntity> getAllActiveContestOnly() {
		
		return contestRepo.getAllActiveContestOnly();
	}

	public List<ContestEntity> getAllLiveContestOnly() {
		// TODO Auto-generated method stub
		return  contestRepo.getAllLiveContestOnly();
	}
	
	
	

	public List<ContestEntity> getAllMyFinishedContestInitiate(String accontName) {

		List<String> response= teamRepo.GetMyFinishedTeam(accontName);
//		System.out.println(response);

		HashSet<String> contestCode=new HashSet<String>();
		System.out.println(contestCode.size());
		for(String code:response) {
			contestCode.add(code);
	   }
		
		
			System.out.println(contestCode);

		ArrayList<ContestEntity> conTestentity=new ArrayList<ContestEntity>();

		for(String code:contestCode) {
			
          System.out.println(code);
			
			ContestEntity entity=contestRepo.findByContestCode(code);
			conTestentity.add(entity);
			
			
		}
		
		
		
		
		return conTestentity;
	}
	


}
