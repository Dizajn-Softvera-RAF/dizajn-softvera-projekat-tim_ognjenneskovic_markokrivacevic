package raf.dsw.classycraft.app.messageGenerator;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class Message
{
    private final String msg;
    private final MessageType type;
    private final Timestamp time;

    public Message(String msg, MessageType type, Timestamp time)
    {
        this.msg = msg;
        this.type = type;
        this.time = time;
    }
}
