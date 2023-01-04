import React, { useEffect, useState } from 'react'

export function BatsMann({myteam}) {
    const[mybat,setBatsmnnn]=useState([]);

  useEffect(()=>{
    console.log("22")
    WKSET();
    console.log("22")
  
  },[myteam])
  
  const WKSET=()=>{
      console.log(myteam)
    for(let i=0;myteam.length>i;i++){
      if(myteam[i].player.role==="BAT"){
      
     
        setBatsmnnn(mybat=>[...mybat,myteam[i]])
        
      }
    }
   
  }
  return (
      <div className='container' id='dfbfvffgvhs3we'>
    {mybat.length>0?(
        mybat.map((bow,index)=><div className='container' id='djfhhfy756g'>
          <div>
          <h6>{bow.player.playerName}</h6>
          </div>
          
          </div>)
      
          ):<div>There is no Bowler</div>}
          
          </div>
  )
}



export function WicketKeaperr({myteam}) {
    const[mybat,setBatsmnnn]=useState([]);

    useEffect(()=>{
      console.log("22")
      WKSET();
      console.log("22")
    
    },[myteam])
    
    const WKSET=()=>{
        console.log(myteam)
      for(let i=0;myteam.length>i;i++){
        if(myteam[i].player.role==="WK"){
        
       
          setBatsmnnn(mybat=>[...mybat,myteam[i]])
          
        }
      }
     
    }
    return (
        <div className='container' id='dfbfvffgvhs3we'>
      {mybat.length>0?(
          mybat.map((bow,index)=><div className='container' id='djfhhfy756g'>
            <div>
            <h6>{bow.player.playerName}</h6>
            </div>
            
            </div>)
        
            ):<div>There is no Bowler</div>}
            
            </div>
    )
  }


  
export function AllRounderr({myteam}) {
    const[mybat,setBatsmnnn]=useState([]);

  useEffect(()=>{
    console.log("22")
    WKSET();
    console.log("22")
  
  },[myteam])
  
  const WKSET=()=>{
      console.log(myteam)
    for(let i=0;myteam.length>i;i++){
      if(myteam[i].player.role==="ALL-ROW"){
      
     
        setBatsmnnn(mybat=>[...mybat,myteam[i]])
        
      }
    }
   
  }
  return (
      <div className='container' id='dfbfvffgvhs3we'>
    {mybat.length>0?(
        mybat.map((bow,index)=><div className='container' id='djfhhfy756g'>
          <div>
          <h6>{bow.player.playerName}</h6>
          </div>
          
          </div>)
      
          ):<div>There is no Bowler</div>}
          
          </div>
  )
  }


  export function Bowlerr({myteam}) {
    const[mybat,setBatsmnnn]=useState([]);

    useEffect(()=>{
      console.log("22")
      WKSET();
      console.log("22")
    
    },[myteam])
    
    const WKSET=()=>{
        console.log(myteam)
      for(let i=0;myteam.length>i;i++){
        if(myteam[i].player.role==="BOW"){
        
       
          setBatsmnnn(mybat=>[...mybat,myteam[i]])
          
        }
      }
     
    }
    return (
        <div className='container' id='dfbfvffgvhs3we'>
      {mybat.length>0?(
          mybat.map((bow,index)=><div className='container' id='djfhhfy756g'>
            <div>
            <h6>{bow.player.playerName}</h6>
            </div>
            
            </div>)
        
            ):<div>There is no Bowler</div>}
            
            </div>
    )
  }

