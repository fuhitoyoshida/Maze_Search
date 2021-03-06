import java.util.ArrayList;

/**
 * A state in the search represented by the (x,y) coordinates of the square and
 * the parent. In other words a (square,parent) pair where square is a Square,
 * parent is a State.
 * 
 * You should fill the getSuccessors(...) method of this class.
 * 
 */
public class State {

	private Square square;
	private State parent;

	// Maintain the gValue (the distance from start)
	// You may not need it for the DFS but you will
	// definitely need it for AStar
	private int gValue;

	// States are nodes in the search tree, therefore each has a depth.
	private int depth;

	/**
	 * @param square
	 *            current square
	 * @param parent
	 *            parent state
	 * @param gValue
	 *            total distance from start
	 */
	public State(Square square, State parent, int gValue, int depth) {
		this.square = square;
		this.parent = parent;
		this.gValue = gValue;
		this.depth = depth;
	}

	/**
	 * @param visited
	 *            explored[i][j] is true if (i,j) is already explored
	 * @param maze
	 *            initial maze to get find the neighbors
	 * @return all the successors of the current state
	 */
	public ArrayList<State> getSuccessors(boolean[][] explored, Maze maze) {
		// FILL THIS METHOD

		// TODO check all four neighbors (up, right, down, left)
		// TODO return all unvisited neighbors
		// TODO remember that each successor's depth and gValue are
		// +1 of this object.
		ArrayList<State> list = new ArrayList<State>();
		//left
		if(getY()-1 >= 0 && (maze.getSquareValue(getX(), getY()-1) != '%') && !explored[getX()][getY()-1]){
			list.add(new State(new Square(getX(), getY()-1), this, getGValue()+1, getDepth()+1));
		}
		//down	
		if(getX()+1 <= maze.getNoOfRows()-1 && (maze.getSquareValue(getX()+1, getY()) != '%') && !explored[getX()+1][getY()]){
			list.add(new State(new Square(getX()+1, getY()), this, getGValue()+1, getDepth()+1));

		}
		//right	
		if(getY()+1 <= maze.getNoOfCols()-1 && (maze.getSquareValue(getX(), getY()+1) != '%') && !explored[getX()][getY()+1]){
			list.add(new State(new Square(getX(), getY()+1), this, getGValue()+1, getDepth()+1));

		}
		//up	
		if(getX()-1 >= 0 && (maze.getSquareValue(getX()-1, getY()) != '%') && !explored[getX()-1][getY()]){
			list.add(new State(new Square(getX()-1, getY()), this, getGValue()+1, getDepth()+1));

		}
		
		return list;
		
	}

	/**
	 * @return x coordinate of the current state
	 */
	public int getX() {
		return square.X;
	}

	/**
	 * @return y coordinate of the current state
	 */
	public int getY() {
		return square.Y;
	}

	/**
	 * @param maze initial maze
	 * @return true is the current state is a goal state
	 */
	public boolean isGoal(Maze maze) {
		if (square.X == maze.getGoalSquare().X
				&& square.Y == maze.getGoalSquare().Y)
			return true;

		return false;
	}

	/**
	 * @return the current state's square representation
	 */
	public Square getSquare() {
		return square;
	}

	/**
	 * @return parent of the current state
	 */
	public State getParent() {
		return parent;
	}

	/**
	 * You may not need g() value in the DFS but you will need it in A-star
	 * search.
	 * 
	 * @return g() value of the current state
	 */
	public int getGValue() {
		return gValue;
	}

	/**
	 * @return depth of the state (node)
	 */
	public int getDepth() {
		return depth;
	}
}
