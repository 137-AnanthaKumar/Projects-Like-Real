import React, { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom';

function Components() {

    const navigate=useNavigate();
    
    const [Username, setUserName] = useState("")


    const Profile=()=>{
        navigate("/user/myaccount")
    }
    const Logout=()=>{
        sessionStorage.removeItem("user");
        navigate("/");
    }
    const Game=()=>{
        navigate("/user/game");
    }

    useEffect(() => {
        const cust = JSON.parse(sessionStorage.getItem("user"));
        const userName = cust.username;
        setUserName(userName);


    }, []);



    return (
        <div className="container">

            <div className="jumbotron" id='dfjfdgfugf'>
                <h1>Welcome Back <span style={{ color: "blue" }}>{Username}</span></h1>
                <p>Bootstrap is the most popular HTML, CSS, and JS framework for developing
                    responsive, mobile-first projects on the web.</p>
            </div>
            <div className="well well-lg">
                <div className='container' id='cont'>
                    <div className='container' id='designFrd' onClick={Profile}>
                    
                    <h3>PROFILE</h3></div>

                </div>
                <div className='container' id='cont' onClick={Game}>
                    <div className='container' id='designFrd'>
                    <h3>GAME</h3></div> </div>
                   
                    <div className='container' id='cont'>
                        <div className='container' id='designFrd'><h3 >CONTACT</h3></div> </div>
                    
                    <div className='container' id='cont'>
                    <div className='container' id='designFrd'><h3>FEEDPACK</h3></div>
                   
                    </div>
                    <div className='container' id='cont' >
                    <div  className='container' id='designFrd' onClick={Logout} ><h3 clas>LOGOUT</h3></div>
                   
                    </div>

</div>
                </div>

            
            )
}

            export default Components