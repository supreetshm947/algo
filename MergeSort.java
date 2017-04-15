
public class MergeSort {
	
	public static int[] a = {7,5,4,3,1,0,9,2,6,8}; 
	
	public static void divide(int a[], int l, int h){
		if(h>l){
			int m = l+(h-l)/2;
			divide(a,l,m);
			divide(a,m+1,h);
			merge(a,l,m,h);
		}
	}
	
	public static void merge(int a[], int l, int m, int h){ 
		int n1 = m-l+1;
		int n2 = h-m;
		
		int[] a1 = new int [n1];
		int[] a2 = new int [n2];
		
		int j = 0, k = 0;
		
		for(int i = l; i <= m ; i++){
			a1[j++] = a[i];
		}
		for(int i = m+1; i <= h; i++){
			a2[k++] = a[i];
		}
		int i = l;
		j= 0;
		k= 0;
		
		while(j<n1&&k<n2){
			if(a1[j]<a2[k]){
				a[i++] = a1[j++];
			}else{
				a[i++] = a2[k++];
			}
		}
		while(j<n1){
			a[i++] = a1[j++];
		}
		while(k<n2){
			a[i++] = a2[k++];
		}
		
	}
	
	public static void main(String s[]){
		divide(a, 0, a.length-1);
		for(int l : a){
			System.out.print(l+" ");
		}
	}
}
