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

            int row = random.nextInt(2) + 0;
            int column = random.nextInt(2) + 0;
            System.out.println("Computer turn");
            while (!gameGrid.markGameCell(row, column, "O")) {
                row = random.nextInt(2) + 0;
                column = random.nextInt(2) + 0;
            }
            gameGrid.setCurrentPlayerType(GameGrid.PlayerType.HUMAN_PLAYER);
            gameGrid.revalidate();
            gameGrid.repaint();
        }
    }
}
