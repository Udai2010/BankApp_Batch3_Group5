import React, { useEffect, useState } from 'react';
import { Button} from '@mui/material';
import { Link } from 'react-router-dom';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { Box, Typography, FormControl, InputLabel, MenuItem, Select, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material';
import axios from 'axios';
import NavBar from './NavBar';
const defaultTheme = createTheme();
export default function AccountPage() {

    const [customerId, setCustomerId] = useState("");
    const [accounts, setAccounts] = useState([]);

    const [selectedAccount, setSelectedAccont] = useState(-1);
    const [transactions, setTransactions] = useState([]);
    const [startdate, setstartdate] = useState("")
    const [enddate, setenddate] = useState("")



    async function getAccounts(customer_id, setAccounts) {
        const url = `http://localhost:3000/account/${customer_id}`;
        await axios.get(url).then((response) => {
            setAccounts(response.data);
        });

    }

    const sdateChangeHandler = (event) => {
        setstartdate(event.target.value);
      };
    
      const edateChangeHandler = (event) => {
        setenddate(event.target.value);
      };
      const submitHandler=( event)=>{
        event.preventDefault();
        console.log(startdate);
        console.log(enddate);

        const url = `http://localhost:3000/statement/${selectedAccount}/${startdate}/${enddate}`;
        axios.get(url).then((response) => {
        console.log(response);
        setTransactions(response.data);
        }).catch((err) => {
            alert(err);
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
            <h2>Account Statement</h2> 
            
      {accounts.length > 0 ?<div>
        <FormControl style={{display:"inline", margin:"20px", padding:"20px"}}>
          <InputLabel id="selectAccount">Account number</InputLabel>
          <Select 
            id="selectAccount"
            value={selectedAccount}
            label="Account number"
            onChange={handleChange}>
              {accounts.map((acc) => {
                return <MenuItem value={acc.account_id}>{acc.account_id}</MenuItem>
              })}
            </Select>
            <form onSubmit={submitHandler} style={{display:"inline", margin:"50px"}}>
        
            <label for="sdate">     Start Date     </label>
            <input type="date" id="sdate" onChange={sdateChangeHandler}></input>
            {/* <br/> */}
            <label for="edate">     End Date    </label>
            <input type="date" id="edate" min={startdate} onChange={edateChangeHandler}/>
            {/* <br/> */}
            <input type="submit" color="blue" />
            
            </form>
        </FormControl>
        </div>: <p>No accounts</p>
      }
    
      

    
        
    {transactions.length>0?<TableContainer>
        <Table>
            <TableHead>
                <TableRow>
                    <TableCell>Transaction Id</TableCell>
                    <TableCell>Sender</TableCell>
                    <TableCell>Receiver</TableCell>
                    <TableCell>Amount</TableCell>
                    <TableCell>Transaction type</TableCell>
                    <TableCell>Date</TableCell>
                    <TableCell>Status</TableCell>
                </TableRow>
            </TableHead>
            <TableBody>
                {transactions.map((row) => {
                    return (<TableRow key={row.transaction_id}>
                        <TableCell>{row.transaction_id}</TableCell>
                        <TableCell>{row.sender_account.account_id}</TableCell>
                        <TableCell>{row.receiver_account?.account_id}</TableCell>
                        <TableCell>{row.amount}</TableCell>
                        <TableCell>{row.transactionType}</TableCell>
                        <TableCell>{row.transactionDate}</TableCell>
                        <TableCell>{row.status}</TableCell>
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