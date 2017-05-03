import java.util.Scanner;

public class RandomisedSelection {
	public static int sort(int a[], int s){
		if(a.length==1){
			return a[0];
		}
		return quickSort(a, 0, a.length, s);
	}
	
	public static int quickSort(int a[], int l, int r, int s){
		if(r-l==1)
			return a[l];
		if(r>l){
			int pIndex = (l + (int)Math.random() )%(r-l);
			swap(a, l, pIndex);
			int p = a[l];
			int i = l+1;
			for(int j = i; j<r; j++){
				if(p>a[j]){
					swap(a, i, j);
					i++;
				}
			}
			swap(a, l, i-1);
			if(s==i-1)
				return a[i-1];
			else if(s<i-1)
				return quickSort(a, l, i-1, s);
			else
				return quickSort(a, i, r, s-i);
		}else
			return 0;
	}
	
	public static void swap(int a[], int x, int y){
		if(x!=y){
			a[x] = a[x] + a[y];
			a[y] = a[x] - a[y];
			a[x] = a[x] - a[y];
		}
	}
	
	public static void main(String s[]){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int se = sc.nextInt();
		int a[] = new int[n]; 
		for( int i = 0; i<n; i++){
			a[i] = sc.nextInt();
		}
		System.out.println(sort(a, se-1));
		sc.close();
	}
}
