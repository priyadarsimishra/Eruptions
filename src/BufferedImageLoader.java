import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
//This is class loads in our spritesheet and background
public class BufferedImageLoader 
{
	private BufferedImage image;
	/* This method returns a BufferedImage by taking a path
	 * and using ImageIO it reads for an image in the location
	 * given by the path and once the image is found it is put as
	 * image and this image is returned. This method throws an
	 * IOException when their no image in the path */
	public BufferedImage loadImage(String path) throws IOException
	{
		image = ImageIO.read(getClass().getResource(path));
		return image;
	}
}
