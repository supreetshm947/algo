import java.util.Scanner;

public class QuickSort {
	public static void sort(int a[], int n){
		if(n==1){
			return;
		}else{
			quickSort(a, 0, n);
		}
	}
	
	public static void quickSort(int a[],int l, int r){
		if(l<r){
			int p = a[l];
			int i = l+1;
			for(int j = i; j<r; j++){
				if(p>a[j]){
					swap(a, i, j);
					i++;
				}
			}
			swap(a, l, i-1);
			quickSort(a, l, i-1);
			quickSort(a, i, r);
		}
	}
	
	public static void swap(int a[],int i, int j){
		if(i!=j){
			a[i] = a[i]+a[j];
			a[j] = a[i]-a[j];
			a[i] = a[i]-a[j];
		}
	}
	public static void main(String s[]){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int a[] = new int[n];
		for(int i = 0; i < n; i++){
			a[i] = sc.nextInt();
		}
		sort(a, a.length);
		for(int i : a){
			System.out.print(i+" ");
		}
		sc.close();
	}
}
