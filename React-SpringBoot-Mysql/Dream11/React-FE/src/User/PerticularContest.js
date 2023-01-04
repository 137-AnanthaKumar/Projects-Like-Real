import React, { useEffect, useState } from 'react'
import UserService from '../service/UserService'
import PeriTiContestDetail from './PeriTiContestDetail'
import TeamCreate from './TeamCreate'
import CapVcSelect from './TeamView/CapVcSelect'
import ViewMyTeam from './ViewMyTeam'

function PerticularContest({contestID,accountId}) {
const[contestInHere,setContestInHere]=useState({})
const[players,setPlayers]=useState([])
const[accoundIdHere,setAccountIdHere]=useState(accountId);
const[state,SetState]=useState(1)
const[captain,setCaptain]=useState();
const[viceCaptain,setViceCaptain]=useState();




const[myteam,setMyTeam]=useState([]);
const[myTeamPlayerId,setMyTeamPlayerId]=useState([])
const[lengthofPlayer,setLengthOfPlayr]=useState(0);

const playerSelected=(playerID)=>{
  const exist = myTeamPlayerId.find((x) => x === playerID);
if(exist){
  return true;
}
else{

  return false;

}
}

const onRemovePlayer=(player)=>{
  setLengthOfPlayr(lengthofPlayer-1)

setMyTeam(myteam.filter((x) => x.player.playerId !== player.playerId));
setMyTeamPlayerId(myTeamPlayerId.filter((x) => x !== player.playerId));
//setMyTeamPlayerId([...myTeamPlayerId(0,player.)])
console.log(myTeamPlayerId)
console.log(myteam)



}


const JoinContest=()=>{
 UserService.JoinContest(contestID,accoundIdHere,viceCaptain,captain,myTeamPlayerId).then((response)=>{
  console.log("hi aki")

   console.log(response.body);
   console.log(response)
   console.log("hi aki")
   if(response.data==="sucess"){
     alert("Contest Joined SuccessFully  ")
   }

   else if(response.data==="BalanceLOW"){
     alert("Your Account Balance is Low")
   }
   else if(response.data==="Allready Joined"){
    alert("Your Already Joined This Contest")
  }

 }).catch(error=>{
  console.log(error)
})

}

const onAddPlayer=(player)=>{
  setLengthOfPlayr(lengthofPlayer+1)

  console.log("clicked")
  console.log(myteam)  

  if(state===1){
    console.log("state 1")
    setMyTeam([...myteam, { ...myteam, player }]);
    // const forStatus={}
    // forStatus.playerID=player.playerId;
    // forStatus.Available=true;
    // console.log(forStatus)
setMyTeamPlayerId(myTeamPlayerId=>[...myTeamPlayerId,player.playerId])
SetState(2);

  }
  else if(state===2){
    console.log("state 2")
    console.log(myteam.length)
    let found=false;
      for(let i=0;i<myteam.length;i++){
          if(player.playerId===myteam[i].player.playerId){
          found=true;
         console.log("Already found")}
        }
      if(!found){
          setMyTeam([...myteam, { ...myteam, player }]);
          // const forStatus={};
          // forStatus.playerID=player.playerId;
          // forStatus.Available=true;
          // console.log(forStatus)
         setMyTeamPlayerId(myTeamPlayerId=>[...myTeamPlayerId,player.playerId])
        }

  }
 
  }


 

  const Cap=(id)=>{
   
    if(captain===id){
      return true;
    }
    else{
      return false;
    }
    }
  
    const ViceCap=(id)=>{
     

   if(viceCaptain===id){
     return true;
   }
   else{
     return 
   }
     
   }


const onAddPlayerId=(id)=>{
console.log(id);
}

const[page,setPage]=useState(1)
const Pages=()=>{
    if(page===1){
        return <PeriTiContestDetail contestInHere={contestInHere} setPage={setPage}/>
        }
    else if(page===2){
        return <TeamCreate onAddPlayer={onAddPlayer} 
         setPage={setPage} page={page}
         lengthofPlayer={lengthofPlayer}
        onRemovePlayer={onRemovePlayer}
        playerSelected={playerSelected}
        myTeamPlayerId={myTeamPlayerId}
        setMyTeamPlayerId={setMyTeamPlayerId}
        players={players} myteam={myteam} 
       setMyTeam={setMyTeam}
        />
    }
    else if(page===3){
      return<ViewMyTeam myteam={myteam} setPage={setPage} page={page}/>
    }
    else if(page===4){
      return <CapVcSelect JoinContest={JoinContest} myteam={myteam} viceCaptain={viceCaptain} captain={captain} ViceCap={ViceCap} Cap={Cap} setViceCaptain={setViceCaptain} setCaptain={setCaptain} setPage={setPage}/>
    }
}

useEffect(()=>{

 UserService.GetContastById(contestID).then((response=>{
    setContestInHere(response.data);
    setPlayers(response.data.players);
    console.log(response.data)
 }))

},[contestID])

  return (
    <div>{Pages()}</div>
  )
}

export default PerticularContest