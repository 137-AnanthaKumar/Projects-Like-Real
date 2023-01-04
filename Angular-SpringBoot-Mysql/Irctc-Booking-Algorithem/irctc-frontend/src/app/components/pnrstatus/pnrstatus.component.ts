import { Component, OnInit } from '@angular/core';
import { PNRResponse } from 'src/app/entity/pnrenq';
import { PnrenqService } from 'src/app/service/pnrenq.service';

@Component({
  selector: 'app-pnrstatus',
  templateUrl: './pnrstatus.component.html',
  styleUrls: ['./pnrstatus.component.css']
})
export class PnrstatusComponent implements OnInit {
 serching:boolean=false;
 "pnr":number;
 "dataStastus":boolean=false;
 "pnrdata":PNRResponse ;
  "error":boolean=false;
  constructor(private pnrServise:PnrenqService) { }
  myDate = new Date();
  ngOnInit(): void {

  }

  onSearch(){
    
    this.serching=true;
    console.log(this.pnr)
    
    this.pnrServise.getPnrResult(this.pnr).subscribe(data=>{
     console.log(data)
     
    this.pnrdata=data;
    console.log(this.pnrdata.msg)
    if(this.pnrdata.msg){
      this.error=false;
      this.serching=false;
      this.dataStastus=true;
    }
    else{
      this.dataStastus=false;
      this.serching=false;
      this.error=true;
  
    }
  
   },   error => console.log(error));

  }

}
