import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { ThemeProvider, createTheme } from '@mui/material/styles';
import Navbar from './components/Navbar';
import IncidentList from './components/IncidentList';
import AddIncident from './components/AddIncident';
import EditIncident from './components/EditIncident';

const theme = createTheme({
    palette: {
        primary: {
            main: '#7ab9f4',
        },
        secondary: {
            main: '#f27fa8',
        },
    },
});

function App() {
    return (
        <ThemeProvider theme={theme}>
            <Router>
                <Navbar />
                <Routes>
                    <Route path="/" element={<IncidentList />} />
                    <Route path="/add" element={<AddIncident />} />
                    <Route path="/edit/:id" element={<EditIncident />} />
                </Routes>
            </Router>
        </ThemeProvider>
    );
}

export default App;