import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Fireball extends GameObject
{
	private double x,y;
	private SpriteTextures texture;
	private ObjectHandler handler;
	private ID id;
	private Random r = new Random();
	private int yVel;
	public Fireball(double x,double y,SpriteTextures texture,ObjectHandler handler,ID id,int yVel)
	{
		super(x,y,id);
		this.x = x;
		this.y = y;
		this.handler = handler;
		this.texture = texture;
		this.yVel = yVel;
	}
	public Rectangle getRect()
	{
		return new Rectangle((int)x,(int)y,50,50);
	}
	public void update()
	{
		y+=yVel;
		if(y>800)
			handler.removeObject(this);
		handler.addObject(new Trail((int)x+5,(int)y-30,ID.Trail,Color.YELLOW,40,40,0.08f,handler));
	}
	public void render(Graphics g) 
	{
		g.drawImage(texture.fireball,(int)x,(int)y,50,50,null);
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
