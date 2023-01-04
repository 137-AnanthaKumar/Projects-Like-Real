import React, { useState } from 'react'
import axios from 'axios';
function UserHome() {
    const[Photographers,setPhotographers]=useState([])
    const[images, setImages]=useState([])
    const[welcome,setWelcome]=useState(false)
    let jwtToken = localStorage.getItem("jwtToken");
    let token = `Bearer ${jwtToken}`;
    //  let userId=localStorage.getItem("userId");
     console.log(token);
   
     const header = {
       Authorization: token,
       "Access-Control-Allow-Origin": "*",
     };
 
    const getprofilePhotos= async (id)=>{
        let res = await axios.get(`http://localhost:8080/photo/getall/${id}`, {
    headers: header,
     });
     let fetchProducts = res.data;
     setImages(fetchProducts);
    }

    const getPhotos= async (cata)=>{
      
         //eturn axios.get(url+"/"+contestCode,{headers:header})
       
         let res =await axios.get(`http://localhost:8080/search/get/${cata}`, {
           headers: header,
         });
       
         console.log(res.data);
       
       setPhotographers(res.data);
       console.log(setPhotographers)
    }
   


  return (
    <div>

<select class="form-select form-select-lg mb-3" onChange={(e)=>{
    getPhotos(e.target.value)
    setWelcome(false)
}} aria-label=".form-select-lg example">
  <option selected>All</option>
  <option value="Nature">Nature</option>
  <option value="Wedding">Wedding</option>
  <option value="Concert">Concert</option>
</select>
<div>
<div>
{!welcome? ( <>{Photographers.map(data =>  (
  <div class="card" >
  <div class="card-header">
    Name: {data.userName}
    Mobile: {data.contact}
  </div>
  <div class="card-body">
    <blockquote class="blockquote mb-0">
      <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.</p>
      <footer class="blockquote-footer">Someone famous in <cite title="Source Title">Source Title</cite></footer>
    </blockquote>
    <button onClick={()=>{
    setWelcome(true);
getprofilePhotos(data.id)
  }}>Go to This Profile</button>
  </div>
</div>)
         )
        }</>):(<div>
        <button onClick={()=>{
              setWelcome(false);
        }}>Back</button>

            {
                images.map(data => {
         
            return (
            
                <div className=''>
                <div key={data.imageId} id="image" >
                  <img src={"/images/" + data.imageName} alt="img" width="300" height="200"/>
                </div>
                </div>
            
              
              )
          })
        }
        </div>)}
</div>




</div>


    </div>
  )
}

export default UserHome;

