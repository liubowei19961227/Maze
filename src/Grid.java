
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import javax.swing.JPanel;
/**
 * Grid is a class that draws all things in maze game
 * @author Yang Ge, Renwen Zhang, Bowei Liu, Lena Nesterenko
 *
 */


public class Grid extends JPanel {
	private ImageIcon background;
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Point> fillCells;// the list contains all coordinates of maze's wall 
	private List<Point> fillCells2;// the list contains all coordinates of second maze's wall when playing double mode
    private Point current;//the position of player
    private Point current2;//the position of second player when playing double mode
    public Point hint;//the coordinate of hint 
    public List<Point> enemy;//the coordinates of all enemies
    public List<Point> hostage;//the coordinates of all hostages
    public int doubleFlag;//if player are playing double mode, doubleFlag would be 1, otherwise is 0
    public int mode;//0 for easy mode, 1 for medium mode, 2 for hard mode, 3 for double mode, 4 for adventure mode
    
    /**
     * Construct a Grid object
     * @param theme
     * @param flag
     * @param m, the mode number
     */
    public Grid(ImageIcon theme, int flag, int m) {
        fillCells = new ArrayList<>(25);
        fillCells2 = new ArrayList<>(25);
        doubleFlag = flag;
        hostage = new ArrayList<>();
        current = new Point(1,0);
        current2 = new Point(1,0);
        //hint = new Point();
        this.background = theme;
        mode = m;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //////////Set background
        Image image = background.getImage();                 
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        //////////Set wall
        ImageIcon wall = new ImageIcon(this.getClass().getResource("image/Wall.jpg"));
        Image w = wall.getImage();
        for (Point fillCell : fillCells) {
        
        	int cellX;
        	int cellY;
            if(mode == 0){

                cellX = 200 + (fillCell.x * 20);
                cellY = 150 + (fillCell.y * 20);
                g.drawImage(w, cellX, cellY, 20, 20, null);
        	}
            if(mode == 1){
            	cellX = 180 + (fillCell.x * 15);
                cellY = 130 + (fillCell.y * 15);
                g.drawImage(w, cellX, cellY, 15, 15, null);
            }
            if(mode == 2){
            	cellX = 150 + (fillCell.x * 10);
                cellY = 100 + (fillCell.y * 10);
                g.drawImage(w, cellX, cellY, 10, 10, null);
            }
            if(mode == 3){
            	cellX = 0 + (fillCell.x * 15);
                cellY = 130 + (fillCell.y * 15);
                g.drawImage(w, cellX, cellY, 15, 15, null);
            }    
            if(mode == 4){
            	cellX = 180 + (fillCell.x * 15);
                cellY = 130 + (fillCell.y * 15);
                g.drawImage(w, cellX, cellY, 15, 15, null);
            }  
        }
       
        for (Point fillCell2 : fillCells2) {
        	int cellX = 420 + (fillCell2.x * 15);
        	int cellY = 130 + (fillCell2.y * 15);
	        g.drawImage(w, cellX, cellY, 15, 15, null);          
        }
        ///////Set hostages
        ImageIcon host = new ImageIcon(this.getClass().getResource("image/RobinLogo.jpg"));
        Image ho = host.getImage();       
        if(hostage != null){
	        for (Point h : hostage) {
	            int cellX = 180 + (h.x * 15);
	            int cellY = 130 + (h.y * 15);	
		        g.drawImage(ho, cellX, cellY, 15, 15, null);
	        }
        }
        /////Set player(s)
        ImageIcon player = new ImageIcon(this.getClass().getResource("image/BatmanLogo.jpg"));
        Image p = player.getImage();
        if(mode == 0){
	        int cellX = 200 + (current.x * 20);
	        int cellY = 150 + (current.y * 20);
	        g.drawImage(p, cellX, cellY, 20, 20, null);
        }    
        if(mode == 1){
	        int cellX = 180 + (current.x * 15);
	        int cellY = 130 + (current.y * 15);
	        g.drawImage(p, cellX, cellY, 15, 15, null);
        } 
        if(mode == 2){
	        int cellX = 150 + (current.x * 10);
	        int cellY = 100 + (current.y * 10);
	        g.drawImage(p, cellX, cellY, 10, 10, null);
        } 
        if(mode == 3){
	        int cellX = 0 + (current.x * 15);
	        int cellY = 130 + (current.y * 15);
	        g.drawImage(p, cellX, cellY, 15, 15, null);
        }
        if(mode == 4){
	        int cellX = 180 + (current.x * 15);
	        int cellY = 130 + (current.y * 15);
	        g.drawImage(p, cellX, cellY, 15, 15, null);
        } 
        
        if(doubleFlag == 1 &&current2 != null){
        	
            int cellX2 = 420 + (current2.x * 15);
            int cellY2 = 130 + (current2.y * 15);
            g.drawImage(p, cellX2, cellY2, 15, 15, null);
        }
        ///Set hint
        if(hint != null){
        	int cellX1;
        	int cellY1;
        	if(mode == 0){
		        cellX1 = 200 + (hint.x * 20);
		        cellY1 = 150 + (hint.y * 20);
		        g.setColor(Color.BLUE);
		        g.fillRect(cellX1, cellY1, 20, 20);
        	}
        	if(mode == 1){
		        cellX1 = 180 + (hint.x * 15);
		        cellY1 = 130 + (hint.y * 15);
		        g.setColor(Color.BLUE);
		        g.fillRect(cellX1, cellY1, 15, 15);
        	}
        	if(mode == 2){
		        cellX1 = 150 + (hint.x * 10);
		        cellY1 = 100 + (hint.y * 10);
		        g.setColor(Color.BLUE);
		        g.fillRect(cellX1, cellY1, 10, 10);
        	}
        	if(mode == 3){
		        cellX1 = 0 + (hint.x * 15);
		        cellY1 = 130 + (hint.y * 15);
		        g.setColor(Color.BLUE);
		        g.fillRect(cellX1, cellY1, 15, 15);
        	}
	        if(doubleFlag == 1){
	        	cellX1 = 420 + (hint.x * 15);
		        cellY1 = 130 + (hint.y * 15);
		        g.setColor(Color.BLUE);
		        g.fillRect(cellX1, cellY1, 15, 15);
	        }
        }
        
        ////////Set enemies
        ImageIcon ene = new ImageIcon(this.getClass().getResource("image/JokerLogo.jpg"));
        Image en = ene.getImage();    
        if(enemy != null){
        	for (Point e : enemy) {
		        int cellXe = 180 + (e.x * 15);
		        int cellYe = 130 + (e.y * 15);
		        g.drawImage(en, cellXe, cellYe, 15, 15, null);
        	}  
        }
        
    }

    /**
     * Add the position of wall to fillCells
     * @param x
     * @param y
     */
    public void fillCell(int x, int y) {
        fillCells.add(new Point(x, y));
        repaint();
    }
    
   /**
    * Change the position of the player
    * @param x
    * @param y
    */
    public void current(int x, int y){
    	current = new Point(x,y);
    	repaint();
    }
    /**
     * Change the position of the second player when playing double mode
     * @param x
     * @param y
     */
    public void current2(int x, int y){
    	current2 = new Point(x,y);
    	repaint();
    }
    
    /**
     * Change the position of hint 
     * @param x
     * @param y
     */
    public void printHint(int x, int y) {
        hint = new Point(x,y);
        repaint();
    }
    
    /**
     * Change the "enemy" field data to Es
     * @param Es
     */
    public void printEnemy(List<Enemy> Es){
    	enemy = new ArrayList<Point>();
    	int i = 0;
    	for(i = 0; i < Es.size(); i++){
    		enemy.add(new Point(Es.get(i).getPosition().getX(), Es.get(i).getPosition().getY()));
    	}
    	repaint();
    }
    
    /**
     * Change the "hostage" field data to Hs
     * @param Hs
     */
    public void printHostage(List<Node> Hs){
    	hostage = new ArrayList<Point>();
    	int i = 0;
    	for(i = 0; i < Hs.size(); i++){
    		hostage.add(new Point(Hs.get(i).getX(),Hs.get(i).getY()));
    	}
    	repaint();
    }
    /**
     * Add the position of wall to fillCells and fillCells2 when playing double mode 
     * @param x
     * @param y
     */
    public void doubleFillCell(int x, int y){
    	System.out.println(x+"  "+y);
    	fillCells.add(new Point(x, y));
    	fillCells2.add(new Point(x,y));
    	System.out.println(x+"  "+y);
        repaint();
    }
    
}
