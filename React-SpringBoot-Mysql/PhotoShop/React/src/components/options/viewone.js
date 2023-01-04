import React from 'react'
import { useState } from 'react';
import axios from 'axios';
import { useEffect } from 'react';

function Viewone({setType}) {
     

    const [obj, setObj] = useState([]);
    useEffect(() => {
        console.log("useeffe");
        fetchProducts();
    }, []);

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
      
        setObj(fetchProducts);
      
        // }
      };
  return (
    <div  >
    <button onClick={()=>{
      setType(2);
    }}>go back</button>
      <div >
        {
          obj.map(data => {
         
            return (
            
                <div className=''>
                <div key={data.imageId} id="image" >
                  <img src={"/images/" + data.imageName} alt="img" width="300" height="200"/>
                </div>
                </div>
            
              
              )
          })
        }


      </div>
    </div>
  )
}

export default Viewone