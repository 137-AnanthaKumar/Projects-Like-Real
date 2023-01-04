import React, { useEffect, useState } from 'react'
import UserService from '../service/UserService';
import '../User/User.css';
import CompletedContest from './CompletedContest';
import LiveContest from './LiveContest';
import MyContest from './MyContest';
import PerticularContest from './PerticularContest';
function Game() {
  const[userGamepage,SetUserGamePage]=useState(1);
  const[contestID,setContestId]=useState();
  const[accountId,setAccountId]=useState();
  const[refreash,setRefreash]=useState(1)
  const[accountName,setAccountName]=useState()
  const[contestCode,setContastCode]=useState("")
  const[sta,Setsta]=useState(1)
  useEffect(() => {
    
    const cust = JSON.parse(sessionStorage.getItem("user"));
  
   UserService.GetUserById(cust.id).then((response)=>{
 
    setAccountName(response.data.userAccount.accountName);
    setAccountId(response.data.userAccount.accountId)

  
   }).catch(error=>{
     console.log(error)
   })


}, [refreash]);

 console.log(contestID)
  const UserGamePage=()=>{


   if(userGamepage===1){
     return <LiveContest setContastCode={setContastCode} SetUserGamePage={SetUserGamePage} setContestId={setContestId} />
   }
   else if(userGamepage===2){
     return <MyContest accountName={accountName}/>
   }
   else if(userGamepage===3){
     return <CompletedContest accountName={accountName}/>
   }

   else if(userGamepage===4){
     return<PerticularContest accountId={accountId} contestID={contestID} />
   }

     
  }

  return (

    <div className='jumbotron' id='ffffgfjfdd'>Game
    
    <div className='container' id='constgf'>
      
 <div className='container' id='controllUercon'>

<div className='container' onClick={()=>{SetUserGamePage(1)}}><h3 className={userGamepage===1 ||userGamepage===4? ('hgfhgfy'):('hgfhgfh')}>Live contest</h3></div>
<div className='container' onClick={()=>{SetUserGamePage(2)}}><h3 className={userGamepage===2? ('hgfhgfy'):('hgfhgfh')}>My Contest</h3></div>
<div className='container' onClick={()=>{SetUserGamePage(3)}}><h3 className={userGamepage===3? ('hgfhgfy'):('hgfhgfh')}>Completed Contest</h3></div>

 </div>
 <hr/>
 <div className='container' id='fdfdjfgg78'>
{UserGamePage()}
 </div>

 

    </div>



    
    </div>

  )
}

export default Game