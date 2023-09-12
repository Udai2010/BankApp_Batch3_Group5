import React, { useState } from 'react';
import axios from "axios";
// import { Link } from 'react-router-dom';

export default function RegistrationPage() {

  const baseUrl = "http://localhost:3000/createCustomer"

  const [name, setname] = useState("")
  const [email, setemail] = useState("")
  const [pannumber, setpannumber] = useState("")
  const [dob, setdob] = useState("")
  const [password, setpassword] = useState("")
  const [fathername, setfathername] = useState("")
  const [mothername, setmothername] = useState("")
  const [address, setaddress] = useState("")

  const nameChangeHandler = (event) => {
    setname(event.target.value);
  };

  const emailChangeHandler = (event) => {
    setemail(event.target.value);
  };

  const pannumberChangeHandler = (event) => {
    setpannumber(event.target.value);
  };

  const dobChangeHandler = (event) => {
    setdob(event.target.value);
  };

  const passwordChangeHandler = (event) => {
    setpassword(event.target.value);
  };

  const fathernameChangeHandler = (event) => {
    setfathername(event.target.value);
  };

  const mothernameChangeHandler = (event) => {
    setmothername(event.target.value);
  };

  const addressChangeHandler = (event) => {
    setaddress(event.target.value);
  };

  const submitHandler = (event) => {
    event.preventDefault();
    axios.post(baseUrl, {

      name: name,
      email: email,
      pan_number: pannumber,
      password: password,
      dob: dob,
      fathername: fathername,
      mothername: mothername,
      address: address

    }).then((response) => {
      alert("Customer Created Successfully")
      console.log(response)
      // {<Link to="localhost/" />}
    }).catch((error) => {
      alert(error)
    })

  }

  return (
    <center>    
      <div>
      <form>
        <label for="name">Customer Name</label>
        <input type="text" onChange={nameChangeHandler} id="name" ></input><br></br>

        <label for="email">Email</label>
        <input type="email" onChange={emailChangeHandler} id="email" ></input><br></br>

        <label for="pannumber">Pan Number</label>
        <input type="text" onChange={pannumberChangeHandler} id="pannumber" ></input><br>
        </br>

        <label for="dob">Dob</label>
        <input type="text" onChange={dobChangeHandler} id="dob" ></input><br></br>

        <label for="password">Password</label>
        <input type="text" onChange={passwordChangeHandler} id="password" ></input><br></br>

        <label for="fathername">Father Name</label>
        <input type="text" onChange={fathernameChangeHandler} id="fathername" ></input><br></br>

        <label for="mothername">Mother Name</label>
        <input type="text" onChange={mothernameChangeHandler} id="mothername" ></input><br></br>

        <label for="address">Address</label>
        <input type="text" onChange={addressChangeHandler} id="address" ></input><br></br>

        <input type="submit" onClick={submitHandler} value="register"></input>

      </form>



    </div>
    </center>
  )
}
