import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * A* algorithm search
 * 
 * You should fill the search() method of this class.
 */
public class AStarSearcher extends Searcher {

	/**
	 * Calls the parent class constructor.
	 * 
	 * @see Searcher
	 * @param maze initial maze.
	 */
	public AStarSearcher(Maze maze) {
		super(maze);
	}

	/**
	 * Main a-star search algorithm.
	 * 
	 * @return true if the search finds a solution, false otherwise.
	 */
	public boolean search() {

		// FILL THIS METHOD

		// explored list is a Boolean array that indicates if a state associated with a given position in the maze has already been explored. 
		boolean[][] explored = new boolean[maze.getNoOfRows()][maze.getNoOfCols()];
		// ...

		PriorityQueue<StateFValuePair> frontier = new PriorityQueue<StateFValuePair>();

		// TODO initialize the root state and add
		// to frontier list
		// ...
		State state = new State(maze.getPlayerSquare(), null, 0, 0);
		double fValue = Math.abs(maze.getPlayerSquare().X - maze.getGoalSquare().X) + Math.abs(maze.getPlayerSquare().Y - maze.getGoalSquare().Y);
		StateFValuePair fValueState = new StateFValuePair(state, fValue);
		frontier.add(fValueState);
				
		List<State> list = null;
		while (!frontier.isEmpty()) {
			// TODO return true if a solution has been found
			// TODO maintain the cost, noOfNodesExpanded (a.k.a. noOfNodesExplored),
			// maxDepthSearched, maxSizeOfFrontier during
			// the search
			// TODO update the maze if a solution found

			// use frontier.poll() to extract the minimum stateFValuePair.
			// use frontier.add(...) to add stateFValue pairs
			if(frontier.size() > maxSizeOfFrontier) maxSizeOfFrontier = frontier.size();
			fValueState = frontier.poll();
			state = fValueState.getState();
			//set the state as explored
			explored[state.getX()][state.getY()] = true;
			//update noOfNodesExpanded
			noOfNodesExpanded++;
			//update maxDepthSearched
			if(state.getDepth() > maxDepthSearched) maxDepthSearched = state.getDepth();

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
			for(State successor : list){
				Iterator<StateFValuePair> itr = frontier.iterator();
				boolean found = false;
				while(itr.hasNext()){
					StateFValuePair frontier_node = itr.next();
					//if there is identical state in frontier
					if(successor.getX() == frontier_node.getState().getX() && successor.getY() == frontier_node.getState().getY()){
						//found identical state
						found = true;
						//compare gValue, remain the smaller gValue state
						if(successor.getGValue() < frontier_node.getState().getGValue()){
							frontier.remove(frontier_node);
							fValue = Math.abs(successor.getX() - maze.getGoalSquare().X) + Math.abs(successor.getY() - maze.getGoalSquare().Y);
							frontier.add(new StateFValuePair(successor, successor.getGValue() + fValue));
							break;
						}
					}
				}

				if(!found){
					fValue = Math.abs(successor.getX() - maze.getGoalSquare().X) + Math.abs(successor.getY() - maze.getGoalSquare().Y);
					frontier.add(new StateFValuePair(successor, successor.getGValue() + fValue)) ;
				}
			}
		}
		// TODO return false if no solution
		return false;
	}
}


