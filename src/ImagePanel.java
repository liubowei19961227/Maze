import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * ImagePanel is used to for generate a panel which contain a image
 * @author Yang Ge, Renwen Zhang, Bowei Liu, Lena Nesterenko
 *
 */
public class ImagePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private Image image;
		//	private int iWidth2;
		//	private int iHeight2;
	/**	
	 * Construct imagePanel
	 * @param image
	 */
	public ImagePanel(Image image){
		this.image = image;
	}
			
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);             
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);	
		}
			
}