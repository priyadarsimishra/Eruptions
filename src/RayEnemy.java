import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;
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
	private int yTimer = r.nextInt(25)+20;
	private int rayRate = 100;
	private ObjectHandler handler;
	private SpriteTextures texture;
	private Image explosion;
	private ImageIcon icon;
	private boolean isExplode;
	private int imageExplosionTime = 0;
	private boolean score = false;
	private Game game;
	/* This is the constructor for the Ray Enemy
	 * and it requires the same parameter as other game objects */
	public RayEnemy(double x, double y, ID id,ObjectHandler handler,SpriteTextures texture, Game game) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
		this.game = game;
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
		icon = new ImageIcon(getClass().getResource("/Bomb.gif"));
		explosion = icon.getImage();
		if(isExplode)
		{
			if(imageExplosionTime >= 350)
			{
				isExplode = false;
				imageExplosionTime = 0;
			}
			else
			{
				g.drawImage(explosion,(int)x,(int)y,50,50,null);
				imageExplosionTime++;
			}
		}
	}
	/* This checks for collision with the bullets to reduce health */
	public void checkCollision()
	{
		boolean stopScore = true;
		for(int i = 0;i<handler.object.size();i++)
		{
			//System.out.println("handler.object.size(): "+handler.object.size());
			GameObject obj = handler.object.get(i);
			if(obj.id == ID.DoubleBullet)
			{
 				if(getRect().intersects(obj.getRect()))
				{
 					if(game.upgrades.isSniper) HUD.RAYHEALTH-=15;
					else if(game.upgrades.isDualPistol) HUD.RAYHEALTH-=2;
					else if(game.upgrades.isShotgun) HUD.RAYHEALTH-=1;
					else HUD.RAYHEALTH-=3;
					handler.removeObject(obj);
					if(HUD.RAYHEALTH<=0 && stopScore)
					{
						handler.removeObject(obj);
						HUD.RAYHEALTH = 0;
						handler.removeObject(this);
						if(stopScore)
						{
							HUD.SCORE+=350;
							stopScore = false;
						}
 						System.out.println("Score after: "+HUD.SCORE);
					}
					System.out.println("stopScore:"+stopScore);
				}
			}
			if(obj.id == ID.ExplosiveBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					isExplode = true;
					if(game.upgrades.isSniper) HUD.RAYHEALTH-=10;
					else if(game.upgrades.isDualPistol) HUD.RAYHEALTH-=4;
					else if(game.upgrades.isShotgun) HUD.RAYHEALTH-=1;
					else HUD.RAYHEALTH-=2;
					handler.removeObject(obj);
					if(HUD.RAYHEALTH<=0)
					{
						HUD.RAYHEALTH = 0;
						handler.removeObject(this);
						HUD.SCORE+=350;
					}
				}
			}
			if(obj.id == ID.Bullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					if(game.upgrades.isSniper) HUD.RAYHEALTH-=14;
					else if(game.upgrades.isDualPistol) HUD.RAYHEALTH-=3;
					else if(game.upgrades.isShotgun) HUD.RAYHEALTH-=1;
					HUD.RAYHEALTH-=5;
					handler.removeObject(obj);
					if(HUD.RAYHEALTH<=0)
					{
						HUD.RAYHEALTH = 0;

						handler.removeObject(this);
						HUD.SCORE+=350;
					}
				}
			}
			if(obj.id == ID.ShotgunBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					if(game.upgrades.isSniper) HUD.RAYHEALTH-=10;
					else if(game.upgrades.isDualPistol) HUD.RAYHEALTH-=4;
					else if(game.upgrades.isShotgun) HUD.RAYHEALTH-=2;
					else HUD.RAYHEALTH-=3;
					handler.removeObject(obj);
					if(HUD.RAYHEALTH<=0)
					{
						HUD.RAYHEALTH = 0;
						handler.removeObject(this);
						HUD.SCORE+=350;
					}
				}
			}
		}
	}
}
