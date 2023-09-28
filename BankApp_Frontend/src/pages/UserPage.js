import React, { useEffect, useState } from 'react';
import { Paper, Typography } from '@mui/material';
import { Link } from 'react-router-dom';
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
            <center>
            <Paper>
                <Typography variant='h4'>Customer details</Typography>
                <Typography variant='h6'>Name: {customer.name}</Typography>
                <Typography variant='h6'>Email: {customer.email}</Typography>
                <Typography variant='h6'>PAN: {customer.pan_number}</Typography>
                <Typography variant='h6'>Date of birth: {customer.dob}</Typography>
                <Typography variant='h6'>Father name: {customer.fathername}</Typography>
                <Typography variant='h6'>Mother name: {customer.mothername}</Typography>
                <Typography variant='h6'>Address: {customer.address}</Typography>
            </Paper>
            </center>
        </ThemeProvider>
    
    </>)

}
