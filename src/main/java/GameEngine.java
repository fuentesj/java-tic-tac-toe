import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.lang.reflect.Method;

/**
 * Created by Jonathan on 10/22/17.
 */
public class GameEngine {

    private GameStrategy strategy;
    private GameGrid gameGrid;
    private JFrame frame;
    private JPanel primaryPanel;
    private JMenu toplevelmenu;
    private JMenuBar menuBar;
    private JMenuItem menuItem;
    private JMenuItem reloadGameMenuItem;
    private JMenuItem saveMenuItem;

    public GameEngine() {

    }

    public void configureGameEngine() {
        this.strategy = new EasyGameStrategy();
        initializeGameFrame();
        buildGameMenu();
        displayGameFrame();
    }

    private void displayGameFrame() {
        this.frame.add(this.primaryPanel);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    private void initializeGameFrame() {
        this.primaryPanel = new JPanel();
        this.frame = new JFrame("Tic Tac Toe");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gameGrid = new GameGrid();
        this.primaryPanel.add(this.gameGrid);
    }

    private void buildGameMenu() {
        this.toplevelmenu = new JMenu("File");
        this.menuBar = new JMenuBar();
        this.menuBar.add(toplevelmenu);
        this.menuItem = new JMenuItem("Options");
        this.menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameOptions gameOptions = new GameOptions();
                JOptionPane.showMessageDialog(null, gameOptions);
                gameOptions.getSelectedPlayerDifficulty();
                gameOptions.getSelectedPlayerMark();
            }
        });

        this.toplevelmenu.add(menuItem);
        this.reloadGameMenuItem = new JMenuItem("Reload Game");
        this.toplevelmenu.add(this.reloadGameMenuItem);
        this.saveMenuItem = new JMenuItem("Save Game");
        toplevelmenu.add(saveMenuItem);
        this.frame.setJMenuBar(menuBar);
    }

    private void resetGameEngine() {
        this.frame.dispose();
        configureGameEngine();
        this.run();
    }

    public void run() {
        while (!this.gameGrid.isGameOver()) {
            this.strategy.makeMove(this.gameGrid);
        }

        Object[] options = {"Yes", "No"};
        int result = JOptionPane.showOptionDialog(null,
                "Game Over!\nNew Game?",
                "",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        switch (result) {
            case 0:
                resetGameEngine();
                System.out.println("Yes");
                break;
            case 1:
                System.exit(0);
                break;
        }
    }
}