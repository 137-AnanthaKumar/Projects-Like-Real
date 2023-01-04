import React, { useEffect, useState } from 'react'
import '../User/User.css';


function PeriTiContestDetail({ contestInHere, setPage }) {
    const [percentge, setPercentage] = useState()
    const [joned, setJoined] = useState()
    useEffect(() => {
        var a = contestInHere.totalTeams;
        var b = contestInHere.availPlace;
        var c = a - b;
        setJoined(c)
        var d = ((c / a) * 100)
        setPercentage(d)


    }, [contestInHere])
    return (
        <div className='container bg-dark text-white' > 

            <div className='container text-white'>
                <table className="table table-borderless text-white">

                    <tbody>
                        <thead>
                            <tr>
                                <div className='container text-white'>
                                    TITLE : {contestInHere.title}
                                </div>

                            </tr>
                        </thead>
                        <tr>
                            <th scope="row">Entre Fee</th>

                            <td>{contestInHere.entreFee}</td>
                        </tr>
                        <tr>
                            <th scope="row ">Total Prize</th>

                            <td>{contestInHere.contestAmount}</td>
                        </tr>
                        <tr>
                            <th scope="row ">AllowedTeams</th>

                            <td>{contestInHere.maxTeamsPerUser}</td>
                        </tr>
                        <tr>
                            <th scope="row">Winning Percentge</th>

                            <td>Will Be Updated</td>
                        </tr>
                    </tbody>
                </table>
                <div className='container' id="dhfy664t">
                    <div id='jhdff455t'> <h6>AVAILABLE:{contestInHere.availPlace}</h6>
                        <h6 >JOINED :{joned} </h6> </div>

                    <progress className="progress bg-primary" style={{ width: "600px" }} max="100" value={percentge}></progress>
                    {/* style={{margin:"0px 0px 300px 300px "}} */}
                </div>
                <div className='container'>
                    <center><button className='btn btn-info' onClick={() => {
                        setPage(2)
                    }} style={{ width: "100px" }} >Join</button></center>

                </div>

            </div>
        </div>
    )
}

export default PeriTiContestDetail