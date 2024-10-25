export interface AuthResponse {
  status: boolean
  info: string
  token?: string
}

export interface BasePageProps {
  logoutUser: () => void
}
