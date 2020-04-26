import java.awt.Graphics;
import java.awt.Rectangle;
/* This is the class for the player bullet
 * to you can kill enemies starting from level 2 */
public class Bullet extends GameObject
{
	private double x;
	private double y;
	private double yVel;
	private ID id;
	private ObjectHandler handler;
	private SpriteTextures texture;
	/* The constructor initializes the variables for this object depending on 
	 * what was passed in */
	public Bullet(double x, double y, ID id,ObjectHandler handler,SpriteTextures texture, double yVel) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
		this.yVel = yVel;
	}
	/* This creates a rectangle around the bullet  
	 * which is used to check collision with enemies */
	public Rectangle getRect() 
	{	
		return new Rectangle((int)x,(int)y,32,32);
	}
	/* This method is called 60 times per second and 
	 * it makes the bullet go up and it also adds
	 * a trail using the Trail class and it removes 
	 * the object when it is passed the y value(<0) of
	 * the game screen */
	public void update() 
	{
		y+=yVel;
		if(y<=-32)
			handler.removeObject(this);
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws the bullet into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		g.drawImage(texture.bullet,(int)x,(int)y,null);
	}
	/* These two methods are not needed(yet) but since 
	 * this class extends the abstract class GameObject we 
	 * need to have these methods */
	public double getX() 
	{
		return 0;
	}
	public double getY() 
	{
		return 0;
	}
}
