import java.util.Scanner;

public class MergeSort {
	
	public static void divide(int a[], int l, int h){
		if(h>l){
			int m = l+(h-l)/2;
			divide(a,l,m);
			divide(a,m+1,h);
			merge(a,l,m,h);
		}
	}
	
	public static void merge(int a[], int l, int m, int h){ 
		int i = l;
		int j = m+1;
		int k = l;
		int aux[] = a.clone();
		while(i<=m||j<=h){
			if(i>m){
				a[k++] = aux[j++];
			}else if(j>h){
				a[k++] = aux[i++];
			}else if(aux[i]<=aux[j]){
				a[k++] = aux[i++];
			}else {
				a[k++] = aux[j++];
			}
		}
	}

	public static void main(String s[]){
		Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
        	int n = in.nextInt();
            int arr[] = new int[n];
            for(int arr_i=0; arr_i < n; arr_i++){
                arr[arr_i] = in.nextInt();
            }
        	divide(arr, 0, arr.length-1);
    		for(int l : arr){
    			System.out.print(l+" ");
    		}
    		System.out.println();
        }
        in.close();
	}
}
