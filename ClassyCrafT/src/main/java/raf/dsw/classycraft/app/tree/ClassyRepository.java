package raf.dsw.classycraft.app.tree;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ClassyRepository
{
    private static ClassyRepository instance = null;
    private List<ClassyNode> nodes;
    private ClassyRepository()
    {
        nodes = new ArrayList<>();
    }
    public static ClassyRepository getInstance()
    {
        if(instance == null)
            instance = new ClassyRepository();
        return instance;
    }
    public ClassyNode createNode(NodeType type, String name)
    {
        var tree = ClassyTree.getInstance();
        var node = tree.createNode(type, name);
        nodes.add(node);
        return node;
    }
    public Project createNode(NodeType type, String name, String author, String path)
    {
        var tree = ClassyTree.getInstance();
        var node = tree.createNode(type, name, author, path);
        nodes.add(node);
        return node;
    }
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

}
