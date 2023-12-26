package GUI.Dashboard;

import javax.swing.*;

public class aboutMenu extends JFrame {
    private JPanel mainPanel;
    private JPanel TitlePanel;
    private JLabel firstRow;
    private JLabel secondRow;
    private JLabel thirdRow;
    private JPanel infomationPanel;
    private JLabel fullName;
    private JLabel fullNameInfo;
    private JLabel studentCode;
    private JLabel studentCodeInfo;
    private JLabel classes;
    private JLabel classInfo;
    private JButton exitButton;
    public aboutMenu() {
        this.initFrame();
        exitButton.addActionListener(e -> dispose());
    }
    public void initFrame() {
        this.setContentPane(mainPanel);
        this.setSize(500, 400);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
    }

    public static void main(String[] args) {
        new aboutMenu().setVisible(true);
    }
}
