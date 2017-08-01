/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Math;


import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author DCA_3
 */
public class PixelOperation {
    
public Point BarycenterCalculate(ArrayList<Point> ContourPixels)
{
    int Xaverage = 0, Yaverage = 0;
    Point Barycenter = new Point(Xaverage,Yaverage);
    if (ContourPixels.size()>0)
    {
        for(int ArrayElement=0; ArrayElement < ContourPixels.size(); ArrayElement++) 
        {
            Xaverage = Xaverage + ContourPixels.get(ArrayElement).x ;
            Yaverage = Yaverage + ContourPixels.get(ArrayElement).y ;
        }
    Xaverage = (Xaverage / ContourPixels.size());  
    Yaverage = (Yaverage / ContourPixels.size()); 
    Barycenter = new Point(Xaverage,Yaverage);
    } 
  
   return Barycenter;
}




public double PerimeterCalculate2(ArrayList<Point> PerimeterPixels)
{
    double Per=0;
    for(int x=0;x< PerimeterPixels.size()-1;x++)
    {
        Per= Per +   Math.sqrt(Math.pow((PerimeterPixels.get(x+1).x-PerimeterPixels.get(x).x),2)+ Math.pow((PerimeterPixels.get(x+1).y-PerimeterPixels.get(x).y),2));
    }             
   return Per;
}



public double PolygonArea(ArrayList<Point> CoordinateContours)
{
/*
Point[] p = new Point[] {new Point(1776,1355),
new Point(1791,1434),
new Point(1791,1449),
new Point(1776,1449),

};


  double   area2 = 0;         // Accumulates area in the loop
  int      J = p.length-1;
  for(int I=0;I< p.length;I++)
   {
      area2 = area2 +  (p[J].x + p[I].x )*(p[J].y -p[I].y ); 
      J = I;
   }   
    
  System.out.println("Area alterna "  + Math.abs(area2/2));*/

    double   area = 0;         // Accumulates area in the loop
    int      j = CoordinateContours.size()-1;
    for(int i=0;i< CoordinateContours.size();i++)
    {
        area = area +  (CoordinateContours.get(j).x + CoordinateContours.get(i).x)*(CoordinateContours.get(j).y - CoordinateContours.get(i).y); 
        j = i;
    }   
    
  return Math.abs(area/2);
}

/*
function polygonArea(X, Y, numPoints) 
{ 
  area = 0;         // Accumulates area in the loop
  j = numPoints-1;  // The last vertex is the 'previous' one to the first

  for (i=0; i<numPoints; i++)
    { area = area +  (X[j]+X[i]) * (Y[j]-Y[i]); 
      j = i;  //j is previous vertex to i
    }
  return area/2;
}
*/





/*public double PerimeterCalculate(double radio)
{
   double Perimeter;
   Perimeter = Math.PI * (2*radio);
   return Perimeter;
}*/


public double RoundnessCalculate(double Area, double Perimeter)
{
   double Roundness;
   
   if ((Area>0)&&(Perimeter>0))
   Roundness = 1/((Math.pow(Perimeter, 2)/(4*(Math.PI)*Area)));
   else
    Roundness = 0;   
   return Roundness;

}



}
