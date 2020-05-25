package game.eruption;
import java.awt.image.BufferedImage;
/* This class gets each picture as the
 * each sprite is 32 x 32 and this gets each specific image */
public class SpriteSheet 
{
	private BufferedImage image;
	/* This gets the spritesheet image from the 
	 * BufferedImageLoader through the constructor */
	public SpriteSheet(BufferedImage image)
	{
		this.image = image;
	}
	/* This method gets a 32 x32 image which is one square 
	 * in the sprite sheet and its get the image depending
	 * on the column and row is passed and using 32 x 32 width and height */
	public BufferedImage getImage(int col, int row,int width,int height)
	{
		BufferedImage newImage = image.getSubimage((col * 32) - 32,(row * 32) - 32, width, height);
		return newImage;
	}

}
