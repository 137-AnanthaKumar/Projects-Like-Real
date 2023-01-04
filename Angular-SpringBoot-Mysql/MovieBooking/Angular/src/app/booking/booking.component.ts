import { Component, OnInit } from '@angular/core';

import { jsPDF } from 'jspdf'
import html2canvas from 'html2canvas';
import { Router } from '@angular/router';
import { DataService } from '../model/model.module';
@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {
currentIndex=0;
  constructor(private router:Router) { }
  details:any={}


showTour:string=""
  ngOnInit(): void {
    this.details=history.state;

    console.log("shoe "+this.showTour)
    if(this.details.ticketId==undefined){
      this.router.navigateByUrl('/bookyourseats');
    }
  }
  captureScreen() {
    let data = document.getElementById('contentToConvert');
    html2canvas(data as any).then(canvas => {
        var imgWidth = 210;
        var pageHeight = 295;
        var imgHeight = canvas.height * imgWidth / canvas.width;
        var heightLeft = imgHeight;
        const contentDataURL = canvas.toDataURL('image/png');
        let pdfData = new jsPDF('p', 'mm', 'a4');
        var position = 0;
        pdfData.addImage(contentDataURL, 'PNG', 0, position, imgWidth, imgHeight)
        pdfData.save(`MyPdf.pdf`);
    });

}}
