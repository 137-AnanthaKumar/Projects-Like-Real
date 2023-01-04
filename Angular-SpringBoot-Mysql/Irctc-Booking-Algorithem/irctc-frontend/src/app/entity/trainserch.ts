import { Time } from "@angular/common";

export class TrainSearchDetail{
    "fromStation":string;
    "toStation":string;
    "date":Date;
    "classes":string='SL';
    "allclass":string='GEN';
    
}

export interface TrainSearchResponse{
    "tripId":string;
    "fromStation":string;
    "toStaion":string;
    "endOfJourney":Date;
    "dateOfJourney":Date;
    "startTime":Time;
    "endTime":Time;
    "trainno":number;
    "trainName":string;
    "claass":string;
    "classType":string;
 "avaiability":classAvail[];
    
    

    }
export interface classAvail{
           "classs":string;
            "availAble":string;
            "prize":number;
           "loaded":boolean;
}