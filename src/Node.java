import java.util.ArrayList;
/**
 * Node stores the coordinate
 */
import java.util.Random;
public class Node {
	private int x;
	private int y;
	/**
	 * Constructor
	 * @param x
	 * @param y
	 */
	public Node(int x,int y){
		this.x = x;
		this.y = y;
	}
	/**
	 * Return "x" field data 
	 * @return x
	 */
	public int getX(){
		return this.x;
	}
	/**
	 * Return "y" field data
	 * @return y
	 */
	public int getY(){
		return this.y;
	}
	
	/**
	 * swap x and y
	 */
	public void swap(){
		int temp = x;
		x = y;
		y = temp;
	}
	
	
	/**
	 * check if the node is in the maze or not
	 * @param node
	 * @param difficulty
	 * @return true if the node is in the maze, false otherwise 
	 */
	private boolean check(Node node,int difficulty){
		if(node.getX() >=0 && node.getX()<=difficulty-1 && node.getY()>=0 && node.getY()<=difficulty-1){
			return true;
		}
		return false;
	}
	/**
	 * This method is used to generate a random number
	 * @param min
	 * @param max
	 * @return a number which is randomly generated
	 */
	public int randomGenerator(int min,int max){
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	/**
	 * 
	 * @param difficulty
	 * @return list of accessed nodes
	 */
	public ArrayList<Node>getNeibour(int difficulty){
		ArrayList<Node>list = new ArrayList<Node>();
		Node n1 = new Node(this.x+2,this.y);
		Node n2 = new Node(this.x-2,this.y);
		Node n3 = new Node(this.x,this.y-2);
		Node n4 = new Node(this.x,this.y+2);
		if(check(n1,difficulty)){
			list.add(n1);
		}
		if(check(n2,difficulty)){
			list.add(n2);
		}
		if(check(n3,difficulty)){
			list.add(n3);
		}
		if(check(n4,difficulty)){
			list.add(n4);
		}
		return list;
		
	}
	/**
	 * check if the node obj is equal to the current node or not
	 * @param obj
	 * @return true if equal, false otherwise
	 */
	public boolean equal(Node obj){
		if(this.x == obj.getX() && this.y == obj.getY()){
			return true;
		}
		return false;
	}
	

}
