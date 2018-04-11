import java.util.Scanner;

public class MangoDistribution {
	
	private static int[][] matrix;
	
	public static void computeWays(int m, int b) {
		matrix = new int[m+1][b+1];
		for(int i=1;i<=m;i++) {
			for(int j=0;j<=b;j++) {
				if(i==1)
					matrix[i][j] = 1;
				else if(j<i)
					matrix[i][j] = matrix[i-1][j];
				else
					matrix[i][j] = matrix[i-1][j]+matrix[i][j-i];
			}
		}
	}
	
	public static void main(String s[]) {
		Scanner scan = new Scanner(System.in);
		int mango = scan.nextInt();
		int blokes = scan.nextInt();
		computeWays(mango, blokes);
		System.out.println(matrix[mango][blokes]);
		scan.close();
	}
}
