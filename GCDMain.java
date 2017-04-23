import java.util.Scanner;

public class GCDMain {
	
	static int a, b;
	
	public static int getGCD(){
		if(a<0||b<0){
			return 1;
		}else{
			if(a<b){
				swap();
			}
			if(a%b==0){
				return b;
			}else{
				swap();
				b = b - a;
				return getGCD();
			}
		}
	}
	
	public static void swap(){
		a = a + b;
		b = a - b;
		a = a - b;
	}
	
	public static void main(String s[]){
		Scanner in = new Scanner(System.in);
        a = in.nextInt();
        b = in.nextInt();
		System.out.println(getGCD());
		in.close();
	}
}
