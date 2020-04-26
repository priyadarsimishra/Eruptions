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
	public static int SCORE = 0;
	private int sector = 0;
	private int level = 0;
	private int greenValue = 255;
	private Game game;
	private Menu menu;
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
		if(game.gameState == game.STATE.MENU)
		{
			HEALTH = 100;
		}
	}	
	/* This method is also called 60 times per second
	 * and it draws the the black outline around the health bar
	 * the gray background of the health bar and the color part
	 * of the health bar */
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
