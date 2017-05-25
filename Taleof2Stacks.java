import java.util.Scanner;
import java.util.Stack;

public class Taleof2Stacks {
	
	static class MyQueue<T> {
		Stack<T> newOnTop = new Stack<>();
		Stack<T> oldOnTop = new Stack<>();
		
		void enqueue(T item){
			newOnTop.push(item);
		}
		
		T dequeue(){
			if(oldOnTop.isEmpty())
				fillOld();
			return oldOnTop.pop();
		}
		
		T peek(){
			if(oldOnTop.isEmpty())
				fillOld();
			return oldOnTop.peek();	
		}
		
		void fillOld(){
			if(!newOnTop.isEmpty()){
				oldOnTop.push(newOnTop.pop());
			}
		}
	}
	
	public static void main(String[] args) {
        MyQueue<Integer> queue = new Taleof2Stacks.MyQueue<>();

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();
            if (operation == 1) { // enqueue
              queue.enqueue(scan.nextInt());
            } else if (operation == 2) { // dequeue
              queue.dequeue();
            } else if (operation == 3) { // print/peek
              System.out.println(queue.peek());
            }
        }
        scan.close();
    }
}

