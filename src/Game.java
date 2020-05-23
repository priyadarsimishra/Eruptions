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
	private Image level2;
	private Image level3;
	private Image level4;
	private Image gameComplete;
	private Image background;
	private Image customizeBackground;
	private ImageIcon icon;
	private ImageIcon lev2Icon;
	private ImageIcon lev3Icon;
	private ImageIcon lev4Icon;
	private ImageIcon gameCompleteIcon;
	private ImageIcon customizeBackgroundIcon;
	private Image menuImage;
	private Image upgradeImage;
	private ImageIcon menuIcon;
	private ImageIcon upgradeImageIcon;
	public BufferedImage level1Display;
	public BufferedImage level2Display;
	public BufferedImage level3Display;
	public BufferedImage level4Display;
	public Image story;
	public ImageIcon storyIcon;
	public BufferedImage level4Old;
	private Window window;
	public Player player;
	private HUD hud;
	public ObjectHandler handler;
	private SpriteTextures texture;
	private Spawn spawner;
	private Menu menu;
	private LevelDisplay levelDisplay;
	public Upgrades upgrades;
	public Customize customize;
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
	public int bossFight = 2000;
	public int bossFight2 = 2000;
	public int bossFight3 = 2000;
	public int bossFight4 = 2000;
	private boolean [] keyDown = new boolean[2];
	public boolean isBossFight = false;
	public boolean isBossFight2 = false;
	public boolean isBossFight3 = false;
	public boolean isBossFight4 = false;
	private boolean isShooting = false;
	public boolean isLevel1Complete;
	public boolean isLevel2Complete;
	public boolean isLevel3Complete;
	public boolean isLevel4Complete;
	public static final STATE STATE = null;
	public boolean setHighScore = false;
	public static String stateholder = "";
	private PlayerInfo playerInfo = null;
	public int pistolReload = 0;
	public int dualReload = 0;
	public int shotgunReload = 0;
	public int sniperReload = 0;
	boolean check = true;
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
		GAMECOMPLETE,
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
			level1Display = loader.loadImage("/Level1Display.png");
			level2Display = loader.loadImage("/Level2Display.png");
			level3Display = loader.loadImage("/Level3Display.png");
			level4Display = loader.loadImage("/Level4Display.png");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		gameCompleteIcon = new ImageIcon(this.getClass().getResource("/GameCompleteBackground.gif"));
		customizeBackgroundIcon = new ImageIcon(this.getClass().getResource("/Customize.jpg"));
		customizeBackground = customizeBackgroundIcon.getImage();
		gameComplete = gameCompleteIcon.getImage();
		icon = new ImageIcon(this.getClass().getResource("/background.gif"));
		upgradeImageIcon = new ImageIcon(this.getClass().getResource("/Upgrades.jpg"));
		storyIcon = new ImageIcon(this.getClass().getResource("/Story.gif"));
		menuIcon = new ImageIcon(this.getClass().getResource("/Menu.gif"));
		lev2Icon = new ImageIcon(this.getClass().getResource("LEVEL2.gif"));
		lev3Icon = new ImageIcon(this.getClass().getResource("LEVEL3.gif"));
		lev4Icon = new ImageIcon(this.getClass().getResource("LEVEL4.gif"));
		level2 = lev2Icon.getImage();
		level3 = lev3Icon.getImage();
		level4 = lev4Icon.getImage();
		story = storyIcon.getImage();
		upgradeImage = upgradeImageIcon.getImage();
		addKeyListener(new KeyMovement(this));
		background = icon.getImage();
		menuImage = menuIcon.getImage();
		playerInfo = new PlayerInfo();
		texture = new SpriteTextures(this);
		handler = new ObjectHandler();
		menu = new Menu(this,handler,texture,playerInfo);
		addMouseListener(menu);
		addMouseMotionListener(menu);
		upgrades = new Upgrades(handler,texture,this);
		addMouseListener(upgrades);
		addMouseMotionListener(upgrades);
		customize = new Customize(handler,texture,this);	
		addMouseListener(customize);
		addMouseMotionListener(customize);
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
			while(catchUp>=1)
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
			
//			try
//			{
//				thread.sleep(1);
//			}
//			catch(Exception e)
//			{
//				e.printStackTrace();
//			}
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
			handler.clearAll();
			menu.update();
			hud.update();
			player.x = 385;
			level1Complete = 0;
			level2Complete = 0;
			level3Complete = 0;
			level4Complete = 0;
			level1pause = 0;
			level2pause = 0;
			level3pause = 0;
			level4pause = 0;
			isBossFight = false;
			isBossFight2 = false;
			isBossFight3 = false;
			isBossFight4 = false;
			check = true;
			pistolReload = 0;
			dualReload = 0;
			shotgunReload = 0;
			sniperReload = 0;
			HUD.LEVEL1BOSSHEALTH = 200;
			HUD.LEVEL2BOSSHEALTH = 500;
			HUD.LEVEL3BOSSHEALTH = 550;
			HUD.LEVEL4BOSSHEALTH = 570;
			HUD.SCORE = 0;
			HUD.COUNT= 0;
			//HUD.LEVEL = 0;
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
			player.changeSpeed = false;
			player.changeBack = 20;
			player.pistolShoot = false;
			player.doubleShoot = false;
			player.bombPic = false;
			player.particle = false;
			player.explosionPic = false;
			hud.addScore = false;
			menu.storeScoreStop = false;
			menu.storeScoreStop2 = false;
			menu.storeScoreStop3 = false;
			menu.storeScoreStop4 = false;
			menu.storeScoreStop5 = false;
			menu.storeScoreStop6 = false;
			menu.storeScoreStop7 = false;
			menu.storeScoreStop8 = false;
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
			upgrades.box3Row1Cost = 1000;
			upgrades.box4Row1Cost = 1500;
			upgrades.isShield = false;
			upgrades.isBullet = false;
			upgrades.isSplitBullet = false;
			upgrades.isDualPistolBullet = false;
			upgrades.isShotgunBullet = false;
			upgrades.isPistol = false;
			upgrades.isShotgun = false;
			upgrades.isDualPistol = false;
			upgrades.isSniper = false;
			upgrades.isScoreBoost = false;
			Player.playerMoved = false;
			player.x = 385;
			player.y = 695;
			player.changeSpeed = false;
			RocketEnemy.bulletShow = true;
			ThrowerEnemy.giveInfo = true;
			ShieldEnemy.once = false;
			ShieldEnemy.startDamage = false;
			BoomerangEnemy.isThrow = false;
			Rocket.destroyed = false;
			Level4Boss.makeOne = false;
			Level4Boss.isAlive = false;
		}		
		else if(gameState == STATE.LEVEL1)
		{
			HUD.LEVEL = 1;
			if((level1Complete < bossFight) && !isBossFight)
			{
				if(hud.HEALTH<=0)
				{
					gameState = STATE.DEADSCREEN;
					menu.levelChange = true;
					hud.update();
					level1pause = 0;
					player.x = 385;
					level1Complete = 0;
					bossDisplay = 0;
					HUD.SHIELDHEALTH = 0;
					isBossFight = false;
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
					level1pause = 0;
					bossDisplay = 0;
					upgrades.isShield = false;
					player.shieldMade = false;
					HUD.UNDERGROUNDHEALTH = 20;
					upgrades.isPistol = false;
					upgrades.isDualPistol = false;
					upgrades.isShotgun = false;
					upgrades.isSniper = false;
					player.pistolShoot = false;
					player.doubleShoot = false;
					player.shotgunShoot = false;
					player.sniperShoot = false;
				}
			}
			if(isBossFight && bossDisplay>=500)
			{
				if(hud.HEALTH<=0)
				{
					gameState = STATE.DEADSCREEN;
					menu.levelChange = true;
					hud.update();
					level1pause = 0;
					player.x = 385;
					level1Complete = 0;
					HUD.LEVEL1BOSSHEALTH = 200;
					isBossFight = false;
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
								player.pistolShoot = false;							
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
					menu.levelChange = false;
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
					menu.levelChange = true;
					hud.update();
					handler.clearAll();
					level2pause = 0;
					isBossFight2 = false;
					player.x = 385;
					level2Complete = 0;
					bossDisplay2 = 0;
					HUD.UNDERGROUNDHEALTH = 25;
					HUD.WIZARDHEALTH = 25;
					HUD.THROWERHEALTH = 50;
					HUD.SPLITHEALTH1 = 25;
					HUD.LEVEL2BOSSHEALTH = 500;
					HUD.SHIELDHEALTH = 0;
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
						if(upgrades.isPistol)
						{
							if(pistolReload>=16)
							{
								check = true;
							}
							else
							{
								check = false;
								pistolReload++;
								HUD.PISTOLRELOAD++;
								
							}
						}
						if(upgrades.isDualPistol)
						{
							if(dualReload>=30)
							{
								check = true;
							}
							else
							{
								check = false;
								HUD.DUALRELOAD++;
								dualReload++;
							}
						}
						if(upgrades.isShotgun)
						{
							if(shotgunReload>=50)
							{
								check = true;
							}
							else
							{
								check = false;
								shotgunReload++;
								HUD.SHOTGUNRELOAD++;
							}
						}
						if(upgrades.isSniper)
						{
							if(sniperReload>=100)
							{
								check = true;
							}
							else
							{
								check = false;
								sniperReload++;
								HUD.SNIPERRELOAD++;
							}
						}
						if(gunStop>=50 && !isShooting)
						{
							if(player.doubleShoot)
								player.doubleShoot = false;
							else if(player.pistolShoot)
								player.pistolShoot = false;	
							gunStop = 0;
						}
						else gunStop++;	
						if(shotgunReload == 30 && !isShooting)
						{
							if(player.shotgunShoot)
								player.shotgunShoot = false;
							else
								player.sniperShoot = false;
						}
						if(sniperReload == 100 && !isShooting)
						{
							if(player.sniperShoot)
								player.sniperShoot = false;
						}
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
					level2pause = 0;
					bossDisplay2 = 0;
					upgrades.isShield = false;
					player.shieldMade = false;
					upgrades.isPistol = false;
					upgrades.isDualPistol = false;
					upgrades.isShotgun = false;
					upgrades.isSniper = false;
					player.pistolShoot = false;
					player.doubleShoot = false;
					player.shotgunShoot = false;
					player.sniperShoot = false;
				}
			}
			if(isBossFight2 && bossDisplay2>=200)
			{
				if(HUD.HEALTH<=0)
				{
					gameState = STATE.DEADSCREEN;
					menu.levelChange = true;
					hud.update();
					handler.clearAll();
					level2pause = 0;
					level2Complete = 0;
					isBossFight2 = false;
					player.x = 385;
					HUD.LEVEL2BOSSHEALTH = 500;
					bossDisplay2 = 0;
					HUD.UNDERGROUNDHEALTH = 25;
					HUD.WIZARDHEALTH = 25;
					HUD.THROWERHEALTH = 50;
					HUD.SPLITHEALTH1 = 25;
					HUD.SHIELDHEALTH = 0;
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
						
						if(gunStop>=50 && !isShooting)
						{
							if(player.doubleShoot)
								player.doubleShoot = false;
							else
								player.pistolShoot = false;							
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
						menu.levelChange = false;
						handler.clearAll();
						HUD.LEVEL2BOSSHEALTH = 0;
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
					menu.levelChange = true;
					handler.clearAll();
					hud.update();
					HUD.LEVEL3BOSSHEALTH = 550;
					level3pause = 0;
					isBossFight3 = false;
					bossDisplay3 = 0;
					level3Complete = 0;
					HUD.SHIELDHEALTH = 0;
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
						if(upgrades.isPistol)
						{
							if(pistolReload>=16)
							{
								check = true;
							}
							else
							{
								check = false;
								pistolReload++;
								HUD.PISTOLRELOAD++;
								
							}
						}
						if(upgrades.isDualPistol)
						{
							if(dualReload>=30)
							{
								check = true;
							}
							else
							{
								check = false;
								HUD.DUALRELOAD++;
								dualReload++;
							}
						}
						if(upgrades.isShotgun)
						{
							if(shotgunReload>=50)
							{
								check = true;
							}
							else
							{
								check = false;
								shotgunReload++;
								HUD.SHOTGUNRELOAD++;
							}
						}
						if(upgrades.isSniper)
						{
							if(sniperReload>=100)
							{
								check = true;
							}
							else
							{
								check = false;
								sniperReload++;
								HUD.SNIPERRELOAD++;
							}
						}
						if(gunStop>=50 && !isShooting)
						{
							if(player.doubleShoot)
								player.doubleShoot = false;
							else
								player.pistolShoot = false;							
							gunStop = 0;
						}
						else gunStop++;	
						if(shotgunReload == 30 && !isShooting)
						{
							if(player.shotgunShoot)
								player.shotgunShoot = false;
						}
						if(sniperReload == 100 && !isShooting)
						{
							if(player.sniperShoot)
								player.sniperShoot = false;
						}
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
					level3pause = 0;
					bossDisplay3 = 0;
					upgrades.isPistol = false;
					upgrades.isDualPistol = false;
					upgrades.isShotgun = false;
					upgrades.isSniper = false;
					upgrades.isShield = false;
					player.shieldMade = false;
					player.pistolShoot = false;
					player.doubleShoot = false;
					player.shotgunShoot = false;
					player.sniperShoot = false;
					HUD.SHIELDHEALTH = 0;
 				}
			}
			if(isBossFight3 && bossDisplay3>=200)
			{
				if(HUD.HEALTH<=0)
				{
					gameState = STATE.DEADSCREEN;
					menu.levelChange = true;
					handler.clearAll();
					hud.update();
					HUD.LEVEL3BOSSHEALTH = 550;
					HUD.SHIELDHEALTH = 0;
					level3pause = 0;
					isBossFight3 = false;
					level3Complete = 0;
					bossDisplay3 = 0;
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
								player.pistolShoot = false;
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
					menu.levelChange = false;
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
					menu.levelChange = true;
					level4Complete = 0;
					HUD.SHIELDHEALTH = 0;
					level4pause = 0;
					bossDisplay4 = 0;
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
						if(upgrades.isPistol)
						{
							if(pistolReload>=16)
							{
								check = true;
							}
							else
							{
								check = false;
								pistolReload++;
								HUD.PISTOLRELOAD++;
								
							}
						}
						if(upgrades.isDualPistol)
						{
							if(dualReload>=30)
							{
								check = true;
							}
							else
							{
								check = false;
								HUD.DUALRELOAD++;
								dualReload++;
							}
						}
						if(upgrades.isShotgun)
						{
							if(shotgunReload>=50)
							{
								check = true;
							}
							else
							{
								check = false;
								shotgunReload++;
								HUD.SHOTGUNRELOAD++;
							}
						}
						if(upgrades.isSniper)
						{
							if(sniperReload>=100)
							{
								check = true;
							}
							else
							{
								check = false;
								sniperReload++;
								HUD.SNIPERRELOAD++;
							}
						}
						if(gunStop>=50 && !isShooting)
						{
							if(player.doubleShoot)
								player.doubleShoot = false;
							else
								player.pistolShoot = false;
							gunStop = 0;
						}
						else gunStop++;	
						if(shotgunReload == 30 && !isShooting)
						{
							if(player.shotgunShoot)
								player.shotgunShoot = false;
						}
						if(sniperReload == 100 && !isShooting)
						{
							if(player.sniperShoot)
								player.sniperShoot = false;
						}
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
					level4pause = 0;
					bossDisplay4 = 0;
					upgrades.isShield = false;
					player.shieldMade = false;
					upgrades.isPistol = false;
					upgrades.isDualPistol = false;
					upgrades.isShotgun = false;
					upgrades.isSniper = false;
					player.pistolShoot = false;
					player.doubleShoot = false;
					player.shotgunShoot = false;
					player.sniperShoot = false;
				}
			}
			if(isBossFight4 && bossDisplay4>=200)
			{
				if(HUD.HEALTH<=0)
				{
					gameState = STATE.DEADSCREEN;
					menu.levelChange = true;
					handler.clearAll();
					HUD.LEVEL4BOSSHEALTH = 570;
					HUD.SHIELDHEALTH = 0;
					hud.update();
					level4pause = 0;
					isBossFight4 = false;
					level4Complete = 0;
					bossDisplay4 = 0;
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
								player.shotgunShoot = false;
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
					menu.levelChange = false;
					HUD.LEVEL4BOSSHEALTH = 0;
					handler.clearAll();
					hud.update();
					menu.update();
					handler.update();
				}
			}
		}
		else if(gameState == STATE.GAMECOMPLETE)
		{
			player.update();
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
			g.drawImage(customizeBackground,0,0,WIDTH,HEIGHT,null);
			menu.render(g);
			customize.render(g);
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
			g.drawImage(level2,0,0,WIDTH,HEIGHT,null);
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
			g.drawImage(level3,0,0,WIDTH,HEIGHT,null);
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
			g.drawImage(level4,0,0,WIDTH,HEIGHT,null);
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
			g.drawImage(upgradeImage,0,0,WIDTH,HEIGHT,null);
			upgrades.render(g);
		}
		else if(gameState == STATE.GAMECOMPLETE)
		{
			g.drawImage(gameComplete,0,0,WIDTH,HEIGHT,null);
			player.render(g);
			levelDisplay.render(g);
			menu.render(g);
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
			player.pistolShoot = true;
			handler.addObject(new WaterBullet(player.getX()+9,player.getY()-25,ID.WaterBullet,handler,texture,-15));			
			bulletCount++;
		}
		if(key == KeyEvent.VK_SPACE && !isShooting && gameState == STATE.LEVEL2 && !isBossFight2 && check)
		{
			//testing for shop here
			isShooting = true;
			if(upgrades.isShotgun) 
			{
				player.shotgunShoot = true;
				player.doubleShoot = false;
				player.sniperShoot = false;
				player.pistolShoot = false;
			}
			else if(upgrades.isDualPistol)
			{
				player.shotgunShoot = false;
				player.doubleShoot = true;
				player.sniperShoot = false;
				player.pistolShoot = false;
			}
			else if(upgrades.isSniper)
			{
				player.shotgunShoot = false;
				player.doubleShoot = false;
				player.sniperShoot = true;
				player.pistolShoot = false;
			}
			else
			{
				upgrades.isPistol = true;
				upgrades.isBullet = true;
				player.shotgunShoot = false;
				player.doubleShoot = false;
				player.sniperShoot = false;
				player.pistolShoot = true;
			}
			//bullets
			/* ******** pistol bullets ******** */
			if(upgrades.isDualPistolBullet && player.pistolShoot) 
			{
				handler.addObject(new ExplosiveBullet(player.getX()+9,player.getY()-25,ID.ExplosiveBullet,handler,texture,-15,this));
			}
			else if(upgrades.isSplitBullet && player.pistolShoot) 
			{
				handler.addObject(new DoubleBullet(player.getX()+9,player.getY()-25,ID.DoubleBullet,handler,texture,-12,this));
			}
			else if(upgrades.isShotgunBullet && player.pistolShoot) 
			{
				handler.addObject(new ShotgunBullet(player.getX()+9,player.getY()-25,ID.ShotgunBullet,handler,texture,-12,this));
			}
			else if(player.pistolShoot)
			{
				handler.addObject(new Bullet(player.getX()+9,player.getY()-25,ID.Bullet,handler,texture,-15,this));
			}
			/* ******** dual pistols bullets ******* */
			if(upgrades.isDualPistolBullet && player.doubleShoot) 
			{
				handler.addObject(new ExplosiveBullet(player.getX()-5,player.getY()-25,ID.ExplosiveBullet,handler,texture,-15,this));
				handler.addObject(new ExplosiveBullet(player.getX()+22,player.getY()-25,ID.ExplosiveBullet,handler,texture,-15,this));
			}
			else if(upgrades.isSplitBullet && player.doubleShoot) 
			{
				handler.addObject(new DoubleBullet(player.getX()-5,player.getY()-25,ID.DoubleBullet,handler,texture,-12,this));
				handler.addObject(new DoubleBullet(player.getX()+22,player.getY()-25,ID.DoubleBullet,handler,texture,-12,this));
			}
			else if(upgrades.isShotgunBullet && player.doubleShoot) 
			{
				//edit make 5 each side
				handler.addObject(new ShotgunBullet(player.getX()-5,player.getY()-25,ID.ShotgunBullet,handler,texture,-15,this));
				handler.addObject(new ShotgunBullet(player.getX()+22,player.getY()-25,ID.ShotgunBullet,handler,texture,-15,this));

			}
			else if(player.doubleShoot)
			{
				handler.addObject(new Bullet(player.getX()-5,player.getY()-25,ID.Bullet,handler,texture,-15,this));
				handler.addObject(new Bullet(player.getX()+22,player.getY()-25,ID.Bullet,handler,texture,-15,this));
			}
			/* ******** shotgun bullets ******** */
			if(upgrades.isDualPistolBullet && player.shotgunShoot) 
			{
				//edit this after class
				handler.addObject(new ExplosiveBullet(player.getX()+4,player.getY()-25,ID.ExplosiveBullet,handler,texture,-24, this));
				handler.addObject(new ExplosiveBullet(player.getX()+10,player.getY()-25,ID.ExplosiveBullet,handler,texture,-24,this));
				handler.addObject(new ExplosiveBullet(player.getX()+16,player.getY()-25,ID.ExplosiveBullet,handler,texture,-24,this));
				handler.addObject(new ExplosiveBullet(player.getX()+22,player.getY()-25,ID.ExplosiveBullet,handler,texture,-24,this));
				handler.addObject(new ExplosiveBullet(player.getX()+28,player.getY()-25,ID.ExplosiveBullet,handler,texture,-24,this));
			}
			else if(upgrades.isSplitBullet && player.shotgunShoot) 
			{
				handler.addObject(new DoubleBullet(player.getX()+8,player.getY()-25,ID.DoubleBullet,handler,texture,-24,this));
				handler.addObject(new DoubleBullet(player.getX()+16,player.getY()-25,ID.DoubleBullet,handler,texture,-24,this));
				handler.addObject(new DoubleBullet(player.getX()+24,player.getY()-25,ID.DoubleBullet,handler,texture,-24,this));
			}
			else if(upgrades.isShotgunBullet && player.shotgunShoot) 
			{
				handler.addObject(new ShotgunBullet(player.getX()+4,player.getY()-20,ID.ShotgunBullet,handler,texture,-24,this));
				handler.addObject(new ShotgunBullet(player.getX()+10,player.getY()-20,ID.ShotgunBullet,handler,texture,-24,this));
				handler.addObject(new ShotgunBullet(player.getX()+16,player.getY()-20,ID.ShotgunBullet,handler,texture,-24,this));
				handler.addObject(new ShotgunBullet(player.getX()+22,player.getY()-20,ID.ShotgunBullet,handler,texture,-24,this));
				handler.addObject(new ShotgunBullet(player.getX()+28,player.getY()-20,ID.ShotgunBullet,handler,texture,-24,this));
			}
			else if(player.shotgunShoot)
			{
				handler.addObject(new Bullet(player.getX()+4,player.getY()-25,ID.Bullet,handler,texture,-24,this));
				handler.addObject(new Bullet(player.getX()+10,player.getY()-25,ID.Bullet,handler,texture,-24,this));
				handler.addObject(new Bullet(player.getX()+16,player.getY()-25,ID.Bullet,handler,texture,-24,this));
				handler.addObject(new Bullet(player.getX()+22,player.getY()-25,ID.Bullet,handler,texture,-24,this));
				handler.addObject(new Bullet(player.getX()+28,player.getY()-25,ID.Bullet,handler,texture,-24,this));
			}
			/* ******** sniper bullets ******** */
			if(upgrades.isDualPistolBullet && player.sniperShoot) 
			{
				handler.addObject(new ExplosiveBullet(player.getX()+9,player.getY()-25,ID.ExplosiveBullet,handler,texture,-20,this));
			}
			else if(upgrades.isSplitBullet && player.sniperShoot) 
			{
				handler.addObject(new DoubleBullet(player.getX()+9,player.getY()-25,ID.DoubleBullet,handler,texture,-20,this));
			}
			else if(upgrades.isShotgunBullet && player.sniperShoot) 
			{
				handler.addObject(new ShotgunBullet(player.getX()+9,player.getY()-20,ID.ShotgunBullet,handler,texture,-20,this));
			}
			else if(player.sniperShoot)
			{
				handler.addObject(new Bullet(player.getX()+9,player.getY()-25,ID.Bullet,handler,texture,-20,this));
			}
			//Reload
			if(upgrades.isPistol)
			{
				pistolReload = 0;
				HUD.PISTOLRELOAD = 0;
			}
			else if(upgrades.isDualPistol)
			{
				dualReload = 0;
				HUD.DUALRELOAD = 0;
			}
			else if(upgrades.isShotgun)
			{
				shotgunReload = 0;
				HUD.SHOTGUNRELOAD = 0;
			}
			else if(upgrades.isSniper)
			{
				sniperReload = 0;
				HUD.SNIPERRELOAD = 0;
			}
		}
		if(key == KeyEvent.VK_SPACE && gameState == STATE.LEVEL2 && isBossFight2 && doubleBulletCount < player.keyCount)
		{
			upgrades.isBullet = false;
			upgrades.isDualPistolBullet = false;
			upgrades.isShotgunBullet = false;
			upgrades.isSplitBullet = false;
			player.pistolShoot =false;
			player.shotgunShoot = false;
			player.sniperShoot = false;
			player.doubleShoot = false;
			player.pistolShoot = true;
			handler.addObject(new DoubleBullet(player.getX()+9,player.getY()-25,ID.DoubleBullet,handler,texture,-12,this));
			doubleBulletCount++;
		}
		if(key == KeyEvent.VK_SPACE && gameState == STATE.LEVEL3 && !isShooting && !isBossFight3 && check)
		{
			isShooting = true;
			if(upgrades.isShotgun) 
			{
				player.shotgunShoot = true;
				player.doubleShoot = false;
				player.sniperShoot = false;
				player.pistolShoot = false;
			}
			else if(upgrades.isDualPistol)
			{
				player.shotgunShoot = false;
				player.doubleShoot = true;
				player.sniperShoot = false;
				player.pistolShoot = false;
			}
			else if(upgrades.isSniper)
			{
				player.shotgunShoot = false;
				player.doubleShoot = false;
				player.sniperShoot = true;
				player.pistolShoot = false;
			}
			else
			{
				upgrades.isPistol = true;
				upgrades.isSplitBullet = true;
				player.shotgunShoot = false;
				player.doubleShoot = false;
				player.sniperShoot = false;
				player.pistolShoot = true;
			}
			//bullet
			/* ******** Pistol bullets ******** */
			if(upgrades.isDualPistolBullet && player.pistolShoot) 
			{
				handler.addObject(new ExplosiveBullet(player.getX()+9,player.getY()-25,ID.ExplosiveBullet,handler,texture,-15,this));
			}
			else if(upgrades.isBullet && player.pistolShoot) 
			{
				handler.addObject(new Bullet(player.getX()+9,player.getY()-25,ID.Bullet,handler,texture,-15,this));
			}
			else if(upgrades.isShotgunBullet && player.pistolShoot) 
			{
				handler.addObject(new ShotgunBullet(player.getX()+9,player.getY()-25,ID.ShotgunBullet,handler,texture,-12,this));
			}
			else if(player.pistolShoot)
			{
				handler.addObject(new DoubleBullet(player.getX()+9,player.getY()-25,ID.DoubleBullet,handler,texture,-12,this));
			}
			/* ******** DualPistol Bullets ******** */
			if(upgrades.isDualPistolBullet && player.doubleShoot) 
			{
				handler.addObject(new ExplosiveBullet(player.getX()-5,player.getY()-25,ID.ExplosiveBullet,handler,texture,-15,this));
				handler.addObject(new ExplosiveBullet(player.getX()+22,player.getY()-25,ID.ExplosiveBullet,handler,texture,-15,this));
			}
			else if(upgrades.isSplitBullet && player.doubleShoot) 
			{
				handler.addObject(new DoubleBullet(player.getX()-2,player.getY()-25,ID.DoubleBullet,handler,texture,-12,this));
				handler.addObject(new DoubleBullet(player.getX()+26,player.getY()-25,ID.DoubleBullet,handler,texture,-12,this));
			}
			else if(upgrades.isShotgunBullet && player.doubleShoot) 
			{
				//edit make 5 each side
				handler.addObject(new ShotgunBullet(player.getX()-5,player.getY()-25,ID.ShotgunBullet,handler,texture,-15,this));
				handler.addObject(new ShotgunBullet(player.getX()+22,player.getY()-25,ID.ShotgunBullet,handler,texture,-15,this));
			}
			else if(player.doubleShoot)
			{
				handler.addObject(new Bullet(player.getX()-5,player.getY()-25,ID.Bullet,handler,texture,-15,this));
				handler.addObject(new Bullet(player.getX()+22,player.getY()-25,ID.Bullet,handler,texture,-15,this));
			}
			/* ******** shotgun bullets ******** */
			if(upgrades.isDualPistolBullet && player.shotgunShoot) 
			{
				//edit this after class
				handler.addObject(new ExplosiveBullet(player.getX()+4,player.getY()-25,ID.ExplosiveBullet,handler,texture,-24,this));
				handler.addObject(new ExplosiveBullet(player.getX()+10,player.getY()-25,ID.ExplosiveBullet,handler,texture,-24,this));
				handler.addObject(new ExplosiveBullet(player.getX()+16,player.getY()-25,ID.ExplosiveBullet,handler,texture,-24,this));
				handler.addObject(new ExplosiveBullet(player.getX()+22,player.getY()-25,ID.ExplosiveBullet,handler,texture,-24,this));
				handler.addObject(new ExplosiveBullet(player.getX()+28,player.getY()-25,ID.ExplosiveBullet,handler,texture,-24,this));
			}
			else if(upgrades.isSplitBullet && player.shotgunShoot) 
			{
				handler.addObject(new DoubleBullet(player.getX()+8,player.getY()-25,ID.DoubleBullet,handler,texture,-24,this));
				handler.addObject(new DoubleBullet(player.getX()+16,player.getY()-25,ID.DoubleBullet,handler,texture,-24,this));
				handler.addObject(new DoubleBullet(player.getX()+24,player.getY()-25,ID.DoubleBullet,handler,texture,-24,this));
			}
			else if(upgrades.isShotgunBullet && player.shotgunShoot) 
			{
				handler.addObject(new ShotgunBullet(player.getX()+4,player.getY()-20,ID.ShotgunBullet,handler,texture,-24,this));
				handler.addObject(new ShotgunBullet(player.getX()+10,player.getY()-20,ID.ShotgunBullet,handler,texture,-24,this));
				handler.addObject(new ShotgunBullet(player.getX()+16,player.getY()-20,ID.ShotgunBullet,handler,texture,-24,this));
				handler.addObject(new ShotgunBullet(player.getX()+22,player.getY()-20,ID.ShotgunBullet,handler,texture,-24,this));
				handler.addObject(new ShotgunBullet(player.getX()+28,player.getY()-20,ID.ShotgunBullet,handler,texture,-24,this));
			}
			else if(player.shotgunShoot)
			{
				handler.addObject(new Bullet(player.getX()+4,player.getY()-25,ID.Bullet,handler,texture,-24,this));
				handler.addObject(new Bullet(player.getX()+10,player.getY()-25,ID.Bullet,handler,texture,-24,this));
				handler.addObject(new Bullet(player.getX()+16,player.getY()-25,ID.Bullet,handler,texture,-24,this));
				handler.addObject(new Bullet(player.getX()+22,player.getY()-25,ID.Bullet,handler,texture,-24,this));
				handler.addObject(new Bullet(player.getX()+28,player.getY()-25,ID.Bullet,handler,texture,-24,this));
			}
			/* ******** sniper bullets ******** */
			if(upgrades.isDualPistolBullet && player.sniperShoot) 
			{
				handler.addObject(new ExplosiveBullet(player.getX()+9,player.getY()-25,ID.ExplosiveBullet,handler,texture,20,this));
			}
			else if(upgrades.isSplitBullet && player.sniperShoot) 
			{
				handler.addObject(new DoubleBullet(player.getX()+9,player.getY()-25,ID.DoubleBullet,handler,texture,-20,this));
			}
			else if(upgrades.isShotgunBullet && player.sniperShoot) 
			{
				handler.addObject(new ShotgunBullet(player.getX()+9,player.getY()-20,ID.ShotgunBullet,handler,texture,-20,this));
			}
			else if(player.sniperShoot)
			{
				handler.addObject(new Bullet(player.getX()+9,player.getY()-25,ID.Bullet,handler,texture,-20,this));
			}
			//Reload
			if(upgrades.isPistol)
			{
				pistolReload = 0;
				HUD.PISTOLRELOAD = 0;
			}
			else if(upgrades.isDualPistol)
			{
				dualReload = 0;
				HUD.DUALRELOAD = 0;
			}
			else if(upgrades.isShotgun)
			{
				shotgunReload = 0;
				HUD.SHOTGUNRELOAD = 0;
			}
			else if(upgrades.isSniper)
			{
				sniperReload = 0;
				HUD.SNIPERRELOAD = 0;
			}
		}
		if(key == KeyEvent.VK_SPACE && gameState == STATE.LEVEL3 && isBossFight3 && explosiveBulletCount < player.eggCount)
		{
			player.doubleShoot = true;
			handler.addObject(new ExplosiveBullet(player.getX()-5,player.getY()-25,ID.ExplosiveBullet,handler,texture,-15,this));
			handler.addObject(new ExplosiveBullet(player.getX()+22,player.getY()-25,ID.ExplosiveBullet,handler,texture,-15,this));
			explosiveBulletCount++;
		}
		if(key == KeyEvent.VK_SPACE && gameState == STATE.LEVEL4 && !isShooting && !isBossFight4 && check)
		{
			isShooting = true;
			if(upgrades.isShotgun) 
			{
				player.shotgunShoot = true;
				player.doubleShoot = false;
				player.sniperShoot = false;
				player.pistolShoot = false;
			}
			else if(upgrades.isPistol)
			{
				player.shotgunShoot = false;
				player.doubleShoot = false;
				player.sniperShoot = false;
				player.pistolShoot = true;
			}
			else if(upgrades.isSniper)
			{
				player.shotgunShoot = false;
				player.doubleShoot = false;
				player.sniperShoot = true;
				player.pistolShoot = false;
			}
			else
			{
				upgrades.isDualPistol = true;
				upgrades.isDualPistolBullet = true;
				player.shotgunShoot = false;
				player.doubleShoot = true;
				player.sniperShoot = false;
				player.pistolShoot = false;
			}
			//bullet
			/* ******** Pistol bullets ******** */
			if(upgrades.isBullet && player.pistolShoot) 
			{
				handler.addObject(new Bullet(player.getX()+9,player.getY()-25,ID.Bullet,handler,texture,-15,this));
			}
			else if(upgrades.isSplitBullet && player.pistolShoot) 
			{
				handler.addObject(new DoubleBullet(player.getX()+9,player.getY()-25,ID.DoubleBullet,handler,texture,-12,this));
			}
			else if(upgrades.isShotgunBullet && player.pistolShoot) 
			{
				handler.addObject(new ShotgunBullet(player.getX()+9,player.getY()-25,ID.ShotgunBullet,handler,texture,-12,this));
			}
			else if(player.pistolShoot)
			{
				handler.addObject(new ExplosiveBullet(player.getX()+9,player.getY()-25,ID.ExplosiveBullet,handler,texture,-15,this));
			}
			/* ******** DualPistol Bullets ******** */
			if(upgrades.isBullet && player.doubleShoot) 
			{ 
				handler.addObject(new Bullet(player.getX()-5,player.getY()-25,ID.Bullet,handler,texture,-15,this));
				handler.addObject(new Bullet(player.getX()+22,player.getY()-25,ID.Bullet,handler,texture,-15,this));
			}
			else if(upgrades.isSplitBullet && player.doubleShoot) 
			{
				handler.addObject(new DoubleBullet(player.getX()-2,player.getY()-25,ID.DoubleBullet,handler,texture,-12,this));
				handler.addObject(new DoubleBullet(player.getX()+26,player.getY()-25,ID.DoubleBullet,handler,texture,-12,this));
			}
			else if(upgrades.isShotgunBullet && player.doubleShoot) 
			{
				//edit make 5 each side
				handler.addObject(new ShotgunBullet(player.getX()-5,player.getY()-25,ID.ShotgunBullet,handler,texture,-15,this));
				handler.addObject(new ShotgunBullet(player.getX()+22,player.getY()-25,ID.ShotgunBullet,handler,texture,-15,this));
			}
			else if(player.doubleShoot)
			{
				handler.addObject(new ExplosiveBullet(player.getX()-5,player.getY()-25,ID.ExplosiveBullet,handler,texture,-15,this));
				handler.addObject(new ExplosiveBullet(player.getX()+22,player.getY()-25,ID.ExplosiveBullet,handler,texture,-15,this));
			}
			/* ******** shotgun bullets ******** */
			if(upgrades.isBullet && player.shotgunShoot) 
			{
				//edit this after class
				handler.addObject(new Bullet(player.getX()+4,player.getY()-25,ID.Bullet,handler,texture,-24,this));
				handler.addObject(new Bullet(player.getX()+10,player.getY()-25,ID.Bullet,handler,texture,-24,this));
				handler.addObject(new Bullet(player.getX()+16,player.getY()-25,ID.Bullet,handler,texture,-24,this));
				handler.addObject(new Bullet(player.getX()+22,player.getY()-25,ID.Bullet,handler,texture,-24,this));
				handler.addObject(new Bullet(player.getX()+28,player.getY()-25,ID.Bullet,handler,texture,-24,this));
			}
			else if(upgrades.isSplitBullet && player.shotgunShoot) 
			{
				handler.addObject(new DoubleBullet(player.getX()+8,player.getY()-25,ID.DoubleBullet,handler,texture,-24,this));
				handler.addObject(new DoubleBullet(player.getX()+16,player.getY()-25,ID.DoubleBullet,handler,texture,-24,this));
				handler.addObject(new DoubleBullet(player.getX()+24,player.getY()-25,ID.DoubleBullet,handler,texture,-24,this));
			}
			else if(upgrades.isShotgunBullet && player.shotgunShoot) 
			{
				handler.addObject(new ShotgunBullet(player.getX()+4,player.getY()-20,ID.ShotgunBullet,handler,texture,-24,this));
				handler.addObject(new ShotgunBullet(player.getX()+10,player.getY()-20,ID.ShotgunBullet,handler,texture,-24,this));
				handler.addObject(new ShotgunBullet(player.getX()+16,player.getY()-20,ID.ShotgunBullet,handler,texture,-24,this));
				handler.addObject(new ShotgunBullet(player.getX()+22,player.getY()-20,ID.ShotgunBullet,handler,texture,-24,this));
				handler.addObject(new ShotgunBullet(player.getX()+28,player.getY()-20,ID.ShotgunBullet,handler,texture,-24,this));
			}
			else if(player.shotgunShoot)
			{
				handler.addObject(new ExplosiveBullet(player.getX()+4,player.getY()-25,ID.ExplosiveBullet,handler,texture,-24,this));
				handler.addObject(new ExplosiveBullet(player.getX()+10,player.getY()-25,ID.ExplosiveBullet,handler,texture,-24,this));
				handler.addObject(new ExplosiveBullet(player.getX()+16,player.getY()-25,ID.ExplosiveBullet,handler,texture,-24,this));
				handler.addObject(new ExplosiveBullet(player.getX()+22,player.getY()-25,ID.ExplosiveBullet,handler,texture,-24,this));
				handler.addObject(new ExplosiveBullet(player.getX()+28,player.getY()-25,ID.ExplosiveBullet,handler,texture,-24,this));
			}
			/* ******** sniper bullets ******** */
			if(upgrades.isBullet && player.sniperShoot) 
			{
				handler.addObject(new Bullet(player.getX()+9,player.getY()-25,ID.Bullet,handler,texture,-20,this));
			}
			else if(upgrades.isSplitBullet && player.sniperShoot) 
			{
				handler.addObject(new DoubleBullet(player.getX()+9,player.getY()-25,ID.DoubleBullet,handler,texture,-20,this));
			}
			else if(upgrades.isShotgunBullet && player.sniperShoot) 
			{
				handler.addObject(new ShotgunBullet(player.getX()+9,player.getY()-20,ID.ShotgunBullet,handler,texture,-20,this));
			}
			else if(player.sniperShoot)
			{
				handler.addObject(new ExplosiveBullet(player.getX()+9,player.getY()-25,ID.ExplosiveBullet,handler,texture,-20,this));
			}
			//Reload
			if(upgrades.isPistol)
			{
				pistolReload = 0;
				HUD.PISTOLRELOAD = 0;
			}
			else if(upgrades.isDualPistol)
			{
				dualReload = 0;
				HUD.DUALRELOAD = 0;
			}
			else if(upgrades.isShotgun)
			{
				shotgunReload = 0;
				HUD.SHOTGUNRELOAD = 0;
			}
			else if(upgrades.isSniper)
			{
				sniperReload = 0;
				HUD.SNIPERRELOAD = 0;
			}
		}
		if(key == KeyEvent.VK_SPACE && gameState == STATE.LEVEL4 && isBossFight4 && shotgunBulletCount < player.crateCount)
		{
			player.shotgunShoot = true;
			handler.addObject(new ShotgunBullet(player.getX()+4,player.getY()-20,ID.ShotgunBullet,handler,texture,-12,this));
			handler.addObject(new ShotgunBullet(player.getX()+10,player.getY()-20,ID.ShotgunBullet,handler,texture,-12,this));
			handler.addObject(new ShotgunBullet(player.getX()+16,player.getY()-20,ID.ShotgunBullet,handler,texture,-12,this));
			handler.addObject(new ShotgunBullet(player.getX()+22,player.getY()-20,ID.ShotgunBullet,handler,texture,-12,this));
			handler.addObject(new ShotgunBullet(player.getX()+28,player.getY()-20,ID.ShotgunBullet,handler,texture,-12,this));
			shotgunBulletCount++;
		}
		if(key == KeyEvent.VK_SHIFT && (gameState == STATE.LEVEL1 || gameState == STATE.LEVEL2 || gameState == STATE.LEVEL3 || gameState == STATE.LEVEL4) && (level1pause>=500 || level2pause>=500 || level3pause>=500 || level4pause>=500) && (!isBossFight || !isBossFight2 || !isBossFight3 || !isBossFight4) && !(HUD.LEVEL1BOSSHEALTH<=0 || HUD.LEVEL2BOSSHEALTH<=0 || HUD.LEVEL3BOSSHEALTH<=0 || HUD.LEVEL4BOSSHEALTH<=0))
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
//			reload = 10;
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
	}
}