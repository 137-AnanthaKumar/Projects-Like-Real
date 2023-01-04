import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-directives',
  templateUrl: './directives.component.html',
  styleUrls: ['./directives.component.css']
})
export class DirectivesComponent implements OnInit {
b:boolean=true;
c:string="SHOWSAMPLE";
btnname:string='HIDE';
  constructor() { }

 ngOnInit(): void {
  }

  myevent1(){
this.c="NONE";
console.log("hdhhd")  }
  myevent(){
 
   if(this.b){
      this.b=false;
      this.btnname='SHOW'
   }
   else{
    this.b=true;
    this.btnname='HIDE'


   }

  }




}
