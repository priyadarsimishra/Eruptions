// Priyadarsi Mishra
// 4/27/20
// Period: 5
// ERUPTION Game Project
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.ImageIcon;
/* This is the main class that runs the game contains the initializations
of all of the other classes */
public class Game extends Canvas implements Runnable
{
	public static final int HEIGHT = 800;
	public static final int WIDTH = 800;
	private static final String TITLE = "ERUPTION";
	private Thread thread;
	private boolean running = false;
	private BufferedImage spritesheet = null;
	private Image background;
	private ImageIcon icon;
	public BufferedImage level1;
	private Window window;
	private Player player;
	private HUD hud;
	public ObjectHandler handler;
	private SpriteTextures texture;
	private Spawn spawner;
	private Menu menu;
	private LevelDisplay levelDisplay;
	public int wait = 0;
	public int level1Complete = 0;
	private int bossDisplay = 0;
	private int bulletCount = 0;
	public int bossFight = 1500;
	public boolean isBossFight = false;
	private boolean isShooting = false;
	public boolean isLevel1Complete = false;
	public boolean isLevel2Complete = false;
	public boolean isLevel3Complete = false;
	public static final STATE STATE = null;
	/* This enumeration holds constants for the State of the game */
	public enum STATE
	{
		MENU,
		HELP,
		SELECTLEVEL,
		LEVEL1,
		LEVEL2,
		ITEMSTORE,
		UPGRADES,
		DEADSCREEN,
	};
	public static STATE gameState = STATE.MENU;
	/* In the constructor we make our JFrame */ 
	public Game()
	{
		window = new Window(WIDTH,HEIGHT,TITLE,this);
	}
	/* This method gets all our images that we need in the game
	 * and its also initializes all of the class instances */
	public void runImage()
	{
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try
		{
			spritesheet = loader.loadImage("/SpriteSheet.png");
			level1 = loader.loadImage("/LEVEL1.png");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		icon = new ImageIcon(this.getClass().getResource("background.gif"));
		background = icon.getImage();
		addKeyListener(new KeyMovement(this));
		texture = new SpriteTextures(this);
		handler = new ObjectHandler();
		menu = new Menu(this,handler,texture);
		addMouseListener(menu);
		spawner = new Spawn(handler,hud,this,texture);
		hud = new HUD(this,menu);
		levelDisplay = new LevelDisplay(this);
		player = new Player(385,738,this,texture,handler,ID.Player);
	}
	/* This method is run as the thread starts due to "synchronized" 
	 * and it checks if the game is not running then it starts the thread */
	public synchronized void start()
	{
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	/* This method is also checked as the thread is running and if
	 * running is false then it stops the thread basically stopping the game */
	public synchronized void stop()
	{
		if(!running)
			return;
		running = false;
		try
		{
			thread.join();
		}
		catch(InterruptedException e) 
		{
			e.printStackTrace();
		}
		System.exit(1);
	}
	/* This method is the game loop and it is running always when the thread starts
	 * and this always updates the contents in the game */
	public void run()
	{
		runImage();
		int updates = 0;
		int FPS = 0;
		long lastTime = System.nanoTime();
		final double amountOfUpdates = 60.0;
		double ns = 1000000000/amountOfUpdates;
		double catchUp  = 0;
		long timerCheck = System.currentTimeMillis();
		while(running)
		{
			long now = System.nanoTime();
			catchUp += (now-lastTime)/ns;
			lastTime = now;
			if(catchUp >=1)
			{
				update();
				updates++;
				catchUp--;
				
			}
			render();
			FPS++;
			if(System.currentTimeMillis() - timerCheck>1000)
			{
				timerCheck+=1000;
				//System.out.println("Updates: "+updates+", FPS: "+FPS);			
				FPS = 0;
				updates = 0;
			}
		}
		stop();
	}
	/* This method updates 60 times per second
	 * and depending on which State of the game it
	 * calls the update method of certain classes */
	public void update()
	{
		if(gameState == STATE.MENU)
		{
			menu.update();
			hud.update();
			isBossFight = false;
			spawner.bossMade = false;
		}
		else if(gameState == STATE.LEVEL1)
		{
			if((level1Complete < bossFight) && !isBossFight)
			{
				//isLevel1Complete = true;
				if(hud.HEALTH<=0)
				{
					gameState = STATE.DEADSCREEN;
					wait = 0;
					player.x = 385;
					level1Complete = 0;
					bossDisplay = 0;
					HUD.LEVEL1BOSSHEALTH = 200;
				}
				else
				{
					if(wait>=500)
					{
						player.update();
						handler.update();
						hud.update();
						spawner.update();
					}
					else 
					{
						wait++;
					}
				}
				level1Complete++;
			}
			else
			{
				if(!isBossFight)
				{
					isBossFight = true;
					handler.clearAll();
				}
			}
			if(isBossFight && bossDisplay>=1000)
			{
				if(HUD.LEVEL1BOSSHEALTH<=0)
				{
					handler.clearAll();
				}
				if(hud.HEALTH<=0)
				{
					gameState = STATE.DEADSCREEN;
					wait = 0;
					player.x = 385;
					level1Complete = 0;
					HUD.LEVEL1BOSSHEALTH = 200;
				}
				else 
				{
					if(wait>=500)
					{
						player.update();
						handler.update();
						hud.update();
						spawner.update();
					}
					else 
					{
						wait++;
					}
				}
				
			}
		}
		else if(gameState == STATE.LEVEL2)
		{
			player.update();
			handler.update();
			hud.update();
			hud.HEALTH = 100;
		}
		else if(gameState == STATE.DEADSCREEN)
		{
			menu.update();
		}
	}
	/* This method renders all our images into the game 
	 * and also depending on the game State is renders 
	 * specific images using a BufferStrategy which makes
	 * the image change smooth */
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();		
		if(bs==null)
		{
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		///////////////////////////////Everything under is drawing
		if(gameState == STATE.MENU)
		{
			g.setColor(Color.ORANGE);
			g.fillRect(0, 0, Game.WIDTH,Game.HEIGHT);
			menu.render(g);
		}
		else if(gameState == STATE.SELECTLEVEL)
		{
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, Game.WIDTH,Game.HEIGHT);
			menu.render(g);
		}
		else if(gameState == STATE.HELP)
		{
			g.setColor(Color.YELLOW);
			g.fillRect(0, 0,WIDTH,HEIGHT);
			menu.render(g);
		}
		else if(gameState == STATE.LEVEL1)
		{
			g.drawImage(background,0,0,WIDTH,HEIGHT,null);
			player.render(g);
			if(wait>=500)
			{
				handler.render(g);
			}
			else 
			{
				wait++;
				levelDisplay.render(g);
			}
			if(bossDisplay>=1000 && isBossFight)
			{
				handler.render(g);
				hud.render(g);
			}
			else if(isBossFight)
			{
				bossDisplay++;
				levelDisplay.render(g);
			}
			hud.render(g);
			handler.render(g);
			if(HUD.LEVEL1BOSSHEALTH<=0)
			{
				levelDisplay.render(g);
				menu.render(g);
			}
		}
		else if(gameState == STATE.LEVEL2)
		{	
			g.setColor(Color.PINK);
			g.fillRect(0,0,WIDTH,HEIGHT);
			player.render(g);
			handler.render(g);
			hud.render(g);
		}
		else if(gameState == STATE.DEADSCREEN)
		{
			g.setColor(Color.RED);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			menu.render(g);
		}
		///////////////////////////////Everything above is drawing
		g.dispose();
		bs.show();
	}
	/* This method is the key Pressed method for moving 
	 * the character and shooting bullets(has not yet been applied) */
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT)
		{ 
			player.setXVel(7);
		}
		if(key == KeyEvent.VK_LEFT)
		{
			player.setXVel(-7);
		}
		if(key == KeyEvent.VK_SPACE && gameState == STATE.LEVEL1 && isBossFight && bulletCount < player.bucketCount)
		{
			handler.addObject(new WaterBullet(player.getX()+9,player.getY()-25,ID.WaterBullet,handler,texture,-15));			
			bulletCount++;
			
		}
		if(key == KeyEvent.VK_SPACE && !isShooting && gameState == STATE.LEVEL2)
		{
			isShooting = true;
			handler.addObject(new Bullet(player.getX()+9,player.getY()-25,ID.Bullet,handler,texture,-15));

		}//shoot bullets
	}
	/* This method is the key Released method for stopping 
	 * the character and stopping continuous bullets shooting(has not yet been applied) */
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT)
		{
			player.setXVel(0);
		}
		if(key == KeyEvent.VK_LEFT)
		{
			player.setXVel(0);
		}
		if(key == KeyEvent.VK_SPACE && gameState == STATE.LEVEL2)
		{
			isShooting = false;
		}//shoot bullets
	}
	/* This is the static restrict method which is used
	 * to restrict the player from moving off the screen
	 * and to restrict the Health display 
	 * from dropping less than 0 */
	public static double restrict(double loc,double min,double max)
	{
		if(loc >= max)
			return loc = max;
		else if(loc <= min)
			return loc = min;
		else
			return loc;
	}
	/* This method returns the sprite sheet image */
	public BufferedImage getSpriteSheet()
	{
		return spritesheet;
	}
	/* This is the main method that calls the constructor
	 * and starts the game */
	public static void main(String [] args)
	{
	
		Game game = new Game();
		game.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		game.setMaximumSize(new Dimension(WIDTH,HEIGHT));
		game.setMinimumSize(new Dimension(WIDTH,HEIGHT));
		game.setBackground(Color.ORANGE);
		/*g.drawString("Hello", 20, 20);
		
		//game.setVisible(false);
		JFrame frame = new JFrame(TITLE);
		frame.add(game);
		frame.pack();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();*/
	}
}
