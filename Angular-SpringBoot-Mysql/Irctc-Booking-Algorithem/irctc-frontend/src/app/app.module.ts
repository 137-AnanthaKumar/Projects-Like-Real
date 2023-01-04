import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgSelectModule } from '@ng-select/ng-select';
import { HttpClientModule } from '@angular/common/http'
import { ToastService, AngularToastifyModule } from 'angular-toastify'; 
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { PnrstatusComponent } from './components/pnrstatus/pnrstatus.component';
import { RegisterComponent } from './components/register/register.component';
import { MyaccontComponent } from './components/myaccounts/myaccont/myaccont.component';
import { FormsModule } from '@angular/forms';
import { BookticketComponent } from './components/bookticket/bookticket.component';
import { PassengerreviewComponent } from './components/bookticket/passengerreview/passengerreview.component';
import { TrainsearchComponent } from './components/trainsearch/trainsearch.component';
import { TrainlistComponent } from './components/trainlist/trainlist.component';
import { PassengerComponent } from './components/bookticket/passenger/passenger.component';
import { TicketviewComponent } from './components/bookticket/ticketview/ticketview.component';
import { LoginComponent } from './components/login/login.component';
import { BookedticketComponent } from './components/bookticket/bookedticket/bookedticket.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    PnrstatusComponent,
    RegisterComponent,
    MyaccontComponent,
    BookticketComponent,
    PassengerreviewComponent,
    TrainsearchComponent,
    TrainlistComponent,
    PassengerComponent,
    TicketviewComponent,
    LoginComponent,
    BookedticketComponent,
    
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgSelectModule,
    FormsModule,
    HttpClientModule,
    AngularToastifyModule,

    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
