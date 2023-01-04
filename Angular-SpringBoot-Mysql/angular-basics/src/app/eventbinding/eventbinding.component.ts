import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-eventbinding',
  templateUrl: './eventbinding.component.html',
  styleUrls: ['./eventbinding.component.css']
})
export class EventbindingComponent implements OnInit {
 value='Event Binding'

 msg:string='';
  constructor() { }

  ngOnInit(): void {
  }
  event(){
    this.value="Wow Its Works Fine"
  }
  eventMy(x:any){
    // this.msg=x.target.value;
    this.msg=x.target.value;
  }
  eventMy1(y:any){
   this.msg=y;
  }

}
