import React, { useEffect, useState} from 'react'

function Gallary(props) {
    const [obj, setObj] = useState([]);

  useEffect(() => {
    console.log("running");
    console.log(props.update);
    fetch('http://localhost:8080/all-photos', {
      method: 'GET'
    })
      .then((res) => {
        if (res.ok) {
          console.log("success");
          return res.json();


        } else {
          throw Error("Not working" + " " + res.url);
        }

      }).then((data) => {
        data.forEach(element => {
          console.log(element);
        });
        setObj(data);
        // console.log(data);
      })
      .catch((err) => console.log("Error is here", err));
      

  }, [props.update])
  return (
    <div className="row my-3 gallary">
      <div className="col all-images">
        {
          obj.map(data => {
            const { id, imageName, imageSize, className } = data;
            return (<div key={id} className={`photo-card ${className}`}>
              <img src={"/images/" + imageName} alt={id} />
            </div>)
          })
        }


      </div>
    </div>
  )
}

export default Gallary