package game.eruption;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

/* This is the upgrade system class during levels
 * where you can buy certain items with your score */
public class Upgrades extends MouseAdapter
{
	private ObjectHandler handler;
	private SpriteTextures texture;
	private Game game;
	public Color skyBlue = new Color(0,191,255);
	public Color springGreen = new Color(0,255,127);
	public Color mistyRose = new Color(255,228,225);
	public Color gold = new Color(255,215,0);
	private Image shield;
	private ImageIcon shieldIcon;
	private Image coinLogo;
	private ImageIcon coinLogoIcon;
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
	//boolean for display
	public boolean notEnoughMoney = false;
	public boolean highHealth = false;
	public boolean alreadyClickedRow1 = false;
	public boolean level1Issue = false;
	public boolean alreadyClickedRow2 = false;
	public boolean levelUnlockRow2Lev2 = false;
	public boolean levelUnlockRow2Lev3 = false;
	public boolean levelUnlockRow2Lev4 = false;
	public boolean notBossFightRow2 = false;
	public boolean notEnoughMoneyRow2 = false;
	//row 3
	public boolean level1IssueRow3 = false;
	public boolean alreadyClickedRow3 = false;
	public boolean levelUnlockRow3Lev2 = false;
	public boolean levelUnlockRow3Lev3 = false;
	public boolean notEnoughMoneyRow3 = false;
	public boolean notBossFightRow3 = false;
	//other
	public int box1Row1Cost = 1000;
	public int box2Row1Cost = 2000;
	public int box3Row1Cost = 1000;
	public int box4Row1Cost = 1500;
	//bullets
	//waterBullet
	public int box1Row2Cost = 500;
	//splitBullet
	public int box2Row2Cost = 800;
	//DualPistolBullet
	public int box3Row2Cost = 1000;
	//Shotgun Bullet
	public int box4Row2Cost = 1300;
	//guns
	//pistol
	public int box1Row3Cost = 500;
	//shotgun
	public int box2Row3Cost = 1000;
	//dual pistols
	public int box3Row3Cost = 1500;
	//sniper
	public int box4Row3Cost = 2000;
	
	public boolean isShield;
	public boolean isScoreBoost;
	
	public boolean isBullet;
	public boolean isSplitBullet;
	public boolean isDualPistolBullet;
	public boolean isShotgunBullet;
	//guns
	public boolean isPistol;
	public boolean isShotgun;
	public boolean isDualPistol;
	public boolean isSniper; 
	/* This constructor requires some parameters that render 
	 * objects from sprite sheet and the handler to make changes*/
	public Upgrades(ObjectHandler handler, SpriteTextures texture, Game game)
	{
		this.handler = handler;
		this.texture = texture;
		this.game = game;
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
		shieldIcon = new ImageIcon(getClass().getResource("/ShieldLogo.png"));
		shield = shieldIcon.getImage();
		coinLogoIcon = new ImageIcon(getClass().getResource("/CoinLogo.png"));
		coinLogo = coinLogoIcon.getImage();
		g.setColor(mistyRose);
		Font shopFont = new Font("Superpower Synonym",Font.BOLD,160);
		g.setFont(shopFont);
        ((Graphics2D)g).setStroke(new BasicStroke(2));
		g.drawString("UPGRADES",60,125);
		Font backButtonFont = new Font("Roboto",Font.BOLD,48);
		g.setFont(backButtonFont);
		g.setColor(Color.WHITE);
		g.drawString("SCORE: "+HUD.SCORE, 5, 180);
		g.setColor(Color.YELLOW);
		if(backButton) g.fillRect(330, 720, 150, 50);
		else g.drawRect(330, 720, 150, 50);
		if(backButton) g.setColor(Color.RED);
		else g.setColor(Color.YELLOW);
		g.drawString("BACK", 340, 760);
		
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
		g.drawImage(shield,490, 200, 110, 110,null);
		//put picture of upgrade
		g.fillRect(660, 200, 110, 110);
		g.drawImage(coinLogo,660, 200, 110, 110,null);
		//put picture of upgrade
		//2nd row
		g.fillRect(150, 380, 110, 110);
		g.drawImage(texture.waterBulletLogo,150, 380, 110, 110,null);
		//put picture of upgrade
		g.fillRect(320, 380, 110, 110);
		g.drawImage(texture.splitBulletLogo,320, 380, 110, 110,null);
		//put picture of upgrade
		g.fillRect(490, 380, 110, 110);
		g.drawImage(texture.dualpistolBulletLogo,490, 380, 110, 110,null);
		//put picture of upgrade
		g.fillRect(660, 380, 110, 110);
		g.drawImage(texture.shotgunBulletlogo,660, 380, 110, 110,null);
		//put picture of upgrade
		//3rd row
		g.fillRect(150, 560, 110, 110);
		g.drawImage(texture.pistol,150, 560, 110, 110,null);
		//put picture of upgrade
		g.fillRect(320, 560, 110, 110);
		g.drawImage(texture.shotgun,320, 560, 110, 110,null);
		//put picture of upgrade
		g.fillRect(490, 560, 110, 110);
		g.drawImage(texture.dualPistol,490, 560, 110, 110,null);
		//put picture of upgrade
		g.fillRect(660, 560, 110, 110);
		g.drawImage(texture.sniper,660, 560, 110, 110,null);
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
        else g.setColor(gold);
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
        else g.setColor(Color.YELLOW);
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
        else g.setColor(gold);
		g.drawRect(490, 200, 111, 111);
		g.setColor(Color.YELLOW);
		g.drawString("Shield",520,328);
		g.drawString("Cost: "+box3Row1Cost,509, 345);
		//put text under upgrade here
		//4th square: Row 
		if(fourthRow1SquareClicked) 
        {
       		if(SquareRED)
       			g.setColor(Color.RED);
       		else
       			g.setColor(springGreen); 
       	}
        else g.setColor(gold);
		g.drawRect(660, 200, 111, 111);
		g.setColor(Color.YELLOW);
		g.drawString("Score Boost",672,328);
		g.drawString("Cost: "+box4Row1Cost,674, 345);
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
        else g.setColor(gold);
		g.drawRect(150, 380, 111, 111);
		g.setColor(Color.YELLOW);
		g.setFont(itemFont);
		g.drawString("Water Bullet",162,510);
		g.drawString("Cost: "+box1Row2Cost,170, 530);
		//put text under upgrade here
		//2nd Square: Row 2
		if(secondRow2SquareClicked) 
        {
       		if(SquareRED)
       			g.setColor(Color.RED);
       		else
       			g.setColor(springGreen); 
       	}
        else g.setColor(gold);
		g.drawRect(320, 380, 111, 111);
		g.setColor(Color.YELLOW);
		g.setFont(itemFont);
		g.drawString("Split Bullet",334,510);
		g.drawString("Cost: "+box2Row2Cost,340, 530);
		//put text under upgrade here
		//3rd Square: Row 2
		if(thirdRow2SquareClicked) 
        {
       		if(SquareRED)
       			g.setColor(Color.RED);
       		else
       			g.setColor(springGreen); 
       	}
        else g.setColor(gold);
		g.drawRect(490, 380, 111, 111);
		g.setColor(Color.YELLOW);
		g.setFont(itemFont);
		g.drawString("Explosive Bullet",488,510);
		g.drawString("Cost: "+box3Row2Cost,506, 530);
		//put text under upgrade here
		//4th Square: Row 2
		if(fourthRow2SquareClicked) 
        {
       		if(SquareRED)
       			g.setColor(Color.RED);
       		else
       			g.setColor(springGreen); 
       	}
        else g.setColor(gold);
		g.drawRect(660, 380, 111, 111);
		g.setColor(Color.YELLOW);
		g.setFont(itemFont);
		g.drawString("Shotgun Bullet",662,510);
		g.drawString("Cost: "+box4Row2Cost,676, 530);
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
        else g.setColor(gold);
		g.drawRect(150, 560, 111, 111);
		g.setColor(Color.YELLOW);
		g.setFont(itemFont);
		g.drawString("Pistol",183,688);
		g.drawString("Cost: "+box1Row3Cost,170, 706);
		//put text under upgrade here
		//2nd Square: Row 3
		if(secondRow3SquareClicked) 
        {
       		if(SquareRED)
       			g.setColor(Color.RED);
       		else
       			g.setColor(springGreen); 
       	}
        else g.setColor(gold);
		g.drawRect(320, 560, 111, 111);
		g.setColor(Color.YELLOW);
		g.setFont(itemFont);
		g.drawString("Shotgun",346,688);
		g.drawString("Cost: "+box2Row3Cost,337, 706);
		//put text under upgrade here
		//3rd Square: Row 3
		if(thirdRow3SquareClicked) 
        {
       		if(SquareRED)
       			g.setColor(Color.RED);
       		else
       			g.setColor(springGreen); 
       	}
        else g.setColor(gold);
		g.drawRect(490, 560, 111, 111);
		g.setColor(Color.YELLOW);
		g.setFont(itemFont);
		g.drawString("Dual Pistols",502,688);
		g.drawString("Cost: "+box3Row3Cost,505, 706);
		//put text under upgrade here
		//4th Square: Row 3
		if(fourthRow3SquareClicked) 
        {
       		if(SquareRED)
       			g.setColor(Color.RED);
       		else
       			g.setColor(springGreen); 
       	}
        else g.setColor(gold);
		g.drawRect(660, 560, 111, 111);
		g.setColor(Color.YELLOW);
		g.setFont(itemFont);
		g.drawString("Sniper",690,688);
		g.drawString("Cost: "+box4Row3Cost,675, 706);
		
		g.setColor(Color.CYAN);
		Font errorMessageFont = new Font("Roboto",Font.BOLD,18);
		g.setFont(errorMessageFont);
		if(notEnoughMoney)
		{
			g.drawString("You do", 32, 240);
			g.drawString("not have", 22,260);
			g.drawString("enough money!",4, 280);
		}
		if(highHealth)
		{
			if(HUD.HEALTH == 100)
			{
				g.drawString("You have", 36, 240);
				g.drawString("full health!", 32,260);
			}
			else if(HUD.HEALTH > 70 && HUD.HEALTH<100)
			{
				g.drawString("Your health",23,240);
				g.drawString("is high!", 35, 260);
			}
		}
		if(alreadyClickedRow1)
		{
			g.drawString("You are already",1,240);
			g.drawString("equipped",20,260);
			g.drawString("with this item",9,280);
		}
		if(notEnoughMoneyRow2)
		{
			g.drawString("You do", 32, 440);
			g.drawString("not have", 22,460);
			g.drawString("enough money!",2, 480);
		}
		if(level1Issue)
		{
			g.drawString("Not Avaliable", 16, 440);
			g.drawString("in Level 1", 28, 460);
		}
		if(notBossFightRow2)
		{
			g.drawString("Not Avaliable", 16, 440);
			g.drawString("During", 30, 460);
			g.drawString("Boss Fight", 20, 480);
		}
		if(alreadyClickedRow2)
		{
			g.drawString("You are already",1,440);
			g.drawString("equipped",20,460);
			g.drawString("with this item",9,480);
		}
		if(levelUnlockRow2Lev2)
		{
			g.drawString("Unlock after", 16, 440);
			g.drawString("Level 2", 30, 460);
		}
		else if(levelUnlockRow2Lev3)
		{
			g.drawString("Unlock after", 16, 440);
			g.drawString("Level 3", 30, 460);
		}
		else if(levelUnlockRow2Lev4)
		{
			g.drawString("Unlock after", 16, 440);
			g.drawString("Level 4", 30, 460);
		}
		//Row 3
		if(alreadyClickedRow3)
		{
			g.drawString("You are already",1,600);
			g.drawString("equipped",20,620);
			g.drawString("with this item",9,640);
		}
		if(notEnoughMoneyRow3)
		{
			g.drawString("You do", 32, 600);
			g.drawString("not have", 22,620);
			g.drawString("enough money!",2, 640);
		}
		if(level1IssueRow3)
		{
			g.drawString("Not Avaliable", 16, 640);
			g.drawString("in Level 1", 28, 660);
		}
		if(notBossFightRow3)
		{
			g.drawString("Not Avaliable", 16, 600);
			g.drawString("During", 30, 620);
			g.drawString("Boss Fight", 20, 640);
		}
		if(levelUnlockRow3Lev2)
		{
			g.drawString("Unlock after", 16, 640);
			g.drawString("Level 2", 30, 660);
		}
		else if(levelUnlockRow3Lev3)
		{
			g.drawString("Unlock after", 16, 640);
			g.drawString("Level 3", 30, 660);
		}
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
			SoundPlayer.getSound("mouse_clicked").play();
			if(Game.stateholder.equalsIgnoreCase("Level1")) 
			{
				Game.gameState = Game.STATE.LEVEL1;
				HUD.DUALRELOAD = 0;
				HUD.PISTOLRELOAD = 0;
				HUD.SHOTGUNRELOAD = 0;
				HUD.SNIPERRELOAD = 0;
			}
			if(Game.stateholder.equalsIgnoreCase("Level2"))
			{
				Game.gameState = Game.STATE.LEVEL2;
				HUD.DUALRELOAD = 0;
				HUD.PISTOLRELOAD = 0;
				HUD.SHOTGUNRELOAD = 0;
				HUD.SNIPERRELOAD = 0;
			}
			if(Game.stateholder.equalsIgnoreCase("Level3"))
			{
				Game.gameState = Game.STATE.LEVEL3;
				HUD.DUALRELOAD = 0;
				HUD.PISTOLRELOAD = 0;
				HUD.SHOTGUNRELOAD = 0;
				HUD.SNIPERRELOAD = 0;
			}
			if(Game.stateholder.equalsIgnoreCase("Level4"))
			{
				Game.gameState = Game.STATE.LEVEL4;
				HUD.DUALRELOAD = 0;
				HUD.PISTOLRELOAD = 0;
				HUD.SHOTGUNRELOAD = 0;
				HUD.SNIPERRELOAD = 0;
			}
		}
		if(Game.gameState == Game.STATE.UPGRADES)
		{
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
					{
						SquareRED = true;
						highHealth = true;
					}
				}
				else
				{
					SquareRED = true;
					notEnoughMoney = true;
				}
			}
			if(contains(mx,my,320, 200, 111, 111) && !secondRow1SquareClicked)
			{
				secondRow1SquareClicked = true;
				if(HUD.HEALTH == 100)
				{
					SquareRED = true;
					highHealth = true;
				}
				else
				{
					if(HUD.SCORE>=box2Row1Cost)
					{
						HUD.HEALTH = 100;
						HUD.SCORE-=box2Row1Cost;
						box2Row1Cost+=500;
					}
					else 
					{
						SquareRED = true;
						notEnoughMoney = true;
					}
				}
			}
			if(contains(mx,my,490, 200, 111, 111) && !thirdRow1SquareClicked)
			{
				thirdRow1SquareClicked = true;
				if(!isShield)
				{
					if(HUD.SCORE>=box3Row1Cost)
					{
						//Here is where you put the impact of the upgrade
						isShield = true;
						HUD.SCORE-=box3Row1Cost;
						box3Row1Cost+=500;
					}
					else 
					{
						SquareRED = true;
						notEnoughMoney = true;
					}
				}
				else 
				{
					SquareRED = true;
					alreadyClickedRow1 = true;
				}
			}
			if(contains(mx,my,660, 200, 111, 111) && !fourthRow1SquareClicked)
			{
				fourthRow1SquareClicked = true;
				if(!isScoreBoost)
				{
					if(HUD.SCORE>=box4Row1Cost)
					{
						//Here is where you put the impact of the upgrade
						isScoreBoost = true;
						HUD.SCORE-=box4Row1Cost;
						box4Row1Cost+=500;
					}
					else 
					{
						SquareRED = true;
						notEnoughMoney = true;
					}
				}
				else 
				{
					SquareRED = true;
					alreadyClickedRow1 = true;
				}
			}
			if(contains(mx,my,150, 380, 111, 111) && !firstRow2SquareClicked)
			{
				firstRow2SquareClicked = true;
				if(!(Game.stateholder.equalsIgnoreCase("Level1")) && !isBullet && !(game.isBossFight || game.isBossFight2 || game.isBossFight3 || game.isBossFight4))
				{
					if(HUD.SCORE>=box1Row2Cost)
					{
						//Here is where you put the impact of the upgrade
						isSplitBullet = false;
						isDualPistolBullet = false;
						isShotgunBullet = false;
						isBullet = true;
						HUD.SCORE-=box1Row2Cost;
					}
					else if(HUD.SCORE<=box1Row2Cost)
					{
						notEnoughMoneyRow2 = true;
						SquareRED = true;
					}
				}
				else if(Game.stateholder.equalsIgnoreCase("Level1"))
				{
					level1Issue = true;
					SquareRED = true;
				}
				else if(game.isBossFight || game.isBossFight2 || game.isBossFight3 || game.isBossFight4)
				{
					SquareRED = true;
					notBossFightRow2 = true;
				}
				else if(isBullet)
				{
					alreadyClickedRow2 = true;
					SquareRED = true;
				}
			}
			if(contains(mx,my,320, 380, 111, 111) && !secondRow2SquareClicked)
			{
				secondRow2SquareClicked = true;	
				if(!(Game.stateholder.equalsIgnoreCase("Level1")) && !isSplitBullet && game.isLevel2Complete && !(game.isBossFight || game.isBossFight2 || game.isBossFight3 || game.isBossFight4))
				{
					secondRow2SquareClicked = true;	
					if(HUD.SCORE>=box2Row2Cost)
					{
						//Here is where you put the impact of the upgrade
						isDualPistolBullet = false;
						isShotgunBullet = false;
						isBullet = false;
						isSplitBullet = true;
						HUD.SCORE-=box2Row2Cost;
					}
					else if(HUD.SCORE<=box1Row2Cost)
					{
						notEnoughMoneyRow2 = true;
						SquareRED = true;
					}
				}
				else if(Game.stateholder.equalsIgnoreCase("Level1"))
				{
					level1Issue = true;
					SquareRED = true;
				}
				else if(!game.isLevel2Complete)
				{
					levelUnlockRow2Lev2 = true;
					SquareRED = true;
				}
				else if(game.isBossFight || game.isBossFight2 || game.isBossFight3 || game.isBossFight4)
				{
					SquareRED = true;
					notBossFightRow2 = true;
				}
				else if(isSplitBullet)
				{
					alreadyClickedRow2 = true;
					SquareRED = true;
				}
			}
			if(contains(mx,my,490, 380, 111, 111) && !thirdRow2SquareClicked)
			{
				thirdRow2SquareClicked = true;
				if(!(Game.stateholder.equalsIgnoreCase("Level1")) && !isDualPistolBullet && game.isLevel3Complete && !(game.isBossFight || game.isBossFight2 || game.isBossFight3 || game.isBossFight4))
				{
					if(HUD.SCORE>=box3Row2Cost)
					{
						//Here is where you put the impact of the upgrade
						isShotgunBullet = false;
						isBullet = false;
						isSplitBullet = false;
						isDualPistolBullet = true;
						HUD.SCORE-=box3Row2Cost;
					}
					else if(HUD.SCORE<=box1Row2Cost)
					{
						notEnoughMoneyRow2 = true;
						SquareRED = true;
					}
				}
				else if(Game.stateholder.equalsIgnoreCase("Level1"))
				{
					level1Issue = true;
					SquareRED = true;
				}
				else if(!game.isLevel3Complete)
				{
					levelUnlockRow2Lev3 = true;
					SquareRED = true;
				}
				else if(game.isBossFight || game.isBossFight2 || game.isBossFight3 || game.isBossFight4)
				{
					SquareRED = true;
					notBossFightRow2 = true;
				}
				else if(isDualPistolBullet)
				{
					alreadyClickedRow2 = true;
					SquareRED = true;
				}
			}
			if(contains(mx,my,660, 380, 111, 111) && !fourthRow2SquareClicked)
			{
				fourthRow2SquareClicked = true;
				if(!(Game.stateholder.equalsIgnoreCase("Level1")) && !isShotgunBullet && game.isLevel4Complete  && !(game.isBossFight || game.isBossFight2 || game.isBossFight3 || game.isBossFight4))
				{
					if(HUD.SCORE>=box4Row2Cost)
					{
						//Here is where you put the impact of the upgrade
						isBullet = false;
						isSplitBullet = false;
						isDualPistolBullet = false;
						isShotgunBullet = true;
						HUD.SCORE-=box4Row2Cost;
						box4Row2Cost+=500;
					}
					else if(HUD.SCORE<=box1Row2Cost)
					{
						notEnoughMoneyRow2 = true;
						SquareRED = true;
					}
				}
				else if(!game.isLevel4Complete)
				{
					levelUnlockRow2Lev4 = true;
					SquareRED = true;
				}
				else if(Game.stateholder.equalsIgnoreCase("Level1"))
				{
					level1Issue = true;
					SquareRED = true;
				}
				else if(game.isBossFight || game.isBossFight2 || game.isBossFight3 || game.isBossFight4)
				{
					SquareRED = true;
					notBossFightRow2 = true;
				}
				else if(isShotgunBullet)
				{
					alreadyClickedRow2 = true;
					SquareRED = true;
				}
			}
			if(contains(mx,my,150, 560, 111, 111) && !firstRow3SquareClicked)
			{
				firstRow3SquareClicked = true;
				if(!(Game.stateholder.equalsIgnoreCase("Level1")) && !isPistol  && !(game.isBossFight || game.isBossFight2 || game.isBossFight3 || game.isBossFight4))
				{
					if(HUD.SCORE>=box1Row3Cost)
					{
						//Here is where you put the impact of the upgrade
						isSniper = false;
						isDualPistol = false;
						isShotgun = false;
						isPistol = true;
						HUD.SCORE-=box1Row3Cost;
					}
					else 
					{
						notEnoughMoneyRow3 = true;
						SquareRED = true;
					}
				}
				else if(Game.stateholder.equalsIgnoreCase("Level1"))
				{
					level1IssueRow3 = true;
					SquareRED = true;
				}
				else if(game.isBossFight || game.isBossFight2 || game.isBossFight3 || game.isBossFight4)
				{
					notBossFightRow3 = true;
					SquareRED = true;
				}
				else if(isPistol)
				{
					alreadyClickedRow3  = true;
					SquareRED = true;
				}
			}
			if(contains(mx,my,320, 560, 111, 111) && !secondRow3SquareClicked)
			{
				secondRow3SquareClicked = true;
				if(!(Game.stateholder.equalsIgnoreCase("Level1")) && !isShotgun  && !(game.isBossFight || game.isBossFight2 || game.isBossFight3 || game.isBossFight4))
				{
					if(HUD.SCORE>=box2Row3Cost)
					{
						//Here is where you put the impact of the upgrade
						isPistol = false;
						isSniper = false;
						isDualPistol = false;
						isShotgun = true;
						HUD.SCORE-=box2Row3Cost;
					}
					else 
					{
						notEnoughMoneyRow3 = true;
						SquareRED = true;
					}
				}
				else if(Game.stateholder.equalsIgnoreCase("Level1"))
				{
					level1IssueRow3 = true;
					SquareRED = true;
				}
				else if(game.isBossFight || game.isBossFight2 || game.isBossFight3 || game.isBossFight4)
				{
					notBossFightRow3 = true;
					SquareRED = true;
				}
				else if(isShotgun)
				{
					alreadyClickedRow3  = true;
					SquareRED = true;
				}
			}
			if(contains(mx,my,490, 560, 111, 111) && !thirdRow3SquareClicked)
			{
				thirdRow3SquareClicked = true;
				if(!(Game.stateholder.equalsIgnoreCase("Level1")) && !isDualPistol && game.isLevel2Complete  && !(game.isBossFight || game.isBossFight2 || game.isBossFight3 || game.isBossFight4))
				{
					if(HUD.SCORE>=box3Row3Cost)
					{
						//Here is where you put the impact of the upgrade
						isPistol = false;
						isShotgun = false;
						isSniper = false;
						isDualPistol = true;
						HUD.SCORE-=box3Row3Cost;
					}
					else 
					{
						notEnoughMoneyRow3 = true;
						SquareRED = true;
					}
				}
				else if(Game.stateholder.equalsIgnoreCase("Level1"))
				{
					level1IssueRow3 = true;
					SquareRED = true;
				}
				else if(!game.isLevel2Complete)
				{
					levelUnlockRow3Lev2 = true;
					SquareRED = true;
				}
				else if(game.isBossFight || game.isBossFight2 || game.isBossFight3 || game.isBossFight4)
				{
					notBossFightRow3 = true;
					SquareRED = true;
				}
				else if(isDualPistol)
				{
					alreadyClickedRow3  = true;
					SquareRED = true;
				}
			}
			if(contains(mx,my,660, 560, 111, 111) && !fourthRow3SquareClicked)
			{
				fourthRow3SquareClicked = true;
				if(!(Game.stateholder.equalsIgnoreCase("Level1")) && !isSniper && game.isLevel3Complete  && !(game.isBossFight || game.isBossFight2 || game.isBossFight3 || game.isBossFight4))
				{
					if(HUD.SCORE>=box4Row3Cost)
					{
						//Here is where you put the impact of the upgrade
						isPistol = false;
						isShotgun = false;
						isDualPistol = false;
						isSniper = true;
						HUD.SCORE-=box4Row3Cost;
					}
					else 
					{
						notEnoughMoneyRow3 = true;
						SquareRED = true;
					}
				}
				else if(Game.stateholder.equalsIgnoreCase("Level1"))
				{
					level1IssueRow3 = true;
					SquareRED = true;
				}
				else if(!game.isLevel3Complete)
				{
					levelUnlockRow3Lev3 = true;
					SquareRED = true;
				}
				else if(game.isBossFight || game.isBossFight2 || game.isBossFight3 || game.isBossFight4)
				{
					notBossFightRow3 = true;
					SquareRED = true;
				}
				else if(isSniper)
				{
					alreadyClickedRow3  = true;
					SquareRED = true;
				}
			}
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
			if(notEnoughMoney) notEnoughMoney = false;
			if(highHealth) highHealth = false;
			if(SquareRED)
				SquareRED = false;
		}
		if(secondRow1SquareClicked)
		{
			secondRow1SquareClicked = false;
			if(notEnoughMoney) notEnoughMoney = false;
			if(highHealth) highHealth = false;
			if(SquareRED)
				SquareRED = false;
		}
		if(thirdRow1SquareClicked)
		{
			thirdRow1SquareClicked = false;
			if(notEnoughMoney) notEnoughMoney = false;
			if(alreadyClickedRow1) alreadyClickedRow1 = false;
			if(SquareRED)
				SquareRED = false;
		}
		if(fourthRow1SquareClicked)
		{
			fourthRow1SquareClicked = false;
			if(notEnoughMoney) notEnoughMoney = false;
			if(alreadyClickedRow1) alreadyClickedRow1 = false;
			if(SquareRED)
				SquareRED = false;
		}
		if(firstRow2SquareClicked)
		{
			firstRow2SquareClicked = false;
			if(level1Issue) level1Issue = false; 
			if(alreadyClickedRow2) alreadyClickedRow2 = false;
			if(notBossFightRow2) notBossFightRow2 = false;
			if(notEnoughMoneyRow2) notEnoughMoneyRow2 = false;
			if(SquareRED)
				SquareRED = false;
		}
		if(secondRow2SquareClicked)
		{
			secondRow2SquareClicked = false;
			if(level1Issue) level1Issue = false; 
			if(levelUnlockRow2Lev2) levelUnlockRow2Lev2 = false;
			if(alreadyClickedRow2) alreadyClickedRow2 = false;
			if(notBossFightRow2) notBossFightRow2 = false;
			if(notEnoughMoneyRow2) notEnoughMoneyRow2 = false;
			if(SquareRED)
				SquareRED = false;
		}
		if(thirdRow2SquareClicked)
		{
			thirdRow2SquareClicked = false;
			if(level1Issue) level1Issue = false;
			if(levelUnlockRow2Lev3) levelUnlockRow2Lev3 = false;
			if(alreadyClickedRow2) alreadyClickedRow2 = false;
			if(notBossFightRow2) notBossFightRow2 = false;
			if(notEnoughMoneyRow2) notEnoughMoneyRow2 = false;
			if(SquareRED)
				SquareRED = false;
		}
		if(fourthRow2SquareClicked)
		{
			fourthRow2SquareClicked = false;
			if(level1Issue) level1Issue = false; 
			if(levelUnlockRow2Lev4) levelUnlockRow2Lev4 = false;
			if(alreadyClickedRow2) alreadyClickedRow2 = false;
			if(notBossFightRow2) notBossFightRow2 = false;
			if(notEnoughMoneyRow2) notEnoughMoneyRow2 = false;
			if(SquareRED)
				SquareRED = false;
		}
		if(firstRow3SquareClicked)
		{
			firstRow3SquareClicked = false;
			if(level1IssueRow3) level1IssueRow3 = false;
			if(notBossFightRow3) notBossFightRow3 = false;
			if(notEnoughMoneyRow3) notEnoughMoneyRow3 = false;
			if(alreadyClickedRow3) alreadyClickedRow3 = false;
			if(SquareRED)
				SquareRED = false;
		}
		if(secondRow3SquareClicked)
		{
			secondRow3SquareClicked = false;
			if(level1IssueRow3) level1IssueRow3 = false;
			if(notBossFightRow3) notBossFightRow3 = false;
			if(notEnoughMoneyRow3) notEnoughMoneyRow3 = false;
			if(alreadyClickedRow3) alreadyClickedRow3 = false;
			if(SquareRED)
				SquareRED = false;
		}
		if(thirdRow3SquareClicked)
		{
			thirdRow3SquareClicked = false;
			if(level1IssueRow3) level1IssueRow3 = false;
			if(levelUnlockRow3Lev2) levelUnlockRow3Lev2 = false;
			if(notBossFightRow3) notBossFightRow3 = false;
			if(notEnoughMoneyRow3) notEnoughMoneyRow3 = false;
			if(alreadyClickedRow3) alreadyClickedRow3 = false;
			if(SquareRED)
				SquareRED = false;
		}
		if(fourthRow3SquareClicked)
		{
			fourthRow3SquareClicked = false;
			if(level1IssueRow3) level1IssueRow3 = false;
			if(levelUnlockRow3Lev3) levelUnlockRow3Lev3 = false;
			if(notBossFightRow3) notBossFightRow3 = false;
			if(notEnoughMoneyRow3) notEnoughMoneyRow3 = false;
			if(alreadyClickedRow3) alreadyClickedRow3 = false;
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
