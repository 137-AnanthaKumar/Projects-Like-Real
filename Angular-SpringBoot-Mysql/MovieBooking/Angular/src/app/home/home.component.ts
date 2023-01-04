import { Component, OnInit } from '@angular/core';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public currentImage = 0;
  active:boolean=false;
  constructor(private servise:ServiceService) { }
  "c":Date;
  "date":any;

  moviesForThisDay:any={

  obj:[
     {

    "movieId": "NIAS",

      "movieName": "VTV",

      "times": [

          "04:30 PM",

          "09:30 PM"

      ]

},
{

    "movieId": "NIAS",

      "movieName": "ABCD",

      "times": [

          "04:30 PM",

          "09:30 PM"

      ]
}
  ]}
  ngOnInit(): void {
  this.c = new Date()
  this.date=this.formatDate(this.c);
  // this.servise.getMoviesForThisDay(this.date).subscribe(date=>{
  //   console.log(date)
  //   this.moviesForThisDay=date;

  // })
console.log(this.moviesForThisDay.error)

    // console.log(this.formatDate(this.c));
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

public theatreImages=[
  {
    url:'../../assets/entrance.jpg'
  },
  {
    url:'../../assets/interior.jpg'
  }
]
public selectImage(index:number):void {
  this.currentImage=index;
}
}
