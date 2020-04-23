import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class ObjectHandler 
{
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	private Random r = new Random();
	public void update()
	{
		for(int i = 0;i<object.size();i++)
		{
			GameObject obj = object.get(i);
			obj.update();
		}
	}
	public void render(Graphics g)
	{
		for(int i = 0;i<object.size();i++)
		{
			GameObject obj = object.get(i);
			obj.render(g);
		}
	}
	public void clearEnemys()
	{
		for(int i = 0;i<object.size();i++)
		{
			GameObject tempObject = object.get(i);
			object.clear();
		}
	}
	public void addObject(GameObject obj)
	{
		this.object.add(obj);
	}
	public void removeObject(GameObject obj)
	{
		this.object.remove(obj);
	}
}
