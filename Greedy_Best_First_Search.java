import java.util.PriorityQueue;

public class Greedy_Best_First_Search {
	class Node implements Comparable<Node>{
		int h;
		int r;
		int c;
		int time;
		Node parent;
		boolean[] v;
		Node(int r, int c){
			this.r = r;
			this.c = c;
			v = new boolean[] {true, true, true, true};
		}
		Node(Node cp){
			this.r = cp.r;
			this.c = cp.c;
			v = cp.v;
		}
		@Override
		public int compareTo(Node other){
			if(this.h == other.h){
				return this.time-other.time;
			}
			return this.h - other.h;
		}
	}
	
	static int time = 0;
	public static void main(String[] args){
		Greedy_Best_First_Search s = new Greedy_Best_First_Search();
		Node[][] b = s.init();
		s.search(b);
	}
	//up, right, down, left
	//up - 1, right - 4, down - 10, left - 2
	private void search(Node[][] b){
		PriorityQueue<Node> frontier = new PriorityQueue<Node>();
		boolean[][] v = new boolean[5][5];
		Node node = b[3][2];
		node.h = Math.abs(node.r - 1) + Math.abs(node.c - 2);

		frontier.add(node);
	
		while(!frontier.isEmpty()){
			node = frontier.remove();
			int r = node.r;
			int c = node.c;
			v[r][c] = true;
			
			System.out.println("expanding (" + r + ", " + c + ") " + "h: " + node.h);
			if(r == 1 && c == 2){
				System.out.println("Found");
				break;
			}
			
			//up
			if(r-1 >= 0 && node.v[0] && !v[r-1][c]){
				Node next = b[r-1][c];
				next.h = Math.abs(next.r - 1) + Math.abs(next.c - 2);
				next.time = time++;
				next.parent = node;
				frontier.add(next);
			}
			
			//right
			if(c+1 <= 4 && node.v[1] && !v[r][c+1]){
				Node next = b[r][c+1];
				next.h = Math.abs(next.r - 1) + Math.abs(next.c - 2);
				next.time = time++;
				next.parent = node;
				frontier.add(next);
			}
			
			//down
			if(r+1 <= 4 && node.v[2] && !v[r+1][c]){
				Node next = b[r+1][c];
				next.h = Math.abs(next.r - 1) + Math.abs(next.c - 2);
				next.time = time++;
				next.parent = node;
				frontier.add(next);
			}
			
			//left
			if(c-1 >= 0 && node.v[3] && !v[r][c-1]){
				Node next = b[r][c-1];
				next.h = Math.abs(next.r - 1) + Math.abs(next.c - 2);
				next.time = time++;
				next.parent = node;
				frontier.add(next);
			}	
				
		}
		
		while(node != null){
			System.out.print("(" + node.r + ", " + node.c + ")" + " <- ");
			node = node.parent;
		}
	}
	
	private Node[][] init(){
		Node[][] b = new Node[5][5];
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 5; j++){
				b[i][j] = new Node(i, j);
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
