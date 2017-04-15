import java.util.Scanner;

public class ArrayLeftRotation {

    public static int[] arrayLeftRotation(int[] a, int n, int k) {
      int temp[] = new int[n];
      k = k%n;
      int j=0;
      for(int i= k; i<n;i++){
          temp[j++]=a[i];
      }
      for(int i= 0; i< k;i++){
          temp[j++]=a[i];
      }
      return temp;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
      
        int[] output = new int[n];
        output = arrayLeftRotation(a, n, k);
        for(int i = 0; i < n; i++)
            System.out.print(output[i] + " ");
      
        System.out.println();
      
    }
}
