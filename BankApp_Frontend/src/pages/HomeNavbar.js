import React from "react";
import { Box, AppBar, Toolbar, Typography, Button } from "@mui/material";
import { useNavigate } from "react-router-dom";

export default function HomeNavbar() {

    const navigate = useNavigate();


    return(
        <>
            <Box sx={{ flexGrow: 1 }}>
                    <AppBar position="static">
                        <Toolbar sx={{justifyContent:'space-between'}}>
                        <>
                             <Button color="inherit" href="/">
                                 <Typography variant="h6" component="div"  sx={{ flexGrow: 1 }}>
                                     Liberty Bank
                                 </Typography>
                             </Button>
                             <Box sx={{display:'flex'}}>
                                 <Button align ='right' color="inherit" href="/register">Register</Button>
                                 <Button align='right'  color="inherit" href="/login">Login</Button>
                                 <Button align='right'  color="inherit" href="/adminlogin">Admin Login</Button>
                             </Box>
                         </>
                        </Toolbar>
                    </AppBar>
            </Box>
        </>
    )
}