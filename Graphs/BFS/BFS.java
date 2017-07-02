import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
	
	private Map<Integer,List<Integer>> adjList; 
	private ArrayList<Integer> tList;
	private Map<Integer, Boolean> visited;
	private Map<Integer, Double> hopCount;
	private Queue<Integer> utilQueue;
	
	void init(){
		adjList = new HashMap<Integer,List<Integer>>();
		tList = new ArrayList<Integer>();
		visited = new HashMap<>();
		hopCount = new HashMap<>();
		try(Scanner scanner = new Scanner (new InputStreamReader(this.getClass().
	    		getClassLoader().getResourceAsStream("input.txt")));) {
			
		    while(scanner.hasNextLine()) {
			   String s = scanner.nextLine();
			   String num[] = s.split("\\t");
			   List<Integer> adj = new ArrayList<Integer>();
			   for (int i = 1; i < num.length; i++)
				   adj.add(Integer.parseInt(num[i]));
			   adjList.put(Integer.parseInt(num[0]), adj);
			   tList.add(Integer.parseInt(num[0]));
			   visited.put(Integer.parseInt(num[0]), false);
			   hopCount.put(Integer.parseInt(num[0]), Double.POSITIVE_INFINITY);
		    }
		  } catch (Exception e) {
			  e.printStackTrace();
		  }

	}
	
	public boolean bfSearch(int search){
		while(!utilQueue.isEmpty()){
			int n = utilQueue.poll();
			visited.put(n, true);
			for(int ed : adjList.get(n)){
				if(ed==search)
					return true;
				if(!visited.get(ed))
					utilQueue.add(ed);
			}
		}
		return false;
	}
	
	public void bfsMinHop(){
		while(!utilQueue.isEmpty()){
			int n = utilQueue.poll();
			visited.put(n, true);
			for(int ed : adjList.get(n)){
				if(!visited.get(ed)){
					hopCount.put(ed, hopCount.get(n)+1);
					utilQueue.add(ed);
				}
			}
		}
	}
	
	public static void main(String s[]){
		BFS bfs = new BFS();
		bfs.init();
		
		bfs.utilQueue = new PriorityQueue<>();
		bfs.utilQueue.add(0);
		bfs.hopCount.put(0, new Double(0));
		
		bfs.bfsMinHop();
		
		for(int node : bfs.hopCount.keySet()){
			System.out.println(node+"->"+bfs.hopCount.get(node));
		}
	}
}