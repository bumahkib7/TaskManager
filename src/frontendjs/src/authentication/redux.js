import { createAsyncThunk } from '@reduxjs/toolkit';
import fetch from 'cross-fetch';

require('dotenv').config();

export const login = createAsyncThunk(
    'auth/login',
    async ({name, password}, thunkAPI) => {
        const response = await fetch(`${process.env.REACT_APP_API_URL}/auth/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({name, password}),
        });
        if (!response.ok) {
            return thunkAPI.rejectWithValue({
                status: response.status, statusText: response.statusText, data: response.data
            });
        }
        return response.text();
    }
);