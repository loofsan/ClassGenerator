package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

class PlusButtonAction {
    private final Map<String, List<ClassInfo>> classMapFall;
    private final Map<String, List<ClassInfo>> classMapSpring;
    private final BayesianSorter bayesianSorter;
    private final WordToggleButton wordToggleButton;
    private final JLabel bestClassLabel;

    public PlusButtonAction(Map<String, List<ClassInfo>> classMapFall,
                            Map<String, List<ClassInfo>> classMapSpring,
                            BayesianSorter bayesianSorter,
                            WordToggleButton wordToggleButton,
                            JLabel bestClassLabel) {
        this.classMapFall = classMapFall;
        this.classMapSpring = classMapSpring;
        this.bayesianSorter = bayesianSorter;
        this.wordToggleButton = wordToggleButton;
        this.bestClassLabel = bestClassLabel;
    }

    public void execute() {
        // Create a new JFrame
        JFrame frame = new JFrame();
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null); // Center the frame

        // Create a JPanel to hold the JLabel and JTextField
        JPanel panel = getTextPanel();

        // Create a JLabel asking "What is the class that you want?"
        JLabel label = new JLabel("Provide Input Example: engl 100", SwingConstants.CENTER);
        label.setFont(new Font("Source Sans", Font.PLAIN, 18));
        panel.add(label);

        // Create a JTextField for the user to enter the class name
        JTextField textField = new JTextField(11);
        textField.setFont(new Font("Source Sans", Font.PLAIN, 20));
        panel.add(textField);

        // Create a "Submit" button
        JButton submitButton = submitButtonResult(textField);
        frame.getRootPane().setDefaultButton(submitButton);
        panel.add(submitButton);


        // Add the panel to the frame
        frame.add(panel);

        // Make the frame visible
        frame.setVisible(true);
    }

    private JButton submitButtonResult(JTextField textField) {
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Source Sans", Font.PLAIN, 20));
        submitButton.addActionListener(e -> {
            // Create an instance of DataTest and call the testDataOutput method
            DataOutput dataOutput = new DataOutput(classMapFall, classMapSpring, bayesianSorter);
            String userInput = wordToggleButton.getSelectedSemester() + " " + textField.getText();
            String bestClass = dataOutput.printResult(userInput);

            // Update the bestClassLabel with the best class
            bestClassLabel.setText(bestClass);
            // Change font name, style, and size as needed
            bestClassLabel.setFont(new Font("Source Sans", Font.BOLD, 14));
            bestClassLabel.setForeground(new Color(201, 201, 201));
        });
        return submitButton;
    }

    private static JPanel getTextPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 1));
        int borderSize = 10;
        panel.setBorder(BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize));
        // Set the background color to darkish grey
        panel.setBackground(new Color(169, 169, 169));
        return panel;
    }
}