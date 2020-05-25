package game.eruption;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/* This class if for the boomerang thrown by the
 * boomerang enemy */
public class Boomerang extends GameObject
{
	public double x,y;
	private ID id;
	private ObjectHandler handler;
	private SpriteTextures texture;
	private int yVel;
	private boolean check = true;
	private ImageIcon icon;
	private Image boomerang;
	/* This is the constructor for the Boomerang
	 * and it requires the same parameter as other game objects */
	public Boomerang(double x, double y, ID id,ObjectHandler handler,SpriteTextures texture,int yVel) 
	{
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
		this.yVel = yVel;
	}
	/* This creates a rectangle around the Boomerang 
	 * which is used to check collision with the objects */
	public Rectangle getRect() 
	{
		return new Rectangle((int)x,(int)y,40,60);
	}
	/* This method is called 60 times per second and 
	 * it makes the Boomerang move up and down */
	public void update() 
	{
		y+=yVel;
		if(y>=700) yVel*= -1;
		if(y<=BoomerangEnemy.y)
		{
			handler.removeObject(this);
			BoomerangEnemy.isThrow = false;
		}
	}
	/* This method is also called 60 times per second 
	 * and it takes the values and draws boomerang into the game
	 * with it's updated locations(x and y) */
	public void render(Graphics g) 
	{	
		icon = new ImageIcon(getClass().getResource("Boomerang.gif"));
		boomerang = icon.getImage();
		g.drawImage(boomerang,(int)x,(int)y,40,60,null);
	}
}
