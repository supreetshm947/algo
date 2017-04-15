
public class MatrixMul {
	
	public static int[][] addMatrix(int a[][], int b[][], int n){
		int c[][] = new int [n][n];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				c[i][j] = a[i][j]+b[i][j];
			}
		}
		return c;
	}
	
	public static int[][] split (int a[][], int b[][], int n){
		if(n==1){
			int c[][] = {{a[0][0]*b[0][0]}};
			return c;
		}
		int[][] a1 = new int[n/2][n/2];
		int[][] a2 = new int[n/2][n/2];
		int[][] a3 = new int[n/2][n/2];
		int[][] a4 = new int[n/2][n/2];
		
		int[][] b1 = new int[n/2][n/2];
		int[][] b2 = new int[n/2][n/2];
		int[][] b3 = new int[n/2][n/2];
		int[][] b4 = new int[n/2][n/2];
		
		for(int i = 0; i < n/2; i++){
			for(int j = 0; j<n/2; j++){
				a1[i][j] = a[i][j];
			}
		}
		
		for(int i = 0; i < n/2; i++){
			for(int j = n/2; j<n; j++){
				a2[i][j-n/2] = a[i][j];
			}
		}
		
		for(int i = n/2; i < n; i++){
			for(int j = 0; j<n/2; j++){
				a3[i-n/2][j] = a[i][j];
			}
		}
		
		for(int i = n/2; i < n; i++){
			for(int j = n/2; j<n; j++){
				a4[i-n/2][j-n/2] = a[i][j];
			}
		}
		
		for(int i = 0; i < n/2; i++){
			for(int j = 0; j<n/2; j++){
				b1[i][j] = b[i][j];
			}
		}
		
		for(int i = 0; i < n/2; i++){
			for(int j = n/2; j<n; j++){
				b2[i][j-n/2] = b[i][j];
			}
		}
		
		for(int i = n/2; i < n; i++){
			for(int j = 0; j<n/2; j++){
				b3[i-n/2][j] = b[i][j];
			}
		}
		
		for(int i = n/2; i < n; i++){
			for(int j = n/2; j<n; j++){
				b4[i-n/2][j-n/2] = b[i][j];
			}
		}
		
		int[][] p = addMatrix(split(a1,b1,n/2),split(a2,b3,n/2),n/2);
		int[][] q = addMatrix(split(a1,b2,n/2),split(a2,b4,n/2),n/2);
		int[][] r = addMatrix(split(a3,b1,n/2),split(a4,b3,n/2),n/2);
		int[][] s = addMatrix(split(a3,b2,n/2),split(a4,b4,n/2),n/2);
		
		return bigMatrix(p,q,r,s,n/2);
		
	}
	
	public static int[][] bigMatrix(int[][] p, int[][] q, int [][]r, int[][] s, int n){
		int c[][] = new int[2*n][2*n];
		
		for(int i = 0; i<n; i++){
			for(int j = 0; j<n; j++){
				c[i][j] = p[i][j];
			}
		}
		
		for(int i = 0; i<n; i++){
			for(int j = n; j<2*n; j++){
				c[i][j] = q[i][j-n];
			}
		}
		
		for(int i = n; i<2*n; i++){
			for(int j = 0; j<n; j++){
				c[i][j] = r[i-n][j];
			}
		}
		
		for(int i = n; i<2*n; i++){
			for(int j = n; j<2*n; j++){
				c[i][j] = s[i-n][j-n];
			}
		}
		return c;
	}
	
	public static void main(String s[]){
		int a[][] = {{1,2},{3,4}};
		int b[][] = {{1,2},{3,4}};
		int r[][] = split(a, b, a.length);
		
		for(int i=0;i<r.length;i++){
			if(i>0)
			System.out.println();
			for(int j=0;j<r.length;j++){
				System.out.print(r[i][j]+"\t");
			}
		}
	}
}

