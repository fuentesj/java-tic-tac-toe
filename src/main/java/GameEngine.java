import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jonathan on 10/22/17.
 */
public class GameEngine {

    public GameEngine() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {

                JPanel primaryPanel = new JPanel();
                final JFrame frame = new JFrame("Tic Tac Toe");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                GameGrid gameGrid = new GameGrid();
                primaryPanel.add(gameGrid);

                JMenu toplevelmenu = new JMenu("File");
                JMenuBar menuBar = new JMenuBar();
                menuBar.add(toplevelmenu);
                final JMenuItem menuItem = new JMenuItem("Options");
                menuItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        GameOptions gameOptions = new GameOptions();
                        JOptionPane.showMessageDialog(null, gameOptions);
                    }
                });

                toplevelmenu.add(menuItem);
                JMenuItem reloadGameMenuItem = new JMenuItem("Reload Game");
                toplevelmenu.add(reloadGameMenuItem);
                JMenuItem saveMenuItem = new JMenuItem("Save Game");
                toplevelmenu.add(saveMenuItem);

                frame.setJMenuBar(menuBar);
                frame.add(primaryPanel);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
