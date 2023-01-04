import { Time } from "@angular/common"

export  class MovieDetails{

        "movieId": string
        "movieName": string
        "description": string
        "trainlerUrl": string
        "movieCast": string
        "screen":number
        "prize": number
        "time": Time
        "date": Date
        "bookingOpened": boolean
        "movieStatus": string
        "bookedseats": string[]
        "lockedseats":string[]=[]
        "pathseats":string[]
}

export class BookMovie{
  "movieId":string
"emailId":string
"mobile":number
"selectedSeats":string[]=[]

}

export class BookMovieDetails{
  "selectedSeats":string[]=[]

  "movie":MovieDetails
}

export class AddMovie{
  "movieStatus":string
  "date":Date
  "time":string
  "trainlarUrl":string
  "prize":number
  "description":string
  "movieCast":string
  "bookingOpened":boolean
  "movieName":string
  "days":number
  "screen":number
}

export class Login{
  "username":string;
  "password":string;
}
export class AddAdmin{

  "fullName" : string
  "doorNo" : string
  "area":string
  "salary": number
  "user":{
       "username":string
       "email":string

  }
}
export class header {
  "Authorization":string
  "Access-Control-Allow-Origin": "*"
   };
export class UpdateProfile{
  "email":string
  "username":string
  "fullName":string
  "mobileNo":string
  "password":string
}

export class NewEmployee{
  "fullName" : string
    "doorNo" : string
    "area":string
    "salary":number
    "mobileNo":number
    "user": User
}

export class User{
  "username":string
  "email":string
}
