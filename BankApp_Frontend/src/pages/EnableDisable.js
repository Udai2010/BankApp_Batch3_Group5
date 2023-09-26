import React, { useEffect, useState, useRef } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import {
  FormControl,
  TextField,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Button,
} from "@mui/material";
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
  };

  return (
    <>
      <h2>Enable Disable Account</h2>

      <div>
        <FormControl>
          <TextField
            id="customerId"
            value={customerId}
            label="Customer id"
            onChange={onCustomerIdChange}
            onKeyPress={handleKeyPress}
          />
        </FormControl>
      </div>

      {accounts.length > 0 ? (
        <TableContainer>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>Account Id</TableCell>
                <TableCell>Balance</TableCell>
                <TableCell>Account Type</TableCell>
                <TableCell>IFSC</TableCell>
                <TableCell>Branch</TableCell>
                <TableCell>Status</TableCell>
                <TableCell>Enable/Disable</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {accounts.map((row) => {
                return (
                  <TableRow key={row.account_id}>
                    <TableCell>{row.account_id}</TableCell>
                    <TableCell>{row.balance}</TableCell>
                    <TableCell>{row.account_type}</TableCell>
                    <TableCell>{row.ifsc}</TableCell>
                    <TableCell>{row.branch}</TableCell>
                    <TableCell>{row.status}</TableCell>
                    <TableCell>
                      <Button
                        fullWidth
                        variant="contained"
                        data-accid={row.account_id}
                        data-status={row.status}
                        onClick={(e) => onEnableDisable(e)}
                        sx={{ mt: 3, mb: 2 }}
                      >
                        Enable/Disable
                      </Button>
                    </TableCell>
                  </TableRow>
                );
              })}
            </TableBody>
          </Table>
        </TableContainer>
      ) : (
        <p>There are no accounts for this Customer.</p>
      )}
    </>
  );
}
