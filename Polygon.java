/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polygon;
import static java.lang.Math.*;
/**
 *
 * @author mtlash
 */
public class Polygon {

    /**
     * @param args the command line arguments
     */
    Point[] points;
    Polygon(Point[] points){
        this.points = points;
    }
    
    double getLengthOfSides() {
        //You write!
        double value = 0.0;
        double y2y1 = 0.0;
        double x2x1 = 0.0;
        for(int i = 1; i < points.length; i++){
            x2x1 = points[i].x - points[i-1].x;
            y2y1 = points[i].y - points[i-1].y;
            x2x1 = Math.pow(x2x1, 2);
            y2y1 = Math.pow(y2y1, 2);
            x2x1 = Math.sqrt((x2x1 + y2y1));
            value = value + x2x1;
        }
        x2x1 = points[0].x - points[points.length-1].x;
        y2y1 = points[0].y - points[points.length-1].y;
        x2x1 = Math.pow(x2x1, 2);
        y2y1 = Math.pow(y2y1, 2);
        x2x1 = Math.sqrt((x2x1 + y2y1));
        value = value + x2x1;
        return value;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Point[] arrayOfPoints = {new Point(0,0), new Point(0,10),
            new Point(10,10), new Point(10,0) };
        
        Polygon myPolygon = new Polygon(arrayOfPoints);
        System.out.println("Total length of all sides: " + 
                myPolygon.getLengthOfSides());
        
    }
    
}
