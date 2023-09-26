import React, { useState, useEffect } from 'react';
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
import { useNavigate } from 'react-router-dom';
import { passwordHashService } from '../services/PasswordHashService';

const defaultTheme = createTheme();

export default function ForgotPassowrd() {
  const baseURL = "http://localhost:3000/forgotpassword";
  const [customerId, setCustomerId] = useState("");
  const [otp, setOtp] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const onCustomerIdChange=(event)=> {
        setCustomerId(event.target.value);
    };

  const onPasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const onOtpChange = (event) => {
        setOtp(event.target.value);
  };

  
  const onForgotPassword = (event) => {
    
    event.preventDefault();
    axios
      .put(baseURL, {
        username: customerId,
        password: passwordHashService(password),
        otp:otp
      })
      .then((response) => {
        alert(response.data);
        window.location.assign("/login");
      })
      .catch((err) => {
        alert("error- " + err)
      });
  };


  return (
    <>
      <ThemeProvider theme={defaultTheme}>
        <Container component="main" maxWidth="sm">
          <CssBaseline />
          <Box
            sx={{
              marginTop: 8,
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
            }}
          >
            <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
            </Avatar>
            <Typography component="h1" variant="h5">
              Forgot Password
            </Typography>
            <Box component="form" onSubmit={onForgotPassword} noValidate sx={{ mt: 1 }}>
              <Grid containter>
              <Grid item xm={12}>
                  <TextField
                    margin="normal"
                    required
                    fullWidth
                    id="customerId"
                    label="Customer ID"
                    name="customerId"
                    type='text'
                    value={customerId}
                    onChange={onCustomerIdChange}
                    autoFocus
                  />
                </Grid>

                <Grid item xm={12}>
                  <TextField
                    margin="normal"
                    required
                    fullWidth
                    id="password"
                    label="New Password"
                    name="password"
                    type='password'
                    value={password}
                    onChange={onPasswordChange}
                    autoFocus
                  />
                </Grid>

                <Grid item xm={12}>
                  <TextField
                    margin="normal"
                    required
                    fullWidth
                    id="otp"
                    label="OTP"
                    name="otp"
                    type='text'
                    value={otp}
                    onChange={onOtpChange}
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
                Change Password
              </Button>
            </Box>
          </Box>
        </Container>
      </ThemeProvider>
    </>
  )
}
