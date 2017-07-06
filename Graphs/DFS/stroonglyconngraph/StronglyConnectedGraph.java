import java.io.InputStreamReader;
import java.io.ObjectOutputStream.PutField;
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
	private ArrayList<Integer> tList;
	private int size;
	private int leader;
	Map<Integer,Integer> leaders;
	Map<Integer,Integer> topological;
	
	void init(){
		adjList = new HashMap<Integer,List<Integer>>();
		rAdjList = new HashMap<Integer,List<Integer>>();
		tList = new ArrayList<Integer>();
		visited = new HashMap<>();
		leaders = new HashMap<>();
		topological = new HashMap<>();
		cAdjList = new HashMap<>();
		
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
		for(int i : rAdjList.keySet()){
			leader = i;
			dfs1(i);
		}
		convertGraph(topological);
	}
	
	public void dfs1(int a){
		if(!visited.get(a)){
			visited.put(a, true);
			leaders.put(a,  leader);
			for(int j : rAdjList.get(a)){
				dfs1(j);
			}
			topological.put(a, size++);
		} 
	}
	
	public void convertGraph(Map<Integer, Integer> top){
		for(int i : top.keySet()){
			cAdjList.put(top.get(i), rAdjList.get(i));
		}
	}
	
	public static void main(String s[]){
		StronglyConnectedGraph strong = new StronglyConnectedGraph();
		strong.init();
		
		strong.reverseGraph();
		
		strong.computeStronglyConnected();
		
		int g = 98980;
	}
}
