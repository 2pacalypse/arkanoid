package main.java;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class BrickHard extends Brick {

	BrickHard(int x, int y) {
		super(x, y);
		//getLabel().setBackground(Color.black);
		//getLabel().setBorder(BorderFactory.createEtchedBorder(Color.darkGray, Color.lightGray));
		getLabel().setIcon(new ImageIcon(getClass().getResource("../resources/yellowBrick.png")));
		setNumHitsTobreak(3);
	}
	

	public Brick hit() {
		return new BrickMedium(getX(), getY());
		
	}

}
