import axios from 'axios';

export const fetchIncidents = async () => {
    try {
        const response = await axios.get('http://localhost:8181/api/incidents/all');
        return response.data;
    } catch (error) {
        console.error('Ошибка при получении инцидентов:', error);
        throw error;
    }
};

export const getIncident = async (id) => {
    try {
        const response = await axios.get(`http://localhost:8181/api/incidents/${id}`);
        return response.data;
    } catch (error) {
        console.error('Ошибка при получении инцидентов:', error);
        throw error;
    }
}

export const deleteIncident = async (id) => {
    try {
        await axios.delete(`http://localhost:8181/api/incidents/delete/${id}`);
    } catch (error) {
        console.error('Ошибка при удалении инцидента:', error);
        throw error;
    }
};

export const updateIncident = async (id, name, description, status) => {
    try {
        await axios.patch(`http://localhost:8181/api/incidents/update/${id}`, { name, description, status });
    } catch (error) {
        console.error('Ошибка при обновлении инцидента:', error.response?.data || error.message);
    }
};

export const createIncident = async (incidentData) => {
    try {
        const response = await axios.post('http://localhost:8181/api/incidents/add', incidentData);
    } catch (error) {
        console.error('Ошибка при добавлении инцидента:', error.response?.data || error.message);
        throw error;
    }
};