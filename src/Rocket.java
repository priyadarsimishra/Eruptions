import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
/* This is the class for Rocket bullet for the Rocket enemy */
public class Rocket extends GameObject
{
	public static double x;
	public static double y;
	private ID id;
	private ObjectHandler handler;
	private SpriteTextures texture;
	private double yVel;
	private double xVel;
	private double differenceX = 0;
	private double differenceY = 0;
	private double distance = 0;
	public static boolean destroyed = false;
	int centerX = 10;
	int centerY = 10;
	double angle = 0;
	private double speed;
	/* The constructor initializes the 
	 * variables for this object depending on 
	 * what was passed in */
	public Rocket(double x, double y, ID id, ObjectHandler handler,SpriteTextures texture, double speed) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
		this.speed = speed;
	}
	/* This creates a rectangle around the Rocket
	 * which is used to check collision with the player */
	public Rectangle getRect() 
	{
		Rectangle rect = new Rectangle((int)x, (int)y,40,46);
		return rect;
		
	}
	/* This method is called 60 times per second and 
	 * it makes the Rocket fall toward the player */
	public void update() 
	{
		x+=xVel;
		y+=yVel;
		differenceX = x - Player.x - 25;
		differenceY = y - Player.y - 19;
		distance = Math.sqrt(Math.pow(x-Player.x, 2) + Math.pow(y - Player.y, 2));
		
		xVel = (speed/distance)*differenceX;
		yVel = (speed/distance)*differenceY;
		
		checkCollision();
	}
	public void checkCollision()
	{
		for(int i = 0;i<handler.object.size();i++)
		{
			GameObject obj = handler.object.get(i);
			if(obj.id == ID.DoubleBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(obj);
					handler.removeObject(this);
					destroyed = true;
				}
			}
			if(obj.id == ID.ShotgunBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(this);
					handler.removeObject(obj);
					destroyed = true;
				}
			}
		}
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws the Rocket into the game
	 * with it's updated locations(x and y) and rotates it */
	public void render(Graphics g)
	{
		Graphics2D g2d =(Graphics2D)g;
		AffineTransform backup = g2d.getTransform();
		angle = -Math.atan2( x - Player.x , y - Player.y )-(Math.PI/2)-45;
	    AffineTransform at = AffineTransform.getRotateInstance(angle, (int)x+10, (int)y+5);
	    g2d.setTransform(at);
		g.drawImage(texture.rocket,(int)x,(int)y,46,46,null);
	    g2d.setTransform(backup);

	}
}
