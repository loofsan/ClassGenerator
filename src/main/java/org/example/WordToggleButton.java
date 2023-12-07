package org.example;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class WordToggleButton extends JPanel {

    private JComboBox<String> toggleSwitch;
    private String[] words = {"Fall", "Spring"};
    private Timer colorTransitionTimer;
    private Color targetColor;

    public String getSelectedSemester() {
        String selectedSemester = (String) toggleSwitch.getSelectedItem();
        return selectedSemester.equals("Fall") ? "F" : "S";
    }

    public WordToggleButton() {
        toggleSwitch = new JComboBox<>(words);
        toggleSwitch.setFont(new Font("Tahoma 11", Font.PLAIN, 16));
        toggleSwitch.setFocusable(false);
        toggleSwitch.setBackground(Color.BLUE);
        toggleSwitch.setForeground(Color.WHITE);
        toggleSwitch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleSwitchChanged();
            }
        });

        add(toggleSwitch);

        colorTransitionTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBackgroundColor();
            }
        });
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