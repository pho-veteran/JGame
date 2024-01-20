package GUI.Dashboard.Admin;

import GUI.Dashboard.aboutMenu;

import javax.swing.*;
import java.awt.*;

public class adminDashboard extends JFrame {
    private final ImageIcon accountEditorImage = new ImageIcon(new ImageIcon("src/icon/account/account.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
    private final ImageIcon gameEditorImage = new ImageIcon(new ImageIcon("src/icon/dashboard/admin/games.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
    private JPanel mainPanel;
    private JPanel menuPanel;
    private JButton exitButton;
    private JButton about;
    private JButton changePassword;
    private JPanel navigationPanel;
    private JButton gameEditor;
    private JPanel contentPanel;
    private JPanel gamePanel;
    private CardLayout cardLayout;

    public adminDashboard() {
        this.initFrame();
    }

    public void initFrame() {
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setSize(1100, 850);
        this.setLocationRelativeTo(null);
        this.initComponents();
        this.initListeners();
    }

    public void initComponents() {
        gameEditor.setIcon(gameEditorImage);
    }

    public void initListeners() {
        exitButton.addActionListener(e -> {
            dispose();
        });
        about.addActionListener(e -> {
            new aboutMenu().setVisible(true);
        });

        gameEditor.addActionListener(e -> {
            gameEditor.setBackground(Color.decode("#303952"));
            cardLayout.show(contentPanel, "gamePanel");
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        cardLayout = new CardLayout();

        contentPanel = new JPanel();
        contentPanel.setLayout(cardLayout);

        gamePanel = new gameEditorPanel();

        contentPanel.add(gamePanel, "gamePanel");
        cardLayout.show(contentPanel, "gamePanel");
    }
}
