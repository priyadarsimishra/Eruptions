package game.eruption;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
/* This class is responsible for displaying the Menu 
 * and it handles the Death Screen and it also 
 * has a MouseAdapter to add mouse input for mouse input */
public class Menu extends MouseAdapter
{
	private Game game;
	private ObjectHandler handler;
	private Spawn spawner;
	private SpriteTextures texture;
	public Color purple = new Color(139,0,139);
	public Color skyBlue = new Color(0,191,255);
	public Color lime = new Color(50,205,50);
	public Color coral = new Color(255,127,80);
	public Color deepPink = new Color(255,20,147);
	public Color springGreen = new Color(0,255,127);
	public Color violet = new Color(238,130,238);
	public Color lavender = new Color(230,230,250);
	public boolean customizeColor = false;
	public boolean playColor = false;
	public boolean helpColor = false;
	public boolean exitColor = false;
	public boolean helpColorBACK = false;
	public boolean backButtonLevelSelect = false;
	public boolean backButtonCustomize = false;
	public boolean deathScreenButton = false;
	public boolean storyButton = false;
	public boolean storyButtonBACK = false;
	public boolean storyButtonMENU = false;
	public boolean howtoPlayButton = false;
	public boolean howtoPlayButtonBACK = false;
	public boolean howtoPlayButtonMENU = false;
	public boolean menuButtonLevel1End = false;
	public boolean menuButtonLevel2End = false;
	public boolean menuButtonLevel3End = false;
	public boolean menuButtonLevel4End = false;
	public boolean nextLevelLevel1End = false;
	public boolean nextLevelLevel2End = false;
	public boolean nextLevelLevel3End = false;
	public boolean gameCompleteLevel4End = false;
	public boolean gameCompleteMenuButton = false;
	public boolean stopScoreChange0 = false;
	public boolean stopScoreChange1 = false;
	public boolean stopScoreChange2 = false;	
	public boolean stopScoreChange3 = false;
	public boolean stopScoreChange4 = false;
	public boolean stopScoreChange5 = false;
	public boolean stopScoreChange6 = false;
	public boolean stopScoreChange7 = false;
	public boolean stopScoreChange8 = false;
	public boolean level1highlight = false;
	public boolean level2highlight = false;
	public boolean level3highlight = false;
	public boolean level4highlight = false;
	public boolean levelChange;
	private ImageIcon icon2 = new ImageIcon(getClass().getResource("/CoinLogo.png"));
	private Image image = icon2.getImage();
	private ImageIcon icon3 = new ImageIcon(getClass().getResource("/CoinBoostTime.png"));
	private Image image3 = icon3.getImage();
	private ImageIcon icon4 = new ImageIcon(getClass().getResource("/GameDisplay.png"));
	private Image image4 = icon4.getImage();
	private ImageIcon icon5 = new ImageIcon(getClass().getResource("/ShieldHealth.png"));
	private Image image5 = icon5.getImage();
	private ImageIcon icon6 = new ImageIcon(getClass().getResource("/blood.gif"));
	private Image image6 = icon6.getImage();
	private PlayerInfo playerInfo;
	FileUtils fileUtils = new FileUtils();
	public boolean storeScoreStop = false;
	public boolean storeScoreStop2 = false;
	public boolean storeScoreStop3 = false;
	public boolean storeScoreStop4 = false;
	public boolean storeScoreStop5 = false;
	public boolean storeScoreStop6 = false;
	public boolean storeScoreStop7 = false;
	public boolean storeScoreStop8 = false;
	
	
	
	public boolean gameCompleteStoreStop = false;

	private Image questionDisplay;
	private ImageIcon icon;
	/* This constructor has a game instance and handler instance
	 * for this class so we can check the game State or
	 * to clear all the objects in the handler and 
	 * so we can display images */
	public Menu(Game game, ObjectHandler handler,SpriteTextures texture,PlayerInfo playerInfo)
	{
		this.game = game;
		this.handler = handler;
		this.texture = texture;
		this.playerInfo = playerInfo;
	}
	/* This method is used to update but is not in use yet */
	public void update(){}
	/* his is the mousePressed method that checks for "buttons"(rectangle) 
	 * and changes the game state accordingly and it also clears the objects
	 * or exits if you press the exit button */
	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		if(game.gameState == game.STATE.MENU)
			SoundPlayer.getSound("mouse_clicked").play();
		if(game.gameState == game.STATE.CUSTOMIZE)
			SoundPlayer.getSound("mouse_clicked").play();
		if(game.gameState == game.STATE.UPGRADES)
			SoundPlayer.getSound("mouse_clicked").play();
		if(contains(mx,my,Game.WIDTH/2-60,Game.HEIGHT/2,101,51) && game.gameState == game.STATE.NAMEPANEL)
		{
			// OK BUTTON
			SoundPlayer.getSound("mouse_clicked").play();
			game.gameState = game.STATE.MENU;
		}
		else if(contains(mx,my,game.WIDTH/2-115,game.HEIGHT/2-60,200,100) && game.gameState == game.STATE.MENU)
		{
			//CUSTOMIZE BUTTON
			SoundPlayer.getSound("mouse_clicked").play();
			game.gameState = game.STATE.CUSTOMIZE;
		}
		else if(contains(mx,my,5,700,250,75) && game.gameState == game.STATE.CUSTOMIZE)
		{
			//BACK BUTTON IN CUSTOMIZE
			SoundPlayer.getSound("mouse_clicked").play();
			game.gameState = game.STATE.MENU;
		}
		else if(contains(mx, my,game.WIDTH/2-115,game.HEIGHT/2-188,200,100) && game.gameState == game.STATE.MENU)
		{
			// PLAY BUTTON
			SoundPlayer.getSound("mouse_clicked").play();
			game.gameState = game.STATE.SELECTLEVEL;
		}
		else if(contains(mx,my,game.WIDTH/2-115,game.HEIGHT/2+70,200,100) && game.gameState == game.STATE.MENU)
		{
			//HELP BUTTON
			SoundPlayer.getSound("mouse_clicked").play();
			game.gameState = game.STATE.HELP;
		}
		else if(contains(mx,my,game.WIDTH/2-120,game.HEIGHT/2+250,250,100) && game.gameState == game.STATE.HELP)
		{
			// BACK BUTTON in HELP
			SoundPlayer.getSound("mouse_clicked").play();
			game.gameState = game.STATE.MENU;
		}
		else if(contains(mx,my,game.WIDTH/2-120,game.HEIGHT/2+230,250,100) && game.gameState == game.STATE.SELECTLEVEL)
		{
			// BACK BUTTON IN LEVEL CHOOSE
			SoundPlayer.getSound("mouse_clicked").play();
			game.gameState = game.STATE.MENU;
		}
		else if(contains(mx,my,game.WIDTH/2-115,game.HEIGHT/2+200,200,100) && game.gameState == game.STATE.MENU)
		{
			// EXIT BUTTON
			SoundPlayer.getSound("mouse_clicked").play();
			System.exit(1);
			
		}
		else if(contains(mx,my,50,115,261,261) && game.gameState == game.STATE.SELECTLEVEL)
		{
			//LEVEL 1
			SoundPlayer.getSound("mouse_clicked").play();
			game.gameState = game.STATE.LEVEL1;
			HUD.HIGHSCORE = 0;
			storeScoreStop = false;
			storeScoreStop2 = false;
			storeScoreStop3 = false;
			storeScoreStop4 = false;
			storeScoreStop5 = false;
			storeScoreStop6 = false;
			storeScoreStop7 = false;
			storeScoreStop8 = false;
			HUD.SHIELDHEALTH = 0;
			game.upgrades.box1Row1Cost = 1000;
			game.upgrades.box2Row1Cost = 2000;
			game.upgrades.box3Row1Cost = 1000;
			game.upgrades.box4Row1Cost = 1500;
			game.upgrades.isBullet = false;
			game.upgrades.isSplitBullet = false;
			game.upgrades.isScoreBoost = false;
			game.upgrades.isDualPistolBullet = false;
			game.upgrades.isShotgunBullet = false;
			game.upgrades.isShield = false;
			game.player.pistolShoot = false;
			game.player.doubleShoot = false;
			game.player.shotgunShoot = false;
			game.player.sniperShoot = false;
		}
		else if(contains(mx,my,490,115,261,261) && game.isLevel1Complete && game.gameState == game.STATE.SELECTLEVEL)
		{
			// LEVEL 2
			SoundPlayer.getSound("mouse_clicked").play();
			game.gameState = game.STATE.LEVEL2;
			storeScoreStop = false;
			storeScoreStop2 = false;
			storeScoreStop3 = false;
			storeScoreStop4 = false;
			storeScoreStop5 = false;
			storeScoreStop6 = false;
			storeScoreStop7 = false;
			storeScoreStop8 = false;
			HUD.SHIELDHEALTH = 0;
			game.upgrades.box1Row1Cost = 1000;
			game.upgrades.box2Row1Cost = 2000;
			game.upgrades.box3Row1Cost = 1000;
			game.upgrades.box4Row1Cost = 1500;
			game.upgrades.isBullet = false;
			game.upgrades.isSplitBullet = false;
			game.upgrades.isScoreBoost = false;
			game.upgrades.isDualPistolBullet = false;
			game.upgrades.isShotgunBullet = false;
			game.upgrades.isShield = false;
			game.player.pistolShoot = false;
			game.player.doubleShoot = false;
			game.player.shotgunShoot = false;
			game.player.sniperShoot = false;
			HUD.UNDERGROUNDHEALTH = 50;
			HUD.WIZARDHEALTH = 25;
			HUD.THROWERHEALTH = 50;
			HUD.SPLITHEALTH1 = 25;
			HUD.SPLITHEALTH2 = 25;
			HUD.HIGHSCORE = 0;
			ThrowerEnemy.giveInfo = true;
			
		}
		else if(contains(mx,my,50,400, 261, 261) && game.isLevel2Complete && game.gameState == game.STATE.SELECTLEVEL)
		{
			// LEVEL 3
			SoundPlayer.getSound("mouse_clicked").play();
			game.gameState = game.STATE.LEVEL3;	
			storeScoreStop = false;
			storeScoreStop2 = false;
			storeScoreStop3 = false;
			storeScoreStop4 = false;
			storeScoreStop5 = false;
			storeScoreStop6 = false;
			storeScoreStop7 = false;
			storeScoreStop8 = false;
			HUD.HIGHSCORE = 0;
			HUD.SHIELDHEALTH = 0;
			game.upgrades.box1Row1Cost = 1000;
			game.upgrades.box2Row1Cost = 2000;
			game.upgrades.box3Row1Cost = 1000;
			game.upgrades.box4Row1Cost = 1500;
			game.upgrades.isShield = false;
			game.upgrades.isBullet = false;
			game.upgrades.isSplitBullet = false;
			game.upgrades.isScoreBoost = false;
			game.upgrades.isDualPistolBullet = false;
			game.upgrades.isShotgunBullet = false;
			game.player.pistolShoot = false;
			game.player.doubleShoot = false;
			game.player.shotgunShoot = false;
			game.player.sniperShoot = false;
			//HUD.EXPLODERHEALTH = 20;
		}
		else if(contains(mx,my,490, 400, 261, 261) &&  game.isLevel3Complete && game.gameState == game.STATE.SELECTLEVEL)
		{
			// LEVEL 4
			SoundPlayer.getSound("mouse_clicked").play();
			game.gameState = game.STATE.LEVEL4;
			storeScoreStop = false;
			storeScoreStop2 = false;
			storeScoreStop3 = false;
			storeScoreStop4 = false;
			storeScoreStop5 = false;
			storeScoreStop6 = false;
			storeScoreStop7 = false;
			storeScoreStop8 = false;
			HUD.HIGHSCORE = 0;
			HUD.EXPLODERHEALTH = 20;
			HUD.SHIELDHEALTH = 0;
			game.upgrades.box1Row1Cost = 1000;
			game.upgrades.box2Row1Cost = 2000;
			game.upgrades.box3Row1Cost = 1000;
			game.upgrades.box4Row1Cost = 1500;
			game.upgrades.isShield = false;
			game.upgrades.isBullet = false;
			game.upgrades.isSplitBullet = false;
			game.upgrades.isDualPistolBullet = false;
			game.upgrades.isScoreBoost = false;
			game.upgrades.isShotgunBullet = false;
			game.player.pistolShoot = false;
			game.player.doubleShoot = false;
			game.player.shotgunShoot = false;
			game.player.sniperShoot = false;
		}
		else if(contains(mx,my,game.WIDTH/2-370,game.HEIGHT/2+250,740,100) && game.gameState == game.STATE.DEADSCREEN)
		{
			//BACK TO MENU BUTTON
			SoundPlayer.getSound("mouse_clicked").play();
			game.gameState = game.STATE.MENU;
		}		
		else if(contains(mx,my,620, 290, 150, 50) && game.gameState == game.STATE.LEVEL1 && HUD.LEVEL1BOSSHEALTH<=0)
		{
			// END OF LEVEL 1 
			SoundPlayer.getSound("mouse_clicked").play();
			game.gameState = game.STATE.LEVEL2;
			storeScoreStop = false;
			storeScoreStop2 = false;
			storeScoreStop3 = false;
			storeScoreStop4 = false;
			storeScoreStop5 = false;
			storeScoreStop6 = false;
			storeScoreStop7 = false;
			storeScoreStop8 = false;
			game.check = true;
			game.upgrades.box1Row1Cost = 1000;
			game.upgrades.box2Row1Cost = 2000;
			game.upgrades.box3Row1Cost = 1000;
			game.upgrades.box4Row1Cost = 1500;
			game.upgrades.isBullet = false;
			game.upgrades.isShield = false;
			game.upgrades.isSplitBullet = false;
			game.upgrades.isDualPistolBullet = false;
			game.upgrades.isShotgunBullet = false;
			game.upgrades.isScoreBoost = false;
			game.player.pistolShoot = false;
			game.player.doubleShoot = false;
			game.player.shotgunShoot = false;
			game.player.sniperShoot = false;
			handler.clearAll();
			game.isBossFight = false;
			game.bossDisplay = 0;
			game.bossDisplay2 = 0;
			game.player.x = 385;
			HUD.HEALTH = 100;
			HUD.SCORE = 0;
			HUD.COUNT = 0;
			HUD.HIGHSCORE = 0;
			HUD.LEVEL1BOSSHEALTH = 200;	
			HUD.SHIELDHEALTH = 0;
		}
		else if(contains(mx,my,620, 490, 150, 50) && game.gameState == game.STATE.LEVEL1 && HUD.LEVEL1BOSSHEALTH<=0)
		{
			// BACK TO MENU FROM END OF LEVEL 1
			SoundPlayer.getSound("mouse_clicked").play();
			game.gameState = game.STATE.MENU;
			HUD.LEVEL = 2;
			stopScoreChange0 = false;
		}
		else if(contains(mx,my,120,160,560,200) && game.gameState == game.STATE.HELP)
		{
			// STORY BUTTON 
			SoundPlayer.getSound("mouse_clicked").play();
			game.gameState = game.STATE.STORY;
		}
		else if(contains(mx,my,120,400,560,200) && game.gameState == game.STATE.HELP)
		{
			// HOW TO PLAY BUTTON
			SoundPlayer.getSound("mouse_clicked").play();
			game.gameState = game.STATE.HOWTOPLAY;
		}
		else if(contains(mx,my,20,120,50,120) && game.gameState == game.STATE.STORY)
		{
			// BACK FROM STORY BUTTON
			SoundPlayer.getSound("mouse_clicked").play();
			game.gameState = game.STATE.HELP;
		}
		else if(contains(mx,my,20+680,120,50,120) && game.gameState == game.STATE.STORY)
		{
			// MENU BUTTON FROM STORY
			SoundPlayer.getSound("mouse_clicked").play();
			game.gameState = game.STATE.MENU;
		}
		else if(contains(mx,my,620,720,150,50) && game.gameState == Game.STATE.HOWTOPLAY)
		{
			// MENU BUTTON FOR HOW TO PLAY
			SoundPlayer.getSound("mouse_clicked").play();
			game.gameState = game.STATE.MENU;
		}
		else if(contains(mx,my,30,720,150,50) && game.gameState == Game.STATE.HOWTOPLAY)
		{
			// BACK BUTTON FOR HOW TO PLAY
			SoundPlayer.getSound("mouse_clicked").play();
			game.gameState = game.STATE.HELP;
		}
		else if(contains(mx,my,620, 290, 150, 50) && game.gameState == game.STATE.LEVEL2 && HUD.LEVEL2BOSSHEALTH<=0)
		{
			// END OF LEVEL 2
			SoundPlayer.getSound("mouse_clicked").play();
			game.gameState = game.STATE.LEVEL3;
			storeScoreStop = false;
			storeScoreStop2 = false;
			storeScoreStop3 = false;
			storeScoreStop4 = false;
			storeScoreStop5 = false;
			storeScoreStop6 = false;
			storeScoreStop7 = false;
			storeScoreStop8 = false;
			game.upgrades.isBullet = false;
			game.upgrades.isShield = false;
			game.upgrades.isSplitBullet = false;
			game.upgrades.isDualPistolBullet = false;
			game.upgrades.isShotgunBullet = false;
			game.upgrades.isScoreBoost = false;
			game.check = true;
			game.player.pistolShoot = false;
			game.player.doubleShoot = false;
			game.player.shotgunShoot = false;
			game.player.sniperShoot = false;
			game.upgrades.box1Row1Cost = 1000;
			game.upgrades.box2Row1Cost = 2000;
			game.upgrades.box3Row1Cost = 1000;
			game.upgrades.box4Row1Cost = 1500;
			handler.clearAll();
			game.isBossFight2 = false;
			game.player.x = 385;
			HUD.HIGHSCORE = 0;
			HUD.HEALTH = 100;
			HUD.SCORE = 0;
			HUD.COUNT = 0;
			HUD.LEVEL2BOSSHEALTH = 500;
			HUD.SHIELDHEALTH = 0;
		}
		else if(contains(mx,my,620, 290, 150, 50) && game.gameState == game.STATE.LEVEL3 && HUD.LEVEL3BOSSHEALTH<=0)
		{
			// END OF LEVEL 3
			SoundPlayer.getSound("mouse_clicked").play();
			game.gameState = game.STATE.LEVEL4;
			storeScoreStop = false;
			storeScoreStop2 = false;
			storeScoreStop3 = false;
			storeScoreStop4 = false;
			storeScoreStop5 = false;
			storeScoreStop6 = false;
			storeScoreStop7 = false;
			storeScoreStop8 = false;
			game.upgrades.isBullet = false;
			game.upgrades.isShield = false;
			game.upgrades.isSplitBullet = false;
			game.upgrades.isDualPistolBullet = false;
			game.upgrades.isShotgunBullet = false;
			game.player.pistolShoot = false;
			game.player.doubleShoot = false;
			game.player.shotgunShoot = false;
			game.check = true;
			game.player.sniperShoot = false;
			game.upgrades.box1Row1Cost = 1000;
			game.upgrades.box2Row1Cost = 2000;
			game.upgrades.box3Row1Cost = 1000;
			game.upgrades.box4Row1Cost = 1500;
			game.upgrades.isScoreBoost = false;
			handler.clearAll();
			game.isBossFight3 = false;
			game.player.x = 385;
			HUD.HEALTH = 100;
			HUD.SCORE = 0;
			HUD.COUNT = 0;
			HUD.HIGHSCORE = 0;
			HUD.LEVEL3BOSSHEALTH = 550;
			HUD.EXPLODERHEALTH = 20;
			HUD.SHIELDHEALTH = 0;
		}
		else if(contains(mx,my,620, 490, 150, 50) && game.gameState == game.STATE.LEVEL2 && HUD.LEVEL2BOSSHEALTH<=0)
		{
			// END OF LEVEL 2
			SoundPlayer.getSound("mouse_clicked").play();
			game.gameState = game.STATE.MENU;
			stopScoreChange0 = false;
			HUD.LEVEL = 3;
		}
		else if(contains(mx,my,620, 490, 150, 50) && game.gameState == game.STATE.LEVEL3 && HUD.LEVEL3BOSSHEALTH<=0)
		{
			// END OF LEVEL 3
			SoundPlayer.getSound("mouse_clicked").play();
			game.gameState = game.STATE.MENU;
			stopScoreChange0 = false;
			HUD.LEVEL = 4;
		}
		else if(contains(mx,my,500, 365, 250, 80) && game.gameState == game.STATE.LEVEL4 && HUD.LEVEL4BOSSHEALTH<=0)
		{
			// GOES TO GAME COMPLETE
			stopScoreChange0 = false;
			SoundPlayer.getSound("mouse_clicked").play();
			game.gameState = game.STATE.GAMECOMPLETE;
			game.player.x = 370;
			HUD.LEVEL = 5;
		}
		else if(contains(mx,my,10,570,780,75) && game.gameState == game.STATE.GAMECOMPLETE && Player.playerMoved)
		{
			// GAME COMPLETE MENU
			SoundPlayer.getSound("mouse_clicked").play();
			game.gameState = game.STATE.MENU;
		}
		else if(game.gameState == game.STATE.DEADSCREEN && !stopScoreChange1)
		{
			HUD.COUNT = HUD.SCORE;
			stopScoreChange1 = true;
		}
		else if(game.gameState == game.STATE.LEVEL1 && !stopScoreChange2 && HUD.LEVEL1BOSSHEALTH<=0)
		{
			HUD.COUNT = HUD.SCORE;
			stopScoreChange2 = true;
		}
		else if(game.gameState == game.STATE.DEADSCREEN && !stopScoreChange3)
		{
			HUD.COUNT = HUD.SCORE;
			stopScoreChange3 = true;
		}
		else if(game.gameState == game.STATE.LEVEL2 && !stopScoreChange4 && HUD.LEVEL2BOSSHEALTH<=0)
		{
			HUD.COUNT = HUD.SCORE;
			stopScoreChange4 = true;
		}
		else if(game.gameState == game.STATE.DEADSCREEN && !stopScoreChange5)
		{
			HUD.COUNT = HUD.SCORE;
			stopScoreChange5 = true;
		}
		else if(game.gameState == game.STATE.LEVEL3 && !stopScoreChange6 && HUD.LEVEL3BOSSHEALTH<=0)
		{
			HUD.COUNT = HUD.SCORE;
			stopScoreChange6 = true;
		}
		else if(game.gameState == game.STATE.DEADSCREEN && !stopScoreChange7)
		{
			HUD.COUNT = HUD.SCORE;
			stopScoreChange7 = true;
		}
		else if(game.gameState == game.STATE.LEVEL4 && !stopScoreChange8 && HUD.LEVEL4BOSSHEALTH<=0)
		{
			HUD.COUNT = HUD.SCORE;
			stopScoreChange8 = true;
		}
	}
	/* This method is for adding cool effects 
	 * when the mouse pointer is above a button */
	public void mouseMoved(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		if(contains(mx,my,game.WIDTH/2-115,game.HEIGHT/2-188,200,100) && game.gameState == game.STATE.MENU) playColor = true;
		else playColor = false;
		if(contains(mx,my,game.WIDTH/2-115,game.HEIGHT/2-60,200,100) && game.gameState == game.STATE.MENU) customizeColor = true;
		else customizeColor = false;
		if(contains(mx,my,game.WIDTH/2-115,game.HEIGHT/2+70,200,100) && game.gameState == game.STATE.MENU) helpColor = true;
		else helpColor = false;
		if(contains(mx,my,game.WIDTH/2-115,game.HEIGHT/2+200,200,100) && game.gameState == game.STATE.MENU) exitColor = true;
		else exitColor = false;
		if(contains(mx,my,game.WIDTH/2-75,game.HEIGHT/2+265,150,50) && game.gameState == game.STATE.SELECTLEVEL) backButtonLevelSelect = true;
		else backButtonLevelSelect = false;
		if(contains(mx,my,5,700,250,75) && game.gameState == game.STATE.CUSTOMIZE) backButtonCustomize = true;
		else backButtonCustomize = false;
		if(contains(mx,my,game.WIDTH/2-370,game.HEIGHT/2+250,740,100) && game.gameState == game.STATE.DEADSCREEN) deathScreenButton = true;
		else deathScreenButton = false;
		if(contains(mx,my,120,160,560,200) && game.gameState == game.STATE.HELP) storyButton = true;
		else storyButton = false;
		if(contains(mx,my,620, 490, 150, 50)  && game.gameState == game.STATE.LEVEL1 && HUD.LEVEL1BOSSHEALTH<=0) menuButtonLevel1End = true;
		else menuButtonLevel1End = false;
		if(contains(mx,my,620, 290, 150, 50) && game.gameState == game.STATE.LEVEL1 && HUD.LEVEL1BOSSHEALTH<=0) nextLevelLevel1End = true;
		else nextLevelLevel1End = false;
		if(contains(mx,my,120,400,560,200) && game.gameState == game.STATE.HELP) howtoPlayButton = true;
		else howtoPlayButton = false;
		if(contains(mx,my,20,120,50,120) && game.gameState == game.STATE.STORY) storyButtonBACK = true;
		else storyButtonBACK = false;
		if(contains(mx,my,20+680,120,50,120) && game.gameState == game.STATE.STORY) storyButtonMENU = true;
		else storyButtonMENU = false;
		if(contains(mx,my,30,720,150,50) && game.gameState == Game.STATE.HOWTOPLAY) howtoPlayButtonBACK = true;
		else howtoPlayButtonBACK = false;
		if(contains(mx,my,620,720,150,50) && game.gameState == Game.STATE.HOWTOPLAY) howtoPlayButtonMENU = true;
		else howtoPlayButtonMENU = false;
		if(contains(mx,my,game.WIDTH/2-120,game.HEIGHT/2+250,250,100) && game.gameState == game.STATE.HELP) helpColorBACK = true;
		else helpColorBACK = false;
		if(contains(mx,my,50,115,261,261) && game.gameState == game.STATE.SELECTLEVEL)level1highlight = true;
		else level1highlight = false;
		if(contains(mx,my,490,115,261,261) && game.gameState == game.STATE.SELECTLEVEL) level2highlight = true;
		else level2highlight = false;
		if(contains(mx,my,50,400,261,261) && game.gameState == game.STATE.SELECTLEVEL) level3highlight = true;
		else level3highlight = false;
		if(contains(mx,my,490,400,261,261) && game.gameState == game.STATE.SELECTLEVEL) level4highlight = true;
		else level4highlight = false;
		if(contains(mx,my,620, 290, 150, 50) && game.gameState == game.STATE.LEVEL2 && HUD.LEVEL2BOSSHEALTH<=0) nextLevelLevel2End = true;
		else nextLevelLevel2End = false;
		if(contains(mx,my,620, 490, 150, 50) && game.gameState == game.STATE.LEVEL2 && HUD.LEVEL2BOSSHEALTH<=0) menuButtonLevel2End = true;
		else menuButtonLevel2End = false;
		if(contains(mx,my,620, 290, 150, 50) && game.gameState == game.STATE.LEVEL3 && HUD.LEVEL3BOSSHEALTH<=0) nextLevelLevel3End = true;
		else nextLevelLevel3End = false;
		if(contains(mx,my,620, 490, 150, 50) && game.gameState == game.STATE.LEVEL3 && HUD.LEVEL3BOSSHEALTH<=0) menuButtonLevel3End = true;
		else menuButtonLevel3End = false;
		if(contains(mx,my,500, 365, 250, 80) && game.gameState == game.STATE.LEVEL4 && HUD.LEVEL4BOSSHEALTH<=0) gameCompleteLevel4End = true;
		else gameCompleteLevel4End = false;
		if(contains(mx,my,10,570,780,75) && game.gameState == game.STATE.GAMECOMPLETE && Player.playerMoved) gameCompleteMenuButton = true;
		else gameCompleteMenuButton = false;
	}
	/* This method returns a boolean and it checks if the
	 * mouse cursor is inside the rectangle is which case
	 * it returns true or else it returns false */
	public boolean contains(int mx, int my,int x, int y,int width,int height)
	{
		if(mx >= x && mx <= x+width)
		{
			if(my >= y && my <= y+height)
			{
				return true;
			}
			else return false;
		}
		else return false;
	}
	/* This method renders 60 times per second
	 * as it draws the menu screen and the buttons
	 * It also draws the death screen 
	 * depending on the game State */
	public void render(Graphics g)
	{
		if(game.gameState == game.STATE.MENU)
		{
	        ((Graphics2D)g).setStroke(new BasicStroke(3));
			g.setColor(violet);
			Font font = new Font("Superpower Synonym",Font.BOLD,180);
			g.setFont(font);
			g.drawString(Game.TITLE,42,160);
			Font customize = new Font("Arial",Font.BOLD,34);
			g.setFont(customize);
			
			if(customizeColor) g.setColor(purple);
			else g.setColor(lavender);
			if(customizeColor) g.fillRect(game.WIDTH/2-115,game.HEIGHT/2-60,200,100);
			else g.drawRect(game.WIDTH/2-115,game.HEIGHT/2-60,200,100);
			if(customizeColor) g.setColor(Color.WHITE);
			else g.setColor(lavender);
			g.drawString("CUSTOMIZE", game.WIDTH/2-115, game.HEIGHT/2);
			
			if(playColor) g.setColor(Color.BLACK);
			else g.setColor(skyBlue);
			if(playColor) g.fillRect(game.WIDTH/2-115,game.HEIGHT/2-188,200,100);
			else g.drawRect(game.WIDTH/2-115,game.HEIGHT/2-188,200,100);
			
			Font f1 = new Font("Arial",Font.BOLD,60);
			g.setFont(f1);
			if(playColor) g.setColor(Color.WHITE);
			else g.setColor(skyBlue);
			g.drawString("PLAY", game.WIDTH/2-93, game.HEIGHT/2-120);
			
			if(helpColor) g.setColor(skyBlue);
			else g.setColor(springGreen);
			if(helpColor) g.fillRect(game.WIDTH/2-115,game.HEIGHT/2+70,200,100);
			else g.drawRect(game.WIDTH/2-115,game.HEIGHT/2+70,200,100);
			
			Font helpfont = new Font("Arial",Font.BOLD,60);
			g.setFont(helpfont);
			if(helpColor) g.setColor(Color.WHITE);
			else g.setColor(springGreen);
			g.drawString("HELP", game.WIDTH/2-93, game.HEIGHT/2+140);
			
			if(exitColor) g.setColor(lime);
			else g.setColor(deepPink);
			if(exitColor) g.fillRect(game.WIDTH/2-115,game.HEIGHT/2+200,200,100);
			else g.drawRect(game.WIDTH/2-115,game.HEIGHT/2+200,200,100);
			
			Font f2 = new Font("Arial",Font.BOLD,60);
			g.setFont(f2);
			if(exitColor) g.setColor(Color.WHITE);
			else g.setColor(deepPink);
			g.drawString("EXIT", game.WIDTH/2-80, game.HEIGHT/2+270);
			
			Font f = new Font("Arial",Font.BOLD,18);
			g.setFont(f);
			g.setColor(deepPink);
			if(playerInfo != null && !stopScoreChange0)
			{
				playerInfo.setPlayerName(Game.NAME);
				playerInfo.setHighestLevelScore(HUD.HIGHSCORE);
				playerInfo.setTotalScore(HUD.TOTALSCORE);
				playerInfo.setPlayerLevel(HUD.LEVEL);
				fileUtils.store(playerInfo);
				stopScoreChange0 = true;
			}
			if(!levelChange)
			{
				int level = fileUtils.getThePlayerLevel(Game.NAME);
				if(level == 2)
				{
					game.isLevel1Complete = true;
				}
				else if(level == 3)
				{
					game.isLevel1Complete = true;
					game.isLevel2Complete = true;
				}
				else if(level == 4)
				{
					game.isLevel1Complete = true;
					game.isLevel2Complete = true;
					game.isLevel3Complete = true;
				}
				else if(level == 5)
				{
					game.isLevel1Complete = true;
					game.isLevel2Complete = true;
					game.isLevel3Complete = true;
					game.isLevel4Complete = true;
				}
			}
			g.drawString("Money: "+fileUtils.getTotalScoreForAPlayer(Game.NAME),3,Game.HEIGHT-30);
			g.setColor(Color.BLACK);
			g.drawString("Version 1.0",700,20);
			g.drawString("Welcome "+Game.NAME+"!",5,20);
		}
		else if(game.gameState == game.STATE.CUSTOMIZE)
		{
			g.setColor(Color.YELLOW);
			Font customizeTitle = new Font("Superpower Synonym",Font.BOLD,175);
			g.setFont(customizeTitle);
			g.drawString("CUSTOMIZE",5,130);
			Font backButton = new Font("Arial",Font.BOLD,80);
			g.setFont(backButton);
			if(backButtonCustomize) g.setColor(deepPink);
			else g.setColor(Color.YELLOW);
			if(backButtonCustomize) g.fillRect(5,700,250,75);
			else g.drawRect(5,700,250,75);
			if(backButtonCustomize) g.setColor(Color.GREEN);
			else g.setColor(Color.YELLOW);
			g.drawString("BACK",15,763);	
			g.setColor(Color.YELLOW);
			Font totalScore = new Font("Arial",Font.BOLD,18);
			g.setFont(totalScore);
		}
		else if(game.gameState == game.STATE.HELP)
		{
			g.setColor(Color.WHITE);
			Font f5 = new Font("TimesNewRoman",Font.BOLD,88);
			g.setFont(f5);
			g.drawString("HELP",game.WIDTH/2-118,75);
			if(helpColorBACK) g.setColor(springGreen);
			else g.setColor(skyBlue);
			if(helpColorBACK) g.fillRect(game.WIDTH/2-120,game.HEIGHT/2+250,250,100);
			else g.drawRect(game.WIDTH/2-120,game.HEIGHT/2+250,250,100);
			if(helpColorBACK) g.setColor(Color.RED);
			else g.setColor(skyBlue);
			g.drawString("BACK",game.WIDTH/2-118,game.HEIGHT/2+330);
			
			if(storyButton) g.setColor(purple);
			else g.setColor(Color.GREEN);
			if(storyButton) g.fillRect(120,160,560,200);
			else g.fillRect(120,160,560,200);
			Font f4 = new Font("TimesNewRoman",Font.BOLD,150);
			g.setFont(f4);
			if(storyButton) g.setColor(Color.WHITE);
			else g.setColor(Color.RED);
			g.drawString("STORY", 145, 310);
			
			if(howtoPlayButton) g.setColor(Color.BLACK);
			else g.setColor(Color.MAGENTA);
			if(howtoPlayButton) g.fillRect(120,400,560,200);
			else g.fillRect(120,400,560,200);
			Font f6 = new Font("TimesNewRoman",Font.BOLD,100);
			g.setFont(f6);
			if(howtoPlayButton) g.setColor(Color.CYAN);
			else g.setColor(Color.WHITE);
			g.drawString("HOW TO", 185, 485);
			g.drawString("PLAY", 280, 580);
		}
		else if(game.gameState == game.STATE.STORY)
		{
			g.setColor(Color.BLACK);
			Font f9 = new Font("TimesNewRoman",Font.BOLD,88);
			g.setFont(f9);
			g.drawString("STORY",game.WIDTH/2-160,75);
			Font f5 = new Font("TimesNewRoman",Font.BOLD,24);
			g.setFont(f5);
			if(storyButtonBACK) g.setColor(Color.YELLOW);
			else g.setColor(Color.WHITE);
			if(storyButtonBACK) g.fillRect(20,120,50,120);
			else g.drawRect(20,120,50,120);
			if(storyButtonBACK) g.setColor(Color.RED);
			else g.setColor(Color.WHITE);
			g.drawString("B",36,143);
			g.drawString("A",36,173);
			g.drawString("C",36,203);
			g.drawString("K",36,233);
			if(storyButtonMENU) g.setColor(Color.YELLOW);
			else g.setColor(Color.WHITE);
			if(storyButtonMENU) g.fillRect(20+680,120,50,120);
			else g.drawRect(20+680,120,50,120);
			if(storyButtonMENU) g.setColor(Color.RED);			
			g.drawString("M",36+680,143);
			g.drawString("E",36+682,173);
			g.drawString("N",36+680,203);
			g.drawString("U",36+680,233);

			Color back = new Color(220,220,220,160);
			g.setColor(back);
			Font f6 = new Font("Superpower Synonym",Font.BOLD,24);
			g.setFont(f6);
			g.fillRect(0, 560, 800, 220);
			g.setColor(Color.BLACK);
			g.drawString("Hello "+Game.NAME+", you have been trapped in a volcano, and you have to get",5,105+475);
			g.drawString("out. I am the king of this volcano and you should know that you will",5,140+475);
			g.drawString("not escape. You will encounter so many enemies and along the way", 5, 175+475);
			g.drawString("you can pick up coins, but you will burn to death because of fireballs", 5, 210+475);
			g.drawString("magma rocks, from this ravaging volcano. Along the way you might", 5, 245+475);
			g.drawString("encounter items that might help but ultimately forget about escape", 5, 275+475);
			g.drawString("Or will you escape", Game.WIDTH/2-110, 310+465);
			Font f7 = new Font("Arial",Font.BOLD,24);
			g.setFont(f7);
			g.drawString("?", Game.WIDTH/2+110, 310+465);
		}
		else if(game.gameState == game.STATE.HOWTOPLAY)
		{
			g.setColor(Color.CYAN);
			Font htp = new Font("Roboto",Font.BOLD,88);
			g.setFont(htp);
			g.drawString("HOW TO PLAY",game.WIDTH/2-320,75);

			Font f5 = new Font("TimesNewRoman",Font.BOLD,48);
			g.setFont(f5);
			if(howtoPlayButtonBACK) g.setColor(coral);
			else g.setColor(Color.YELLOW);
			if(howtoPlayButtonBACK) g.fillRect(30,720,150,50);
			else g.drawRect(30,720,150,50);
			if(howtoPlayButtonBACK) g.setColor(Color.BLUE);
			else g.setColor(Color.YELLOW);
			g.drawString("BACK",38,762);			
			
			g.setColor(Color.YELLOW);
			if(howtoPlayButtonMENU) g.setColor(purple);
			else g.setColor(Color.YELLOW);
			if(howtoPlayButtonMENU) g.fillRect(620,720,150,50);
			else g.drawRect(620,720,150,50);
			if(howtoPlayButtonMENU) g.setColor(Color.PINK);
			else g.setColor(Color.YELLOW);
			g.drawString("MENU",621,762);

			g.setColor(lavender);
			Font instructionFont = new Font("Arial",Font.BOLD,24);
			g.setFont(instructionFont);
			g.drawImage(texture.rightArrowKey,25,125,50,50,null);
			g.drawString("Move Right ",80,155);
			g.drawImage(texture.leftArrowKey,25,175,50,50,null);
			g.drawString("Move Left ",80,209);
			g.drawImage(texture.space,25,230,50,50,null);
			g.drawString("Shoot",84,263);
			g.drawImage(texture.shiftKey,25,280,50,50,null);
			g.drawString("To open Upgrades",84,310);
			g.drawImage(image3,5,346,80,20,null);
			g.drawString("Coin Boost Time", 88, 363);
			g.drawImage(image5,5,396,80,20,null);
			g.drawString("Shield Health", 88, 413);
			g.drawImage(image4,380,115,385,385,null);
			g.setColor(skyBlue);
			g.drawString("GAME DISPLAY", 493,110);
			
			g.setColor(coral);
			g.drawString("OBJECTIVE:",5,442);
			g.drawString("Dodge objects, kill enemies and ", 5, 467);
			g.drawString("TRY TO ESCAPE!",70, 494);
			
	        ((Graphics2D)g).setStroke(new BasicStroke(4));
			g.setColor(Color.CYAN);
			g.drawLine(0,505,800,505);
			
			g.setColor(Color.GREEN);
			g.drawString("SCORE CHART", 312, 530);
			g.setColor(Color.YELLOW);
			g.drawRect(380,115,386,386);
			g.fillRect(20,540,39,39);
			g.fillRect(20,600,39,39);
			g.fillRect(20,660,39,39);
			g.fillRect(150,540,39,39);
			g.fillRect(150,600,39,39);
			g.fillRect(150,660,39,39);
			g.drawRect(280,720,39,39);
			g.fillRect(280,540,39,39);
			g.fillRect(280,600,39,39);
			g.fillRect(280,660,39,39);
			g.fillRect(410,540,39,39);
			g.fillRect(410,600,39,39);
			g.fillRect(410,660,39,39);
			g.fillRect(540,540,39,39);
			g.fillRect(540,600,39,39);
			g.fillRect(540,660,39,39);
			g.fillRect(670,540,39,39);
			g.fillRect(670,600,39,39);
			g.fillRect(670,660,39,39);
			g.setColor(Color.CYAN);
			g.drawRect(20,540,40,40);
			g.drawRect(20,600,40,40);
			g.drawRect(20,660,40,40);
			g.drawRect(150,540,40,40);
			g.drawRect(150,600,40,40);
			g.drawRect(150,660,40,40);
			g.drawImage(image,280,720,39,39,null);
			g.drawRect(280,720,40,40);
			g.drawRect(280,540,40,40);
			g.drawRect(280,600,40,40);
			g.drawRect(280,660,40,40);
			g.drawRect(410,540,40,40);
			g.drawRect(410,600,40,40);
			g.drawRect(410,660,40,40);
			g.drawRect(540,540,40,40);
			g.drawRect(540,600,40,40);
			g.drawRect(540,660,40,40);
			g.drawRect(670,540,40,40);
			g.drawRect(670,600,40,40);
			g.drawRect(670,660,40,40);
			g.drawImage(texture.bronzeCoin,25,545,30,30,null);
			g.drawImage(texture.silverCoin,25,605,30,30,null);
			g.drawImage(texture.goldCoin,25,665,30,30,null);
			
			g.drawImage(texture.emerald,155,545,30,30,null);
			g.drawImage(texture.ruby,155,605,30,30,null);
			g.drawImage(texture.diamond,155,665,30,30,null);
			
			g.drawImage(texture.pinkGem,285,545,30,30,null);
			g.drawImage(texture.purpleEmerald,285,605,30,30,null);
			g.drawImage(texture.undergroundEnemy,285,665,30,30,null);
			
			g.drawImage(texture.throwerEnemyLeft,415,545,30,30,null);
			g.drawImage(texture.rightWizard,415,605,30,30,null);
			g.drawImage(texture.exploderEnemyLeft,415,665,30,30,null);
			
			
			g.drawImage(texture.rocketEnemy,545,545,30,30,null);
			g.drawImage(texture.rayEnemy,545,605,30,30,null);
			g.drawImage(texture.babyDragon,545,665,30,30,null);
			
			g.drawImage(texture.boomerangEnemyLeft,675,545,30,30,null);
			g.drawImage(texture.tankEnemy,675,605,30,30,null);
			g.drawImage(texture.shieldEnemy,675,665,30,30,null);
			
			g.setColor(Color.YELLOW);
			Font nameFont = new Font("Roboto",Font.BOLD,11);
			g.setFont(nameFont);
			g.drawString("Bronze Coin", 65, 550);
			g.drawString("Silver Coin", 65, 610);
			g.drawString("Gold Coin", 65, 670);
			g.drawString("Emerald", 206, 550);
			g.drawString("Ruby", 215, 610);
			g.drawString("Diamond", 206, 670);
			g.drawString("Pink Gem", 328, 550);
			g.drawString("Purple Gem", 328, 610);
			g.drawString("Underground", 328, 665);
			g.drawString("Enemy", 339, 677);
			g.drawString("Thrower", 460, 545);
			g.drawString("Enemy", 465, 555);
			g.drawString("Wizard", 465, 610);
			g.drawString("Exploder", 460, 665);
			g.drawString("Enemy", 465, 677);
			g.drawString("Rocket Enemy", 585, 550);
			g.drawString("Ray Enemy", 590, 610);
			g.drawString("Baby Dragon", 589, 670);
			g.drawString("Boomerang", 720, 545);
			g.drawString("Enemy", 727, 556);
			g.drawString("Tank Enemy", 718, 610);
			g.drawString("Shield Enemy", 718, 670);
			g.drawString("rewards twice as",349 ,743);
			g.drawString("much money than usual", 337,754);
			
			Font scoreFont = new Font("Roboto",Font.BOLD,20);
			g.setFont(scoreFont);
			g.drawString("100", 75, 570);
			g.drawString("250", 75, 630);
			g.drawString("500", 75, 690);
			g.drawString("200", 210, 570);
			g.drawString("500", 210, 632);
			g.drawString("750", 210, 690);
			g.drawString("150", 335, 570);
			g.drawString("400", 335, 630);
			g.drawString("500", 335, 695);
			g.drawString("200", 465, 575);
			g.drawString("600", 465, 630);
			g.drawString("200", 465, 695);
			g.drawString("150", 598, 570);
			g.drawString("350", 598, 630);
			g.drawString("400", 602, 690);
			g.drawString("200", 726, 576);
			g.drawString("450", 730, 630);
			g.drawString("250", 730, 690);
			g.drawString("COIN UPGRADE", 330,727);
		}
		else if(game.gameState == game.STATE.SELECTLEVEL)
		{
			icon = new ImageIcon(getClass().getResource("/QuestionDisplay.png"));
			questionDisplay = icon.getImage();
			
			g.setColor(Color.GRAY);
			g.fillRect(50,115,260,260);
			g.setColor(Color.CYAN);
			Font lev = new Font("Superpower Synonym", Font.BOLD,128);
			g.setFont(lev);
			g.drawString("Select Level",5,100);
			g.drawImage(game.level1Display,50,115,260,260,null);
			if(level1highlight) g.setColor(Color.BLACK);
			else g.setColor(Color.WHITE);
	        ((Graphics2D)g).setStroke(new BasicStroke(3));
			g.drawRect(50,115,261,261);
			
			Font levChoose = new Font("TimesNewRoman",Font.BOLD,54);
			g.setFont(levChoose);
			if(backButtonLevelSelect) g.setColor(coral);
			else g.setColor(Color.WHITE);
			if(backButtonLevelSelect) g.fillRect(game.WIDTH/2-75,game.HEIGHT/2+265,150,50);
			else g.drawRect(game.WIDTH/2-75,game.HEIGHT/2+265,150,50);
			if(backButtonLevelSelect) g.setColor(Color.YELLOW);
			else g.setColor(Color.WHITE);
			g.drawString("BACK",game.WIDTH/2-75,game.HEIGHT/2+310);
			
			g.setColor(Color.YELLOW);
			Font difLevDisplay = new Font("TimesNewRoman",Font.BOLD,18);
			g.setFont(difLevDisplay);
			g.drawString("LEVEL 1",144,396);
			
			g.setColor(Color.GRAY);
			g.fillRect(490,115,260,260);
			if(game.isLevel1Complete) g.drawImage(game.level2Display,490,115,260,260,null);
			else g.drawImage(questionDisplay,490,115,260,260,null);
			g.setColor(Color.YELLOW);
			g.setFont(difLevDisplay);
			g.drawString("LEVEL 2",590,396);
			if(level2highlight) g.setColor(Color.BLACK);
			else g.setColor(Color.WHITE);
			g.drawRect(490,115,261,261);
			g.setColor(Color.CYAN);
			Font name = new Font("Arial",Font.BOLD,18);
			g.setFont(name);
			g.drawString("Player: "+Game.NAME,5,770);
			
	        ((Graphics2D)g).setStroke(new BasicStroke(3));
			g.setColor(Color.GRAY);
			g.fillRect(50, 400, 260, 260);
			if(game.isLevel2Complete) g.drawImage(game.level3Display,50, 400, 260, 260,null);
			else g.drawImage(questionDisplay,50, 400, 260, 260,null);
			if(level3highlight) g.setColor(Color.BLACK);
			else g.setColor(Color.WHITE);
			g.drawRect(50, 400, 261, 261);
			g.setColor(Color.CYAN);
			g.drawString("LEVEL 3",144,680);
			
			((Graphics2D)g).setStroke(new BasicStroke(3));
			g.setColor(Color.GRAY);
			g.fillRect(490, 400, 260, 260);
			if(game.isLevel3Complete) g.drawImage(game.level4Display,490, 400, 260, 260,null);
			else g.drawImage(questionDisplay,490, 400, 260, 260,null);
			if(level4highlight) g.setColor(Color.BLACK);
			else g.setColor(Color.WHITE);
			g.drawRect(490, 400, 261, 261);
			g.setColor(Color.CYAN);
			g.drawString("LEVEL 4",590,680);

		}
		else if(game.gameState == game.STATE.GAMECOMPLETE)
		{
			if(Player.playerMoved)
			{
				g.setColor(Color.WHITE);
				Font f2 = new Font("Roboto",Font.BOLD,60);
				g.setFont(f2);
				g.drawString("GAME COMPLETED",Game.WIDTH/2-285, Game.HEIGHT/2-80);
				
				g.setColor(Color.GREEN);
				Font f3 = new Font("Roboto",Font.BOLD | Font.ITALIC,48);
				g.setFont(f3);
				g.drawString("You escaped the Volcano!",Game.WIDTH/2-320, Game.HEIGHT/2-30);
				
				g.setColor(Color.WHITE);
				Font f4 = new Font("Roboto",Font.BOLD | Font.ITALIC,24);
				g.setFont(f4);
				g.setColor(deepPink);
				g.drawString("Creator: ",10,420);
				
				Font f5 = new Font("Roboto",Font.BOLD,24);
				g.setFont(f5);
				g.drawString("Priyadarsi Mishra",120,420);
				
				g.setColor(skyBlue);
				g.drawString("You can go back and play all levels and try to beat", 85, 450);
				g.drawString("your previous highscores. You can also go back and", 75, 480);
				g.drawString("customize your character", 230, 510);

				g.setColor(Color.WHITE);
				
				if(gameCompleteMenuButton) g.fillRect(10,570,780,75);
				else g.drawRect(10,570,780,75);
				g.setFont(f3);
				if(gameCompleteMenuButton) g.setColor(Color.BLACK);
				else g.setColor(Color.WHITE);
				g.drawString("BACK TO MENU", 200, 625);
				if(playerInfo != null && !gameCompleteStoreStop && HUD.LEVEL == 5)
				{
					playerInfo.setPlayerName(Game.NAME);
					playerInfo.setHighestLevelScore(HUD.HIGHSCORE);
					playerInfo.setTotalScore(HUD.TOTALSCORE);
					playerInfo.setPlayerLevel(HUD.LEVEL);
					fileUtils.store(playerInfo);
					gameCompleteStoreStop = true;
				}
			}			
		}
		else if(game.gameState == game.STATE.DEADSCREEN)
		{
			g.drawImage(image6,460,5,500,500,null);
			g.drawImage(image6,5,5,500,500,null);
			g.setColor(Color.YELLOW);
			Font f3 = new Font("Superpower Synonym", Font.BOLD,200);
			g.setFont(f3);
			g.drawString("YOU LOST!",Game.WIDTH/2-382,170);

			g.setColor(Color.YELLOW);
			if(deathScreenButton) g.fillRect(game.WIDTH/2-370,game.HEIGHT/2+250,740,100);
			else g.drawRect(game.WIDTH/2-370,game.HEIGHT/2+250,740,100);
			Font returnTo = new Font("Superpower Synonym",Font.BOLD,88);
			g.setFont(returnTo);
			if(deathScreenButton) g.setColor(Color.RED);
			else g.setColor(Color.YELLOW);
			g.drawString("Return To",game.WIDTH/2-322,game.HEIGHT/2+330);
			g.drawString("MENU",game.WIDTH/2+115,game.HEIGHT/2+330);
			
			g.setColor(Color.YELLOW);
			Font tryAgain = new Font("Roboto",Font.BOLD,78);
			g.setFont(tryAgain);
			g.drawString("TRY TO DO BETTER", 5, 480);
			g.drawString("NEXT TIME!",160, 560);

			
			g.setColor(Color.YELLOW);
			((Graphics2D)g).setStroke(new BasicStroke(6));
			g.drawRect(0, 200, Game.WIDTH,400);
			Font count = new Font("TimesNewRoman",Font.BOLD,40);
			g.setFont(count);
			//Levels Selection
			if(HUD.LEVEL == 1)
			{
				if(HUD.SCORE == 0)
				{
					g.drawString("SCORE: 0",5,255);
					if(playerInfo != null)
					{
						g.drawString("LEVEL 1 HIGHSCORE: "+fileUtils.getHighestScore(Game.NAME,HUD.LEVEL),5,295);
					}
				}
				else if(HUD.SCORE == HUD.COUNT)
				{
					g.drawString("SCORE: "+(HUD.COUNT),5,255);
					if(playerInfo != null)
					{
						g.drawString("LEVEL 1 HIGHSCORE: "+fileUtils.getHighestScore(Game.NAME,HUD.LEVEL),5,295);
					}
				}
				else
				{
					g.drawString("SCORE: "+(HUD.COUNT-2),5,255);
					if(playerInfo != null)
					{
						g.drawString("LEVEL 1 HIGHSCORE: "+fileUtils.getHighestScore(Game.NAME,HUD.LEVEL),5,295);
					}
						
				}
			}
			if(HUD.LEVEL == 2)
			{
				if(HUD.SCORE == 0)
				{
					g.drawString("SCORE: 0",5,255);
					if(playerInfo != null)
					{
						g.drawString("LEVEL 2 HIGHSCORE: "+fileUtils.getHighestScore(Game.NAME,HUD.LEVEL),5,295);
					}
				}
				else if(HUD.SCORE == HUD.COUNT)
				{
					g.drawString("SCORE: "+(HUD.COUNT),5,255);
					if(playerInfo != null)
					{
						g.drawString("LEVEL 2 HIGHSCORE: "+fileUtils.getHighestScore(Game.NAME,HUD.LEVEL),5,295);
					}
				}
				else
				{
					g.drawString("SCORE: "+(HUD.COUNT-2),5,255);
					if(playerInfo != null)
					{
						g.drawString("LEVEL 2 HIGHSCORE: "+fileUtils.getHighestScore(Game.NAME,HUD.LEVEL),5,295);
					}
						
				}
			}
			
			if(HUD.LEVEL == 3)
			{
				if(HUD.SCORE == 0)
				{
					g.drawString("SCORE: 0",5,255);
					if(playerInfo != null)
					{
						g.drawString("LEVEL 3 HIGHSCORE: "+fileUtils.getHighestScore(Game.NAME,HUD.LEVEL),5,295);
					}
				}
				else if(HUD.SCORE == HUD.COUNT)
				{
					g.drawString("SCORE: "+(HUD.COUNT),5,255);
					if(playerInfo != null)
					{
						g.drawString("LEVEL 3 HIGHSCORE: "+fileUtils.getHighestScore(Game.NAME,HUD.LEVEL),5,295);
					}
				}
				else
				{
					g.drawString("SCORE: "+(HUD.COUNT-2),5,255);
					if(playerInfo != null)
					{
						g.drawString("LEVEL 3 HIGHSCORE: "+fileUtils.getHighestScore(Game.NAME,HUD.LEVEL),5,295);
					}
						
				}
			}
			if(HUD.LEVEL == 4)
			{
				if(HUD.SCORE == 0)
				{
					g.drawString("SCORE: 0",5,255);
					if(playerInfo != null)
					{
						g.drawString("LEVEL 4 HIGHSCORE: "+fileUtils.getHighestScore(Game.NAME,HUD.LEVEL),5,295);
					}
				}
				else if(HUD.SCORE == HUD.COUNT)
				{
					g.drawString("SCORE: "+(HUD.COUNT),5,255);
					if(playerInfo != null)
					{
						g.drawString("LEVEL 4 HIGHSCORE: "+fileUtils.getHighestScore(Game.NAME,HUD.LEVEL),5,295);
					}
				}
				else
				{
					g.drawString("SCORE: "+(HUD.COUNT-2),5,255);
					if(playerInfo != null)
					{
						g.drawString("LEVEL 4 HIGHSCORE: "+fileUtils.getHighestScore(Game.NAME,HUD.LEVEL),5,295);
					}
						
				}
			}
			
			if(playerInfo != null && !storeScoreStop)
			{
				playerInfo.setPlayerName(Game.NAME);
				playerInfo.setHighestLevelScore(HUD.HIGHSCORE);
				playerInfo.setTotalScore(HUD.TOTALSCORE);
				playerInfo.setPlayerLevel(HUD.LEVEL);
				fileUtils.store(playerInfo);
				storeScoreStop = true;
			}
			g.drawString("Player: "+Game.NAME, 5, 330);
			((Graphics2D)g).setStroke(new BasicStroke(6));
			g.drawRect(1,1,Game.WIDTH-2,Game.HEIGHT-25);
			g.drawString("Level: "+HUD.LEVEL,5,372);
		}
		else if(game.gameState == game.STATE.LEVEL1 && HUD.LEVEL1BOSSHEALTH<=0)
		{
			if(LevelDisplay.scoreTime<=0)
			{
				g.setColor(Color.WHITE);
				Font f = new Font("Superpower Synonym",Font.BOLD,28);
				g.setFont(f);
				if(nextLevelLevel1End) 	g.fillRect(620, 290, 150, 50);
				else g.drawRect(620, 290, 150, 50);
				if(nextLevelLevel1End) g.setColor(Color.BLACK);
				else g.setColor(Color.WHITE);
				g.drawString("Next Level", 624,325);
				
				g.setColor(Color.WHITE);
				if(menuButtonLevel1End) g.fillRect(620, 490, 150, 50);
				else g.drawRect(620, 490, 150, 50);
				if(menuButtonLevel1End) g.setColor(Color.BLACK);
				else g.setColor(Color.WHITE);
				g.drawString("MENU",665,525);
				g.setColor(Color.WHITE);
				Font f2 = new Font("Arial",Font.BOLD,40);
				g.setFont(f2);
				g.drawString("LEVEL COMPLETED",Game.WIDTH/2-197, Game.HEIGHT/2-160);
				g.drawLine(2, 250, Game.WIDTH-2, 250);
				if(playerInfo != null && !storeScoreStop5 && HUD.LEVEL == 1)
				{
					playerInfo.setPlayerName(Game.NAME);
					playerInfo.setHighestLevelScore(HUD.HIGHSCORE);
					playerInfo.setTotalScore(HUD.TOTALSCORE);
					playerInfo.setPlayerLevel(HUD.LEVEL);
					fileUtils.store(playerInfo);
					storeScoreStop5 = true;
				}
				if(HUD.LEVEL == 1)
				{
					if(HUD.SCORE == 0)
					{
						g.drawString("SCORE: 0",5,295);
						if(playerInfo != null)
								g.drawString("LEVEL 1 HIGHSCORE: "+fileUtils.getHighestScore(Game.NAME,HUD.LEVEL),5,335);
					}
					else if(HUD.SCORE == HUD.COUNT)
					{
						g.drawString("SCORE: "+(HUD.COUNT),5,295);
						if(playerInfo != null)
							g.drawString("LEVEL 1 HIGHSCORE: "+fileUtils.getHighestScore(Game.NAME,HUD.LEVEL),5,335);							
					}
					else
					{
						g.drawString("SCORE: "+(HUD.COUNT-1),5,295);
						if(playerInfo != null)
							g.drawString("LEVEL 1 HIGHSCORE: "+fileUtils.getHighestScore(Game.NAME,HUD.LEVEL),5,335);						
					}
				}
				g.drawLine(200, 250, 600, 250);
				g.drawString("Player: "+Game.NAME, 5, 380);
				g.drawString("Level: "+HUD.LEVEL,5,420);
				g.setColor(Color.YELLOW);
				
				g.drawString("UNLOCK:",5,460);
				g.setColor(Color.GREEN);
				Font unlock = new Font("Superpower Synonym",Font.BOLD,30);
				g.setFont(unlock);
				g.drawString("You have unlocked the water bullet", 5, 500);
				g.drawImage(texture.bullet,535,475,null);
				g.drawString("This is now accessible in all levels after", 5, 540);
				g.drawString("Level "+HUD.LEVEL, 5, 586);


			}
			
		}
		else if(game.gameState == game.STATE.LEVEL2 && HUD.LEVEL2BOSSHEALTH<=0)
		{
			if(LevelDisplay.scoreTime2<=0)
			{
				g.setColor(Color.WHITE);
				Font f = new Font("Superpower Synonym",Font.BOLD,28);
				g.setFont(f);
				/* Edit here */
				if(nextLevelLevel2End) 	g.fillRect(620, 290, 150, 50);
				else g.drawRect(620, 290, 150, 50);
				if(nextLevelLevel2End) g.setColor(Color.BLACK);
				else g.setColor(Color.WHITE);
				g.drawString("Next Level", 624,325);
				
				g.setColor(Color.WHITE);
				/* Edit here */
				if(menuButtonLevel2End) g.fillRect(620, 490, 150, 50);
				else g.drawRect(620, 490, 150, 50);
				if(menuButtonLevel2End) g.setColor(Color.BLACK);
				else g.setColor(Color.WHITE);
				g.drawString("MENU",665,525);
				g.setColor(Color.WHITE);
				Font f2 = new Font("Arial",Font.BOLD,40);
				g.setFont(f2);
				g.drawString("LEVEL COMPLETED",Game.WIDTH/2-197, Game.HEIGHT/2-160);
				g.drawLine(2, 250, Game.WIDTH-2, 250);
				if(playerInfo != null && !storeScoreStop6 && HUD.LEVEL == 2)
				{
					playerInfo.setPlayerName(Game.NAME);
					playerInfo.setHighestLevelScore(HUD.HIGHSCORE);
					playerInfo.setTotalScore(HUD.TOTALSCORE);
					playerInfo.setPlayerLevel(HUD.LEVEL);
					fileUtils.store(playerInfo);
					storeScoreStop6 = true;
				}
				if(HUD.LEVEL == 2)
				{
					if(HUD.SCORE == 0)
					{
						g.drawString("SCORE: 0",5,295);
						if(playerInfo != null)
						{
							g.drawString("LEVEL 2 HIGHSCORE: "+fileUtils.getHighestScore(Game.NAME,HUD.LEVEL),5,335);
						}
					}
					else if(HUD.SCORE == HUD.COUNT)
					{
						g.drawString("SCORE: "+(HUD.COUNT),5,295);
						if(playerInfo != null)
						{
							g.drawString("LEVEL 2 HIGHSCORE: "+fileUtils.getHighestScore(Game.NAME,HUD.LEVEL),5,335);
						}
					}
					else
					{
						g.drawString("SCORE: "+(HUD.COUNT-2),5,295);
						if(playerInfo != null)
						{
							g.drawString("LEVEL 2 HIGHSCORE: "+fileUtils.getHighestScore(Game.NAME,HUD.LEVEL),5,335);
						}
							
					}
				}
				g.drawLine(200, 250, 600, 250);
				g.drawString("Player: "+Game.NAME, 5, 380);
				g.drawString("Level: "+HUD.LEVEL,5,420);
				g.setColor(Color.YELLOW);
				
				g.drawString("UNLOCK:",5,460);
				g.setColor(Color.GREEN);
				Font unlock = new Font("Superpower Synonym",Font.BOLD,30);
				g.setFont(unlock);
				g.drawString("You unlocked the double bullet", 5, 500);
				g.drawImage(texture.doubleBullet,455,475,null);
				g.drawString("This is now accessible in all levels after", 5, 540);
				g.drawString("Level "+HUD.LEVEL, 5, 586);
			}
		}
		else if(game.gameState == game.STATE.LEVEL3 && HUD.LEVEL3BOSSHEALTH<=0)
		{
			if(LevelDisplay.scoreTime3<=0)
			{
				g.setColor(Color.WHITE);
				Font f = new Font("Superpower Synonym",Font.BOLD,28);
				g.setFont(f);
				/* Edit here */
				if(nextLevelLevel3End) 	g.fillRect(620, 290, 150, 50);
				else g.drawRect(620, 290, 150, 50);
				if(nextLevelLevel3End) g.setColor(Color.BLACK);
				else g.setColor(Color.WHITE);
				g.drawString("Next Level", 624,325);
				
				g.setColor(Color.WHITE);
				/* Edit here */
				if(menuButtonLevel3End) g.fillRect(620, 490, 150, 50);
				else g.drawRect(620, 490, 150, 50);
				if(menuButtonLevel3End) g.setColor(Color.BLACK);
				else g.setColor(Color.WHITE);
				g.drawString("MENU",665,525);
				g.setColor(Color.WHITE);
				Font f2 = new Font("Arial",Font.BOLD,40);
				g.setFont(f2);
				g.drawString("LEVEL COMPLETED",Game.WIDTH/2-197, Game.HEIGHT/2-160);
				g.drawLine(2, 250, Game.WIDTH-2, 250);
				if(playerInfo != null && !storeScoreStop7 && HUD.LEVEL == 3)
				{
					playerInfo.setPlayerName(Game.NAME);
					playerInfo.setHighestLevelScore(HUD.HIGHSCORE);
					playerInfo.setTotalScore(HUD.TOTALSCORE);
					playerInfo.setPlayerLevel(HUD.LEVEL);
					fileUtils.store(playerInfo);
					storeScoreStop7 = true;
				}
				if(HUD.LEVEL == 3)
				{
					if(HUD.SCORE == 0)
					{
						g.drawString("SCORE: 0",5,295);
						if(playerInfo != null)
						{
							g.drawString("LEVEL 3 HIGHSCORE: "+fileUtils.getHighestScore(Game.NAME,HUD.LEVEL),5,335);
						}
					}
					else if(HUD.SCORE == HUD.COUNT)
					{
						g.drawString("SCORE: "+(HUD.COUNT),5,295);
						if(playerInfo != null)
						{
							g.drawString("LEVEL 3 HIGHSCORE: "+fileUtils.getHighestScore(Game.NAME,HUD.LEVEL),5,335);
						}
					}
					else
					{
						g.drawString("SCORE: "+(HUD.COUNT-2),5,295);
						if(playerInfo != null)
						{
							g.drawString("LEVEL 3 HIGHSCORE: "+fileUtils.getHighestScore(Game.NAME,HUD.LEVEL),5,335);
						}
							
					}
				}
				g.drawLine(200, 250, 600, 250);
				g.drawString("Player: "+Game.NAME, 5, 380);
				g.drawString("Level: "+HUD.LEVEL,5,420);
				g.setColor(Color.YELLOW);
				
				g.drawString("UNLOCK:",5,460);
				g.setColor(Color.GREEN);
				Font unlock = new Font("Superpower Synonym",Font.BOLD,30);
				g.setFont(unlock);
				g.drawString("You unlocked the explosive bullet", 5, 500);
				g.drawImage(texture.explosiveBullet,490,475,null);
				g.drawString("This is now accessible in all levels after", 5, 540);
				g.drawString("Level "+HUD.LEVEL, 5, 586);
			}
		}
		else if(game.gameState == game.STATE.LEVEL4 && HUD.LEVEL4BOSSHEALTH<=0)
		{
			if(LevelDisplay.scoreTime4<=0)
			{
				g.setColor(Color.WHITE);
				Font f = new Font("Superpower Synonym",Font.BOLD,76);
				g.setFont(f);
				/* Edit here */
				if(gameCompleteLevel4End) g.fillRect(500, 365, 250, 80);
				else g.drawRect(500,365, 250, 80);
				if(gameCompleteLevel4End) g.setColor(Color.BLACK);
				else g.setColor(Color.WHITE);
				g.drawString("NEXT", 546,430);
				
				g.setColor(Color.WHITE);
				/* Edit here */
				g.setColor(Color.WHITE);
				Font f2 = new Font("Arial",Font.BOLD,40);
				g.setFont(f2);
				g.drawString("LEVEL COMPLETED",Game.WIDTH/2-197, Game.HEIGHT/2-160);
				g.drawLine(2, 250, Game.WIDTH-2, 250);
				if(playerInfo != null && !storeScoreStop8 && HUD.LEVEL == 4)
				{
					playerInfo.setPlayerName(Game.NAME);
					playerInfo.setHighestLevelScore(HUD.HIGHSCORE);
					playerInfo.setTotalScore(HUD.TOTALSCORE);
					playerInfo.setPlayerLevel(HUD.LEVEL);
					fileUtils.store(playerInfo);
					storeScoreStop8 = true;
				}
				if(HUD.LEVEL == 4)
				{
					if(HUD.SCORE == 0)
					{
						g.drawString("SCORE: 0",5,295);
						if(playerInfo != null)
						{
							g.drawString("LEVEL 4 HIGHSCORE: "+fileUtils.getHighestScore(Game.NAME,HUD.LEVEL),5,335);
						}
					}
					else if(HUD.SCORE == HUD.COUNT)
					{
						g.drawString("SCORE: "+(HUD.COUNT),5,295);
						if(playerInfo != null)
						{
							g.drawString("LEVEL 4 HIGHSCORE: "+fileUtils.getHighestScore(Game.NAME,HUD.LEVEL),5,335);
						}
					}
					else
					{
						g.drawString("SCORE: "+(HUD.COUNT-2),5,295);
						if(playerInfo != null)
						{
							g.drawString("LEVEL 4 HIGHSCORE: "+fileUtils.getHighestScore(Game.NAME,HUD.LEVEL),5,335);
						}
							
					}
				}
				g.drawLine(200, 250, 600, 250);
				g.drawString("Player: "+Game.NAME, 5, 380);
				g.drawString("Level: "+HUD.LEVEL,5,420);
				g.setColor(Color.YELLOW);
				
				g.drawString("UNLOCK:",5,460);
				g.setColor(Color.GREEN);
				Font unlock = new Font("Superpower Synonym",Font.BOLD,30);
				g.setFont(unlock);
				g.drawString("You unlocked the shotgun bullet", 5, 500);
				g.drawImage(texture.shotgunBullet,490,475,null);
				g.drawString("You completed the game,how did you escape", 5, 540);
				g.drawString("Congratulations", 5, 586);
			}
		}
	}
}
