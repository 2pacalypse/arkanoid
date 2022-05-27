package main.java;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Ball{
	
	public static final int startX = 300;
	public static final int startY = 500;
	public static final int startR = 24;
	
	public static final int startVelocityX = -2;
	public static final int startVelocityY = -2;
	
	private double currentX;
	private double currentY;
	private double currentR;
	
	private double currentVelocityX;
	private double currentVelocityY;
	
	private JLabel ball;
	
	Ball(){
		setCurrentX(startX);
		setCurrentY(startY);
		setCurrentR(startR);
		setCurrentVelocityX(startVelocityX);
		setCurrentVelocityY(startVelocityY);
		setBall(new JLabel());
		getBall().setBounds((int) getCurrentX(), (int)getCurrentY(),(int) getCurrentR(),(int) getCurrentR());
		getBall().setIcon(new ImageIcon(getClass().getResource("../resources/redBall.png")));
	}
	
	public void reset() {
		setCurrentX(startX);
		setCurrentY(startY);
		setCurrentR(startR);
		setCurrentVelocityX(startVelocityX);
		setCurrentVelocityY(startVelocityY);
		getBall().setBounds((int) getCurrentX(), (int) getCurrentY(),(int) getCurrentR(), (int)getCurrentR());
		
	}
	

	public double getCurrentX() {
		return currentX;
	}

	public void setCurrentX(double currentX) {
		this.currentX = currentX;
	}

	public double getCurrentY() {
		return currentY;
	}

	public void setCurrentY(double currentY) {
		this.currentY = currentY;
	}

	public double getCurrentR() {
		return currentR;
	}

	public void setCurrentR(double currentR) {
		this.currentR = currentR;
	}

	public JLabel getBall() {
		return ball;
	}

	public void setBall(JLabel ball) {
		this.ball = ball;
	}
	
	public void updateBall() {
		getBall().setBounds((int) getCurrentX(),(int) getCurrentY(),(int) getCurrentR(),(int) getCurrentR());
	}


	public double getCurrentVelocityX() {
		return currentVelocityX;
	}


	public void setCurrentVelocityX(double currentVelocityX) {
		this.currentVelocityX = currentVelocityX;
	}


	public double getCurrentVelocityY() {
		return currentVelocityY;
	}


	public void setCurrentVelocityY(double currentVelocityY) {
		this.currentVelocityY = currentVelocityY;
	}
	








}
