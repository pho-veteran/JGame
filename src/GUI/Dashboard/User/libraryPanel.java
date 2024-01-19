package GUI.Dashboard.User;

import BUS.AccountService;
import BUS.GameService;
import DTO.Account;
import DTO.Game;
import GUI.CustomComponents.ScrollPaneWin11;
import GUI.CustomComponents.libraryImageTable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class libraryPanel extends JPanel {
    private final Account account;
    private JPanel mainPanel;
    private JTable gameOwnedTable;
    private JScrollPane gameOwnedScrollPane;
    private JPanel gameInfoPanel;
    private JLabel gameInfo_Logo;
    private JLabel gameInfo_Name;
    private JLabel gameInfo_Genre;
    private JButton gameInfo_Download;
    private JLabel gameInfo_Desc;
    private JPanel gameOwnedPanel;
    private JPanel searchPanel;
    private JButton searchPanelHide;
    private JTextField searchBox;
    private JLabel seachByNameLabel;
    private JLabel searchByGenreLabel;
    private JComboBox byGenreComboBox;
    private JLabel searchHeader_1;
    private JButton confirmSearchButton;
    private JPanel searchPanel_2;
    private JButton searchPanelShow;
    private JLabel searchHeader;
    private JLabel gameOwnedListHeader;
    private ArrayList<Game> ownedGamesList = new ArrayList<>();

    public libraryPanel(Account account) {
        this.account = account;
        this.initPanel();
        this.initButtonListeners();
    }

    public void initButtonListeners() {
        searchPanelHide.addActionListener(e -> {
            searchPanel.setVisible(false);
            searchPanel_2.setVisible(true);
        });
        searchPanelShow.addActionListener(e -> {
            searchPanel.setVisible(true);
            searchPanel_2.setVisible(false);
        });
        confirmSearchButton.addActionListener(e -> {
            fetchSearchedOwnedGameList(searchBox.getText(), byGenreComboBox.getSelectedItem().toString());
            initSearchedGameOwnedTable();
        });
    }

    public void initPanel() {
        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
        initGameOwnedTable();
        loadGameGenreComboBox();
        gameInfoPanel.setVisible(false);
        searchPanel_2.setVisible(false);
    }

    public void updateGameInfoPanel(Game game) {
        gameInfo_Logo.setIcon(new ImageIcon(game.getBannerURL()));
        gameInfo_Name.setText(game.getName());
        gameInfo_Genre.setText("Genre: " + game.getGenre());
        gameInfo_Desc.setText("<html><p style=\"width:300px; text-align:center;\">" +
                game.getDescription()
                + "</p></html>");
        gameInfoPanel.setVisible(true);
    }

    private void createUIComponents() {
        //Custom ScrollPane
        gameOwnedScrollPane = new ScrollPaneWin11();
        gameOwnedScrollPane.setBorder(null);
        gameOwnedScrollPane.setViewportBorder(null);
        gameOwnedScrollPane.setColumnHeader(null);
        gameOwnedScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public void fetchOwnedGameList() {
        ownedGamesList = new AccountService().getOwnedGames(new AccountService().getUserID(account.getUsername()));
    }

    public void fetchSearchedOwnedGameList(String name, String genre) {
        ownedGamesList = new AccountService().getSearchedOwnedGames(new AccountService().getUserID(account.getUsername()), name, genre);
    }

    public void initSearchedGameOwnedTable() {
        String[] columnNames = {"Image", "Name"};
        Object[][] data = new Object[ownedGamesList.size()][2];
        for (int i = 0; i < ownedGamesList.size(); i++) {
            data[i][0] = new ImageIcon(ownedGamesList.get(i).getBannerURL());
            data[i][1] = ownedGamesList.get(i).getName();
        }
        gameOwnedTable = new libraryImageTable(columnNames, data);
        gameOwnedTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && gameOwnedTable.getSelectedRow() != -1) {
                int selectedRow = gameOwnedTable.getSelectedRow();
                Game selectedGame = ownedGamesList.get(selectedRow);
                updateGameInfoPanel(selectedGame);
            }
        });
        gameOwnedScrollPane.setViewportView(gameOwnedTable);
        gameOwnedScrollPane.setBackground(Color.decode("#1A2034"));
    }

    public void initGameOwnedTable() {
        fetchOwnedGameList();
        String[] columnNames = {"Image", "Name"};
        Object[][] data = new Object[ownedGamesList.size()][2];
        for (int i = 0; i < ownedGamesList.size(); i++) {
            data[i][0] = new ImageIcon(ownedGamesList.get(i).getBannerURL());
            data[i][1] = ownedGamesList.get(i).getName();
        }
        gameOwnedTable = new libraryImageTable(columnNames, data);
        gameOwnedTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && gameOwnedTable.getSelectedRow() != -1) {
                int selectedRow = gameOwnedTable.getSelectedRow();
                Game selectedGame = ownedGamesList.get(selectedRow);
                updateGameInfoPanel(selectedGame);
            }
        });
        gameOwnedScrollPane.setViewportView(gameOwnedTable);
    }

    public void loadGameGenreComboBox() {
        byGenreComboBox.addItem("");
        ArrayList<String> gameGenreList = new GameService().getGameGenreList();
        for (String genre : gameGenreList) {
            byGenreComboBox.addItem(genre);
        }
        byGenreComboBox.setSelectedIndex(0);
    }
}
