package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

class RoundButton extends JButton {
    public RoundButton(String label) {
        super(label);
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Dimension size = getSize();
        Insets insets = getInsets();
        int width = size.width - 1 - insets.left - insets.right;
        int height = size.height - 1 - insets.top - insets.bottom;

        RoundRectangle2D roundedRect = new RoundRectangle2D.Double(
                insets.left,
                insets.top,
                width,
                height,
                20, // Adjust the radius for roundness
                20
        );

        g2.setColor(getBackground());
        g2.fill(roundedRect);

        g2.setColor(getForeground());
        g2.draw(roundedRect);

        g2.dispose();

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, 20, 20);
    }
}
