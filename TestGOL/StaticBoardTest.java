import org.junit.Assert;
import org.junit.Test;
import sample.Board.StaticBoard;


/**
 * Test Class Game of Life
 **/
public class StaticBoardTest {


    @Test
    public void testNextGenerationEqual(){
        StaticBoard staticBoard = new StaticBoard(4,4);

        int[][] board = {
                { 0, 0, 0, 0 },
                { 0, 1, 1, 0 },
                { 0, 1, 1, 0 },
                { 0, 0, 0, 0 }
        };

        staticBoard.setBrett(board);
        staticBoard.nextGeneration();
        Assert.assertEquals("0000011001100000", staticBoard.toString());

    }


    @Test
    public void testBrett2(){
        StaticBoard testStaticBoard2 = new StaticBoard(4,4);

        int[][] board = {
                { 0, 0, 0, 0 },
                { 0, 1, 0, 0 },
                { 0, 1, 0, 0 },
                { 0, 1, 0, 0 }
        };

        int[][] board2 = {
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 1, 1, 1, 0 },
                { 0, 0, 0, 0 }
        };

        testStaticBoard2.setBrett(board);
        testStaticBoard2.nextGeneration();
        Assert.assertArrayEquals(board2, testStaticBoard2.getBrett());
        Assert.assertEquals("0010001000100000", testStaticBoard2.toString());

    }

    /*
    @Test
    //Test utvidelse av brett expand method
*/

}


