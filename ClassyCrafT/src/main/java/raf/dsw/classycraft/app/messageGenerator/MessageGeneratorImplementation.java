package raf.dsw.classycraft.app.messageGenerator;

import raf.dsw.classycraft.app.observer.Publisher;
import raf.dsw.classycraft.app.observer.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class MessageGeneratorImplementation implements Publisher
{

    private final List<Subscriber> subscribers;
    public MessageGeneratorImplementation()
    {
        subscribers = new ArrayList<>();
    }
    @Override
    public void addSubscriber(Subscriber sub) {
        subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(Subscriber sub) {
        subscribers.remove(sub);
    }

    @Override
    public void notifySubscriber(Object notification) {
        for(var sub: subscribers)
            sub.update(notification);
    }
}
