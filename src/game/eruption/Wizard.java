package game.eruption;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

/* This is the class for our Wizard Enemy */
public class Wizard extends GameObject
{
	public static double x;
	public static double y;
	private ID id;
	private ObjectHandler handler;
	private SpriteTextures texture;
	private Random r = new Random();
	private double xVel;
	private double yVel;
	private int fireRate = 500;
	private Image explosion;
	private ImageIcon icon;
	private int imageExplosionTime = 0;
	private boolean isExplode = false;
	private Game game;
	/* This is the constructor for the Wizard 
	 * and it requires the same parameter as other game objects */
	public Wizard(double x, double y, ID id,double xVel,double yVel,ObjectHandler handler,SpriteTextures texture,Game game) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
		this.xVel = xVel;
		this.yVel = yVel;
		this.game = game;
	}
	/* This creates a rectangle around the wizard enemy 
	 * which is used to check collision with the bullet and player */
	public Rectangle getRect() 
	{
		return new Rectangle((int)x,(int)y,42,42);
	}
	/* This method is called 60 times per second and 
	 * it makes the wizard move diagonally */
	public void update()
	{
//		System.out.println(xVel);
//		System.out.println(yVel);
		x+=xVel;
		y+=yVel;
		checkCollision();
		if(x<=0 || x>=770) xVel*=-1;
		if(y>=800)
		{
			handler.removeObject(this);
			Spawn.wizardspawn = true;
		}
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws Wizard into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		icon = new ImageIcon(getClass().getResource("/Bomb.gif"));
		explosion = icon.getImage();
		if(xVel>0)
			g.drawImage(texture.rightWizard,(int)x,(int)y,42,42,null);
		else
			g.drawImage(texture.leftWizard,(int)x,(int)y,42,42,null);
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
			handler.addObject(new FreezePotion(this.x+8,this.y+14,r.nextInt(6)+3,ID.FreezePotion,handler,texture));
			fireRate = 500;
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
					handler.removeObject(obj);
					if(game.upgrades.isSniper) HUD.WIZARDHEALTH-=25;
					else if(game.upgrades.isDualPistol) HUD.WIZARDHEALTH-=3;
					else if(game.upgrades.isShotgun) HUD.WIZARDHEALTH-=4;
					else HUD.WIZARDHEALTH-=10;
					if(HUD.WIZARDHEALTH<=0)
					{
						handler.removeObject(this);
						if(game.upgrades.isScoreBoost) HUD.SCORE+=1200;
						else HUD.SCORE+=600;
					}
				}
			}
			if(obj.id == ID.DoubleBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(obj);
					if(game.upgrades.isSniper) HUD.WIZARDHEALTH-=30;
					else if(game.upgrades.isDualPistol) HUD.WIZARDHEALTH-=3;
					else if(game.upgrades.isShotgun) HUD.WIZARDHEALTH-=4;
					else HUD.WIZARDHEALTH-=10;
					if(HUD.WIZARDHEALTH<=0)
					{
						handler.removeObject(this);
						if(game.upgrades.isScoreBoost) HUD.SCORE+=1200;
						else HUD.SCORE+=600;
					}
				}
			}
			if(obj.id == ID.ShotgunBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(obj);
					if(game.upgrades.isSniper) HUD.WIZARDHEALTH-=20;
					else if(game.upgrades.isDualPistol) HUD.WIZARDHEALTH-=3;
					else if(game.upgrades.isShotgun) HUD.WIZARDHEALTH-=4;
					else HUD.WIZARDHEALTH-=10;
					if(HUD.WIZARDHEALTH<=0)
					{
						handler.removeObject(this);
						if(game.upgrades.isScoreBoost) HUD.SCORE+=1200;
						else HUD.SCORE+=600;
					}
				}
			}
			if(obj.id == ID.ExplosiveBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					isExplode = true;
					handler.removeObject(obj);
					if(game.upgrades.isSniper) HUD.WIZARDHEALTH-=25;
					else if(game.upgrades.isDualPistol) HUD.WIZARDHEALTH-=3;
					else if(game.upgrades.isShotgun) HUD.WIZARDHEALTH-=4;
					else HUD.WIZARDHEALTH-=10;
					if(HUD.WIZARDHEALTH<=0)
					{
						handler.removeObject(this);
						if(game.upgrades.isScoreBoost) HUD.SCORE+=1200;
						else HUD.SCORE+=600;
					}
				}
			}
		}
	}
}
