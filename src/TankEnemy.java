import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

/* This is for the tank enemy in LEVEL 4*/
public class TankEnemy extends GameObject
{
	public static double x,y;
	private ID id;
	private ObjectHandler handler;
	private SpriteTextures texture;
	private double xVel;
	private double yVel;
	private Random r = new Random();
	private int xTimer = 5;
	private int yTimer = 40;
	private boolean isHit = false;
	private Image explosion;
	private ImageIcon icon;
	private int imageExplosionTime = 0;
	private int fireRate = 120;
	/* This is the constructor for the Tank Enemy
	 * and it requires the same parameter as other game objects */
	public TankEnemy(double x, double y, ID id,ObjectHandler handler,SpriteTextures texture) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
	}
	/* This creates a rectangle around the Tank Enemy 
	 * which is used to check collision with the objects */
	public Rectangle getRect() 
	{
		return new Rectangle((int)x,(int)y,60,60);
	}
	/* This method is called 60 times per second and 
	 * it makes the Tank Enemy move left and right */
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
			if(xVel==0) xVel = r.nextInt(7)+4;
		}
		if(x<=0 || x>=740) xVel*=-1;
		if(fireRate<=0)
		{
			int randSpeed = r.nextInt(15)+8;
			handler.addObject(new TankBullet(x,y+60,randSpeed,ID.TankBullet,handler,texture));
			handler.addObject(new TankBullet(x+22,y+60,randSpeed,ID.TankBullet,handler,texture));
			handler.addObject(new TankBullet(x+44,y+60,randSpeed,ID.TankBullet,handler,texture));
			fireRate = 120;
		}
		else fireRate--;
		checkCollision();
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws Tank Enemy into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		icon = new ImageIcon(getClass().getResource("/Bomb.gif"));
		explosion = icon.getImage();
		g.drawImage(texture.tankEnemy,(int)x,(int)y,60,60,null);
		if(isHit)
		{
			if(imageExplosionTime >= 200)
			{
				isHit = false;
				imageExplosionTime = 0;
			}
			else
			{
				g.drawImage(explosion,(int)x-20,(int)y-40,100,100,null);
				imageExplosionTime++;
			}
		}
	}	
	/* This method checks for collision between bullet
	 * and other game Objects */
	public void checkCollision()
	{
		for(int i= 0;i<handler.object.size();i++)
		{
			GameObject obj = handler.object.get(i);
			if(obj.id == ID.ExplosiveBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					HUD.TANKHEALTH-=8;
					isHit = true;
					handler.removeObject(obj);
					if(HUD.TANKHEALTH<=0)
					{
						HUD.TANKHEALTH = 0;
						HUD.SCORE+=450;
						handler.removeObject(this);
					}
				}
			}
		}
	}
}
