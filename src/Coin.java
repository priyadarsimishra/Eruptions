import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Coin extends GameObject
{
	private double x,y;
	private SpriteTextures texture;
	private ObjectHandler handler;
	private Random r = new Random();
	private int timer = 60;
	private double yVel = r.nextInt(10)+3;
	private ID id;
	public Coin(double x,double y,SpriteTextures texture,ObjectHandler handler,ID id)
	{
		super(x,y,id);	
		this.x = x;
		this.y = y;
		this.texture = texture;
		this.handler = handler;
	}
	public Rectangle getRect()
	{
		return new Rectangle((int)x,(int)y,32,32);
	}
	public void update() 
	{
		y+=yVel;
		if(y>800)
			handler.removeObject(this);	
	}
	public void render(Graphics g) 
	{
		g.drawImage(texture.coin,(int)x,(int)y,null);
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