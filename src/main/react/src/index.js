import React from 'react';
import ReactDOM from 'react-dom/client'; // Импортируйте createRoot из react-dom/client
import { ThemeProvider, createTheme } from '@mui/material/styles';
import App from './App';

const theme = createTheme({
    palette: {
        primary: {
            main: '#1976d2',
        },
        secondary: {
            main: '#dc004e',
        },
    },
});

// Создайте корневой элемент для рендера
const root = ReactDOM.createRoot(document.getElementById('root'));

// Рендер приложения
root.render(
    <ThemeProvider theme={theme}>
        <App />
    </ThemeProvider>
);