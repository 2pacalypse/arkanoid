package main.java;


import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.event.*;


public class Runner{
	
	public static final String windowTitle = "Arkanoid";
	public static final int boardWidth = 600;
	public static final int boardHeight = 600;
	
    JFrame frame;
    Paddle paddle;
    Ball ball;
    Home home;
    
    private Runnable startNewGame() {
		return new Runnable() {

			@Override
			public void run() {
				return;
				
			}
			
		};
    }
    

    public void init(){
        frame = new JFrame(windowTitle);
        paddle = new Paddle();
        ball = new Ball();
        home = new Home();
        frame.add(home.getPanel());
        

        //frame.setLayout(null);
        frame.getContentPane().setPreferredSize(new Dimension(boardWidth, boardHeight));
        frame.pack();
        
        frame.setVisible(true);
        
        
       /* 
        
        
        frame.add(paddle.getBar());
        frame.add(ball.getBall());
        
        


        
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
       

	*/

        
    }

    
    public static void main(String[] args){
        Runner game = new Runner();
        game.init();
        //game.start();
    }



	public void start() {

			while (true) {
				ball.setCurrentX((ball.getCurrentX() + ball.getCurrentVelocityX()));
				ball.setCurrentY((ball.getCurrentY() + ball.getCurrentVelocityY()));
				ball.updateBall();
				
				if (ball.getCurrentX() >= 600 - ball.getCurrentR()) {
					ball.setCurrentVelocityX(-ball.getCurrentVelocityX());
				}
				else if (ball.getCurrentY() <= 0 ) {
					ball.setCurrentVelocityY(-ball.getCurrentVelocityY());
				}
				else if(ball.getCurrentX() <= 0) {
					ball.setCurrentVelocityX(-ball.getCurrentVelocityX());
				}
				
				else if (ball.getCurrentY() + ball.getCurrentR() >= paddle.getCurrentY() && (ball.getCurrentX() >= paddle.getCurrentX() && ball.getCurrentX() <= paddle.getCurrentX() + paddle.getCurrentWidth())) {
					ball.setCurrentVelocityY(-ball.getCurrentVelocityY());
				}
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		
	}
    
}
