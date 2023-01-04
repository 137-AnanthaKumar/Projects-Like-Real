import axios from "axios";


class AdminService{


 

    
  AddNewTeam(teamName,players){

    
    const cust = JSON.parse(sessionStorage.getItem("admin"));
    const type = cust.tokenType;
    const token=cust.accessToken;
    const completeToken=type+" "+token;  
    const header ={
        "Authorization":completeToken,
        "Access-Control-Allow-Origin": "*",
         };

     const url="http://localhost:8080/teams/addNewTeam"; 
     const body={
         "teamName":teamName,
         "players":players,
     }  
     console.log(body)
     
     return axios.post(url,body,{headers:header})

  }

    UpdateRuns(playerId,contastId,catches,wicket,run){

        const cust = JSON.parse(sessionStorage.getItem("admin"));
    const type = cust.tokenType;
    const token=cust.accessToken;
    const completeToken=type+" "+token;

    const url="http://localhost:8080/liveUpdates/updatesRunAndPoints"
    const header ={
        "Authorization":completeToken,
      
         };

         const body={ "playerId":playerId,
         "contestId":contastId,
         "runs":run,
         "catches":catches,
         "wicket":wicket
     }

     return axios.put(url,body,{headers:header})
    }

    RunUpdateForAllteams(contestCodee,contestIde){
        console.log(typeof contestIde)
        const cust = JSON.parse(sessionStorage.getItem("admin"));
        const type = cust.tokenType;
        const token=cust.accessToken;
        const completeToken=type+" "+token;
    
        const url="http://localhost:8080/liveUpdates/updateallTeamsInDp"
        const header ={
            "Authorization":completeToken,
          
             };

         const body={
            "contestId":contestIde,
            "contestCode":contestCodee
         }    
         console.log(body)
         return axios.put(url,body,{headers:header} )    
    }
    SetRanks(contestCodee,contestIde){
        console.log(typeof contestCodee)
        const cust = JSON.parse(sessionStorage.getItem("admin"));
        const type = cust.tokenType;
        const token=cust.accessToken;
        const completeToken=type+" "+token;
    
        const url="http://localhost:8080/liveUpdates/setranks"
        const header ={
            "Authorization":completeToken,
          
             };

         const body={
            "contestId":contestIde,
            "contestCode":contestCodee
         }    
        console.log(body)
         return axios.put(url,body,{headers:header} )    
    }
    
   
   addNewContest(title,teamId,contestAmount,totalteams,maxTeamsPerUser,entreeFee,rankListForWinners,winpercentage,finalWinners){

    const cust = JSON.parse(sessionStorage.getItem("admin"));

    const type = cust.tokenType;
    const token=cust.accessToken;
    const completeToken=type+" "+token;
const url="http://localhost:8080/contest/addNewContests";
const header ={
    "Authorization":completeToken,
    "Access-Control-Allow-Origin": "*",
     };
     
const body={
    "title":title,
    "teamsId":teamId,
    "contestAmount":contestAmount,
    "totalteams":totalteams,
    "maxTeamsPerUser":maxTeamsPerUser,
    "entreFee":entreeFee,
    "prizeList":rankListForWinners,
    "winningPercentage":winpercentage,
    "finalWinners":finalWinners,
}
console.log(body)
return axios.post(url,body,{headers:header})



   }

    getAllTeams(){
        const cust = JSON.parse(sessionStorage.getItem("admin"));
        const type = cust.tokenType;
        const token=cust.accessToken;
        const completeToken=type+" "+token;
        const header ={
           "Authorization":completeToken,
            };
            const url="http://localhost:8080/teams/getAllCricketTeams";
            return axios.get(url,{headers:header})

    }



    EnableDisableControll(contastId){
        const cust = JSON.parse(sessionStorage.getItem("admin"));
        const type = cust.tokenType;
        const token=cust.accessToken;
        const completeToken=type+" "+token;
    console.log(completeToken)

    let config = {
        headers: {
            "Authorization":completeToken,
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json"
        }}
    
       const url="http://localhost:8080/contest/enableDisableContest";

       return axios.post(url+"/"+contastId,config)

}
    GetContastById(contastId){
        const cust = JSON.parse(sessionStorage.getItem("admin"));
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
    const cust = JSON.parse(sessionStorage.getItem("admin"));
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

 GetActiveContestOnly(){
    const cust = JSON.parse(sessionStorage.getItem("admin"));
    const type = cust.tokenType;
    const token=cust.accessToken;
    const completeToken=type+" "+token;
   console.log("ommplete token"+completeToken)

      const header ={
           "Authorization":completeToken,
             };

       const url="http://localhost:8080/contest/getAllActiveContestonly";
       return axios.get(url,{headers:header})
 }

 FinalPrizeCalculation(contestCode){
    const cust = JSON.parse(sessionStorage.getItem("admin"));
    const type = cust.tokenType;
    const token=cust.accessToken;
    const completeToken=type+" "+token;
   console.log("ommplete token"+completeToken)

      const header ={
           "Authorization":completeToken,
             };

             const body={
                "contestCode":contestCode,
             }
    const url="http://localhost:8080/adminControll/prizeListCalculation";
    return axios.put(url,body,{headers:header})

 }

 MakeTranseAction(contestCode){
    const cust = JSON.parse(sessionStorage.getItem("admin"));
    const type = cust.tokenType;
    const token=cust.accessToken;
    const completeToken=type+" "+token;
   console.log("ommplete token"+completeToken)

      const header ={
           "Authorization":completeToken,
           "Access-Control-Allow-Origin": "*",

             };

             const body={
                "contestCode":contestCode,
             }
    const url="http://localhost:8080/transAction/tranActionMethod";
    return axios.post(url,body,{headers:header})

 }


 GetLiveContestOnly(){
    const cust = JSON.parse(sessionStorage.getItem("admin"));
    const type = cust.tokenType;
    const token=cust.accessToken;
    const completeToken=type+" "+token;
   console.log("ommplete token"+completeToken)

      const header ={
           "Authorization":completeToken,
             };

       const url="http://localhost:8080/contest/getAllLiveContestonly";
       return axios.get(url,{headers:header})
 }

}
export default new AdminService();
