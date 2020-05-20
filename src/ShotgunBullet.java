import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
/* This is the class for the player bullet
 * to you can kill the boss starting in level 4 */
public class ShotgunBullet extends GameObject
{
	private double x;
	private double y;
	private double yVel;
	private ID id;
	private ObjectHandler handler;
	private SpriteTextures texture;
	private boolean split = false;
	private Game game;
	/* The constructor initializes the variables for this object depending on 
	 * what was passed in */
	public ShotgunBullet(double x, double y, ID id,ObjectHandler handler,SpriteTextures texture, double yVel, Game game) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
		this.yVel = yVel;
		this.game = game;
	}
	/* This creates a rectangle around the DoubleBullet  
	 * which is used to check collision with enemies */
	public Rectangle getRect() 
	{	
		if(game.upgrades.isSniper) return new Rectangle((int)x-2,(int)y,15,15);
		else if(game.upgrades.isPistol) return new Rectangle((int)x-2,(int)y,20,20);
		else if(game.upgrades.isDualPistol) return new Rectangle((int)x-2,(int)y,14,14);
		return new Rectangle((int)x,(int)y,5,8);
	}
	/* This method is called 60 times per second and 
	 * it makes the shotgunBullet go up and it also adds
	 * the another bullet object and removes the previous on when 
	 * it is passed the y value(<450) of
	 * the game screen */
	public void update() 
	{
		y+=yVel;
		checkCollision();
	}
	/* This method checks Collision depending on the 
	 * game Object which decreases the health
	 * of the boss */
	public void checkCollision()
	{
		for(int i = 0; i<handler.object.size();i++)
		{
			GameObject obj = handler.object.get(i);
			if(obj.id == ID.Level4BossInvisibleTrail)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(this);
					if(Level4Boss.isInvisible)
						Level4Boss.isInvisible = false;
				}
			}
			if(obj.id == ID.Level4Boss)
			{
				if(getRect().intersects(obj.getRect()))
				{
					HUD.LEVEL4BOSSHEALTH-=7;
					handler.removeObject(this);
					if(HUD.LEVEL4BOSSHEALTH<=0)
					{
						handler.removeObject(obj);
					}
				}
			}
		}
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws the shotgunBullet into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		if(game.upgrades.isSniper) g.drawImage(texture.shotgunBullet,(int)x-2,(int)y,15,15,null);
		else if(game.upgrades.isPistol) g.drawImage(texture.shotgunBullet,(int)x-2,(int)y,20,20,null);
		else if(game.upgrades.isDualPistol) g.drawImage(texture.shotgunBullet,(int)x-2,(int)y,14,14,null);
		else g.drawImage(texture.shotgunBullet,(int)x-2,(int)y,5,8,null);
	}
	/* These two methods are not needed(yet) but since 
	 * this class extends the abstract class GameObject we 
	 * need to have these methods */
	public double getX() 
	{
		return 0;
	}
	public double getY() 
	{
		return 0;
	}
}
