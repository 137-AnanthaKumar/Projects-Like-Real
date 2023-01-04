import React, { useEffect, useState } from 'react'
import { AiOutlineDelete } from 'react-icons/ai';

function WicketKeper({Wickep,RemovePlayer}) {

const[WicketKeper,setWicketKeper]=useState([]);

useEffect(()=>{
  setWicketKeper([]);
  console.log("22")
  WKSET();
  console.log("22")
  
},[Wickep])

const WKSET=()=>{
  for(let i=0;Wickep.length>i;i++){
    if(Wickep[i].role==="WK"){
      console.log(Wickep[i])
   
      setWicketKeper(WicketKeper=>[...WicketKeper,Wickep[i]])
      
    }
  }
 
}




  return (
    <>
{WicketKeper.length>0?(
  WicketKeper.map((wk,index)=><div className='bowler'>
    <div>
    Bowler<h1>{wk.name}</h1>
    <h5>{wk.role}</h5> 
    </div>
    <div className='removrt5t' onClick={()=>{
        RemovePlayer(index)}}><AiOutlineDelete size={30}/></div>
    
    </div>)

    ):<div>There is no WK SELECTED</div>}
    
    </>
  )
}

export default WicketKeper