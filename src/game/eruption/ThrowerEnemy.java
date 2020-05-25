package game.eruption;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;
/* This is class is for the Thrower enemy which is introduced
 * in LEVEL 2 */
public class ThrowerEnemy extends GameObject
{
	public static double x;
	public static double y;
	private int xVel;
	private int yVel = 5;
	private int width;
	private int height;
	public static double giveX;
	public static double giveY;
	private Random r = new Random();
	private int ytimer = r.nextInt(50)+30;
	private int xTimer = 40;
	private ID id;
	public static boolean split = false;
	public static boolean giveInfo = false;
	private ObjectHandler handler;
	private SpriteTextures texture;
	public int fireRate = 350;
	private Image explosion;
	private ImageIcon icon;
	private int imageExplosionTime = 0;
	private boolean isExplode = false;
	private Game game;
	/* This is the constructor for the Thrower Enemy
	 * and it requires the same parameter as other game objects */
	public ThrowerEnemy(double x, double y, ID id,ObjectHandler handler, SpriteTextures texture,int width, int height, Game game)
	{
		super(x,y,id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
		this.width = width;
		this.height = height;
		this.game = game;
	}
	/* This creates a rectangle around the ThrowerEnemy enemy 
	 * which is used to check collision with the bullet */
	public Rectangle getRect() 
	{
		return new Rectangle((int)x,(int)y,width,height);
	}
	/* This method is called 60 times per second and 
	 * it makes the enemy move left or right the player 
	 * has to shoot it and then it splits in half */
	public void update() 
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
			if(xVel == 0) xVel = 5;
		}
		if(x<=0 || x>=(Game.WIDTH-width)) xVel *= -1;
		checkCollision();
		if(HUD.THROWERHEALTH <= 25)
		{
			split  = true;
			giveX = x;
			giveY = y;
			giveInfo = split;
			HUD.THROWERHEALTH = 0;
			handler.removeObject(this);
		}
		else giveInfo = false;
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws Thrower enemy into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		icon = new ImageIcon(getClass().getResource("/Bomb.gif"));
		explosion = icon.getImage();
		if(xVel <= 0)
			g.drawImage(texture.throwerEnemyLeft,(int)x,(int)y,width,height,null);
		else 
			g.drawImage(texture.throwerEnemyRight,(int)x,(int)y,width,height,null);
		if(isExplode)
		{
			if(imageExplosionTime >= 200)
			{
				isExplode = false;
				imageExplosionTime = 0;
			}
			else
			{
				g.drawImage(explosion,(int)x-20,(int)y-20,80,80,null);
				imageExplosionTime++;
			}
		}
		if(fireRate<=0)
		{
			handler.addObject(new GoldenRod(this.x+10,this.y+14,r.nextInt(8)+5,ID.GoldenRod,handler,texture));
			fireRate = 350;
		}
		else fireRate--;
	}
	/* This method checks for collision between bullet
	 * and other game Objects such as enemies */
	public void checkCollision()
	{
		for(int i = 0;i<handler.object.size();i++)
		{
			GameObject obj = handler.object.get(i);
			if(obj.id == ID.Bullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					if(game.upgrades.isSniper) HUD.THROWERHEALTH-=25;
					else if(game.upgrades.isDualPistol) HUD.THROWERHEALTH-=5;
					else if(game.upgrades.isShotgun) HUD.THROWERHEALTH-=2;
					else HUD.THROWERHEALTH-=5;
					handler.removeObject(obj);
				}
			}
			if(obj.id == ID.ExplosiveBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					isExplode = true;
					if(game.upgrades.isSniper) HUD.THROWERHEALTH-=25;
					else if(game.upgrades.isDualPistol) HUD.THROWERHEALTH-=5;
					else if(game.upgrades.isShotgun) HUD.THROWERHEALTH-=6;
					else HUD.THROWERHEALTH-=3;
					handler.removeObject(obj);
				}
			}
			if(obj.id == ID.ShotgunBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					if(game.upgrades.isSniper) HUD.THROWERHEALTH-=15;
					else if(game.upgrades.isDualPistol) HUD.THROWERHEALTH-=5;
					else if(game.upgrades.isShotgun) HUD.THROWERHEALTH-=2;
					else HUD.THROWERHEALTH-=3;
					handler.removeObject(obj);
				}
			}
			if(obj.id == ID.DoubleBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					if(game.upgrades.isSniper) HUD.THROWERHEALTH-=20;
					else if(game.upgrades.isDualPistol) HUD.THROWERHEALTH-=5;
					else if(game.upgrades.isShotgun) HUD.THROWERHEALTH-=3;
					else HUD.THROWERHEALTH-=3;
					handler.removeObject(obj);
				}
			}
		}
	}
}
