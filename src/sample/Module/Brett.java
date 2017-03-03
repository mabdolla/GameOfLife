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
        brett[0][1]=1;
        brett[0][2]=1;
        brett[1][1]=1;
        brett[1][2]=1;
        brett[1][3]=1;




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
        if (isAlive==0 && naboer == 0)
            return 0;
        if (isAlive==0 && naboer == 1)
            return 0;
        if (isAlive==0 && naboer == 2)
            return 0;
        if (isAlive==0 && naboer == 3)
            return 1;
        if(isAlive==0 && naboer >= 4)
            return 0;
        if(isAlive==1 && naboer <= 1)
            return 0;
        if(isAlive==1 && naboer == 2)
            return 1;
        if(isAlive==1 && naboer == 3)
            return 1;
        if(isAlive==1 && naboer >= 4)
            return 0;

        return 0;
    }


    public int getNeighbours(int x, int y){
        //x = rader
        //y = kolonner

        int antallNaboer = 0;

        if (!(x-1==-1 || y-1==-1) && brett[x-1][y-1] == 1)antallNaboer++; // Oppe venstre
        if (!(y-1==-1) && brett[x][y-1] == 1)antallNaboer++; //Oppe midten
        if (!(x+1==50 || y-1==-1) && brett[x+1][y-1] == 1)antallNaboer++; //Oppe høyre
        if (!(x+1==50 || y+1==50) && brett[x+1][y+1] == 1)antallNaboer++; //Nede høyre
        if (!(x-1==-1 || y+1==50) && brett[x-1][y+1] == 1)antallNaboer++;  //Nede venstre
        if (!(x+1==50) && brett[x+1][y] == 1)antallNaboer++;   //Midten høyre
        if (!(y+1==50) && brett[x][y+1] == 1)antallNaboer++;   //Nede midten
        if (!(x-1==-1)&& brett[x-1][y] == 1)antallNaboer++;    //Venstre midten

        if (antallNaboer>0)
            System.out.println(x+","+y+" har "+antallNaboer+" naboer");


       try {

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

