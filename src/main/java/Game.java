package main.java;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.AbstractAction;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Game implements Runnable {

	enum State {
		START, PLAY, OVER
	};

	public static final int startingNumLives = 3;
	public static final String numLivesLabelText = "Lives: ";

	private Paddle paddle = new Paddle();
	private Ball ball = new Ball();
	private JPanel panel = new JPanel();

	private int numLives = startingNumLives;
	private JLabel numLivesLabel = new JLabel();

	private State state = State.START;
	private Object lock = new Object();

	private Level currentLevel = Level.firstLevel();

	Game() {



		getPanel().setLayout(null);
		getPanel().add(getPaddle().getBar());
		getPanel().add(getBall().getBall());

		
		getPanel().add(getCurrentLevel().getPanel());
		getPanel().setVisible(true);
		

		getNumLivesLabel().setText(numLivesLabelText + numLives);
		getPanel().add(getNumLivesLabel());
		getNumLivesLabel().setBounds(Runner.boardWidth - 50, 0, 100, 20);

		getPanel().addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				paddle.setCurrentX(e.getX());
				paddle.updateBar();
			}
		});

		InputMap imap = getPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		imap.put(KeyStroke.getKeyStroke("SPACE"), "spaceAction");
		ActionMap amap = getPanel().getActionMap();
		amap.put("spaceAction", new AbstractAction() {
			private static final long serialVersionUID = -7644732643919166651L;

			public void actionPerformed(ActionEvent e) {

				synchronized (lock) {
					setState(State.PLAY);
					lock.notify();

				}
			}
		});

	}

	public void start() {

	}

	public Paddle getPaddle() {
		return paddle;
	}

	public void setPaddle(Paddle paddle) {
		this.paddle = paddle;
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	@Override
	public void run() {

		while (numLives > 0) {
			synchronized (lock) {
				while (getState() == State.START) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			
			getBall().setCurrentX((getBall().getCurrentX() + getBall().getCurrentVelocityX()));
			getBall().setCurrentY((getBall().getCurrentY() + getBall().getCurrentVelocityY()));
			getBall().updateBall();

			for (Brick b: getCurrentLevel().getBricks()) {
				System.out.println(b.getX() + " " + b.getY());
				if ((ball.getCurrentY() <= (b.getY() + b.getHeight())) && ball.getCurrentX() >= b.getX() && ball.getCurrentX() <= b.getX() + b.getWidth()) {
					b.getLabel().setBackground(Color.orange);
					ball.setCurrentY(b.getY() + b.getHeight()  );
					getBall().updateBall();
					ball.setCurrentVelocityY(-ball.getCurrentVelocityY());
					
					b.getLabel().setBackground(Color.orange);
					continue;
				}
			}
			
		
			
			
		

			
			
			if (getBall().getCurrentX() >= 600 - getBall().getCurrentR()) {
				getBall().setCurrentVelocityX(-getBall().getCurrentVelocityX());
			}
			if (getBall().getCurrentY() <= 0) {
				getBall().setCurrentVelocityY(-getBall().getCurrentVelocityY());
			}
			if (getBall().getCurrentX() <= 0) {
				getBall().setCurrentVelocityX(-getBall().getCurrentVelocityX());
			}

			if (getBall().getCurrentY() + getBall().getCurrentR() >= getPaddle().getCurrentY()
					&& (getBall().getCurrentX() + getBall().getCurrentR() >= getPaddle().getCurrentX()
							&& getBall().getCurrentX() <= getPaddle().getCurrentX() + getPaddle().getCurrentWidth())) {
				getBall().setCurrentVelocityY(-getBall().getCurrentVelocityY());
			}

			if (getBall().getCurrentY() >= 600) {
				setNumLives(getNumLives() - 1);
				getBall().reset();
				setState(State.START);
				getNumLivesLabel().setText(numLivesLabelText + numLives);

			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		System.out.println("game over");

	}

	public int getNumLives() {
		return numLives;
	}

	public void setNumLives(int numLives) {
		this.numLives = numLives;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public JLabel getNumLivesLabel() {
		return numLivesLabel;
	}

	public void setNumLivesLabel(JLabel numLivesLabel) {
		this.numLivesLabel = numLivesLabel;
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(Level currentLevel) {
		this.currentLevel = currentLevel;
	}

}
