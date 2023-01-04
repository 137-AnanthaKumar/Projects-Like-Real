import "./HeroImgStyles.css";
// import "./HomeStyles.css";

import React from "react";
import { Link } from "react-router-dom";
import IntroImg from "../assets/intro-bg7.jpg";

const HeroImg = () => {
  return (
    <div class="hero">
      <div className="mask">
        <img className="intro-img" src={IntroImg} alt="Intro" />
      </div>

      <div className="content">
        {/* <p>Hire an amazing local photographer anywhere in the world </p>
        <h4>Choose a photographer you love — we’ll take care of the rest</h4> */}

        <p>Hire an amazing local Photographer</p>
        <h4>Choose a photographer you love</h4>

        <div>
          <Link to="/About" className="btn">
            About us
          </Link>
          <Link to="/contact" className="btn btn-light">
            Contact
          </Link>
        </div>
      </div>
    </div>
  );
};

export default HeroImg;
