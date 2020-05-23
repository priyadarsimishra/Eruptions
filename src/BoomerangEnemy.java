import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

/* This is the class for the BoomerangEnemy is LEVEL 4 */
public class BoomerangEnemy extends GameObject
{
	public static double x,y;
	private ID id;
	private ObjectHandler handler;
	private SpriteTextures texture;
	private Random r = new Random();
	private int ytimer = r.nextInt(25)+15;
	private int xTimer = 40;
	private int xVel;
	private int yVel;
	public static boolean isThrow = false;
	public boolean stopSpawn = false;
	private int stopMove = 120;
	private boolean isExplode = false;
	private Image explosion;
	private ImageIcon icon;
	private int imageExplosionTime = 0;
	private Game game;
	/* This is the constructor for the BoomerangEnemy
	 * and it requires the same parameter as other game objects */
	public BoomerangEnemy(double x, double y, ID id,ObjectHandler handler,SpriteTextures texture,int yVel, Game game) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
		this.yVel = yVel;
		this.game = game;
	}
	/* This creates a rectangle around the BoomerangEnemy 
	 * which is used to check collision with the objects */
	public Rectangle getRect() 
	{
		return new Rectangle((int)x,(int)y,45,45);
	}
	/* This method is called 60 times per second and 
	 * it makes the BoomerangEnemy move left and right */
	public void update() 
	{
		if(isThrow)
		{
			xVel = 0;
			yVel = 0;
			if(stopSpawn)
				handler.addObject(new Boomerang(x+14,y,ID.Boomerang,handler,texture,r.nextInt(5)+12));
			stopSpawn = false;
			stopMove = 120;
		}
		else
		{
			x+=xVel;
			y+=yVel;
			if(ytimer<=0)
			{
				yVel = 0;
			}
			else ytimer--;
			if(ytimer<=0) xTimer--;
			if(xTimer<=0)
			{
				if(xVel == 0) xVel = r.nextInt(10)+5;
			}
			if(x<=0 || x>=(Game.WIDTH-45)) xVel *= -1;
		}
		if(stopMove <= 0)
		{
			isThrow = true;
			stopSpawn = true;
		}
		else stopMove--;
		checkCollision();
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws boomerang Enemy into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		icon = new ImageIcon(getClass().getResource("/Explosion.gif"));
		explosion = icon.getImage();
		g.setColor(Color.YELLOW);
		if(xVel <=0 )
			g.drawImage(texture.boomerangEnemyLeft,(int)x,(int)y,45,45,null);
		else
			g.drawImage(texture.boomerangEnemyRight,(int)x,(int)y,45,45,null);
		if(isExplode)
		{
			if(imageExplosionTime >= 200)
			{
				isExplode = false;
				imageExplosionTime = 0;
			}
			else
			{
				g.drawImage(explosion,(int)x,(int)y,40,40,null);
				imageExplosionTime++;
			}
		}
	}
	/* This method checks for collision between bullet
	 * and other game Objects */
	public void checkCollision()
	{
		for(int i = 0;i<handler.object.size();i++)
		{
			GameObject obj = handler.object.get(i);
			if(obj.id == ID.ExplosiveBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					if(game.upgrades.isSniper) HUD.BOOMERANGHEALTH-=15;
					else if(game.upgrades.isDualPistol) HUD.BOOMERANGHEALTH-=3;
					else if(game.upgrades.isShotgun) HUD.BOOMERANGHEALTH-=2;
					else HUD.BOOMERANGHEALTH-=5;
					isExplode = true;
					handler.removeObject(obj);
					if(HUD.BOOMERANGHEALTH<=0)
					{
						handler.removeObject(this);
						if(game.upgrades.isScoreBoost) HUD.SCORE+=400;
						else HUD.SCORE+=200;
					}
				}
			}
			if(obj.id == ID.Bullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					if(game.upgrades.isSniper) HUD.BOOMERANGHEALTH-=20;
					else if(game.upgrades.isDualPistol) HUD.BOOMERANGHEALTH-=5;
					else if(game.upgrades.isShotgun) HUD.BOOMERANGHEALTH-=2;
					else HUD.BOOMERANGHEALTH-=8;
					handler.removeObject(obj);
					if(HUD.BOOMERANGHEALTH<=0)
					{
						handler.removeObject(this);
						if(game.upgrades.isScoreBoost) HUD.SCORE+=400;
						else HUD.SCORE+=200;
					}
				}
			}
			if(obj.id == ID.ShotgunBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					if(game.upgrades.isSniper) HUD.BOOMERANGHEALTH-=12;
					else if(game.upgrades.isDualPistol) HUD.BOOMERANGHEALTH-=3;
					else if(game.upgrades.isShotgun) HUD.BOOMERANGHEALTH-=2;
					else HUD.BOOMERANGHEALTH-=2;
					handler.removeObject(obj);
					if(HUD.BOOMERANGHEALTH<=0)
					{
						handler.removeObject(this);
						if(game.upgrades.isScoreBoost) HUD.SCORE+=400;
						else HUD.SCORE+=200;
					}
				}
			}
			if(obj.id == ID.DoubleBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					if(game.upgrades.isSniper) HUD.BOOMERANGHEALTH-=12;
					else if(game.upgrades.isDualPistol) HUD.BOOMERANGHEALTH-=2;
					else if(game.upgrades.isShotgun) HUD.BOOMERANGHEALTH-=1;
					else HUD.BOOMERANGHEALTH-=3;
					if(HUD.BOOMERANGHEALTH<=0)
					{
						handler.removeObject(this);
						if(game.upgrades.isScoreBoost) HUD.SCORE+=400;
						else HUD.SCORE+=200;
					}
				}
			}
		}
	}
}
