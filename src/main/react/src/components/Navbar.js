import React from 'react';
import { AppBar, Toolbar, Typography, Button, Link } from '@mui/material';
import { Link as RouterLink } from 'react-router-dom';

const Navbar = () => {
    return (
        <AppBar position="static">
            <Toolbar>
                <Typography variant="h6" style={{ flexGrow: 1 }}>
                    What's Happened
                </Typography>
                <Button color="inherit" component={RouterLink} to="/">
                    Инциденты
                </Button>
                <Button color="inherit" component={RouterLink} to="/add">
                    Добавить инцидент
                </Button>
            </Toolbar>
        </AppBar>
    );
};

export default Navbar;