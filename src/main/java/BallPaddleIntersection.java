package main.java;

public class BallPaddleIntersection {
	private int intersectionX;
	private int intersectionY;
	private double dist = Float.POSITIVE_INFINITY;
	
	BallPaddleIntersection(Ball ball, Paddle paddle){
		int ballRadius = ball.getCurrentR() / 2;
		int ballCenterX = ball.getCurrentX() + ballRadius;
		int ballCenterY = ball.getCurrentY() + ballRadius;
		
		double k = (paddle.getCurrentY() - ballRadius - ballCenterY) /(double) ball.getCurrentVelocityY();
		int x = (int) (ballCenterX + k*ball.getCurrentVelocityX());
		
		if (ballCenterY + ballRadius <= paddle.getCurrentY() && 
			ballCenterY + ballRadius + ball.getCurrentVelocityY() >= paddle.getCurrentY() &&
			x + ballRadius >= paddle.getCurrentX() &&
			x <= paddle.getCurrentX() + paddle.getCurrentWidth() + ballRadius) {
			dist = Math.pow(paddle.getCurrentY() - ballCenterY, 2) + Math.pow(x - ballCenterX, 2);
			intersectionX = x;
			intersectionY = paddle.getCurrentY();
		}
		
	}

	public int getIntersectionX() {
		return intersectionX;
	}

	public void setIntersectionX(int intersectionX) {
		this.intersectionX = intersectionX;
	}

	public int getIntersectionY() {
		return intersectionY;
	}

	public void setIntersectionY(int intersectionY) {
		this.intersectionY = intersectionY;
	}

	public double getDist() {
		return dist;
	}

	public void setDist(double dist) {
		this.dist = dist;
	}
}
