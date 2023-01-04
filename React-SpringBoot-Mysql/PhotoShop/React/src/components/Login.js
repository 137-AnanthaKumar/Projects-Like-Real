import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

import Navbar from "./Navbar";
import Footer from "./Footer";
import "./Login.css";

function Login(props) {
  const navigate = useNavigate();
  const [input, setInput] = useState({
    userName: "",
    password: "",
  });

  // console.log("props ", props);
  const [passError, setPassError] = useState(false);
  console.log(passError);

  const [userNameerror, setUsernameerror] = useState(false);
  console.log(userNameerror);

  let updateLoginData = (e) => {
    setInput({
      ...input,
      [e.target.name]: e.target.value,
    });
  };

  // const expr = /^[a-zA-Z_]{3,15}$/;

  const validateUsername = (a) => {
    const imp = /^[A-Z][A-Za-z0-9_]{5,15}$/;
    //  const uppercase=/^[A-Z]/;
    // // const charLength=/{3,15}$/;
    if (imp.test(a)) {
      setUsernameerror("");
      return false;
    } else {
      setUsernameerror(
        "It should contain atleast 5 or not more than 15 charector,It should start with alphabet,it should not start with special charector,it is mandatory field it cannot be blank,it should also contain number,it should contain underscore"
      );
      return true;
    }
  };

  const isPassValid = (a) => {
    let imp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
    if (imp.test(a)) {
      setPassError("");
      return false;
    } else {
      setPassError("password did not match.please enter the valid password");
      return true;
    }
  };

  let loginSubmit = (e) => {
    e.preventDefault();
  };

  let login = async (e) => {
    let passValid = isPassValid(input.password);
    let userNameValid = validateUsername(input.userName);
    if (!userNameValid && !passValid) {
      let res = await axios.post("http://localhost:8080/admin/login", input);
      console.log(res.msg);
      alert(res.data.msg);
      console.log(res);

      if (res.data.error) {
        console.log(res.data.msg);
      } else {
        console.log(res.data.role[0].authority);
        // console.log(res.data.jwt);
        // console.log(res.data.res);
        // console.log(res);
        console.log(res.data.msg);
        localStorage.setItem("jwtToken", res.data.jwt);
        localStorage.setItem("role", res.data.role[0].authority);
        localStorage.setItem("name", res.data.name);
        localStorage.setItem("userId", res.data.userId);

        let role = localStorage.getItem("role");
        if (role === "ROLE_Admin") {
          navigate("/Admin");
          alert("Logged in successfully");
        }
        if (role === "ROLE_Photographer") {
          navigate("/photographer");
          alert("Logged in successfully");
        } else {
          alert("Page not found ");
        }

        setInput({
          userName: "",
          password: "",
        });
      }
    } else {
      alert("Validation Failed");

      // } else {
      //   alert("Validation Failed");
      // }
    }
  };

  let goToSignup = (e) => {
    navigate("/register");
  };

  return (
    <>
      <Navbar />
      <section className="login-block">
        <div className="container">
          <div className="row ">
            <div className="col login-sec">
              <h2 className="text-center">Login Now</h2>
              <form onSubmit={loginSubmit} className="login-form" action="">
                <div className="form-group">
                  <label
                    htmlFor="exampleInputemail1"
                    className="text-uppercase"
                  >
                    User Name
                  </label>

                  <input
                    className="form-control"
                    type="text"
                    name="userName"
                    onChange={updateLoginData}
                    value={input.userName}
                    id="exampleInputEmail1"
                    placeholder="userName"
                  />
                  {validateUsername ? (
                    <span style={{ color: "yellow" }} id="errmsg">
                      {userNameerror}
                    </span>
                  ) : null}
                </div>

                <div className="form-group">
                  <label
                    htmlFor="exampleInputPassword1"
                    className="text-uppercase"
                  >
                    Password
                  </label>
                  <input
                    className="form-control"
                    name="password"
                    onChange={updateLoginData}
                    value={input.password}
                    type="password"
                    id="exampleInputPassword1"
                    placeholder="password"
                  />
                  {isPassValid ? (
                    <span style={{ color: "yellow" }} id="errmsg">
                      {passError}
                    </span>
                  ) : null}
                </div>

                <div className="form-group">
                  <input
                    onClick={(e) => {
                      login(e);
                    }}
                    type="submit"
                    className="btn btn-login float-right"
                    value="Login"
                  />
                </div>

                <div className="form-group">
                  <h5
                    style={{
                      color: "white",
                      marginLeft: "70px",
                      marginTop: "30px",
                    }}
                    onClick={(e) => {
                      goToSignup();
                    }}
                  >
                    Create new account ? Please
                    <a href="/register">Register</a>
                  </h5>
                </div>
              </form>
            </div>
          </div>
        </div>
        <Footer />
      </section>
    </>
  );
}

export default Login;
