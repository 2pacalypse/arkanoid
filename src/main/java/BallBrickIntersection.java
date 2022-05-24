package main.java;



public class BallBrickIntersection {
	enum Side {UP, DOWN, LEFT, RIGHT};
	private Side side;
	private int intersectionX;
	private int intersectionY;
	private double dist = Float.POSITIVE_INFINITY;
	
	BallBrickIntersection(Ball ball, Brick brick){
		
		down(ball, brick);
		right(ball, brick);
		left(ball, brick);
		up(ball, brick);
	
		
	}
	
	private void down(Ball ball, Brick brick) {
		float m =  ball.getCurrentVelocityY() / (float) ball.getCurrentVelocityX();
		int x = (int) (((brick.getY() + brick.getHeight() - ball.getCurrentY())) /m + ball.getCurrentX());
		
		//down
		if (ball.getCurrentY() >= brick.getY() + brick.getHeight() && ball.getCurrentY() + ball.getCurrentVelocityY() <= brick.getY() + brick.getHeight() && brick.getX() <= x && x<= brick.getX() + brick.getWidth()) {
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
		float m =  ball.getCurrentVelocityY() / (float) ball.getCurrentVelocityX();
		int y = (int) (m * (brick.getX() + brick.getWidth() - ball.getCurrentX()) + ball.getCurrentY() );
		
		if (ball.getCurrentX() >= brick.getX() + brick.getWidth() && ball.getCurrentX() + ball.getCurrentVelocityX() <= brick.getX() + brick.getWidth() && y >= brick.getY() && y <= brick.getY() + brick.getHeight()) {
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
		
	}
	
	private void up(Ball ball, Brick brick) {
		float m =  ball.getCurrentVelocityY() / (float) ball.getCurrentVelocityX();
		int x = (int) (((brick.getY() - ball.getCurrentY() - ball.getCurrentR())) /m + ball.getCurrentX());
		
		//down
		if (ball.getCurrentY() + ball.getCurrentR() <= brick.getY()  && ball.getCurrentY() + ball.getCurrentR() + ball.getCurrentVelocityY() >= brick.getY() && brick.getX() <= x && x<= brick.getX() + brick.getWidth()) {
			double currentDist = Math.pow(ball.getCurrentY() + ball.getCurrentR() - brick.getY(), 2) + Math.pow(ball.getCurrentX() - x, 2) ;
			if (currentDist <= dist) {
				dist = currentDist;
				intersectionX = x;
				intersectionY = brick.getY();
				side = Side.UP;
			}
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

	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;
	}

}