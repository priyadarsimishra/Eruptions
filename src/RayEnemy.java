import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
/* This is class is for the Ray enemy which is introduced
 * in LEVEL 3 */
public class RayEnemy extends GameObject
{
	public static double x;
	public static double y;
	private ID id;
	private double xVel;
	private double yVel;
	private Random r = new Random();
	private int xTimer = 5;
	private int yTimer = r.nextInt(50)+30;
	private int rayRate = 100;
	private ObjectHandler handler;
	private SpriteTextures texture;
	/* This is the constructor for the Ray Enemy
	 * and it requires the same parameter as other game objects */
	public RayEnemy(double x, double y, ID id,ObjectHandler handler,SpriteTextures texture) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
	}
	/* This creates a rectangle around the Ray Enemy 
	 * which is used to check collision with the objects */
	public Rectangle getRect() 
	{		
		return new Rectangle((int)x,(int)y,54,54);
	}
	/* This method is called 60 times per second and 
	 * it makes the Ray Enemy move left and right */
	public void update() 
	{
		x+=xVel;
		y+=yVel;
		if(yTimer<=0)
			yVel = 0;
		else 
		{
			yTimer--;
			yVel = 8;
		}
		if(yTimer<=0) xTimer--;
		if(xTimer<=0)
		{
			if(xVel==0) xVel = 4;
		}
		if(x<=0 || x>=745) xVel*=-1;
		
		if(rayRate<=0)
		{
			handler.addObject(new RayBullet(x+23,y+30,ID.RayBullet,handler,texture,r.nextInt(8)+4));
			rayRate = 100;
		}
		else
		{
			rayRate--;
		}
		checkCollision();
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws Ray Enemy into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g)
	{
		g.drawImage(texture.rayEnemy,(int)x,(int)y,54,54,null);
	}
	/* This checks for collision with the bullets to reduce health */
	public void checkCollision()
	{
		for(int i = 0;i<handler.object.size();i++)
		{
			GameObject obj = handler.object.get(i);
			if(obj.id == ID.DoubleBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					HUD.RAYHEALTH-=3;
					handler.removeObject(obj);
					if(HUD.RAYHEALTH<=0)
					{
						handler.removeObject(this);
						HUD.SCORE+=350;
					}
				}
			}
		}
	}
}
