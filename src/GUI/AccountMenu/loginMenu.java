package GUI.AccountMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginMenu extends JFrame {
    private JLabel header;
    private JPanel inputPanel;
    private JTextField username;
    private JButton loginButton;
    private JPanel mainPanel;
    private JLabel navigateToSignUp;
    private JButton signupButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/icon/account/user-interface.png").getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING));
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
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(270, 320);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        header.setIcon(imageIcon);
    }

    public static void main(String[] args) {
        new loginMenu().setVisible(true);
    }
}
