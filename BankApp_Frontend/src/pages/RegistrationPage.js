import React, { useState } from 'react';
import axios from "axios";

import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { Grid } from '@mui/material';
import Backdrop from '@mui/material/Backdrop';
import Modal from '@mui/material/Modal';
import Fade from '@mui/material/Fade';
import { useNavigate } from 'react-router-dom';
import { passwordHashService } from '../services/PasswordHashService';
import { DatePicker, LocalizationProvider } from '@mui/x-date-pickers';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs/AdapterDayjs';
import dayjs from 'dayjs';
import { auto } from '@popperjs/core';

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


const defaultTheme = createTheme();


export default function RegistrationPage() {

  const baseUrl = "http://localhost:3000/auth/createCustomer"

  const [name, setname] = useState("")
  const [email, setemail] = useState("")
  const [pannumber, setpannumber] = useState("")
  const [dob, setdob] = useState(dayjs())
  const [password, setpassword] = useState("")
  const [fathername, setfathername] = useState("")
  const [mothername, setmothername] = useState("")
  const [address, setaddress] = useState("")

  const [success, setSuccess] = useState(false)

  const [message,setMessage] = useState("")

  const navigate = useNavigate()

  // Model Handler
  const [open, setOpen] = useState(true);
  const handleClose = () => {
    setOpen(false)
    navigate('/login')

  };

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
    const hasedPassword = passwordHashService(password);
    const birthdate = dob.year()+'-'+(dob.month()+1)+'-'+dob.date();
    console.log(birthdate);
    axios.post(baseUrl, {

      name: name,
      email: email,
      pan_number: pannumber,
      password: hasedPassword,
      dob: birthdate,
      fathername: fathername,
      mothername: mothername,
      address: address

    }).then((response) => {

      setMessage(response.data);
      setSuccess(true);
    }).catch((error) => {
      alert(error)
    })

  }

  return (
    <>
      {!success ?
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
                <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                </Avatar>
                <Typography component="h1" variant="h5">
                  Register Customer
                </Typography>
                <Box component="form" onSubmit={submitHandler} sx={{ mt: 1 }}>
                  <Grid container columnSpacing={1.5}>
                    <Grid item xs={6} >
                      <TextField
                        margin="normal"
                        required
                        fullWidth
                        id="name"
                        placeholder="Name"
                        name="name"
                        autoComplete="name"
                        onChange={nameChangeHandler}
                        autoFocus
                      />
                    </Grid>
                    <Grid item xs={6}>
                      <TextField
                        margin="normal"
                        required
                        fullWidth
                        id="email"
                        placeholder="Email Address"
                        name="email"
                        autoComplete="email"
                        onChange={emailChangeHandler}
                        autoFocus
                      />
                    </Grid>
                    <Grid item xs={6}>
                      <TextField
                        margin="normal"
                        required
                        fullWidth
                        id="fathername"
                        placeholder="Father Name"
                        name="fathername"
                        autoComplete="fathername"
                        onChange={fathernameChangeHandler}
                        autoFocus
                      />
                    </Grid>
                    <Grid item xs={6}>
                      <TextField
                        margin="normal"
                        required
                        fullWidth
                        id="mothername"
                        placeholder="Mother Name"
                        name="mothername"
                        autoComplete="mothername"
                        onChange={mothernameChangeHandler}
                        autoFocus
                      />
                    </Grid>
                    <Grid item xs={6}>
                      <TextField
                        margin="normal"
                        required
                        fullWidth
                        id="pannumber"
                        placeholder="Pan Number"
                        name="pannumber"
                        autoComplete="pannumber"
                        onChange={pannumberChangeHandler}
                        autoFocus
                      />
                    </Grid>
                    <LocalizationProvider dateAdapter={AdapterDayjs}>
                      <Grid item xm={6} marginTop={'15px'}>
                          <DatePicker  required fullWidth label="Date Of Birth" disableFuture
                              value={dob} onChange={(dob)=>setdob(dob)} />
                      </Grid>
                    </LocalizationProvider>

//                     <Grid item xs={6}>
//                       <TextField
//                         margin="normal"
//                         required
//                         fullWidth
//                         type='date'
//                         id="dob"
//                         name="dob"
//                         autoComplete="dob"
//                         onChange={dobChangeHandler}
//                         autoFocus
//                       />
//                     </Grid>

                    <Grid item xs={12}>
                      <TextField
                        margin="normal"
                        required
                        fullWidth
                        type="password"
                        id="password"
                        placeholder="Password"
                        name="password"
                        onChange={passwordChangeHandler}
                        autoFocus
                      />
                    </Grid>
                    <Grid item xs={12}>
                      <TextField
                        margin="normal"
                        required
                        fullWidth
                        id="address"
                        placeholder="Current Address"
                        name="address"
                        autoComplete="address"
                        onChange={addressChangeHandler}
                        autoFocus
                      />
                    </Grid>
                  </Grid>
                  <Button
                    type="submit"
                    fullWidth
                    variant="contained"
                    sx={{ mt: 3, mb: 2 }}
                  >
                    Register
                  </Button>
                </Box>
              </Box>
            </Container>
          </ThemeProvider>
        </>
        :
        <>
          <div>
            <Modal
              aria-placeholderledby="transition-modal-title"
              aria-describedby="transition-modal-description"
              open={open}
              onClose={handleClose}
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

                     Registration Successful. {message}

                  </Typography>
                </Box>
              </Fade>
            </Modal>
          </div>
        </>}
    </>
  )
}