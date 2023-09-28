
import React, { useState, useEffect } from "react";
import { Link, useNavigate } from 'react-router-dom';

import axios from "axios";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { Grid } from "@mui/material";

import Backdrop from '@mui/material/Backdrop';
import Modal from '@mui/material/Modal';
import Fade from '@mui/material/Fade';
import { passwordHashService } from "../services/PasswordHashService";

const defaultTheme = createTheme();


const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  p: 4,
};


export default function LoginPage() {
  const baseURL = "http://localhost:3000/auth/login";
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  // const navigate = useNavigate();
  // const baseURL = "http://localhost:3000/login";

  // const [username, setUsername] = useState("");
  // const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const [success, setSuccess] = useState(false)
  const [view,setView]=useState(false); 
  const navigate = useNavigate();



  const [open, setOpen] = useState(true);
  const handleValidClose = () => {
    setOpen(false);
    navigate('/dashboard');
  };

  const handleInvalidClose = () => {
    setOpen(false);
    setSuccess(false);
    setView(false);
    setMessage("");
  };

  const onUsernameChange = (event) => {
    setUsername(event.target.value);
  };

  const onPasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const onLogin = async (event) => {
    event.preventDefault();
    const hashedPassword = passwordHashService(password);
    console.log(username);
    let response = await axios.post(baseURL, {
      userID: username,
      password: hashedPassword,
    }).catch((err) => {
      console.log(err);
      alert(err);
    });
    if (response.data) {
      const newToken = response.data;

      try {
        localStorage.setItem("token", newToken);
        console.log("Token saved to local storage:", newToken);
      } catch (error) {
        console.error("Error saving token to local storage:", error);
      }

      const token = localStorage.getItem("token");
      if (token) {
        console.log(token);
      }
      
      window.sessionStorage.setItem("customer_id", username);
      window.location.assign("/dashboard");
    } else {
      alert("error in authentication");
    }
    // const hashedPassword = passwordHashService(password)
    // console.log(username)
    // axios
    //   .post(baseURL, {
    //     userID: username,
    //     password: hashedPassword
    //   })

    //   const token = localStorage.getItem("token");
    //   if (token) {
    //     console.log(token);
    //   }
      
    //   window.sessionStorage.setItem("customer_id", username);
    //   window.location.assign("/dashboard");
    // } else {
    //   alert("error in authentication");
    // }
  };

  return (

    <>
    { !success ?
      <>
      <ThemeProvider theme={defaultTheme}>
        <Container component="main" maxWidth="sm">
          <CssBaseline />
          <Box
            sx={{
              p:3,
              color: '#000',
              marginTop: 8,
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
              backgroundColor: 'rgba(225, 225, 225, 0.8)',
              borderRadius: '10px'
            }}
          >
            <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}></Avatar>
            <Typography component="h1" variant="h5">
              Login
            </Typography>
            <Box component="form" onSubmit={onLogin} noValidate sx={{ mt: 1 }}>
              <Grid containter>
                <Grid item xm={12}>
                  <TextField
                    margin="normal"
                    required
                    fullWidth
                    id="username"
                    placeholder="Customer ID"
                    name="username"
                    value={username}
                    onChange={onUsernameChange}
                    autoFocus
                  />
                </Grid>
                <Grid item xm={12}>
                  <TextField
                    margin="normal"
                    required
                    fullWidth
                    id="password"
                    placeholder="Password"
                    name="password"
                    type="password"
                    value={password}
                    onChange={onPasswordChange}
                  />
                </Grid>
              </Grid>
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
              >
                Login
              </Button>

              <Button
                fullWidth
                variant="contained"
                component={Link} to="/forgotpassword"
                sx={{ mt: 3, mb: 2 }}
              >
                Forgot Password
              </Button>

              <Button
                fullWidth
                variant="contained"
                component={Link} to="/register"
                sx={{ mt: 3, mb: 2 }}
              >
                Register
              </Button>

            </Box>

          </Box>
        </Container>
      </ThemeProvider>
      </>
      : view ?
      <>
        <div>
            <Modal
              aria-placeholderledby="transition-modal-title"
              aria-describedby="transition-modal-description"
              open={open}
              onClose={handleValidClose}
              closeAfterTransition
              slots={{ backdrop: Backdrop }}
              slotProps={{
                backdrop: {
                  timeout: 500,
                },
              }}
            >
              <Fade in={open}>
                <Box sx={style}>
                  <Typography id="transition-modal-description" sx={{ mt: 2 }}>
                    {message}
                  </Typography>
                </Box>
              </Fade>
            </Modal>
          </div>
      </>
      :
      <>
        <div>
            <Modal
              aria-placeholderledby="transition-modal-title"
              aria-describedby="transition-modal-description"
              open={open}
              onClose={handleInvalidClose}
              closeAfterTransition
              slots={{ backdrop: Backdrop }}
              slotProps={{
                backdrop: {
                  timeout: 500,
                },
              }}
            >
              <Fade in={open}>
                <Box sx={style}>
                  <Typography id="transition-modal-description" sx={{ mt: 2 }}>
                      {message}
                  </Typography>
                </Box>
              </Fade>
            </Modal>
          </div>
      </>
    } 
    </>
  )
}
