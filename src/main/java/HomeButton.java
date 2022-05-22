package main.java;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class HomeButton {
	
	private JLabel label;

	HomeButton() {
		setLabel(new JLabel());
		getLabel().setAlignmentX(JLabel.CENTER_ALIGNMENT);
		getLabel().setVisible(true);

	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}
	
	
	public void registerEvent(Runnable r) {
    	getLabel().addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				r.run();
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
    		
    	});
    	
    	
    }
    

}
