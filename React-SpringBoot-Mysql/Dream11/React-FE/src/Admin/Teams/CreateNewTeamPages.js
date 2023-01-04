import React, { useState } from 'react'
import WicketKeper from './WicketKeper';
import BatsMan from './BatsMan';
import AllRow from './AllRow';
import Bowler from './Bowler';
import AllPlayers from './AllPlayers';


function CreateNewTeamPages({ allplayers,teamName,playerRolePage ,SetAllPlayers,setPlayerRolePage}) {

  function RemovePlayer(index){
    console.log("index "+index)
    SetAllPlayers(allplayers => {
      return allplayers.filter((value, i) => i !== index)
    })
  }

  
  const PlayerRoleDiv = () => {

    if (playerRolePage === 1) {
      return <WicketKeper RemovePlayer={RemovePlayer} Wickep={allplayers}/>
    }
    else if (playerRolePage === 2) {
      return <BatsMan RemovePlayer={RemovePlayer} Bats={allplayers}/>
    }
    else if (playerRolePage === 3) {
      return <AllRow RemovePlayer={RemovePlayer} AllRowder={allplayers}/>
    }
    else if (playerRolePage === 4) {
      return <Bowler RemovePlayer={RemovePlayer} Bowler={allplayers} />
    }
    else if(playerRolePage === 5){
      return <AllPlayers RemovePlayer={RemovePlayer}  allplayers={allplayers}/>
    }
  }

  return (
    <div>
      <div><button type="button" class="btn btn-outline-primary btn-lg btn-block">CREATING NEW TEAM</button></div>

      <div className="container-fluid">
        {/* <center><h1>{teamName==""?(<h4>Team Name</h4>):({teamName})} </h1></center> */}
        <center><h1>{teamName} </h1></center>

        <hr/>

      </div>
      <div className='roleclass'>
        <div onClick={() => {
          setPlayerRolePage(1)
         
        }} className={playerRolePage==1?'roleclas1':'roleclas'}>
          <h6>WICKET KEAPER</h6>
        </div>
        <div onClick={() => {
          setPlayerRolePage(2)
        }} className={playerRolePage==2?'roleclas1':'roleclas'}>
          <h6>BATSMAN</h6>
        </div>
        <div onClick={() => {
          setPlayerRolePage(3)
        }} className={playerRolePage==3?'roleclas1':'roleclas'}>
          <h6>ALLROUNDER</h6>
        </div>
        <div onClick={() => {
          setPlayerRolePage(4)
        }} className={playerRolePage==4?'roleclas1':'roleclas'}>
          <h6>BOWLER</h6>
        </div>

      </div>
      <div>
        {PlayerRoleDiv()}
      </div>
    </div>)
}

export default CreateNewTeamPages