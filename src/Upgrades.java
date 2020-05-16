import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/* This is the upgrade system class during levels
 * where you can buy certain items with your score */
public class Upgrades extends MouseAdapter
{
	private ObjectHandler handler;
	private SpriteTextures texture;
	public Color skyBlue = new Color(0,191,255);
	public Color springGreen = new Color(0,255,127);
	private boolean backButton = false;
	public int redTime = 2;
	public boolean SquareRED = false;
	public boolean firstRow1SquareClicked = false;
	public boolean secondRow1SquareClicked = false;
	public boolean thirdRow1SquareClicked = false;
	public boolean fourthRow1SquareClicked = false;
	public boolean firstRow2SquareClicked = false;
	public boolean secondRow2SquareClicked = false;
	public boolean thirdRow2SquareClicked = false;
	public boolean fourthRow2SquareClicked = false;
	public boolean firstRow3SquareClicked = false;
	public boolean secondRow3SquareClicked = false;
	public boolean thirdRow3SquareClicked = false;
	public boolean fourthRow3SquareClicked = false;
	public int box1Row1Cost = 1000;
	public int box2Row1Cost = 2000;
	public int box3Row1Cost = 500;
	public int box4Row1Cost = 1000;
	public int box1Row2Cost = 500;
	public int box2Row2Cost = 800;
	public int box3Row2Cost = 1000;
	public int box4Row2Cost = 1300;
	public int box1Row3Cost = 500;
	public int box2Row3Cost = 1000;
	public int box3Row3Cost = 1200;
	public int box4Row3Cost = 2000;
	/* This constructor requires some parameters that render 
	 * objects from sprite sheet and the handler to make changes*/
	public Upgrades(ObjectHandler handler, SpriteTextures texture)
	{
		this.handler = handler;
		this.texture = texture;
	}
	/* This method is for adding cool effects 
	 * when the mouse pointer is above a button */
	public void mouseMoved(MouseEvent e)
	{
		int mx = e.getX(); 
		int my = e.getY();
		if(contains(mx,my,330, 720, 150, 50) && Game.gameState == Game.STATE.UPGRADES) backButton = true;
		else backButton = false;
	}
	/* This method is called around 60 times per second
	 * and it renders anything in Upgrades */
	public void render(Graphics g)
	{
		g.setColor(Color.BLACK);
		Font shopFont = new Font("Superpower Synonym",Font.BOLD,160);
		g.setFont(shopFont);
		g.drawString("UPGRADES",60,125);
		Font backButtonFont = new Font("Arial",Font.BOLD,48);
		g.setFont(backButtonFont);
		g.drawString("Score: "+HUD.SCORE, 5, 180);
		g.setColor(Color.BLACK);
		if(backButton) g.fillRect(330, 720, 150, 50);
		else g.drawRect(330, 720, 150, 50);
		if(backButton) g.setColor(Color.WHITE);
		else g.setColor(Color.BLACK);
		g.drawString("BACK", 335, 760);
		
		g.setColor(Color.BLACK);
		Font upgradeName = new Font("HEALTH",Font.BOLD,48);
		g.setFont(upgradeName);
		g.setColor(Color.GRAY);
		//1st row
		g.fillRect(150, 200, 110, 110);	
		g.drawImage(texture.healthLogo,150,200,110,110,null);
		g.fillRect(320, 200, 110, 110);
		g.drawImage(texture.healthRefillLogo,320,200,110,110,null);
		g.fillRect(490, 200, 110, 110);
		//put picture of upgrade
		g.fillRect(660, 200, 110, 110);
		//put picture of upgrade
		//2nd row
		g.fillRect(150, 380, 110, 110);
		//put picture of upgrade
		g.fillRect(320, 380, 110, 110);
		//put picture of upgrade
		g.fillRect(490, 380, 110, 110);
		//put picture of upgrade
		g.fillRect(660, 380, 110, 110);
		//put picture of upgrade
		//3rd row
		g.fillRect(150, 560, 110, 110);
		//put picture of upgrade
		g.fillRect(320, 560, 110, 110);
		//put picture of upgrade
		g.fillRect(490, 560, 110, 110);
		//put picture of upgrade
		g.fillRect(660, 560, 110, 110);
		//put picture of upgrade
	
		//set stroke
        ((Graphics2D)g).setStroke(new BasicStroke(4));
		//1st row outline
        //1st square: Row 1
        if(firstRow1SquareClicked) 
        {
       		if(SquareRED)
       			g.setColor(Color.RED);
       		else
       			g.setColor(springGreen); 
       	}
        else g.setColor(skyBlue);
		g.drawRect(150, 200, 111, 111);
		if(secondRow1SquareClicked) g.setColor(Color.BLACK);
		else g.setColor(Color.WHITE);
		g.setColor(Color.YELLOW);
		Font itemFont = new Font("Roboto",Font.BOLD,14);
		g.setFont(itemFont);
		g.drawString("Increase Health",150,326);
		g.drawString("by 30", 180, 340);
		g.drawString("Cost: "+box1Row1Cost,163, 354);
		//2nd square: Row 1
		if(secondRow1SquareClicked) 
        {
       		if(SquareRED)
       			g.setColor(Color.RED);
       		else
       			g.setColor(springGreen); 
       	}
        else g.setColor(skyBlue);
		g.drawRect(320, 200, 111, 111);
		g.setColor(Color.YELLOW);
		g.drawString("Refill Health",332,328);
		g.drawString("Cost: "+box2Row1Cost,338, 345);
		//3rd square: Row 1
		if(thirdRow1SquareClicked) 
        {
       		if(SquareRED)
       			g.setColor(Color.RED);
       		else
       			g.setColor(springGreen); 
       	}
        else g.setColor(skyBlue);
		g.drawRect(490, 200, 111, 111);
		//put text under upgrade here
		//4th square: Row 
		if(fourthRow1SquareClicked) 
        {
       		if(SquareRED)
       			g.setColor(Color.RED);
       		else
       			g.setColor(springGreen); 
       	}
        else g.setColor(skyBlue);
		g.drawRect(660, 200, 111, 111);
		//put text under upgrade here
		//2nd row outline
		//1st Square: Row 2
		if(firstRow2SquareClicked) 
        {
       		if(SquareRED)
       			g.setColor(Color.RED);
       		else
       			g.setColor(springGreen); 
       	}
        else g.setColor(skyBlue);
		g.drawRect(150, 380, 111, 111);
		//put text under upgrade here
		//2nd Square: Row 2
		if(secondRow2SquareClicked) 
        {
       		if(SquareRED)
       			g.setColor(Color.RED);
       		else
       			g.setColor(springGreen); 
       	}
        else g.setColor(skyBlue);
		g.drawRect(320, 380, 111, 111);
		//put text under upgrade here
		//3rd Square: Row 2
		if(thirdRow2SquareClicked) 
        {
       		if(SquareRED)
       			g.setColor(Color.RED);
       		else
       			g.setColor(springGreen); 
       	}
        else g.setColor(skyBlue);
		g.drawRect(490, 380, 111, 111);
		//put text under upgrade here
		//4th Square: Row 2
		if(fourthRow2SquareClicked) 
        {
       		if(SquareRED)
       			g.setColor(Color.RED);
       		else
       			g.setColor(springGreen); 
       	}
        else g.setColor(skyBlue);
		g.drawRect(660, 380, 111, 111);
		//put text under upgrade here
		//3rd row outline
		//1st Square: Row 3
		if(firstRow3SquareClicked) 
        {
       		if(SquareRED)
       			g.setColor(Color.RED);
       		else
       			g.setColor(springGreen); 
       	}
        else g.setColor(skyBlue);
		g.drawRect(150, 560, 111, 111);
		//put text under upgrade here
		//2nd Square: Row 3
		if(secondRow3SquareClicked) 
        {
       		if(SquareRED)
       			g.setColor(Color.RED);
       		else
       			g.setColor(springGreen); 
       	}
        else g.setColor(skyBlue);
		g.drawRect(320, 560, 111, 111);
		//put text under upgrade here
		//3rd Square: Row 3
		if(thirdRow3SquareClicked) 
        {
       		if(SquareRED)
       			g.setColor(Color.RED);
       		else
       			g.setColor(springGreen); 
       	}
        else g.setColor(skyBlue);
		g.drawRect(490, 560, 111, 111);
		//put text under upgrade here
		//4th Square: Row 3
		if(fourthRow3SquareClicked) 
        {
       		if(SquareRED)
       			g.setColor(Color.RED);
       		else
       			g.setColor(springGreen); 
       	}
        else g.setColor(skyBlue);
		g.drawRect(660, 560, 111, 111);
		//put text under upgrade here
	}
	/* This method is part of MouseListener and this
	 * is required to make sure you buy items in the shop */
	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		if(contains(mx,my,330, 720, 150, 50) && Game.gameState == Game.STATE.UPGRADES)
		{
			if(Game.stateholder.equalsIgnoreCase("Level1")) 
				Game.gameState = Game.STATE.LEVEL1;
			if(Game.stateholder.equalsIgnoreCase("Level2"))
				Game.gameState = Game.STATE.LEVEL2;
			if(Game.stateholder.equalsIgnoreCase("Level3"))
				Game.gameState = Game.STATE.LEVEL3;
			if(Game.stateholder.equalsIgnoreCase("Level4"))
				Game.gameState = Game.STATE.LEVEL4;
		}
		if(contains(mx,my,150, 200, 110, 110) && !firstRow1SquareClicked)
		{
			firstRow1SquareClicked = true;
			if(HUD.SCORE >= box1Row1Cost)
			{
				if(HUD.HEALTH<=70)
				{
					HUD.HEALTH+=30;
					HUD.SCORE-=box1Row1Cost;
					box1Row1Cost+=500;
				}
				else if(HUD.HEALTH>70)
					SquareRED = true;
			}
			else
				SquareRED = true;
		}
		if(contains(mx,my,320, 200, 111, 111) && !secondRow1SquareClicked)
		{
			secondRow1SquareClicked = true;
			if(HUD.SCORE>=box2Row1Cost)
			{
				HUD.HEALTH = 100;
				HUD.SCORE-=box2Row1Cost;
				box2Row1Cost+=500;
			}
			else SquareRED = true;
		}
		if(contains(mx,my,490, 200, 111, 111) && !thirdRow1SquareClicked)
		{
			thirdRow1SquareClicked = true;
			if(HUD.SCORE>=box3Row1Cost)
			{
				//Here is where you put the impact of the upgrade
				HUD.SCORE-=box3Row1Cost;
				box3Row1Cost+=500;
			}
			else SquareRED = true;
		}
		if(contains(mx,my,660, 200, 111, 111) && !fourthRow1SquareClicked)
		{
			fourthRow1SquareClicked = true;
			if(HUD.SCORE>=box4Row1Cost)
			{
				//Here is where you put the impact of the upgrade
				HUD.SCORE-=box4Row1Cost;
				box4Row1Cost+=500;
			}
			else SquareRED = true;
		}
		if(contains(mx,my,150, 380, 111, 111) && !firstRow2SquareClicked)
		{
			firstRow2SquareClicked = true;
			if(HUD.SCORE>=box1Row2Cost)
			{
				//Here is where you put the impact of the upgrade
				HUD.SCORE-=box1Row2Cost;
				box1Row2Cost+=500;
			}
			else SquareRED = true;
		}
		if(contains(mx,my,320, 380, 111, 111) && !secondRow2SquareClicked)
		{
			secondRow2SquareClicked = true;
			if(HUD.SCORE>=box2Row2Cost)
			{
				//Here is where you put the impact of the upgrade
				HUD.SCORE-=box2Row2Cost;
				box2Row2Cost+=500;
			}
			else SquareRED = true;
		}
		if(contains(mx,my,490, 380, 111, 111) && !thirdRow2SquareClicked)
		{
			thirdRow2SquareClicked = true;
			if(HUD.SCORE>=box3Row2Cost)
			{
				//Here is where you put the impact of the upgrade
				HUD.SCORE-=box3Row2Cost;
				box3Row2Cost+=500;
			}
			else SquareRED = true;
		}
		if(contains(mx,my,660, 380, 111, 111) && !fourthRow2SquareClicked)
		{
			fourthRow2SquareClicked = true;
			if(HUD.SCORE>=box4Row2Cost)
			{
				//Here is where you put the impact of the upgrade
				HUD.SCORE-=box4Row2Cost;
				box4Row2Cost+=500;
			}
			else SquareRED = true;
		}
		if(contains(mx,my,150, 560, 111, 111) && !firstRow3SquareClicked)
		{
			firstRow3SquareClicked = true;
			if(HUD.SCORE>=box1Row3Cost)
			{
				//Here is where you put the impact of the upgrade
				HUD.SCORE-=box1Row3Cost;
				box1Row3Cost+=500;
			}
			else SquareRED = true;
		}
		if(contains(mx,my,320, 560, 111, 111) && !secondRow3SquareClicked)
		{
			secondRow3SquareClicked = true;
			if(HUD.SCORE>=box2Row3Cost)
			{
				//Here is where you put the impact of the upgrade
				HUD.SCORE-=box2Row3Cost;
				box2Row3Cost+=500;
			}
			else SquareRED = true;
		}
		if(contains(mx,my,490, 560, 111, 111) && !thirdRow3SquareClicked)
		{
			thirdRow3SquareClicked = true;
			if(HUD.SCORE>=box3Row3Cost)
			{
				//Here is where you put the impact of the upgrade
				HUD.SCORE-=box3Row3Cost;
				box3Row3Cost+=500;
			}
			else SquareRED = true;
		}
		if(contains(mx,my,660, 560, 111, 111) && !fourthRow3SquareClicked)
		{
			fourthRow3SquareClicked = true;
			if(HUD.SCORE>=box4Row3Cost)
			{
				//Here is where you put the impact of the upgrade
				HUD.SCORE-=box4Row3Cost;
				box4Row3Cost+=500;
			}
			else SquareRED = true;
		}
		
	}
	/* This method is part of MouseListener and this
	 * is required to make sure you buy items in the shop 
	 * to check if you released or not */
	public void mouseReleased(MouseEvent e)
	{
		if(firstRow1SquareClicked)
		{
			firstRow1SquareClicked = false;		
			if(SquareRED)
				SquareRED = false;
		}
		if(secondRow1SquareClicked)
		{
			secondRow1SquareClicked = false;
			if(SquareRED)
				SquareRED = false;
		}
		if(thirdRow1SquareClicked)
		{
			thirdRow1SquareClicked = false;
			if(SquareRED)
				SquareRED = false;
		}
		if(fourthRow1SquareClicked)
		{
			fourthRow1SquareClicked = false;
			if(SquareRED)
				SquareRED = false;
		}
		if(firstRow2SquareClicked)
		{
			firstRow2SquareClicked = false;
			if(SquareRED)
				SquareRED = false;
		}
		if(secondRow2SquareClicked)
		{
			secondRow2SquareClicked = false;
			if(SquareRED)
				SquareRED = false;
		}
		if(thirdRow2SquareClicked)
		{
			thirdRow2SquareClicked = false;
			if(SquareRED)
				SquareRED = false;
		}
		if(fourthRow2SquareClicked)
		{
			fourthRow2SquareClicked = false;
			if(SquareRED)
				SquareRED = false;
		}
		if(firstRow3SquareClicked)
		{
			firstRow3SquareClicked = false;
			if(SquareRED)
				SquareRED = false;
		}
		if(secondRow3SquareClicked)
		{
			secondRow3SquareClicked = false;
			if(SquareRED)
				SquareRED = false;
		}
		if(thirdRow3SquareClicked)
		{
			thirdRow3SquareClicked = false;
			if(SquareRED)
				SquareRED = false;
		}
		if(fourthRow3SquareClicked)
		{
			fourthRow3SquareClicked = false;
			if(SquareRED)
				SquareRED = false;
		}
	}
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
}
