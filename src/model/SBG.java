package model;


import Tetriminos.Tetrimino;
import Tetriminos.TetriminoFactory;

import java.util.ArrayList;
import java.util.Random;

public class SBG {
    private ArrayList<Tetrimino> bag = new ArrayList<Tetrimino>();
    boolean firstDraw;

    public SBG(){
        refillBag();
        firstDraw=true;
    }

    private void refillBag(){
        bag=TetriminoFactory.getFullList();
    }

    public Tetrimino drawPiece(){
        int i;
        Random r= new Random();
        Tetrimino piece;
        if(firstDraw){
            i=3+r.nextInt(bag.size()-3);
            piece=bag.get(i);
            firstDraw=false;
        }else{
            if(bag.isEmpty())
                refillBag();
            i=r.nextInt(bag.size());
            piece=bag.get(i);
        }
        bag.remove(i);
        return piece;
    }
}
