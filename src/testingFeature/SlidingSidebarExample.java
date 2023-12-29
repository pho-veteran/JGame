package testingFeature;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SlidingSidebarExample extends JFrame {
    //Create a sidebar panel, then add an button to toggle it on and off
    private JPanel sidebarPanel;
    private JButton toggleSidebarButton;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    //Create a sidebar panel, then add an button to toggle it on and off
    public SlidingSidebarExample() {
        setTitle("Sliding Sidebar Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

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


        //Create a sidebar panel, then add an button to toggle it on and off
        sidebarPanel = new JPanel();
        sidebarPanel.setBackground(Color.LIGHT_GRAY);
        sidebarPanel.setPreferredSize(new Dimension(100, 0));

        toggleSidebarButton = new JButton("Toggle Sidebar");
        toggleSidebarButton.addActionListener(e -> {
            if (sidebarPanel.isVisible()) {
                sidebarPanel.setVisible(false);
            } else {
                sidebarPanel.setVisible(true);
            }
        });

        // Add the sidebar panel and main panel to the frame
        add(sidebarPanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
        add(toggleSidebarButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SlidingSidebarExample().setVisible(true);
        });
    }
}

