import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/* This class appends and stores information in our file */ 
public class FileUtils 
{
	public static List<PlayerInfo> playerList = new ArrayList<PlayerInfo>();
	/* This is the main class for the File Utils which can be used to see 
	 * the specific players stats used for testing purposes*/
	public static void main(String[] args) 
	{
//		FileUtils fileUtils = new FileUtils();
		//PlayerInfo player1 = new PlayerInfo("priya",100,1500,1);
//		PlayerInfo player2 = new PlayerInfo("Baba1",300,1600);
//		
		//fileUtils.store(player1);
//		fileUtils.store(player2);
//		PlayerInfo player3 = new PlayerInfo("biswa",900,2500);
//		PlayerInfo player4 = new PlayerInfo("biswa",2500,3400);
//	
//		fileUtils.store(player3);
//		fileUtils.store(player4);
//
//		System.out.println("Highest Score for biswa: "+fileUtils.getHighestScore("biswa"));
//		System.out.println("Total Score for biswa: "+fileUtils.getTotalScoreForAPlayer("biswa"));
//		System.out.println("Highest Score for baba1: "+fileUtils.getHighestScore("baba1"));
//		System.out.println("Total Score for baba1: "+fileUtils.getTotalScoreForAPlayer("baba1"));
//		System.out.println("Highscore for player: "+fileUtils.getHighestScore("priya"));
//		System.out.println("Level for player: "+fileUtils.getThePlayerLevel("priya"));
		

	}

	/* This method returns an integer as it takes the player
	 * name and returns their highest score */ 
	public int getHighestScore(String playerName) 
	{
		PlayerInfo player = null;
		FileInputStream fis = null;
		ObjectInputStream ois =null;
		boolean has = true;
		int highestScore=0;
		
		try{
			fis = new FileInputStream("Player.txt");
			
			while(has)
			{
				ois = new ObjectInputStream(fis);
				if(fis.available() !=0)
				{
					player = (PlayerInfo) ois.readObject();
					playerList.add(player);
				}
				else
				{
					has =false;
				}
				
			}
			ois.close();
			fis.close();
		}
		catch(Exception e)
		{
//           System.out.println(e.printStackTrace());
		}
		if(playerList != null && playerList.size() >0) 
		{
			Collections.sort(playerList, new Comparator<PlayerInfo>() 
			{
				public int compare(PlayerInfo o1, PlayerInfo o2) 
				{
					 return (o1.getHighestIndividualScore()-o2.getHighestIndividualScore());
				}
			});
		}
		
		if(playerList != null && playerList.size() >0) 
		{
			for(PlayerInfo player1 : playerList) {
				if(player1.getPlayerName().equalsIgnoreCase(playerName)) 
				{
					highestScore = player1.getHighestIndividualScore();
				}
			}
		}
		return highestScore;
	}
	/* This method returns an integer as it takes the player
	 * name and returns their total score */ 
	public int getTotalScoreForAPlayer(String playerName) 
	{
		PlayerInfo player = null;
		FileInputStream fis = null;
		ObjectInputStream ois =null;
		boolean has = true;
		int totalScore=0;
		
		try{
			fis = new FileInputStream("Player.txt");
			
			while(has)
			{
				ois = new ObjectInputStream(fis);
				if(fis.available() !=0)
				{
					player = (PlayerInfo) ois.readObject();
					playerList.add(player);
				}
				else
				{
					has =false;
				}
				
			}
			ois.close();
			fis.close();
		}
		catch(Exception e)
		{
//           System.out.println(e.printStackTrace());
		}
	
		if(playerList != null && playerList.size() >0) 
		{
			Collections.sort(playerList, new Comparator<PlayerInfo>() 
			{
				public int compare(PlayerInfo o1, PlayerInfo o2) 
				{
					 return (o1.getTotalIndividualScore()-o2.getTotalIndividualScore());
				}
			});
		}
		
		if(playerList != null && playerList.size() >0) 
		{
			for(PlayerInfo player1 : playerList) {
				if(player1.getPlayerName().equalsIgnoreCase(playerName)) 
				{
					
					totalScore = player1.getTotalIndividualScore();
				}
			}
		}
		return totalScore;
	}
	/* This method returns the last level the player 
	 * was on which basically helps to know what levels
	 * to allow them to see */
	public int getThePlayerLevel(String playerName)
	{
		PlayerInfo player = null;
		FileInputStream fis = null;
		ObjectInputStream ois =null;
		boolean has = true;
		int level = 0;
		
		try
		{
			fis = new FileInputStream("Player.txt");
			
			while(has)
			{
				ois = new ObjectInputStream(fis);
				if(fis.available() !=0)
				{
					player = (PlayerInfo) ois.readObject();
					playerList.add(player);
				}
				else
				{
					has =false;
				}
				
			}
			ois.close();
			fis.close();
		}
		catch(Exception e)
		{
//	           System.out.println(e.printStackTrace());
		}
		
		if(playerList != null && playerList.size() >0) 
		{
			Collections.sort(playerList, new Comparator<PlayerInfo>() 
			{
				public int compare(PlayerInfo o1, PlayerInfo o2) 
				{
					 return (o1.getPlayerLevel()- o2.getPlayerLevel());
				}
			});
		}
		
		if(playerList != null && playerList.size()>0)
		{
			for(PlayerInfo player1 : playerList) {
				if(player1.getPlayerName().equalsIgnoreCase(playerName)) 
				{
					
					level = player1.getPlayerLevel();
				}
			}
		}
		return level;
	}
	/* This method returns a boolean as it takes the player
	 * stores information and creates the file returns true if the file exists */ 
	public boolean store(PlayerInfo player1) 
	{
		boolean stored = false;
		FileOutputStream fos =null;
		ObjectOutputStream oos = null;
		
		try{
			fos = new FileOutputStream(new File("Player.txt"),true);
			oos = new ObjectOutputStream(fos);
			if(null ==player1.getPlayerLevel()) {
				player1.setPlayerLevel(0);
			}
			oos.writeObject(player1);
			stored =true;
			oos.flush();
			oos.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Failed in writing details:"+ ioe.getMessage());	
		}
		return stored;
	}

}
