package main.java;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;

public class BrickEasy extends Brick {

	BrickEasy(int x, int y) {
		super(x, y);
		setNumHitsTobreak(1);
		//getLabel().setBackground(Color.red);
		getLabel().setIcon(new ImageIcon(getClass().getResource("../resources/redBrick.png")));
		//getLabel().setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	}

}
