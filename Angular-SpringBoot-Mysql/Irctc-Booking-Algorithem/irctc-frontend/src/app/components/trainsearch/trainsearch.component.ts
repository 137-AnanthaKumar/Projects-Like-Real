import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TrainSearchDetail } from 'src/app/entity/trainserch';
import { Output, EventEmitter } from '@angular/core';

import { UserregisterService } from 'src/app/service/userregister.service';
import { RailwayStaions } from 'src/app/entity/RailwayStation';

@Component({
  selector: 'app-trainsearch',
  templateUrl: './trainsearch.component.html',
  styleUrls: ['./trainsearch.component.css']
})
export class TrainsearchComponent implements OnInit {

  constructor(  private router: Router,private service:UserregisterService) { }
 
  searchdata: TrainSearchDetail=new TrainSearchDetail();
//stat:RailwayStaions=new RailwayStaions();
"stat":string[]=[];
"statto":string[]=[];
  ngOnInit(): void {
    

  }

  satTionClick(type:string,value:string){
    if(type=='from'){
         this.searchdata.fromStation=value;
    }
    if(type=='to'){
      this.searchdata.toStation=value;
    }
    this.stat=[];
    this.statto=[];
    
  }

  onChangeStation(ty:string){
    this.stat=[];
    this.statto=[];
    console.log("keyup")
   if( ty=='from'){
    this.service.getStations(this.searchdata.fromStation).subscribe(data=>{
      console.log(data.stations)
      console.log(data)
      for (let i = 0; i < data.stations.length; i++) {
        console.log(data.stations[i])
        this.stat.push(data.stations[i])

      }
      console.log(this.stat.length)
      
    })
    
   }

    if( ty=='to'){
      this.service.getStations(this.searchdata.toStation).subscribe(data=>{
        console.log(data.stations)
        console.log(data)
        for (let i = 0; i < data.stations.length; i++) {
          console.log(data.stations[i])
          this.statto.push(data.stations[i])
  
        }
        console.log(this.stat.length)
        
      })
      
     }
 
       }
  

  goToPnrEnq(){
    this.router.navigate(['pnrStatus']);
  }

  onSearch(){
    console.log("this.searchdata")

   console.log(this.searchdata)
     if(this.searchdata.date==null || this.searchdata.fromStation.length==0||this.searchdata.toStation.length==0){
        alert("FILL ALL THE INPUT")
     }
     else{
      console.log(this.searchdata);
      this.router.navigateByUrl('/trainlist',{state:this.searchdata});
     
     }
   
  }


}
