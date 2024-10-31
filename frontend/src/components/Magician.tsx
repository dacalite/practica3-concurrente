import { MagicianType } from '@/lib/types'
import frameImg from './assets/Mago.webp'
import DarknessIcon from './assets/DarknessIcon'
import FireIcon from './assets/FireIcon'
import WaterIcon from './assets/WaterIcon'
import WindIcon from './assets/WindIcon'

const iconMap = {
  [MagicianType.FIRE]: <FireIcon />,
  [MagicianType.WATER]: <WaterIcon />,
  [MagicianType.WIND]: <WindIcon />,
  [MagicianType.DARKNESS]: <DarknessIcon />,
}

function Magician({ type, level }: { type: MagicianType; level: number }) {
  return (
    <div className='h-full flex items-center justify-center relative'>
      <img src={frameImg} alt='frame' />
      <div className='absolute top-10 right-3'>{iconMap[type]}</div>
      <h1 className='absolute text-2xl top-1/4 left-10 font-bold'>
        Lvl. {level}
      </h1>
    </div>
  )
}

export default Magician
