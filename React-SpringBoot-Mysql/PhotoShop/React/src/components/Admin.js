import React, { useEffect, useState } from "react";

import { Table } from "react-bootstrap";
import "./Admin.css";

import axios from "axios";
import Footer from "./Footer";
import Navbar from "./Navbar";

function Admin() {
  const [AdminAndUsers, setAdminAndUsers] = useState([]);

  useEffect(() => {
    console.log("useeffe");
    fetchProducts();
  }, []);

  let fetchProducts = async () => {
    let jwtToken = localStorage.getItem("jwtToken");

    let token = `Bearer ${jwtToken}`;
    console.log(token);

    const header = {
      Authorization: token,
      "Access-Control-Allow-Origin": "*",
    };
    //eturn axios.get(url+"/"+contestCode,{headers:header})

    let res = await axios.get("http://localhost:8080/admin/allphotographers", {
      headers: header,
    });

    console.log(res.data);

    // if (res.data) {

    //   alert(res.data.msg);

    // } else {

    let fetchProducts = res.data;

    setAdminAndUsers(fetchProducts);

    // }
  };

  let deleteAccount = async (id) => {
    try {
      let jwtToken = localStorage.getItem("jwtToken");

      let token = `Bearer ${jwtToken}`;

      let res = await axios.delete(
        `http://localhost:8080/remove/${id}`

        ,{

          headers: { Authorization: token },

        }
      );

      console.log(res.data);

      alert(res.data.msg);

      fetchProducts();
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <>
      <Navbar />
      <div classname="admin" style={{ marginTop: "100px" }}>
        <Table classname="table" striped bordered hover>
          <thead>
            <tr>
              <th>ID</th>

              <th>NAME</th>

              <th>CONTACT</th>

              <th>GMAIL </th>

              <th>CATEGORY</th>

              <th>USERNAME</th>

              <th>PASSWORD</th>

              <th>ACTION</th>
            </tr>
          </thead>

          <tbody>
            {AdminAndUsers.map((details) => {
              return (
                <tr key={details.id}>
                  <td>{details.id}</td>

                  <td>{details.name}</td>

                  <td>{details.contact}</td>

                  <td>{details.gmail}</td>

                  <td>{details.category}</td>

                  <td>{details.userName}</td>

                  <td>{details.password}</td>

                  <td>
                    <button
                      className="btn btn-danger"
                      onClick={() => {
                        deleteAccount(details.id);
                      }}
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </Table>
      </div>
      <Footer />
    </>
  );
}

export default Admin;
