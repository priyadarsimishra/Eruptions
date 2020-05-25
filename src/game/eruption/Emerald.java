package game.eruption;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/* This is class is for the Emerald in LEVEL 2 */
public class Emerald extends GameObject
{
	private double x;
	private double y;
	private int yVel;
	private ID id;
	private ObjectHandler handler;
	private SpriteTextures texture;
	/* The constructor initializes the variables for this object depending on 
	 * what was passed in */
	public Emerald(double x, double y, ID id,ObjectHandler handler,SpriteTextures texture, int yVel) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
		this.yVel = yVel;
	}
	/* This creates a rectangle around the emerald 
	 * which is used to check collision with the player */
	public Rectangle getRect() 
	{
		return new Rectangle((int)x+5,(int)y,20,32);
	}
	/* This method is called 60 times per second and 
	 * it makes the emerald fall down, y value of the game screen */
	public void update() 
	{
		y+=yVel;
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws the emeralds into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		g.drawImage(texture.emerald,(int)x,(int)y,null);
	}
	
}
