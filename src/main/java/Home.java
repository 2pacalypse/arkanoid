package main.java;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Home {
	
	public static final int startX = 75;
	public static final int startY = 250;
	public static final int margin = 10;


	private JPanel panel;
	private JLabel[] buttons;
	private JLabel bg;
	
	

	public Home() {
		setPanel(new JPanel());
		setButtons(new JLabel[Game.HOME_NUM_BUTTONS]);
		bg = new JLabel();
		getPanel().setLayout(null);
		
		bg.setBounds(0, 0, Game.BOARD_WIDTH, Game.BOARD_HEIGHT);
		bg.setIcon(new ImageIcon(getClass().getResource(Game.GAME_BG_PATH)));
		
		

		
		for (int i = 0; i < Game.HOME_NUM_BUTTONS; i++) {
			getButtons()[i] = new JLabel();
			getButtons()[i].setBounds( startX + (Game.BRICK_DEFAULT_WIDTH + margin) * i, startY, Game.BRICK_DEFAULT_WIDTH, Game.BRICK_DEFAULT_HEIGHT);
			
			getButtons()[i].setIcon(new ImageIcon(getClass().getResource(Game.RED_BRICK_PATH)));
			getButtons()[i].setText(Game.HOME_BUTTON_TEXTS[i]);
			getButtons()[i].setVerticalTextPosition(JLabel.CENTER);
			getButtons()[i].setHorizontalTextPosition(JLabel.CENTER);
			getButtons()[i].setForeground(Game.HOME_BUTTON_TEXT_COLOR);
			getPanel().add(getButtons()[i]);
		}
		
		panel.setComponentZOrder(bg, panel.getComponentCount() - 1);
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
