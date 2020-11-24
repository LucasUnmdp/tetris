package Tetriminos;

import exception.CantRotException;
import model.Coordinates;

public class I extends Tetrimino {

    public I(){
        this.initialize();
        numberpiece=7;
    }
    @Override
    public boolean wallKick(int[][] pf) {
        Coordinates aux;
        Coordinates[] auxVec = null;
        boolean valid = false;
        if (coord[0].getJ() == coord[1].getJ()) {
            if (coord[center].getI() > coord[1].getI()) {
                if (canMoveLeft(pf)) {
                    auxVec = getCopyCoord();
                    for (int j = 0; j < 4; j++) {
                        auxVec[j].sub(0, 1);
                    }
                    valid = true;
                }else
                    if (canMoveRight(pf)) {
                        auxVec = getCopyCoord();
                        for (int j = 0; j < 4; j++) {
                            coord[j].sum(0, 1);
                        }
                        if (canMoveRight(pf)) {
                            for (int j = 0; j < 4; j++) {
                                auxVec[j].sum(0, 2);
                            }
                            valid = true;
                        }
                        for (int j = 0; j < 4; j++) {
                        coord[j].sub(0, 1);
                        }
                    }
                int i = 0;
                while (i < 4 && valid) {
                    aux = new Coordinates(auxVec[center].getI(), auxVec[center].getJ() - 2 + i);
                    valid = validCoord(aux, pf);
                    aux = new Coordinates(auxVec[center - 1].getI(), auxVec[center].getJ() - 2 + i);
                    valid &= validCoord(aux, pf);
                    i++;
                }
            } else {
                if (canMoveRight(pf)) {
                    auxVec = getCopyCoord();
                    for (int j = 0; j < 4; j++)
                        auxVec[j].sum(0, 1);
                    valid = true;
                }else
                    if (canMoveLeft(pf)) {
                        auxVec = getCopyCoord();
                        for (int j = 0; j < 4; j++)
                            coord[j].sub(0, 1);
                        if (canMoveLeft(pf)) {
                            for (int j = 0; j < 4; j++)
                                auxVec[j].sub(0, 2);
                            valid = true;
                        }
                        for (int j = 0; j < 4; j++)
                        coord[j].sum(0, 1);
                    }
                int i = 0;
                while (i < 4 && valid) {
                    aux = new Coordinates(auxVec[center].getI(), auxVec[center].getJ() - 1 + i);
                    valid = validCoord(aux, pf);
                    aux = new Coordinates(auxVec[center - 1].getI(), auxVec[center].getJ() - 1 + i);
                    valid &= validCoord(aux, pf);
                    i++;
                }

            }
            if (valid)
                this.coord = auxVec;
        }
        return valid;
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
