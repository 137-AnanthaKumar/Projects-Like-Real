import React, { useEffect, useState } from 'react'
import UserService from '../../service/UserService';
import { BatsMann, WicketKeaperr,AllRounderr,Bowlerr } from '../TeamView/TeamView';

function ViewTeam({teamId,setMyContestPage}) {
    
    const[myteam,setMyTeam]=useState([]);
    const[keeper,setKeeper]=useState([]);
    const[batsman,setBatman]=useState([]);
    const[allrow,setSllRounder]=useState([]);
    const[bowler,setBowler]=useState([]);
   
  useEffect(()=>{


    UserService.getMyTeam(teamId).then((response)=>{
        console.log("MYTEAM USEEFF")

          setMyTeam(response.data.myPlayers)
        console.log(response.data.myPlayers)   
    })
  },[])


  useEffect(()=>{
    SplitThisTeam();
  },[myteam])

  const SplitThisTeam=()=>{
    console.log("MYTEAM")
    console.log(myteam[0])

    for(let i=0;myteam.length>i;i++){
        console.log("MYTEAM")



        if(myteam[i].role==="WK"){
            setKeeper(keeper=>[...keeper,myteam[i]])
          }
        else if(myteam[i].role==="BAT"){
             setBatman(batsman=>[...batsman,myteam[i]])
        }
        else if(myteam[i].role==="ALL-ROW"){
             setSllRounder(allrow=>[...allrow,myteam[i]])
            }
        else if(myteam[i].role==="BOW"){
             setBowler(bowler=>[...bowler,myteam[i]])
            }

    
      }

  }


  return (
    <div className='container' id='viewteamid'> 

    <div className='container' id="viewteamidwk">
       <div className='container' id='viewwkkk'>
       <div className='container' id='dfbfvffgvhs3we'>
      {keeper.length>0?(
        keeper.map((bow,index)=><div className='container' id='djfhhfy756g'>
            <div>
            {bow.captain===true && <div className='capVc'>C</div>}
          {bow.vc===true && <div className='capVc'>VC</div>}
            <h6>{bow.name} ({bow.points})</h6>
            </div>
           
            
            </div>)
        
            ):<div>There is no WK</div>}
            
            </div>

        </div>
    </div>
    <div className='container' id="viewteamidwk">
        <div className='container' id='viewwkkk'>
        <div className='container' id='dfbfvffgvhs3we'>
      {batsman.length>0?(
        batsman.map((bow,index)=><div className='container' id='djfhhfy756g'>
            <div>
            {bow.captain===true && <div className='capVc'>C</div>}
          {bow.vc===true && <div className='capVc'>VC</div>}
            <h6>{bow.name} ({bow.points})</h6>
            </div>
            
            </div>)
        
            ):<div>There is no WK</div>}
            
            </div>
        </div>
    </div>
    <div className='container' id="viewteamidwk"><div className='container' id='viewwkkk'>
    <div className='container' id='dfbfvffgvhs3we'>
      {allrow.length>0?(
        allrow.map((bow,index)=><div className='container' id='djfhhfy756g'>
            <div>
            {bow.captain===true && <div className='capVc'>C</div>}
          {bow.vc===true && <div className='capVc'>VC</div>}
            <h6>{bow.name} ({bow.points})</h6>
            </div>
            
            </div>)
        
            ):<div>There is no WK</div>}
            
            </div>
    
    </div></div>
<div className='container'  id="viewteamidwk"><div className='container' id='viewwkkk'>


<div className='container' id='dfbfvffgvhs3we'>
      {bowler.length>0?(
        bowler.map((bow,index)=><div className='container' id='djfhhfy756g'>
      
            <div>
            {bow.captain===true && <div className='capVc'>C</div>}
          {bow.vc===true && <div className='capVc'>VC</div>}

            <h6>{bow.name} ({bow.points})</h6>
            </div>
            
            </div>)
        
            ):<div>There is no WK</div>}
            
            </div>
</div></div>


    <div className='container' id='buttominTeamview'>
    <button onClick={()=>{setMyContestPage(2)}} className='btn btn-primary'>Close</button>

  </div>
    
    </div>
  )
}

export default ViewTeam