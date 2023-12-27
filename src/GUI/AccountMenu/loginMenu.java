package GUI.AccountMenu;

import BUS.AccountService;
import DTO.Account;
import GUI.Dashboard.User.userDashboard;

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
    private JButton exitButton;
    private JButton dupe;
    private ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/icon/account/account.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
    public loginMenu() {
        this.initFrame();
        navigateSignupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new registerMenu().setVisible(true);
                    }
                });
                dispose();
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Check if the fields are empty
                if (usernameField.getText().equals("") || passwordField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //Check if the username exists
                Account account = new AccountService().login(usernameField.getText(), passwordField.getText());
                if (account == null) {
                    JOptionPane.showMessageDialog(null, "Wrong username or password!"
                            , "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //Check if the account is a user or an admin
                String accessRight = account.getAccessRight();
                if (accessRight.equals("Admin")) {
                    new GUI.Dashboard.Admin.adminDashboard().setVisible(true);
                    JOptionPane.showMessageDialog(null, "Welcome back, admin!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
                else {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            new userDashboard(account).setVisible(true);
                        }
                    });
                    JOptionPane.showMessageDialog(null, "Welcome back, " + usernameField.getText() + "!", "Success", JOptionPane.INFORMATION_MESSAGE, imageIcon);
                    dispose();
                }
            }
        });
        //Exit button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    public void initFrame() {
        this.setTitle("Login");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(340, 320);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        logo.setIcon(imageIcon);
    }
    public static void main(String[] args) {
        new loginMenu().setVisible(true);
    }
}
