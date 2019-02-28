/**
 * CSYE6200 Assignment 5AB
 * FileName: BGGenerationSet.java
 * @author hemalkumar gadhiya
 * NU ID: 001460577
 */
package edu.neu.cyse6200.bg;
import java.util.Observable;


/**
 * BGGenerationSet is Observable class and implements runnable
 * @author hemal gadhiya
 *
 */
public class BGGenerationSet extends Observable implements Runnable
{
	protected BGDraw observer = null;
	protected BGRule rl;
	protected int maxGen;
	protected String selected;
	private static BGGenerationSet instance = null;
	
	/**
	 * COnstructor
	 * @param rl : BGRule
	 * @param maxGen : max no. of generation
	 */
	public BGGenerationSet(BGRule rl, int maxGen)
	{
		this.rl = rl;
		this.maxGen = maxGen;
		observer = BGDraw.getinstance();  // get the singleton instance of BGDraw
		this.addObserver(observer);       // add BGDraw as the observer 
	}
	
	/**
	 * This method will notify the observer 
	 * 	 @param s : stem added is being notified to observer
	 */
	public void notify(Stem s)
	{
		setChanged();
		notifyObservers(s);
	}
	
	/**
	 * thread calls this method when it is started
	 */
	@Override
	public void run() {

		BGGeneration gen = new BGGeneration(rl,maxGen);
		if(selected == "Rule1")          // if rule 1 is selected generate tree accordingly
		{
			gen.generate();	
		}
		
		else if( selected == "Rule2")    // if rule 2 is selected generate tree accordingly
		{
			gen.generate2();
		}
		
		for(Stem s : gen.stemlist)      // looping through stemlist when tree is generated and notifying observer about it
		{
			notify(s);
			try {
				Thread.sleep(50L);     //pause for 50 milli seconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
				
		}	
		
	}

}
