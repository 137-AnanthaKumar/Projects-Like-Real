import React, { useEffect, useState } from 'react'
import { AiOutlineDelete } from 'react-icons/ai';

function BatsMan({Bats,RemovePlayer}) {

  const[Batsman,setBatsman]=useState([]);

  useEffect(()=>{
    setBatsman([])
    BATSET();
    
  
  },[Bats])
  
  const BATSET=()=>{
    for(let i=0;Bats.length>i;i++){
      if(Bats[i].role==="BAT"){
        console.log(Bats[i])
     
        setBatsman(Batsman=>[...Batsman,Bats[i]])
        
      }
    }
   
  }

  return (
    <>
{Batsman.length>0?(
  Batsman.map((bat,index)=><div key={index} className='bowler'>
    <div>
    Bowler<h1>{bat.name}</h1>
    <h5>{bat.role}</h5> 
    </div>
    <div className='removrt5t' onClick={()=>{
        RemovePlayer(index)}}><AiOutlineDelete size={30}/></div>
    </div>)

    ):<div className='bowler'>There is no Bowler Selrcted</div>}
    
    </>
  )
}

export default BatsMan