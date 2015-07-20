/*
 * Some Practice with:
 * Eclipse, and USING objects, and
 * methods, and adding files that someone
 * else gave me.(and a peek at GUIs)
 * 
 * @author Nathan Anneken
 * @version 1.0
 */
import java.awt.Point;
public class PointPractice {

	public static void main(String[] args) {
		System.out.println("Hello!");
		//create a Point object
		//create an array of Point objects
		// Create a method that does something to an array
		//Add a file that Norm wrote
		//something pretty 
		Point p = new Point(5,5);
		Point q;
		q = new Point(8,2);
		System.out.println(p.getX());
		System.out.println(q.getY());
		System.out.println(p.getLocation());
		System.out.println(p.toString());
		System.out.println(p);
		System.out.println("Nudging P three to the left:");
		p.translate(-3, 0);
		System.out.println(p);
		
		// create an array of 500 Points
		Point[] points = new Point[500];
		//warning! We do no have 500 Points yet.
		// We have 500 Point REFERENCES, all set to null
		
		// Creates the 500 points
		for(int i=0; i<points.length; i++) {
			
			int randomX = (int)(500* Math.random());
			int randomY = (int)(500* Math.random());
			points[i] = new Point(randomX, randomY);
		}
		for(Point pt : points)
			System.out.println(pt);
		new PointDisplay(points, 5, false, 6);
		
	}

}