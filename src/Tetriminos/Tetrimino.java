package Tetriminos;

import exception.CantMoveException;
import exception.CantRotException;
import model.Coordinates;

public abstract class Tetrimino {
    protected Coordinates [] coord;
    protected int center;
    protected int numberpiece;

    protected void initialize (){
        this.coord= new Coordinates[4];
        for(int i=0;i<4;i++){
            this.coord[i]=new Coordinates(0,0);
        }
    }

    public int getNumberpiece() {
        return numberpiece;
    }

    public abstract void spawn();

    public abstract void rotDer(int [][] pf) throws CantRotException;

    public abstract void rotIzq(int [][] pf) throws CantRotException;

    public Coordinates[] getCoord() {
        return coord;
    }

    public Coordinates[] getCopyCoord(){
        Coordinates[] coord = new Coordinates[4];
        for(int i=0;i<4;i++){
            coord[i]=new Coordinates(this.coord[i].getI(),this.coord[i].getJ());
        }
        return coord;
    }

    public void nextStep(int[][] pf) throws CantMoveException{
        if(canMoveDown(pf)){
            for(int x=0;x<4;x++)
                coord[x].sum(1,0);
        }else{
            throw new CantMoveException("asd");
        }
    }

    public void move(int[][] pf,String direction) throws CantMoveException{
        switch (direction){
            case"right":
                if(canMoveRight(pf)) {
                    for (int x = 0; x < 4; x++)
                        coord[x].sum(0, 1);
                }
                break;
            case"left":
                if(canMoveLeft(pf)) {
                    for (int x = 0; x < 4; x++)
                        coord[x].sub(0, 1);
                }
                break;
            case "down":
                nextStep(pf);
                break;
        }
    }
    public boolean wallKick(int[][] pf){
        boolean flag=false;
        if(canMoveLeft(pf)){
            flag=true;
            for (int x = 0; x < 4; x++)
                coord[x].sub(0, 1);
        }else{
            if(canMoveRight(pf)){
                flag=true;
                for (int x = 0; x < 4; x++)
                    coord[x].sum(0, 1);
            }
        }
        return flag;
    }

    private boolean canMoveDown(int[][]pf){
        boolean flag=true;
        int x =0;
        while(flag && x<4){
            Coordinates c = new Coordinates(coord[x].getI(),coord[x].getJ());
            if(c.getI()+1<pf.length && pf[c.getI()+1][c.getJ()]!=0){
                c.sum(1,0);
                flag=isIn(c);
            }else
            if(c.getI()+1==pf.length)
                flag=false;
            x++;
        }
        return flag;
    }

    protected boolean canMoveRight(int[][]pf){
        boolean flag=true;
        int x =0;
        while(flag && x<4){
            Coordinates c = new Coordinates(coord[x].getI(),coord[x].getJ());
            if(c.getJ()+1<pf[0].length && pf[c.getI()][c.getJ()+1]!=0){
                c.sum(0,1);
                flag=isIn(c);

            }else
            if(c.getJ()+1==pf[0].length)
                flag=false;
            x++;
        }
        return flag;
    }

    protected boolean canMoveLeft(int[][]pf){
        boolean flag=true;
        int x =0;
        while(flag && x<4){
            Coordinates c = new Coordinates(coord[x].getI(),coord[x].getJ());
            if(c.getJ()-1>-1 && pf[c.getI()][c.getJ()-1]!=0){
                c.sub(0,1);
                flag=isIn(c);
            }else
            if(c.getJ()-1==-1)
                flag=false;
            x++;
        }
        return flag;
    }

    private boolean isIn(Coordinates c){
        boolean flag=true;
        int j=0;
        while(flag && j<4){
            if(c.equals(coord[j])){
                flag=false;
            }
            j++;
        }
        return !flag;
    }
    protected boolean validCoord(Coordinates c,int[][] pf){
        boolean flag=true;
        if(c.getI()<0 || c.getI()>=pf.length || c.getJ()<0 || c.getJ()>=pf[0].length)
            flag=false;
        else
            if(pf[c.getI()][c.getJ()]!=0 && !isIn(c))
                flag=false;
        return flag;
    }
}
