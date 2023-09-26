import React, { useEffect, useState } from "react";
import {
  Button,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from "@mui/material";
import { Link } from "react-router-dom";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import axios from "axios";
import React, { useEffect, useState } from 'react';
import { Button} from '@mui/material';
import { Link } from 'react-router-dom';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { FormControl, InputLabel, MenuItem, Select, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material';
import axios from 'axios';

const defaultTheme = createTheme();
export default function AccountPage() {
    
  const  token  = localStorage.getItem('token')

  const [customerId, setCustomerId] = useState("");
  const [accounts, setAccounts] = useState([]);

  async function getAccounts(customer_id, setAccounts) {
    console.log(token);
    const authToken = `Bearer ${token}`;
    const axiosInstance = axios.create({
      baseURL: "http://localhost:3000", // Replace with your API URL
      headers: {
        Authorization: authToken,
        "Content-Type": "application/json", // You can include other headers if needed
      },
    });
    await axiosInstance.get(`/account/${customer_id}`).then((response) => {
      setAccounts(response.data);
    });
  }
  
    const [selectedAccount, setSelectedAccont] = useState(-1);
    const [transactions, setTransactions] = useState([]);
    const [startdate, setstartdate] = useState("")
     const [enddate, setenddate] = useState("")


    async function getAccounts(customer_id, setAccounts) {
        const url = `http://localhost:3000/account/${customer_id}`;
        await axios.get(url).then((response) => {
            setAccounts(response.data);
        });

  useEffect(() => {
    getAccounts(window.sessionStorage.getItem("customer_id"), setAccounts);
    console.log(accounts);
  }, []);
  return (
    <>
      <h2>Account dashboard</h2>

      {accounts.length > 0 ? (
        <TableContainer>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>Account Id</TableCell>
                <TableCell>IFSC</TableCell>
                <TableCell>Branch</TableCell>
                <TableCell>Account type</TableCell>
                <TableCell>Balance</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {accounts.map((row) => {
                return (
                  <TableRow key={row.account_id}>
                    <TableCell>{row.account_id}</TableCell>
                    <TableCell>{row.ifsc}</TableCell>
                    <TableCell>{row.branch}</TableCell>
                    <TableCell>{row.account_type}</TableCell>
                    <TableCell>{row.balance}</TableCell>
                  </TableRow>
                );
              })}
            </TableBody>
          </Table>
        </TableContainer>
      ) : (
        <p>There are no accounts for this user.</p>
      )}

      <Button
        variant="contained"
        component={Link}
        to="/openaccount"
        sx={{ mt: 3, mb: 2 }}
      >
        Create a new account
      </Button>
    </>
  );
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
                <h2>Account dashboard</h2> 

                {accounts.length>0?<TableContainer>
                    <Table>
                        <TableHead>
                            <TableRow>
                                <TableCell>Account Id</TableCell>
                                <TableCell>IFSC</TableCell>
                                <TableCell>Branch</TableCell>
                                <TableCell>Account type</TableCell>
                                <TableCell>Balance</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {accounts.map((row) => {
                                return (<TableRow key={row.account_id}>
                                    <TableCell>{row.account_id}</TableCell>
                                    <TableCell>{row.ifsc}</TableCell>
                                    <TableCell>{row.branch}</TableCell>
                                    <TableCell>{row.account_type}</TableCell>
                                    <TableCell>{row.balance}</TableCell>
                                </TableRow>)
                            })}
                        </TableBody>
                    </Table>
                </TableContainer>: <p>There are no accounts for this user.</p>}
                
                <Button 
                        variant="contained" component={Link} to="/openaccount"
                        sx={{ mt: 3, mb: 2 }}>Create a new account</Button> 

            <h2>Account Statement</h2> 
            
      {accounts.length > 0 ?<div>
        <FormControl>
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
        </FormControl>
        </div>: <p>No accounts</p>
      }
    
      

    <form onSubmit={submitHandler}>
        <label for="sdate">     Start Date     </label>
        <input type="date" id="sdate" onChange={sdateChangeHandler}></input>
        <br/>
        <label for="edate">     End Date    </label>
        <input type="date" id="edate" onChange={edateChangeHandler}/>
        <br/>
        <input type="submit" color="blue" />
        
        </form>
        
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
    </TableContainer>: <p>No transactions found for this date range in the account .</p>}

                </>
    )
}