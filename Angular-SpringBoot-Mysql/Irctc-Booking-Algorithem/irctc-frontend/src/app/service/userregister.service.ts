import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Sample ,Registration} from '../entity/registration';
import { Login } from '../entity/login';
import { RailwayStaions } from '../entity/RailwayStation';

@Injectable({
  providedIn: 'root'
})
export class UserregisterService {

  constructor(private httpClient: HttpClient) {}

  private baseURL = "http://localhost:8080/irctc/auth/validation";
  private regisTraionApi="http://localhost:8080/irctc/auth/signup";
  private loginURL="http://localhost:8080/irctc/auth/signin";
  private tokenValiURL="http://localhost:8080/irctc/auth/tokenVaidate";
 private   stationURL="http://localhost:8080/irctc/enquiry/stations"

 private "api"="https://pet-project-springboot.azurewebsites.net//movie/getMovieforTdy/2022-08-20"



getAvailaAble(sample:Sample):Observable<any>{

    return  this.httpClient.post(`${this.baseURL}`, sample);

}

getMovies(){
  console.log("hii")
  return  this.httpClient.get(`${this.api}`);
}

registerNewUser(register:Registration):Observable<any>{
  return  this.httpClient.post(`${this.regisTraionApi}`, register);
}

logIn(login:Login):Observable<any>{
  return this.httpClient.post(`${this.loginURL}`, login);
}
validateToken(token:string):Observable<any>{
  return this.httpClient.get(`${this.tokenValiURL}/${token}`);
}

getStations(letters:string):Observable<any>{
  return this.httpClient.get<any>(`${this.stationURL}/${letters}`);
}


}
