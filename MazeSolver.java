/**
 *A program call MazeSolver that creates a maze and solves the maze on the spot.
 *@author Sergio Perez 
 *@version 1.0
 */
import javax.swing.JFrame;
import java.awt.Color;

public class MazeSolver {
	
	private static MazePanel maze;  // The panel that holds the maze.
	private int autoDelay = 0;  // milliseconds of delay added after any move or heading change.

	public static void main(String[] args) throws InterruptedException {
		JFrame window = new JFrame("Recursive Maze Solver Demo");
		maze = new MazePanel(100,110,5);
		window.setContentPane(maze);
		window.pack();
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocation(100, 50);
		window.setVisible(true);
		// TODO: call the maze solver method
		
		
		recurse(1,1); // the maze is solve with the recursive method 
	
		
	}
	
	/**
	 *A subroutine that solves the maze. The maze uses a red line to highlight the path that solves the maze.
	 *@param row takes an int, the start of the maze
	 *@param col takes an int, the start of the maze
	 */
		
	public static void recurse(int row, int col){
		
		if (row == maze.getRows()-2 && col == maze.getColumns()-2) {
			
			
			int s = TextIO.getlnInt();
			try {
				System.exit(0);
			} finally {
				System.err.println("I'm not dead yet!");
				System.exit(1);
			}
			
		}else {
			
			delay(100);

			randomColor();
			
			if (maze.getColor(row,col+1) == Color.WHITE) {
				
				maze.setColor(row,col,Color.RED);
				maze.repaint();
				recurse(row,col+1);
				maze.setColor(row,col+1,Color.YELLOW);
				maze.repaint();
			//	delay(100);
				
			}
			if(maze.getColor(row-1,col) == Color.WHITE){
				
				maze.setColor(row,col,Color.RED);
				maze.repaint();
				recurse(row-1,col);
				maze.setColor(row-1,col,Color.YELLOW);
				maze.repaint();
			//	delay(100);
				
			}
			if(maze.getColor(row+1,col) == Color.WHITE){
				
				maze.setColor(row,col,Color.RED);
				maze.repaint();
				recurse(row+1,col);
				maze.setColor(row+1,col,Color.YELLOW);
				maze.repaint();
			//	delay(100);
				
			}
			if (maze.getColor(row,col-1) == Color.WHITE) {
				
				maze.setColor(row,col,Color.RED);
				maze.repaint();
				recurse(row,col-1);
				maze.setColor(row,col-1,Color.YELLOW);
				maze.repaint();
				//delay(100);
				
			}

		}
		
		
		
	
	}
	
	/**
	 * Set the turtle's color to a random value.  An HSB color is created with
	 * random hue and with brightness and saturation set to 1.  This is a
	 * random "spectral" color.
	 */
	public static Color randomColor() {
		float hue = (float)Math.random();
		Color c = Color.getHSBColor(hue, 1, 1);
		return c;
	}
	
	/**
	 * Sets the amount of delay between actions of the turtle, in milliseconds.  The default
	 * value, zero, means no delay.  A positive value will add an automatic delay after 
	 * each operation that affects the appearance of the panel.  This means any time the
	 * turtle moves (but only if either the turtle is visible or the pen is down),
	 * and any time the turtle turns (but only if the turtle is visible).  Delays
	 * also occur after drawing a string or clearing the canvas.  Also, delays are
	 * not used if autopaint is false.
	 * @param milliseconds  the number of milliseconds of delay added after each movement or
	 *    heading change of the turtle.  Default value is 0 (no delay).  Any value <= 0
	 *    means no delay.
	 */
	public void setAutoDelay(int milliseconds) {
		autoDelay = milliseconds;
	}
	
	private static void delay( int milliseconds ) {
		if (milliseconds > 0) {
			try {
				Thread.sleep(milliseconds);
			}
			catch (InterruptedException e){
			}
		}
	}
	
}