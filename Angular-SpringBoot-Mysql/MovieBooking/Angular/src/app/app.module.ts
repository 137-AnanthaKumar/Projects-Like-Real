import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MovieTicketsSeatsComponent } from './seats/movie-tickets-seats/movie-tickets-seats.component';
import { MoviesComponent } from './movies/movies.component';
import { ReviewComponent } from './review/review.component';
import { BookingComponent } from './booking/booking.component';
import { BookingFailedComponent } from './booking-failed/booking-failed.component';
import { SearchComponent } from './search/search.component';
import { TiketsearchComponent } from './tiketsearch/tiketsearch.component'
import { FormsModule } from '@angular/forms';
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';
import { AdminComponent } from './admin/admin.component';
import { DatePipe } from '@angular/common';
import { TableComponent } from './table/table.component';
import { NewmovieComponent } from './admin/newmovie/newmovie.component';
import { AdminloginComponent } from './admin/adminlogin/adminlogin.component';
import { ServiceService } from './service.service';
import { ChatboxComponent } from './admin/chatbox/chatbox.component';
import { EmployeeComponent } from './admin/employee/employee.component';
import { ActivityComponent } from './admin/activity/activity.component';
import { FinanceComponent } from './admin/finance/finance.component';
import {MatDialogModule} from "@angular/material";
import { EmployeeactivityComponent } from './admin/employee/employeeactivity/employeeactivity.component';

// import pdfMake from 'pdfmake/build/pdfmake';
// import pdfFonts from 'pdfmake/build/vfs_fonts';
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    MovieTicketsSeatsComponent,
    MoviesComponent,
    ReviewComponent,
    BookingComponent,
    BookingFailedComponent,
    SearchComponent,
    TiketsearchComponent,
    AdminComponent,
    TableComponent,
    NewmovieComponent,
    AdminloginComponent,
    ChatboxComponent,
    EmployeeComponent,
    ActivityComponent,
    FinanceComponent,
    EmployeeactivityComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule,
    FormsModule,


  ],
  providers: [DatePipe,{provide:HTTP_INTERCEPTORS,useClass:ServiceService,multi:true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
