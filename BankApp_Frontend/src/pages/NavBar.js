import React from "react";
import { Box, AppBar, Toolbar, Typography, Button } from "@mui/material";
import { useNavigate } from "react-router-dom";

export default function NavBar({userType}) {

    const navigate = useNavigate();

    const onLogout = () => {
        console.log(userType);
        sessionStorage.clear();
        alert("Thank you for Banking with us.")
        navigate("/login")
    }

    return(
        <>
            <Box sx={{ flexGrow: 1 }}>
                    <AppBar position="static">
                        <Toolbar sx={{justifyContent:'space-between'}}>
                        {   
                            userType === "admin" ? 
                             <>
                             <Button color="inherit" href="/admindashboard">
                                 <Typography variant="h6" component="div"  sx={{ flexGrow: 1 }}>
                                     Online Banking App
                                 </Typography>
                             </Button>
                             <Box sx={{display:'flex'}}>
                                 <Button align ='right' color="inherit" href="/changepassword">Change password</Button>
                                 <Button align='right'  color="inherit" onClick={onLogout}>Logout</Button>
                             </Box>
                         </>
                        :
                        <>
                        <Button color="inherit" href="/dashboard">
                            <Typography variant="h6" component="div"  sx={{ flexGrow: 1 }}>
                                Online Banking App
                            </Typography>
                        </Button>
                        <Box sx={{display:'flex'}}>
                            <Button align ='right' color="inherit" href="/changepassword">Change password</Button>
                            <Button align='right'  color="inherit" onClick={onLogout}>Logout</Button>
                        </Box>
                    </>
                           
                        }
                        </Toolbar>
                    </AppBar>
            </Box>
        </>
    )
}