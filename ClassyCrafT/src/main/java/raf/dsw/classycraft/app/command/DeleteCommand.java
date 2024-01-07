package raf.dsw.classycraft.app.command;

import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.composite.ElementPainter;
import raf.dsw.classycraft.app.classyRepository.implementation.Connection;
import raf.dsw.classycraft.app.classyRepository.implementation.Interclass;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.ConnectionPainter;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.PackageView;

import java.util.ArrayList;
import java.util.List;

public class DeleteCommand extends AbstractCommand{

    private DiagramView currdiagramView;
    private PackageView packageView;
    private List<ElementPainter> selected;
    private List<ElementPainter> deleted;

    public DeleteCommand(PackageView packageView,List<ElementPainter> selected) {
        this.packageView = packageView;
        this.currdiagramView = (DiagramView) packageView.getTabbedPane().getSelectedComponent();
        this.selected = selected;
        deleted = new ArrayList<>();
    }
    @Override
    public void doCommand() {
        List<ElementPainter> deletedTopics = new ArrayList<>();
        for(ElementPainter p : selected){
            if(p instanceof InterClassPainter) {
                deleted.add(p);
                deletedTopics.add(p);
            }
        }

        for(ElementPainter p : deletedTopics){
            deleteAssocToTopic(p.getElement());
            currdiagramView.deletePainterForCurrent(p);
        }
    }

    @Override
    public void undoCommand() {
        for(ElementPainter p : deleted){
            if(p instanceof InterClassPainter)
                currdiagramView.addPainterForCurrent(p);
        }

        List<Interclass> topicsOnWorkspcae = new ArrayList<>();
        for(ElementPainter p : currdiagramView.getPainters()){
            if(p instanceof InterClassPainter)
                topicsOnWorkspcae.add((Interclass) p.getElement());
        }

        for(ElementPainter p : deleted){
            if(p instanceof ConnectionPainter) continue;
            Connection a = (Connection) p.getElement();
            if(topicsOnWorkspcae.contains(a.getNodeFrom()) && topicsOnWorkspcae.contains(a.getNodeTo()))
                currdiagramView.addPainterForCurrent(p);
        }
    }

    private void deleteAssocToTopic(DiagramElement element){
        Interclass interclass = (Interclass)element;
        List<ElementPainter> paintersforCurr = currdiagramView.getPainters();
        List<ElementPainter> connectionToDelete = new ArrayList<>();

        for(ElementPainter p : paintersforCurr){
            if(p instanceof ConnectionPainter){
                Connection con = (Connection) p.getElement();
                if(con.getNodeFrom().equals(interclass) || con.getNodeTo().equals(interclass)){
                    deleted.add(p);
                    connectionToDelete.add(p);
                }
            }
        }
        for(ElementPainter p : connectionToDelete){
            currdiagramView.deletePainterForCurrent(p);
        }
    }


}
