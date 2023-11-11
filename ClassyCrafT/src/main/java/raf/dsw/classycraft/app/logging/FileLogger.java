package raf.dsw.classycraft.app.logging;

import raf.dsw.classycraft.app.messageGenerator.Message;
import raf.dsw.classycraft.app.messageGenerator.MessageType;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class FileLogger implements ILogger
{
    public FileLogger()  {
        // Clear file contents
        var logPath = getClass().getResource("/log.txt");
        try {
            var fw = new FileWriter(logPath.getFile(), false);
            fw.write("");
            fw.close();
        }
        catch (IOException e)
        {
            System.err.println("Failed to open and clear log.txt file");
        }
    }
    @Override
    public void logMessage(Message msg)
    {
        var logPath = getClass().getResource("/log.txt");
        try {
            var fileWriter = new FileWriter(logPath.getFile(),true);
            var formattedMsg = String.format("[%s][%s] %s \n",msgTypeStrMap.get(msg.getType()),
                    msg.getTime().toString(), msg.getMsg());
            fileWriter.write(formattedMsg);
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Failed to write to log.txt file");
        }
    }
}
