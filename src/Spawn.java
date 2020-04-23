import java.awt.Graphics;
import java.util.Random;


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
	private int fireballTimer = 100;
	private int wave = 500;
	int gap1;
	private boolean isWave = false;
	public Spawn(ObjectHandler handler, HUD hud,Game game,SpriteTextures texture)
	{
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		this.texture = texture;
	}
	public void update()
	{
		/* Level 1 */
		if(game.gameState == game.STATE.LEVEL1 && game.wait>=500)
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
				handler.addObject(new Fireball(r.nextInt(740),-50,texture,handler,ID.Fireball,r.nextInt(13)+5));
				fireballTimer = 140;
			}
			else
				fireballTimer--;
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
				gap1 = r.nextInt(11)+1;
				while(total<=12)
				{
					if(total == gap1)
					{
						total++;
						handler.addObject(new Fireball(total*60+30,-32,texture,handler,ID.Fireball,6));
					}
					else
					{
						handler.addObject(new Fireball(total*60+30,-32,texture,handler,ID.Fireball,6));
					}
					total++;
				}
				if(total >= 12)
					isWave = false;
				else 
					isWave = true;
			}
		}
	}

}
