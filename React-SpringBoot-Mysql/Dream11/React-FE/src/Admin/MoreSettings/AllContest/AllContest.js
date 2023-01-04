import React, { useEffect, useState } from 'react'
import AdminService from '../../../service/AdminService';
import { FcSearch } from 'react-icons/fc';
import './AllContest.css';
function AllContest({setPages,setContestIde,setContestCodee}) {
    const [contest, setContest] = useState([]);
    const [contestCode, setContestCode] = useState()
    const [contestId, setContestId] = useState();


    const [searchBy, setSearchBy] = useState("");
    const [inputBox, setInputBox] = useState(false)

    const InputField = (value) => {
        if (searchBy === "ContestCode") {
            setContestCode(value)
            console.log(value)
        }
        else if (searchBy === "ContestId") {
            setContestId(value);
            console.log(value)

        }
    }

    const OnSubmit=()=>{
     console.log("submitted")
    }

    useEffect(() => {
        AdminService.GetActiveContest().then((response) => {
            setContest(response.data)
            console.log(response.data)
        })

    }, [])

    useEffect(() => {

        if (searchBy === "ContestCode") {
            setInputBox(true)
        }
        else if (searchBy === "Active") {
            setInputBox(false)
            AdminService.GetActiveContestOnly().then((response) => {
                setContest(response.data);
            })
        }

        else if (searchBy === "All") {
            setInputBox(false)
            AdminService.GetActiveContest().then((response) => {
                setContest(response.data)
                console.log(response.data)
            })

        }
        else if (searchBy === "Live") {
            setInputBox(false)
            AdminService.GetLiveContestOnly().then((response) => {
                setContest(response.data);
            })
        }
        else if (searchBy === "ContestId") {
            setInputBox(true)
        }
        else {
            setInputBox(false)
        }
        console.log(searchBy)
    }, [searchBy])






    return (
        <div className='container'>
            <div className='container' id='sddfc3er'>
                <div className='container'>

                    <select onChange={(e) => {
                        setSearchBy(e.target.value)
                    }} className="form-select" aria-label="Default select example">
                        <option selected>Open this select menu</option>
                        <option value="All">Get All Contest</option>

                        <option value="Live">Search By Live</option>
                        <option value="Active">Search By Active</option>
                        <option value="ContestCode">Search By ContestCode </option>
                        <option value="ContestId">Search By ContestId </option>

                    </select>
                </div>
                {inputBox && <div class="form-group">
                    <input onChange={(e) => {
                        InputField(e.target.value)
                    }} type="email" className="form-control" id="exsrrrf" placeholder="Enter " />
                </div>}
                {inputBox && <div className='container'><button onClick={OnSubmit} className='btn btn-primary'>Search</button></div>}



            </div>

            <div className='container'><table className="table table-striped table-dark">
                <thead>
                    <tr>
                        <th scope="col">Contest Id</th>
                        <th scope="col">Contest Code</th>
                        <th scope="col">Contest Status</th>
                        <th scope="col">Title</th>
                        <th scope="col">LockStatus</th>
                        <th scope="col">Action</th>



                    </tr>
                </thead>
                <tbody>
                    {contest.map(contests =>

                        <tr key={contests.contestId}>
                            <th scope="row">{contests.contestId}</th>
                            <td>{contests.contestCode}</td>
                            <td>{contests.contextStatus}</td>
                            <td>{contests.title}</td>
                            <td>{contests.contestLock}</td>
                            <td><h6 id='jdrv7667t' onClick={()=>{
                                setContestIde(contests.contestId)
                                setContestCodee(contests.contestCode)
                                setPages("perticularContest")
                            }}>View</h6></td>

                        </tr>
                    )}
                </tbody>
            </table>
            </div>


        </div>

    )
}

export default AllContest