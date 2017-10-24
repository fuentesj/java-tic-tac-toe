import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class GameGrid extends JPanel {

    private GridBagConstraints gridBagConstraints;
    private GridBagLayout gridBagLayout;
    private Player currentPlayer;

    public enum Player {
        xPlayer,
        oPlayer,
    }

    public GameGrid() {
        this.currentPlayer = Player.xPlayer;
        this.gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        this.gridBagConstraints = new GridBagConstraints();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                gridBagConstraints.gridx = col;
                gridBagConstraints.gridy = row;

                GameCell cellPane = new GameCell();
                Border border = null;
                if (row < 2) {
                    if (col < 2) {
                        border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
                    }
                } else {
                    if (col < 2) {
                        border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                    }
                }
                cellPane.setBorder(border);
                cellPane.setLayout(new GridBagLayout());
                add(cellPane, gridBagConstraints);
            }
        }
    }



    private GameCell findGameCell(int rowNumber, int colNumber) {
        GameCell match = null;
        for (Component current : getComponents()) {
            GridBagConstraints currentGridBagConstraints = this.gridBagLayout.getConstraints(current);
            if (currentGridBagConstraints.gridx == rowNumber && currentGridBagConstraints.gridy == colNumber) {
                match = (GameCell) current;
                break;
            }
        }
        return match;
    }

    public void markGameCell(int rowNumber, int colNumber, String player) {
        GameCell currentGameCell = this.findGameCell(rowNumber, colNumber);
        if (currentGameCell.isEmpty()) {
            gridBagConstraints.gridx = rowNumber;
            gridBagConstraints.gridy = colNumber;
            currentGameCell.markCell(player);
            add(currentGameCell, gridBagConstraints);
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean isGameOver() {
        return false;
    }

    public void playerTurn() {

    }

    public void computerTurn() {
        this.markGameCell(0, 0, "O");
    }
}