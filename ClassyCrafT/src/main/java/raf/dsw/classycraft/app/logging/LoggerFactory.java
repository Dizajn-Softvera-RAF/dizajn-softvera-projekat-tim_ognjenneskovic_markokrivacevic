package raf.dsw.classycraft.app.logging;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LoggerFactory
{
    public Logger getLogger(LoggerType type) throws IOException {
        if(type == LoggerType.FileLogger)
            return new FileLogger();
        else
            return new ConsoleLogger();
    }
}
