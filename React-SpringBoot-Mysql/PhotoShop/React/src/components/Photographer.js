import React, { useState } from "react";
import Navbar from "./Navbar";
import Footer from "./Footer";
import Upload from "./options/upload";
import Viewall from "./options/viewall";
import Viewone from "./options/viewone";
import axios from "axios";
import View from "./options/view";
import './Photographer.css';
import UserHome from "./userpage/userHome";

const Photographer = () => {
  const [data, setData] = useState([]);
  const [file, setFile] = useState();
  const[dat,setDat]=useState({});
  
   const save= async ()=>{
    let jwtToken = localStorage.getItem("jwtToken");

    let token = `Bearer ${jwtToken}`;
   
   console.log(file)
    let res = await axios.post("http://localhost:8080/photographer/reg/photo/upload/1/nature/{file}?", {
      headers: { "Authorization": token,
      "Content-type": "application/json",
        "Access-Control-Allow-Origin": "*",}
   
     });

     console.log(res)

   }

  const filterResult = (photographerItem) => {
    const result = data.filter((curData) => {
      return curData.photographer === photographerItem;
    });

    setData(result);
  };
  const[typep,setTypeP]=useState(1);
 const pageType=()=>{
      if(typep===1){
        return <UserHome/>
      }
    if(typep===2){
      return (
      <>
       <div className="d-flex">
      <div className="mygrp" onClick={()=>{
           setType(1)
         }}><h4>UPLAOD NEW IMAGE</h4></div>
       <div className="mygrp" onClick={()=>{
           setType(2)
         }}><h4>VIEW IN TABLE</h4></div>
       <div className="mygrp" onClick={()=>{
           setType(3)
         }}><h4>VIEW ALL MY IMAGE</h4></div>
     </div>
        <div> {Options()} </div>
     </>)
    }

 }

 const[type,setType]=useState(1);
  const Options=()=>{

  if(type===1){
    return  <Upload setFile={setFile} save={save}/>
  }
  if(type===2){
    return <Viewall setType={setType} setDat={setDat}/>
  }
  if(type===3){
    return <Viewone setType={setType}/>
  }
  if(type===4){

    return <View dat={dat} setType={setType}/>

  }
    
   

  }

  return (
    <>
      <Navbar />
      <div className="d-flex"><div onClick={()=>{
        setTypeP(2)
      }}>My Account</div><div onClick={()=>{
        setTypeP(1)
      }}>Home Page</div></div>

<div>
{pageType()}
</div>


    
    
    </>
  );
};

export default Photographer;
