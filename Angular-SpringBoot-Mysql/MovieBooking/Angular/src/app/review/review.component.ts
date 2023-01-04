import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BookMovieDetails } from '../model/model';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {

  constructor(private router:Router,private service:ServiceService) { }
  "details":BookMovieDetails;
  moviedetails:any={}
  seats:string[]=[]

  finalJson:any={
    mobile:'',
    emailId:'',
    movieId:"",
    selectedSeats:[]
  }
  prize:number=0;



  ngOnInit(): void {
    this.details=history.state;
    this.seats=this.details.selectedSeats;
    this.moviedetails=this.details.movie;
    console.log(this.seats)
    if(this.seats==undefined){
      this.router.navigateByUrl('/bookyourseats');
    }

    this.amountCal();
  }






  amountCal(){
      this.prize=this.details.movie.prize*this.seats.length
  }

  goback(){
    this.router.navigateByUrl('/bookyourseats');
  }
ticketdetails:any={}
  booking(){
    console.log(this.finalJson)
    this.service.bookTicket(this.finalJson).subscribe(data=>{
      if(!data.error){
        this.ticketdetails=data.obj3;
        alert("Ticket Booked")
        this.router.navigateByUrl('/booked',{state:this.ticketdetails});
      }
      else{

        alert("booking failed "+data.errormsg)
        this.router.navigateByUrl('/bookyourseats')
      }

    })


  }
  errormsg=""
  mobileNoVerify(){
    if(this.finalJson.mobile.toString().length==10){
    this.errormsg=""
    }
    else{
      this.errormsg="Mobile Number must be 10 digit"
    }
  }

  onConfirm(){
    sessionStorage.removeItem('selectedseats')
    this.finalJson.movieId=this.moviedetails.movieId
    this.finalJson.selectedSeats=this.seats;
    console.log(this.finalJson.mobile+"l "+this.finalJson.mobile.toString().length)
    if(this.finalJson.emailId=="" || (this.finalJson.mobile.toString().length==0)){
       alert("Please Enter all the inputs")
    }else{
      if((this.finalJson.mobile.toString().length<10) || (this.finalJson.mobile.toString().length>10)){
        alert("please enter 10 digit mobile no")
        this.errormsg="Mobile Number must be 10 digit"
         }
      else{
        this.errormsg=""
        this.booking();
      }
    }
 console.log("final")
 }

}
