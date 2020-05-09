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
	private int fireballTimer = 120;
	private int magmaRockTimer = 100;
	private int wave = 500;
	private int enemyFireball = 40;
	private int waterbucketTime = 100;
	private int disappear = 20;
	private int enemyBulletSpeed = 60;
	public static boolean undergroundenemyShow = true;
	public static boolean wizardspawn = true;
	public static boolean throwerspawn = true;
	private int throwerTime = 300;
	private int diamondTime = 1200;
	private int rubyTime = 600;
	private int wizardTime = 200;
	private boolean isWave = false;
	private int gap;
	public boolean bossMade = false;
	public boolean dead = false;
	public static int showIt = 400;
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
		if(game.gameState == game.STATE.LEVEL2 && game.level2pause>=500)
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
					showIt = 400;
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
					wizardTime = 200;
				}
				else wizardTime--;
			}
			if(throwerspawn)
			{
				ThrowerEnemy.split = false;
				HUD.THROWERHEALTH = 50;
				handler.addObject(new ThrowerEnemy(r.nextInt(768)+0,-32,ID.ThrowerEnemy,handler,texture,48,48));
				throwerspawn = false;
			}
			if(ThrowerEnemy.giveInfo)
			{
				LevelDisplay.split1Time = 200;
				LevelDisplay.split2Time = 200;
				ThrowerEnemy.giveInfo = true;
				HUD.SPLITHEALTH1 = 25;
				HUD.SPLITHEALTH2 = 25;
				handler.addObject(new SplitEnemy1(ThrowerEnemy.giveX+10,ThrowerEnemy.giveY,ID.SplitEnemy2,handler,texture,32,32,ThrowerEnemy.giveInfo));
				handler.addObject(new SplitEnemy2(ThrowerEnemy.giveX-10,ThrowerEnemy.giveY,ID.SplitEnemy2,handler,texture,32,32,ThrowerEnemy.giveInfo));

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
					
					handler.addObject(new ThrowerEnemy(r.nextInt(730)+50,r.nextInt(200)+32,ID.ThrowerEnemy,handler,texture,48,48));
					HUD.THROWERHEALTH = 50;
					throwerTime =300;
				}
				else throwerTime--;
			}
			if(fireballTimer<=0)
			{		
				handler.addObject(new Fireball(r.nextInt(740),-50,texture,handler,ID.Fireball,r.nextInt(16)+7));
				
				fireballTimer = 120;
			}
			else
				fireballTimer--;
		}
	}
}
