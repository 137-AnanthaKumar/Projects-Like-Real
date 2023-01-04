import axios from "axios";
import { useState } from "react";


class UserService{


  getPrizeListForContest(contestCode){
    const cust = JSON.parse(sessionStorage.getItem("user"));
  const type = cust.tokenType;
  const token=cust.accessToken;
  const completeToken=type+" "+token;  
  const header ={
      "Authorization":completeToken,
        "Access-Control-Allow-Origin": "*",
    };

    const url="http://localhost:8080/contest/getPrizeList"
   return axios.get(url+"/"+contestCode,{headers:header});
    
  }

getMyTeam(teamId){
  const cust = JSON.parse(sessionStorage.getItem("user"));
  const type = cust.tokenType;
  const token=cust.accessToken;
  const completeToken=type+" "+token;  
  const header ={
      "Authorization":completeToken,
        "Access-Control-Allow-Origin": "*",
    };

    const url="http://localhost:8080/liveUpdates/getMyteamWithPoints"
    return axios.get(url+"/"+teamId,{headers:header})

}


  GetTeamsByRank(contestCode){
    const cust = JSON.parse(sessionStorage.getItem("user"));
    const type = cust.tokenType;
    const token=cust.accessToken;
    const completeToken=type+" "+token;  
    const header ={
        "Authorization":completeToken,
          "Access-Control-Allow-Origin": "*",
      };
   const url="http://localhost:8080/liveUpdates/getTeamsOrderByPoints"
   return axios.get(url+"/"+contestCode,{headers:header})

  }
  GetMyAccountTeams(accountName,contestCode){
    const cust = JSON.parse(sessionStorage.getItem("user"));
    const type = cust.tokenType;
    const token=cust.accessToken;
    const completeToken=type+" "+token;  
    const header ={
        "Authorization":completeToken,
          "Access-Control-Allow-Origin": "*",
      };
      const url="http://localhost:8080/dream11Account/getmyAccountTeams"
      return axios.get(url+"/"+accountName+"/"+contestCode,{headers:header})
  }




getAllTeamsForThisContest(contestCode){
  const cust = JSON.parse(sessionStorage.getItem("user"));
  const type = cust.tokenType;
  const token=cust.accessToken;
  const completeToken=type+" "+token;  
  const header ={
      "Authorization":completeToken,
        "Access-Control-Allow-Origin": "*",
    };
 const url="http://localhost:8080/contest/getTeamsContest"
 return axios.get(url+"/"+contestCode,{headers:header})

 
}


getContestInitiate(accountName){
  const cust = JSON.parse(sessionStorage.getItem("user"));
  const type = cust.tokenType;
  const token=cust.accessToken;
  const completeToken=type+" "+token;  

  const header ={
    "Authorization":completeToken,
       
    "Access-Control-Allow-Origin": "*",
   
   };

   const url="http://localhost:8080/contest/getMyContestInitiate"

   return axios.get(url+"/"+accountName,{headers:header})
}

getContestInitiateFinal(accountName){
  const cust = JSON.parse(sessionStorage.getItem("user"));
  const type = cust.tokenType;
  const token=cust.accessToken;
  const completeToken=type+" "+token;  

  const header ={
    "Authorization":completeToken,
       
    "Access-Control-Allow-Origin": "*",
   
   };

   const url="http://localhost:8080/contest/getMyContestInitiateFinal"

   return axios.get(url+"/"+accountName,{headers:header})
}

  JoinContest(contestId,accountID,viceCaptainId,captainId,playerIdForThisContest){
    const cust = JSON.parse(sessionStorage.getItem("user"));
  const type = cust.tokenType;
  const token=cust.accessToken;
  const completeToken=type+" "+token;  

  const header ={
    "Authorization":completeToken,
       
    "Access-Control-Allow-Origin": "*",
   
   };

   const body={
    "contestId": contestId,
    "accountID":accountID,
    "captainId":captainId,
    "viceCaptainId":viceCaptainId,
    "playerIdForThisContest":playerIdForThisContest
    
   }
const url="http://localhost:8080/contest/joinContest";
const data={};
return axios.post(url,body,{headers:header});


  }
    SignUp(username,email,password,accountname){
        console.log(username);
        console.log(email);
        console.log(password);
        console.log(accountname);
       

          const body={
            "username":username,
            "email":email,
            "password":password,
            "account":{
             "accountName":accountname 
            }
    //         "username":"akilaaaa",
    // "email":"akfff@gmail.com",
    // "password":"afsdf1234",
    // "account":{
    //  "accountName":"setfAjk"  
   // }
        }

        console.log(body)
        console.log(body.email)

        const url="http://localhost:8080/api/auth/signup";
        console.log("Api call")
        return axios.post(url,body);
        
    }
 LogIn(username,password){
      console.log(username);
     
      console.log(password);
     
      const header ={

       
        "Access-Control-Allow-Origin": "*",
       
       };

        const body={
          "username":username,
          "password":password,
         
  //         "username":"akilaaaa",
  // "password":"afsdf1234",
  
      }
      console.log(body)
      console.log(body.password)

      const url="http://localhost:8080/api/auth/signin";
      console.log("Api call")
      return axios.post(url,body,{headers:header});
  }

GetUserById(userId){

  const cust = JSON.parse(sessionStorage.getItem("user"));
  const type = cust.tokenType;
  const token=cust.accessToken;
  const completeToken=type+" "+token;  

  const header ={
    "Authorization":completeToken,
       
    "Access-Control-Allow-Origin": "*",
   
   };

   const url="http://localhost:8080/dream11Account/getUser"
   return axios.get(url+"/"+userId,{headers:header})
}
GetContastById(contastId){
  const cust = JSON.parse(sessionStorage.getItem("user"));
  const type = cust.tokenType;
  const token=cust.accessToken;
  const completeToken=type+" "+token;
  const header ={

      "Authorization":completeToken,
      "Access-Control-Allow-Origin": "*",
     
     };

 const url="http://localhost:8080/contest/getcontestDetail";
 return axios.get(url+"/"+contastId,{headers:header})

}

GetActiveContest(){
  const cust = JSON.parse(sessionStorage.getItem("user"));
const type = cust.tokenType;
const token=cust.accessToken;
const completeToken=type+" "+token;
console.log("ommplete token"+completeToken)

const header ={
     "Authorization":completeToken,
       };

 const url="http://localhost:8080/contest/getAllActiveContest";
 return axios.get(url,{headers:header})
}

}

export default new UserService();