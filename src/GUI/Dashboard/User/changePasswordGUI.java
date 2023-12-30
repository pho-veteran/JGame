package GUI.Dashboard.User;

import BUS.AccountService;
import DTO.Account;

import javax.swing.*;
import java.awt.*;

public class changePasswordGUI extends JFrame {
    private JPanel mainPanel;
    private JPanel inputPanel;
    private JTextField currentPasswordField;
    private JLabel currentPasswordLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JLabel confirmPassword;
    private JPasswordField confirmPasswordField;
    private JButton confirmButton;
    private JButton dupe;
    private JButton exitButton;
    private JLabel changeYourPasswordLabel;
    private Account account;
    public changePasswordGUI(Account account) {
        this.initFrame();
        this.account = account;
        exitButton.addActionListener(e -> dispose());
        confirmButton.addActionListener(e -> {
            String getCurrentPassword = new AccountService().getPassword(account.getId());
            if (!passwordField.getText().equals(confirmPasswordField.getText())) {
                JOptionPane.showMessageDialog(null, "Password does not match!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!currentPasswordField.getText().equals(getCurrentPassword)) {
                JOptionPane.showMessageDialog(null, "Wrong current password!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                new AccountService().changePassword(passwordField.getText(), account.getId());
                JOptionPane.showMessageDialog(null, "Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });
    }
    public void initFrame() {
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(423, 500);
        this.setLocationRelativeTo(null);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        confirmPasswordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        currentPasswordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        this.setUndecorated(true);
    }
}
