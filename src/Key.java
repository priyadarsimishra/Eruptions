import java.awt.Graphics;
import java.awt.Rectangle;

/* This is class is for the Key in LEVEL 2 */
public class Key extends GameObject
{
	private double x;
	private double y;
	private ID id;
	private ObjectHandler handler;
	private SpriteTextures texture;
	public Key(double x, double y, ID id,ObjectHandler handler,SpriteTextures texture) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
	}
	/* This creates a rectangle around the Key
	 * which is used to check collision with the player */
	public Rectangle getRect()
	{
		return new Rectangle((int)x,(int)y,36,36);
	}
	/* This method is not needed but is required
	 * as this class extends the Game Object abstract class */
	public void update() {}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws the key into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{	
		g.drawImage(texture.key,(int)x,(int)y,36,36,null);
	}
}
