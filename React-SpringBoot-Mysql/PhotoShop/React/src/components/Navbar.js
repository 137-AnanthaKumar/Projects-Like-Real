import "./NavbarStyles.css";
// import "./HomeStyles.css";
import React, { useState } from "react";

import { Link } from "react-router-dom";
//Importing icons
import { FaBars, FaTimes } from "react-icons/fa";
//rafce(functional component shortcut)

import Logo from "../assets/logo.png";
import Photo_Search from "./Photo_Search";

const Navbar = () => {
  const [click, setClick] = useState(false);
  const handleClick = () => setClick(!click);
  return (
    // <Navbar bg="white" variant="dark">
    <div className="header">
      <Link to="/">
        <img src={Logo} alt="true" className="logo-img" />
      </Link>

      <Photo_Search />

      <ul className={click ? "nav-menu active" : "nav-menu"}>
        <li>
          <Link to="/">Home</Link>
        </li>

        <li>
          <Link to="/about">About</Link>
        </li>
        <li>
          <Link to="/contact">Contact</Link>
        </li>
        <li>
          <Link to="/login">Login</Link>
        </li>
        <li>
          <Link to="/register">Register</Link>
        </li>
      </ul>

      {/* Creating top side threebars called Hamburger*/}
      <div className="hamburger" onClick={handleClick}>
        {click ? (
          <FaTimes size={20} style={{ color: "#fff" }} />
        ) : (
          <FaBars size={20} style={{ color: "#fff" }} />
        )}
      </div>
    </div>
    // </Navbar>
  );
};

export default Navbar;
