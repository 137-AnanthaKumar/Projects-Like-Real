import React, { useEffect, useState } from 'react'
import UserService from '../service/UserService'
import ContestDivision from './MyContest/ContestDivision'
import ContestTeamView from './MyContest/ContestTeamView'
import ViewTeam from './MyContest/ViewTeam'

function CompletedContest({accountName}) {
  const[responseDate,setResponseData]=useState([])
  const[myContestPage,setMyContestPage]=useState(1)
  const[contestCode,setContestCode]=useState()
  const[contestTitle,setContestTitle]=useState()
  const[teamId,setTeamID]=useState()

  useEffect(()=>{
    UserService.getContestInitiateFinal(accountName).then((response=>{
     console.log(response.data)
     setResponseData(response.data)
   })).catch(error=>{
      console.log(error)
    })
},[accountName])


const MyContestPage=()=>{
  if(myContestPage===1){

    
    return<ContestDivision setContestTitle={setContestTitle} setMyContestPage={setMyContestPage} setContestCode={setContestCode} responseDate={responseDate}/>
  }
  else if(myContestPage===2){
    return<ContestTeamView setTeamID={setTeamID} contestTitle={contestTitle} accountName={accountName} setMyContestPage={setMyContestPage}  contestCode={contestCode}/>
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

export default CompletedContest