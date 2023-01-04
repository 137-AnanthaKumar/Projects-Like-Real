import React, { useEffect, useState } from 'react'

function CapVcSelect({JoinContest,setPage,captain,viceCaptain,Cap,ViceCap,myteam,setViceCaptain,setCaptain}) {
    

    
    return (
        <div className='container' id='dgvhs3tgwe'>
      {myteam.length>0?(
        myteam.map((bow,index)=><div className='container' id='djfy756g'>
            <div className='container'>
            <h4>{bow.player.playerName}</h4>
            </div>
            
<div className='hkrkefmfnnjj'>
            <div className='container'><h4 onClick={()=>{

                if(viceCaptain===bow.player.playerId){
                    console.log(ViceCap)
                    setViceCaptain()
                }
                setCaptain(bow.player.playerId)

            }} id={!Cap(bow.player.playerId)?("dtdftegtd7"):("dtdftegtd8")}>C</h4></div>
            <div className='container'><h4 onClick={()=>{
                if(captain===bow.player.playerId){
                    setCaptain();
                }
                setViceCaptain(bow.player.playerId) 

            }} id={!ViceCap(bow.player.playerId)?("dtdftegtd7"):("dtdftegtd8")}>VC</h4></div>
</div>
            </div>)
        
            ):<div>There is no Bowler</div>}
            
            <div className='container' id='buttominTeamvi'>
    <button onClick={()=>setPage(2)} className='btn btn-primary'>Close</button>
    <button  className='btn btn-primary' onClick={JoinContest}>Continoue</button>

       </div>
            </div>
    )
}

export default CapVcSelect