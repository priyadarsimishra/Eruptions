import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
/* This class is for creating the Player when the handler 
adds this object into the game as it is part of the GameObject abstract class */
public class Player extends GameObject
{
	public static double x;
	public static double y;
	private double xVel = 0;
	private double yVel = 0;
	public static boolean changeSpeed = true;
	public static int changeBack = 20;
	private ID id;
 	private SpriteTextures texture;
	private ObjectHandler handler;
	private Game game;
	public int bucketCount = 0;
	private Image explosion;
	private Image freeze;
	private Image bomb;
	private ImageIcon icon1;
	private ImageIcon icon2;
	private ImageIcon icon3;
	private int imageTime = 0;
	public static boolean shoot = false;
	public static boolean isDisplay = false;
	/* This constructor has similar parameters to other game objects
	 * and this is required to make the player work */
	public Player(double x,double y,Game game,SpriteTextures texture,ObjectHandler handler,ID id)
	{
		super(x,y,id);
		this.x = x;
		this.y = y;
		this.texture = texture;
		this.handler = handler;
		this.game = game;
	}
	/* This method creates a rectangle around the player
	 * so we can deal with collision */
	public Rectangle getRect()
	{
		return new Rectangle((int)x,(int)y+18,37,63);
	}
	/* This method is called 60 times per second
	 * and it makes the player go left or right and
	 * it restricts it so it cannot go off screen 
	 * and the it also checks for collision */
	public void update()
	{
		x+=xVel;
		if(x<=0)
			x=0;
		if(x>=763)
			x=763;
		checkCollision();
	}
	/* This method is called 60 times per second
	 * Depending on the state it draws the player */
	public void render(Graphics g)
	{
		icon1 = new ImageIcon(getClass().getResource("Explosion.gif"));
		explosion = icon1.getImage();
		icon2 = new ImageIcon(getClass().getResource("Bomb.gif"));
		bomb = icon2.getImage();
		icon3 = new ImageIcon(getClass().getResource("Freeze.gif"));
		freeze = icon3.getImage();
		if(shoot) g.drawImage(texture.playerShooting,(int)x,(int)y,50,80,null);
		else g.drawImage(texture.player,(int)x,(int)y,50,80,null);
		if(changeSpeed) g.drawImage(freeze,(int)x-10,(int)y,60,60,null);
		for(int i=0;i<handler.object.size();i++)
		{
			GameObject obj = handler.object.get(i);
			
			if(obj.id == ID.Fireball || obj.id == ID.FireballTrail)
			{
				if(getRect().intersects(obj.getRect()))
				{
					if(imageTime<=360)
						g.drawImage(explosion,(int)x-50,(int)y-160,null);
					else imageTime++;
				}
			}
			if(obj.id == ID.Level1BossBomb)
			{
				if(getRect().intersects(obj.getRect()))
				{
					if(imageTime<=360)
						g.drawImage(bomb,(int)x-50,(int)y-160,null);
					else imageTime++;
				}
			}
		}
	}
	/* This method checks Collision depending on the 
	 * game Object which either adds to the score or 
	 * changes the health */
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
			if(obj.id == ID.Level1BossBomb)
			{
				if(getRect().intersects(obj.getRect()))
				{
					HUD.HEALTH-=15;
					handler.removeObject(obj);
				}
			}
			if(obj.id == ID.MagmaRock)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(obj);
					HUD.HEALTH-=10;
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
			if(obj.id == ID.Waterbucket)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(obj);
					bucketCount++;
				}
			}
			if(obj.id == ID.UnderGroundEnemy)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(obj);
					HUD.HEALTH-=25;
					Spawn.undergroundenemyShow = true;
				}
			}
			if(obj.id == ID.EnemyBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(obj);
					HUD.HEALTH-=8;
				}
			}
			if(obj.id == ID.DiamondGem)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(obj);
					HUD.SCORE+=750;
				}
			}
			if(obj.id == ID.RubyGem)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(obj);
					HUD.SCORE+=500;
				}
			}
			if(obj.id == ID.Wizard)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(obj);
					HUD.HEALTH-=30;
					Spawn.wizardspawn = true;
				}
			}
			if(obj.id == ID.FreezePotion)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(obj);
					HUD.HEALTH-=8;
					changeSpeed = true;
					changeBack = 20;
					isDisplay = true;
				}
			}
			if(obj.id == ID.GoldenRod)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(obj);
					HUD.HEALTH-=5;
				}
			}
		}
	}
	/* This method sets XVel so this will be 
	 * used to change the speed */
	public void setXVel(double xVel)
	{
		this.xVel = xVel;
	}
	/* This method gets XVel so this return
	 * the speed of the player */
	public double getXVel()
	{
		return xVel;
	}
	/* These two method return x and y locations 
	 * of the players but it is not used right now */
	public double getX() 
	{
		return x;
	}
	public double getY() 
	{
		return y;
	}
}
