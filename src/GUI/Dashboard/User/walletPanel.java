package GUI.Dashboard.User;

import BUS.AccountService;
import DTO.Account;

import javax.swing.*;
import java.awt.*;

public class walletPanel extends JPanel {
    private Account account;
    private JPanel mainPanel;
    private JPanel jPrimePanel;
    private JLabel jPrimeHeader;
    private JPanel jPrimePurchase;
    private JLabel jPrime_status_desc;
    private JButton jPrime_buyButton;
    private JLabel jPrime_status_header;
    private JSeparator separator1;
    private JLabel addFundHeader;
    private JPanel contentPanel;
    private JPanel addFundPanel;
    private JButton addFund_20Button;
    private JLabel addFund_20Label;
    private JPanel addFund_20;
    private JPanel addFund_50;
    private JLabel addFund_50Label;
    private JButton addFund_50Button;
    private JPanel addFund_100;
    private JLabel addFund_100Label;
    private JButton addFund_100Button;
    private JPanel addFund_200;
    private JLabel addFund_200Label;
    private JButton addFund_200Button;
    private JLabel thanksLabel;
    private userDashboard controlDashboard;
    public walletPanel(Account account, userDashboard controlDashboard) {
        this.account = account;
        this.initPanel();
        this.controlDashboard = controlDashboard;
    }
    public void initPanel() {
        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
        this.initPrimePanel();
        this.initAddFundButton();
    }
    public void initPrimePanel() {
        if (new AccountService().getPrimeStatus(account.getUsername()) == 0) {
            jPrime_status_header.setText("You don't have your JPrime membership yet!");
            jPrime_buyButton.setVisible(true);
            jPrime_status_desc.setText("<html><p style=\"width:450px; text-align:left;\">" +
                    "<b><font color='#F7D794'>JPrime Membership</font></b> offers a 20% discount on every game purchased " +
                    "from the affiliated store. You can enhance your gaming experience while enjoying discounted prices on a variety " +
                    "of games available in our store!" +
                    "</p></html>");
        } else {
            setjPrimePurchase();
        }
        jPrime_buyButton.addActionListener(e -> {
            new AccountService().buyPrime(account.getUsername());
            controlDashboard.initAccountInfomation();
            setjPrimePurchase();
        });
    }
    public void setjPrimePurchase() {
        jPrime_status_header.setText("You have purchased a JPrime membership!");
        jPrime_buyButton.setVisible(false);
        jPrime_status_desc.setText("<html><p style=\"width:700px; text-align:left;\">" +
                "<b><font color='#F7D794'>JPrime Membership</font></b> offers a 20% discount on every game purchased " +
                "from the affiliated store. You can enhance your gaming experience while enjoying discounted prices on a variety " +
                "of games available in our store!" +
                "</p></html>");
    }
    public void initAddFundButton() {
        addFund_20Button.addActionListener(e -> {
            new AccountService().addFund(account.getUsername(), 20);
            controlDashboard.initAccountInfomation();
            thanksLabel.setText("Thank you for your purchase!");
        });
        addFund_50Button.addActionListener(e -> {
            new AccountService().addFund(account.getUsername(), 50);
            controlDashboard.initAccountInfomation();
            thanksLabel.setText("Thank you for your purchase!");
        });
        addFund_100Button.addActionListener(e -> {
            new AccountService().addFund(account.getUsername(), 100);
            controlDashboard.initAccountInfomation();
            thanksLabel.setText("Thank you for your purchase!");
        });
        addFund_200Button.addActionListener(e -> {
            new AccountService().addFund(account.getUsername(), 200);
            controlDashboard.initAccountInfomation();
            thanksLabel.setText("Thank you for your purchase!");
        });
    }
}
