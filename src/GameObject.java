import java.awt.Graphics;
import java.awt.Rectangle;
/* This is the abstract class which every 
 * GameObject in the game extends too */
public abstract class GameObject 
{
	protected double x,y;
	protected ID id;
	/* This constructor initializes x,y values and ID 
	 * which is an enum what helps with collision */
	public GameObject(double x,double y, ID id)
	{
		this.x = x;
		this.y = y;
		this.id = id;
	}
	// This is the update method which updates the game 60 times per second
	public abstract void update();
	// This is the render method which updates the drawings in the game 60 times per second
	public abstract void render(Graphics g);
	// This is the getRect method which helps create a rectangle around a object for collision
	public abstract Rectangle getRect();
	// This method is to return the x location of the object
	public double getX()
	{
		return x;
	}
	// This method is to return the y location of the object
	public double getY()
	{
		return y;
	}

}
