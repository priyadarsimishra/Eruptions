import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
/* This class is used to display the health, score, level
 * and more */
public class HUD 
{	
	public static int HEALTH = 100;
	public static int LEVEL = 0;
	public static int LEVEL1BOSSHEALTH = 200;
	public static int LEVEL2BOSSHEALTH = 500;
	public static int SCORE = 0;
	public static int HIGHSCORE = 0;
	public static int COUNT = 0;
	public static int TOTALSCORE =0;
	public static int UNDERGROUNDHEALTH = 25;
	public static int WIZARDHEALTH = 50;
	public static int THROWERHEALTH = 50;
	public static int SPLITHEALTH1 = 25;
	public static int SPLITHEALTH2 = 25;
	private int sector = 0;
	private int level = 0;
	public int display = 150;
	public int display2 = 150;
	public int displaylev2 = 150;
	private int greenValue = 255;
	private Game game;
	private Menu menu;
	private FileUtils fileUtils = new FileUtils();
	boolean stopScore = false;
	boolean stopScore2 = false;
	boolean stopScorelev2 = false;
	boolean stoptotalScore = false;
	boolean stoptotalScore2 = false;
	boolean addScore = false;
	boolean addScore2 = false;
	boolean highScoreStop = false;
	/* This class needs game instance and a menu instance
	 * which is done through this constructor */
	public HUD(Game game,Menu menu)
	{
		this.game = game;
		this.menu = menu;
		
	}
	/* This method is also called 60 times per second 
	 * and it changes greenValue which is the color of the health bar and 
	 * it restricts the health bar from going into negative numbers 
	 * and if you are in the menu your health is reset to 100 */
	public void update()
	{
		HEALTH = (int)Game.restrict(HEALTH,0,HEALTH+(sector/2));
		greenValue = HEALTH*2;
		greenValue = (int)Game.restrict(greenValue, 0, 255);
		LEVEL1BOSSHEALTH = (int)Game.restrict(LEVEL1BOSSHEALTH,0,LEVEL1BOSSHEALTH+(sector));		
		LEVEL2BOSSHEALTH = (int)Game.restrict(LEVEL2BOSSHEALTH, 0, LEVEL2BOSSHEALTH+(sector));
		UNDERGROUNDHEALTH = (int)Game.restrict(UNDERGROUNDHEALTH, 0, UNDERGROUNDHEALTH+(sector/4));
		WIZARDHEALTH = (int)Game.restrict(WIZARDHEALTH,0,WIZARDHEALTH+(sector/4));
		THROWERHEALTH = (int)Game.restrict(THROWERHEALTH, 0, THROWERHEALTH+(sector/4));
		SPLITHEALTH1 = (int)Game.restrict(SPLITHEALTH1, 0, SPLITHEALTH1+(sector/4));
		SPLITHEALTH2 = (int)Game.restrict(SPLITHEALTH2, 0, SPLITHEALTH2+(sector/4));
		if(game.gameState == game.STATE.MENU)
		{
			HEALTH = 100;
			LEVEL1BOSSHEALTH = 200;
		}
		/*if(game.isBossFight2)
		{
			LEVEL2BOSSHEALTH-=2;
		}*/
		if(SCORE>=HIGHSCORE)
		{
			HIGHSCORE = SCORE;
			/*if(HIGHSCORECOUNT == SCORE && !highScoreStop)
			{
				HIGHSCORECOUNT++;
			}
			else highScoreStop = true;*/
		}
		if(LEVEL1BOSSHEALTH<=0 && !addScore)
		{
			SCORE+=1000;
			addScore = true;
		}
		if(SCORE == COUNT && HUD.LEVEL1BOSSHEALTH<=0)
		{
			stopScore = true;
		}
		if(!stoptotalScore && LEVEL1BOSSHEALTH<=0)
		{
			TOTALSCORE = fileUtils.getTotalScoreForAPlayer(game.NAME);
			TOTALSCORE+=SCORE;
			stoptotalScore = true;
		}
		if(LEVEL1BOSSHEALTH<=0 && !stopScore && LevelDisplay.scoreTime<=0 && game.gameState == Game.STATE.LEVEL1 && display<=0)
		{
			if(COUNT>SCORE) stopScore = true;
			else stopScore = false;
			COUNT++;
			//System.out.println("Count: "+COUNT);
		}
		else
		{
			display--;
		}
		
		if(LEVEL2BOSSHEALTH<=0 && !addScore2)
		{
			SCORE+=1000;
			addScore2 = true;
		}
		if(SCORE == COUNT && HUD.LEVEL2BOSSHEALTH<=0)
			stopScorelev2 = true;
		if(!stoptotalScore2 && LEVEL2BOSSHEALTH<=0)
		{
			TOTALSCORE = fileUtils.getTotalScoreForAPlayer(game.NAME);
			TOTALSCORE+=SCORE;
			stoptotalScore2 = true;
		}
		if(LEVEL2BOSSHEALTH<=0 && !stopScorelev2 && LevelDisplay.scoreTime2<=0 && game.gameState == Game.STATE.LEVEL2 && displaylev2<=0)
		{
			if(COUNT>SCORE) stopScorelev2 = true;
			else stopScorelev2 = false;
			COUNT++;
			//System.out.println("Count: "+COUNT);
		}
		else
		{
			displaylev2--;
		}
		if(SCORE == COUNT)
		{
			stopScore2 = false;
		}
		if(!stopScore2 && game.gameState == Game.STATE.DEADSCREEN && display2<=0)
		{
			if(COUNT>SCORE) stopScore2 = true;
			else stopScore2 = false;
			COUNT++;
		}
		else
		{
			display2--;
		}
		//System.out.println("totalCount: "+TOTALSCORE);
	}	
	/* This method is also called 60 times per second
	 * and it draws the the black outline around the health bar
	 * the gray background of the health bar and the color part
	 * of the health bar */
	public void render(Graphics g)
	{
		if(game.gameState == game.STATE.LEVEL2 && game.level2pause>=500 && !game.isBossFight2)
		{
			if(UnderGroundEnemy.show)
			{
				g.setColor(Color.GRAY);
				g.fillRect((int)UnderGroundEnemy.x+10,(int)UnderGroundEnemy.y-20,UNDERGROUNDHEALTH*2,10);
				g.setColor(Color.RED);
				g.fillRect((int)UnderGroundEnemy.x+10,(int)UnderGroundEnemy.y-20,UNDERGROUNDHEALTH*2,10);
			}
			
			g.setColor(Color.GRAY);
			g.fillRect((int)Wizard.x-10, (int)Wizard.y-15, WIZARDHEALTH, 10);
			g.setColor(Color.ORANGE);
			g.fillRect((int)Wizard.x-10, (int)Wizard.y-15, WIZARDHEALTH, 10);
			if(!(ThrowerEnemy.giveInfo))
			{
				g.setColor(Color.GRAY);
				g.fillRect((int)ThrowerEnemy.x-4, (int)ThrowerEnemy.y-15, THROWERHEALTH, 10);
				g.setColor(Color.BLUE);
				g.fillRect((int)ThrowerEnemy.x-4, (int)ThrowerEnemy.y-15, THROWERHEALTH, 10);
			}			
			if(SplitEnemy1.show && THROWERHEALTH<=25)
			{
				g.setColor(Color.GRAY);
				g.fillRect((int)SplitEnemy1.x-1, (int)SplitEnemy1.y-15, SPLITHEALTH1, 10);
				g.setColor(Color.YELLOW);
				g.fillRect((int)SplitEnemy1.x-1, (int)SplitEnemy1.y-15, SPLITHEALTH1, 10);
			}
			if(SplitEnemy2.show && THROWERHEALTH<=25)
			{
				g.setColor(Color.GRAY);
				g.fillRect((int)SplitEnemy2.x-1, (int)SplitEnemy2.y-15, SPLITHEALTH2, 10);
				g.setColor(Color.YELLOW);
				g.fillRect((int)SplitEnemy2.x-1, (int)SplitEnemy2.y-15, SPLITHEALTH2, 10);
			}
		}
		if(Game.gameState == game.STATE.LEVEL2 && game.level2pause>=500 && game.isBossFight2)
		{
			g.setColor(Color.GRAY);
			g.fillRect(280,10,500+sector,50);
			g.setColor(Color.RED);
			g.fillRect(280,10,LEVEL2BOSSHEALTH,50);
			((Graphics2D)g).setStroke(new BasicStroke(3));
			g.setColor(Color.BLACK);
			g.drawRect(280,10,500+sector,50);
			g.setColor(Color.WHITE);
			Font health = new Font("Arial",Font.BOLD,24);
			g.setFont(health);
   			g.drawString(LEVEL2BOSSHEALTH+"",716,43);
		}
		if(LEVEL2BOSSHEALTH<0)
			LEVEL2BOSSHEALTH = 0;
		if(LEVEL1BOSSHEALTH > 0)
		{
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 220, 120);
			g.setColor(Color.WHITE);
			Font scoreFont = new Font("Superpower Synonym",Font.BOLD,24);
			g.setFont(scoreFont);
			g.drawString("Score: "+SCORE,5,85);
			g.drawString("Level: "+LEVEL,5,110);
		}
		g.setColor(Color.GRAY);
		g.fillRect(10,10,200+sector,50);
		g.setColor(new Color(120,greenValue,60));
		g.fillRect(10,10,HEALTH*2,50);
		((Graphics2D)g).setStroke(new BasicStroke(3));
		g.setColor(Color.WHITE);
		g.drawRect(10,10,200+sector,50);
		Font newFont = new Font("Arial",Font.BOLD,24);
		g.setFont(newFont);
		g.setColor(Color.BLACK);
		if(HEALTH>0)
		{
			if(HEALTH == 100)
				g.drawString(HEALTH+"%",148,43);
			else
				g.drawString(HEALTH+"%",160,43);	
		}
		//g.drawString("HEALTH", , y);

		
		if(game.isBossFight && game.gameState == game.STATE.LEVEL1)
		{
			g.setColor(Color.GRAY);
			g.fillRect(380,10,400+sector,50);
			g.setColor(Color.RED);
			g.fillRect(380,10,LEVEL1BOSSHEALTH*2,50);
			((Graphics2D)g).setStroke(new BasicStroke(3));
			g.setColor(Color.BLACK);
			g.drawRect(380,10,400+sector,50);
			g.setColor(Color.WHITE);
			if(!(LEVEL1BOSSHEALTH<0))
			{
				if(LEVEL1BOSSHEALTH == 200)
					g.drawString(LEVEL1BOSSHEALTH+"",716,43);
				else
					g.drawString(LEVEL1BOSSHEALTH+"",728,43);
			}
		}
	}
}
