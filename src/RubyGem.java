import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/* This class is for the ruby in the game
 * which the player can collect */
public class RubyGem extends GameObject
{
	private double x;
	private double y;
	private int yVel;
	private ID id;
	private Random r = new Random();
	private SpriteTextures texture;
	private ObjectHandler handler;
	/* The constructor initializes the variables for this object depending on 
	 * what was passed in */
	public RubyGem(double x, double y, ID id, ObjectHandler handler, SpriteTextures texture, int yVel) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
		this.yVel = yVel;
	}
	/* This creates a rectangle around the ruby 
	 * which is used to check collision with the player */
	public Rectangle getRect() 
	{
		return new Rectangle((int)x,(int)y,32,32);
	}
	/* This method is called 60 times per second and 
	 * it makes the ruby falls in different directions(this one is faster + rare) 
	 * and it also adds a trail using the Trail class and it removes 
	 * the object when it is passed the y value of
	 * the game screen */
	public void update() 
	{
		y+=yVel;
		if(y>=800)
		{
			handler.removeObject(this);
		}
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws the ruby into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		g.drawImage(texture.ruby,(int)x,(int)y,null);
	}
}