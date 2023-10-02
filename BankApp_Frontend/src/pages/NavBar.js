import React, { useState } from "react";
import { Box, AppBar, Toolbar, Typography, Button } from "@mui/material";
import { useNavigate } from "react-router-dom";
import Snackbar from "@mui/material/Snackbar";
import MuiAlert from "@mui/material/Alert";

export default function NavBar({ userType }) {

    const navigate = useNavigate();
    const [openExitSnackbar, setOpenExitSnackbar] = useState(false);
    const [alertMessage, setAlertMessage] = useState("");

    const handleCloseExitSnackbar = (event, reason) => {
        if (reason === 'clickaway') {
            return;
        }
        setOpenExitSnackbar(false);
        navigate("/");
    }

    const onLogout = () => {
        console.log(userType);
        sessionStorage.clear();
        setAlertMessage("Thank you for Banking with us.")
        setOpenExitSnackbar(true);
    }

    return (
        <>
            <Box sx={{ flexGrow: 1 }}>
                <AppBar position="static">
                    <Toolbar sx={{ justifyContent: 'space-between' }}>
                        {
                            userType === "admin" ?
                                <>
                                    <Button color="inherit" href="/admindashboard">
                                        <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                                            Liberty Bank
                                        </Typography>
                                    </Button>
                                    <Box sx={{ display: 'flex' }}>
                                        <Button align='right' color="inherit" onClick={onLogout}>Logout</Button>
                                    </Box>
                                </>
                                :
                                <>
                                    <Button color="inherit" href="/dashboard">
                                        <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                                            Liberty Bank
                                        </Typography>
                                    </Button>
                                    <Box sx={{ display: 'flex' }}>
                                        <Button align='right' color="inherit" href="/changepassword">Change password</Button>
                                        <Button align='right' color="inherit" onClick={onLogout}>Logout</Button>
                                    </Box>
                                </>

                        }
                    </Toolbar>
                </AppBar>
            </Box>
            <Snackbar
                open={openExitSnackbar}
                autoHideDuration={6000}
                onClose={handleCloseExitSnackbar}
                anchorOrigin={{ vertical: 'top', horizontal: 'center' }}
            >
                <MuiAlert
                    elevation={6}
                    variant="filled"
                    severity="success"
                    onClose={handleCloseExitSnackbar}
                >
                    {alertMessage}
                </MuiAlert>
            </Snackbar>
        </>
    )
}