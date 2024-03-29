import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends GameObject
{
	private double x;
	private double y;
	private double xVel = 0;
	private double yVel = 0;
	private ID id;
	private BufferedImage player;
	private SpriteTextures texture;
	private ObjectHandler handler;
	public Player(double x,double y,Game game,SpriteTextures texture,ObjectHandler handler,ID id)
	{
		super(x,y,id);
		this.x = x;
		this.y = y;
		this.texture = texture;
		this.handler = handler;
	}
	public Rectangle getRect()
	{
		return new Rectangle((int)x,(int)y,60,42);
	}
	public void update()
	{
		x+=xVel;
		x = Game.restrict(x, 0, Game.WIDTH-60);
		checkCollision();
	}
	public void render(Graphics g)
	{
		g.drawImage(texture.player,(int)x,(int)y,60,42,null);
	}
	public void setXVel(double xVel)
	{
		this.xVel = xVel;
	}
	public void checkCollision()
	{
		for(int i=0;i<handler.object.size();i++)
		{
			GameObject obj = handler.object.get(i);
			if(obj.id == ID.Fireball)
			{
				if(getRect().intersects(obj.getRect()))
				{
					HUD.HEALTH-=2;
				}
			}
		}
	}
	public double getXVel()
	{
		return xVel;
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
