package GUI.Dashboard.Admin;

import GUI.Dashboard.aboutMenu;

import javax.swing.*;
import java.awt.*;

public class adminDashboard extends JFrame {
    private JPanel mainPanel;
    private JPanel menuPanel;
    private JButton exitButton;
    private JButton about;
    private JButton changePassword;
    private JPanel navigationPanel;
    private JButton accountEditor;
    private JButton gameEditor;
    private JPanel contentPanel;
    private JPanel accountPanel;
    private JPanel gamePanel;
    private ImageIcon accountEditorImage = new ImageIcon(new ImageIcon("src/icon/account/account.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
    private ImageIcon gameEditorImage = new ImageIcon(new ImageIcon("src/icon/dashboard/admin/games.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
    private CardLayout cardLayout;
    public adminDashboard() {
        this.initFrame();
    }
    public void initFrame(){
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setSize(1100, 800);
        this.setLocationRelativeTo(null);
        this.initComponents();
        this.initListeners();
    }
    public void initComponents(){
        accountEditor.setIcon(accountEditorImage);
        gameEditor.setIcon(gameEditorImage);
    }
    public void initListeners() {
        exitButton.addActionListener(e -> {
            dispose();
        });
        about.addActionListener(e -> { new aboutMenu().setVisible(true); });

        accountEditor.addActionListener(e -> {
            accountEditor.setBackground(Color.decode("#303952"));
            gameEditor.setBackground(Color.decode("#596275"));
            cardLayout.show(contentPanel, "accountPanel");
        });
        gameEditor.addActionListener(e -> {
            gameEditor.setBackground(Color.decode("#303952"));
            accountEditor.setBackground(Color.decode("#596275"));
            cardLayout.show(contentPanel, "gamePanel");
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        cardLayout = new CardLayout();

        contentPanel = new JPanel();
        contentPanel.setLayout(cardLayout);

        gamePanel = new gameEditorPanel();
        accountPanel = new accountEditorPanel();

        contentPanel.add(gamePanel, "gamePanel");
        contentPanel.add(accountPanel, "accountPanel");
        cardLayout.show(contentPanel, "gamePanel");
    }
}
