import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
/* This is the class for the player bullet
 * to you can kill the boss starting in level 2 */
public class DoubleBullet extends GameObject
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
	public DoubleBullet(double x, double y, ID id,ObjectHandler handler,SpriteTextures texture, double yVel, Game game) 
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
		if(game.upgrades.isShotgun) return new Rectangle((int)x-2,(int)y,14,14);
		else if(game.upgrades.isDualPistol) return new Rectangle((int)x-2,(int)y,14,14);
		else if(game.upgrades.isSniper) return new Rectangle((int)x-2,(int)y,15,15);
		else return new Rectangle((int)x+4,(int)y,12,20);
	}
	/* This method is called 60 times per second and 
	 * it makes the DoubleBullet go up and it also adds
	 * the another bullet object and removes the previous on when 
	 * it is passed the y value(<450) of
	 * the game screen */
	public void update() 
	{
		y+=yVel;
		if(y<=450)
		{
			split = true;
			if(split)
			{
				handler.addObject(new SplitBullet1((int)x-15,450,ID.DoubleBullet,handler,texture, this.game));
				handler.addObject(new SplitBullet2((int)x+15,450,ID.DoubleBullet,handler,texture, this.game));
			}
			handler.removeObject(this);
		}
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
			if(obj.id == ID.Level2Boss)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(this);
					HUD.LEVEL2BOSSHEALTH-=20;
				}
			}
		}
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws the DoubleBullet into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		if(game.upgrades.isShotgun) g.drawImage(texture.doubleBullet,(int)x-2,(int)y,14,14,null);
		else if(game.upgrades.isDualPistol) g.drawImage(texture.doubleBullet,(int)x,(int)y,14,14,null);
		else if(game.upgrades.isSniper) g.drawImage(texture.doubleBullet,(int)x-2,(int)y,15,15,null);
		else g.drawImage(texture.doubleBullet,(int)x-2,(int)y,20,20,null);
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
