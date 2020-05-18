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
		FileUtils fileUtils = new FileUtils();
//		fileUtils.store(new PlayerInfo("papa",500,100,1));
//		fileUtils.store(new PlayerInfo("papa",1000,100,2));
//		fileUtils.store(new PlayerInfo("papa",1500,100,3));
//		fileUtils.store(new PlayerInfo("papa",2500,100,4));
//		
//		fileUtils.store(new PlayerInfo("mama",5000,100,1));
//		//fileUtils.store(new PlayerInfo("mama",10000,100,2));
//		fileUtils.store(new PlayerInfo("mama",15000,100,3));
//		fileUtils.store(new PlayerInfo("mama",25000,100,4));		
		
		System.out.println("******** Higehest Score per Level ***********");
		System.out.println(fileUtils.getHighestScore("priya",1));
		System.out.println(fileUtils.getHighestScore("priya",2));
		System.out.println(fileUtils.getHighestScore("priya",3));
		System.out.println(fileUtils.getHighestScore("priya",4));
		
		System.out.println("******** Higehest Score per Level 2nd Player***********");
		System.out.println(fileUtils.getHighestScore("priya",1));
		System.out.println(fileUtils.getHighestScore("priya",2));
		System.out.println(fileUtils.getHighestScore("priya",3));
		System.out.println(fileUtils.getHighestScore("priya",4));
		
		System.out.println("******** Total Score per Level ***********");
		
		System.out.println(fileUtils.getTotalScoreForAPlayer("priya"));
		System.out.println(fileUtils.getTotalScoreForAPlayer("priya"));
		
		
	}

	/* This method returns an integer as it takes the player
	 * name and returns their highest score */ 
	public int getHighestScore(String playerName,int level) 
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
					 return (o1.getHighestLevelScore()-o2.getHighestLevelScore());
				}
			});
		}
		
		if(playerList != null && playerList.size() >0) 
		{
			for(PlayerInfo player1 : playerList) {
				if(player1.getPlayerName().equalsIgnoreCase(playerName)
						&& player1.getPlayerLevel() == level) 
				{
					highestScore = player1.getHighestLevelScore();
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
					 return (o1.getTotalScore()-o2.getTotalScore());
				}
			});
		}
		
		if(playerList != null && playerList.size() >0) 
		{
			for(PlayerInfo player1 : playerList) {
				if(player1.getPlayerName().equalsIgnoreCase(playerName)) 
				{
					
					totalScore = player1.getTotalScore();
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
