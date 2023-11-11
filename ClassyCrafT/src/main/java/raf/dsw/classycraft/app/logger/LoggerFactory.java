package raf.dsw.classycraft.app.logger;

import raf.dsw.classycraft.app.observer.Subscriber;

public class LoggerFactory
{
    public static Subscriber getLogger(LoggerType loggerType)
    {
        switch (loggerType)
        {
            case FileLogger:
                return new FileLogger();
            case ConsoleLogger:
                return new ConsoleLogger();
            default:
                return null;
        }
    }
}
