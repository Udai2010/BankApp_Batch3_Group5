import React, { useEffect, useState } from 'react';
import {Typography, Grid, Box, Card, CardContent, CardActions, Container, CssBaseline, TextField } from '@mui/material';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import axios from 'axios';
import { purple } from "@mui/material/colors";
import NavBar from './NavBar';

const defaultTheme = createTheme(
  {palette:{
      primary: purple
  }}
);

export default function AdminSearchuser() {
  const token = localStorage.getItem("token");

  const [selectedCustomer, setSelectedCustomer] = useState(-1);
  const [customer, setCustomer] = useState({});

  async function getCustomer() {
    const authToken = `Bearer ${token}`;
    const axiosInstance = axios.create({
      baseURL: "http://localhost:3000", // Replace with your API URL
      headers: {
        Authorization: authToken,
        "Content-Type": "application/json", // You can include other headers if needed
      },
    });
    const url = `http://localhost:3000/searchUser/${selectedCustomer}`;
    await axiosInstance.get(url).then((response) => {
      console.log(response);
      setCustomer(response.data);
    });
  }

  const handleChange = (event) => {
    console.log(event.target.value);
    setSelectedCustomer(event.target.value);
  };
  const handleKeyPress = (event) => {
    if (event.key == "Enter") {
      handleSearch();
    }
  };

  const handleSearch = () => {
    getCustomer(selectedCustomer);
  };

  return (
    <>
      <ThemeProvider theme={defaultTheme}>
        <NavBar userType="admin"/>
          <Typography component="h1" variant="h5" align='center' sx={{color: 'mediumorchid', fontSize: '20px', fontWeight: 'bold'}}>
                    SELECT CUSTOMER TO VIEW
          </Typography> 
            <Box sx={{ flexGrow: 2 }}>
                <Grid container spacing={2} sx={{margin: 'auto', width: '75%', display: 'flex', justifyContent: 'center'}}>
                    <Grid item xm={6}>        
                    <Typography component="h4" variant="h5" align='center' sx={{mt:'5%', color: 'dimgrey', fontSize: '20px', fontWeight: 'bold'}}>
                                Select Customer To View
                    </Typography>
                    </Grid>
                    <Grid item xm={6}> 
                    <TextField 
                      id="selectedCustomer"
                      // value={selectedCustomer}
                      label="Customer id"
                      onChange={handleChange}
                      onKeyPress={handleKeyPress}
                      />
                    </Grid>
                </Grid>
            </Box>
          <Container component="main" maxWidth="sm">
          <CssBaseline />
          <Box
            sx={{
              marginTop: 5,
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
            }}
          >
            <Card
                  sx={{
                    border: '0.5rem outset plum',
                    width: '25em'
                  }}
                 >
                   <CardContent sx={{margin: 'auto', width: '50%', display: 'flex', justifyContent: 'center'}}>       
                      <Typography component="h1" variant="h5" align='center' sx={{color: 'mediumorchid', fontSize: '20px', fontWeight: 'bold'}}>
                            CUSTOMER DETAILS
                      </Typography>
                    </CardContent>
                    <CardActions sx={{margin: 'auto', width: '75%', display: 'flex', justifyContent: 'center'}}>
                        <Grid container spacing={2}>
                            <Grid item xm={12} sx={{display:'flex'}}>
                                <Typography sx={{fontSize:'20px', color: 'darkolivegreen', fontWeight:'bold'}}>NAME: </Typography>
                            
                                <Typography sx={{ml:'5px', fontSize:'20px', color: 'charcoal', fontWeight:'bold'}}>{customer.name}</Typography>
                            </Grid>
                            
                            <Grid item xm={12} sx={{display:'flex'}}>
                                <Typography sx={{fontSize:'20px', color: 'darkolivegreen', fontWeight:'bold'}}>EMAIL: </Typography>

                                <Typography sx={{ml:'5px', fontSize:'20px', color: 'charcoal', fontWeight:'bold'}}>{customer.email}</Typography>
                            </Grid>

                            <Grid item xm={12} sx={{display:'flex'}}>
                                <Typography sx={{fontSize:'20px', color: 'darkolivegreen', fontWeight:'bold'}}>PAN: </Typography>
                            
                                <Typography sx={{ml:'5px', fontSize:'20px', color: 'charcoal', fontWeight:'bold'}}>{customer.pan_number}</Typography>
                            </Grid>
                            
                            <Grid item xm={12} sx={{display:'flex'}}>
                                <Typography sx={{fontSize:'20px', color: 'darkolivegreen', fontWeight:'bold'}}>Date Of Birth: </Typography>
                            
                                <Typography sx={{ml:'5px', fontSize:'20px', color: 'charcoal', fontWeight:'bold'}}>{customer.dob}</Typography>
                            </Grid>
                            
                            <Grid item xm={12} sx={{display:'flex'}}>
                                <Typography sx={{fontSize:'20px', color: 'darkolivegreen', fontWeight:'bold'}}>FATHER'S NAME: </Typography>
                            
                                <Typography sx={{ml:'5px',fontSize:'20px', color: 'charcoal', fontWeight:'bold'}}>{customer.fathername}</Typography>
                            </Grid>

                            <Grid item xm={12} sx={{display:'flex'}}>
                                <Typography sx={{fontSize:'20px', color: 'darkolivegreen', fontWeight:'bold'}}>MOTHER'S NAME: </Typography>
                            
                                <Typography sx={{ml:'5px', fontSize:'20px', color: 'charcoal', fontWeight:'bold'}}>{customer.mothername}</Typography>
                            </Grid>
                            
                            <Grid item xm={12} sx={{display:'flex'}}>
                                <Typography sx={{fontSize:'20px', color: 'darkolivegreen', fontWeight:'bold'}}>ADDRESS: </Typography>
                            
                                <Typography sx={{ml:'5px', fontSize:'20px', color: 'charcoal', fontWeight:'bold'}}>{customer.address}</Typography>
                            </Grid>           
                        </Grid>
                      </CardActions>
                    </Card>
              </Box>
        </Container>    
        </ThemeProvider>
    </>
  );
}
