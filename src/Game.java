import java.awt.EventQueue;
/**
 * Game is a class used to call menu
 * @author Yang Ge, Renwen Zhang, Bowei Liu, Lena Nesterenko
 *
 */
public class Game {
	public static void main(String[]args){
			EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {				
            	Menu menu;
				try {
					menu = new Menu();
					menu.setVisible(true);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//menu.setVisible(true);
            }
        });
		//Maze test = new Maze(41);
		//test.show();
	}
}
