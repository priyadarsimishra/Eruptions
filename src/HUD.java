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
	public static int LEVEL3BOSSHEALTH = 550;
	public static int LEVEL4BOSSHEALTH = 570;
	public static int SCORE = 0;
	public static int HIGHSCORE = 0;
	public static int COUNT = 0;
	public static int TOTALSCORE =0;
	public static int UNDERGROUNDHEALTH = 25;
	public static int WIZARDHEALTH = 50;
	public static int THROWERHEALTH = 50;
	public static int SPLITHEALTH1 = 25;
	public static int SPLITHEALTH2 = 25;
	public static int EXPLODERHEALTH = 20;
	public static int RAYHEALTH = 30;
	public static int ROCKETHEALTH = 60;
	public static int BABYDRAGONHEALTH = 15;
	public static int TANKHEALTH = 80;
	public static int BOOMERANGHEALTH = 40;
	public static int SHIELDENEMYHEALTH = 40;
 	private Color lightPurple = new Color(141, 112, 255);
	public Color deepPink = new Color(255,20,147);
	private Color limeChiffon = new Color(255,250,205);
	public Color aqua = new Color(0,255,255);
	public Color azure = new Color(240,255,255);
	private Color springGreen = new Color(0,255,127);
	private int sector = 0;
	private int level = 0;
	public int display = 150;
	public int display2 = 150;
	public int displaylev2 = 150;
	public int displaylev3 = 150;
	public int displaylev4 = 150;
	private int greenValue = 255;
	private Game game;
	private Menu menu;
	private FileUtils fileUtils = new FileUtils();
	boolean stopScore = false;
	boolean stopScore2 = false;
	boolean stopScorelev2 = false;
	boolean stopScorelev3 = false;
	boolean stopScorelev4 = false;
	boolean stoptotalScore = false;
	boolean stoptotalScore2 = false;
	boolean stoptotalScore3 = false;
	boolean stoptotalScore4 = false;
	boolean addScore = false;
	boolean addScore2 = false;
	boolean addScore3 = false;
	boolean addScore4 = false;
	boolean highScoreStop = false;
	private ObjectHandler handler;
	private SpriteTextures texture;
	private BabyDragon bd;
	/* This class needs game instance and a menu instance
	 * which is done through this constructor */
	public HUD(Game game,Menu menu)
	{
		this.game = game;
		this.menu = menu;
		handler = new ObjectHandler();
		texture = new SpriteTextures(game);
		bd = new BabyDragon((int)Level3Boss.spawnX,(int)Level3Boss.spawnY,ID.BabyDragon,handler,texture);
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
		LEVEL3BOSSHEALTH = (int)Game.restrict(LEVEL3BOSSHEALTH, 0, LEVEL3BOSSHEALTH+(sector));
		LEVEL4BOSSHEALTH = (int)Game.restrict(LEVEL4BOSSHEALTH, 0, LEVEL4BOSSHEALTH+(sector));
		UNDERGROUNDHEALTH = (int)Game.restrict(UNDERGROUNDHEALTH, 0, UNDERGROUNDHEALTH+(sector/4));
		WIZARDHEALTH = (int)Game.restrict(WIZARDHEALTH,0,WIZARDHEALTH+(sector/4));
		THROWERHEALTH = (int)Game.restrict(THROWERHEALTH, 0, THROWERHEALTH+(sector/4));
		SPLITHEALTH1 = (int)Game.restrict(SPLITHEALTH1, 0, SPLITHEALTH1+(sector/4));
		SPLITHEALTH2 = (int)Game.restrict(SPLITHEALTH2, 0, SPLITHEALTH2+(sector/4));
		EXPLODERHEALTH = (int)Game.restrict(EXPLODERHEALTH, 0, EXPLODERHEALTH+(sector/4));
		RAYHEALTH = (int)Game.restrict(RAYHEALTH, 0, RAYHEALTH+(sector));
		ROCKETHEALTH = (int)Game.restrict(ROCKETHEALTH, 0, ROCKETHEALTH+(sector/4));
		BABYDRAGONHEALTH = (int)Game.restrict(BABYDRAGONHEALTH, 0, BABYDRAGONHEALTH+(sector/2));
		TANKHEALTH = (int)Game.restrict(TANKHEALTH, 0, TANKHEALTH+(sector/4));
		BOOMERANGHEALTH = (int)Game.restrict(BOOMERANGHEALTH, 0, BOOMERANGHEALTH+(sector/4));
		SHIELDENEMYHEALTH = (int)Game.restrict(SHIELDENEMYHEALTH, 0, SHIELDENEMYHEALTH+(sector));
		if(game.gameState == game.STATE.MENU)
		{
			HEALTH = 100;
			LEVEL1BOSSHEALTH = 200;
		}
		if(game.isBossFight)
		{
			LEVEL1BOSSHEALTH-=1;
		}
		
		if(game.isBossFight2)
		{
			LEVEL2BOSSHEALTH-=2;
		}
		if(game.isBossFight3)
		{
			LEVEL3BOSSHEALTH-=2;
		}
		if(game.isBossFight4)
		{
			LEVEL4BOSSHEALTH-=2;
		}
		if(SCORE>=HIGHSCORE)
		{
			HIGHSCORE = SCORE;
		}
		// LEVEL 1 END PAGE
		if(LEVEL1BOSSHEALTH<=0 && !addScore)
		{
			SCORE+=1000;
			addScore = true;
		}
		if(SCORE == COUNT && HUD.LEVEL1BOSSHEALTH<=0)
		{
			stopScore = true;
		}
		if(!stoptotalScore && (LEVEL1BOSSHEALTH<=0 || LEVEL2BOSSHEALTH<=0))
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
		}
		else
		{
			display--;
		}
		//LEVEL 2 END PAGE
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
		}
		else
		{
			displaylev2--;
		}
		//LEVEL 3 END PAGE
		if(LEVEL3BOSSHEALTH<=0 && !addScore3)
		{
			SCORE+=1000;
			addScore3 = true;
		}
		if(SCORE == COUNT && HUD.LEVEL3BOSSHEALTH<=0)
			stopScorelev3 = true;
		if(!stoptotalScore3 && LEVEL3BOSSHEALTH<=0)
		{
			TOTALSCORE = fileUtils.getTotalScoreForAPlayer(game.NAME);
			TOTALSCORE+=SCORE;
			stoptotalScore3 = true;
		}
		if(LEVEL3BOSSHEALTH<=0 && !stopScorelev3 && LevelDisplay.scoreTime3<=0 && game.gameState == Game.STATE.LEVEL3 && displaylev3<=0)
		{
			if(COUNT>SCORE) stopScorelev3 = true;
			else stopScorelev3 = false;
			COUNT++;
		}
		else
		{
			displaylev3--;
		}
		//LEVEL 4 END PAGE
		if(LEVEL4BOSSHEALTH<=0 && !addScore4)
		{
			SCORE+=3000;
			addScore4 = true;
		}
		if(SCORE == COUNT && HUD.LEVEL4BOSSHEALTH<=0)
			stopScorelev4 = true;
		if(!stoptotalScore4 && LEVEL4BOSSHEALTH<=0)
		{
			TOTALSCORE = fileUtils.getTotalScoreForAPlayer(game.NAME);
			TOTALSCORE+=SCORE;
			stoptotalScore4 = true;
		}
		if(LEVEL4BOSSHEALTH<=0 && !stopScorelev4 && LevelDisplay.scoreTime4<=0 && game.gameState == Game.STATE.LEVEL4 && displaylev4<=0)
		{
			if(COUNT>SCORE) stopScorelev4 = true;
			else stopScorelev4 = false;
			COUNT++;
		}
		else
		{
			displaylev4--;
		}
		//DEATHSCREEN
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
	}	
	/* This method is also called 60 times per second
	 * and it draws the the black outline around the health bar
	 * the gray background of the health bar and the color part
	 * of the health bar */
	public void render(Graphics g)
	{
		if(game.gameState == game.STATE.LEVEL2 && game.level2pause>=500 && !game.isBossFight2)
		{
			if(UnderGroundEnemy.show && !(UNDERGROUNDHEALTH<=0))
			{
				g.setColor(Color.GRAY);
				g.fillRect((int)UnderGroundEnemy.x+7,(int)UnderGroundEnemy.y-20,50+sector/4,10);
				g.setColor(limeChiffon);
				g.fillRect((int)UnderGroundEnemy.x+7,(int)UnderGroundEnemy.y-20,UNDERGROUNDHEALTH*2,10);
			}
			if(!(WIZARDHEALTH<=0))
			{
				g.setColor(Color.GRAY);
				g.fillRect((int)Wizard.x-10, (int)Wizard.y-15, 50+sector/4, 10);
				g.setColor(Color.ORANGE);
				g.fillRect((int)Wizard.x-10, (int)Wizard.y-15, WIZARDHEALTH, 10);
			}
			if(!(ThrowerEnemy.giveInfo) && !(THROWERHEALTH<=25))
			{
				g.setColor(Color.GRAY);
				g.fillRect((int)ThrowerEnemy.x-4, (int)ThrowerEnemy.y-15, 50+sector/4, 10);
				g.setColor(Color.BLUE);
				g.fillRect((int)ThrowerEnemy.x-4, (int)ThrowerEnemy.y-15, THROWERHEALTH, 10);
			}			
			if(SplitEnemy1.show && THROWERHEALTH<=25 && !(SPLITHEALTH1<=0))
			{
				g.setColor(Color.GRAY);
				g.fillRect((int)SplitEnemy1.x-1, (int)SplitEnemy1.y-15, 25+sector/4, 10);
				g.setColor(Color.YELLOW);
				g.fillRect((int)SplitEnemy1.x-1, (int)SplitEnemy1.y-15, SPLITHEALTH1, 10);
			}
			if(SplitEnemy2.show && THROWERHEALTH<=25 && !(SPLITHEALTH2<=0))
			{
				g.setColor(Color.GRAY);
				g.fillRect((int)SplitEnemy2.x-1, (int)SplitEnemy2.y-15, 25+sector/4, 10);
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
		if(game.gameState == game.STATE.LEVEL3 && game.level3pause>=500 && !game.isBossFight3)
		{
			if(!(EXPLODERHEALTH<=0))
			{
				g.setColor(Color.GRAY);
				g.fillRect((int)ExploderEnemy.x,(int)ExploderEnemy.y-20,40+sector/4,10);
				g.setColor(aqua);
				g.fillRect((int)ExploderEnemy.x,(int)ExploderEnemy.y-20,EXPLODERHEALTH*2,10);
			}
			if(!(RAYHEALTH<=0))
			{
				g.setColor(Color.GRAY);
				g.fillRect((int)RayEnemy.x-2,(int)RayEnemy.y-20,60+sector,10);
				g.setColor(limeChiffon);
				g.fillRect((int)RayEnemy.x-2,(int)RayEnemy.y-20,RAYHEALTH*2,10);
			}
			if(!(ROCKETHEALTH<=0))
			{
				g.setColor(Color.GRAY);
				g.fillRect((int)RocketEnemy.x-4,(int)RocketEnemy.y-20,60+sector/4,10);
				g.setColor(Color.BLACK);
				g.fillRect((int)RocketEnemy.x-4,(int)RocketEnemy.y-20,ROCKETHEALTH,10);
			}
		}
		if(game.gameState == game.STATE.LEVEL3 && game.level3pause>=500 && game.isBossFight3)
		{
			g.setColor(Color.GRAY);
			g.fillRect(235,10,550+sector/4,50);
			g.setColor(Color.RED);
			g.fillRect(235,10,LEVEL3BOSSHEALTH,50);
			((Graphics2D)g).setStroke(new BasicStroke(3));
			g.setColor(Color.BLACK);
			g.drawRect(235,10,550+sector/4,50);
			g.setColor(Color.WHITE); 
			Font health = new Font("Arial",Font.BOLD,24);
			g.setFont(health);
   			g.drawString(LEVEL3BOSSHEALTH+"",716,43);
   			
   			if(BABYDRAGONHEALTH > 0)
			{
				g.setColor(Color.GRAY);
				g.fillRect((int)BabyDragon.x+8, (int)BabyDragon.y-15, 30+sector/2, 10);
				g.setColor(aqua);
				g.fillRect((int)BabyDragon.x+8, (int)BabyDragon.y-15, BABYDRAGONHEALTH*2, 10);
			}
		}
		if(game.gameState == game.STATE.LEVEL4 && game.level4pause>=500 && !game.isBossFight4)
		{
			if(!(TANKHEALTH<=0))
			{
				g.setColor(Color.GRAY);
				g.fillRect((int)TankEnemy.x,(int)TankEnemy.y-20,60+sector/4,10);
				g.setColor(deepPink);
				g.fillRect((int)TankEnemy.x,(int)TankEnemy.y-20,TANKHEALTH,10);
			}
			if(!(BOOMERANGHEALTH<=0))
			{
				g.setColor(Color.GRAY);
				g.fillRect((int)BoomerangEnemy.x+2,(int)BoomerangEnemy.y-20,40+sector/4,10);
				g.setColor(lightPurple);
				g.fillRect((int)BoomerangEnemy.x+2,(int)BoomerangEnemy.y-20,BOOMERANGHEALTH,10);
			}
			if(!(SHIELDENEMYHEALTH<=0))
			{
				g.setColor(Color.GRAY);
				g.fillRect((int)ShieldEnemy.x,(int)ShieldEnemy.y-20,40+sector/4,10);
				g.setColor(azure);
				g.fillRect((int)ShieldEnemy.x,(int)ShieldEnemy.y-20,SHIELDENEMYHEALTH,10);
			}
		}
		if(game.gameState == game.STATE.LEVEL4 && game.level4pause>=500 && game.isBossFight4)
		{
			g.setColor(Color.GRAY);
			g.fillRect(225,10,570+sector/6,50);
			g.setColor(Color.RED);
			g.fillRect(225,10,LEVEL4BOSSHEALTH,50);
			((Graphics2D)g).setStroke(new BasicStroke(3));
			g.setColor(Color.BLACK);
			g.drawRect(225,10,570+sector/6,50);
			g.setColor(Color.WHITE); 
			Font health = new Font("Arial",Font.BOLD,24);
			g.setFont(health);
   			g.drawString(LEVEL4BOSSHEALTH+"",716,43);
   			
   			if(!(EXPLODERHEALTH<0) && Level4Boss.isAlive)
			{
				g.setColor(Color.GRAY);
				g.fillRect((int)ExploderEnemy.x,(int)ExploderEnemy.y-20,40+sector/4,10);
				g.setColor(aqua);
				g.fillRect((int)ExploderEnemy.x,(int)ExploderEnemy.y-20,EXPLODERHEALTH*2,10);
			}
		}
		if(LEVEL2BOSSHEALTH<0)
			LEVEL2BOSSHEALTH = 0;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 220, 120);
		g.setColor(Color.WHITE);
		Font scoreFont = new Font("Superpower Synonym",Font.BOLD,24);
		g.setFont(scoreFont);
		if(LEVEL1BOSSHEALTH > 0 && Game.gameState == Game.STATE.LEVEL1)
		{
			g.drawString("Score: "+SCORE,5,85);
			g.drawString("Level: "+LEVEL,5,110);
		}
		else if(LEVEL2BOSSHEALTH > 0 && Game.gameState == Game.STATE.LEVEL2)
		{
			g.drawString("Score: "+SCORE,5,85);
			g.drawString("Level: "+LEVEL,5,110);
		}
		else if(LEVEL3BOSSHEALTH > 0 && Game.gameState == Game.STATE.LEVEL3)
		{
			g.drawString("Score: "+SCORE,5,85);
			g.drawString("Level: "+LEVEL,5,110);
		}
		else if(LEVEL4BOSSHEALTH > 0 && Game.gameState == Game.STATE.LEVEL4)
		{
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
		if(LEVEL3BOSSHEALTH<=0)
			LEVEL3BOSSHEALTH = 0;
		if(LEVEL4BOSSHEALTH<=0)
			LEVEL4BOSSHEALTH = 0;
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
