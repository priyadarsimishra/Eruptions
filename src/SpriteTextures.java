import java.awt.image.BufferedImage;
/* This class gets the specific image from the sprite sheet
 * and puts it into the specific Buffered Image  */
public class SpriteTextures 
{
	public BufferedImage player,playerShooting,bronzeCoin,silverCoin,goldCoin,fireball,bullet,waterBullet;
	public BufferedImage rightArrowKey, leftArrowKey, space, BossLevel1, waterbucket, bomb;
	public BufferedImage rightMagmaRock,leftMagmaRock, undergroundEnemy, ruby,diamond;
	public BufferedImage enemyBullet,rightWizard,leftWizard,freezePotion,throwerEnemy, Bosslevel2,key;
	public BufferedImage emerald, doubleBullet, bossArrow;
	private SpriteSheet spriteSheet = null;
	/* This is the constructor that requires the game instance 
	 * to get the sprite sheet and calls the getTexture */
	public SpriteTextures(Game game)
	{
		spriteSheet = new SpriteSheet(game.getSpriteSheet());
		getTextures();
	}
	/* This initializes the BufferImages with the specific box
	 * in the sprite sheets */
	public void getTextures()
	{
		//rows and columns
		player = playerShooting = spriteSheet.getImage(4, 3, 32, 32);
		playerShooting = spriteSheet.getImage(1, 1, 32, 32);
		ruby = spriteSheet.getImage(2, 1, 32, 32);
		fireball = spriteSheet.getImage(3, 1, 32, 32);
		bullet = spriteSheet.getImage(4, 1, 32, 32);
		rightArrowKey = spriteSheet.getImage(5, 1, 32, 32);
		leftArrowKey = spriteSheet.getImage(6, 1, 32, 32);
		space = spriteSheet.getImage(7, 1, 32, 32);
		BossLevel1 = spriteSheet.getImage(8,1,32,32);
		silverCoin = spriteSheet.getImage(1, 2, 32, 32);
		goldCoin = spriteSheet.getImage(2, 2, 32, 32);
		bronzeCoin = spriteSheet.getImage(3, 2, 32, 32);
		waterbucket = spriteSheet.getImage(4, 2, 32, 32);
		waterBullet = spriteSheet.getImage(5, 2, 32, 32);
		bomb = spriteSheet.getImage(6, 2, 32, 32);
		rightMagmaRock = spriteSheet.getImage(7, 2, 32, 32);
		leftMagmaRock = spriteSheet.getImage(8, 2, 32, 32);
		undergroundEnemy = spriteSheet.getImage(1, 3, 32, 32);
		diamond = spriteSheet.getImage(2, 3, 32, 32);
		enemyBullet = spriteSheet.getImage(3, 3, 32, 32);
		leftWizard = spriteSheet.getImage(5, 3, 32, 32);
		rightWizard = spriteSheet.getImage(6, 3, 32, 32);
		freezePotion = spriteSheet.getImage(7, 3, 32, 32);
		throwerEnemy = spriteSheet.getImage(8, 3, 32, 32);
		Bosslevel2 = spriteSheet.getImage(1, 4, 32, 32);
		key = spriteSheet.getImage(2, 4, 32, 32);
		emerald = spriteSheet.getImage(3, 4, 32, 32);
		doubleBullet = spriteSheet.getImage(4, 4, 32, 32);
		bossArrow = spriteSheet.getImage(5, 4, 32, 32);
	}
}