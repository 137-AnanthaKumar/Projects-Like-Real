package com.dream11.fantasy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream11.fantasy.dto.CreateContestDto;
import com.dream11.fantasy.dto.JoinContestDto;
import com.dream11.fantasy.model.ContestEntity;
import com.dream11.fantasy.model.ContestPlayerEntity;
import com.dream11.fantasy.model.MyTeam;
import com.dream11.fantasy.model.PlayersEntity;
import com.dream11.fantasy.model.PrizeListCreate;
import com.dream11.fantasy.model.TeamsEntity;
import com.dream11.fantasy.repository.TeamsEntityRepo;
import com.dream11.fantasy.service.ContestService;
import com.dream11.fantasy.service.TeamsService;


@CrossOrigin("*")
@RestController
@RequestMapping("/contest")
public class ContestController {

	@Autowired
	private TeamsService serviceTeam;
	
	@Autowired
	private ContestService contestService;
	@Autowired
	private TeamsEntityRepo teamsEntRepo;
	@PostMapping("/addNewContests")
	@PreAuthorize("hasRole('ADMIN') or hasRole('CONTROLLER')")
     public String createNewContests(@RequestBody CreateContestDto dto ) {
		String ide1="DREAM";
		
		 int length=5;
		  String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	      String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
	     
	      
	      String combinedChars = capitalCaseLetters + lowerCaseLetters  ;
	      Random random = new Random();
	      char[] password = new char[length];

	      password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
	      password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
	     
	     
	   
	      for(int i = 2; i< length ; i++) {
	         password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
	      }
	      
	      String svaa=String.valueOf(password);  
	      System.out.println(svaa);
		
		Date date=new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		 String strDate= formatter.format(date); 
		 
		

		 // String first4charForName = name.substring(0,4).toUpperCase();
		  String last4charForDate = strDate.substring(0,2);
		 // System.out.println();
         String Ide=ide1+last4charForDate+svaa;

		ContestEntity contestEntity=new ContestEntity();
		contestEntity.setFinalWinners(dto.getFinalWinners());
		contestEntity.setWinningPercentage(dto.getWinningPercentage());
		contestEntity.setContestCode(Ide);
		contestEntity.setTitle(dto.getTitle());
		contestEntity.setMaxTeamsPerUser(dto.getMaxTeamsPerUser());
		contestEntity.setContestAmount(dto.getEntreFee()*dto.getTotalteams());
		contestEntity.setTotalTeams(dto.getTotalteams());
		contestEntity.setAvailPlace(dto.getTotalteams());
		contestEntity.setEntreFee(dto.getEntreFee());
		//contestEntity.setEntreFee((dto.getContestAmount())/(dto.getTotalteams()));
		
		
		int[] newArr=new int[dto.getTeamsId().length];
		newArr=dto.getTeamsId();
		
	   for(int id:newArr) {
		
		   TeamsEntity enti=serviceTeam.getPlayers(id);
		  
		   List<PlayersEntity> contestPlayers=enti.getPlayers();
		   for(PlayersEntity entity: contestPlayers) {
			   ContestPlayerEntity contestPlayer=new ContestPlayerEntity();
			   contestPlayer.setPlayerName(entity.getName());
			   contestPlayer.setRole(entity.getRole());
			   contestPlayer.setTeam(enti.getTeamName());
			   contestEntity.addPlayer(contestPlayer);//83
		   }
     }
		
	   ContestEntity cretedContest=contestService.addNewContest(contestEntity);
		contestService.addPrizeList(dto.getPrizeList(),cretedContest.getContestCode());

		return "Contest Added Successfully";
		
		
	}
	
	@GetMapping("/getAllActiveContest")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('CONTROLLER')")
	public List<ContestEntity> getAllActiveContest() {
		List<ContestEntity> entity=contestService.getAllActiveContest();
		return entity;
		}
	@GetMapping("/getAllActiveContestonly")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('CONTROLLER')")
	public List<ContestEntity> getAllActiveContestOnly() {
		List<ContestEntity> entity=contestService.getAllActiveContestOnly();
		return entity;
		}
	
	@GetMapping("/getAllLiveContestonly")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('CONTROLLER')")
	public List<ContestEntity> getAllLiveContestOnly() {
		List<ContestEntity> entity=contestService.getAllLiveContestOnly();
		return entity;
		}
	
	@PostMapping("/joinContest")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<String> joinContest(@RequestBody JoinContestDto dto){
		String ststus=contestService.JoiningContest(dto);
		return ResponseEntity.ok(ststus);
		
	}
	
	@GetMapping("/getcontestDetail/{contastId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('CONTROLLER')")
	public Optional<ContestEntity> getContastById(@PathVariable int contastId) {
		Optional<ContestEntity> entity=contestService.getContast(contastId);
		return entity;
	}
	
	@GetMapping("/getMyContestInitiate/{AccontName}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('CONTROLLER')")
	public List<ContestEntity> getMyContestByAccountNameAndActive(@PathVariable String AccontName) {
		System.out.println("CONTETCODE"+AccontName);

		List<ContestEntity> entity=contestService.getAllMyActiveContestInitiate(AccontName);
		System.out.println("cotest controller  End 144");

		return entity;
		}
	
	@GetMapping("/getMyContestInitiateFinal/{AccontName}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('CONTROLLER')")
	public List<ContestEntity> getMyContestByAccountNameAndFinished(@PathVariable String AccontName) {
		System.out.println("CONTETCODE"+AccontName);

		List<ContestEntity> entity=contestService.getAllMyFinishedContestInitiate(AccontName);
		System.out.println("cotest controller  End 144");

		return entity;
		}
	
	@GetMapping("/getTeamsContest/{contestCode}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('CONTROLLER')")
	public List<MyTeam> getTeamsForThisContest(@PathVariable String contestCode) {
		
		List<MyTeam> teams=contestService.getAllTeamsForThisContest(contestCode);
		return teams;
		
	}
	
	@PostMapping("/enableDisableContest/{contastId}")
	@PreAuthorize(" hasRole('ADMIN') or hasRole('CONTROLLER')")
     public Integer EnableDisableContest(@PathVariable int contastId) {
		System.out.println("HAY MAN I CAMe HERE "+contastId);
		int vall=contestService.EnableDisAbleContest(contastId);
		return vall;
	}
	
	@GetMapping("getPrizeList/{contestCode}")
	@PreAuthorize(" hasRole('ADMIN') or hasRole('USER')")
    public List<PrizeListCreate> getPrizeList(@PathVariable String contestCode) {
		List<PrizeListCreate> list=contestService.getPrizelist(contestCode);
		
		return list;
		
		
	}
	
	
	
	
	
	
	
	
	


}
