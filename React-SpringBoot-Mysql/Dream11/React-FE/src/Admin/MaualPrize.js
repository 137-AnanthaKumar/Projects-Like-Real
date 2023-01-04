import React, { useEffect, useState } from 'react'
import './MaualPrize.css';
function MaualPrize({ setRankListForWinners,setPrizeListStatus,contestAmount, totalteams, winpercentage }) {
const avail=contestAmount * (70 / 100);
const[availAmount, setAvailAmount]=useState(avail)
const[gameAmount,setGameAmount]=useState(avail)
const[sampleDetail,setSampleDetail]=useState({"fromRank":"","toRank":"","winningAmount":"","noOfWinners":""})

const[RankFrom,setRankFrom]=useState("")
const[RankTo,setRankTo]=useState("")
const[winning,setWinnig]=useState("")
const[noofWinners,setNoOfWinners]=useState("")

const[totalNoofWinners,setTotalNoOfWinners]=useState(0);
const[currentRank,setCurrentRank]=useState("0")
const[rankDeatails,setRankDerails]=useState([{"fromRank":"0","toRank":"","winningAmount":"","noOfWinners":""}])

const[status,setStatus]=useState(false)
useEffect(()=>{
  FetchNoOfWinners()

},[RankTo])

const FinalSubmit=()=>{
  setRankListForWinners(rankDeatails);
  setPrizeListStatus(true)
}

const FetchNoOfWinners=()=>{
  const index=rankDeatails.length-1;
  console.log("ahh")
  var lengthOf=0;
    for(var b=RankFrom;b<=RankTo;b++){
      console.log("ahh"+b)
      lengthOf=lengthOf+1;
      if(RankTo==b){
        console.log("FINAL B"+b)
        if(RankFrom==0){
          setNoOfWinners(lengthOf-1)

        }
        else{
          console.log("here")
          setNoOfWinners(lengthOf)
       }
      }
    }
    rankDeatails[index].winningAmount=winning;
    rankDeatails[index].noOfWinners=noofWinners;
    setRankDerails([...rankDeatails])

}

const HandleChenge=(e)=>{
  setRankFrom(e.target.value);
}
const HandleChenge2=(e)=>{

  setRankFrom(currentRank)
  setRankTo(e.target.value)
}
const HandleChenge3=(e)=>{
setWinnig(e.target.value)

setAvailAmount(gameAmount-(e.target.value*noofWinners));


}

const HandleSubmit=()=>{
  const lengthOfArray=rankDeatails.length;
  const index=lengthOfArray-1;
  // rankDeatails[index].fromRank=RankFrom;
  rankDeatails[index].toRank=RankTo;
  rankDeatails[index].winningAmount=winning;
  rankDeatails[index].noOfWinners=noofWinners;
  setRankDerails([...rankDeatails])
  console.log(rankDeatails)
  setAvailAmount(gameAmount-(winning*noofWinners));

  sampleDetail.fromRank=Number(RankTo)+Number("1");
  var total=0;
  for(var i=0;rankDeatails.length>i;i++){
    total=rankDeatails[i].noOfWinners+total;
    console.log(total)
  }

  setTotalNoOfWinners(total)
  if(sampleDetail.fromRank>(totalteams*(winpercentage/100))){
    setStatus(true);
  }
  else{
    sampleDetail.toRank="0";
    sampleDetail.noOfWinners="0";
    sampleDetail.winningAmount="0";
       setCurrentRank(Number(RankTo)+Number("1"))
       setSampleDetail({...sampleDetail});
    setRankDerails(rankDeatails=>[...rankDeatails,sampleDetail])
    setRankFrom("")
    setRankTo("")
    setWinnig("")
    setNoOfWinners("")
    setGameAmount(availAmount);
   
  }
  
  
}

const Check=()=>{
  console.log(rankDeatails);
}

  return (
    <div className='container-fluid'>
      <div className='container' id='clm1'>

        <div className='container' >
          <div className='classnameeeee'> <label> Entre Fee :</label>{contestAmount / totalteams}</div>
        </div>
        <div className='container'>
          <div className='classnameeeee'><label>Total Teams :</label>{totalteams}</div></div>
        <div className='container'>
          <div className='classnameeeee'> <label>Winning Percentage:</label>{winpercentage}%</div></div>


      </div>

      <div className='container'>
        <table className="table table-dark">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">CONTEST AMOUNT</th>
              <th scope="col"> GAMING PERCENTAGE</th>
              <th scope="col">GAME AMOUNT</th>
              <th scope="col">AVAIL AMOUNT</th>

              <th scope='col'>TOTAL TEAMS</th>
              <th scope='col'>FINAL WINNERS</th>

            </tr>
          </thead>
          <tbody>
            <tr>
              <th scope="row">1</th>
              <td><strong>{contestAmount}</strong></td>
              <td><strong>{"70"}%</strong></td>
              <td>{contestAmount * (70 / 100)}</td>
              <td>{availAmount}</td>

              <td>{totalteams}</td>
              <td>{totalteams*(winpercentage/100)}</td>

            </tr>
          </tbody>
        </table>

      </div>
      <table className="table">
  <thead className="thead-dark">
    <tr>
    
      <th className='withff' scope="col">RANK FROM</th>
      <th className='withff' scope="col">RANK TO</th>
      <th scope="col">WINNINGS</th>
      <th scope="col">No.Of.Winners({totalNoofWinners})</th>
      <th scope='col'>RANK LIST</th>
    </tr>
  </thead>
  <tbody>

  


    
   {rankDeatails.length>0 ? (rankDeatails.map((rankdetil,index)=>
    <tr  key={index}>
      <td className='withff'><input onChange={HandleChenge} value={rankdetil.fromRank} className='vanakam' type="text"></input></td>
      <td className='withff'><input onChange={HandleChenge2}  className='vanakam' type="text"></input></td>
      <td><input onChange={HandleChenge3} type='text'></input></td>
      <td>{rankdetil.noOfWinners}</td>
      <td>{rankdetil.fromRank}-{rankdetil.ToRank} {"--> "}{rankdetil.winningAmount}</td>
    </tr>
  )):("NONE")}


  
   
    
  </tbody>
</table>
{!status? (<div><button className='btn btn-primary' onClick={HandleSubmit}>ONADD</button></div>
):(<div><button onClick={FinalSubmit} className='btn btn-primary' >FINISH PRIZE LIST</button></div>
)}
<p onClick={Check}>Check </p>


    </div>
  )
}

export default MaualPrize