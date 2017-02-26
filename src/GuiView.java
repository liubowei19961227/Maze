/**
 * GuiView is a helper class to view the maze of the moving of the agent
 * 
 * @author Yang Ge, Renwen Zhang, Bowei Liu, Lena Nesterenko
 *
 */
public class GuiView {
	/**
	 * This is used to generate wall based on maze m to Grid gamePanel(easy, medium, hard mode)
	 * @param gamePanel
	 * @param m
	 */
	public void gamePanelView(Grid gamePanel, Maze m){
		int[][]matrix = m.getMatrix();
		int difficulty = m.getDifficulty(); 
		for(int x = 0; x < difficulty; x++) {
 			for (int y = 0; y < difficulty; y++) {
 	    		if (matrix[y][x] == 0) {
 	    			
 	    			gamePanel.fillCell(x, y);
 	    		}
 	    		
     		}
     	}
	}
	
	/**
	 * This is used to generate wall based on maze m to Grid gamePanel(double mode)
	 * @param gamePanel
	 * @param m
	 */
	public void doublePanelView(Grid gamePanel, Maze m){
		int[][]matrix = m.getMatrix();
		int difficulty = m.getDifficulty(); 
		for(int x = 0; x < difficulty; x++) {
 			for (int y = 0; y < difficulty; y++) {
 	    		if (matrix[y][x] == 0) {
 	    			
 	    			gamePanel.doubleFillCell(x, y);
 	    		}
 	    		
     		}
     	}
	}
	
	/**
	 * this method is used to form wall to Grid gamePanel(adventureGameNext) based on the maze m and the
	 * position of the player 
	 * @param gamePanel
	 * @param m
	 * @param position
	 */
	public void adventureTwo(Grid gamePanel, Maze m, Node position){
		int difficulty = m.getDifficulty(); 
		int[][]matrix = m.getMatrix();
		int a = -1;
		int b = -1;
		for(a = -1; a <= 1; a++){
			for(b = -1; b <= 1; b++){
				if(position.getY()+a <= difficulty-1 && position.getY()+a >= 0 &&
						position.getX()+b <= difficulty -1 && position.getX()+b >= 0){
					if (matrix[position.getX()+b][position.getY()+a] == 0) {
	 	    			
	 	    			gamePanel.fillCell(position.getY()+a,position.getX()+b);
	 	    		}
				}
			}
		}
		
	}
	
	/**
	 * The player goes up
	 * @param adventureGame
	 * @param player
	 * @param m
	 */
	public void viewMoveUp(Grid adventureGame, Batman player, Maze m){
		int[][]matrix = m.getMatrix();
		int difficulty = m.getDifficulty();  
		if(!ifStart(player.getPosition().getY(),player.getPosition().getX(),difficulty)){
              if(matrix[player.getPosition().getX()-1][player.getPosition().getY()] == 1){
              	player.go_up(m);
              	adventureGame.current(player.getPosition().getY(),player.getPosition().getX());
              	
              }	
          }    
	}
	
	/**
	 * The player goes down
	 * @param adventureGame
	 * @param player
	 * @param m
	 */
	public void viewMoveDown(Grid adventureGame, Batman player, Maze m){
		int[][]matrix = m.getMatrix();
		int difficulty = m.getDifficulty(); 
		if(!ifFinish(player.getPosition().getY(),player.getPosition().getX(),difficulty)){
             if(matrix[player.getPosition().getX()+1][player.getPosition().getY()] == 1){
             	player.go_down(m);
             	adventureGame.current(player.getPosition().getY(),player.getPosition().getX());
             	
             	
             }	
         } 
		
	}
	
	/**
	 * The player goes left
	 * @param adventureGame
	 * @param player
	 * @param m
	 */
	public void viewMoveLeft(Grid adventureGame, Batman player, Maze m){
		 int[][]matrix = m.getMatrix();
		 if(matrix[player.getPosition().getX()][player.getPosition().getY()-1] == 1){
         	player.go_left(m);
         	
         	adventureGame.current(player.getPosition().getY(),player.getPosition().getX());
         	
         	
         	
         }	
	}
	/**
	 * The player goes right
	 * @param adventureGame
	 * @param player
	 * @param m
	 */
	public void viewMoveRight(Grid adventureGame, Batman player, Maze m){
		 int[][]matrix = m.getMatrix();
		 if(matrix[player.getPosition().getX()][player.getPosition().getY()+1] == 1){
         	player.go_right(m);
         
         	adventureGame.current(player.getPosition().getY(),player.getPosition().getX());
         	
         	
         }	
	}
	
	/**
	 * Check if the player is at starting position
	 * @param x
	 * @param y
	 * @param difficulty
	 * @return true if the player is at starting position, false otherwise
	 */
	private boolean ifStart(int x, int y, int difficulty){
		if(x==1 && y == 0){
			return true;
		}
		return false;
	}
	/**
	 * Check if the player is at exit position
	 * @param x
	 * @param y
	 * @param difficulty
	 * @return true if the player is at exit position, false otherwise
	 */
	private boolean ifFinish(int x, int y, int difficulty){
		if(x == difficulty - 2 && y == difficulty - 1){
			return true;
		}
		return false;
	}
	
	
	
	
}
