import React, { useEffect, useState } from 'react';
import { Box, FormControl, Grid, Typography, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField } from '@mui/material';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import axios from 'axios';
import { purple } from "@mui/material/colors";
import NavBar from './NavBar';
import Snackbar from "@mui/material/Snackbar";
import MuiAlert from "@mui/material/Alert";

const defaultTheme = createTheme(
  {
    palette: {
      primary: purple
    }
  }
);

export default function AdminViewTransactions() {
  const token = localStorage.getItem("token");

  const authToken = `Bearer ${token}`;
  const axiosInstance = axios.create({
    baseURL: "http://localhost:3000", // Replace with your API URL
    headers: {
      Authorization: authToken,
      "Content-Type": "application/json", // You can include other headers if needed
    },
  });

  const [customerId, setCustomerId] = useState("");
  const [transactions, setTransactions] = useState([]);
  const [selectedCustomer, setSelectedCustomer] = useState(-1);

  const [openErrorSnackbar, setOpenErrorSnackbar] = useState(false);
  const [alertMessage, setAlertMessage] = useState("");

  const handleCloseErrorSnackbar = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }
    setOpenErrorSnackbar(false);
  }


  async function getTransactions(customer_id, setTransactions) {
    const url = `http://localhost:3000/admingettransactions/${customer_id}`;
    await axiosInstance.get(url).then((response) => {
      console.log(response);

      setTransactions(response.data);
    }).catch((err) => {
      setAlertMessage(err);
      setOpenErrorSnackbar(true);
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

  //   useEffect(() => {
  //     if(selectedCustomer !== -1) getTransactions(selectedCustomer, setTransactions);
  //   }, [selectedCustomer]);
  const handleSearch = () => {
    getTransactions(selectedCustomer, setTransactions);

  }

  return (
    <>
      <ThemeProvider theme={defaultTheme}>
        <NavBar userType="admin" />

        <Typography component="h1" variant="h5" align='center' sx={{ color: 'mediumorchid', fontSize: '20px', fontWeight: 'bold' }}>
          VIEW TRANSACTION HISTORY
        </Typography>

        <Box sx={{ flexGrow: 2 }}>
          <Grid container spacing={2} sx={{ margin: 'auto', width: '75%', display: 'flex', justifyContent: 'center' }}>
            <Grid item xm={6}>
              <Typography component="h4" variant="h5" align='center' sx={{ mt: '5%', color: 'dimgrey', fontSize: '20px', fontWeight: 'bold' }}>
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

        {transactions.length > 0 ?
          <TableContainer >
            <Table sx={{ border: '0.5rem outset plum', marginTop: '2%' }}>
              <TableHead sx={{ border: '0.5rem outset plum' }}>
                <TableRow sx={{ border: '0.5rem outset plum' }}>
                  <TableCell align="center" sx={{ border: '0.5rem outset plum', color: 'darkolivegreen', fontWeight: 'bold' }}>Transaction Id</TableCell>
                  <TableCell align="center" sx={{ border: '0.5rem outset plum', color: 'darkolivegreen', fontWeight: 'bold' }}>Sender</TableCell>
                  <TableCell align="center" sx={{ border: '0.5rem outset plum', color: 'darkolivegreen', fontWeight: 'bold' }}>Receiver</TableCell>
                  <TableCell align="center" sx={{ border: '0.5rem outset plum', color: 'darkolivegreen', fontWeight: 'bold' }}>Amount</TableCell>
                  <TableCell align="center" sx={{ border: '0.5rem outset plum', color: 'darkolivegreen', fontWeight: 'bold' }}>Transaction type</TableCell>
                  <TableCell align="center" sx={{ border: '0.5rem outset plum', color: 'darkolivegreen', fontWeight: 'bold' }}>Date</TableCell>
                  <TableCell align="center" sx={{ border: '0.5rem outset plum', color: 'darkolivegreen', fontWeight: 'bold' }}>Status</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {transactions.map((row) => {
                  return (<TableRow key={row.transaction_id} sx={{ border: '0.5rem outset plum' }}>
                    <TableCell align='center' sx={{ border: '0.5rem outset plum', color: 'charcoal', fontWeight: 'bold' }}>{row.transaction_id}</TableCell>
                    <TableCell align='center' sx={{ border: '0.5rem outset plum', color: 'charcoal', fontWeight: 'bold' }}>{row.sender_account.account_id}</TableCell>
                    <TableCell align='center' sx={{ border: '0.5rem outset plum', color: 'charcoal', fontWeight: 'bold' }}>{row.receiver_account?.account_id != null ? row.receiver_account.account_id : '-'}</TableCell>
                    <TableCell align='center' sx={{ border: '0.5rem outset plum', color: 'charcoal', fontWeight: 'bold' }}>{row.amount}</TableCell>
                    <TableCell align='center' sx={{ border: '0.5rem outset plum', color: 'charcoal', fontWeight: 'bold' }}>{row.transactionType}</TableCell>
                    <TableCell align='center' sx={{ border: '0.5rem outset plum', color: 'charcoal', fontWeight: 'bold' }}>{row.transactionDate}</TableCell>
                    <TableCell align='center' sx={{ border: '0.5rem outset plum', color: 'charcoal', fontWeight: 'bold' }}>{row.status}</TableCell>
                  </TableRow>)
                })}
              </TableBody>
            </Table>
          </TableContainer> : <Typography component="h3" variant="h5" align='center' sx={{ color: 'charcoal', fontSize: '20px', fontWeight: 'bold' }}>
            NO TRANSACTIONS FOR THIS ACCOUNT
          </Typography>
        }
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
    </>
  )
}
