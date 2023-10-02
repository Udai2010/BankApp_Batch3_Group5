import React,{useState, useEffect} from "react";
import axios from "axios";
import { createTheme, ThemeProvider, AppBar,Box,Toolbar,Typography,Button } from "@mui/material";
import { purple } from "@mui/material/colors";

import { Grid, Avatar, CssBaseline, Container, Card, CardContent, CardActions } from '@mui/material';

import PageviewIcon from '@mui/icons-material/Pageview';
import ContrastIcon from '@mui/icons-material/Contrast';
import AddIcon from '@mui/icons-material/Add';
import PersonSearchIcon from '@mui/icons-material/PersonSearch';
import { Link } from "react-router-dom";
import NavBar from "./NavBar";

const defaultTheme = createTheme(
    {palette:{
        primary: purple
    }}
);

export default function AdminDashboard () {
    const token = localStorage.getItem("token");

    const authToken = `Bearer ${token}`;
    const axiosInstance = axios.create({
        baseURL: "http://localhost:3000", // Replace with your API URL
        headers: {
        Authorization: authToken,
        "Content-Type": "application/json", // You can include other headers if needed
        },
    });
    const [admin_id, setAdminId] = useState("")
    const [admin, setAdmin] = useState([])

    async function getAdmin(admin_id, setAdmin) {
        console.log(token);
        const url = `http://localhost:3000/admin/${admin_id}`;
        await axiosInstance.get(url).then((response) => {
            setAdmin(response.data);
        })
        .catch((err)=>{
            alert(err);
        });

    }

    useEffect(() => {
        getAdmin(window.sessionStorage.getItem("admin_id"), setAdmin);
    }, [admin_id])

    return(
        <>
            <ThemeProvider theme={defaultTheme}>
                <NavBar userType="admin"/>
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
            <Typography variant="h4" component="div" sx={{ flexGrow: 1, fontWeight: 'bold', color: 'mediumorchid' }}>
                        Welcome {admin.name}, what would you like to do?
            </Typography>
            
            <Box sx={{ flexGrow: 2 }}>
                <Grid container spacing={2}>
                    <Grid item xs={6} alignItems="center">
                    <Card sx={{ minWidth: 200, border: '0.5rem outset plum' }}>
                    <CardContent sx={{margin: 'auto', width: '50%', display: 'flex', justifyContent: 'center'}}>
                    
                        <AddIcon align="center" style={{ fontSize: 60 }}/>
                    
                    </CardContent>
                    <CardActions>
                        <Button fullWidth
                        variant="contained" component={Link} to="/openaccount/admin"
                        sx={{ mt: 3, mb: 2 }}>Create Account</Button>
                    </CardActions>
                </Card>
                    </Grid>
                    <Grid item xs={6}>
                    <Card sx={{ minWidth: 200, border: '0.5rem outset plum' }}>
                    <CardContent sx={{margin: 'auto', width: '50%', display: 'flex', justifyContent: 'center'}}>
                    
                        <ContrastIcon align="center" style={{ fontSize: 60 }}/>
                    </CardContent>

                    <CardActions>
                        <Button fullWidth
                        variant="contained"  component={Link} to="/enable"
                        sx={{ mt: 3, mb: 2 }}>Enable/Disable</Button>
                    </CardActions>
                    </Card>
                    </Grid>
                    <Grid item xs={6}>
                    <Card sx={{ minWidth: 200, border: '0.5rem outset plum' }}>
                    <CardContent sx={{margin: 'auto', width: '50%', display: 'flex', justifyContent: 'center'}}>
                    
                        <PersonSearchIcon align="center" style={{ fontSize: 60 }}/>
                    </CardContent>

                    <CardActions>
                        <Button fullWidth
                        variant="contained" component={Link} to="/adminsearch"
                        sx={{ mt: 3, mb: 2 }}>User Search</Button>
                    </CardActions>
                    </Card> 
                    </Grid>
                    <Grid item xs={6}>
                    <Card sx={{ minWidth: 200, border: '0.5rem outset plum' }}>
                    <CardContent sx={{margin: 'auto', width: '50%', display: 'flex', justifyContent: 'center'}}>
                            <PageviewIcon align="center" style={{ fontSize: 60 }}/>
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