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
	public static int SCORE = 0;
	public static int HIGHSCORE = 0;
	public static int COUNT = 0;
	public static int TOTALSCORE =0;
	public static int UNDERGROUNDHEALTH = 25;
	private int sector = 0;
	private int level = 0;
	public int display = 150;
	public int display2 = 150;
	private int greenValue = 255;
	private Game game;
	private Menu menu;
	private FileUtils fileUtils = new FileUtils();
	boolean stopScore = false;
	boolean stopScore2 = false;
	boolean stoptotalScore = false;
	boolean addScore = false;
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
		UNDERGROUNDHEALTH = (int)Game.restrict(UNDERGROUNDHEALTH, 0, LEVEL1BOSSHEALTH+(sector/4));
		if(game.gameState == game.STATE.MENU)
		{
			HEALTH = 100;
			LEVEL1BOSSHEALTH = 200;
		}
		/*if(game.isBossFight)
		{
			LEVEL1BOSSHEALTH-=2;
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
					g.drawString((LEVEL1BOSSHEALTH/2)+"%",716,43);
				else
					g.drawString((LEVEL1BOSSHEALTH/2)+"%",728,43);
			}
		}
		if(game.gameState == game.STATE.LEVEL2 && UnderGroundEnemy.show)
		{
			g.setColor(Color.GRAY);
			g.fillRect((int)UnderGroundEnemy.x+10,(int)UnderGroundEnemy.y-20,UNDERGROUNDHEALTH*2,10);
			g.setColor(Color.RED);
			g.fillRect((int)UnderGroundEnemy.x+10,(int)UnderGroundEnemy.y-20,UNDERGROUNDHEALTH*2,10);
		}
	}
}
