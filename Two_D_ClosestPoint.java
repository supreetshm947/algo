import java.awt.geom.Point2D;
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
public class Two_D_ClosestPoint {
	
	private double bestDistance = Double.POSITIVE_INFINITY;
	private Point2D point1, point2;
	
	public double getBestDistance() {
		return bestDistance;
	}

	public void setBestDistance(double bestDistance) {
		this.bestDistance = bestDistance;
	}

	public Point2D getPoint1() {
		return point1;
	}

	public void setPoint1(Point2D point1) {
		this.point1 = point1;
	}

	public Point2D getPoint2() {
		return point2;
	}

	public void setPoint2(Point2D point2) {
		this.point2 = point2;
	}

	void getClosestPair(Point2D[] points){
		int N = points.length;
		if(N<=1)
			return;
		
		Point2D pointsByX[] = new Point2D[N];
		for(int i=0;i<=N-1;i++){
			pointsByX[i] = points[i];
		}
		
		Arrays.sort(pointsByX, new X_ORDER());
		
		for(int i=0;i<N-1;i++){
			if(pointsByX[i].equals(pointsByX[i+1])){
				setBestDistance(0.0);
				setPoint1(pointsByX[i]);
				setPoint2(pointsByX[i+1]);
				return;
			}	
		}
	
		Point2D pointsByY[] = new Point2D[N];
		for(int i=0;i<=N-1;i++){
			pointsByY[i] = pointsByX[i];
		}
		
		Point2D aux[] = new Point2D[N];
		
		Divide(pointsByX, pointsByY, aux, 0, N-1);
		
	}
	
	double Divide(Point2D[] pointsByX, Point2D[] pointsByY, Point2D[] aux, int h, int t){
		if(h<t){
			int m = (h+t)/2;
			
			Point2D median = pointsByX[m];
			
			double delta1 = Divide(pointsByX, pointsByY, aux, h, m);
			double delta2 = Divide(pointsByX, pointsByY, aux, m+1, t);
			double delta = Math.min(delta1, delta2);
			
			merge(pointsByY, aux, h, m, t);
			
			int c=0;
			for(int i=h; i<=t; i++){
				if(Math.abs(pointsByY[i].getX()-median.getX())<delta)
					aux[c++] = pointsByY[i];
			}
			
			for(int i=0;i<c-1;i++){
				for(int j=i+1;(j<c)&&(Math.abs(aux[j].getY()-aux[i].getY())<delta);j++){
					double distance = aux[i].distance(aux[j]);
					if(distance<delta){
						delta = distance;
						if(delta<getBestDistance()){
							setBestDistance(delta);
							setPoint1(aux[i]);
							setPoint2(aux[j]);
						}
					}
				}
			}
			return delta;	
		}
		return Double.POSITIVE_INFINITY;
	}
	
	void merge(Point2D[] pointsByY, Point2D[] aux, int h, int m, int t){
		
		for(int k=h;k<=t;k++){
			aux[k] = pointsByY[k];
		}
		
		int i = h;
		int j = m+1;
		
		for(int k=h;k<=t;k++){
			if(i>m){
				pointsByY[k] = aux[j++];
			}
			else if(j>t){
				pointsByY[k] = aux[i++];
			}
			else if(aux[i].getY()<aux[j].getY()){
				pointsByY[k] = aux[i++];
			}
			else if(aux[i].getY()>aux[j].getY()){
				pointsByY[k] = aux[j++];
			}
			else {
				pointsByY[k] =aux[i++];
			}
		}
		
	}
	
	public static void main(String s[]){
		
		Point2D[] points = {new Point2D.Double(2,3),new Point2D.Double(56,3),new Point2D.Double(43,7),new Point2D.Double(22,3),new Point2D.Double(49,6)};
		
		Two_D_ClosestPoint obj = new Two_D_ClosestPoint();
		obj.getClosestPair(points);
		
		System.out.println("Closest Distance: "+obj.getBestDistance());
		System.out.println("Point1: ("+obj.getPoint1().getX()+","+obj.getPoint1().getY()+")");
		System.out.println("Point2: ("+obj.getPoint2().getX()+","+obj.getPoint2().getY()+")");
		
	}
}
