package game.eruption;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
/* This is the class for the bullet 
 * released by the tank enemy */
public class TankBullet extends GameObject
{
	private double x;
	private double y;
	private double yVel;
	private ID id;
	private SpriteTextures texture;
	private ObjectHandler handler;
	/* The constructor initializes the variables for this object depending on 
	 * what was passed in */
	public TankBullet(double x, double y,double yVel, ID id, ObjectHandler handler,SpriteTextures texture) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.yVel = yVel;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
	}
	/* This creates a rectangle around the tank bullet 
	 * which is used to check collision with the player */
	public Rectangle getRect() 
	{
		return new Rectangle((int)x,(int)y,18,18);
	}
	/* This method is called 60 times per second and 
	 * it makes the tank bullet fall down 
	 * until it is passed the y value of
	 * the game screen */
	public void update() 
	{
		y+=yVel;
		if(y>=800)
			handler.removeObject(this);
		handler.addObject(new Trail((int)x+4,(int)y-10,ID.TankBulletTrail,Color.BLUE,10,10,0.1f,handler));
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws the tank bullet into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		g.drawImage(texture.tankBullet,(int)x,(int)y,18,18,null);
	}

}
