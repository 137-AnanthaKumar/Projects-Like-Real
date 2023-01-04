import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-classbinding',
  templateUrl: './classbinding.component.html',
  styleUrls: ['./classbinding.component.css']
})
export class ClassbindingComponent implements OnInit {
  boo :boolean=true;
  constructor() { }



  onClick(){
  console.log("clicked "+this.boo)
  this.boo=false;

}
  ngOnInit(): void {
  }

}
