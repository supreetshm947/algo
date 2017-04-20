import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class X_ORDER implements Comparator<Point2D>{

	@Override
	public int compare(Point2D o1, Point2D o2) {
		if(o1.getX()==o2.getX())  
			return 0;  
		else if(o1.getX()>o2.getX())  
			return 1;  
		else  
			return -1;  
	}
	
}

class Y_ORDER implements Comparator<Point2D>{

	@Override
	public int compare(Point2D o1, Point2D o2) {
		if(o1.getY()==o2.getY())  
			return 0;  
		else if(o1.getY()>o2.getY())  
			return 1;  
		else  
			return -1;  
	}
	
}


public class TwoDClosest_TimRoug {	
	
	static double best = Double.POSITIVE_INFINITY;
	static ArrayList<Point2D> bestPair = new ArrayList<>();
	
	public static void divide(Point2D[] Px, Point2D[] Py, int n){
		if(n>2){
			Point2D Ax[] = new Point2D[n/2];
			Point2D Bx[] = new Point2D[n/2];
			Point2D Ay[] = new Point2D[n/2];
			Point2D By[] = new Point2D[n/2];
			for(int i=0;i<n;i++){
				if(i<n/2){
					Ax[i] = Px[i];
					Ay[i] = Py[i]; 
				}else{
					Bx[i-n/2] = Px[i];
					By[i-n/2] = Py[i];
				}
			}
			divide(Ax, Ay, n/2 );
			divide(Bx, By, n/2 );
			
			closestSplitPair(Px, Py, n);
			
		}else if (n==2){
			best = Px[0].distance(Px[1]);
			bestPair.clear();
			bestPair.add(Px[0]);
			bestPair.add(Px[1]);
		}else{
			best = Double.POSITIVE_INFINITY;
			bestPair.clear();
		}
	}
	private static double closestSplitPair(Point2D[] px, Point2D[] py, int n) {
			Point2D meanX = px[n/2-1];
			ArrayList<Point2D> Sy = new ArrayList<>();
			
			for(Point2D yPoint : py){
				if(yPoint.getX()<(meanX.getX()+best)||yPoint.getX()<(meanX.getX()+best)){
					Sy.add(yPoint);
				}
			}
			
			for(int i = 0; i<Sy.size()-1;i++){
				double len = Double.min(7, Sy.size()-i);
				for( int j=1; j< len; j++){
					if(Sy.get(i).distance(Sy.get(j))<best){
						best = Sy.get(i).distance(Sy.get(j));
						bestPair.clear();
						bestPair.add(Sy.get(i));
						bestPair.add(Sy.get(j));
					}
				}
			}
			
		return 0;
	}
	
	public static void main(String s[]){
		Point2D[] a = {new Point2D.Double(2,3),new Point2D.Double(56,3),new Point2D.Double(43,7),new Point2D.Double(22,3)};
		
		Point2D x_a[] = a.clone();
		Arrays.sort(x_a, new X_ORDER());
		Point2D y_a[] = a.clone();
		Arrays.sort(y_a, new Y_ORDER());
		
		divide(x_a, y_a, a.length);
		
		if(bestPair.size()==2){
			System.out.println("Best Distance:"+best);
			for(Point2D point : bestPair){
				System.out.print("("+point.getX()+","+point.getY()+")"+"\t");
			}
		}
	}
}
