import java.util.PriorityQueue;


public class UCS {
	class Node implements Comparable<Node>{
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
			if(this.cost == other.cost){
				return this.time-other.time;
			}
			return this.cost - other.cost;
		}
	}
	static int time = 0;
	//static int expanded = 1;
	public static void main(String[] args){
		UCS s = new UCS();
		Node[][] b = s.init();
		s.search(b);
	}
	//up, right, down, left
	//up - 1, right - 4, down - 10, left - 2
	private void search(Node[][] b){
		PriorityQueue<Node> p = new PriorityQueue<Node>();
		boolean[][] v = new boolean[5][5];
		Node s = b[3][2];
		p.add(s);
		Node cur = null;
		while(!p.isEmpty()){
			cur = p.remove();
			int x = cur.x;
			int y = cur.y;
			if(v[x][y]) continue;
			System.out.println("expanding (" + x + ", " + y + ")" + "Cost: " + cur.cost);
			if(x == 1 && y == 2){
				System.out.println("Found");
				break;
			}
			
			v[x][y] = true;
			//up
			if(x > 0 && cur.v[0] && !v[x-1][y]){
				Node next = b[x-1][y];
				if(next.parent == null){
					next.cost = cur.cost+1;
					next.time = time++;
					next.parent = cur;
					p.add(next);
					
				}else{
					if(next.cost > cur.cost + 1){
						//better cost
						Node dup = new Node(next);
						dup.cost = cur.cost+1;
						dup.time = time++;
						dup.parent = cur;
						p.add(dup);
						b[x-1][y] = dup;
					}
				}
			}
			
			//right
			if(y < 4 && cur.v[1] && !v[x][y+1]){
				Node next = b[x][y+1];
				if(next.parent == null){
					next.cost = cur.cost+4;
					next.time = time++;
					next.parent = cur;
					p.add(next);
					
				}else{
					if(next.cost > cur.cost + 4){
						//better cost
						Node dup = new Node(next);
						dup.cost = cur.cost+4;
						dup.time = time++;
						dup.parent = cur;
						p.add(dup);
						b[x][y+1] = dup;
					}
				}
			}
			
			//down
			if(x < 4 && cur.v[2] && !v[x+1][y]){
				Node next = b[x+1][y];
				if(next.parent == null){
					next.cost = cur.cost+10;
					next.time = time++;
					next.parent = cur;
					p.add(next);
					
				}else{
					if(next.cost > cur.cost + 10){
						//better cost
						Node dup = new Node(next);
						dup.cost = cur.cost+10;
						dup.time = time++;
						dup.parent = cur;
						p.add(dup);
						b[x+1][y] = dup;
					}
				}
			}
			
			//left
			if(y > 0 && cur.v[3] && !v[x][y-1]){
				Node next = b[x][y-1];
				if(next.parent == null){
					next.cost = cur.cost+2;
					next.time = time++;
					next.parent = cur;
					p.add(next);
					
				}else{
					if(next.cost > cur.cost + 2){
						//better cost
						Node dup = new Node(next);
						dup.cost = cur.cost+2;
						dup.time = time++;
						dup.parent = cur;
						p.add(dup);
						b[x][y-1] = dup;
					}
				}
			}
		}
		
		System.out.println("Cost: " + cur.cost);
		while(cur != null){
			System.out.print("(" + cur.x + ", " + cur.y + ")" + " <- ");
			cur = cur.parent;
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
