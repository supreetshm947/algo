import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DFS {
	private Map<Integer,List<Integer>> adjList; 
	private ArrayList<Integer> tList;
	private Map<Integer, Boolean> visited;
	
	void init(){
		adjList = new HashMap<Integer,List<Integer>>();
		tList = new ArrayList<Integer>();
		visited = new HashMap<>();
		
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
		    }
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	}
	
	public void dfSearch(int node){
		visited.put(node, true);
		for(int e : adjList.get(node)){
			if(!visited.get(e))
				dfSearch(e);
		}
	}
	
	public static void main(String s[]){
		DFS dfs = new DFS();
		dfs.init();
		dfs.dfSearch(0);
		for(int n : dfs.visited.keySet()){
			System.out.println(n+"->"+dfs.visited.get(n));
		}
	}
}
