import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jonathan on 10/22/17.
 */
public class GameEngine implements GameEndListener {

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

    public void configureGameEngine(Map<String, String> configurationMap) {
        this.strategy = new EasyGameStrategy(this);
        initializeGameFrame(this.strategy, configurationMap);
        buildGameMenu();
        displayGameFrame();
    }

    public void configureGameEngine() {
        this.strategy = new EasyGameStrategy(this);
        initializeGameFrame(strategy, new HashMap<String, String>());
        buildGameMenu();
        displayGameFrame();
    }

    private void displayGameFrame() {
        this.frame.add(this.primaryPanel);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    private void initializeGameFrame(GameStrategy strategy, Map<String, String> configurationMap) {
        this.primaryPanel = new JPanel();
        this.frame = new JFrame("Tic Tac Toe");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (configurationMap != null && configurationMap.containsKey("selectedPlayerMark")) {
            this.gameGrid = new GameGrid(strategy, this, configurationMap.get("selectedPlayerMark"));
        } else {
            this.gameGrid = new GameGrid(strategy, this);
        }
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
                final Map<String, String> configurationMap = new HashMap<String, String>();
                configurationMap.put("selectedDifficulty", gameOptions.getSelectedPlayerDifficulty());
                configurationMap.put("selectedPlayerMark", gameOptions.getSelectedPlayerMark());

                SwingWorker worker = new SwingWorker<Boolean, Integer>() {
                    @Override
                    public Boolean doInBackground() {
                        resetGameEngine(configurationMap);
                        return true;
                    }
                };
                worker.execute();
            }
        });

        this.toplevelmenu.add(menuItem);
        this.reloadGameMenuItem = new JMenuItem("Reload Game");
        this.toplevelmenu.add(this.reloadGameMenuItem);
        this.saveMenuItem = new JMenuItem("Save Game");
        toplevelmenu.add(saveMenuItem);
        this.frame.setJMenuBar(menuBar);
    }

    private void resetGameEngine(Map<String, String> configurationMap) {
        this.frame.dispose();
        configureGameEngine(configurationMap);
    }

    private void resetGameEngine() {
        this.frame.dispose();
        this.configureGameEngine(new HashMap<String, String>());
    }

    public void onGameEnd() {
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