package Tetriminos;

import exception.CantRotException;
import model.Coordinates;
import view.View;

import javax.swing.plaf.ViewportUI;
import java.awt.*;

public class S extends Tetrimino {

    public S(){
        this.initialize();
        numberpiece=3;
    }

    @Override
    public void paintTetrimino(Graphics g, int width, int height) {
        g.setColor(Color.GREEN);
        g.fillRect(width,height+ View.square, View.square,View.square);
        g.fillRect(width+View.square,height+View.square,View.square,View.square);
        g.fillRect(width+View.square,height,View.square,View.square);
        g.fillRect(width+View.square*2,height,View.square,View.square);
    }

    @Override
    public void spawn() {
        coord[0].setCoords(1,3);
        coord[1].setCoords(1,4);
        coord[2].setCoords(0,4);
        coord[3].setCoords(0,5);
        center=1;
    }

    @Override
    public void rotDer(int[][] pf) throws CantRotException {
        Coordinates[] temp = new Coordinates[4];
        for(int i=0;i<3;i++) {
            if (i != center)
                if (coord[i].getI() < coord[center].getI())
                    temp[i] = new Coordinates(coord[i].getI() + 1, coord[i].getJ() + 1);
                else if (coord[i].getI() > coord[center].getI())
                    temp[i] = new Coordinates(coord[i].getI() - 1, coord[i].getJ() - 1);
                else if (coord[i].getJ() < coord[center].getJ())
                    temp[i] = new Coordinates(coord[i].getI() - 1, coord[i].getJ() + 1);
                else
                    temp[i] = new Coordinates(coord[i].getI() + 1, coord[i].getJ() - 1);
            else
                temp[i] = new Coordinates(coord[i].getI(), coord[i].getJ());
        }
        if(coord[3].getI()<coord[center].getI() && coord[3].getJ()<coord[center].getJ())
            temp[3] = new Coordinates(coord[3].getI(),coord[3].getJ()+2);
        else
            if (coord[3].getI() > coord[center].getI() && coord[3].getJ() > coord[center].getJ())
                temp[3] = new Coordinates(coord[3].getI(),coord[3].getJ()-2);
            else
                if (coord[3].getI() < coord[center].getI() && coord[3].getJ() > coord[center].getJ())
                    temp[3] = new Coordinates(coord[3].getI() +2, coord[3].getJ());
                else
                    temp[3] = new Coordinates(coord[3].getI()-2, coord[3].getJ());

        for(int i=0;i<4;i++){
            if(!validCoord(temp[i],pf)){
                throw new CantRotException("");
            }
        }
        this.coord=temp;
    }

    @Override
    public void rotIzq(int[][] pf) throws CantRotException {
        Coordinates[] temp = new Coordinates[4];
        for(int i=0;i<3;i++){
            if(i!=center)
                if(coord[i].getI()<coord[center].getI())
                    temp[i]= new Coordinates(coord[i].getI()+1,coord[i].getJ()-1);
                else
                if(coord[i].getI()>coord[center].getI())
                    temp[i]= new Coordinates(coord[i].getI()-1,coord[i].getJ()+1);
                else
                if(coord[i].getJ()<coord[center].getJ())
                    temp[i]= new Coordinates(coord[i].getI()+1,coord[i].getJ()+1);
                else
                    temp[i]= new Coordinates(coord[i].getI()-1,coord[i].getJ()-1);
            else
                temp[i]= new Coordinates(coord[i].getI(),coord[i].getJ());
        }
        if(coord[3].getI()<coord[center].getI() && coord[3].getJ()<coord[center].getJ())
            temp[3] = new Coordinates(coord[3].getI()+2,coord[3].getJ());
        else
            if (coord[3].getI() > coord[center].getI() && coord[3].getJ() > coord[center].getJ())
                temp[3] = new Coordinates(coord[3].getI()-2,coord[3].getJ());
            else
                if (coord[3].getI() < coord[center].getI() && coord[3].getJ() > coord[center].getJ())
                    temp[3] = new Coordinates(coord[3].getI(), coord[3].getJ()-2);
                else
                    temp[3] = new Coordinates(coord[3].getI(), coord[3].getJ()+2);

        for(int i=0;i<4;i++){
            if(!validCoord(temp[i],pf)){
                throw new CantRotException("");
            }
        }
        this.coord=temp;
    }


}
