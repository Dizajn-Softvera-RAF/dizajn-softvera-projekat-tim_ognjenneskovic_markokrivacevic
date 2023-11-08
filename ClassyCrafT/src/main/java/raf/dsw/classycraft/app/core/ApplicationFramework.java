package raf.dsw.classycraft.app.core;

import com.sun.tools.javac.Main;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.logging.ConsoleLogger;
import raf.dsw.classycraft.app.logging.FileLogger;
import raf.dsw.classycraft.app.logging.MessageGenerator;
import raf.dsw.classycraft.app.logging.MessageType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;

public class ApplicationFramework {

    private static ApplicationFramework instance;
    public MessageGenerator testGenerator;

    //buduca polja za model celog projekta

    private ApplicationFramework(){

    }

    public void initialize(){
        var mainFrame = MainFrame.getInstance();
        mainFrame.setVisible(true);
        testGenerator = new MessageGenerator();
        testGenerator.addListener(new ConsoleLogger());
        try {
            var fileLogger = new FileLogger();
            testGenerator.addListener(fileLogger);
        } catch (Exception e) {
            var currentTime = new Timestamp(System.currentTimeMillis());
            testGenerator.generateMessage("Failed to add file logger", MessageType.ERROR, currentTime);
        }
        testGenerator.addListener(mainFrame);
    }

    public static ApplicationFramework getInstance(){
        if(instance==null){
            instance = new ApplicationFramework();
        }
        return instance;
    }
}
