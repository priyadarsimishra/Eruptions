package game.eruption;
import java.awt.Color;
import java.awt.Font;
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
	private double yVel = -5;
	public static boolean changeSpeed = true;
	public static int changeBack = 20;
	private ID id;
 	private SpriteTextures texture;
	private ObjectHandler handler;
	private Game game;
	public int bucketCount = 0;
	public int keyCount = 0;
	public int eggCount = 0;
	public int crateCount = 0;
	private Image explosion;
	private Image freeze;
	private Image bomb;
	private Image healthparticle;
	private Image batmanPlayer;
	private ImageIcon batmanIcon;
	private Image batmanPlayerShooting;
	private ImageIcon batmanShootingIcon;
	private Image batmanPlayerDualShooting;
	private ImageIcon batmanDualShootingIcon;
	private ImageIcon spidermanPlayer;
	private Image spiderman;
	private Image spidermanDual;
	private Image spidermanShooting;
	private Image mickeyMouse;
	private Image mickeyMouseShooting;
	private Image mickeyMouseDual;
	private Image gunSkin1;
	private Image gunSkin2;
	private Image ShotgunSkin1;
	private Image ShotgunSkin2;
	private Image SniperSkin1;
	private Image SniperSkin2;
	private Image DualPistol1Skin2;
	private Image DualPistol2Skin3;
	private ImageIcon mickeyMouseIcon;
	private ImageIcon mickeyMouseDualIcon;
	private ImageIcon mickeyMouseShootingIcon;
	private ImageIcon spidermanShootingIcon;
	private ImageIcon spidermanDualIcon;
	private ImageIcon gunSkin1Icon;
	private ImageIcon gunSkin2Icon;
	private ImageIcon ShotgunSkin1Icon;
	private ImageIcon ShotgunSkin2Icon;
	private ImageIcon SniperSkin1Icon;
	private ImageIcon SniperSkin2Icon;
	private ImageIcon DualPistol1Skin2Icon;	
	private ImageIcon DualPistol2Skin3Icon;
	private ImageIcon icon1;
	private ImageIcon icon2;
	private ImageIcon icon3;
	private ImageIcon icon4;
	private int imageTimeExplosion = 0;
	private int imageTimeBomb = 0;
	private int imageTimeParticle = 0;
	public static boolean pistolShoot = false;
	public static boolean doubleShoot = false;
	public static boolean shotgunShoot = false;
	public static boolean sniperShoot = false;
	public static boolean isDisplay = false;
	public static boolean explosionPic;
	public static boolean bombPic;
	public static boolean particle;
	public boolean shieldMade = false;
	private Image pistol;
	private ImageIcon pistolIcon;
	private Image shotgun;
	private ImageIcon shotgunIcon;
	private Image sniper;
	private ImageIcon sniperIcon;
	private int pause = 60;
	private double offset = 0;
	public static boolean playerMoved = false;
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
		if(game.gameState == game.STATE.GAMECOMPLETE)
		{
			yVel = -8;
			if(y<=75) 
			{
				y = 75;
				playerMoved = true;
			}
			else y+=yVel;
		}
		else
		{
			x+=xVel;
			if(x<=0)
				x=0;
			if(x>=763)
				x=763;
			checkCollision();
		}
	}
	/* This method is called 60 times per second
	 * Depending on the state it draws the player */
	public void render(Graphics g)
	{
		// Images
		shotgunIcon = new ImageIcon(getClass().getResource("/Shotgun.png"));
		shotgun = shotgunIcon.getImage();
		sniperIcon = new ImageIcon(getClass().getResource("/Sniper.png"));
		sniper = sniperIcon.getImage();
		icon1 = new ImageIcon(getClass().getResource("/Explosion.gif"));
		explosion = icon1.getImage();
		icon2 = new ImageIcon(getClass().getResource("/Bomb.gif"));
		bomb = icon2.getImage();
		icon3 = new ImageIcon(getClass().getResource("/Freeze.gif"));
		freeze = icon3.getImage();
		icon4 = new ImageIcon(getClass().getResource("/health.png"));
		healthparticle = icon4.getImage();
		batmanIcon = new ImageIcon(getClass().getResource("/BatmanPlayer.png"));
		batmanPlayer = batmanIcon.getImage();
		batmanShootingIcon = new ImageIcon(getClass().getResource("/BatmanShooting.png"));
		batmanPlayerShooting = batmanShootingIcon.getImage();
		batmanDualShootingIcon = new ImageIcon(getClass().getResource("/BatmanDualShooting.png"));
		batmanPlayerDualShooting = batmanDualShootingIcon.getImage();
		pistolIcon = new ImageIcon(getClass().getResource("/Pistol.png"));
		pistol = pistolIcon.getImage();
		spidermanPlayer = new ImageIcon(getClass().getResource("/SpidermanPlayer.png"));
		spiderman = spidermanPlayer.getImage();
		spidermanShootingIcon = new ImageIcon(getClass().getResource("/SpidermanShooting.png"));
		spidermanShooting = spidermanShootingIcon.getImage();
		spidermanDualIcon = new ImageIcon(getClass().getResource("/SpiderManDual.png"));
		spidermanDual = spidermanDualIcon.getImage();
		mickeyMouseIcon = new ImageIcon(getClass().getResource("/MickeyMousePlayer.png"));
		mickeyMouse = mickeyMouseIcon.getImage();
		mickeyMouseShootingIcon = new ImageIcon(getClass().getResource("/MickeyMousePistol.png"));
		mickeyMouseShooting = mickeyMouseShootingIcon.getImage();
		mickeyMouseDualIcon =  new ImageIcon(getClass().getResource("/MickeyMouseDual.png"));
		mickeyMouseDual = mickeyMouseDualIcon.getImage();
		gunSkin1Icon = new ImageIcon(getClass().getResource("/GunSkin1.png"));
		gunSkin1 = gunSkin1Icon.getImage();
		gunSkin2Icon = new ImageIcon(getClass().getResource("/gunSkin2.png"));
		gunSkin2 = gunSkin2Icon.getImage();
		ShotgunSkin1Icon = new ImageIcon(getClass().getResource("/ShotgunSkin1.png"));
		ShotgunSkin1 = ShotgunSkin1Icon.getImage();
		ShotgunSkin2Icon = new ImageIcon(getClass().getResource("/ShotgunSkin2.png"));
		ShotgunSkin2 = ShotgunSkin2Icon.getImage();
		SniperSkin1Icon = new ImageIcon(getClass().getResource("/SniperSkin1.png"));
		SniperSkin1 = SniperSkin1Icon.getImage();
		SniperSkin2Icon = new ImageIcon(getClass().getResource("/Skin2Sniper.png"));
		SniperSkin2 = SniperSkin2Icon.getImage();
		DualPistol1Skin2Icon = new ImageIcon(getClass().getResource("/Skin2DualPistol.png"));
		DualPistol1Skin2 = DualPistol1Skin2Icon.getImage();
		DualPistol2Skin3Icon = new ImageIcon(getClass().getResource("/Skin3DualPistol.png"));
		DualPistol2Skin3 = DualPistol2Skin3Icon.getImage();
		// Images
		if(game.customize.isSkin1)
		{
			if(pistolShoot) 
			{
				g.drawImage(batmanPlayerShooting,(int)x,(int)y+12,50,70,null);
				if(game.customize.gunSkin2Row1Clicked)
				{
					g.drawImage(gunSkin1,(int)x+12,(int)y-4,16,16,null);
				}
				else if(game.customize.gunSkin3Row1Clicked)
				{
					g.drawImage(gunSkin2,(int)x+12,(int)y-4,16,16,null);
				}
				else
				{
					game.customize.gunSkin1Row1Clicked = true;
					g.drawImage(pistol,(int)x+12,(int)y-4,16,16,null);
				}
			}
			else if(doubleShoot)
			{
				g.drawImage(batmanPlayerDualShooting,(int)x,(int)y+12,50,70,null);
				if(game.customize.gunSkin2Row4Clicked)
				{
					g.drawImage(DualPistol1Skin2,(int)x-9,(int)y+2,26,26,null);
					g.drawImage(DualPistol1Skin2,(int)x+25,(int)y+2,26,26,null);
				}
				else if(game.customize.gunSkin3Row4Clicked)
				{
					g.drawImage(DualPistol2Skin3,(int)x-9,(int)y+2,26,26,null);
					g.drawImage(DualPistol2Skin3,(int)x+25,(int)y+2,26,26,null);
				}
				else
				{
					game.customize.gunSkin1Row4Clicked = true;
				}
			}
			else if(shotgunShoot) 
			{
				g.drawImage(batmanPlayerShooting,(int)x,(int)y+12,50,70,null);
				if(game.customize.gunSkin2Row2Clicked)
				{
					g.drawImage(ShotgunSkin1,(int)x+4,(int)y-10,32,32,null);
				}
				else if(game.customize.gunSkin3Row2Clicked)
				{
					g.drawImage(ShotgunSkin2,(int)x+4,(int)y-10,32,32,null);
				}
				else
				{
					game.customize.gunSkin1Row2Clicked = true;
					g.drawImage(shotgun,(int)x+4,(int)y-10,32,32,null);
				}
			}
			else if(sniperShoot)
			{
				g.drawImage(batmanPlayerShooting,(int)x,(int)y+12,50,70,null);
				if(game.customize.gunSkin2Row3Clicked)
				{
					g.drawImage(SniperSkin1,(int)x-2,(int)y-22,44,44,null);
				}
				else if(game.customize.gunSkin3Row3Clicked)
				{
					g.drawImage(SniperSkin2,(int)x-2,(int)y-22,44,44,null);
				}
				else
				{
					game.customize.gunSkin1Row3Clicked = true;
					g.drawImage(sniper,(int)x-2,(int)y-25,44,44,null);
				}
			}
			else g.drawImage(batmanPlayer,(int)x,(int)y+12,50,70,null);
			if(changeSpeed) 
			{
				g.drawImage(freeze,(int)x-10,(int)y,60,60,null);
			}
		}
		else if(game.customize.isSkin2)
		{
			if(pistolShoot) 
			{
				g.drawImage(spidermanShooting,(int)x,(int)y+12,50,70,null);
				if(game.customize.gunSkin2Row1Clicked)
				{
					g.drawImage(gunSkin1,(int)x+11,(int)y-4,16,16,null);
				}
				else if(game.customize.gunSkin3Row1Clicked)
				{
					g.drawImage(gunSkin2,(int)x+11,(int)y-4,16,16,null);
				}
				else
				{
					game.customize.gunSkin1Row1Clicked = true;
					g.drawImage(pistol,(int)x+11,(int)y-4,16,16,null);
				}
			}
			else if(doubleShoot)
			{
				g.drawImage(spidermanDual,(int)x,(int)y+12,50,70,null);
				if(game.customize.gunSkin2Row4Clicked)
				{
					g.drawImage(DualPistol1Skin2,(int)x-9,(int)y+2,23,23,null);
					g.drawImage(DualPistol1Skin2,(int)x+20,(int)y+2,23,23,null);
				}
				else if(game.customize.gunSkin3Row4Clicked)
				{
					g.drawImage(DualPistol2Skin3,(int)x-9,(int)y+2,23,23,null);
					g.drawImage(DualPistol2Skin3,(int)x+20,(int)y+2,23,23,null);
				}
				else
				{
					game.customize.gunSkin1Row4Clicked = true;
				}
			}
			else if(shotgunShoot) 
			{
				g.drawImage(spidermanShooting,(int)x,(int)y+12,50,70,null);
				if(game.customize.gunSkin2Row2Clicked)
				{
					g.drawImage(ShotgunSkin1,(int)x+3,(int)y-10,32,32,null);
				}
				else if(game.customize.gunSkin3Row2Clicked)
				{
					g.drawImage(ShotgunSkin2,(int)x+3,(int)y-10,32,32,null);

				}
				else
				{
					game.customize.gunSkin1Row2Clicked = true;
					g.drawImage(shotgun,(int)x+3,(int)y-10,32,32,null);
				}
			}
			else if(sniperShoot)
			{
				if(game.customize.gunSkin2Row3Clicked)
				{
					g.drawImage(SniperSkin1,(int)x-3,(int)y-25,44,44,null);
				}
				else if(game.customize.gunSkin3Row3Clicked)
				{
					g.drawImage(SniperSkin2,(int)x-3,(int)y-25,44,44,null);
				}
				else
				{
					game.customize.gunSkin1Row3Clicked = true;
					g.drawImage(sniper,(int)x-3,(int)y-25,44,44,null);
				}
				g.drawImage(spidermanShooting,(int)x,(int)y+12,50,70,null);
			}
			else g.drawImage(spiderman,(int)x,(int)y+12,50,70,null);
			if(changeSpeed) 
			{
				g.drawImage(freeze,(int)x-10,(int)y,60,60,null);
			}
		}
		else if(game.customize.isSkin3)
		{
			if(pistolShoot) 
			{
				g.drawImage(mickeyMouseShooting,(int)x,(int)y+12,50,70,null);
				if(game.customize.gunSkin2Row1Clicked)
				{
					g.drawImage(gunSkin1,(int)x+9,(int)y+2,16,16,null);
				}
				else if(game.customize.gunSkin3Row1Clicked)
				{
					g.drawImage(gunSkin2,(int)x+9,(int)y+2,16,16,null);
				}
				else
				{
					game.customize.gunSkin1Row1Clicked = true;
					g.drawImage(pistol,(int)x+9,(int)y+2,16,16,null);
				}
			}
			else if(doubleShoot)
			{
				g.drawImage(mickeyMouseDual,(int)x,(int)y+12,50,70,null);
				if(game.customize.gunSkin2Row4Clicked)
				{
					g.drawImage(DualPistol1Skin2,(int)x-9,(int)y+3,23,23,null);
					g.drawImage(DualPistol1Skin2,(int)x+23,(int)y+3,23,23,null);
				}
				else if(game.customize.gunSkin3Row4Clicked)
				{
					g.drawImage(DualPistol2Skin3,(int)x-9,(int)y+3,23,23,null);
					g.drawImage(DualPistol2Skin3,(int)x+23,(int)y+3,23,23,null);
				}
				else
				{
					game.customize.gunSkin1Row4Clicked = true;
				}
			}
			else if(shotgunShoot) 
			{
				g.drawImage(mickeyMouseShooting,(int)x-2,(int)y+12,50,70,null);
				if(game.customize.gunSkin2Row2Clicked)
				{
					g.drawImage(ShotgunSkin1,(int)x,(int)y-10,32,32,null);
				}
				else if(game.customize.gunSkin3Row2Clicked)
				{
					g.drawImage(ShotgunSkin2,(int)x,(int)y-10,32,32,null);
				}
				else
				{
					game.customize.gunSkin1Row2Clicked = true;
					g.drawImage(shotgun,(int)x,(int)y-10,32,32,null);
				}
			}
			else if(sniperShoot)
			{
				if(game.customize.gunSkin2Row3Clicked)
				{
					g.drawImage(SniperSkin1,(int)x-8,(int)y-25,44,44,null);
				}
				else if(game.customize.gunSkin3Row3Clicked)
				{
					g.drawImage(SniperSkin2,(int)x-8,(int)y-25,44,44,null);
				}
				else
				{
					game.customize.gunSkin1Row3Clicked = true;
					g.drawImage(sniper,(int)x-8,(int)y-25,44,44,null);
				}
				g.drawImage(mickeyMouseShooting,(int)x-3,(int)y+12,50,70,null);
			}
			else g.drawImage(mickeyMouse,(int)x,(int)y+12,50,70,null);
			if(changeSpeed) 
			{
				g.drawImage(freeze,(int)x-10,(int)y,60,60,null);
			}
		}
		else
		{
			game.customize.defaultSkin = true;
			if(pistolShoot)
			{
				g.drawImage(texture.playerShooting,(int)x,(int)y,50,80,null);
				if(game.customize.gunSkin2Row1Clicked)
				{
					g.drawImage(gunSkin1,(int)x+9,(int)y+2,16,16,null);
				}
				else if(game.customize.gunSkin3Row1Clicked)
				{
					g.drawImage(gunSkin2,(int)x+9,(int)y+2,16,16,null);
				}
				else
				{
					game.customize.gunSkin1Row1Clicked = true;
					g.drawImage(pistol,(int)x+9,(int)y+2,16,16,null);
				}
			}
			else if(doubleShoot) 
			{
				g.drawImage(texture.doublePistolPlayer,(int)x,(int)y,50,80,null);
				if(game.customize.gunSkin2Row4Clicked)
				{
					g.drawImage(DualPistol1Skin2,(int)x-8,(int)y-6,23,23,null);
					g.drawImage(DualPistol1Skin2,(int)x+20,(int)y-6,23,23,null);
				}
				else if(game.customize.gunSkin3Row4Clicked)
				{
					g.drawImage(DualPistol2Skin3,(int)x-8,(int)y-6,23,23,null);
					g.drawImage(DualPistol2Skin3,(int)x+20,(int)y-6,23,23,null);
				}
				else
				{
					game.customize.gunSkin1Row4Clicked = true;
				}
			}
			else if(shotgunShoot) 
			{
				g.drawImage(texture.playerShooting,(int)x,(int)y,50,80,null);
				if(game.customize.gunSkin2Row2Clicked)
				{
					g.drawImage(ShotgunSkin1,(int)x+2,(int)y-10,32,32,null);
				}
				else if(game.customize.gunSkin3Row2Clicked)
				{
					g.drawImage(ShotgunSkin2,(int)x+2,(int)y-10,32,32,null);
				}
				else
				{
					game.customize.gunSkin1Row2Clicked = true;
					g.drawImage(shotgun,(int)x+2,(int)y-10,32,32,null);
				}
			}
			else if(sniperShoot)
			{
				g.drawImage(texture.playerShooting,(int)x,(int)y,50,80,null);
				if(game.customize.gunSkin2Row3Clicked)
				{
					g.drawImage(SniperSkin1,(int)x-5,(int)y-25,44,44,null);
				}
				else if(game.customize.gunSkin3Row3Clicked)
				{
					g.drawImage(SniperSkin2,(int)x-5,(int)y-25,44,44,null);
				}
				else
				{
					game.customize.gunSkin1Row3Clicked = true;
					g.drawImage(sniper,(int)x-5,(int)y-25,44,44,null);
				}
			}
			else g.drawImage(texture.player,(int)x,(int)y,50,80,null);
			if(changeSpeed)
			{
				g.drawImage(freeze,(int)x-10,(int)y,60,60,null);
			}
		}
		if(particle)
		{
			if(imageTimeParticle>=100)
			{
				particle = false;
				imageTimeParticle = 0;
			}
			else 
			{
				g.drawImage(healthparticle,(int)x+10,(int)y-8,20,20,null);
				imageTimeParticle++;
			}
		}
		if(explosionPic)
		{
			if(imageTimeExplosion>=350)
			{
				explosionPic = false;
				imageTimeExplosion = 0;
			}
			else
			{
				g.drawImage(explosion,(int)x-53,(int)y-120,null);
				imageTimeExplosion++;
			}
		}
		if(bombPic)
		{
			if(imageTimeBomb>=350)
			{
				bombPic = false;
				imageTimeBomb = 0;
			}
			else
			{
				g.drawImage(bomb,(int)x-50,(int)y-80,null);
				imageTimeBomb++;
			}
		}
		if(game.upgrades.isShield && !shieldMade)
		{
			HUD.SHIELDHEALTH = 8;
			handler.addObject(new PlayerShield(x,y,ID.PlayerShield,handler,game));
			shieldMade = true;
		}
		if(game.upgrades.isScoreBoost)
		{
			HUD.SCOREBOOST-=0.01;
			if(HUD.SCOREBOOST<=0)
			{
				game.upgrades.isScoreBoost = false;
				HUD.SCOREBOOST = 4000;
			}
		}
		if(game.gameState == game.STATE.GAMECOMPLETE)
		{
			Font nameFont = new Font("Roboto",Font.BOLD,12);
			g.setFont(nameFont);
			g.setColor(Color.WHITE);
			offset = Game.NAME.length()*2.75;
			double nameOffset = Game.NAME.length()*(Game.NAME.length()/9);
			g.fillRect((int)(x-offset+2),(int)y+102,(int)(Game.NAME.length()+26+offset*2),20);
			g.setColor(Color.RED);
			g.drawString(Game.NAME,(int)(x-(nameOffset+((double)Game.NAME.length()/4.5))),(int)y+114);
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
					SoundPlayer.getSound("fire_ball").play();
					HUD.HEALTH-=1;
					explosionPic = true;
				}
			}
			if(obj.id == ID.Level1BossBomb)
			{
				if(getRect().intersects(obj.getRect()))
				{
					SoundPlayer.getSound("bomb").play();
					HUD.HEALTH-=15;
					bombPic = true;
					handler.removeObject(obj);
				}
			}
			if(obj.id == ID.Level2Boss)
			{
				if(getRect().intersects(obj.getRect()))
				{
					SoundPlayer.getSound("boss_damage").play();
					HUD.HEALTH-=2;
				}
			}
			if(obj.id == ID.Level3Boss)
			{
				if(getRect().intersects(obj.getRect()))
				{
					SoundPlayer.getSound("boss_damage").play();
					HUD.HEALTH-=1;
				}
			}
			if(obj.id == ID.Level4Boss)
			{
				if(getRect().intersects(obj.getRect()))
				{
					SoundPlayer.getSound("boss_damage").play();
					HUD.HEALTH-=0.8;
				}
			}
			if(obj.id == ID.MagmaRock)
			{
				if(getRect().intersects(obj.getRect()))
				{
					SoundPlayer.getSound("magma_rock").play();
					handler.removeObject(obj);
					HUD.HEALTH-=10;
				}
			}
			if(obj.id == ID.BronzeCoin)
			{
				if(getRect().intersects(obj.getRect()))
				{
					SoundPlayer.getSound("bronze_coin").play();
					handler.removeObject(obj);
					if(game.upgrades.isScoreBoost) HUD.SCORE+=200;
					else HUD.SCORE+=100;
				}
			}
			if(obj.id == ID.SilverCoin)
			{
				if(getRect().intersects(obj.getRect()))
				{
					SoundPlayer.getSound("silver_coin").play();
					handler.removeObject(obj);
					if(game.upgrades.isScoreBoost) HUD.SCORE+=500;
					else HUD.SCORE+=250;
				}
			}
			if(obj.id == ID.GoldCoin)
			{
				if(getRect().intersects(obj.getRect()))
				{
					SoundPlayer.getSound("gold_coin").play();
					handler.removeObject(obj);
					if(game.upgrades.isScoreBoost) HUD.SCORE+=1000;
					else HUD.SCORE+=500;
				}
			}
			if(obj.id == ID.Waterbucket)
			{
				if(getRect().intersects(obj.getRect()))
				{
					SoundPlayer.getSound("water_bucket_pickUp").play();
					handler.removeObject(obj);
					bucketCount++;
				}
			}
			if(obj.id == ID.Key)
			{
				if(getRect().intersects(obj.getRect()))
				{
					SoundPlayer.getSound("key_pickUp").play();
					handler.removeObject(obj);
					keyCount++;
				}
			}
			if(obj.id == ID.Egg)
			{
				if(getRect().intersects(obj.getRect()))
				{
					SoundPlayer.getSound("egg_cracking").play();
					handler.removeObject(obj);
					eggCount++;
				}
			}
			if(obj.id == ID.Crate)
			{
				if(getRect().intersects(obj.getRect()))
				{
					SoundPlayer.getSound("crate_pickUp").play();
					handler.removeObject(obj);
					crateCount++;
				}
			}
			if(obj.id == ID.UnderGroundEnemy)
			{
				if(getRect().intersects(obj.getRect()))
				{
					SoundPlayer.getSound("under_explosion").play();
					handler.removeObject(obj);
					HUD.HEALTH-=25;
					explosionPic = true;
					Spawn.undergroundenemyShow = true;
				}
			}
			if(obj.id == ID.EnemyBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					SoundPlayer.getSound("enemyBullet").play();
					handler.removeObject(obj);
					HUD.HEALTH-=6;
				}
			}
			if(obj.id == ID.DiamondGem)
			{
				if(getRect().intersects(obj.getRect()))
				{
					SoundPlayer.getSound("diamond_pickUp").play();
					handler.removeObject(obj);
					if(game.upgrades.isScoreBoost) HUD.SCORE+=1500;
					else HUD.SCORE+=750;
				}
			}
			if(obj.id == ID.RubyGem)
			{
				if(getRect().intersects(obj.getRect()))
				{
					SoundPlayer.getSound("ruby_pickUp").play();
					handler.removeObject(obj);
					if(game.upgrades.isScoreBoost) HUD.SCORE+=1000;
					else HUD.SCORE+=500;
				}
			}
			if(obj.id == ID.Wizard)
			{
				if(getRect().intersects(obj.getRect()))
				{
					SoundPlayer.getSound("wizard").play();
					handler.removeObject(obj);
					HUD.HEALTH-=30;
					changeSpeed = true;
					changeBack = 5;
					isDisplay = true;
					Spawn.wizardspawn = true;
				}
			}
			if(obj.id == ID.FreezePotion)
			{
				if(getRect().intersects(obj.getRect()))
				{
					SoundPlayer.getSound("freeze_potion").play();
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
					SoundPlayer.getSound("golden_rod").play();
					handler.removeObject(obj);
					HUD.HEALTH-=5;
				}
			}
			if(obj.id == ID.Emerald)
			{
				if(getRect().intersects(obj.getRect()))
				{
					SoundPlayer.getSound("emerald").play();
					handler.removeObject(obj);
					if(game.upgrades.isScoreBoost) HUD.SCORE+=400;
					else HUD.SCORE+=200;
				}
			}
			if(obj.id == ID.BossArrow)
			{
				if(getRect().intersects(obj.getRect()))
				{
					SoundPlayer.getSound("boss_arrow").play();
					handler.removeObject(obj);
					HUD.HEALTH-=5;
				}
			}
			if(obj.id == ID.PinkGem)
			{
				if(getRect().intersects(obj.getRect()))
				{
					SoundPlayer.getSound("pink_gem").play();
					handler.removeObject(obj);
					if(game.upgrades.isScoreBoost) HUD.SCORE+=300;
					else HUD.SCORE+=150;
				}
			}
			if(obj.id == ID.HealthPotion)
			{
				if(getRect().intersects(obj.getRect()))
				{
					SoundPlayer.getSound("health_potion").play();
					handler.removeObject(obj);
					if(HUD.HEALTH<=90)
						HUD.HEALTH+=10;
					else HUD.HEALTH = 100;
					particle = true;
				}
			}
			if(obj.id == ID.ExploderEnemy)
			{
				if(getRect().intersects(obj.getRect()) && !game.isBossFight4)
				{
					SoundPlayer.getSound("exploder_enemy").play();
					handler.removeObject(obj);
					HUD.HEALTH-=15;
					Spawn.exploderEnemyTime = true;
					HUD.EXPLODERHEALTH = 1;
					explosionPic = true;
				}
			}
			//Level4Boss Fight
			if(obj.id == ID.ExploderEnemy)
			{
				if(getRect().intersects(obj.getRect()) && game.isBossFight4)
				{
					SoundPlayer.getSound("exploder_enemy").play();
					handler.removeObject(obj);
					HUD.HEALTH-=15;
					HUD.EXPLODERHEALTH = 1;
					explosionPic = true;
					Level4Boss.isAlive = false;
				}
			}
			if(obj.id == ID.RayBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					SoundPlayer.getSound("raybullet_damage").play();
					HUD.HEALTH-=0.2;
					handler.removeObject(obj);
				}
			}
			if(obj.id == ID.Rocket)
			{
				if(getRect().intersects(obj.getRect()))
				{
					SoundPlayer.getSound("rocket_impact").play();
					HUD.HEALTH-=8;
					handler.removeObject(obj);
					Rocket.destroyed = true;
				}
			}
			if(obj.id == ID.Boomerang)
			{
				if(getRect().intersects(obj.getRect()))
				{
					HUD.HEALTH-=4;
				}
			}
			if(obj.id == ID.TankBullet)
			{
				if(getRect().intersects(obj.getRect()))
				{
					SoundPlayer.getSound("tank_bullet").play();
					HUD.HEALTH-=15;
					handler.removeObject(obj);
				}
			}
			if(obj.id == ID.PurpleEmerald)
			{
				if(getRect().intersects(obj.getRect()))
				{
					SoundPlayer.getSound("purple_emerald").play();
					if(game.upgrades.isScoreBoost) HUD.SCORE+=800;
					else HUD.SCORE+=400;
					handler.removeObject(obj);
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
