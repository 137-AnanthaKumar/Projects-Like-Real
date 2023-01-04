import "./ContactFormStyles.css";

import React from "react";

const ContactForm = () => {
  return (
    <>
      <div className="container">
        <div className="form">
          <form>
            <label style={{ color: "white" }}>Your Name</label>
            <input type="text"></input>
            <label style={{ color: "white" }}>Email</label>
            <input type="email"></input>
            <label style={{ color: "white" }}>Message</label>
            <textarea
              style={{ color: "white" }}
              rows="6"
              placeholder="Type your message here"
            />
            <button className="btn">SUBMIT</button>
          </form>
        </div>
      </div>
    </>
  );
};

export default ContactForm;
