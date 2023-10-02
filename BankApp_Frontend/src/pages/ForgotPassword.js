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
import { Grid, IconButton, InputAdornment } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { passwordHashService } from "../services/PasswordHashService";
import HomeNavbar from "./HomeNavbar";
import { brown } from "@mui/material/colors";
import Snackbar from "@mui/material/Snackbar";
import MuiAlert from "@mui/material/Alert"
import { Visibility, VisibilityOff } from "@mui/icons-material";

const defaultTheme = createTheme(
  {
    palette: {
      primary: brown
    }
  }
);

export default function ForgotPassowrd() {
  const token = localStorage.getItem("token");

  const authToken = `Bearer ${token}`;
  const axiosInstance = axios.create({
    baseURL: "http://localhost:3000", // Replace with your API URL
    headers: {
      Authorization: authToken,
      "Content-Type": "application/json", // You can include other headers if needed
    },
  });
  const baseURL = "http://localhost:3000/forgotpassword";
  const [customerId, setCustomerId] = useState("");
  const [otp, setOtp] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

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
    window.location.assign("/login");
  }


  const onCustomerIdChange = (event) => {
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
    axiosInstance
      .put(baseURL, {
        username: customerId,
        password: passwordHashService(password),
        otp: otp,
      })
      .then((response) => {
        setAlertMessage(response.data);
        setOpenSuccessSnackbar(true);
      })
      .catch((err) => {
        setAlertMessage(err.response.data);
        setOpenErrorSnackbar(true);
      });
  };

  return (
    <>
      <ThemeProvider theme={defaultTheme}>
        <HomeNavbar />
        <Container component="main" maxWidth="sm">
          <CssBaseline />
          <Box
            sx={{
              marginTop: 5,
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
              border: '0.5rem outset #827717'
            }}
          >
            <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}></Avatar>
            <Typography component="h1" variant="h5" sx={{ color: '#616161' }}>
              FORGOT PASSWORD
            </Typography>
            <Box
              component="form"
              onSubmit={onForgotPassword}
              sx={{ mt: 1 }}
            >
              <Grid containter>
                <Grid item xm={12}>
                  <TextField
                    margin="normal"
                    required
                    fullWidth
                    id="customerId"
                    label="Customer ID"
                    name="customerId"
                    type="text"
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
                    label="Password"
                    name="password"
                    type={showPassword ? "password" : "text"}
                    value={password}
                    onChange={onPasswordChange}
                    InputProps={{
                      endAdornment: (
                        <InputAdornment position="end">
                          <IconButton
                            onClick={handleTogglePassword}
                            edge="end">
                            {showPassword ? <Visibility /> : <VisibilityOff />}
                          </IconButton>
                        </InputAdornment>
                      )
                    }}
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
                    type="text"
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
