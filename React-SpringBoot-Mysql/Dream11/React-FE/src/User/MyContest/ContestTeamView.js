import React, { useState } from 'react'
import LeaderBoard from './LeaderBoard';
import Winning from './Winning';

function ContestTeamView({setMyContestPage,accountName,contestCode,setTeamID,contestTitle}) {

    const[contestTeamViewPage,setContestTeamViewPage]=useState(2);
  return (
    <div>TITLE : {contestTitle}
    <p onClick={()=>{setMyContestPage(1)}}>Go Back </p>

    {contestCode}
<hr></hr>
<div className='container' id='fnjf988'>
<div className='container' id={contestTeamViewPage===1? ("helllo"):("hellooo")}> <h4 onClick={()=>{setContestTeamViewPage(1)}}>Winning</h4></div>

 <div className='container' id={contestTeamViewPage===1? ("hellooo"):("helllo")}><h4 onClick={()=>{setContestTeamViewPage(2)}}>LeaderBoard</h4></div>

</div>
<hr/>

    <div className='container' >
        {contestTeamViewPage===1 && <Winning contestCode={contestCode} />}
        {contestTeamViewPage===2 && <LeaderBoard accountName={accountName} setMyContestPage={setMyContestPage} setTeamID={setTeamID} contestCode={contestCode}/>}
    </div>
        </div>
  )
}

export default ContestTeamView