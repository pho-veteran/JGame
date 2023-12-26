package GUI.AccountMenu;

import BUS.AccountService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class registerMenu extends JFrame {
    private JLabel logo;
    private JPanel mainPanel;
    private JPanel inputPanel;
    private JTextField usernameField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JLabel navigateToLogin;
    private JButton navigateToLoginButton;
    private JButton signUpButton;
    private JPasswordField confirmPasswordField;
    private JLabel confirmPassword;
    private JButton exitButton;
    private ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/icon/account/account.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
    public registerMenu() {
        this.initFrame();
        navigateToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new loginMenu().setVisible(true);
                    }
                });
                dispose();
            }
        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!usernameField.getText().isEmpty() && passwordField.getText().equals(confirmPasswordField.getText())) {
                    Integer status = null;

                    try {
                        status = new AccountService().register(usernameField.getText(), passwordField.getText());
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    if (status == 1) {
                        JOptionPane.showMessageDialog(null, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                new loginMenu().setVisible(true);
                            }
                        });
                        dispose();
                    } else if (status == -1)
                        JOptionPane.showMessageDialog(null, "Account exist!", "Error", JOptionPane.ERROR_MESSAGE);
                    else JOptionPane.showMessageDialog(null, "Registration failed!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                    JOptionPane.showMessageDialog(null, "Passwords are not the same or username is empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        this.setResizable(false);
        this.setUndecorated(true);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        confirmPasswordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        logo.setIcon(imageIcon);
    }
}
