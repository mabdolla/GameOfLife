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
        System.out.println(brett.toString());
        brett.nextGeneration();
        System.out.println(brett.toString());
        Assert.assertArrayEquals(board, brett.getBrett());

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
        Assert.assertEquals(0000000000000000, testBrett2.toString());

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


