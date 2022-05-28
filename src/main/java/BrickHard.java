package main.java;


import javax.swing.ImageIcon;

public class BrickHard extends Brick {

	BrickHard(int x, int y) {
		super(x, y);
		//getLabel().setBackground(Color.black);
		//getLabel().setBorder(BorderFactory.createEtchedBorder(Color.darkGray, Color.lightGray));
		getLabel().setIcon(new ImageIcon(getClass().getResource(Game.YELLOW_BRICK_PATH)));
		setNumHitsTobreak(3);
	}
	

	public Brick hit() {
		return new BrickMedium(getX(), getY());
		
	}

}
