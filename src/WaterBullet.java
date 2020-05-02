import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
/* This is the class for the player bullet
 * to you can kill the boss starting in level 1 */
public class WaterBullet extends GameObject
{
	private double x;
	private double y;
	private double yVel;
	private ID id;
	private ObjectHandler handler;
	private SpriteTextures texture;
	/* The constructor initializes the variables for this object depending on 
	 * what was passed in */
	public WaterBullet(double x, double y, ID id,ObjectHandler handler,SpriteTextures texture, double yVel) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
		this.yVel = yVel;
	}
	/* This creates a rectangle around the WaterBullet  
	 * which is used to check collision with enemies */
	public Rectangle getRect() 
	{	
		return new Rectangle((int)x,(int)y,32,32);
	}
	/* This method is called 60 times per second and 
	 * it makes the WaterBullet go up and it also adds
	 * the object is removed when it is passed the y value(<0) of
	 * the game screen */
	public void update() 
	{
		y+=yVel;
		if(y<=-32)
			handler.removeObject(this);
		checkCollision();
		handler.addObject(new Trail((int)x+10,(int)y-15,ID.WaterBullet,Color.CYAN,16,16,0.08f,handler));
		
	}
	/* This method checks Collision depending on the 
	 * game Object which either decreases the health
	 * of the boss */
	public void checkCollision()
	{
		for(int i = 0; i<handler.object.size();i++)
		{
			GameObject obj = handler.object.get(i);
			if(obj.id == ID.Level1Boss)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(this);
					HUD.LEVEL1BOSSHEALTH-=20;
				}
			}
			if(obj.id == ID.Level1BossBomb)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(obj);
					handler.removeObject(this);
				}
			}
		}
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws the WaterBullet into the game
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
