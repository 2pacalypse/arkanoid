package main.java;




import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Home {
	
	public static final int startX = 75;
	public static final int startY = 250;
	public static final int buttonWidth = 64;
	public static final int buttonHeight = 32;
	public static final int margin = 10;

	public static final int numButtons = 6;
	public static final String[] texts = {"PLAY", "OPTIONS", "SCORES", "HELP", "ABOUT", "EXIT"};
	
	private JPanel panel;
	private JLabel[] buttons;
	private JLabel bg;
	
	

	public Home() {
		setPanel(new JPanel());
		setButtons(new JLabel[numButtons]);
		bg = new JLabel();
		getPanel().setLayout(null);
		
		bg.setBounds(0, 0, Game.boardWidth, Game.boardHeight);
		bg.setIcon(new ImageIcon(getClass().getResource("../resources/gameBg.png")));
		
		

		
		for (int i = 0; i < numButtons; i++) {
			getButtons()[i] = new JLabel();
			getButtons()[i].setBounds( startX + (buttonWidth + margin) * i, startY, buttonWidth, buttonHeight);
			
			getButtons()[i].setIcon(new ImageIcon(getClass().getResource("../resources/redBrick.png")));
			getButtons()[i].setText(texts[i]);
			getButtons()[i].setVerticalTextPosition(JLabel.CENTER);
			getButtons()[i].setHorizontalTextPosition(JLabel.CENTER);
			getButtons()[i].setForeground(Color.white);
			getPanel().add(getButtons()[i]);
		}
		
		panel.setComponentZOrder(bg, 6);
		panel.add(bg);
	}


	public JPanel getPanel() {
		return panel;
	}


	public void setPanel(JPanel panel) {
		this.panel = panel;
	}


	public JLabel[] getButtons() {
		return buttons;
	}


	public void setButtons(JLabel[] buttons) {
		this.buttons = buttons;
	}

}
