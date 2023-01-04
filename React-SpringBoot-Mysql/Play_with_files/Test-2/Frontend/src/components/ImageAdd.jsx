import React, {useState} from 'react'

function ImageAdd(props) {
    const [count, setCount] = useState(0);

    const [fileInput, setInput] = useState({
        imageName: '',
        imageSize: 0,
        className: ''
    });
    const [buttonOperation, setButtonOperation] = useState("");
    const dimension = {
        width: 0,
        height: 0
    }

    const [getDim, setDim] = useState(dimension);

    function increment() {
        setCount(pre => pre + 1);
    }

    const getInputValue = (e) => {
        let img = new Image();
        const inputValue = e.target;
        const fileName = inputValue.files[0].name;
        const fileType = inputValue.files[0].type;
        const fileSize = inputValue.files[0].size;
        img.src = window.URL.createObjectURL(e.target.files[0])
        img.onload = () => {
            let height = img.height;
            let width = img.width;
            setDim({
                width: width,
                height: height

            })

        }
        console.log(fileSize);
        const type = fileType.split('/')[1];
        if (fileName && (type === 'png' || type === 'jpeg')) {
            setInput({ ...fileInput, imageName: fileName, imageSize: fileSize });
            increment();

            setButtonOperation('');

        } else {

            setInput({
                imageName: 'Wrong type file',
                imageSize: 0,
                className: ''

            });
            setButtonOperation('disable');

        }

    }

    function buttonClick() {
        console.log(getDim, "ButtonClicked");
        if (getDim.height > getDim.width) {
            console.log("Tall");
            setInput(pre => ({ ...pre, className: 'tall' }));

        } else {
            console.log("wide");
            setInput(pre => ({ ...pre, className: '' }));

        }
    }


    function formSubmit(e) {
        e.preventDefault();

        if (fileInput.imageSize === 0) {
            setInput({ ...fileInput, imageName: "Wrong option" });

        } else {
            console.log(fileInput);
            console.log(getDim);

            fetch('http://localhost:8080/save', {
                method: 'POST',
                body: JSON.stringify(fileInput),
                headers: {
                    'Content-type': 'application/json'
                }

            }).then((res) => {
                if (res.ok) {
                    console.log("success" + " " + res);

                    return res.text();


                } else {
                    throw Error("Not working" + " " + res.url);
                }

            }).then((data) => {
                // data.forEach(element => {
                //     console.log(element);
                // });
                console.log(data);
                console.log(count);
                callApp(count);
            })
                .catch((err) => console.log("Error is here", err));

            setInput({
                imageName: '',
                imageSize: 0,
                className: ''

            });
            


        }
        

    }
    

    function callApp(a) {
        props.addImage(a);

    }
    return (
        <div className="row pt-3 image-add">
            <div className="col-4 m-auto">

                <form onSubmit={formSubmit} className="text-center">
                    <h5 className="imageName" id="imageName">{fileInput.imageName && ` "${fileInput.imageName}" selected`}</h5>

                    <input type="file" hidden name="image" id="image-section" onChange={getInputValue} />
                    <label htmlFor="image-section"><i className="fa-solid fa-plus"></i></label><br />
                    <button type="submit" className={`btn btn-success mt-2 ${buttonOperation}`} onClick={buttonClick}>
                        upload
                    </button>

                </form>

            </div>
        </div>
    )
}

export default ImageAdd