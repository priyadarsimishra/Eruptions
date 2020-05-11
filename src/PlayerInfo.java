import java.io.Serializable;
/* This class holds all player information that is used tp store
 * player progress */
public class PlayerInfo 
implements Serializable
{
	/* This is a default constructor used for declaration/initialization
	 * of the class */
	public PlayerInfo() 
	{
		super();
	}
	private static final long serialVersionUID = 1L;
	private String playerName;
	private Integer highestIndividualScore;
	private Integer totalIndividualScore;
	private Integer playerLevel;
	/* This is the constructor that has parameters which
	 * store the players highest score, name, and their
	 * total score */
	public PlayerInfo(String playerName, Integer highestIndividualScore,Integer totalIndividualScore,Integer playerLevel) 
	{
		super();
		this.playerName = playerName;
		this.highestIndividualScore = highestIndividualScore;
		this.totalIndividualScore = totalIndividualScore;
		this.playerLevel = playerLevel;
	}
	/* Gets the player name */
	public String getPlayerName() 
	{
		return playerName;
	}
	/* Sets the player name */
	public void setPlayerName(String playerName) 
	{
		this.playerName = playerName;
	}
	/* Gets the player Highest score */
	public Integer getHighestIndividualScore() 
	{
		return highestIndividualScore;
	}
	/* sets the player Highest score used for drawing */
	public void setHighestIndividualScore(Integer highestIndividualScore) 
	{
		this.highestIndividualScore = highestIndividualScore;
	}
	/* Gets the player total score */
	public Integer getTotalIndividualScore() 
	{
		return totalIndividualScore;
	}
	/* sets the player total score used for drawing */
	public void setTotalIndividualScore(Integer totalIndividualScore) 
	{
		this.totalIndividualScore = totalIndividualScore;
	}
	/* Gets player Level */
	public Integer getPlayerLevel()
	{
		return playerLevel;
	}
	/* Sets the player level */
	public void setPlayerLevel(Integer playerLevel)
	{
		this.playerLevel = playerLevel;
	}
	/* This returns all the player details used in FileUtils */
	public String toString() {
		return "PlayerInfo [playerName=" + playerName + ", highestIndividualScore=" + highestIndividualScore
				+ ", totalIndividualScore=" + totalIndividualScore + ", playerLevel=" + playerLevel + "]";
	}
}
