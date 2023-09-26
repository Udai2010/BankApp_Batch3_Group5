
import React, { useEffect, useState } from 'react';
import { FormControl, InputLabel, MenuItem, Select, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material';
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
               <NavBar/>

    <div>TransactionPage
      {accounts.length > 0 ? (
        <div>
          <FormControl>
            <InputLabel id="selectAccount">Account number</InputLabel>
            <Select
              id="selectAccount"
              value={selectedAccount}
              label="Account number"
              onChange={handleChange}
            >
              {accounts.map((acc) => {
                return (
                  <MenuItem value={acc.account_id}>{acc.account_id}</MenuItem>
                );
              })}
            </Select>
          </FormControl>
        </div>
      ) : (
        <p>No accounts</p>
      )}
      {transactions.length > 0 ? (
        <TableContainer>
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
                return (
                  <TableRow key={row.transaction_id}>
                    <TableCell>{row.transaction_id}</TableCell>
                    <TableCell>{row.sender_account.account_id}</TableCell>
                    <TableCell>{row.receiver_account?.account_id}</TableCell>
                    <TableCell>{row.amount}</TableCell>
                    <TableCell>{row.transactionType}</TableCell>
                    <TableCell>{row.transactionDate}</TableCell>
                    <TableCell>{row.status}</TableCell>
                  </TableRow>
                );
              })}
            </TableBody>
        </Table>
    </TableContainer>: <p>There are no transactions for this account.</p>}
    
  </div>
  </>
  )

}
