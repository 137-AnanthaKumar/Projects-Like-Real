import React, { useEffect, useState } from 'react'
import AdminService from '../service/AdminService';
import '../Admin/Css/PerticularContastView.css';
import { TiArrowBack } from 'react-icons/ti';

function PerticularContastView({ setPage,setSettingPage, setContastCodeHome,contastCode,setProcessPage,stage, contastId }) {


    const [perticularContast, setPeticularContast] = useState({});
    const[playerr,setPlayers]=useState([])
    // const[contastId1,setContastId]=useState(contastId)

    useEffect(() => {
        console.log("Use Effect in Pet Vivew Cont")
        GetContastById(contastId);
        
    }, [stage])

    const GetContastById = (contastId1) => {
        console.log("Get Contast By Id" + contastId1)
        AdminService.GetContastById(contastId).then((response) => {
            setPeticularContast(response.data)
            setContastCodeHome(response.data.contestCode)
            setPlayers(response.data.players)
            console.log("periticular view anand")
            
        }).catch(error => {
            console.log(error);
        })
    }



    return (

        <div className='perticularContast'>
            <center><h4>contastId : {perticularContast.contestId}</h4></center> <stong><p className="goback" onClick={() => {
                setPage(1)
                setProcessPage(1)
                setSettingPage(1)
            }}><TiArrowBack/>Go Back</p></stong>



            <center><h6>contest setting</h6></center>
            <hr />
            <table id="customers">

                <tr>
                    <td> <strong>Contast Code </strong></td>
                    <td> {perticularContast.contestCode}</td>

                </tr>
                <tr>
                    <td><strong>Contast Status</strong></td>
                    <td> {perticularContast.contextStatus}</td>

                </tr>
                <tr>
                    <td> <stong>Joining Fee </stong></td>
                    <td> {perticularContast.entreFee}</td>

                </tr>
                <tr>
                    <td><stong>Allowed Teams  </stong></td>
                    <td> {perticularContast.maxTeamsPerUser}</td>

                </tr>
                <tr>
                    <td><stong>Contast Amount</stong></td>
                    <td> {perticularContast.contestAmount}</td>

                </tr>
                <tr>
                    <td><stong> Available Space </stong></td>
                    <td>{perticularContast.availPlace}</td>

                </tr>
                <tr>
                    <td><stong> Total Teams </stong></td>
                    <td>{perticularContast.totalTeams}</td>

                </tr>

            </table>
            <hr />
            <center><h3>PLAYERS</h3></center>
            <hr />
            <div className='btngrpplayers'>


                <div className="accordion" id="accordionExample">
                    {playerr.map(player =>
                        <div className="accordion-item" key={player.playerId}>
                            <h2 className="accordion-header" id="headingOne">
                                <button className="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target={"#collapseone"+player.playerId} aria-expanded="true" aria-controls="collapseOne">
                                    {player.playerName}  (<span>{player.points}</span>)-({player.role}-{player.team})
                                   
                                </button>
                               
                            </h2>
                            <div id={"collapseone"+player.playerId} className="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                                <div className="accordion-body">
                                    <table class="table table-striped table-dark">
                                        <thead>
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">CATCH</th>
                                                <th scope="col">FIFTY</th>
                                                <th scope="col">FOUR</th>
                                                <th scope="col">SIX</th>
                                                <th scope="col">CENTURY</th>
                                                <th scope="col">RUN OUT</th>
                                                <th scope="col">RUNS</th>
                                                <th scope="col">WICKET</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <th scope="row">1</th>
                                                <td>{player.catches}</td>
                                                <td>{player.fifty}</td>
                                                <td>{player.fours}</td>
                                                <td>{player.six}</td>
                                                <td>{player.hundred}</td>
                                                <td>{player.runout}</td>
                                                <td>{player.runs}</td>
                                                <td>{player.wicket}</td>
                                            </tr>
                                           
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>


                    )}



                </div>
            </div>


        </div>

    )
}

export default PerticularContastView