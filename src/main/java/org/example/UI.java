package org.example;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class UI {
    public void testRunUI() {

        // Testing the desktop app
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Class Generator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            // Create a panel for the main content
            JPanel contentPanel = new JPanel(new BorderLayout());

            // Create a panel for the title and subtitle, centered
            JPanel titlePanel = new JPanel(new BorderLayout());
            titlePanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));

            JLabel titleLabel = new JLabel("Class Generator", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            titlePanel.add(titleLabel, BorderLayout.NORTH);

            JLabel subtitleLabel = new JLabel("This app will generate the best class for you", SwingConstants.CENTER);
            subtitleLabel.setFont(new Font("Arial", Font.ITALIC, 14));
            titlePanel.add(subtitleLabel, BorderLayout.CENTER);

            // WordToggleButton
            WordToggleButton wordToggleButton = new WordToggleButton();
            titlePanel.add(wordToggleButton, BorderLayout.SOUTH);

            contentPanel.add(titlePanel, BorderLayout.NORTH);

            // Create three columns (a little bigger)
            JPanel mainPanel = new JPanel(new GridLayout(1, 3));
            mainPanel.setBorder(new RoundBorder(20, 20));

            // First Column
            DefaultListModel<String> toDoModel = new DefaultListModel<>();
            JList<String> toDoList = new JList<>(toDoModel);
            JScrollPane toDoScrollPane = new JScrollPane(toDoList);
            // Adjust the font size here
            mainPanel.add(
                    createColumnPanel("To-Do", toDoScrollPane, 16));

            // Second Column
            DefaultListModel<String> inProgressModel = new DefaultListModel<>();
            JList<String> inProgressList = new JList<>(inProgressModel);
            JScrollPane inProgressScrollPane = new JScrollPane(inProgressList);
            // Adjust the font size here
            mainPanel.add(
                    createColumnPanel("In Progress", inProgressScrollPane, 16));

            // Third Column
            DefaultListModel<String> doneModel = new DefaultListModel<>();
            JList<String> doneList = new JList<>(doneModel);
            JScrollPane doneScrollPane = new JScrollPane(doneList);
            // Adjust the font size here
            mainPanel.add(
                    createColumnPanel("Done", doneScrollPane, 16));

            contentPanel.add(mainPanel, BorderLayout.CENTER);


            contentPanel.add(mainPanel, BorderLayout.CENTER);

            frame.getContentPane().add(contentPanel);
            centerFrame(frame);
            frame.setVisible(true);
        });


    }
    private static void titleLabel(JPanel titlePanel) {
        JLabel titleLabel = new JLabel("Class Generator", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel, BorderLayout.NORTH);

        JLabel subtitleLabel = new JLabel("This app will generate the best class for you", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        titlePanel.add(subtitleLabel, BorderLayout.SOUTH);
    }


    // Modify createColumnPanel method for round borders
    private static JPanel createColumnPanel(String title, JScrollPane scrollPane, int fontSize) {
        JPanel columnPanel = new JPanel(new BorderLayout());
        columnPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));

        // Use JLabel with CENTER alignment for the title
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, fontSize));

        columnPanel.add(titleLabel, BorderLayout.NORTH);
        columnPanel.add(scrollPane, BorderLayout.CENTER);

        return columnPanel;
    }

    private static void centerFrame(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) (screenSize.getWidth() - frame.getWidth()) / 2;
        int centerY = (int) (screenSize.getHeight() - frame.getHeight()) / 2;
        frame.setLocation(centerX, centerY);
    }

}
