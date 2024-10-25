import AnimatedBackgroundWrapper from '@/components/background/AnimatedBackgroundWrapper'
import AuthPage from '@/pages/AuthPage'
import DashboardPage from '@/pages/DashboardPage'
import { Routes, Route, Navigate } from 'react-router-dom'
import ProtectedRoute from './ProtectedRoute'

interface AppRoutesProps {
  isAuthenticated: boolean
  authSession: (token: string) => void
  logoutUser: () => void
}

export default function AppRoutes({
  isAuthenticated,
  authSession,
  logoutUser,
}: AppRoutesProps) {
  return (
    <Routes>
      <Route
        path='/login'
        element={
          isAuthenticated ? (
            <Navigate to='/dashboard' replace />
          ) : (
            <>
              <AnimatedBackgroundWrapper />
              <AuthPage authSession={authSession} />
            </>
          )
        }
      />
      <Route
        path='/dashboard'
        element={
          <ProtectedRoute
            isAuthenticated={isAuthenticated}
            redirectPath='/login'
          >
            <DashboardPage logoutUser={logoutUser} />
          </ProtectedRoute>
        }
      />
      <Route path='*' element={<Navigate to='/login' replace />} />
    </Routes>
  )
}
