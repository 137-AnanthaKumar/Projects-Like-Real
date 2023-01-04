import React,{useEffect, useState} from 'react'
import { Navigate, useNavigate } from 'react-router-dom';

function Header(props) {
const[adminId,setAdminID]=useState();
const navigate=useNavigate();

const LogOut=()=>{
    sessionStorage.removeItem("admin");
    navigate("/");

}
    useEffect(() => {
        const cust = JSON.parse(sessionStorage.getItem("admin"));
        const AdminId = cust.id;
        setAdminID(AdminId);
      
        // console.log("In ADMIN Homr"+customerId)
      }, []);

  return (
    <div><nav class="navbar navbar-inverse navbar-fixed-top">
    <div className="container-fluid">
      <div className="navbar-header">
        <h3 className='hello'>Hello <span className='adminname'> {props.username}</span></h3>
      </div>
      <ul className="nav">
        <li className="active"><h4 >Admin ID: {adminId}</h4></li>
        <li><a href="#">Page 1</a></li>
        <li><a href="#">Page 2</a></li>
        <li><a href="#">Page 3</a></li>
      </ul>
      <div><button onClick={LogOut}>LOG OUT</button></div>
    </div>
  </nav></div>
  )
}

export default Header