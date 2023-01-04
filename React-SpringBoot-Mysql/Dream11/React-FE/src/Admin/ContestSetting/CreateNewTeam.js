import React, {  useState } from 'react'

function CreateNewTeam({ SetAllPlayers,allplayers,setPlayerRolePage,teamName,FechPlayers,setTeamName }) {
  const[error,setError]=useState(false)
  const[finalErroe,setFinalErr]=useState(false)
  const[errormsg,setErrorMsg]=useState("")
  const [Player, setSinglePlayer] = useState({
    name: "",
    role: "",
  })

  
  const Asdd = () => {
    if(Player.name===""){
      setError(true)
      setErrorMsg("FILL ALL THE INPUT FIELD")
    }
    else if(Player.role===""){
      setError(true)
      setErrorMsg("Please Enter Player Name")
    }
    else{
      SetAllPlayers([...allplayers,Player])
      setSinglePlayer({
        name: "",
        role: "",
      });
      setPlayerRolePage(5)
      setFinalErr(true)
      setError(false)

    }



   
  }
  return (


    <div>
      <div className="form-group">
        <label >TEAM NAME</label>
        <input onChange={(e) => {
          setTeamName(e.target.value)
        }} value={teamName} type="text" className="form-control" placeholder="Enter Team Name" />
      </div>
      <hr />
      <hr />
      <div className="form-group">
        <label >PLAYER NAME</label>
        <input type="text" value={Player.name} onChange={(e) => {
          setErrorMsg("")
          setSinglePlayer({ name: e.target.value })
        }} className="form-control" placeholder="Enter Player Name" />
      </div>
      <div className='container'>{errormsg}</div>

      <select value={Player.role} required onChange={(e) => {
     if(Player.name===""){
      setErrorMsg("Please Enter Player Name")
     }
     else{
      setSinglePlayer({ ...Player, role: e.target.value })
      setError(true)

     }
        
      }} class="form-select" aria-label="Default select example">
       <option  selected>SELECT ROLE</option>
        <option value="WK" >WK</option>
        <option value="BAT">BAT</option>
        <option value="BOW">BOW</option>
        <option value="ALL-ROW">ALL-ROW</option>
      </select>
      <p>..................................................................................</p>

      {error ?(      <button className='btn btn-primary btn-lg btn-block' onClick={Asdd }>ADD PLAYER</button>
):(      <button className='btn btn-primary btn-lg btn-block' disabled onClick={Asdd}>ADD PLAYER</button>
)}

{finalErroe?(<button className='btn btn-primary btn-lg btn-block' 
    
    onClick={FechPlayers}
    >FINAL SUBMIT</button>):(<button className='btn btn-primary btn-lg btn-block' 
    disabled
    onClick={FechPlayers}
    >FINAL SUBMIT</button>)}
     
    </div>
  )
}

export default CreateNewTeam;