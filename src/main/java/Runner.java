package main.java;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.CardLayout;
import java.awt.Dimension;


public class Runner{
	
	public static final String windowTitle = "Arkanoid";
	public static final int boardWidth = 600;
	public static final int boardHeight = 600;
	public static final String homePanel = "home";
	public static final String gamePanel = "game";
	
	
    JFrame frame;
    Home home;
    Game game;
    JPanel cards;
    Runner(){
        frame = new JFrame(windowTitle);
        frame.getContentPane().setPreferredSize(new Dimension(boardWidth, boardHeight));
        frame.pack();
        


        
        cards = new JPanel(new CardLayout());
        home = new Home();
        game = new Game();
        
        Runnable runnables[] = {startNewGame(), startNewGame(), startNewGame(), startNewGame(), startNewGame(), startNewGame()};
        
        for(int i = 0; i < Home.numButtons; i++) {
        	home.getButtons()[i].registerEvent(runnables[i]);
        }
        
    
        
        
        cards.add(home.getPanel(), homePanel);
        cards.add(game.getPanel(), gamePanel);
        frame.add(cards);

     
        frame.setVisible(true);
        
         
    }
    
    public Runnable showHome() {
    	return new Runnable() {

			@Override
			public void run() {
				((CardLayout)(cards.getLayout())).show(cards, homePanel);
				
				return;

				
			}
    		
    	};
    }
    
    
    private Runnable startNewGame() {
		return new Runnable() {

			@Override
			public void run() {
				((CardLayout)(cards.getLayout())).show(cards, gamePanel);
				//Thread t = new Thread(game);
				//t.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("haha");
				//showHome();
			}
			
		};
    }
    
    


    
    public static void main(String[] args){
       Game g = new Game();
       
        
        //game.start();
    }


    
}
