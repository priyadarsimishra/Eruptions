import java.awt.image.BufferedImage;
/* This class gets the specific image from the sprite sheet
 * and puts it into the specific Buffered Image  */
public class SpriteTextures 
{
	public BufferedImage player,bronzeCoin,silverCoin,goldCoin,fireball,bullet;
	public BufferedImage rightArrow, leftArrow, space;
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
		player = spriteSheet.getImage(1, 1, 32, 32);
		rightArrow = spriteSheet.getImage(5, 1, 32, 32);
		leftArrow = spriteSheet.getImage(6, 1, 32, 32);
		space = spriteSheet.getImage(7, 1, 32, 32);
		bronzeCoin = spriteSheet.getImage(3, 2, 32, 32);
		silverCoin = spriteSheet.getImage(1, 2, 32, 32);
		goldCoin = spriteSheet.getImage(2, 2, 32, 32);
		fireball = spriteSheet.getImage(3, 1, 32, 32);
		bullet = spriteSheet.getImage(4, 1, 32, 32);
	}
}