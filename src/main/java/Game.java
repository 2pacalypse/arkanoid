package main.java;


import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.event.*;


public class Game implements Runnable{
	
	public static final String windowTitle = "Arkanoid";
	public static final int boardWidth = 600;
	public static final int boardHeight = 600;
	
    JFrame frame;
    Paddle paddle;
    Ball ball;

    public void init(){
        frame = new JFrame(windowTitle);
        paddle = new Paddle();
        ball = new Ball();

        frame.getContentPane().setPreferredSize(new Dimension(boardWidth, boardHeight));
        frame.pack();
        
        frame.setLayout(null);
        frame.add(paddle.getBar());
        frame.add(ball.getBall());
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
        game.run();
    }


	@Override
	public void run() {

			while (true) {
				ball.setCurrentX((ball.getCurrentX() + ball.getCurrentVelocityX()));
				ball.setCurrentY((ball.getCurrentY() + ball.getCurrentVelocityY()));
				ball.updateBall();
				
				if (ball.getCurrentX() >= 600 - ball.getCurrentR()) {
					ball.setCurrentVelocityX(-ball.getCurrentVelocityX());
					ball.setCurrentVelocityY(ball.getCurrentVelocityY());
				}
				
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		
	}
    
}
