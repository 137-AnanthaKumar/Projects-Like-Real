import React from 'react'

function ContestHeader(props) {
  return (
    <div className='contestBtm'>
        <button onClick={()=>{
            props.setContestPage(1)
        }} id="btn1" className='btn btn-info'>Active Contest</button>
        <button 
        onClick={()=>{
            props.setContestPage(2)
        }} id="btn2" className='btn btn-info'>Finished Contest</button>
    </div>
  )
}

export default ContestHeader