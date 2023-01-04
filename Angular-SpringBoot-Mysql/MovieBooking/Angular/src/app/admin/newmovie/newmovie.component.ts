import { Component, OnInit } from '@angular/core';
import { AddMovie } from 'src/app/model/model';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-newmovie',
  templateUrl: './newmovie.component.html',
  styleUrls: ['./newmovie.component.css']
})
export class NewmovieComponent implements OnInit {



  ngOnInit(): void {
    this.page=true
    this.getScreens();
  }

  "addmovie":AddMovie= new AddMovie()
  constructor(private service:ServiceService) {
  }

  "screens":number[];

  getScreens(){
     this.service.getScreen().subscribe(data=>{

        this.screens=data;
     })
  }





  timeda:any[]=['12:15 AM','12:30 AM', '04:15 AM ','04:30 AM', '08:30 AM','08:45 AM','12:00 PM','12:45 PM','03:15 PM','03:45 PM']
  respons:any;
  page:boolean=true;
  submitmovie(){
   console.log(this.addmovie)

    this.service.addMovieNew(this.addmovie.screen,this.addmovie.days,this.addmovie).subscribe(data=>{

      // console.log(data)
      this.respons=data;
      // this.adminpage='status'
      console.log(this.respons)
    })
  }

}
