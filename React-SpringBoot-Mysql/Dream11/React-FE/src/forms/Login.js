import React from 'react'

function Login(props) {
  return (
    <div>
    <br /><br />
    <div className = "container">
         <div className = "row">
             <div className = "card col-md-6 offset-md-3 offset-md-3">
               
                 <div className = "card-body">
                     
                         <div className = "form-group mb-2">
                             <label className = "form-label"> User Name :</label>
                             <input
                                 type = "text"
                                 placeholder = "Enter  name"
                                 name = "firstName"
                                 className = "form-control"
                                 value = {props.username}
                                 onChange = {(e) => props.setName(e.target.value)}
                             >
                             </input>
                         </div>

                         <div className = "form-group mb-2">
                             <label className = "form-label"> Password :</label>
                             <input
                                 type = "text"
                                 placeholder = "Enter Password "
                                 name = "emailId"
                                 className = "form-control"
                                 value = {props.password}
                                 onChange = {(e) => props.setPassword(e.target.value)}
                             >
                             </input>
                         </div>

                         <button className = "btn btn-success" onClick = {(e) => props.LogInRequest(e)} >Log In </button>
                         <p>Already Registered?.Clicke<p onClick={()=>{
        props.setSignupPage(true);
    }}>here to Login Form</p></p>

                 </div>
             </div>
           
         </div>
        
    </div>
    

 </div>
  )

}

export default Login