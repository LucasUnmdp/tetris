package Tetriminos;

import exception.CantMoveException;
import exception.CantRotException;
import model.Coordinates;

public class Z extends Tetrimino{

    public Z(){
        this.initialize();
        numberpiece=2;
    }
    @Override
    public void spawn() {
        coord[0].setCoords(1,5);
        coord[1].setCoords(1,4);
        coord[2].setCoords(0,4);
        coord[3].setCoords(0,3);
        center= 2;
    }

    @Override
    public void rotDer(int [][] pf) throws CantRotException {
        Coordinates[] temp = new Coordinates[4];
        for(int i=1;i<4;i++) {
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
        if (coord[0].getI() < coord[center].getI() && coord[0].getJ() < coord[center].getJ())
            temp[0] = new Coordinates(coord[0].getI(), coord[0].getJ() + 2);
        else
            if (coord[0].getI() > coord[center].getI() && coord[0].getJ() > coord[center].getJ())
                temp[0] = new Coordinates(coord[0].getI(), coord[0].getJ() - 2);
            else
                if (coord[0].getI() < coord[center].getI() && coord[0].getJ() > coord[center].getJ())
                    temp[0] = new Coordinates(coord[0].getI() + 2, coord[0].getJ());
                else
                    temp[0] = new Coordinates(coord[0].getI() - 2, coord[0].getJ());

        for(int i=0;i<4;i++){
            if(!validCoord(temp[i],pf)){
                throw new CantRotException("");
            }
        }
        this.coord=temp;
    }

    @Override
    public void rotIzq(int [][] pf) throws CantRotException {
        Coordinates[] temp = new Coordinates[4];
        for(int i=1;i<4;i++){
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
        if(coord[0].getI()<coord[center].getI() && coord[0].getJ()<coord[center].getJ())
            temp[0] = new Coordinates(coord[0].getI()+2, coord[0].getJ());
        else
            if (coord[0].getI() > coord[center].getI() && coord[0].getJ() > coord[center].getJ())
                temp[0] = new Coordinates(coord[0].getI()-2, coord[0].getJ());
            else
                if (coord[0].getI() < coord[center].getI() && coord[0].getJ() > coord[center].getJ())
                    temp[0] = new Coordinates(coord[0].getI() , coord[0].getJ()-2);
                else
                    temp[0] = new Coordinates(coord[0].getI() , coord[0].getJ()+2);

        for(int i=0;i<4;i++){
            if(!validCoord(temp[i],pf)){
                throw new CantRotException("");
            }
        }
        this.coord=temp;
    }
}
