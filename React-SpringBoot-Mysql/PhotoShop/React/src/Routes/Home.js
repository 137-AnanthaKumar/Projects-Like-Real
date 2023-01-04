import React from "react";

import Carasoul from "../components/Carasoul";
import Footer from "../components/Footer";
import Navbar from "../components/Navbar";
// import HeroImg from "../components/HeroImg";

const Home = () => {
  return (
    <div>
      <Navbar />
      {/* <HeroImg /> */}

      <Carasoul />
      <Footer />
    </div>
  );
};

export default Home;
