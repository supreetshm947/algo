import java.util.Scanner;

public class RunningMedian{
	
	int lowHeap[];
	int highHeap[];
	
	int pos1;
	int pos2;
	
	void init(int size){
		pos1 = 1;
		pos2 = 1;
		
		highHeap = new int[size/2+2];
		lowHeap = new int[size/2+1];
	}
	
	public double computeMedian(int k){
		if(pos1==1){
			insertHigh(highHeap, pos1, k);
			return highHeap[pos1++];
		}
		if(pos1==pos2){
			if(highHeap[1]>k){
				insertHigh(highHeap, pos1, k);
				pos1++;
			}else{
				if(lowHeap[1]<k){
					insertHigh(highHeap, pos1, removeLow(lowHeap, pos2));
					pos2--;
					pos1++;
					insertLow(lowHeap, pos2, k);
					pos2++;
				}else{
					insertHigh(highHeap, pos1, k);
					pos1++;
				}
			}
		}else{
			if(lowHeap[1]<k&&pos2>1){
				insertLow(lowHeap, pos2, k);
				pos2++;
			}else{
				if(highHeap[1]>k){
					insertLow(lowHeap, pos2, removeHigh(highHeap, pos1));
					pos2++;
					pos1--;
					insertHigh(highHeap, pos1, k);
					pos1++;
				}else{
					insertLow(lowHeap, pos2, k);
					pos2++;
				}
			}
		}
		
		if((pos1-1+pos2-1)%2!=0){
			return highHeap[1];
		}
		return ((double)(highHeap[1]+lowHeap[1])/2);
	}
	
	void insertHigh(int heap[], int pos, int k){
		heap[pos] = k;
		int t = pos;
		while(t/2>=1&&heap[t/2]<heap[t]){
			swap(heap, t, t/2);
			t = t/2;
		}
	}
	
	void insertLow(int heap[], int pos, int k){
		heap[pos] = k;
		int t = pos;
		while(t/2>=1&&heap[t/2]>heap[t]){
			swap(heap, t, t/2);
			t = t/2;
		}
	}
	
	int removeLow(int heap[], int pos){
		int root = heap[1];
		heap[1] = heap[pos-1];
		int t = 1;
		pos--;
		while((2*t<=pos&&heap[t]>heap[2*t])||(2*t+1<=pos&&heap[t]>heap[2*t+1])){
			if(2*t+1<=pos)
				if(heap[2*t]>heap[2*t+1]){
					swap(heap,2*t+1,t);
					t=2*t+1;
					continue;
				}
			swap(heap, 2*t,t);
			t=2*t;
		}
		return root;
	}
	
	int removeHigh(int heap[], int pos){
		int root = heap[1];
		heap[1] = heap[pos-1];
		int t = 1;
		pos--;
		while((2*t<=pos&&heap[t]<heap[2*t])||(2*t+1<=pos&&heap[t]<heap[2*t+1])){
			if(2*t+1<=pos)
				if(heap[2*t]<heap[2*t+1]){
					swap(heap,2*t+1,t);
					t=2*t+1;
					continue;
				}
			swap(heap, 2*t,t);
			t=2*t;
		}
		return root;
	}
	
	void swap(int heap[], int i, int j){
		heap[i] = heap[i] + heap[j];
		heap[j] = heap[i] - heap[j];
		heap[i] = heap[i] - heap[j];
	}

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        RunningMedian sol = new RunningMedian();
        sol.init(n);
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
            System.out.println(sol.computeMedian(a[a_i]));
        }
        in.close();
    }
}
