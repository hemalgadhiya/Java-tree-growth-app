/**
 * CSYE6200 Assignment 5AB
 * FileName: TreeApp.java
 * @author hemalkumar gadhiya
 * NU ID: 001460577
 */
package edu.neu.cyse6200.bg;
import edu.neu.cyse6200.ui.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TreeApp extends BGApp
{
	private static Logger log = Logger.getLogger(WolfApp.class.getName());
	private JPanel mainPanel = null;
	private JPanel northPanel = null;
	private JButton startbtn = null;
	private JButton stopbtn = null;
	private JComboBox ruleSelect = null;   
	private boolean isStop;
	private boolean isStart;
	private boolean isSelected;
	protected String selected;
	protected BGGenerationSet genset = null;
	private Thread th;
	
	/**
	 * Constructor
	 */
	public TreeApp()
	{
		frame.setSize(1366,700);
		frame.setTitle("Tree APP");
		menuMgr.createDefaultActions();
		showUI();
	}
	
	/**
	 * returns the main Panel that has the north panel and the draw panel
	 */
	@Override
	public JPanel getMainPanel() 
	{
		
		mainPanel = new JPanel();    // new J panel
		mainPanel.setLayout(new BorderLayout());
    	mainPanel.add(BorderLayout.NORTH, getNorthPanel());  // add north panel to main panel
    	log.info("northpanel added");
    	
    	bgPanel = BGDraw.getinstance();     // create a singleton instance of BGDraw
    	mainPanel.add(BorderLayout.CENTER, bgPanel);  // add draw panel to main panel
		return mainPanel;
	}
	
	/**
	 * Creates North Panel
	 * @return north panel
	 */
	public JPanel getNorthPanel()
	{
	
		northPanel = new JPanel();
		log.info("North Panel created");
		northPanel.setLayout(new FlowLayout());
		northPanel.setBackground(Color.BLACK);   // set the color of panel as black
		
    	ruleSelect = new JComboBox ();    // new combo box
    	ruleSelect.addItem("Select option");   // add options to the combo box
    	ruleSelect.addItem("Rule1");
    	ruleSelect.addItem("Rule2");
		ruleSelect.addActionListener(this);      //allow the app to hear button press
		
		northPanel.add(ruleSelect);   // add combo box to north panel
		
		JLabel lbl = new JLabel("Max Gen: ");
		lbl.setForeground(Color.GREEN);
		
		northPanel.add(lbl);
		maxGen = new JTextField();     // new text field
		maxGen.setColumns(2);      
		maxGen.addActionListener(this);   //allow the app to hear button press
		northPanel.add(maxGen);           // add text field to north panel
		
		
	
    	startbtn = new JButton("Start");
    	startbtn.addActionListener(this);  // Allow the app to hear about button pushes
    	northPanel.add(startbtn);
    	
    	stopbtn = new JButton("Stop"); // Allow the app to hear about button pushes
    	stopbtn.addActionListener(this);
    	northPanel.add(stopbtn);
    		
    	return northPanel;
	}

	/**
	 * Task that a combo box performs when pressed
	 * 
	 * @param selected
	 */
	protected void combobox(String selected)
	{
		log.info("selected " + selected);
		
			isSelected = true;   //is rule selected ?
			isStart = false;     // Start should not start before rule selection
			this.selected = selected;    // set selected as the rule selected
			isStop = true;        // stop should not work before rule selection
			bgPanel.startGrowth = false;    // set growth start as false
			bgPanel.repaint();        // repaint when rule selcted
		
	}
	
	/**
	 * task start button performs when presse
	 */

	protected void startbtn()
	{
		if(th != null && th.isAlive())    // checks whether thread is not null and still alive
		{
			th.resume();        // if condition matched resume the thread
		}
		
		isStart = true;       // start pressed
		isStop = false;       // don't press stop before start
		bgPanel.startGrowth = true;     //start the growth
		
		if(isSelected)     // is rule selected
		{
		
			
				Stem s = new Stem(bgPanel.getWidth()/2,bgPanel.getHeight(),-90,10);    // new base stem
				BGRule bgr = new BGRule(s);                                           
				genset = new BGGenerationSet(bgr,Integer.parseInt(maxGen.getText()));   
				
				genset.selected = this.selected;    //set the rule selected
				th = new Thread(genset);         // new runnable thread
				th.start();                     // start the thread
			
		}
		
	}
	/**
	 * Task the Stop button performs 
	 */
	protected void stopbtn()
	{
		if(th.isAlive())    // if thread still running pause it
			th.suspend();
		isStop = true;      // stop pressed
		isSelected = false;  // after stop pressed select the rule again 
		isStart = false;      // after stop pressed press start again to perform its task
		
		bgPanel.startGrowth = false;    // stop the growth
		bgPanel.repaint();      // repaint 
		
	}
	
	
	/**
	 * actions performed when the buttons in north panel are pressed
	 */
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		log.info("We received an ActionEvent ");
		if (ae.getActionCommand() == "Start")
		{
			System.out.println("Start pressed");
			startbtn();
		}
		
			
		else if (ae.getActionCommand() == "Stop")
		{
			System.out.println("Stop pressed");
			stopbtn();
			
		}
		else if (ae.getSource() == maxGen)
		{
			System.out.println("Entered Generation: " + maxGen.getText());   // prints the entered max generation
		
		}
		else 
		{
			JComboBox ruleSelect = (JComboBox) ae.getSource();
			Object selected = ruleSelect.getSelectedItem();
			combobox(selected.toString());
		}
	}
	
	
	
	@Override
	public void windowActivated(WindowEvent arg0) {
		
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
				
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		
		
	}
	public static void main(String[] args) {
		TreeApp treeapp = new TreeApp();
		
		log.info("TreeApp started");
	}

	

}
