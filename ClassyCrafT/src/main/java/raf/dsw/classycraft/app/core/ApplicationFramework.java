package raf.dsw.classycraft.app.core;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.logger.ConsoleLogger;
import raf.dsw.classycraft.app.logger.FileLogger;
import raf.dsw.classycraft.app.messageGenerator.Message;
import raf.dsw.classycraft.app.messageGenerator.MessageGeneratorImplementation;
import raf.dsw.classycraft.app.messageGenerator.MessageType;
import raf.dsw.classycraft.app.observer.Publisher;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApplicationFramework {

    private static ApplicationFramework instance;
    protected Gui gui;
    protected ClassyRepository classyRepository;
    private MessageGeneratorImplementation messageGenerator;

    public void run(){
        this.gui.start();
    }

    private ApplicationFramework(){
    }


    public void initialize(Gui gui,ClassyRepository classyRepository){
          this.gui=gui;
          this.classyRepository = classyRepository;
          this.messageGenerator = new MessageGeneratorImplementation();

          // Add loggers to msg generator
          var consoleLogger = new ConsoleLogger();
          var fileLogger = new FileLogger();
          messageGenerator.addSuscriber(consoleLogger);
          messageGenerator.addSuscriber(fileLogger);
          messageGenerator.addSuscriber(MainFrame.getInstance());

        LocalDateTime currentTime = LocalDateTime.now();
        messageGenerator.notifySubscriber(new Message(MessageType.INFO,"Test 123", currentTime));
    }

    public static ApplicationFramework getInstance(){
        if(instance==null){
            instance = new ApplicationFramework();
        }
        return instance;
    }
}
