import java.awt.Graphics;
import java.util.Random;

public class Spawn 
{
	private ObjectHandler handler;
	private Game game;
	private HUD hud;
	private Random r = new Random();
	private SpriteTextures texture;
	private int timer = 100;
	private int fireballTimer = 160;
	private int wave = 500;
	private int gap = r.nextInt(12);
	public Spawn(ObjectHandler handler, HUD hud,Game game,SpriteTextures texture)
	{
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		this.texture = texture;
	}
	public void update()
	{
		if(timer<=0)
		{		
			handler.addObject(new Coin(r.nextInt(768),-32,texture,handler,ID.Coin));
			timer = 100;
		}
		else
			timer--;
		if(fireballTimer<=0)
		{		
			handler.addObject(new Fireball(r.nextInt(740),-32,texture,handler,ID.Fireball,r.nextInt(13)+5));
			fireballTimer = 60;
		}
		else
			fireballTimer--;
		if(wave<=0)
		{
			int count = 0;
			while(count<12)
			{
				int gap = r.nextInt(12);
				if(gap == count)
					count++;
				handler.addObject(new Fireball(count*60,-32,texture,handler,ID.Fireball,6));
				count++;
			}
			wave = 500;
		}
		else 
			wave--;
	}

}
