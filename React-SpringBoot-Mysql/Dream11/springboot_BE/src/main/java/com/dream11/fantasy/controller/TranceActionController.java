package com.dream11.fantasy.controller;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream11.fantasy.dto.UpdateAllInDpDto;
import com.dream11.fantasy.model.ContestEntity;
import com.dream11.fantasy.model.DreamElevenAccount;
import com.dream11.fantasy.model.MyTeam;
import com.dream11.fantasy.model.TransAction;
import com.dream11.fantasy.model.UserAccount;
import com.dream11.fantasy.repository.ContestEntityRepo;
import com.dream11.fantasy.repository.DreamElevenAccountRepo;
import com.dream11.fantasy.repository.MyTeamRepo;
import com.dream11.fantasy.repository.TransActionRepo;
import com.dream11.fantasy.repository.UserAccountRepo;


@CrossOrigin("*")
@RestController
@RequestMapping("/transAction")
public class TranceActionController {
	
	@Autowired 
    private TransActionRepo repoTrans;
	@Autowired
	private MyTeamRepo teamsEntRepo;
	@Autowired
	private ContestEntityRepo contestRepo;
	
	@Autowired
	private UserAccountRepo repoUserAccount;
	
	@Autowired
	private DreamElevenAccountRepo repoDream;
	
	@PostMapping("/tranActionMethod")
	@PreAuthorize("hasRole('ADMIN') or hasRole('CONTROLLER')")
	public String transActionManagement(@RequestBody UpdateAllInDpDto dtol) {
		
		
		
	
		ContestEntity entityList=contestRepo.findByContestCode(dtol.getContestCode());
		if(entityList.getContextStatus().equalsIgnoreCase("FINISHED")) {
			
			Date date=new Date();
		    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
			 String strDate= formatter.format(date); 
		        LocalTime timeObj = LocalTime.now();

			 
			
			List<MyTeam> teams=teamsEntRepo.findByContestCodeWinners(dtol.getContestCode());
			for(MyTeam myTeam:teams ) {
				if(myTeam.getStatusCredit().equalsIgnoreCase("NOT_CREDITED") && myTeam.getWiining()!=0) {
					int a=(int)myTeam.getWiining();
					DreamElevenAccount dreamAccount=repoDream.getById(1);
					dreamAccount.setAmount(dreamAccount.getAmount()-a);
					
					TransAction action=new TransAction(987123,myTeam.getAccountId(),a,strDate,timeObj.toString(),"DREAM_TO_USER",dtol.getContestCode());
					repoTrans.save(action);

					UserAccount userAccount=repoUserAccount.getById(myTeam.getAccountId());
					userAccount.setWinningsAmount(userAccount.getWinningsAmount()+a);
					userAccount.setCurrentAmount(userAccount.getCurrentAmount()+a);
					repoUserAccount.save(userAccount);
					myTeam.setStatusCredit("CREDITED");
					
					repoDream.save(dreamAccount);
					//userAccount.setWonContest(userAccount.getWonContest()+1);
					
				}
				
				else if(myTeam.getStatusCredit().equalsIgnoreCase("NOT_CREDITED") && myTeam.getWiining()==0) {
					myTeam.setStatusCredit("LOSE");
					
				}
				
				
				teamsEntRepo.saveAll(teams);
				}
				
			
			
			
		}
		
		return "FULL_SUCESS";
		
	}

}
