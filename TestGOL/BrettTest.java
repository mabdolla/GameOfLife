import org.junit.Test;
import sample.ModuleTest.Brett;

/**
 * Test Class Game of Life
 **/
public class BrettTest  {



    @Test
    public void testNextGeneration(){
        Brett testBrett1 = new Brett(4,4, null);

        int[][] board = {
                { 0, 0, 0, 0 },
                { 0, 1, 1, 0 },
                { 0, 1, 1, 0 },
                { 0, 0, 0, 0 }
        };

        testBrett1.setBrett(board);
        testBrett1.nextGeneration();
        org.junit.Assert.assertEquals(0000011001100000, testBrett1.toString());

    }


    @Test
    public void testBrett2(){
        Brett testBrett2 = new Brett(4,4, null);

        int[][] board = {
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        };

        testBrett2.setBrett(board);
        testBrett2.nextGeneration();
        org.junit.Assert.assertEquals(0000000000000000, testBrett2.toString());

    }

    @Test
    public void String() {

        Brett brett = new Brett(4,4, null);

        int[][] board = {
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 1, 0, 0, 0 },
                { 0, 0, 0, 1 }
        };

        brett.setBrett(board);

        String msg = "";
        for (int kolonne = 0; kolonne < board.length; kolonne++) {
            for (int rad =0; rad < board[0].length; rad++) {
                if (board[rad][kolonne] == 0) {
                    msg = msg + "0";
                    System.out.println(msg);
                } else {
                    msg = msg + "1";
                    System.out.println(msg);
                }
            }
        }

    }
}


