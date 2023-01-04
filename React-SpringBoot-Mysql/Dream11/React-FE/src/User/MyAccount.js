import React, { useEffect, useState } from 'react'
import UserService from '../service/UserService';

function MyAccount() {
const[sta,setSta]=useState(1)
const[name,setName]=useState("")
 const[account,SetAccount]=useState({})
 const[currentBalance,setCurrentBalance]=useState("VIEW BALANCE")
const[user,setUser]=useState({})


  useEffect(() => {
   
  
   //FetchDeatails(cust.id);
   FetchAccounts()

}, []);

const FetchAccounts=()=>{
  const cust = JSON.parse(sessionStorage.getItem("user"));
  setName(cust.username);
  setSta(cust.id)
  UserService.GetUserById(cust.id).then((response)=>{
    console.log(account)
    SetAccount(response.data.userAccount)

  }).catch(error=>{
    console.log(error)
  })
}

  
      const FetchDeatails=()=>{
        UserService.GetUserById(sta).then((response)=>{
       
          setCurrentBalance(response.data.userAccount.currentAmount)
         
        
         }).catch(error=>{
           console.log(error)
         })
      }

      
  


  return (
    <div><nav className="navbar navbar-light bg-light">
   


  <div className='container'><h2>Profile</h2></div>
  
  
 
  

  </nav>
  
  <div className='jumbotron'>
  <table className="table table-bordered">
 
  <tbody>
  <tr>
      
      <td colspan="2"><strong>USER NAME</strong></td>
      <td >{name}</td>
     
    </tr>
    <tr>
      
      <td colspan="2"><strong>ACCOUNT NAME </strong></td>
      <td >{name}</td>
     
    </tr>
    <tr>
      
      <td colspan="2"><strong>BALANCE </strong></td>
      <td ><a href="#" className="btn btn-primary"><stong onClick={FetchDeatails()}> {currentBalance}</stong></a></td>
     
    </tr>
    <tr>
      
      <td colspan="2"><strong>MAIL ID</strong></td>
      <td >{user.email}</td>
     
    </tr>
    <tr >
    
      <td  colspan="2"><strong>ACCOUNT NAME</strong></td>
      
      <td >hh</td>
    </tr>
   
  </tbody>
</table>



<div className='container' id='jfhjhjf'><table>
  <tbody>
  <tr>
      <th colspan="3" scope="row"><div className='container-fluid' id='insidetable'>

        <div className='container' id='inside1'> 
        
        <div className='container' id='inside2'> <h3>Total Contest :{account.totalJoinedContest} </h3></div>
        <div className='container' id="inside2"> <h3>Total Amount :{account.totalamountjoining}</h3> </div>
        <div className='container' id="inside2"> <h3>Total Win :{account.winningsAmount}</h3> </div>
        <div className='container' id="inside2"> <h3>Total Winning Contest :{account.wonContest}</h3> </div>


        </div>
       
      </div></th>
     
      
    </tr>
  </tbody>
</table>
</div>

  </div>

 

  </div>
  
 
  )
}

export default MyAccount