package main.java;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import javax.swing.border.EtchedBorder;

public class Brick {
	
	public static final int defaultWidth = 64;
	public static final int defaultHeight = 32;

	
	
	private int x;
	private int y;
	private int width;
	private int height;
	private int numHitsTobreak;
	private JLabel label;
	
	
	Brick(int x, int y){
		this.x = x;
		this.y = y;
		this.width = defaultWidth;
		this.height = defaultHeight;
		this.label = new JLabel();
		this.label.setLayout(null);
		this.label.setBounds(x, y, width, height);
		this.label.setOpaque(true);
	}
	

	public Brick hit() {
		return null;
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




	public JLabel getLabel() {
		return label;
	}





	public void setLabel(JLabel label) {
		this.label = label;
	}

}
