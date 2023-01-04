import { Component } from '@angular/core';
import { Login } from './entity/login';
import { TrainSearchDetail } from './entity/trainserch';
import { UserregisterService } from './service/userregister.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'trainBooking';
 isLoggeIs=false;
"token":any;
 "loginData":Login=new Login();
"dataa":any;
 constructor(private userServise:UserregisterService) { }

  ngOnInit(): void {
    console.log("onint")
   this.getTokenAndValidate();
this.userServise.getMovies().subscribe(dade=>{
  console.log(dade)
});

  }
  getTokenAndValidate(){
    console.log("validate start")
   this.token=this.getToken();
   this.userServise.validateToken(this.token).subscribe(data=>{
    if(data.message=='valid'){
     this.isLoggeIs=true;
    }
    if(data.message=='notvalid'){
      this.isLoggeIs=false;
      localStorage.removeItem('token');
    }
    console.log(data)
    console.log("validate end")

   })

  }

  getToken(){
    console.log("token get")
    return  localStorage.getItem('token');
  }
onLogOut(){
  localStorage.removeItem('userId');
  localStorage.removeItem('tokenType')
  localStorage.removeItem('token');
  this.isLoggeIs=false;
}

 onLogin(data:Login,loginfromOut:boolean):boolean{

  if(loginfromOut){
    this.userServise.logIn(data).subscribe(data=>{
      if(data.accessToken){
        console.log("Sucess")
        console.log(data)
        localStorage.setItem('userId', data.id);
        localStorage.setItem('tokenType',data.tokenType)
        localStorage.setItem('token',data.accessToken);
        this.isLoggeIs=true;
      }


    });
  }
  if(!loginfromOut){
    this.userServise.logIn(this.loginData).subscribe(data=>{
      if(data.accessToken){
        console.log("Sucess")
        localStorage.setItem('userId', data.id);
        localStorage.setItem('tokenType',data.tokenType)
        localStorage.setItem('token',data.accessToken);
        this.isLoggeIs=true;


      }


    });
  }



    let tok= this.getToken()
    if(tok!=undefined||tok!=null){
      return true;
    }
    else{
      return false;
    }



 }




}
