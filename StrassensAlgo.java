class StrassensAlgo{
	
	public static int[][] addMatrix(int a[][], int b[][], int n){
		int[][] c = new int[n][n];
		for(int i = 0; i<n; i++){
			for(int j=0; j<n; j++){
				c[i][j] = a[i][j]+b[i][j];
			}
		}
		return c;
	}
	
	public static int[][] subMatrix(int a[][], int b[][], int n){
		int[][] c = new int[n][n];
		for(int i = 0; i<n; i++){
			for(int j=0; j<n; j++){
				c[i][j] = a[i][j]-b[i][j];
			}
		}
		return c;
	}
	
	public static int[][] mulMatrix(int a[][], int b[][], int n){
		if(n==1){
			int c[][] = {{a[0][0]*b[0][0]}};
			return c;
		}
		int[][] a1 = new int [n/2][n/2];
		int[][] a2 = new int [n/2][n/2];
		int[][] a3 = new int [n/2][n/2];
		int[][] a4 = new int [n/2][n/2];
		
		int[][] b1 = new int [n/2][n/2];
		int[][] b2 = new int [n/2][n/2];
		int[][] b3 = new int [n/2][n/2];
		int[][] b4 = new int [n/2][n/2];
		
		for(int i = 0; i<n/2; i++){
			for(int j= 0; j<n/2; j++){
				a1[i][j] = a[i][j];
				b1[i][j] = b[i][j];
			}
		}
		
		for(int i = 0; i<n/2; i++){
			for(int j= n/2; j<n; j++){
				a2[i][j-n/2] = a[i][j];
				b2[i][j-n/2] = b[i][j];
			}
		}
		
		for(int i = n/2; i<n; i++){
			for(int j= 0; j<n/2; j++){
				a3[i-n/2][j] = a[i][j];
				b3[i-n/2][j] = b[i][j];
			}
		}
		
		for(int i = n/2; i<n; i++){
			for(int j= n/2; j<n; j++){
				a4[i-n/2][j-n/2] = a[i][j];
				b4[i-n/2][j-n/2] = b[i][j];
			}
		}
		
		int p1[][] = mulMatrix(a1, subMatrix(b2, b4, n/2), n/2);
		int p2[][] = mulMatrix(addMatrix(a1, a2, n/2), b4, n/2);
		int p3[][] = mulMatrix(addMatrix(a3, a4, n/2), b1, n/2);
		int p4[][] = mulMatrix(a4, subMatrix(b3, b1, n/2), n/2);
		int p5[][] = mulMatrix(addMatrix(a1, a4, n/2), addMatrix(b1, b4, n/2), n/2);
		int p6[][] = mulMatrix(subMatrix(a2, a4, n/2), addMatrix(b3, b4, n/2), n/2);
		int p7[][] = mulMatrix(subMatrix(a1, a3, n/2), addMatrix(b1, b2, n/2), n/2);
		
		int w[][] = addMatrix(subMatrix(addMatrix(p5, p4, n/2), p2, n/2), p6, n/2);
		int x[][] = addMatrix(p1, p2, n/2);
		int y[][] = addMatrix(p3, p4, n/2);
		int z[][] = subMatrix(addMatrix(p1, p5, n/2), addMatrix(p3, p7, n/2), n/2);
		
		return bigMatrix(w, x, y, z, n);

		
		}

	private static int[][] bigMatrix(int[][] c1, int[][] c2, int[][] c3, int[][] c4, int n) {
		int c[][] = new int[n][n];
		
		for(int i =0; i<n/2;i++)
			for(int j =0; j<n/2;j++)
				c[i][j] = c1[i][j];
		
		for(int i =0; i<n/2;i++)
			for(int j =n/2; j<n;j++)
				c[i][j] = c2[i][j-n/2];
		
		for(int i =n/2; i<n;i++)
			for(int j =0; j<n/2;j++)
				c[i][j] = c3[i-n/2][j];
		
		for(int i =n/2; i<n;i++)
			for(int j =n/2; j<n;j++)
				c[i][j] = c4[i-n/2][j-n/2];
		
		return c;
	}
	
	public static void main(String s[]){
		int a[][] = {{1,2},{3,4}};
		int b[][] = {{1,2},{3,4}};
		int r[][] = mulMatrix(a, b, a.length);
		
		for(int i=0;i<r.length;i++){
			if(i>0)
			System.out.println();
			for(int j=0;j<r.length;j++){
				System.out.print(r[i][j]+"\t");
			}
		}
	}
}