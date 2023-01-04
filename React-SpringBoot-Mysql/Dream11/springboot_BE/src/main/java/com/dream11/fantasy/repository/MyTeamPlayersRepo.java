package com.dream11.fantasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dream11.fantasy.model.MyTeamPlayersEntity;

@Repository
public interface MyTeamPlayersRepo extends JpaRepository<MyTeamPlayersEntity, Integer> {

}
