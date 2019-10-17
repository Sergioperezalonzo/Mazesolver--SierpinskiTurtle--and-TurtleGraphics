import javax.swing.JFrame;

/**
 * A program that makes Sierpinski Triangles by telling a virtual "turtle"
 * how to move around in the window.  The turtle has a "pen".
 * If the pen is down when the turtle is moved, it leaves a
 * trail.
 */
public class SierpinskiTurtle {

	private static JFrame window;  // The window that holds the turtle panel.

	private static TurtlePanel turtle; // The drawing area, thought of as the "turtle".
	                                   // Drawing is done by calling methods such as
	                                   // turtle.forward() to make the turtle move.


	public static void main(String[] args) throws InterruptedException {
		turtle = new TurtlePanel();  // Makes a panel with default preferred size, 600 pixels.
		window = new JFrame("Turtle Graphics Test");
		window.setContentPane(turtle);
		window.pack();
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		runSierpinskiDemo(); // Do the drawing!
	}


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


	/**
	 * Draws Sierpinsky triangles with recursion levels from 0 to 9,
	 * with a delay of one second between them.
	 */
	private static void runSierpinskiDemo() {
		for (int i = 0; i < 10; i++) { 
			if (i < 4) {
				turtle.setAutoDelay(30); // I want to see the process
				turtle.setTurtleIsVisible(true);
			}
			else {
				turtle.setAutoDelay(0); // draw as fast as possible, and don't show turtle.
				turtle.setTurtleIsVisible(false);
			}
			turtle.clear();
			turtle.penUp();
			turtle.moveTo(-9,8);  // in the upper right of the drawing
			turtle.string("Sierpinski Triangle, level " + i);
			turtle.moveTo(-8,-6); // lower left corner of the main triangle.
			turtle.penDown();
			sierpinskiTriangle(16,i);
			delay(2000); // to seconds to look at the picture
		}
	}


	/**
	 * Draws a Sierpinsky triangle.  The starting turtle position is the lower
	 * left corner of the triangle, and the base of the triangle is aligned
	 * in the direction that the turtle is pointing when the method is called.
	 * Postcondition:  The turtle is returned to its original position and heading.
	 * @param size gives the length of each of the sides of the triangle.
	 * @param recursionLevel a non-negative integer telling how deep the
	 *    recursion should go.  A value of zero produces a simple triangle.
	 *    Larger values give more complex pictures. (Only small values are
	 *    practical, since after a recursion level of 9 or so, no more detail
	 *    is added to the picture, and the time it takes to draw the picture
	 *    increases exponentially.)
	 */
	private static void sierpinskiTriangle(double size, int recursionLevel) {
		if (recursionLevel == 0) {  // Just draw a regular triangle.
			turtle.forward(size);
			turtle.turn(120);
			turtle.forward(size);
			turtle.turn(120);
			turtle.forward(size);
			turtle.turn(-240); // Return to original direction.
		}
		else {  // Draw three smaller Sierpiensky triangles, with a lower recursion level.

			sierpinskiTriangle(size/2, recursionLevel - 1);  // first triangle

			turtle.forward(size/2);  // Move to far end of first side.
			sierpinskiTriangle(size/2, recursionLevel - 1);  // second triangle
			turtle.back(size/2);  // Move back to starting point.

			turtle.turn(60);
			turtle.forward(size/2); // Move to far end of third side.
			turtle.turn(-60);
			sierpinskiTriangle(size/2, recursionLevel - 1);  // third triangle
			turtle.turn(60);
			turtle.back(size/2);  // Move back to starting point.
			turtle.turn(-60);
		}
	}

}