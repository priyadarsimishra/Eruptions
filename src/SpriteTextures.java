import java.awt.image.BufferedImage;

public class SpriteTextures 
{
	public BufferedImage player,bronzeCoin,silverCoin,goldCoin,fireball,bullet;
	private SpriteSheet spriteSheet = null;
	
	public SpriteTextures(Game game)
	{
		spriteSheet = new SpriteSheet(game.getSpriteSheet());
		getTextures();
	}
	public void getTextures()
	{
		//rows and columns
		player = spriteSheet.getImage(1, 1, 32, 32);
		bronzeCoin = spriteSheet.getImage(3, 2, 32, 32);
		silverCoin = spriteSheet.getImage(1, 2, 32, 32);
		goldCoin = spriteSheet.getImage(2, 2, 32, 32);
		fireball = spriteSheet.getImage(3, 1, 32, 32);
		bullet = spriteSheet.getImage(4, 1, 32, 32);
	}
}