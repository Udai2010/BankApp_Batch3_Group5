import React from "react";
import HomeNavbar from "./HomeNavbar";
import { Typography } from "@mui/material";

export default function ErrorPage() {
    return(
        <>
            <HomeNavbar/>
            <Typography component="h1" variant="h5" align='center' sx={{color: 'steelblue', fontSize: '20px', fontWeight: 'bold'}}>
                          ERROR 404...PLEASE RETURN TO USER DASHBOARD
                      </Typography>
        </>
    )
}