import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import AdminService from '../service/AdminService';
import ControllerService from '../service/ControllerService';

import AdminButtonGrp from './AdminButtonGrp';
import ContestControllerProcess from './ContestControllerProcess';
import CreateNewTeam from './ContestSetting/CreateNewTeam';
import CreteNewContest from './ContestSetting/CreteNewContest';
import RunUpdates from './ContestSetting/RunUpdates';
import Header from './Header';
import MaualPrize from './MaualPrize';
import FinalContestSetting from './MoreSettings/FinalContestSetting';
import NewTeamProcess from './NewTeamProcess';
import CreateNewTeamPages from './Teams/CreateNewTeamPages';
import ViewRegisterdeTeams from './Teams/ViewRegisterdeTeams';
function AdminHome() {
  const [username, setUserName] = useState("");
  const [contastId, setContastId] = useState();
  const navigate = useNavigate();


  const [processPage, setProcessPage] = useState(1);
  const [settingPage, setSettingPage] = useState(1);
const[mainAdminControl,setMainAdminControl]=useState(0);


  const [useEfff, setUseEff] = useState(1)
  const [claName, setClasNameclr] = useState(1);
  const [stage, SetStage] = useState(true)
  const [user, setUser] = useState("Jesse Hall");
  const [teamName, setTeamName] = useState()
  const [newPlayers, setNewPlayers] = useState([])
  const [entreeFee, setEntreFee] = useState();
  const PlayersForTeam = [];
  const [useeffForPertiViewCon, setUseEffPertiView] = useState(1);


  const [allplayers, SetAllPlayers] = useState([])
  const [contestCodehome, setContastCodeHome] = useState("")

  const [playerRolePage, setPlayerRolePage] = useState(5)
  console.log("contestcode home")

  console.log(contestCodehome)

const RemovePlayer=()=>{
  console.log("index here ")
}

  const FechPlayers = () => {

    AdminService.AddNewTeam(teamName, allplayers).then((response => {
      if (response.data.error === "No Error") {
        alert("TEAM ADDED SUCESSFULLY")
      }
      SetAllPlayers([])
    }))

  }



  const [contestAmount, setContestAmount] = useState();
  const [totalteams, setTotalTeams] = useState();
  const [winpercentage, setWinningPercen] = useState();
  const [prizeListStatus, setPrizeListStatus] = useState(false);
  const [rankListForWinners, setRankListForWinners] = useState([])

  useEffect(()=>{
    console.log("WINNERS LIST ")
console.log(rankListForWinners)
  },[prizeListStatus])
  const AdminProcessPage = () => {
    if (processPage === 1 || processPage === 3) {
      return <ContestControllerProcess setContastCodeHome={setContastCodeHome}
        stage={stage}
        setContastId={setContastId}
        useeffForPertiViewCon={useeffForPertiViewCon}
        setSettingPage={setSettingPage}
        setProcessPage={setProcessPage} />
    }
    else if (processPage === 2) {
      return <NewTeamProcess processPage={processPage}
        setProcessPage={setProcessPage}
        setSettingPage={setSettingPage} />
    }
    else if (processPage === 4) {
      return <CreateNewTeamPages setPlayerRolePage={setPlayerRolePage}
        setClasNameclr={setClasNameclr}
        playerRolePage={playerRolePage}
        SetAllPlayers={SetAllPlayers}
        RemovePlayer={RemovePlayer}
        allplayers={allplayers}
        teamName={teamName} />
    }
    else if (processPage === 5) {
      return <ViewRegisterdeTeams />
    }

    else if (processPage === 6) {
      return <MaualPrize totalteams={totalteams}
        contestAmount={contestAmount}
        winpercentage={winpercentage}
        setPrizeListStatus={setPrizeListStatus}
        setRankListForWinners={setRankListForWinners}
      />
    }


  }

  const AdminSettins = () => {
    if (settingPage === 1) {
      return <CreteNewContest setProcessPage={setProcessPage}
        totalteams={totalteams}
        winpercentage={winpercentage}
        rankListForWinners={rankListForWinners}
        prizeListStatus={prizeListStatus}
        setEntreFee={setEntreFee}
        entreeFee={entreeFee}
        contestAmount={contestAmount}
        setTotalTeams={setTotalTeams}
        setContestAmount={setContestAmount}
        setWinningPercen={setWinningPercen}
      />
    }
    else if (settingPage === 2) {
      return <CreateNewTeam SetAllPlayers={SetAllPlayers}
        allplayers={allplayers}
        setPlayerRolePage={setPlayerRolePage}
        FechPlayers={FechPlayers}
        PlayersForTeam={PlayersForTeam}
        teamName={teamName}
        setNewPlayers={setNewPlayers}
        setTeamName={setTeamName} />
    }


    else if (settingPage === 3) {
      console.log("contest id in setting " + contastId)
      return <RunUpdates SetStage={SetStage}
        contestCodehome={contestCodehome}
        setProcessPage={setUseEffPertiView} contastId={contastId} />
    }

    else (<div>Admin Settings</div>)
  }


  useEffect(() => {
    const cust = JSON.parse(sessionStorage.getItem("admin"));
    const userName = cust.username;
    setUserName(userName);


  }, [useEfff]);


  return (



    <div><Header username={username} />
     {mainAdminControl ===0 &&  
      <div className='adminHomeBody'>
        <div className='admincontrollGroup'>
          <AdminButtonGrp
                   setProcessPage={setProcessPage}
                   setMainAdminControl={setMainAdminControl}
                    setSettingPage={setSettingPage} />
        </div>
  
        <div className='adminProcess'>
          {AdminProcessPage()}
          </div>
        <div className='adminHelper'>
          {AdminSettins()}

        </div>

      </div>}
      {mainAdminControl===1 &&  <div className='adminHomeBody'>
        <div className='admincontrollGroup'>
          <AdminButtonGrp mainAdminControl={mainAdminControl}
                         setProcessPage={setProcessPage}
                          setSettingPage={setSettingPage} />
        </div>
  
      <div> <FinalContestSetting setMainAdminControl={setMainAdminControl}/></div>

      </div> }

    </div>

  )
}

export default AdminHome