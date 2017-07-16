import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Dijikstra {
	
	private Map<Integer,Map<Integer,Integer>> adjList;
	private Map<Integer, Boolean> visited;
	private ArrayList<Integer> tList;
	private int A[];
	private Set<Integer> B;
	private List<Integer> X;
	
	void init(){
		adjList = new HashMap<>();
		tList = new ArrayList<Integer>();
		visited = new HashMap<>();
		B = new TreeSet<>();
		X = new ArrayList<>();
		
		try (Scanner scanner = new Scanner(
				new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("input.txt")));) {
			while (scanner.hasNextLine()) {
				String s = scanner.nextLine();
				String num[] = s.split("\\t");
				Map<Integer, Integer> adj = new HashMap<>();
				for (int i = 1; i < num.length; i = i + 2) {
					adj.put(Integer.parseInt(num[i]), Integer.parseInt(num[i + 1]));
				}
				adjList.put(Integer.parseInt(num[0]), adj);
				visited.put(Integer.parseInt(num[0]), false);
				tList.add(Integer.parseInt(num[0]));
			}
			A = new int[tList.size()];
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void shortestpath(int a){
		A[a] = 0;
		X.add(a);
		visited.put(a, true);
		while(X.size()!=tList.size()){
			Double min = Double.POSITIVE_INFINITY;
			int u=0,v=0; 
			for(int n : X){
				for(int m : adjList.get(n).keySet()){
					if(!visited.get(m)){
						int t = A[n] + adjList.get(n).get(m);
						if(min>t){
							min = (double)t;
							u=n;
							v=m;
						}
					}
				}
			}
			A[v] = min.intValue();
			B.add(u);
			B.add(v);
			X.add(v);
			visited.put(v, true);
		}
	}
	
	public static void main(String s[]){
		Dijikstra d = new Dijikstra();
		d.init();
		d.shortestpath(Collections.min(d.adjList.keySet()));
	}
}