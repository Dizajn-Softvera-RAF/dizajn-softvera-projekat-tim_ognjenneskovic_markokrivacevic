package raf.dsw.classycraft.app.command;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.composite.ElementPainter;
import raf.dsw.classycraft.app.classyRepository.implementation.Connection;
import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.classyRepository.implementation.Interclass;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.PackageView;
import raf.dsw.classycraft.app.gui.swing.view.ProjectView;

import java.awt.*;

public class NewConnectionCommand extends AbstractCommand{

    private DiagramView diagramView;
    private Diagram currDiagram;
    private ElementPainter thisPainter;
    private Interclass start;
    private Interclass end;

    public NewConnectionCommand(DiagramView diagramView,Diagram diagram,Interclass start,Interclass end){
        this.diagramView = diagramView;
        this.currDiagram = diagram;
        this.start = start;
        this.end = end;
    }

    @Override
    public void doCommand() {
//        if(thisPainter==null){
//            String name = start.getName() + "-" + end.getName();
//            Connection conn = new Connection(currDiagram,Color.BLACK,10,start,end) {
//                @Override
//                public DiagramElement copy() {
//                    return null;
//                }
//            };
//            thisPainter = new ConnectionPainter<>(conn);
//
//        }
    }


    @Override
    public void undoCommand() {
       diagramView.deletePainterForCurrent(thisPainter);
    }
}
