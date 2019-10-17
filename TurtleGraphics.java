/**
 *A program call Turtle Graphics that draws different designs.The drawings are draw with recursive methods
 *@author Sergio Perez 
 *@version 1.0
 */
import javax.swing.JFrame;

import java.awt.Color;

/**
 * A program that makes pictures by telling a virtual "turtle"
 * how to move around in the window.  The turtle has a "pen".
 * If the pen is down when the turtle is moved, it leaves a
 * trail.
 */
public class TurtleGraphics {

	private static JFrame window;  // The window that holds the turtle panel.

	private static TurtlePanel turtle; // The drawing area, thought of as the "turtle".
	                                   // Drawing is done by calling methods such as
	                                   // turtle.forward() to make the turtle move.

	
	
	/**
	 *A subroutine that draws a square.The square is succesfully drawn with recursive methods
	 *@param size takes a double
	 *@param level takes a int 
	 */
	
	public static void square(double size, int level) {
		
		if(level == 0){
				turtle.forward(size);
			
		}else {
			
			square(size/3, level-1);
			turtle.turn(90);
			square(size/3, level-1);
			turtle.turn(-90);
			square(size/3, level-1);
			turtle.turn(-90);
			square(size/3, level-1);
			turtle.turn(90);
			square(size/3, level-1);
		}
		
		
	}
	
	/**
	 *A subroutine that draws a randomTree.The randomTree is succesfully drawn with recursive methods
	 *@param size takes a double
	 *@param level takes a int 
	 */
	public static void randomTree(double size, int level) {
			
		
		int convert = (int) size;
		double s = Math.random()*size/2 + size/2+0.1;
		
		if(level == 0) {
			
			turtle.forward(s);
			turtle.back(s);
			
		} else {
			
			turtle.randomColor();
			turtle.forward(s/2);
			turtle.turn(45);
			randomTree(s/3,level-1);
			turtle.turn(-90);
			randomTree(s/3,level-1);
			turtle.turn(45);
			randomTree(s/2,level-1);
			turtle.back(s/2);
			
		}
	}
	
	/**
	 *A subroutine that draws a tree.The tree is succesfully drawn with recursive methods
	 *@param size takes a double
	 *@param level takes a int 
	 */
	
	public static void Tree(double size, int level) {

		if(level == 0){
			
			turtle.color(Color.GREEN);
			turtle.forward(size);
			turtle.back(size);
			
		} else {
			
			turtle.color(Color.RED);
			turtle.lineWidth(size/2);
			turtle.forward(size/2);
			turtle.turn(45);
			turtle.color(Color.RED);		
			Tree(size/2,level-1);
			turtle.turn(-90);
			turtle.color(Color.RED);	
			Tree(size/2,level-1);
			turtle.turn(45);
			turtle.color(Color.RED);
			turtle.back(size/2);

		}
	}
	
	/**
	 *A subroutine that draws a koch.The koch is succesfully drawn with recursive methods
	 *@param size takes a double
	 *@param level takes a int 
	 */
	public static void koch(double size, int level) {
		
		if(level == 0){
			
			turtle.forward(size);
			
		} else {
			
			koch(size/3, level-1);
			turtle.turn(60);
			koch(size/3, level-1);
			turtle.turn(-120);
			koch(size/3, level-1);
			turtle.turn(60);
			koch(size/3, level-1);
			
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		turtle = new TurtlePanel();  // Makes a panel with default preferred size, 600 pixels.
		window = new JFrame("Turtle Graphics Test");
		window.setContentPane(turtle);
		window.pack();
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		//takes the turtle to the left side of the window 
		turtle.penUp();
		turtle.back(10);
		turtle.penDown();
		//draws the koch 
		koch(20, 8);
		delay(4000);
		turtle.penUp();
		turtle.home();
		turtle.penDown();
		//clears the window, takes the koch design 
		turtle.clear();
		
		///takes the turtle to the left side of the window 
		turtle.penUp();
		turtle.back(10);
		turtle.penDown();
		square(20, 8);//draws the square design 
		
		delay(4000);
		turtle.penUp();
		turtle.home();
		turtle.penDown();
		//takes the square design 
		turtle.clear();
		
		turtle.penUp();
		turtle.turn(270);
		turtle.forward(9);
		turtle.turn(180);
		turtle.penDown();
		//draws a tree with different legths 
		Tree(18,4);
		
		
		delay(4000);
		turtle.penUp();
		turtle.home();
		turtle.penDown();
		//takes the tree design 
		turtle.clear();
		
		turtle.penUp();
		turtle.turn(270);
		turtle.forward(9);
		turtle.turn(180);
		turtle.penDown();
		//draws a random colorful tree 
		randomTree(20,9);
		
		
		delay(4000);
		turtle.penUp();
		turtle.home();
		turtle.penDown();
		//clears the window from the random tree design 
		turtle.clear();
		
		
		turtle.setTurtleIsVisible(false);
		turtle.setAutoDelay(10);
		//draws the circle with multiple lines 
		for (int i = 0; i < 360; i++) {
			
			turtle.randomColor();
			double dist = 10*Math.random();
			turtle.forward(dist);
			turtle.back(dist);
			turtle.turn(1);
		}

		delay(4000);
		turtle.penUp();
		turtle.home();
		turtle.penDown();
		turtle.clear();
		//draws a square with a for loop method
		for (int i = 0; i < 4; i++ ) {
			
			turtle.setAutoDelay(1000);
			turtle.forward(5);
			turtle.turn(90);
			
		}
		
	} // end Main
	
	/**
	 * A short utility method for inserting a delay into a program.
	 * When a thread calls this method, it "sleeps" for the specified
	 * time.  Other threads -- in this case, the GUI thread that repaints
	 * the picture -- can continue in the meantime.  (This method was
	 * written because Thread.sleep() can throw a checked exception of
	 * type InterruptedException that needs to be handled.  This
	 * delay() method can be called without worrying about the
	 * exception.)
	 * @param milliseconds The number of milliseconds to sleep.  If this
	 * parameter is less than or equal to zero, this method does nothing.
	 */
	private static void delay( int milliseconds ) {
		if (milliseconds > 0) {
			try {
				Thread.sleep(milliseconds);
			}
			catch (InterruptedException e){
			}
		}
	}

} // end TurtleGraphics
