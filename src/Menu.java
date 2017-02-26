
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
/**
 * Menu is a class extends JFrame, and contain all buttons, panels, game modes
 * @author Yang Ge, Renwen Zhang, Bowei Liu, Lena Nesterenko
 *
 */

public class Menu extends JFrame{

	private static final long serialVersionUID = 1L;
	/////buttons
	private JButton homeButton;//return to home panel
	private JButton playButton;//game mode selecting mode
	private JButton exitButton;//close game
	private JButton easyButton;
	private JButton mediumButton;
	private JButton hardButton;
	private JButton storySettingButton;
	private JButton gameOptionButton;     //waiting to be implemented
	private JButton themeButton; //waiting to be done
	private JButton musicButton; //waiting to be done
	private boolean music = false;
	Clip musicClip;
	private JButton hintButton;
	//for theme
	private JButton jokerButton;
	private JButton catWomanButton;
	private JButton nightButton;
	private JButton batCarButton;	
	//adventure
	private JButton adventureButton;
	//double-player
	private JButton doubleButton;
	//static int xLoc = 0;
	//static int yLoc = 0;
	
	public Node hint;
	///panels
	private JPanel bossPanel;
	private JPanel homePanel;
	private JPanel modePanel; //game difficulty
	private JPanel storyPanel;
	private JPanel gameOptionPanel;
	private JPanel themePanel;
	////game mode grids
	private Grid EasyGame;
	private Grid MediumGame;
	private Grid HardGame;
	//new game mode
	private Grid adventureGame;
	private Grid adventureGameNext;
	private Grid doubleGame;
	//layout
	private CardLayout cl = new CardLayout();
	
	
	//image
	private ImageIcon theme = new ImageIcon(this.getClass().getResource("image/Batman.jpg"));
	
	//timer
	Timer timer;
	TimerTask tt;//Timer task for the moving of enemies 
	TimerTask tt1;//count down timer
	static int count = 10;
	static int fakeTimer = 1000;

	private HIframe frame1 = new HIframe();
	
	//player
	Batman player = new Batman();
	int hurtFlag = 0;
	int crazyFlag = 0;
	
	Batman player2 = new Batman();

	
	/**
	 * Constructor
	 * @throws InterruptedException
	 */
	public Menu() throws InterruptedException{
		initUI();
	}
	/**
	 * Initialize UI
	 */
	private void initUI(){
		bossPanel = new JPanel();
		createModePanel();
		createStoryPanel();
		createHomePanel();
		createGameOptionPanel();
		createThemePanel();
		
		bossPanel.setLayout(cl);
		bossPanel.add("home",homePanel);
		bossPanel.add("story",storyPanel);
		bossPanel.add("mode",modePanel);
		bossPanel.add("option",gameOptionPanel);
		bossPanel.add("theme",themePanel);
		cl.show(bossPanel, "home");
		add(bossPanel);
		
		
		setSize(1000,1000);
		setTitle("Maze Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
	
	
	
	//-------------
	//create panel 
	
	/**
	 * Create Home Panel
	 */
	private void createHomePanel(){
		ImageIcon img = new ImageIcon(this.getClass().getResource("image/FirstMenu.jpg"));
		Image i = img.getImage();		
		homePanel = new ImagePanel(i);
		homePanel.setLayout(new BoxLayout(homePanel,BoxLayout.Y_AXIS));
		homePanel.setBorder(new EmptyBorder(new Insets(300,300,400,200)));
		createPlayButton();
		createStorySettingButton();
		createExitButton();
		createGameOptionButton();	
		exitButton.setPreferredSize(new Dimension(150, 40));
		
		homePanel.add(playButton);
		homePanel.add(storySettingButton);
		homePanel.add(exitButton);
		homePanel.add(gameOptionButton);
	}
	/**
	 * create mode panel for mode selecting
	 */
	private void createModePanel(){
		try{
			ImageIcon img = new ImageIcon(this.getClass().getResource("image/SecondMenu.jpg"));
			Image i = img.getImage();
			modePanel = new ImagePanel(i);
			modePanel.setBorder(new EmptyBorder(new Insets(200,500,400,300)));			
			createEasyButton();
			createMediumButton();
			createHardButton();
			createHomeButton(0);
			createAdventureButton();
			createDoubleButton();
			modePanel.add(easyButton);
			modePanel.add(mediumButton);
			modePanel.add(hardButton);
			modePanel.add(doubleButton);
			modePanel.add(adventureButton);
			modePanel.add(homeButton);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * create story panel which contains the story of our game
	 */
	private void createStoryPanel(){
		ImageIcon img = new ImageIcon(this.getClass().getResource("image/SecondMenu.jpg"));
	    Image i = img.getImage();		
		storyPanel = new ImagePanel(i);
		storyPanel.setBorder(new EmptyBorder(new Insets(300,300,400,300)));
		JLabel label = new JLabel("<html><p>Batman is trapped in a maze set up by joker and he has to get out... </p>watch it</p>                        https://www.youtube.com/watch?v=qiWITgoR9Ao ", SwingConstants.CENTER);
    	label.setForeground(Color.red);
    	storyPanel.add(label);
    	createHomeButton(0);
    	storyPanel.add(homeButton);
	}
	
	/**
	 * create game option panel which can change theme image, and add music
	 */
	public void createGameOptionPanel(){            //game option _____________________________________________________
		ImageIcon img = new ImageIcon(this.getClass().getResource("image/SecondMenu.jpg"));
		Image i = img.getImage();		
	    gameOptionPanel = new ImagePanel(i);
	    gameOptionPanel.setLayout(new GridBagLayout());	    
		createThemeButton();
		createMusicButton();
		createHomeButton(0);
		gameOptionPanel.add(themeButton);
		gameOptionPanel.add(musicButton);
		gameOptionPanel.add(homeButton);		
	}
	/**
	 * create theme panel for theme image selecting
	 */
	private void createThemePanel(){
		ImageIcon img = new ImageIcon(this.getClass().getResource("image/SecondMenu.jpg"));
	    Image i = img.getImage();		
		themePanel = new ImagePanel(i);
		themePanel.setLayout(new GridBagLayout());
		createJokerButton();
		createNightButton();
		createCatWomanButton();
		createBatCarButton();
		createHomeButton(0);		
		themePanel.add(jokerButton);
		themePanel.add(nightButton);
		themePanel.add(catWomanButton);
		themePanel.add(batCarButton);
		themePanel.add(homeButton);		
	}
	
	//Game panel

	
//***************************************

	
	
	//finish game panel
	/**
	 * create game panel for easy, medium, hard game mode
	 * @param difficulty
	 */
	private void createGamePanel(final int difficulty){
		
		final GuiView view = new GuiView();	
		final Maze m = new Maze(difficulty);
		int[][]matrix = m.getMatrix();
			//Node hint = new Node(difficulty-2,difficulty-1); //
			int mode = 0;
			if(difficulty == 21){
				mode = 0;
			}else if(difficulty == 31){
				mode = 1;
			}else if(difficulty == 51){
				mode = 2;
			}
			
			final Grid gamePanel = new Grid(theme,0,mode);
			if(difficulty == 21){
				EasyGame = gamePanel;
			}else if(difficulty == 31){
				MediumGame = gamePanel;
			}else if(difficulty == 51){
				HardGame = gamePanel;
			}
			
			createHintButton(m,gamePanel);
			 //EasyGame.requestFocus();
			gamePanel.add(hintButton);
			
			EventQueue.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                try {
	                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
	                } 
	              //final Maze m = new Maze(difficulty);
	              
	               view.gamePanelView(gamePanel, m);
	               gamePanel.printHint(difficulty-2,difficulty-1);
	                //xLoc += 1;
	                gamePanel.addKeyListener(new KeyAdapter(){
	                    public void keyPressed(KeyEvent e){    
	                    	
	                		
	                		//System.out.println(hint.getX()+ "  "+hint.getY());
	                    	
	                    	System.out.println("Working on top!");
	                        if (e.getKeyCode() == Event.ENTER) {
	                            System.out.println("You have pressed enter.........");
	                        }
	                        switch( e.getKeyCode() ) { 
	                        case KeyEvent.VK_UP:
	                            System.out.println("UP1111");
	                            if(!ifStart(player.getPosition().getX(),player.getPosition().getY(),difficulty)){
	                                if(matrix[player.getPosition().getX()-1][player.getPosition().getY()] == 1){
	   
	                                	Node move = player.go_up(m);
	                                	gamePanel.current(move.getY(), move.getX());
	                                	createHintButton(m,gamePanel);
	                                	gamePanel.add(hintButton);
	                                	
	                                	
	                                }	
	                            }
	                            
	                            break;
	                        case KeyEvent.VK_DOWN:
	                            System.out.println("DOWN");
	                            if(!ifFinish(player.getPosition().getX(),player.getPosition().getY(),difficulty)){
	                            	if(matrix[player.getPosition().getX()+1][player.getPosition().getY()] == 1){
	                                	System.out.println("current pos is " + player.getPosition().getX() + player.getPosition().getY());
	                                	Node move = player.go_down(m);
	                                	System.out.println("move is " + move.getX() + " "+move.getY());
	                                	//System.out.println("------------------------------------------");
	                                	gamePanel.current(move.getY(), move.getX());
	                                	System.out.println("it's fine for gamePanel.current");
	                                	createHintButton(m,gamePanel);
	                                	gamePanel.add(hintButton);
	                                	 
	                                	
	                                   
	                                    
	                                	 
	                                }
	                            } 
	                           
	                            break;
	                        case KeyEvent.VK_LEFT:
	                            System.out.println("LEFT");
	                            if(matrix[player.getPosition().getX()][player.getPosition().getY()-1] == 1){
	                            	Node move = player.go_left(m);
                                	gamePanel.current(move.getY(), move.getX());
	                            	//createHintButton(m,gamePanel);
	                            	//gamePanel.add(hintButton);
	                            	
	                            	
	                            	
	                            }
	                            
	                            break;
	                        case KeyEvent.VK_RIGHT :
	                            System.out.println("RIGHT");
	                            if(matrix[player.getPosition().getX()][player.getPosition().getY()+1] == 1){
	                            	Node move = player.go_right(m);
                                	gamePanel.current(move.getY(), move.getX());
	                            	createHintButton(m,gamePanel);
	                            	gamePanel.add(hintButton);
	                            	
	                            	
	                            	
	                            }
	                            
	                            break;
	                     }
	                     if(ifFinish(player.getPosition().getX(),player.getPosition().getY(), difficulty)){
	                    	 JFrame frame = new JFrame();
	                    	 JOptionPane.showMessageDialog(frame, "You won");
	                    	 frame.dispose();
	                    	 cl.show(bossPanel, "home");
	                     }
	                     
	                     if(player.getPosition().getY() == gamePanel.hint.getX() && player.getPosition().getX() ==gamePanel.hint.getY()){
	                    	 gamePanel.printHint(difficulty-2,difficulty-1);
	                     }
	                     
	                        
	                }        
	        }); 
	                gamePanel.requestFocus();
	                
	            }
	        });
			createHomeButton(0);
			gamePanel.add(homeButton);
			
			//////////
			
			
			
			
			////////////
		}
	
	
	
	
	//raptors-------------------------

		

	       
			
			
			////////////
	
	

	
	//create Button
	/**
	 * create home button
	 * @param mode
	 */
	private void createHomeButton(final int mode){
		homeButton = new JButton("home");
		homeButton.setPreferredSize(new Dimension(150, 40));
		//homeButton.setBackground(Color.BLACK);
		//homeButton.setForeground(Color.BLACK);
		homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if(mode == 1){
                	tt.cancel();
                	tt1.cancel();
                	count = 12;
                }
            	//tt.cancel();
            	cl.show(bossPanel, "home");
            	
            	//above
            }
        });
		
	}

	/**
	 * create play button
	 */
	public void createPlayButton(){
		playButton = new JButton("play");
		playButton.setPreferredSize(new Dimension(150, 40));
		//playButton.setBackground(Color.BLACK);
		//playButton.setForeground(Color.BLACK);
		playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                
            	cl.show(bossPanel, "mode");
            	
            	//above
            }
        });
		
	}
	

	/**
	 * create story setting button
	 */
	
	private void createStorySettingButton(){
		storySettingButton = new JButton("story setting");
		storySettingButton.setPreferredSize(new Dimension(150, 40));
		storySettingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	
            	cl.show(bossPanel,"story");
            }
        });

	}
	/**
	 * create exit button
	 */
	public void createExitButton(){
		exitButton = new JButton("exit");
		exitButton.setPreferredSize(new Dimension(150, 40));  //change
		exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
	}
	/**
	 * create easy game mode button
	 */
	private void createEasyButton(){
		easyButton = new JButton("Easy");
		easyButton.setPreferredSize(new Dimension(150, 40));
		easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                player.reset();
            	createGamePanel(21);
                //easyButton.requestFocus();
                bossPanel.add("easyPanel",EasyGame);
                cl.show(bossPanel, "easyPanel");
            }
        });
	}
	/**
	 * create medium game mode button
	 */
	private void createMediumButton(){
		mediumButton = new JButton("Medium");
		mediumButton.setPreferredSize(new Dimension(150, 40));
		mediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //xLoc = 0;
                //yLoc = 0;
            	player.reset();
                createGamePanel(31);
                //easyButton.requestFocus();
                bossPanel.add("mediumPanel",MediumGame);
                cl.show(bossPanel, "mediumPanel");
            }
        });
	}
	/**
	 * create hard game mode button
	 */
	private void createHardButton(){
		hardButton = new JButton("Hard");
		hardButton.setPreferredSize(new Dimension(150, 40));
		hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //xLoc = 0;
                //yLoc = 0;
            	player.reset();
                createGamePanel(51);
                //easyButton.requestFocus();
                bossPanel.add("hardPanel",HardGame);
                cl.show(bossPanel, "hardPanel");
            }
        });
	}
	
	
	
	/**
	 * create game option button
	 */
	private void createGameOptionButton(){
		gameOptionButton = new JButton("option");
		gameOptionButton.setPreferredSize(new Dimension(150, 40));
		
		gameOptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	
            	cl.show(bossPanel,"option");
            }
        });
	}
	
	
	/**
	 * create music button for active music
	 */
	private void createMusicButton(){
		musicButton = new JButton("music");
		musicButton.setPreferredSize(new Dimension(150, 40));
		//musicButton.setBackground(Color.BLACK);
		//musicButton.setForeground(Color.WHITE);
		musicButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (music) {
					music = false;
					musicClip.stop();
				} else {
					music = true;
					try {
						musicClip = AudioSystem.getClip();
						AudioInputStream ais = AudioSystem.getAudioInputStream(new File("src/music/Arcadia.wav")); // PATH FOR THE .WAV FILE
						musicClip.open(ais);
						musicClip.loop(0); // PLAY ONCE
					} catch(Exception exc) {
						System.out.println(exc);
					}
				}			    				    
			}
		});
	}	
	/**
	 * create theme button for image selecting
	 */
	private void createThemeButton(){
		themeButton = new JButton("theme");
		themeButton.setPreferredSize(new Dimension(150, 40));
		themeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	
            	cl.show(bossPanel,"theme");
            }
        });
	}
	
	/**
	 * the joker image button
	 */
	//different themes
	private void createJokerButton(){
		jokerButton = new JButton("Joker");
		jokerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	
            	theme = new ImageIcon(this.getClass().getResource("image/Joker.jpg"));
            	cl.show(bossPanel,"home");
            }
        });
	}
	/**
	 * the cat woman image button
	 */
	private void createCatWomanButton(){
		catWomanButton = new JButton("CatWoman");
		catWomanButton.setPreferredSize(new Dimension(150, 40));
		catWomanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	
            	theme = new ImageIcon(this.getClass().getResource("image/Catwoman.jpg"));
            	cl.show(bossPanel,"home");
            }
        });
	}
	/**
	 * the night image button
	 */
	private void createNightButton(){
		nightButton = new JButton("Nightwing");
		nightButton.setPreferredSize(new Dimension(150, 40));
		nightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	
            	theme = new ImageIcon(this.getClass().getResource("image/Nightwing.jpg"));
            	cl.show(bossPanel,"home");
            }
        });
	}
	/**
	 * the bat car image button
	 */
	private void createBatCarButton(){
		batCarButton = new JButton("Batman");
		batCarButton.setPreferredSize(new Dimension(150, 40));
		batCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	
            	theme = new ImageIcon(this.getClass().getResource("image/Batman.jpg"));
            	cl.show(bossPanel,"home");
            }
        });
	}
	/**
	 * create hint button
	 * @param maze
	 * @param gamePanel
	 */
	private void createHintButton(final Maze maze,final Grid gamePanel){
		
		
		hintButton = new JButton("hints");
		hintButton.setPreferredSize(new Dimension(150, 40));
		hintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	
        		//System.out.println(xLoc + "  " +yLoc);
            	Node h = maze.giveHints(player);
            	
        		//gamePanel.hint = new Point(h.getX(),h.getY());
        		//System.out.println(hint.getX()+ "  "+hint.getY());
            	gamePanel.printHint(h.getX(), h.getY());
            	
            	if(maze.getDifficulty() == 21){
            		EasyGame.requestFocus();
            	} else if(maze.getDifficulty() == 31){
            		MediumGame.requestFocus();
            	} else if(maze.getDifficulty() == 51){
            		HardGame.requestFocus();
            	}
            	
            }
        });
		
	}
	/**
	 * check if player is at starting position or not
	 * @param x
	 * @param y
	 * @param difficulty
	 * @return true if player is at starting position, false otherwise
	 */
	private boolean ifStart(int x, int y, int difficulty){
		if(x==0 && y == 1){
			return true;
		}
		return false;
	}
	/**
	 * check if player is at exit position or not
	 * @param x
	 * @param y
	 * @param difficulty
	 * @return true if player is at exit position, false otherwise
	 */
	private boolean ifFinish(int x, int y, int difficulty){
		if(x == difficulty - 1 && y == difficulty - 2){
			return true;
		}
		return false;
	}
	
	
	//adventure
	/**
	 * create adventure game mode
	 * @param difficulty
	 */
	private void createAdventureGame(final int difficulty){
		
		hurtFlag = 0;
		crazyFlag = 0;
		final Maze m = new Maze(difficulty);
		adventureGame = new Grid(theme,0,4);
		final GuiView view = new GuiView();
		
	
		
		////////////stamina
		final JLabel stamina = new JLabel();
		//stamina.setText("Remaining stamina: " + player.getStamina());
		System.out.println(player.getStamina());
		stamina.setText("<html><font color='red'>Remaining stamina: " + player.getStamina() + "</font></html>");
		adventureGame.add(stamina);
		
		////////////
		final List<Enemy> es = new ArrayList<Enemy>();
		final Enemy one = new Enemy(difficulty);
		final Enemy two = new Enemy(difficulty);
		final Enemy three = new Enemy(difficulty);
		final Enemy four = new Enemy(difficulty);
		es.add(one);
		es.add(two);
		es.add(three);
		es.add(four);
		adventureGame.printEnemy(es);
		
		timer = new Timer();  
	   	tt = new TimerTask() {  
   	 
	   		public void run() {  
	   			one.move(m);
	   			two.move(m);
	   			three.move(m);
	   			four.move(m);
	         
	          
	           adventureGame.printEnemy(es);
	          
		       for(Enemy en : es){
	            	
		    	   if(player.getPosition().getX() == en.getPosition().getY() && player.getPosition().getY() == en.getPosition().getX()){
		    		   System.out.println("hurt!!!!!!");
		    		   player.getHurt();
		    		   stamina.setForeground(Color.RED);
		    		   //stamina.setText("Remaining stamina: " + player.getStamina());
		    			stamina.setText("<html><font color='red'>Remaining stamina: " + player.getStamina() + "</font></html>");
		    	   }
		       }	
			   if(hurtFlag == 0){        
			       if(!player.ifStillAlive()){
			    	   tt.cancel();
			    	   tt1.cancel();	
			    	   JFrame frame = new JFrame();
			    	   JOptionPane.showMessageDialog(frame, "You lose");
			    	   frame.dispose();
			    	   
			    	   System.out.println("1");
			    	   cl.show(bossPanel, "home");
			    	 
			       }
			   }
	       }  
	   };
	    
	    timer.scheduleAtFixedRate(tt, 1000*1, 1000*1);

		
		
		
		
		EventQueue.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	            try {
	                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
	            } 
	            final ArrayList<Node> hostage = m.getHostage();
	            if(hostage.size() == 0){
	            	System.out.println("shit dfjdkfjdkfjdkfjdkfjdkfjdkfjkjfkj");
	            }
	            
	            adventureGame.printHostage(hostage);
	            final ArrayList<Node>crazyNodes = m.getCrazyNodes();
	            System.out.println("crazyNodes size is "+crazyNodes.size());
	           
	            
	            view.gamePanelView(adventureGame, m);
	           
	            adventureGame.addKeyListener(new KeyAdapter(){
	                public void keyPressed(KeyEvent e){    
	                	//System.out.println("Working on top!");
	                	
	                    if (e.getKeyCode() == Event.ENTER) {
	                        System.out.println("You have pressed enter.........");
	                    }
	                    switch( e.getKeyCode() ) { 
	                    case KeyEvent.VK_UP:
	                    	if(crazyFlag == 0){
	                    		view.viewMoveUp(adventureGame, player, m);
	                    	}else if(crazyFlag == 1){
	                    		view.viewMoveDown(adventureGame, player, m);
	                    	}
	                        break;
	                    case KeyEvent.VK_DOWN:
	                        if(crazyFlag == 0){
	                        	view.viewMoveDown(adventureGame, player, m);
	                        	System.out.println("ohfjdkfjdkfjdkfj");
	                        }else if(crazyFlag == 1){
	                        	view.viewMoveUp(adventureGame, player, m);
	                        }
	                        break;
	                    case KeyEvent.VK_LEFT:
	                        //System.out.println("LEFT");
	                        if(crazyFlag == 0){
	                        	view.viewMoveLeft(adventureGame, player, m);
	                        }else if(crazyFlag == 1){
	                        	view.viewMoveRight(adventureGame, player, m);
	                        }
	                        break;
	                    case KeyEvent.VK_RIGHT :
	                        if(crazyFlag == 0){
	                        	view.viewMoveRight(adventureGame, player, m);
	                        }else if(crazyFlag == 1){
	                        	view.viewMoveLeft(adventureGame, player, m);
	                        }
	                        break;
	                    }
	                    
	                    
	                    
	                    for(Enemy en : es){
		                    	
	                    	if(player.getPosition().getX() == en.getPosition().getY() && player.getPosition().getY() == en.getPosition().getX()){
	                    		System.out.println("hurt!!!!!!");
	                    		player.getHurt();
	                    		stamina.setForeground(Color.RED);
	                    		stamina.setText("Remaining stamina: " + player.getStamina());
	                    	}
	                    }	
		                 
	                    if(!player.ifStillAlive()){
		                    hurtFlag = 1;
		                    tt.cancel();
	                    	tt1.cancel();
	                    	JFrame frame = new JFrame();
	                    	JOptionPane.showMessageDialog(frame, "You lose");
	                    	frame.dispose();
	                    	
	                    	System.out.println("2");
	                    	cl.show(bossPanel, "home");
			                   	
	                    }
		                    
	                    if(player.ifStillAlive()){
	                    	for(Node c : crazyNodes){
	                    		if(player.getPosition().getX() == c.getY() && player.getPosition().getY() == c.getX()){
	                    			System.out.println("???????????????????????????????");
	                    			if(crazyFlag == 0){
	                    				JFrame frame = new JFrame();
		   	                    	 	JOptionPane.showMessageDialog(frame, "Batman is getting insane");
		   	                    	 	frame.dispose();
	                    			}
	                    			fakeTimer = count;
	                    			crazyFlag = 1;
	                    			
	                    		}
	                    	}
	                    }
	                    
	                    if(count <= fakeTimer - 10){
	                    	crazyFlag = 0;
	                    }
		                    
	                    if(ifFinish(player.getPosition().getX(),player.getPosition().getY(), difficulty) && hostage.size() == 0 &&
	                    		player.ifStillAlive()){
	                    	tt.cancel();
	                    	tt1.cancel();	

	                    	JFrame frame = new JFrame();
			                   	 
		                    JOptionPane.showMessageDialog(frame, "You won");
		                    frame.dispose();
	                    	
	                    	
	                    	 player.reset();
	                         count = 1000;
	                         createAdventureGameNext(31);
	                         //easyButton.requestFocus();
	                         bossPanel.add("adventureNext",adventureGameNext);
	                         cl.show(bossPanel, "adventureNext");
	                    	
	                    	
		                   	
			               
	                    }
		                    
	                    //rescue the hostage
	                   try{
	                	   for(Node h: hostage){
		                    	if(player.getPosition().getX() == h.getY() && player.getPosition().getY() == h.getX()){
		                    		hostage.remove(h);
		                    		adventureGame.printHostage(hostage);
		                    		new Thread(frame1).start();
		                    		frame1.showWindow();
		                    		//System.out.println("oh, here?????");
		                    		break;
		                    		
		                    	}
		                    }
	                	   
	                	   
	                   }catch(Exception exception){
	                	   System.out.println("the exception is 781 is "+exception.getMessage());
	                   }
	                    
	                    
	            }        
	    }); 
	           
	            adventureGame.requestFocus();
	            
	        }
	    });
		
		
			
		
		
		createHomeButton(1);
		adventureGame.add(homeButton);
		
		
		///////////////
		
		
		////////////////
		final JLabel time = new JLabel();
		time.setText("<html><font color = 'red'>" + String.valueOf(count) + "</font></html>");
		adventureGame.add(time);
		
		Timer timer1 = new Timer();  
	   	tt1 = new TimerTask() {  
   	 
	   		public void run() {  
	   			count--;
	   	//		time.setText(String.valueOf(count));
	   			time.setText("<html><font color = 'red'>" + String.valueOf(count) + "</font></html>");
	   			if(count == 0){
	   				tt1.cancel();
	   				JFrame frame = new JFrame();
	               	JOptionPane.showMessageDialog(frame, "You lose");
	               	frame.dispose();
	               	tt.cancel();
	               	cl.show(bossPanel, "home");
	   			}
	       }  
	   };
	    
	    timer1.scheduleAtFixedRate(tt1, 1000*1, 1000*1);
		
		////////////
		
		
		
		
		
	}	
	
	/**
	 * create adventure game next game mode which is after adventure game 
	 * @param difficulty
	 */
	
	public void createAdventureGameNext(final int difficulty){
		crazyFlag = 0;
		fakeTimer = 1000;
		hurtFlag = 0;
		final Maze m = new Maze(difficulty);
		adventureGameNext = new Grid(theme,0,4);
		final GuiView view = new GuiView();
		player.reset();
	
		////////////stamina
		final JLabel stamina = new JLabel("Remaining stamina: " + player.getStamina(), JLabel.CENTER);
		//stamina.setText("Remaining stamina: " + player.getStamina().toString(), JLabel.CENTER);
		stamina.setForeground(Color.RED);
		adventureGameNext.add(stamina);
		
		////////////
		final List<Enemy> es = new ArrayList<Enemy>();
		final Enemy one = new Enemy(difficulty);
		final Enemy two = new Enemy(difficulty);
		final Enemy three = new Enemy(difficulty);
		final Enemy four = new Enemy(difficulty);
		es.add(one);
		es.add(two);
		es.add(three);
		es.add(four);
		adventureGameNext.printEnemy(es);
		
		timer = new Timer();  
	   	tt = new TimerTask() {  
   	 
	   		public void run() {  
	   			one.move(m);
	   			two.move(m);
	   			three.move(m);
	   			four.move(m);
	   			
	           adventureGameNext.printEnemy(es);
	          
		       for(Enemy en : es){
	            	
		    	   if(player.getPosition().getX() == en.getPosition().getY() && player.getPosition().getY() == en.getPosition().getX()){
		    		   System.out.println("hurt!!!!!!");
		    		   player.getHurt();
		    		   stamina.setForeground(Color.WHITE);
		    		   stamina.setText("<html><font color='red'>Remaining stamina: </font></html>" + player.getStamina());
		    		   stamina.setForeground(Color.WHITE);
		    	   }
		       }	
			   if(hurtFlag == 0){        
			       if(!player.ifStillAlive()){
			    	   tt.cancel();
			    	   tt1.cancel();	
			    	   JFrame frame = new JFrame();
			    	   JOptionPane.showMessageDialog(frame, "You lose");
			    	   frame.dispose();
			    	   
			    	   System.out.println("1");
			    	   cl.show(bossPanel, "home");
			    	 
			       }
			   }
	       }  
	   };
	    
	    timer.scheduleAtFixedRate(tt, 1000*1, 1000*1);

		
		view.adventureTwo(adventureGameNext,m,player.getPosition());
		
		
		EventQueue.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	            try {
	                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
	            } 
	            final ArrayList<Node> hostage = m.getHostage();
	            if(hostage.size() == 0){
	            	System.out.println("shit dfjdkfjdkfjdkfjdkfjdkfjdkfjkjfkj");
	            }
	            
	            adventureGameNext.printHostage(hostage);
	            //view.gamePanelView(adventureGame, m);
	           
	            ArrayList<Node>crazyNodes = m.getCrazyNodes();
	            
	            
	            
	            adventureGameNext.addKeyListener(new KeyAdapter(){
	                public void keyPressed(KeyEvent e){    
	                	//System.out.println("Working on top!");
	                	
	                    if (e.getKeyCode() == Event.ENTER) {
	                        System.out.println("You have pressed enter.........");
	                    }
	                    switch( e.getKeyCode() ) { 
	                    case KeyEvent.VK_UP:
	                        //System.out.println("UP1111");
	                        if(crazyFlag == 0){
	                        	 view.viewMoveUp(adventureGameNext, player, m);
	                        }else if(crazyFlag == 1){
	                        	view.viewMoveDown(adventureGameNext, player, m);
	                        }
	                        
	                        break;
	                    case KeyEvent.VK_DOWN:
	                        //System.out.println("DOWN");
	                        if(crazyFlag == 0){
	                        	view.viewMoveDown(adventureGameNext, player, m);
	                        	System.out.println("fdfddfdfdf");
	                        }else if(crazyFlag == 1){
	                        	view.viewMoveUp(adventureGameNext, player, m);
	                        }
	                        break;
	                    case KeyEvent.VK_LEFT:
	                        if(crazyFlag == 0){
	                        	view.viewMoveLeft(adventureGameNext, player, m);
	                        }else if(crazyFlag == 1){
	                        	view.viewMoveRight(adventureGameNext, player, m);
	                        }
	                        break;
	                    case KeyEvent.VK_RIGHT :
	                        if(crazyFlag == 0){
	                        	view.viewMoveRight(adventureGameNext, player, m);
	                        }else if(crazyFlag == 1){
	                        	view.viewMoveLeft(adventureGameNext, player, m);
	                        }
	                        break;
	                    }
	                    
	                    view.adventureTwo(adventureGameNext,m,player.getPosition());
	                    
	                    for(Enemy en : es){
		                    	
	                    	if(player.getPosition().getX() == en.getPosition().getY() && player.getPosition().getY() == en.getPosition().getX()){
	                    		System.out.println("hurt!!!!!!");
	                    		player.getHurt();
	                    		stamina.setText("Remaining stamina: " + player.getStamina());
	                    	}
	                    }	
		                    
	                    if(!player.ifStillAlive()){
		                    hurtFlag = 1;
		                    tt.cancel();
	                    	tt1.cancel();
	                    	JFrame frame = new JFrame();
	                    	JOptionPane.showMessageDialog(frame, "You lose");
	                    	frame.dispose();
	                    	
	                    	System.out.println("2");
	                    	cl.show(bossPanel, "home");
			                   	
	                    }
		                    
	                    
	                    
		                    
	                    if(ifFinish(player.getPosition().getX(),player.getPosition().getY(), difficulty) && hostage.size() == 0 &&
	                    		player.ifStillAlive()){
	                    	tt.cancel();
	                    	tt1.cancel();	
	                    	JFrame frame = new JFrame();
			                   	 
		                    JOptionPane.showMessageDialog(frame, "You won");
		                   	frame.dispose();
			                   	 
		                   	cl.show(bossPanel, "home");
	                    }
		               if(player.ifStillAlive()){
		            	   for(Node c : crazyNodes){
		            		   if(player.getPosition().getX() == c.getY() && player.getPosition().getY() == c.getX()){
		            			   if(crazyFlag == 0){
		            				   JFrame frame = new JFrame();
		   	                    	   JOptionPane.showMessageDialog(frame, "Batman is getting insane");
		   	                    	   frame.dispose();
		            			   }
		            			   fakeTimer = count;
		            			   crazyFlag = 1;
		            		   }
		            	   }
		               }
		               
		               if(count <= fakeTimer - 10){
		            	   crazyFlag = 0;
		               }
	                    //rescue the hostage
	                   try{
	                	   for(Node h: hostage){
		                    	if(player.getPosition().getX() == h.getY() && player.getPosition().getY() == h.getX()){
		                    		hostage.remove(h);
		                    		adventureGameNext.printHostage(hostage);
		                    		new Thread(frame1).start();
		                    		frame1.showWindow();
		                    		break;
		                    		
		                    	}
		                    }
	                	   
	                	   
	                   }catch(Exception exception){
	                	   System.out.println("the exception is 781 is "+exception.getMessage());
	                   }
	                    
	                    
	            }        
	    }); 
	           
	            adventureGameNext.requestFocus();
	            
	        }
	    });
		
		
			
		
		
		createHomeButton(1);
		adventureGameNext.add(homeButton);
		
		
		///////////////
		
		
		////////////////
		final JLabel time = new JLabel();
		time.setText("<html><font color = 'red'>" + String.valueOf(count) + "</font></html>");
		adventureGameNext.add(time);
		
		Timer timer1 = new Timer();  
	   	tt1 = new TimerTask() {  
   	 
	   		public void run() {  
	   			count--;
	   			time.setText("<html><font color = 'red'>" + String.valueOf(count) + "</font></html>");
	   			if(count == 0){
	   				tt1.cancel();
	   				JFrame frame = new JFrame();
	               	JOptionPane.showMessageDialog(frame, "You lose");
	               	frame.dispose();
	               	tt.cancel();
	               	cl.show(bossPanel, "home");
	   			}
	       }  
	   };
	    
	    timer1.scheduleAtFixedRate(tt1, 1000*1, 1000*1);
		
		
	}

	
	//-------------
	/**
	 * create adventure game button
	 */
	public void createAdventureButton(){
		adventureButton = new JButton("adventure");
		adventureButton.setPreferredSize(new Dimension(150, 40));
		adventureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	player.reset();
            	
                count = 200;
                createAdventureGame(31);
                //createAdventureGame(7);
                bossPanel.add("adventure",adventureGame);
                cl.show(bossPanel, "adventure");
            }
        });
	}
	
	/**
	 * create double mode button
	 */
	
	public void createDoubleButton(){
		doubleButton = new JButton("double");
		doubleButton.setPreferredSize(new Dimension(150, 40));
		doubleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //xLoc = 0;
                //yLoc = 0;
            	player.reset();
            	player2.reset();
                createDoublePanel(25);
                //easyButton.requestFocus();
                bossPanel.add("doublePanel",doubleGame);
                cl.show(bossPanel, "doublePanel");
            }
        });
	}
	
	/**
	 * create double game mode panel
	 * @param difficulty
	 */
	private void createDoublePanel(final int difficulty){
		
	final GuiView view = new GuiView();	
	final Maze m = new Maze(difficulty);
	int[][]matrix = m.getMatrix();
		//Node hint = new Node(difficulty-2,difficulty-1); //
	final Grid gamePanel = new Grid(theme,1,3);

		doubleGame = gamePanel;
		
		
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                } 
              //final Maze m = new Maze(difficulty);
              
               //view.gamePanelView(gamePanel, m);
               view.doublePanelView(gamePanel, m);
               gamePanel.printHint(difficulty-2,difficulty-1);
                //xLoc += 1;
               gamePanel.addKeyListener(new KeyAdapter(){
                    public void keyPressed(KeyEvent e){    
                    	//Node current = new Node(player.getPosition().getX(),player.getPosition().getY());
                    	//System.out.println(xLoc + "  " +yLoc);
                		
                		//System.out.println(hint.getX()+ "  "+hint.getY());
                    	
                    	System.out.println("Working on top!");
                        if (e.getKeyCode() == Event.ENTER) {
                            System.out.println("You have pressed enter.........");
                        }
                        switch( e.getKeyCode() ) { 
                        case KeyEvent.VK_W:
                            System.out.println("UP");
                            if(!ifStart(player.getPosition().getX(),player.getPosition().getY(),difficulty)){
                                if(matrix[player.getPosition().getX()-1][player.getPosition().getY()] == 1){
   
                                	Node move = player.go_up(m);
                                	gamePanel.current(move.getY(), move.getX());
                                	
                                }	
                            }
                            
                            break;
                        case KeyEvent.VK_S:
                            System.out.println("DOWN");
                            if(!ifFinish(player.getPosition().getX(),player.getPosition().getY(),difficulty)){
                            	if(matrix[player.getPosition().getX()+1][player.getPosition().getY()] == 1){
                                	System.out.println("current pos is " + player.getPosition().getX() + player.getPosition().getY());
                                	Node move = player.go_down(m);
                                	System.out.println("move is " + move.getX() + " "+move.getY());
                                	System.out.println("------------------------------------------");
                                	gamePanel.current(move.getY(), move.getX());
                                	
                                }
                            } 
                           
                            break;
                        case KeyEvent.VK_A:
                            System.out.println("LEFT");
                            if(matrix[player.getPosition().getX()][player.getPosition().getY()-1] == 1){
                            	Node move = player.go_left(m);
                            	gamePanel.current(move.getY(), move.getX());

                            }
                            
                            break;
                        case KeyEvent.VK_D :
                            System.out.println("RIGHT");
                            if(matrix[player.getPosition().getX()][player.getPosition().getY()+1] == 1){
                            	Node move = player.go_right(m);
                            	gamePanel.current(move.getY(), move.getX());
                            	
                            }
                            
                            break;
                            
                            
                            
                        case KeyEvent.VK_UP:
                            System.out.println("UP");
                            if(!ifStart(player2.getPosition().getX(),player2.getPosition().getY(),difficulty)){
                                if(matrix[player2.getPosition().getX()-1][player2.getPosition().getY()] == 1){
   
                                	Node move = player2.go_up(m);
                                	gamePanel.current2(move.getY(), move.getX());
                                	
                                }	
                            }
                            
                            break;
                        case KeyEvent.VK_DOWN:
                            System.out.println("DOWN");
                            if(!ifFinish(player2.getPosition().getX(),player2.getPosition().getY(),difficulty)){
                            	if(matrix[player2.getPosition().getX()+1][player2.getPosition().getY()] == 1){
                                	System.out.println("current pos is " + player2.getPosition().getX() + player2.getPosition().getY());
                                	Node move = player2.go_down(m);
                                	System.out.println("move is " + move.getX() + " "+move.getY());
                                	System.out.println("------------------------------------------");
                                	gamePanel.current2(move.getY(), move.getX());
                                	
                                }
                            } 
                           
                            break;
                        case KeyEvent.VK_LEFT:
                            System.out.println("LEFT");
                            if(matrix[player2.getPosition().getX()][player2.getPosition().getY()-1] == 1){
                            	Node move = player2.go_left(m);
                            	gamePanel.current2(move.getY(), move.getX());

                            }
                            
                            break;
                        case KeyEvent.VK_RIGHT :
                            System.out.println("RIGHT");
                            if(matrix[player2.getPosition().getX()][player2.getPosition().getY()+1] == 1){
                            	Node move = player2.go_right(m);
                            	gamePanel.current2(move.getY(), move.getX());
                            	
                            }
                            
                            break;
                     }
                     if(ifFinish(player.getPosition().getX(),player.getPosition().getY(), difficulty)){
                    	 JFrame frame = new JFrame();
                    	 JOptionPane.showMessageDialog(frame, "Player1 won");
                    	 frame.dispose();
                    	 cl.show(bossPanel, "home");
                     }
                     if(ifFinish(player2.getPosition().getX(),player2.getPosition().getY(), difficulty)){
                    	 JFrame frame = new JFrame();
                    	 JOptionPane.showMessageDialog(frame, "Player2 won");
                    	 frame.dispose();
                    	 cl.show(bossPanel, "home");
                     }
                     
                        
                }        
        }); 
                gamePanel.requestFocus();
                
            }
        });
		createHomeButton(0);
		gamePanel.add(homeButton);
		
	}
}
