import { AdminComponent } from './admin/admin.component';
import { MoviesComponent } from './movies/movies.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { MovieTicketsSeatsComponent } from './seats/movie-tickets-seats/movie-tickets-seats.component';
import { ReviewComponent } from './review/review.component';
import { BookingComponent } from './booking/booking.component';
import { BookingFailedComponent } from './booking-failed/booking-failed.component';
import { TiketsearchComponent } from './tiketsearch/tiketsearch.component';
import { AdminloginComponent } from './admin/adminlogin/adminlogin.component';

const routes: Routes = [
{path:"home",component:HomeComponent},
{path:"bookyourseats",component:MovieTicketsSeatsComponent},
{path:"movies",component:MoviesComponent},
{path:"review",component:ReviewComponent},
{path:"booked",component:BookingComponent},
{path:"failed",component:BookingFailedComponent},
{path:"ticketsearch", component: TiketsearchComponent},
{path:"adminlogin", component: AdminloginComponent},
{path:"admin",component:AdminComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
