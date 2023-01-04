import React, { useEffect, useState } from 'react'
import UserService from '../../service/UserService'

function Winning({contestCode}) {
const[prizeList,setPrizeList]=useState([])

  useEffect(()=>{
       UserService.getPrizeListForContest(contestCode).then((response)=>{
        console.log(response.data);
        setPrizeList(response.data);
       })
  },[contestCode])
    
  return (
    <div className='container'>
      {prizeList.map((prizelist)=>(<div key={prizelist.rangeId} className='container'>
     
       <h3>{prizelist.fromRank}{"-"}{prizelist.toRank}{"==>"}{prizelist.winningAmount}</h3>


      </div>))}

    </div>
  )
}

export default Winning;