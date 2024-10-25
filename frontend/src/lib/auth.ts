import axios from 'axios'
import { AuthResponse } from './types'
import { jwtDecode } from 'jwt-decode'

export function isTokenExpired(token: string): boolean {
  const decoded: any = jwtDecode(token)
  const currentTime = Date.now() / 1000
  return decoded.exp < currentTime
}

export async function checkToken(token: string): Promise<boolean> {
  try {
    const response = await axios.post(
      'http://localhost:8080/public/api/v1/jwt-check',
      {
        token,
      }
    )

    if (response.status === 200) {
      return true
    } else if (response.status === 401) {
      return false
    } else {
      console.error('Error:', response.statusText)
      return false
    }
  } catch (error: any) {
    if (error.status === 401) {
      return false
    } else {
      console.error('Error:', error.response.data)
      return false
    }
  }
}

// Función para autenticar al usuario
export async function authenticateUser(
  email: string,
  password: string
): Promise<AuthResponse> {
  try {
    const response = await axios.post('http://localhost:8080/public/login', {
      email,
      password,
    })

    if (response.status === 200) {
      return { status: true, info: 'Login exitoso', token: response.data }
    } else if (response.status === 401) {
      return { status: false, info: 'Credenciales incorrectas' }
    } else {
      console.error('Error:', response.statusText)
      return { status: false, info: 'Error desconocido' }
    }
  } catch (error: any) {
    if (error.status === 401) {
      return { status: false, info: 'Credenciales incorrectas' }
    } else {
      console.error('Error:', error.response.data)
      return { status: false, info: 'Error desconocido' }
    }
  }
}

// Función para registrar un nuevo usuario
export async function registerUser(
  name: string,
  email: string,
  password: string,
  passwordConfirm: string
): Promise<AuthResponse> {
  try {
    const response = await axios.post('http://localhost:8080/public/register', {
      name,
      email,
      password,
      passwordConfirm,
    })

    if (response.status === 201) {
      return { status: true, info: 'Registro exitoso' }
    } else if (response.status === 401) {
      return { status: false, info: 'Las contraseñas no coinciden' }
    } else if (response.status === 409) {
      return {
        status: false,
        info: 'El email está asociado a un usuario existente',
      }
    } else {
      console.error('Error:', response.statusText)
      return { status: false, info: 'Error desconocido' }
    }
  } catch (error) {
    console.error('Error conectando con el servidor:', error)
    return { status: false, info: 'Error conectando con el servidor' }
  }
}
