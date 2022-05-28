package main.java;


import javax.swing.ImageIcon;


public class BrickEasy extends Brick {

	BrickEasy(int x, int y) {
		super(x, y);
		setNumHitsTobreak(1);
		//getLabel().setBackground(Color.red);
		//getLabel().setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		getLabel().setIcon(new ImageIcon(getClass().getResource(Game.RED_BRICK_PATH)));

	}

}
