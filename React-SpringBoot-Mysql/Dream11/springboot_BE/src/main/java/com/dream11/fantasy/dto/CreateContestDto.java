package com.dream11.fantasy.dto;

import java.util.ArrayList;
import java.util.List;

import com.dream11.fantasy.model.PrizeListCreate;

import lombok.Data;

@Data
public class CreateContestDto {
private int contestId;
private String title;
private String contestCode;
private int[] teamsId;
private int contestAmount;

private List<PrizeListCreate> prizeList=new ArrayList<PrizeListCreate>();
private int entreFee;
private int finalWinners;
private int winningPercentage;

private int totalteams;
private int maxTeamsPerUser;

}
