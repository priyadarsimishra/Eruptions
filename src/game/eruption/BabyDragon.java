package game.eruption;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

/* This is the class for the baby dragon that appears when
 * the boss from Level 3 breaks the egg */
public class BabyDragon extends GameObject
{
	public static double x;
	public static double y;
	private ID id;
	private ObjectHandler handler;
	private SpriteTextures texture;
	private int xVel = 6;
	private int yVel = -3;
	private boolean stopMove = false;
	public int dragonFire = 20;
	private Random r = new Random();
	private Image explosion;
	private ImageIcon icon;
	private boolean isExplode;
	private int imageExplosionTime = 0;
	private double colX = 0;
	private double colY = 0;
	private Game game;
	private int callTime = 80;
	/* This is the constructor for the Baby Dragon
	 * and it requires the same parameter as other game objects */
	public BabyDragon(double x, double y, ID id,ObjectHandler handler,SpriteTextures texture,Game game) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
		this.game = game; 
	}
	/* This creates a rectangle around the Baby Dragon 
	 * which is used to check collision */
	public Rectangle getRect() 
	{
		return new Rectangle((int)x,(int)y,48,48);
	}
	/* This method is called 60 times per second and 
	 * it makes the Baby Dragon move left or right
	 * and then it shoots the player */
	public void update() 
	{
		if(HUD.EXPLODERHEALTH>0) 
		{
			if(callTime<=0)
			{
				SoundPlayer.getSound("dragon_call").play();
				callTime = 80;
			}
			else callTime--;
		}
		x+=xVel;
		y+=yVel;
		if(y<500) 
		{
			y = 500;
			yVel = 0;
			stopMove = false;
		}
		if(yVel == 0 && !stopMove)
		{
			xVel = r.nextInt(6)+3;
			stopMove = true;
		}
		if(dragonFire<=0)
		{
			handler.addObject(new EnemyBullet((int)x,(int)y,5,ID.EnemyBullet,handler,texture));
			dragonFire = 20;
		}
		else dragonFire--;
		if(x<=0 || x>=768) xVel*=-1;
		
		//checkCollision();
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws BabyDragon into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		checkCollision();
		icon = new ImageIcon(getClass().getResource("/Explosion.gif"));
		explosion = icon.getImage();
		g.drawImage(texture.babyDragon,(int)x,(int)y,48,48,null);
		if(isExplode)
		{
			if(imageExplosionTime >= 350)
			{
				isExplode = false;
				imageExplosionTime = 0;
			}
			else
			{
				g.drawImage(explosion,(int)colX,(int)colY,50,50,null);
				imageExplosionTime++;
			}
		}
	}
	/* This method checks for collision with game objects
	 * depending on their id */
	public void checkCollision()
	{
		for(int i = 0;i<handler.object.size();i++)
		{
			GameObject object = handler.object.get(i);
			if(object.id == ID.ExplosiveBullet)
			{
				if(getRect().intersects(object.getRect()))
				{
					isExplode = true;
					colX = x;
					colY = y;
					handler.removeObject(object);
					HUD.BABYDRAGONHEALTH-=5;
					if(HUD.BABYDRAGONHEALTH<=0)
					{
						handler.removeObject(this);
						HUD.BABYDRAGONHEALTH = 0;
						Spawn.spawnbabyDragon = false;
						if(game.upgrades.isScoreBoost) HUD.SCORE+=800;
						else HUD.SCORE+=400;
					}

				}
			}
		}
	}
}
