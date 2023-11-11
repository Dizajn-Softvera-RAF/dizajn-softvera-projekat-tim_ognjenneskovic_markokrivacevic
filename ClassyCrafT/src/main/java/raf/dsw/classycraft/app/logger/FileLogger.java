package raf.dsw.classycraft.app.logger;

import raf.dsw.classycraft.app.messageGenerator.Message;
import raf.dsw.classycraft.app.observer.Subscriber;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Subscriber
{
    public FileLogger()  {
        // Clear log file
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
    public void update(Object obj) {
        Message msg = (Message) obj;
        var logPath = getClass().getResource("/log.txt");
        try {
            var fileWriter = new FileWriter(logPath.getFile(),true);
            var formattedMsg = String.format("[%s][%s] %s \n",msg.getTip(),
                    msg.getVreme().toString(), msg.getPoruka());
            fileWriter.write(formattedMsg);
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Failed to write to log.txt file");
        }
    }
}
