package model;

import Tetriminos.Tetrimino;
import exception.CantMoveException;
import exception.CantRotException;

/*
"o"=1=Yellow
"z"=2=red
"s"=3=pink
"t"=4=green
"l"=5=orange
"j"=6=blue
"i"=7=cyan
 */

public class Pieces {
    private Tetrimino actualP;
    private Engine engine;
    private SBG sbg;
    private boolean inGame;

    public Pieces(Engine e){
        this.engine=e;
        sbg= new SBG();
        this.inGame=true;
        nextPiece();
    }

    public void nextStep(){
        int[][] pf= engine.getPlayField();
        Coordinates[] coords= actualP.getCopyCoord();
        try {
            actualP.nextStep(pf);
            pf=cleanPiece(pf,coords);
            pf=drawPiece(pf,actualP.getCoord());
            engine.setPlayField(pf);
        } catch (CantMoveException e) {
            nextPiece();
        }
    }

    public void fastSteps() {
        boolean flag=true;
        int[][] pf= engine.getPlayField();
        Coordinates[] coords= actualP.getCopyCoord();
        while(flag){
            try {
                actualP.nextStep(pf);
            } catch (CantMoveException e) {
                pf=cleanPiece(pf,coords);
                pf=drawPiece(pf,actualP.getCoord());
                flag=false;
                engine.setPlayField(pf);
                nextPiece();
            }
        }
    }

    public void rot(String direction){
        int[][] pf= engine.getPlayField();
        try {
            Coordinates[] coords= actualP.getCopyCoord();
            if (direction.equals("clockwise"))
                this.actualP.rotDer(pf);
            else
                this.actualP.rotIzq(pf);
            pf=cleanPiece(pf,coords);
            pf=drawPiece(pf,actualP.getCoord());
            engine.setPlayField(pf);
        }catch (CantRotException e) {
            Coordinates[] coords=actualP.getCopyCoord();
            this.actualP.wallKick(pf);
            pf=cleanPiece(pf,coords);
            pf=drawPiece(pf,actualP.getCoord());
            engine.setPlayField(pf);
            rot(direction);
        }
    }

    public void move(String direction){
        int[][] pf= engine.getPlayField();
        Coordinates[] coords=actualP.getCopyCoord();
        try {
            actualP.move(pf,direction);
            pf=cleanPiece(pf,coords);
            pf=drawPiece(pf,actualP.getCoord());
            engine.setPlayField(pf);
        } catch (CantMoveException e) {
            nextPiece();
        }
    }

    public void nextPiece() {
        checkMat();
        if(this.inGame) {
            actualP = sbg.drawPiece();
            actualP.spawn();
            int[][]pf=drawPiece(engine.getPlayField(),actualP.getCoord());
            engine.setPlayField(pf);
            nextStep();
        }
    }
    private void checkMat()  {
        int[][] pf= engine.getPlayField();
        boolean line;
        int j;
        for (int i=1;i<22;i++){
            j=0;
            line=true;
            while(line&&j<10){
                if(pf[i][j++]==0)
                    line=false;
            }
            if(line){
                for(int y=i;y>0;y--){
                    for(int x=0;x<10;x++){
                        pf[y][x]=pf[y-1][x];
                    }
                }
            }
        }
        for(j=0;j<10;j++){
            if(pf[0][j]!=0 || pf[1][j]!=0)
                this.inGame=false;
        }
        engine.setPlayField(pf);
    }

    public Tetrimino getActualP() {
        return actualP;
    }

    public boolean isInGame(){
        return this.inGame;
    }

    private int[][]  drawPiece(int[][] pf, Coordinates coord[]){
        for (int i=0; i<4;i++){
            pf[coord[i].getI()][coord[i].getJ()]=actualP.getNumberpiece();
        }
        return pf;
    }

    private int[][] cleanPiece(int[][] pf,Coordinates coord[]){
        for (int i=0; i<4;i++){
            pf[coord[i].getI()][coord[i].getJ()]=0;
        }
        return pf;
    }

}