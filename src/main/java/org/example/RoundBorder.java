package org.example;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.Border;

public class RoundBorder extends RoundRectangle2D.Float implements Border {

    public RoundBorder(float arcWidth, float arcHeight) {
        super(0, 0, 0, 0, arcWidth, arcHeight);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets((int) this.getY(), (int) this.getX(), (int) this.getY(), (int) this.getX());
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(this);
    }
}