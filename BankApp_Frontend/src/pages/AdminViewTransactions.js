import React, { useEffect, useState } from 'react';
import { FormControl, Button, MenuItem, Select, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField } from '@mui/material';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import axios from 'axios';

const defaultTheme = createTheme();
export default function AdminViewTransactions() {
  const [customerId, setCustomerId] = useState("");
  const [transactions, setTransactions] = useState([]);
  const [selectedCustomer, setSelectedCustomer] = useState(-1);


  async function getTransactions(customer_id, setTransactions) {
    const url = `http://localhost:3000/admingettransactions/${customer_id}`;
    await axios.get(url).then((response) => {
      console.log(response);
        setTransactions(response.data);
    });
  }

  const handleChange = (event) => {
    console.log(event.target.value);
    setSelectedCustomer(event.target.value);
  }
  const handleKeyPress = (event) => {
    if(event.key == 'Enter'){
        handleSearch();
    }
  }

//   useEffect(() => {
//     if(selectedCustomer !== -1) getTransactions(selectedCustomer, setTransactions);
//   }, [selectedCustomer]); 
  const handleSearch = () => {
    getTransactions(selectedCustomer, setTransactions);
  }
  
  return (
    <div>TransactionPage

      <div>
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
    </TableContainer>: <p>There are no transactions for this Customer.</p>}
    
  </div>
  )
}
