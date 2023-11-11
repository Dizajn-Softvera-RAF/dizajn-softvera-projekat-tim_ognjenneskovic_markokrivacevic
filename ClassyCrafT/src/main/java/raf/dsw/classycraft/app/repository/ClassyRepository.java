package raf.dsw.classycraft.app.repository;

import com.sun.tools.javac.Main;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.repository.composite.IClassyNodeListener;
import raf.dsw.classycraft.app.repository.composite.NodeType;
import raf.dsw.classycraft.app.tree.ClassyTree;
import raf.dsw.classycraft.app.repository.implementation.Diagram;
import raf.dsw.classycraft.app.repository.implementation.Project;
import raf.dsw.classycraft.app.repository.implementation.Package;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ClassyRepository
{
    private static ClassyRepository instance = null;
    private List<ClassyNode> nodes;
    private EnumMap<NodeType, ArrayList<IClassyNodeListener>> nodeTypeListeners;
    private ClassyRepository()
    {
        nodes = new ArrayList<>();
        nodeTypeListeners = new EnumMap<>(Map.of(
                NodeType.PACKAGE, new ArrayList<>(),
                NodeType.DIAGRAM, new ArrayList<>(),
                NodeType.PROJECT, new ArrayList<>()
        ));
    }
    public static ClassyRepository getInstance()
    {
        if(instance == null)
            instance = new ClassyRepository();
        return instance;
    }
    public ClassyNode createNode(NodeType type, String name, ClassyNodeComposite parent)
    {
        var tree = MainFrame.getInstance().getClassyTree();
        var node = tree.createNode(type, name, parent);
        nodes.add(node);
        for (var listener: nodeTypeListeners.get(type))
            node.addOnChangeListener(listener);
        return node;
    }
    public Project createNode(NodeType type, String name, String author, String path, ClassyNodeComposite parent)
    {
        var tree = MainFrame.getInstance().getClassyTree();
        var node = tree.createNode(type, name, author, path, parent);
        nodes.add(node);
        for (var listener: nodeTypeListeners.get(type))
            node.addOnChangeListener(listener);
        return node;
    }
    /**
     * Returns all nodes of given type
     */
    public List<ClassyNode> getNodes(NodeType type)
    {
        var nodeTypeClassMap = new EnumMap<>(Map.of(
                NodeType.PACKAGE, Package.class,
                NodeType.DIAGRAM, Diagram.class,
                NodeType.PROJECT, Project.class
        ));
        var list = new ArrayList<ClassyNode>();
        for (var node : nodes)
            if(node.getClass() == nodeTypeClassMap.get(type))
                list.add(node);

        return list;
    }

    /**
     * Adds listener to all nodes of given type
     */
    public void addOnChangeListener(IClassyNodeListener listener, NodeType type)
    {
        for(var node: getNodes(type))
            node.addOnChangeListener(listener);
        nodeTypeListeners.get(type).add(listener);
    }
}