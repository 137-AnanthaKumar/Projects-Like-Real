import React, { useEffect, useState } from 'react'
import AdminService from '../service/AdminService';
import ContastCard from './ContastCard';
import PerticularContastView from './PerticularContastView';

function ContestDivision({setProcessPage,setContastCodeHome,useeffForPertiViewCon,setSettingPage,setContastId,stage}) {

  const [contast, setContast] = useState([])
  const [page, setPage] = useState(1);
  const [contastId, setContastId1] = useState();
  const [contastCode, setContastCode] = useState();
 const[reFreash,setRefreash]=useState(1);
  useEffect(() => {

    FetchDetails();
  }, [reFreash])

  const Refreash=()=>{
    setRefreash(reFreash+1)
  }
  

  const Pages = () => {
    if (page === 1) {
      return <ContastCard setSettingPage={setSettingPage}
        contast={contast} setPage={setPage}
       
        setContastId={setContastId1}  setContastCode={setContastCode}  />
    }
    else if (page === 2) {
      setContastId(contastId);
      return <PerticularContastView setContastCodeHome={setContastCodeHome} stage={useeffForPertiViewCon} setSettingPage={setSettingPage} setProcessPage={setProcessPage} contastId={contastId} setPage={setPage} contastCode={contastCode} />
    }
  }




  const FetchDetails = () => {
    AdminService.GetActiveContest().then((response) => {
      setContast(response.data)
      console.log("Fetxching Details in Contest Divi.js")
      console.log(response.data);
    }).catch(error => {
      console.log(error)
    })

  }






  return (
    <div>
    <div><center><p onClick={Refreash}>Refreash</p></center></div>
      {Pages()}
    </div>








  )
}

export default ContestDivision