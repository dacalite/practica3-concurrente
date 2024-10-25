import { useState, useEffect } from 'react'
import { BrowserRouter as Router } from 'react-router-dom'
import AppRoutes from './hocs/AppRoutes'
import { checkToken, isTokenExpired } from './lib/auth'

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(false)

  // Verifica si hay una sesión guardada en el localStorage
  useEffect(() => {
    const storedToken = localStorage.getItem('jwt_token')
    if (storedToken) {
      if (isTokenExpired(storedToken)) {
        logoutUser()
      } else {
        checkToken(storedToken).then((isCorrectToken) =>
          setIsAuthenticated(isCorrectToken)
        )
      }
    }
  }, [])

  const logoutUser = () => {
    setIsAuthenticated(false)
    localStorage.removeItem('jwt_token') // Eliminar token del localStorage
  }

  const authSession = (token: string) => {
    setIsAuthenticated(true)
    localStorage.setItem('jwt_token', token) // Guardar token en localStorage
  }

  return (
    <Router>
      <AppRoutes
        isAuthenticated={isAuthenticated} //Para gestionar las rutas protegidas
        authSession={authSession} //Para llamar desde la ruta de login
        logoutUser={logoutUser} //Para llamar desde cualquier ruta
      />
    </Router>
  )
}

export default App
