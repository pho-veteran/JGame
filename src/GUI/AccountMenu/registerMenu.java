package GUI.AccountMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class registerMenu extends JFrame {
    private JLabel header;
    private JPanel mainPanel;
    private JPanel inputPanel;
    private JTextField username;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JLabel navigateToLogin;
    private JButton navigateToLoginButton;
    private JButton signUpButton;
    private JPasswordField passwordField1;
    private JLabel confirmPassword;
    private ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/icon/account/user-interface.png").getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING));
    public registerMenu() {
        this.initFrame();
        navigateToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new loginMenu().setVisible(true);
                dispose();
            }
        });
    }
    public void initFrame() {
        this.setTitle("Sign Up");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(320, 370);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
        header.setIcon(imageIcon);
    }

    public static void main(String[] args) {
        new registerMenu().setVisible(true);
    }
}
