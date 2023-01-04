import React from "react";
import ContactForm from "../components/ContactForm";
import Footer from "../components/Footer";
import HeroImg2 from "../components/HeroImg2";
import Navbar from "../components/Navbar";

const Contact = () => {
  return (
    <>
      <Navbar />
      <HeroImg2 heading="CONTACT" text="Contact Us for any Queries" />
      <ContactForm />
      <Footer />
    </>
  );
};
export default Contact;
