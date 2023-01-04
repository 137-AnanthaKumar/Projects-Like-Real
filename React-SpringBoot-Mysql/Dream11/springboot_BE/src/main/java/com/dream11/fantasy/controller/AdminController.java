package com.dream11.fantasy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream11.fantasy.dto.UpdateAllInDpDto;
import com.dream11.fantasy.service.LiveGameService;

@CrossOrigin("*")
@RestController
@RequestMapping("/adminControll")
public class AdminController {
	
	
	@Autowired
	private LiveGameService liveGameService;
	
	
	@PutMapping("/setRanks/{contestCode}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> updateRankForPerticularContest(@PathVariable String contestCode){
		
		String responseFromServiceq=liveGameService.updatesRanks(contestCode);
		return ResponseEntity.ok(responseFromServiceq);
		
	}
	
	@PutMapping("/updatePOintsInAllTeams/{contestCode}/{contestId}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('CONTROLLER')")
	public ResponseEntity<String> getMyTeamWithPoint(@PathVariable String contestCode, @PathVariable int contestId ){
		 String value=liveGameService.updateAllTeams(contestCode,contestId);
		 return ResponseEntity.ok(value);
		 
		
	}
	
	
	@PutMapping("/prizeListCalculation")
	@PreAuthorize("hasRole('ADMIN') or hasRole('CONTROLLER')")
    public String finalPrizeList(@RequestBody UpdateAllInDpDto ubdate){
		 String value=liveGameService.finalPrizeListFetch(ubdate.getContestCode());
		
		return value;
		
	}
	
	

}
