import React, { useState } from 'react'
import AllContest from './AllContest/AllContest';
import PerticularContestControl from './AllContest/PerticularContestControl';
import './FinalContestSetting.css';
function FinalContestSetting({setMainAdminControl}) {


 const [pages,setPages]=useState("allContests");
 const[contestCodee,setContestCodee]=useState();
 const[contestIde,setContestIde]=useState();

  return (
  <div className='container'>
    <div className='container' id='fin23e'>
   <div onClick={()=>{
        setMainAdminControl(0)
    }} className='container'><h4>Home</h4></div>
   <div className='container' onClick={()=>{setPages("allContests")}}><h4>All Contest</h4></div>
   <div className='container' onClick={()=>{setPages("prizeCall")}}><h4>Prize Calculation</h4></div>
   <div className='container' onClick={()=>{setPages("viewww")}}><h4> View</h4></div>

  </div>
  <div className='container' id='hjn5554e'> 
  {pages==="allContests" &&  <div className='container' id='allcontestss'></div>}
  {pages==="prizeCall" &&  <div className='container' id='prizeCall'></div>}
  {pages==="viewww" &&  <div className='container' id='viewww'></div>}

</div>
<div className='container' id='gamerole'>
 {pages==="allContests" && <AllContest setContestIde={setContestIde}
                                     setContestCodee={setContestCodee}
                                     setPages={setPages}
 />}
 {pages==="perticularContest" && <PerticularContestControl contestIde={contestIde}
                                                        contestCodee={contestCodee}
 />}



</div>


    </div>
  )
}

export default FinalContestSetting