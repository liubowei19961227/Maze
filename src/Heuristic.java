/**
 * Heuristic class is used to calculate the heuristic
 * @author Yang Ge, Renwen Zhang, Bowei Liu, Lena Nesterenko
 *
 */

public class Heuristic {
	//targetX and targetY is the exit
	public double HeristicCalculation(Node n1, Node n2, int[][]matrix){
		int x1 = n1.getX();
		int y1 = n1.getY();
		int targetX = n2.getX();
		int targetY = n2.getY();
		double h = Math.sqrt((targetX-x1) * (targetX-x1) + (targetY-y1)*(targetY-y1));
		if(x1 == targetX){
			if(y1 > targetY){
				for(int j = y1-1 ; j > targetY; j--){
					if(matrix[x1][j]== 0){
						h += 100;
					}
				}
			}else if(targetY > y1){
				for(int j = targetY-1; j> y1;j--){
					if(matrix[x1][j]== 0){
						h += 100;
					}
				}
			}
		}else if(y1 == targetY){
			if(x1 > targetX){
				for(int j = x1 -1 ; j > targetX ; j--){
					if(matrix[j][y1] == 0){
						h += 100;
					}
				}
			
			
			}else if(targetX > x1){
				
				for(int j = targetX -1 ; j > x1 ; j--){
					if(matrix[j][y1] == 0){
						h += 100;
					}
				}
				
			}
		}
		
	
		return h;
	}
	







}



