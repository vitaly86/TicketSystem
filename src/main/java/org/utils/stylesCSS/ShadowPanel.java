package org.utils.stylesCSS;

import javax.swing.*;
import java.awt.*;

public class ShadowPanel extends JPanel {

    private final Color shadowColor = new Color(0, 0, 0, 50); // Semi-transparent black

    public ShadowPanel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        int shadowSize = 5;
        int x = shadowSize;
        int y = shadowSize;
        int w = getWidth() - shadowSize * 2;
        int h = getHeight() - shadowSize * 2;

        // Enable anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw shadow
        g2d.setColor(shadowColor);
        g2d.fillRoundRect(x, y, w, h, 20, 20);

        // Draw panel content
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth() - shadowSize, getHeight() - shadowSize, 20, 20);

        g2d.dispose();
    }
}

