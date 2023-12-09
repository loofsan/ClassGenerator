package org.example;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

class WordToggleButton extends JPanel {

    private final JComboBox<String> toggleSwitch;
    private final Timer colorTransitionTimer;
    private Color targetColor;

    public String getSelectedSemester() {
        String selectedSemester = (String) toggleSwitch.getSelectedItem();
        assert selectedSemester != null;
        return selectedSemester.equals("Fall") ? "F" : "S";
    }

    public WordToggleButton() {

        UIManager.put("ToolTip.foreground", new ColorUIResource(Color.WHITE));
        UIManager.put("ToolTip.background", new ColorUIResource(Color.BLACK));

        setLayout(new FlowLayout(FlowLayout.CENTER)); // Align components to the left

        String[] words = {"Fall", "Spring"};
        toggleSwitch = new JComboBox<>(words);
        toggleSwitch.setFont(new Font("Tahoma 11", Font.PLAIN, 16));
        toggleSwitch.setFocusable(false);
        toggleSwitch.setBackground(Color.BLUE);
        toggleSwitch.setForeground(Color.WHITE);
        toggleSwitch.setToolTipText("Choose the semester that you want");
        toggleSwitch.getPreferredSize();
        toggleSwitch.addActionListener(e -> toggleSwitchChanged());

        add(toggleSwitch);

        colorTransitionTimer = new Timer(10, e -> updateBackgroundColor());
    }

    private void toggleSwitchChanged() {
        targetColor = Color.BLUE;
        colorTransitionTimer.start();
    }

    private void updateBackgroundColor() {
        Color currentColor = toggleSwitch.getBackground();
        int stepSize = 5;

        if (currentColor.equals(targetColor)) {
            colorTransitionTimer.stop();
            return;
        }

        int redDiff = currentColor.getRed() - targetColor.getRed();
        int greenDiff = currentColor.getGreen() - targetColor.getGreen();
        int blueDiff = currentColor.getBlue() - targetColor.getBlue();

        int newRed = Math.max(0, currentColor.getRed() - (redDiff > 0 ? stepSize : -stepSize));
        int newGreen = Math.max(0, currentColor.getGreen() - (greenDiff > 0 ? stepSize : -stepSize));
        int newBlue = Math.max(0, currentColor.getBlue() - (blueDiff > 0 ? stepSize : -stepSize));

        Color newColor = new Color(newRed, newGreen, newBlue);
        toggleSwitch.setBackground(newColor);
    }
}