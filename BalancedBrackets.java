import java.util.Scanner;
import java.util.Stack;

public class BalancedBrackets {
	
	public static boolean isBalanced(String expression) {
		Stack<Character> open = new Stack<>();
		for(int i = 0 ; i<expression.length(); i++)
			if(i<expression.length()-1&&ismatch(expression.charAt(i), expression.charAt(i+1)))
				i++;
			else if(isopen(expression.charAt(i)))
				open.push(expression.charAt(i));
			else{
				if(open.size()>0&&!ismatch(open.pop(), expression.charAt(i)))
					return false;
			}
		
		return true;
	}
	
	static boolean ismatch(char a, char b){
		if(a=='('&&b==')')
			return true;
		else if(a=='['&&b==']')
			return true;
		else if(a=='{'&&b=='}')
			return true;
		return false;
	}
	
	static boolean isopen(char a){
		if(a=='('||a=='['||a=='{')
			return true;
		return false;
	}
	
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            String expression = in.next();
            System.out.println( (isBalanced(expression)) ? "YES" : "NO" );
        }
        in.close();
    }
}