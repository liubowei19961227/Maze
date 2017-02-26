import java.util.Random;
/**
 * Enemy is a class that stores the information of a enemy(position,
 * the moving strategy)
 * @author Yang Ge, Renwen Zhang, Bowei Liu, Lena Nesterenko
 *
 */
public class Enemy {
	private Node position;
	private int strategy;    //0 means doesn't move and 1 means moving 
	
	/**
	 * Construct a enemy object
	 * @param difficulty
	 */
	public Enemy(int difficulty){
		Node start = new Node(difficulty-2,difficulty-1);
		
		//Node start = new Node(1,1);
		this.position = start;
		this.strategy = 1;
		
	}
	/**
	 * Change the moving strategy
	 */
	private void changeStrategy(){
		if(this.strategy == 1){
			this.strategy = 0;
		}else if(this.strategy == 0){
			this.strategy = 1;
		}
	}
	
	/**
	 * Return "position" field data
	 * @return the current position of the enemy
	 */ 
	public Node getPosition(){
		return this.position;	
	}
	
	/**
	 * This is used to generate a random number
	 * @param min integer
	 * @param max integer
	 * @return the number that is randomly generated
	 */
	private int randomGenerator(int min,int max){
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
	/**
	 * This is used to randomly move the enemy
	 * @param maze
	 */
	public void move(Maze maze){
		int rand = randomGenerator(0,1);
		if(rand == 0){
			changeStrategy();
		}
		/*if(this.strategy == 0){
			return this.position;
		}*/
		Node move = maze.randomMove(position);
		this.position = move;
		//return move;
		
	}
	
	
	
}
