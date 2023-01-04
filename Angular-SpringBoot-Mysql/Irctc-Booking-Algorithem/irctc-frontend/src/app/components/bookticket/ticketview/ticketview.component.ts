import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BookTicket } from 'src/app/entity/bookTicket';
import { PnrenqService } from 'src/app/service/pnrenq.service';


@Component({
  selector: 'app-ticketview',
  templateUrl: './ticketview.component.html',
  styleUrls: ['./ticketview.component.css']
})
export class TicketviewComponent implements OnInit {
  "ticketReview":BookTicket;
  constructor(private ticketService:PnrenqService,private router:Router){ }
  
  "finalTicket":any={};

  ngOnInit(): void {
    this.ticketReview=history.state;
    console.log("thuuuhh")
    console.log(this.ticketReview)
  }
//{trainNo}/${fromSta}/${fromSta}/${date}/${clas}`,b

  bookticket(){
    console.log(this.ticketReview)
   this.ticketService.bookTicket(this.ticketReview,this.ticketReview.clasaa).subscribe(data=>{
    console.log(data)
    this.finalTicket=data;
    this.router.navigateByUrl('/booking/bookedTicked',{state:this.finalTicket});
   });
 }

}
