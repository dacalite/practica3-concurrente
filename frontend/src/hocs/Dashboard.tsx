import Magician from '@/components/Magician';
import MagicianConfigurer from '@/components/MagicianConfigurer';
import Spells from '@/components/Spells';
import { MagicianType } from '@/lib/types';
import axios from 'axios';
import { SetStateAction, useEffect, useRef, useState } from 'react';

type AuditLogLevel = 'INFO' | 'ERROR';

interface AuditLog {
  id: number;
  message: string;
  level: AuditLogLevel;
  timestamp: string;
}

function Dashboard() {
  const [magicianType, setMagicianType] = useState<MagicianType | undefined>();
  const [magicianName, setMagicianName] = useState('Mago');
  const [magicianLevel, setMagicianLevel] = useState(0);
  const [auditLogs, setAuditLogs] = useState<AuditLog[]>([]);
  
  // Create a ref for the logs container
  const logsContainerRef = useRef<HTMLDivElement>(null);

  const handleNameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    !magicianType && setMagicianName(event.target.value);
  };

  const evokeSpell = async (spell: string) => {
    const token = localStorage.getItem('jwt_token');
    try {
      // 1. POST request to invoke spell
      await axios.post(
        'http://localhost:8080/public/api/v1/magicians/evoke',
        { magician: magicianName, spell },
        { headers: { Authorization: `Bearer ${token}` } }
      );
    } catch (error) {
      console.error('Error invoking spell:', error);
    } finally {
      // 2. GET request to update magician info
      const magicianResponse = await axios.get(
        'http://localhost:8080/public/api/v1/magicians',
        { headers: { Authorization: `Bearer ${token}` } }
      );
      const magicianData = magicianResponse.data.find(
        (m: any) => m.name === magicianName
      );
      if (magicianData && magicianData.level !== magicianLevel) {
        setMagicianLevel(magicianData.level);
      }

      // 3. GET request to fetch audit logs
      const logsResponse = await axios.get(
        'http://localhost:8080/public/api/v1/logs',
        {
          headers: { Authorization: `Bearer ${token}` },
        }
      );
      setAuditLogs(logsResponse.data);
    }
  };

  const parseLogs = (logs: AuditLog[]) =>
    logs.map((log) => (
      <h1
        key={log.id}
        className={`text-xl w-full ${
          log.level === 'ERROR' ? 'text-red-700' : 'text-black'
        }`}
      >
        {`>> [${new Date(log.timestamp).toLocaleString()}] ${log.message}`}
      </h1>
    ));

  const configureMagician = async (
    type: SetStateAction<MagicianType | undefined>
  ) => {
    setMagicianType(type);

    const token = localStorage.getItem('jwt_token');
    try {
      // POST request to create the magician
      await axios.post(
        'http://localhost:8080/public/api/v1/magicians',
        {
          name: magicianName,
          type,
          experience: 0,
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
    } catch (error) {
      console.error('Error configuring magician:', error);
    }
  };

  // Effect to scroll to the bottom of the logs container whenever logs update
  useEffect(() => {
    if (logsContainerRef.current) {
      logsContainerRef.current.scrollTop = logsContainerRef.current.scrollHeight;
    }
  }, [auditLogs]);

  return (
    <div className='w-full h-full grid grid-cols-3 grid-rows-1 gap-4 pt-28 pr-24'>
      <div className='w-full h-full flex flex-col items-center gap-6 col-span-2'>
        <input
          type='text'
          value={magicianName}
          onChange={handleNameChange}
          className='text-2xl text-center border-b-2 border-gray-300 focus:border-blue-500 outline-none'
          placeholder='Enter Magician Name'
        />
        <div className='w-full h-full flex flex-col items-center justify-center'>
          {magicianType ? (
            <Magician type={magicianType} level={magicianLevel} />
          ) : (
            <MagicianConfigurer setType={configureMagician} />
          )}
          <Spells evokeSpell={evokeSpell} />
        </div>
      </div>
      <div className='w-full h-full flex flex-col items-center gap-6'>
        <h1 className='text-2xl w-full text-center'>Logs de Auditoría</h1>
        <div
          ref={logsContainerRef} // Attach ref to the logs container
          className='w-full h-full overflow-y-auto my-14'
        >
          {parseLogs(auditLogs)}
        </div>
      </div>
    </div>
  );
}

export default Dashboard;
