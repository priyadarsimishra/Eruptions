import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class HUD 
{	
	public static int HEALTH = 100;
	public static int SCORE = 0;
	private int sector = 0;
	private int level = 0;
	private int greenValue = 255;
	private Game game;
	private Menu menu;
	public HUD(Game game,Menu menu)
	{
		this.game = game;
		this.menu = menu;
	}
	public void update()
	{
		HEALTH = (int)Game.restrict(HEALTH,0,HEALTH+(sector/2));
		greenValue = HEALTH*2;
		greenValue = (int)Game.restrict(greenValue, 0, 255);
		if(game.gameState == game.STATE.MENU)
		{
			HEALTH = 100;
		}
	}	
	public void render(Graphics g)
	{
		g.setColor(Color.GRAY);
		g.fillRect(10,10,200+sector,50);
		g.setColor(new Color(120,greenValue,60));
		g.fillRect(10,10,HEALTH*2,50);
		((Graphics2D)g).setStroke(new BasicStroke(3));
		g.setColor(Color.BLACK);
		g.drawRect(10,10,200+sector,50);
	}
}
