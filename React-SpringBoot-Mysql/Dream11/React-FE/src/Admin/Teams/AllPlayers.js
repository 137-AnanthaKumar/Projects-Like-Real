import React from 'react'
import { AiOutlineDelete } from 'react-icons/ai';


function AllPlayers({allplayers,RemovePlayer}) {





  return (

    
    <>
    {allplayers.length>0?(
      allplayers.map((bat,index)=><div key={index} className='bowler'>
        <div>
        PLAYER NAME<h1>{bat.name}</h1>
        <h5>{bat.role}</h5> 
        </div>
        <div className='removrt5t' onClick={()=>{
        RemovePlayer(index)}}><AiOutlineDelete size={30}/></div>
        
        </div>)
    
        ):<div className='bowler'>There is no Player Selrcted</div>}
        
        </>
      )
  
}

export default AllPlayers