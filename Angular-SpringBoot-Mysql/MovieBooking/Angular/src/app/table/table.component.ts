import { Component, OnInit } from '@angular/core';
import { Logics } from '../logics';
import { BookMovieDetails, MovieDetails } from '../model/model';
import { MoviesComponent } from '../movies/movies.component';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {
  settingpage:string='all'
  insidecontrol:string='controlspage'
periticuarview:boolean=true;
msg:string=""
tikets:any[]=[]
"moviedetails":BookMovieDetails=new BookMovieDetails() ;
bookedseats:string[]=[];
lockedseats:string[]=[];
pathseats:string[]=[];

"details":MovieDetails;
"movies":any[]=[
  {
      "movieId": "lK#1kY8I",
      "movieName": "ANAND",
      "description": "HI  ANANTHA KUMAR",
      "movieCast": "2",
      "prize": 150,
      "availableSeats": 334,
      "trainlarUrl": "dsfdf",
      "addedTime": "22:37:19",
      "time": "08:15:00",
      "date": "2022-11-19",
      "bookingOpened": true,
      "movieStatus": "2",
      "screen": {
          "screenId": 2,
          "type": "4K/DOLPY",
          "totalSeats": 336
      }
  },
  {
      "movieId": "wR@5@3hw",
      "movieName": "ANAND",
      "description": "HI  ANANTHA KUMAR",
      "movieCast": "2",
      "prize": 150,
      "availableSeats": 336,
      "trainlarUrl": "dsfdf",
      "addedTime": "22:37:18",
      "time": "08:15:00",
      "date": "2022-11-18",
      "bookingOpened": false,
      "movieStatus": "2",
      "screen": {
          "screenId": 2,
          "type": "4K/DOLPY",
          "totalSeats": 336
      }
  }
]
  constructor(private servise:ServiceService, private servicelogic:Logics) { }
date:any
re:any

changeStatus(id:string){
  console.log(id)


this.servise.getMovie(id).subscribe(data=>{
this.view[0]=data
})

}
  ngOnInit(): void {
    this.re=new Date();
    this.date=this.servicelogic.formatDate(this.re)
    this.gettoday();
  }

  view:any=[]
  getView(b:string){
  //  this.periticuarview
console.log(b)
     this.view= this.movies.filter(obj => {
      return obj.movieId === b
    })
    console.log(this.view)
    this.settingpage='perticular'
  }

 PericularSettingControl(val:string){
  this.moviedetails.selectedSeats=[]
if(val==='one-step'){
  this.settingpage='perticular'
}
if(val==='two-step'){
  this.settingpage='all'
}

 }

  PerticularSetting(data:any){
    console.log(data);

    this.settingpage='perticular-setting'
    this.perticularMovieShow(data.movieId);

   }


  gettoday(){
    this.servise.getAllmovieAdmin(this.date).subscribe(data=>{
      this.movies=data;
      })

      if(this.movies.length===0){

      }
  }

  chosenMod:any;
  onChenge(){
    this.settingpage='all'
    if(this.chosenMod==='1'){
         this.gettoday();
    }
    else if(this.chosenMod==='2'){
      this.getAllUpcomingMovie()
    }
    else if(this.chosenMod==='3'){
     this.bookingOpendMovies();
    }
    else{
       this.bookingFalseMovies();
    }
  }
  changepage(){
   this.settingpage='all'
  }


  backToControl(){
    this.insidecontrol="controlspage"
  }

  bookingOpendMovies(){
   this.servise.getAllBokkingOpenMovie().subscribe(data=>{
    this.movies=data;
   })
  }
  bookingFalseMovies(){
    this.servise.getAllBokkingCloseMovie().subscribe(data=>{
      this.movies=data;
     })
  }


  getAllUpcomingMovie(){
    this.movies=[];
    this.servise.getAllUpcomingMovie().subscribe(data=>{
      this.movies=data;
      })
  }

  "line":string[]=['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P']
 "numbers":number[]=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28];

 public selectSeat(seats: string) {
    console.log("59")
    // this.moviedetails.selectedSeats.push(seats)
    console.log(this.moviedetails.selectedSeats)

   if(this.details.bookedseats.includes(seats)  ){
        alert("seat already booked")
   }
 else{
          if(!this.moviedetails.selectedSeats.includes(seats)){

              this.moviedetails.selectedSeats.push(seats);
         }else{
            let a= this.moviedetails.selectedSeats.indexOf(seats);
            this.moviedetails.selectedSeats.splice(a, 1);
           // this.moviedetails.selectedSeats=this.selectedSeats

          }

       }
     //console.log(this.selectedSeats)

  }
 fetchDatahere(data:any){
  if(!data.error){
    this.details=data.obj2
    this.moviedetails.movie=this.details;
    this.bookedseats=this.details.bookedseats
    this.lockedseats=this.details.lockedseats;
    this.pathseats=this.details.pathseats
   // this.moviedetails=
     console.log(this.lockedseats)
    }
   else{
     alert("Something wrong in server !")
   }
 }

  perticularMovieShow(id:string){

    this.servise.getMovieShowDeailsWithBookedSeatsForAdmin(id).subscribe(data=>{
    this.fetchDatahere(data)

    })

 }

avaiable(seatNo:string):string{
if(this.moviedetails.selectedSeats.includes(seatNo)  ){
  return 'filled';
}
else if(this.bookedseats.includes(seatNo)) {
  return 'avail'
}
else if(this.lockedseats.includes(seatNo)){
  console.log("FOUND")
    return 'locked'
}
else if(this.pathseats.includes(seatNo)){
  return 'path'
}
else{
  return 'none'
}

}
finalJson:any={
  mobile:'',
  emailId:'',
  movieId:"",
  selectedSeats:[]
}


listgroupt=[0,10]
numberOfPage=[1]
currentPage=1
interMediateClice(num:number){

  let a=(num*10)-10
  this.listgroupt=[a,10];
  this.currentPage=num;
  // this.getData()

}
coClickPrevious(){
  if(this.listgroupt[0]>=10){
    // let b=this.listgroupt[0]
    let a=this.listgroupt[0]-10
    let c=[a,10]
    this.listgroupt=c;
    this.currentPage=this.currentPage-1
    this.numberOfPage.pop()
    console.log(c)
    //this.getData()
  }
}

onClickNext(){
  if((this.tikets.length == 10) ){

    let a=this.listgroupt[0]+10
    // let b=this.listgroupt[1]+10
    let c=[a,10]
    this.listgroupt=c;
    console.log(c)
 if(this.currentPage==this.numberOfPage.length){
   this.numberOfPage.push(this.numberOfPage[this.numberOfPage.length-1]+1)
 }


    this.currentPage=this.currentPage+1
    this.getData()
  }
}

onClickView(a:string){
  this.insidecontrol="ticketspage"
  this.getData()

}
 getData(){
  this.servise.getBookedTickets(this.moviedetails.movie.movieId,this.listgroupt[0],this.listgroupt[1]).subscribe(data=>{
    this.tikets=data
    console.log(data)
      })
 }

onClickButton(a:string){

    this.bookedseats=[]
    this.lockedseats=[]
    this.pathseats=[]
  this.finalJson.movieId=this.details.movieId
  this.finalJson.selectedSeats= this.moviedetails.selectedSeats

  this.servise.lockSeats(this.finalJson,a).subscribe(data=>{
    this.fetchDatahere(data)
  })

  this.moviedetails.selectedSeats=[]

}




}
