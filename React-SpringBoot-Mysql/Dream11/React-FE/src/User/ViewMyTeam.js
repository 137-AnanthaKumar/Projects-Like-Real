import React from 'react'
import { AllRounderr, BatsMann, Bowlerr, WicketKeaperr } from './TeamView/TeamView'

function ViewMyTeam({setPage,myteam,page}) {



  return (
    <div className='container' id='viewteamid'> 


    <div className='container' id="viewteamidwk"><div className='container' id='viewwkkk'><WicketKeaperr myteam={myteam}/></div></div>
    <div className='container' id="viewteamidwk"><div className='container' id='viewwkkk'><BatsMann myteam={myteam}/></div></div>
    <div className='container' id="viewteamidwk"><div className='container' id='viewwkkk'><AllRounderr myteam={myteam}/></div></div>


    <div className='container'  id="viewteamidwk"><div className='container' id='viewwkkk'><Bowlerr myteam={myteam}/></div></div>


    <div className='container' id='buttominTeamview'>
    <button onClick={()=>setPage(2)} className='btn btn-primary'>Close</button>
    <button onClick={()=>setPage(4)}  className='btn btn-primary'>Continoue</button>

  </div>
    
    </div>
  )
}

export default ViewMyTeam