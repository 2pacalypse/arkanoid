package main.java;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Paddle {
	
	
	private int currentX;
	private int currentY;
	private int currentWidth;
	private int currentHeight;
	
	
	private JLabel bar;
	
	
	Paddle(){
		
		setCurrentX(Game.PADDLE_START_X);
		setCurrentY(Game.PADDLE_START_Y);
		setCurrentWidth(Game.PADDLE_MEDIUM_WIDTH);
		setCurrentHeight(Game.PADDLE_HEIGHT);
		

		setBar(new JLabel());
		getBar().setBounds(getCurrentX(), getCurrentY(), getCurrentWidth(), getCurrentHeight());
		getBar().setIcon(new ImageIcon(getClass().getResource(Game.PADDLE_MEDIUM_PATH)));

	}


	public JLabel getBar() {
		return bar;
	}


	public void setBar(JLabel bar) {
		this.bar = bar;
	}


	public int getCurrentX() {
		return currentX;
	}


	public void setCurrentX(int currentX) {
		this.currentX = currentX;
	}


	public int getCurrentY() {
		return currentY;
	}


	public void setCurrentY(int currentY) {
		this.currentY = currentY;
	}


	public int getCurrentWidth() {
		return currentWidth;
	}


	public void setCurrentWidth(int currentWidth) {
		this.currentWidth = currentWidth;
	}


	public int getCurrentHeight() {
		return currentHeight;
	}


	public void setCurrentHeight(int currentHeight) {
		this.currentHeight = currentHeight;
	}


	public void updateBar() {
		getBar().setBounds(getCurrentX(), getCurrentY(), getCurrentWidth(), getCurrentHeight());
	}

	
	
	
	
}
