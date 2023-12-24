package GUI.AccountMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginMenu extends JFrame {

    private JPanel inputPanel;
    private JTextField usernameField;
    private JButton loginButton;
    private JPanel mainPanel;
    private JLabel navigateToSignUp;
    private JButton signupButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JLabel logo;
    private ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/icon/account/account.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
    public loginMenu() {
        this.initFrame();
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new registerMenu().setVisible(true);
                dispose();
            }
        });
    }
    public void initFrame() {
        this.setTitle("Login");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(270, 320);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        usernameField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        logo.setIcon(imageIcon);
    }
    public static void main(String[] args) {
        new loginMenu().setVisible(true);
    }
}
