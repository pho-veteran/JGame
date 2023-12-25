package GUI.Dashboard.User;

import javax.swing.*;
import java.awt.*;

public class userDashboard extends JFrame {
    private JPanel mainPanel;
    private JButton storeNavigator;
    private JButton libraryNavigator;
    private JButton walletNavigator;
    private JPanel navigator;
    private JPanel profileInfo;
    private JSeparator separator1;
    private JPanel leftPanel;

    public userDashboard() {
        this.initFrame();
    }
    public void initFrame() {
        this.setTitle("User Dashboard");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        storeNavigator.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.decode("#546de5")));
        storeNavigator.setBorderPainted(true);
        libraryNavigator.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.decode("#546de5")));
        libraryNavigator.setBorderPainted(true);
        walletNavigator.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.decode("#546de5")));
    }
    public static void main(String[] args) {
        new userDashboard().setVisible(true);
    }
}
