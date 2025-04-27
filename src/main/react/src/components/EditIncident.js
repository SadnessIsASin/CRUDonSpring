import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { TextField, Button, Container, Typography, Select, MenuItem, FormControl, InputLabel } from '@mui/material';
import { useParams, useNavigate } from 'react-router-dom';
import {updateIncident} from "../services/api";

const EditIncident = () => {
    const { id } = useParams(); // Получаем ID инцидента из URL
    const [name, setName] = useState(''); // Название инцидента
    const [description, setDescription] = useState(''); // Описание инцидента
    const [status, setStatus] = useState('Открыт'); // Статус инцидента (значение по умолчанию: "Open")
    const navigate = useNavigate();

    useEffect(() => {
        fetchIncident();
    }, []);

    // Загрузка данных инцидента по ID
    const fetchIncident = async () => {
        try {
            const response = await axios.get(`/get-incident/${id}`); // GET-запрос для получения данных
            const incident = response.data;

            // Устанавливаем значения полей
            setName(incident.name);
            setDescription(incident.description);

            // Проверяем, что статус существует и соответствует допустимым значениям
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

    // Обновление данных инцидента
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await updateIncident(id, name, description, status);// PUT-запрос для обновления данных
            navigate('/'); // Перенаправляем пользователя на главную страницу
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
                {/* Поле для названия */}
                <TextField
                    label="Название"
                    fullWidth
                    margin="normal"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    required
                />

                {/* Поле для описания */}
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

                {/* Выпадающий список для статуса */}
                <FormControl fullWidth margin="normal" required>
                    <InputLabel>Статус</InputLabel>
                    <Select
                        value={status} // Текущее значение статуса
                        label="Статус"
                        onChange={(e) => setStatus(e.target.value)} // Обновление значения статуса
                    >
                        <MenuItem value="Открыт">Открыт</MenuItem>
                        <MenuItem value="В работе">В работе</MenuItem>
                        <MenuItem value="Закрыт">Закрыт</MenuItem>
                    </Select>
                </FormControl>

                {/* Кнопка отправки формы */}
                <Button type="submit" variant="contained" color="primary">
                    Обновить
                </Button>
            </form>
        </Container>
    );
};

export default EditIncident;