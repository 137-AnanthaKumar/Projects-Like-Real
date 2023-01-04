import React, { useEffect, useState } from 'react'
import UserService from '../service/UserService'
import ContestDivision from './MyContest/ContestDivision'
import ContestTeamView from './MyContest/ContestTeamView'
import '../User/User.css';
import ViewTeam from './MyContest/ViewTeam';

function MyContest({accountName}) {
const[responseDate,setResponseData]=useState([])
const[myContestPage,setMyContestPage]=useState(1)
const[contestCode,setContestCode]=useState()
const[contestTitle,setContestTitle]=useState()

console.log("Account NAem "+accountName)
  useEffect(()=>{
      UserService.getContestInitiate(accountName).then((response=>{
       console.log(response.data)
       setResponseData(response.data)
     })).catch(error=>{
        console.log(error)
      })
},[accountName])
  const[teamId,setTeamID]=useState()

const MyContestPage=()=>{
  if(myContestPage===1){
    return<ContestDivision setContestTitle={setContestTitle} setMyContestPage={setMyContestPage} setContestCode={setContestCode} responseDate={responseDate}/>
  }
  else if(myContestPage===2){
    return<ContestTeamView accountName={accountName} setTeamID={setTeamID} contestTitle={contestTitle} setMyContestPage={setMyContestPage}  contestCode={contestCode}/>
  }

  else if(myContestPage===3){

    return <ViewTeam setMyContestPage={setMyContestPage} teamId={teamId}/>
  }
}





  return (
    <div className='container'>MyContest
       {MyContestPage()}
    </div>
  )
}

export default MyContest