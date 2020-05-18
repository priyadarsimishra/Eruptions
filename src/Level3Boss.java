import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;
/* This class is for the Level 3 boss*/
public class Level3Boss extends GameObject
{
	public static double x;
	public static double y;
	private ID id;
	private int xVel;
	private int yVel;
	private int timerX = 40;
	private int timerY = 15;
	private SpriteTextures texture;
	private ObjectHandler handler;
	private boolean rage = false;
	private boolean move = false;
	private boolean down = true;
	private boolean firstX = true;
	private boolean noRage = true;
	private double topY = 176;
	private Random r = new Random();
	private int rageTimer = 0;
	private boolean isExplode;
	private int imageExplosionTime = 0;
	private Image explosion;
	private ImageIcon icon;
	private Image fire;
	private ImageIcon icon2;
	private Image greenFire;
	private ImageIcon icon3;
	private double colX;
	private double colY;
	public static double spawnX,spawnY;
	/* This is the constructor for the boss which requires
	 * almost the same parameters as the other game Objects */
	public Level3Boss(double x, double y, ID id, SpriteTextures texture, ObjectHandler handler, int yVel)
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.texture = texture;
		this.handler = handler;
		this.yVel = yVel;
	}
	/* This method returns a rectangle used for collision detection */
	public Rectangle getRect() 
	{
		return new Rectangle((int)x,(int)y,250,250);
	}
	/* This method runs 60 times per second and it gives 
	 * the boss the downward motion and the pause and left and right
	 * movement as well as the bouncing of the walls */
	public void update()
	{
		x+=xVel;
		y+=yVel;
		if(timerY<=0)
		{
			yVel = 0;
		}
		else timerY--;
		if(timerY<=0) timerX--;
		if(!rage)
		{
			if(timerX<=0)
			{
				if(x <= 0) x = 0;
				if(xVel == 0) xVel = r.nextInt(13)+8;
			}
			if(x<=0 || x>=558) xVel *= -1.05;
		}
		if(noRage)
		{
			rageTimer = r.nextInt(120)+50;
			noRage = false;
		}
		if(rageTimer<=0 && !noRage)
		{
			rage = true;
			down = true;
			move = false;
			noRage = true;
		}
		else rageTimer--;
		if(rage)
		{
			xVel = 0;
			if(down)
			{
				yVel = r.nextInt(20)+10;
			}
			if(y>=Game.HEIGHT-275) 
			{
				move = true;
				down = false;
			}
			if(y<=topY)
			{
				move = false;
				rage = false;
				rageTimer = 200;
				timerX = 20;
				noRage = true;
			}
			if(move)
				yVel = -10;
			
		}
		if(HUD.LEVEL3BOSSHEALTH<=0)
		{
			handler.removeObject(this);
			rage = false;
			move = false;
			down = true;
			firstX = true;
			noRage = true;
		}
		checkCollision();
	}
	/* This method also runs 60 times per second and 
	 * it draws according to the updates of the boss */
	public void render(Graphics g)
	{
		icon = new ImageIcon(getClass().getResource("/Explosion.gif"));
		explosion = icon.getImage();
		icon2 = new ImageIcon(getClass().getResource("/fire.gif"));
		fire = icon2.getImage();
		icon3 = new ImageIcon(getClass().getResource("greenFire.gif"));
		greenFire = icon3.getImage();
		g.drawImage(fire,(int)x,(int)y-60,250,100,null);
		g.drawImage(texture.Bosslevel3,(int)x,(int)y,250,250,null);
		g.drawImage(greenFire,(int)x-20,(int)y+150,100,100,null);
		g.drawImage(greenFire,(int)x+165,(int)y+150,100,100,null);
		if(isExplode)
		{
			if(imageExplosionTime >= 350)
			{
				isExplode = false;
				imageExplosionTime = 0;
			}
			else 
			{
				g.drawImage(explosion,(int)colX,(int)colY,null);
				imageExplosionTime++;
			}
		}
	}
	/* This method detects collision with a key if it touches
	 * a key the key will disappear */
	public void checkCollision()
	{
		for(int i = 0;i<handler.object.size();i++)
		{
			GameObject object = handler.object.get(i);
			if(object.id == ID.Egg)
			{
				if(getRect().intersects(object.getRect()))
				{
					if(HUD.BABYDRAGONHEALTH<=0)
						Spawn.spawnbabyDragon = true;
					spawnX = x;
					spawnY = y;
					handler.removeObject(object);

				}
			}
			if(object.id == ID.ExplosiveBullet)
			{
				if(getRect().intersects(object.getRect()))
				{
					isExplode = true;
					colX = x;
					colY = y;
					handler.removeObject(object);
					HUD.LEVEL3BOSSHEALTH-=18;
				}
			}
		}
	}
}
