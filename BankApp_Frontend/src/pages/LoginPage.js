import React from 'react'
import { useState } from 'react'
import axios from 'axios'

export default function LoginPage() {
  const baseURL = "http://localhost:3000/login";
  const [username,setUsername]=useState("");
  const [password,setPassword]=useState("");

  const onUsernameChange = (event) => {
    setUsername(event.target.value);
  };

  const onPasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const onLogin = (event) => {
    event.preventDefault();
    axios
      .post(baseURL,{
          username:username,
          password:password
      })
      .then((response)=>{
        alert(response.data);
        console.log(response);
        //window.location="/dashboard";
      })
      .catch((err)=>{
        alert("error- "+err)
      });  
  };

  return (
    <div>
      <form onSubmit={onLogin} >
      <h2>LoginPage</h2>
      <label>Username</label>
      <input type="text" value={username} required onChange={onUsernameChange}/><br/>
      <label>Password</label>
      <input type="password" value={password} required onChange={onPasswordChange}/><br/>
      <button type="submit">Login</button>
      </form>
    </div>
  )
}
