package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Map;

public class UI {
    private final Map<String, List<ClassInfo>> classMapFall;
    private final Map<String, List<ClassInfo>> classMapSpring;
    private final BayesianSorter bayesianSorter;

    UI(Map<String, List<ClassInfo>> classMapFall,
              Map<String, List<ClassInfo>> classMapSpring,
              BayesianSorter bayesianSorter) {
        this.classMapFall = classMapFall;
        this.classMapSpring = classMapSpring;
        this.bayesianSorter = bayesianSorter;
    }

    public void runUI() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Degree Class Generator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(2000, 800);
            UIManager.put("JFrame.contentPaneBorder", BorderFactory.createEmptyBorder(0, 0, 0, 0));
            UIManager.put("JFrame.windowBorder", BorderFactory.createEmptyBorder(0, 0, 0, 0));

            JPanel contentPanel = new JPanel(new BorderLayout());
            contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
            contentPanel.setBackground(new Color(34, 40, 44));

            JPanel titlePanel = new JPanel(new BorderLayout());
            titlePanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
            titlePanel.setBackground(new Color(34, 40, 44));

            titleLabel(titlePanel);

            WordToggleButton wordToggleButton = new WordToggleButton();
            wordToggleButton.setBackground(new Color(34, 40, 44));
            titlePanel.add(wordToggleButton, BorderLayout.SOUTH);

            contentPanel.add(titlePanel, BorderLayout.NORTH);

            JPanel mainPanel = new JPanel(new GridLayout(1, 3, 20, 20));
            mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
            mainPanel.setBackground(new Color(34, 40, 44));

            DefaultListModel<String> toDoModel = new DefaultListModel<>();
            JList<String> toDoList = new JList<>(toDoModel);
            JScrollPane toDoScrollPane = new JScrollPane(toDoList);
            //Set color
            toDoScrollPane.getViewport().getView().setBackground(new Color(21, 25, 28));
            toDoScrollPane.setBorder(null);
            mainPanel.add(createColumnPanel("To-Do", toDoScrollPane, wordToggleButton));

            DefaultListModel<String> inProgressModel = new DefaultListModel<>();
            JList<String> inProgressList = new JList<>(inProgressModel);
            JScrollPane inProgressScrollPane = new JScrollPane(inProgressList);
            //Set color
            inProgressScrollPane.getViewport().getView().setBackground(new Color(21, 25, 28));
            inProgressScrollPane.setBorder(null);
            mainPanel.add(createColumnPanel("In Progress", inProgressScrollPane, wordToggleButton));

            DefaultListModel<String> doneModel = new DefaultListModel<>();
            JList<String> doneList = new JList<>(doneModel);
            JScrollPane doneScrollPane = new JScrollPane(doneList);
            //Set color
            doneScrollPane.getViewport().getView().setBackground(new Color(21, 25, 28));
            doneScrollPane.setBorder(null);
            mainPanel.add(createColumnPanel("Done", doneScrollPane, wordToggleButton));

            contentPanel.add(mainPanel, BorderLayout.CENTER);

            frame.getContentPane().add(contentPanel);
            centerFrame(frame);
            frame.setVisible(true);
        });
    }

    private static void titleLabel(JPanel titlePanel) {
        JLabel titleLabel = new JLabel("Degree Class Generator", SwingConstants.CENTER);
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font customFont = Font.createFont(Font.TRUETYPE_FONT,
                    new File(
                            "src/main/resources/Fonts/RobotoFont/Roboto-Thin.ttf"));
            ge.registerFont(customFont);
        } catch (Exception e) {
            e.printStackTrace();
        }
        titleLabel.setFont(new Font("Roboto Thin", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel, BorderLayout.NORTH);
    }

    private JPanel createColumnPanel(String title,
                                     JScrollPane scrollPane,
                                     WordToggleButton wordToggleButton)
    {
        // Set arcWidth and arcHeight as needed
        RoundPanel columnPanel = new RoundPanel(50, 50);
        columnPanel.setLayout(new BorderLayout());
        columnPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(21, 25, 28), 1));

        titlePanel.setBackground(new Color(21, 25, 28));

        JButton button = createAddButton();
        button.addActionListener(e -> {
            System.out.println(title + " button clicked");

            // Create a JPanel to hold the sections of "+" and "-" buttons
            JPanel headerPanel;
            if (scrollPane.getColumnHeader() == null) {
                headerPanel = new JPanel();
                headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.PAGE_AXIS));
                headerPanel.setBackground(new Color(0, 0, 0, 0));
                scrollPane.setColumnHeaderView(headerPanel);
            } else {
                headerPanel = (JPanel) scrollPane.getColumnHeader().getView();
            }

            JLabel bestClassLabel = new JLabel();
            bestClassLabel.setFont(new Font("Arial", Font.ITALIC, 10));
            bestClassLabel.setHorizontalAlignment(JLabel.CENTER);
            bestClassLabel.setVerticalAlignment(JLabel.CENTER);


            JButton plusButton = new JButton("+");
            plusButton.setForeground(Color.GREEN.darker());
            plusButton.setFont(new Font("Arial", Font.BOLD, 20));

            plusButton.setToolTipText("Add a course");

            plusButton.addActionListener(e12 -> {
                // Create an instance of PlusButtonAction and call the execute method
                PlusButtonAction plusButtonAction = new PlusButtonAction
                        (classMapFall, classMapSpring, bayesianSorter,
                                wordToggleButton, bestClassLabel);
                plusButtonAction.execute();
            });

            JButton xButton = new JButton("×");
            xButton.setFont(new Font("Arial", Font.BOLD, 20));
            xButton.setForeground(Color.RED);
            xButton.setToolTipText("Close a course");
            xButton.addActionListener(e1 -> {
                // Get the parent of the "X" button, which is the buttonPanel
                Component buttonPanel = xButton.getParent();
                // Get the parent of the buttonPanel, which is the headerPanel
                Container headerPanel1 = buttonPanel.getParent();
                // Remove the buttonPanel from the headerPanel
                headerPanel1.remove(buttonPanel);
                // Refresh the headerPanel
                headerPanel1.revalidate();
                headerPanel1.repaint();
            });

            plusButton.setPreferredSize(xButton.getPreferredSize());

            JPanel buttonPanel = new RoundPanel(20, 20);
            buttonPanel.setBackground(new Color(57, 62, 70));
            buttonPanel.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();

            // Add the "X" button to the west
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0.2; // Increase the weightx of the "X" button
            buttonPanel.add(xButton, gbc);

            // Add the bestClassLabel to the center
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.weightx = 0.6; // Decrease the weightx of the bestClassLabel
            buttonPanel.add(bestClassLabel, gbc);

            // Add the "+" button to the east
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.weightx = 0.2; // Increase the weightx of the "+" button
            buttonPanel.add(plusButton, gbc);

            // Add the buttonPanel to the headerPanel
            headerPanel.add(buttonPanel);

            // Refresh the scrollPane
            scrollPane.revalidate();
            scrollPane.repaint();
        });


        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Roboto Thin", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);

        titlePanel.add(button, BorderLayout.NORTH);
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        columnPanel.add(titlePanel, BorderLayout.NORTH);
        columnPanel.add(scrollPane, BorderLayout.CENTER);
        columnPanel.setBackground(new Color(21, 25, 28));

        return columnPanel;
    }

    private static void centerFrame(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) (screenSize.getWidth() - frame.getWidth()) / 2;
        int centerY = (int) (screenSize.getHeight() - frame.getHeight()) / 2;
        frame.setLocation(centerX, centerY);
    }

    private static JButton createAddButton() {
        RoundButton button = new RoundButton("Add Class");
        Color customColor = new Color(115, 117, 148);

        // Apply styles
        button.setBorder(BorderFactory.createLineBorder(customColor));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBackground(new Color(0, 0, 0, 0)); // Transparent background
        // Set font and font color
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font customFont = Font.createFont(Font.TRUETYPE_FONT,
                    new File("src/main/resources/Fonts/Univa Nova/UnivaNova-Light.ttf"));
            ge.registerFont(customFont);
        } catch (Exception e) {
            e.printStackTrace();
        }
        button.setFont(new Font("UnivaNova-Light", Font.PLAIN, 16));
        button.setForeground(customColor);
        button.setPreferredSize(new Dimension(100, 25));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Lighter border on hover
                button.setBorder(BorderFactory.createLineBorder(customColor.brighter()));
                // Lighter font color on hover
                button.setForeground(customColor.brighter());

            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Original border on exit
                button.setBorder(BorderFactory.createLineBorder(customColor));
                // Original font color on exit
                button.setForeground(customColor);
            }

        });

        // Add an action listener (optional)
        button.addActionListener(e -> System.out.println("Button Clicked!"));

        return button;
    }
}

