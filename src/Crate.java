import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
/* This class is for creating the crate when the handler 
adds this object into the game as it is part of the GameObject abstract class */
public class Crate extends GameObject
{
	private double x,y;
	private SpriteTextures texture;
	private ObjectHandler handler;
	private ID id;
	/* The constructor initializes the variables for this object depending on 
	 * what was passed in */
	public Crate(double x,double y,SpriteTextures texture,ObjectHandler handler,ID id)
	{
		super(x,y,id);	
		this.x = x;
		this.y = y;
		this.texture = texture;
		this.handler = handler;
	}
	/* This creates a rectangle around the crate
	 * which is used to check collision with the player */
	public Rectangle getRect()
	{
		return new Rectangle((int)x,(int)y,36,36);
	}
	/* This method is called 60 times per second and 
	 * it makes the crate */
	public void update() {}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws the crate into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		g.drawImage(texture.crate,(int)x,(int)y,36,36,null);
	}
	/* These two methods are not needed(yet) but since 
	 * this class extends the abstract class GameObject 
	 * we need to have these methods */
	public double getX() 
	{
		return 0;
	}
	public double getY() 
	{
		return 0;
	}
}