import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class GameGrid extends JPanel {

    private GridBagConstraints gridBagConstraints;
    private GridBagLayout gridBagLayout;
    private PlayerMark currentPlayerMark;
    private PlayerType currentPlayerType;

    public enum PlayerMark {
        PLAYER_X,
        PLAYER_O,
    }

    public enum PlayerType {
        HUMAN_PLAYER,
        COMPUTER_PLAYER
    }

    public GameGrid() {
        this.currentPlayerMark = PlayerMark.PLAYER_X;
        this.currentPlayerType = PlayerType.HUMAN_PLAYER;
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
        gridBagConstraints.gridx = rowNumber;
        gridBagConstraints.gridy = colNumber;
        currentGameCell.markCell(player);
        this.add(currentGameCell, gridBagConstraints);
    }

    public boolean isGameCellEmpty(int rowNumber, int colNumber) {
        GameCell currentGameCell = this.findGameCell(rowNumber, colNumber);
        return currentGameCell.isEmpty();
    }

    public PlayerMark getCurrentPlayerMark() {
        return currentPlayerMark;
    }

    public void setCurrentPlayerMark(PlayerMark currentPlayerMark) {
        this.currentPlayerMark = currentPlayerMark;
    }

    public void playerTurn() {

    }

    public void computerTurn() {
        this.markGameCell(0, 0, "O");
    }

    public boolean isGameOver() {
        return this.doesRowContainEndState() ||
                this.doesColumnContainEndState() ||
                this.doesDiagonalContainEndState() ||
                this.isDraw();
    }

    private boolean doesRowContainEndState() {
        boolean firstRow = this.isEndStatePresentInThreeGameCells(new int[]{0, 0, 0, 1, 0, 2});
        boolean secondRow = this.isEndStatePresentInThreeGameCells(new int[]{1, 0, 1, 1, 1, 2});
        boolean thirdRow = this.isEndStatePresentInThreeGameCells(new int[]{2, 0, 2, 1, 2, 2});
        return firstRow || secondRow || thirdRow;
    }

    private boolean doesColumnContainEndState() {
        boolean firstColumn = this.isEndStatePresentInThreeGameCells(new int[]{0, 0, 1, 0, 2, 0});
        boolean secondColumn = this.isEndStatePresentInThreeGameCells(new int[]{0, 1, 1, 1, 2, 1});
        boolean thirdColumn = this.isEndStatePresentInThreeGameCells(new int[]{0, 2, 1, 2, 2, 2});
        return firstColumn || secondColumn || thirdColumn;
    }

    private boolean doesDiagonalContainEndState() {
        boolean firstDiagonal = this.isEndStatePresentInThreeGameCells(new int[]{0, 0, 1, 1, 2, 2});
        boolean secondDiagonal = this.isEndStatePresentInThreeGameCells(new int[]{0, 2, 1, 1, 2, 0});
        return firstDiagonal || secondDiagonal;
    }

    public boolean isDraw() {
        boolean doesRowContainEndState = this.doesRowContainEndState();
        boolean doesColumnContainEndState = this.doesColumnContainEndState();
        boolean doesDiagonalContainEndState = this.doesDiagonalContainEndState();
        boolean areAllGameCellsMarked = this.areAllGameCellsMarked();
        return !doesRowContainEndState && !doesColumnContainEndState && !doesDiagonalContainEndState && areAllGameCellsMarked;
    }

    private boolean areAllGameCellsMarked() {
        boolean allGameCellsMarked = true;
        for (Component component : this.getComponents()) {
            JPanel panel = (JPanel) component;
            if (panel.getComponents().length == 0) {
                allGameCellsMarked = false;
                break;
            }
        }
        return allGameCellsMarked;
    }

    private boolean isEndStatePresentInThreeGameCells(int[] gridPositionsArray) {
        int firstRow = gridPositionsArray[0];
        int firstColumn = gridPositionsArray[1];
        int secondRow = gridPositionsArray[2];
        int secondColumn = gridPositionsArray[3];
        int thirdRow = gridPositionsArray[4];
        int thirdColumn = gridPositionsArray[5];

        GameCell first = this.findGameCell(firstRow, firstColumn);
        GameCell second = this.findGameCell(secondRow, secondColumn);
        GameCell third = this.findGameCell(thirdRow, thirdColumn);

        String firstMark = first.getPlayerMark();
        String secondMark = second.getPlayerMark();
        String thirdMark = third.getPlayerMark();

        return firstMark.equals(secondMark) && secondMark.equals(thirdMark) && !firstMark.equals("");
    }

    public PlayerType getCurrentPlayerType() {
        return currentPlayerType;
    }

    public void setCurrentPlayerType(PlayerType currentPlayerType) {
        this.currentPlayerType = currentPlayerType;
    }
}