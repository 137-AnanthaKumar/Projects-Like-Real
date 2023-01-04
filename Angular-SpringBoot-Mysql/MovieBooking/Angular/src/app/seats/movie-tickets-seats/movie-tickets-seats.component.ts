import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { BookMovie, BookMovieDetails, MovieDetails } from 'src/app/model/model';

@Component({
  selector: 'app-movie-tickets-seats',
  templateUrl: './movie-tickets-seats.component.html',
  styleUrls: ['./movie-tickets-seats.component.css'],
})
export class MovieTicketsSeatsComponent implements OnInit {
  urlSafe: SafeResourceUrl | undefined;
  url: string = '';
  'moviedetails': BookMovieDetails = new BookMovieDetails();
  bookedseats: string[] = [];
  pathlockseats: string[] = [];

  // selectedSeats: string[] = [];

  'details': MovieDetails;

  constructor(private router: Router, public sanitizer: DomSanitizer) {}
  isSelected: boolean = true;
  getUrl(): string {
    console.log('getRou');
    return this.url;
  }

  ngOnInit(): void {
    this.details = history.state;
    this.moviedetails.movie = this.details;
    this.urlSafe = this.sanitizer.bypassSecurityTrustResourceUrl(
      this.details.trainlerUrl
    );
    console.log('re render');
    this.fetchOldData();
    if (this.details.trainlerUrl == undefined) {
      this.router.navigateByUrl('/movies');
    }

    this.bookedseats = this.details.bookedseats;

    this.pathlockseats = this.details.pathseats;
  }

  fetchOldData() {
    console.log('dtaa');
    //console.log(JSON.parse(sessionStorage.getItem('selectedseats')|| '{}')
    if (sessionStorage.getItem('selectedseats') != null) {
      this.moviedetails.selectedSeats = JSON.parse(
        sessionStorage.getItem('selectedseats') || '{}'
      );
    }

    // if(this.moviedetails.selectedSeats=null){
    //   this.moviedetails.selectedSeats=[]
    // }
    console.log(sessionStorage.getItem('selectedseats'));
  }

  'line': string[] = [
    'A',
    'B',
    'C',
    'D',
    'E',
    'F',
    'G',
    'H',
    'I',
    'J',
    'K',
    'L',
    'M',
    'N',
    'O',
    'P',
  ];
  'numbers': number[] = [
    1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21,
    22, 23, 24, 25, 26, 27, 28,
  ];

  public selectSeat(seats: string) {
    console.log('59');
    // this.moviedetails.selectedSeats.push(seats)
    console.log(this.moviedetails.selectedSeats);

    if (this.details.bookedseats.includes(seats)) {
      alert('seat already booked');
    } else {
      if (!this.moviedetails.selectedSeats.includes(seats)) {
        if (this.moviedetails.selectedSeats.length == 10) {
          alert('Max 10 can be booked');
        } else {
          this.moviedetails.selectedSeats.push(seats);
          // this.moviedetails.selectedSeats=this.selectedSeats
        }
      } else {
        let a = this.moviedetails.selectedSeats.indexOf(seats);
        this.moviedetails.selectedSeats.splice(a, 1);
        // this.moviedetails.selectedSeats=this.selectedSeats
      }
    }
    //console.log(this.selectedSeats)
  }

  avaiable(seatNo: string): string {
    if (this.moviedetails.selectedSeats.includes(seatNo)) {
      return 'filled';
    } else if (this.bookedseats.includes(seatNo)) {
      return 'avail';
    } else if (this.pathlockseats.includes(seatNo)) {
      return 'path';
    } else {
      return 'none';
    }
  }

  onContinue() {
    sessionStorage.setItem(
      'selectedseats',
      JSON.stringify(this.moviedetails.selectedSeats)
    );
    this.router.navigateByUrl('/review', { state: this.moviedetails });
  }
}
