import java.awt.image.BufferedImage;

public class SpriteTextures 
{
	public BufferedImage player,coin,fireball,bullet;
	private SpriteSheet spriteSheet = null;
	
	public SpriteTextures(Game game)
	{
		spriteSheet = new SpriteSheet(game.getSpriteSheet());
		getTextures();
	}
	public void getTextures()
	{
		player = spriteSheet.getImage(1, 1, 32, 32);
		coin = spriteSheet.getImage(2, 1, 32, 32);
		fireball = spriteSheet.getImage(3, 1, 32, 32);
		bullet = spriteSheet.getImage(4, 1, 32, 32);
	}
}