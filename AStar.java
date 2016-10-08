import java.util.PriorityQueue;


public class AStar {
	class Node implements Comparable<Node>{
		int h;
		int cost;
		int x;
		int y;
		int time;
		Node parent;
		boolean[] v;
		Node(int x, int y){
			this.x = x;
			this.y = y;
			v = new boolean[] {true, true, true, true};
		}
		Node(Node cp){
			this.x = cp.x;
			this.y = cp.y;
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
	//static int expanded = 1;
	public static void main(String[] args){
		AStar s = new AStar();
		Node[][] b = s.init();
		s.search(b);
	}
	
	//up, right, down, left
	//up - 1, right - 4, down - 10, left - 2
	private void search(Node[][] b){
		PriorityQueue<Node> p = new PriorityQueue<Node>();
		boolean[][] v = new boolean[5][5];
		Node node = b[3][2];
		node.h = Math.abs(node.x - 1) + Math.abs(node.y - 2);
		p.add(node);
		while(!p.isEmpty()){
			node = p.remove();
			int x = node.x;
			int y = node.y;
			if(v[x][y]) continue;
			System.out.println("expanding (" + x + ", " + y + ")" + " f: " + node.h);
			if(x == 1 && y == 2){
				System.out.println("Found");
				break;
			}
			
			v[x][y] = true;
			//up
			if(x > 0 && node.v[0] && !v[x-1][y]){
				Node next = b[x-1][y];
				int heuristics = Math.abs(next.x - 1) + Math.abs(next.y - 2);
				if(next.parent == null){
					next.cost = node.cost+1;
					next.h = next.cost + heuristics;
					next.time = time++;
					next.parent = node;
					p.add(next);
				}else{
					if(next.cost > node.cost + 1){
						//better cost
						Node dup = new Node(next);
						dup.cost = node.cost+1;
						dup.h = next.cost + heuristics;
						dup.time = time++;
						dup.parent = node;
						b[x-1][y] = dup;
						p.add(dup);
					}
				}
			}
			
			//right
			if(y < 4 && node.v[1] && !v[x][y+1]){
				Node next = b[x][y+1];
				int heuristics = Math.abs(next.x - 1) + Math.abs(next.y - 2);
				if(next.parent == null){
					next.cost = node.cost+1;
					next.h = next.cost + heuristics;
					next.time = time++;
					next.parent = node;
					p.add(next);
					
				}else{
					if(next.cost > node.cost + 1){
						//better cost
						Node dup = new Node(next);
						dup.cost = node.cost+1;
						dup.h = next.cost + heuristics;
						dup.time = time++;
						dup.parent = node;
						b[x][y+1] = dup;
						p.add(dup);
					}
				}
			}
			
			//down
			if(x < 4 && node.v[2] && !v[x+1][y]){
				Node next = b[x+1][y];
				int heuristics = Math.abs(next.x - 1) + Math.abs(next.y - 2);
				if(next.parent == null){
					next.cost = node.cost+1;
					next.h = next.cost + heuristics;
					next.time = time++;
					next.parent = node;
					p.add(next);
					
				}else{
					if(next.cost > node.cost + 1){
						//better cost
						Node dup = new Node(next);
						dup.cost = node.cost+1;
						dup.h = next.cost + heuristics;
						dup.time = time++;
						dup.parent = node;
						b[x+1][y] = dup;
						p.add(dup);
					}
				}
			}
			
			//left
			if(y > 0 && node.v[3] && !v[x][y-1]){
				Node next = b[x][y-1];
				int heuristics = Math.abs(next.x - 1) + Math.abs(next.y - 2);
				if(next.parent == null){
					next.cost = node.cost+1;
					next.h = next.cost + heuristics;
					next.time = time++;
					next.parent = node;
					p.add(next);
					
				}else{
					if(next.cost > node.cost + 1){
						//better cost
						Node dup = new Node(next);
						dup.cost = node.cost+1;
						dup.h = next.cost + heuristics;
						dup.time = time++;
						dup.parent = node;
						b[x][y-1] = dup;
						p.add(dup);
					}
				}
			}
		}
		
		while(node != null){
			System.out.print("(" + node.x + ", " + node.y + ")" + " <- ");
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