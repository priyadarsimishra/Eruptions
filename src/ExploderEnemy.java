import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

/* This is class is for the Exploder Enemy which is introduced
 * in LEVEL 3 */
public class ExploderEnemy extends GameObject
{
	public static double x;
	public static double y;
	private double xVel = 0;
	private double yVel = 0;
	private ID id;
	private SpriteTextures texture;
	private ObjectHandler handler;
	private double differenceX = 0;
	private double differenceY = 0;
	private double distance = 0;
	private Random r = new Random();
	private boolean isExplode = false;
	private Image explosion;
	private ImageIcon icon;
	private int imageExplosionTime = 0;
	private double speed;
	/* This is the constructor for the Exploder Enemy
	 * and it requires the same parameter as other game objects */
	public ExploderEnemy(double x,double y,ID id,ObjectHandler handler,SpriteTextures texture, double speed)
	{
		super(x,y,id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
		this.speed = speed;
	}
	/* This creates a rectangle around the Exploder Enemy 
	 * which is used to check collision with the player */
	public Rectangle getRect() 
	{
		return new Rectangle((int)x,(int)y,32,32);
	}
	/* This method is called 60 times per second and 
	 * it makes the Exploder Enemy move towards the player 
	 * and it appears if the player shoots the enemy */
	public void update() 
	{		
		differenceX = x - Player.x;
		differenceY = y - Player.y;
		distance = Math.sqrt(Math.pow(x-Player.x, 2) + Math.pow(y - Player.y, 2));
			
		xVel = (speed/distance)*differenceX;
		yVel = (speed/distance)*differenceY;
		x+=xVel;
		y+=yVel;
		checkCollision();
	}
	/* This method checks for collision with game objects
	 * depending on their id */
	public void checkCollision()
	{
		for(int i = 0;i<handler.object.size();i++)
		{
			GameObject obj = handler.object.get(i);
			if(obj.id == ID.DoubleBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					HUD.EXPLODERHEALTH-=5;
					handler.removeObject(obj);
					if(HUD.EXPLODERHEALTH<=0)
					{
						handler.removeObject(this);
						HUD.SCORE+=200;
					}
				}
			}
			if(obj.id == ID.ShotgunBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					HUD.EXPLODERHEALTH-=4;
					handler.removeObject(obj);
					isExplode = true;
					if(HUD.EXPLODERHEALTH<=0)
					{
						HUD.EXPLODERHEALTH = 0;
						handler.removeObject(this);
						HUD.SCORE+=200;
					}
				}
			}
		}
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws Exploder Enemy into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		icon = new ImageIcon(getClass().getResource("/Bomb.gif"));
		explosion = icon.getImage();
		g.drawImage(texture.exploderEnemy,(int)x,(int)y,null);
		if(isExplode)
		{
			if(imageExplosionTime >= 200)
			{
				isExplode = false;
				imageExplosionTime = 0;
			}
			else
			{
				g.drawImage(explosion,(int)this.x-10,(int)this.y-30,60,60,null);
				imageExplosionTime++;
			}
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
