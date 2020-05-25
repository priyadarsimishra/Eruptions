package game.eruption;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;
/* This is class is for the SplitEnemy1 which is introduced
 * in LEVEL 2 */
public class SplitEnemy2 extends GameObject
{
	public static double x;
	public static double y;
	private int xVel = -5;
	private int yVel;
	private int width;
	private int height;
	private Random r = new Random();
	private int ytimer = r.nextInt(50)+20;
	private int xTimer = 40;
	private ID id;
	public static boolean show = false;
	private ObjectHandler handler;
	private SpriteTextures texture;
	private int fireRate = 300;
	private Image explosion;
	private ImageIcon icon;
	private int imageExplosionTime = 0;
	private boolean isExplode = false;
	private Game game;
	/* This is the constructor for the SplitEnemy1
	 * and it requires the same parameter as other game objects */
	public SplitEnemy2(double x, double y, ID id,ObjectHandler handler, SpriteTextures texture,int width, int height, boolean show, Game game)
	{
		super(x,y,id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
		this.width = width;
		this.height = height;
		this.show = show;
		this.game = game;
	}
	/* This creates a rectangle around the SplitEnemy1 enemy 
	 * which is used to check collision with the bullet */
	public Rectangle getRect() 
	{
		return new Rectangle((int)x,(int)y,width,height);
	}
	/* This method is called 60 times per second and 
	 * it makes the SplitEnemy1 move left or right the player 
	 * has to shoot it and then it splits in half */
	public void update() 
	{	
		x+=xVel;
		if(x<=0 || x>=(Game.WIDTH-width)) xVel *= -1;
		checkCollision();
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws SplitEnemy1 into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		icon = new ImageIcon(getClass().getResource("/Bomb.gif"));
		explosion = icon.getImage();
		if(isExplode)
		{
			if(imageExplosionTime >= 200)
			{
				isExplode = false;
				imageExplosionTime = 0;
			}
			else
			{
				g.drawImage(explosion,(int)x-50,(int)y-50,80,80,null);
				imageExplosionTime++;
			}
		}
		if(xVel <= 0)
			g.drawImage(texture.throwerEnemyLeft,(int)x-15,(int)y-10,width,height,null);
		else 
			g.drawImage(texture.throwerEnemyRight,(int)x-15,(int)y-10,width,height,null);		
		if(fireRate<=0)
		{
			handler.addObject(new GoldenRod(this.x+10,this.y+14,r.nextInt(8)+5,ID.GoldenRod,handler,texture));
			fireRate = 250;
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
					HUD.SPLITHEALTH2-=5;
					handler.removeObject(obj);
					if(HUD.SPLITHEALTH2 <= 0)
					{
						handler.removeObject(this);
						ThrowerEnemy.giveInfo = false;
						if(game.upgrades.isScoreBoost) HUD.SCORE+=400;
						else HUD.SCORE+=200;
					}
				}
			}
			if(obj.id == ID.DoubleBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					HUD.SPLITHEALTH2-=3;
					handler.removeObject(obj);
					if(HUD.SPLITHEALTH2 <= 0)
					{
						handler.removeObject(this);
						ThrowerEnemy.giveInfo = false;
						if(game.upgrades.isScoreBoost) HUD.SCORE+=400;
						else HUD.SCORE+=200;
					}
				}
			}
			if(obj.id == ID.ExplosiveBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					isExplode = true;
					HUD.SPLITHEALTH2-=4;
					handler.removeObject(obj);
					if(HUD.SPLITHEALTH2 <= 0)
					{
						handler.removeObject(this);
						ThrowerEnemy.giveInfo = false;
						if(game.upgrades.isScoreBoost) HUD.SCORE+=400;
						else HUD.SCORE+=200;
					}
				}
			}
			if(obj.id == ID.ShotgunBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					HUD.SPLITHEALTH2-=4;
					handler.removeObject(obj);
					if(HUD.SPLITHEALTH2 <= 0)
					{
						handler.removeObject(this);
						ThrowerEnemy.giveInfo = false;
						if(game.upgrades.isScoreBoost) HUD.SCORE+=400;
						else HUD.SCORE+=200;
					}
				}
			}
		}
	}
}
