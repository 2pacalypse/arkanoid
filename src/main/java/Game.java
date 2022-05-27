package main.java;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.AbstractAction;

import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import main.java.BallBrickIntersection.Side;

public class Game {
	public static final String windowTitle = "Arkanoid";
	public static final int boardWidth = 600;
	public static final int boardHeight = 600;
	public static final String homePanel = "home";
	public static final String gamePanel = "game";
	public static final int startingNumLives = 3;
	public static final String numLivesLabelText = "Lives: ";

	enum State {
		START, PLAY, OVER
	};

	private JFrame frame;
	private JLabel bg = new JLabel();
	private JPanel panel = new JPanel();

	private Paddle paddle = new Paddle();
	private Ball ball = new Ball();
	private Home home = new Home();
	private Scores scores = new Scores();
	private Level currentLevel = Level.dummyLevel();
	private int currentLevelIdx = 0;
	private Runnable[] levels = new Runnable[2];
	

	private int numLives = startingNumLives;
	private JLabel numLivesLabel = new JLabel();

	private State state = State.START;
	private Object lock = new Object();

	JPanel cards = new JPanel(new CardLayout());
	

	Game() {
		levels[0] = new Runnable() {
			@Override
			public void run() {
				Level.dummyLevel();
				
			}
			
		};
		
		levels[1] = new Runnable() {

			@Override
			public void run() {
				Level.firstLevel();
				
			}
			
		};
		
		
		
	
		

		frame = new JFrame(windowTitle);
		frame.getContentPane().setPreferredSize(new Dimension(boardWidth, boardHeight));
		frame.pack();

		getPanel().setLayout(null);
		getPanel().add(getPaddle().getBar());
		getPanel().add(getBall().getBall());
		getPanel().add(getCurrentLevel().getPanel());
		
		bg.setBounds(0, 0, boardWidth, boardHeight);
		bg.setIcon(new ImageIcon(getClass().getResource("../resources/gameBg.png")));
		
		getPanel().add(bg);
		getPanel().setComponentZOrder(bg, 3);
		
		


		
		

		getNumLivesLabel().setText(numLivesLabelText + numLives);
		getNumLivesLabel().setBounds(boardWidth - 50, 0, 100, 20);
		getNumLivesLabel().setForeground(Color.white);
		getPanel().add(getNumLivesLabel());
		getPanel().setComponentZOrder(getNumLivesLabel(), 0);

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
		
		
		InputMap imap2 = cards.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		imap2.put(KeyStroke.getKeyStroke("BACK_SPACE"), "backSpaceAction");
		ActionMap amap2 = cards.getActionMap();
		amap2.put("backSpaceAction", new AbstractAction() {
			private static final long serialVersionUID = -7644732643919166651L;

			public void actionPerformed(ActionEvent e) {
				((CardLayout) (cards.getLayout())).show(cards, "home");
				
			}
		});
		
		
		

		home.getButtons()[2].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				((CardLayout) (cards.getLayout())).show(cards, "scoretable");
			}
		});

		home.getButtons()[0].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				((CardLayout) (cards.getLayout())).show(cards, "panel");
				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub

						play();

					}

				});
				t.start();
			}

		});

		cards.add(panel, "panel");
		cards.add(home.getPanel(), "home");
		cards.add(scores.getPanel(), "scoretable");
		((CardLayout) (cards.getLayout())).show(cards, "home");

		frame.add(cards);
		frame.setVisible(true);	

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

	public void play() {

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
			
			if (getCurrentLevel().getBricks().size() == 0) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				state = State.START;
				panel.remove(currentLevel.getPanel());
				currentLevelIdx++;
				currentLevel = levels[currentLevelIdx].run();
				panel.add(currentLevel.getPanel(), 2);
				getBall().reset();
				panel.repaint();
				

				continue;
			}

			BallPaddleIntersection ballpaddle = new BallPaddleIntersection(ball, paddle);

			Brick closest = null;
			BallBrickIntersection closestIntersection = null;
			double closestDist = Float.POSITIVE_INFINITY;
			for (Brick brick : getCurrentLevel().getBricks()) {
				BallBrickIntersection result = new BallBrickIntersection(ball, brick);
				if (result.getDist() < closestDist) {
					closest = brick;
					closestDist = result.getDist();
					closestIntersection = result;
				}
			}

			if (closest != null) {
				BallBrickIntersection result = closestIntersection;
				if (result.getSide() == Side.RIGHT) {

					ball.setCurrentX(result.getIntersectionX());
					ball.setCurrentY(result.getIntersectionY());
					getBall().updateBall();
					ball.setCurrentVelocityX(-ball.getCurrentVelocityX());
					// closest.getLabel().setBackground(Color.green);

				} else if (result.getSide() == Side.DOWN) {
					ball.setCurrentX(result.getIntersectionX());
					ball.setCurrentY(result.getIntersectionY());
					getBall().updateBall();
					ball.setCurrentVelocityY(-ball.getCurrentVelocityY());
					// closest.getLabel().setBackground(Color.orange);

				} else if (result.getSide() == Side.UP) {
					ball.setCurrentX(result.getIntersectionX());
					ball.setCurrentY(result.getIntersectionY() - ball.getCurrentR());
					getBall().updateBall();
					ball.setCurrentVelocityY(-ball.getCurrentVelocityY());
					// closest.getLabel().setBackground(Color.yellow);

				} else if (result.getSide() == Side.LEFT) {
					ball.setCurrentX(result.getIntersectionX() - ball.getCurrentR());
					ball.setCurrentY(result.getIntersectionY());
					getBall().updateBall();
					ball.setCurrentVelocityX(-ball.getCurrentVelocityX());
					// closest.getLabel().setBackground(Color.black);
				}

				Brick newBrick = closest.hit();
				getCurrentLevel().getBricks().remove(closest);
				getCurrentLevel().getPanel().remove(closest.getLabel());
				if (newBrick != null) {
					getCurrentLevel().getBricks().add(newBrick);
					getCurrentLevel().getPanel().add(newBrick.getLabel());
				}

			} else if (ballpaddle.getDist() != Float.POSITIVE_INFINITY) {
				double alpha = (ballpaddle.getIntersectionX() - paddle.getCurrentX())
						/ (double) paddle.getCurrentWidth();
				double theta = -70 * (1 - alpha) + 70 * alpha;
				double magnitude = Math
						.sqrt(Math.pow(ball.getCurrentVelocityX(), 2) + Math.pow(ball.getCurrentVelocityY(), 2));

				double vx = (magnitude * Math.sin(Math.toRadians(theta)));
				double vy = -(magnitude * Math.cos(Math.toRadians(theta)));

				ball.setCurrentX(ballpaddle.getIntersectionX() - ball.getCurrentR() / 2);
				ball.setCurrentY(ballpaddle.getIntersectionY() - ball.getCurrentR());
				ball.setCurrentVelocityY(vy);
				ball.setCurrentVelocityX(vx);
				getBall().updateBall();
			} else {
				getBall().setCurrentX((getBall().getCurrentX() + getBall().getCurrentVelocityX()));
				getBall().setCurrentY((getBall().getCurrentY() + getBall().getCurrentVelocityY()));
				getBall().updateBall();
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

			if (getBall().getCurrentY() >= 600) {
				setNumLives(getNumLives() - 1);
				getBall().reset();
				setState(State.START);
				getNumLivesLabel().setText(numLivesLabelText + numLives);

			}

			getPanel().repaint();

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}


		String name = JOptionPane.showInputDialog(getPanel(), "Enter user name", "Game over!",
				JOptionPane.INFORMATION_MESSAGE);
		while (name == null || name.isEmpty()) {
			JOptionPane.showMessageDialog(getPanel(), "Please enter a user name", "Error", JOptionPane.ERROR_MESSAGE);
			name = JOptionPane.showInputDialog(getPanel(), "Enter user name", "Game over!",
					JOptionPane.INFORMATION_MESSAGE);
		}
		scores.addScore(name, 300);
		((CardLayout) (cards.getLayout())).show(cards, "scoretable");
		


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
