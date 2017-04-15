
public class KaratSuba {

	public int recursive(int x, int y){
		int n = getLength(x);
		if(n==1){
			return x*y;
		}
		int pow = (int)Math.pow(10, n/2);
		int a = x/pow;
		int b = x%pow;
		int c = y/pow;
		int d = y%pow;
		
		int n1 = recursive(a, c);
		int n2 = recursive(a + b, c + d);
		int n3 = recursive(b, d);
		
		return (pow*pow)*n1 + pow*(n2-n1-n3) + n3;
		
		//return pow*recursive(a, c)+(pow*recursive(a, d)+pow*recursive(b, c))+recursive(b, d);
	}
	public int getLength(int x){
		int n = 0;
		while(x>0){
			x/=10;
			n++;
		}
		return n;
	}
	public static void main(String[] args) {
		System.out.println(new KaratSuba().recursive(24, 11));
	}

}
