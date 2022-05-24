package main.java;

import java.awt.Color;

import javax.swing.JLabel;

public class Brick {
	
	public static final int defaultWidth = 50;
	public static final int defaultHeight = 20;
	public static final int defaultnumHitsTobreak = 1;
	public static final Color defaultColor = Color.orange;
	
	
	private int x;
	private int y;
	private int width;
	private int height;
	private int numHitsTobreak;
	private Color color;
	private JLabel label;
	
	
	Brick(int x, int y, int width, int height, int numHitsTobreak, Color color){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.numHitsTobreak = numHitsTobreak;
		this.color = color;
		this.label = new JLabel();
		this.label.setLayout(null);
		this.label.setBounds(x, y, width, height);
		this.label.setBackground(color);
		this.label.setOpaque(true);
	}
	
	
	class BallBrickIntersection{

	}
	
	
	
	
	


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public int getNumHitsTobreak() {
		return numHitsTobreak;
	}


	public void setNumHitsTobreak(int numHitsTobreak) {
		this.numHitsTobreak = numHitsTobreak;
	}


	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}





	public JLabel getLabel() {
		return label;
	}





	public void setLabel(JLabel label) {
		this.label = label;
	}

}
