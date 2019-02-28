/**
 * CSYE6200 Assignment 5AB
 * FileName: Stem.java
 * @author hemalkumar gadhiya
 * NU ID: 001460577
 */
package edu.neu.cyse6200.bg;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;
import javax.swing.JPanel;
import edu.neu.cyse6200.ui.BGCanvas;

public class BGDraw extends JPanel implements Observer 
{

	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(BGCanvas.class.getName());
	private static BGDraw instance = null;
	protected boolean startGrowth = false;   //variable to check the growth start
    protected String selected;   // string to select the rule selected
    protected int value;       // stores max generation value
    private Stem st;
   
	/**
	 * Singleton method to get only one instance of BGDraw
	 * @return
	 */
	public static BGDraw getinstance()
	{
		if(instance==null)
		{
			instance = new BGDraw();
			System.out.println("Hello");
		}
			
		return instance;
	}
	/**
	 * The UI thread calls this method when the screen changes, or in response
	 * to a user initiated call to repaint();
	 */
	public void paint(Graphics g) {
		log.info("Draw Started");
		drawBG(g); 
	}

	
	/**
	 * draws the line based on stem cordinates
	 * @param g
	 */

	public void drawBG(Graphics g) 
	{
		//Dimension size = getSize();
		
		log.info("Drawing BG " );
		
		if(startGrowth)
		{
			g.drawLine(st.getStart_x(), st.getStart_y(), st.end_x, st.end_y);
			
		}
        
       
    	  
	}

	/**
	 * listens to the observable class 
	 * 
	 * gets the generated stem as an update with delay of 10 milli second and calls the paint method to paint that stem
	 */
	@Override
	public void update(Observable o, Object update) {
		
		st = (Stem)update;
		System.out.println(st);
		this.revalidate();     // recalculate the layout as new component is added
		this.repaint();        // calls the paint method
		
	}

}
