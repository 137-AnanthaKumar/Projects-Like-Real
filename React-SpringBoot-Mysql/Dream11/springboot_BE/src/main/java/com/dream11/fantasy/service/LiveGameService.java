package com.dream11.fantasy.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream11.fantasy.commen.ConstantPoints.PLAYER_POINT;
import com.dream11.fantasy.dto.LiveGameDto;
import com.dream11.fantasy.model.ContestEntity;
import com.dream11.fantasy.model.ContestPlayerEntity;
import com.dream11.fantasy.model.MyTeam;
import com.dream11.fantasy.model.MyTeamPlayersEntity;
import com.dream11.fantasy.model.PrizeListCreate;
import com.dream11.fantasy.repository.ContestEntityRepo;
import com.dream11.fantasy.repository.ContestPlayerEntityRepo;
import com.dream11.fantasy.repository.MyTeamRepo;
import com.dream11.fantasy.repository.PrizeListCreateRepo;


@Service
public class LiveGameService {
//	@Autowired
//    private ContestEntityRepo contestEntityRepo;
	@Autowired
	private TeamsService teamsService;
	
	@Autowired
	private PrizeListCreateRepo prizeListrepo;
	@Autowired
	private ContestPlayerEntityRepo contestPlayerRepo;
	
	@Autowired
	private ContestEntityRepo contestEntityRepo;
	
	@Autowired
	private MyTeamRepo myteamRepo;
	
	public String updateDataForRuns(LiveGameDto liveGameDto) {
		ContestEntity contest=contestEntityRepo.getById(liveGameDto.getContestId());
		if(	contest.getContextStatus().equalsIgnoreCase("Active")) {
			contest.setContextStatus("LIVE");
			contestEntityRepo.save(contest);

		}
		ContestPlayerEntity player=contestPlayerRepo.getById(liveGameDto.getPlayerId());
		
		
		
	//	HashMap<String, Integer> forWhat = new HashMap<String, Integer>();
		
		if(liveGameDto.getCatches()!=0 ) {
			player.setCatches(player.getCatches()+liveGameDto.getCatches());
			player.setPoints(player.getPoints()+(PLAYER_POINT.CATCH)*liveGameDto.getCatches());
			System.out.println("POIYts"+player.getPoints()+(PLAYER_POINT.CATCH));
			contestPlayerRepo.save(player);
			return "CATCH_UPDATED";
		}
		
		else if(liveGameDto.getWicket() !=0) {
			player.setWicket(liveGameDto.getWicket()+player.getWicket());
			player.setPoints(player.getPoints()+PLAYER_POINT.WICKET);
			if(player.getWicket()==5) {
				player.setPoints(player.getPoints()+PLAYER_POINT.FIVEWICKETHALL);
			}
			contestPlayerRepo.save(player);
			return "WICKET_UPDATED";
		}
		
		else if(liveGameDto.getRuns()!=0) {
			
			int i;
			for(i=0;i<liveGameDto.getRuns();i++) {
				player.setRuns(player.getRuns()+1);
				
				player.setPoints(player.getPoints()+(PLAYER_POINT.SINGLERUN));
				
				if(player.getRuns()==50) {
					player.setPoints(player.getPoints()+PLAYER_POINT.FIFTYBONUS);
				}
				if(player.getRuns()==100) {
					player.setPoints(player.getPoints()+PLAYER_POINT.CENTURYBONUS);
				}
				
				
				
			}
			
			if(liveGameDto.getRuns()==4) {
				player.setFours(player.getFours()+1);
				player.setPoints(player.getPoints()+PLAYER_POINT.FOURRUNS);
			}
			if(liveGameDto.getRuns()==6) {
				player.setSix(player.getSix()+1);
				player.setPoints(player.getPoints()+PLAYER_POINT.SIXRUN);
			}
			
			contestPlayerRepo.save(player);
			
			
			//String status=teamsService.setPointsAndRunsPlayerTable(liveGameDto.getContestId());
			//----STORE POINS INDIVIDUL TEAMS
			
			return "RUNS_UPDATED";
			
		}
		
		else {
			return "GIVE_PROPER_INPUT";
		}
		
		
		
		
	}
	//------------------------
	public String reverseUpdateDataForRuns(LiveGameDto liveGameDto) {
		ContestPlayerEntity player=contestPlayerRepo.getById(liveGameDto.getPlayerId());
		if(liveGameDto.getCatches()!=0 ) {
				player.setCatches(player.getCatches()-liveGameDto.getCatches());
				player.setPoints(player.getPoints()-(PLAYER_POINT.CATCH));
				contestPlayerRepo.save(player);
				return "RE_CATCH_UPDATED";
			}
			
			else if(liveGameDto.getWicket() !=0) {
				player.setWicket(liveGameDto.getWicket()-player.getWicket());
				player.setPoints(player.getPoints()-PLAYER_POINT.WICKET);
				if(player.getWicket()==5) {
					player.setPoints(player.getPoints()-PLAYER_POINT.FIVEWICKETHALL);
				}
				contestPlayerRepo.save(player);
				return "RE_WICKET_UPDATED";
			}
			
			else if(liveGameDto.getRuns()!=0) {
				
				int i;
				for(i=0;i<liveGameDto.getRuns();i++) {
					player.setRuns(player.getRuns()-1);
					
					player.setPoints(player.getPoints()-(PLAYER_POINT.SINGLERUN));
					
					if(player.getRuns()==50) {
						player.setPoints(player.getPoints()-PLAYER_POINT.FIFTYBONUS);
					}
					if(player.getRuns()==100) {
						player.setPoints(player.getPoints()-PLAYER_POINT.CENTURYBONUS);
					}
				}
				
				if(liveGameDto.getRuns()==4) {
					player.setFours(player.getFours()-1);
					player.setPoints(player.getPoints()-PLAYER_POINT.FOURRUNS);
				}
				if(liveGameDto.getRuns()==6) {
					player.setSix(player.getSix()-1);
					player.setPoints(player.getPoints()-PLAYER_POINT.SIXRUN);
				}
				
				contestPlayerRepo.save(player);
				return "RE_RUNS_UPDATED";
				
			}
			
			else {
				return "GIVE_PROPER_INPUT";
			}
	}
	
	
//	public MyTeam getMyTeamWithPoints(int teamId) {
//		
//		return myteamRepo.getById(teamId);
//	}
	public String updateAllTeams(String contestCode, int contestId) {
		ContestEntity contestEntity=contestEntityRepo.getById(contestId);
		
		System.out.println("heree");
		ArrayList<MyTeam> team=myteamRepo.findByContestCode(contestCode);
		System.out.println(team.size());
		
		
		for(MyTeam tea:team) {
			
			
			List<MyTeamPlayersEntity> singleTeamplayer=tea.getMyPlayers();
			 ArrayList<Long> totalPoints = new ArrayList<Long>();
			for(MyTeamPlayersEntity myplayer:singleTeamplayer) {
				
				ContestPlayerEntity objj=contestPlayerRepo.getById(myplayer.getPlayerId());
				
				if(myplayer.isCaptain()) {
					myplayer.setPoints(objj.getPoints()*3);
					
				}
				else if(myplayer.isVc()){
					
					myplayer.setPoints(objj.getPoints()*2);
				}
				else {
					myplayer.setPoints(objj.getPoints());

				}
				
				totalPoints.add(myplayer.getPoints());
				
				
				
			}
			
			long sum = 0;

			 for (long number : totalPoints){
			     sum += number;
			 }
			
			
			tea.setMyPlayers(singleTeamplayer);
			tea.setTotalPoits(sum);
			if(tea.getTeamStatus().equalsIgnoreCase("Live")){
				
			}
			else {
				tea.setTeamStatus("Live");
			}
			myteamRepo.saveAll(team);
			
			
			
			
			
		}
		return "DONE";
	}
	public Optional<MyTeam> getMyPoints(int teamId) {
		System.out.println("2");
		// TODO Auto-generated method stub
		return myteamRepo.findById(teamId);
	}
	public List<MyTeam> getPointsForContest(String contestCode) {
		
		return myteamRepo.getPoindsFromTop(contestCode);
	}
	public String updatesRanks(String contestCode) {
		List<MyTeam> myteamm=myteamRepo.getPoindsFromTop(contestCode);
		long totalPointsss=0;
		int currentrankkk=0;//1    2    2    2
		int samepoints=0;// 1    2
		for(MyTeam object:myteamm) {
			System.out.println(totalPointsss);
			System.out.println(object.getTotalPoits());
			if(totalPointsss==object.getTotalPoits()) {
				object.setMyRank(currentrankkk);
				samepoints=samepoints+1;
//				System.out.println("TOTAL POINTS "+totalPointsss);
//
//				System.out.println("TOTAL samepoints "+samepoints);
//				System.out.println("TOTAL currentrankkk "+currentrankkk);


				//totalPointsss=object.getTotalPoits();

			}
			else {
				totalPointsss=object.getTotalPoits();
				object.setMyRank(currentrankkk+1+samepoints);
//				System.out.println("TOTAL POINTS "+totalPointsss);
//
//				System.out.println("TOTAL samepoints "+samepoints);
//				System.out.println("TOTAL currentrankkk "+currentrankkk);

				currentrankkk=currentrankkk+1+samepoints;
				samepoints=0;
				
				if(samepoints==0) {
					
				}
				
				
			}
		
		}
		myteamRepo.saveAll(myteamm);
		
		return "SUCESSFULLY_UPDATEDRA";
	}
	public MyTeam getOneTeamById(int teamid) {
		// TODO Auto-generated method stub
		return myteamRepo.getById(teamid);
	}
	
	
	public String finalPrizeListFetch(String contestCode) {
		
		List<PrizeListCreate> prizeRankList=prizeListrepo.findByContestCode(contestCode);
		
		
		int TotalWiiners=contestEntityRepo.getTotalWinners(contestCode);
//	
		   HashMap<Integer, Integer> rankWistWinning = new HashMap<Integer, Integer>();
		   HashMap<Integer, Integer> finalWinning = new HashMap<Integer, Integer>();
      for(PrizeListCreate prizelist:prizeRankList) {//0-1,2-8,9-10
			int fromRank=prizelist.getFromRank();
			int toRAnk=prizelist.getToRank();
			int status=fromRank;
			
				while(toRAnk>=status) {
					
					if(status!=0) {
						rankWistWinning.put(status, prizelist.getWinningAmount());
					}
					
					status++;
				}
		}
//		System.out.println("rankWiining");
//		System.out.println(rankWistWinning);
      
        HashMap<Integer, Integer> multipleRanks = new HashMap<Integer, Integer>();
		List<Integer> ranks=myteamRepo.getRankOnlyFromTop(contestCode);
//		System.out.println("totalWinners "+TotalWiiners);
//		System.out.println(ranks);

		for(Integer rank:ranks) {
			Integer count=multipleRanks.get(rank);
			if(count == null) {
				multipleRanks.put(rank, 1);
			}
			else {
				multipleRanks.put(rank, ++count);

			}
		}
//	System.out.println("RANK GOT USERS");
//	System.out.println(multipleRanks);
//	System.out.println(multipleRanks.size()); 
		
	for(int i=1;TotalWiiners>=i;i++) {
		if(multipleRanks.get(i)!=null) {
			if(multipleRanks.get(i)==1) {
				finalWinning.put(i,rankWistWinning.get(i));
			}
			
			else if(multipleRanks.get(i)>1) {
				int rankPrize=rankWistWinning.get(i);
				int sameRank=multipleRanks.get(i);
			int toTargrtRank=(multipleRanks.get(i)-1)+i;
			int totalForSameNo=0;
			for(int j=i;toTargrtRank>=j;j++) {
				if(TotalWiiners>=j) {
					totalForSameNo=totalForSameNo+rankWistWinning.get(j);				}
			
			}
			int finalAmount=(totalForSameNo/sameRank);

			for(int j=i;toTargrtRank>=j;j++) {
				finalWinning.put(i, finalAmount);
			}

			}
		}
		
	}
//	System.out.println(finalWinning);
//
//	System.out.println(finalWinning.size());
	List<MyTeam> prizeAmountUpdateInteams=myteamRepo.findByContestCode(contestCode);
	
	for(MyTeam mytramOpj: prizeAmountUpdateInteams) {
		
		int myrank=mytramOpj.getMyRank();
		   mytramOpj.setTeamStatus("FINISHED");

	   Integer 	winningamount=finalWinning.get(myrank);
	   if(winningamount != null) {
		   mytramOpj.setWiining(winningamount);
		   myteamRepo.save(mytramOpj);
	   }  
	}
	
	ContestEntity ennn=contestEntityRepo.findByContestCode(contestCode);
	ennn.setContextStatus("FINISHED");
	contestEntityRepo.save(ennn);
		return "FINISHED";
	}

}
