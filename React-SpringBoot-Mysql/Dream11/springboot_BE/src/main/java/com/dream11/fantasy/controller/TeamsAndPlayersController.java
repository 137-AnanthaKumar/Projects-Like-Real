package com.dream11.fantasy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream11.fantasy.dto.ApiResponse;
import com.dream11.fantasy.model.TeamsEntity;
import com.dream11.fantasy.service.TeamsService;


@CrossOrigin("*")
@RestController
@RequestMapping("/teams")
public class TeamsAndPlayersController {
	@Autowired
	private TeamsService teamsService;
	
	@PostMapping("/addNewTeam")
	@PreAuthorize("hasRole('ADMIN') or hasRole('CONTROLLER')")
	public ResponseEntity<ApiResponse> addTeamAndPlayers(@RequestBody TeamsEntity entity){
		
		TeamsEntity newEntity=teamsService.addNewTeamToDreamm11(entity);
		ApiResponse apiresponse=new ApiResponse();
		apiresponse.setError("No Error");
		apiresponse.setData(newEntity);
		return ResponseEntity.status(apiresponse.getStatus()).body(apiresponse);
		
	}
	
	@GetMapping("/getAllCricketTeams")
	@PreAuthorize("hasRole('ADMIN') or hasRole('CONTROLLER')")
	public List<TeamsEntity> getAllTeam(){
		List<TeamsEntity> teamsEntity=teamsService.getAllTeams();
		return teamsEntity;
	}

}
