import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
/* This class is used to display the level at the start of each level */
import java.awt.Color;
import java.awt.Font;
public class LevelDisplay 
{
	private Game game;
	public static int scoreTime = 300;
	public static int scoreTime2 = 300;
	public static int scoreTime3 = 300;
	public static int scoreTime4 = 300;
	public static int wizardTime = 100;
	public static int underTime = 100;
	public static int split1Time = 200;
	public static int split2Time = 200;
	public static int exploderTime = 200;
	public static int rayEnemyTime = 200;
	public static int rocketEnemyTime = 200;
	public static int dragonTime = 200;
	public static int tankTime = 200;
	public static int boomerangTime = 200;
	public static int shieldTime = 200;
	private Color springGreen = new Color(0,255,127);
	public Color skyBlue = new Color(0,191,255);
	public Color lime = new Color(50,205,50);
	public Color navy = new Color(0,0,128);
	public Color deepPink = new Color(255,20,147);
	public Color aqua = new Color(0,255,255);
	public Color azure = new Color(240,255,255);
	public Color darkMagenta = new Color(139,0,139);
	/* This constructor has a game instance parameter 
	 * so the class can use get the State of the game
	 * and display the appropriate level text */
	public LevelDisplay(Game game)
	{
		this.game = game;
	}
	/* This method renders the text depending on the level of the game */
	public void render(Graphics g)
	{
		if(game.gameState == game.STATE.LEVEL1 && game.level1pause<=500 && !game.isBossFight)
		{
	        ((Graphics2D)g).setStroke(new BasicStroke(3));
			g.setColor(Color.BLACK);
			g.fillRect( Game.WIDTH/2-370, Game.HEIGHT/2-150, 740, 210);
			g.setColor(Color.WHITE);
			Font levelFont = new Font("Superpower Synonym",Font.BOLD,220);
			g.setFont(levelFont);
			g.drawString("LEVEL 1", Game.WIDTH/2-355,Game.HEIGHT/2+30);
			g.drawRect( Game.WIDTH/2-370, Game.HEIGHT/2-150, 741, 211);
		}
		if(game.gameState == game.STATE.LEVEL1 && game.isBossFight && HUD.LEVEL1BOSSHEALTH>0)
		{
			
	        ((Graphics2D)g).setStroke(new BasicStroke(3));
			g.setColor(Color.BLACK);
			g.fillRect(30, 280, 740, 250);
			g.setColor(Color.WHITE);
			Font bossFont = new Font("Superpower Synonym",Font.BOLD,160);
			g.setFont(bossFont);
			g.drawString("LAVA MAN", Game.WIDTH/2-360,Game.HEIGHT/2+20);
			Font help = new Font("Superpower Synonym",Font.BOLD,40);
			g.setFont(help);
			g.drawString("Collect Water buckets", Game.WIDTH/2-230,Game.HEIGHT/2+60);
			g.drawString("Shoot them by pressing SPACE", Game.WIDTH/2-275,Game.HEIGHT/2+100);
			g.drawRect(30, 280, 741, 251);
		}
		if(game.gameState == game.STATE.LEVEL1 && HUD.LEVEL1BOSSHEALTH<=0)
		{
			if(scoreTime <= 0)
			{
				g.setColor(Color.BLACK);
				g.fillRect(0, Game.HEIGHT/2-200,800,400);
				g.setColor(Color.WHITE);
				g.drawRect(2, Game.HEIGHT/2-200, 797, 398);
			}
			else
			{
				Font newFont = new Font("Arial",Font.BOLD,64);
				g.setFont(newFont);
				g.setColor(springGreen);
				if((int)Level1Boss.x+80+200>=Game.WIDTH)
				{
					g.drawString("+1000",(int)Level1Boss.x-20,Game.HEIGHT/2+20);
				}
				else				
					g.drawString("+1000",(int)Level1Boss.x+80,(int)Level1Boss.y+200);
				scoreTime--;
			}
		}
		if(game.gameState == game.STATE.LEVEL2 && !(game.level2pause>=500) && !game.isBossFight2)
		{
	        ((Graphics2D)g).setStroke(new BasicStroke(3));
			g.setColor(Color.BLACK);
			g.fillRect(10,255,780,200);
			g.setColor(Color.WHITE);
			Font levelFont = new Font("Superpower Synonym",Font.BOLD,220);
			g.setFont(levelFont);
			g.drawString("LEVEL 2", Game.WIDTH/2-385,Game.HEIGHT/2+30);
			g.drawRect(10,255,781,201);
		}
		if(game.gameState == game.STATE.LEVEL3 && !(game.level3pause>=500) && !game.isBossFight3)
		{
	        ((Graphics2D)g).setStroke(new BasicStroke(3));
	        g.setColor(Color.BLACK);
	        g.fillRect(10, 250, 780, 210);
			g.setColor(Color.WHITE);
			Font levelFont = new Font("Superpower Synonym",Font.BOLD,220);
			g.setFont(levelFont);
			g.drawString("LEVEL 3", Game.WIDTH/2-387,Game.HEIGHT/2+30);
	        g.drawRect(10, 250, 781, 211);
		}
		if(game.gameState == game.STATE.LEVEL4 && !(game.level4pause>=500) && !game.isBossFight4)
		{
	        ((Graphics2D)g).setStroke(new BasicStroke(3));
	        g.setColor(Color.BLACK);
	        g.fillRect(5, 250, 790, 210);
			g.setColor(Color.WHITE);
			Font levelFont = new Font("Superpower Synonym",Font.BOLD,220);
			g.setFont(levelFont);
			g.drawString("LEVEL 4", Game.WIDTH/2-392,Game.HEIGHT/2+30);
	        g.drawRect(5, 250, 791, 211);
		}
		if(game.gameState == game.STATE.LEVEL2 && game.level2pause>=500 && HUD.WIZARDHEALTH<=0)
		{
			Font scoreFont = new Font("Arial",Font.BOLD,24);
			g.setFont(scoreFont);
			if(wizardTime>0)
			{
				g.setColor(skyBlue);
				g.drawString("+300", (int)Wizard.x, (int)Wizard.y);
				wizardTime--;
			}
		}
		if(game.gameState == game.STATE.LEVEL2 && game.level2pause>=500 && HUD.UNDERGROUNDHEALTH<=0)
		{
			Font scoreFont = new Font("Arial",Font.BOLD,24);
			g.setFont(scoreFont);
			if(underTime>0)
			{
				g.setColor(Color.GREEN);
				g.drawString("+500", (int)UnderGroundEnemy.x, (int)UnderGroundEnemy.y);
				underTime--;
			}
		}
		if(game.gameState == game.STATE.LEVEL2 && game.level2pause>=500 && HUD.SPLITHEALTH1<=0 && HUD.THROWERHEALTH<=25)
		{
			Font scoreFont = new Font("Arial",Font.BOLD,24);
			g.setFont(scoreFont);
			if(split1Time>0)
			{
				g.setColor(springGreen);
				g.drawString("+200", (int)SplitEnemy1.x, (int)SplitEnemy1.y);
				split1Time--;
			}
		}
		if(game.gameState == game.STATE.LEVEL2 && game.level2pause>=500 && HUD.SPLITHEALTH2<=0 && HUD.THROWERHEALTH<=25)
		{
			Font scoreFont = new Font("Arial",Font.BOLD,24);
			g.setFont(scoreFont);
			if(split2Time>0)
			{
				g.setColor(springGreen);
				g.drawString("+200", (int)SplitEnemy2.x, (int)SplitEnemy2.y);
				split2Time--;
			}
		}
		if(game.gameState == game.STATE.LEVEL2 && game.isBossFight2 && HUD.LEVEL2BOSSHEALTH>0 && game.bossDisplay2<200)
		{
	        ((Graphics2D)g).setStroke(new BasicStroke(3));
			g.setColor(Color.BLACK);
			g.fillRect(10, 140, 780,430);
			g.setColor(Color.WHITE);
			Font bossFont = new Font("Superpower Synonym",Font.BOLD,220);
			g.setFont(bossFont);
			g.drawString("ARMY", Game.WIDTH/2-230,Game.HEIGHT/2-80);
			g.drawString("SOLDIER", Game.WIDTH/2-370,Game.HEIGHT/2+70);
			Font help = new Font("Superpower Synonym",Font.BOLD,40);
			g.setFont(help);
			g.drawString("Collect The Keys", Game.WIDTH/2-155,Game.HEIGHT/2+110);
			g.drawString("Shoot them by pressing SPACE", Game.WIDTH/2-260,Game.HEIGHT/2+150);
			g.drawRect(10, 140, 781,431);
		}
		if(game.gameState == game.STATE.LEVEL2 && HUD.LEVEL2BOSSHEALTH<=0)
		{
			if(scoreTime2 <= 0)
			{
				g.setColor(Color.BLACK);
				g.fillRect(0, Game.HEIGHT/2-200,800,400);
				g.setColor(Color.WHITE);
				g.drawRect(2, Game.HEIGHT/2-200, 797, 398);
			}
			else
			{
				Font newFont = new Font("Arial",Font.BOLD,64);
				g.setFont(newFont);
				g.setColor(springGreen);
				if((int)Level2Boss.x+80+200>=Game.WIDTH)
				{
					g.drawString("+1000",(int)Level2Boss.x-20,Game.HEIGHT/2+20);
				}
				else				
					g.drawString("+1000",(int)Level2Boss.x+80,(int)Level2Boss.y+200);
				scoreTime2--;
			}
		}
		if(game.gameState == game.STATE.LEVEL3 && game.level3pause>=500 && HUD.EXPLODERHEALTH<=0)
		{
			Font scoreFont = new Font("Arial",Font.BOLD,24);
			g.setFont(scoreFont);
			if(exploderTime>0)
			{
				g.setColor(springGreen);
				g.drawString("+200", (int)ExploderEnemy.x, (int)ExploderEnemy.y);
				exploderTime--;
			}
		}
		if(game.gameState == game.STATE.LEVEL3 && game.level3pause>=500 && HUD.RAYHEALTH<=0)
		{
			Font scoreFont = new Font("Arial",Font.BOLD,24);
			g.setFont(scoreFont);
			if(rayEnemyTime>0)
			{
				g.setColor(skyBlue);
				g.drawString("+350", (int)RayEnemy.x, (int)RayEnemy.y);
				rayEnemyTime--;
			}
		}
		if(game.gameState == game.STATE.LEVEL3 && game.level3pause>=500 && HUD.ROCKETHEALTH<=0)
		{
			Font scoreFont = new Font("Arial",Font.BOLD,24);
			g.setFont(scoreFont);
			if(rocketEnemyTime>0)
			{
				g.setColor(Color.BLUE);
				g.drawString("+150", (int)RocketEnemy.x, (int)RocketEnemy.y);
				rocketEnemyTime--;
			}
		}
		if(game.gameState == game.STATE.LEVEL3 && game.isBossFight3 && HUD.LEVEL3BOSSHEALTH>0 && game.bossDisplay3<200)
		{
	        ((Graphics2D)g).setStroke(new BasicStroke(3));
			g.setColor(Color.BLACK);
			g.fillRect(60,250,680,315);
			g.setColor(Color.WHITE);
			Font bossFont = new Font("Superpower Synonym",Font.BOLD,220);
			g.setFont(bossFont);
			g.drawString("PRINCE", Game.WIDTH/2-325,Game.HEIGHT/2+20);
			Font help = new Font("Superpower Synonym",Font.BOLD,40);
			g.setFont(help);
			g.drawString("Collect The Eggs", Game.WIDTH/2-155,Game.HEIGHT/2+60);
			g.drawString("Shoot them by pressing SPACE", Game.WIDTH/2-260,Game.HEIGHT/2+100);
			g.drawString("Be aware of dragons", Game.WIDTH/2-190,Game.HEIGHT/2+150);
			Font scoreFont = new Font("Arial",Font.BOLD,24);
			g.setFont(scoreFont);
			g.drawRect(60,250,681,316);
		}
		if(game.gameState == game.STATE.LEVEL3 && game.isBossFight3 && HUD.LEVEL3BOSSHEALTH>0 && !(game.bossDisplay3<200) && HUD.BABYDRAGONHEALTH<=0)
		{
			Font scoreFont = new Font("Arial",Font.BOLD,24);
			g.setFont(scoreFont);
			if(dragonTime>0)
			{
				g.setColor(azure);
				g.drawString("+400", (int)BabyDragon.x, (int)BabyDragon.y);
				dragonTime--;
			}
		}
		if(game.gameState == game.STATE.LEVEL3 && HUD.LEVEL3BOSSHEALTH<=0 )
		{
			if(scoreTime3 <= 0)
			{
				g.setColor(Color.BLACK);
				g.fillRect(0, Game.HEIGHT/2-200,800,400);
				g.setColor(Color.WHITE);
				g.drawRect(2, Game.HEIGHT/2-200, 797, 398);
			}
			else
			{
				Font newFont = new Font("Arial",Font.BOLD,64);
				g.setFont(newFont);
				g.setColor(springGreen);
				if((int)Level3Boss.x+80+200>=Game.WIDTH)
				{
					g.drawString("+1000",(int)Level3Boss.x-20,Game.HEIGHT/2+20);
				}
				else				
					g.drawString("+1000",(int)Level3Boss.x+80,(int)Level3Boss.y+200);
				scoreTime3--;
			}
		}
		if(game.gameState == game.STATE.LEVEL4 && game.level4pause>=500 && HUD.TANKHEALTH<=0)
		{
			Font scoreFont = new Font("Arial",Font.BOLD,24);
			g.setFont(scoreFont);
			if(tankTime>0)
			{
				g.setColor(skyBlue);
				g.drawString("+450", (int)TankEnemy.x, (int)TankEnemy.y);
				tankTime--;
			}
		}
		if(game.gameState == game.STATE.LEVEL4 && game.level4pause>=500 && HUD.BOOMERANGHEALTH<=0)
		{
			Font scoreFont = new Font("Arial",Font.BOLD,24);
			g.setFont(scoreFont);
			if(boomerangTime>0)
			{
				g.setColor(lime);
				g.drawString("+200", (int)BoomerangEnemy.x, (int)BoomerangEnemy.y);
				boomerangTime--;
			}
		}
		if(game.gameState == game.STATE.LEVEL4 && game.level4pause>=500 && HUD.SHIELDENEMYHEALTH<=0)
		{
			Font scoreFont = new Font("Arial",Font.BOLD,24);
			g.setFont(scoreFont);
			if(shieldTime>0)
			{
				g.setColor(darkMagenta);
				g.drawString("+250", (int)ShieldEnemy.x+3, (int)ShieldEnemy.y);
				shieldTime--;
			}
		}
		if(game.gameState == game.STATE.LEVEL4 && game.isBossFight4 && HUD.LEVEL4BOSSHEALTH>0 && game.bossDisplay4<200)
		{
			((Graphics2D)g).setStroke(new BasicStroke(3));
			g.setColor(Color.BLACK);
			g.fillRect(130,255,570,265);
			g.setColor(Color.WHITE);
			Font bossFont = new Font("Superpower Synonym",Font.BOLD,220);
			g.setFont(bossFont);
			g.drawString("KING", Game.WIDTH/2-200,Game.HEIGHT/2+20);
			Font help = new Font("Superpower Synonym",Font.BOLD,40);
			g.setFont(help);
			g.drawString("Collect The Crates", Game.WIDTH/2-165,Game.HEIGHT/2+60);
			g.drawString("Shoot them by pressing SPACE", Game.WIDTH/2-260,Game.HEIGHT/2+100);
			g.drawRect(130,255,571,261);
		}
		if(game.gameState == game.STATE.LEVEL4 && game.level4pause>=500 && HUD.EXPLODERHEALTH<=0 && game.isBossFight4 && !(game.bossDisplay4<200))
		{
			Font scoreFont = new Font("Arial",Font.BOLD,24);
			g.setFont(scoreFont);
			if(exploderTime>0)
			{
				g.setColor(skyBlue);
				g.drawString("+200", (int)ExploderEnemy.x, (int)ExploderEnemy.y);
				exploderTime--;
			}
		}
		if(game.gameState == game.STATE.LEVEL4 && HUD.LEVEL4BOSSHEALTH<=0)
		{
			if(scoreTime4 <= 0)
			{
				g.setColor(Color.BLACK);
				g.fillRect(0, Game.HEIGHT/2-200,800,400);
				g.setColor(Color.WHITE);
				g.drawRect(2, Game.HEIGHT/2-200, 797, 398);
			}
			else
			{
				Font newFont = new Font("Arial",Font.BOLD,64);
				g.setFont(newFont);
				g.setColor(springGreen);
				if((int)Level3Boss.x+80+200>=Game.WIDTH)
				{
					g.drawString("+3000",(int)Level4Boss.x-20,Game.HEIGHT/2+20);
				}
				else				
					g.drawString("+3000",(int)Level4Boss.x+80,(int)Level4Boss.y+200);
				scoreTime4--;
			}
		}
	}
}
