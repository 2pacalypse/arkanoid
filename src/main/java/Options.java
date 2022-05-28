package main.java;



import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Options {


	
	
	private JPanel panel;
	
	
	private int selectedPaddleIdx = 1;
	private JButton levelButtons[] = new JButton[3];
	private JButton paddleButtons[] = new JButton[3];
	private JLabel paddle;
	private JLabel selectLevel;
	private JLabel selectPaddle;
	
	Options(){
		JLabel bg = new JLabel();
		bg.setBounds(0, 0, Game.BOARD_WIDTH, Game.BOARD_HEIGHT);
		bg.setIcon(new ImageIcon(getClass().getResource(Game.GAME_BG_PATH)));
		
		panel = new JPanel();
		panel.setLayout(null);
		
		selectLevel = new JLabel("Select Level");
		selectLevel.setBounds(Game.LEVEL_BUTTONS_START_X + 75, Game.LEVEL_BUTTONS_START_Y - 50, 100, 50);
		panel.add(selectLevel);
		
		
		for (int i = 0; i < 3; i++) {
			levelButtons[i] = new JButton();
			levelButtons[i].setBounds( Game.LEVEL_BUTTONS_START_X + (Game.BRICK_DEFAULT_WIDTH + Game.LEVEL_BUTTONS_MARGIN) * i, Game.LEVEL_BUTTONS_START_Y, Game.BRICK_DEFAULT_WIDTH, Game.BRICK_DEFAULT_HEIGHT);
			levelButtons[i].setIcon(new ImageIcon(getClass().getResource(Game.RED_BRICK_PATH)));
			levelButtons[i].setText(Integer.toString(i));
			levelButtons[i].setVerticalTextPosition(JLabel.CENTER);
			levelButtons[i].setHorizontalTextPosition(JLabel.CENTER);
			levelButtons[i].setForeground(Game.OPTIONS_LEVEL_BUTTON_TEXT_COLOR);
			levelButtons[i].setRolloverEnabled(false);
			levelButtons[i].setBorder(null);
			getPanel().add(levelButtons[i]);
		}
		levelButtons[0].setIcon(new ImageIcon(getClass().getResource(Game.BLUE_BRICK_PATH)));
		

		
		selectPaddle = new JLabel("Select Paddle");
		selectPaddle.setBounds(Game.PADDLE_BUTTONS_START_X + 75, Game.PADDLE_BUTTONS_START_Y - 50, 100, 50);
		panel.add(selectPaddle);
		
		
		for (int i = 0; i < 3; i++) {
			paddleButtons[i] = new JButton();
			
			
			paddleButtons[i].setBounds( Game.PADDLE_BUTTONS_START_X + (Game.BRICK_DEFAULT_WIDTH + Game.PADDLE_BUTTONS_MARGIN) * i, Game.PADDLE_BUTTONS_START_Y, Game.BRICK_DEFAULT_WIDTH, Game.BRICK_DEFAULT_HEIGHT);
			paddleButtons[i].setIcon(new ImageIcon(getClass().getResource(Game.RED_BRICK_PATH)));
			paddleButtons[i].setText(Game.PADDLE_BUTTONS_TEXTS[i]);
			paddleButtons[i].setForeground(Game.OPTIONS_PADDLE_BUTTON_TEXT_COLOR);
			paddleButtons[i].setRolloverEnabled(false);
			paddleButtons[i].setBorder(null);
			paddleButtons[i].setVerticalTextPosition(JLabel.CENTER);
			paddleButtons[i].setHorizontalTextPosition(JLabel.CENTER);
			paddleButtons[i].setActionCommand(Integer.toString(i));
			getPanel().add(paddleButtons[i]);
		}
		
		
		paddleButtons[1].setIcon(new ImageIcon(getClass().getResource(Game.BLUE_BRICK_PATH)));
		
		
		setPaddle(new JLabel());
		getPaddle().setIcon(new ImageIcon(getClass().getResource(Game.PADDLE_MEDIUM_PATH)));
		getPaddle().setBounds(Game.OPTIONS_PADDLE_START_X, Game.OPTIONS_PADDLE_START_Y, Game.PADDLE_MEDIUM_WIDTH, Game.PADDLE_HEIGHT);
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


	public JLabel getPaddle() {
		return paddle;
	}


	public void setPaddle(JLabel paddle) {
		this.paddle = paddle;
	}


	public int getSelectedPaddleIdx() {
		return selectedPaddleIdx;
	}


	public void setSelectedPaddleIdx(int selectedPaddleIdx) {
		this.selectedPaddleIdx = selectedPaddleIdx;
	}
}
