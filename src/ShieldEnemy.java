import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

/* This is for the shield enemy in LEVEL 4 */
public class ShieldEnemy extends GameObject
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
	public static boolean startDamage = false;
	public static boolean once = false;
	private int fireRate = 30;
	/* This is the constructor for the shield Enemy
	 * and it requires the same parameter as other game objects */
	public ShieldEnemy(double x, double y, ID id,ObjectHandler handler,SpriteTextures texture) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
	}
	/* This creates a rectangle around the shield Enemy 
	 * which is used to check collision with the objects */
	public Rectangle getRect() 
	{
		return new Rectangle((int)x,(int)y,40,40);
	}
	/* This method is called 60 times per second and 
	 * it makes the shield Enemy move left and right */
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
		if(x<=0 || x>=760) xVel*=-1;
		if(!startDamage && !once)
		{
		    handler.addObject(new Shield(ShieldEnemy.x,ShieldEnemy.y+50,ID.Shield,handler,texture));
			once = true;
		}
		if(fireRate<=0)
		{
			handler.addObject(new EnemyBullet(x+15,y,r.nextInt(15)+8,ID.EnemyBullet,handler,texture));
			fireRate = 30;
		}
		else fireRate--;
		checkCollision();
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws shield Enemy into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		icon = new ImageIcon(getClass().getResource("/Bomb.gif"));
		explosion = icon.getImage();
		g.drawImage(texture.shieldEnemy,(int)x,(int)y,40,40,null);
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
	/* This method checks for collision between enemy
	 * and other game Objects */
	public void checkCollision()
	{
		for(int i= 0;i<handler.object.size();i++)
		{
			GameObject obj = handler.object.get(i);
			if(obj.id == ID.ExplosiveBullet)
			{
				if(getRect().intersects(obj.getRect()) && startDamage)
				{
					HUD.SHIELDENEMYHEALTH-=8;
					isHit = true;
					handler.removeObject(obj);
					if(HUD.SHIELDENEMYHEALTH<=0)
					{
						once = false;
						startDamage = false;
						HUD.SHIELDENEMYHEALTH = 0;
						HUD.SCORE+=250;
						handler.removeObject(this);
					}
				}
			}
		}
	}
}
