import java.util.Random;

/**
 * Created by Jonathan on 10/28/17.
 */
public class EasyGameStrategy implements GameStrategy, MakeMoveListener {

    private Random random;
    private GameEndListener gameEndListener;

    public EasyGameStrategy(GameEndListener gameEndListener) {
        this.gameEndListener = gameEndListener;
        random = new Random();
    }


    @Override
    public void onMakeMoveEvent(GameGrid gameGrid) {
        System.out.println("Computer turn!");
        makeMove(gameGrid);
    }


    public void makeMove(GameGrid gameGrid) {
        if (!gameGrid.isGameOver()) {
            if (gameGrid.getCurrentPlayerType() == GameGrid.PlayerType.COMPUTER_PLAYER) {
                int row = random.nextInt(3) + 0;
                int column = random.nextInt(3) + 0;
                while (!gameGrid.isGameCellEmpty(row, column)) {
                    row = random.nextInt(3) + 0;
                    column = random.nextInt(3) + 0;
                }
                if (gameGrid.getCurrentPlayerMark() == GameGrid.PlayerMark.PLAYER_O) {
                    gameGrid.markGameCell(row, column, "O");
                    gameGrid.setCurrentPlayerMark(GameGrid.PlayerMark.PLAYER_X);
                } else {
                    gameGrid.markGameCell(row, column, "X");
                    gameGrid.setCurrentPlayerMark(GameGrid.PlayerMark.PLAYER_O);
                }
                gameGrid.setCurrentPlayerType(GameGrid.PlayerType.HUMAN_PLAYER);
            }
        } else {
            gameEndListener.onGameEnd();
        }

    }
}
