import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class RandomContractionAlgo {
	
	static Map<Integer, List<Integer>> adjList;
	static List<Integer> tList;
	static int mincut = Integer.MAX_VALUE;
	static int size;
	static Random r = new Random();
	
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
		  } catch (Exception e) {
			  e.printStackTrace();
		  }

	}
	
	public static void calcMin(){
		while(tList.size()>2){
			int tt = r.nextInt(tList.size());
			int vIndex = tList.get(tt);
			List<Integer> vList= adjList.get(vIndex);
			
			int dIndex = vList.get(r.nextInt(vList.size()));
			List<Integer> dList= adjList.get(dIndex);
			
			for(Iterator<Integer> dIter = dList.iterator(); dIter.hasNext();){
				int auxIndex = dIter.next();
				List<Integer> auxList = adjList.get(auxIndex);
				
				while(auxList.remove(new Integer(dIndex)));
				auxList.add(vIndex);
				vList.add(auxIndex);
			}
			
			//adjList.remove(new Integer(dIndex));
			
			adjList.remove(dIndex);
			tList.remove(new Integer(dIndex));
			while(vList.remove(new Integer(vIndex)));
		}
		mincut = Math.min(mincut, adjList.get(tList.get(0)).size());
	}
	
	public static void main(String s[]){
		RandomContractionAlgo contractionAlgo = new RandomContractionAlgo();
		contractionAlgo.init();
		
		for(int i=0; i<size*size; i++){
			contractionAlgo.init();
			calcMin();
			System.out.println("Min Iter--"+mincut);
		}
		System.out.println("final cut--"+mincut);
	}
}
