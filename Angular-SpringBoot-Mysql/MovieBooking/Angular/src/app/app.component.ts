import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent  implements OnInit{

  title = 'movie-booking';
  userName:string=""
  roleNav:string=""
  logoutoption:boolean=false
  constructor( private router:Router) { }
  ngOnInit(): void {
    let id=localStorage.getItem('userId');
    let role=localStorage.getItem('role');

    this.adminValidate(role,id)
  }

  logOut(){

    console.log("logout")
    localStorage.removeItem('userId')
    localStorage.removeItem('role')
    localStorage.removeItem('token')
    localStorage.removeItem('tokenType')
   this.roleNav=""
    this.logoutoption=false
    this.router.navigateByUrl('/adminlogin');
  }
  adminValidate(role:any,id:any){
    console.log(role+" "+id)
  if(role===null || id===null){
  //  this.roleNav="WEl"
  }
  else{

   // let a=role;
    this.roleNav=role;
    console.log(role+""+this.roleNav)

    if(role==="ROLE_ADMIN" || role==="ROLE_MANAGER" || role==="ROLE_OWNER"){
     this.logoutoption=true;

    }
  }


  }

}
