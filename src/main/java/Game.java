package main.java;


import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.event.*;


public class Game {
	
	public static final int boardWidth = 600;
	public static final int boardHeight = 600;
	
    JFrame frame;
    Paddle paddle;

    public void init(){
        frame = new JFrame("Arkanoid");
        paddle = new Paddle();

        frame.getContentPane().setPreferredSize(new Dimension(boardWidth, boardHeight));
        frame.pack();
        
        frame.setLayout(null);
        frame.add(paddle.getBar());
        frame.setVisible(true);


        
        frame.getContentPane().addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
            }
       
            @Override
            public void mouseMoved(MouseEvent e) {
            	paddle.setCurrentX(e.getX());
            	paddle.updateBar();
            }
       });
       



        
    }

    
    public static void main(String[] args){
        Game game = new Game();
        game.init();

    }
    
}
