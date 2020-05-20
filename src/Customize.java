import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/* This is the class for the Customize section of the game */
public class Customize extends MouseAdapter
{
	private Game game;
	private ObjectHandler handler;
	private SpriteTextures texture;
	/* This constructor requires some parameters that render 
	 * objects from sprite sheet and the handler to make changes*/
	public Customize(ObjectHandler handler, SpriteTextures texture, Game game)
	{
		this.handler = handler;
		this.texture = texture;
		this.game = game;
	}
	/* This method is called around 60 times per second
	 * and it renders anything in Upgrades */
	public void render(Graphics g)
	{
	}
	/* This method is part of MouseListener and this
	 * is required to make sure you buy items in the customize */
	public void mousePressed(MouseEvent e)
	{
		
	}
	/* This method is part of MouseListener and this
	 * is required to make sure you buy items in the customize 
	 * to check if you released or not */
	public void mouseReleased(MouseEvent e)
	{
		
	}
}
