import React, { useState } from 'react'


function NewTeamProcess({processPage,setProcessPage,setSettingPage}) {


const TeamPage=()=>{
if(processPage===2){
  return (
<div><div> <button onClick={()=>{
      
        setProcessPage(5)
       
      }} className='btn btn-info btn-lg btn-block'>View Registerd Teams</button></div>
      <hr/>
      <div> <button onClick={()=>{
       
        setSettingPage(2)
        setProcessPage(4)

       
      }} className='btn btn-info btn-lg btn-block'>Create New Team</button></div></div>
  )
}
 
}

  return (


      <div>
      {TeamPage()}
     </div>
  )
}

export default NewTeamProcess