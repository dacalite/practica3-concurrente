import { MagicianType } from '@/lib/types'
import { SetStateAction } from 'react'
import frameImg from './assets/MagoConfigurer.webp'
import DarknessIcon from './assets/DarknessIcon'
import FireIcon from './assets/FireIcon'
import WaterIcon from './assets/WaterIcon'
import WindIcon from './assets/WindIcon'

interface MagicianConfigurerProps {
  setType: (value: SetStateAction<MagicianType | undefined>) => void
}

const iconMap = {
  [MagicianType.FIRE]: <FireIcon />,
  [MagicianType.WATER]: <WaterIcon />,
  [MagicianType.WIND]: <WindIcon />,
  [MagicianType.DARKNESS]: <DarknessIcon />,
}

function MagicianConfigurer({ setType }: MagicianConfigurerProps) {
  return (
    <div className='h-full flex items-center justify-center relative'>
      <img src={frameImg} alt='frame configurer' />
      <div className='absolute grid grid-cols-2 grid-rows-2 gap-4 mt-8'>
      {Object.entries(iconMap).map(([type, IconComponent]) => (
          <div
            key={type}
            onClick={() => setType(MagicianType[type as keyof typeof MagicianType])}
            className='p-2 flex items-center justify-center border rounded-md cursor-pointer hover:bg-gray-200'
          >
            {IconComponent}
          </div>
        ))}
      </div>
    </div>
  )
}

export default MagicianConfigurer
