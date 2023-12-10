package raf.dsw.classycraft.app.classyRepository.implementation.painters;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.composite.ElementPainter;
import raf.dsw.classycraft.app.classyRepository.implementation.Interclass;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class InterClassPainter extends ElementPainter {

    private final int MAX_STRING_LENGTH = 15;
    private final int NAME_SPACING = 20;
    private final int CONTENT_SPACING = 10;
    public InterClassPainter(DiagramElement element, int x, int y) {
        super(element, x, y);
    }

    private String limitString(String str)
    {
        var newStr = str;
        if (str.length() > MAX_STRING_LENGTH)
        {
            newStr = str.substring(0, MAX_STRING_LENGTH - 3) + "...";
        }
        return newStr;
    }

    @Override
    public void paint(Graphics g) {
        if (!(element instanceof Interclass))
            return;
        constructBoundingBox(g, true);
    }

    private Rectangle constructBoundingBox(Graphics g, boolean shouldDraw)
    {
        if(shouldDraw)
            g.setColor(Color.BLACK); // Set color black for text

        var interClass = (Interclass)element;

        int maxRectWidth = 0;
        int totalRectHeight = 0;

        FontMetrics fontMetrics;
        if (g != null)
            fontMetrics = g.getFontMetrics();
        else {
            var diagramView = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView();
            fontMetrics = diagramView.getFontMetrics(diagramView.getFont());
        }

        if (shouldDraw)
            g.drawString(element.getName(), x + 10, y + NAME_SPACING);

        var nameBounds = fontMetrics.getStringBounds(element.getName(), g);
        maxRectWidth = Math.max(maxRectWidth, (int)nameBounds.getWidth());
        totalRectHeight += NAME_SPACING;
        totalRectHeight += (int)nameBounds.getHeight();
        totalRectHeight += NAME_SPACING;


        for(var content: interClass.getContent())
        {
            var name = limitString(content.getName());
            // Get bounds for the string to be drawn
            var bounds = fontMetrics.getStringBounds(name, g);
            int w = (int)bounds.getWidth();
            int h = (int)bounds.getHeight();

            if (shouldDraw)
                g.drawString(name, x + 10, y + totalRectHeight);

            maxRectWidth = Math.max(maxRectWidth, w);
            totalRectHeight += h;
            totalRectHeight += CONTENT_SPACING;
        }
        // Draw rectangle
        int w = maxRectWidth + 20;
        int h = totalRectHeight + 20;
        if(shouldDraw) {
            g.setColor(element.getColor());
            g.drawRect(x, y, w, h);
        }
        return new Rectangle(x, y, w, h);
    }
    @Override
    public Rectangle getBoundingBox() {

        return constructBoundingBox(null, false);
    }
}
