package main.java;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;


public class Paddle {
	
	public static final int startX = 300;
	public static final int startY = 550;
	public static final int startWidth = 100;
	public static final int startHeight = 20;
	public static final Color startBarColor = Color.GRAY;
	public static final Color startBorderColor = Color.BLACK;
	public static final int startBorderThickness = 4;
	
	
	private int currentX;
	private int currentY;
	private int currentWidth;
	private int currentHeight;
	private Color currentBarColor;
	private Color currentBorderColor;
	private int currentBorderThickness;
	
	
	
	
	private JLabel bar;
	
	
	Paddle(){
		
		setCurrentX(startX);
		setCurrentY(startY);
		setCurrentWidth(startWidth);
		setCurrentHeight(startHeight);
		setCurrentBarColor(startBarColor);
		setCurrentBorderColor(startBorderColor);
		setCurrentBorderThickness(startBorderThickness);
		

		setBar(new JLabel());
		getBar().setOpaque(true);
		getBar().setBounds(getCurrentX(), getCurrentY(), getCurrentWidth(), getCurrentHeight());
		getBar().setBackground(getCurrentBarColor());
		getBar().setBorder(BorderFactory.createLineBorder(currentBorderColor, currentBorderThickness, false));
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


	public Color getCurrentBarColor() {
		return currentBarColor;
	}


	public void setCurrentBarColor(Color currentBarColor) {
		this.currentBarColor = currentBarColor;
	}


	public Color getCurrentBorderColor() {
		return currentBorderColor;
	}


	public void setCurrentBorderColor(Color currentBorderColor) {
		this.currentBorderColor = currentBorderColor;
	}


	public int getCurrentBorderThickness() {
		return currentBorderThickness;
	}


	public void setCurrentBorderThickness(int currentBorderThickness) {
		this.currentBorderThickness = currentBorderThickness;
	}
	
	public void updateBar() {
		getBar().setBounds(getCurrentX(), getCurrentY(), getCurrentWidth(), getCurrentHeight());
	}

	
	
	
	
}
