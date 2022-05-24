package main.java;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Level {
	
	private JPanel panel;
	private ArrayList<Brick> bricks;
	
	Level(){
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 600, 600);
		setBricks(new ArrayList<Brick>());
		
	}
	
	public static Level firstLevel() {
		Level level = new Level();
		int margin = 6;

		for(int i = 0; i < 1; i++) {
			for (int j = 0; j < 6; j++) {
				Brick brick = new Brick(50 + j*(Brick.defaultWidth +margin), 200 + i*(Brick.defaultHeight + margin), Brick.defaultWidth, Brick.defaultHeight,1, Color.red);
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
