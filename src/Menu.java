import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter
{
	private Game game;
	private ObjectHandler handler;
	public Menu(Game game, ObjectHandler handler)
	{
		this.game = game;
		this.handler = handler;
	}
	public void update()
	{
	}
	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		if(contains(mx, my,game.WIDTH/2-115,game.HEIGHT/2-100,200,100))
		{
			game.gameState = game.STATE.LEVEL1;
		}
	}
	public void mouseReleased(MouseEvent e)
	{
		
	}
	public boolean contains(int mx, int my,int x, int y,int width,int height)
	{
		if(mx>=x && mx <=mx+width)
		{
			if(my>=y && my<=y+height)
			{
				return true;
			}
			else return false;
		}
		else return false;
	}
	public void render(Graphics g)
	{
		if(game.gameState == game.STATE.MENU)
		{
			g.setColor(Color.RED);
			Font font = new Font("TimesNewRoman",Font.BOLD,120);
			g.setFont(font);
			g.drawString("Eruptions",90,120);
			
			g.setColor(Color.RED);
			g.drawRect(game.WIDTH/2-115,game.HEIGHT/2-100,200,100);
			
			Font f = new Font("TimesNewRoman",Font.BOLD,60);
			g.setFont(f);
			g.drawString("PLAY", game.WIDTH/2-93, game.HEIGHT/2-30);
		}
	}
}
