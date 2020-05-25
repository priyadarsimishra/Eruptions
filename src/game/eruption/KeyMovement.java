package game.eruption;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/* This class is used to call methods
 * in the game class for movement of the player
 * as this class used keyAdapter so you do not
 * to add 3 key methods */
public class KeyMovement extends KeyAdapter
{
	private Game game;
	/* This is the constructor which needs a game instance */
	public KeyMovement(Game game)
	{
		this.game = game;
	}
	/* This is the method that calls the keyPressed for keyMovement */
	public void keyPressed(KeyEvent e)
	{
		game.keyPressed(e);
	}
	/* This is the method that calls the keyReleased for keyMovement */
	public void keyReleased(KeyEvent e)
	{
		game.keyReleased(e);
	}
}
