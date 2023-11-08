package raf.dsw.classycraft.app.logging;

import java.sql.Timestamp;
import java.util.EnumMap;
import java.util.Map;

public class ConsoleLogger implements Logger
{
    @Override
    public void logMessage(String msg, MessageType type, Timestamp time)
    {
        System.out.printf("[%s][%s] %s%n",msgTypeStrMap.get(type),time.toString(),msg);
    }
}
