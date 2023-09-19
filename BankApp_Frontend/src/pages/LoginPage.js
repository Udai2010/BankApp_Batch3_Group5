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
import { passwordHashService } from '../services/PasswordHashService';

const defaultTheme = createTheme();

export default function LoginPage() {
  const baseURL = "http://localhost:3000/login";
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  
  const onUsernameChange = (event) => {
    setUsername(event.target.value);
  };

  const onPasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const onLogin = (event) => {
    event.preventDefault();
    const hashedPassword = passwordHashService(password)
    console.log(username)
    axios
      .post(baseURL, {
        userID: username,
        password: hashedPassword
      })
      .then((response) => {
        alert(response.data);
        window.sessionStorage.setItem("customer_id", username);
        window.location.assign("/dashboard");
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
                    label="Customer ID"
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
                    label="Password"
                    name="password"
                    type='password'
                    value={password}
                    onChange={onPasswordChange}
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
                Login
              </Button>
            </Box>
          </Box>
        </Container>
      </ThemeProvider>
    </>
  )
}
