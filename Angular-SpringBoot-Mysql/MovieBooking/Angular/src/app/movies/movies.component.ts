import { Time } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MovieDetails } from '../model/model';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {

  constructor(private servise:ServiceService,private router:Router) { }



  "c":Date;
  "date":any;
  "date_am_pm":any

  moviesForThisDay:any

  ngOnInit(): void {
console.log("oninit")
sessionStorage.removeItem('selectedseats')
  this.c = new Date()
  this.date=this.formatDate(this.c);
  this.date_am_pm=this.formatdate_am_pm(this.date)
  this.servise.getMoviesForThisDay(this.date).subscribe(date=>{
    console.log(date)
    this.moviesForThisDay=date;

  }
  )
console.log(this.moviesForThisDay.error)

    // console.log(this.formatDate(this.c));
  }

  error:boolean=false;
  number=0;
  newDateMovie(day:number){
    this.number=day;
    var currentDate = new Date();
    console.log(day)
    if(day===0){
      currentDate.setDate(currentDate.getDate() + day);
    }
    else{
      currentDate.setDate(currentDate.getDate() + day);
    }

   this.date=this.formatDate(currentDate)
    this.servise.getMoviesForThisDay(this.date).subscribe(date=>{
      console.log(date)
      if(date.error){
        this.error=date.error
      }else{
        this.error=false
        this.moviesForThisDay=date;
      }



    })
  }




 formatDate(c:Date) {
    var d = new Date(c),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2)
        month = '0' + month;
    if (day.length < 2)
        day = '0' + day;

    return [year, month, day].join('-');
}

 days:string[] = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
 nextDaysFromtdy:string[]=[]
formatdate_am_pm(date:string)
{
  var d = new Date(date);
  var dayName = this.days[d.getDay()];
  let a= this.days.indexOf( dayName);
  for(let i=0;i<4;i++){
     console.log("92")
     if((a+1+i)<=this.days.length){
      console.log("1 "+this.days[a+i])
      this.nextDaysFromtdy.push(this.days[a+i]);
     }
     else{
      console.log(a+ " "+i)
      if(i==2){
        this.nextDaysFromtdy.push(this.days[0])
      }
      if(i==3){
        this.nextDaysFromtdy.push(this.days[1])
      }

     // this.nextDaysFromtdy.push(this.days[7-a-i])


     // if((7-a-i)==)

     }

  }
  return dayName;

}



moviedetails:MovieDetails=new MovieDetails();
perticularMovieShow(date:Date,time:any,name:string){
   this.servise.getMovieShowDeailsWithBookedSeats(date,time,name).subscribe(data=>{
    if(!data.error){
      console.log("141")

      this.moviedetails=data.obj2
      console.log(this.moviedetails)
      this.router.navigateByUrl('/bookyourseats',{state:this.moviedetails});

    }
    else{
      alert("Something wrong in server !")
    }
   })
}


}
