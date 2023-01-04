import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { BookTicket, Passengers } from 'src/app/entity/bookTicket';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-passenger',
  templateUrl: './passenger.component.html',
  styleUrls: ['./passenger.component.css']
})
export class PassengerComponent implements OnInit {
 data:any={}

@Output() newItemEvent = new EventEmitter<string>();
  constructor(private router:Router) { }


"passenger":Passengers=new Passengers();
"ticketDetail":BookTicket=new BookTicket();
"passengers":Passengers[]=[{
  passengerName:"",
  gender:"",
  passengerAge:""
}];

"singlePsg":any={};

ngOnInit(): void {
  this.data=history.state;
  console.log("here")
  console.log(this.data)
 }

 onKeyUp(value:any,type:string,index:number){
  // let a:any=this.trainlist.find(i=> i.tripId===tripid);
  this.passenger=this.passengers[index];

  switch(type) {
    case 'age': {
      this.passenger.passengerAge=value;

       break;
    }
    case 'name': {
      this.passenger.passengerName=value;
      break;
    }
    case 'gender': {
     this.passenger.gender=value;
      break;
   }

 }
 this.passengers[index]=this.passenger;


 }

  addNewPassenger(){

    if(this.passengers.length<6){
      this.passenger={
        passengerName:"",
        gender:"",
        passengerAge:""
      };
      this.passengers.push(this.passenger)

      console.log(this.passengers)
    }
    else{
      alert("6 Passengers only allowed")
    }


 }

 removePassenger(index:number){
 this.passengers.splice(index,1);
 }

 getuserId(){
  return localStorage.getItem('userId')
 }


 onContinue(){

 this.ticketDetail.passengers=this.passengers;

 this.ticketDetail.clasaa=this.data.claass;
 this.ticketDetail.fromSta=this.data.fromStation;
 this.ticketDetail.toSta=this.data.toStaion;
 this.ticketDetail.tripcode=this.data.tripId;
 this.ticketDetail.noOfPassenger=this.passengers.length;
 this.ticketDetail.user_id=this.getuserId();
 this.ticketDetail.classType=this.data.classType;
 this.ticketDetail.paymentMode="ONLINE";
 console.log(this.ticketDetail);

 this.newItemEvent.emit('psg');
 this.router.navigateByUrl('/booking/passengerreview',{state:this.ticketDetail});
}

}
