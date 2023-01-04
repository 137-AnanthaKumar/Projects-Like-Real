import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Registration, Sample } from 'src/app/entity/registration';
import { UserregisterService } from 'src/app/service/userregister.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
 form:string="basic";

 register:Registration=new Registration()
  
  constructor(private router: Router, private registerService:UserregisterService) { }
 
  ngOnInit() {
   
  }
  countries = [
    {id: 1, name: "United States"},
    {id: 2, name: "Australia"},
    {id: 3, name: "Canada"},
    {id: 4, name: "Russia"},
    {id: 5, name: "England"},
    {id:6 ,name:"India"},
    {id:7,name:"United KingDom"}
 ];
 states = [
  {id: 1, name: "Tamil Nadu"},
  {id: 2, name: "Karnadaka"},
  {id: 3, name: "Kerala"},
  {id: 4, name: "Delhi"},
  {id: 5, name: "Gujarat"},
 
];

  formControl(y:string){
     this.form=y;
     console.log(this.form)
  }
  agecounter(i: number) {

    return new Array(i);
  }

  formSubmit(){
    this.register;
    console.log(this.register)
  }

 

}
