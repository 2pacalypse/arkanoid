package main.java;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.concurrent.Callable;

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
import javax.swing.JButton;

import main.java.BallBrickIntersection.Side;

public class Game {
	public static final String windowTitle = "Arkanoid";
	public static final int boardWidth = 600;
	public static final int boardHeight = 600;
	public static final String homePanel = "home";
	public static final String gamePanel = "game";
	public static final int startingNumLives = 3;
	public static final String numLivesLabelText = "Lives: ";
	public static final String scoreLabelText = "Score: ";
	public static final String levelLabelText = "Level: ";
	


	enum State {
		ZERO, START, PLAY, INTERRUPTED, OVER
	};

	private JFrame frame;
	private JLabel bg = new JLabel();
	private JPanel panel = new JPanel();

	private Paddle paddle = new Paddle();
	private Ball ball = new Ball();
	private Home home = new Home();
	private Scores scores = new Scores();
	private Options options = new Options();
	private Level currentLevel;

	private int currentLevelIdx = 0;
	private JLabel currentLevelLabel = new JLabel();

	private ArrayList<Callable<Level>> levels = new ArrayList<Callable<Level>>();

	private int numLives = startingNumLives;
	private JLabel numLivesLabel = new JLabel();

	private State state = State.START;
	private Object lock = new Object();

	JPanel cards = new JPanel(new CardLayout());

	private int currentScore;
	private JLabel currentScoreLabel = new JLabel();
	
	boolean arrowMoveRight = false;
	boolean arrowMoveLeft = false;
	
	int mouseLastPosX = paddle.getCurrentX();

	Game() {
		levels.add(new Callable<Level>() {

			@Override
			public Level call() throws Exception {
				// TODO Auto-generated method stub
				return Level.firstLevel();
			}

		});

		levels.add(new Callable<Level>() {

			@Override
			public Level call() throws Exception {
				// TODO Auto-generated method stub	
				return Level.dummyLevel();
			}

		});

		levels.add(new Callable<Level>() {

			@Override
			public Level call() throws Exception {
				// TODO Auto-generated method stub
				return Level.dummyLevel();
			}

		});
		
		try {
			currentLevel = levels.get(0).call();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

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

		currentScoreLabel.setText(scoreLabelText + currentScore);
		currentScoreLabel.setBounds(0, 0, 100, 20);
		currentScoreLabel.setForeground(Color.white);
		getPanel().add(currentScoreLabel);
		getPanel().setComponentZOrder(currentScoreLabel, 0);

		currentLevelLabel.setText(levelLabelText + currentLevelIdx);
		currentLevelLabel.setBounds(275, 0, 100, 20);
		currentLevelLabel.setForeground(Color.white);
		getPanel().add(currentLevelLabel);
		getPanel().setComponentZOrder(currentLevelLabel, 0);

		
		registerMouseMovementListener();
		registerArrowMovementListener();
		registerBallKickListener();
		registerBackListener();
		registerQuitListener();
		registerHomeButtonListeners();
		registerOptionsLevelButtonListeners();
		registerOptionsPaddleButtonListeners();

		



		cards.add(panel, "panel");
		cards.add(home.getPanel(), "home");
		cards.add(scores.getPanel(), "scoretable");
		cards.add(options.getPanel(), "options");
		((CardLayout) (cards.getLayout())).show(cards, "home");

		frame.add(cards);
		frame.setVisible(true);
	}
	
	
	private void registerOptionsPaddleButtonListeners() {
		for (int i = 0; i < 3; i++) {
			options.getPaddleButtons()[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					options.getPaddleButtons()[options.getSelectedPaddleIdx()].setBackground(Color.gray);
					e.getComponent().setBackground(Color.DARK_GRAY);
					int ball_type = Integer.parseInt(((JButton) (e.getComponent())).getActionCommand());
					options.setSelectedPaddleIdx(ball_type);
					if (ball_type == 0) {
						paddle.getBar().setIcon(new ImageIcon(getClass().getResource("../resources/paddleSmall.png")));
						paddle.setCurrentWidth(96);
						options.getPaddle()
								.setIcon(new ImageIcon(getClass().getResource("../resources/paddleSmall.png")));
						options.getPaddle().setBounds(400, 500, 96, 24);
					} else if (ball_type == 1) {
						paddle.getBar().setIcon(new ImageIcon(getClass().getResource("../resources/paddle.png")));
						paddle.setCurrentWidth(128);
						options.getPaddle().setIcon(new ImageIcon(getClass().getResource("../resources/paddle.png")));
						options.getPaddle().setBounds(400, 500, 128, 24);
					} else if (ball_type == 2) {
						paddle.getBar().setIcon(new ImageIcon(getClass().getResource("../resources/paddleBig.png")));
						paddle.setCurrentWidth(160);
						options.getPaddle()
								.setIcon(new ImageIcon(getClass().getResource("../resources/paddleBig.png")));
						options.getPaddle().setBounds(400, 500, 160, 24);
					}
				}
			});
		}
	}
	
	private void registerOptionsLevelButtonListeners(){
		for (int i = 0; i < 3; i++) {
			options.getLevelButtons()[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					options.getLevelButtons()[currentLevelIdx].setBackground(Color.gray);
					currentLevelIdx = Integer.parseInt(((JButton) (e.getComponent())).getText());
					options.getLevelButtons()[currentLevelIdx].setBackground(Color.gray);
					e.getComponent().setBackground(Color.DARK_GRAY);
					
					try {
						currentLevel = levels.get(currentLevelIdx).call();
						currentLevelLabel.setText(levelLabelText + currentLevelIdx);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
	}
	
	private void registerHomeButtonListeners() {

		home.getButtons()[0].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				((CardLayout) (cards.getLayout())).show(cards, "panel");
				state = State.START;
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


		home.getButtons()[1].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				((CardLayout) (cards.getLayout())).show(cards, "options");
			}
		});

		
		home.getButtons()[2].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				((CardLayout) (cards.getLayout())).show(cards, "scoretable");
			}
		});

		home.getButtons()[3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(cards, "help text", "Help", JOptionPane.QUESTION_MESSAGE);

			}
		});

		home.getButtons()[4].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(cards, "ABOUT TEXT", "About", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		home.getButtons()[5].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (JOptionPane.showConfirmDialog(cards, "exit text", "Quit", JOptionPane.YES_NO_OPTION) == 0) {
					System.exit(0);
				}

			}
		});

	}
	
	private void registerQuitListener() {
		InputMap imap = cards.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap amap = cards.getActionMap();
		imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK), "quitAction");
		amap.put("quitAction", new AbstractAction() {

			private static final long serialVersionUID = 9144157574387174169L;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(cards, "exit text", "Quit", JOptionPane.YES_NO_OPTION) == 0) {
					System.exit(0);
				}

			}

		});
	}
	
	private void registerBackListener() {
		InputMap imap = cards.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		imap.put(KeyStroke.getKeyStroke("BACK_SPACE"), "backSpaceAction");
		ActionMap amap = cards.getActionMap();
		amap.put("backSpaceAction", new AbstractAction() {
			private static final long serialVersionUID = -7644732643919166651L;

			public void actionPerformed(ActionEvent e) {

				synchronized (lock) {
					if (state == State.PLAY || state == State.START) {
						state = State.INTERRUPTED;
						lock.notify();
					}

				}
				((CardLayout) (cards.getLayout())).show(cards, "home");

			}
		});
	}
	
	private void registerBallKickListener() {
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
	
	private void registerMouseMovementListener() {
		getPanel().addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				if (state == State.PLAY) {
					getPaddle().setCurrentX(e.getX());
					getPaddle().updateBar();
				}


			}
		});
	}
	
	private void registerArrowMovementListener() {
		InputMap imap = getPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap amap = getPanel().getActionMap();
		
		imap.put(KeyStroke.getKeyStroke("pressed RIGHT"), "rightArrowAction");
		amap.put("rightArrowAction", new AbstractAction() {
			private static final long serialVersionUID = -7644732643919166651L;

			public void actionPerformed(ActionEvent e) {
				
				arrowMoveRight = true;

			}
		});
		
		imap.put(KeyStroke.getKeyStroke("released RIGHT"), "rightArrowActionRelease");
		amap.put("rightArrowActionRelease", new AbstractAction() {
			private static final long serialVersionUID = -7644732643919166651L;

			public void actionPerformed(ActionEvent e) {
				
				arrowMoveRight = false;

			}
		});
		
		imap.put(KeyStroke.getKeyStroke("pressed LEFT"), "leftArrowAction");
		amap.put("leftArrowAction", new AbstractAction() {
			private static final long serialVersionUID = -7644732643919166651L;

			public void actionPerformed(ActionEvent e) {
				
				arrowMoveLeft = true;

			}
		});
		
		imap.put(KeyStroke.getKeyStroke("released LEFT"), "leftArrowActionRelease");
		amap.put("leftArrowActionRelease", new AbstractAction() {
			private static final long serialVersionUID = -7644732643919166651L;

			public void actionPerformed(ActionEvent e) {
				
				arrowMoveLeft = false;

			}
		});

		
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
			
			if(arrowMoveRight) {
				paddle.setCurrentX(paddle.getCurrentX() + 5);
				paddle.updateBar();
			}
			
			if(arrowMoveLeft) {
				paddle.setCurrentX(paddle.getCurrentX() - 5);
				paddle.updateBar();
			}


			if (state == State.INTERRUPTED) {
				getBall().reset();
				state = State.ZERO;
				panel.remove(currentLevel.getPanel());
				currentLevelIdx = 0;
				currentLevelLabel.setText(levelLabelText + currentLevelIdx);

				try {
					currentLevel = levels.get(currentLevelIdx).call();
				} catch (Exception e) {
					e.printStackTrace();
				}
				panel.add(currentLevel.getPanel(), 2);

				setNumLives(startingNumLives);
				getNumLivesLabel().setText(numLivesLabelText + numLives);
				currentScore = 0;
				currentScoreLabel.setText(scoreLabelText + currentScore);

				return;
			}

			if (getCurrentLevel().getBricks().size() == 0) {
				JOptionPane.showMessageDialog(getPanel(), "You have passed the level.", "Success!",
						JOptionPane.INFORMATION_MESSAGE);

				state = State.START;
				panel.remove(currentLevel.getPanel());
				currentLevelIdx++;

				try {
					currentLevel = levels.get(currentLevelIdx).call();
					currentLevelLabel.setText(levelLabelText + currentLevelIdx);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(getPanel(), "You have cleared all the levels.", "Success!",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				}
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
				currentScore += 10;
				currentScoreLabel.setText(scoreLabelText + currentScore);

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
			} 

			else if (getBall().getCurrentX() + getBall().getCurrentVelocityX() >= 600 - getBall().getCurrentR()) {
				getBall().setCurrentX(600 - getBall().getCurrentR());
				getBall().setCurrentVelocityX(-getBall().getCurrentVelocityX());
				getBall().updateBall();
			}
			else if (getBall().getCurrentY() + getBall().getCurrentVelocityY() <= 0) {
				getBall().setCurrentY(0);
				getBall().setCurrentVelocityY(-getBall().getCurrentVelocityY());
				getBall().updateBall();
			}
			else if (getBall().getCurrentX() + getBall().getCurrentVelocityX() <= 0) {
				getBall().setCurrentX(0);
				getBall().setCurrentVelocityX(-getBall().getCurrentVelocityX());
				getBall().updateBall();
			}

			else if (getBall().getCurrentY() + getBall().getCurrentVelocityY() >= 600) {
				setNumLives(getNumLives() - 1);
				getBall().reset();
				setState(State.START);
				getNumLivesLabel().setText(numLivesLabelText + numLives);

			}
			else {
				getBall().setCurrentX((getBall().getCurrentX() + getBall().getCurrentVelocityX()));
				getBall().setCurrentY((getBall().getCurrentY() + getBall().getCurrentVelocityY()));
				getBall().updateBall();
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
		scores.addScore(name, currentScore);
		((CardLayout) (cards.getLayout())).show(cards, "scoretable");

		getBall().reset();
		setState(State.START);
		setNumLives(startingNumLives);
		getNumLivesLabel().setText(numLivesLabelText + numLives);
		currentScore = 0;
		currentScoreLabel.setText(scoreLabelText + currentScore);

		state = State.START;
		panel.remove(currentLevel.getPanel());
		currentLevelIdx = 0;
		currentLevelLabel.setText(levelLabelText + currentLevelIdx);
		try {
			currentLevel = levels.get(currentLevelIdx).call();
		} catch (Exception e) {
			e.printStackTrace();
		}
		panel.add(currentLevel.getPanel(), 2);
		panel.repaint();

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
