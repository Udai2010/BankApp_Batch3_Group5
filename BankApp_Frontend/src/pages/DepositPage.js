import React, { useState, useEffect } from "react";
import axios from "axios";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { FormControl, InputLabel, Select, MenuItem } from "@mui/material";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { Grid } from "@mui/material";

const defaultTheme = createTheme();

export default function DepositPage() {
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
  const [amount, setAmount] = useState("");
  const [selectedAccount, setSelectedAccont] = useState(-1);

  async function getAccounts(customer_id, setAccounts) {
    const url = `http://localhost:3000/account/${customer_id}`;
    await axios.get(url).then((response) => {
      setAccounts(response.data);
      console.log(accounts);
    });
  }

  const onAmountChange = (event) => {
    if (event.target.value < 0) {
      alert("Negative amount not allowed");
      //document.getElementById('amount').value = '';
      return;
    }
    setAmount(event.target.value);
  };

  const onSelectAccount = (event) => {
    setSelectedAccont(event.target.value);
  };

  useEffect(() => {
    getAccounts(window.sessionStorage.getItem("customer_id"), setAccounts);
  }, [customerId]);
  useEffect(() => {
    if (accounts.length > 0) setSelectedAccont(accounts[0].account_id);
  }, [accounts]);

  const onDeposit = (event) => {
    event.preventDefault();
    const url = "http://localhost:3000/deposit";
    axiosInstance
      .put(url, {
        account_id: selectedAccount,
        amount: amount,
      })
      .then((response) => {
        alert(response.data);
        //console.log(response);
        //navigate("/dashboard");
      })
      .catch((err) => {
        alert("error- " + err);
      });
  };

  return (
    <>
      <ThemeProvider theme={defaultTheme}>
        <Container component="main" maxWidth="sm">
          <CssBaseline />
          <Box
            sx={{
              marginTop: 8,
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
            }}
          >
            <Typography component="h1" variant="h5">
              Deposit
            </Typography>
            <Box
              component="form"
              onSubmit={onDeposit}
              noValidate
              sx={{ mt: 1 }}
            >
              {accounts.length > 0 ? (
                <div>
                  <FormControl>
                    <InputLabel id="selectAccount">Account number</InputLabel>
                    <Select
                      id="selectAccount"
                      value={selectedAccount}
                      label="Account number"
                      onChange={onSelectAccount}
                    >
                      {accounts.map((acc) => {
                        return (
                          <MenuItem value={acc.account_id}>
                            {acc.account_id}
                          </MenuItem>
                        );
                      })}
                    </Select>
                  </FormControl>
                </div>
              ) : (
                <p>No accounts</p>
              )}
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
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
              >
                Deposit
              </Button>
            </Box>
          </Box>
        </Container>
      </ThemeProvider>
    </>
  );
}
