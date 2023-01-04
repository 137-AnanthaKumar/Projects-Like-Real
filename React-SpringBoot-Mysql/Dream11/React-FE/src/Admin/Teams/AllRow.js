import React, { useEffect, useState } from 'react'
import { AiOutlineDelete } from 'react-icons/ai';

function AllRow({AllRowder,RemovePlayer}) {
  const[allRound,setAllRounder]=useState([]);

  useEffect(()=>{
    setAllRounder([])
    ALLROW();
   
  
  },[AllRow])
  
  const ALLROW=()=>{
    for(let i=0;AllRowder.length>i;i++){
      if(AllRowder[i].role==="ALL-ROW"){
        
     
        setAllRounder(allRound=>[...allRound,AllRowder[i]])
        
      }
    }
   
  }
  return (
    <>
{allRound.length>0?(
  allRound.map((al,index)=><div key={index} className='bowler'>
    <div>
    Bowler<h1>{al.name}</h1>
    <h5>{al.role}</h5> 
    </div>
    <div className='removrt5t' onClick={()=>{
        RemovePlayer(index)}}><AiOutlineDelete size={30}/></div>
    </div>)

    ):<div>There is no ALLROUNDER SELECTED</div>}
    
    </>
  )
}

export default AllRow