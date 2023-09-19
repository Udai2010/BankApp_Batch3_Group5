import React from "react";


import { createTheme, ThemeProvider, AppBar,Box,Toolbar,Typography,Button } from "@mui/material";

import { Grid, Avatar, CssBaseline, Container, Card, CardContent, CardActions } from '@mui/material';

import BadgeIcon from '@mui/icons-material/Badge';
import AccountBoxIcon from '@mui/icons-material/AccountBox';
import AddBoxIcon from '@mui/icons-material/AddBox';
import IndeterminateCheckBoxIcon from '@mui/icons-material/IndeterminateCheckBox';
import LoopIcon from '@mui/icons-material/Loop';
import CompareArrowsIcon from '@mui/icons-material/CompareArrows';
import { Link } from "react-router-dom";

const defaultTheme = createTheme();

export default function Dashboard () {
    return(
        <>
            <ThemeProvider theme={defaultTheme}>
                <Box sx={{ flexGrow: 1 }}>
                    <AppBar position="static">
                    <Toolbar>
                    
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                        Online Banking App
                    </Typography>
                    <Button color="inherit">Home</Button>
                    <Button color="inherit">About</Button>
                    <Button color="inherit">Contact</Button>
                    <Button color="inherit" href="/changepassword">Change password</Button>
                    </Toolbar>
                    </AppBar>
                </Box>
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
                        Hello User
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