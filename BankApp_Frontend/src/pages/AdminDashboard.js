import React from "react";


import { createTheme, ThemeProvider, AppBar,Box,Toolbar,Typography,Button } from "@mui/material";
import { purple } from "@mui/material/colors";

import { Grid, Avatar, CssBaseline, Container, Card, CardContent, CardActions } from '@mui/material';

import BadgeIcon from '@mui/icons-material/Badge';
import AccountBoxIcon from '@mui/icons-material/AccountBox';
import AddBoxIcon from '@mui/icons-material/AddBox';
import IndeterminateCheckBoxIcon from '@mui/icons-material/IndeterminateCheckBox';
import LoopIcon from '@mui/icons-material/Loop';
import CompareArrowsIcon from '@mui/icons-material/CompareArrows';
import { Link } from "react-router-dom";

const defaultTheme = createTheme(
    {palette:{
        primary: purple
    }}
);

export default function AdminDashboard () {
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
                    <Button color="inherit" >Change password</Button>
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
                        Welcome Admin
            </Typography>
            
            <Box sx={{ flexGrow: 2 }}>
                <Grid container spacing={2}>
                    <Grid item xs={6} alignItems="center">
                    <Card sx={{ minWidth: 275 }}>
                    <CardContent style={{alignItems:"center"}}>
                    
                        <AccountBoxIcon style={{ fontSize: 60 }}/>
                    
                    </CardContent>
                    <CardActions>
                        <Button fullWidth
                        variant="contained" 
                        sx={{ mt: 3, mb: 2 }}>Create Account</Button>
                    </CardActions>
                </Card>
                    </Grid>
                    <Grid item xs={6}>
                    <Card sx={{ minWidth: 275 }}>
                    <CardContent>
                        <BadgeIcon style={{ fontSize: 60 }}/>
                    </CardContent>

                    <CardActions>
                        <Button fullWidth
                        variant="contained" 
                        sx={{ mt: 3, mb: 2 }}>Enable/Disable</Button>
                    </CardActions>
                    </Card>
                    </Grid>
                    <Grid item xs={6}>
                    <Card sx={{ minWidth: 275 }}>
                    <CardContent>
                        <AddBoxIcon style={{ fontSize: 60 }}/>
                    </CardContent>

                    <CardActions>
                        <Button fullWidth
                        variant="contained" component={Link} to="/adminsearch"
                        sx={{ mt: 3, mb: 2 }}>User Search</Button>
                    </CardActions>
                    </Card> 
                    </Grid>
                    <Grid item xs={6}>
                        <Card sx={{ minWidth: 275 }}>
                        <CardContent>
                            <IndeterminateCheckBoxIcon style={{ fontSize: 60 }}/>
                        </CardContent>

                        <CardActions>
                            <Button fullWidth
                            variant="contained" component={Link} to="/viewalltransaction"
                            sx={{ mt: 3, mb: 2 }}>View Transaction</Button>
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