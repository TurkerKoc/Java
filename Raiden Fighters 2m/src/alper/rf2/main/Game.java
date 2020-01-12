package alper.rf2.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import alper.rf2.main.interfaces.GameInterfaceA;
import alper.rf2.main.interfaces.GameInterfaceB;
import alper.rf2.main.menu.User;


public class Game extends Canvas implements Runnable 
{
	
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 500, HEIGHT = 700; // frame width and height
	private static final String title = "Raiden Fighters 2"; // frame title
	
	private static Game game = new Game(); // Game object
	private static User user = new User(); // User object
	public static JFrame frame;
	private Player player;
	private Lists lists;
	private Images images;
	
	private boolean gameRunning = false; // to control game state
	private Thread thread; // only thread for game
	
	// all images used in the game
	private BufferedImage playerImage = null;
	private BufferedImage laserImage = null;
	private BufferedImage missileImage = null;
	private BufferedImage enemyImage = null;
	private BufferedImage lifeImage = null;
	private BufferedImage enemyTankImage = null;
	private BufferedImage backgroundImage = null;
	private static List<BufferedImage> explosionImageList= new ArrayList<BufferedImage>();
		
	public LinkedList<GameInterfaceA> objectA; // List for player, player laser, player missile
	public LinkedList<GameInterfaceB> objectB; // List for enemy jet, enemy tank, enemy laser
	
    public static String currentUser, currentPassword; // information for current user

	private boolean intermediate = false; // true is intermediate, false is novive game hardness.
	
    public int score=0; // holds player score 
	private boolean isMissileShooting; // shooting missile is allowed every 10 seconds.
	
	public static boolean fireMissile = false;
	static JFrame initialScreen ;//= new JFrame("Raiden Fighters 2");
	static JFrame gameOverScreen ;//= new JFrame("Raiden Fighters 2");
	static JFrame scoresScreen;//= new JFrame("Raiden Fighters 2");

	public static void main(String args[])
	{
	
		// setting size of the component
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT)); 
		game.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		game.setMinimumSize(new Dimension(WIDTH, HEIGHT));

		frame = new JFrame(); // creating new frame
		
		
		// Menu items 
		JMenuItem quit = new JMenuItem("Quit");
		JMenuItem credits = new JMenuItem("Credits");
		JMenuItem login = new JMenuItem("Login");
		JMenuItem register = new JMenuItem("Register");
		JMenuItem playGame = new JMenuItem("Play Game");
		JMenuItem scores = new JMenuItem("Scores");
	
		// Menus
		JMenu file = new JMenu("File");
		JMenu help = new JMenu("Help");
		
		//Menu items added to File menu
		file.add(playGame);
		file.add(login);
		file.add(register);
		file.add(scores);
		file.add(quit);

		//Menu items added to Help menu
		help.add(credits);
		
		// set menu bar and add it to the frame
	    JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar); 
	
		// Menus added to the menu bar
		menuBar.add(file);
		menuBar.add(help);
		
		frame.add(game); 		// Game object added to the frame
		frame.pack(); // if preferredSize minimumSize maximumSize methods used, pack method should be used to fit all the components to the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // enables the exit button
		frame.setResizable(false); // does not allows to size window
		frame.setLocationRelativeTo(null); //initializes frame at the center of the screen
		frame.setVisible(false); // visibility of the frame
		game.createInitialScreen(); //create initial screen before game starts for login or register.
		
		//Quit action at menuBar
		class quitAction implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
					System.exit(0);				
			}
		}
		quit.addActionListener(new quitAction());
	
		//Credits Action at menuBar
		class creditsAction implements ActionListener
		{	@Override
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(frame, "\nİbrahim Alper KOÇ\n\n 20130702029 \n\n Yeditepe University - "
						+ "Computer Engineering\n\n alper.koc@windowslive.com", "İbrahim Alper KOÇ",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		credits.addActionListener(new creditsAction());
		
		// Login action at menuBar
		class loginAction implements ActionListener
		{	@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(user.loginUser()==1)
				{
					game.start();
				}
			}
		}
		login.addActionListener(new loginAction());
		
		// Register action at menuBar
		class registerAction implements ActionListener
		{	@Override
			public void actionPerformed(ActionEvent e) 
			{
				user.registerUser();
			}
		}
		register.addActionListener(new registerAction());
		
		//Scores action at menuBar
		class scoresAction implements ActionListener
		{	@Override
			public void actionPerformed(ActionEvent e) 
			{
				game.createScoresScreen();
			}

		}
		scores.addActionListener(new scoresAction());
	
	}
	public void init()// initializes game 
	{
		requestFocus();// focus for keyboard and mouse inputs
		LoadImages get = new LoadImages(); //
		
		// load all images that used in the game
		playerImage = get.getImage("/playerBlue.png");
		laserImage = get.getImage("/laserRed.png");
		backgroundImage = get.getImage("/gameBackground.png");
		lifeImage = get.getImage("/heart.png");
		enemyImage = get.getImage("/enemyOrange.png");
		enemyTankImage = get.getImage("/tank1.png");
		missileImage = get.getImage("/laserRed10.png");
		
		// explosion animation is created with 14 images
		for(int i = 0; i<14; i++)
		{
			explosionImageList.add(get.getImage("/explosion/explosion"+i+".png")) ;	
		}
		addKeyListener(new Input(this));// adding key listener to class

		images = new Images(this);// images object
		player = new Player(250, 700,images);// setting player with initial position
		lists = new Lists(images,this);
		
		if(intermediate) //if intermediate
		{
			lists.addEnemyJet(4);// add 4 enemy jet
			lists.addEnemyTank(1);// add 1 tank
		}
		else // if novice
		{
			lists.addEnemyJet(3);// add 3 enemy jet
		}
		objectA = lists.getObjectA(); // player - laser - missile
		objectB = lists.getObjectB(); // enemy jet - enemy tank - enemy laser
	}
	//private synchronized start()
	private void start() // not synchronized because only one thread is used. When called, game starts.
	{	
			gameRunning = true; // it is not setted false anyhere. if needed to stop game, should be set to false;
			thread = new Thread(this);
			thread.start();
	}
	
	@Override
	public void run()
	{	
		init();// initializing the game frame	
		
		long pastTime = System.nanoTime();
		final double nanoSeconds = 1000000000 / 30;// 30 is frame count per second
		double change = 0 ;
		
		while(gameRunning)
		{
			long now = System.nanoTime();
			change += (now - pastTime) / nanoSeconds;
			pastTime = now;
			if (change >= 1) 
			{
				update();
				change--;
			}
			try
			{
				draw();
			} catch (Exception e){}
			
		}
		
	/*	init();

		while(running)
		{
			update();
			draw();
		}
		
		stop();
		
		*/

}
	
	private void update() 
	{
		player.update(); // updates player
		lists.update(); // updates all enemies lasers and missiles
				
		if (Collision.isCollided2(player, game.objectB)) // controls collision with player and any enemy object without tanks
		{
			EnemyJets.isCollidedWithPlayer = true; // setting true for start animation
			// getting x and y positions for animation position
			EnemyJets.xPosition = player.getX();
			EnemyJets.yPosition = player.getY();
			
			player.remainingLife--; // if any crash occurs with player, decrease life
			
			if (player.remainingLife == 0) // if life is zero
			{				
				frame.setVisible(false); //close frame
				user.getUsersToArray(); // get users to array for score comparison
				
				for(int i =0; i< user.getUserList().size() ; i++) // loop for all users in the arrayList
				{
					// if current sername password equals any user in the arrayList
					// and score of current user is greater than old score
					if (user.getUserList().get(i).getUsername().equals(currentUser) &&
						user.getUserList().get(i).getPassword().equals(currentPassword) && 
						user.getUserList().get(i).getScore() < Player.score)
					{
						// set score of current user
						user.getUserList().get(i).setScore(Player.score);
					}
				}
		        user.setUsersToFile();// after all user operations on the arraylist, write all users to the file
				createGameOverScreen();// show gameover screen
			}
		}
	}
	private void draw() 
	{
		BufferStrategy bufferStrategy = this.getBufferStrategy(); // returns boolean
		if (bufferStrategy == null) // previous buffer disposed, returns null
		{
			createBufferStrategy(2); // creates 2 frames before drawing the frame
			return;
		}
		Graphics g = bufferStrategy.getDrawGraphics();
		
		g.drawImage(backgroundImage, 0, 0, this);

		player.draw(g); // draws player to the frame
		lists.draw(g); // draws all enemies lasers and missiles	
		
		g.dispose(); // releases resources
		bufferStrategy.show(); // shows the frame
		
	}
	public void keyPressed(KeyEvent event) 
	{
		int pressedButton = event.getKeyCode();	
		if( pressedButton == KeyEvent.VK_LEFT)
		{
			player.setVelocityX(-5); // move player left
		}
		if(pressedButton== KeyEvent.VK_RIGHT)
		{
			player.setVelocityX(5); // move player right
		}
		if(pressedButton== KeyEvent.VK_UP)
		{
			player.setVelocityY(-5); // move player up
		}
		if(pressedButton== KeyEvent.VK_DOWN)
		{
			player.setVelocityY(5); // move player down
		}
		if(pressedButton== KeyEvent.VK_SPACE )// if space button is pressed
		{
				// shoot double laser
				lists.addObject(new Laser(player.getX() + 10, player.getY(), images,this));	
				lists.addObject(new Laser(player.getX() + 30, player.getY(), images,this));	
		}
		if(pressedButton== KeyEvent.VK_A && !isMissileShooting) // if A button is pressed
		{
			for(int i=0; i<objectA.size() ; i++)// remove all A object which is outside of the screen which is moved before
			{
				if(objectA.get(i).getX() >500 || objectA.get(i).getY() > 700) // checking areas outside of the frame
				{
					lists.removeObject(objectA.get(i)); // remove object
				}
			}
			for(int i=0; i<objectB.size() ; i++)// remove all B object which is outside of the screen which is moved before
			{
				if(objectB.get(i).getX() >500 || objectB.get(i).getY() > 700)// checking areas outside of the frame
				{
					lists.removeObject(objectB.get(i));// remove object
				}
			}
			if (fireMissile) // returns true 10 seconds after shooting previous missile, add 3 rows of missile
			{
				lists.addObject(new Missile(player.getX(), player.getY() -30, images,this));	
				lists.addObject(new Missile(player.getX(), player.getY(), images,this));	
				lists.addObject(new Missile(player.getX(), player.getY() +30, images,this));	

				Player.timer = 300;// timer for missile. game runs on 30 fps. 300 means 10 second.
				fireMissile = false;// setting false for 10 second duration.
			}
		}
	}
	public void keyReleased(KeyEvent e) 
	{
		// if key released, stop player
		int releasedButton = e.getKeyCode();
		if( releasedButton == KeyEvent.VK_LEFT)
		{
			player.setVelocityX(0);
		}
		if(releasedButton== KeyEvent.VK_RIGHT)
		{
			player.setVelocityX(0);
		}
		if(releasedButton== KeyEvent.VK_UP)
		{
			player.setVelocityY(0);
		}
		if(releasedButton== KeyEvent.VK_DOWN)
		{
			player.setVelocityY(0);
		}
		if(releasedButton== KeyEvent.VK_SPACE)
		{
	
		}
	}
	public BufferedImage getPlayerImage() 
	{
		return playerImage;
	}
	public BufferedImage getLaserImage() {
		return laserImage;
	}
	public BufferedImage getLifeImage() {
		return lifeImage;
	}
	public BufferedImage getMissileImage() {
		return missileImage;
	}
	public BufferedImage getEnemyImage() {
		return enemyImage;
	}
	public BufferedImage getEnemyTankImage() {
		return enemyTankImage;
	}
	public BufferedImage getBackgroundImage()
	{
		return backgroundImage;
	}
	public static List<BufferedImage> getExplosionImageList() {
		return explosionImageList;
	}
	public static void setExplosionImageList(List<BufferedImage> explosionImageList) 
	{
		Game.explosionImageList = explosionImageList;
	}
	public static String getTitle() {
		return title;
	}
	
	
	private void createInitialScreen()
	{
		initialScreen = new JFrame("Raiden Fighters 2");
		initialScreen.setBounds(200,200,500,700);
		initialScreen.getContentPane().setLayout(null);
		initialScreen.setLocationRelativeTo(null);
		initialScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initialScreen.setContentPane(new JLabel(new ImageIcon("Assets/background.png")));
		
		JMenuItem quit = new JMenuItem("Quit");
		JMenuItem credits = new JMenuItem("Credits");
		JMenuItem login = new JMenuItem("Login");
		JMenuItem register = new JMenuItem("Register");
		JMenuItem playGame = new JMenuItem("Play Game");
		JMenuItem scores = new JMenuItem("Scores");
		JMenuItem controls = new JMenuItem("Controller");

		JMenu file = new JMenu("File");
		JMenu help = new JMenu("Help");
		
		file.add(playGame);
		file.add(login);
		file.add(register);
		file.add(scores);
		file.add(quit);

		help.add(credits);
		help.add(controls);
		
	    JMenuBar menuBar = new JMenuBar();
		initialScreen.setJMenuBar(menuBar); 
	
		menuBar.add(file);
		menuBar.add(help);
		
		class playGameAction implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
					if(user.loginUser()==1)
					{
						initialScreen.setVisible(false);
						frame.setVisible(true);
						game.start();
						

						
					}
			}
		}
		playGame.addActionListener(new playGameAction());

		class quitAction implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
					System.exit(0);				
			}
		}
		quit.addActionListener(new quitAction());

		class creditsAction implements ActionListener
		{	@Override
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(frame, "\nİbrahim Alper KOÇ\n\n 20130702029 \n\n Yeditepe University - "
						+ "Computer Engineering\n\n alper.koc@windowslive.com", "İbrahim Alper KOÇ",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		credits.addActionListener(new creditsAction());

		class controlsAction implements ActionListener
		{	@Override
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(frame, "\n Move - Arrow Keys\n\n Laser - Space \n\n Missile - A \n\n (Recharge duration for missile is 10 seconds.) ", "Controls",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		controls.addActionListener(new controlsAction());
		
		class loginAction implements ActionListener
		{	@Override
			public void actionPerformed(ActionEvent e) 
			{
			

				if(user.loginUser()==1)
				{
					initialScreen.setVisible(false);
					frame.setVisible(true);
					game.start();

					
				}
			}
	
		
		}
		login.addActionListener(new loginAction());
	
		class registerAction implements ActionListener
		{	@Override
			public void actionPerformed(ActionEvent e) 
			{
			//User user = new User();
			user.registerUser();
			}

		}
		register.addActionListener(new registerAction());

		class scoresAction implements ActionListener
		{	@Override
			public void actionPerformed(ActionEvent e) 
			{
			System.out.println("scores");
			game.createScoresScreen();
			}

		}
		scores.addActionListener(new scoresAction());
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setForeground(Color.white);
		usernameLabel.setBounds(initialScreen.getBounds().width/2 - 45, initialScreen.getBounds().height/2 + 30, 80, 20);
		
		JTextField usernameTF = new JTextField();
		usernameTF.setBounds(initialScreen.getBounds().width/2 - 100, initialScreen.getBounds().height/2 + 50, 180, 30);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(Color.white);
		passwordLabel.setBounds(initialScreen.getBounds().width/2 - 45, initialScreen.getBounds().height/2 + 80, 80, 20);
		
		JPasswordField passwordTF = new JPasswordField();
		passwordTF.setBounds(initialScreen.getBounds().width/2 -100, initialScreen.getBounds().height/2 + 100, 180, 30);

		JButton playButton = new JButton("Play");
		playButton.setBounds(initialScreen.getBounds().width/2 - 50, initialScreen.getBounds().height/2 + 140, 80, 50);
		
		JButton noviceButton = new JButton("Novice");
		noviceButton.setBounds(initialScreen.getBounds().width/2 - 110 , initialScreen.getBounds().height/2 + 200, 100, 40);
		
		JButton intermediateButton = new JButton("Intermediate");
		intermediateButton.setBounds(initialScreen.getBounds().width/2 -5, initialScreen.getBounds().height/2 + 200, 100, 40);
		
		intermediate = false;
		noviceButton.setForeground(Color.BLUE);
		initialScreen.setResizable(false);
		
		initialScreen.add(usernameLabel);
		initialScreen.add(usernameTF);
		initialScreen.add(passwordLabel);
		initialScreen.add(passwordTF);
		initialScreen.add(playButton);
		initialScreen.add(noviceButton);
		initialScreen.add(intermediateButton);

		playButton.addMouseListener(new MouseListener() 
		{
			
			@Override
			public void mouseReleased(MouseEvent e) 
			{				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				

					if(user.loginUser(usernameTF.getText(),passwordTF.getText())==1)
					{
						initialScreen.setVisible(false);
						frame.setVisible(true);
						game.start();

						
					}
					else
					{

					}
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {				
			}
		});

		noviceButton.addMouseListener(new MouseListener() 
		{
			
			@Override
			public void mouseReleased(MouseEvent e) 
			{				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
				intermediate = false;
				noviceButton.setForeground(Color.BLUE);
				intermediateButton.setForeground(Color.BLACK);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {				
			}
		});
		intermediateButton.addMouseListener(new MouseListener() 
		{
			
			@Override
			public void mouseReleased(MouseEvent e) 
			{				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
				intermediate = true;
				intermediateButton.setForeground(Color.BLUE);
				noviceButton.setForeground(Color.BLACK);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {				
			}
		});
		initialScreen.setVisible(true);
	}
	
	private void createGameOverScreen()
	{
		gameOverScreen = new JFrame("Raiden Fighters 2");
		gameOverScreen.setBounds(200,200,500,700);
		gameOverScreen.getContentPane().setLayout(null);
		gameOverScreen.setLocationRelativeTo(null);
		gameOverScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameOverScreen.setContentPane(new JLabel(new ImageIcon("Assets/background.png")));
		
		JLabel scoresLabel = new JLabel("Highscores");
		scoresLabel.setForeground(Color.white);
		scoresLabel.setFont(new Font("Stencil", Font.BOLD, 24));
		scoresLabel.setBounds(initialScreen.getBounds().width/2 - 80, initialScreen.getBounds().height/2 + 50, 150, 20);

		JMenuItem quit = new JMenuItem("Quit");
		JMenuItem credits = new JMenuItem("Credits");
		JMenuItem login = new JMenuItem("Login");
		JMenuItem register = new JMenuItem("Register");
		JMenuItem playGame = new JMenuItem("Play Game");
		JMenuItem scores = new JMenuItem("Scores");

		JMenu file = new JMenu("File");
		JMenu help = new JMenu("Help");
		
		file.add(playGame);
		file.add(login);
		file.add(register);
		file.add(scores);
		file.add(quit);

		help.add(credits);
		
	    JMenuBar menuBar = new JMenuBar();
		gameOverScreen.setJMenuBar(menuBar); 
	
		menuBar.add(file);
		menuBar.add(help);
		playGame.setEnabled(false);
		login.setEnabled(false);

		class playGameAction implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//User user = new User();
				user.loginUser();
				
					if(user.loginUser()==1)
					{
						initialScreen.setVisible(false);
						frame.setVisible(true);
						game.start();
						

						
					}
			}
		}
		playGame.addActionListener(new playGameAction());

		class quitAction implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
					System.exit(0);				
			}
		}
		quit.addActionListener(new quitAction());

		class creditsAction implements ActionListener
		{	@Override
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(frame, "\nİbrahim Alper KOÇ\n\n 20130702029 \n\n Yeditepe University - "
						+ "Computer Engineering\n\n alper.koc@windowslive.com", "İbrahim Alper KOÇ",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		credits.addActionListener(new creditsAction());
		
		class loginAction implements ActionListener
		{	@Override
			public void actionPerformed(ActionEvent e) 
			{
			
			//User user = new User();
			user.loginUser();
			
				if(user.loginUser()==1)
				{
					initialScreen.setVisible(false);
					frame.setVisible(true);
					game.start();

					
				}
			}
	
		
		}
		login.addActionListener(new loginAction());
	
		class registerAction implements ActionListener
		{	@Override
			public void actionPerformed(ActionEvent e) 
			{
			//User user = new User();
			user.registerUser();
			}

		}
		register.addActionListener(new registerAction());

		class scoresAction implements ActionListener
		{	@Override
			public void actionPerformed(ActionEvent e) 
			{
			System.out.println("scores");
			game.createScoresScreen();
			}

		}
		scores.addActionListener(new scoresAction());
	
		user.getUsersToArray();
		user.getUserList().sort(Comparator.comparing(User::getScore)); // sort arraylist ascending order
		Collections.reverse(user.getUserList()); // reverse arraylist to get descending order
		
		
		JLabel first;
		if (user.getUserList().size() >= 1) 
		{
			first = new JLabel(user.getUserList().get(0).getUsername()+ " - "+ Integer.toString(user.getUserList().get(0).getScore()));
		}	
		else
		{
			first = new JLabel("- - -");

		}
			first.setForeground(Color.white);
			first.setHorizontalAlignment(SwingConstants.CENTER);
			first.setFont(new Font("Stencil", Font.BOLD, 20));
			first.setBounds(initialScreen.getBounds().width/2 - 115, initialScreen.getBounds().height/2 + 90, 200, 20);

		JLabel second;
		if (user.getUserList().size() >= 2) 		
		{
			second = new JLabel(user.getUserList().get(1).getUsername()+ " - "+ Integer.toString(user.getUserList().get(1).getScore()));
		}
		else
		{
			second = new JLabel("- - -");

		}
			second.setForeground(Color.white);
			second.setHorizontalAlignment(SwingConstants.CENTER);
			second.setFont(new Font("Stencil", Font.BOLD, 16));
			second.setBounds(initialScreen.getBounds().width/2 - 115, initialScreen.getBounds().height/2 + 125, 200, 20);

		
		JLabel third;
		if (user.getUserList().size() >= 3) 
		{
			 third = new JLabel(user.getUserList().get(2).getUsername()+ " - "+ Integer.toString(user.getUserList().get(2).getScore()));
		}
		else
		{
			third = new JLabel("- - -");

		}
			third.setForeground(Color.white);
			third.setHorizontalAlignment(SwingConstants.CENTER);
			third.setFont(new Font("Stencil", Font.BOLD, 12));
			third.setBounds(initialScreen.getBounds().width/2 - 115, initialScreen.getBounds().height/2 +155, 200, 20);

	
		gameOverScreen.add(scoresLabel);
		gameOverScreen.add(first);
		gameOverScreen.add(second);
		gameOverScreen.add(third);
		gameOverScreen.setVisible(true);
		
		try
		{
			thread.stop();
			thread.interrupt();
			thread =null;

		}
		catch(Exception e)
		{
			
		}		
	}
	
	private void createScoresScreen()
	{
		scoresScreen = new JFrame("Raiden Fighters 2");
		scoresScreen.setBounds(200,200,500,700);
		scoresScreen.getContentPane().setLayout(null);
		scoresScreen.setLocationRelativeTo(null);
		scoresScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scoresScreen.setContentPane(new JLabel(new ImageIcon("Assets/background.png")));
		JLabel scoresLabel = new JLabel("Highscores");
		scoresLabel.setForeground(Color.white);
		scoresLabel.setFont(new Font("Stencil", Font.BOLD, 24));
		scoresLabel.setBounds(initialScreen.getBounds().width/2 - 80, initialScreen.getBounds().height/2 + 50, 150, 20);

		JMenuItem quit = new JMenuItem("Quit");
		JMenuItem credits = new JMenuItem("Credits");
		JMenuItem login = new JMenuItem("Login");
		JMenuItem register = new JMenuItem("Register");
		JMenuItem playGame = new JMenuItem("Play Game");
		JMenuItem scores = new JMenuItem("Scores");

		JMenu file = new JMenu("File");
		JMenu help = new JMenu("Help");
		
		file.add(playGame);
		file.add(login);
		file.add(register);
		file.add(scores);
		file.add(quit);

		help.add(credits);
		
	    JMenuBar menuBar = new JMenuBar();
		scoresScreen.setJMenuBar(menuBar); 
	
		menuBar.add(file);
		menuBar.add(help);

		
		class playGameAction implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//User user = new User();
				user.loginUser();
				
					if(user.loginUser()==1)
					{
						initialScreen.setVisible(false);
						frame.setVisible(true);
						game.start();
						

						
					}
			}
		}
		playGame.addActionListener(new playGameAction());
		
		class quitAction implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
					System.exit(0);				
			}
		}
		quit.addActionListener(new quitAction());

		class creditsAction implements ActionListener
		{	@Override
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(frame, "\nİbrahim Alper KOÇ\n\n 20130702029 \n\n Yeditepe University - "
						+ "Computer Engineering\n\n alper.koc@windowslive.com", "İbrahim Alper KOÇ",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		credits.addActionListener(new creditsAction());
		
		class loginAction implements ActionListener
		{	@Override
			public void actionPerformed(ActionEvent e) 
			{
			
			//User user = new User();
			user.loginUser();
			
				if(user.loginUser()==1)
				{
					initialScreen.setVisible(false);
					frame.setVisible(true);
					game.start();

					
				}
			}
	
		
		}
		login.addActionListener(new loginAction());
	
		class registerAction implements ActionListener
		{	@Override
			public void actionPerformed(ActionEvent e) 
			{
			//User user = new User();
			user.registerUser();
			}

		}
		register.addActionListener(new registerAction());

		class scoresAction implements ActionListener
		{	@Override
			public void actionPerformed(ActionEvent e) 
			{
			System.out.println("scores");
			game.createScoresScreen();
			}

		}
		scores.addActionListener(new scoresAction());
		
		user.getUsersToArray();
		user.getUserList().sort(Comparator.comparing(User::getScore));
		Collections.reverse(user.getUserList());
		
		JLabel first;
		if (user.getUserList().size() >= 1) 
		{
			first = new JLabel(user.getUserList().get(0).getUsername()+ " - "+ Integer.toString(user.getUserList().get(0).getScore()));
		}	
		else
		{
			first = new JLabel("- - -");
		}
			first.setForeground(Color.white);
			first.setHorizontalAlignment(SwingConstants.CENTER);
			first.setFont(new Font("Stencil", Font.BOLD, 18));
			first.setBounds(initialScreen.getBounds().width/2 - 115, initialScreen.getBounds().height/2 + 90, 200, 20);

		JLabel second;
		if (user.getUserList().size() >= 2) 		
		{
			second = new JLabel(user.getUserList().get(1).getUsername()+ " - "+ Integer.toString(user.getUserList().get(1).getScore()));
		}
		else
		{
			second = new JLabel("- - -");
		}
			second.setForeground(Color.white);
			second.setHorizontalAlignment(SwingConstants.CENTER);
			second.setFont(new Font("Stencil", Font.BOLD, 18));
			second.setBounds(initialScreen.getBounds().width/2 - 115, initialScreen.getBounds().height/2 + 120, 200, 20);

		
		JLabel third;
		if (user.getUserList().size() >= 3) 
		{
			 third = new JLabel(user.getUserList().get(2).getUsername()+ " - "+ Integer.toString(user.getUserList().get(2).getScore()));
		}
		else
		{
			third = new JLabel("- - -");
		}
			third.setForeground(Color.white);
			third.setHorizontalAlignment(SwingConstants.CENTER);
			third.setFont(new Font("Stencil", Font.BOLD, 18));
			third.setBounds(initialScreen.getBounds().width/2 - 115, initialScreen.getBounds().height/2 +150, 200, 20);
			
		JLabel fourth;
		if (user.getUserList().size() >= 4) 
		{
			 fourth = new JLabel(user.getUserList().get(3).getUsername()+ " - "+ Integer.toString(user.getUserList().get(3).getScore()));
		}
		else
		{
			fourth = new JLabel("- - -");
		}
		fourth.setForeground(Color.white);
		fourth.setHorizontalAlignment(SwingConstants.CENTER);
		fourth.setFont(new Font("Stencil", Font.BOLD, 18));
		fourth.setBounds(initialScreen.getBounds().width/2 - 115, initialScreen.getBounds().height/2 +180, 200, 20);
		
		JLabel fifth;
		if (user.getUserList().size() >= 5) 
		{
			 fifth = new JLabel(user.getUserList().get(4).getUsername()+ " - "+ Integer.toString(user.getUserList().get(4).getScore()));
		}
		else
		{
			fifth = new JLabel("- - -");
		}
		fifth.setForeground(Color.white);
		fifth.setHorizontalAlignment(SwingConstants.CENTER);
		fifth.setFont(new Font("Stencil", Font.BOLD, 18));
		fifth.setBounds(initialScreen.getBounds().width/2 - 115, initialScreen.getBounds().height/2 +210, 200, 20);
		
		scoresScreen.add(scoresLabel);
		scoresScreen.add(first);
		scoresScreen.add(second);
		scoresScreen.add(third);
		scoresScreen.add(fourth);
		scoresScreen.add(fifth);


		initialScreen.setVisible(false);
		scoresScreen.setVisible(true);
		
	
	}
}
