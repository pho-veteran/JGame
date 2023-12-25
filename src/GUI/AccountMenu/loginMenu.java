package GUI.AccountMenu;

import BUS.AccountService;

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
    private JButton navigateSignupButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JLabel logo;
    private ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/icon/account/account.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
    public loginMenu() {
        this.initFrame();
        navigateSignupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new registerMenu().setVisible(true);
                dispose();
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accessRight =
                    new AccountService().login(usernameField.getText(), passwordField.getText());
                if (accessRight.equals("admin")) {
                    new GUI.Dashboard.Admin.adminDashboard().setVisible(true);
                    dispose();
                }
                else if (accessRight.equals("user")) {
                    new GUI.Dashboard.User.userDashboard().setVisible(true);
                    dispose();
                }
                else
                    JOptionPane.showMessageDialog(null, "Wrong username or password!"
                        , "", JOptionPane.ERROR_MESSAGE);
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
