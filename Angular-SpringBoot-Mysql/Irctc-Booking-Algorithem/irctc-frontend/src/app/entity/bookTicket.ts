export class BookTicket{
    "user_id":any
    "fromSta":string
    "tripcode":string
    
    "classType":string
    "clasaa":string
    "paymentMode":"ONLINE"
    "toSta":string
    "mobileNo":number
    "noOfPassenger":number
    "passengers":Passengers[]

}

export class Passengers{
    
    "passengerName":string="";
    "passengerAge":string;
    "gender":string="";
}