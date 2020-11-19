package model;

import exception.LoseException;
import view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Engine implements KeyListener {
    private int [][] playField;
    private JFrame window;
    private View view;
    private Pieces piece;
    private int vel;

    public Engine (){
        this.playField=new int[22][10];
        this.vel=750;
        createWindow();
        initializeThread();
    }

    private void createWindow(){
        this.window= new JFrame("Tetris");
        window.addKeyListener(this);
        SwingUtilities.invokeLater(()->{
            this.window.setLayout(new BorderLayout());
            this.view= new View(this,800,710);
            this.window.add(this.view,BorderLayout.CENTER);
            this.window.setResizable(false);
            this.window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.window.pack();
            this.window.setLocationRelativeTo(null);
            this.window.setVisible(true);
        });
    }

    public int[][] getPlayField() {
        return playField;
    }
    public void setPlayField(int [][] pf){
        this.playField=pf;
    }

    private void initializeThread(){
        this.piece = new Pieces(this);
        new Thread(()->{
            while(true){
                try {
                    this.piece.nextStep();
                    if(!this.piece.isInGame())
                        throw new LoseException("Perdiste capo");
                    Thread.sleep(vel);
                } catch (InterruptedException e){
                }
                catch(LoseException e){
                    JOptionPane.showMessageDialog(null, "f", "Perdiste", JOptionPane.PLAIN_MESSAGE);
                    this.playField= new int[22][10];
                    this.piece = new Pieces(this);
            }
            }
        }).start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_RIGHT:
                    this.piece.move("right");
                break;
            case KeyEvent.VK_LEFT:
                    this.piece.move("left");
                break;
            case KeyEvent.VK_DOWN:
                    this.piece.move("down");
                break;
            case KeyEvent.VK_SPACE:
                this.piece.fastSteps();
                break;
            case KeyEvent.VK_X:
                this.piece.rot("clockwise");
                break;
            case KeyEvent.VK_Z:
                this.piece.rot("counterclockwise");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void setVel(int vel) {
        this.vel = vel;
    }
}
