
import React, { useEffect, useState } from 'react'
import AdminService from '../../service/AdminService';
import ControllerService from '../../service/ControllerService';
import UserService from '../../service/UserService';
function RunUpdates({contestCodehome, setProcessPage,contastId ,SetStage}) {
    const [perticularContast, setPeticularContast] = useState({});
    const [playerr, setPlayers] = useState([])
    const [playerId, setPlayerId] = useState();
    const[run,setRun]=useState("")
    const[catches,setCatch]=useState("")
    const[wicket,setWicket]=useState("")
    const [playerName, setPlayerName] = useState("No Player selected")

    console.log("perti")
    console.log(perticularContast)
    useEffect(() => {

        GetContastById(contastId);
    }, [contastId])

    
 const UpdateRunAndRank=()=>{
    console.log("run ubdates")

    
      ControllerService.PointsUpdateInAlleams(contestCodehome,contastId).then((response)=>{
        console.log(response.data)
      })

     }

  

    const GetContastById = (contastId) => {
        console.log("Get Contast By Id" + contastId)

        AdminService.GetContastById(contastId).then((response) => {
            setPeticularContast(response.data)
            setPlayers(response.data.players)
            console.log("RUN UBDATE PAGE")
            console.log(response.data);

        }).catch(error => {
            console.log(error);
        })
    }

    const EnableDisableContest=()=>{
        console.log(contastId)
        AdminService.EnableDisableControll(contastId).then((response)=>{
            console.log(response.data)
        });
    }

    const UpdatePoints=()=>{
        
        AdminService.UpdateRuns(playerId,contastId,catches,wicket,run).then((response)=>{
            if(response.data==="CATCH_UPDATED"){
                alert(response.data)
            }
            else if(response.data==="WICKET_UPDATED"){
                alert(response.data)
            }
            else if(response.data==="RUNS_UPDATED"){
                alert(response.data)
            }
        })
        SetStage(false);
        setProcessPage(5)
        setProcessPage(3);
    }



    



    return (
        <div>RunUpdates {contastId} {contestCodehome}

            <div><center>{playerName}</center></div>

            <div>
                <select onChange={(e) => {
                    setPlayerId(e.target.value)

                }} class="form-select form-select-lg mb-3" aria-label=".form-select-lg example">
                    <option selected>SELECT PLAYER</option>
                    {playerr.map(player =>
                        <option key={player.playerId} value={player.playerId}>{player.playerName}</option>
                    )}
                </select>
                <div class="form-group">
                    <label for="usr">RUN</label>
                    <input onChange={(e)=>{
                        setRun(e.target.value);
                        setWicket("");
                        setCatch("");
                    }} type="text" value={run} class="form-control" id="usr" />
                </div>
                <div class="form-group">
                    <label for="usr">CATCH</label>
                    <input  onChange={(e)=>{
                        setRun("");
                        setWicket("");
                        setCatch(e.target.value);
                    }}type="text" value={catches} class="form-control" id="usr" />
                </div>
                <div class="form-group">
                    <label for="usr">WICKET </label>
                    <input 
                     onChange={(e)=>{
                        setRun("");
                        setWicket(e.target.value);
                        setCatch("");
                    }} type="text" value={wicket} class="form-control" id="usr" />
                </div>
                <button onClick={UpdatePoints} type="button" class="btn btn-primary btn-block">UBDATE RUNS</button>
            </div>
            <hr />
            <button onClick={()=>{UpdateRunAndRank()}} type="button" class="btn btn-primary btn-block">UPDATE ALL IN DATABASE</button>
   




     <div id='bigball'>
     {perticularContast.contestLock==="UNLOCKED" && <div onClick={EnableDisableContest} id="lockeddiv"></div>}
         
      {perticularContast.contestLock==="LOCKED" && <div  onClick={EnableDisableContest} className='lockeddiv1'></div>} 

     </div>
        </div>
    )
}

export default RunUpdates