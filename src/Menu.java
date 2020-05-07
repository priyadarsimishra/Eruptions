import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
/* This class is responsible for displaying the Menu 
 * and it handles the Death Screen and it also 
 * has a MouseAdapter to add mouse input for mouse input */
public class Menu extends MouseAdapter
{
	private Game game;
	private ObjectHandler handler;
	private Spawn spawner;
	private SpriteTextures texture;
	public boolean stopScoreChange1 = false;
	public boolean stopScoreChange2 = false;
	private PlayerInfo playerInfo;
	FileUtils fileUtils = new FileUtils();
	public boolean storeScoreStop = false;
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
		else if(contains(mx, my,285,300,200,100) && game.gameState == game.STATE.MENU)
		{
			// PLAY BUTTON
			game.gameState = game.STATE.SELECTLEVEL;
		}
		else if(contains(mx,my,game.WIDTH/2-115,game.HEIGHT/2+50,200,100) && game.gameState == game.STATE.MENU)
		{
			//HELP BUTTON
			game.gameState = game.STATE.HELP;
		}
		else if(contains(mx,my,game.WIDTH/2-120,game.HEIGHT/2+250,250,100) && game.gameState == game.STATE.HELP)
		{
			// BACK BUTTON
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
		else if(contains(mx,my,50,150,121,121) && game.gameState == game.STATE.SELECTLEVEL)
		{
			//LEVEL 1
			game.gameState = game.STATE.LEVEL1;
		}
		else if(contains(mx,my,220,150,121,121) && game.isLevel1Complete)
		{
			// LEVEL 2
			game.gameState = game.STATE.LEVEL2;	
		}
		else if(contains(mx,my,game.WIDTH/2-370,game.HEIGHT/2+250,740,100) && game.gameState == game.STATE.DEADSCREEN)
		{
			//BACK TO MENU BUTTON
			game.gameState = game.STATE.MENU;
			handler.clearAll();
			game.isBossFight = false;
		}		
		else if(contains(mx,my,620, 290, 150, 50) && game.gameState == game.STATE.LEVEL1 && HUD.LEVEL1BOSSHEALTH<=0)
		{
			// END OF LEVEL 1 
			game.gameState = game.STATE.LEVEL2;
			game.isBossFight = false;
			game.player.x = 385;
			HUD.HEALTH = 100;
			HUD.SCORE = 0;
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
			// BACK TO HELP BUTTON
			game.gameState = game.STATE.HELP;
		}
		else if(contains(mx,my,20+680,120,50,120) && game.gameState == game.STATE.STORY)
		{
			// MENU BUTTON FROM STORY
			game.gameState = game.STATE.MENU;
		}
		else if(contains(mx,my,game.WIDTH/2+50,game.HEIGHT/2+250,270,100) && game.gameState == Game.STATE.HOWTOPLAY)
		{
			// BACK BUTTON FOR HOW TO PLAY
			game.gameState = game.STATE.MENU;
		}
		else if(contains(mx,my,game.WIDTH/2-300,game.HEIGHT/2+250,250,100) && game.gameState == Game.STATE.HOWTOPLAY)
		{
			// BACK BUTTON FOR HOW TO PLAY
			game.gameState = game.STATE.HELP;
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
//		if(stopScoreChange1 || stopScoreChange2)
//		{
//			storeScoreStop = false;
//		}
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
			g.setColor(Color.RED);
			Font font = new Font("Superpower Synonym",Font.BOLD,180);
			g.setFont(font);
			g.drawString(Game.TITLE,42,160);
			
			g.setColor(Color.RED);
			g.drawRect(game.WIDTH/2-115,game.HEIGHT/2-100,200,100);
			
			Font f1 = new Font("Arial",Font.BOLD,60);
			g.setFont(f1);
			g.drawString("PLAY", game.WIDTH/2-93, game.HEIGHT/2-30);
			
			g.setColor(Color.RED);
			g.drawRect(game.WIDTH/2-115,game.HEIGHT/2+50,200,100);
			Font helpfont = new Font("Arial",Font.BOLD,60);
			g.setFont(helpfont);
			g.drawString("HELP", game.WIDTH/2-93, game.HEIGHT/2+120);
			
			
			g.setColor(Color.RED);
			g.drawRect(game.WIDTH/2-115,game.HEIGHT/2+200,200,100);
			
			Font f2 = new Font("Arial",Font.BOLD,60);
			g.setFont(f2);
			g.drawString("EXIT", game.WIDTH/2-85, game.HEIGHT/2+275);
			
			Font f = new Font("Arial",Font.BOLD,18);
			g.setFont(f);
			g.drawString("Money: "+fileUtils.getTotalScoreForAPlayer(Game.NAME),3,Game.HEIGHT-30);
			g.drawString("Version 0.3",700,20);
			g.drawString("Welcome "+Game.NAME+"!",5,20);
		}
		else if(game.gameState == game.STATE.HELP)
		{
			g.setColor(Color.BLACK);
			Font f5 = new Font("TimesNewRoman",Font.BOLD,88);
			g.setFont(f5);
			g.drawRect(game.WIDTH/2-120,game.HEIGHT/2+250,250,100);
			g.drawString("BACK",game.WIDTH/2-118,game.HEIGHT/2+330);
			g.drawString("HELP",game.WIDTH/2-118,75);
			
			g.setColor(Color.RED);
			g.fillRect(264, 180, 260, 120);
			g.setColor(Color.WHITE);
			Font f4 = new Font("TimesNewRoman",Font.BOLD,70);
			g.setFont(f4);
			g.drawString("STORY", 275, 260);
			
			g.setColor(Color.BLACK);
			g.fillRect(264, 330, 260, 120);
			g.setColor(Color.WHITE);
			Font f6 = new Font("TimesNewRoman",Font.BOLD,50);
			g.setFont(f6);
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
			g.setColor(Color.WHITE);
			g.drawRect(20,120,50,120);
			g.drawString("B",36,143);
			g.drawString("A",36,173);
			g.drawString("C",36,203);
			g.drawString("K",36,233);
			g.drawRect(20+680,120,50,120);
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
			g.drawRect(game.WIDTH/2-300,game.HEIGHT/2+250,250,100);
			g.drawString("BACK",game.WIDTH/2-297,game.HEIGHT/2+330);
			g.drawString("HOW TO PLAY",game.WIDTH/2-320,75);
			
			g.drawRect(game.WIDTH/2+50,game.HEIGHT/2+250,270,100);
			g.drawString("MENU",game.WIDTH/2+53,game.HEIGHT/2+330);
			
			Font instructionFont = new Font("Arial",Font.BOLD,24);
			g.setFont(instructionFont);
			g.drawImage(texture.rightArrow,25,125,50,50,null);
			g.drawString("Move Right ",80,155);
			g.drawImage(texture.leftArrow,25,175,50,50,null);
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
			g.setColor(Color.GRAY);
			g.fillRect(50,150,120,120);
			g.setColor(Color.WHITE);
			Font lev = new Font("TimesNewRoman", Font.BOLD,120);
			g.setFont(lev);
			g.drawString("Select Level",45,100);
			g.drawImage(game.level1,50,150,120,120,null);
	        ((Graphics2D)g).setStroke(new BasicStroke(3));
			g.drawRect(50,150,121,121);
			
			Font levChoose = new Font("TimesNewRoman",Font.BOLD,88);
			g.setFont(levChoose);
			g.drawString("BACK",game.WIDTH/2-118,game.HEIGHT/2+310);
			g.drawRect(game.WIDTH/2-120,game.HEIGHT/2+230,250,100);
			
			g.setColor(Color.CYAN);
			Font difLevDisplay = new Font("TimesNewRoman",Font.BOLD,30);
			g.setFont(difLevDisplay);
			g.drawString("Level 1",55,300);
			
			g.setColor(Color.GRAY);
			g.fillRect(220,150,120,120);
			g.setColor(Color.CYAN);
			g.setFont(difLevDisplay);
			g.drawString("Level 2",225,300);
			g.setColor(Color.WHITE);
			g.drawRect(220,150,121,121);
			Font name = new Font("Arial",Font.BOLD,18);
			g.setFont(name);
			g.drawString("Player: "+Game.NAME,5,770);
		}
		else if(game.gameState == game.STATE.DEADSCREEN)
		{
			g.setColor(Color.YELLOW);
			Font f3 = new Font("Superpower Synonym", Font.BOLD,200);
			g.setFont(f3);
			g.drawString("YOU LOST!",Game.WIDTH/2-382,170);
			
			g.drawRect(game.WIDTH/2-370,game.HEIGHT/2+250,740,100);
			Font returnTo = new Font("Superpower Synonym",Font.BOLD,88);
			g.setFont(returnTo);
			g.drawString("Return To",game.WIDTH/2-322,game.HEIGHT/2+330);
			g.drawString("MENU",game.WIDTH/2+115,game.HEIGHT/2+330);
			
			((Graphics2D)g).setStroke(new BasicStroke(6));
			g.drawRect(0, 200, Game.WIDTH,400);
			Font count = new Font("TimesNewRoman",Font.BOLD,40);
			g.setFont(count);
			if(playerInfo != null && !storeScoreStop)
			{
				System.out.println("Storing the Player Details:");
				playerInfo.setPlayerName(Game.NAME);
				playerInfo.setHighestIndividualScore(HUD.HIGHSCORE);
				playerInfo.setTotalIndividualScore(HUD.TOTALSCORE);
				playerInfo.setPlayerLevel(HUD.LEVEL);
				fileUtils.store(playerInfo);
				storeScoreStop = true;
			}
			if(HUD.SCORE == 0)
			{
				g.drawString("SCORE: 0",5,255);
				if(playerInfo != null)
					g.drawString("LEVEL 1 HIGHSCORE: "+fileUtils.getHighestScore(Game.NAME),5,295);
			}
			else
			{
				g.drawString("SCORE: "+(HUD.COUNT-2),5,255);
				if(playerInfo != null)
					g.drawString("LEVEL 1 HIGHSCORE: "+fileUtils.getHighestScore(Game.NAME),5,295);
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
				g.drawRect(620, 290, 150, 50);
				g.drawString("Next Level", 624,325);
				g.drawRect(620, 490, 150, 50);
				g.drawString("MENU",665,525);
				Font f2 = new Font("Arial",Font.BOLD,40);
				g.setFont(f2);
				g.drawString("LEVEL COMPLETED",Game.WIDTH/2-197, Game.HEIGHT/2-160);
				g.drawLine(2, 250, Game.WIDTH-2, 250);
				if(playerInfo != null && !storeScoreStop)
				{
					System.out.println("Storing the Player Details:");
					playerInfo.setPlayerName(Game.NAME);
					playerInfo.setHighestIndividualScore(HUD.HIGHSCORE);
					playerInfo.setTotalIndividualScore(HUD.TOTALSCORE);
					playerInfo.setPlayerLevel(HUD.LEVEL);
					fileUtils.store(playerInfo);
					storeScoreStop = true;
				}
				if(HUD.COUNT == 0 && HUD.SCORE == 0)
				{
					g.drawString("SCORE: 0",5, 300);
					g.drawString("LEVEL 1 HIGHSCORE: "+fileUtils.getHighestScore(Game.NAME),5,340);
				}
				else
				{
					g.drawString("SCORE: "+(HUD.COUNT-2),5, 300);
					g.drawString("LEVEL 1 HIGHSCORE: "+fileUtils.getHighestScore(Game.NAME),5,340);
				}
				g.drawLine(200, 250, 600, 250);
				g.drawString("Player: "+Game.NAME, 5, 380);
				g.drawString("Level: "+HUD.LEVEL,5,420);
			}
		}
	}
}
