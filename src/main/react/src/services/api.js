import axios from 'axios';

export const fetchIncidents = async () => {
    try {
        const response = await axios.get('http://localhost:8181/api/get-all-incidents');
        return response.data;
    } catch (error) {
        console.error('Ошибка при получении инцидентов:', error);
        throw error;
    }
};

export const deleteIncident = async (id) => {
    try {
        await axios.delete(`http://localhost:8181/api/delete-incident/${id}`);
    } catch (error) {
        console.error('Ошибка при удалении инцидента:', error);
        throw error;
    }
};

export const updateIncident = async (id, name, description, status) => {
    try {
        await axios.patch(`http://localhost:8181/api/update-incident/${id}`, { name, description, status });
    } catch (error) {
        console.error('Ошибка при обновлении инцидента:', error.response?.data || error.message);
    }
};

export const createIncident = async (incidentData) => {
    try {
        const response = await axios.post('http://localhost:8181/api/post-incident', incidentData);
    } catch (error) {
        console.error('Ошибка при добавлении инцидента:', error.response?.data || error.message);
        throw error;
    }
};