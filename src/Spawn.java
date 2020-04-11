import java.awt.Graphics;
import java.util.Random;

public class Spawn 
{
	private ObjectHandler handler;
	private Game game;
	private HUD hud;
	private Random r = new Random();
	private SpriteTextures texture;
	private int timer = 200;
	private int fireballTimer = 120;
	private int wave = 500;
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
			timer = 200;
		}
		else
			timer--;
		if(fireballTimer<=0)
		{		
			handler.addObject(new Fireball(r.nextInt(740),-32,texture,handler,ID.Fireball));
			fireballTimer = 60;
		}
		else
			fireballTimer--;
		if(wave<=0)
		{
			handler.addObject(new Fireball(0,-32,texture,handler,ID.Fireball));
			handler.addObject(new Fireball(60,-32,texture,handler,ID.Fireball));
			handler.addObject(new Fireball(120,-32,texture,handler,ID.Fireball));
			handler.addObject(new Fireball(180,-32,texture,handler,ID.Fireball));
			handler.addObject(new Fireball(240,-32,texture,handler,ID.Fireball));
			handler.addObject(new Fireball(300,-32,texture,handler,ID.Fireball));
			handler.addObject(new Fireball(360,-32,texture,handler,ID.Fireball));
			handler.addObject(new Fireball(480,-32,texture,handler,ID.Fireball));
			handler.addObject(new Fireball(540,-32,texture,handler,ID.Fireball));
			handler.addObject(new Fireball(600,-32,texture,handler,ID.Fireball));
			handler.addObject(new Fireball(660,-32,texture,handler,ID.Fireball));
			handler.addObject(new Fireball(720,-32,texture,handler,ID.Fireball));
			wave = 500;
		}
		else 
			wave--;
	}

}
