package raf.dsw.classycraft.app.gui.swing.view;

import com.sun.tools.javac.Main;
import raf.dsw.classycraft.app.classyRepository.implementation.Interclass;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Atribut;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Klasa;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Metod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EditClassPopup extends JDialog {
    private DefaultListModel<String> methodsListModel;
    private DefaultListModel<String> variablesListModel = null;
    private JList<String> methodsList;
    private JList<String> variablesList;
    private final Interclass interclass;
    private JTextField nameTextField;

    public EditClassPopup(JFrame parentFrame, Interclass interclass) {
        super(parentFrame, "Edit methods / attributes", true);

        methodsListModel = new DefaultListModel<>();
        if(interclass instanceof Klasa)
        {
            variablesListModel = new DefaultListModel<>();
            for(var x: interclass.getContent())
            {
                if(x instanceof Atribut)
                    variablesListModel.addElement(x.getName());
                else
                    methodsListModel.addElement(x.getName());
            }
        }

        methodsList = new JList<>(methodsListModel);
        if(variablesListModel != null)
            variablesList = new JList<>(variablesListModel);
        this.interclass = interclass;

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                onClose();
            }
        });

        initComponents();
    }
    private void onClose()
    {
        interclass.clearContent();
        for(var x: methodsListModel.toArray())
            interclass.addContent(new Metod((String)x));

        if(variablesListModel != null)
            for(var x: variablesListModel.toArray())
                interclass.addContent(new Atribut((String)x));

        interclass.setName(nameTextField.getText());
        var diagramView = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView();
        diagramView.paint();
        MainFrame.getInstance().getClassyTree().repaintTree();
    }
    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel methodsPanel = new JPanel(new BorderLayout());

        nameTextField = new JTextField(interclass.getName());
        add(nameTextField, BorderLayout.NORTH);

        methodsPanel.add(new JLabel("Methods"), BorderLayout.NORTH);
        methodsPanel.add(new JScrollPane(methodsList), BorderLayout.CENTER);

        JButton addMethodButton = new JButton("Add Method");
        addMethodButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(EditClassPopup.this, "Enter method name:");
            if (input != null && !input.isEmpty())
                methodsListModel.addElement(input);
        });

        JButton removeMethodButton = new JButton("Remove Method");
        removeMethodButton.addActionListener(e -> {
            int selectedIndex = methodsList.getSelectedIndex();
            if (selectedIndex != -1)
                methodsListModel.remove(selectedIndex);
        });

        JPanel methodButtons = new JPanel(new FlowLayout());
        methodButtons.add(addMethodButton);
        methodButtons.add(removeMethodButton);
        methodsPanel.add(methodButtons, BorderLayout.SOUTH);
        add(methodsPanel, BorderLayout.WEST);


        if(variablesListModel != null)
        {
            JPanel variablesPanel = new JPanel(new BorderLayout());

            variablesPanel.add(new JLabel("Variables"), BorderLayout.NORTH);
            variablesPanel.add(new JScrollPane(variablesList), BorderLayout.CENTER);
            JButton addVariableButton = new JButton("Add Variable");
            addVariableButton.addActionListener(e -> {
                String input = JOptionPane.showInputDialog(EditClassPopup.this, "Enter variable name:");
                if (input != null && !input.isEmpty())
                    variablesListModel.addElement(input);
            });

            JButton removeVariableButton = new JButton("Remove Variable");
            removeVariableButton.addActionListener(e -> {
                int selectedIndex = variablesList.getSelectedIndex();
                if (selectedIndex != -1)
                    variablesListModel.remove(selectedIndex);
            });

            JPanel variableButtons = new JPanel(new FlowLayout());
            variableButtons.add(addVariableButton);
            variableButtons.add(removeVariableButton);
            variablesPanel.add(variableButtons, BorderLayout.SOUTH);
            add(variablesPanel, BorderLayout.EAST);
        }

        pack();
        setLocationRelativeTo(getParent());
    }


}
