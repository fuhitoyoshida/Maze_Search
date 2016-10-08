import java.util.LinkedList;
import java.util.List;

/**
 * Depth-First Search (DFS)
 * 
 * You should fill the search() method of this class.
 */
public class DepthFirstSearcher extends Searcher {

	/**
	 * Calls the parent class constructor.
	 * 
	 * @see Searcher
	 * @param maze initial maze.
	 */
	public DepthFirstSearcher(Maze maze) {
		super(maze);
	}

	/**
	 * Main depth first search algorithm.
	 * 
	 * @return true if the search finds a solution, false otherwise.
	 */
	public boolean search() {
		// FILL THIS METHOD

		// explored list is a 2D Boolean array that indicates if a state associated with a given position in the maze has already been explored.
		boolean[][] explored = new boolean[maze.getNoOfRows()][maze.getNoOfCols()];

		// ...

		// Stack implementing the Frontier list
		LinkedList<State> stack = new LinkedList<State>();
		//initial player state
		State state = new State(maze.getPlayerSquare(), null, 0, 0);
		stack.add(state);

		List<State> list = null;
		while (!stack.isEmpty()) {
			// TODO return true if find a solution
			// TODO maintain the cost, noOfNodesExpanded (a.k.a. noOfNodesExplored),
			// maxDepthSearched, maxSizeOfFrontier during
			// the search
			// TODO update the maze if a solution found

			// use stack.pop() to pop the stack.
			// use stack.push(...) to elements to stack

			//update maxSizeOfFrontier
			if(stack.size() > maxSizeOfFrontier) maxSizeOfFrontier = stack.size();
			state = stack.pop();
			//update noOfNodesExpanded
			noOfNodesExpanded++;
			//update maxDepthSearched
			if(state.getDepth() > maxDepthSearched) maxDepthSearched = state.getDepth();

			//goal checking
			if(state.isGoal(maze)){
				while(state.getParent() != null){
					state = state.getParent();
					maze.setOneSquare(state.getSquare(), '.');
					//update cost
					cost++;
				}
				maze.setOneSquare(maze.getPlayerSquare(), 'S');
				return true;
			}

			list = state.getSuccessors(explored, maze);
			//cycle check then push successors to frontier
			for(State st : list){
				State temp = state;
				boolean isCycle = false;
				while(temp != null){
					if(st.getX() == temp.getX() && st.getY() == temp.getY()){
						isCycle = true;
						break;
					}
					temp = temp.getParent();
				}
				if(!isCycle) stack.push(st);
			}
		}

		// TODO return false if no solution
		return false;
	}
}
