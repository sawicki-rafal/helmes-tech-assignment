import axios from 'axios';

const apiBaseUrl = process.env.VUE_APP_REST_API_BASE_URL;

const config = {
    baseURL: apiBaseUrl,
    timeout: 1000,
};

const httpClient = axios.create(config);

export { httpClient };