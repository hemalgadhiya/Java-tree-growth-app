/**
 * CSYE6200 Assignment 5AB
 * FileName: BGGeneration.java
 * @author hemalkumar gadhiya
 * NU ID: 001460577
 */
package edu.neu.cyse6200.bg;

import java.util.ArrayList;

public class BGGeneration 
{
	protected BGRule rule = null;
	int maxGen;
	protected ArrayList<Stem> stemlist;
	
	/**
	 * Constructor
	 * @param rule : rule is passed for generating the tree
	 * @param maxGen : maximum number of generation the tree will grow
	 */
	public BGGeneration(BGRule rule, int maxGen)
	{
		this.rule = rule;
		this.maxGen = maxGen;
		stemlist = new ArrayList<Stem>();
	}
	
	/**
	 * This method will generate the entire tree according to Rule 2
	 */
	public void generate()
	{
		rule.rule1(maxGen , stemlist);
	}
	
	/**
	 * This method will generate the entire tree according to Rule 2
	 */
	public void generate2()
	{
		rule.rule2(maxGen, stemlist);
	}
	
	
	
	

}
