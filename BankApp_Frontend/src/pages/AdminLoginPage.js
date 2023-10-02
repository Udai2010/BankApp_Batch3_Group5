import React, { useState } from "react";
import axios from "axios";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { Grid, IconButton, InputAdornment } from "@mui/material";
import { useNavigate } from "react-router-dom";
import HomeNavbar from "./HomeNavbar";
import { brown } from "@mui/material/colors";
import Snackbar from "@mui/material/Snackbar";
import MuiAlert from "@mui/material/Alert"
import { Visibility, VisibilityOff } from "@mui/icons-material";

const defaultTheme = createTheme(
  {palette:{
      primary: brown
  }}
);

export default function AdminLoginPage() {
  const baseURL = "http://localhost:3000/auth/adminlogin";
  const [admin_id, setAdmin_Id] = useState("");
  const [password, setPassword] = useState("");

  const [showPassword, setShowPassword] = useState(true);
  const [openErrorSnackbar, setOpenErrorSnackbar] = useState(false);
  const [openSuccessSnackbar, setOpenSuccessSnackbar] = useState(false);
  const [alertMessage, setAlertMessage] = useState("");

  const handleTogglePassword = () => {
    setShowPassword(!showPassword);
  }

  const handleCloseErrorSnackbar = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }
    setOpenErrorSnackbar(false);
  }

  const handleCloseSuccessSnackbar = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }
    setOpenSuccessSnackbar(false);
    window.location.assign("/admindashboard");
  }


  const onAdminIdChange = (event) => {
    setAdmin_Id(event.target.value);
  };

  const onPasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const onAdminLogin = async (event) => {
    event.preventDefault();
    await axios
      .post(baseURL, {
        admin_id: admin_id,
        password: password,
      })
      .then((response) => {
        console.log(response);
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

        window.sessionStorage.setItem("admin_id", admin_id);
        window.sessionStorage.removeItem("customer_id");
        setAlertMessage("Valid Admin Login")
        setOpenSuccessSnackbar(true);
      })
      .catch((err) => {
        console.log(err);
        setAlertMessage(err.response.data);
        setOpenErrorSnackbar(true);
      });
  };

  return (
    <>
      <ThemeProvider theme={defaultTheme}>
        <HomeNavbar/>
        
        <Container component="main" maxWidth="sm">
          <CssBaseline />
          <Box
            sx={{
              
              marginTop: 5,
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
              border: '0.5rem outset #827717'
            }}
          >
            <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}></Avatar>
            <Typography component="h1" variant="h5" sx={{color: '#616161'}}>
              ADMIN LOGIN
            </Typography>
            <Box
              component="form"
              onSubmit={onAdminLogin}
              sx={{ mt: 1 }}
            >
                <Grid containter>
                  <Grid item xm={12}>
                    <TextField
                      margin="normal"
                      required
                      fullWidth
                      id="admin_id"
                      label="Admin ID"
                      name="admin_id"
                      value={admin_id}
                      onChange={onAdminIdChange}
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
                      type={showPassword?"password":"text"}
                      value={password}
                      onChange={onPasswordChange}
                      InputProps={{
                        endAdornment: (
                          <InputAdornment position="end">
                          <IconButton
                            onClick={handleTogglePassword}
                            edge="end">
                                {showPassword? <Visibility/>:<VisibilityOff/>}
                          </IconButton>
                          </InputAdornment>
                        )
                      }}
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
      <Snackbar
        open={openErrorSnackbar}
        autoHideDuration={6000}
        onClose={handleCloseErrorSnackbar}
        anchorOrigin={{ vertical: 'top', horizontal: 'center' }}
      >
        <MuiAlert
          elevation={6}
          variant="filled"
          severity="error"
          onClose={handleCloseErrorSnackbar}
        >
          {alertMessage}
        </MuiAlert>
      </Snackbar>
      <Snackbar
        open={openSuccessSnackbar}
        autoHideDuration={6000}
        onClose={handleCloseSuccessSnackbar}
        anchorOrigin={{ vertical: 'top', horizontal: 'center' }}
      >
        <MuiAlert
          elevation={6}
          variant="filled"
          severity="success"
          onClose={handleCloseSuccessSnackbar}
        >
          {alertMessage}
        </MuiAlert>
      </Snackbar>
    </>
  );
}
