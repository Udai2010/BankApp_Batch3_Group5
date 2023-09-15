import React from 'react';
import { Button } from '@mui/material';
import { Link } from 'react-router-dom';
export default function AccountPage() {
    return(
        <>
                <h2>Welcome to Account Page</h2> 
                <label>Create New Account</label>
                <Button 
                        variant="contained" component={Link} to="/openaccount"
                        sx={{ mt: 3, mb: 2 }}>Account</Button>   
        </>
    )
}