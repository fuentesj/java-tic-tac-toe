import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Jonathan on 10/24/17.
 */
public class GameGridTest {

    private GameGrid gameGrid;

    @Before
    public void setUp() {
        gameGrid = new GameGrid();
    }

    @Test
    public void isGameOverWhenThereAreThreeMarksInOneRow() {
        gameGrid.markGameCell(0,0, "X");
        gameGrid.markGameCell(0, 1, "X");
        gameGrid.markGameCell(0, 2, "X");
        assertTrue(gameGrid.isGameOver());
    }

    @Test
    public void isGameOverWhenThereAreThreeMarksInOneColumn() {
        gameGrid.markGameCell(0,0, "X");
        gameGrid.markGameCell(1, 0, "X");
        gameGrid.markGameCell(2, 0, "X");
        assertTrue(gameGrid.isGameOver());
    }

    @Test
    public void isGameOverWhenThereAreThreeMarksInOneDiagonal() {
        gameGrid.markGameCell(0,0, "X");
        gameGrid.markGameCell(1, 1, "X");
        gameGrid.markGameCell(2, 2, "X");
        assertTrue(gameGrid.isGameOver());
    }

    @Ignore("This test needs to be fixed")
    @Test
    public void isGameOverWhenThereIsDraw() {
        gameGrid.markGameCell(0,0, "O");
        gameGrid.markGameCell(1, 1, "O");
        gameGrid.markGameCell(2, 2, "X");

        gameGrid.markGameCell(0,0, "O");
        gameGrid.markGameCell(1, 1, "X");
        gameGrid.markGameCell(2, 2, "X");

        gameGrid.markGameCell(0,0, "O");
        gameGrid.markGameCell(1, 1, "X");
        gameGrid.markGameCell(2, 2, "O");
        assertTrue(gameGrid.isDraw());
    }
}
