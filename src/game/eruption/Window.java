package game.eruption;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/* This class creates the Canvas to draw on */
public class Window extends JPanel
{
	public static JFrame frame;
	/* This sets up our JFrame and gives it the title, width, height and adds the game */
	public Window(int width, int height, String title, Game game)
	{
		frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width,height));
		frame.setMaximumSize(new Dimension(width,height));
		frame.setMinimumSize(new Dimension(width,height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
}