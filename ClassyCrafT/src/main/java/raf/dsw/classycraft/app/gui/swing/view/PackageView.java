package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.classyRepository.implementation.Package;
import raf.dsw.classycraft.app.observer.Subscriber;

import javax.swing.*;

@Getter
@Setter
public class PackageView extends JPanel implements Subscriber {

    private Package Package;

    public PackageView(Package aPackage){
        this.Package=aPackage;
    }
    @Override
    public void update(Object notification) {

    }
}
