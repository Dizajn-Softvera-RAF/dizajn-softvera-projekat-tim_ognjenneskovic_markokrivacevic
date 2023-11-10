package raf.dsw.classycraft.app.logging;

import raf.dsw.classycraft.app.messageGenerator.Message;
import raf.dsw.classycraft.app.messageGenerator.MessageType;

import java.sql.Timestamp;
import java.util.EnumMap;
import java.util.Map;

public interface ILogger
{
    EnumMap<MessageType,String> msgTypeStrMap = new EnumMap<>(Map.of(
            MessageType.INFO, "INFO",
            MessageType.ERROR, "ERROR",
            MessageType.WARNING, "WARNING"
    ));
    void logMessage(Message msg);
}
