import React from 'react'
import { useState } from 'react';
import './new.css';

import FileUploadService from '../../service/service';
//import { useEffect } from 'react';
function Upload({setFile,save}) {
    const [selectedFiles, setSelectedFiles] = useState(undefined);
    const [currentFile, setCurrentFile] = useState(undefined);
    const [progress, setProgress] = useState(0);
    const [message, setMessage] = useState("");
   // const [fileInfos, setFileInfos] = useState([]);
  
    // useEffect(() => {
    //     FileUploadService.getFiles().then((response) => {
    //     setFileInfos(response.data);
    //   });
    // }, []);
  
    const selectFile = (event) => {
      setSelectedFiles(event.target.files);
    };
  
    const upload = () => {
      let currentFile = selectedFiles[0];
  
      setProgress(0);
      setCurrentFile(currentFile);
  
      FileUploadService.upload(currentFile, (event) => {
        setProgress(Math.round((100 * event.loaded) / event.total));
      }, 'Nature')
        .then((response) => {
          setMessage(response.data.message);
        //   return FileUploadService.getFiles();
        })
     .catch(() => {
          setProgress(0);
          setMessage("Could not upload the file!");
          setCurrentFile(undefined);
        });
  
      setSelectedFiles(undefined);
    };
  
    return (
      <div>
        {currentFile && (
          <div className="progress">
            <div
              className="progress-bar progress-bar-info progress-bar-striped"
              role="progressbar"
              aria-valuenow={progress}
              aria-valuemin="0"
              aria-valuemax="100"
              style={{ width: progress + "%" }}
            >
              {progress}%
            </div>
          </div>
        )}
  
         <div className='uplaodform'>
            <div>  <label className="btn btn-default">
          <input type="file" onChange={selectFile} />
        </label></div>
        <div></div>
        <button
          className="btn btn-success"
          disabled={!selectedFiles}
          onClick={upload}
        >
          Upload
        </button>
         </div>

      
  
    
  
        <div className="alert alert-light" role="alert">
          {message}
        </div>
  
     
      </div>
    );
}

export default Upload;