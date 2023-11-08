package raf.dsw.classycraft.app.logging;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Timestamp;

public class FileLogger implements Logger
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
    public void logMessage(String msg, MessageType type, Timestamp time)
    {
        var logPath = getClass().getResource("/log.txt");
        try {
            var fileWriter = new FileWriter(logPath.getFile(),true);
            var formattedMsg = String.format("[%s][%s] %s \n",msgTypeStrMap.get(type),time.toString(), msg);
            fileWriter.write(formattedMsg);
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Failed to write to log.txt file");
        }
    }
}
