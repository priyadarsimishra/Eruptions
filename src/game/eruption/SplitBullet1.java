package game.eruption;
import java.awt.Graphics;
import java.awt.Rectangle;

/* This is the class for the bullet that splits into two 
 * used to kill boss in Level 2 */
public class SplitBullet1 extends GameObject
{
	public double x;
	public double y;
	private ID id;
	private int yVel = -12;
	private ObjectHandler handler;
	private SpriteTextures texture;
	private Game game;
	/* The constructor initializes the variables for this object depending on 
	 * what was passed in */ 
	public SplitBullet1(double x, double y, ID id,ObjectHandler handler,SpriteTextures texture, Game game) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
		this.game = game;
	}
	/* This creates a rectangle around the Split Bullet  
	 * which is used to check collision with enemies */
	public Rectangle getRect() 
	{
		return new Rectangle((int)x,(int)y,20,20);
	}
	/* This method is called 60 times per second and 
	 * it makes the Split Bullet go up and is removed when
	 * it is passed the game screen */
	public void update() 
	{
		if(game.upgrades.isSniper) yVel = -35;
		else yVel = -12;
		y+=yVel;
		if(y<=-32)
			handler.removeObject(this);
		checkCollision();
		if(x<=0)
			x = 2;
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws the Split Bullet into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		//System.out.println(game.upgrades.isShotgun);
		if(game.upgrades.isShotgun) g.drawImage(texture.doubleBullet,(int)x,(int)y,14,14,null);
		else if(game.upgrades.isSniper) g.drawImage(texture.doubleBullet,(int)x,(int)y,15,15,null);
		else g.drawImage(texture.doubleBullet,(int)x,(int)y,20,20,null);
	}
	/* This method checks Collision depending on the 
	 * game Object which decreases the health
	 * of the boss */
	public void checkCollision()
	{
		for(int i =0;i<handler.object.size();i++)
		{
			GameObject obj = handler.object.get(i);
			if(obj.id == ID.Level2Boss)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(this);
					HUD.LEVEL2BOSSHEALTH-=15;
				}
			}
		}
	}
}
