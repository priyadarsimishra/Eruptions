import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter
{
	private Game game;
	private ObjectHandler handler;
	private Spawn spawner;
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
		if(contains(mx, my,285,300,200,100) && game.gameState == game.STATE.MENU)
		{
			game.gameState = game.STATE.LEVEL1;
		}
		else if(contains(mx,my,game.WIDTH/2-115,game.HEIGHT/2+200,200,100) && game.gameState == game.STATE.MENU)
		{
			System.exit(1);
		}
		else if(contains(mx,my,game.WIDTH/2-115,game.HEIGHT/2-100,200,100))
		{
			game.gameState = game.STATE.MENU;
			handler.clearEnemys();
		}
		else if(contains(mx,my,game.WIDTH/2-115,game.HEIGHT/2+200,200,100))
		{
			game.gameState = game.STATE.LEVEL1;
			handler.clearEnemys();
		}
		
	}
	public void mouseReleased(MouseEvent e)
	{
		
	}
	public boolean contains(int mx, int my,int x, int y,int width,int height)
	{
		if(mx >= x && mx <= x+width)
		{
			if(my >= y && my <= y+height)
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
			
			Font f1 = new Font("TimesNewRoman",Font.BOLD,60);
			g.setFont(f1);
			g.drawString("PLAY", game.WIDTH/2-93, game.HEIGHT/2-30);
			
			g.setColor(Color.RED);
			g.drawRect(game.WIDTH/2-115,game.HEIGHT/2+200,200,100);
			
			Font f2 = new Font("TimesNewRoman",Font.BOLD,60);
			g.setFont(f2);
			g.drawString("EXIT", game.WIDTH/2-85, game.HEIGHT/2+275);
		}
		else if(game.gameState == game.STATE.DEADSCREEN)
		{
			g.setColor(Color.YELLOW);
			
			g.drawRect(game.WIDTH/2-115,game.HEIGHT/2-100,200,100);
			Font f3 = new Font("TimesNewRoman",Font.BOLD,36);
			g.setFont(f3);
			g.drawString("Return To",game.WIDTH/2-108,game.HEIGHT/2-60);
			Font f4 = new Font("TimesNewRoman",Font.BOLD,50);
			g.setFont(f4);
			g.drawString("MENU",game.WIDTH/2-88,game.HEIGHT/2-10);
			
			
		}
	}
}
