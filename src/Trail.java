import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject
{
	private ObjectHandler handler;
	private double x,y;
	private float flow = 1;
	private int width,height;
	private Color color;
	private float span;
	private ID id;
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
	public Rectangle getRect() {
		return new Rectangle((int)x,(int)y,width,height);
	}
	public void update()
	{
		if(flow>span)
		{
			flow-=span-0.001f;
		}
		else
			handler.removeObject(this);
	}
	public void render(Graphics g) 
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.setComposite(makeTransparent(flow));
		g.setColor(color);
		g.fillRect((int)x,(int)y,width,height);
		g2.setComposite(makeTransparent(1));
	}
	public AlphaComposite makeTransparent(float flow)
	{
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type,flow));
	}
	public double getX() 
	{
		return 0;
	}
	public double getY() 
	{
		return 0;
	}
}
