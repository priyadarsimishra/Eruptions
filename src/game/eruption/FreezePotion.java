package game.eruption;
import java.awt.Graphics;
import java.awt.Rectangle;
/* This is the class for the FreezePotion 
 * released by the Wizard */
public class FreezePotion extends GameObject
{
	private double x;
	private double y;
	private double yVel;
	private ID id;
	private SpriteTextures texture;
	private ObjectHandler handler;
	/* The constructor initializes the variables for this object depending on 
	 * what was passed in */
	public FreezePotion(double x, double y,double yVel, ID id, ObjectHandler handler,SpriteTextures texture) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.yVel = yVel;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
	}
	/* This creates a rectangle around the FreezePotion 
	 * which is used to check collision with the player */
	public Rectangle getRect() 
	{
		return new Rectangle((int)x,(int)y,32,32);
	}
	/* This method is called 60 times per second and 
	 * it makes the FreezePotion fall down */
	public void update() 
	{
		y+=yVel;
		if(y>=800)
			handler.removeObject(this);
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws the FreezePotion into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		g.drawImage(texture.freezePotion,(int)x,(int)y,null);
	}

}
