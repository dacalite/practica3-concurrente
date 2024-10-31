import React from 'react'

enum FireSpells {
  LEVEL_1 = 'Incendio',
  LEVEL_2 = 'Inflamari',
  LEVEL_3 = 'Confringo',
  LEVEL_4 = 'Fiendfyre',
}

enum WaterSpells {
  LEVEL_1 = 'Aguamenti',
  LEVEL_2 = 'Glacius',
  LEVEL_3 = 'Aquarium',
  LEVEL_4 = 'Tsunamis',
}

enum WindSpells {
  LEVEL_1 = 'Ventus',
  LEVEL_2 = 'Depulso',
  LEVEL_3 = 'Aero',
  LEVEL_4 = 'Tempestus',
}

enum DarknessSpells {
  LEVEL_1 = 'Nox',
  LEVEL_2 = 'Obscuro',
  LEVEL_3 = 'Morsmordre',
  LEVEL_4 = 'Nocturnis',
}

const SpellTypes = {
  FUEGO: FireSpells,
  AGUA: WaterSpells,
  VIENTO: WindSpells,
  OSCURIDAD: DarknessSpells,
} as const

interface SpellsProps {
  evokeSpell: (spell: string) => void
}

function Spells({ evokeSpell }: SpellsProps) {
  const spellLevels = ['LEVEL_1', 'LEVEL_2', 'LEVEL_3', 'LEVEL_4'] as const
  const levelLabels = ['Lvl.1', 'Lvl.2', 'Lvl.3', 'Lvl.4']

  return (
    <div className='w-full h-full p-20'>
      <table className='min-w-full bg-white border border-gray-200'>
        <thead>
          <tr>
            <th className='py-2 px-4 border-b border-gray-200 text-center bg-gray-100 font-bold'>
              Level
            </th>
            {Object.keys(SpellTypes).map((spellType) => (
              <th
                key={spellType}
                className={`py-2 px-4 border-b border-gray-200 text-center bg-gray-100 font-bold ${
                  spellType === 'FUEGO'
                    ? 'text-red-600'
                    : spellType === 'AGUA'
                    ? 'text-blue-600'
                    : spellType === 'OSCURIDAD'
                    ? 'text-gray-300'
                    : ''
                }`}
              >
                {spellType}
              </th>
            ))}
          </tr>
        </thead>
        <tbody>
          {spellLevels.map((level, index) => (
            <tr key={level}>
              <td className='py-2 px-4 border-b border-gray-200 text-center bg-gray-50 font-medium'>
                {levelLabels[index]}
              </td>
              {Object.entries(SpellTypes).map(([spellType, spells]) => (
                <td
                  key={spells[level]}
                  className={`py-4 px-4 border-b border-gray-200 text-center cursor-pointer hover:bg-gray-200 ${
                    spellType === 'FUEGO'
                      ? 'bg-red-50 text-red-600'
                      : spellType === 'AGUA'
                      ? 'bg-blue-50 text-blue-600'
                      : spellType === 'OSCURIDAD'
                      ? 'bg-gray-800 text-gray-300'
                      : ''
                  }`}
                  onClick={() => evokeSpell(spells[level])}
                >
                  {spells[level]}
                </td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

export default Spells
