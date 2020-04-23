import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;

import javax.swing.JFrame;
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
	private BufferedImage background;
	private Window window;
	private Player player;
	private HUD hud;
	public ObjectHandler handler;
	private SpriteTextures texture;
	private Spawn spawner;
	boolean isShooting = false;
	private Menu menu;
	private LevelDisplay levelDisplay;
	public int wait = 0;
	public static final STATE STATE = null;
	public enum STATE
	{
		MENU,
		LEVEL1,
		LEVEL2,
		SHOP,
		DEADSCREEN,
	};
	public static STATE gameState = STATE.MENU;
	public Game()
	{
		window = new Window(WIDTH,HEIGHT,TITLE,this);
	}
	public void runImage()
	{
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try
		{
			spritesheet = loader.loadImage("/SpriteSheet.png");
			background = loader.loadImage("/background.png");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		addKeyListener(new KeyMovement(this));
		texture = new SpriteTextures(this);
		handler = new ObjectHandler();
		menu = new Menu(this,handler);
		addMouseListener(menu);
		spawner = new Spawn(handler,hud,this,texture);
		hud = new HUD(this,menu);
		levelDisplay = new LevelDisplay(this);
		player = new Player(385,738,this,texture,handler,ID.Player);
	}
	public synchronized void start()
	{
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
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
				System.out.println("Updates: "+updates+", FPS: "+FPS);			
				FPS = 0;
				updates = 0;
			}
		}
		stop();
	}
	public void update()
	{
		if(gameState == STATE.MENU)
		{
			menu.update();
			handler.update();
			hud.update();
		}
		else if(gameState == STATE.LEVEL1)
		{
			if(hud.HEALTH<=0)
			{
				gameState = STATE.DEADSCREEN;
				wait = 0;
				player.x = 385;
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
		else if(gameState == STATE.DEADSCREEN)
		{
			menu.update();
		}
	}
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
		if(key == KeyEvent.VK_SPACE && !isShooting)
		{
			isShooting = true;
		}//shoot bullets
	}
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
		if(key == KeyEvent.VK_SPACE)
		{
			isShooting = false;
		}//shoot bullets
	}
	public static double restrict(double loc,double min,double max)
	{
		if(loc >= max)
			return loc = max;
		else if(loc <= min)
			return loc = min;
		else
			return loc;
	}
	public BufferedImage getSpriteSheet()
	{
		return spritesheet;
	}
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
