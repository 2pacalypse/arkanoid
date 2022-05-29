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
	public static final String WINDOW_TITLE = "Arkanoid";
	public static final int BOARD_WIDTH = 600;
	public static final int BOARD_HEIGHT = 600;

	public static final int DEFAULT_NUM_LIVES = 3;
	public static final String NUM_LIVES_LABEL_TEXT = "Lives: ";
	public static final String SCORE_LABEL_TEXT = "Score: ";
	public static final String LEVEL_LABEL_TEXT = "Level: ";
	
	private static final String CARDLAYOUT_HOME = "home";
	private static final String CARDLAYOUT_GAME = "game";
	private static final String CARDLAYOUT_OPTIONS = "options";
	private static final String CARDLAYOUT_SCORES = "scoretable";
	
	
	public static final Color LEVEL_LABEL_COLOR = Color.BLACK;
	public static final Color SCORE_LABEL_COLOR = Color.BLACK;
	public static final Color NUM_LIVES_LABEL_COLOR = Color.BLACK;
	


	
	public static final Color HOME_BUTTON_TEXT_COLOR = Color.white;
	public static final Color SCORE_TABLE_HEADER_BG_COLOR = Color.lightGray;
	public static final Color SCORE_TABLE_HEADER_FG_COLOR = Color.black;
	public static final Color OPTIONS_LEVEL_BUTTON_TEXT_COLOR = Color.white;
	public static final Color OPTIONS_PADDLE_BUTTON_TEXT_COLOR = Color.white;
	
	
	public static final int HOME_NUM_BUTTONS = 6;
	public static final String[] HOME_BUTTON_TEXTS = {"PLAY", "OPTIONS", "SCORES", "HELP", "ABOUT", "EXIT"};
	

	
	public static final int PADDLE_MAX_ANGLE = 70;
	public static final int GAME_CYCLE_SLEEP_MS = 5;
	
	
	public static final String EXIT_TEXT = "Are you sure you want to exit the game?";
	public static final String EXIT_TEXT_TITLE = "Quit";
	public static final String HELP_TEXT = "<html><ul><li>Yellow Bricks: 3 hits.</li><li>Orange Bricks: 2 hits.</li><li>Red Bricks: 1 hit.</li><li>Move either with mouse or left and right arrow keys.</li> <li>Get back to home with backspace key.</li><li>Only top 10 scores are saved.</li></ul></html>";
	public static final String HELP_TEXT_TITLE = "Help";
	public static final String ABOUT_TEXT = "ABOUT_TEXT";
	public static final String ABOUT_TEXT_TITLE = "About";
	public static final String USERNAME_NOT_PROVIDED_TITLE = "Error";
	public static final String USERNAME_NOT_PROVIDED_MESSAGE = "Please enter a user name";
	public static final String GAME_OVER_TITLE = "Game over!";
	public static final String GAME_OVER_MESSAGE = "Enter user name";
	public static final String LEVEL_PASS_TITLE = "Success!";
	public static final String LEVEL_PASS_TEXT = "You have passed the level.";
	public static final String ALL_LEVELS_PASS_TEXT = "You have cleared all the levels.";
	public static final String  ALL_LEVELS_PASS_TITLE = "Success!";
	
	public static final String GAME_BG_PATH = "../resources/gameBg.png";
	public static final String PADDLE_BIG_PATH = "../resources/paddleBig.png";
	public static final String PADDLE_MEDIUM_PATH = "../resources/paddle.png";
	public static final String PADDLE_SMALL_PATH = "../resources/paddleSmall.png";
	
	public static final String RED_BRICK_PATH = "../resources/redBrick.png";
	public static final String ORANGE_BRICK_PATH = "../resources/orangeBrick.png";
	public static final String YELLOW_BRICK_PATH = "../resources/yellowBrick.png";
	public static final String BLUE_BRICK_PATH = "../resources/blueBrick.png";
	
	
	public static final int BRICK_DEFAULT_WIDTH = 64;
	public static final int BRICK_DEFAULT_HEIGHT = 32;
	
	public static final int PADDLE_BIG_WIDTH = 160;
	public static final int PADDLE_MEDIUM_WIDTH = 128;
	public static final int PADDLE_SMALL_WIDTH = 96;
	public static final int PADDLE_START_X = 250;
	public static final int PADDLE_START_Y = 550;
	public static final int PADDLE_HEIGHT = 24;
	
	public static final int MAX_NUM_SCORES_TO_KEEP = 10;
	public static final String[] SCORE_TABLE_COLUMNS = {"Player", "Date","Time", "Score"};
	public static final String SCORE_SAVE_PATH = "./scores";
	public static final int SCORE_TABLE_ROW_HEIGHT = 20;
	public static final int TABLE_SCORE_COLUMN_INDEX = 3;
	
	public static final int HOME_BUTTONS_START_X = 75;
	public static final int HOME_BUTTONS_START_Y = 250;
	public static final int HOME_BUTTONS_MARGIN = 10;
	
	public static final int LEVEL_BUTTONS_START_X = 175;
	public static final int LEVEL_BUTTONS_START_Y = 200;
	public static final int LEVEL_BUTTONS_MARGIN = 10;
	
	public static final int PADDLE_BUTTONS_START_X = 175;
	public static final int PADDLE_BUTTONS_START_Y = 350;
	public static final int PADDLE_BUTTONS_MARGIN = 10;
	public static final String[] PADDLE_BUTTONS_TEXTS = {"SMALL", "MEDIUM", "BIG"};
	public static final int OPTIONS_PADDLE_START_X = 225;
	public static final int OPTIONS_PADDLE_START_Y = 400;
	
	public static final int BALL_START_X = 300;
	public static final int BALL_START_Y = 525;
	public static final int BALL_START_R = 24;
	public static final int BALL_START_VELOCITY_X = -2;
	public static final int BALL_START_VELOCITY_Y = -2;
	public static final String BALL_RED_PATH = "../resources/redBall.png";
	


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

	private int numLives = DEFAULT_NUM_LIVES;
	private JLabel numLivesLabel = new JLabel();

	private State state = State.START;
	private Object lock = new Object();

	JPanel cards = new JPanel(new CardLayout());

	private int currentScore;
	private JLabel currentScoreLabel = new JLabel();
	
	boolean arrowMoveRight = false;
	boolean arrowMoveLeft = false;
	
	int mouseLastPosX = paddle.getCurrentX();
	
	private JLabel back;

	Game() {
		buildLevelList();


		frame = new JFrame(WINDOW_TITLE);
		frame.getContentPane().setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		getPanel().setLayout(null);
		getPanel().add(getPaddle().getBar());
		getPanel().add(getBall().getBall());
		getPanel().add(getCurrentLevel().getPanel());





		buildTopBar();
		
		back = new JLabel("Press backspace to go back.");
		back.setBounds(Game.PADDLE_BUTTONS_START_X + 30, Game.BOARD_HEIGHT - 20, 200, 20);
		panel.add(back);
		
		bg.setBounds(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
		bg.setIcon(new ImageIcon(getClass().getResource(GAME_BG_PATH)));

		getPanel().add(bg);
		getPanel().setComponentZOrder(bg, getPanel().getComponentCount()  - 1);

		
		registerMouseMovementListener();
		registerArrowMovementListener();
		registerBallKickListener();
		registerBackListener();
		registerQuitListener();
		registerHomeButtonListeners();
		registerOptionsLevelButtonListeners();
		registerOptionsPaddleButtonListeners();

		



		cards.add(panel, CARDLAYOUT_GAME);
		cards.add(home.getPanel(), CARDLAYOUT_HOME);
		cards.add(scores.getPanel(), CARDLAYOUT_SCORES);
		cards.add(options.getPanel(), CARDLAYOUT_OPTIONS);
		((CardLayout) (cards.getLayout())).show(cards, CARDLAYOUT_HOME);

		frame.add(cards);
		frame.setVisible(true);
	}
	
	private void buildTopBar() {
		getNumLivesLabel().setText(NUM_LIVES_LABEL_TEXT + numLives);
		getNumLivesLabel().setBounds(BOARD_WIDTH - 50, 0, 100, 20);
		getNumLivesLabel().setForeground(NUM_LIVES_LABEL_COLOR);
		getPanel().add(getNumLivesLabel());
		getPanel().setComponentZOrder(getNumLivesLabel(), 0);
		
		currentScoreLabel.setText(SCORE_LABEL_TEXT + currentScore);
		currentScoreLabel.setBounds(0, 0, 100, 20);
		currentScoreLabel.setForeground(SCORE_LABEL_COLOR);
		getPanel().add(currentScoreLabel);
		getPanel().setComponentZOrder(currentScoreLabel, 0);

		currentLevelLabel.setText(LEVEL_LABEL_TEXT + currentLevelIdx);
		currentLevelLabel.setBounds(275, 0, 100, 20);
		currentLevelLabel.setForeground(LEVEL_LABEL_COLOR);
		getPanel().add(currentLevelLabel);
		getPanel().setComponentZOrder(currentLevelLabel, 0);
		
	}
	
	private void buildLevelList() {
		levels.add(new Callable<Level>() {

			@Override
			public Level call() throws Exception {
				// TODO Auto-generated method stub
				return Level.randomLevel(10);
			}

		});

		levels.add(new Callable<Level>() {

			@Override
			public Level call() throws Exception {
				// TODO Auto-generated method stub	
				return Level.randomLevel(20);
			}

		});

		levels.add(new Callable<Level>() {

			@Override
			public Level call() throws Exception {
				// TODO Auto-generated method stub
				return Level.randomLevel(30);
			}

		});
		
		try {
			currentLevel = levels.get(0).call();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
	
	
	private void registerOptionsPaddleButtonListeners() {
		for (int i = 0; i < 3; i++) {
			options.getPaddleButtons()[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					options.getPaddleButtons()[options.getSelectedPaddleIdx()].setIcon(new ImageIcon(getClass().getResource(Game.RED_BRICK_PATH)));
					((JButton) e.getComponent() ).setIcon(new ImageIcon(getClass().getResource(Game.BLUE_BRICK_PATH)));
					int paddle_type = Integer.parseInt(((JButton) (e.getComponent())).getActionCommand());
					options.setSelectedPaddleIdx(paddle_type);
					if (paddle_type == 0) {
						paddle.getBar().setIcon(new ImageIcon(getClass().getResource(PADDLE_SMALL_PATH)));
						paddle.setCurrentWidth(PADDLE_SMALL_WIDTH);
						paddle.updateBar();
						options.getPaddle()
								.setIcon(new ImageIcon(getClass().getResource(PADDLE_SMALL_PATH)));
						options.getPaddle().setBounds(Game.OPTIONS_PADDLE_START_X, Game.OPTIONS_PADDLE_START_Y, PADDLE_SMALL_WIDTH, PADDLE_HEIGHT);
					} else if (paddle_type == 1) {
						paddle.getBar().setIcon(new ImageIcon(getClass().getResource(PADDLE_MEDIUM_PATH)));
						paddle.setCurrentWidth(PADDLE_MEDIUM_WIDTH);
						paddle.updateBar();
						options.getPaddle().setIcon(new ImageIcon(getClass().getResource(PADDLE_MEDIUM_PATH)));
						options.getPaddle().setBounds(Game.OPTIONS_PADDLE_START_X, Game.OPTIONS_PADDLE_START_Y, PADDLE_MEDIUM_WIDTH, PADDLE_HEIGHT);
					} else if (paddle_type == 2) {
						paddle.getBar().setIcon(new ImageIcon(getClass().getResource(PADDLE_BIG_PATH)));
						paddle.setCurrentWidth(PADDLE_BIG_WIDTH);
						paddle.updateBar();
						options.getPaddle()
								.setIcon(new ImageIcon(getClass().getResource(PADDLE_BIG_PATH)));
						options.getPaddle().setBounds(Game.OPTIONS_PADDLE_START_X, Game.OPTIONS_PADDLE_START_Y, PADDLE_BIG_WIDTH, PADDLE_HEIGHT);
					}
				}
			});
		}
	}
	
	private void registerOptionsLevelButtonListeners(){
		for (int i = 0; i < 3; i++) {
			options.getLevelButtons()[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					panel.remove(currentLevel.getPanel());
					options.getLevelButtons()[options.getSelectedLevelIdx()].setIcon(new ImageIcon(getClass().getResource(Game.RED_BRICK_PATH)));
					
					((JButton)e.getComponent()).setIcon(new ImageIcon(getClass().getResource(Game.BLUE_BRICK_PATH)));
					
					
					try {
						currentLevelIdx = Integer.parseInt(((JButton) (e.getComponent())).getText());
						options.setSelectedLevelIdx(currentLevelIdx);
						currentLevel = levels.get(currentLevelIdx).call();
						currentLevelLabel.setText(LEVEL_LABEL_TEXT + currentLevelIdx);
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					panel.add(currentLevel.getPanel(), 2);
					panel.repaint();
					
				}
			});
		}
	}
	
	private void registerHomeButtonListeners() {

		home.getButtons()[0].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				((CardLayout) (cards.getLayout())).show(cards, CARDLAYOUT_GAME);
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
				((CardLayout) (cards.getLayout())).show(cards, CARDLAYOUT_OPTIONS);
			}
		});

		
		home.getButtons()[2].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				((CardLayout) (cards.getLayout())).show(cards, CARDLAYOUT_SCORES);
			}
		});

		home.getButtons()[3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(cards, HELP_TEXT, HELP_TEXT_TITLE, JOptionPane.QUESTION_MESSAGE);

			}
		});

		home.getButtons()[4].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(cards, ABOUT_TEXT, ABOUT_TEXT_TITLE, JOptionPane.INFORMATION_MESSAGE);

			}
		});
		home.getButtons()[5].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (JOptionPane.showConfirmDialog(cards, EXIT_TEXT, EXIT_TEXT_TITLE, JOptionPane.YES_NO_OPTION) == 0) {
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
				if (JOptionPane.showConfirmDialog(cards, EXIT_TEXT, EXIT_TEXT_TITLE, JOptionPane.YES_NO_OPTION) == 0) {
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
				((CardLayout) (cards.getLayout())).show(cards, CARDLAYOUT_HOME);

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
	
	private void resetGame() {
		//bring the ball to its starting position.
		getBall().reset();
		state = State.ZERO;
		
		//bring again the current level selected from options
		panel.remove(currentLevel.getPanel());
		currentLevelIdx = options.getSelectedLevelIdx();
		currentLevelLabel.setText(LEVEL_LABEL_TEXT + currentLevelIdx);
		try {
			currentLevel = levels.get(currentLevelIdx).call();
		} catch (Exception e) {
			e.printStackTrace();
		}
		panel.add(currentLevel.getPanel(), 2);


		//refresh the lives, reset the score, bring the paddle to its original position.
		setNumLives(DEFAULT_NUM_LIVES);
		getNumLivesLabel().setText(NUM_LIVES_LABEL_TEXT + numLives);
		currentScore = 0;
		currentScoreLabel.setText(SCORE_LABEL_TEXT + currentScore);
		getPaddle().setCurrentX(PADDLE_START_X);
		getPaddle().updateBar();
		panel.repaint();
	}

	public void play() {
		while (numLives > 0) {
			//wait for user to press SPACE
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
			
			//move the paddle accordingly if an arrow key is pressed
			if(arrowMoveRight) {
				paddle.setCurrentX(paddle.getCurrentX() + 5);
				paddle.updateBar();
			}
			
			if(arrowMoveLeft) {
				paddle.setCurrentX(paddle.getCurrentX() - 5);
				paddle.updateBar();
			}

			//when the backspace is pressed during game, i.e. the game is interrupted, reset the game.
			if (state == State.INTERRUPTED) {
				resetGame();
				return;
			}

			//when there is no brick left, pass the level
			if (getCurrentLevel().getBricks().size() == 0) {
				JOptionPane.showMessageDialog(getPanel(), LEVEL_PASS_TEXT, LEVEL_PASS_TITLE,
						JOptionPane.INFORMATION_MESSAGE);

				state = State.START; // user has to press space
				panel.remove(currentLevel.getPanel()); //remove current level
				currentLevelIdx++; //next level idx

				try {
					currentLevel = levels.get(currentLevelIdx).call();
					currentLevelLabel.setText(LEVEL_LABEL_TEXT + currentLevelIdx);
				} catch (Exception e) {
					//when there isn't any next level, it means game over.
					JOptionPane.showMessageDialog(getPanel(), ALL_LEVELS_PASS_TEXT, ALL_LEVELS_PASS_TITLE,
							JOptionPane.INFORMATION_MESSAGE);
					break;
				}
				panel.add(currentLevel.getPanel(), 2); //load the next level
				getBall().reset(); //bring the ball to its original position
				
				getPaddle().setCurrentX(PADDLE_START_X); //bring the paddle to its original position
				getPaddle().updateBar(); //redraw the paddle
				
				panel.repaint(); //redraw the whole scene

				continue;
			}

			//ball paddle intersection
			BallPaddleIntersection ballpaddle = new BallPaddleIntersection(ball, paddle);

			//get the closest ball brick intersection
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

			//if there is a ball brick intersection
			if (closest != null) {
				currentScore += 10;
				currentScoreLabel.setText(SCORE_LABEL_TEXT + currentScore);

				BallBrickIntersection result = closestIntersection;
				if (result.getSide() == Side.RIGHT) {
					//ball hits the brick from right
					ball.setCurrentX(result.getIntersectionX());
					ball.setCurrentY(result.getIntersectionY());
					getBall().updateBall();
					ball.setCurrentVelocityX(-ball.getCurrentVelocityX());

				} else if (result.getSide() == Side.DOWN) {
					//ball hits the brick from down
					ball.setCurrentX(result.getIntersectionX());
					ball.setCurrentY(result.getIntersectionY());
					getBall().updateBall();
					ball.setCurrentVelocityY(-ball.getCurrentVelocityY());


				} else if (result.getSide() == Side.UP) {
					//ball hits the brick from up
					ball.setCurrentX(result.getIntersectionX());
					ball.setCurrentY(result.getIntersectionY() - ball.getCurrentR());
					getBall().updateBall();
					ball.setCurrentVelocityY(-ball.getCurrentVelocityY());
					

				} else if (result.getSide() == Side.LEFT) {
					//ball hits the brick from left
					ball.setCurrentX(result.getIntersectionX() - ball.getCurrentR());
					ball.setCurrentY(result.getIntersectionY());
					getBall().updateBall();
					ball.setCurrentVelocityX(-ball.getCurrentVelocityX());
					
				}

				Brick newBrick = closest.hit(); // hitting the closest brick returns another one
				getCurrentLevel().getBricks().remove(closest); //remove the hit brick
				getCurrentLevel().getPanel().remove(closest.getLabel()); //remove the hit brick
				if (newBrick != null) {
					getCurrentLevel().getBricks().add(newBrick); //place the new one if there is
					getCurrentLevel().getPanel().add(newBrick.getLabel()); //place the new one if there is
				}

			} else if (ballpaddle.getDist() != Float.POSITIVE_INFINITY) {
				//if ball hits the paddle
				double alpha = (ballpaddle.getIntersectionX() - paddle.getCurrentX())
						/ (double) paddle.getCurrentWidth();
				double theta = -PADDLE_MAX_ANGLE * (1 - alpha) + PADDLE_MAX_ANGLE * alpha;
				double magnitude = Math
						.sqrt(Math.pow(ball.getCurrentVelocityX(), 2) + Math.pow(ball.getCurrentVelocityY(), 2));

				double vx = (magnitude * Math.sin(Math.toRadians(theta))); //calculate the new velocity
				double vy = -(magnitude * Math.cos(Math.toRadians(theta))); //in x and y

				ball.setCurrentX(ballpaddle.getIntersectionX() - ball.getCurrentR() / 2);
				ball.setCurrentY(ballpaddle.getIntersectionY() - ball.getCurrentR());
				ball.setCurrentVelocityY(vy);
				ball.setCurrentVelocityX(vx);
				getBall().updateBall();//update the ball velocity and x, y
			} 

			else if (getBall().getCurrentX() + getBall().getCurrentVelocityX() >= BOARD_WIDTH - getBall().getCurrentR()) {
				//ball hits the right wall
				getBall().setCurrentX(600 - getBall().getCurrentR());
				getBall().setCurrentVelocityX(-getBall().getCurrentVelocityX());
				getBall().updateBall();
			}
			else if (getBall().getCurrentY() + getBall().getCurrentVelocityY() <= 0) {
				//ball hits the upper wall
				getBall().setCurrentY(0);
				getBall().setCurrentVelocityY(-getBall().getCurrentVelocityY());
				getBall().updateBall();
			}
			else if (getBall().getCurrentX() + getBall().getCurrentVelocityX() <= 0) {
				//ball hits the left wall
				getBall().setCurrentX(0);
				getBall().setCurrentVelocityX(-getBall().getCurrentVelocityX());
				getBall().updateBall();
			}

			else if (getBall().getCurrentY() + getBall().getCurrentVelocityY() >= BOARD_HEIGHT) {
				//die when ball hits the lower wall
				setNumLives(getNumLives() - 1); //lose 1 life
				getBall().reset(); //reset the başş
				getPaddle().setCurrentX(PADDLE_START_X);
				getPaddle().updateBar();//reset the paddle
				setState(State.START); //wait for space to be pressed
				getNumLivesLabel().setText(NUM_LIVES_LABEL_TEXT + numLives);

			}
			else {
				//when there isn't any collision, just move the ball
				getBall().setCurrentX((getBall().getCurrentX() + getBall().getCurrentVelocityX()));
				getBall().setCurrentY((getBall().getCurrentY() + getBall().getCurrentVelocityY()));
				getBall().updateBall();
			}

			getPanel().repaint(); //redraw to frame
			

			try {
				Thread.sleep(GAME_CYCLE_SLEEP_MS); //how frequent we update the frame
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		//while loop terminated, it means game over.
		String name = JOptionPane.showInputDialog(getPanel(), GAME_OVER_MESSAGE, GAME_OVER_TITLE,
				JOptionPane.INFORMATION_MESSAGE);
		//force user to enter name, no empty name allowed
		while (name == null || name.isEmpty()) {
			JOptionPane.showMessageDialog(getPanel(), USERNAME_NOT_PROVIDED_MESSAGE, USERNAME_NOT_PROVIDED_TITLE, JOptionPane.ERROR_MESSAGE);
			name = JOptionPane.showInputDialog(getPanel(), GAME_OVER_MESSAGE, GAME_OVER_TITLE,
					JOptionPane.INFORMATION_MESSAGE);
		}
		scores.addScore(name, currentScore);
		((CardLayout) (cards.getLayout())).show(cards, CARDLAYOUT_SCORES);

		resetGame(); // reset the game upon exit
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
	
    public static void main(String[] args){
        Game g = new Game();
     }

}
