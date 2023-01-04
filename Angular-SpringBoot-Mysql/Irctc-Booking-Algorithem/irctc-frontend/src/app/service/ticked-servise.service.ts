import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TickedServiseService {

  

  constructor(private httpClient: HttpClient) { }
    // private toBookAPI:"http://localhost:8080/irctc/booking/bookticket";

  
}
