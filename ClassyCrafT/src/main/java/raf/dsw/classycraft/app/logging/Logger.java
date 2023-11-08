package raf.dsw.classycraft.app.logging;

import java.sql.Timestamp;
import java.util.EnumMap;
import java.util.Map;

public interface Logger
{
    static final EnumMap<MessageType,String> msgTypeStrMap = new EnumMap<>(Map.of(
            MessageType.INFO, "INFO",
            MessageType.ERROR, "ERROR",
            MessageType.WARNING, "WARNING"
    ));
    public abstract void logMessage(String msg, MessageType type, Timestamp time);
}
