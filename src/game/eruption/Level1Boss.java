package game.eruption;
import java.awt.Graphics;
import java.awt.Rectangle;

/* This is the class for the Boss 
 * of Level 1 */
public class Level1Boss extends GameObject
{
	public static double x;
	public static double y; 
	private ID id;
	private int yVel;
	private int xVel;
	private SpriteTextures texture;
	private int ytimer = 20;
	private int xTimer = 40;
	private Game game;
	/* This is the constructor for the boss which requires
	 * almost the same parameters as the other game Objects */
	public Level1Boss(double x, double y, ID id, SpriteTextures texture,Game game,int yVel) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.texture = texture;
		this.game = game;
		this.yVel = yVel;
	}
	/* This method runs 60 times per second and it gives 
	 * the enemy the downward motion and the pause and left and right
	 * movement as well as the bouncing of the walls */
	public void update() 
	{
		x+=xVel;
		y+=yVel;
		if(ytimer<=0)
		{
			yVel = 0;
		}
		else ytimer--;
		if(ytimer<=0) xTimer--;
		if(xTimer<=0)
		{
			if(xVel == 0) xVel = 5;
		}
		
		if(x<=0 || x>=600) xVel *= -1;
	}
	/* This method also runs 60 times per second and 
	 * it draws according to the updates of the boss */
	public void render(Graphics g) 
	{
		g.drawImage(texture.BossLevel1,(int)x,(int)y,200,200,null);
	}
	/* This method returns a rectangle used for collision detection */
	public Rectangle getRect()
	{	
		return new Rectangle((int)x,(int)y,200,200);
	}
	/* These two method return x and y locations 
	 * of the players but it is not used right now */
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
}
