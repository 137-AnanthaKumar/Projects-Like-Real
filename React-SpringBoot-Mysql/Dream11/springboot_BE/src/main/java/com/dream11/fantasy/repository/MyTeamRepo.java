package com.dream11.fantasy.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dream11.fantasy.model.MyTeam;

@Repository
public interface MyTeamRepo extends JpaRepository<MyTeam, Integer> {

	 @Query(value="SELECT * FROM my_team  WHERE contest_code=:contestCode ORDER BY total_poits DESC",nativeQuery = true)
	 List<MyTeam> getPoindsFromTop(String contestCode);
	 
	 @Query(value="SELECT my_rank FROM my_team  WHERE contest_code=:contestCode ORDER BY total_poits DESC",nativeQuery = true)
	 List<Integer> getRankOnlyFromTop(String contestCode);
	 
	  @Query(value = "SELECT contest_code FROM my_team u WHERE (u.team_status ='Active' OR u.team_status ='Live') AND  account_name=:AccountName ", nativeQuery = true)
      List<String> GetMyActiveTeam(String AccountName);
	  

	  @Query(value = "SELECT contest_code FROM my_team u WHERE u.team_status ='FINISHED' AND  account_name=:AccountName ", nativeQuery = true)
      List<String> GetMyFinishedTeam(String AccountName);
	  
	  @Query(value = "SELECT COUNT(account_id) FROM my_team  WHERE contest_code=:contestCode AND account_id=:Id ", nativeQuery = true)
      Integer lengthOfdata(String contestCode,int Id);

	ArrayList<MyTeam> findByContestCode(String contestCode);
	
	 @Query(value="SELECT * FROM my_team  WHERE contest_code=:contestCode ",nativeQuery = true)
     ArrayList<MyTeam> findByContestCodeWinners(String contestCode);


	MyTeam getById(int contestId);

	 @Query(value="SELECT * FROM my_team  WHERE account_name=:accountName AND contest_code=:contestCode ",nativeQuery = true)
    List<MyTeam> getMyAccountTeams(String accountName, String contestCode);

}
