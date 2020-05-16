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
	public static final String TITLE = "ERUPTION";
	public static String NAME = "";
	private Thread thread;
	private boolean running = false;
	private BufferedImage spritesheet = null;
	private BufferedImage level2 = null;
	private BufferedImage level3 = null;
	private BufferedImage level4 = null;
	private Image background;
	private ImageIcon icon;
	private Image menuImage;
	private ImageIcon menuIcon;
	public BufferedImage level1;
	public BufferedImage story;
	private Window window;
	public Player player;
	private HUD hud;
	public ObjectHandler handler;
	private SpriteTextures texture;
	private Spawn spawner;
	private Menu menu;
	private LevelDisplay levelDisplay;
	public Upgrades upgrades;
	private int gunStop = 0;
	public static int level1pause = 0;
	public static int level2pause = 0;
	public static int level3pause = 0;
	public static int level4pause = 0;
	public int level1Complete = 0;
	public int level2Complete = 0;
	public int level3Complete = 0;
	public int level4Complete = 0;
	private int xVel = 7;
	public int bossDisplay = 0;
	public int bossDisplay2 = 0;
	public int bossDisplay3 = 0;
	public int bossDisplay4 = 0;
	private int bulletCount = 0;
	private int explosiveBulletCount = 0;
	private int doubleBulletCount = 0;
	private int shotgunBulletCount = 0;
	public int bossFight = 1500;
	public int bossFight2 = 1500;
	public int bossFight3 = 1500;
	public int bossFight4 = 2000;
	private boolean [] keyDown = new boolean[2];
	public boolean isBossFight = false;
	public boolean isBossFight2 = false;
	public boolean isBossFight3 = false;
	public boolean isBossFight4 = false;
	private boolean isShooting = false;
	public boolean isLevel1Complete = false;
	public boolean isLevel2Complete = false;
	public boolean isLevel3Complete = false;
	public boolean isLevel4Complete = false;
	public static final STATE STATE = null;
	public static String stateholder = "";
	private PlayerInfo playerInfo = null;
	/* This enumeration holds constants for the State of the game */
	public enum STATE
	{
		NAMEPANEL,
		MENU,
		HELP,
		HOWTOPLAY,
		STORY,
		SELECTLEVEL,
		LEVEL1,
		LEVEL2,
		LEVEL3,
		LEVEL4,
		UPGRADES,
		CUSTOMIZE,
		DEADSCREEN,
	};
	public static STATE gameState = STATE.NAMEPANEL;
	/* In the constructor we make our JFrame */ 
	public Game()
	{
		//gameState = STATE.NAMEPANEL;
		NamePanel np = new NamePanel(this);
		//window = new Window(WIDTH,HEIGHT,TITLE,this);
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
			story = loader.loadImage("/Story.png");
			level2 = loader.loadImage("/LEVEL2.png");
			level3 = loader.loadImage("/LEVEL3.png");
			level4 = loader.loadImage("/LEVEL4.png");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		icon = new ImageIcon(this.getClass().getResource("/background.gif"));
		menuIcon = new ImageIcon(this.getClass().getResource("/Menu.gif"));
		addKeyListener(new KeyMovement(this));
		background = icon.getImage();
		menuImage = menuIcon.getImage();
		playerInfo = new PlayerInfo();
		texture = new SpriteTextures(this);
		handler = new ObjectHandler();
		menu = new Menu(this,handler,texture,playerInfo);
		addMouseListener(menu);
		addMouseMotionListener(menu);
		upgrades = new Upgrades(handler,texture);
		addMouseListener(upgrades);
		addMouseMotionListener(upgrades);
		spawner = new Spawn(handler,hud,this,texture);
		hud = new HUD(this,menu);
		levelDisplay = new LevelDisplay(this);
		player = new Player(385,695,this,texture,handler,ID.Player);
		NAME = NamePanel.field.getText();
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
		long lastTime = System.nanoTime();
		double amountOfUpdates = 60.0;
		double ns = 1000000000/amountOfUpdates;
		double catchUp  = 0;
		int updates = 0;
		int FPS = 0;
		long timerCheck = System.currentTimeMillis();
		while(running)
		{
			long now = System.nanoTime();
			catchUp += (now-lastTime)/ns;
			lastTime = now;
			while(catchUp >=1)
			{
				update();
//				try {
//					Thread.sleep(1);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
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
//		try {
//			Thread.sleep(1);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		stop();
	}
	/* This method updates 60 times per second
	 * and depending on which State of the game it
	 * calls the update method of certain classes */
	public void update()
	{
		if(gameState == STATE.MENU)
		{
			handler.clearAll();
			menu.update();
			hud.update();
			player.x = 385;
			level1Complete = 0;
			level2Complete = 0;
			level3Complete = 0;
			level4Complete = 0;
			bossDisplay = 0;
			bossDisplay2 = 0;
			bossDisplay3 = 0;			
			bossDisplay4 = 0;
			level1pause = 0;
			level2pause = 0;
			level3pause = 0;
			level4pause = 0;
			isBossFight = false;
			isBossFight2 = false;
			isBossFight3 = false;
			isBossFight4 = false;
			HUD.LEVEL1BOSSHEALTH = 200;
			HUD.LEVEL2BOSSHEALTH = 500;
			HUD.LEVEL3BOSSHEALTH = 550;
			HUD.LEVEL4BOSSHEALTH = 570;
			HUD.SCORE = 0;
			HUD.COUNT= 0;
			HUD.LEVEL = 0;
			HUD.UNDERGROUNDHEALTH = 1;
			HUD.WIZARDHEALTH = 1;
			HUD.THROWERHEALTH = 1;
			HUD.SPLITHEALTH1 = 0;
			HUD.SPLITHEALTH2 = 0;
			HUD.EXPLODERHEALTH = 1;
			HUD.RAYHEALTH = 1;
			HUD.ROCKETHEALTH = 1;
			HUD.BABYDRAGONHEALTH = 0;
			HUD.TANKHEALTH = 1;
			HUD.BOOMERANGHEALTH = 1;
			hud.display = 150;
			hud.display2 = 150;
			hud.displaylev3 = 150;
			hud.displaylev4 = 150;
			/* Change here when level progress has been implemented */
			isLevel1Complete = true;
			isLevel2Complete = true;
			isLevel3Complete = true;
			/* Change here when level progress has been implemented */
			player.changeSpeed = false;
			player.changeBack = 20;
			player.shoot = false;
			player.doubleShoot = false;
			player.bombPic = false;
			player.particle = false;
			player.explosionPic = false;
			hud.addScore = false;
			menu.storeScoreStop = false;
			hud.stopScore = false;
			hud.stopScore2 = false;
			hud.stopScorelev3  = false;
			hud.stopScorelev4 = false;
			hud.stoptotalScore = false;
			hud.stoptotalScore2 = false;
			hud.stoptotalScore3 = false;
			hud.stoptotalScore4 = false;
			menu.stopScoreChange1 = false;
			menu.stopScoreChange2 = false;
			menu.stopScoreChange3 = false;
			menu.stopScoreChange4 = false;
			menu.stopScoreChange5 = false;
			levelDisplay.scoreTime = 300;
			levelDisplay.scoreTime2 = 300;
			levelDisplay.scoreTime3 = 300;
			levelDisplay.scoreTime3 = 300;
			levelDisplay.wizardTime= 100;
			levelDisplay.underTime = 100;
			levelDisplay.split1Time = 200;
			levelDisplay.split2Time = 200;
			levelDisplay.exploderTime = 200;
			levelDisplay.rayEnemyTime = 200;
			levelDisplay.rocketEnemyTime = 200;
			levelDisplay.dragonTime = 200;
			levelDisplay.tankTime = 200;
			levelDisplay.shieldTime = 200;
			spawner.bossMade = false;
			spawner.bossMade2 = false;
			spawner.bossMade3 = false;
			spawner.bossMade4 = false;
			spawner.shieldEnemyTime = true;
			spawner.exploderEnemyTime = true;
			spawner.wizardspawn = true;
			spawner.throwerspawn = true;
			spawner.undergroundenemyShow = true;
			spawner.rayEnemyTime = true;
			spawner.rocketEnemyTime = true;
			spawner.tankspawn = true;
			spawner.boomerangEnemyTime = true;
			upgrades.box1Row1Cost = 1000;
			upgrades.box2Row1Cost = 2000;
			RocketEnemy.bulletShow = true;
			ThrowerEnemy.giveInfo = true;
			ShieldEnemy.once = false;
			ShieldEnemy.startDamage = false;
			BoomerangEnemy.isThrow = false;
			Rocket.destroyed = false;
			Level4Boss.makeOne = false;
		}		
		else if(gameState == STATE.LEVEL1)
		{
			HUD.LEVEL = 1;
			if((level1Complete < bossFight) && !isBossFight)
			{
				if(hud.HEALTH<=0)
				{
					gameState = STATE.DEADSCREEN;
					hud.update();
					level1pause = 0;
					player.x = 385;
					level1Complete = 0;
					bossDisplay = 0;
					HUD.LEVEL1BOSSHEALTH = 200;
				}
				else
				{
					if(level1pause>=500)
					{
						player.update();
						hud.update();
						handler.update();
						spawner.update();
					}
					else 
					{
						level1pause++;
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
			if(isBossFight && bossDisplay>=500)
			{
				if(hud.HEALTH<=0)
				{
					gameState = STATE.DEADSCREEN;
					hud.update();
					level1pause = 0;
					player.x = 385;
					level1Complete = 0;
					HUD.LEVEL1BOSSHEALTH = 200;
					bossDisplay = 0;
				}
				else 
				{
					if(level1pause>=500)
					{
						player.update();
						handler.update();
						hud.update();
						spawner.update();
						if(gunStop>=100 && !isShooting)
						{
							if(player.doubleShoot)
								player.doubleShoot = false;
							else
								player.shoot = false;							
							gunStop = 0;
						}
						else gunStop++;	
					}
					else 
					{
						level1pause++;
					}
				}
				if(HUD.LEVEL1BOSSHEALTH<=0)
				{
					isLevel1Complete = true;
					HUD.LEVEL1BOSSHEALTH = 0;
					handler.clearAll();
					hud.update();
					menu.update();
					handler.update();
				}
				
			}
		}
		else if(gameState == STATE.LEVEL2)
		{
			HUD.LEVEL = 2;
			if((level2Complete<bossFight2) && !(isBossFight2))
			{
				if(HUD.HEALTH<=0)
				{
					gameState = STATE.DEADSCREEN;
					hud.update();
					handler.clearAll();
					level2pause = 0;
					isBossFight2 = false;
					player.x = 385;
					HUD.UNDERGROUNDHEALTH = 25;
					HUD.WIZARDHEALTH = 25;
					HUD.THROWERHEALTH = 50;
					HUD.SPLITHEALTH1 = 25;
					HUD.SPLITHEALTH2 = 25;
					UnderGroundEnemy.show = false;
					Spawn.undergroundenemyShow = true;
					Spawn.wizardspawn = false;
				}
				else
				{
					if(level2pause>=500)
					{
						hud.update();
						menu.update();			
						player.update();
						handler.update();
						spawner.update();
						if(gunStop>=50 && !isShooting)
						{
							if(player.doubleShoot)
								player.doubleShoot = false;
							else
								player.shoot = false;							
							gunStop = 0;
						}
						else gunStop++;	
					}
					else
					{
						hud.update();
						level2pause++;
					}
				}
				level2Complete++;
			}
			else
			{
				if(!isBossFight2)
				{
					isBossFight2 = true;
					handler.clearAll();
				}
			}
			if(isBossFight2 && bossDisplay2>=200)
			{
				if(HUD.HEALTH<=0)
				{
					gameState = STATE.DEADSCREEN;
					hud.update();
					handler.clearAll();
					level2pause = 0;
					isBossFight2 = false;
					player.x = 385;
					HUD.UNDERGROUNDHEALTH = 25;
					HUD.WIZARDHEALTH = 25;
					HUD.THROWERHEALTH = 50;
					HUD.SPLITHEALTH1 = 25;
					HUD.SPLITHEALTH2 = 25;
					UnderGroundEnemy.show = false;
					Spawn.undergroundenemyShow = true;
					Spawn.wizardspawn = false;
				}
				else
				{
					if(level2pause>=500)
					{
						handler.update();
						player.update();
						hud.update();
						spawner.update();
						if(gunStop>=100 && !isShooting)
						{
							if(player.doubleShoot)
								player.doubleShoot = false;
							else
								player.shoot = false;							
							gunStop = 0;
						}
						else gunStop++;	
					}
					else 
					{
						level2pause++;
					}
					if(HUD.LEVEL2BOSSHEALTH<=0)
					{
						isLevel2Complete = true;
						HUD.LEVEL2BOSSHEALTH = 0;
						handler.clearAll();
						hud.update();
						menu.update();
						handler.update();
					}
				}
			}
		}		
		else if(gameState == STATE.LEVEL3) 
		{
			HUD.LEVEL = 3;
			if((level3Complete<bossFight3) && !(isBossFight3))
			{
				if(HUD.HEALTH<=0)
				{
					gameState = STATE.DEADSCREEN;
					handler.clearAll();
					hud.update();
					level3pause = 0;
					isBossFight3 = false;
					level3Complete = 0;
					HUD.EXPLODERHEALTH = 20;
				}
				else
				{
					if(level3pause>=500)
					{
						hud.update();
						handler.update();
						player.update();
						spawner.update();
						if(gunStop>=50 && !isShooting)
						{
							if(player.doubleShoot)
								player.doubleShoot = false;
							else
								player.shoot = false;							
							gunStop = 0;
						}
						else gunStop++;	
					}
					else
					{
						hud.update();
						level3pause++;
					}
				}
				level3Complete++;
			}
			else
			{
				if(!isBossFight3)
				{
					isBossFight3 = true;
					handler.clearAll();
 				}
			}
			if(isBossFight3 && bossDisplay3>=200)
			{
				if(HUD.HEALTH<=0)
				{
					gameState = STATE.DEADSCREEN;
					handler.clearAll();
					hud.update();
					level3pause = 0;
					isBossFight3 = false;
					level3Complete = 0;
					HUD.EXPLODERHEALTH = 20;
				}
				else
				{
					if(level3pause>=500)
					{
						hud.update();
						handler.update();
						player.update();
						spawner.update();
						if(gunStop>=50 && !isShooting)
						{
							if(player.doubleShoot)
								player.doubleShoot = false;
							else
								player.shoot = false;
							gunStop = 0;
						}
						else gunStop++;	
					}
					else
					{
						hud.update();
						level3pause++;
					}
				}
				if(HUD.LEVEL3BOSSHEALTH<=0)
				{
					isLevel3Complete = true;
					HUD.LEVEL3BOSSHEALTH = 0;
					handler.clearAll();
					hud.update();
					menu.update();
					handler.update();
				}
			}
		}
		else if(gameState == STATE.LEVEL4) 
		{
			HUD.LEVEL = 4;
			if((level4Complete < bossFight4) && !(isBossFight4))
			{
				if(HUD.HEALTH <=0)
				{
					gameState = STATE.DEADSCREEN;
					handler.clearAll();
					hud.update();
					BoomerangEnemy.isThrow = false;
					ShieldEnemy.once = false;
					ShieldEnemy.startDamage = false;
				}
				else
				{
					if(level4pause>=500)
					{
						hud.update();
						handler.update();
						player.update();
						spawner.update();
						if(gunStop>=50 && !isShooting)
						{
							if(player.doubleShoot)
								player.doubleShoot = false;
							else
								player.shoot = false;
							gunStop = 0;
						}
						else gunStop++;	
					}
					else
					{
						hud.update();
						level4pause++;
					}
				}
				level4Complete++;
			}
			else
			{
				if(!(isBossFight4))
				{
					isBossFight4 = true;
					handler.clearAll();
				}
			}
			if(isBossFight4 && bossDisplay4>=200)
			{
				if(HUD.HEALTH<=0)
				{
					gameState = STATE.DEADSCREEN;
					handler.clearAll();
					hud.update();
					level4pause = 0;
					isBossFight4 = false;
					level4Complete = 0;
					BoomerangEnemy.isThrow = false;
					ShieldEnemy.once = false;
					ShieldEnemy.startDamage = false;
					//HUD.EXPLODERHEALTH = 20;
				}
				else
				{
					if(level4pause>=500)
					{
						hud.update();
						handler.update();
						player.update();
						spawner.update();
						if(gunStop>=50 && !isShooting)
						{
							if(player.doubleShoot)
								player.doubleShoot = false;
							else
								player.shoot = false;
							gunStop = 0;
						}
						else gunStop++;	
					}
					else
					{
						hud.update();
						level4pause++;
					}
				}
				if(HUD.LEVEL4BOSSHEALTH<=0)
				{
					isLevel4Complete = true;
					HUD.LEVEL4BOSSHEALTH = 0;
					handler.clearAll();
					hud.update();
					menu.update();
					handler.update();
				}
			}
		}
		else if(gameState == STATE.DEADSCREEN)
		{
			hud.update();
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
//			g.setColor(Color.ORANGE);
//			g.fillRect(0, 0, Game.WIDTH,Game.HEIGHT);
			g.drawImage(menuImage,0,0,WIDTH,HEIGHT,null);
			menu.render(g);
		}
		else if(gameState == STATE.SELECTLEVEL)
		{
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, Game.WIDTH,Game.HEIGHT);
			menu.render(g);
		}
		else if(gameState == STATE.CUSTOMIZE)
		{
			g.setColor(Color.PINK);
			g.fillRect(0,0, WIDTH,HEIGHT);
			menu.render(g);
		}
		else if(gameState == STATE.HELP)
		{
				g.setColor(Color.YELLOW);
				g.fillRect(0, 0,WIDTH,HEIGHT);
				menu.render(g);
		}
		else if(gameState == STATE.STORY)
		{
			g.drawImage(story,0,0,Game.WIDTH,Game.HEIGHT,null);
			menu.render(g);
		}
		else if(gameState == STATE.HOWTOPLAY)
		{
			g.setColor(Color.CYAN);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			menu.render(g);
		}  
		else if(gameState == STATE.LEVEL1)
		{
			g.drawImage(background,0,0,WIDTH,HEIGHT,null);
			player.render(g);
			if(level1pause>=500)
			{
				handler.render(g);
			}
			else 
			{
				level1pause++;
				levelDisplay.render(g);
			}
			hud.render(g);
			if(bossDisplay>=500 && isBossFight)
			{
				hud.render(g);
				handler.render(g);
			}
			else if(isBossFight)
			{
				bossDisplay++;
				levelDisplay.render(g);
			}
			if(HUD.LEVEL1BOSSHEALTH<=0)
			{
				levelDisplay.render(g);
				menu.render(g);
			}
		}
		else if(gameState == STATE.LEVEL2)
		{	
			g.drawImage(level2,0,0,null);
			player.render(g);
			if(level2pause>=500)
			{
				hud.render(g);
				handler.render(g);
				levelDisplay.render(g);
			}
			else 
			{
				levelDisplay.render(g);
				level2pause++;
				hud.render(g);
			}
			hud.render(g);
			if(bossDisplay2>=200 && isBossFight2)
			{
				hud.render(g);
				handler.render(g);
			}
			else if(isBossFight2)
			{
				bossDisplay2++;
				levelDisplay.render(g);
			}
			if(HUD.LEVEL2BOSSHEALTH<=0)
			{
				levelDisplay.render(g);
				menu.render(g);
			}
		}
		else if(gameState == STATE.LEVEL3) 
		{
			g.drawImage(level3,0,0,null);
			player.render(g);
			if(level3pause>=500)
			{
				hud.render(g);
				handler.render(g);
				levelDisplay.render(g);
			}
			else 
			{
				levelDisplay.render(g);
				level3pause++;
				hud.render(g);
			}
			hud.render(g);
			if(bossDisplay3>=200 && isBossFight3)
			{
				hud.render(g);
				handler.render(g);
			}
			else if(isBossFight3)
			{
				bossDisplay3++;
				levelDisplay.render(g);
			}
			if(HUD.LEVEL3BOSSHEALTH<=0)
			{
				levelDisplay.render(g);
				menu.render(g);
			}
		}
		else if(gameState == STATE.LEVEL4) 
		{
			g.drawImage(level4,0,0,null);
			player.render(g);
			if(level4pause>=500)
			{
				hud.render(g);
				handler.render(g);
				levelDisplay.render(g);
			}
			else 
			{
				levelDisplay.render(g);
				level4pause++;
				hud.render(g);
			}
			hud.render(g);
			if(bossDisplay4>=200 && isBossFight4)
			{
				hud.render(g);
				handler.render(g);
			}
			else if(isBossFight4)
			{
				bossDisplay4++;
				levelDisplay.render(g);
			}
			if(HUD.LEVEL4BOSSHEALTH<=0)
			{
				levelDisplay.render(g);
				menu.render(g);
			}
		}
		else if(gameState == STATE.UPGRADES)
		{
			g.setColor(menu.coral);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			upgrades.render(g);
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
			keyDown[0] = true;
			if(player.changeSpeed)
			{
				if(xVel>=0)
				{
					if(player.changeBack<=0)
					{
						player.changeBack = 20;
						player.changeSpeed = false;
					}
					else
					{
						player.changeBack--;
						player.setXVel(3);
					}		
				}
			}
			else
			{
				player.setXVel(7);
			}
		}
		if(key == KeyEvent.VK_LEFT)
		{
			keyDown[1] = true;
			if(player.changeSpeed)
			{
				if(xVel>=0)
				{
					if(player.changeBack<=0)
					{
						player.changeSpeed = false;
						player.changeBack = 20;
					}
					else
					{
						player.changeBack--;
						player.setXVel(-3);
					}
						
				}
			}
			else
			{
				player.setXVel(-7);
			}
		}
		if(key == KeyEvent.VK_SPACE && gameState == STATE.LEVEL1 && isBossFight && bulletCount < player.bucketCount)
		{
			player.shoot = true;
			handler.addObject(new WaterBullet(player.getX()+9,player.getY()-25,ID.WaterBullet,handler,texture,-15));			
			bulletCount++;
		}
		if(key == KeyEvent.VK_SPACE && !isShooting && gameState == STATE.LEVEL2 && !isBossFight2)
		{
			player.shoot = true;
			isShooting = true;
			handler.addObject(new Bullet(player.getX()+9,player.getY()-25,ID.Bullet,handler,texture,-15));

		}//shoot bullets
		if(key == KeyEvent.VK_SPACE && gameState == STATE.LEVEL2 && isBossFight2 && doubleBulletCount < player.keyCount)
		{
			player.shoot = true;
			handler.addObject(new DoubleBullet(player.getX()+9,player.getY()-25,ID.DoubleBullet,handler,texture,-12));
			doubleBulletCount++;
		}
		if(key == KeyEvent.VK_SPACE && gameState == STATE.LEVEL3 && !isShooting && !isBossFight3)
		{
			player.shoot = true;
			isShooting = true;
			handler.addObject(new DoubleBullet(player.getX()+9,player.getY()-25,ID.DoubleBullet,handler,texture,-12));
		}
		if(key == KeyEvent.VK_SPACE && gameState == STATE.LEVEL3 && isBossFight3 && explosiveBulletCount < player.eggCount)
		{
			player.doubleShoot = true;
			handler.addObject(new ExplosiveBullet(player.getX()-5,player.getY()-25,ID.ExplosiveBullet,handler,texture,-15));
			handler.addObject(new ExplosiveBullet(player.getX()+22,player.getY()-25,ID.ExplosiveBullet,handler,texture,-15));
			explosiveBulletCount++;
		}
		if(key == KeyEvent.VK_SPACE && gameState == STATE.LEVEL4 && !isShooting && !isBossFight4)
		{
			player.doubleShoot = true;
			isShooting = true;
			handler.addObject(new ExplosiveBullet(player.getX()-5,player.getY()-25,ID.ExplosiveBullet,handler,texture,-15));
			handler.addObject(new ExplosiveBullet(player.getX()+22,player.getY()-25,ID.ExplosiveBullet,handler,texture,-15));		
		}
		if(key == KeyEvent.VK_SPACE && gameState == STATE.LEVEL4 && isBossFight4 && shotgunBulletCount < player.crateCount)
		{
			player.shoot = true;
			handler.addObject(new ShotgunBullet(player.getX()+4,player.getY()-20,ID.ShotgunBullet,handler,texture,-12));
			handler.addObject(new ShotgunBullet(player.getX()+10,player.getY()-20,ID.ShotgunBullet,handler,texture,-12));
			handler.addObject(new ShotgunBullet(player.getX()+16,player.getY()-20,ID.ShotgunBullet,handler,texture,-12));
			handler.addObject(new ShotgunBullet(player.getX()+22,player.getY()-20,ID.ShotgunBullet,handler,texture,-12));
			handler.addObject(new ShotgunBullet(player.getX()+28,player.getY()-20,ID.ShotgunBullet,handler,texture,-12));
			shotgunBulletCount++;
		}
		if(key == KeyEvent.VK_SHIFT && (gameState == STATE.LEVEL1 || gameState == STATE.LEVEL2 || gameState == STATE.LEVEL3 || gameState == STATE.LEVEL4) && (level1pause>=500 || level2pause>=500 || level3pause>=500 || level4pause>=500) && (!isBossFight || !isBossFight2 || !isBossFight3 || !isBossFight4))
		{
			if(gameState == STATE.LEVEL1)
				stateholder = "LEVEL1";
			else if(gameState == STATE.LEVEL2)
				stateholder = "LEVEL2";
			else if(gameState == STATE.LEVEL3)
				stateholder = "LEVEL3";
			else if(gameState == STATE.LEVEL4)
				stateholder = "LEVEL4";
			gameState = STATE.UPGRADES;
		}
		if(key == KeyEvent.VK_SHIFT && (isBossFight || isBossFight2 || isBossFight3) && (bossDisplay>500 || bossDisplay2>200 || bossDisplay3 > 200))
		{
			if(gameState == STATE.LEVEL1)
				stateholder = "LEVEL1";
			else if(gameState == STATE.LEVEL2)
				stateholder = "LEVEL2";
			else if(gameState == STATE.LEVEL3)
				stateholder = "LEVEL3";
			else if(gameState == STATE.LEVEL4)
				stateholder = "LEVEL4";
			gameState = STATE.UPGRADES;
		}
		if(key == KeyEvent.VK_SHIFT  && !(bossDisplay<500 || bossDisplay2<200 || bossDisplay3<200) && (isBossFight || isBossFight2 || isBossFight3))
		{
			if(gameState == STATE.LEVEL1)
				stateholder = "LEVEL1";
			else if(gameState == STATE.LEVEL2)
				stateholder = "LEVEL2";
			else if(gameState == STATE.LEVEL3)
				stateholder = "LEVEL3";
			else if(gameState == STATE.LEVEL4)
				stateholder = "LEVEL4";
			gameState = STATE.UPGRADES;
		}
	}
	/* This method is the key Released method for stopping 
	 * the character and stopping continuous bullets shooting(has not yet been applied) */
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT)
		{
			keyDown[0] = false;
		}
		if(key == KeyEvent.VK_LEFT)
		{
			keyDown[1] = false;
		}
		if(key == KeyEvent.VK_SPACE && gameState == STATE.LEVEL2)
		{		
			isShooting = false;
		}//shoot bullets
		if(key == KeyEvent.VK_SPACE && gameState == STATE.LEVEL3)
		{		
			isShooting = false;
		}
//		if(key == KeyEvent.VK_SPACE && gameState == STATE.LEVEL3)
//		{		
//			isShooting = false;
//		}
		if(key == KeyEvent.VK_SPACE && gameState == STATE.LEVEL4)
		{		
			isShooting = false;
		}
		//movement(fixes the sticky key problem)
		if(!keyDown[0] && !keyDown[1])
		{
			player.setXVel(0);
		}
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
