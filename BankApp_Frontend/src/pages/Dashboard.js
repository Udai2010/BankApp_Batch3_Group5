import React, { useState, useEffect } from "react";
import axios from 'axios';

import { createTheme, ThemeProvider, AppBar,Box,Toolbar,Typography,Button } from "@mui/material";

import { Grid, Avatar, CssBaseline, Container, Card, CardContent, CardActions } from '@mui/material';

import BadgeIcon from '@mui/icons-material/Badge';
import AccountBoxIcon from '@mui/icons-material/AccountBox';
import AddBoxIcon from '@mui/icons-material/AddBox';
import IndeterminateCheckBoxIcon from '@mui/icons-material/IndeterminateCheckBox';
import LoopIcon from '@mui/icons-material/Loop';
import CompareArrowsIcon from '@mui/icons-material/CompareArrows';
import { Link } from "react-router-dom";
import NavBar from "./NavBar";
const defaultTheme = createTheme();

export default function Dashboard () {

    const [customer_id, setCustomerId] = useState("")
    const [customer, setCustomer] = useState([])

    async function getCustomer(customer_id, setCustomer) {
        const url = `http://localhost:3000/user/${customer_id}`;
        await axios.get(url).then((response) => {
            setCustomer(response.data);
        });

    }

    useEffect(() => {
        getCustomer(window.sessionStorage.getItem("customer_id"), setCustomer);
    }, [customer_id])
    
    return(
        <>
            <ThemeProvider theme={defaultTheme}>
                <NavBar/>

            <Container component="div" maxWidth="85%">
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
            <Typography variant="h4" component="div" sx={{ flexGrow: 1 }}>
                        Hello {customer.name}, what do you want to do today?
            </Typography>
            
            <Box sx={{ flexGrow: 2 }}>
                <Grid container spacing={2}>
                    <Grid item xs={4} alignItems="center">
                    <Card sx={{ minWidth: 275 }}>
                    <CardContent style={{alignItems:"center"}}>
                    
                        <AccountBoxIcon style={{ fontSize: 60 }}/>
                    
                    </CardContent>
                    <CardActions>
                        <Button fullWidth
                        variant="contained" component={Link} to="/account"
                        sx={{ mt: 3, mb: 2 }}>Account</Button>
                    </CardActions>
                </Card>
                    </Grid>
                    <Grid item xs={4}>
                    <Card sx={{ minWidth: 275 }}>
                    <CardContent>
                        <BadgeIcon style={{ fontSize: 60 }}/>
                    </CardContent>

                    <CardActions>
                        <Button fullWidth
                        variant="contained" component={Link} to="/user"
                        sx={{ mt: 3, mb: 2 }}>Profile</Button>
                    </CardActions>
                    </Card>
                    </Grid>
                    <Grid item xs={4}>
                    <Card sx={{ minWidth: 275 }}>
                    <CardContent>
                        <AddBoxIcon style={{ fontSize: 60 }}/>
                    </CardContent>

                    <CardActions>
                        <Button fullWidth
                        variant="contained" component={Link} to="/deposit"
                        sx={{ mt: 3, mb: 2 }}>Deposit</Button>
                    </CardActions>
                    </Card> 
                    </Grid>
                    <Grid item xs={4}>
                        <Card sx={{ minWidth: 275 }}>
                        <CardContent>
                            <IndeterminateCheckBoxIcon style={{ fontSize: 60 }}/>
                        </CardContent>

                        <CardActions>
                            <Button fullWidth
                            variant="contained" component={Link} to="/withdraw"
                            sx={{ mt: 3, mb: 2 }}>Withdraw</Button>
                        </CardActions>
                        </Card>
                    </Grid>

                    <Grid item xs={4}>
                        <Card sx={{ minWidth: 275 }}>
                        <CardContent>
                            <CompareArrowsIcon style={{ fontSize: 60 }}/>
                        </CardContent>

                        <CardActions>
                            <Button fullWidth
                            variant="contained" component={Link} to="/fundtransfer"
                            sx={{ mt: 3, mb: 2 }}>Fund Transfer</Button>
                        </CardActions>
                        </Card>
                    </Grid>

                    <Grid item xs={4}>
                        <Card sx={{ minWidth: 275 }}>
                        <CardContent>
                            <LoopIcon style={{ fontSize: 60 }}/>
                        </CardContent>

                        <CardActions>
                            <Button fullWidth
                            variant="contained" component={Link} to="/transaction"
                            sx={{ mt: 3, mb: 2 }}>Transaction History</Button>
                        </CardActions>
                        </Card>
                    </Grid>
                </Grid>
            </Box>


          </Box>
        </Container>
            </ThemeProvider>
        </>
    )
}