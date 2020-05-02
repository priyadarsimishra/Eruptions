import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
/*This class is for creating the MagmaRock when the handler 
adds this object into the game and it is also part of the GameObject abstract class */
public class MagmaRock extends GameObject
{
	private double x,y;
	private SpriteTextures texture;
	private ObjectHandler handler;
	private ID id;
	private Random r = new Random();
	private int yVel;
	private int xVel;
	private Color trailColor = new Color(160,82,45);
	private boolean side; //1-true,0-false
	/* /* The constructor initializes the variables for this object depending on 
	 * what was passed in and also this has yVel parameter which will be used
	 * to have different speeds for the MagmaRock */
	public MagmaRock(double x,double y,SpriteTextures texture,ObjectHandler handler,ID id,int yVel, int xVel,boolean side)
	{
		super(x,y,id);
		this.x = x;
		this.y = y;
		this.handler = handler;
		this.texture = texture;
		this.yVel = yVel;
		this.xVel = xVel;
		this.side = side;
	}
	/* This creates a rectangle around the MagmaRock 
	 * which is used to check collision with the player */
	public Rectangle getRect()
	{
		return new Rectangle((int)x,(int)y,70,70);
	}
	/* This method is called 60 times per second and 
	 * it makes the MagmaRock fall down(at different speeds depending on yVel) 
	 * and it also adds a trail using the Trail class and it removes 
	 * the object when it is passed the y value of
	 * the game screen */
	public void update()
	{
		x+=xVel;
		y+=yVel;
		if(y>800)
			handler.removeObject(this);
		//if(x<=0 || x>= 750) xVel*=-1;
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws the MagmaRock into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		if(side)
			g.drawImage(texture.rightMagmaRock,(int)x,(int)y,70,70,null);
		else 
			g.drawImage(texture.leftMagmaRock,(int)x,(int)y,70,70,null);
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
