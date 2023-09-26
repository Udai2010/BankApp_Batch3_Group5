import React, { useEffect, useState } from "react";
import {
  Typography,
  Paper,
  FormControl,
  Button,
  MenuItem,
  Select,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  TextField,
} from "@mui/material";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import axios from "axios";

const defaultTheme = createTheme();

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
      <div>
        <h3>Search Customer</h3>
        <h4>Enter Customer ID</h4>
        <FormControl>
          <TextField
            id="selectedCustomer"
            // value={selectedCustomer}
            label="Customer id"
            onChange={handleChange}
            onKeyPress={handleKeyPress}
          />
        </FormControl>
      </div>

      <ThemeProvider theme={defaultTheme}>
        <Paper>
          <Typography variant="h4">Customer details</Typography>
          <Typography variant="h6">Name: {customer.name}</Typography>
          <Typography variant="h6">Email: {customer.email}</Typography>
          <Typography variant="h6">PAN: {customer.pan_number}</Typography>
          <Typography variant="h6">Date of birth: {customer.dob}</Typography>
          <Typography variant="h6">
            Father name: {customer.fathername}
          </Typography>
          <Typography variant="h6">
            Mother name: {customer.mothername}
          </Typography>
          <Typography variant="h6">Address: {customer.address}</Typography>
        </Paper>
      </ThemeProvider>
    </>
  );
}
