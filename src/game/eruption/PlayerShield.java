package game.eruption;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
/* This is the class for the Shield for the shield enemy */
public class PlayerShield extends GameObject
{
	private double x,y;
	private ID id;
	private ObjectHandler handler;
	private Game game;
	public int hit = 0;
	private ImageIcon shieldIcon;
	private Image shield;
	/* This constructor has similar parameters to other game objects
	 * and this is required to make the player work */
	public PlayerShield(double x, double y, ID id,ObjectHandler handler,Game game) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.game = game;
	}
	/* This method creates a rectangle around the shield
	 * so we can deal with collision */
	public Rectangle getRect() 
	{
		return new Rectangle((int)game.player.x-10,(int)game.player.y-30,60,30);
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
		shieldIcon = new ImageIcon(getClass().getResource("/PlayerShield.png"));
		shield = shieldIcon.getImage();
		g.drawImage(shield,(int)game.player.x-10,(int)game.player.y-30,60,30,null);
	}
	/* This method checks Collision depending on the 
	 * game Object which reduces the health of the shield */
	public void checkCollision()
	{
		for(int i = 0;i<handler.object.size();i++)
		{
			GameObject obj = handler.object.get(i);
			if(obj.id == ID.Fireball || obj.id == ID.FireballTrail)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(obj);	
					handler.removeObject(this);
					game.upgrades.isShield = false;
					HUD.SHIELDHEALTH = 0;
				}
			}
			if(obj.id == ID.Level1BossBomb)
			{
				if(getRect().intersects(obj.getRect()))
				{	
					handler.removeObject(obj);
					handler.removeObject(this);
					game.upgrades.isShield = false;
					HUD.SHIELDHEALTH = 0;
				}
			}
			if(obj.id == ID.Level2Boss)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(this);
					game.upgrades.isShield = false;
					HUD.SHIELDHEALTH = 0;
				}
			}
			if(obj.id == ID.Level3Boss)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(this);
					game.upgrades.isShield = false;
					HUD.SHIELDHEALTH = 0;
					HUD.HEALTH-=1;
				}
			}
			if(obj.id == ID.Level4Boss)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(this);
					game.upgrades.isShield = false;
					HUD.SHIELDHEALTH = 0;
					HUD.HEALTH-=0.9;
				}
			}
			if(obj.id == ID.MagmaRock)
			{
				if(getRect().intersects(obj.getRect()))
				{
					hit++;
					HUD.SHIELDHEALTH--;
					handler.removeObject(obj);
					if(hit == 8)
					{
						handler.removeObject(this);
						game.upgrades.isShield = false;
						hit = 0;
						HUD.SHIELDHEALTH = 0;
					}
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
			if(obj.id == ID.UnderGroundEnemy)
			{
				if(getRect().intersects(obj.getRect()))
				{
					hit+=5;
					HUD.SHIELDHEALTH-=5;
					handler.removeObject(obj);
					if(hit >= 8) 
					{
						handler.removeObject(this);
						HUD.SHIELDHEALTH = 0;
						hit = 0;
						game.upgrades.isShield = false;
						Spawn.undergroundenemyShow = true;
					}
				}
			}
			if(obj.id == ID.EnemyBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					hit+=2;
					HUD.SHIELDHEALTH-=2;
					handler.removeObject(obj);
					if(hit >= 8)
					{
						handler.removeObject(this);
						game.upgrades.isShield = false;
						HUD.SHIELDHEALTH = 0;
						hit = 0;
					}
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
					hit+=4;
					HUD.SHIELDHEALTH-=4;
					handler.removeObject(obj);
					if(hit >=8)
					{
						HUD.SHIELDHEALTH = 0;
						hit = 0;
						game.upgrades.isShield = false;
						handler.removeObject(this);
					}
					Spawn.wizardspawn = true;
				}
			}
			if(obj.id == ID.FreezePotion)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(obj);
					HUD.HEALTH-=8;
				}
			}
			if(obj.id == ID.GoldenRod)
			{
				if(getRect().intersects(obj.getRect()))
				{
					hit++;
					HUD.SHIELDHEALTH--;
					handler.removeObject(obj);
					if(hit>=8)
					{
						HUD.SHIELDHEALTH = 0;
						game.upgrades.isShield = false;
						hit = 0;
						handler.removeObject(this);
					}
				}
			}
			if(obj.id == ID.Emerald)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(obj);
					HUD.SCORE+=200;
				}
			}
			if(obj.id == ID.BossArrow)
			{
				if(getRect().intersects(obj.getRect()))
				{
					hit+=2;
					HUD.SHIELDHEALTH-=2;
					handler.removeObject(obj);
					if(hit>=8)
					{
						hit = 0;
						HUD.SHIELDHEALTH = 0;
						handler.removeObject(this);
						game.upgrades.isShield = false;
					}
				}
			}
			if(obj.id == ID.PinkGem)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(obj);
					HUD.SCORE+=150;
				}
			}
			if(obj.id == ID.HealthPotion)
			{
				if(getRect().intersects(obj.getRect()))
				{
					handler.removeObject(obj);
					if(HUD.HEALTH<=90)
						HUD.HEALTH+=10;
					else HUD.HEALTH = 100;
				}
			}
			if(obj.id == ID.ExploderEnemy)
			{
				if(getRect().intersects(obj.getRect()) && !game.isBossFight4)
				{
					hit+=5;
					HUD.SHIELDHEALTH-=5;
					handler.removeObject(obj);
					if(hit>=8)
					{
						handler.removeObject(this);
						hit = 0;
						HUD.SHIELDHEALTH = 0;
						game.upgrades.isShield = false;
					}
					HUD.EXPLODERHEALTH = 1;
					Spawn.exploderEnemyTime = true;
				}
			}
			if(obj.id == ID.ExploderEnemy)
			{
				if(getRect().intersects(obj.getRect()) && game.isBossFight4)
				{
					hit+=5;
					HUD.SHIELDHEALTH-=5;
					handler.removeObject(obj);
					if(hit>=8)
					{
						handler.removeObject(this);
						game.upgrades.isShield = false;
						hit = 0;
						HUD.SHIELDHEALTH = 0;
					}
					Spawn.exploderEnemyTime = true;
					HUD.EXPLODERHEALTH = 1;
				}
			}
			if(obj.id == ID.RayBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					hit+=3;
					HUD.SHIELDHEALTH-=3;
					handler.removeObject(obj);
					if(hit>=8)
					{
						handler.removeObject(this);
						hit = 0;
						HUD.SHIELDHEALTH = 0;
						game.upgrades.isShield = false;
					}
				}
			}
			if(obj.id == ID.Rocket)
			{
				if(getRect().intersects(obj.getRect()))
				{
					hit+=2;
					HUD.SHIELDHEALTH-=2;
					handler.removeObject(obj);
					if(hit>=8)
					{
						hit = 0;
						HUD.SHIELDHEALTH = 0;
						handler.removeObject(this);
						Rocket.destroyed = true;
						game.upgrades.isShield = false;
					}
				}
			}
			if(obj.id == ID.Boomerang)
			{
				if(getRect().intersects(obj.getRect()))
				{
					hit+=2;
					HUD.SHIELDHEALTH-=2;
					if(hit>=8) 
					{
						hit = 0;
						HUD.SHIELDHEALTH = 0;
						handler.removeObject(this);
						game.upgrades.isShield = false;
					}
				}
			}
			if(obj.id == ID.TankBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					hit+=3;
					HUD.SHIELDHEALTH-=3;
					handler.removeObject(obj);
					if(hit>=8)
					{
						hit = 0;
						HUD.SHIELDHEALTH = 0;
						handler.removeObject(this);
						game.upgrades.isShield = false;
					}
				}
			}
			if(obj.id == ID.PurpleEmerald)
			{
				if(getRect().intersects(obj.getRect()))
				{
					HUD.SCORE+=400;
					handler.removeObject(obj);
				}
			}
		}
	}
}
