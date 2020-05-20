import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
/* This is the class for the Shield for the shield enemy */
public class Shield extends GameObject
{
	private double x,y;
	private ID id;
	private ObjectHandler handler;
	private SpriteTextures texture;
	private int hit = 0;
	/* This constructor has similar parameters to other game objects
	 * and this is required to make the player work */
	public Shield(double x, double y, ID id,ObjectHandler handler,SpriteTextures texture) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
	}
	/* This method creates a rectangle around the shield
	 * so we can deal with collision */
	public Rectangle getRect() 
	{
		return new Rectangle((int)ShieldEnemy.x-10,(int)ShieldEnemy.y+45,30,15);
	}
	/* This method is called 60 times per second
	 * and it just checks for collision */
	public void update() 
	{		
		checkCollision();
	}
	/* This method is called 60 times per second
	 * Depending on the state it draws the shield */
	public void render(Graphics g) 
	{
		g.drawImage(texture.shield,(int)ShieldEnemy.x-5,(int)ShieldEnemy.y+45,50,15,null);
	}
	/* This method checks Collision depending on the 
	 * game Object which reduces the health of the shield */
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
						hit = 0;
						
					}
				}
			}
			if(obj.id == ID.Bullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					hit++;
					handler.removeObject(obj);
					if(hit == 6)
					{
						ShieldEnemy.startDamage = true;
						handler.removeObject(this);
						hit = 0;
					}
				}
			}
			if(obj.id == ID.DoubleBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					hit++;
					handler.removeObject(obj);
					if(hit == 8)
					{
						ShieldEnemy.startDamage = true;
						handler.removeObject(this);
						hit = 0;
					}
				}
			}
			if(obj.id == ID.ShotgunBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					hit++;
					handler.removeObject(obj);
					if(hit == 5)
					{
						ShieldEnemy.startDamage = true;
						handler.removeObject(this);
						hit = 0;
					}
				}
			}
		}
	}
}
