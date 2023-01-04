import React, { useEffect, useState } from 'react'

export function WicketKeper({players,onRemovePlayer,myteam,onAddPlayer,setMyTeamPlayerId,playerSelected,setMyTeam}) {
    const[selected,setSelected]=useState(false);
    const[wicketKeper,setWicketKeper]=useState([]);
        useEffect(()=>{
          
            WicketSet();
          
        
        },[])
        
        const WicketSet=()=>{
          for(let i=0;players.length>i;i++){
            if(players[i].role==="WK"){
           
              setWicketKeper(wicketKeper=>[...wicketKeper,players[i]])
              
            }
          }
         
        }
        

   

  return (
    <div className='container division' >
  {wicketKeper.length>0?(
    wicketKeper.map((bat)=><div className='container' key={bat.playerId} id={playerSelected(bat.playerId)? ("fhjfyt7"):("fhjfyt6")}>
    <div className='text-white locoImg'></div>


              <div className='container' id={playerSelected(bat.playerId)?('hjgfjfjcf335'):('hjgfjfjcf334')}>
              <h4 className='text-info'>{bat.playerName}</h4>
              <h5 className='text-warning'>{bat.role}</h5> 
              </div>
              {myteam.length<=10 ?( <div className='container' id={playerSelected(bat.playerId)? ("sdhjgfhdherd"):("sdhjgfhdhd")}>
              {
                !playerSelected(bat.playerId)?(
                
                <h3 onClick={()=>{onAddPlayer(bat)}
                }>+</h3>)
                  :(<h3 onClick={()=>{
                      onRemovePlayer(bat)
                      
                      }}>-</h3>)
              }
             
             </div>):( <div className='container' id={playerSelected(bat.playerId)? ("sdhjgfhdherd"):("sdhjgfhdhd")}>
              {
                !playerSelected(bat.playerId)?(
                
                <h3 
                >""</h3>)
                  :(<h3 onClick={()=>{
                      onRemovePlayer(bat)
                      
                      }}>-</h3>)
              }
             
             </div>)}
             
              
              </div>)
          
              ):<div className='bowler'>There is no WicketKeaper  Here</div>}
            
              </div>)
  
}


export function BatsMan({players,setMyTeam,onAddPlayer,onRemovePlayer,playerSelected,setMyTeamPlayerId,myteam}) {
    const[selected,setSelected]=useState(false);

    const[Batsman,setBatsman]=useState([]);
    console.log(BatsMan)
        useEffect(()=>{
          
          BATSET();
          
        
        },[players])
        
        const BATSET=()=>{
          for(let i=0;players.length>i;i++){
            if(players[i].role==="BAT"){
           
              setBatsman(Batsman=>[...Batsman,players[i]])
              
            }
          }
         
        }

    return (
        <div className='container division'>
        {Batsman.length>0?(
            Batsman.map((bat)=><div className='container' key={bat.playerId} id={playerSelected(bat.playerId)? ("fhjfyt7"):("fhjfyt6")}>
            <div className='text-white locoImg'></div>

              <div className='container' id={playerSelected(bat.playerId)?('hjgfjfjcf335'):('hjgfjfjcf334')}>
             
              <h4>{bat.playerName}</h4>
              <h5 className='text-warning'>{bat.role}</h5> 
              </div>
              {myteam.length<=10 ?( <div className='container' id={playerSelected(bat.playerId)? ("sdhjgfhdherd"):("sdhjgfhdhd")}>
              {
                !playerSelected(bat.playerId)?(
                
                <h3 onClick={()=>{onAddPlayer(bat)}
                }>+</h3>)
                  :(<h3 onClick={()=>{
                      onRemovePlayer(bat)
                      
                      }}>-</h3>)
              }
             
             </div>):( <div className='container ' id={playerSelected(bat.playerId)? ("sdhjgfhdherd"):("sdhjgfhdhd")}>
              {
                !playerSelected(bat.playerId)?(
                
                <h3 
                >""</h3>)
                  :(<h3 onClick={()=>{
                      onRemovePlayer(bat)
                      
                      }}>-</h3>)
              }
             
             </div>)}

              
              </div>)
          
              ):<div className='bowler'>There is no W Here</div>}
              </div>)
  }

  export function Bowler({players,setMyTeam,onAddPlayer,playerSelected,onRemovePlayer,setMyTeamPlayerId,myteam}) {
    const[selected,setSelected]=useState(true);

    const[Bowler,setBowler]=useState([]);
    console.log(BatsMan)
        useEffect(()=>{
          
          BATSET();
          
        
        },[players])
        
        const BATSET=()=>{
          for(let i=0;players.length>i;i++){
            if(players[i].role==="BOW"){
           
              setBowler(Bowler=>[...Bowler,players[i]])
              
            }
          }
         
        }

    return (
      <div className='container division'>
        {Bowler.length>0?(
            Bowler.map((bat)=><div className='container' key={bat.playerId} id={playerSelected(bat.playerId)? ("fhjfyt7"):("fhjfyt6")}>
            <div className='text-white locoImg'></div>

              <div className='container'id={playerSelected(bat.playerId)?('hjgfjfjcf335'):('hjgfjfjcf334')}>
             
              <h4>{bat.playerName}</h4>
              <h5 className='text-warning'>{bat.role}</h5> 
              </div>
              {myteam.length<=10 ?( <div className='container' id={playerSelected(bat.playerId)? ("sdhjgfhdherd"):("sdhjgfhdhd")}>
              {
                !playerSelected(bat.playerId)?(
                
                <h3 onClick={()=>{onAddPlayer(bat)}
                }>+</h3>)
                  :(<h3 onClick={()=>{
                      onRemovePlayer(bat)
                      
                      }}>-</h3>)
              }
             
             </div>):( <div className='container' id={playerSelected(bat.playerId)? ("sdhjgfhdherd"):("sdhjgfhdhd")}>
              {
                !playerSelected(bat.playerId)?(
                
                <h3 
                >""</h3>)
                  :(<h3 onClick={()=>{
                      onRemovePlayer(bat)
                      
                      }}>-</h3>)
              }
             
             </div>)}
              
              </div>)
          
              ):<div className='bowler'>There is no Bowler Here</div>}
              </div>)
    
  }

  export function Allrowunder({players,setMyTeam,onAddPlayer,playerSelected,onRemovePlayer,setMyTeamPlayerId,myteam}) {
    const[selected,setSelected]=useState(true);


    const[AllRounder,setAllRounder]=useState([]);
        useEffect(()=>{
          
          BATSET();
          
        
        },[players])
        
        const BATSET=()=>{
          for(let i=0;players.length>i;i++){
            if(players[i].role==="ALL-ROW"){
           
              setAllRounder(AllRounder=>[...AllRounder,players[i]])
              
            }
          }
         
        }
    return (
      <div className='container division'>
        {AllRounder.length>0?(
            AllRounder.map((bat)=><div className='container' key={bat.playerId} id={playerSelected(bat.playerId)? ("fhjfyt7"):("fhjfyt6")}>
            <div className='text-white locoImg'></div>

              <div className='container' id={playerSelected(bat.playerId)?('hjgfjfjcf335'):('hjgfjfjcf334')}>
             
              <h4>{bat.playerName}</h4>
              <h5 className='text-warning'>{bat.role}</h5> 
              </div>
              {myteam.length<=10 ?( <div className='container' id={playerSelected(bat.playerId)? ("sdhjgfhdherd"):("sdhjgfhdhd")}>
              {
                !playerSelected(bat.playerId)?(
                
                <h3 onClick={()=>{onAddPlayer(bat)}
                }>+</h3>)
                  :(<h3 onClick={()=>{
                      onRemovePlayer(bat)
                      
                      }}>-</h3>)
              }
             
             </div>):( <div className='container' id={playerSelected(bat.playerId)? ("sdhjgfhdherd"):("sdhjgfhdhd")}>
              {
                !playerSelected(bat.playerId)?(
                
                <h3 
                >""</h3>)
                  :(<h3 onClick={()=>{
                      onRemovePlayer(bat)
                      
                      }}>-</h3>)
              }
             
             </div>)}
              
              </div>)
          
              ):<div className='bowler'>There is no Bowler Here</div>}
              </div>)
   
  }
