package game.eruption;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
/*This class is for creating the FireBall when the handler 
adds this object into the game and it is also part of the GameObject abstract class */
public class Fireball extends GameObject
{
	private double x,y;
	private SpriteTextures texture;
	private ObjectHandler handler;
	private ID id;
	private Random r = new Random();
	private int yVel;
	private Color trailColor = new Color(240,50,20);
	/* /* The constructor initializes the variables for this object depending on 
	 * what was passed in and also this has yVel parameter which will be used
	 * to have different speeds for the Fireball */
	public Fireball(double x,double y,SpriteTextures texture,ObjectHandler handler,ID id,int yVel)
	{
		super(x,y,id);
		this.x = x;
		this.y = y;
		this.handler = handler;
		this.texture = texture;
		this.yVel = yVel;
	}
	/* This creates a rectangle around the fireball 
	 * which is used to check collision with the player */
	public Rectangle getRect()
	{
		return new Rectangle((int)x,(int)y,50,50);
	}
	/* This method is called 60 times per second and 
	 * it makes the fireball fall down(at different speeds depending on yVel) 
	 * and it also adds a trail using the Trail class and it removes 
	 * the object when it is passed the y value of
	 * the game screen */
	public void update()
	{
		y+=yVel;
		if(y>800)
			handler.removeObject(this);
		handler.addObject(new Trail((int)x+9,(int)y-30,ID.FireballTrail,trailColor,32,32,0.08f,handler));
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws the fireball into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		g.drawImage(texture.fireball,(int)x,(int)y,50,50,null);
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
