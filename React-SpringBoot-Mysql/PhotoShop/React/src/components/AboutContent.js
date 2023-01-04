import "./AboutContentStyles.css";

import React from "react";
import { Link } from "react-router-dom";

import Webpage1 from "../assets/webp1.avif";
import Webpage2 from "../assets/webp2.avif";

const AboutContent = () => {
  return (
    <div className="about">
      <div className="left">
        <h1>Who We Are?</h1>
        <p>
          PhotoHUb is a platform for creative professionals around the world to
          showcase their own work and to discover the creative work of others
          through Search the perfect photographer. These PhotoHub Community
          Guidelines (“Guidelines”) govern your use of PhotoHub to maintain an
          engaging and trustworthy community that fosters creativity across the
          platform.
        </p>
        <Link to="/contact">
          <button className="btn">Contact</button>
        </Link>
      </div>

      <div className="right">
        <div className="img-container">
          <div className="img-stack top">
            <img src={Webpage1} className="img" alt="true" />
          </div>
          <div className="img-stack bottom">
            <img src={Webpage2} className="img" alt="true" />
          </div>
        </div>
      </div>
    </div>
  );
};

export default AboutContent;
