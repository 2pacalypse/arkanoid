package main.java;


import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Level {
	
	private JPanel panel;
	private ArrayList<Brick> bricks;
	
	Level(){
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 600, 600);
		setBricks(new ArrayList<Brick>());
		panel.setOpaque(false);
		
	}
	
	public static Level dummyLevel() {
		Level level = new Level();
		int margin = 6;

		for(int i = 0; i < 1 ; i++) {
			for (int j = 0; j < 1; j++) {
				Brick brick = new BrickEasy(20 + j*(Game.BRICK_DEFAULT_WIDTH +margin), 200 + i*(Game.BRICK_DEFAULT_HEIGHT + margin));
				level.getPanel().add(brick.getLabel());
				level.getBricks().add(brick);
			}
		}
		

		
		level.getPanel().setVisible(true);
		return level;
	}
	
	public static Level secondLevel() {
		Level level = new Level();
		
		int numBricksWanted = 10;
		int frameTop = 400;
		int frameBottom = 0;
		int frameLeft = 0;
		int frameRight = 600 - Game.BRICK_DEFAULT_WIDTH;
		Random rnd = new Random(42);
		ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
		while(numBricksWanted > 0) {
			int startX =  frameLeft + (int)(rnd.nextDouble()*(frameRight - frameLeft));
			int startY =  frameBottom + (int)(rnd.nextDouble()*(frameTop - frameBottom));
			Rectangle candidate = new Rectangle(startX, startY, Game.BRICK_DEFAULT_WIDTH, Game.BRICK_DEFAULT_HEIGHT);
			boolean isOkay = true;
			for (Rectangle rect : rects) {
				if(candidate.intersects(rect)) {
					isOkay = false;
					break;
				}
			}
			if (isOkay) {
				rects.add(candidate);
				numBricksWanted--;
			}
			
				

			}

		for (Rectangle rect: rects) {
			int brickType = rnd.nextInt(3);
			Brick brick = null;
			if (brickType == 0) {
				brick = new BrickEasy(rect.x, rect.y);
			}else if(brickType == 1) {
				brick = new BrickMedium(rect.x, rect.y);
			}else if (brickType == 2) {
				brick = new BrickHard(rect.x, rect.y);
			}else {
				
			}
			level.getPanel().add(brick.getLabel());
			level.getBricks().add(brick);
		
		}
		
		
		level.getPanel().setVisible(true);
		return level;
		
	}
	
	public static Level firstLevel() {
		Level level = new Level();
		int margin = 6;

		for(int i = 0; i < 2 ; i++) {
			for (int j = 0; j < 6; j++) {
				Brick brick = new BrickEasy(75 + j*(Game.BRICK_DEFAULT_WIDTH +margin), 100 + i*(Game.BRICK_DEFAULT_HEIGHT + margin));
				level.getPanel().add(brick.getLabel());
				level.getBricks().add(brick);
			}
		}
		
		for(int i = 2; i < 4 ; i++) {
			for (int j = 0; j < 6; j++) {
				Brick brick = new BrickMedium(75 + j*(Game.BRICK_DEFAULT_WIDTH +margin), 100 + i*(Game.BRICK_DEFAULT_HEIGHT + margin));
				level.getPanel().add(brick.getLabel());
				level.getBricks().add(brick);
			}
		}
		for(int i = 4; i < 6 ; i++) {
			for (int j = 0; j < 6; j++) {
				Brick brick = new BrickHard(75 + j*(Game.BRICK_DEFAULT_WIDTH +margin), 100 + i*(Game.BRICK_DEFAULT_HEIGHT + margin));
				level.getPanel().add(brick.getLabel());
				level.getBricks().add(brick);
			}
		}
		
		level.getPanel().setVisible(true);
		return level;
		
	}


	public JPanel getPanel() {
		return panel;
	}


	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public ArrayList<Brick> getBricks() {
		return bricks;
	}

	public void setBricks(ArrayList<Brick> bricks) {
		this.bricks = bricks;
	}
	
	
}
