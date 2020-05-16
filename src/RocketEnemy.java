import java.awt.Graphics;
import java.awt.Rectangle;
/* This is class is for the RocketEnemy which is introduced
 * in LEVEL 3 */
public class RocketEnemy extends GameObject
{
	public static double x;
	public static double y;
	private ID id;
	private ObjectHandler handler;
	private SpriteTextures texture;
	private int yTimer = 50;
	private int yVel = 6;
	public static boolean bulletShow = true;
	private int fireRate = 120;
	/* This is the constructor for the RocketEnemy
	 * and it requires the same parameter as other game objects */
	public RocketEnemy(double x, double y, ID id,ObjectHandler handler,SpriteTextures texture) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
	}
	/* This creates a rectangle around the RocketEnemy
	 * which is used to check collision with the objects */
	public Rectangle getRect() 
	{
		return new Rectangle((int)x,(int)y,48,48);
	}
	/* This method is called 60 times per second and 
	 * it makes the RocketEnemy shoot at the player */
	public void update() 
	{
		y+=yVel;
		if(yTimer<=0) yVel = 0;
		else yTimer--;
		if(yTimer<=0)
		{
			if(bulletShow)
			{
				handler.addObject(new Rocket(RocketEnemy.x,RocketEnemy.y,ID.Rocket,handler,texture,-3.5));
				bulletShow = false;
			}
			if(Rocket.destroyed)
			{
				if(fireRate<=0)
				{
					handler.addObject(new Rocket(RocketEnemy.x,RocketEnemy.y,ID.Rocket,handler,texture,-3.5));
					Rocket.destroyed = false;
					fireRate = 120;
				}
				else fireRate--;
			}
		}
		checkCollision();
	}
	public void checkCollision()
	{
		for(int i = 0;i<handler.object.size();i++)
		{
			GameObject obj = handler.object.get(i);
			if(obj.id == ID.DoubleBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					HUD.ROCKETHEALTH-=5;
					handler.removeObject(obj);
					if(HUD.ROCKETHEALTH<=0)
					{
						handler.removeObject(this);
						HUD.SCORE+=150;
					}
				}
			}
		}
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws RocketEnemy into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		g.drawImage(texture.rocketEnemy,(int)x,(int)y,48,48,null);
	}
}
