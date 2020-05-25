package game.eruption;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
		JLabel background = new JLabel(new ImageIcon(getClass().getResource("/NamePanelImage.gif")));
		background.setLayout(new BorderLayout());
		background.setPreferredSize(new Dimension(800,800));
		frame.add(background);
		
		label = new JLabel();
		label.setFont(new Font("Roboto",Font.BOLD,48));
		label.setText("PLEASE ENTER YOUR NAME");
		field = new JTextField("",10);
		field.setFont(new Font("Arial",Font.BOLD,48));
		JButton OkButton = new JButton("OK");
		OkButton.setFont(new Font("Roboto",Font.BOLD,36));
		OkButton.setPreferredSize(new Dimension(100,50));
		OkButtonHandler obh = new OkButtonHandler();
		OkButton.addActionListener(obh);
		JPanel north = new JPanel();
		north.setLayout(new BorderLayout());
		JPanel nnorth = new JPanel();
		nnorth.setBackground(new Color(0,0,0,0));
		JPanel enorth = new JPanel();
		enorth.setBackground(new Color(0,0,0,0));
		JPanel cnorth = new JPanel();
		cnorth.setBackground(new Color(0,0,0,0));
		JPanel wnorth = new JPanel();
		wnorth.setBackground(new Color(0,0,0,0));
		JPanel snorth = new JPanel();
		snorth.setBackground(new Color(255,255,255,120));
		snorth.add(label);
		north.add(nnorth,BorderLayout.NORTH);
		north.add(enorth,BorderLayout.EAST);
		north.add(cnorth,BorderLayout.CENTER);
		north.add(wnorth,BorderLayout.WEST);
		north.add(snorth,BorderLayout.SOUTH);
		north.setBackground(new Color(0,0,0,0));
		north.setPreferredSize(new Dimension(200,200));
		JPanel east = new JPanel();
		east.setPreferredSize(new Dimension(200,200));
		east.setBackground(new Color(0,0,0,0));
		JPanel center = new JPanel();	
		center.setLayout(new BorderLayout());
		center.setBackground(new Color(0,0,0,0));
		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(200,200));
		west.setBackground(new Color(0,0,0,0));
		JPanel south = new JPanel();
		south.setPreferredSize(new Dimension(200,500));
		south.setBackground(new Color(0,0,0,0));
		center.add(field);
		south.add(OkButton);
		background.add(north,BorderLayout.NORTH);
		background.add(east,BorderLayout.EAST);
		background.add(center,BorderLayout.CENTER);
		background.add(west,BorderLayout.WEST);
		background.add(south,BorderLayout.SOUTH);
		
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
			SoundPlayer.getSound("mouse_clicked").play();
			if(command.equals("OK") && !field.getText().isEmpty())
			{
				Game.gameState = Game.STATE.MENU;
				frame.setVisible(false);
				Window window = new Window(Game.WIDTH, Game.HEIGHT,Game.TITLE, game);
			}
		}
	}
}
