package raf.dsw.classycraft.app.gui.swing.tree;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.classyRepository.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.gui.swing.factory.FactoryUtils;
import raf.dsw.classycraft.app.gui.swing.factory.NodeFactory;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.util.Enumeration;

@Getter
@Setter
public class ClassyTreeImplementation implements ClassyTree{

    private ClassyTreeView treeView;
    private DefaultTreeModel treeModel;

    @Override
    public ClassyTreeView generateTree(ProjectExplorer projectExplorer) {
        ClassyTreeItem root = new ClassyTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new ClassyTreeView(treeModel);
        // Set root as selected node
        treeView.setSelectionPath(new TreePath(root.getPath()));
        return treeView;
    }
    public ClassyTreeItem getRoot(){
        return (ClassyTreeItem) treeModel.getRoot();
    }

    private ClassyTreeItem findNodeDFS(ClassyNode node, ClassyTreeItem root){
        if(root.getClassyNode().equals(node)){
            return root;
        }
        for(int i = 0; i < root.getChildCount(); i++){
            var child = (ClassyTreeItem) root.getChildAt(i);
            var found = findNodeDFS(node, child);
            if(found != null){
                return found;
            }
        }
        return null;
    }
    public ClassyTreeItem findNode(ClassyNode node){
        Enumeration<TreeNode> element = getRoot().depthFirstEnumeration();
        while (element.hasMoreElements()) {
            ClassyTreeItem currNode = (ClassyTreeItem) element.nextElement();
            if (currNode.getClassyNode().equals(node)) {
                return currNode;
            }
        }
        return null;
    }

    @Override
    public void addChild(ClassyTreeItem parent) {
        if (!(parent.getClassyNode() instanceof ClassyNodeComposite)){
            return;
        }

        ClassyNode child = createChild(parent.getClassyNode());
        var node = new ClassyTreeItem(child);
        parent.add(node);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
        treeView.setSelectionPath(new TreePath(node.getPath()));
        ((ClassyNodeComposite) parent.getClassyNode()).addChild(child);
    }

    @Override
    public void removeChild(ClassyTreeItem child) {
        if(child.getClassyNode().getParent() == null)
        {
            return;
        }
        var parent = child.getParent();

        child.removeFromParent(); // Remove from treeView
        ((ClassyNodeComposite)child.getClassyNode().getParent()).deleteChild(child.getClassyNode()); // Remove from model

        // Set parent as selected node
        treeView.setSelectionPath(new TreePath(parent));
        treeView.expandPath(new TreePath(parent));
        SwingUtilities.updateComponentTreeUI(treeView);

    }

    @Override
    public ClassyTreeItem getSelectedNode() {
        return (ClassyTreeItem) treeView.getLastSelectedPathComponent();
    }

    private ClassyNode createChild(ClassyNode parent){
        NodeFactory nf = new FactoryUtils().getNodeFactory(parent);
        return nf.createNode((ClassyNodeComposite) parent);
    }
    @Override
    public void repaintTree()
    {
        var currentSelection = treeView.getSelectionPath();
        treeModel.reload();
        treeView.setSelectionPath(currentSelection);
    }

    public TreePath findPathToNode(ClassyTreeItem root, ClassyNode currMapNode) {
        //pretrazivanje stabla rekurzivno
        Enumeration<TreeNode> element = root.depthFirstEnumeration();
        while (element.hasMoreElements()) {
            ClassyTreeItem node = (ClassyTreeItem) element.nextElement();
            if (node.getClassyNode().equals(currMapNode)) {
                return new TreePath(node.getPath());
            }
        }
        return null;
    }
    public void deleteInTree(ClassyNode child){
        //trazimo put to trenutne mape, selektujemo trenutnu mapu -> dodajemo wrapper u tree -> trigeruje se repaint drveta
        ClassyTree ourTree = MainFrame.getInstance().getClassyTree();
        ClassyTreeItem root = ((ClassyTreeImplementation)ourTree).getSelectedNode();
        TreePath pathToCurrMap = ((ClassyTreeImplementation)MainFrame.getInstance().getClassyTree()).findPathToNode(root, child);

        ((ClassyTreeImplementation)ourTree).getTreeView().setSelectionPath(pathToCurrMap);
        System.out.println("TRee " + ourTree.getSelectedNode());
        ourTree.removeChild(ourTree.getSelectedNode());
    }
    public void addToTree(ClassyNode parent, ClassyNode child){
        //trazimo put to trenutne mape, selektujemo trenutnu mapu -> dodajemo wrapper u tree -> trigeruje se repaint drveta
        ClassyTree ourTree = MainFrame.getInstance().getClassyTree();
        ClassyTreeItem root = ((ClassyTreeImplementation)ourTree).getSelectedNode();
        TreePath pathToCurrMap = ((ClassyTreeImplementation)MainFrame.getInstance().getClassyTree()).findPathToNode(root, parent);

        ((ClassyTreeImplementation)ourTree).getTreeView().setSelectionPath(pathToCurrMap);
        ourTree.addChild(ourTree.getSelectedNode());
    }
}
