import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PNRResponse } from '../entity/pnrenq';
import { BookTicket } from '../entity/bookTicket';
@Injectable({
  providedIn: 'root'
})
export class PnrenqService {

  constructor(private httpClient: HttpClient) { }
 baseURL="http://localhost:8080/irctc/enquiry/pnrsearch"
 bookingURL="http://localhost:8080/irctc/booking/bookticket"

getPnrResult(id:number): Observable<PNRResponse>{
  return this.httpClient.get<PNRResponse>(`${this.baseURL}/${id}`);
}
bookTicket(bookingDetail: BookTicket,clas:string):Observable<any>{

  return this.httpClient.post<any>(`${this.bookingURL}/${clas}`,bookingDetail)
}

}
