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
	private PlayerInfo playerInfo;
	FileUtils fileUtils = new FileUtils();
	public boolean storeScoreStop = false;
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
		if(contains(mx,my,Game.WIDTH/2-60,Game.HEIGHT/2,101,51) && game.gameState == game.STATE.NAMEPANEL)
		{
			// OK BUTTON
			game.gameState = game.STATE.MENU;
		}
		else if(contains(mx,my,game.WIDTH/2-115,game.HEIGHT/2-60,200,100) && game.gameState == game.STATE.MENU)
		{
			//CUSTOMIZE BUTTON
			game.gameState = game.STATE.CUSTOMIZE;
		}
		else if(contains(mx,my,game.WIDTH/2-130,game.HEIGHT/2+280,260,80) && game.gameState == game.STATE.CUSTOMIZE)
		{
			//BACK BUTTON IN CUSTOMIZE
			game.gameState = game.STATE.MENU;
		}
		else if(contains(mx, my,game.WIDTH/2-115,game.HEIGHT/2-188,200,100) && game.gameState == game.STATE.MENU)
		{
			// PLAY BUTTON
			game.gameState = game.STATE.SELECTLEVEL;
		}
		else if(contains(mx,my,game.WIDTH/2-115,game.HEIGHT/2+70,200,100) && game.gameState == game.STATE.MENU)
		{
			//HELP BUTTON
			game.gameState = game.STATE.HELP;
		}
		else if(contains(mx,my,game.WIDTH/2-120,game.HEIGHT/2+250,250,100) && game.gameState == game.STATE.HELP)
		{
			// BACK BUTTON in HELP
			game.gameState = game.STATE.MENU;
		}
		else if(contains(mx,my,game.WIDTH/2-120,game.HEIGHT/2+230,250,100) && game.gameState == game.STATE.SELECTLEVEL)
		{
			// BACK BUTTON IN LEVEL CHOOSE
			game.gameState = game.STATE.MENU;
		}
		else if(contains(mx,my,game.WIDTH/2-115,game.HEIGHT/2+200,200,100) && game.gameState == game.STATE.MENU)
		{
			// EXIT BUTTON
			System.exit(1);
		}
		else if(contains(mx,my,50,115,261,261) && game.gameState == game.STATE.SELECTLEVEL)
		{
			//LEVEL 1
			game.gameState = game.STATE.LEVEL1;
			HUD.HIGHSCORE = 0;
			storeScoreStop = false;
			game.upgrades.box1Row1Cost = 1000;
			game.upgrades.box2Row1Cost = 2000;
			game.upgrades.isBullet = false;
			game.upgrades.isSplitBullet = false;
			game.upgrades.isDualPistolBullet = false;
			game.upgrades.isShotgunBullet = false;
		}
		else if(contains(mx,my,490,115,261,261) && game.isLevel1Complete && game.gameState == game.STATE.SELECTLEVEL)
		{
			// LEVEL 2
			game.gameState = game.STATE.LEVEL2;
			storeScoreStop = false;
			game.upgrades.box1Row1Cost = 1000;
			game.upgrades.box2Row1Cost = 2000;
			game.upgrades.isBullet = false;
			game.upgrades.isSplitBullet = false;
			game.upgrades.isDualPistolBullet = false;
			game.upgrades.isShotgunBullet = false;
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
			game.gameState = game.STATE.LEVEL3;	
			storeScoreStop = false;
			HUD.HIGHSCORE = 0;
			game.upgrades.box1Row1Cost = 1000;
			game.upgrades.box2Row1Cost = 2000;
			game.upgrades.isBullet = false;
			game.upgrades.isSplitBullet = false;
			game.upgrades.isDualPistolBullet = false;
			game.upgrades.isShotgunBullet = false;
			//HUD.EXPLODERHEALTH = 20;
		}
		else if(contains(mx,my,490, 400, 261, 261) &&  game.isLevel3Complete && game.gameState == game.STATE.SELECTLEVEL)
		{
			// LEVEL 4
			game.gameState = game.STATE.LEVEL4;
			storeScoreStop = false;
			HUD.HIGHSCORE = 0;
			HUD.EXPLODERHEALTH = 20;
			game.upgrades.box1Row1Cost = 1000;
			game.upgrades.box2Row1Cost = 2000;
			game.upgrades.isBullet = false;
			game.upgrades.isSplitBullet = false;
			game.upgrades.isDualPistolBullet = false;
			game.upgrades.isShotgunBullet = false;
		}
		else if(contains(mx,my,game.WIDTH/2-370,game.HEIGHT/2+250,740,100) && game.gameState == game.STATE.DEADSCREEN)
		{
			//BACK TO MENU BUTTON
			game.gameState = game.STATE.MENU;
		}		
		else if(contains(mx,my,620, 290, 150, 50) && game.gameState == game.STATE.LEVEL1 && HUD.LEVEL1BOSSHEALTH<=0)
		{
			// END OF LEVEL 1 
			game.gameState = game.STATE.LEVEL2;
			storeScoreStop = false;
			game.upgrades.box1Row1Cost = 1000;
			game.upgrades.box2Row1Cost = 2000;
			game.upgrades.isBullet = false;
			game.upgrades.isSplitBullet = false;
			game.upgrades.isDualPistolBullet = false;
			game.upgrades.isShotgunBullet = false;
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
		}
		else if(contains(mx,my,620, 490, 150, 50) && game.gameState == game.STATE.LEVEL1 && HUD.LEVEL1BOSSHEALTH<=0)
		{
			// BACK TO MENU FROM END OF LEVEL 1
			game.gameState = game.STATE.MENU;
		}
		else if(contains(mx,my,264, 180, 260, 120) && game.gameState == game.STATE.HELP)
		{
			// STORY BUTTON 
			game.gameState = game.STATE.STORY;
		}
		else if(contains(mx,my,264, 330, 260, 120) && game.gameState == game.STATE.HELP)
		{
			// HOW TO PLAY BUTTON
			game.gameState = game.STATE.HOWTOPLAY;
		}
		else if(contains(mx,my,20,120,50,120) && game.gameState == game.STATE.STORY)
		{
			// BACK FROM STORY BUTTON
			game.gameState = game.STATE.HELP;
		}
		else if(contains(mx,my,20+680,120,50,120) && game.gameState == game.STATE.STORY)
		{
			// MENU BUTTON FROM STORY
			game.gameState = game.STATE.MENU;
		}
		else if(contains(mx,my,game.WIDTH/2+50,game.HEIGHT/2+250,270,100) && game.gameState == Game.STATE.HOWTOPLAY)
		{
			// MENU BUTTON FOR HOW TO PLAY
			game.gameState = game.STATE.MENU;
		}
		else if(contains(mx,my,game.WIDTH/2-300,game.HEIGHT/2+250,250,100) && game.gameState == Game.STATE.HOWTOPLAY)
		{
			// BACK BUTTON FOR HOW TO PLAY
			game.gameState = game.STATE.HELP;
		}
		else if(contains(mx,my,620, 290, 150, 50) && game.gameState == game.STATE.LEVEL2 && HUD.LEVEL2BOSSHEALTH<=0)
		{
			// END OF LEVEL 2
			game.gameState = game.STATE.LEVEL3;
			storeScoreStop = false;
			game.upgrades.isBullet = false;
			game.upgrades.isSplitBullet = false;
			game.upgrades.isDualPistolBullet = false;
			game.upgrades.isShotgunBullet = false;
			game.upgrades.box1Row1Cost = 1000;
			game.upgrades.box2Row1Cost = 2000;
			handler.clearAll();
			game.isBossFight2 = false;
			game.player.x = 385;
			HUD.HIGHSCORE = 0;
			HUD.HEALTH = 100;
			HUD.SCORE = 0;
			HUD.COUNT = 0;
			HUD.LEVEL2BOSSHEALTH = 500;
		}
		else if(contains(mx,my,620, 290, 150, 50) && game.gameState == game.STATE.LEVEL3 && HUD.LEVEL3BOSSHEALTH<=0)
		{
			// END OF LEVEL 3
			game.gameState = game.STATE.LEVEL4;
			storeScoreStop = false;
			game.upgrades.isBullet = false;
			game.upgrades.isSplitBullet = false;
			game.upgrades.isDualPistolBullet = false;
			game.upgrades.isShotgunBullet = false;
			game.upgrades.box1Row1Cost = 1000;
			game.upgrades.box2Row1Cost = 2000;
			handler.clearAll();
			game.isBossFight3 = false;
			game.player.x = 385;
			HUD.HEALTH = 100;
			HUD.SCORE = 0;
			HUD.COUNT = 0;
			HUD.HIGHSCORE = 0;
			HUD.LEVEL3BOSSHEALTH = 550;
			HUD.EXPLODERHEALTH = 20;
		}
		else if(contains(mx,my,620, 490, 150, 50) && game.gameState == game.STATE.LEVEL2 && HUD.LEVEL2BOSSHEALTH<=0)
		{
			// END OF LEVEL 2
			game.gameState = game.STATE.MENU;
		}
		else if(contains(mx,my,620, 490, 150, 50) && game.gameState == game.STATE.LEVEL3 && HUD.LEVEL3BOSSHEALTH<=0)
		{
			// END OF LEVEL 3
			game.gameState = game.STATE.MENU;
		}
		else if(contains(mx,my,620, 490, 150, 50) && game.gameState == game.STATE.LEVEL4 && HUD.LEVEL4BOSSHEALTH<=0)
		{
			// END OF LEVEL 4
			game.gameState = game.STATE.MENU;
		}
		else if(contains(mx,my,620, 290, 150, 50) && game.gameState == game.STATE.LEVEL4 && HUD.LEVEL4BOSSHEALTH<=0)
		{
			game.gameState = game.STATE.GAMECOMPLETE;
			game.player.x = 370;
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
//		if(stopScoreChange1 || stopScoreChange2)
//		{
//			storeScoreStop = false;
//		}
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
		if(contains(mx,my,game.WIDTH/2-130,game.HEIGHT/2+280,260,80) && game.gameState == game.STATE.CUSTOMIZE) backButtonCustomize = true;
		else backButtonCustomize = false;
		if(contains(mx,my,game.WIDTH/2-370,game.HEIGHT/2+250,740,100) && game.gameState == game.STATE.DEADSCREEN) deathScreenButton = true;
		else deathScreenButton = false;
		if(contains(mx,my,264, 180, 260, 120) && game.gameState == game.STATE.HELP) storyButton = true;
		else storyButton = false;
		if(contains(mx,my,620, 490, 150, 50)  && game.gameState == game.STATE.LEVEL1 && HUD.LEVEL1BOSSHEALTH<=0) menuButtonLevel1End = true;
		else menuButtonLevel1End = false;
		if(contains(mx,my,620, 290, 150, 50) && game.gameState == game.STATE.LEVEL1 && HUD.LEVEL1BOSSHEALTH<=0) nextLevelLevel1End = true;
		else nextLevelLevel1End = false;
		if(contains(mx,my,264, 330, 260, 120) && game.gameState == game.STATE.HELP) howtoPlayButton = true;
		else howtoPlayButton = false;
		if(contains(mx,my,20,120,50,120) && game.gameState == game.STATE.STORY) storyButtonBACK = true;
		else storyButtonBACK = false;
		if(contains(mx,my,20+680,120,50,120) && game.gameState == game.STATE.STORY) storyButtonMENU = true;
		else storyButtonMENU = false;
		if(contains(mx,my,game.WIDTH/2-300,game.HEIGHT/2+250,250,100) && game.gameState == Game.STATE.HOWTOPLAY) howtoPlayButtonBACK = true;
		else howtoPlayButtonBACK = false;
		if(contains(mx,my,game.WIDTH/2+50,game.HEIGHT/2+250,270,100) && game.gameState == Game.STATE.HOWTOPLAY) howtoPlayButtonMENU = true;
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
		if(contains(mx,my,620, 490, 150, 50) && game.gameState == game.STATE.LEVEL4 && HUD.LEVEL4BOSSHEALTH<=0) menuButtonLevel4End = true;
		else menuButtonLevel4End = false;
		if(contains(mx,my,620, 290, 150, 50) && game.gameState == game.STATE.LEVEL4 && HUD.LEVEL4BOSSHEALTH<=0) gameCompleteLevel4End = true;
		else gameCompleteLevel4End = false;
		
	}
	/* This is the mouseReleased method which is not in use right now */
	public void mouseReleased(MouseEvent e) {}
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
			g.drawString("Money: "+fileUtils.getTotalScoreForAPlayer(Game.NAME),3,Game.HEIGHT-30);
			g.setColor(Color.BLACK);
			g.drawString("Version 0.6",700,20);
			g.drawString("Welcome "+Game.NAME+"!",5,20);
		}
		else if(game.gameState == game.STATE.CUSTOMIZE)
		{
			g.setColor(Color.YELLOW);
			Font customizeTitle = new Font("Superpower Synonym",Font.BOLD,175);
			g.setFont(customizeTitle);
			g.drawString("CUSTOMIZE",5,130);
			Font backButton = new Font("Arial",Font.BOLD,88);
			g.setFont(backButton);
			if(backButtonCustomize) g.setColor(deepPink);
			else g.setColor(Color.YELLOW);
			if(backButtonCustomize) g.fillRect(game.WIDTH/2-130,game.HEIGHT/2+280,260,80);
			else g.drawRect(game.WIDTH/2-130,game.HEIGHT/2+280,260,80);
			if(backButtonCustomize) g.setColor(Color.GREEN);
			else g.setColor(Color.YELLOW);
			g.drawString("BACK",game.WIDTH/2-130,game.HEIGHT/2+350);	
			g.setColor(Color.YELLOW);
			Font totalScore = new Font("Arial",Font.BOLD,18);
			g.setFont(totalScore);
			g.drawString("Money: "+fileUtils.getTotalScoreForAPlayer(Game.NAME),3,Game.HEIGHT-30);
		}
		else if(game.gameState == game.STATE.HELP)
		{
			g.setColor(Color.BLACK);
			Font f5 = new Font("TimesNewRoman",Font.BOLD,88);
			g.setFont(f5);
			g.drawString("HELP",game.WIDTH/2-118,75);
			if(helpColorBACK) g.setColor(springGreen);
			else g.setColor(Color.BLACK);
			if(helpColorBACK) g.fillRect(game.WIDTH/2-120,game.HEIGHT/2+250,250,100);
			else g.drawRect(game.WIDTH/2-120,game.HEIGHT/2+250,250,100);
			if(helpColorBACK) g.setColor(Color.RED);
			else g.setColor(Color.BLACK);
			g.drawString("BACK",game.WIDTH/2-118,game.HEIGHT/2+330);
			
			if(storyButton) g.setColor(purple);
			else g.setColor(Color.BLACK);
			if(storyButton) g.fillRect(264, 180, 260, 120);
			else g.fillRect(264, 180, 260, 120);
			Font f4 = new Font("TimesNewRoman",Font.BOLD,70);
			g.setFont(f4);
			if(storyButton) g.setColor(Color.WHITE);
			else g.setColor(Color.RED);
			g.drawString("STORY", 275, 260);
			
			if(howtoPlayButton) g.setColor(Color.BLACK);
			else g.setColor(Color.MAGENTA);
			if(howtoPlayButton) g.fillRect(264, 330, 260, 120);
			else g.fillRect(264, 330, 260, 120);
			Font f6 = new Font("TimesNewRoman",Font.BOLD,50);
			g.setFont(f6);
			if(howtoPlayButton) g.setColor(Color.CYAN);
			else g.setColor(Color.WHITE);
			g.drawString("HOW TO", 285, 380);
			g.drawString("PLAY", 325, 440);
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
			g.setColor(Color.BLACK);
			Font f5 = new Font("TimesNewRoman",Font.BOLD,88);
			g.setFont(f5);
			g.drawString("HOW TO PLAY",game.WIDTH/2-320,75);
			if(howtoPlayButtonBACK) g.setColor(coral);
			else g.setColor(Color.BLACK);
			if(howtoPlayButtonBACK) g.fillRect(game.WIDTH/2-300,game.HEIGHT/2+250,250,100);
			else g.drawRect(game.WIDTH/2-300,game.HEIGHT/2+250,250,100);
			if(howtoPlayButtonBACK) g.setColor(Color.BLUE);
			else g.setColor(Color.BLACK);
			g.drawString("BACK",game.WIDTH/2-297,game.HEIGHT/2+330);			
			
			g.setColor(Color.BLACK);
			if(howtoPlayButtonMENU) g.setColor(purple);
			else g.setColor(Color.BLACK);
			if(howtoPlayButtonMENU) g.fillRect(game.WIDTH/2+50,game.HEIGHT/2+250,270,100);
			else g.drawRect(game.WIDTH/2+50,game.HEIGHT/2+250,270,100);
			if(howtoPlayButtonMENU) g.setColor(Color.PINK);
			else g.setColor(Color.BLACK);
			g.drawRect(game.WIDTH/2+50,game.HEIGHT/2+250,270,100);
			g.drawString("MENU",game.WIDTH/2+53,game.HEIGHT/2+330);
			
			g.setColor(Color.BLACK);
			Font instructionFont = new Font("Arial",Font.BOLD,24);
			g.setFont(instructionFont);
			g.drawImage(texture.rightArrowKey,25,125,50,50,null);
			g.drawString("Move Right ",80,155);
			g.drawImage(texture.leftArrowKey,25,175,50,50,null);
			g.drawString("Move Left ",80,209);
			g.drawImage(texture.space,25,230,50,50,null);
			g.drawString("Shoot Bullets ",80,265);
			g.drawImage(texture.waterbucket,25,280,50,50,null);
			g.drawString("Collect Water", 80, 300);
			g.drawString("To get Bullets", 80, 325);
			g.drawImage(texture.fireball, 25,340,50,50,null);
			g.drawString("Avoid Fireballs", 80, 375);
			g.drawImage(texture.bomb,25,400,50,50,null);
			g.drawString("These will blow", 80, 420);
			g.drawString("you up", 115, 440);
			g.drawImage(texture.bronzeCoin,25,460,50,50,null);
			g.drawString("Increases score", 80,480);
			g.drawString("by 100", 115, 500);
			g.drawImage(texture.silverCoin,25,520,50,50,null);
			g.drawString("Increases score", 80,540);
			g.drawString("by 250", 115, 560);
			g.drawImage(texture.goldCoin,25,580,50,50,null);
			g.drawString("Increases score", 80,600);
			g.drawString("by 500", 115, 620);
			g.drawImage(texture.rightMagmaRock,250,125,50,50,null);
			g.drawString("Avoid these",298,145);
			g.drawString("Rocks",328,165);
			g.drawImage(texture.undergroundEnemy,250,175,50,50,null);
			g.drawString("Can you",298,195);
			g.drawString("See him?", 298,218);

		}
		else if(game.gameState == game.STATE.SELECTLEVEL)
		{
			int level = fileUtils.getThePlayerLevel(Game.NAME);
			if(level == 2) game.isLevel1Complete  = true;
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
			icon = new ImageIcon(getClass().getResource("QuestionDisplay.png"));
			questionDisplay = icon.getImage();
			
			g.setColor(Color.GRAY);
			g.fillRect(50,115,260,260);
			g.setColor(Color.WHITE);
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
			
			g.setColor(Color.CYAN);
			Font difLevDisplay = new Font("TimesNewRoman",Font.BOLD,18);
			g.setFont(difLevDisplay);
			g.drawString("LEVEL 1",144,396);
			
			g.setColor(Color.GRAY);
			g.fillRect(490,115,260,260);
			if(game.isLevel1Complete) g.drawImage(game.level2Display,490,115,260,260,null);
			else g.drawImage(questionDisplay,490,115,260,260,null);
			g.setColor(Color.CYAN);
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
				g.drawRect(10,570,780,75);
				g.setFont(f3);
				g.drawString("BACK TO MENU", 200, 625);
				
			}			
		}
		else if(game.gameState == game.STATE.DEADSCREEN)
		{
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
			((Graphics2D)g).setStroke(new BasicStroke(6));
			g.drawRect(0, 200, Game.WIDTH,400);
			Font count = new Font("TimesNewRoman",Font.BOLD,40);
			g.setFont(count);
			if(playerInfo != null && !storeScoreStop && HUD.LEVEL == 1)
			{
				//System.out.println("Storing the Player Score:"+HUD.HIGHSCORE+" : HUD.LEVEL : "+HUD.LEVEL);
				playerInfo.setPlayerName(Game.NAME);
				playerInfo.setHighestLevelScore(HUD.HIGHSCORE);
				playerInfo.setTotalScore(HUD.TOTALSCORE);
				playerInfo.setPlayerLevel(HUD.LEVEL);
				fileUtils.store(playerInfo);
				storeScoreStop = true;
			}
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
			if(playerInfo != null && !storeScoreStop && HUD.LEVEL == 2)
			{
				playerInfo.setPlayerName(Game.NAME);
				playerInfo.setHighestLevelScore(HUD.HIGHSCORE);
				playerInfo.setTotalScore(HUD.TOTALSCORE);
				playerInfo.setPlayerLevel(HUD.LEVEL);
				fileUtils.store(playerInfo);
				storeScoreStop = true;
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
			if(playerInfo != null && !storeScoreStop && HUD.LEVEL == 3)
			{
				playerInfo.setPlayerName(Game.NAME);
				playerInfo.setHighestLevelScore(HUD.HIGHSCORE);
				playerInfo.setTotalScore(HUD.TOTALSCORE);
				playerInfo.setPlayerLevel(HUD.LEVEL);
				fileUtils.store(playerInfo);
				storeScoreStop = true;
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
			if(playerInfo != null && !storeScoreStop && HUD.LEVEL == 4)
			{
				playerInfo.setPlayerName(Game.NAME);
				playerInfo.setHighestLevelScore(HUD.HIGHSCORE);
				playerInfo.setTotalScore(HUD.TOTALSCORE);
				playerInfo.setPlayerLevel(HUD.LEVEL);
				fileUtils.store(playerInfo);
				storeScoreStop = true;
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
				if(playerInfo != null && !storeScoreStop && HUD.LEVEL == 1)
				{
					//System.out.println("Storing the Player Score:"+HUD.HIGHSCORE+" : HUD.LEVEL : "+HUD.LEVEL);
					playerInfo.setPlayerName(Game.NAME);
					playerInfo.setHighestLevelScore(HUD.HIGHSCORE);
					playerInfo.setTotalScore(HUD.TOTALSCORE);
					playerInfo.setPlayerLevel(HUD.LEVEL);
					fileUtils.store(playerInfo);
					storeScoreStop = true;
				}
				if(HUD.LEVEL == 1)
				{
					System.out.println("Storing the Player Score:"+fileUtils.getHighestScore(Game.NAME,HUD.LEVEL)+" : HUD.LEVEL : "+HUD.LEVEL);
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
				if(playerInfo != null && !storeScoreStop && HUD.LEVEL == 2)
				{
					//System.out.println("Storing the Player Score:"+HUD.HIGHSCORE+" : HUD.LEVEL : "+HUD.LEVEL);
					playerInfo.setPlayerName(Game.NAME);
					playerInfo.setHighestLevelScore(HUD.HIGHSCORE);
					playerInfo.setTotalScore(HUD.TOTALSCORE);
					playerInfo.setPlayerLevel(HUD.LEVEL);
					fileUtils.store(playerInfo);
					storeScoreStop = true;
				}
				if(HUD.LEVEL == 2)
				{
					System.out.println("Getting the Player Score:"+fileUtils.getHighestScore(Game.NAME,HUD.LEVEL)+" : HUD.LEVEL : "+HUD.LEVEL);
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
				if(playerInfo != null && !storeScoreStop && HUD.LEVEL == 3)
				{
					playerInfo.setPlayerName(Game.NAME);
					playerInfo.setHighestLevelScore(HUD.HIGHSCORE);
					playerInfo.setTotalScore(HUD.TOTALSCORE);
					playerInfo.setPlayerLevel(HUD.LEVEL);
					fileUtils.store(playerInfo);
					storeScoreStop = true;
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
				Font f = new Font("Superpower Synonym",Font.BOLD,28);
				g.setFont(f);
				/* Edit here */
				if(gameCompleteLevel4End) g.fillRect(620, 290, 150, 50);
				else g.drawRect(620, 290, 150, 50);
				if(gameCompleteLevel4End) g.setColor(Color.BLACK);
				else g.setColor(Color.WHITE);
				g.drawString("Next Level", 624,325);
				
				g.setColor(Color.WHITE);
				/* Edit here */
				if(menuButtonLevel4End) g.fillRect(620, 490, 150, 50);
				else g.drawRect(620, 490, 150, 50);
				if(menuButtonLevel4End) g.setColor(Color.BLACK);
				else g.setColor(Color.WHITE);
				g.drawString("MENU",665,525);
				g.setColor(Color.WHITE);
				Font f2 = new Font("Arial",Font.BOLD,40);
				g.setFont(f2);
				g.drawString("LEVEL COMPLETED",Game.WIDTH/2-197, Game.HEIGHT/2-160);
				g.drawLine(2, 250, Game.WIDTH-2, 250);
				if(playerInfo != null && !storeScoreStop && HUD.LEVEL == 4)
				{
					//System.out.println("Storing the Player Score:"+HUD.HIGHSCORE+" : HUD.LEVEL : "+HUD.LEVEL);
					playerInfo.setPlayerName(Game.NAME);
					playerInfo.setHighestLevelScore(HUD.HIGHSCORE);
					playerInfo.setTotalScore(HUD.TOTALSCORE);
					playerInfo.setPlayerLevel(HUD.LEVEL);
					fileUtils.store(playerInfo);
					storeScoreStop = true;
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
