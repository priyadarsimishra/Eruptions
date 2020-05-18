import java.awt.Graphics;
import java.util.Random;
/* This class is used to spawn our game objects 
 * at random times which adds to the game play */
public class Spawn 
{
	private ObjectHandler handler;
	private Game game;
	private HUD hud;
	private Random r = new Random();
	private SpriteTextures texture;
	private int bronzeTimer = r.nextInt(150)+70;
	private int silverTimer = r.nextInt(200)+120;
	private int goldTimer = r.nextInt(600)+300;
	private int emeraldTime = 200;
	private int purpleTime = 400;
	private int rubyTime = 600;
	private int diamondTime = 900;
	private int healthTime = 450;
	private int fireballTimer = 120;
	private int magmaRockTimer = 100;
	private int wave = 500;
	private int enemyFireball = 40;
	private int waterbucketTime = 100;
	private int keyTime = 100;
	private int disappear = 20;
	private int enemyBulletSpeed = 60;
	private int enemyArrow = 80;
	private int pinkGemTime = 120;
	private int icePotion = 140;
	public int exploderTime = 100;
	public int rayTime = 300;
	public int rayBulletTime = 200;
	public int rocketTime = 400;
	public int eggTime = 100;
	public int tankTime = 350;
	public int boomerangTime = 250;
	public int shieldEnemy = 150;
	public int crateTime = 100;
	public int level4bulletTime = 250;
	public static boolean shieldEnemyTime = true;
	public static boolean rocketEnemyTime = true;
	public static boolean boomerangEnemyTime = true;
	public static boolean spawnbabyDragon = false;
	public static boolean rayEnemyTime = true;
	public static boolean exploderEnemyTime = true;
	public static boolean undergroundenemyShow = true;
	public static boolean wizardspawn = true;
	public static boolean throwerspawn = true;
	public static boolean tankspawn  = true;
	private int throwerTime = 650;
	private int wizardTime = 300;
	private boolean isWave = false;
	private int gap;
	public boolean bossMade = false;
	public boolean bossMade2 = false;
	public boolean bossMade3 = false;
	public boolean bossMade4 = false;
	public boolean dead = false;
	public boolean stopSplit = false;
	public static int showIt = 500;
	/* This constructor has many parameters
	 * that are needed to control the adding of
	 * certain Game Objects */
	public Spawn(ObjectHandler handler, HUD hud,Game game,SpriteTextures texture)
	{
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		this.texture = texture;
	}
	/* This method updates 60 times per second 
	 * and this method adds objects depending on the timing 
	 * and the level */
	public void update()
	{
		/* Level 1 */
		if(game.gameState == game.STATE.LEVEL1 && game.level1pause>=500 && !game.isBossFight)
		{
			//bronze coin
			if(bronzeTimer<=0)
			{		
				handler.addObject(new BronzeCoin(r.nextInt(768),-32,texture,handler,ID.BronzeCoin));
				bronzeTimer = r.nextInt(150)+100;
			}
			else
				bronzeTimer--;
			//silver coin
			if(silverTimer<=0)
			{		
				handler.addObject(new SilverCoin(r.nextInt(768),-32,texture,handler,ID.SilverCoin));
				silverTimer = r.nextInt(200)+120;;
			}
			else
				silverTimer--;
			//gold coin
			if(goldTimer<=0)
			{		
				handler.addObject(new GoldCoin(r.nextInt(768),-32,texture,handler,ID.GoldCoin));
				goldTimer = r.nextInt(600)+300;
			}
			else
				goldTimer--;
			//fire ball
			if(fireballTimer<=0)
			{		
				handler.addObject(new Fireball(r.nextInt(740),-50,texture,handler,ID.Fireball,r.nextInt(16)+7));
				
				fireballTimer = 120;
			}
			else
				fireballTimer--;
			if(magmaRockTimer<=0)
			{
				handler.addObject(new MagmaRock(r.nextInt(10)+0,r.nextInt(200)+0,texture,handler,ID.MagmaRock,r.nextInt(10)+5,r.nextInt(8)+3,true));
				handler.addObject(new MagmaRock(r.nextInt(800)+600,r.nextInt(100)+0,texture,handler,ID.MagmaRock,r.nextInt(10)+5,-(r.nextInt(8)+3),false));
				magmaRockTimer = 100;
			}
			else magmaRockTimer--;
			if(wave<=0)
			{
				isWave = true;
				wave = 500;
			}
			else
			{
				isWave = false;
				wave--;
			}
			while(isWave)
			{
				//wave
				int total = 0;		
				gap = r.nextInt(12)+1;
				while(total<=12)
				{
					if(total == gap)
					{
						total++;
						handler.addObject(new Fireball(total*60+26,-32,texture,handler,ID.Fireball,7));
					}
					else
					{
						handler.addObject(new Fireball(total*60+26,-32,texture,handler,ID.Fireball,7));
					}
					total++;
				}
				if(total >= 12)
					isWave = false;
				else 
					isWave = true;
			}
		}
		/* Boss Fight Level 1*/
		if(game.isBossFight && !bossMade)
		{
			bossMade = true;
			handler.addObject(new Level1Boss(Game.WIDTH/2-100,-10,ID.Level1Boss,texture,game,6));
		}
		if(game.isBossFight)
		{
			if(enemyFireball <= 0)
			{
				int randomSpeed = r.nextInt(9)+7;
				handler.addObject(new Level1BossBomb((int)Level1Boss.x+10,(int)Level1Boss.y+150,texture,handler,ID.Level1BossBomb,randomSpeed));
				handler.addObject(new Level1BossBomb((int)Level1Boss.x+80,(int)Level1Boss.y+150,texture,handler,ID.Level1BossBomb,randomSpeed));
				handler.addObject(new Level1BossBomb((int)Level1Boss.x+150,(int)Level1Boss.y+150,texture,handler,ID.Level1BossBomb,randomSpeed));
				enemyFireball = 40;
			}
			else enemyFireball--;
			if(waterbucketTime <= 0)
			{
				WaterBucket wb = new WaterBucket(r.nextInt(Game.WIDTH-36),730,texture,handler,ID.Waterbucket);
				handler.addObject(wb);
				waterbucketTime = 100;
			}
			else waterbucketTime--;
		}
		/* Level 2 */
		if(game.gameState == game.STATE.LEVEL2 && game.level2pause>=500 && !game.isBossFight2)
		{
			/* Underground Enemy */
			if(undergroundenemyShow)
			{
				HUD.UNDERGROUNDHEALTH = 25;
				handler.addObject(new UnderGroundEnemy(r.nextInt(746),r.nextInt(5)+1,ID.UnderGroundEnemy,handler,texture));
				undergroundenemyShow = false;
			}
			if(HUD.UNDERGROUNDHEALTH<=0)
			{		
				if(showIt<=0)
				{
					LevelDisplay.underTime = 100;
					UnderGroundEnemy.show = false;
					handler.addObject(new UnderGroundEnemy(r.nextInt(746),r.nextInt(5)+1,ID.UnderGroundEnemy,handler,texture));
					HUD.UNDERGROUNDHEALTH = 25;	
					showIt = 500;
				}
				else
					showIt--;
			}
			if(diamondTime<=0)
			{
				handler.addObject(new DiamondGem(r.nextInt(748)+0, r.nextInt(100)-100,ID.DiamondGem,handler,texture, r.nextInt(14)+5,r.nextInt(14)+5));
				diamondTime = 1200;
			}
			else diamondTime--;
			if(rubyTime<=0)
			{
				handler.addObject(new RubyGem(r.nextInt(768)+0,r.nextInt(100)-32,ID.RubyGem,handler,texture,r.nextInt(12)+7));
				handler.addObject(new RubyGem(r.nextInt(7688)+0,r.nextInt(100)-32,ID.RubyGem,handler,texture,r.nextInt(12)+7));
				rubyTime = 600;
			}
			else rubyTime--;
			if(wizardspawn)
			{
				HUD.WIZARDHEALTH = 50;
				LevelDisplay.wizardTime = 100;
				HUD.WIZARDHEALTH = 50;
				handler.addObject(new Wizard(r.nextInt(760)+0,-5,ID.Wizard,r.nextInt(6)+3,r.nextInt(6)+3,handler,texture));
				wizardspawn = false;
			}
			if(HUD.WIZARDHEALTH<=0)
			{
				if(wizardTime<=0)
				{
					LevelDisplay.wizardTime = 100;
					handler.addObject(new Wizard(r.nextInt(768)+1,-5,ID.Wizard,r.nextInt(3)+2,r.nextInt(2)+1,handler,texture));
					HUD.WIZARDHEALTH = 50;
					wizardTime = 300;
				}
				else wizardTime--;
			}
			if(throwerspawn)
			{
				ThrowerEnemy.split = false;
				HUD.THROWERHEALTH = 50;
				handler.addObject(new ThrowerEnemy(r.nextInt(540)+80,-32,ID.ThrowerEnemy,handler,texture,48,48));
				throwerspawn = false;
			}
			if(ThrowerEnemy.giveInfo && HUD.THROWERHEALTH<=25)
			{
				LevelDisplay.split1Time = 200;
				LevelDisplay.split2Time = 200;
				ThrowerEnemy.giveInfo = true;
				HUD.SPLITHEALTH1 = 25;
				HUD.SPLITHEALTH2 = 25;
				handler.addObject(new SplitEnemy1(ThrowerEnemy.giveX,ThrowerEnemy.giveY,ID.SplitEnemy2,handler,texture,32,32,ThrowerEnemy.giveInfo));
				handler.addObject(new SplitEnemy2(ThrowerEnemy.giveX,ThrowerEnemy.giveY,ID.SplitEnemy2,handler,texture,32,32,ThrowerEnemy.giveInfo));

				ThrowerEnemy.giveInfo = false;
			}
			if(!ThrowerEnemy.giveInfo && HUD.SPLITHEALTH1<=0 && HUD.SPLITHEALTH2<=0)
			{
				SplitEnemy1.show = false;
				SplitEnemy2.show = false;
			}
			if(HUD.THROWERHEALTH<=0 && !SplitEnemy1.show && !SplitEnemy2.show)
			{
				if(throwerTime<=0)
				{
					ThrowerEnemy.split = false;
					
					handler.addObject(new ThrowerEnemy(r.nextInt(540)+80,r.nextInt(200)+0,ID.ThrowerEnemy,handler,texture,48,48));
					HUD.THROWERHEALTH = 50;
					throwerTime = 500;
				}
				else throwerTime--;
			}
			if(fireballTimer<=0)
			{		
				handler.addObject(new Fireball(r.nextInt(740),-50,texture,handler,ID.Fireball,r.nextInt(16)+7));
				
				fireballTimer = 100;
			}
			else
				fireballTimer--;
			if(emeraldTime<= 0)
			{
				handler.addObject(new Emerald(r.nextInt(768)+0,-32,ID.Emerald,handler,texture,r.nextInt(8)+3));
				emeraldTime = 200;
			}
			else emeraldTime--;
			
		}
		/* Boss Fight in level 2*/
		if(game.isBossFight2 && !bossMade2)
		{
			bossMade2 = true;
			handler.addObject(new Level2Boss(Game.WIDTH/2-100,-40,ID.Level2Boss,texture,handler,6));
		}
		if(game.isBossFight2)
		{
			if(enemyArrow<=0)
			{
				handler.addObject(new BossArrow(Level2Boss.x+50,Level2Boss.y+150,ID.BossArrow,handler,texture,10));
				handler.addObject(new BossArrow(Level2Boss.x+120,Level2Boss.y+150,ID.BossArrow,handler,texture,10));
				enemyArrow = 60;
			}
			else enemyArrow--;
			if(icePotion<=0)
			{
				handler.addObject(new FreezePotion(Level2Boss.x+95,Level2Boss.y+150,r.nextInt(8)+2,ID.FreezePotion,handler,texture));
				icePotion = 140;
			}
			else icePotion--;
			if(keyTime<=0)
			{
				Key key = new Key(r.nextInt(764)+0,730,ID.Key,handler,texture);
				handler.addObject(key);
				keyTime = 140;
			}
			else keyTime--;
			if(diamondTime<=0)
			{
				handler.addObject(new DiamondGem(r.nextInt(748)+0, r.nextInt(100)-100,ID.DiamondGem,handler,texture, r.nextInt(14)+5,r.nextInt(14)+5));
				diamondTime = 750;
			}
			else diamondTime--;
			if(rubyTime<=0)
			{
				handler.addObject(new RubyGem(r.nextInt(768)+0,r.nextInt(100)-32,ID.RubyGem,handler,texture,r.nextInt(12)+7));
				handler.addObject(new RubyGem(r.nextInt(768)+0,r.nextInt(100)-32,ID.RubyGem,handler,texture,r.nextInt(12)+7));
				rubyTime = 450;
			}
			else rubyTime--;
		}
		if(game.gameState == game.STATE.LEVEL3 && game.level3pause>=500 && !game.isBossFight3)
		{
			if(pinkGemTime<=0)
			{
				handler.addObject(new PinkGem(r.nextInt(768),-32,ID.PinkGem,handler,texture,r.nextInt(12)+7));
				pinkGemTime = 120;
			}
			else pinkGemTime--;
			if(magmaRockTimer<=0)
			{
				handler.addObject(new MagmaRock(r.nextInt(10)+0,r.nextInt(200)+0,texture,handler,ID.MagmaRock,r.nextInt(10)+5,r.nextInt(8)+3,true));
				handler.addObject(new MagmaRock(r.nextInt(800)+600,r.nextInt(100)+0,texture,handler,ID.MagmaRock,r.nextInt(10)+5,-(r.nextInt(8)+3),false));
				magmaRockTimer = 100;
			}
			else magmaRockTimer--;
			if(goldTimer<=0)
			{		
				handler.addObject(new GoldCoin(r.nextInt(768),-32,texture,handler,ID.GoldCoin));
				goldTimer = r.nextInt(600)+300;
			}
			if(diamondTime<=0)
			{
				handler.addObject(new DiamondGem(r.nextInt(748)+0, r.nextInt(100)-100,ID.DiamondGem,handler,texture, r.nextInt(14)+5,r.nextInt(14)+5));
				diamondTime = 1200;
			}
			else diamondTime--;
			if(healthTime<=0)
			{
				handler.addObject(new HealthPotion(r.nextInt(748)+0, r.nextInt(100)-100,ID.HealthPotion,handler,texture, r.nextInt(12)+5,r.nextInt(14)+5));
				healthTime = 450;
			}
			else healthTime--;
			/* Enemies */
			if(exploderEnemyTime)
			{
				LevelDisplay.exploderTime = 200;
				HUD.EXPLODERHEALTH = 20;
				handler.addObject(new ExploderEnemy(r.nextInt(746),-32,ID.ExploderEnemy,handler,texture,-4));
				exploderEnemyTime = false;
			}
			if(HUD.EXPLODERHEALTH<=0)
			{
				if(exploderTime<=0)
				{
					LevelDisplay.exploderTime = 200;
					HUD.EXPLODERHEALTH = 20;
					handler.addObject(new ExploderEnemy(r.nextInt(746),-32,ID.ExploderEnemy,handler,texture,-4));
					exploderTime = 250;
				}
				else exploderTime--;
			}
			if(rayEnemyTime)
			{
				LevelDisplay.rayEnemyTime = 200;
				HUD.RAYHEALTH = 30;
				handler.addObject(new RayEnemy(r.nextInt(745),-32,ID.RayEnemy,handler,texture));
				rayEnemyTime = false;
			}
			if(HUD.RAYHEALTH<=0)
			{
				if(rayTime <= 0)
				{
					LevelDisplay.rayEnemyTime = 200;
					HUD.RAYHEALTH = 30;
					handler.addObject(new RayEnemy(r.nextInt(745),-32,ID.RayEnemy,handler,texture));
					rayTime = 300;
				}
				else rayTime--;
			}
			if(rocketEnemyTime)
			{
				LevelDisplay.rocketEnemyTime = 200;
				HUD.ROCKETHEALTH = 60;
				handler.addObject(new RocketEnemy(r.nextInt(745),-32,ID.RocketEnemy,handler,texture));
				rocketEnemyTime = false;
			}
			if(HUD.ROCKETHEALTH<=0)
			{
				if(rocketTime<=0)
				{
					LevelDisplay.rocketEnemyTime = 200;
					HUD.ROCKETHEALTH = 60;
					handler.addObject(new RocketEnemy(r.nextInt(745),-32,ID.RocketEnemy,handler,texture));
					rocketTime = 400;
				}
				else rocketTime--;
			}
		}
		if(game.isBossFight3 && !bossMade3)
		{
			bossMade3 = true;
			handler.addObject(new Level3Boss(Game.WIDTH/2-100,-40,ID.Level3Boss,texture,handler,10));
		}
		if(game.isBossFight3)
		{
			if(eggTime<=0)
			{
				Egg egg = new Egg(r.nextInt(764)+0,730,texture,handler,ID.Egg);
				handler.addObject(egg);
				eggTime = 100;
			}
			else eggTime--;
			if(spawnbabyDragon)
			{
				LevelDisplay.dragonTime = 200;
				HUD.BABYDRAGONHEALTH = 15;
				handler.addObject(new BabyDragon((int)Level3Boss.spawnX,(int)Level3Boss.spawnY,ID.BabyDragon,handler,texture));
				spawnbabyDragon = false;
			}
			if(rayBulletTime<=0)
			{
				handler.addObject(new RayBullet(Level3Boss.x+40,Level3Boss.y+200,ID.RayBullet,handler,texture,r.nextInt(8)+4));
				handler.addObject(new RayBullet(Level3Boss.x+200,Level3Boss.y+200,ID.RayBullet,handler,texture,r.nextInt(8)+4));
				rayBulletTime = 200;
			}
			else rayBulletTime--;
			if(pinkGemTime<=0)
			{
				handler.addObject(new PinkGem(r.nextInt(768),-32,ID.PinkGem,handler,texture,r.nextInt(12)+7));
				pinkGemTime = 120;
			}
			else pinkGemTime--;
			if(diamondTime<=0)
			{
				handler.addObject(new DiamondGem(r.nextInt(748)+0, r.nextInt(100)-100,ID.DiamondGem,handler,texture, r.nextInt(14)+5,r.nextInt(14)+5));
				diamondTime = 600;
			}
			else diamondTime--;
		}
		if(game.gameState == game.STATE.LEVEL4 && game.level4pause>=500 && !game.isBossFight4)
		{
			if(tankspawn)
			{
				LevelDisplay.tankTime = 200;
				HUD.TANKHEALTH = 60;
				handler.addObject(new TankEnemy(r.nextInt(700)+20,-32,ID.TankEnemy,handler,texture));
				tankspawn = false;
			}
			if(HUD.TANKHEALTH<=0)
			{
				if(tankTime<=0)
				{
					LevelDisplay.tankTime = 200;
					handler.addObject(new TankEnemy(r.nextInt(700)+20,-32,ID.TankEnemy,handler,texture));
					HUD.TANKHEALTH = 60;
					tankTime = 450;
				}
				else tankTime--;
			}
			if(boomerangEnemyTime)
			{
				LevelDisplay.boomerangTime = 200;
				HUD.BOOMERANGHEALTH = 40;
				handler.addObject(new BoomerangEnemy(r.nextInt(720)+20,-32,ID.BoomerangEnemy,handler,texture,r.nextInt(10)+7));
				boomerangEnemyTime = false;
			}
			if(HUD.BOOMERANGHEALTH<=0)
			{
				if(boomerangTime<=0)
				{
					LevelDisplay.boomerangTime = 200;
					handler.addObject(new BoomerangEnemy(r.nextInt(720)+20,-32,ID.BoomerangEnemy,handler,texture,r.nextInt(8)+4));
					HUD.BOOMERANGHEALTH = 40;
					boomerangTime = 250;
				}
				else boomerangTime--;
			}
			if(pinkGemTime<=0)
			{
				handler.addObject(new PinkGem(r.nextInt(768),-32,ID.PinkGem,handler,texture,r.nextInt(12)+7));
				pinkGemTime = 120;
			}
			else pinkGemTime--;
			if(emeraldTime<= 0)
			{
				handler.addObject(new Emerald(r.nextInt(768)+0,-32,ID.Emerald,handler,texture,r.nextInt(8)+3));
				emeraldTime = 200;
			}
			else emeraldTime--;
			if(purpleTime<= 0)
			{
				handler.addObject(new PurpleEmerald(r.nextInt(768)+0,-32,ID.PurpleEmerald,handler,texture,r.nextInt(8)+3));
				purpleTime = 400;
			}
			else purpleTime--;
			if(wave<=0)
			{
				isWave = true;
				wave = 500;
			}
			else
			{
				isWave = false;
				wave--;
			}
			while(isWave)
			{
				//wave
				int total = 0;		
				gap = r.nextInt(12)+1;
				while(total<=12)
				{
					if(total == gap)
					{
						total++;
						handler.addObject(new Fireball(total*60+26,-32,texture,handler,ID.Fireball,7));
					}
					else
					{
						handler.addObject(new Fireball(total*60+26,-32,texture,handler,ID.Fireball,7));
					}
					total++;
				}
				if(total >= 12)
					isWave = false;
				else 
					isWave = true;
			}
			if(healthTime<=0)
			{
				handler.addObject(new HealthPotion(r.nextInt(748)+0, r.nextInt(100)-100,ID.HealthPotion,handler,texture, r.nextInt(12)+5,r.nextInt(14)+5));
				healthTime = 450;
			}
			else healthTime--;
			if(shieldEnemyTime)
			{
				LevelDisplay.shieldTime = 200;
				HUD.SHIELDENEMYHEALTH = 40;
				handler.addObject(new ShieldEnemy(r.nextInt(720)+20,-32,ID.ShieldEnemy,handler,texture));
				shieldEnemyTime = false;
			}
			if(HUD.SHIELDENEMYHEALTH<=0)
			{
				if(shieldEnemy<=0)
				{
					LevelDisplay.shieldTime = 200;
					handler.addObject(new ShieldEnemy(r.nextInt(720)+20,-32,ID.ShieldEnemy,handler,texture));
					HUD.SHIELDENEMYHEALTH = 40;
					shieldEnemy = 250;
				}
				else shieldEnemy--;
			}
			if(silverTimer<=0)
			{		
				handler.addObject(new SilverCoin(r.nextInt(768),-32,texture,handler,ID.SilverCoin));
				silverTimer = r.nextInt(200)+120;;
			}
			else
				silverTimer--;
			if(magmaRockTimer<=0)
			{
				handler.addObject(new MagmaRock(r.nextInt(10)+0,r.nextInt(200)+0,texture,handler,ID.MagmaRock,r.nextInt(10)+5,r.nextInt(8)+3,true));
				handler.addObject(new MagmaRock(r.nextInt(800)+600,r.nextInt(100)+0,texture,handler,ID.MagmaRock,r.nextInt(10)+5,-(r.nextInt(8)+3),false));
				magmaRockTimer = 100;
			}
			else magmaRockTimer--;
		}
		if(game.isBossFight4 && !bossMade4)
		{
			//make the boss here
			handler.addObject(new Level4Boss(Game.WIDTH/2-100,-40,ID.Level4Boss,texture,handler,10));
			bossMade4 = true;
		}
		if(game.isBossFight4)
		{
			if(crateTime<=0)
			{
				Crate crate = new Crate(r.nextInt(764)+0,730,texture,handler,ID.Crate);
				handler.addObject(crate);
				crateTime = 75;
			}
			else crateTime--;
			if(pinkGemTime<=0)
			{
				handler.addObject(new PinkGem(r.nextInt(768),-32,ID.PinkGem,handler,texture,r.nextInt(12)+7));
				pinkGemTime = 120;
			}
			else pinkGemTime--;
			if(diamondTime<=0)
			{
				handler.addObject(new DiamondGem(r.nextInt(748)+0, r.nextInt(100)-100,ID.DiamondGem,handler,texture, r.nextInt(14)+5,r.nextInt(14)+5));
				diamondTime = 600;
			}
			else diamondTime--;
			if(emeraldTime<= 0)
			{
				handler.addObject(new Emerald(r.nextInt(768)+0,-32,ID.Emerald,handler,texture,r.nextInt(8)+3));
				emeraldTime = 200;
			}
			else emeraldTime--;
			
		}
	}
}
