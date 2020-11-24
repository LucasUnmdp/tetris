package Tetriminos;

import exception.CantRotException;
import view.View;

import java.awt.*;

public class O extends Tetrimino{

    public O(){
        this.initialize();
        this.numberpiece=1;
    }

    @Override
    public void paintTetrimino(Graphics g, int width, int height) {
        g.setColor(Color.YELLOW);
        g.fillRect(width,height+View.square,View.square,View.square);
        g.fillRect(width,height,View.square,View.square);
        g.fillRect(width+View.square,height,View.square,View.square);
        g.fillRect(width+View.square,height+View.square,View.square,View.square);
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
