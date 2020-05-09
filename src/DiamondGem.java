import java.awt.Graphics;
import java.awt.Rectangle;

/* This class is for the diamond in the game
 * which the player can collect */
public class DiamondGem extends GameObject
{
	private double x;
	private double y;
	private double xVel;
	private double yVel;
	private ID id;
	private SpriteTextures texture;
	private ObjectHandler handler;
	/* The constructor initializes the variables for this object depending on 
	 * what was passed in */
	public DiamondGem(double x, double y, ID id, ObjectHandler handler, SpriteTextures texture, int xVel, int yVel) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
		this.xVel = xVel;
		this.yVel = yVel;
	}
	/* This creates a rectangle around the diamond 
	 * which is used to check collision with the player */
	public Rectangle getRect() 
	{
		return new Rectangle((int)x,(int)y,48,48);
	}
	/* This method is called 60 times per second and 
	 * it makes the diamond falls in different directions(this one is faster + rare) 
	 * and it also adds a trail using the Trail class and it removes 
	 * the object when it is passed the y value of
	 * the game screen */
	public void update() 
	{
		x+=xVel;
		y+=yVel;
		if(x<=0 || x>=752) xVel*=-1;
		if(y>=800)
		{
			handler.removeObject(this);
		}
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws the diamonds into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		g.drawImage(texture.diamond,(int)x,(int)y,48,48,null);
	}
}
