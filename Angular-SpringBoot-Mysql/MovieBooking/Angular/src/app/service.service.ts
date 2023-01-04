import { HttpClient, HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AddMovie, header, Login, NewEmployee, UpdateProfile } from './model/model';

@Injectable({
  providedIn: 'root'
})
export class ServiceService implements HttpInterceptor {

  private GET_MOVIE_API="http://localhost:8080/movie/getMovieforTdy"
  private GET_MOVIE_PERT="http://localhost:8080/movie/getMovieDetails"
  private BOOK_MOVIE="http://localhost:8080/bookmovieticket/bookticket"
  private GET_MY_BOOKED_MOVIES="http://localhost:8080/bookmovieticket/getticket"
  private GET_BOOKED_MOVIESBYID="http://localhost:8080/bookmovieticket/getAllTicketForthisMovie"
  private POST_MOVOE="http://localhost:8080/movie/addMovie"
  private GET_SCREEN="http://localhost:8080/movie/getScreen"
  private GET_ALLMOVIE_ADMIN="http://localhost:8080/movie/getMovieforTdyforadmin"
  private GET_ALLUPCOMING_MOVIE="http://localhost:8080/movie/getAllUpcomingmovie"
  private GET_MOVIE="http://localhost:8080/movie/status/admin"
  private SET_STATUS="http://localhost:8080/movie/statusChange"
  private GET_OPEN_MOVIE="http://localhost:8080/movie/getBookingOpendMovie"
  private GET_CLOSE_MOVIE="http://localhost:8080/movie/getAllBookingClosed"
  private GET_MOVIE_WITH_BOOKEDFOR_ADMIN="http://localhost:8080/movie/getMovieSeatsadmin/lockseat"
  private GET_UNLOCKSEAT_ADMIN="http://localhost:8080/movie/unlockseat/admin"
  private GET_LOCKSEAT_ADMIN="http://localhost:8080/movie/lockseat/admin"
  private GET_PATH_SEAT="http://localhost:8080/movie/pathlockseat/admin"
  private GET_ADMINLOGIN="http://localhost:8080/petproject/auth/signin/admin"
  private GET_ROLE_API="http://localhost:8080/petproject/manager/control/getRole/workers"
  private GET_EMPLYEEALL_API="http://localhost:8080/petproject/manager/control/getall/workers"
  private GET_EMPLYEEONE_API="http://localhost:8080/petproject/manager/control/getOneWorker/workers"
  private GET_TRACKING="http://localhost:8080/petproject/manager/control/getAllActivity/workers"
  private GET_UPDATEPASS="http://localhost:8080/petproject/manager/control/updatePassword/workers"
  private GET_IPDATEPROFILE="http://localhost:8080/petproject/manager/control/update/workers"
  private GET_ADDEMPLOYEE="http://localhost:8080/petproject/manager/control/addemployee"
  private GET_DELETEEMPLOYEE="http://localhost:8080/petproject/manager/control/deleteEmployee"
  private GET_BLOCK="http://localhost:8080/petproject/manager/control/blockAdmin"
  private GET_ACTIVATE="http://localhost:8080/petproject/manager/control/ActivateAdmin"
  private GET_ROLE_CHANGE="http://localhost:8080/petproject/manager/control/roleChange/workers"
  constructor(private httpClient: HttpClient) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
   // throw new Error('Method not implemented.');
   let token=localStorage.getItem('token')
  let tokenType=localStorage.getItem('tokenType')
  let completeToken=tokenType+" "+token
//let token=""
    let jwttoken=req.clone({
setHeaders:{
  Authorization: completeToken,
  "Access-Control-Allow-Origin": "*",
}
    })
    return next.handle(jwttoken)
  }

"head":Headers=new Headers();
//headers = new HttpHeaders()
//  loadHeader():string{

//   let token=localStorage.getItem('token')
//   let tokenType=localStorage.getItem('tokenType')
//   let completeToken=tokenType+" "+token
//   return completeToken;

// }

getBookedTickets(id:string,a:number,b:number){
  return this.httpClient.get<any>(`${this.GET_BOOKED_MOVIESBYID}/${id}/${a}/${b}`)
}
roleChange(code:number,role:string){
  return this.httpClient.put<any>(`${this.GET_ROLE_CHANGE}/${code}/${role}`,null)
}
blockAdmin(id:number){
  return this.httpClient.put<any>(`${this.GET_BLOCK}/${id}`,null)
}

activate(id:number){
  return this.httpClient.put<any>(`${this.GET_ACTIVATE}/${id}`,null)
}

deleteEmployee(id:number){
  return this.httpClient.delete<any>(`${this.GET_DELETEEMPLOYEE}/${id}`)
}
addEmployee(role:string,employee:NewEmployee){
  return this.httpClient.post<any>(`${this.GET_ADDEMPLOYEE}/${role}`,employee)
}

updatePassword(id:number,data:UpdateProfile){
  return this.httpClient.put<any>(`${this.GET_UPDATEPASS}/${id}`,data)
}

updateProfile(id:number,data:UpdateProfile){
  return this.httpClient.put<any>(`${this.GET_IPDATEPROFILE}/${id}`,data)
}

getTrackingData(a:number,b:number){
  return this.httpClient.get<any>(`${this.GET_TRACKING}/${a}/${b}`)
}
getTrackingDataPer(a:number,b:number,c:number){
  return this.httpClient.get<any>(`${this.GET_TRACKING}/${a}/${b}/${c}`)
}

getOneEmployee(id:number){
  return this.httpClient.get<any>(`${this.GET_EMPLYEEONE_API}/${id}`)
}

getAllEmployee(){
  return this.httpClient.get<any>(`${this.GET_EMPLYEEALL_API}`)
}
 getRole(){
  return this.httpClient.get<any>(`${this.GET_ROLE_API}`)
 }

  loginAdmin(logindata:Login){
    return this.httpClient.post<Login>(`${this.GET_ADMINLOGIN}`,logindata)
  }

  getMoviesForThisDay(date:any){
    return this.httpClient.get<any>(`${this.GET_MOVIE_API}/${date}`)
  }

  getMovieShowDeailsWithBookedSeats(date:any,time:any,name:string){
    return this.httpClient.get<any>(`${this.GET_MOVIE_PERT}/${date}/${time}/${name}`)
  }

  bookTicket(data:any){
      return this.httpClient.post<any>(`${this.BOOK_MOVIE}`,data)
  }

  lockSeats(data:any,a:string){
    return this.httpClient.put<any>(`${this.GET_LOCKSEAT_ADMIN}/${a}`,data)
  }

   getMyTicket(mobile:number,date:any){
    return this.httpClient.get<any>(`${this.GET_MY_BOOKED_MOVIES}/${mobile}/${date}`)
  }

  addMovieNew(screen:number,days:number,movie:AddMovie){
    // let headers = new HttpHeaders({'Content-Type':'application/json; charset=utf-8'});
    // headers = headers.set('Authorization', this.loadHeader() );
    return this.httpClient.post<AddMovie>(`${this.POST_MOVOE}/${screen}/${days}`,movie)
  }

  getScreen(){
    // let headers = new HttpHeaders()
    // headers = headers.set('Authorization', this.loadHeader() );
   // return this.httpClient.get<any>(`${this.GET_SCREEN}`,{headers})
   return this.httpClient.get<any>(`${this.GET_SCREEN}`)
  }

  getAllmovieAdmin(date:any){
    return this.httpClient.get<any>(`${this.GET_ALLMOVIE_ADMIN}/${date}`)
  }

  getAllUpcomingMovie(){
    return this.httpClient.get<any>(`${this.GET_ALLUPCOMING_MOVIE}`)

  }
  getMovieShowDeailsWithBookedSeatsForAdmin(id:string){
    return this.httpClient.get<any>(`${this.GET_MOVIE_WITH_BOOKEDFOR_ADMIN}/${id}`)
  }
  getAllBokkingOpenMovie(){
    return this.httpClient.get<any>(`${this.GET_OPEN_MOVIE}`)

  }
  getAllBokkingCloseMovie(){
    return this.httpClient.get<any>(`${this.GET_CLOSE_MOVIE}`)

  }
  getMovie(id:string){
    return this.httpClient.get<any>(`${this.GET_MOVIE}/${id}`)

  }


  statusChange(idd:string){
    return this.httpClient.get(`${this.SET_STATUS}/${idd}`)
  }
}
