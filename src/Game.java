import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Game extends Canvas implements Runnable
{
	public static final int HEIGHT = 800;
	public static final int WIDTH = 800;
	private static final String TITLE = "Eruption";
	private Thread thread;
	private boolean run = false;
	private BufferedImage spritesheet = null;
	private BufferedImage background;
	private Window window;
	private Player player;
	private HUD hud;
	private ObjectHandler handler;
	private SpriteTextures texture;
	private Spawn spawner;
	
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
		hud = new HUD();
		spawner = new Spawn(handler,hud,this,texture);
		player = new Player(385,735,this,texture,handler,ID.Player);
	}
	public synchronized void start()
	{
		if(run)
			return;
		run = true;
		thread = new Thread(this);
		thread.start();
	}
	public synchronized void stop()
	{
		if(!run)
			return;
		run = false;
		try
		{
			thread.join();
		}
		catch(InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	public void run()
	{
		runImage();
		long time = System.nanoTime();
		double amountOfUpdates = 60.0;
		double ns = 1000000000/amountOfUpdates;
		double catchUp  = 0.0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(run)
		{
			long now = System.nanoTime();
			catchUp += (now-time)/ns;
			time = now;
			while(catchUp>=1)
			{
				update();
				updates++;
				catchUp--;
			}
			render();
			frames++;
			if(System.currentTimeMillis()-timer>=1000)
			{
				timer+=1000;
				System.out.println("Ticks: "+updates+", FPS: "+frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	private void update()
	{
		player.update();
		handler.update();
		hud.update();
		spawner.update();
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
		g.drawImage(background,0,0,WIDTH,HEIGHT,null);
		handler.render(g);
		hud.render(g);
		player.render(g);
		///////////////////////////////Everything above is drawing
		g.dispose();
		bs.show();
	}
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT)
		{ 
			player.setXVel(8);
		}
		if(key == KeyEvent.VK_LEFT)
		{
			player.setXVel(-8);
		}
		if(key == KeyEvent.VK_SPACE){}//shoot bullets
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
		if(key == KeyEvent.VK_SPACE){}//shoot bullets
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
	}
	
}
