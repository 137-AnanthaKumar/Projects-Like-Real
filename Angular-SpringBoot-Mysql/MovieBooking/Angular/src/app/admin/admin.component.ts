import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { tap } from 'rxjs';
import { AddMovie } from '../model/model';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
})
export class AdminComponent implements OnInit {
  currentTime: any;
  currentDate: any;
  adminpage: string = 'addnew';
  addingmovie: string = 'addmovieform';
  employeeRole: string = '';
  employeeCode:number=0;
  changePass:boolean=false

  navpage: number = 1;
  'addmovie': AddMovie = new AddMovie();
  constructor(public datepipe: DatePipe, private service: ServiceService) {
    this.currentDate = this.datepipe.transform(new Date(), 'MM/dd/yyyy');
    this.currentTime = this.datepipe.transform(new Date(), 'hh:mm');
  }

  'screens': number[];
  ngOnInit(): void {
    let role = localStorage.getItem('userId');

    this.page = true;

    this.getRole();

  }

  getRole() {
    this.service.getRole().subscribe((data) => {
      this.employeeRole = data.message;
      this.employeeCode=data.a;

     this.preValidate(data.status)
    });


  }

  preValidate(a:string){
    console.log(a+" accountstatus")
    if(a==="YETTOACTIVATE"){
      console.log(a+" accountstatus")
      this.adminpage="single-table"
      this.navpage = 2;
      this.changePass=true
    }else{
      this.getScreens();
    }


  }

  manEmployee(a: string): boolean {
    console.log(
      'ROLLEEEEEEEEEEEEEEEEEEEEEEEEE' + ' ' + this,
      this.employeeRole
    );

    switch (this.employeeRole) {
      case 'ROLE_ADMIN':
        if (a === 'emp') {
          return true;
        } else {
          return false;
        }

      case 'ROLE_MANAGER':
        console.log('MANG');
        return true;

      case 'ROLE_OWNER':
        console.log('OWNER');
        return true;
    }

    return true;
  }

  getScreens() {
    this.service.getScreen().subscribe((data) => {
      this.screens = data;
    });
  }

  switchtap1(a: string) {
    this.addingmovie = a;
    switch (a) {
      case 'old-extend': {
        // this.addingmovie='old-extend';
        break;
      }
      case 'extend': {
        //  this.addingmovie='extend';
        break;
      }
    }
  }

  swithctap(a: string) {
    this.adminpage = a;
    console.log(a);
    switch (a) {
      case 'addnew': {
        console.log(a + '' + 1);
        this.navpage = 1;
        break;
      }

      case 'single-table': {
        this.navpage = 2;
        break;
      }

      case 'table': {
        this.navpage = 3;
        break;
      }

      case 'finance': {
        this.navpage = 4;
        break;
      }
      case 'activity': {
        this.navpage = 5;
        break;
      }
    }
  }
  timeda: any[] = [
    '12:15 AM',
    '12:30 AM',
    '04:15 AM ',
    '04:30 AM',
    '08:30 AM',
    '08:45 AM',
    '12:00 PM',
    '12:45 PM',
    '03:15 PM',
    '03:45 PM',
  ];
  respons: any;
  page: boolean = true;
  submitmovie() {
    // console.log(this.addmovie)

    this.service
      .addMovieNew(this.addmovie.screen, this.addmovie.days, this.addmovie)
      .subscribe((data) => {
        // console.log(data)
        this.respons = data;
        this.addingmovie = 'addresponse';
        console.log(this.respons);
      });
  }
}
