package view;

import Tetriminos.Tetrimino;
import model.Engine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JPanel {
    private Engine eng;
    private boolean paint= true;
    public static int square=35;

    public View (Engine e, int width, int height){
        this.eng=e;
        this.setPreferredSize(new Dimension(width,height));
        initializeThread();
    }

    private void initializeThread(){
        new Thread(()->{
            while(paint){
                this.repaint();
            }
        }).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        paintPieces(g);
        paintStage(g);
    }

    private void paintPieces(Graphics g){
        int [][] m = eng.getPlayField();
        for(int i=2; i< m.length;i++) {
            for (int j = 0; j < m[0].length; j++) {
                switch (m[i][j]){
                    case 1:
                        g.setColor(Color.YELLOW);
                        break;
                    case 2:
                        g.setColor(Color.RED);
                        break;
                    case 3:
                        g.setColor(Color.GREEN);
                        break;
                    case 4:
                        g.setColor(Color.decode("#FF1493"));
                        break;
                    case 5:
                        g.setColor(Color.ORANGE);
                        break;
                    case 6:
                        g.setColor(Color.BLUE);
                        break;
                    case 7:
                        g.setColor(Color.CYAN);
                }
                if(m[i][j]!=0)
                    g.fillRect(j*square+200,i*square-(2*square),square,square);
            }
        }
    }

    private void paintStage(Graphics g){
        int [][] m = eng.getPlayField();
        ArrayList<Tetrimino> queue=this.eng.getPiece().getQueue();
        g.setColor(Color.GRAY);
        for(int i=2; i< m.length;i++){
            for(int j=0; j<m[0].length;j++){
                g.drawRect(j*square+200,i*square-(2*square),square,square);
            }
        }
        for(int i=0;i<5;i++){
            Tetrimino t=queue.get(i);
            t.paintTetrimino(g,200+square*10+35,i*square*3+10);
        }
    }
}
