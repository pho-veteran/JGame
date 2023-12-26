package TestingFeatureZone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavigationExample {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public NavigationExample() {
        frame = new JFrame("Navigation Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Panel 1
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("This is Panel 1"));
        mainPanel.add(panel1, "Panel1");

        // Panel 2
        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("This is Panel 2"));
        mainPanel.add(panel2, "Panel2");

        // Panel 3
        JPanel panel3 = new JPanel();
        panel3.add(new JLabel("This is Panel 3"));
        mainPanel.add(panel3, "Panel3");

        // Navigation buttons
        JButton prevButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");

        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.previous(mainPanel);
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.next(mainPanel);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);

        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NavigationExample();
            }
        });
    }
}

