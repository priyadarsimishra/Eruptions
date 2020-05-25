package game.eruption;
import java.awt.Graphics;
import java.awt.Rectangle;

/* This class is for the HealthPotion in the game
 * which the player can collect */
public class HealthPotion extends GameObject
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
	public HealthPotion(double x, double y, ID id, ObjectHandler handler, SpriteTextures texture, int xVel, int yVel) 
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
	/* This creates a rectangle around the HealthPotion 
	 * which is used to check collision with the player */
	public Rectangle getRect() 
	{
		return new Rectangle((int)x,(int)y,32,32);
	}
	/* This method is called 60 times per second and 
	 * it makes the HealthPotion falls in different directions */
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
	 * and it takes the values and draws the HealthPotion into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		g.drawImage(texture.healthPotion,(int)x,(int)y,32,32,null);
	}
}
