import React from 'react'
import { Link } from 'react-router-dom'
import { Button } from '@mui/material'
export default function HomePage() {
    return (
        <>
            <div>HomePage</div>
            <Button
                    type="submit"
                    variant="contained" 
                    component={Link} to="/register"
                    sx={{ mt: 3, mb: 2 }}
                  >
                    Register
            </Button>
            <Button
                    type="submit"
                    variant="contained"
                    component={Link} to="/login"
                    sx={{ mt: 3, mb: 2 }}
                  >
                    Login
            </Button>
        </>
    )
}