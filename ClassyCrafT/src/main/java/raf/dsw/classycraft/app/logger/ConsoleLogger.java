package raf.dsw.classycraft.app.logger;

import raf.dsw.classycraft.app.messageGenerator.Message;
import raf.dsw.classycraft.app.observer.Subscriber;

public class ConsoleLogger implements Subscriber
{
    @Override
    public void update(Object obj) {
        Message msg = (Message) obj;
        System.out.printf("[%s][%s] %s%n",msg.getTip(), msg.getVreme().toString(), msg.getPoruka());
    }
}
