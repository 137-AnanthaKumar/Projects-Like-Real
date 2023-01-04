import React from 'react'

function ContastCard({contast,setPage,setContastId,setContastCode,setSettingPage}) {
  return (
    <div>{contast.map(contasts=>
        <div className='contastmap' key={contasts.contestId} onClick={()=>{
            setPage(2);
            setContastId(contasts.contestId);
            setContastCode(contast.contestCode)
            console.log(contast.contestCode)
            setSettingPage(3)
        }}>
         <div className='divition1'>
      
           <h3>TIMING :  </h3>
           <p>ENTRE FEE: {contasts.entreFee}</p>
           <p>AVAILABLE PLACE :{contasts.availPlace}</p>
         </div>  
         <div className='divition2'>
          <h3>{contasts.title}</h3> 
      
         </div>
         <div className='divition3'>
          <p>Total Joining :{contasts.totalTeams}</p>
           <p className='para'>ENTRE FEE : {contasts.entreFee}</p>
         </div>
      
        </div>
      )}</div>
  )
}

export default ContastCard