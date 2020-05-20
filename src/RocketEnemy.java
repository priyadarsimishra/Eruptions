import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
/* This is class is for the RocketEnemy which is introduced
 * in LEVEL 3 */
public class RocketEnemy extends GameObject
{
	public static double x;
	public static double y;
	private ID id;
	private ObjectHandler handler;
	private SpriteTextures texture;
	private int yTimer = 50;
	private int yVel = 6;
	public static boolean bulletShow = true;
	private int fireRate = 120;
	private Image explosion;
	private ImageIcon icon;
	private boolean isExplode;
	private int imageExplosionTime = 0;
	private Game game;
	/* This is the constructor for the RocketEnemy
	 * and it requires the same parameter as other game objects */
	public RocketEnemy(double x, double y, ID id,ObjectHandler handler,SpriteTextures texture, Game game) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
		this.game = game;
	}
	/* This creates a rectangle around the RocketEnemy
	 * which is used to check collision with the objects */
	public Rectangle getRect() 
	{
		return new Rectangle((int)x,(int)y,48,48);
	}
	/* This method is called 60 times per second and 
	 * it makes the RocketEnemy shoot at the player */
	public void update() 
	{
		y+=yVel;
		if(yTimer<=0) yVel = 0;
		else yTimer--;
		if(yTimer<=0)
		{
			if(bulletShow)
			{
				handler.addObject(new Rocket(RocketEnemy.x,RocketEnemy.y,ID.Rocket,handler,texture,-3.5));
				bulletShow = false;
			}
			if(Rocket.destroyed)
			{
				if(fireRate<=0)
				{
					handler.addObject(new Rocket(RocketEnemy.x,RocketEnemy.y,ID.Rocket,handler,texture,-3.5));
					Rocket.destroyed = false;
					fireRate = 120;
				}
				else fireRate--;
			}
		}
		checkCollision();
	}
	public void checkCollision()
	{
		for(int i = 0;i<handler.object.size();i++)
		{
			GameObject obj = handler.object.get(i);
			if(obj.id == ID.DoubleBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					if(game.upgrades.isSniper) HUD.ROCKETHEALTH-=18;
					else if(game.upgrades.isDualPistol) HUD.ROCKETHEALTH-=5;
					else if(game.upgrades.isShotgun) HUD.ROCKETHEALTH-=1;
					else HUD.ROCKETHEALTH-=5;
					handler.removeObject(obj);
					if(HUD.ROCKETHEALTH<=0)
					{
						HUD.ROCKETHEALTH = 0;
						handler.removeObject(this);
						HUD.SCORE+=150;
					}
				}
			}
			if(obj.id == ID.Bullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					if(game.upgrades.isSniper) HUD.ROCKETHEALTH-=12;
					else if(game.upgrades.isDualPistol) HUD.ROCKETHEALTH-=4;
					else if(game.upgrades.isShotgun) HUD.ROCKETHEALTH-=1;
					else HUD.ROCKETHEALTH-=6;
					handler.removeObject(obj);
					if(HUD.ROCKETHEALTH<=0)
					{
						HUD.ROCKETHEALTH = 0;
						handler.removeObject(this);
						HUD.SCORE+=150;
					}
				}
			}
			if(obj.id == ID.ShotgunBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					if(game.upgrades.isSniper) HUD.ROCKETHEALTH-=10;
					else if(game.upgrades.isDualPistol) HUD.ROCKETHEALTH-=5;
					else if(game.upgrades.isShotgun) HUD.ROCKETHEALTH-=2;
					else HUD.ROCKETHEALTH-=2;
					handler.removeObject(obj);
					if(HUD.ROCKETHEALTH<=0)
					{
						HUD.ROCKETHEALTH = 0;
						handler.removeObject(this);
						HUD.SCORE+=150;
					}
				}
			}
			if(obj.id == ID.ExplosiveBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					isExplode = true;
					if(game.upgrades.isSniper) HUD.ROCKETHEALTH-=13;
					else if(game.upgrades.isDualPistol) HUD.ROCKETHEALTH-=7;
					else if(game.upgrades.isShotgun) HUD.ROCKETHEALTH-=1;
					else HUD.ROCKETHEALTH-=6;
					handler.removeObject(obj);
					if(HUD.ROCKETHEALTH<=0)
					{
						HUD.ROCKETHEALTH = 0;
						handler.removeObject(this);
						HUD.SCORE+=150;
					}
				}
			}
		}
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws RocketEnemy into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		g.drawImage(texture.rocketEnemy,(int)x,(int)y,48,48,null);
		icon = new ImageIcon(getClass().getResource("/Bomb.gif"));
		explosion = icon.getImage();
		if(isExplode)
		{
			if(imageExplosionTime >= 350)
			{
				isExplode = false;
				imageExplosionTime = 0;
			}
			else
			{
				g.drawImage(explosion,(int)x,(int)y,50,50,null);
				imageExplosionTime++;
			}
		}
	}
}
