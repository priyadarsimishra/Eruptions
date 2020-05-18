import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/* This is class is for the Underground enemy which is introduced
 * in LEVEL 2 */
public class UnderGroundEnemy extends GameObject
{
	public static double x;
	public static double y;
	private double xVel = 0;
	private double xVel2 = 5;
	private double yVel = 0;
	private ID id;
	private SpriteTextures texture;
	private ObjectHandler handler;
	private double differenceX = 0;
	private double differenceY = 0;
	private double distance = 0;
	public static boolean show = false;
	public int fireRate = 200;
	private Random r = new Random();
	private Color brown = new Color(160,82,45,3);
	/* This is the constructor for the Under Ground Enemy
	 * and it requires the same parameter as other game objects */
	public UnderGroundEnemy(double x,double y,ID id,ObjectHandler handler,SpriteTextures texture)
	{
		super(x,y,id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
	}
	/* This creates a rectangle around the underground enemy 
	 * which is used to check collision with the player */
	public Rectangle getRect() 
	{
		return new Rectangle((int)x,(int)y,54,54);
	}
	/* This method is called 60 times per second and 
	 * it makes the underground enemy move towards the player 
	 * and it appears if the player shoots the enemy */
	public void update() 
	{		
		if(show)
		{
			if(x<=0 || x>=749)
			{
				xVel2 = -xVel2;
			}
			x+=xVel2;
		}
		if(!show)
		{
			differenceX = x - Player.x - 25;
			differenceY = y - Player.y - 19;
			distance = Math.sqrt(Math.pow(x-Player.x, 2) + Math.pow(y - Player.y, 2));
			
			xVel = (-3.5/distance)*differenceX;
			yVel = (-3.5 /distance)*differenceY;
			x+=xVel;
			y+=yVel;
		}
		checkCollision();
	}
	/* This method checks for collision between bullet
	 * and other game Objects such as enemies */
	public void checkCollision()
	{
		for(int i = 0;i<handler.object.size();i++)
		{
			GameObject object = handler.object.get(i);
			if(object.id == ID.Bullet)
			{
				if(getRect().intersects(object.getRect()))
				{
					show = true;
					handler.removeObject(object);
				}
				if(getRect().intersects(object.getRect()) && show)
				{
					HUD.UNDERGROUNDHEALTH-=5;
					handler.removeObject(object);
					if(HUD.UNDERGROUNDHEALTH<=0)
					{
						handler.removeObject(this);
						HUD.SCORE+=500;
					}
				}
			}
		}
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws underground enemy into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{		
		if(show)
		{
			g.drawImage(texture.undergroundEnemy,(int)x,(int)y,54,54,null);
			if(fireRate<=0)
			{
				handler.addObject(new EnemyBullet(this.x+10,this.y+14,r.nextInt(8)+5,ID.EnemyBullet,handler,texture));
				fireRate = 200;
			}
			else fireRate--;
		}
		else
		{
			show = false;
			g.setColor(brown);
			handler.addObject(new Trail((int)x,(int)y,ID.UnderGroundEnemyTrail,brown,32,32,0.07f,handler));
		}
	}
	/* These methods are used to get the location
	 * of the enemy so we can compare with player location */
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
}
