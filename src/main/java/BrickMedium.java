package main.java;


import javax.swing.ImageIcon;


public class BrickMedium extends Brick {

	BrickMedium(int x, int y) {
		super(x, y);
		//getLabel().setBackground(Color.orange);
		//getLabel().setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		getLabel().setIcon(new ImageIcon(getClass().getResource(Game.ORANGE_BRICK_PATH)));
		setNumHitsTobreak(2);
	}
	
	public Brick hit() {
		return new BrickEasy(getX(), getY());
	}

}
