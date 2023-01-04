import { Time } from "@angular/common";

export class PNRResponse{
    "msg":boolean;
    "time":any;
    "pnr":number;
    "dateOfJourney":any;
    "toSta":string;
    "fromSta":string;
    "dateOfbooking":any;
    "trainNo":number;
     "passengers":Passenger[]


}

export class Passenger{
    "passe":string
    "seatNo":string
}