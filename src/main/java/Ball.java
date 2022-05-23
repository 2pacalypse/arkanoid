package main.java;

import java.awt.Color;


import javax.swing.JLabel;

public class Ball{
	
	public static final int startX = 300;
	public static final int startY = 250;
	public static final int startR = 25;
	
	public static final int startVelocityX = 2;
	public static final int startVelocityY = 2;
	
	private int currentX;
	private int currentY;
	private int currentR;
	
	private int currentVelocityX;
	private int currentVelocityY;
	
	private JLabel ball;
	
	Ball(){
		setCurrentX(startX);
		setCurrentY(startY);
		setCurrentR(startR);
		setCurrentVelocityX(startVelocityX);
		setCurrentVelocityY(startVelocityY);
		setBall(new JLabel());
		getBall().setOpaque(true);
		getBall().setBounds(getCurrentX(), getCurrentY(), getCurrentR(), getCurrentR());
		getBall().setBackground(Color.green);
	}
	
	public void reset() {
		setCurrentX(startX);
		setCurrentY(startY);
		setCurrentR(startR);
		setCurrentVelocityX(startVelocityX);
		setCurrentVelocityY(startVelocityY);
		getBall().setBounds(getCurrentX(), getCurrentY(), getCurrentR(), getCurrentR());
		
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

	public int getCurrentR() {
		return currentR;
	}

	public void setCurrentR(int currentR) {
		this.currentR = currentR;
	}

	public JLabel getBall() {
		return ball;
	}

	public void setBall(JLabel ball) {
		this.ball = ball;
	}
	
	public void updateBall() {
		getBall().setBounds(getCurrentX(), getCurrentY(), getCurrentR(), getCurrentR());
	}


	public int getCurrentVelocityX() {
		return currentVelocityX;
	}


	public void setCurrentVelocityX(int currentVelocityX) {
		this.currentVelocityX = currentVelocityX;
	}


	public int getCurrentVelocityY() {
		return currentVelocityY;
	}


	public void setCurrentVelocityY(int currentVelocityY) {
		this.currentVelocityY = currentVelocityY;
	}
	








}
