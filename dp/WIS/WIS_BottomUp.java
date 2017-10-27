
public class WIS_BottomUp {
	public static int m[] = new int[10];
	public static void getISWieight(int a[]) {
		for(int i = 1; i<a.length; i++) {
			m[i+1] = Math.max(m[i], m[i-1]+a[i]);
		}
	}
	public static void main(String s[]) {
		int a[] = {1,4,5,4};
		m[0] = 0;
		m[1] = a[0];
		getISWieight(a);
		System.out.println(m[a.length]);
	}
}
