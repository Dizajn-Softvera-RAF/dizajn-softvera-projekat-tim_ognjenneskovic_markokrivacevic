package raf.dsw.classycraft.app.logging;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MessageGenerator
{
    private List<Logger> listeners;
    public MessageGenerator()
    {
        listeners = new ArrayList<>();
    }
    public void addListener(Logger logger)
    {
        listeners.add(logger);
    }
    public void generateMessage(String msg, MessageType type, Timestamp time)
    {
        for(var logger: listeners)
            logger.logMessage(msg,type,time);
    }
}
