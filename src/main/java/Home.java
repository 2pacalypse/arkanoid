package main.java;



import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class Home {

	public static final int numButtons = 6;
	public static final String[] texts = {"NEW GAME", "OPTIONS", "SCORES", "HELP", "ABOUT", "EXIT"};
	
	private JPanel panel;
	private HomeButton[] buttons;
	

	public Home() {
		setPanel(new JPanel());
		setButtons(new HomeButton[numButtons]);
		getPanel().setLayout(new BoxLayout(getPanel(), BoxLayout.Y_AXIS));
		getPanel().add(Box.createRigidArea(new Dimension(0,100)));
		
		for (int i = 0; i < numButtons; i++) {
			getButtons()[i] = new HomeButton();
			getButtons()[i].getLabel().setText(texts[i]);
			getPanel().add(getButtons()[i].getLabel());
			getPanel().add(Box.createRigidArea(new Dimension(0,5)));
		}
	}


	public JPanel getPanel() {
		return panel;
	}


	public void setPanel(JPanel panel) {
		this.panel = panel;
	}


	public HomeButton[] getButtons() {
		return buttons;
	}


	public void setButtons(HomeButton[] buttons) {
		this.buttons = buttons;
	}

}
