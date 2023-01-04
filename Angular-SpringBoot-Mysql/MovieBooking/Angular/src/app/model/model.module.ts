import { Injectable } from '@angular/core';

@Injectable()
export class DataService {

   _showTour: string='';

   set showTour(value: string) {
      this._showTour = value;
   }

   get showTour(): string {
       return this._showTour;
   }

   constructor() {}

}
