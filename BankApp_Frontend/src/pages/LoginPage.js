import React, { useState, useEffect } from "react";
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
import { passwordHashService } from "../services/PasswordHashService";

const defaultTheme = createTheme();

export default function LoginPage() {
  const baseURL = "http://localhost:3000/auth/login";
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  // const navigate = useNavigate();
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
  };

  return (
    <>
      <ThemeProvider theme={defaultTheme}>
        <Container component="main" maxWidth="sm">
          <CssBaseline />
          <Box
            sx={{
              marginTop: 8,
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
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
                    label="Customer ID"
                    name="username"
                    value={username}
                    // autoComplete="name"
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
                    type="password"
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
            {/* 
            <div style={{ display: 'block', textAlign: 'center' }}>
              <form onSubmit={onLogin} >
                <h2>LoginPage</h2>
                <label>Username</label>
                <input type="text" value={username} required onChange={onUsernameChange} /><br />
                <label>Password</label>
                <input type="password" value={password} required onChange={onPasswordChange} /><br />
                <button type="submit">Login</button>
              </form>
            </div> */}
          </Box>
        </Container>
      </ThemeProvider>
    </>
  );
}
