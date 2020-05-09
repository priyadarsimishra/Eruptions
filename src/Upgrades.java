import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/* This is the upgrade system class during levels
 * where you can buy certain items with your score */
public class Upgrades extends MouseAdapter
{
	private ObjectHandler handler;
	private SpriteTextures texture;
	private boolean backButton = false;
	/* This constructor requires some parameters that render 
	 * objects from sprite sheet and the handler to make changes*/
	public Upgrades(ObjectHandler handler, SpriteTextures texture)
	{
		this.handler = handler;
		this.texture = texture;
	}
	/* This method is for adding cool effects 
	 * when the mouse pointer is above a button */
	public void mouseMoved(MouseEvent e)
	{
		int mx = e.getX(); 
		int my = e.getY();
		if(contains(mx,my,330, 720, 150, 50) && Game.gameState == Game.STATE.UPGRADES) backButton = true;
		else backButton = false;
	}
	/* This method is called around 60 times per second
	 * and it renders anything in Upgrades */
	public void render(Graphics g)
	{
		g.setColor(Color.BLACK);
		Font shopFont = new Font("Superpower Synonym",Font.BOLD,160);
		g.setFont(shopFont);
		g.drawString("UPGRADES",60,125);
		Font backButtonFont = new Font("Arial",Font.BOLD,48);
		g.setFont(backButtonFont);
		g.drawString("Score: "+HUD.SCORE, 5, 165);
		g.setColor(Color.BLACK);
		if(backButton) g.fillRect(330, 720, 150, 50);
		else g.drawRect(330, 720, 150, 50);
		if(backButton) g.setColor(Color.WHITE);
		else g.setColor(Color.BLACK);
		g.drawString("BACK", 335, 760);
		
	}
	/* This method is part of MouseListener and this
	 * is required to make sure you buy items in the shop */
	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		if(contains(mx,my,330, 720, 150, 50) && Game.gameState == Game.STATE.UPGRADES)
		{
			if(Game.stateholder.equalsIgnoreCase("Level1")) 
				Game.gameState = Game.STATE.LEVEL1;
			if(Game.stateholder.equalsIgnoreCase("Level2"))
				Game.gameState = Game.STATE.LEVEL2;
			if(Game.stateholder.equalsIgnoreCase("Level3"))
				Game.gameState = Game.STATE.LEVEL3;
			if(Game.stateholder.equalsIgnoreCase("Level4"))
				Game.gameState = Game.STATE.LEVEL4;
		}
	}
	/* This method returns a boolean and it checks if the
	 * mouse cursor is inside the rectangle is which case
	 * it returns true or else it returns false */
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
}
