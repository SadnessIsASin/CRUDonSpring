import React, { useEffect, useState } from 'react';
import { Table, TableBody, TableCell, TableHead, TableRow, Paper, Button } from '@mui/material';
import { Link, useNavigate } from 'react-router-dom';
import { fetchIncidents, deleteIncident } from '../services/api';
import * as XLSX from 'xlsx';

const IncidentList = () => {
    const [incidents, setIncidents] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        loadIncidents();
    }, []);

    const loadIncidents = async () => {
        try {
            const data = await fetchIncidents();
            const sortedIncidents = data.sort((a, b) => {
                return new Date(b.createdAt) - new Date(a.createdAt);
            });
            setIncidents(sortedIncidents);
        } catch (error) {
            console.error('Ошибка при загрузке инцидентов:', error);
        }
    };

    const handleDelete = async (id) => {
        try {
            await deleteIncident(id);
            loadIncidents();
        } catch (error) {
            console.error('Ошибка при удалении инцидента:', error);
        }
    };

    const exportToExcel = () => {
        const formattedData = incidents.map((incident) => ({
            ID: incident.id,
            Название: incident.name,
            Описание: incident.description,
            Статус: incident.status,
            Локация: incident.location,
            Тип: incident.type,
            'Дата создания': incident.createdAt
                ? new Date(incident.createdAt).toLocaleString()
                : '-',
        }));

        const worksheet = XLSX.utils.json_to_sheet(formattedData);
        const workbook = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(workbook, worksheet, 'Инциденты');
        XLSX.writeFile(workbook, 'Инциденты.xlsx');
    };

    return (
        <div>
            <h2>Список инцидентов</h2>
            <Button
                variant="contained"
                color="primary"
                onClick={exportToExcel}
                style={{ marginBottom: 20 }}
            >
                Выгрузить в Excel
            </Button>
            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Название</TableCell>
                            <TableCell>Описание</TableCell>
                            <TableCell>Статус</TableCell>
                            <TableCell>Локация</TableCell>
                            <TableCell>Тип</TableCell>
                            <TableCell>Дата создания</TableCell>
                            <TableCell>Действия</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {incidents.map((incident) => (
                            <TableRow key={incident.id}>
                                <TableCell>{incident.id}</TableCell>
                                <TableCell>{incident.name}</TableCell>
                                <TableCell>{incident.description}</TableCell>
                                <TableCell>{incident.status}</TableCell>
                                <TableCell>{incident.location}</TableCell>
                                <TableCell>{incident.type}</TableCell>
                                <TableCell>
                                    {new Date(incident.createdAt).toLocaleString()}
                                </TableCell>
                                <TableCell>
                                    <Button
                                        variant="contained"
                                        color="primary"
                                        component={Link}
                                        to={`/edit/${incident.id}`}
                                        style={{ marginRight: 8 }}
                                    >
                                        Изменить
                                    </Button>
                                    <Button
                                        variant="contained"
                                        color="secondary"
                                        onClick={() => handleDelete(incident.id)}
                                    >
                                        Удалить
                                    </Button>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );
};

export default IncidentList;