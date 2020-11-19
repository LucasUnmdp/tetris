package Tetriminos;

import exception.CantRotException;

public class O extends Tetrimino{

    public O(){
        this.initialize();
        this.numberpiece=1;
    }

    @Override
    public void spawn() {
        this.center=0;
        coord[0].setCoords(1,4);
        coord[1].setCoords(1,5);
        coord[2].setCoords(0,4);
        coord[3].setCoords(0,5);
    }

    @Override
    public void rotDer(int[][] pf) throws CantRotException {

    }

    @Override
    public void rotIzq(int[][] pf) throws CantRotException {

    }


}
