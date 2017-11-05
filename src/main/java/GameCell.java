import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GameCell extends JPanel {

    private List<MakeMoveListener> makeMoveListeners = new ArrayList<>();
    private List<GameEndListener> gameEndListeners = new ArrayList<>();

    public GameCell(GameStrategy strategy, GameEngine engine) {
        makeMoveListeners.add((MakeMoveListener) strategy);
        gameEndListeners.add(engine);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                GameCell cellPane = (GameCell) e.getSource();
                GameGrid gameGrid = (GameGrid) cellPane.getParent();
                if (!gameGrid.isGameOver()) {
                    if (cellPane.isEmpty()) {
                        if (gameGrid.getCurrentPlayerType() == GameGrid.PlayerType.HUMAN_PLAYER) {
                            if (gameGrid.getCurrentPlayerMark() == GameGrid.PlayerMark.PLAYER_X) {
                                cellPane.markCell("X");
                                gameGrid.setCurrentPlayerMark(GameGrid.PlayerMark.PLAYER_O);
                            } else {
                                cellPane.markCell("O");
                                gameGrid.setCurrentPlayerMark(GameGrid.PlayerMark.PLAYER_X);
                            }
                            gameGrid.setCurrentPlayerType(GameGrid.PlayerType.COMPUTER_PLAYER);
                        }
                        cellPane.revalidate();
                        cellPane.repaint();
                        for (MakeMoveListener makeMoveListener : makeMoveListeners) {
                            makeMoveListener.onMakeMoveEvent(gameGrid);
                        }
                    }
                } else {
                    for (GameEndListener gameEndListener : gameEndListeners) {
                        gameEndListener.onGameEnd();
                    }
                }
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 100);
    }

    public boolean isEmpty() {
        return this.getComponents().length == 0;
    }

    public void markCell(String player) {
        if (this.isEmpty()) {
            JLabel label = new JLabel(player, SwingConstants.LEFT);
            label.setFont(new Font("Verdana", 1, 80));
            label.setForeground(Color.BLACK);
            this.add(label);
        }
    }

    public String getPlayerMark() {
        if (this.getComponents().length > 0) {
            Component component = this.getComponent(0);
            if (component != null) {
                JLabel label = (JLabel) component;
                return label.getText();
            } else {
                return "";
            }
        } else {
            return "";
        }
    }
}