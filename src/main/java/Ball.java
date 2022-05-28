package main.java;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Ball{
	

	
	private double currentX;
	private double currentY;
	private double currentR;
	
	private double currentVelocityX;
	private double currentVelocityY;
	
	private JLabel ball;
	
	Ball(){
		setCurrentX(Game.BALL_START_X);
		setCurrentY(Game.BALL_START_Y);
		setCurrentR(Game.BALL_START_R);
		setCurrentVelocityX(Game.BALL_START_VELOCITY_X);
		setCurrentVelocityY(Game.BALL_START_VELOCITY_Y);
		setBall(new JLabel());
		getBall().setBounds((int) getCurrentX(), (int)getCurrentY(),(int) getCurrentR(),(int) getCurrentR());
		getBall().setIcon(new ImageIcon(getClass().getResource(Game.BALL_RED_PATH)));
	}
	
	public void reset() {
		setCurrentX(Game.BALL_START_X);
		setCurrentY(Game.BALL_START_Y);
		setCurrentR(Game.BALL_START_R);
		setCurrentVelocityX(Game.BALL_START_VELOCITY_X);
		setCurrentVelocityY(Game.BALL_START_VELOCITY_Y);
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
