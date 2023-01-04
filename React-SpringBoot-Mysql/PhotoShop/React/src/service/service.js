import axios from "axios";


const upload = (file, onUploadProgress,catagory) => {
  let formData = new FormData();

  formData.append("file", file);
  let jwtToken = localStorage.getItem("jwtToken");

  let token = `Bearer ${jwtToken}`;
  let userId=localStorage.getItem("userId");

  return axios.post(`http://localhost:8080/photo/upload/${catagory}/${userId}`, formData, {
    headers: {
      "Authorization": token,
      "Access-Control-Allow-Origin": "*",
      "Content-Type": "multipart/form-data",
    },
    onUploadProgress,
  });
};

const getFiles = () => {
  return axios.get("/files");
};

const FileUploadService = {
  upload,
  getFiles,
};

export default FileUploadService;