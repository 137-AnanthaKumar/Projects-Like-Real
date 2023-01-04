import React, { useEffect, useState } from 'react'
import './TeamCreate.css';
import { Allrowunder, BatsMan, Bowler, WicketKeper } from './Teams'

function TeamCreate({setPage,myTeamPlayerId,page,onRemovePlayer,lengthofPlayer,playerSelected,onAddPlayer,myteam,players,setMyTeam,setMyTeamPlayerId}) {


 const[playerRolePage,setPlayerRolePage]=useState(1)

 


const PlayerRoleDiv = () => {

    if (playerRolePage === 1) {
      return <WicketKeper
      lengthofPlayer={lengthofPlayer}
       players={players} 
      onRemovePlayer={onRemovePlayer}
      playerSelected={playerSelected}
      setMyTeamPlayerId={setMyTeamPlayerId} 
      myteam={myteam}
      onAddPlayer={onAddPlayer}
      setMyTeam={setMyTeam}/>
    }
    else if (playerRolePage === 2) {
      return <BatsMan players={players}
      onRemovePlayer={onRemovePlayer}
      lengthofPlayer={lengthofPlayer}
      playerSelected={playerSelected}
            myteam={myteam}
            onAddPlayer={onAddPlayer}
      setMyTeamPlayerId={setMyTeamPlayerId} 
      setMyTeam={setMyTeam}/>
    }
    else if (playerRolePage === 3) {
      return <Allrowunder
      onRemovePlayer={onRemovePlayer}
      onAddPlayer={onAddPlayer}
      
      lengthofPlayer={lengthofPlayer}
      playerSelected={playerSelected}
       players={players} 
      setMyTeamPlayerId={setMyTeamPlayerId}
      myteam={myteam}
setMyTeam={setMyTeam}/>
    }
    else if (playerRolePage === 4) {
      return <Bowler players={players} 
      onRemovePlayer={onRemovePlayer}
      playerSelected={playerSelected}
      lengthofPlayer={lengthofPlayer}
          myteam={myteam}
          onAddPlayer={onAddPlayer}
       setMyTeamPlayerId={setMyTeamPlayerId} setMyTeam={setMyTeam}/>
    }
    else if(playerRolePage === 5){
      return (<div>Nothing</div>)
    }
  }

  return (

     <div className='container' id='hellooo988u'>
 <div className='bg-white text-white'>
    <p onClick={()=>{setPage(1)}}> Go BACK</p>
   
    <div className='container' id='teamLengthModel'>
    
    <div className={lengthofPlayer>0  && "bg-primary"}></div>
    <div className={lengthofPlayer>1 && "bg-primary"}></div>
    <div className={ lengthofPlayer>2 && "bg-primary"}></div>
    <div className={ lengthofPlayer>3 && "bg-primary"}></div>
    <div className={ lengthofPlayer>4 && "bg-primary"}></div>
    <div className={ lengthofPlayer>5 && "bg-primary"}></div>
    <div className={ lengthofPlayer>6 && "bg-primary"}></div>
    <div className={ lengthofPlayer>7 && "bg-primary"}></div>
    <div className={ lengthofPlayer>8 && "bg-primary"}></div>
    <div className={lengthofPlayer>9 && "bg-primary"}></div>
    <div className={lengthofPlayer>10  && "bg-primary"}></div>
    <h2 className='text-primary'>{lengthofPlayer}</h2>



    
    </div>
  
      <div className='roleclass'>
        <div onClick={() => {
          setPlayerRolePage(1)
         
        }} className={playerRolePage===1?'roleclas1':'roleclas'}>
          <h6>WICKET KEAPER</h6>
        </div>
        <div onClick={() => {
          setPlayerRolePage(2)
        }} className={playerRolePage===2?'roleclas1':'roleclas'}>
          <h6>BATSMAN</h6>
        </div>
        <div onClick={() => {
          setPlayerRolePage(3)
        }} className={playerRolePage===3?'roleclas1':'roleclas'}>
          <h6>ALLROUNDER</h6>
        </div>
        <div onClick={() => {
          setPlayerRolePage(4)
        }} className={playerRolePage===4?'roleclas1':'roleclas'}>
          <h6>BOWLER</h6>
        </div>

      </div></div>
      <div className='playersScr'>
        {PlayerRoleDiv()}
      </div>


      <div className='container' id='fhfhfhfggdg'>
        <div className='container' onClick={()=>setPage(3)} id={myTeamPlayerId.length>0?("hjf64f1"):("hjf64ff")}><center><h3>View Team</h3></center></div>
        {lengthofPlayer===11 && <div className='container'  id={myTeamPlayerId.length>0?("hjf64f1"):("hjf64ff")}><center><h3>Finish</h3></center></div>
}
 


        </div>
    </div>)
  
}

export default TeamCreate