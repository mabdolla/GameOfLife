import org.junit.Assert;
import org.junit.Test;
import sample.Board.StaticBoard;


/**
 * The Game Of Life application created for HIOA
 * The Test Class contains tests to ensure the application runs
 * as expected.
 * The class extends StaticBoard
 * <p>
 * @author Fredrik, Hans Jacob, Mohammad
 * Studentnr : S309293, s305064, s309856
 **/
public class StaticBoardTest extends StaticBoard {

    /**
     * Test Class Game of Life
     * testNextGenerationEqual  tests to see if the expected result corresponds to the array above it.
     **/
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

    /**
     * The testBrett2 tests to see if "board2" will display
     * a horizontal row of three living cells when "board" has three vertical living cells, as that is the expected result.
     **/
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

    /**
     * The code tests the next generation of the commonly used glider.
     * TestBrett3 tests to see if the expected result corresponds to the array above it
     **/
    @Test
    public void testBrett3() {
        StaticBoard testBrett3 = new StaticBoard(6, 6);

        int[][] board3 = {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0},
                {0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };

        int[][] board4 = {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0},
                {0, 0, 1, 1, 0, 0},
                {0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };

        testBrett3.setBrett(board3);
        testBrett3.nextGeneration();
        Assert.assertArrayEquals(board4, testBrett3.getBrett());
        Assert.assertEquals("000000001000000110001100000000000000", testBrett3.toString());
    }

    /**
     * The following code tests one single cell.
     * The rules state that a single cell with no neighbours shall "die" of loneliness.
     * TestBrett4 tests to see if the expected result corresponds to the array above it.
     **/
    @Test
    public void testBrett4(){
        StaticBoard testBrett4 = new StaticBoard(3,3);

        int[][] board5 = {
                { 0, 0, 0},
                { 0, 1, 0},
                { 0, 0, 0}

        };

        int[][] board6 = {
                { 0, 0, 0},
                { 0, 0, 0},
                { 0, 0, 0}
        };

        testBrett4.setBrett(board5);
        testBrett4.nextGeneration();
        Assert.assertArrayEquals(board6,testBrett4.getBrett());
        Assert.assertEquals("000000000", testBrett4.toString());

    }

    /**
     * This test is supposed to fail.
     **/

    @Test
    public void testBrett5(){
        StaticBoard testBrett5 = new StaticBoard(3,3);

        int[][] board7 = {
                { 1, 1, 1},
                { 1, 0, 1},
                { 1, 1, 1}

        };

        int[][] board8 = {
                { 1, 1, 1},
                { 1, 1, 1},
                { 1, 1, 1}
        };

        testBrett5.setBrett(board7);
        testBrett5.nextGeneration();
        Assert.assertArrayEquals(board8,testBrett5.getBrett());
        Assert.assertEquals("111111111", testBrett5.toString());

    }


}


