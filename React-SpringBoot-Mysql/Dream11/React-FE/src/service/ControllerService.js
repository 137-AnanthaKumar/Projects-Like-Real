
import axios from "axios";

class ControllerService{

PointsUpdateInAlleams(contestCodehome,contastId){
    const cust = JSON.parse(sessionStorage.getItem("admin"));
    const type = cust.tokenType;
    const token=cust.accessToken;
    const completeToken=type+" "+token;
    const header ={
       "Authorization":completeToken,
        };

        const url="http://localhost:8080/adminControll/updatePOintsInAllTeams/DREAM26dPbYc/1";
      return  axios.put(url,{headers:header});


}
    
}
export default new ControllerService();