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
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;

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

    public void addChild(ClassyTreeItem parent)
    {
        var tree = MainFrame.getInstance().getClassyTree();
        var node = tree.createNode(parent.getNode());
        nodes.add(node);
        tree.addChild(parent, node);
    }
}
