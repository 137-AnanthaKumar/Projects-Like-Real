import React, { useEffect, useState } from 'react'

import { AiOutlineDelete } from 'react-icons/ai';

function Bowler({Bowler,RemovePlayer}) {
  const[mybow,setMyBow]=useState([]);

  useEffect(()=>{
    setMyBow([])
    console.log("22")
    WKSET();
    console.log("22")
  
  },[Bowler])
  
  const WKSET=()=>{
    for(let i=0;Bowler.length>i;i++){
      if(Bowler[i].role==="BOW"){
      
     
        setMyBow(mybow=>[...mybow,Bowler[i]])
        
      }
    }
   
  }
  

  
  return (
   
<>
{mybow.length>0?(
  mybow.map((bow,index)=><div className='bowler'>
    <div>
    Bowler<h1>{bow.name}</h1>
    <h5>{bow.role}</h5> 
    </div>
    <div className='removrt5t' onClick={()=>{
        RemovePlayer(index)}}><AiOutlineDelete size={30}/></div>
    </div>)

    ):<div>There is no Bowler</div>}
    
    </>
     )
}

export default Bowler