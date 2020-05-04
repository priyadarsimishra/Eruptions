
import java.io.Serializable;

public class PlayerInfo 
implements Serializable
{
	public PlayerInfo() 
	{
		super();
	}
	private static final long serialVersionUID = 1L;
	private String playerName;
	private Integer highestIndividualScore;
	private Integer totalIndividualScore;
	public PlayerInfo(String playerName, Integer highestIndividualScore,Integer totalIndividualScore) 
	{
		super();
		this.playerName = playerName;
		this.highestIndividualScore = highestIndividualScore;
		this.totalIndividualScore = totalIndividualScore;
	}
	public String getPlayerName() 
	{
		return playerName;
	}
	public void setPlayerName(String playerName) 
	{
		this.playerName = playerName;
	}
	public Integer getHighestIndividualScore() 
	{
		return highestIndividualScore;
	}
	public void setHighestIndividualScore(Integer highestIndividualScore) 
	{
		this.highestIndividualScore = highestIndividualScore;
	}
	public Integer getTotalIndividualScore() 
	{
		return totalIndividualScore;
	}
	public void setTotalIndividualScore(Integer totalIndividualScore) 
	{
		this.totalIndividualScore = totalIndividualScore;
	}
	public String toString() 
	{
		return "Player [playerName=" + playerName + ", highestIndividualScore="
				+ highestIndividualScore + ", totalIndividualScore="
				+ totalIndividualScore + "]";
	}
}
