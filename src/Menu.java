import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/* This class is responsible for displaying the Menu 
 * and it handles the Death Screen and it also 
 * has a MouseAdapter to add mouse input for mouse input */
public class Menu extends MouseAdapter
{
	private Game game;
	private ObjectHandler handler;
	private Spawn spawner;
	private SpriteTextures texture;
	/* This constructor has a game instance and handler instance
	 * for this class so we can check the game State or
	 * to clear all the objects in the handler and 
	 * so we can display images */
	public Menu(Game game, ObjectHandler handler,SpriteTextures texture)
	{
		this.game = game;
		this.handler = handler;
		this.texture = texture;
	}
	/* This method is used to update but is not in use yet */
	public void update(){}
	/* his is the mousePressed method that checks for "buttons"(rectangle) 
	 * and changes the game state accordingly and it also clears the objects
	 * or exits if you press the exit button */
	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		if(contains(mx, my,285,300,200,100) && game.gameState == game.STATE.MENU)
		{
			// PLAY BUTTON
			game.gameState = game.STATE.SELECTLEVEL;
		}
		else if(contains(mx,my,game.WIDTH/2-115,game.HEIGHT/2+50,200,100) && game.gameState == game.STATE.MENU)
		{
			//HELP BUTTON
			game.gameState = game.STATE.HELP;
		}
		else if(contains(mx,my,game.WIDTH/2-120,game.HEIGHT/2+250,250,100) && game.gameState == game.STATE.HELP)
		{
			// BACK BUTTON
			game.gameState = game.STATE.MENU;
		}
		else if(contains(mx,my,game.WIDTH/2-120,game.HEIGHT/2+250,250,100) && game.gameState == game.STATE.SELECTLEVEL)
		{
			// BACK BUTTON IN LEVEL CHOOSE
			game.gameState = game.STATE.MENU;
		}
		else if(contains(mx,my,game.WIDTH/2-115,game.HEIGHT/2+200,200,100) && game.gameState == game.STATE.MENU)
		{
			// EXIT BUTTON
			System.exit(1);
		}
		else if(contains(mx,my,50,150,121,121) && game.gameState == game.STATE.SELECTLEVEL)
		{
			//LEVEL 1
			game.gameState = game.STATE.LEVEL1;
		}
		else if(contains(mx,my,game.WIDTH/2-370,game.HEIGHT/2+250,740,100) && game.gameState == game.STATE.DEADSCREEN)
		{
			//BACK TO MENU BUTTON
			game.gameState = game.STATE.MENU;
			handler.clearAll();
		}		
	}
	/* This is the mouseReleased method which is not in use right now */
	public void mouseReleased(MouseEvent e) {}
	/* This method returns a boolean and it checks if the
	 * mouse cursor is inside the rectangle is which case
	 * it returns true or else it returns false */
	public boolean contains(int mx, int my,int x, int y,int width,int height)
	{
		if(mx >= x && mx <= x+width)
		{
			if(my >= y && my <= y+height)
			{
				return true;
			}
			else return false;
		}
		else return false;
	}
	/* This method renders 60 times per second
	 * as it draws the menu screen and the buttons
	 * It also draws the death screen 
	 * depending on the game State */
	public void render(Graphics g)
	{
		if(game.gameState == game.STATE.MENU)
		{
			g.setColor(Color.RED);
			Font font = new Font("TimesNewRoman",Font.BOLD,120);
			g.setFont(font);
			g.drawString("Eruptions",90,120);
			
			g.setColor(Color.RED);
			g.drawRect(game.WIDTH/2-115,game.HEIGHT/2-100,200,100);
			
			Font f1 = new Font("TimesNewRoman",Font.BOLD,60);
			g.setFont(f1);
			g.drawString("PLAY", game.WIDTH/2-93, game.HEIGHT/2-30);
			
			g.setColor(Color.RED);
			g.drawRect(game.WIDTH/2-115,game.HEIGHT/2+50,200,100);
			Font helpfont = new Font("TimesNewRoman",Font.BOLD,60);
			g.setFont(helpfont);
			g.drawString("HELP", game.WIDTH/2-93, game.HEIGHT/2+120);
			
			
			g.setColor(Color.RED);
			g.drawRect(game.WIDTH/2-115,game.HEIGHT/2+200,200,100);
			
			Font f2 = new Font("TimesNewRoman",Font.BOLD,60);
			g.setFont(f2);
			g.drawString("EXIT", game.WIDTH/2-85, game.HEIGHT/2+275);
		}
		else if(game.gameState == game.STATE.HELP)
		{
			g.setColor(Color.BLACK);
			g.drawRect(game.WIDTH/2-120,game.HEIGHT/2+250,250,100);
			Font f5 = new Font("TimesNewRoman",Font.BOLD,88);
			g.setFont(f5);
			g.drawString("BACK",game.WIDTH/2-118,game.HEIGHT/2+330);
			
			Font helpFont = new Font("TimesNewRoman",Font.BOLD,60);
			g.setFont(helpFont);
			g.drawImage(texture.rightArrow,50,200,100,100,null);
			g.drawString("Move Right ",150,270);
			g.drawImage(texture.leftArrow,50,350,100,100,null);
			g.drawString("Move Left ",150,420);
			g.drawImage(texture.space,50,500,100,100,null);
			g.drawString("Shoot Bullets ",160,570);
		}
		else if(game.gameState == game.STATE.SELECTLEVEL)
		{
			g.setColor(Color.WHITE);
			Font lev = new Font("TimesNewRoman", Font.BOLD,120);
			g.setFont(lev);
			g.drawString("Select Level",45,100);
			g.drawImage(game.level1,50,150,120,120,null);
	        ((Graphics2D)g).setStroke(new BasicStroke(3));
			g.drawRect(50,150,121,121);
			
			Font levChoose = new Font("TimesNewRoman",Font.BOLD,88);
			g.setFont(levChoose);
			g.drawString("BACK",game.WIDTH/2-118,game.HEIGHT/2+330);
			g.drawRect(game.WIDTH/2-120,game.HEIGHT/2+250,250,100);
			
			g.setColor(Color.CYAN);
			Font difLevDisplay = new Font("TimesNewRoman",Font.BOLD,30);
			g.setFont(difLevDisplay);
			g.drawString("Level 1",55,300);
			
		}
		else if(game.gameState == game.STATE.DEADSCREEN)
		{
			g.setColor(Color.YELLOW);
			Font f3 = new Font("TimesNewRoman", Font.BOLD,120);
			g.setFont(f3);
			g.drawString("YOU LOST!",Game.WIDTH/2-335,120);
			
			
			g.drawRect(game.WIDTH/2-370,game.HEIGHT/2+250,740,100);
			Font returnTo = new Font("TimesNewRoman",Font.BOLD,88);
			g.setFont(returnTo);
			g.drawString("Return To",game.WIDTH/2-370,game.HEIGHT/2+330);
			g.drawString("MENU",game.WIDTH/2+100,game.HEIGHT/2+330);
		}
	}
}
