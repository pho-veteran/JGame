package GUI.Dashboard.User;

import javax.swing.*;

public class userDashboard extends JFrame {
    private JPanel mainPanel;
    private JLabel Logo;
    private JPanel statusBar;
    private JPanel navigationBar;
    private JLabel welcomeUsername;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    public userDashboard() {
        this.initFrame();

    }
    public void initFrame() {
        this.setTitle("User Dashboard");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
}
