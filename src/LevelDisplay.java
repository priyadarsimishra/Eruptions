import java.awt.Graphics;
/* This class is used to display the level at the start of each level */
import java.awt.Color;
import java.awt.Font;
public class LevelDisplay 
{
	private Game game;
	public static int scoreTime = 300;
	public static int scoreTime2 = 300;
	public static int wizardTime = 100;
	public static int underTime = 100;
	public static int split1Time = 200;
	public static int split2Time = 200;
	private Color springGreen = new Color(0,255,127);
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
			g.setColor(Color.WHITE);
			Font levelFont = new Font("Superpower Synonym",Font.BOLD,220);
			g.setFont(levelFont);
			g.drawString("LEVEL 1", Game.WIDTH/2-355,Game.HEIGHT/2+30);
		}
		if(game.gameState == game.STATE.LEVEL1 && game.isBossFight && HUD.LEVEL1BOSSHEALTH>0)
		{
			g.setColor(Color.WHITE);
			Font bossFont = new Font("Superpower Synonym",Font.BOLD,160);
			g.setFont(bossFont);
			g.drawString("LAVA MAN", Game.WIDTH/2-354,Game.HEIGHT/2+20);
			Font help = new Font("Superpower Synonym",Font.BOLD,40);
			g.setFont(help);
			g.drawString("Collect Water buckets", Game.WIDTH/2-230,Game.HEIGHT/2+60);
			g.drawString("Shoot them by pressing SPACE", Game.WIDTH/2-260,Game.HEIGHT/2+100);
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
		if(game.gameState == game.STATE.LEVEL2 && !(game.level2pause>=500))
		{
			g.setColor(Color.WHITE);
			Font levelFont = new Font("Superpower Synonym",Font.BOLD,220);
			g.setFont(levelFont);
			g.drawString("LEVEL 2", Game.WIDTH/2-385,Game.HEIGHT/2+30);
		}
		if(game.gameState == game.STATE.LEVEL2 && game.level2pause>=500 && HUD.WIZARDHEALTH<=0)
		{
			Font scoreFont = new Font("Arial",Font.BOLD,24);
			g.setFont(scoreFont);
			if(wizardTime>0)
			{
				g.setColor(Color.RED);
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
				g.setColor(Color.RED);
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
			g.setColor(Color.WHITE);
			Font bossFont = new Font("Superpower Synonym",Font.BOLD,220);
			g.setFont(bossFont);
			g.drawString("ARMY", Game.WIDTH/2-225,Game.HEIGHT/2-130);
			g.drawString("SOLDIER", Game.WIDTH/2-370,Game.HEIGHT/2+20);
			Font help = new Font("Superpower Synonym",Font.BOLD,40);
			g.setFont(help);
			g.drawString("Collect The Keys", Game.WIDTH/2-155,Game.HEIGHT/2+60);
			g.drawString("Shoot them by pressing SPACE", Game.WIDTH/2-260,Game.HEIGHT/2+100);
		}
		if(game.gameState == game.STATE.LEVEL2 && HUD.LEVEL2BOSSHEALTH<=0)
		{
			if(scoreTime2 <= 0)
			{
				g.setColor(Color.BLACK);
				g.fillRect(0, Game.HEIGHT/2-200,800,400);
				g.setColor(Color.WHITE);
				g.drawRect(2, Game.HEIGHT/2-200, 797, 398);
//				g.setColor(Color.BLUE);
//				g.drawString("Hello",300,300);
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
				System.out.println(scoreTime2);
			}
		}
	}
}
