import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import Login from './forms/Login';
import SignUp from './forms/SignUp';
import UserService from './service/UserService';
function MainPage() {
  const navigate=useNavigate();
const[signuppage,setSignupPage]=useState(false);
const[username,setName]=useState("");
const[password,setPassword]=useState("");
const[email,setEmail]=useState("");
const[accountName,setAccountName]=useState("")

const SignUpRequest=(e)=>{
  e.preventDefault();
  UserService.SignUp(username,email,password,accountName).then((response) =>{
    console.log(response.data)
    if(response.data.message==="User registered successfully!"){
       alert("Success")
      }

      else if(response.data.message==="Error: Username is already taken!"){
        alert(response.data.message)
      }
      else if(response.data.message==="Error: Account Name is already in use!"){
        alert(response.data.message)
      }
    }).catch(error => {
     console.log(error)
 });
}

const LogInRequest=(e)=>{
  e.preventDefault();
  UserService.LogIn(username,password).then((response)=>{
    // console.log(response.data)

    // console.log(response.data.roles[0])
    const role=response.data.roles[0];
    if(role==="ROLE_ADMIN"){
      sessionStorage.setItem("admin", JSON.stringify(response.data));
       navigate("/adminHome")
    }
   else if(role==="ROLE_USER"){
      sessionStorage.setItem("user", JSON.stringify(response.data));
       navigate("/userHome")
    }
    else if(role==="ROLE_CONTROLLER"){
      sessionStorage.setItem("admin", JSON.stringify(response.data));
       navigate("/adminHome")
    }

    else {
      alert("SerVer is Offline Please Try Again Latter")
    }
    // console.log(role)
  })
}

const PageDisplay=()=>{
  if(signuppage){
    return <SignUp 
    setSignupPage={setSignupPage}
    username={username}
    setName={setName}
    password={password}
    setPassword={setPassword}
    email={email}
    setEmail={setEmail}
    accountName={accountName}
    setAccountName={setAccountName}
    SignUpRequest={SignUpRequest}

    />;
  
  }
  else{
    return <Login username={username}
    setName={setName}
    password={password}
    setPassword={setPassword}
    setSignupPage={setSignupPage}
    LogInRequest={LogInRequest}
    />
  }
}
  return (
    <div className='main'>
 <div className='page'>
    {PageDisplay()}
 </div>


    </div>
  )
}

export default MainPage