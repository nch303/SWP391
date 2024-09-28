import axios from 'axios';

// Base URL for your API
const BASE_URL = 'https://localhost:8080/api';

// Create an axios instance with default config
const api = axios.create({
    baseURL: BASE_URL,
    headers: {
        'Content-Type': 'application/json',
    },
});

// API functions for different endpoints

// Koi related API calls
export const koiAPI = {
    getAllKoi: () => api.get('/koi'),
    getKoiById: (id) => api.get(`/koi/${id}`),
    createKoi: (koiData) => api.post('/koi', koiData),
    updateKoi: (id, koiData) => api.put(`/koi/${id}`, koiData),
    deleteKoi: (id) => api.delete(`/koi/${id}`),
};

// Pond related API calls
export const pondAPI = {
    getAllPonds: () => api.get('/ponds'),
    getPondById: (id) => api.get(`/ponds/${id}`),
    createPond: (pondData) => api.post('/ponds', pondData),
    updatePond: (id, pondData) => api.put(`/ponds/${id}`, pondData),
    deletePond: (id) => api.delete(`/ponds/${id}`),
};

// User related API calls
export const userAPI = {
    getUserProfile: () => api.get('/user/profile'),
    updateUserProfile: (userData) => api.put('/user/profile', userData),
};

// Add more API functions as needed

export default api;