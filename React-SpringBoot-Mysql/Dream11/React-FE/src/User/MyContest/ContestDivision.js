import React, { useEffect, useState } from 'react'
import ViewMyTeam from '../ViewMyTeam'

function ContestDivision({responseDate,setMyContestPage,setContestTitle,setContestCode}) {

  const[pageSwitch,setPageSwitch]=useState(1)
  const[myteam,setMyTeam]=useState([]);
   useEffect(()=>{
          
   },[])
 

  return (

    <>
    {pageSwitch===1&& <div className='container' id='mycontestdivv'>

{responseDate.length>0?(responseDate.map((data)=>( <div 
onClick={()=>{setMyContestPage(2)
   setContestCode(data.contestCode)
   setContestTitle(data.title)
}} key={data.contestId} className='container' id='contestviewrfdd'>
     <div className='container' >
  <h2 className='titkrcass' >{data.title}</h2>
         <h5><strong>Entre--</strong>{data.entreFee}.Rs</h5>
     </div>
<progress className="progress" style={{ width: "450px" }} max="100" value={((data.totalTeams-data.availPlace)/data.totalTeams)*100}></progress>
     </div>))):("There is No Contest Joined Yet")}</div>}



     {pageSwitch===2&& <div><ViewMyTeam/></div>}

    </>
    
    
  )
}

export default ContestDivision