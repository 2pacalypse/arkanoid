package main.java;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;

public class BrickMedium extends Brick {

	BrickMedium(int x, int y) {
		super(x, y);
		getLabel().setBackground(Color.orange);
		getLabel().setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		setNumHitsTobreak(2);
	}
	
	public Brick hit() {
		return new BrickEasy(getX(), getY());
	}

}
