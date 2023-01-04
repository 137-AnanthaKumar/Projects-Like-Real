import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { TrainSearchResponse } from '../entity/trainserch';
@Injectable({
  providedIn: 'root'
})
export class TrainService {
"searchTrainApi"="http://localhost:8080/irctc/booking/searchtrain";
"availAbleAPI"="http://localhost:8080/irctc/booking/avail";
"passReviewApi"="http://localhost:8080/irctc/booking/passReview"
///12661/TSI/TBM/2022-10-25/1A"
  constructor(private httpClient: HttpClient) { }

  getTrainListForThisDay(from:string,toSta:string,date:Date,claaa:string,classtype:string):Observable<any>{

    return this.httpClient.get<any>(`${this.searchTrainApi}/${from}/${toSta}/${date}/${claaa}/${classtype}`);
  }

  getAvaiAble(trainNo:number,from:string,toSta:string,date:Date,classs:string){
    return this.httpClient.get<any>(`${this.availAbleAPI}/${trainNo}/${from}/${toSta}/${date}/${classs}`)

  }

  getReviewDetails(tripId:string,coachtype:string,clas:string){
         return this.httpClient.get<any>(`${this.passReviewApi}/${tripId}/${coachtype}/${clas}`)
  }


}