package com.example.irctc.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.irctc.dto.request.RouteRequest;
import com.example.irctc.model.DistanceBetweenStation;
import com.example.irctc.model.RailwayStation;
import com.example.irctc.model.Role;
import com.example.irctc.model.Route;
import com.example.irctc.model.RouteStations;
import com.example.irctc.repo.DistanceBetweenStationRepo;
import com.example.irctc.repo.RailwayStationRepo;
import com.example.irctc.repo.RouteRepo;
import com.example.irctc.repo.RouteStationsRepo;

@Service
public class RouteService {
	
	@Autowired
	private RouteRepo routeRepo;
	
	@Autowired
	private RailwayStationRepo railwayStationRepo;
	
	@Autowired
	private DistanceBetweenStationRepo distanseRepo;
	
	@Autowired
	private RouteStationsRepo rp;

	public String addNewRoute(RouteRequest routes) {
		
		if(!routeRepo.existsByRouteId(routes.getRouteId())) {
			List<String> selectedStationForThisRoute=routes.getRouteStation();
			
			
		    List<RouteStations> stations = new ArrayList<>();
		    
		    Route routeObj=new Route(routes.getRouteId(),routes.getStartingStation(),routes.getEndStation(),
		    		routes.getDistance(),routes.getNoOfStations());
		    
		    int line=0;
		    int distanse=0;
		    for(String route:selectedStationForThisRoute) {
		    	
		    	RailwayStation obj=railwayStationRepo.getOne(route);
		    	RouteStations obj2=new RouteStations();
		    	System.out.println("1");
		    	if(obj!=null) {
		    		obj2.setStation(obj);
		    		obj2.setLineNo(line);
		    		obj2.setStationCode(route);
			    	System.out.println("2"+obj2.getLineNo());

		    		obj2.setDistanseFromStart(distanse);
			    	System.out.println("3");

		    		 stations.add(obj2);
				    	System.out.println("4");

		    		
				   

		    	}
		    	line++;
		    	
		    }
	    	System.out.println("122");

		    routeObj.setStations(stations);
	    	System.out.println("121");
	    	
		    routeRepo.save(routeObj);
		    addDistanse(routes.getStartingStation(),routes.getEndStation(),routes.getDistance());
		    
		    

			return "routeadded";
		}
		else {
		    addDistanse(routes.getStartingStation(),routes.getEndStation(),routes.getDistance());

			return "g";
		}
		
		
	}
	
	
	
	
	public void addDistanse(String fromStation, String toStaion, int distanse) {
		DistanceBetweenStation onj=distanseRepo.existRecord(fromStation,toStaion);
		if(onj==null) {
			DistanceBetweenStation obj2=new DistanceBetweenStation(fromStation,toStaion,distanse);
			distanseRepo.save(obj2);
		}
		
		System.out.println(fromStation+" "+toStaion+" "+distanse+" "+onj);
		
		
	}

}
