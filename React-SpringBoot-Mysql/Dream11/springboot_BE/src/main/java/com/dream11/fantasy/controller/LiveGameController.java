package com.dream11.fantasy.controller;

import java.util.List;
import java.util.Optional;

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

import com.dream11.fantasy.dto.LiveGameDto;
import com.dream11.fantasy.dto.UpdateAllInDpDto;
import com.dream11.fantasy.model.MyTeam;
import com.dream11.fantasy.service.LiveGameService;


@CrossOrigin("*")
@RestController
@RequestMapping("/liveUpdates")
public class LiveGameController {
	@Autowired
	private LiveGameService liveGameService;

	
	@PutMapping("/updatesRunAndPoints")
	@PreAuthorize("hasRole('ADMIN') or hasRole('CONTROLLER')")
	public ResponseEntity<String> updatesPoints(@RequestBody LiveGameDto liveGameDto){
		String responseFromService=liveGameService.updateDataForRuns(liveGameDto);
		
		return ResponseEntity.ok(responseFromService);
		
	}
	
	@PutMapping("/reverseUpdatesRunAndPoints")
	@PreAuthorize("hasRole('ADMIN') or hasRole('CONTROLLER')")
	public ResponseEntity<String> reverseUpdatesPoints(@RequestBody LiveGameDto liveGameDto){
		String responseFromService=liveGameService.reverseUpdateDataForRuns(liveGameDto);
		
		return ResponseEntity.ok(responseFromService);
		
	}
	
	
	
	
	

	
	@PostMapping("/hii")
	@PreAuthorize("hasRole('ADMIN')")
    public void SayHello() {
		System.out.println("HELLO ADMIN");
	}
	
	
	
	@GetMapping("/getMyteamWithPoints/{teamId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('CONTROLLER')")
	public Optional<MyTeam> getMyTeamWithPoints(@PathVariable int teamId){
		System.out.println("1");
		Optional<MyTeam> myteam=liveGameService.getMyPoints(teamId);
		System.out.println("3");
		return myteam;
		
	}
	
	@PutMapping("updateallTeamsInDp")
	@PreAuthorize( "hasRole('ADMIN')")
	public ResponseEntity<String> UpdateAllPointsInDb(@RequestBody UpdateAllInDpDto updateAllInDpDto) {
		 String value=liveGameService.updateAllTeams(updateAllInDpDto.getContestCode(),updateAllInDpDto.getContestId());
		 return ResponseEntity.ok(value);
	}
	
	@PutMapping("setranks")
	@PreAuthorize( "hasRole('ADMIN')")
	public ResponseEntity<String> setRanks(@RequestBody UpdateAllInDpDto updateAllInDpDto) {
		String responseFromServiceq=liveGameService.updatesRanks(updateAllInDpDto.getContestCode());
		return ResponseEntity.ok(responseFromServiceq);
	}

	
	@GetMapping("getTeamsOrderByPoints/{contestCode}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('CONTROLLER')")
	public List<MyTeam> getTeamsOrderByPoints(@PathVariable String contestCode){
		List<MyTeam> myteams=liveGameService.getPointsForContest(contestCode);
		
		return myteams;
		
	}
	
	@GetMapping("getteam/{teamId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public MyTeam getMyTeam(@PathVariable int teamid) {
		System.out.println("hsgsffsgfgf");
		MyTeam myteamOpj=liveGameService.getOneTeamById(teamid);
		return myteamOpj;
		
	}
	
	
	
	

}
