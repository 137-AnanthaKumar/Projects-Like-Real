package com.example.irctc.dto.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class StationSearchResponse {

	private List<String> stations=new ArrayList<String>();
}
