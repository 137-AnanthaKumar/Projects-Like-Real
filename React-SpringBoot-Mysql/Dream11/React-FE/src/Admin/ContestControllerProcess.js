import React, { useState } from 'react'
import ContestDivision from './ContestDivision';
import ContestHeader from './ContestHeader'
import FinishedContest from './FinishedContest';
import PerticularContastView from './PerticularContastView';

function ContestControllerProcess({stage,setContastCodeHome,useeffForPertiViewCon,setProcessPage,setSettingPage,setContastId}) {

    const [contestPage,setContestPage]=useState(1);
 const PageDisplayContest=()=>{
if(contestPage===1){
    return <ContestDivision stage={stage}
    setContastCodeHome={setContastCodeHome}
     setContastId={setContastId} 
     setSettingPage={setSettingPage}
     useeffForPertiViewCon={useeffForPertiViewCon}
     setProcessPage={setProcessPage} setContestPage={setContestPage}/>
}

else if(contestPage===2){
    return<FinishedContest/>
}

    }


  return (
    <div><ContestHeader setContestPage={setContestPage}/>
    
    <hr/>
    <div>
        {PageDisplayContest()}
    </div>
    
    </div>
  )
}

export default ContestControllerProcess