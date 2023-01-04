import React from 'react'
import { useState } from 'react';
import { useEffect } from 'react';
import { Table } from "react-bootstrap";
import axios from 'axios';

function Viewall({setType,setDat}) {
  let jwtToken = localStorage.getItem("jwtToken");

  let token = `Bearer ${jwtToken}`;
  let userId=localStorage.getItem("userId");
  console.log(token);

  const header = {
    Authorization: token,
    "Access-Control-Allow-Origin": "*",
  };
const[Photographers,setPhotographers]=useState([])
useEffect(() => {
  console.log("useeffe");
  fetchProducts();
}, []);

const deleteImage=(imageId)=>{
console.log(imageId)
delete_im(imageId);


}

const Dounload=(imageId)=>{
  console.log("jhh")
  dounload_im(imageId);
}

let dounload_im = async (imageId) => { 
  let res = await axios.get(`http://localhost:8080/photo/download/${imageId}`, {
    headers: header,
  });

  // if(res==='successfully deleted'){
  //   alert("SUCCESSFULLY DELETED")
  // }
}

let delete_im = async (imageId) => { 
  let res = await axios.delete(`http://localhost:8080/photo/delete/${imageId}`, {
    headers: header,
  });

  // if(res==='successfully deleted'){
  //   alert("SUCCESSFULLY DELETED")
  // }
}

let fetchProducts = async () => {
  let jwtToken = localStorage.getItem("jwtToken");
 let token = `Bearer ${jwtToken}`;
  let userId=localStorage.getItem("userId");
  console.log(token);

  const header = {
    Authorization: token,
    "Access-Control-Allow-Origin": "*",
  };
  //eturn axios.get(url+"/"+contestCode,{headers:header})

  let res = await axios.get(`http://localhost:8080/photo/getall/${userId}`, {
    headers: header,
  });

  console.log(res.data);
 let fetchProducts = res.data;
setPhotographers(fetchProducts);
};

  return (
   <div>      
   <Table classname="table" >  <thead>
   <tr>
       <th scope="col">Image Id</th>
       <th scope="col">CATEGORY</th>
       <th scope="col">FILE TYPE</th>
       <th scope="col">CONTENT</th>
       <th scope='col'>ACTION</th>
      



   </tr>
</thead>
{Photographers.length===0? (<div>No Records Found</div>):

(
  <tbody>

  {Photographers.map(contests =>

<tr key={contests.imageId}>
    <td>1</td>
    <td>{contests.category}</td>
    <td></td>
    <td>{contests.imageName}</td>

    <td><button className='btn btn-primary' onClick={()=>{
          deleteImage(contests.imageId)
    }}>Delete</button>
    <button className='btn btn-primary' onClick={()=>{
     setType(4);
     setDat(contests);
    }}>View</button>
    <button className='btn btn-primary' onClick={()=>{

    Dounload(contests.imageName)}}>Dounload</button></td>

</tr>
)}
</tbody>)}

 

 </Table>
</div>
  )
}

export default Viewall;