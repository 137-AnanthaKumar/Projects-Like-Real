import React, { useEffect, useState } from 'react'
import UserService from '../../service/UserService'

function LeaderBoard({ contestCode, accountName, setMyContestPage, setTeamID }) {
  const [refresh, setRefresh] = useState(1)
  const myAccountName = accountName;
  const [teams, setTeams] = useState([]);
  const [myAccountTeams, setMyAccountteams] = useState([]);
  useEffect(() => {


    UserService.GetTeamsByRank(contestCode).then((response) => {
      console.log(response.data)
      setTeams(response.data);


    }).catch(error => {
      console.log(error)
    })

    UserService.GetMyAccountTeams(accountName, contestCode).then((response => {


      setMyAccountteams(response.data)
      console.log("I am Here Thanaga" + accountName)
      console.log(response.data)
      console.log("woe")
    }))

  }, [refresh])




  return (
    <div className='container'>
      <hr />
      <div className='container' id='anand'>
        <div className='container' id='anand1'> All Teams({teams.length})</div>
        <div className='container' id='anand2'>Points</div>
        <div className='container' id='anand3'>Rank</div>

      </div>

      <hr />
      {myAccountTeams.length > 0 ? (
        myAccountTeams.map((data) => (<div className='container'
          onClick={() => {
            setTeamID(data.teamId)
            setMyContestPage(3)
          }} key={data.teamId} id='saravananultra'>

          <div className='container' id='saravanan0'>ImG</div>
          <div className='container' id='saravanan1'><h3><strong>{data.accountName}({data.teamNumber})</strong></h3></div>
          <div className='container' id='saravanan2'><h3><strong>{data.totalPoits}
            {data.teamStatus === "FINISHED" && data.wiining !== 0 && <div style={{ "color": "yellow" }}><h5>You Won : Rs.{data.wiining}</h5></div>}

          </strong></h3></div>
          <div className='container' id='saravanan3'><h5><strong>{data.myRank}</strong></h5></div>

          <hr />
        </div>))) : (<div>No Teams Joined</div>)}

      {teams.length > 0 ? (
        teams.map((data) => {
          if(data.accountName!==myAccountName){
            return (
          // {data.accountName===myAccountName ? ():("")}
        
          <div className='container'
            onClick={() => {
              if (data.teamStatus === "Live" || data.teamStatus === "FINISHED") {
                setTeamID(data.teamId)
                setMyContestPage(3)
              }
              else {
                alert("Match Not Started ! You Can view everyone Team When match Started")
              }

            }} key={data.teamId} id='saravanan'>



            <div className='container' id='saravanan0'>ImG</div>
            <div className='container' id='saravanan1'><h3><strong>{data.accountName}({data.teamNumber})</strong></h3></div>
            <div className='container' id='saravanan2'><h4><strong>{data.totalPoits}
              {data.teamStatus === "FINISHED" && data.wiining !== 0 && <div style={{ "color": "green" }}><h5> Won : Rs.{data.wiining}</h5></div>}

            </strong></h4></div>
            <div className='container' id='saravanan3'><h4><strong>{data.myRank}</strong></h4></div>

            <hr />
          </div>

        )
          
        }

        } )) : (<div>No Teams Joined</div>)}
    </div>
  )
}

export default LeaderBoard