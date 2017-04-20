

public class One_D_ClosestPoint {
	
	int large = 0;
	int  ClosestPair(int a[], int h, int t){
		if(t>h){
			int m = h+(t-h)/2;
			ClosestPair(a,h,m);
			ClosestPair(a,m+1,t);
			return getClosest(a,h,m,t);
		}
		return 0;
	}
	int getClosest(int a[], int h, int m, int t){
		int n1 = m-h+1;
		int n2 = t-m;
		
		
		int arr1[] = new int[n1];
		int arr2[] = new int[n2];
		
		
		for(int i=0;i<n1;i++){
			arr1[i]=a[i+h];
		}
		
		for(int i=0;i<n2;i++){
			arr2[i]=a[i+m+1];
		}

		int k=h;
		int i=0;
		int j=0;
		
		while(i!=n1&&j!=n2){
			if(arr1[i]>arr2[j]){
				a[k++]=arr2[j++];
			}
			else if(arr2[j]>arr1[i]){
				a[k++]=arr1[i++];
			}
			else{
				a[k++]=arr1[i++];
			}
			if(k>1)
			if((Math.abs(a[k-1]-a[k-2]))<(Math.abs(a[large+1]-a[large]))){
				large = k-2;
			}
		}
		
		while(i!=n1){
			a[k++]=arr1[i++];
			if(k>1)
				if((Math.abs(a[k-1]-a[k-2]))<(Math.abs(a[large+1]-a[large]))){
				large = k-2;
			}
		}
		
		while(j!=n2){
			a[k++]=arr2[j++];
			if(k>1)
				if((Math.abs(a[k-1]-a[k-2]))<(Math.abs(a[large+1]-a[large]))){
				large = k-2;
			}
		}
		
		return large;
	}
	
	public static void main(String s[]){
		int a[]={1,8,99,3,5,66,43,434};
		
		int closestIndex = new One_D_ClosestPoint().ClosestPair(a, 0, a.length-1);
		
		System.out.println("Closest Pair is: ("+a[closestIndex]+","+a[closestIndex+1]+")");
		
		/*for(int i=0;i<a.length;i++){
			System.out.print(a[i]+"\t");
		}*/
	}
}

