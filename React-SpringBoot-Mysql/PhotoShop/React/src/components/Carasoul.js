import React from "react";
import "bootstrap/dist/css/bootstrap.css";
import Carousel from "react-bootstrap/Carousel";
import "./Carasoulstyles.css";
// import Footer from "./Footer";

const Carasoul = () => {
  return (
    <>
      <div>
        <Carousel>
          <Carousel.Item>
            <img
              className="d-block w-100"
              src="https://images.unsplash.com/photo-1465210402880-b309f971f374?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1025&q=80"
              alt="First slide"
            />
            <Carousel.Caption className="content">
              <h3>"I Walk ,I look,I see,I stop,I photograph."</h3>
              <p>-Leon Levinstein</p>
            </Carousel.Caption>
          </Carousel.Item>

          <Carousel.Item>
            <img
              className="d-block w-100"
              src="https://images.unsplash.com/photo-1465251126641-16c25b923501?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=838&q=80"
              alt="Second slide"
            />

            <Carousel.Caption className="content">
              <h3>"You don't take photograph,you make it."</h3>
              <p>-Ansel Adams.</p>
            </Carousel.Caption>
          </Carousel.Item>

          <Carousel.Item>
            <img
              className="d-block w-100"
              src="https://images.unsplash.com/photo-1506434304575-afbb92660c28?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=871&q=80"
              alt="Third slide"
            />

            <Carousel.Caption className="content">
              <h3>"Only photograph what you love"</h3>
              <p>-Tim Walker</p>
            </Carousel.Caption>
          </Carousel.Item>
        </Carousel>
        {/* <!-- Carousel wrapper --> */}
        <hr />
      </div>
    </>
  );
};

export default Carasoul;
