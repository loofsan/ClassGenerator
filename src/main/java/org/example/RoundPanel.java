package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundPanel extends JPanel {
    private final int arcWidth;
    private final int arcHeight;

    public RoundPanel(int arcWidth, int arcHeight) {
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();
        RoundRectangle2D roundedRectangle =
                new RoundRectangle2D.Float
                        (0, 0, width - 1, height - 1, arcWidth, arcHeight);

        g2d.setColor(getBackground());
        g2d.fill(roundedRectangle);

        g2d.dispose();
    }
}
