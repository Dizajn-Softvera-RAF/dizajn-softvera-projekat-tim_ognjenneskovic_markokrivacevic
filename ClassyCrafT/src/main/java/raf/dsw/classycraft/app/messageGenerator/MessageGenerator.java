package raf.dsw.classycraft.app.messageGenerator;

import raf.dsw.classycraft.app.logging.ILogger;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MessageGenerator
{
    private final List<ILogger> listeners;
    public MessageGenerator()
    {
        listeners = new ArrayList<>();
    }
    public void addListener(ILogger logger)
    {
        listeners.add(logger);
    }
    public void generateMessage(Message msg)
    {
        for(var logger: listeners)
            logger.logMessage(msg );
    }
}
