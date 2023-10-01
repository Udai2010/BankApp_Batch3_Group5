import React from "react";
import { Link } from "react-router-dom";
import { Button, Box } from "@mui/material";
export default function HomePage() {
  return (
    <>
      <div className="card center">
        <div className="centerCard">
          <div>
            <p>Welcome To Liberty Bank</p>
          </div>
          <Button
            type="submit"
            variant="contained"
            component={Link}
            to="/register"
            sx={{mt:1, mb:3, mr:5 }}
          >
            Register
          </Button>
          <Button
            type="submit"
            variant="contained"
            component={Link}
            to="/login"
            sx={{mt:1, mb:3, ml:2 }}
          >
            Login
          </Button>
        </div>
      </div>
    </>
  );
}
