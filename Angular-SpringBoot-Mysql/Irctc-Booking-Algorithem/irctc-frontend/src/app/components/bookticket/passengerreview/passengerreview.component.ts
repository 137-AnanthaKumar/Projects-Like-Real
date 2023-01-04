import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BookTicket } from 'src/app/entity/bookTicket';
import { TrainService } from 'src/app/service/trainServise';

@Component({
  selector: 'app-passengerreview',
  templateUrl: './passengerreview.component.html',
  styleUrls: ['./passengerreview.component.css']
})
export class PassengerreviewComponent implements OnInit {

  "ticketReview":BookTicket;

  "trainCurrenyDetail":any={};
  constructor(private service:TrainService,private router:Router) { }

  ngOnInit(): void {

    this.ticketReview=history.state;
   

    console.log(this.ticketReview)
    this.getAvailDetail();
  }

  getAvailDetail(){
   this.trainCurrenyDetail=this.service.getReviewDetails(this.ticketReview.tripcode,this.ticketReview.classType,this.ticketReview.clasaa).subscribe(data=>{
    this.trainCurrenyDetail=data;
    console.log("hii review");
    console.log(this.trainCurrenyDetail)
   })
  }

  paymentPage(){
    this.router.navigateByUrl('/booking/payment',{state:this.ticketReview});
  }

}
