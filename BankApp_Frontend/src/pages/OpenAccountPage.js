import React, { useEffect, useState } from 'react'
import axios from 'axios'

import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { Checkbox, FormControlLabel, Grid } from '@mui/material';
import Backdrop from '@mui/material/Backdrop';
import Modal from '@mui/material/Modal';
import Fade from '@mui/material/Fade';
import {Select} from '@mui/material';
import {MenuItem} from '@mui/material';
import { useNavigate } from 'react-router-dom';
import NavBar from './NavBar';
const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  p: 4,
};

const defaultTheme = createTheme();

export default function OpenAccountPage() {
  const token = localStorage.getItem("token");

  const authToken = `Bearer ${token}`;
  const axiosInstance = axios.create({
    baseURL: "http://localhost:3000", // Replace with your API URL
    headers: {
      Authorization: authToken,
      "Content-Type": "application/json", // You can include other headers if needed
    },
  });

  const baseURL = "http://localhost:3000/createAccount/";
  const [customerId,setCustomerId]=useState("");
  const [occupationType,setOccupationType]=useState("");
  const [sourceOfIncome, setSourceOfIncome]=useState("");
  const [grossSalary, setGrossSalary]=useState("");
  const [debit_card, setDebitCard]=useState("");
  const [net_banking, setNet_banking]=useState(false);
  const [account_type, setAccountType]=useState(false);
  const [branch, setBranch] = useState("");
  const [success, setSuccess] = useState(false)
  const [message, setMessage] = useState("");
  const navigate = useNavigate()

  useEffect(() => {
    setCustomerId(window.sessionStorage.getItem("customer_id"));
  }, [customerId])
  
  // Model Handler
  const [open, setOpen] = useState(true);
  const handleClose = () => {
    setOpen(false);
    setSuccess(false);
    navigate('/account');
  };


  const onCustomerIdChange = (event) => {
    setCustomerId(event.target.value);
  };

  const onOccupationTypeChange = (event) => {
    setOccupationType(event.target.value);
  };

  const onSourceOfIncomeChange = (event) => {
    setSourceOfIncome(event.target.value);
  };

  const onGrossSalaryChange = (event) => {
    setGrossSalary(event.target.value);
  };

  const onDebitChange = (event) => {
    setDebitCard(event.target.checked);
  }

  const onNetBankingChange = (event) => {
    setNet_banking(event.target.checked);
  }

  const onBranchChange = (event) => {
    setBranch(event.target.value);
  }

  const onAccountTypeChange = (event) => {
    setAccountType(event.target.value);
    console.log(account_type);
  }

  const ifscGenerate = () => {
    const characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    let result='';
    for(let i=0;i<5;i++){
      result+=characters.charAt(Math.floor(Math.random() * characters.length));
    }
    return result;
  }

  const onSubmitForm = (event) => {
    event.preventDefault();
    const debitCardValue = debit_card? "yes" : "no";
    const netBankingValue = net_banking? "yes" : "no";
    const ifsc = branch+ifscGenerate();
    axiosInstance
      .post(baseURL+customerId,{
          debit_card: debitCardValue,
          branch: branch,
          account_type: account_type,
          net_banking: netBankingValue,
          status:"inactive",
          ifsc:ifsc,
          balance:5000,
          occupation_type: occupationType,
          income_source: sourceOfIncome,
          annual_income: grossSalary

      })
      .then((response)=>{
        setMessage(response.data);
        setSuccess(true);
        console.log(response);
      })
      .catch((err)=>{
        alert("error- "+err)
      });  
  };

  return (
    <>
      {!success ?
        <>
          <ThemeProvider theme={defaultTheme}>
          <NavBar/>

            <Container component="main" maxWidth="sm">
              <CssBaseline />
              <Box
                sx={{
                  marginTop: 8,
                  display: 'flex',
                  flexDirection: 'column',
                  alignItems: 'center',
                  border: '0.5rem outset skyblue'
                }}
              >
                <Avatar sx={{ m: 1, bgcolor: 'charcoal' }}>
                </Avatar>
                <Typography component="h1" variant="h5" sx={{color: 'steelblue', fontSize: '20px', fontWeight: 'bold'}}>
                      OPEN AN ACCOUNT
                </Typography>
                <Box component="form" onSubmit={onSubmitForm}  sx={{ m: 2 }}>
                  <Grid container columnSpacing={1.5}>
                    <Grid item xs={6} >
                      <TextField
                          margin="normal"
                          required
                          fullWidth
                          id="customerId"
                          label="Customer ID"
                          name="customerId"
                          autoComplete="customerId"
                          value={customerId}
                          onChange={onCustomerIdChange}
                          autoFocus
                        />
                    </Grid>
                    <Grid item xs={6} >
                      <TextField
                          margin="normal"
                          required
                          fullWidth
                          id="occupationType"
                          label="Occupation type"
                          name="occupationType"
                          autoComplete="occupationType"
                          onChange={onOccupationTypeChange}
                          autoFocus
                        />
                    </Grid>
                    <Grid item xs={6} >
                      <TextField
                          margin="normal"
                          required
                          fullWidth
                          id="sourceOfIncome"
                          label="Source of income"
                          name="sourceOfIncome"
                          autoComplete="sourceOfIncome"
                          onChange={onSourceOfIncomeChange}
                          autoFocus
                        />
                    </Grid>
                    <Grid item xs={6} >
                      <TextField
                          margin="normal"
                          required
                          fullWidth
                          id="annualIncome"
                          label="Annual income"
                          name="annualIncome"
                          autoComplete="annualIncome"
                          onChange={onGrossSalaryChange}
                          autoFocus
                        />
                    </Grid>

                    <Grid item xs={6} >
                      <TextField
                          margin="normal"
                          required
                          fullWidth
                          id="branch"
                          label="Branch"
                          name="branch"
                          autoComplete="branch"
                          onChange={onBranchChange}
                          autoFocus
                        />
                    </Grid>

                    <Grid item xs={6} >
                      <TextField
                            margin="normal"
                            label="Account Type"
                            id="accountType"
                            value={account_type}
                            style={{minWidth:'15em'}}
                            select
                            required
                            fullWidth
                            onChange={onAccountTypeChange}>
                            <MenuItem value={"Savings"}>Savings</MenuItem>
                            <MenuItem value={"Fixed Deposit"}>Fixed Deposit</MenuItem>
                            <MenuItem value={"Salary"}>Salary</MenuItem>
                      </TextField>
                    </Grid>

                    <Grid item xs={6} >
                        <FormControlLabel
                          label="Do you need a debit card?"
                          control={<Checkbox checked={debit_card} onChange={onDebitChange}/>}
                        />
                    </Grid>
                    <Grid item xs={6} >
                        <FormControlLabel
                          label="Do you need Net banking?"
                          control={<Checkbox checked={net_banking} onChange={onNetBankingChange}/>}
                        />
                    </Grid>
                  </Grid>
                  <Button
                    type="submit"
                    fullWidth
                    variant="contained"
                    sx={{ mt: 3, mb: 2 }}
                  >
                    Open Account
                  </Button>
                </Box>
              </Box>
            </Container>
          </ThemeProvider>
        </>
        :
        <>
          <div>
            <Modal
              aria-labelledby="transition-modal-title"
              aria-describedby="transition-modal-description"
              open={open}
              onClose={handleClose}
              closeAfterTransition
              slots={{ backdrop: Backdrop }}
              slotProps={{
                backdrop: {
                  timeout: 500,
                },
              }}
            >
              <Fade in={open}>
                <Box sx={style}>
                  <Typography id="transition-modal-description" sx={{ mt: 2 }}>
                    Account Created Successfully. {message}
                  </Typography>
                </Box>
              </Fade>
            </Modal>
          </div>
        </>
        }
    </>
  )
}