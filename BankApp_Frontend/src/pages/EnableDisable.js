import React, {useEffect,useState, useRef} from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { Typography, Box, Grid, TextField, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Button } from '@mui/material';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { purple } from "@mui/material/colors";
import NavBar from './NavBar';
const defaultTheme = createTheme(
    {palette:{
        primary: purple
    }}
);


export default function EnableDisable() {
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
  const [selectAccount, setSelectedAccount] = useState(-1);
  const [access, setAccess] = useState("");
  const isFirstRender = useRef(true);

  const onCustomerIdChange = (event) => {
    setCustomerId(event.target.value);
  };

  const handleKeyPress = (event) => {
    if (event.key == "Enter") {
      handleSearch();
    }
  };

  const handleSearch = () => {
    getAccounts(customerId);
  };

  async function getAccounts(customerId) {
    const url = `http://localhost:3000/account/${customerId}`;
    await axiosInstance.get(url).then((response) => {
      console.log(response);
      setAccounts(response.data);
    });
  }

  useEffect(() => {
    if (isFirstRender.current) isFirstRender.current = false;
    else EnableDisableAccount();
  }, [access, isFirstRender]);

  const EnableDisableAccount = () => {
    if (access == -1) return;
    console.log(selectAccount);
    console.log(access);
    const url = "http://localhost:3000/enable-disable";
    axios
      .post(url, {
        access: access,
        account_id: selectAccount,
      })
      .then((response) => {
        console.log(response);
        alert(response.data);
      });
  };

  const onEnableDisable = (event) => {
    const accId = event.target.dataset.accid;
    const status = event.target.dataset.status;
    setSelectedAccount(accId);
    if (status == "active") {
      setAccess("inactive");
    } else {
      setAccess("active");
    }

    
    return (
        <>
            <ThemeProvider theme={defaultTheme}>
            <NavBar userType="admin"/>

            <Typography component="h1" variant="h5" align='center' sx={{color: 'mediumorchid', fontSize: '20px', fontWeight: 'bold'}}>
                    ENABLE DISABLE ACCOUNTS
            </Typography> 
            <Box sx={{ flexGrow: 2 }}>
                <Grid container spacing={2} sx={{margin: 'auto', width: '75%', display: 'flex', justifyContent: 'center'}}>
                    <Grid item xm={6}>        
                    <Typography component="h4" variant="h5" align='center' sx={{mt:'5%', color: 'dimgrey', fontSize: '20px', fontWeight: 'bold'}}>
                                Select Customer To View
                    </Typography>
                    </Grid>
                    <Grid item xm={6}> 
                        <TextField 
                            id="customerId"
                            value={customerId}
                            sx={{maxWidth:'6em'}}
                            label="Customer id"
                            onChange={onCustomerIdChange}
                            onKeyPress={handleKeyPress}
                        />
                    </Grid>
                </Grid>
            </Box>            
      
      {accounts.length>0?<TableContainer>
        <Table sx={{border: '0.5rem outset plum', marginTop:'2%'}}>
            <TableHead sx={{border: '0.5rem outset plum'}}>
                <TableRow sx={{border: '0.5rem outset plum'}}>
                    <TableCell align="center" sx={{border: '0.5rem outset plum', color: 'darkolivegreen', fontWeight:'bold'}}>Account Id</TableCell>
                    <TableCell align="center" sx={{border: '0.5rem outset plum', color: 'darkolivegreen', fontWeight:'bold'}}>Balance</TableCell>
                    <TableCell align="center" sx={{border: '0.5rem outset plum', color: 'darkolivegreen', fontWeight:'bold'}}>Account Type</TableCell>
                    <TableCell align="center" sx={{border: '0.5rem outset plum', color: 'darkolivegreen', fontWeight:'bold'}}>IFSC</TableCell>
                    <TableCell align="center" sx={{border: '0.5rem outset plum', color: 'darkolivegreen', fontWeight:'bold'}}>Branch</TableCell>
                    <TableCell align="center" sx={{border: '0.5rem outset plum', color: 'darkolivegreen', fontWeight:'bold'}}>Status</TableCell>
                    <TableCell align="center" sx={{border: '0.5rem outset plum', color: 'darkolivegreen', fontWeight:'bold'}}>Enable/Disable</TableCell>
                </TableRow>
            </TableHead>
            <TableBody>
                {accounts.map((row) => {
                    return (
                        <TableRow key={row.account_id} sx={{border: '0.5rem outset plum'}}>
                            <TableCell align="center" sx={{border: '0.5rem outset plum', color: 'charcoal', fontWeight:'bold'}}>{row.account_id}</TableCell>
                            <TableCell align="center" sx={{border: '0.5rem outset plum', color: 'charcoal', fontWeight:'bold'}}>{row.balance}</TableCell>
                            <TableCell align="center" sx={{border: '0.5rem outset plum', color: 'charcoal', fontWeight:'bold'}}>{row.account_type}</TableCell>
                            <TableCell align="center" sx={{border: '0.5rem outset plum', color: 'charcoal', fontWeight:'bold'}}>{row.ifsc}</TableCell>
                            <TableCell align="center" sx={{border: '0.5rem outset plum', color: 'charcoal', fontWeight:'bold'}}>{row.branch}</TableCell>
                            <TableCell align="center" sx={{border: '0.5rem outset plum', color: 'charcoal', fontWeight:'bold'}}>{row.status}</TableCell>
                            <TableCell align="center" sx={{border: '0.5rem outset plum', color: 'charcoal', fontWeight:'bold'}}>
                                <Button
                                    fullWidth
                                    variant="contained"
                                    data-accid = {row.account_id}
                                    data-status = {row.status}
                                    onClick={(e)=>onEnableDisable(e)}
                                    sx={{ mt: 3, mb: 2 }}
                                >
                                    Enable/Disable
                                </Button>
                            </TableCell>
                        </TableRow>
                        )
                })}
            </TableBody>
        </Table>
    </TableContainer>: <Typography component="h3" variant="h5" align='center' sx={{color: 'charcoal', fontSize: '20px', fontWeight: 'bold'}}>
                        NO ACCOUNTS AVAILABLE FOR CUSTOMER
                </Typography>
        }
    </ThemeProvider>
    </>
    )
}
