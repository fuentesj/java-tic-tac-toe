import java.util.Random;

/**
 * Created by Jonathan on 10/28/17.
 */
public class EasyGameStrategy implements GameStrategy {

    private Random random;

    public EasyGameStrategy() {
        random = new Random();
    }

    public void makeMove(GameGrid gameGrid) {
        if (gameGrid.getCurrentPlayerType() == GameGrid.PlayerType.COMPUTER_PLAYER) {
            int row = random.nextInt(3) + 0;
            int column = random.nextInt(3) + 0;
            while (!gameGrid.isGameCellEmpty(row, column)) {
                row = random.nextInt(3) + 0;
                column = random.nextInt(3) + 0;
            }
            gameGrid.markGameCell(row, column, "O");
            gameGrid.setCurrentPlayerType(GameGrid.PlayerType.HUMAN_PLAYER);
            gameGrid.setCurrentPlayerMark(GameGrid.PlayerMark.PLAYER_X);
            gameGrid.revalidate();
            gameGrid.repaint();
        }
    }
}
