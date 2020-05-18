import java.awt.image.BufferedImage;
/* This class gets the specific image from the sprite sheet
 * and puts it into the specific Buffered Image  */
public class SpriteTextures 
{
	public BufferedImage player,playerShooting,bronzeCoin,silverCoin,goldCoin,fireball,bullet,waterBullet;
	public BufferedImage rightArrowKey, leftArrowKey, space, BossLevel1, waterbucket, bomb;
	public BufferedImage rightMagmaRock,leftMagmaRock, undergroundEnemy, ruby,diamond;
	public BufferedImage enemyBullet,rightWizard,leftWizard,freezePotion,throwerEnemyLeft, Bosslevel2,key;
	public BufferedImage emerald, doubleBullet, bossArrow, pinkGem, healthPotion, healthLogo;
	public BufferedImage shiftKey, exploderEnemyRight, rayEnemy, rocketEnemy,rocket,doublePistolPlayer,rayBullet;
	public BufferedImage healthRefillLogo,explosiveBullet, egg, Bosslevel3, babyDragon,tankEnemy;
	public BufferedImage boomerangEnemyLeft, tankBullet, shield, shieldEnemy,purpleEmerald, shotgunBullet;
	public BufferedImage crate, BossLevel4, throwerEnemyRight,boomerangEnemyRight,exploderEnemyLeft,waterBulletLogo;
	public BufferedImage splitBulletLogo,dualpistolBulletLogo,shotgunBulletlogo,pistol,shotgun,dualPistol,sniper;
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
		throwerEnemyLeft = spriteSheet.getImage(8, 3, 32, 32);
		Bosslevel2 = spriteSheet.getImage(1, 4, 32, 32);
		key = spriteSheet.getImage(2, 4, 32, 32);
		emerald = spriteSheet.getImage(3, 4, 32, 32);
		doubleBullet = spriteSheet.getImage(4, 4, 32, 32);
		bossArrow = spriteSheet.getImage(5, 4, 32, 32);
		pinkGem = spriteSheet.getImage(6, 4, 32, 32);
		healthPotion = spriteSheet.getImage(7, 4, 32, 32);
		healthLogo = spriteSheet.getImage(8, 4, 32, 32);
		exploderEnemyRight = spriteSheet.getImage(1, 5, 32, 32);
		rayEnemy = spriteSheet.getImage(2, 5, 32, 32);
		rocketEnemy = spriteSheet.getImage(3, 5, 32, 32);
		shiftKey = spriteSheet.getImage(4, 5, 32, 32);
		rocket = spriteSheet.getImage(5, 5, 32, 32);
		doublePistolPlayer = spriteSheet.getImage(6, 5, 32, 32);
		rayBullet = spriteSheet.getImage(7, 5, 32, 32);
		healthRefillLogo = spriteSheet.getImage(8, 5, 32, 32);
		explosiveBullet = spriteSheet.getImage(1, 6, 32, 32);
		egg = spriteSheet.getImage(2, 6, 32, 32);
		Bosslevel3 = spriteSheet.getImage(3, 6, 32, 32);
		babyDragon = spriteSheet.getImage(4, 6, 32, 32);
		boomerangEnemyLeft = spriteSheet.getImage(5, 6, 32, 32);
		tankEnemy = spriteSheet.getImage(6, 6, 32, 32);
		tankBullet = spriteSheet.getImage(7, 6, 32, 32);
		shield = spriteSheet.getImage(8, 6, 32, 32);
		shieldEnemy = spriteSheet.getImage(1, 7, 32, 32);
		purpleEmerald = spriteSheet.getImage(2, 7, 32, 32);
		shotgunBullet = spriteSheet.getImage(3, 7, 32, 32);
		crate = spriteSheet.getImage(4, 7, 32, 32);
		BossLevel4 = spriteSheet.getImage(5, 7, 32, 32);
		throwerEnemyRight = spriteSheet.getImage(6, 7, 32, 32);
		boomerangEnemyRight = spriteSheet.getImage(7, 7, 32, 32);
		exploderEnemyLeft = spriteSheet.getImage(8, 7, 32, 32);
		waterBulletLogo = spriteSheet.getImage(1, 8, 32, 32);
		splitBulletLogo = spriteSheet.getImage(2, 8, 32, 32);
		dualpistolBulletLogo = spriteSheet.getImage(3, 8, 32, 32);
		shotgunBulletlogo = spriteSheet.getImage(4, 8, 32, 32);
		pistol = spriteSheet.getImage(5, 8, 32, 32);
		shotgun = spriteSheet.getImage(6, 8, 32, 32);
		dualPistol = spriteSheet.getImage(7, 8, 32, 32);
		sniper = spriteSheet.getImage(8, 8, 32, 32);
	}
}