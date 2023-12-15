package raf.dsw.classycraft.app.gui.swing.view;

import com.sun.tools.javac.Main;
import raf.dsw.classycraft.app.classyRepository.implementation.Interclass;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.Function;

public class EditClassPopup extends JDialog {
    private DefaultListModel<ClassContent> model1;
    private DefaultListModel<ClassContent> model2 = null;
    private JList<ClassContent> list1;
    private JList<ClassContent> list2 = null;
    private final Interclass interclass;
    private JTextField nameTextField;

    public EditClassPopup(JFrame parentFrame, Interclass interclass) {
        super(parentFrame, "Edit methods / attributes", true);

        model1 = new DefaultListModel<>();
        if(interclass instanceof Klasa) {
            model2 = new DefaultListModel<>();
            for(var x: interclass.getContent())
            {
                if(x instanceof Metod)
                    model1.addElement(x);
                else
                    model2.addElement(x);
            }
        }
        else {
            for (var x : interclass.getContent())
                model1.addElement(x);
        }

        list1 = new JList<>(model1);
        if(model2 != null)
            list2 = new JList<>(model2);
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
        for(var x: model1.toArray())
            interclass.addContent((ClassContent)x);

        if(model2 != null)
            for(var x: model2.toArray())
                interclass.addContent((ClassContent) x);

        interclass.setName(nameTextField.getText());
        var diagramView = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView();
        diagramView.paint();
        MainFrame.getInstance().getClassyTree().repaintTree();
    }
    private JPanel constructPanel(DefaultListModel<ClassContent> model, JList<ClassContent> list,
                                  String panelTitle, String addButtonTitle, String removeButtonTitle, String dialogText,
                                  Function<String, ClassContent> constructor)
    {
        JPanel panel = new JPanel(new BorderLayout());

        panel.add(new JLabel(panelTitle), BorderLayout.NORTH);
        panel.add(new JScrollPane(list), BorderLayout.CENTER);

        JButton addButton = new JButton(addButtonTitle);
        addButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(EditClassPopup.this, dialogText);
            if (input != null && !input.isEmpty())
                model.addElement(constructor.apply(input));
        });

        JButton removeButton = new JButton(removeButtonTitle);
        removeButton.addActionListener(e -> {
            int selectedIndex = list.getSelectedIndex();
            if (selectedIndex != -1)
                model.remove(selectedIndex);
        });

        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(addButton);
        buttons.add(removeButton);
        panel.add(buttons, BorderLayout.SOUTH);
        return panel;
    }
    private void initComponents() {
        setLayout(new BorderLayout());

        nameTextField = new JTextField(interclass.getName());
        add(nameTextField, BorderLayout.NORTH);

        if(interclass instanceof Klasa)
        {
            var panel1 = constructPanel(model1, list1, "Methods",
                    "Add method", "Remove method", "Enter method name:",
                    Metod::new);
            var panel2 = constructPanel(model2, list2, "Attributes",
                    "Add attribute", "Remove attribute", "Enter attribute name:",
                    Atribut::new);
            add(panel1, BorderLayout.EAST);
            add(panel2, BorderLayout.WEST);
        }
        else if(interclass instanceof Interfejs)
        {
            var panel1 = constructPanel(model1, list1, "Methods",
                    "Add method", "Remove method", "Enter method name:",
                    Metod::new);
            add(panel1, BorderLayout.CENTER);
        }
        else {
            var panel1 = constructPanel(model1, list1, "Enum values",
                    "Add value", "Remove value", "Enter enum value name:",
                    EnumElement::new);
            add(panel1, BorderLayout.CENTER);
        }

        pack();
        setLocationRelativeTo(getParent());
    }


}
