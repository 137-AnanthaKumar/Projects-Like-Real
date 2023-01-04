import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookedticketComponent } from './components/bookticket/bookedticket/bookedticket.component';
import { BookticketComponent } from './components/bookticket/bookticket.component';
import { PassengerComponent } from './components/bookticket/passenger/passenger.component';
import { PassengerreviewComponent } from './components/bookticket/passengerreview/passengerreview.component';
import { TicketviewComponent } from './components/bookticket/ticketview/ticketview.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { MyaccontComponent } from './components/myaccounts/myaccont/myaccont.component';
import { PnrstatusComponent } from './components/pnrstatus/pnrstatus.component';
import { RegisterComponent } from './components/register/register.component';
import { TrainlistComponent } from './components/trainlist/trainlist.component';
import { TrainsearchComponent } from './components/trainsearch/trainsearch.component';

const routes: Routes = [
{path:'' ,component:HomeComponent},
{path:'pnrStatus', component:PnrstatusComponent},
{path:'registerirctc',component:RegisterComponent},
{path:'myaccount',component:MyaccontComponent},
{path:'trainlist',component:TrainlistComponent},
{path:'trainsearch', component:TrainsearchComponent},
{path:'booking',component:BookticketComponent,children:[
    {path:'passenger',component:PassengerComponent},
    {path:'passengerreview',component:PassengerreviewComponent},
    {path:'payment',component:TicketviewComponent},
    {path:'bookedTicked',component:BookedticketComponent}
  ]},
  {path:'login',component:LoginComponent}


];


// {path: 'employees', component: EmployeeListComponent},
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
