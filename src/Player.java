import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
/* This class is for creating the Player when the handler 
adds this object into the game as it is part of the GameObject abstract class */
public class Player extends GameObject
{
	public double x;
	public double y;
	private double xVel = 0;
	private double yVel = 0;
	private ID id;
 	private SpriteTextures texture;
	private ObjectHandler handler;
	private Game game;
	public int bucketCount = 0;
	/* This constructor has similar parameters to other game objects
	 * and this is required to make the player work */
	public Player(double x,double y,Game game,SpriteTextures texture,ObjectHandler handler,ID id)
	{
		super(x,y,id);
		this.x = x;
		this.y = y;
		this.texture = texture;
		this.handler = handler;
		this.game = game;
	}
	/* This method creates a rectangle around the player
	 * so we can deal with collision */
	public Rectangle getRect()
	{
		return new Rectangle((int)x,(int)y,50,38);
	}
	/* This method is called 60 times per second
	 * and it makes the player go left or right and
	 * it restricts it so it cannot go off screen 
	 * and the it also checks for collision */
	public void update()
	{
		x+=xVel;
		x = Game.restrict(x, 0, Game.WIDTH-50);
		checkCollision();
	}
	/* This method is called 60 times per second
	 * Depending on the state it draws the player */
	public void render(Graphics g)
	{
		g.drawImage(texture.player,(int)x,(int)y,50,38,null);
	}
	/* This method checks Collision depending on the 
	 * game Object which either adds to the score or 
	 * changes the health */
	public void checkCollision()
	{
		for(int i=0;i<handler.object.size();i++)
		{
			GameObject obj = handler.object.get(i);
			if(obj.id == ID.Fireball || obj.id == ID.FireballTrail)
			{
				if(getRect().intersects(obj.getRect()))
				{
					HUD.HEALTH-=1;
				}
			}
			if(obj.id == ID.Level1BossFireball)
			{
				if(getRect().intersects(obj.getRect()))
				{
					HUD.HEALTH-=2;
				}
			}
			if(obj.id == ID.BronzeCoin)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(obj);
					HUD.SCORE+=100;
				}
			}
			if(obj.id == ID.SilverCoin)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(obj);
					HUD.SCORE+=250;
				}
			}
			if(obj.id == ID.GoldCoin)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(obj);
					HUD.SCORE+=500;
				}
			}
			if(obj.id == ID.Waterbucket)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(obj);
					bucketCount++;
				}
			}
		}
	}
	/* This method sets XVel so this will be 
	 * used to change the speed */
	public void setXVel(double xVel)
	{
		this.xVel = xVel;
	}
	/* This method gets XVel so this return
	 * the speed of the player */
	public double getXVel()
	{
		return xVel;
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
