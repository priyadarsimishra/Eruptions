import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject 
{
	protected double x,y;
	protected ID id;
	public GameObject(double x,double y, ID id)
	{
		this.x = x;
		this.y = y;
		this.id = id;
	}
	public abstract void update();
	public abstract void render(Graphics g);
	public abstract Rectangle getRect();
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}

}
