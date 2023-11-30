package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class WordToggleButton extends JPanel {

    private JButton toggleButton;
    private String[] words = {"Fall", "Spring"};
    private int currentWordIndex;
    private Timer colorTransitionTimer;
    private Color targetColor;

    public WordToggleButton() {
        currentWordIndex = 0;
        toggleButton = new JButton(words[0]);
        toggleButton.setFocusPainted(false);
        toggleButton.setBackground(Color.BLACK);
        toggleButton.setForeground(Color.WHITE);
        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleButtonClicked();
            }
        });

        add(toggleButton);

        colorTransitionTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBackgroundColor();
            }
        });
    }

    private void toggleButtonClicked() {
        currentWordIndex = (currentWordIndex + 1) % words.length;
        toggleButton.setText(words[currentWordIndex]);
        targetColor = Color.BLACK;
        colorTransitionTimer.start();
    }

    private void updateBackgroundColor() {
        Color currentColor = toggleButton.getBackground();
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
        toggleButton.setBackground(newColor);
    }
}