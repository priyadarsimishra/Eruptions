import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;
/* This class is for the Level 4 boss */
public class Level4Boss extends GameObject
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
	private Image greenSmoke;
	private ImageIcon icon3;
	private double colX;
	private double colY;
	public static boolean isInvisible = false;
	private int invisbleSpawn = 200;
	private int change = 200;
	public static double spawnX,spawnY;
	private int level4bulletTime = 100;
	private int exploderTime = 500;
	private int rayTime = 450;
	public static boolean makeOne = false;
	private Color kingInvisibleColor = new Color(255,0,0,1);
	public static boolean isAlive = false;
	private Game game;
	/* This is the constructor for the boss which requires
	 * almost the same parameters as the other game Objects */
	public Level4Boss(double x, double y, ID id, SpriteTextures texture, ObjectHandler handler, int yVel, Game game)
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.texture = texture;
		this.handler = handler;
		this.yVel = yVel;
		this.game = game;
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
		if(HUD.LEVEL4BOSSHEALTH<=0)
		{
			handler.removeObject(this);
			rage = false;
			move = false;
			down = true;
			firstX = true;
			noRage = true;
		}
		if(invisbleSpawn<=0)
		{
			isInvisible = true;
			invisbleSpawn = 200;
		}
		else if(isInvisible)
		{
			if(change<=0)
			{
				isInvisible = false;
				change = 200;
			}
			else change--;
		}
		else invisbleSpawn--;
		
		
		if(!makeOne)
		{
			handler.addObject(new Rocket(Level4Boss.x+200,Level4Boss.y+250,ID.Rocket,handler,texture,-1.5));
			makeOne = true;
		}
		if(level4bulletTime <=0 && !rage && Rocket.destroyed)
		{
			handler.addObject(new Rocket(Level4Boss.x+200,Level4Boss.y+250,ID.Rocket,handler,texture,-1.5));
			level4bulletTime = 200;
			Rocket.destroyed = false;
		}
		else level4bulletTime--;
		if(rayTime<=0 && !rage)
		{			
			int speed = r.nextInt(8)+4;
			handler.addObject(new RayBullet(Level4Boss.x+5,Level4Boss.y+250,ID.RayBullet,handler,texture,speed));
			handler.addObject(new RayBullet(Level4Boss.x+200,Level4Boss.y+250,ID.RayBullet,handler,texture,speed));
			rayTime = 500;
		}			
		else 
		{
			rayTime--;
		}
		if(exploderTime<=0)
		{
			if(!isAlive)
				HUD.EXPLODERHEALTH = 20;
			if(HUD.EXPLODERHEALTH>0)
			{
				LevelDisplay.exploderTime = 200;
				HUD.EXPLODERHEALTH = 20;
				handler.addObject(new ExploderEnemy(Level4Boss.x+50,Level4Boss.y+250,ID.ExploderEnemy,handler,texture,-1.5,this.game));
				exploderTime = 550;
				isAlive = true;
			}
		}
		else exploderTime--;
//		if(HUD.EXPLODERHEALTH<=0)
//			Spawn.exploderEnemyTime = true;
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
		icon3 = new ImageIcon(getClass().getResource("/greenSmoke.gif"));
		greenSmoke = icon3.getImage();
		if(!isInvisible)
		{
			g.drawImage(fire,(int)x,(int)y-70,250,100,null);
			g.drawImage(texture.BossLevel4,(int)x,(int)y,250,250,null);
			g.drawImage(greenSmoke,(int)x+65,(int)y+90,120,120,null);
		}
		else
		{
			handler.addObject(new Trail((int)x,(int)y,ID.Level4BossInvisibleTrail,kingInvisibleColor,250,250,0.07f,handler));
		}
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
			if(object.id == ID.Crate)
			{
				if(getRect().intersects(object.getRect()))
				{
					handler.removeObject(object);
				}
			}
		}
	}
}
