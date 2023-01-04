import React, { useEffect, useState } from 'react'
import AdminService from '../../../service/AdminService';
import './PerticularContestControl.css';
function PerticularContestControl(props) {
    const { contestIde, contestCodee } = props;
    const [perticularContast, setPeticularContast] = useState({});
    const [playerr, setPlayers] = useState([])
    const [playerId, setPlayerId] = useState();
    const [run, setRun] = useState("")
    const [catches, setCatch] = useState("")
    const [wicket, setWicket] = useState("")
    const [btnStaus, setBtnStaus] = useState("RUN")


    console.log("perti")
    console.log(perticularContast)
    useEffect(() => {

        GetContastById(contestIde);
    }, [contestIde])





    const GetContastById = (contastId) => {
        console.log("Get Contast By Id" + contastId)

        AdminService.GetContastById(contastId).then((response) => {
            setPeticularContast(response.data)
            setPlayers(response.data.players)


        }).catch(error => {
            console.log(error);
        })
    }

const RunUpdatesInAllTeams=()=>{
    console.log("all updatrs dpb")
    AdminService.RunUpdateForAllteams(contestCodee,contestIde).then((response)=>{
       // alert(response.data)
        if(response.data==="DONE"){
            setBtnStaus("RANK")
        }
    })
    SetRanks();
}

const MakeTranseAction=()=>{
    AdminService.MakeTranseAction(contestCodee).then((response)=>{
        alert(response.data);
    })
}

const SetRanks=()=>{
    AdminService.SetRanks(contestCodee).then((response)=>{
        console.log(response.data)
        if(response.data==="SUCESSFULLY_UPDATEDRA"){
            setBtnStaus("RUN")
        }
    })
}
const FinalPrizeList=()=>{
    console.log("wow its rendering")
    AdminService.FinalPrizeCalculation(contestCodee).then((response=>{
        console.log(response.data)
    }))
}

    const UpdatePoints = () => {

        AdminService.UpdateRuns(playerId, contestIde, catches, wicket, run).then((response) => {
            if (response.data === "CATCH_UPDATED") {
                setBtnStaus("RUNinAllDp");
               // alert(response.data)
            }
            else if (response.data === "WICKET_UPDATED") {
                setBtnStaus("RUNinAllDp");
               // alert(response.data)
            }
            else if (response.data === "RUNS_UPDATED") {
                setBtnStaus("RUNinAllDp");
                // alert(response.data)
            }
        })
        RunUpdatesInAllTeams();
    }

const PagesRender=()=>{
    return ( <div className='container' id='hdfffrj746h'>
    <div className='container'>
        <div className='container'>
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
                <input onChange={(e) => {
                    setRun(e.target.value);
                    setWicket("");
                    setCatch("");
                }} type="text" value={run} class="form-control" id="usr" />
            </div>
            <div class="form-group">
                <label for="usr">CATCH</label>
                <input onChange={(e) => {
                    setRun("");
                    setWicket("");
                    setCatch(e.target.value);
                }} type="text" value={catches} class="form-control" id="usr" />
            </div>
            <div class="form-group">
                <label for="usr">WICKET </label>
                <input
                    onChange={(e) => {
                        setRun("");
                        setWicket(e.target.value);
                        setCatch("");
                    }} type="text" value={wicket} class="form-control" id="usr" />
            </div>
           {btnStaus==="RUN"?   (<button onClick={UpdatePoints} 
           type="button" class="btn btn-primary btn-block">UBDATE RUNS</button>):(<button disabled onClick={UpdatePoints} 
           type="button" class="btn btn-primary btn-block">UBDATE RUNS</button>)}


           {btnStaus==="RUNinAllDp"? (<button type="button" onClick={RunUpdatesInAllTeams} 
           class="btn btn-primary btn-block">UPDATE ALL IN DATABASE</button>):(<button type="button" disabled onClick={RunUpdatesInAllTeams} 
           class="btn btn-primary btn-block">UPDATE ALL IN DATABASE</button>)}

           {btnStaus==="RANK" ? (<button type="button" onClick={SetRanks} 
           class="btn btn-primary btn-block">SET RANKS</button>)
           :
           (<button type="button" disabled onClick={SetRanks} 
           class="btn btn-primary btn-block">SET RANKS</button>)}

        </div>



    </div>
    <div className='container text-center d-inline'> 
              <button className='btn btn-warning' onClick={FinalPrizeList} >FINAL CALCULATION</button> 
              <button className='btn btn-warning' onClick={MakeTranseAction}>MAKE TRANSACTION</button> 

     </div>

    <hr />






</div>)
}





    return (
        <div className='container'>
             <div className='container d-flex bg-info'>
                 <div className='container'>Rank UBDATE</div>
                 <div className='container'>Prize UBDATE</div>
                 <div className='container'>View Teams</div>


             </div>
            <div className='container'>{PagesRender()}</div>        
        </div>

    )
}

export default PerticularContestControl;