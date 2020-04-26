import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
/* This class adds the cool trail effect on the back of some Game Objects */
public class Trail extends GameObject
{
	private ObjectHandler handler;
	private double x,y;
	private float flow = 1;
	private int width,height;
	private Color color;
	private float span;
	private ID id;
	/* The constructor has many parameters to set the location, span, and color as well */
	public Trail(double x,double y,ID id,Color color,int width,int height,float span,ObjectHandler handler)
	{
		super(x,y,id);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.span = span;
		this.handler = handler;
	}
	/* This method returns a rectangle which is used for collision */
	public Rectangle getRect() 
	{
		return new Rectangle((int)x,(int)y,width,height);
	}
	/* This method is called 60 times per second
	 * and it gives the size of the trail attached to the object */
	public void update()
	{
		if(flow>span)
		{
			flow-=span-0.001f;
		}
		else
			handler.removeObject(this);
	}
	/* This draws the trial using graphics 2d and normal graphics */ 
	public void render(Graphics g) 
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.setComposite(makeTransparent(flow));
		g.setColor(color);
		g.fillRect((int)x,(int)y,width,height);
		g2.setComposite(makeTransparent(1));
	}
	/* This method gives the fading effect as 
	 * you see the end of the trail using a flow integer 
	 * to see the area of the fade effect */
	public AlphaComposite makeTransparent(float flow)
	{
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type,flow));
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
