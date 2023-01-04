import { Component, Input, OnInit } from '@angular/core';
import { TrainSearchDetail, TrainSearchResponse } from 'src/app/entity/trainserch';

@Component({
  selector: 'app-bookticket',
  templateUrl: './bookticket.component.html',
  styleUrls: ['./bookticket.component.css']
})
export class BookticketComponent implements OnInit {
  
  constructor() { }

"trip":any={};
"pages":string="first";


 ngOnInit(): void {
    this.trip=history.state;
    console.log(this.trip)
   
  }

  onChangePage($event:any){
    console.log("emited")
    this.pages=$event;
  }

}
