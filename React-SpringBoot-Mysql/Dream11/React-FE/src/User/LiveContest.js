import React, { useEffect, useState } from 'react'
import UserService from '../service/UserService'

function LiveContest({ SetUserGamePage, setContestId, setContastCode }) {
  const [contest, setContast] = useState([])

  useEffect(() => {
    UserService.GetActiveContest().then((response) => {
      setContast(response.data)
      console.log(response.data)
    })

  }, [])


  return (
    <div className='container-fluid bg-dark rounded font-weight-bold'>
      {contest.length}
      {contest.map((data) => (<div style={{ "width": "640px" }} className='container rounded bg-white livecontestt'>

        <div className='row justify-content-center mt-3'>
          <div className='col'></div>
          <div className='col font-weight-bold' >{data.title}</div>
          <div className='col'></div>


        </div>
        <div className='row mt-3'>

          <div className='col-2 '><h5 className='font-weight-bold'>ENTRE :{data.entreFee} </h5></div>
          <div className='col-8 text-center font-weight-bold'> {(data.contestLock === "UNLOCKED" && data.availPlace !== 0) && <button onClick={(e) => {
            SetUserGamePage(4)
            console.log(data.contestId)
            setContestId(data.contestId)
            setContastCode(data.contestCode)
          }
          } className='btn btn-primary'>Join</button>}
            {data.contestLock === "LOCKED" || data.availPlace === 0 && <button disabled className='btn btn-primary'>Join</button>}
        </div>
          <div className='col'>MAX TEAM:{data.maxTeamsPerUser}</div>

        </div>
        <div className='row mt-1'>

          <div className='col-1'>
            TEAMS:{data.totalTeams}
          </div>
          <div className='col-9 font-weight-bold'><progress className="progress mt-2" style={{ width: "400px" }} max="100" value={((data.totalTeams - data.availPlace) / data.totalTeams) * 100}></progress></div>
          <div className='col-2 font-weight-bold'>AVAILABLE :{data.availPlace}</div>

        </div>
      </div>))}

    </div>
  )
}

export default LiveContest