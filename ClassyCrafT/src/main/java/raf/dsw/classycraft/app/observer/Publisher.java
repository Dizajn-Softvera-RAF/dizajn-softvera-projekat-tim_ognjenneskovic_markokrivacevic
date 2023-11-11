package raf.dsw.classycraft.app.observer;

public interface Publisher {

    void addSuscriber(Subscriber sub);
    void removeSubscriber(Subscriber sub);
    void notifySubscriber(Object notification);

}
