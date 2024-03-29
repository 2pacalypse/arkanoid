package main.java;

public class BallPaddleIntersection {
	private double intersectionX;
	private double intersectionY;
	private double dist = Float.POSITIVE_INFINITY;
	
	BallPaddleIntersection(Ball ball, Paddle paddle){
		double ballRadius = ball.getCurrentR() / 2;
		double ballCenterX = ball.getCurrentX() + ballRadius;
		double ballCenterY = ball.getCurrentY() + ballRadius;
		
		double k = (paddle.getCurrentY() - ballRadius - ballCenterY) /(double) ball.getCurrentVelocityY();
		double x = (int) (ballCenterX + k*ball.getCurrentVelocityX());
		
		if (ballCenterY + ballRadius <= paddle.getCurrentY() && //ball is above the paddle previously 
			ballCenterY + ballRadius + ball.getCurrentVelocityY() >= paddle.getCurrentY() && // ball will be below the paddle in the next frame
			x + ballRadius >= paddle.getCurrentX() && // ball will be within the paddle 
			x <= paddle.getCurrentX() + paddle.getCurrentWidth() + ballRadius) { //wrt x axis
			dist = Math.pow(paddle.getCurrentY() - ballCenterY, 2) + Math.pow(x - ballCenterX, 2);
			intersectionX = x;
			intersectionY = paddle.getCurrentY();
		}
		
	}

	public double getIntersectionX() {
		return intersectionX;
	}

	public void setIntersectionX(double intersectionX) {
		this.intersectionX = intersectionX;
	}

	public double getIntersectionY() {
		return intersectionY;
	}

	public void setIntersectionY(double intersectionY) {
		this.intersectionY = intersectionY;
	}

	public double getDist() {
		return dist;
	}

	public void setDist(double dist) {
		this.dist = dist;
	}
}
