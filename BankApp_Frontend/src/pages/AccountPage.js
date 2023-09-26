import React, { useEffect, useState } from 'react';
import { Button, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material';
import { Link } from 'react-router-dom';
import { createTheme, ThemeProvider } from '@mui/material/styles';
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

    useEffect(() => {
        getAccounts(window.sessionStorage.getItem("customer_id"), setAccounts);
        console.log(accounts);
    }, [])
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
        </>
    )
}
