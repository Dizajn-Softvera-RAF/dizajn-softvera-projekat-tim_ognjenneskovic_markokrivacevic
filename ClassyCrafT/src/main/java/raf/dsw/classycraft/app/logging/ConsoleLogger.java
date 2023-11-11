package raf.dsw.classycraft.app.logging;

import raf.dsw.classycraft.app.messageGenerator.Message;
import raf.dsw.classycraft.app.messageGenerator.MessageType;

import java.sql.Timestamp;

public class ConsoleLogger implements ILogger
{
    @Override
    public void logMessage(Message msg)
    {
        System.out.printf("[%s][%s] %s%n",msgTypeStrMap.get(msg.getType()),
                msg.getTime().toString(),msg.getMsg());
    }
}
