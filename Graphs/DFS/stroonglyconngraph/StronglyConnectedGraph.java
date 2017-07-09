import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StronglyConnectedGraph {
	private Map<Integer,List<Integer>> adjList;
	private Map<Integer,List<Integer>> rAdjList;
	private Map<Integer,List<Integer>> cAdjList; 
	private Map<Integer, Boolean> visited;
	private Map<Integer, Boolean> sVisited;
	private ArrayList<Integer> tList;
	private int size;
	private int leader;
	Map<Integer,Integer> leaders;
	Map<Integer,Integer> topological;
	Map<Integer,Integer> rtopological;
	
	void init(){
		adjList = new HashMap<Integer,List<Integer>>();
		rAdjList = new HashMap<Integer,List<Integer>>();
		tList = new ArrayList<Integer>();
		visited = new HashMap<>();
		leaders = new HashMap<>();
		topological = new HashMap<>();
		cAdjList = new HashMap<>();
		sVisited = new HashMap<>();
		rtopological = new HashMap<>();
		
		try(Scanner scanner = new Scanner (new InputStreamReader(this.getClass().
	    		getClassLoader().getResourceAsStream("input.txt")));) {
			
		    while(scanner.hasNextLine()) {
			   String s = scanner.nextLine();
			   String num[] = s.split("\\t");
			   List<Integer> adj = new ArrayList<Integer>();
			   for (int i = 1; i < num.length; i++)
				   adj.add(Integer.parseInt(num[i]));
			   adjList.put(Integer.parseInt(num[0]), adj);
			   visited.put(Integer.parseInt(num[0]), false);
			   sVisited.put(Integer.parseInt(num[0]), false);
			   tList.add(Integer.parseInt(num[0]));
		    }
		    size = tList.size();
		    size--;
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	}
	
	public void reverseGraph(){
		for(int i : adjList.keySet()){
			rAdjList.put(i, new ArrayList<>());
		}
		for(int i : adjList.keySet()){
			for(int j : adjList.get(i)){
				rAdjList.get(j).add(i);
			}
		}
	}
	
	public void computeStronglyConnected(){
		size = Collections.min(rAdjList.keySet(), null);
		List<Integer> tSet = new ArrayList<>(rAdjList.keySet()); 
		Collections.reverse(tSet);
		for(int i : tSet){
			leader = i;
			dfs1(i);
		}
		convertGraph(topological);
		for(int i : tSet){
			List<Integer> sGraph = new ArrayList<>();
			leader = i;
			dfs2(i, sGraph);
			if(sGraph.size()>=1){
				for(int o : sGraph)
					System.out.print(rtopological.get(o)+"->");
				System.out.println();
			}
		}
	}
	
	public void dfs1(int a){
		if(!visited.get(a)){
			visited.put(a, true);
			leaders.put(a,  leader);
			for(int j : rAdjList.get(a)){
				dfs1(j);
			}
			topological.put(a, size);
			rtopological.put(size++, a);
		} 
	}
	
	public void dfs2(int a,	 List<Integer> sGraph){
		if(!sVisited.get(a)){
			sGraph.add(a);
			sVisited.put(a, true);
			for(int j : cAdjList.get(a)){
				dfs2(j,sGraph);
			}
		} 
	}
	
	public void convertGraph(Map<Integer, Integer> top){
		for(Integer i : adjList.keySet()){ 
			List<Integer> tlist = new ArrayList<>();
			for(Integer j : adjList.get(i)){  
				tlist.add(topological.get(j));
			}
			cAdjList.put(top.get(i), tlist);
		}
	}
	
	public static void main(String s[]){
		StronglyConnectedGraph strong = new StronglyConnectedGraph();
		strong.init();
		
		strong.reverseGraph();
		
		strong.computeStronglyConnected();
			
	}
}
