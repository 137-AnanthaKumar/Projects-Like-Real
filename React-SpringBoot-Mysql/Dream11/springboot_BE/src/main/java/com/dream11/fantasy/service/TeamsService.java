package com.dream11.fantasy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream11.fantasy.model.TeamsEntity;
import com.dream11.fantasy.repository.ContestPlayerEntityRepo;
import com.dream11.fantasy.repository.MyTeamPlayersRepo;
import com.dream11.fantasy.repository.TeamsEntityRepo;



@Service
public class TeamsService {
	@Autowired
	private TeamsEntityRepo teamRepo;
	@Autowired
	private MyTeamPlayersRepo teamPlayersRepo;
	
	@Autowired
	private ContestPlayerEntityRepo contestPlayerRepo;

	public TeamsEntity addNewTeamToDreamm11(TeamsEntity entity) {
		// TODO Auto-generated method stub
		return teamRepo.save(entity);
	}

	public TeamsEntity getPlayers(int id) {
		TeamsEntity enti=teamRepo.getById(id);
		return enti;
	}

	public List<TeamsEntity> getAllTeams() {
		// TODO Auto-generated method stub
		return teamRepo.findAll();
	}



}
