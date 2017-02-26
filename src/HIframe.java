
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
/**
 * HIframe is used to generate a Frame when player rescued a hostage,
 * the frame would be closed after one second
 * @author Yang Ge, Renwen Zhang, Bowei Liu, Lena Nesterenko
 */


public class HIframe extends Frame implements Runnable{

	
	private static final long serialVersionUID = 1L;



	public void showWindow(){
		this.setSize(300, 400);
		this.setLocation(300, 200);
	
	    ImageIcon ii = new ImageIcon(this.getClass().getResource("image/RobinLogo.jpg"));
	    Image ii1 = ii.getImage();
	    //JLabel lb = new JLabel(ii);
	    //this.add(lb);
		ImagePanel1 HI = new ImagePanel1(ii1);
		
	    add(HI);
		this.setVisible(true);

	}

	class ImagePanel1 extends JComponent {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Image image;
	    public ImagePanel1(Image image) {
	        this.image = image;
	    }
	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(image, 0, 0, getWidth(), getHeight(),this);
	    }
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			setVisible(false);  
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
