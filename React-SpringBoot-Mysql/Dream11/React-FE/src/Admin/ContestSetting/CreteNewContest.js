import React, { useEffect, useState } from 'react'
import AdminService from '../../service/AdminService';

function CreteNewContest({prizeListStatus,winpercentage,rankListForWinners,setWinningPercen, setProcessPage, entreeFee, setEntreFee, setContestAmount, setTotalTeams, contestAmount, totalteams }) {
    const [title, setTitle] = useState("");
    const [teams, setTeams] = useState([]);
    const [team1, setTeam1] = useState();
    const [team2, setTeam2] = useState();
    const [manualprize, setManualprize] = useState(true)
    const[totalteam,SetTotalTeamHere]=useState()
   const[finalWinners,setFinalWinners]=useState()
   const [maxTeamsPerUser, setMaxTeamsPerUser] = useState();

useEffect(()=>{
    setFinalWinners(totalteam*(winpercentage/100));
},[winpercentage])


    const OnSubmit = () => {
        const teamId = [];
        teamId.push(team1);
        teamId.push(team2)
        console.log(teamId);
        AdminService.addNewContest(title, teamId, contestAmount, totalteams, maxTeamsPerUser, entreeFee,rankListForWinners,winpercentage,finalWinners).then((response) => {
            if (response.data === "Contest Added Successfully") {
                alert(response.data)
            }
        });
    }

    useEffect(() => {
        console.log("UseEffect ContestAmount")
        setContestAmount(entreeFee * totalteams)

    }, [totalteams])

    useEffect(() => {
        GetTeams();
    }, []);

    const GetTeams = () => {
        AdminService.getAllTeams().then((response) => {
            setTeams(response.data)

        })
    }





    return (
        <div>
            <center>CREATE CONTEST</center>
            <hr />


            <div className="form-group">
                <label for="exampleInputPassword1">CONTEST TITLE</label>
                <input onChange={(e) => {
                    setTitle(e.target.value);
                }} type="text" className="form-control" id="exampleInputPassword1" placeholder="ENTER TITLE" />
            </div>
            <div className='selectTap'>
                <select onChange={(e) => {
                    setTeam1(e.target.value)
                }} className="form-select form-select-sm" aria-label=".form-select-sm example">

                    {teams.map(team =>
                        <option key={team.teamId} value={team.teamId}>{team.teamName}</option>
                    )}

                </select>
                <select onChange={(e) => {
                    setTeam2(e.target.value)
                }} className="form-select form-select-sm" aria-label=".form-select-sm example">

                    {teams.map(team =>
                        <option key={team.teamId} value={team.teamId}>{team.teamName}</option>
                    )}
                </select>
            </div>
            <div className="form-group">
                <label for="exampleInputPassword1">Entry Fee</label>
                <input onClick={(e) => {
                    setEntreFee(e.target.value)
                }}
                    type="text" className="form-control" id="exampleInputPassword1" placeholder="Max Teams" />
            </div>
            <div className="form-group">
                <label for="exampleInputPassword1">Total Teams</label>
                <input onChange={(e) => {
                    setTotalTeams(e.target.value);
                    SetTotalTeamHere(e.target.value);
                }} type="text" className="form-control" id="exampleInputPassword1" placeholder="Total Teams" />
            </div>
            <div className="form-group">
                <label for="exampleInputPassword1"> Contest Amount</label>
                <input type="text" value={contestAmount} className="form-control" id="exampleInputPassword1" placeholder="Amount" />
            </div>

            <div className="form-group">
                <label for="exampleInputPassword1">Max Team Entry</label>
                <input onChange={(e) => {
                    setMaxTeamsPerUser(e.target.value);
                }} type="number" className="form-control" id="exampleInputPassword1" placeholder="Max Teams" />
            </div>
            <div className='selectTap'>
                <select onChange={(e)=>{setWinningPercen(e.target.value)}} class="form-select form-select-sm" aria-label=".form-select-sm example">
                    <option selected>SELECT PERCENTAGE</option>
                    <option value="50">50%</option>
                    <option value="60">60%</option>
                    <option value="40">40%</option>
                </select>
            </div>



            <hr></hr>
{prizeListStatus ?(<p>MANAL PRIZELIST ADDED</p>):(   <div>
                {manualprize ? (<div><h6> <span onClick={() => {
                    setProcessPage(6)
                    setManualprize(false)

                }} style={{ color: "red" }}>Click here </span>for Manual Prize Calculation</h6></div>)
                    : (<div><h6><span onClick={() => {
                        setProcessPage(1)
                        setManualprize(true)

                    }} style={{ color: "red" }}>Click here </span>  for automatic prize Calculation</h6></div>)}


            </div>)}
         
         {prizeListStatus && <button onClick={OnSubmit} type="submit" className="btn btn-primary">Submit</button>
}


        </div>
    )
}

export default CreteNewContest