import React, { useState } from 'react';
import {TextField, Button, Container, Typography, FormControl, InputLabel, Select, MenuItem} from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { createIncident } from '../services/api';

const AddIncident = () => {
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [status, setStatus] = useState('Открыт');
    const [location, setLocation] = useState('');
    const [type, setType] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await createIncident({
                name,
                description,
                status,
                location,
                type,
                createdAt: null,
            });
            navigate('/');
        } catch (error) {
            console.error('Ошибка при добавлении инцидента:', error);
        }
    };

    return (
        <Container maxWidth="sm">
            <Typography variant="h4" gutterBottom>
                Добавить инцидент
            </Typography>
            <form onSubmit={handleSubmit}>
                <TextField
                    label="Название"
                    fullWidth
                    margin="normal"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    required
                />
                <TextField
                    label="Описание"
                    fullWidth
                    multiline
                    rows={4}
                    margin="normal"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                    required
                />
                <FormControl fullWidth margin="normal" required>
                    <InputLabel>Статус</InputLabel>
                    <Select
                        value={status} // Текущее значение статуса
                        label="Статус"
                        onChange={(e) => setStatus(e.target.value)}
                    >
                        <MenuItem value="Открыт">Открыт</MenuItem>
                        <MenuItem value="В работе">В работе</MenuItem>
                        <MenuItem value="Закрыт">Закрыт</MenuItem>
                    </Select>
                </FormControl>
                <TextField
                    label="Локация"
                    fullWidth
                    margin="normal"
                    value={location}
                    onChange={(e) => setLocation(e.target.value)}
                    required
                />
                <TextField
                    label="Тип"
                    fullWidth
                    margin="normal"
                    value={type}
                    onChange={(e) => setType(e.target.value)}
                    required
                />
                <Button type="submit" variant="contained" color="primary">
                    Save
                </Button>
            </form>
        </Container>
    );
};

export default AddIncident;