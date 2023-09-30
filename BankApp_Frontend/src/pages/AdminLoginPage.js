import React,{useState} from 'react';
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

const defaultTheme = createTheme();


export default function AdminLoginPage() {
    const baseURL = "http://localhost:3000/adminLogin";
    const [admin_id, setAdmin_Id] = useState("");
    const [password, setPassword] = useState("");

    const onAdminIdChange = (event) => {
      setAdmin_Id(event.target.value);
    };
  
    const onPasswordChange = (event) => {
      setPassword(event.target.value);
    };
  
    const onAdminLogin = (event) => {
      event.preventDefault();
      axios
        .post(baseURL, {
          admin_id: admin_id,
          password: password
        })
        .then((response) => {
          alert(response.data);
          window.sessionStorage.setItem("admin_id", admin_id);
          window.sessionStorage.removeItem("customer_id");
          window.location.assign("/admindashboard");
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
                Admin Login
              </Typography>
              <Box component="form" onSubmit={onAdminLogin} sx={{ mt: 1 }}>
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
                      type='password'
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
              </Box>
            </Box>
          </Container>
        </ThemeProvider>
      </>
    )
  }