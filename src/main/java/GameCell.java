import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameCell extends JPanel {

    public GameCell() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                GameCell cellPane = (GameCell) e.getSource();
                if (cellPane.isEmpty()) {
                    GameGrid gameGrid = (GameGrid) cellPane.getParent();
                    if (gameGrid.getCurrentPlayer() == GameGrid.Player.xPlayer) {
                        cellPane.markCell("X");
                    } else {
                        cellPane.markCell("O");
                    }
                    cellPane.revalidate();
                    cellPane.repaint();
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
        JLabel label = new JLabel(player, SwingConstants.LEFT);
        label.setFont(new Font("Verdana", 1, 80));
        label.setForeground(Color.BLACK);
        this.add(label);
    }
}