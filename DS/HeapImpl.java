import java.util.Scanner;

public class HeapImpl {
	int heap[];
	int size;
	int pos;
	
	public void init(int n){
		heap = new int[n+1];
		size = n;
		pos = 1;
	}
	
	void insert(int k){
		heap[pos] = k;
		int t = pos++;
		while(t/2>=1&&heap[t/2]>heap[t]){
			swap(t, t/2);
			t = t/2;
		}
	}
	
	int remove(){
		int root = heap[1];
		heap[1] = heap[pos];
		int t = 1;
		pos--;
		while((2*t<=pos&&heap[t]>heap[2*t])||(2*t+1<=pos&&heap[t]>heap[2*t+1])){
			if(2*t+1<=pos)
				if(heap[2*t]>heap[2*t+1]){
					swap(2*t+1,t);
					t=2*t+1;
					continue;
				}
			swap(2*t,t);
			t=2*t;
		}
		return root;
	}
	
	void swap(int i, int j){
		heap[i] = heap[i] + heap[j];
		heap[j] = heap[i] - heap[j];
		heap[i] = heap[i] - heap[j];
	}
	public static void main(String s[]){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		HeapImpl heap = new HeapImpl();
		heap.init(n);
		for(int i=0; i<n; i++){
			heap.insert(sc.nextInt());
		}
		heap.pos = n;
		for(int i=0; i<n; i++){
			System.out.print(heap.remove()+" ");
		}
		sc.close();
	}
}
