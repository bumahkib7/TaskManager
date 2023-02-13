import {AppBar, IconButton, Toolbar, Tooltip, Typography} from "@mui/material";
import React from "react";


function MenuIcon() {
    return <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="none" stroke="currentColor"
                strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" className="feather feather-menu"
                viewBox="0 0 24 24">
        <path d="M3 12h18M3 6h18M3 18h18"></path>
    </svg>
}

function HomeOutlinedIcon() {
    return <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="none" stroke="currentColor"
                strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" className="feather feather-home"
                viewBox="0 0 24 24">
        <path d="M3 9l9-7 9 7v11a2 2 0 01-2 2H5a2 2 0 01-2-2V9z"></path>
        <path d="M9 22V12h6v10"></path>
    </svg>
}

function AddIcon() {
    return <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="none" stroke="currentColor"
                strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" className="feather feather-plus"
                viewBox="0 0 24 24">
        <path d="M12 5v14M5 12h14"></path>
    </svg>
}

function AccountCircleIcon() {
    return <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="none" stroke="currentColor"
                strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" className="feather feather-user"
                viewBox="0 0 24 24">
        <path d="M12 14a4 4 0 100-8 4 4 0 000 8z"></path>
        <path d="M21 12c0 5.523-4.477 10-10 10S1 17.523 1 12 5.477 2 11 2s10 4.477 10 10z"></path>
    </svg>
}

export const TopBar = ({goHome, newTask, toggleDrawer}) => {
    return <AppBar position='fixed' sx={{
        zIndex: theme =>
            theme.zIndex.drawer + 1
    }}>
        <Toolbar>
            <IconButton size='large' edge='start' color='inherit'
                        aria-label='menu'
                        onClick={toggleDrawer}>
                <MenuIcon/>
            </IconButton>
            <Tooltip title='Home'>
                <IconButton color='inherit' onClick={goHome}>
                    <HomeOutlinedIcon/>
                </IconButton>
            </Tooltip>
            <Typography variant='h6' component='div' sx={{
                flexGrow: 1
            }}>
                Task manager
            </Typography>
            <Tooltip title='Quick Add'>
                <IconButton color='inherit' onClick={newTask}>
                    <AddIcon/>
                </IconButton>
            </Tooltip>
            <AccountCircleIcon/>
        </Toolbar>
    </AppBar>
}

function goHome() {
    window.location.href = '/';
}