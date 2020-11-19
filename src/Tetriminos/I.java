package Tetriminos;

import exception.CantMoveException;
import exception.CantRotException;
import model.Coordinates;

public class I extends Tetrimino {

    public I(){
        this.initialize();
        numberpiece=7;
    }

    @Override
    public void wallKick(int[][] pf) {
        if(this.canMoveRight(pf)){
            try {
                this.move(pf,"right");
                if(coord[center].getI()>coord[center-1].getI() && this.canMoveRight(pf)){
                    this.move(pf,"right");
                }
            } catch (CantMoveException e) {
                try {
                    this.move(pf,"left");
                } catch (CantMoveException cantMoveException) {
                }
            }
        }else{
            if(this.canMoveLeft(pf)){
                try {
                    this.move(pf, "left");
                    if (coord[center].getI()<coord[center-1].getI() && this.canMoveLeft(pf)) {
                        this.move(pf, "left");
                    }
                } catch (CantMoveException e){
                    this.canMoveRight(pf);
                }
            }
        }
    }

    @Override
    public void spawn() {
        coord[0].setCoords(1,3);
        coord[1].setCoords(1,4);
        coord[2].setCoords(1,5);
        coord[3].setCoords(1,6);
        center=2;
    }

    @Override
    public void rotDer(int[][] pf) throws CantRotException {
        Coordinates[] temp = new Coordinates[4];
        for(int i=0;i<4;i++){
            if (coord[0].getI()==coord[1].getI()){
                if(coord[center].getJ()>coord[center-1].getJ())
                    temp[i]= new Coordinates(coord[center].getI()-1+i,coord[center].getJ());
                else
                    temp[i]= new Coordinates(coord[center].getI()+1-i,coord[center].getJ());
            }else{
                if(coord[center].getI()>coord[center-1].getI())
                    temp[i]= new Coordinates(coord[center].getI(),coord[center].getJ()+1-i);
                else
                    temp[i]= new Coordinates(coord[center].getI(),coord[center].getJ()-1+i);
            }
        }
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
        for(int i=0;i<4;i++){
            if (coord[0].getI()==coord[1].getI()){
                if(coord[center].getJ()>coord[center-1].getJ())
                    temp[i]= new Coordinates(coord[center-1].getI()+2-i,coord[center-1].getJ());
                else
                    temp[i]= new Coordinates(coord[center-1].getI()-2+i,coord[center-1].getJ());
            }else{
                if(coord[center].getI()>coord[center-1].getI())
                    temp[i]= new Coordinates(coord[center-1].getI(),coord[center-1].getJ()-2+i);
                else
                    temp[i]= new Coordinates(coord[center-1].getI(),coord[center-1].getJ()+2-i);
            }
        }
        for(int i=0;i<4;i++){
            if(!validCoord(temp[i],pf)){
                throw new CantRotException("");
            }
        }
        this.coord=temp;
    }


}
