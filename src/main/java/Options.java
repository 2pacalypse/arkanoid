package main.java;


import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Options {

	public static final int levelButtonStartX = 100;
	public static final int levelButtonStartY = 100;
	public static final int buttonWidth = 50;
	public static final int buttonHeight = 50;
	public static final int margin = 10;
	
	
	private JPanel panel;
	
	
	private int currentLevelIdx = 0;
	private int selectedPaddleIdx = 1;
	private JButton levelButtons[] = new JButton[3];
	private JButton paddleButtons[] = new JButton[3];
	private JLabel paddle;
	
	Options(){
		JLabel bg = new JLabel();
		bg.setBounds(0, 0, Game.BOARD_WIDTH, Game.BOARD_HEIGHT);
		bg.setIcon(new ImageIcon(getClass().getResource(Game.GAME_BG_PATH)));
		
		panel = new JPanel();
		panel.setLayout(null);
		
		for (int i = 0; i < 3; i++) {
			levelButtons[i] = new JButton(Integer.toString(i));
			levelButtons[i].setBounds( levelButtonStartX + (buttonWidth + margin) * i, levelButtonStartY, buttonWidth, buttonHeight);
			levelButtons[i].setBackground(Game.OPTIONS_LEVEL_BUTTON_COLOR);
			levelButtons[i].setOpaque(true);
			levelButtons[i].setForeground(Game.OPTIONS_LEVEL_BUTTON_TEXT_COLOR);
			levelButtons[i].setFocusable(false);
			getPanel().add(levelButtons[i]);
		}
		
		levelButtons[0].setBackground(Game.OPTIONS_LEVEL_BUTTON_COLOR_SELECTED);
		
		
		
		for (int i = 0; i < 3; i++) {
			paddleButtons[i] = new JButton();
			paddleButtons[i].setText("Small");
			paddleButtons[i].setActionCommand(Integer.toString(i));
			paddleButtons[i].setBounds( levelButtonStartX + (100 + margin) * i, 400, 100, buttonHeight);
			paddleButtons[i].setBackground(Game.OPTIONS_PADDLE_BUTTON_COLOR);
			paddleButtons[i].setOpaque(true);
			paddleButtons[i].setForeground(Game.OPTIONS_PADDLE_BUTTON_TEXT_COLOR);
			paddleButtons[i].setFocusable(false);
			getPanel().add(paddleButtons[i]);
		}
		
		paddleButtons[1].setBackground(Game.OPTIONS_PADDLE_BUTTON_COLOR_SELECTED);
		
		setPaddle(new JLabel());
		getPaddle().setIcon(new ImageIcon(getClass().getResource(Game.PADDLE_MEDIUM_PATH)));
		getPaddle().setBounds(400, 500, Game.PADDLE_MEDIUM_WIDTH, Game.PADDLE_HEIGHT);
		panel.add(getPaddle());
		

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


	public JButton[] getLevelButtons() {
		return levelButtons;
	}


	public void setLevelButtons(JButton levelButtons[]) {
		this.levelButtons = levelButtons;
	}


	public JButton[] getPaddleButtons() {
		return paddleButtons;
	}


	public void setPaddleButtons(JButton paddleButtons[]) {
		this.paddleButtons = paddleButtons;
	}


	public int getSelectedPaddleIdx() {
		return selectedPaddleIdx;
	}


	public void setSelectedPaddleIdx(int selectedPaddleIdx) {
		this.selectedPaddleIdx = selectedPaddleIdx;
	}


	public JLabel getPaddle() {
		return paddle;
	}


	public void setPaddle(JLabel paddle) {
		this.paddle = paddle;
	}
}
