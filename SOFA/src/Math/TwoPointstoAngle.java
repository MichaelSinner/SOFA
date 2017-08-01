/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Math;

import static java.lang.Math.round;

/**
 *
 * @author DCA_3
 */
public class TwoPointstoAngle {
    public Double PointX1,PointY1,PointX2,PointY2;
    public Double  m, theta, distance;

    public Double getTheta() {
        return theta;
    }

 
    public void setPointX1(Double PointX1) {
        this.PointX1 = PointX1;
    }

    public void setPointY1(Double PointY1) {
        this.PointY1 = PointY1;
    }

    public void setPointX2(Double PointX2) {
        this.PointX2 = PointX2;
    }

    public void setPointY2(Double PointY2) {
        this.PointY2 = PointY2;
    }

    
    public TwoPointstoAngle(Double X1,Double Y1,Double X2,Double Y2)
    {
        PointX1=X1;
        PointY1=Y1;
        PointX2=X2;
        PointY2=Y2;
    }
    
  
    
    
    
    public double AngleCalc ()
    {
        m = (PointY1-PointY2)/(PointX1-PointX2) ;
        //System.out.println("m = " + m);
        theta = Math.abs( Math.toDegrees(Math.atan(m))) ;
        return   (theta);
    }
    
    
    public double EuclideanDistance ()
    {
        distance = Math.sqrt(Math.pow((PointX1-PointX2),2)+ Math.pow((PointY1-PointY2),2));
    //System.out.println("m = " + m);
    
        return (distance);
    }
}
