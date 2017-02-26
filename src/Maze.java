import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
/**
 * Maze is a class that stores all information of a maze(the coordinates of path and wall, the
 * difficulty, the path from starting position to exit position, the hostages positions, the crazy point)
 * @author Yang Ge, Renwen Zhang, Bowei Liu, Lena Nesterenko
 *
 */
public class Maze implements MazeOperation{
	
	public int[][]matrix;
	private int difficulty; //if the difficulty is n, the maze would be (n+1)*(n+1) square
	private int path = 1;
	private int wall = 0;
	private ArrayList<Node>pathRecording;
	private ArrayList<Node>hostage;
	private ArrayList<Node>crazyNodes;
	
	/**
	 * Construct a Maze object
	 * @param difficulty
	 */
	public Maze(int difficulty){  //start is (0,0) and exit is (difficulty,difficulty)
		pathRecording = new ArrayList<Node>();
		hostage = new ArrayList<Node>();
		crazyNodes = new ArrayList<Node>();
		matrix = new int[difficulty][difficulty];
		this.difficulty = difficulty;
		int i,j;
		for(i = 0; i < difficulty;i++){
			for(j = 0;j < difficulty; j++){
				matrix[i][j] = wall;
			}
		}
		generatePath(difficulty);
	
	}
	/**
	 * Return "matrix" field data
	 * @return matrix
	 */
	public int[][]getMatrix(){
		return matrix;
	}
	/**
	 * Return "difficulty" field data
	 * @return difficulty
	 */
	public int getDifficulty(){
		return difficulty;
	}
	
	//s is start and E is end
	/**
	 * Generate a path from starting point to exit position
	 * It uses the backtracker algorithm
	 * the path is recorded as the maze is formed
	 * the positions of crazyNodes and hostages are generated randomly
	 * @param difficulty
	 */
	public void generatePath(int difficulty) {
		Stack<Node>stack = new Stack<Node>();
		Node start = new Node(1,1);
		Node current = start;
		stack.push(start);
		int flag = 0;
		while(!stack.isEmpty()){
			ArrayList<Node>neibours = current.getNeibour(difficulty);
			if(current.getX() == difficulty-2 && current.getY()==difficulty-2){
				flag = 1;
			}
			if(flag == 0 && ifNeibours(neibours)){
				pathRecording.add(new Node(current.getX(),current.getY()));
			}
			int rand = randomGenerator(0,200);
			if(rand == 4){
				hostage.add(new Node(current.getX(),current.getY()));
			}
			
			int rand1 = randomGenerator(0,300);
			if(rand1 == 4){
				crazyNodes.add(new Node(current.getX(),current.getY()));
			}
			
			if(ifNeibours(neibours)){
				stack.push(current);
				Node next = sketch(neibours);
				makePath(current,next);
				current = next;
			}else{
				current = stack.pop();
				if(flag == 0 &&  pathRecording.size() > 0){
					pathRecording.remove(pathRecording.size() - 1);
				}
			}
		}
		matrix[0][1] = path;
		matrix[difficulty-1][difficulty-2]= path;
		
		
		
		
	}
	
	/**
	 * 
	 * @param list
	 * @return true if a node has unvisited neibours and false otherwise
	 */
	private boolean ifNeibours(ArrayList<Node>list){
		if(list.size()==0){
			return false;
		}
		for(Node node : list){
			if(matrix[node.getX()][node.getY()] != path){   //see if there are still unvisited nodes
				return true;
			}
		}
		return false;
	}
	
	/**
	 * this is a method used for helping the maze generation
	 * @param list
	 * @return Node that the maze is going to visit
	 */
	private Node sketch(ArrayList<Node>list){
		ArrayList<Node>accessable = new ArrayList<Node>();
		for(Node node: list){
			if(matrix[node.getX()][node.getY()] != path){
				accessable.add(node);
			}
		}
		int size = accessable.size();
		int randomIndex = randomGenerator(0,size-1);
		Node step = accessable.get(randomIndex);
		return step;
	}
	
	/**
	 * connect the path between two nodes
	 * @param n1
	 * @param n2
	 */
	private void makePath(Node n1,Node n2){
		if(n1.getX() == n2.getX()){
			matrix[n1.getX()][n1.getY()] = path;
			matrix[n1.getX()][(n1.getY()+n2.getY())/2] = path;
			matrix[n2.getX()][n2.getY()] = path;
		}else if(n1.getY() == n2.getY()){
			matrix[n1.getX()][n1.getY()] = path;
			matrix[(n1.getX() + n2.getX())/2][n1.getY()] = path;
			matrix[n2.getX()][n2.getY()] = path;
		}
	}
	/**
	 * Generate a random number
	 * @param min
	 * @param max
	 * @return random number
	 */
	private int randomGenerator(int min,int max){
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
	
	

	
	
	@Override
	public Node giveHints(Batman player) {
		// TODO Auto-generated method stub
		
		if(pathRecording.size() == 0){
			System.out.println("search failed");
			return null;
		}
		Node current = player.getPosition();
		ArrayList<Node>path = player.SearchPath(pathRecording);
		Heuristic h = new Heuristic();
		if(path.size() == 0){
			return new Node(difficulty -2, difficulty -1);
		}
		Node min = path.get(0);
		double mindis = h.HeristicCalculation(current, min,matrix);
		for(Node n : path){
			double dis = h.HeristicCalculation(current, n,matrix);
			if(dis < mindis){
				min = n;
				mindis = dis;
			}
		}
		min.swap();
		
		
		return min;
	}
	
	
	
	/**
	 * return a random move based on the maze and the strategy enemy is adopting
	 * @param n
	 * @return Node the enemy is moving to
	 */
	public Node randomMove(Node n){   //used by the game modes
		int strategy = randomGenerator(0,1);
		ArrayList<Node>possibleMove = getPossibleMove(n);
		int x = n.getX();
		int y = n.getY();
		if(strategy == 0){
			Node move = new Node(x-1,y);
			if(ifGo(move)){
				return move;
			}
			int rand = randomGenerator(0,3);
			if(rand == 0){
				rand = randomGenerator(0,pathRecording.size()-1);
				return pathRecording.get(rand);
			}
			rand = randomGenerator(0,possibleMove.size()-1);
			return possibleMove.get(rand);	
		}else{
			Node move = new Node(x,y-1);
			if(ifGo(move)){
				return move;
			}
			int rand = randomGenerator(0,4);
			if(rand == 0){
				rand = randomGenerator(0,pathRecording.size()-1);
				return pathRecording.get(rand);
			}
			rand = randomGenerator(0,possibleMove.size()-1);
			return possibleMove.get(rand);	
		}
		
		
		
		
	}
	
	
	/**
	 * 
	 * @param n
	 * @return all of possible Moves
	 */
	public ArrayList<Node>getPossibleMove(Node n){
		ArrayList<Node>possibleMove = new ArrayList<Node>();
		int x = n.getX();
		int y = n.getY();
		Node n1 = new Node(x+1,y);
		Node n2 = new Node(x-1,y);
		Node n3 = new Node(x,y+1);
		Node n4 = new Node(x,y-1);
		if(ifGo(n1)){
			possibleMove.add(n1);
		}
		if(ifGo(n2)){
			possibleMove.add(n2);
		}
		if(ifGo(n3)){
			possibleMove.add(n3);
		}
		if(ifGo(n4)){
			possibleMove.add(n4);
		}
		return possibleMove;
	}
	/**
	 * see if the place could be moved to 
	 * @param n
	 * @return true if a place could be accessed and false otherwise
	 */
	public boolean ifGo(Node n){
		int x = n.getX();
		int y = n.getY();
		if(x >= 0 && x <= difficulty -1 && y >= 0 && y <= difficulty -1 && matrix[y][x]!=wall){
			return true;
		}
		return false;
	}
	/**
	 * see if a node has reached the end
	 * @param node
	 * @return true if node has reached end and false otherwise
	 */
	public boolean ifFinish(Node node){
		int x = node.getX();
		int y = node.getY();
		if(x == difficulty -1 && y==difficulty-2){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @return ArrayList contains the position of all of the hostage
	 */
	public ArrayList<Node>getHostage(){
		return hostage;
	}
	
	/**
	 * @return all the crazy nodes
	 */
	public ArrayList<Node>getCrazyNodes(){
		return crazyNodes;
	}
	
	

}
