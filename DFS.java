import java.util.Stack;

public class DFS{
	class Sq{
		private int r;
		private int c;
		private Sq parent;
		boolean[] v = new boolean[]{true, true, true, true}; // 0-up, 1-right, 2-down, 3-left
		
		public Sq(int r, int c, Sq parent){
			this.r = r;
			this.c = c;
			this.parent = parent;
		}
		
		public int getRow(){
			return r;
		}
		
		public int getColumn(){
			return c;
		}
		
		public boolean isGoal(){
			return r == 1 && c == 2;
		}
		
		public Sq getParent(){
			return parent;
		}
		
	}
	
	public static void main(String[] args){
		DFS dfs = new DFS();
		Sq[][] board = dfs.init();
		dfs.search(board);
	}
	
	public void search(Sq[][] board){
		Stack<Sq> stack = new Stack<Sq>();
		boolean[][] visited = new boolean[5][5]; 
		stack.add(new Sq(3, 2, null));
		
		while(!stack.isEmpty()){
			Sq square = stack.pop();
			System.out.println("expanding (" + square.r + ", " + square.c + ")");
			visited[square.r][square.c] = true;
			
			if(square.isGoal()){
				System.out.println("Found");
				while(square.getParent() != null){
					System.out.print("<- (" + square.r + ", " + square.c + ") ");
					square = square.getParent();
				}
				System.out.println("<- (" + square.r + ", " + square.c + ")");
				break;
			}
			
			//left
			if(square.c-1 >= 0 && !visited[square.r][square.c-1] && board[square.r][square.c].v[3]){
				board[square.r][square.c-1].parent = square;
				stack.add(board[square.r][square.c-1]);
			}
			//down
			if(square.r+1 <= 4 && !visited[square.r+1][square.c] && board[square.r][square.c].v[2]){
				board[square.r+1][square.c].parent = square;
				stack.add(board[square.r+1][square.c]);
			}
			//right
			if(square.c+1 <= 4 && !visited[square.r][square.c+1] && board[square.r][square.c].v[1]){
				board[square.r][square.c+1].parent = square;
				stack.add(board[square.r][square.c+1]);
			}
			//up
			if(square.r-1 >= 0 && !visited[square.r-1][square.c] && board[square.r][square.c].v[0]){
				board[square.r-1][square.c].parent = square;
				stack.add(board[square.r-1][square.c]);
			}
				
		}
		
	}
	
	public Sq[][] init(){
		Sq[][] b = new Sq[5][5];
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 5; j++){
				b[i][j] = new Sq(i, j, null);
			}
		}
		b[1][1].v[3] = false;
		b[1][1].v[2] = false;
		b[1][2].v[2] = false;
		b[3][1].v[0] = false;
		b[3][2].v[0] = false;
		b[3][2].v[1] = false;
		
		b[1][0].v[1] = false;
		b[2][1].v[0] = false;
		b[2][1].v[2] = false;
		b[2][2].v[0] = false;
		b[2][2].v[2] = false;
		b[3][3].v[3] = false;
		
		return b;
		
	}
}


	
	


//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Stack;
//
//public class DFS {
//	class Sq { 
//		int x;
//		int y;
//		Sq parent = null;
//		boolean v[]; // 1*4, 0-up, 1-right, 2-down, 3-left
//		Sq(int r, int c, boolean[] can){
//			x = r;
//			y = c;
//			v = can;
//		}
//	}
//	public static void main(String[] args){
//		DFS s = new DFS();
//		Sq[][] b = s.init();
//		s.search(3, 2, new boolean[5][5], b);
//	}
//	private Sq[][] init(){
//		Sq[][] board = new Sq[5][5];
//		for(int i = 0; i < 5; i++){
//			for(int j = 0; j < 5; j++){
//				board[i][j] = new Sq(i, j, new boolean[] {true, true, true, true});
//			}
//		}
//		board[1][1].v[3] = false;
//		board[1][1].v[2] = false;
//		board[1][2].v[2] = false;
//		board[3][1].v[0] = false;
//		board[3][2].v[0] = false;
//		board[3][2].v[1] = false;
//		
//		board[1][0].v[1] = false;
//		board[2][1].v[0] = false;
//		board[2][1].v[2] = false;
//		board[2][2].v[0] = false;
//		board[2][2].v[2] = false;
//		board[3][3].v[3] = false;
//		return board;
//	}
//	
//	private void search(int r, int c, boolean[][] visited, Sq[][] b){
//		Stack<Sq> stack = new Stack<Sq>();
//		stack.add(b[r][c]);
//		List<Sq> explored = new ArrayList<Sq>();
//		
//		while(!stack.isEmpty()){
//			Sq square = stack.pop();
//			explored.add(square);
//			visited[square.x][square.y] = true;
//			
//			//goal state
//			if(square.x == 1 && square.y == 2){
//				
//			}
//			
//			if(square.x)
//			
//		}
//		
//	}
//	
//	private boolean search(int r, int c, boolean[][] visited, Sq[][] b){
//		visited[r][c] = true;
//		if(r == 1 && c == 2) {
//			//System.out.print("(" + r + ", " + c + ")" + " <- ");
//			return true;
//		}
//		if(r > 0 && !visited[r-1][c] && b[r][c].v[0]){
//			if(search(r-1, c, visited, b)){
//				//System.out.print("(" + r + ", " + c + ")" + " <- ");
//				return true;
//			}
//		}
//		if(c < 4 && !visited[r][c+1] && b[r][c].v[1]){
//			if(search(r, c+1, visited, b)){
//				//System.out.print("(" + r + ", " + c + ")" + " <- ");
//				return true;
//			}
//		}
//		if(r < 4 && !visited[r+1][c] && b[r][c].v[2]){
//			if(search(r+1, c, visited, b)){
//				//System.out.print("(" + r + ", " + c + ")" + " <- ");
//				return true;
//			}
//		}
//		if(c > 0 && !visited[r][c-1] && b[r][c].v[0]){
//			if(search(r, c-1, visited, b)){
//				//System.out.print("(" + r + ", " + c + ")" + " <- ");
//				return true;
//			}
//		}
//		return false;
//	}
//	
//}
