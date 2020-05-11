import java.awt.Graphics;
import java.awt.Rectangle;

/* This is the class for the bullet that splits into two 
 * used to kill boss in Level 2 */
public class SplitBullet2 extends GameObject
{
	public static double x;
	public static double y;
	private ID id;
	private int yVel = -12;
	private ObjectHandler handler;
	private SpriteTextures texture;
	/* The constructor initializes the variables for this object depending on 
	 * what was passed in */ 
	public SplitBullet2(double x, double y, ID id,ObjectHandler handler,SpriteTextures texture) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
	}
	/* This creates a rectangle around the Split Bullet  
	 * which is used to check collision with enemies */
	public Rectangle getRect() 
	{
		return new Rectangle((int)x,(int)y,20,20);
	}
	/* This method is called 60 times per second and 
	 * it makes the Split Bullet go up and is removed when
	 * it is passed the game screen */
	public void update() 
	{
		y+=yVel;
		if(y<=-32)
			handler.removeObject(this);
		checkCollision();
		if(x>=780)
			x = 778;
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws the Split Bullet into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		g.drawImage(texture.doubleBullet,(int)x,(int)y,20,20,null);
	}
	/* This method checks Collision depending on the 
	 * game Object which decreases the health
	 * of the boss */
	public void checkCollision()
	{
		for(int i =0;i<handler.object.size();i++)
		{
			GameObject obj = handler.object.get(i);
			if(obj.id == ID.Level2Boss)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(this);
					HUD.LEVEL2BOSSHEALTH-=15;
				}
			}
		}
	}
}
