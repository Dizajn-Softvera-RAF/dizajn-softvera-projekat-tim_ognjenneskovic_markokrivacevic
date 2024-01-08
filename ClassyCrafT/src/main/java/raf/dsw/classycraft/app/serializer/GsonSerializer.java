package raf.dsw.classycraft.app.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.composite.ElementPainter;
import raf.dsw.classycraft.app.classyRepository.implementation.Connection;
import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.classyRepository.implementation.Interclass;
import raf.dsw.classycraft.app.classyRepository.implementation.Project;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.ConnectionPainter;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.ProjectView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GsonSerializer implements Serializer
{

    private final Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(ClassyNode.class, new ProjectSerializer()).create();

    @Override
    public Project loadProject(File file) {
        try {
            var jsonReader = new JsonReader(new FileReader(file));
            return gson.fromJson(jsonReader, Project.class);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveProject(Project project) {
        try (FileWriter writer = new FileWriter(project.getFilePath())) {
            gson.toJson(project, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadTemplate(File file) {
//        ProjectView projectView = MainFrame.getInstance().getDesktopPanel();
//
//        try (FileReader fileReader = new FileReader(file)){
//            Diagram diagram = gson.fromJson(fileReader, Diagram.class);
//            Project project = projectView.getSelectedProject();
//            if(project==null)
//                return;
//            diagram.setParent(project);
//            projectView.update(project);
//            ClassyTreeImplementation cti = (ClassyTreeImplementation) MainFrame.getInstance().getClassyTree();
//            cti.addToTree(project,diagram);
//            for (ClassyNode element : diagram.getChildren())
//                element.setParent(diagram);
//
//            repaintDiagram(projectView,diagram);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
//    private void repaintDiagram(DiagramView diagramView,Diagram diagram){
//        List<DiagramElement> list = new ArrayList<>();
//        for (ClassyNode element : diagram.getChildren()){
//            ElementPainter painter;
//            if(element instanceof Connection){
//                list.add((DiagramElement) element);
//                painter = new ConnectionPainter<>((Connection) element,((Connection) element).getX(), ((Connection) element).getY(), ((Connection) element).getEndX(), ((Connection) element).getEndY());
//            }else {
//                Interclass interclass = (Interclass) element;
//                painter = new InterClassPainter((DiagramElement) element,((Interclass) element).getX(),((Interclass) element).getY());
//            }
//            diagramView.getPainters().add(painter);
//        }
//        diagramView.repaintAll();
//
//        for(DiagramElement e : list){
//
//        }
//    }
//
//    private void redetermineTopicForAssoc(List<ElementPainter> painters,DiagramElement element){
//        Connection c = (Connection) element;
//        for(ElementPainter painter : painters){
//            if(!(painter instanceof InterClassPainter))
//                return;
//            InterClassPainter icp = (InterClassPainter) painter;
//            Interclass ic = (Interclass) icp.getElement();
//            if(icp.getX()!=0 && icp.getY()!=0){
//                c.setNodeFrom(ic);
//            }
//            if(icp.g)
//        }
//    }

    @Override
    public void saveTemplate(Diagram diagram, String templateName) {
        diagram.setTemplate(true);
        diagram.setName(templateName);
        try {
            String userHome = System.getProperty("user.home");
            String filePath = userHome + "/Desktop/" + templateName + ".json";
            FileWriter fw = new FileWriter(filePath, false);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(gson.toJson(diagram));
            pw.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
