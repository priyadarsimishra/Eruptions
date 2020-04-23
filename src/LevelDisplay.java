import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
public class LevelDisplay 
{
	private Game game;
	public LevelDisplay(Game game)
	{
		this.game = game;
	}
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
