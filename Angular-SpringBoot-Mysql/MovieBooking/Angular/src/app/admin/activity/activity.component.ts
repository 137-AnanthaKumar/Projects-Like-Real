import { Component, Input, OnInit } from '@angular/core';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css']
})
export class ActivityComponent implements OnInit {

  constructor(private service:ServiceService) { }
  @Input() code: number = 0;

  onlyforPerticularEmplyee:boolean=false;
  listgroupt=[0,10]
  numberOfPage=[1]
  currentPage=1
  ngOnInit(): void {
    if(this.code==0){
      this.getData()
   this.onlyforPerticularEmplyee=true
    }
    else{
      this.listgroupt=[0,15]
      this.getDataEmployee()
    }

  }

  trackingLog:any[]=[]
  value:number=0;

  getDataEmployee(){
    this.trackingLog=[]
    this.service.getTrackingDataPer(this.listgroupt[0],this.listgroupt[1],this.code).subscribe(data=>{
       this.trackingLog=data;
    })

    this.value=this.trackingLog.length
  }
  getData(){
    this.trackingLog=[]
    this.service.getTrackingData(this.listgroupt[0],this.listgroupt[1]).subscribe(data=>{
       this.trackingLog=data;
    })

    this.value=this.trackingLog.length

  }

interMediateClice(num:number){

  let a=(num*10)-10
  this.listgroupt=[a,10];
  this.currentPage=num;
  this.getData()

}

  onClickNext(){
console.log(this.trackingLog.length+" "+ this.value)
      // let a=this.listgroupt[1]+1
      // let b=this.listgroupt[1]+10
      // let c=[a,b]
      // this.listgroupt=c;
      // console.log(c)
      // this.numberOfPage.push(this.numberOfPage[this.numberOfPage.length-1]+1)
      // this.currentPage=this.currentPage+1
      // this.getData()


    if((this.trackingLog.length == 10) ){

      let a=this.listgroupt[0]+10
      // let b=this.listgroupt[1]+10
      let c=[a,10]
      this.listgroupt=c;
      console.log(c)
   if(this.currentPage==this.numberOfPage.length){
     this.numberOfPage.push(this.numberOfPage[this.numberOfPage.length-1]+1)
   }


      this.currentPage=this.currentPage+1
      this.getData()
    }


  }

  coClickPrevious(){
console.log("pri")
    if(this.listgroupt[0]>=10){
      // let b=this.listgroupt[0]
      let a=this.listgroupt[0]-10
      let c=[a,10]
      this.listgroupt=c;
      this.currentPage=this.currentPage-1
      this.numberOfPage.pop()
      console.log(c)
      this.getData()
    }



  }

}
