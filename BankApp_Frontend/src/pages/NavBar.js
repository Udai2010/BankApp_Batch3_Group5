import React from "react";
import { Box, AppBar, Toolbar, Typography, Button } from "@mui/material";
import { useNavigate } from "react-router-dom";

export default function NavBar() {

    const navigate = useNavigate();

    const onLogout = () => {
        sessionStorage.clear();
        alert("Thank you for Banking with us.")
        navigate("/login")
    }

    return(
        <>
            <Box sx={{ flexGrow: 1 }}>
                    <AppBar position="static">
                        <Toolbar>
                        
                        <Button color="inherit" href="/dashboard">
                            <Typography variant="h6" component="div"  sx={{ flexGrow: 1 }}>
                                Online Banking App
                            </Typography>
                        </Button>
                        <Button color="inherit" href="/changepassword">Change password</Button>
                        <Button color="inherit" onClick={onLogout}>Logout</Button>
                        </Toolbar>
                    </AppBar>
            </Box>
        </>
    )
}