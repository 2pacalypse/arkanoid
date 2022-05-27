package main.java;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Options {

	public static final int levelButtonStartX = 100;
	public static final int levelButtonStartY = 100;
	public static final int buttonWidth = 50;
	public static final int buttonHeight = 50;
	public static final int margin = 10;
	
	
	private JPanel panel;
	
	
	private int currentLevelIdx;
	private JLabel levelButtons[] = new JLabel[3];
	
	Options(){
		JLabel bg = new JLabel();
		bg.setBounds(0, 0, Game.boardWidth, Game.boardHeight);
		bg.setIcon(new ImageIcon(getClass().getResource("../resources/gameBg.png")));
		
		panel = new JPanel();
		panel.setLayout(null);
		
		for (int i = 0; i < 3; i++) {
			levelButtons[i] = new JLabel(Integer.toString(i), SwingConstants.CENTER);
			levelButtons[i].setBounds( levelButtonStartX + (buttonWidth + margin) * i, levelButtonStartY, buttonWidth, buttonHeight);
			levelButtons[i].setBackground(Color.gray);
			levelButtons[i].setOpaque(true);
			levelButtons[i].setForeground(Color.white);
			getPanel().add(levelButtons[i]);
		}
		levelButtons[0].setBackground(Color.DARK_GRAY);
		
		for (int i = 0; i < 3; i++) {
			levelButtons[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					levelButtons[currentLevelIdx].setBackground(Color.gray);
					e.getComponent().setBackground(Color.DARK_GRAY);
					currentLevelIdx = Integer.parseInt(((JLabel)(e.getComponent())).getText());
				}
			});
		}
		
		panel.add(bg);
		panel.setComponentZOrder(bg, panel.getComponentCount() - 1);
		
	}


	public JPanel getPanel() {
		return panel;
	}


	public void setPanel(JPanel panel) {
		this.panel = panel;
	}


	public int getCurrentLevelIdx() {
		return currentLevelIdx;
	}


	public void setCurrentLevelIdx(int currentLevelIdx) {
		this.currentLevelIdx = currentLevelIdx;
	}


	public JLabel[] getLevelButtons() {
		return levelButtons;
	}


	public void setLevelButtons(JLabel levelButtons[]) {
		this.levelButtons = levelButtons;
	}
}
