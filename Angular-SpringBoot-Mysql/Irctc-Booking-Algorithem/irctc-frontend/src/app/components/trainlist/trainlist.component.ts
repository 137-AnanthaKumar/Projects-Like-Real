import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { Login } from 'src/app/entity/login';
import { TrainSearchDetail, TrainSearchResponse } from 'src/app/entity/trainserch';
import { TrainService } from 'src/app/service/trainServise';
import { UserregisterService } from 'src/app/service/userregister.service';
@Component({
  selector: 'app-trainlist',
  templateUrl: './trainlist.component.html',
  styleUrls: ['./trainlist.component.css']
})
export class TrainlistComponent implements OnInit {
 "loading":boolean;
 "notrain":boolean;
 "isLoggeIn":boolean;
 "token":any;
  "searchBy":TrainSearchDetail;
  "peritiCularTrain":TrainSearchResponse;
  "trainlist":TrainSearchResponse[];
  "list":boolean=false;
  "page":boolean=false;

  "stat":string[]=[];
"statto":string[]=[];
"loginData":Login=new Login();

"bookTrain":any={
  cla:"",
  tripid:"",
}






  constructor(private router:Router, private activatedRoute:ActivatedRoute,private service:TrainService,private userservice:UserregisterService,private app:AppComponent) { }
  
  ngOnInit(): void {
   
     this.loading=false;
       this.searchBy=history.state;
       if(this.searchBy.fromStation!=undefined){
        this.getTrainList();
       }
       this.token=this.getToken()
   
      console.log(this.token)
       if(this.token==undefined || this.token==null){
        localStorage.removeItem('userId');
        localStorage.removeItem('tokenType')
       localStorage.removeItem('token');
        console.log("this.token no token")
        this.isLoggeIn=false
       }else{
        console.log("this.token token")
        this.isLoggeIn=true;
       }

  console.log("fianl "+this.isLoggeIn)
      
}

onClick(){
  console.log("text "+ " "+this.isLoggeIn)
}
onSelected(classs:string,tripId:string){
 

  if(classs==this.bookTrain.cla && tripId==this.bookTrain.tripid){
  
    return true;
  }
  else{
   
    return false;
  }

}
selectBokkingTrip(classs:string,tripId:string){

  this.bookTrain={
    cla:classs,
    tripid:tripId,
  }
  console.log(this.bookTrain)

}
onBook(trip:TrainSearchResponse){
   
   trip.claass=this.bookTrain.cla;
   this.router.navigateByUrl('/booking/passenger',{state:trip});
}
getToken(){
  console.log("token get")
  return  localStorage.getItem('token');
}

satTionClick(type:string,value:string){
  if(type=='from'){
       this.searchBy.fromStation=value;
  }
  if(type=='to'){
    this.searchBy.toStation=value;
  }
  this.stat=[];
  this.statto=[];
  
}

onChangeStation(ty:string){
  this.stat=[];
  this.statto=[];
  console.log("keyup")
 if( ty=='from'){
  
  this.userservice.getStations(this.searchBy.fromStation).subscribe(data=>{
    for (let i = 0; i < data.stations.length; i++) {
      this.stat.push(data.stations[i])
    }
       })
  
   }

  if( ty=='to'){
    this.userservice.getStations(this.searchBy.toStation).subscribe(data=>{
         for (let i = 0; i < data.stations.length; i++) {
         this.statto.push(data.stations[i])
      }
                  })
     }
 }

      getTrainList(){
        console.log(this.searchBy)
        this.loading=true;
        this.service.getTrainListForThisDay(this.searchBy.fromStation,this.searchBy.toStation,this.searchBy.date,this.searchBy.classes,this.searchBy.allclass).subscribe(data=>{
         
          this.trainlist=data;
         
          if(this.trainlist.length==undefined){
            this.list=false;
            this.notrain=true;
            this.loading=false;

             }
          if(this.trainlist.length>0){
            this.list=true;
            this.page=true;
            this.notrain=false;
            this.loading=false;
            }
        })
      }


      fetchAvailAble(y:any,classs:string,tripid:string,index:number){
         let a:any=this.trainlist.find(i=> i.tripId===tripid);
         a.avaiability[index].loaded=true;
      }

      Validate(){
       let val=  this.onLogin();
       this.isLoggeIn=val;    
      }

      onLogin():boolean{
        const val=this.app.onLogin(this.loginData,true);
        return val;
        // this.Validate()
        
        // this.isLoggeIn=val;
      
      
  }



     


}
