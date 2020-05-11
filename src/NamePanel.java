import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/* This class displays the name JFrame at the start of the game
 * to store progress for that name */
public class NamePanel extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JPanel panel;
	private Game game;
	public static JTextField field;
	private JLabel label;
	/* This establishes the JFrame and the placements of the textfield
	 * label and button */ 
	public NamePanel(Game game)
	{
		this.game = game;
		frame = new JFrame(Game.TITLE);
		frame.setPreferredSize(new Dimension(Game.WIDTH,Game.HEIGHT));
		frame.setMaximumSize(new Dimension(Game.WIDTH,Game.HEIGHT));
		frame.setMinimumSize(new Dimension(Game.WIDTH,Game.HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.pack();
		panel = new JPanel();
		label = new JLabel();
		label.setFont(new Font("Superpower Synonym",Font.BOLD,64));
		label.setText("Please Enter your Name");
		JPanel north = new JPanel();
		north.setBackground(Color.ORANGE);
		north.add(label);
		JPanel east = new JPanel();
		east.setBackground(Color.ORANGE);
		JPanel center = new JPanel();
		center.setBackground(Color.ORANGE);
		JPanel west = new JPanel();
		west.setBackground(Color.ORANGE);
		JPanel south = new JPanel();
		south.setBackground(Color.ORANGE);
		field = new JTextField("",10);
		JButton OkButton = new JButton("OK");
		OkButton.setFont(new Font("Superpower Synonym",Font.BOLD,36));
		OkButton.setPreferredSize(new Dimension(100,50));
		OkButtonHandler obh = new OkButtonHandler();
		OkButton.addActionListener(obh);
		panel.setBackground(Color.ORANGE);
		panel.setLayout(new BorderLayout());
		field.setFont(new Font("Arial",Font.BOLD,48));
		panel.setPreferredSize(new Dimension(200,200));
		north.setPreferredSize(new Dimension(200,200));
		east.setPreferredSize(new Dimension(200,200));
		west.setPreferredSize(new Dimension(200,200));
		south.setPreferredSize(new Dimension(200,500));
		center.setLayout(new BorderLayout());
		JLabel gameName = new JLabel();
//		gameName.setFont(new Font("Arial",Font.BOLD,64));
//		gameName.setText(Game.TITLE);
		south.add(OkButton);
//		south.add(gameName);
		center.add(field,BorderLayout.CENTER);
		panel.add(field,BorderLayout.CENTER);
		panel.add(north,BorderLayout.NORTH);
		panel.add(east,BorderLayout.EAST);
		panel.add(west,BorderLayout.WEST);
		panel.add(south,BorderLayout.SOUTH);
		frame.add(panel);
		frame.setVisible(true);
	}
	/* This class is the button handler which checks if the textfield
	 * has content or not and then if it does it changes the game State
	 * and runs the other window(main game) */
	class OkButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String command = e.getActionCommand();
			if(command.equals("OK") && !field.getText().isEmpty())
			{
				Game.gameState = Game.STATE.MENU;
				frame.setVisible(false);
				Window window = new Window(Game.WIDTH, Game.HEIGHT,Game.TITLE, game);
			}
		}
	}
}
