import React from 'react'

function View({dat,setType}) {
  return (
    <div>
    <button onClick={()=>{
setType(2)
    }}>Go To Table</button>
       <div>CATACAORY : {dat.category}</div>
       <div> <img src={"/images/" + dat.imageName} alt="img" width="300" height="200"/></div>
       <div>DISCRIPTION :{dat.description} </div>
   

    </div>
  )
}

export default View;