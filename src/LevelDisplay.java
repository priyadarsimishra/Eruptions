import java.awt.Graphics;
/* This class is used to display the level at the start of each level */
import java.awt.Color;
import java.awt.Font;
public class LevelDisplay 
{
	private Game game;
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
		if(game.gameState == game.STATE.LEVEL1)
		{
			g.setColor(Color.WHITE);
			Font levelFont = new Font("TimesNewRoman",Font.BOLD,160);
			g.setFont(levelFont);
			g.drawString("LEVEL 1", Game.WIDTH/2-325,Game.HEIGHT/2+20);
		}
	}
}
