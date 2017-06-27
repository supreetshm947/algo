import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class BinarySearchIceCream {
	
	public static class Pair implements Comparable<Pair>{
		private int index;
		private int value;
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		@Override
		public int compareTo(Pair p) {
			return ((Integer)this.value).compareTo(p.value);
		}
		
	}
	
	public static int searchIndex(int a[], int x, int y, int s){
		if(x<=y){
			int m = y + (x-y)/2;
			if(a[m]>s){
				return searchIndex(a, x, m-1, s);
			}else if(a[m]<s){
				return searchIndex(a, m+1, y, s);
			}else
				return m;
		}else
			return -1;
	}

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int m = in.nextInt();
            int n = in.nextInt();
            int a[] = new int[n];
            for(int a_i=0; a_i < n; a_i++){
                a[a_i] = in.nextInt();
            }
            
            ArrayList<Pair> pairs = new ArrayList<>();
            for(int j = 0; j<n; j++){
            	Pair p = new Pair();
            	p.setIndex(j+1);
            	p.setValue(a[j]);
            	pairs.add(p);
            }
            Arrays.sort(a);
            Collections.sort(pairs);
            for(int i=0;i<n-1;i++){
            	int temp = m - pairs.get(i).getValue();
            	if(t>0){
            		int i2 = searchIndex(a, i+1, n-1, temp);
            		if(i2==-1)
            			continue;
            		else{
            			System.out.println(Math.min(pairs.get(i).getIndex(), pairs.get(i2).getIndex())+" "+Math.max(pairs.get(i).getIndex(), pairs.get(i2).getIndex()));
            			break;
            		}
            			
            	}
            }
        }
        in.close();
    }
}
