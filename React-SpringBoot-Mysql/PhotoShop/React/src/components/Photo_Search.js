import React, { useState } from "react";
import axios from "axios";
import { Card, Row, Col, Container } from "react-bootstrap";
import "./Photo_Search.css";

function Photo_Search() {
  const [category, setCategory] = useState();
  const [photographers, setPhotographers] = useState([]);

  // photographers = async (category) => {
  //   let res = await axios.get(`http://localhost:8080/search/get/${category}`);

  //   setPhotographers(res.data);
  // };

  let photoOnclick = async (category) => {
    //  return axios.get(url + "/" + contastId, { headers: header });
    let res = await axios.get(`http://localhost:8080/search/get/nature`);

    setPhotographers(res.data);
  };

  let onSearch = (e) => {
    setCategory({
      ...category,
      [e.target.name]: e.target.value,
    });
    console.log(category);

    <select value={setCategory}>
      <option value="#">Search Photographer For</option>
      <option value="wedding">wedding</option>
      <option value="event">event</option>
      <option value="consert">consert</option>
    </select>;
  };

  return (
    <>
      <form className="search">
        <select name="category" value={category} onChange={onSearch}>
          <option value="#" hidden>
            Search Photographer For
          </option>

          <option onClick={photoOnclick} value="wedding">
            wedding
          </option>
          <option onClick={photoOnclick} value="event">
            event
          </option>
          <option onClick={photoOnclick} value="consert">
            consert
          </option>
        </select>
      </form>
    </>
  );
}

export default Photo_Search;

//  <div>
//         <Container>
//           <Row>
//             {photographers.map((photographers, k) => (
//               <Col key={k} xs={12} md={4} lg={3}>
//                 <Card>
//                   <Card.Img src={} />

//                   <Card.Body>
//                     <Card.Title>{photographers.name}</Card.Title>
//                     <Card.Text>{photographers.contact}</Card.Text>
//                   </Card.Body>
//                 </Card>
//               </Col>
//             ))}
//           </Row>
//         </Container>
//       </div>
