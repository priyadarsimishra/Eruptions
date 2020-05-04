import java.awt.Graphics;
/* This class is used to display the level at the start of each level */
import java.awt.Color;
import java.awt.Font;
public class LevelDisplay 
{
	private Game game;
	public static int scoreTime = 300;
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
			Font bossFont = new Font("TimesNewRoman",Font.BOLD,160);
			g.setFont(bossFont);
			g.drawString("BOSS!", Game.WIDTH/2-225,Game.HEIGHT/2+20);
			Font help = new Font("TimesNewRoman",Font.BOLD,40);
			g.setFont(help);
			g.drawString("Collect Water buckets", Game.WIDTH/2-230,Game.HEIGHT/2+60);
			g.drawString("Shoot Boss by pressing SPACE", Game.WIDTH/2-300,Game.HEIGHT/2+100);
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
				g.setColor(Color.PINK);
				if((int)Level1Boss.x+80+200>=Game.WIDTH)
				{
					System.out.println("Here");
					g.drawString("+1000",(int)Level1Boss.x-20,Game.HEIGHT/2+20);
				}
				else				
					g.drawString("+1000",(int)Level1Boss.x+80,(int)Level1Boss.y+200);
				scoreTime--;
			}
		}
		if(game.gameState == game.STATE.LEVEL2 && game.level2pause<=500)
		{
			g.setColor(Color.WHITE);
			Font levelFont = new Font("TimesNewRoman",Font.BOLD,160);
			g.setFont(levelFont);
			g.drawString("LEVEL 2", Game.WIDTH/2-325,Game.HEIGHT/2+20);
		}
	}
}
