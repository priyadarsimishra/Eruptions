package game.eruption;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
/* This is the class for the player bullet
 * to you can kill the boss in Level 3 */
public class ExplosiveBullet extends GameObject
{
	private double x;
	private double y;
	private double yVel;
	private ID id;
	private ObjectHandler handler;
	private SpriteTextures texture;
	public boolean isExplode;
	private Image explosion;
	private ImageIcon icon;
	private int imageTimeExplosion = 0;
	private Game game;
	/* The constructor initializes the variables for this object depending on 
	 * what was passed in */
	public ExplosiveBullet(double x, double y, ID id,ObjectHandler handler,SpriteTextures texture, double yVel, Game game) 
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
		if(game.upgrades.isShotgun) return new Rectangle((int)x,(int)y,5,8);
		else if(game.upgrades.isSniper) return new Rectangle((int)x,(int)y,15,15);
		else return new Rectangle((int)x+4,(int)y,12,20);
	}
	/* This method is called 60 times per second and 
	 * it makes the bullet go up, then it is removed if
	 * it is passed the y value(<0) of
	 * the game screen */
	public void update() 
	{
		y+=yVel;
		if(y<=-36)
			handler.removeObject(this);
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws the bullet into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		if(game.upgrades.isShotgun) g.drawImage(texture.explosiveBullet,(int)x,(int)y,5,8,null);
		else if(game.upgrades.isSniper) g.drawImage(texture.explosiveBullet,(int)x,(int)y,15,15,null);
		else g.drawImage(texture.explosiveBullet,(int)x,(int)y,20,20,null);
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
