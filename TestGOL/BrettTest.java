import org.junit.Assert;
import org.junit.Test;
import sample.Board.Brett;


/**
 * Test Class Game of Life
 **/
public class BrettTest  {



    @Test
    public void testNextGenerationEqual(){
        Brett brett = new Brett(4,4);

        int[][] board = {
                { 0, 0, 0, 0 },
                { 0, 1, 1, 0 },
                { 0, 1, 1, 0 },
                { 0, 0, 0, 0 }
        };

        brett.setBrett(board);
        brett.nextGeneration();
        Assert.assertEquals("0000011001100000", brett.toString());

    }


    @Test
    public void testBrett2(){
        Brett testBrett2 = new Brett(4,4);

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

        testBrett2.setBrett(board);
        testBrett2.nextGeneration();
        Assert.assertArrayEquals(board2,testBrett2.getBrett());
        Assert.assertEquals("0010001000100000", testBrett2.toString());

    }


}


