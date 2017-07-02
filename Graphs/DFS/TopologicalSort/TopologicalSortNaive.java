import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TopologicalSortNaive {
	private Map<Integer,List<Integer>> adjList; 
	private ArrayList<Integer> tList;
	private int size;
	private int[] topological;
	
	void init(){
		adjList = new HashMap<Integer,List<Integer>>();
		tList = new ArrayList<Integer>();
		
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
		    }
		    size = tList.size();
		    topological = new int[size];
		    size--;
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	}
	
	public void computeTopolgical(){
		while(!tList.isEmpty()){
			for(int n : tList){
				if(adjList.get(n).isEmpty()){
					for(int t : tList){
						List<Integer> list = adjList.get(t); 
						while(list.remove(new Integer(n)));
					}
					tList.remove(new Integer(n));
					adjList.remove(n);
					topological[size--] = n;
					break;
				}
			}
		}
	}
	
	public static void main(String s[]){
		TopologicalSortNaive obj = new TopologicalSortNaive();
		obj.init();
		
		obj.computeTopolgical();
		
		for(int n : obj.topological){
			System.out.print(n+"-");
		}
	}
}
