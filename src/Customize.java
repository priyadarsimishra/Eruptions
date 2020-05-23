import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
/* This is the class for the Customize section of the game */
public class Customize extends MouseAdapter
{
	private Game game;
	private ObjectHandler handler;
	private SpriteTextures texture;
	
	private Color limeGreen = new Color(124,252,0);
	private int [] skins = new int[4];
	private int [] pistolSkin = new int[4];
	private int skinIndex;
	
	private Image defaultCharacter;
	private ImageIcon defaultIcon;
	private Image batman;
	private ImageIcon batmanIcon;
	private Image spiderman;
	private ImageIcon spidermanIcon;
	private Image mickeyMouse;
	private ImageIcon mickeyMouseIcon;
	private ImageIcon rightIcon;
	private ImageIcon leftIcon;

	private Image defaultPistol;
	private Image defaultShotgun;
	private Image defaultSniper;
	private Image defaultDualPistol;
	private Image right;
	private Image left;
	
	private Image gunSkin1;
	private Image gunSkin2;
	private Image ShotgunSkin1;
	private Image ShotgunSkin2;
	private Image SniperSkin1;
	private Image SniperSkin2;
	private Image DualPistolSkin1;
	private Image DualPistolSkin2;

	private ImageIcon defaultPistolIcon;
	private ImageIcon defaultShotgunIcon;
	private ImageIcon defaultSniperIcon;
	private ImageIcon defaultDualPistolIcon;
	private ImageIcon gunSkin1Icon;
	private ImageIcon gunSkin2Icon;
	private ImageIcon ShotgunSkin1Icon;
	private ImageIcon ShotgunSkin2Icon;
	private ImageIcon SniperSkin1Icon;
	private ImageIcon SniperSkin2Icon;
	private ImageIcon DualPistolSkin1Icon;
	private ImageIcon DualPistolSkin2Icon;

	public boolean defaultBoxClicked;
	public boolean Skin1BoxClicked;
	public boolean Skin2BoxClicked;
	public boolean Skin3BoxClicked;
	
	public boolean batManUnlocked = false;
	public boolean spiderManUnlocked = false;
	public boolean mickeyMouseUnlocked = false;
	
	
	public boolean RedRow2 = false;
	public boolean RedRow3 = false;

	public boolean gunSkin1Row1Clicked = true;
	public boolean gunSkin2Row1Clicked = false;
	public boolean gunSkin3Row1Clicked = false;
	public boolean gunSkin1Row2Clicked = true;
	public boolean gunSkin2Row2Clicked = false;
	public boolean gunSkin3Row2Clicked = false;
	public boolean gunSkin1Row3Clicked = true;
	public boolean gunSkin2Row3Clicked = false;
	public boolean gunSkin3Row3Clicked = false;
	public boolean gunSkin1Row4Clicked = true;
	public boolean gunSkin2Row4Clicked = false;
	public boolean gunSkin3Row4Clicked = false;
	
	public int Skin1Cost = 500;

	public boolean isSkin1;
	public boolean isSkin2;
	public boolean isSkin3;
	public boolean defaultSkin;
	public boolean all = true;
	/* This constructor requires some parameters that render 
	 * objects from sprite sheet and the handler to make changes*/
	public Customize(ObjectHandler handler, SpriteTextures texture, Game game)
	{
		this.handler = handler;
		this.texture = texture;
		this.game = game;
		skins[0] = 0;
		skins[1] = 1;
		skins[2] = 2;
		skins[3] = 3;
		
	}
	/* This method is called around 60 times per second
	 * and it renders anything in Upgrades */
	public void render(Graphics g)
	{
		//Images
		defaultIcon = new ImageIcon(getClass().getResource("DefaultCharacter.png"));
		defaultCharacter = defaultIcon.getImage();
		batmanIcon = new ImageIcon(getClass().getResource("Batman.png"));
		batman = batmanIcon.getImage();
		spidermanIcon = new ImageIcon(getClass().getResource("Spiderman.png"));
		spiderman = spidermanIcon.getImage();
		mickeyMouseIcon = new ImageIcon(getClass().getResource("MickeyMouse.png"));
		mickeyMouse = mickeyMouseIcon.getImage();
		
		defaultPistolIcon = new ImageIcon(getClass().getResource("DefualtPistol.png"));
		defaultShotgunIcon = new ImageIcon(getClass().getResource("DefaultShotgun.png"));
		defaultSniperIcon = new ImageIcon(getClass().getResource("DefaultSniper.png"));
		defaultDualPistolIcon = new ImageIcon(getClass().getResource("DefaultDualPistols.png"));
		gunSkin1Icon = new ImageIcon(getClass().getResource("GunSkin1Logo.png"));
		gunSkin2Icon = new ImageIcon(getClass().getResource("gunSkin2Logo.png"));
		ShotgunSkin1Icon = new ImageIcon(getClass().getResource("ShotgunSkin1Logo.png"));
		ShotgunSkin2Icon = new ImageIcon(getClass().getResource("ShotgunSkin2Logo.png"));
		SniperSkin1Icon = new ImageIcon(getClass().getResource("SniperSkin1Logo.png"));
		SniperSkin2Icon = new ImageIcon(getClass().getResource("SniperSkin2Logo.png"));
		DualPistolSkin1Icon = new ImageIcon(getClass().getResource("Skin2DualPistolLogo.png"));
		DualPistolSkin2Icon = new ImageIcon(getClass().getResource("Skin3DualPistolLogo.png"));
		rightIcon = new ImageIcon(getClass().getResource("Right.png"));
		leftIcon = new ImageIcon(getClass().getResource("Left.png"));
		
		defaultPistol = defaultPistolIcon.getImage();
		defaultShotgun = defaultShotgunIcon.getImage();
		defaultSniper = defaultSniperIcon.getImage();
		defaultDualPistol = defaultDualPistolIcon.getImage();
		gunSkin1 = gunSkin1Icon.getImage();
		gunSkin2 = gunSkin2Icon.getImage();
		ShotgunSkin1 = ShotgunSkin1Icon.getImage();
		ShotgunSkin2 = ShotgunSkin2Icon.getImage();
		SniperSkin1 = SniperSkin1Icon.getImage();
		SniperSkin2 = SniperSkin2Icon.getImage(); 
		DualPistolSkin1 = DualPistolSkin1Icon.getImage();
		DualPistolSkin2 = DualPistolSkin2Icon.getImage();
		right = rightIcon.getImage();
		left = leftIcon.getImage();
		//Images
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(60,220,200,280);
		Font nameFont = new Font("Roboto",Font.BOLD,48);
		g.setFont(nameFont);
		
		if(game.isLevel2Complete) batManUnlocked = true;
		if(game.isLevel3Complete) spiderManUnlocked = true;
		if(game.isLevel4Complete) mickeyMouseUnlocked = true;
		
		if(skinIndex == skins[0])
		{
			g.drawImage(defaultCharacter,60,180,265,315,null);
			if(all) g.setColor(Color.GREEN);
			else g.setColor(Color.YELLOW);
			g.drawString("JOE", 115,550);
			Font unlock = new Font("Roboto",Font.BOLD,30);
			g.setFont(unlock);
			g.setColor(Color.GREEN);
			g.drawString("UNLOCKED!", 70, 620);
		}
		else if(skinIndex == skins[1]) 
		{
			g.drawImage(batman,60,200,245,300,null);
			if(all) g.setColor(Color.GREEN);
			else g.setColor(Color.YELLOW);
			g.drawString("BATMAN", 45,550);
			Font unlock = new Font("Roboto",Font.BOLD,30);
			g.setFont(unlock);
			if(batManUnlocked)
			{
				g.setColor(Color.GREEN);
				g.drawString("UNLOCKED!", 70, 620);
			}
			else
			{
				g.setColor(Color.RED);
				g.drawString("Unlocked", 80, 600);
				g.drawString("AFTER", 100, 630);
				g.drawString("LEVEL 2", 90, 660);
			}
		}
		else if(skinIndex == skins[2])
		{
			g.drawImage(spiderman,55,190,280,310,null);
			if(all) g.setColor(Color.GREEN);
			else g.setColor(Color.YELLOW);
			g.drawString("SPIDERMAN", 15,550);
			Font unlock = new Font("Roboto",Font.BOLD,30);
			g.setFont(unlock);
			if(spiderManUnlocked)
			{
				g.setColor(Color.GREEN);
				g.drawString("UNLOCKED!", 70, 620);
			}
			else
			{
				g.setColor(Color.RED);
				g.drawString("Unlocked", 80, 600);
				g.drawString("AFTER", 100, 630);
				g.drawString("LEVEL 3", 90, 660);
			}
		}
		else if(skinIndex == skins[3])
		{
			g.drawImage(mickeyMouse,55,180,280,320,null);
			if(all) g.setColor(Color.GREEN);
			else g.setColor(Color.YELLOW);
			Font nameFont2 = new Font("Roboto",Font.BOLD,40);
			g.setFont(nameFont2);
			g.drawString("MICKEY MOUSE", 0,550);
			
			Font unlock = new Font("Roboto",Font.BOLD,30);
			g.setFont(unlock);
			
			if(mickeyMouseUnlocked)
			{
				g.setColor(Color.GREEN);
				g.drawString("UNLOCKED!", 70, 620);
			}
			else
			{
				g.setColor(Color.RED);
				g.drawString("Unlocked", 80, 600);
				g.drawString("AFTER", 100, 630);
				g.drawString("LEVEL 4", 90, 660);
			}
		}
		
        ((Graphics2D)g).setStroke(new BasicStroke(6));
        if(all) g.setColor(limeGreen);
        else g.setColor(Color.YELLOW);
		g.drawRect(60,220,201,281);
		//arrows to move character
		g.setColor(Color.WHITE);
		g.drawImage(left,10,330,40,40,null);
		g.drawImage(right,270,330,40,40,null);
		//arrows to move character
		Font font = new Font("Roboto",Font.BOLD,30);
		g.setFont(font);
		g.setColor(Color.CYAN);
		g.drawString("PLAYER SKINS", 53, 200);
		g.drawString("GUN SKINS", 520, 160);
		
		
		if(RedRow2) g.setColor(Color.RED);
		else g.setColor(Color.CYAN);
		Font unlockGunSkinFont = new Font("Roboto",Font.BOLD,15);
		g.setFont(unlockGunSkinFont);
		if(game.isLevel2Complete)
		{
			g.setColor(Color.GREEN);
			g.drawString("UNLOCKED", 552, 210);
		}
		else
		{
			g.drawString("UNLOCKED", 550, 180);
			g.drawString("IN",583, 195);
			g.drawString("LEVEL 2", 560, 210);
		}
		if(RedRow3) g.setColor(Color.RED);
		else g.setColor(Color.CYAN);
		if(game.isLevel3Complete)
		{
			g.setColor(Color.GREEN);
			g.drawString("UNLOCKED", 693, 210);
		}
		else
		{
			g.drawString("UNLOCKED", 690, 180);
			g.drawString("IN",725, 195);
			g.drawString("LEVEL 3", 705, 210);	
		}
	
		g.setColor(Color.CYAN);
		Font detailsfont = new Font("Roboto",Font.BOLD,24);
		g.setFont(detailsfont);
		g.drawString("PISTOL",315,280);
		g.drawString("SHOTGUN",280,420);
		g.drawString("SNIPER",315,560);
		g.drawString("DUAL",323,680);
		g.drawString("PISTOL",312,705);
		
		if(gunSkin1Row1Clicked) g.setColor(limeGreen);
		else g.setColor(Color.BLACK);
		g.drawRect(410,220,90,90);
		g.drawImage(defaultPistol,410,220,89,89,null);
		if(gunSkin2Row1Clicked) g.setColor(limeGreen);
		else g.setColor(Color.BLACK);
		g.drawRect(550,220,90,90);
		g.drawImage(gunSkin1,550,220,89,89,null);
		if(gunSkin3Row1Clicked) g.setColor(limeGreen);
		else g.setColor(Color.BLACK);
		g.drawRect(690,220,90,90);
		g.drawImage(gunSkin2,690,220,89,89,null);
		
		if(gunSkin1Row2Clicked) g.setColor(limeGreen);
		else g.setColor(Color.BLACK);
		g.drawRect(410,360,90,90);
		g.drawImage(defaultShotgun,410,360,89,89,null);

		if(gunSkin2Row2Clicked) g.setColor(limeGreen);
		else g.setColor(Color.BLACK);
		g.drawRect(550,360,90,90);
		g.drawImage(ShotgunSkin1,550,360,89,89,null);

		if(gunSkin3Row2Clicked) g.setColor(limeGreen);
		else g.setColor(Color.BLACK);
		g.drawRect(690,360,90,90);
		g.drawImage(ShotgunSkin2,690,360,89,89,null);
		
		g.setColor(Color.BLACK);
		if(gunSkin1Row3Clicked) g.setColor(limeGreen);
		else g.setColor(Color.BLACK);
		g.drawRect(410,500,90,90);
		g.drawImage(defaultSniper,410,500,89,89,null);
		
		if(gunSkin2Row3Clicked) g.setColor(limeGreen);
		else g.setColor(Color.BLACK);
		g.drawRect(550,500,90,90);
		g.drawImage(SniperSkin1,550,500,89,89,null);
		if(gunSkin3Row3Clicked) g.setColor(limeGreen);
		else g.setColor(Color.BLACK);
		g.drawRect(690,500,90,90);
		g.drawImage(SniperSkin2,690,500,89,89,null);
		
		if(gunSkin1Row4Clicked) g.setColor(limeGreen);
		else g.setColor(Color.BLACK);
		g.drawRect(410,640,90,90);
		g.drawImage(defaultDualPistol,410,640,89,89,null);
		if(gunSkin2Row4Clicked) g.setColor(limeGreen);
		else g.setColor(Color.BLACK);
		g.drawRect(550,640,90,90);
		g.drawImage(DualPistolSkin1,550,640,89,89,null);
		if(gunSkin3Row4Clicked) g.setColor(limeGreen);
		else g.setColor(Color.BLACK);
		g.drawRect(690,640,90,90);
		g.drawImage(DualPistolSkin2,690,640,89,89,null);
	}
	/* This method is part of MouseListener and this
	 * is required to make sure you buy items in the customize */
	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		if(game.gameState == game.gameState.CUSTOMIZE)
		{
			if(contains(mx,my,10,330,40,40))
			{
				all = false;
				defaultSkin = false;
				isSkin1 = false;
				isSkin2 = false;
				isSkin3 = false;
				if(skinIndex == 0) skinIndex = 3;
				else skins[skinIndex] = skins[skinIndex--];
			}
			if(contains(mx,my,270,330,40,40))
			{
				all = false;
				defaultSkin = false;
				isSkin1 = false;
				isSkin2 = false;
				isSkin3 = false;
				if(skinIndex == 3) skinIndex = 0;
				else skins[skinIndex] = skins[skinIndex++];
			}
			if(contains(mx,my,60,220,200,280) && !defaultSkin && skinIndex == 0)
			{
				defaultBoxClicked = true;
				defaultSkin = true;
				isSkin1 = false;
				isSkin2 = false;
				isSkin3 = false;
				all = true;
			}
			else if(contains(mx,my,60,220,200,280) && !isSkin1 && skinIndex == 1 && game.isLevel2Complete)
			{
				Skin1BoxClicked = true;
				isSkin1 = true;
				isSkin2 = false;
				isSkin3 = false;
				defaultSkin = false;
				all = true;
			}
			else if(contains(mx,my,60,220,200,280) && !isSkin2 && skinIndex == 2 && game.isLevel3Complete)
			{
				Skin2BoxClicked = true;
				isSkin1 = false;
				isSkin2 = true;
				isSkin3 = false;
				defaultSkin = false;
				all = true;
			}
			else if(contains(mx,my,60,220,200,280) && !isSkin3 && skinIndex == 3 && game.isLevel4Complete)
			{
				Skin3BoxClicked = true;
				isSkin1 = false;
				isSkin2 = false;
				isSkin3 = true;
				defaultSkin = false;
				all = true;
			}
			
			if(contains(mx,my,410,220,90,90) && !gunSkin1Row1Clicked)
			{
				gunSkin1Row1Clicked = true;
				gunSkin2Row1Clicked = false;
				gunSkin3Row1Clicked = false;
			}
			else if(contains(mx,my,550,220,90,90) && !gunSkin2Row1Clicked)
			{
				if(game.isLevel2Complete)
				{
					gunSkin1Row1Clicked = false;
					gunSkin2Row1Clicked = true;
					gunSkin3Row1Clicked = false;
				}
				else RedRow2 = true;
			}
			else if(contains(mx,my,690,220,90,90) && !gunSkin3Row1Clicked)
			{
				if(game.isLevel3Complete)
				{
					gunSkin1Row1Clicked = false;
					gunSkin2Row1Clicked = false;
					gunSkin3Row1Clicked = true;
				}
				else RedRow3 = true;
				
			}
			if(contains(mx,my,410,360,90,90) && !gunSkin1Row2Clicked)
			{
				gunSkin1Row2Clicked = true;
				gunSkin2Row2Clicked = false;
				gunSkin3Row2Clicked = false;
			}
			else if(contains(mx,my,550,360,90,90) && !gunSkin2Row2Clicked)
			{
				if(game.isLevel2Complete)
				{
					gunSkin1Row2Clicked = false;
					gunSkin2Row2Clicked = true;
					gunSkin3Row2Clicked = false;
				}
				else RedRow2 = true;
			}
			else if(contains(mx,my,690,360,90,90) && !gunSkin3Row2Clicked)
			{
				if(game.isLevel3Complete)
				{
					gunSkin1Row2Clicked = false;
					gunSkin2Row2Clicked = false;
					gunSkin3Row2Clicked = true;
				}
				else RedRow3 = true;
			}
			
			if(contains(mx,my,410,500,90,90) && !gunSkin1Row3Clicked)
			{
				gunSkin1Row3Clicked = true;
				gunSkin2Row3Clicked = false;
				gunSkin3Row3Clicked = false;
			}
			else if(contains(mx,my,550,500,90,90) && !gunSkin2Row3Clicked)
			{
				if(game.isLevel2Complete)
				{
					gunSkin1Row3Clicked = false;
					gunSkin2Row3Clicked = true;
					gunSkin3Row3Clicked = false;	
				}
				else RedRow2 = true;
			}
			else if(contains(mx,my,690,500,90,90) && !gunSkin3Row3Clicked)
			{
				if(game.isLevel3Complete)
				{
					gunSkin1Row3Clicked = false;
					gunSkin2Row3Clicked = false;
					gunSkin3Row3Clicked = true;
				}
				else RedRow3 = true;
			}
			
			if(contains(mx,my,410,640,90,90) && !gunSkin1Row4Clicked)
			{
				gunSkin1Row4Clicked = true;
				gunSkin2Row4Clicked = false;
				gunSkin3Row4Clicked = false;
			}
			else if(contains(mx,my,550,640,90,90) && !gunSkin2Row4Clicked)
			{
				if(game.isLevel2Complete)
				{
					gunSkin1Row4Clicked = false;
					gunSkin2Row4Clicked = true;
					gunSkin3Row4Clicked = false;
				}
				else RedRow2 = true;
			}
			else if(contains(mx,my,690,640,90,90) && !gunSkin3Row4Clicked)
			{
				if(game.isLevel3Complete)
				{
					gunSkin1Row4Clicked = false;
					gunSkin2Row4Clicked = false;
					gunSkin3Row4Clicked = true;
				}
				else RedRow3 = true;
			}
		}
	}
	/* This method is part of MouseListener and this
	 * is required to make sure you buy items in the customize 
	 * to check if you released or not */
	public void mouseReleased(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		if(RedRow2) RedRow2 = false;
		if(RedRow3) RedRow3 = false;
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
