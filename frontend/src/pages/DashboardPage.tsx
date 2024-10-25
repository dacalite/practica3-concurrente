import Dashboard from '@/hocs/Dashboard'
import GenericPage from '@/hocs/GenericPage'
import { BasePageProps } from '@/lib/types'

const DashboardPage: React.FC<BasePageProps> = ({ logoutUser }) => {
  return (
    <GenericPage logoutUser={logoutUser}>
      <Dashboard />
    </GenericPage>
  )
}

export default DashboardPage
