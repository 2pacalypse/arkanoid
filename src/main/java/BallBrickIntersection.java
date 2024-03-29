package main.java;



public class BallBrickIntersection {
	enum Side {UP, DOWN, LEFT, RIGHT};
	private Side side;
	private double intersectionX;
	private double intersectionY;
	private double dist = Float.POSITIVE_INFINITY;
	
	BallBrickIntersection(Ball ball, Brick brick){
		
		down(ball, brick); //check if ball hits the brick from the down
		right(ball, brick);//check if ball hits the brick from the right
		left(ball, brick);//check if ball hits the brick from the left
		up(ball, brick);//check if ball hits the brick from the up
	
		
	}
	
	private void down(Ball ball, Brick brick) {
		double m =  ball.getCurrentVelocityY() / ball.getCurrentVelocityX();
		double x =  (((brick.getY() + brick.getHeight() - ball.getCurrentY())) /m + ball.getCurrentX());
		
		
		if (ball.getCurrentY() >= brick.getY() + brick.getHeight() && ball.getCurrentY() + ball.getCurrentVelocityY() <= brick.getY() + brick.getHeight() && brick.getX() - ball.getCurrentR() <= x && x<= brick.getX() + brick.getWidth()) {
			double currentDist = Math.pow(ball.getCurrentY() - brick.getY() - brick.getHeight(), 2) + Math.pow(ball.getCurrentX() - x, 2) ;
			if (currentDist <= dist) {
				dist = currentDist;
				intersectionX = x;
				intersectionY = brick.getY() + brick.getHeight();
				side = Side.DOWN;
			}
		}	
	}
	
	

	private void right(Ball ball, Brick brick) {
		double m =  ball.getCurrentVelocityY() /  ball.getCurrentVelocityX();
		double y =  (m * (brick.getX() + brick.getWidth() - ball.getCurrentX()) + ball.getCurrentY() );
		
		if (ball.getCurrentX() >= brick.getX() + brick.getWidth() && ball.getCurrentX() + ball.getCurrentVelocityX() <= brick.getX() + brick.getWidth() && y + ball.getCurrentR() >= brick.getY() && y <= brick.getY() + brick.getHeight()) {
			double currentDist = Math.pow(ball.getCurrentY() - y, 2) + Math.pow(ball.getCurrentX()  - brick.getX() - brick.getWidth(), 2) ;
			if (currentDist <= dist) {
				dist = currentDist;
				intersectionX = brick.getX() + brick.getWidth();
				intersectionY = y;
				side = Side.RIGHT;
			}
		}
	}
	
	private void left(Ball ball, Brick brick) {
		
		double m =  ball.getCurrentVelocityY() /  ball.getCurrentVelocityX();
		double y =  (m * (brick.getX() - ball.getCurrentX() - ball.getCurrentR() ) + ball.getCurrentY() );
		
		if (ball.getCurrentX()	+ ball.getCurrentR() <= brick.getX() && ball.getCurrentX() + ball.getCurrentR() + ball.getCurrentVelocityX() >= brick.getX()  && y >= brick.getY() - ball.getCurrentR() && y <= brick.getY() + brick.getHeight()) {
			double currentDist = Math.pow(ball.getCurrentY() - y, 2) + Math.pow(ball.getCurrentX()  - brick.getX(), 2) ;
			if (currentDist <= dist) {
				dist = currentDist;
				intersectionX = brick.getX();
				intersectionY = y;
				side = Side.LEFT;
			}
		}
		
	}
	
	private void up(Ball ball, Brick brick) {
		double m =  ball.getCurrentVelocityY() / ball.getCurrentVelocityX();
		double x =  (((brick.getY() - ball.getCurrentY() - ball.getCurrentR())) /m + ball.getCurrentX());
		
		//down
		if (ball.getCurrentY() + ball.getCurrentR() <= brick.getY()  && ball.getCurrentY() + ball.getCurrentR() + ball.getCurrentVelocityY() >= brick.getY() && brick.getX() <= x + ball.getCurrentR() && x<= brick.getX() + brick.getWidth()) {
			double currentDist = Math.pow(ball.getCurrentY() + ball.getCurrentR() - brick.getY(), 2) + Math.pow(ball.getCurrentX() - x, 2) ;
			if (currentDist <= dist) {
				dist = currentDist;
				intersectionX = x;
				intersectionY = brick.getY();
				side = Side.UP;
			}
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

	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;
	}

}