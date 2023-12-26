package GUI.Dashboard.User;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class userDashboard extends JFrame {
    private JPanel mainPanel;
    private JButton storeNavigator;
    private JButton libraryNavigator;
    private JButton walletNavigator;
    private JPanel navigator;
    private JPanel profileInfo;
    private JSeparator separator1;
    private JPanel leftPanel;
    private JLabel logoLabel;
    private JLabel usernameLabel;
    private JLabel balanceLabel;
    private JLabel primeStatus;
    private JPanel inlineInfo;
    private JPanel contentNaviPane;
    private ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/icon/logo.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
    public userDashboard() {
        this.initFrame();
        storeNavigator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storeNavigator.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.decode("#0EB194")));
                libraryNavigator.setBorder(null);
                walletNavigator.setBorder(null);
            }
        });

        libraryNavigator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storeNavigator.setBorder(null);
                walletNavigator.setBorder(null);
                libraryNavigator.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.decode("#0EB194")));
            }
        });

        walletNavigator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storeNavigator.setBorder(null);
                libraryNavigator.setBorder(null);
                walletNavigator.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.decode("#0EB194")));
            }
        });
    }
    public void initFrame() {
        this.setTitle("User Dashboard");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        logoLabel.setIcon(imageIcon);
//        storeNavigator.setBorder(BorderFactory.createLineBorder(Color.decode("#0EB194"), 3, true));
        storeNavigator.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.decode("#0EB194")));
        storeNavigator.setBorderPainted(true);
    }
    public static void main(String[] args) {
        new userDashboard().setVisible(true);
    }
}
