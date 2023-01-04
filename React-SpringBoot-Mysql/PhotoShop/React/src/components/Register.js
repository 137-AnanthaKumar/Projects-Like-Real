import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import Navbar from "./Navbar";
import Footer from "./Footer";
import "./Reg.css";

function Register(props) {
  const navigate = useNavigate();
  const [input, setInput] = useState({
    name: "",
    contact: "",
    gmail: "",
    role: "",
    category: "",
    userName: "",
    password: "",
  });

  const [nameerror, setnameerror] = useState("");
  const [contacterror, setcontacterror] = useState("");
  const [gmailerror, setgmailerror] = useState("");
  const [userNameerror, setuserNameerror] = useState("");
  const [passError, setPassError] = useState("");

  //Name validation
  const expr = /^[A-Z][A-Za-z0-9_]{5,15}$/;
  const validateName = (name) => {
    if (expr.test(name)) {
      setnameerror("");
      return true;
    } else {
      setnameerror("Enter a valid name");
      return false;
    }
  };

  //Contact Validation
  const cexpr = /^[0-9]{10}$/;
  const validateContact = (contact) => {
    if (contact && cexpr.test(contact)) {
      setcontacterror(true);
      setcontacterror("");
      return true;
    } else {
      setcontacterror(false);
      setcontacterror("It should contain 10 digits");
      return false;
    }
  };

  //Gmail validation
  const emailexpr = /^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$/;
  const validateGmail = (gmail) => {
    if (gmail && emailexpr.test(gmail)) {
      setgmailerror(true);
      setgmailerror("");
      return true;
    } else {
      setgmailerror(false);
      setgmailerror("Enter valid email id");
      return false;
    }
  };

  //Password validation
  const isPassValid = (a) => {
    let imp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
    if (imp.test(a)) {
      setPassError("");
      return true;
    } else {
      setPassError(
        "password must contain alphanumeric value and must contain more than 8 charector "
      );
      return false;
    }
  };

  //Username validation
  const usernameexpr = /^[A-Z][A-Za-z0-9_]{5,15}$/;
  const validateUsername = (userName) => {
    if (userName && usernameexpr.test(userName)) {
      setuserNameerror(true);
      setuserNameerror("");
      return true;
    } else {
      setuserNameerror(false);
      setuserNameerror(
        "It should contain atleast 5 or not more than 15 character,It should start with alphabet,it should not start with special character,it is mandatory field it cannot be blank,it should also contain number,it should contain underscore"
      );
      return false;
    }
  };

  let updateSignupData = (e) => {
    setInput({
      ...input,
      [e.target.name]: e.target.value,
    });
  };
  let signupSubmit = (e) => {
    e.preventDefault();
  };

  let signup = async (e) => {
    let nameValid = validateName(input.name);
    let contactValid = validateContact(input.contact);
    let gmailValid = validateGmail(input.gmail);
    let usernameValid = validateUsername(input.userName);
    let passValid = isPassValid(input.password);

    if (nameValid && passValid) {
      let res = await axios.post(
        "http://localhost:8080/photographer/reg",
        input
      );
      console.log(res.msg);
      alert(res.data.msg);
      console.log(res.data);

      // alert("User created successfully");
      navigate("/login");
      setInput({
        name: "",
        contact: "",
        gmail: "",
        category: "",
        userName: "",
        password: "",
      });
    }
  };

  let goToLogin = (e) => {
    navigate("/login");
  };
  return (
    <>
      <Navbar />

      <section className="register-block">
        <div className="container">
          <div className="row ">
            <div className="col register-sec">
              <h2 className="text-center">Register Now</h2>
              <form onSubmit={signupSubmit} className="register-form" action="">
                <div className="form-group">
                  <label
                    htmlFor="exampleInputEmail1"
                    className="text-uppercase"
                  >
                    Name
                  </label>

                  <input
                    value={input.name}
                    onChange={updateSignupData}
                    name="name"
                    type="text"
                    className="form-control"
                    id="exampleInputPassword1"
                    placeholder="name"
                  />
                  {validateName ? (
                    <span id="errmsg" style={{ color: "yellow" }}>
                      {nameerror}
                    </span>
                  ) : null}
                </div>

                <div className="form-group">
                  <label
                    htmlFor="exampleInputEmail1"
                    className="text-uppercase"
                  >
                    contact
                  </label>
                  <input
                    value={input.contact}
                    onChange={updateSignupData}
                    name="contact"
                    type="text"
                    className="form-control"
                    id="exampleInputPassword1"
                    placeholder="contact"
                  />
                  {validateContact ? (
                    <span id="errmsg" style={{ color: "orangered" }}>
                      {contacterror}
                    </span>
                  ) : null}
                </div>

                <div className="form-group">
                  <label
                    htmlFor="exampleInputEmail1"
                    className="text-uppercase"
                  >
                    Gmail
                  </label>
                  <input
                    value={input.gmail}
                    onChange={updateSignupData}
                    name="gmail"
                    type="email"
                    className="form-control"
                    id="exampleInputPassword1"
                    placeholder="Gmail"
                  />
                  {validateGmail ? (
                    <span id="errmsg" style={{ color: "orangered" }}>
                      {gmailerror}
                    </span>
                  ) : null}
                </div>

                <div className="form-group">
                  <label
                    htmlFor="exampleInputEmail1"
                    className="text-uppercase"
                  >
                    category
                  </label>
                  <input
                    value={input.category}
                    onChange={updateSignupData}
                    name="category"
                    type="text"
                    className="form-control"
                    placeholder=" Enter the category of photography"
                  />
                </div>

                <div className="form-group">
                  <label
                    htmlFor="exampleInputEmail1"
                    className="text-uppercase"
                  >
                    Username
                  </label>
                  <input
                    value={input.userName}
                    onChange={updateSignupData}
                    name="userName"
                    type="text"
                    className="form-control"
                    id="exampleInputPassword1"
                    placeholder="userName"
                  />
                  {validateUsername ? (
                    <span id="errmsg" style={{ color: "yellow" }}>
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
                    value={input.password}
                    onChange={updateSignupData}
                    name="password"
                    type="password"
                    className="form-control"
                    id="exampleInputPassword1"
                    placeholder="password"
                  />
                  {isPassValid ? (
                    <span id="errmsg" style={{ color: "yellow" }}>
                      {passError}
                    </span>
                  ) : null}
                </div>

                <div className="form-group">
                  <input
                    onClick={(e) => {
                      signup();
                    }}
                    type="submit"
                    className="btn btn-login float-right"
                    value="signup"
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
                      goToLogin();
                    }}
                  >
                    Already have account ? Please <a href="/login">Login</a>
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

export default Register;
