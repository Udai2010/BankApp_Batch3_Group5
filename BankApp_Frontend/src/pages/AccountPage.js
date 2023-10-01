import React, { useEffect, useState } from 'react';
import { Button, Paper, TextField} from '@mui/material';
import { Link } from 'react-router-dom';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { Box, Typography, Grid, FormControl, InputLabel, MenuItem, Select, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material';
import axios from 'axios';
import NavBar from './NavBar';
import { DatePicker, LocalizationProvider } from '@mui/x-date-pickers';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs/AdapterDayjs';
import dayjs from 'dayjs';
const defaultTheme = createTheme();
export default function AccountPage() {

    const [customerId, setCustomerId] = useState("");
    const [accounts, setAccounts] = useState([]);

    const [selectedAccount, setSelectedAccont] = useState(-1);
    const [transactions, setTransactions] = useState([]);
    const [startdate, setstartdate] = useState(dayjs())
    const [enddate, setenddate] = useState(dayjs())

    const [a,setA]=useState("");
    const[b,setB]=useState("");

    async function getAccounts(customer_id, setAccounts) {
        const url = `http://localhost:3000/account/${customer_id}`;
        await axios.get(url).then((response) => {
            setAccounts(response.data);
        });

    }

    const sdateChangeHandler = (event) => {
        setA(event.target.value);
        console.log(event);
        console.log(a);
      };
    
      const edateChangeHandler = (event) => {
        setB(event.target.value);
        console.log(event);
        console.log(b);
      };

      const onsubmitHandler=(event)=>{
        event.preventDefault();
        console.log("inside this ")
      }

      const submitHandler=( event)=>{
        event.preventDefault();
        console.log(startdate);
        console.log(enddate);
        const sdate=startdate.year()+'-'+(startdate.month()+1)+'-'+startdate.date();
        const edate=enddate.year()+'-'+(enddate.month()+1)+'-'+enddate.date();
        console.log(sdate);
        const url = `http://localhost:3000/statement/${selectedAccount}/${sdate}/${edate}`;
        axios.get(url).then((response) => {
        console.log(response);
        setTransactions(response.data);
        console.log(transactions)
    });
      }

    const handleChange = (event) => {
        setSelectedAccont(event.target.value);
        //console.log(selectedAccount);
      }

    useEffect(() => {
        getAccounts(window.sessionStorage.getItem("customer_id"), setAccounts);
        console.log(accounts);
    }, [])
    useEffect(() => {
        if(accounts.length > 0) setSelectedAccont(accounts[0].account_id);
      }, [accounts]);

    return(
        <>
                <ThemeProvider theme={defaultTheme}>
                <NavBar/>
                <Typography component="h1" variant="h5" align='center' sx={{color: 'darkblue', fontSize: '20px', fontWeight: 'bold'}}>
                        ACCOUNT DASHBOARD
                </Typography> 
                

                {accounts.length>0?<TableContainer>
                    <Table sx={{border: '0.5rem outset skyblue'}}>
                        <TableHead sx={{border: '0.5rem outset skyblue'}}>
                            <TableRow sx={{border: '0.5rem outset skyblue'}}>
                                <TableCell align="center" sx={{border: '0.5rem outset skyblue', color: 'firebrick', fontWeight:'bold'}}>ACCOUNT ID</TableCell>
                                <TableCell align='center' sx={{border: '0.5rem outset skyblue', color: 'firebrick', fontWeight:'bold'}}>IFSC</TableCell>
                                <TableCell align = 'center' sx={{border: '0.5rem outset skyblue', color: 'firebrick', fontWeight:'bold'}}>BRANCH</TableCell>
                                <TableCell align='center' sx={{border: '0.5rem outset skyblue', color: 'firebrick', fontWeight:'bold'}}>ACCOUNT TYPE</TableCell>
                                <TableCell align='center' sx={{border: '0.5rem outset skyblue', color: 'firebrick', fontWeight:'bold'}}>BALANCE</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {accounts.map((row) => {
                                return (<TableRow sx={{border: '0.5rem outset skyblue'}} key={row.account_id}>
                                    <TableCell align='center' sx={{border: '0.5rem outset skyblue', color: 'charcoal', fontWeight:'bold'}}>{row.account_id}</TableCell>
                                    <TableCell align='center' sx={{border: '0.5rem outset skyblue', color: 'charcoal', fontWeight:'bold'}}>{row.ifsc}</TableCell>
                                    <TableCell align='center' sx={{border: '0.5rem outset skyblue', color: 'charcoal', fontWeight:'bold'}}>{row.branch}</TableCell>
                                    <TableCell align='center' sx={{border: '0.5rem outset skyblue', color: 'charcoal', fontWeight:'bold'}}>{row.account_type}</TableCell>
                                    <TableCell align='center' sx={{border: '0.5rem outset skyblue', color: 'charcoal', fontWeight:'bold'}}>{row.balance}</TableCell>
                                </TableRow>)
                            })}
                        </TableBody>
                    </Table>
                </TableContainer>: <Typography component="h3" variant="h5" align='center' sx={{color: 'charcoal', fontSize: '20px', fontWeight: 'bold'}}>
                        NO ACCOUNTS CREATED
                </Typography>}
                <Box sx={{margin: 'auto', width: '50%', display: 'flex', justifyContent: 'center'}}>
                <Button 
                        align='center'
                        variant="contained" component={Link} to="/openaccount"
                        sx={{ mt: 3, mb: 2 }}>Create a new account</Button> 
                </Box>
                <Typography component="h1" variant="h5" align='center' sx={{color: 'darkblue', fontSize: '20px', fontWeight: 'bold'}}>
                        ACCOUNT STATEMENT
                </Typography>
            
      {accounts.length > 0 ?<div>
        <Box component="form" onSubmit={submitHandler} sx={{ flexGrow: 2 }}>

            <Grid container spacing={2} sx={{margin: 'auto', width: '75%', display: 'flex', justifyContent: 'center'}}>
            <Grid item xm={3}>
               
                        <TextField value={selectedAccount}
                            label="Account No."
                            style={{minWidth:'6em'}}
                            select
                            onChange={handleChange}>
                            {accounts.map((acc) => {
                                return <MenuItem value={acc.account_id}>{acc.account_id}</MenuItem>
                            })}
                        </TextField>
            </Grid>
            
                <LocalizationProvider dateAdapter={AdapterDayjs}>
                    <Grid item xm={3}>
                        <DatePicker label="Start Date" 
                            value={startdate} onChange={(newSDate)=>setstartdate(newSDate)} />
                    </Grid>
                    <Grid item xm={3}>
                        <DatePicker label="End Date" disableFuture
                            value={enddate} onChange={(newEDate)=>setenddate(newEDate)} />
                    </Grid>
                </LocalizationProvider>
                <Grid item xm={3}>
                <Button type="submit"
                    variant="contained" sx={{mt:'10%'}} >
                        Generate
                </Button>
                </Grid>
        </Grid>
        </Box>
        </div>: <></> //empty placeholder since no account message showed already
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
                </Typography>}
                </ThemeProvider>
                </>
    )
}