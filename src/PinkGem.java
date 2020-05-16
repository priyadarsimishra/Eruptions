import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/* This is class is for the PinkGem in LEVEL 3 */
public class PinkGem extends GameObject
{
	private double x;
	private double y;
	private ID id;
	private ObjectHandler handler;
	private SpriteTextures texture;
	private Random r;
	private int yVel;
	/* The constructor initializes the variables for this object depending on 
	 * what was passed in */
	public PinkGem(double x, double y, ID id,ObjectHandler handler,SpriteTextures texture, int yVel) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
		this.yVel = yVel;
	}
	/* This creates a rectangle around the PinkGem 
	 * which is used to check collision with the player */
	public Rectangle getRect() 
	{
		return new Rectangle((int)x,(int)y,32,32);
	}
	/* This method is called 60 times per second and 
	 * it makes the PinkGem fall down, y value of the game screen */
	public void update() 
	{
		y+=yVel;
		if(y>=800)
			handler.removeObject(this);
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws the PinkGems into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		g.drawImage(texture.pinkGem,(int)x,(int)y,null);
	}
	
}
