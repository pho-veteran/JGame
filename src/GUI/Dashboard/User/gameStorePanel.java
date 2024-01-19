package GUI.Dashboard.User;

import BUS.AccountService;
import BUS.GameService;
import DTO.Account;
import DTO.Game;
import GUI.CustomComponents.ScrollPaneWin11;
import GUI.CustomComponents.customImageTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class gameStorePanel extends JPanel {
    private JPanel mainPanel;
    private JPanel placeHolderPanel_1;
    private JPanel placeHolderPanel_2;
    private JLabel topGameHeader;
    private JLabel gameLogo_1;
    private JLabel gameLogo_2;
    private JLabel gameLogo_3;
    private JLabel gameLogo_4;
    private JLabel gameLogo_5;
    private JLabel gameDesc_1;
    private JLabel gameDesc_2;
    private JLabel gameDesc_3;
    private JLabel gameDesc_4;
    private JLabel gameDesc_5;
    private JButton collapseToggle;
    private JButton collapseToggle_2;
    private JPanel collapsePanel;
    private JLabel topGameHeader_2;
    private JTable gameTable;
    private JLabel browsingHeader;
    private JScrollPane gameScrollPane;
    private JPanel toolBar;
    private JPanel searchPanel;
    private JPanel gameInformation;
    private JTextField searchBox;
    private JButton searchPanelHide;
    private JLabel seachByNameLabel;
    private JComboBox byGenreComboBox;
    private JLabel searchByGenreLabel;
    private JLabel searchHeader;
    private JButton searchPanelShow;
    private JPanel searchPanel_2;
    private JLabel searchHeader_1;
    private JButton confirmSearchButton;
    private JLabel gameInfoHeader;
    private JLabel gameInfo_Logo;
    private JPanel infoPanel;
    private JLabel gameInfo_Name;
    private JLabel gameInfo_Genre;
    private JButton gameInfo_Price_Button;
    private Account account;
    private ArrayList<Game> gameList;
    private ArrayList<Game> searchedGameList;
    private Boolean isAnyRowSelected = false;
    private userDashboard parentFrame;
    private libraryPanel controlLibraryPanel;
    public gameStorePanel(Account account, userDashboard parentFrame, libraryPanel controlLibraryPanel) {
        this.initPanel();
        this.account = account;
        this.parentFrame = parentFrame;
        this.controlLibraryPanel = controlLibraryPanel;
        collapseToggle.addActionListener(e -> {
            placeHolderPanel_1.setVisible(false);
            collapsePanel.setVisible(true);
        });
        collapseToggle_2.addActionListener(e -> {
            if (isAnyRowSelected) {
                gameTable.clearSelection();
                isAnyRowSelected = false;
                hideGameInfoPanel();
            }
            placeHolderPanel_1.setVisible(true);
            collapsePanel.setVisible(false);
        });
        searchPanelHide.addActionListener(e -> {
            searchPanel.setVisible(false);
            searchPanel_2.setVisible(true);
        });
        searchPanelShow.addActionListener(e -> {
            searchPanel.setVisible(true);
            searchPanel_2.setVisible(false);
        });
        confirmSearchButton.addActionListener(e -> {
            Object[][] searchedGameListObject = getSearchedGameList(searchBox.getText(),
                    byGenreComboBox.getSelectedItem().toString());
            String[] gameColumnNames = {"Image", "Name"};
            gameTable = new customImageTable(gameColumnNames, searchedGameListObject);
            gameTable.getSelectionModel().addListSelectionListener(ex -> {
                if (!ex.getValueIsAdjusting() && gameTable.getSelectedRow() != -1) {
                    isAnyRowSelected = true;
                    if (placeHolderPanel_1.isVisible()) {
                        placeHolderPanel_1.setVisible(false);
                        collapsePanel.setVisible(true);
                    }
                    int selectedRow = gameTable.getSelectedRow();
                    Game selectedGame = searchedGameList.get(selectedRow);
                    gameInfoDisplay(selectedGame);
                    showGameInfoPanel();
                }
            });
            gameScrollPane.setViewportView(gameTable);
        });
    }

    public void initPanel() {
        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
        collapsePanel.setVisible(false);
        searchPanel.setVisible(false);
        updateTop5Game();
        loadGameGenreComboBox();
        hideGameInfoPanel();
        searchBox.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        byGenreComboBox.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    }
    private void createUIComponents() {
        fetchGameList();
        Object[][] gameData = getGameList();
        String[] gameColumnNames = {"Image", "Name"};
        gameTable = new customImageTable(gameColumnNames, gameData);
        gameTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && gameTable.getSelectedRow() != -1) {
                isAnyRowSelected = true;
                if (placeHolderPanel_1.isVisible()) {
                    placeHolderPanel_1.setVisible(false);
                    collapsePanel.setVisible(true);
                }
                int selectedRow = gameTable.getSelectedRow();
                Game selectedGame = gameList.get(selectedRow);
                gameInfoDisplay(selectedGame);
                showGameInfoPanel();
            }
        });
        gameScrollPane = new ScrollPaneWin11();
        gameScrollPane.setViewportView(gameTable);
        gameScrollPane.setColumnHeader(null);
        gameScrollPane.setBackground(Color.decode("#1A2034"));
    }
    public void fetchGameList() {
        gameList = new GameService().getAllGamesList();
    }
    public void loadGameGenreComboBox() {
        byGenreComboBox.addItem("");
        ArrayList<String> gameGenreList = new GameService().getGameGenreList();
        for (String genre : gameGenreList) {
            byGenreComboBox.addItem(genre);
        }
        byGenreComboBox.setSelectedIndex(0);
    }
    public void updateTop5Game() {
        ArrayList<Game> top5GameList = new GameService().getTop5GamesList();
        gameLogo_1.setIcon(new ImageIcon(top5GameList.get(0).getBannerURL()));
        gameLogo_2.setIcon(new ImageIcon(top5GameList.get(1).getBannerURL()));
        gameLogo_3.setIcon(new ImageIcon(top5GameList.get(2).getBannerURL()));
        gameLogo_4.setIcon(new ImageIcon(top5GameList.get(3).getBannerURL()));
        gameLogo_5.setIcon(new ImageIcon(top5GameList.get(4).getBannerURL()));
        gameDesc_1.setText(top5GameList.get(0).getName());
        gameDesc_2.setText(top5GameList.get(1).getName());
        gameDesc_3.setText(top5GameList.get(2).getName());
        gameDesc_4.setText(top5GameList.get(3).getName());
        gameDesc_5.setText(top5GameList.get(4).getName());
    }
    public void removeGameInfo_Price_ButtonListener() {
        ActionListener[] actionListeners = gameInfo_Price_Button.getActionListeners();
        for (ActionListener al : actionListeners) {
            gameInfo_Price_Button.removeActionListener(al);
        }
    }
    private void gameInfoDisplay(Game game) {
        removeGameInfo_Price_ButtonListener();
        if (new AccountService().checkGameOwned(account.getUsername(), game.getGameId())) {
            gameInfo_Price_Button.setText("Owned");
            gameInfo_Price_Button.setBackground(Color.decode("#5D5F64"));
            gameInfo_Price_Button.setEnabled(false);
        } else {
            Integer primeStatus = new AccountService().getPrimeStatus(account.getUsername());
            Double gamePrice;
            if (primeStatus == 1) {
                gamePrice = Math.ceil(game.getPrice()) * 0.8;
            } else {
                gamePrice = Math.ceil(game.getPrice());
            }
            gameInfo_Price_Button.setEnabled(true);
            gameInfo_Price_Button.setBackground(Color.decode("#58B058"));
            gameInfo_Price_Button.setText(gamePrice + " VND");
            gameInfo_Price_Button.addActionListener(e -> {
                if (new AccountService().getBalance(account.getUsername()) >= gamePrice) {
                    Integer userID = new AccountService().getUserID(account.getUsername());
                    new AccountService().buyGame(userID, game.getGameId(), gamePrice);
                    gameInfo_Price_Button.setText("Owned");
                    gameInfo_Price_Button.setBackground(Color.decode("#5D5F64"));
                    gameInfo_Price_Button.setEnabled(false);
                    parentFrame.initAccountInfomation();
                    controlLibraryPanel.initGameOwnedTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Not enough balance to buy this game");
                }
            });
        }
        gameInfo_Logo.setIcon(new ImageIcon(game.getBannerURL()));
        gameInfo_Name.setText(game.getName());
        gameInfo_Genre.setText("Genre: " + game.getGenre());
        showGameInfoPanel();
    }
    public void showGameInfoPanel() {
        gameInformation.setVisible(true);
        gameInfoHeader.setVisible(true);
    }
    public void hideGameInfoPanel() {
        gameInformation.setVisible(false);
        gameInfoHeader.setVisible(false);
    }
    public Object[][] getGameList() {
        Object[][] gameData = new Object[gameList.size()][2];

        for (int i = 0; i < gameList.size(); i++) {
            gameData[i][0] = new ImageIcon(gameList.get(i).getBannerURL());

            // HTML formatting with bold game name and larger font size for the name
            String multiLineText = "<html></b><br/><b style='font-size: larger;'>" + gameList.get(i).getName() +
                    "</b><br/> </b><br/>" + gameList.get(i).getDescription() + "</html>";

            gameData[i][1] = multiLineText;
        }

        return gameData;
    }
    public Object[][] getSearchedGameList(String name, String genre) {
        searchedGameList = new GameService().searchGame(name, genre);
        Object[][] gameData = new Object[searchedGameList.size()][2];
        for (int i = 0; i < searchedGameList.size(); i++) {
            gameData[i][0] = new ImageIcon(searchedGameList.get(i).getBannerURL());


            String multiLineText = "<html></b><br/><b style='font-size: larger;'>" + searchedGameList.get(i).getName() +
                    "</b><br/> </b><br/>" + searchedGameList.get(i).getDescription() + "</html>";

            gameData[i][1] = multiLineText;
        }
        return gameData;
    }
}
