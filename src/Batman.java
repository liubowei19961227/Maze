import java.util.ArrayList;
/**
 * Batman is a class that stores the position, stamina, and the path
 * that are already walked through of a player
 * @author Yang Ge, Renwen Zhang, Bowei Liu, Lena Nesterenko
 */
public class Batman {
	private Node position;
	private int stamina;
	private ArrayList<Node>path;
	/**
	 * Construct a Batman object
	 * 
	 */
	public Batman(){
		this.position = new Node(0,1);
		this.stamina = 100;
		path = new ArrayList<Node>();
		
	}
	/**
	 * Reset the player to original state(position and stamina)
	 */
	public void reset(){
		position = new Node(0,1);
		stamina = 100;
	}
	/**
	 * Return  "position" field data
	 * @return the position of the player
	 */
	public Node getPosition(){
		return position;
	}
	
	/**
	 * The player goes up, then add the position to path, and return
	 * the position of the player after moving
	 * @param maze, the maze that the player are playing
	 * @return the position after moving
	 */
	public Node go_up(Maze maze){
		int x = position.getX();
		int y = position.getY();
		Node move = new Node(x-1,y);
		position = move;
		path.add(move);
		return move;
		
	}
	
	/**
	 * The player goes down, then add the position to path, and return
	 * the position after moving
	 * @param maze, the maze that the player are playing
	 * @return the position after moving
	 */
	public Node go_down(Maze maze){
		int x = position.getX();
		int y = position.getY();
		Node move = new Node(x+1,y);
		this.position = move;
		path.add(move);
		return position;
	}
	/**
	 * The player goes left, then add the position to path, and return
	 * the position of the player after moving
	 * @param maze, the maze that the player are playing
	 * @return the position after moving
	 */
	public Node go_left(Maze maze){
		int x = position.getX();
		int y = position.getY();
		Node move = new Node(x,y-1);
		position = move;
		path.add(move);
		return move;
	}
	/**
	 * The player goes right, then add the position to path, and return
	 * the position of the player after moving
	 * @param maze, the maze that the player are playing
	 * @return the position after moving
	 */
	public Node go_right(Maze maze){
		int x = position.getX();
		int y = position.getY();
		Node move = new Node(x,y+1);
		position = move;
		path.add(move);
		return move;
	}
	
	
	/**
	 * The player get hurt when the player meet enemy, then lose 30 stamina
	 */
	public void getHurt(){
		this.stamina = this.stamina - 30;
	}
	
	
	/**
	 * Check if the player still alive
	 * @return true if the player is alive,false otherwise
	 */
	public boolean ifStillAlive(){
		if(stamina <= 0){
			return false;
		}
		return true;
	}
	
	/**
	 * Return "stamina" field data
	 * @return the current stamina
	 */
	public int getStamina(){
		return stamina;
	}
	
	/**
	 * this method is used to taking off the path that player has been past from the searching
	 * @param pathRecording
	 * @return the path remaining
	 */
	public ArrayList<Node> SearchPath(ArrayList<Node>pathRecording){
		ArrayList<Node>uncompleted = new ArrayList<Node>();
		for(Node n : pathRecording){
			if(!ifSearchFound(n)){
				uncompleted.add(n);
			}
		}
		return uncompleted;
	}
	
	/**
	 * this is a helper function used by searchPath
	 * @param step
	 * @return true if the place has been past and false otherwise
	 */
	private boolean ifSearchFound(Node step){
		for(Node n : path){
			if(n.equal(step)){
				return true;
			}
		}
		return false;
	}
	
}
