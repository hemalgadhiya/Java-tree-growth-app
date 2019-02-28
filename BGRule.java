/**
 * CSYE6200 Assignment 5AB
 * FileName: BGRule.java
 * @author hemalkumar gadhiya
 * NU ID: 001460577
 */
package edu.neu.cyse6200.bg;
import java.util.ArrayList;

public class BGRule 
{
	Stem base;
	protected ArrayList<Stem> stemlist1;
	
	/**
	 * Constructor
	 * @param s : Base Stem from which the growth starts
	 */
	public BGRule(Stem s)
	{
		this.base = s;
		stemlist1 = new ArrayList<Stem>();
	}
	
	/**
	 * RULE 1
	 * @param level : number of generation the plant grows
	 * @param stemlist : stores all the stems
	 * 
	 * rule1 is a recursive function that splits the stem into three child stem 
	 * if level is less than 4 otherwise it splits the stem into two child stem
	 */
	
	public void rule1(int level, ArrayList<Stem> stemlist)
	{
		
		if (level == 0) return;    //stop condition
		
		int scaling_factor = 17;   
		
		
		base.end_x = base.getStart_x() + (int)(Math.cos(Math.toRadians(base.getAngle())) * base.getLength() * scaling_factor);  //calculates the end x-cordiante of the base stem     
		base.end_y = base.getStart_y() + (int)(Math.sin(Math.toRadians(base.getAngle())) * base.getLength() * scaling_factor);  //calculates the end y-cordiante of the base stem
		
		stemlist.add(base);   // add base stem to stemlist
	
		if(level < 4) 
		{
			base.setLength((base.getLength() * 0.50));     //reduce the length of stem by 0.5
			
			Stem left_child = new Stem(base.end_x,base.end_y,base.getAngle() - 45, base.getLength());      //left_child stem with angle deviation of -45 degree
			Stem right_child = new Stem(base.end_x,base.end_y,base.getAngle() + 45, base.getLength());     //right child stem with angle deviation of 45 degree
			Stem center_child = new Stem(base.end_x, base.end_y, base.getAngle() + 90, base.getLength());  // center child stem with angle deviation of 90 degree
			
		
			setBase(right_child);       //right child is set as the base stem
			rule1(level-1,stemlist);  	//recursive function
			
			setBase(left_child);     	//left child is set as the base stem
			rule1(level-1,stemlist);    //recursive function
			
			setBase(center_child);   	//left child is set as the base stem
			rule1(level-1,stemlist);    //recursive function
	
		}
		else
		{
			base.setLength((base.getLength() * 0.66));   //reduce the length of stem by 0.66
			
			Stem left_child = new Stem(base.end_x,base.end_y,base.getAngle() - 25, base.getLength());      //left_child stem with angle deviation of 25 degree
			Stem right_child = new Stem(base.end_x,base.end_y,base.getAngle() + 25, base.getLength());     //right child stem with angle deviation of -25 degree

						
			setBase(right_child);      //right child is set as the base stem
			rule1(level-1,stemlist);  //recursive function
			
			setBase(left_child);        //left child is set as the base stem
			rule1(level-1,stemlist);    //recursive function
		
		}
	}

	/**
	 * RULE 2
	 * @param level : the max number of generation
	 * @param stemlist : stores all the stems generated
	 */
	
	public void rule2(int level , ArrayList<Stem> stemlist)
	{
		if(level == 0)   // stop condition
			return;
		
		
		int scaling_factor = 14;
		
		base.end_x = base.getStart_x() + (int)(Math.cos(Math.toRadians(base.getAngle())) * base.getLength() * scaling_factor);  //calculates the end x-cordinate
		base.end_y = base.getStart_y() + (int)(Math.sin(Math.toRadians(base.getAngle())) * base.getLength() * scaling_factor);  // calculates the end y-cordinate
		
		
		stemlist.add(base);   //add the base to stemlist
		
		base.setLength(base.getLength()*0.75);
		
		Stem left_child = new Stem(base.end_x,base.end_y,base.getAngle()-15, base.getLength());      //left child stem with angle deviation of 15 degree
		Stem right_child = new Stem(base.end_x,base.end_y,base.getAngle() + 15, base.getLength());   //Right child stem with angle deviation of 15 degree 
		
		setBase(right_child);      		 //right child is set as the base stem
		rule2(level-1 , stemlist);  	//recursive function
		
		setBase(left_child);    		//left child is set as the base stem
		rule2(level-1 , stemlist);    //recursive function
	
		
	}
	
	/**
	 * method to print the stems from stemlist
	 */
	public void print()
	{
		for(Stem i : stemlist1)
		{
			System.out.println(i);
		}
	}
	
	public Stem getBase() 
	{
		return base;
	}

	public void setBase(Stem base) 
	{
		this.base = base;
	}

}
