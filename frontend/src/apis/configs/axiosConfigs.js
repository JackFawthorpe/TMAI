
import axios from "axios"

export const api = axios.create({
    baseURL: "/api",
})

// defining a custom error handler for all APIs
const errorHandler = (error) => {
    return Promise.reject(error)
}

// registering the custom error handler to the
// "api" axios instance
api.interceptors.response.use(undefined, (error) => {
    return errorHandler(error)
})