import React from "react";
import { Link } from "react-router-dom";
import { Button, Grid, Typography, CssBaseline, Box, Card, CardActions, CardContent, Container } from "@mui/material";
import { createTheme, ThemeProvider } from '@mui/material/styles';
import HomeNavbar from "./HomeNavbar";
import { brown } from "@mui/material/colors";

const defaultTheme = createTheme(
  {palette:{
      primary: brown
  }}
);

export default function HomePage() {
  return (
    <>
    <ThemeProvider theme={defaultTheme}>
    <HomeNavbar/>
      <Container component="main" maxWidth="sm">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 10,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}
        >
          <Card
                sx={{
                  border: '0.5rem outset #827717',
                  width: '25em'
                }}
               >
                 <CardContent sx={{margin: 'auto', width: '50%', display: 'flex', justifyContent: 'center'}}>
                    
                    <Typography component="h1" variant="h5" align='center' sx={{color: '#616161', fontSize: '20px', fontWeight: 'bold'}}>
                        WELCOME TO LIBERTY BANK
                    </Typography>
                  </CardContent>
                  <CardActions sx={{margin: 'auto', height: '30vh', display: 'flex', justifyContent: 'center'}}>
                      <Box sx={{ mt: 1 }}>
                      <Grid container  sx={{ display: 'flex', flexDirection: 'column'}}>
                          <Grid item xm={4}>
                          <Button
                          fullWidth
                            type="submit"
                            variant="contained"
                            component={Link}
                            to="/register"
                            sx={{mt:1, mb:3}}
                          >
                            Register
                          </Button>
                          </Grid>
                          <Grid item xm={4}>
                          <Button
                          fullWidth
                            type="submit"
                            variant="contained"
                            component={Link}
                            to="/login"
                            sx={{mt:1, mb:3}}
                          >
                            Login
                          </Button>
                          </Grid>
                          <Grid item xm={4}>
                          <Button
                          fullWidth
                            type="submit"
                            variant="contained"
                            component={Link}
                            to="/adminlogin"
                            sx={{mt:1, mb:3 }}
                          >
                            Admin Login
                          </Button>
                          </Grid>
                        </Grid>
                      </Box>
                    </CardActions>
                  </Card>
            </Box>
      </Container>
    </ThemeProvider>
  </>
  );
}
