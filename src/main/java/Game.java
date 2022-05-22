package main.java;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Game implements Runnable{
	

	public static final int startingNumLives = 3;
	
	
    private Paddle paddle;
    private Ball ball;
    private JPanel panel;
    
    private int numLives;
    private int state = 1;
    
    private Object lock = new Object();
    
    
    
    Game(){
    	setPaddle(new Paddle());
    	setBall(new Ball());
    	setPanel(new JPanel());
    	getPanel().setLayout(null);
        getPanel().add(getPaddle().getBar());
        getPanel().add(getBall().getBall());
        getPanel().setVisible(true);
        
        setNumLives(startingNumLives);
        
              
        getPanel().addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
            }
       
            @Override
            public void mouseMoved(MouseEvent e) {
            	paddle.setCurrentX(e.getX());
            	paddle.updateBar();
            }
       });
        
        
        InputMap imap = getPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        imap.put(KeyStroke.getKeyStroke("SPACE"), "spaceAction");
        ActionMap amap = getPanel().getActionMap();
        amap.put("spaceAction", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
            	
                
                synchronized (lock) {
                	System.out.println("haha");
                	state = 0;
                	lock.notify();
                	
                }
            }
        });
        
	

        
        
    }
    
    
    public void start() {

	
	
}


	public Paddle getPaddle() {
		return paddle;
	}


	public void setPaddle(Paddle paddle) {
		this.paddle = paddle;
	}


	public Ball getBall() {
		return ball;
	}


	public void setBall(Ball ball) {
		this.ball = ball;
	}


	public JPanel getPanel() {
		return panel;
	}


	public void setPanel(JPanel panel) {
		this.panel = panel;
	}


	@Override
	public void run() {
		
		
		while (numLives > 0) {
			synchronized (lock) {
				while (state == 1) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			
			
			getBall().setCurrentX((getBall().getCurrentX() + getBall().getCurrentVelocityX()));
			getBall().setCurrentY((getBall().getCurrentY() + getBall().getCurrentVelocityY()));
			getBall().updateBall();
			
			if (getBall().getCurrentX() >= 600 - getBall().getCurrentR()) {
				getBall().setCurrentVelocityX(-getBall().getCurrentVelocityX());
			}
		    if (getBall().getCurrentY() <= 0 ) {
				getBall().setCurrentVelocityY(-getBall().getCurrentVelocityY());
			}
			if(getBall().getCurrentX() <= 0) {
				getBall().setCurrentVelocityX(-getBall().getCurrentVelocityX());
			}
			
			
			if (getBall().getCurrentY() + getBall().getCurrentR() >= getPaddle().getCurrentY() && (getBall().getCurrentX() + getBall().getCurrentR() >= getPaddle().getCurrentX() && getBall().getCurrentX() <= getPaddle().getCurrentX() + getPaddle().getCurrentWidth())) {
				getBall().setCurrentVelocityY(-getBall().getCurrentVelocityY());
			}
			
			if (getBall().getCurrentY() >= 600) {
				setNumLives(getNumLives() - 1);
				
			}
			
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}


	public int getNumLives() {
		return numLives;
	}


	public void setNumLives(int numLives) {
		this.numLives = numLives;
	}

}
