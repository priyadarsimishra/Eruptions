import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends GameObject
{
	public double x;
	public double y;
	private double xVel = 0;
	private double yVel = 0;
	private ID id;
	private BufferedImage player;
	private SpriteTextures texture;
	private ObjectHandler handler;
	private Game game;
	public Player(double x,double y,Game game,SpriteTextures texture,ObjectHandler handler,ID id)
	{
		super(x,y,id);
		this.x = x;
		this.y = y;
		this.texture = texture;
		this.handler = handler;
		this.game = game;
	}
	public Rectangle getRect()
	{
		return new Rectangle((int)x,(int)y,50,38);
	}
	public void update()
	{
		x+=xVel;
		x = Game.restrict(x, 0, Game.WIDTH-60);
		checkCollision();
	}
	public void render(Graphics g)
	{
		if(game.gameState == game.STATE.LEVEL1)
		{
			g.drawImage(texture.player,(int)x,(int)y,50,38,null);
		}
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
			if(obj.id == ID.Fireball || obj.id == ID.FireballTrail)
			{
				if(getRect().intersects(obj.getRect()))
				{
					HUD.HEALTH-=1;
				}
			}
			if(obj.id == ID.BronzeCoin)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(obj);
					HUD.SCORE+=100;
				}
			}
			if(obj.id == ID.SilverCoin)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(obj);
					HUD.SCORE+=250;
				}
			}
			if(obj.id == ID.GoldCoin)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(obj);
					HUD.SCORE+=500;
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
		return x;
	}
	public double getY() 
	{
		return y;
	}
}
