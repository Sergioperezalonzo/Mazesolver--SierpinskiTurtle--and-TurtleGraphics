import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 *  This class defines a panel that displays a maze.  More exactly,
 *  it displays a grid of squares that represent a maze when the panel
 *  is first created.  In the maze, corridors are drawn as white 
 *  square and walls are black squares.  There is wall around the
 *  boundary of the maze.  There are no loops in the maze.  The
 *  class also includes a method for changing the color of any square.
 */
public class MazePanel extends JPanel {

	private int rows, cols;   // the number of rows and columns in the grid; these are odd integers.
	private Color[][] color;  // holds the colors of all the squares, with null representing the background color (white).
	private int autoDelay = 0;  // milliseconds of delay added after any move or heading change.

	
	/**
	 * Create a maze with 121 rows and 161 columns, and with 5 as the preferred size of the
	 * individual squares in the grid.
	 */
	public MazePanel() {
		this(121,161,5);
	}

	
	/**
	 * Create a maze.
	 * @param rows The number of rows in the maze.  This must be a positive odd number.  If the
	 *   parameter value is an even number, one is added to the value to make it odd.
	 * @param cols The number of columns in the maze.  This must be a positive odd number.  If the
	 *   parameter value is an even number, one is added to the value to make it odd.
	 * @param preferredSquareSize How big each square in the grid should be.  This will determine
	 *   the preferred size of the panel (but when the panel is drawn, if it does not have its
	 *   preferred size, the sizes of the squares will be adjusted so that the maze will exactly
	 *   fit in the panel).
	 */
	public MazePanel(int rows, int cols, int preferredSquareSize) {
		if (rows % 2 == 0)
			rows++; // make it odd
		if (cols % 2 == 0)
			cols++; // make it odd
		setPreferredSize( new Dimension(preferredSquareSize*cols, preferredSquareSize*rows) );
		this.rows = rows;
		this.cols = cols;
		color = new Color[rows][cols];
		create(); // create the maze
	}


	/**
	 * Returns the number of rows in the grid, which must be an odd number.
	 */
	public int getRows() {
		return rows;
	}


	/**
	 * Returns the number of columns in the grid, which must be an odd number.
	 */
	public int getColumns() {
		return cols;
	}


	/**
	 * Returns the color for a specified square.  A return value of null
	 * means that the square will be drawn in the background color of
	 * the panel (white by default).
	 */
	public Color getColor( int row, int column ) {
		return color[row][column];
	}

	
	/**
	 * Sets the color of a specified square.  Setting the color to null
	 * means that the square will be drawn in the background color of the
	 * panel (white by default).  Note that this method does NOT call
	 * repaint(); you have to repaint the panel to see the change.
	 */
	public void setColor( int row, int column, Color color) {
		this.color[row][column] = color;
	}

	
	/**
	 * Create a new random maze, resetting the colors of all the squares
	 * to be either Color.BLACK or null.  Note that this method does
	 * not call repaint(); you have to repaint the panel to see the change.
	 */
	public void create() {
		int[][] maze = new int[rows][cols];
		ArrayList<Point> walls = new ArrayList<Point>();
		for (int i = 1; i<rows-1; i += 2) { // make a grid of empty rooms
			for (int j = 1; j<cols-1; j += 2) {
				if (i < rows-2) {  // record info about wall below this room
					walls.add( new Point(i+1,j) );
				}
				if (j < cols-2) {  // record info about wall to right of this room
					walls.add( new Point(i,j+1) );
				}
				maze[i][j] = walls.size();  // each room is coded with a different negative number
			}
		}
		while ( ! walls.isEmpty() ) {
			Point wall = walls.remove( (int)(Math.random()*walls.size()) );
			int row = wall.x;
			int col = wall.y;
			if (row % 2 == 1 && maze[row][col-1] != maze[row][col+1]) {
				// row is odd; wall separates rooms horizontally
				convert(maze, row, col-1, maze[row][col-1], maze[row][col+1]);
				maze[row][col] = maze[row][col+1];
			}
			else if (row % 2 == 0 && maze[row-1][col] != maze[row+1][col]) {
				// row is even; wall separates rooms vertically
				convert(maze, row-1, col, maze[row-1][col], maze[row+1][col]);
				maze[row][col] = maze[row+1][col];
			}
		}
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++)
				if (maze[row][col] == 0)
					color[row][col] = Color.BLACK;
				else
					color[row][col] = Color.WHITE;
		}
	}

	
	private void convert(int[][] maze, int row, int col, int replace, int replaceWith) {
		// called by create() when a wall is torn down, 
		// to change the code in the corridor on one side of the wall
		// to match the code on the other side, so that the entire connected
		// corridor contains the same code number.
		if (maze[row][col] == replace) {
			maze[row][col] = replaceWith;
			convert(maze, row+1,col,replace,replaceWith);
			convert(maze, row-1,col,replace,replaceWith);
			convert(maze, row,col+1,replace,replaceWith);
			convert(maze, row,col-1,replace,replaceWith);
		}
	}


	/**
	 * Fills the panel with the grid of squares.
	 */
	protected void paintComponent( Graphics g ) {
		super.paintComponent(g);
		double squareWidth = (double)getWidth() / cols;
		double squareHeight = (double)getHeight() / rows;
		for (int row = 0; row < rows; row++) {
			int y = (int)(squareHeight * row);
			int h = (int)(squareHeight * (row+1)) - y;
			for (int col = 0; col < cols; col++) {
				if ( color[row][col] != null) {
					int x = (int)(squareWidth * col);
					int w = (int)(squareWidth * (col+1)) - x;
					g.setColor( color[row][col] );
					g.fillRect(x, y, w, h);
				}
			}
		}
	}

}



