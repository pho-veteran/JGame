package GUI.Dashboard.User;

import BUS.AccountService;
import DTO.Account;
import GUI.Dashboard.aboutMenu;

import javax.swing.*;
import javax.swing.border.Border;
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
    private JPanel contentPanel;
    private JButton exitButton;
    private JPanel menuPanel;
    private JButton about;
    private JButton changePassword;
    private Border currentBorder;
    private Account account;
    private ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/icon/logo.png").getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH));
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainContentPanel;
    public userDashboard(Account account) {
        this.account = account;
        this.initFrame();
        this.initListeners();
        this.initAccountInfomation();
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new aboutMenu().setVisible(true);
                    }
                });
            }
        });
        changePassword.addActionListener(e -> {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new changePasswordGUI(account).setVisible(true);
                }
            });
        });
    }
    public void initListeners() {
        storeNavigator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storeNavigator.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.decode("#0EB194")));
                libraryNavigator.setBorder(currentBorder);
                walletNavigator.setBorder(currentBorder);
                storeNavigator.setBorderPainted(true);
                libraryNavigator.setBorderPainted(false);
                walletNavigator.setBorderPainted(false);
                cardLayout.show(mainContentPanel, "gameStorePanel");
            }
        });

        libraryNavigator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storeNavigator.setBorder(currentBorder);
                walletNavigator.setBorder(currentBorder);
                libraryNavigator.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.decode("#0EB194")));
                storeNavigator.setBorderPainted(false);
                libraryNavigator.setBorderPainted(true);
                walletNavigator.setBorderPainted(false);
                cardLayout.show(mainContentPanel, "libraryPanel");
            }
        });

        walletNavigator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storeNavigator.setBorder(currentBorder);
                libraryNavigator.setBorder(currentBorder);
                walletNavigator.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.decode("#0EB194")));
                storeNavigator.setBorderPainted(false);
                libraryNavigator.setBorderPainted(false);
                walletNavigator.setBorderPainted(true);
                cardLayout.show(mainContentPanel, "walletPanel");
            }
        });
    }
    public void initAccountInfomation() {
        AccountService accountService = new AccountService();

        String username = account.getUsername();
        Double balance = accountService.getBalance(username);
        Integer prime = accountService.getPrimeStatus(username);

        if (username != null) {
            usernameLabel.setText(account.getUsername());
        }

        if (balance != null) {
            balanceLabel.setText(balance + " VND");
        }

        if (primeStatus != null) {
            if (prime == 1) {
                primeStatus.setForeground(Color.decode("#F7D794"));
                primeStatus.setText("Prime Member");
            } else {
                primeStatus.setForeground(Color.decode("#596275"));
                primeStatus.setText("Non-Prime Member");
            }
        }
    }

    public void initFrame() {
        this.setTitle("User Dashboard");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1300, 900);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        logoLabel.setIcon(imageIcon);

        //Get current Border and Margin settings
        currentBorder = storeNavigator.getBorder();
        storeNavigator.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.decode("#0EB194")));
        storeNavigator.setBorderPainted(true);

        //Init mainContentPanel cardLayout
        mainContentPanel = new JPanel();
        mainContentPanel.setLayout(cardLayout);
        libraryPanel libPanel = new libraryPanel(account);
        mainContentPanel.add(new gameStorePanel(account, this, libPanel), "gameStorePanel");
        mainContentPanel.add(libPanel, "libraryPanel");
        mainContentPanel.add(new walletPanel(account), "walletPanel");
        mainContentPanel.setBackground(Color.decode("#3D4450"));
        contentPanel.add(mainContentPanel, BorderLayout.CENTER);
    }
}
