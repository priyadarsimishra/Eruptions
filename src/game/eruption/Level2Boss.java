package game.eruption;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
/* This class is for the Level 2 boss*/
public class Level2Boss extends GameObject
{
	public static double x;
	public static double y;
	private ID id;
	private int xVel;
	private int yVel;
	private int timerX = 40;
	private int timerY = 35;
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
	/* This is the constructor for the boss which requires
	 * almost the same parameters as the other game Objects */
	public Level2Boss(double x, double y, ID id, SpriteTextures texture, ObjectHandler handler, int yVel)
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
		return new Rectangle((int)x,(int)y,200,200);
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
				if(xVel == 0) xVel = 8;
			}
			if(x<=0 || x>=600) xVel *= -1;
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
				SoundPlayer.getSound("boss_rage").play();
				yVel = r.nextInt(20)+10;
			}
			if(y>=Game.HEIGHT-225) 
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
			
			checkCollision();
		}
		if(HUD.LEVEL2BOSSHEALTH<=0)
		{
			handler.removeObject(this);
			rage = false;
			move = false;
			down = true;
			firstX = true;
			noRage = true;
		}
	}
	/* This method also runs 60 times per second and 
	 * it draws according to the updates of the boss */
	public void render(Graphics g)
	{
		g.drawImage(texture.Bosslevel2,(int)x,(int)y,200,200,null);
	}
	/* This method detects collision with a key if it touches
	 * a key the key will disappear */
	public void checkCollision()
	{
		for(int i = 0;i<handler.object.size();i++)
		{
			GameObject object = handler.object.get(i);
			if(object.id == ID.Key)
			{
				if(getRect().intersects(object.getRect()))
				{
					SoundPlayer.getSound("key_pickUp").play();
					handler.removeObject(object);
				}
			}
		}
	}
}
