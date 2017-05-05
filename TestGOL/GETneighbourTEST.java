import org.junit.Assert;
import org.junit.Test;
import sample.Board.StaticBoard;

/**
 * @Test for getneighbours method in Staticboard class.
 */
public class GETneighbourTEST {

    StaticBoard staticBoard = new StaticBoard();



    /**
     * @result Result will return that cell in x position: 3, y position :1 have 0 neighbours
     *
     */
    @Test
    public void neighbourtest0(){


        int[][] board1 = {
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        };

        //x = row placement of cell
        //y = rows placement for cell

        staticBoard.setBoard(board1);

        Assert.assertEquals(0, staticBoard.getNeighbours(3,1));
    }


    /**
     * @result Result will return that cell in x position: 1, y position :1 have 1 alive neighbours
     *
     */
    @Test
    public void neighbourtest1(){


        int[][] board1 = {
                { 0, 0, 0, 0 },
                { 0, 1, 0, 0 },
                { 0, 1, 0, 0 },
                { 0, 0, 0, 0 }
        };

        //x = columns
        //y = rows

        staticBoard.setBoard(board1);

        Assert.assertEquals(1, staticBoard.getNeighbours(1,1));
    }


    /**
     * @result Result will return that cell in x position: 1, y position :2 have 8 alive neighbours
     *
     */
    @Test
    public void neighbourtest3(){

        int[][] board1 = {
                { 0, 1, 1, 1 },
                { 0, 1, 0, 1 },
                { 0, 1, 1, 1 },
                { 0, 0, 0, 0 }
        };

        //x = columns
        //y = rows

        staticBoard.setBoard(board1);

        Assert.assertEquals(8, staticBoard.getNeighbours(1,2));
    }
}
