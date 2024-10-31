export interface AuthResponse {
  status: boolean
  info: string
  token?: string
}

export interface BasePageProps {
  logoutUser: () => void
}

export enum MagicianType {
  FIRE = 'FIRE',
  WATER = 'WATER',
  WIND = 'WIND',
  DARKNESS = 'DARKNESS',
}
