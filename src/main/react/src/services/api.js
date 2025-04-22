import axios from 'axios';
const API_BASE_URL = process.env.REACT_APP_API_URL || 'http://localhost:8181/api'; // Значение по умолчанию для локальной разработки

export const fetchIncidents = async () => {
    console.log('API_BASE_URL:', process.env.REACT_APP_API_URL);
    try {
        const response = await axios.get(`${API_BASE_URL}/get-all-incidents`);
        return response.data;
    } catch (error) {
        console.error('Ошибка при получении инцидентов:', error);
        throw error;
    }
};

export const deleteIncident = async (id) => {
    try {
        await axios.delete(`${API_BASE_URL}/delete-incident/${id}`);
    } catch (error) {
        console.error('Ошибка при удалении инцидента:', error);
        throw error;
    }
};

export const createIncident = async (incidentData) => {
    try {
        const response = await axios.post(`${API_BASE_URL}/post-incident`, incidentData);
        return response.data; // Возвращаем данные, если нужно
    } catch (error) {
        console.error('Ошибка при добавлении инцидента:', error.response?.data || error.message);
        throw error;
    }
};