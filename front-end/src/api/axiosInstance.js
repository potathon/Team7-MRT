import axios from "axios";

const AXIOS_BASE_URL = process.env.REACT_APP_AXIOS_BASE_URL;

export const axiosInstance = axios.create({
  baseURL: AXIOS_BASE_URL,
  timeout: 5000,
  withCredentials: true,
});
