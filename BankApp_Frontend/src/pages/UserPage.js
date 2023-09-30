import React, { useEffect, useState } from 'react';
import {Typography, Container, CssBaseline, Box, Card, CardContent, CardActions, Grid } from '@mui/material';

import { createTheme, ThemeProvider } from '@mui/material/styles';
import axios from 'axios';
import NavBar from './NavBar';

const defaultTheme = createTheme();
export default function UserPage() {
  const token = localStorage.getItem("token");

  const authToken = `Bearer ${token}`;
  const axiosInstance = axios.create({
    baseURL: "http://localhost:3000", // Replace with your API URL
    headers: {
      Authorization: authToken,
      "Content-Type": "application/json", // You can include other headers if needed
    },
  });
  const [customer_id, setCustomerId] = useState("");
  const [customer, setCustomer] = useState([]);

  async function getCustomer(customer_id, setCustomer) {
    const url = `http://localhost:3000/user/${customer_id}`;
    await axiosInstance.get(url).then((response) => {
      setCustomer(response.data);
    });
  }

  useEffect(() => {
    getCustomer(window.sessionStorage.getItem("customer_id"), setCustomer);
    console.log(customer);
  });

    return(<>
        
        <ThemeProvider theme={defaultTheme}>
        <NavBar/>
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
                    border: '0.5rem outset skyblue',
                    width: '25em'
                  }}
                 >
                   <CardContent sx={{margin: 'auto', width: '50%', display: 'flex', justifyContent: 'center'}}>       
                      <Typography component="h1" variant="h5" align='center' sx={{color: 'steelblue', fontSize: '20px', fontWeight: 'bold'}}>
                            CUSTOMER DETAILS
                      </Typography>
                    </CardContent>
                    <CardActions sx={{margin: 'auto', width: '75%', display: 'flex', justifyContent: 'center'}}>
                        <Grid container spacing={2}>
                            <Grid item xm={12} sx={{display:'flex'}}>
                                <Typography sx={{fontSize:'20px', color: 'firebrick', fontWeight:'bold'}}>NAME: </Typography>
                            
                                <Typography sx={{ml:'5px', fontSize:'20px', color: 'charcoal', fontWeight:'bold'}}>{customer.name}</Typography>
                            </Grid>
                            
                            <Grid item xm={12} sx={{display:'flex'}}>
                                <Typography sx={{fontSize:'20px', color: 'firebrick', fontWeight:'bold'}}>EMAIL: </Typography>

                                <Typography sx={{ml:'5px', fontSize:'20px', color: 'charcoal', fontWeight:'bold'}}>{customer.email}</Typography>
                            </Grid>

                            <Grid item xm={12} sx={{display:'flex'}}>
                                <Typography sx={{fontSize:'20px', color: 'firebrick', fontWeight:'bold'}}>PAN: </Typography>
                            
                                <Typography sx={{ml:'5px', fontSize:'20px', color: 'charcoal', fontWeight:'bold'}}>{customer.pan_number}</Typography>
                            </Grid>
                            
                            <Grid item xm={12} sx={{display:'flex'}}>
                                <Typography sx={{fontSize:'20px', color: 'firebrick', fontWeight:'bold'}}>Date Of Birth: </Typography>
                            
                                <Typography sx={{ml:'5px', fontSize:'20px', color: 'charcoal', fontWeight:'bold'}}>{customer.dob}</Typography>
                            </Grid>
                            
                            <Grid item xm={12} sx={{display:'flex'}}>
                                <Typography sx={{fontSize:'20px', color: 'firebrick', fontWeight:'bold'}}>FATHER'S NAME: </Typography>
                            
                                <Typography sx={{ml:'5px',fontSize:'20px', color: 'charcoal', fontWeight:'bold'}}>{customer.fathername}</Typography>
                            </Grid>

                            <Grid item xm={12} sx={{display:'flex'}}>
                                <Typography sx={{fontSize:'20px', color: 'firebrick', fontWeight:'bold'}}>MOTHER'S NAME: </Typography>
                            
                                <Typography sx={{ml:'5px', fontSize:'20px', color: 'charcoal', fontWeight:'bold'}}>{customer.mothername}</Typography>
                            </Grid>
                            
                            <Grid item xm={12} sx={{display:'flex'}}>
                                <Typography sx={{fontSize:'20px', color: 'firebrick', fontWeight:'bold'}}>ADDRESS: </Typography>
                            
                                <Typography sx={{ml:'5px', fontSize:'20px', color: 'charcoal', fontWeight:'bold'}}>{customer.address}</Typography>
                            </Grid>           
                        </Grid>
                      </CardActions>
                    </Card>
              </Box>
        </Container>            
        </ThemeProvider>
    
    </>)

}
