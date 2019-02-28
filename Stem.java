/**
 * CSYE6200 Assignment 5AB
 * FileName: Stem.java
 * @author hemalkumar gadhiya
 * NU ID: 001460577
 */

package edu.neu.cyse6200.bg;
import java.util.ArrayList;


public class Stem 
{
	private int start_x;
	private int start_y;
	protected int end_x;
	protected int end_y;
	private double angle;
	private double length;
	protected ArrayList<Stem>childlist;
	
/**
 * Constructor
 * @param start_x : Starting X cordinate of the Stem
 * @param start_y : Starting Y cordinate of the Stem
 * @param angle : The angle by which stem grows
 * @param length : Lenght of the Stem
 */
	public Stem(int start_x, int start_y, double angle, double length)  //constructor
	{
		this.start_x = start_x;
		this.start_y = start_y;
		this.angle = angle;
		this.length = length;
		childlist = new ArrayList<Stem>();
	}

	public int getStart_x() {
		return start_x;
	}

	public void setStart_x(int start_x) {
		this.start_x = start_x;
	}

	public int getStart_y() {
		return start_y;
	}

	public void setStart_y(int start_y) {
		this.start_y = start_y;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public String toString()
	{
		return(start_x + "\t" + end_x + "\t" + start_y + "\t" + end_y);
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

}
