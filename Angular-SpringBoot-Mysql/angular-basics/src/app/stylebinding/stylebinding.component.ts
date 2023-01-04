import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-stylebinding',
  templateUrl: './stylebinding.component.html',
  styleUrls: ['./stylebinding.component.css']
})
export class StylebindingComponent implements OnInit {

  color:string='blue';
  value:string='Type to Get Value'
  constructor() { }

  ngOnInit(): void {
  }
  

}
