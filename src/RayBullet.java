import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
/* This is the class for ray bullet for the ray enemy */
public class RayBullet extends GameObject
{
	private double x;
	private double y;
	private int yVel;
	private ID id;
	private ObjectHandler handler;
	private SpriteTextures texture;
	/* The constructor initializes the variables for this object depending on 
	 * what was passed in */
	public RayBullet(double x, double y, ID id,ObjectHandler handler,SpriteTextures texture,int yVel) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
		this.yVel = yVel;
	}
	/* This creates a rectangle around the ray
	 * which is used to check collision with the player */
	public Rectangle getRect() 
	{
		return new Rectangle((int)x,(int)y,15,10);
	}
	/* This method is called 60 times per second and 
	 * it makes the ray fall down */
	public void update() 
	{
		y+=yVel;
		if(y>840)
			handler.removeObject(this);
		handler.addObject(new Trail((int)x,(int)y,ID.RayBullet,Color.RED,15,250,0.07f,handler));
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws the ray into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{
		g.drawImage(texture.rayBullet,(int)x-2,(int)y+248,20,20,null);
	}
}
