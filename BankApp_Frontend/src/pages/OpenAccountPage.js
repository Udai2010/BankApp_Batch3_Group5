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
import { useNavigate } from 'react-router-dom';

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
  const baseURL = "http://localhost:3000/createAccount/";
  const [customerId,setCustomerId]=useState("");
  const [occupationType,setOccupationType]=useState("");
  const [sourceOfIncome, setSourceOfIncome]=useState("");
  const [grossSalary, setGrossSalary]=useState("");
  const [debit_card, setDebitCard]=useState("");
  const [net_banking, setNet_banking]=useState("");
  
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
    setDebitCard(event.target.value);
  }

  const onNetBankingChange = (event) => {
    setNet_banking(event.target.value);
  }

  const onSubmitForm = (event) => {
    event.preventDefault();
    axios
      .post(baseURL+customerId,{
          debit_card: debit_card,
          branch: "BLR",
          account_type: "Savings",
          net_banking:net_banking,
          status:"active",
          ifsc:"BLR1234A",
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
            <Container component="main" maxWidth="sm">
              <CssBaseline />
              <Box
                sx={{
                  marginTop: 8,
                  display: 'flex',
                  flexDirection: 'column',
                  alignItems: 'center',
                }}
              >
                <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                </Avatar>
                <Typography component="h1" variant="h5">
                  Open a account
                </Typography>
                <Box component="form" onSubmit={onSubmitForm} noValidate sx={{ mt: 1 }}>
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
