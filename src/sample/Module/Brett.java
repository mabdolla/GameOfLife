package sample.Module;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Brett {
    public GraphicsContext gc;
    private int rad ;
    private int kolonne;
    private int[][] brett;
    private int celleSTR = 15;

    public Brett(int rad, int kolonne, GraphicsContext gc) {
        this.rad=rad;
        this.kolonne=kolonne;
        this.gc=gc;
        brett = new int [rad][kolonne];
        /*brett = new int[][]
                {{0,0,1,0,0,0,0},
                {0,0,1,0,0,0,0},
                {0,0,1,0,0,0,0}};
        */
        testArray();
        draw();


    }

    public void testArray(){
        brett[1][1]=1;
        brett[1][0]=1;
        brett[0][1]=1;
        brett[1][12]=1;
        brett[12][1]=1;
    }

    public void background() {
        /*for (int j = 0; j < brett.length; j++) {
            for (int i = 0; i < brett[0].length; i++) {
                gc.setFill(Color.DARKGRAY);
                gc.fillRect(j*celleSTR,i*celleSTR,celleSTR-1,celleSTR-1);
            }
        }*/
        //lagSpillebrett();

        gc.setFill(Color.YELLOW);
        gc.fillRect(0,0,brett.length*celleSTR,brett[0].length*celleSTR);
    }

    public void draw() {
        background();
        for (int j = 0; j < brett.length; j++) {
            for (int i = 0; i < brett[0].length; i++) {
                if (brett[j][i]==1){
                    gc.setFill(Color.BLACK);
                    gc.fillRect(j*celleSTR,i*celleSTR,celleSTR-1,celleSTR-1);
                }else{
                    gc.setFill(Color.WHITE);
                    gc.fillRect(j*celleSTR,i*celleSTR,celleSTR-1,celleSTR-1);
                }
            }
        }
    }

    public void nextGeneration(){
        int[][] nesteBrett = new int[rad][kolonne];

        //beregning
        for (int x = 0; x < brett.length; x++) {
            for (int y = 0; y < brett[0].length; y++) {
                nesteBrett[x][y] = setCellRules(brett[x][y],getNeighbours(x,y));
            }
        }


        brett = nesteBrett;
        nesteBrett = null;

        draw();
    }

    public int setCellRules(int isAlive,int naboer){
        if (isAlive==1 && naboer <= 3)
            return 0;

        if (isAlive==1 && naboer == 0)
            return 0;

        return 0;
    }


    public int getNeighbours(int x, int y){

        int antallNaboer = 0;

        if (!(x-1==-1 || y-1==-1) && brett[x-1][y-1] == 1)antallNaboer++; // husk rekkefølge, på &&, det som er T.V stemmer., legg in betingelser (løkker) for å ungå at programmet skal aksessere utenfor brettet.

        if (antallNaboer>0)
            System.out.println(x+","+y+" har "+antallNaboer+" naboer");


       try {
           if (brett[x-1][y] == 1)antallNaboer++;//
           if (brett[x-1][y+1] == 1)antallNaboer++;
           if (brett[x][y-1] == 1)antallNaboer++;
           if (brett[x][y+1] == 1)antallNaboer++;
           if (brett[x+1][y-1] == 1)antallNaboer++;
           if (brett[x+1][y] == 1)antallNaboer++;
           if (brett[x+1][y+1] == 1)antallNaboer++;
       } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("FEILET på "+x+", "+y+" : UTTAFOR");
        }
        return antallNaboer;
    }




    public void setCelleSTR (int CSTR) {

        this.celleSTR=CSTR;
    }

    public int getCelleSTR () {

        return celleSTR;
    }

}

