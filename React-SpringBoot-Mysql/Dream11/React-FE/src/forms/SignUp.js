import React from 'react'

function SignUp(props) {

    const Page=(e)=>{
      
        props.setSignupPage(false)
    }
  return (
    <div>
    <br /><br />
    <div className = "container">
         <div className = "row">
             <div className = "card col-md-6 offset-md-3 offset-md-3">
               
                 <div className = "card-body">
                    
                         <div className = "form-group mb-2">
                             <label className = "form-label">  Name :</label>
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
                             <label className = "form-label"> Email Id :</label>
                             <input
                                 type = "email"
                                 placeholder = "Enter email Id"
                                 name = "emailId"
                                 className = "form-control"
                                 value = {props.email}
                                 onChange = {(e) => props.setEmail(e.target.value)}
                                
                             >
                             </input>
                         </div>

                         <div className = "form-group mb-2">
                             <label className = "form-label"> password :</label>
                             <input
                                 type = "password"
                                 placeholder = "Enter Mobile No"
                                 name = "emailId"
                                 className = "form-control"
                                 value = {props.password}
                                 onChange = {(e) => props.setPassword(e.target.value)}
                             >
                             </input>
                         </div>

                         <div className = "form-group mb-2">
                             <label className = "form-label"> Account Name :</label>
                             <input
                                 type = "text"
                                 placeholder = "Enter Account Name"
                                 name = "emailId"
                                 className = "form-control"
                                 value = {props.accountName}
                                 onChange = {(e) => props.setAccountName(e.target.value)}
    
                             >

                             </input>
                         </div>

                         <button className = "btn btn-success" onClick = {(e) => props.SignUpRequest(e)} >Submit </button>
                         <p onClick={Page}>Login Page</p>

                 </div>
             </div>
         </div>

    </div>

 </div>
  )
  
}

export default SignUp;