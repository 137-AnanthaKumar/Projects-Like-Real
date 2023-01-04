import React from "react";
import { Route, Routes } from "react-router";

import "./index.css";

import Home from "./Routes/Home";
import About from "./Routes/About";
import Contact from "./Routes/Contact";

import Login from "./components/Login";
import Register from "./components/Register";
import Admin from "./components/Admin";
import Photographer from "./components/Photographer";

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/contact" element={<Contact />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/admin" element={<Admin />} />
        <Route path="/photographer" element={<Photographer />} />
      </Routes>
    </>
  );
}

export default App;
