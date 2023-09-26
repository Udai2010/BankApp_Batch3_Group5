import React, { useState,useEffect } from 'react';
import axios from "axios";
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import {FormControl, InputLabel, Select, MenuItem, Card, CardContent, CardActions } from '@mui/material';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { Grid } from '@mui/material';
import NavBar from './NavBar';
const defaultTheme = createTheme();


export default function WithdrawPage() {

    const [customerId, setCustomerId] = useState("");
    const [accounts, setAccounts] = useState([]);
    const [amount,setAmount]=useState("");
    const [selectedAccount, setSelectedAccont] = useState(-1);
    const [balance, setBalance] = useState(0);

    async function getAccounts(customer_id, setAccounts) {
      const url = `http://localhost:3000/account/${customer_id}`
      await axios.get(url).then((response) => {
          setAccounts(response.data);
          console.log(accounts);
      });
      
  }

  async function getBalance(account_id, setBalance) {
    const url = `http://localhost:3000/balance/${account_id}`;
    await axios.get(url).then((response) => {
      setBalance(response.data);
      console.log(balance);
    });
  }
    
    const onAmountChange = (event) => {
      if(event.target.value<0){
        alert("Negative amount not allowed");
        //document.getElementById('amount').value = '';
        return;
    }
        setAmount(event.target.value);
    }

    const onSelectAccount = (event) => {
      setSelectedAccont(event.target.value);
      getBalance(selectedAccount, setBalance);
    }

    useEffect(() => {
      getAccounts(window.sessionStorage.getItem("customer_id"), setAccounts);
    }, [customerId]);
    useEffect(() => {
      if(accounts.length > 0) {setSelectedAccont(accounts[0].account_id); }
    }, [accounts]);
    useEffect(()=>{
      if(selectedAccount!==-1) {getBalance(selectedAccount,setBalance)}
    },[selectedAccount]);
    const onWithdraw=(event) => {
        event.preventDefault();
        const url='http://localhost:3000/withdraw';
        axios
          .put(url, {
            account_id: selectedAccount,
            amount: amount
          })
          .then((response) => {
            alert(response.data);
            //console.log(response);
            //navigate("/dashboard");
          })
          .catch((err) => {
            alert(err.response.data.errors)
          }); 
      };

    return(
        <>


        <ThemeProvider theme={defaultTheme}>
        <NavBar/>

        <Container component="main" maxWidth="sm">
          <CssBaseline />
          <Box
           
            sx={{
              margin: 10,
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
                          WITHDRAWAL
                      </Typography>
                   </CardContent>
                   <CardActions sx={{margin: 'auto', width: '50%', display: 'flex', justifyContent: 'center'}}>
                      <Box component="form" onSubmit={onWithdraw} noValidate sx={{ mt: 1 }}>
                        {accounts.length > 0 ?<div>
                          <FormControl fullWidth>
                            <InputLabel id="selectAccount">Account number</InputLabel>
                            <Select 
                              fullWidth
                              id="selectAccount"
                              value={selectedAccount}
                              label="Account number"
                              onChange={onSelectAccount}
                              >
                                {accounts.map((acc) => {
                                  return <MenuItem value={acc.account_id}>{acc.account_id}</MenuItem>
                                })}
                              </Select>
                          </FormControl>
                          </div>: <p>No accounts</p>
                        }
                            <Grid item xm={12}>
                              <TextField
                                margin="normal"
                                required
                                fullWidth
                                id="amount"
                                label="Amount"
                                name="amount"
                                type="text"
                                value={amount}
                                onChange={onAmountChange}
                                autoFocus
                              />
                            </Grid>
                            <Typography variant='h6' sx={{color: 'steelblue', fontSize: '16px', fontWeight: 'bold'}}>
                              BALANCE: ₹{balance}
                            </Typography>
                          <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{ mt: 3, mb: 2 }}
                          >
                            Withdrawal
                          </Button>
                        </Box>
                   </CardActions>
                  </Card>
                  
            </Box>
        </Container>
      </ThemeProvider>
        </>
    )
}