import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { Login } from 'src/app/model/model';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-adminlogin',
  templateUrl: './adminlogin.component.html',
  styleUrls: ['./adminlogin.component.css']
})
export class AdminloginComponent implements OnInit {
 errorMsg:string=""
  constructor(private service:ServiceService, private router:Router,private app:AppComponent) { }

  "loginData":Login=new Login();
    ngOnInit(): void {

   }

  adminLogin(){




    if((this.loginData.username===undefined && this.loginData.password===undefined) || (this.loginData.username==="" && this.loginData.password==="")){
      this.errorMsg="Please Enter Details"
     }else if(this.loginData.username===undefined || this.loginData.username===""  ){
      this.errorMsg="Please enter UserName"
     }else if(this.loginData.password===undefined || this.loginData.password===""){
      this.errorMsg="Please enter password"
     }else{
      this.validateUserName()
     }



  }
err:string=""
  // validateUserName(){
  //   console.log(this.loginData)
  //   this.service.login(this.loginData).subscribe(data=>{

  //   },
  //   error => this.err==="Server" // error path)
  // }


  validateUserName(){
  this.service.loginAdmin(this.loginData).subscribe(
    (data) => this.storeDateSeccionStorage(data), // success path
     error => this.err == "" // error path
  );
  }

  storeDateSeccionStorage(data:any){
    console.log(data)

    if(data.message==="YOUR ACCOUNT IS BLOCKED!..CONTACT YOUR MANAGER"){
      alert(data.message)
    }else{
      localStorage.setItem('userId', data.id);
      localStorage.setItem('tokenType',data.tokenType)
      localStorage.setItem('token',data.accessToken);
      localStorage.setItem('role',data.roles[0]);

      this.app.ngOnInit()

      if(data.roles[0]==="ROLE_ADMIN" || data.roles[0]==="ROLE_MANAGER" || data.roles[0]==="ROLE_OWNER" )
      this.router.navigateByUrl('/admin');
      }


  }





}
