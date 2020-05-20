import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
/* This is the class for the player bullet
 * to you can kill enemies starting from level 2 */
public class Bullet extends GameObject
{
	private double x;
	private double y;
	private double yVel;
	private ID id;
	private ObjectHandler handler;
	private SpriteTextures texture;
	private Game game;
	int reload = 200;
	/* The constructor initializes the variables for this object depending on 
	 * what was passed in */
	public Bullet(double x, double y, ID id,ObjectHandler handler,SpriteTextures texture, double yVel,Game game) 
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
	/* This creates a rectangle around the bullet  
	 * which is used to check collision with enemies */
	public Rectangle getRect() 
	{	
		if(game.player.shotgunShoot) return new Rectangle((int)x-2,(int)y,5,8);
		else if(game.player.sniperShoot) return new Rectangle((int)x-2,(int)y,15,15);
		else return new Rectangle((int)x+4,(int)y,12,20);
	}
	/* This method is called 60 times per second and 
	 * it makes the bullet go up and it also adds
	 * a trail using the Trail class and it removes 
	 * the object when it is passed the y value(<0) of
	 * the game screen */
	public void update() 
	{
		y+=yVel;
		if(y<=-36)
		{
			handler.removeObject(this);
			reload = 200;
		}
	}
//	/* This method checks collision with enemies*/
//	public void checkCollision()
//	{
//		for(int i = 0;i<handler.object.size();i++)
//		{
//			GameObject obj = handler.object.get(i);
//			if(obj.id == ID.UnderGroundEnemy)
//			{
//				if(getRect().intersects(obj.getRect()))
//				{
//					HUD.UNDERGROUNDHEALTH-=5;
//					handler.removeObject(this);
//				}
//			}
//		}
//	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws the bullet into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		if(game.player.shotgunShoot) g.drawImage(texture.bullet,(int)x-2,(int)y,5,8,null);
		else if(game.player.sniperShoot) g.drawImage(texture.bullet,(int)x-2,(int)y,15,15,null);
		else g.drawImage(texture.bullet,(int)x,(int)y,20,20,null);
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
