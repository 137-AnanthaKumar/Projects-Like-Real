import React from 'react'

function AdminButtonGrp({ mainAdminControl,setSettingPage,setMainAdminControl, setProcessPage }) {
    return (
        <div>
               {mainAdminControl===1 ?(""):(<div>
                <button onClick={() => {
                    setProcessPage(1)
                    setSettingPage(1)
                }} className='button1'>Contest Controll</button>
                <button onClick={() => {
                    setProcessPage(2)
                     setSettingPage(4)
                }} className='button2'>New Teams</button>
            </div>)}

         
            <div>
                <button onClick={()=>{
                    setMainAdminControl(1)
                }} className='button1'>Contest Controlls</button>
                <button className='button2'>Button 4</button>
            </div>
  </div>
    )
}

export default AdminButtonGrp