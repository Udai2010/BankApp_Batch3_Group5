import React from "react";
import NavBar from "./NavBar";
import { Typography } from "@mui/material";

export default function ErrorPage() {
    return(
        <>
            <NavBar/>
            <Typography component="h1" variant="h5" align='center' sx={{color: 'steelblue', fontSize: '20px', fontWeight: 'bold'}}>
                          ERROR 404...PLEASE RETURN TO USER DASHBOARD
                      </Typography>
        </>
    )
}