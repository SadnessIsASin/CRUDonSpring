import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { TextField, Button, Container, Typography, Select, MenuItem, FormControl, InputLabel } from '@mui/material';
import { useParams, useNavigate } from 'react-router-dom';
import {getIncident, updateIncident} from "../services/api";

const EditIncident = () => {
    const { id } = useParams();
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [status, setStatus] = useState('Открыт');
    const navigate = useNavigate();

    useEffect(() => {
        fetchIncident();
    }, []);

    const fetchIncident = async () => {
        try {
            const response = await getIncident(id);
            const incident = response.data;

            setName(incident.name);
            setDescription(incident.description);

            const validStatuses = ['Открыт', 'В работе', 'Закрыт'];
            if (validStatuses.includes(incident.status)) {
                setStatus(incident.status);
            } else {
                console.warn(`Недопустимое значение статуса: ${incident.status}. Установлено значение по умолчанию: Открыт`);
                setStatus('Открыт');
            }
        } catch (error) {
            console.error('Ошибка при загрузке инцидента:', error.response?.data || error.message);
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await updateIncident(id, name, description, status);
            navigate('/');
        } catch (error) {
            console.error('Ошибка при обновлении инцидента:', error.response?.data || error.message);
        }
    };

    return (
        <Container maxWidth="sm">
            <Typography variant="h4" gutterBottom>
                Редактирование инцидента
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
                        value={status}
                        label="Статус"
                        onChange={(e) => setStatus(e.target.value)}
                    >
                        <MenuItem value="Открыт">Открыт</MenuItem>
                        <MenuItem value="В работе">В работе</MenuItem>
                        <MenuItem value="Закрыт">Закрыт</MenuItem>
                    </Select>
                </FormControl>
                <Button type="submit" variant="contained" color="primary">
                    Обновить
                </Button>
            </form>
        </Container>
    );
};

export default EditIncident;