import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-bookedticket',
  templateUrl: './bookedticket.component.html',
  styleUrls: ['./bookedticket.component.css']
})
export class BookedticketComponent implements OnInit {
  "finalTicket":any={};
  constructor() { }

  ngOnInit(): void {
    this.finalTicket=history.state;
  }

}
