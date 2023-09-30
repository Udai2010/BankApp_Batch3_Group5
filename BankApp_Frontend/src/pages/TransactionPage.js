
import React, { useEffect, useState } from 'react';
import { Box, Grid, Typography, FormControl, TextField, MenuItem, Select, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import axios from 'axios';
import NavBar from './NavBar';

const defaultTheme = createTheme();
export default function TransactionPage() {
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
  const [accounts, setAccounts] = useState([]);
  const [transactions, setTransactions] = useState([]);
  const [selectedAccount, setSelectedAccont] = useState(-1);

  async function getAccounts(customer_id, setAccounts) {
    const url = `http://localhost:3000/account/${customer_id}`;
    await axiosInstance.get(url).then((response) => {
      setAccounts(response.data);
      console.log(accounts);
    });
  }

  async function getTransactions(account_id, setTransactions) {
    const url = `http://localhost:3000/transactions/${account_id}`;
    await axiosInstance.get(url).then((response) => {
      console.log(response);
      setTransactions(response.data);
    });
  }

  const handleChange = (event) => {
    setSelectedAccont(event.target.value);
  };

  useEffect(() => {
    getAccounts(window.sessionStorage.getItem("customer_id"), setAccounts);
  }, [customerId]);
  useEffect(() => {
    if (accounts.length > 0) setSelectedAccont(accounts[0].account_id);
  }, [accounts]);
  useEffect(() => {
    if(selectedAccount !== -1) getTransactions(selectedAccount, setTransactions);
  }, [selectedAccount]); 
  
  return (
    <>
      <ThemeProvider theme={defaultTheme}>
      <NavBar/>

      <Typography component="h1" variant="h5" align='center' sx={{color: 'darkblue', fontSize: '20px', fontWeight: 'bold'}}>
                        VIEW TRANSACTION HISTORY
                </Typography> 

      {accounts.length > 0 ?<div>

        <Box sx={{ flexGrow: 2 }}>
        <Grid container spacing={2} sx={{margin: 'auto', width: '75%', display: 'flex', justifyContent: 'center'}}>
            <Grid item xm={6}>        
            <Typography component="h4" variant="h5" align='center' sx={{mt:'5%', color: 'darkblue', fontSize: '20px', fontWeight: 'bold'}}>
                        Select Account To View
                </Typography>
              </Grid>
            <Grid item xm={6}> 
            <TextField value={selectedAccount}
                            label="Account No."
                            align="center"
                            style={{minWidth:'15em'}}
                            select
                            onChange={handleChange}>
                            {accounts.map((acc) => {
                                return <MenuItem value={acc.account_id}>{acc.account_id}</MenuItem>
                            })}
                        </TextField>
                        </Grid>
              </Grid>
        </Box>
        </div>: <Typography component="h3" variant="h5" align='center' sx={{color: 'charcoal', fontSize: '20px', fontWeight: 'bold'}}>
                        NO ACCOUNTS CREATED
                </Typography>
      }

      
      {transactions.length>0?
        <TableContainer >
        <Table sx={{border: '0.5rem outset skyblue', marginTop:'2%'}}>
            <TableHead sx={{border: '0.5rem outset skyblue'}}>
                <TableRow sx={{border: '0.5rem outset skyblue'}}>
                    <TableCell align="center" sx={{border: '0.5rem outset skyblue', color: 'firebrick', fontWeight:'bold'}}>Transaction Id</TableCell>
                    <TableCell align="center" sx={{border: '0.5rem outset skyblue', color: 'firebrick', fontWeight:'bold'}}>Sender</TableCell>
                    <TableCell align="center" sx={{border: '0.5rem outset skyblue', color: 'firebrick', fontWeight:'bold'}}>Receiver</TableCell>
                    <TableCell align="center" sx={{border: '0.5rem outset skyblue', color: 'firebrick', fontWeight:'bold'}}>Amount</TableCell>
                    <TableCell align="center" sx={{border: '0.5rem outset skyblue', color: 'firebrick', fontWeight:'bold'}}>Transaction type</TableCell>
                    <TableCell align="center" sx={{border: '0.5rem outset skyblue', color: 'firebrick', fontWeight:'bold'}}>Date</TableCell>
                    <TableCell align="center" sx={{border: '0.5rem outset skyblue', color: 'firebrick', fontWeight:'bold'}}>Status</TableCell>
                </TableRow>
            </TableHead>
            <TableBody>
                {transactions.map((row) => {
                    return (<TableRow key={row.transaction_id} sx={{border: '0.5rem outset skyblue'}}>
                        <TableCell align='center' sx={{border: '0.5rem outset skyblue', color: 'charcoal', fontWeight:'bold'}}>{row.transaction_id}</TableCell>
                        <TableCell align='center' sx={{border: '0.5rem outset skyblue', color: 'charcoal', fontWeight:'bold'}}>{row.sender_account.account_id}</TableCell>
                        <TableCell align='center' sx={{border: '0.5rem outset skyblue', color: 'charcoal', fontWeight:'bold'}}>{row.receiver_account?.account_id != null ? row.receiver_account.account_id : '-'}</TableCell>
                        <TableCell align='center' sx={{border: '0.5rem outset skyblue', color: 'charcoal', fontWeight:'bold'}}>{row.amount}</TableCell>
                        <TableCell align='center' sx={{border: '0.5rem outset skyblue', color: 'charcoal', fontWeight:'bold'}}>{row.transactionType}</TableCell>
                        <TableCell align='center' sx={{border: '0.5rem outset skyblue', color: 'charcoal', fontWeight:'bold'}}>{row.transactionDate}</TableCell>
                        <TableCell align='center' sx={{border: '0.5rem outset skyblue', color: 'charcoal', fontWeight:'bold'}}>{row.status}</TableCell>
                    </TableRow>)
                })}
            </TableBody>

        </Table>

    </TableContainer>: <Typography component="h3" variant="h5" align='center' sx={{color: 'charcoal', fontSize: '20px', fontWeight: 'bold'}}>
                        NO TRANSACTIONS FOR THIS DATE RANGE
                </Typography>
      }

  </ThemeProvider>
  </>
  )

}
