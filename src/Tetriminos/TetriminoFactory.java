package Tetriminos;

import java.util.ArrayList;

public class TetriminoFactory {

    public static Tetrimino getTetrimino(String t){
        Tetrimino tetrimino=null;
        switch (t){
            case "o":
                tetrimino=new O();
                break;
            case "z":
                tetrimino=new Z();
                break;
            case "s":
                tetrimino=new S();
                break;
            case "t":
                tetrimino=new T();
                break;
            case "l":
                tetrimino=new L();
                break;
            case "j":
                tetrimino=new J();
                break;
            case "i":
                tetrimino=new I();
                break;
        }
        return tetrimino;
    }

    public static ArrayList<Tetrimino> getFullList(){
        ArrayList<Tetrimino> pieces = new ArrayList<>();
        pieces.add(new O());
        pieces.add(new Z());
        pieces.add(new S());
        pieces.add(new T());
        pieces.add(new L());
        pieces.add(new J());
        pieces.add(new I());
        return pieces;
    }
}
