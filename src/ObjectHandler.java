import java.awt.Graphics;
import java.util.LinkedList;
/* This class is very important as it adds objects
 * to the game and it also calls updates and renders
 * methods of the specific object */
public class ObjectHandler 
{
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	/* This method is called 60 times per second
	 * and it is responsible for getting one object
	 * per iteration from the Linked List and call
	 * the objects update method */
	public void update()
	{
		for(int i = 0;i<object.size();i++)
		{
			GameObject obj = object.get(i);
			obj.update();
		}
	}
	/* This method is called 60 times per second
	 * and it is responsible for getting one object
	 * per iteration from the Linked List and call
	 * the objects render method */
	public void render(Graphics g)
	{
		for(int i = 0;i<object.size();i++)
		{
			GameObject obj = object.get(i);
			obj.render(g);
		}
	}
	/* This method clears all the objects in the Linked List */
	public void clearAll()
	{
		for(int i = 0;i<object.size();i++)
		{
			object.clear();
		}
	}
	/* This method adds an object to the linked list */
	public void addObject(GameObject obj)
	{
		this.object.add(obj);
	}
	/* This method removes an object to the linked list */
	public void removeObject(GameObject obj)
	{
		this.object.remove(obj);
	}
}
