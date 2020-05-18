import java.awt.Graphics;
import java.awt.Rectangle;

/* This is the arrow for the Level 2 boss */
public class BossArrow extends GameObject
{
	private double x;
	private double y;
	private double yVel;
	private ID id;
	private ObjectHandler handler;
	private SpriteTextures texture;
	/* The constructor initializes the variables for this object depending on 
	 * what was passed in */
	public BossArrow(double x, double y, ID id, ObjectHandler handler,SpriteTextures texture, double yVel) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
		this.yVel = yVel;
	}
	/* This creates a rectangle around the arrow  
	 * which is used to check collision with the player */
	public Rectangle getRect() 
	{
		return new Rectangle((int)x+18, (int)y, 20, 56);
	}	
	/* This method is called 60 times per second and 
	 * it makes the arrow fall down and is removes when it passes 
	 * the game screen */
	public void update() 
	{
		y+=yVel;
		if(y>=820)
			handler.removeObject(this);
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws the enemy bullet into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		g.drawImage(texture.bossArrow,(int)x,(int)y,56,56,null);
	}	
}
