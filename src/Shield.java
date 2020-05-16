import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Shield extends GameObject
{
	private double x,y;
	private ID id;
	private ObjectHandler handler;
	private SpriteTextures texture;
	private int hit = 0;
	public Shield(double x, double y, ID id,ObjectHandler handler,SpriteTextures texture) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
	}
	public Rectangle getRect() 
	{
		return new Rectangle((int)ShieldEnemy.x-10,(int)ShieldEnemy.y+45,30,15);
	}
	public void update() 
	{		
		checkCollision();
	}
	public void render(Graphics g) 
	{
		g.drawImage(texture.shield,(int)ShieldEnemy.x-5,(int)ShieldEnemy.y+45,50,15,null);
	}
	public void checkCollision()
	{
		for(int i = 0;i<handler.object.size();i++)
		{
			GameObject obj = handler.object.get(i);
			if(obj.id == ID.ExplosiveBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					hit++;
					handler.removeObject(obj);
					if(hit == 4)
					{
						ShieldEnemy.startDamage = true;
						handler.removeObject(this);
					}
				}
			}
		}
	}
}
