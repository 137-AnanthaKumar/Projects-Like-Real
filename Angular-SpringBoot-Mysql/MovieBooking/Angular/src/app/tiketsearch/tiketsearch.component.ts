import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataService } from '../model/model.module';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-tiketsearch',
  templateUrl: './tiketsearch.component.html',
  styleUrls: ['./tiketsearch.component.css']
})
export class TiketsearchComponent implements OnInit {

  constructor(private router:Router, private servise:ServiceService) { }
  details:any=[]

//   details:any=[
//     {
//         "ticketId": "jU#3ORpw",
//         "noOdMembers": 2,
//         "bookingTime": "15:21:31",
//         "bookingDate": "2022-10-09",
//         "mobileNo": 9600929071,
//         "mailId": "anandkumartpy@gmail.com",
//         "user": null,
//         "movieDetails": {
//             "movieId": "mS#0TK0f",
//             "movieName": "Ponniyin Selval-2",
//             "description": "World super heros",
//             "movieCast": "UA",
//             "prize": 120,
//             "availableSeats": 193,
//             "trainlarUrl": "https://www.youtube.com/watch?v=D4qAQYlgZQs&ab_channel=TipsTamil",
//             "addedTime": "15:16:17",
//             "time": "22:55:00",
//             "date": "2022-10-09",
//             "bookingOpened": false,
//             "movieStatus": "COMING",
//             "screen": {
//                 "screenId": 2,
//                 "type": "2D/DOLBY",
//                 "totalSeats": 195
//             }
//         },
//         "seatsForThisBooking": [
//             {
//                 "seatId": "pF#1qhVeOH",
//                 "seatLine": "E7",
//                 "seatNo": "E7",
//                 "movieId": "mS#0TK0f",
//                 "status": "BOOKED"
//             },
//             {
//                 "seatId": "uN$7ddhgQr",
//                 "seatLine": "D5",
//                 "seatNo": "D5",
//                 "movieId": "mS#0TK0f",
//                 "status": "BOOKED"
//             }
//         ]
//     }
// ]
  ngOnInit(): void {

  }


  ticketdetails:any={}
  onViewTicket(index:number){
    this.ticketdetails=this.details[index]
  //  this.dataService.showTour = 'show';
    this.router.navigateByUrl('/booked',{state:this.ticketdetails});
  }

 inputs:any={
  mobile:'',
  date:Date

 }



 errorMsg:string=""

dataFe:boolean=false;
  getMyTickets(){
    console.log(this.inputs)
       this.servise.getMyTicket(this.inputs.mobile, this.inputs.date).subscribe(data=>{
        this.details=data;
        this.dataFe=true;
        console.log(data)

        if(this.details==null){
          this.errorMsg="NO TICKET FOUND ON THIS NUMBER Please Check your Input"
          console.log("false")
          this.dataFe=false;

        }

       })
  }

}
