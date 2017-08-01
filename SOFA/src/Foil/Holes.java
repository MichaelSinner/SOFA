/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Foil;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author DCA_3
 */
public class Holes implements Serializable {

    
    Point center, innerPoint, outerPoint, barycenter, barycenterouter;
     Double InnerPerimeter, OuterPerimeter, InnerArea, OuterArea, Roundness, RoundnessOuter;

    public Point getBarycenterouter() {
        return barycenterouter;
    }

    public void setBarycenterouter(Point barycenterouter) {
        this.barycenterouter = barycenterouter;
    }

    public Double getRoundnessOuter() {
        return RoundnessOuter;
    }

    public void setRoundnessOuter(Double RoundnessOuter) {
        this.RoundnessOuter = RoundnessOuter;
    }
   

    public Double getRoundness() {
        return Roundness;
    }

    public void setRoundness(Double Roundness) {
        this.Roundness = Roundness;
    }

    public Double getInnerPerimeter() {
        return InnerPerimeter;
    }

    public void setInnerPerimeter(Double InnerPerimeter) {
        this.InnerPerimeter = InnerPerimeter;
    }

    public Double getOuterPerimeter() {
        return OuterPerimeter;
    }

    public void setOuterPerimeter(Double OuterPerimeter) {
        this.OuterPerimeter = OuterPerimeter;
    }

    public Double getInnerArea() {
        return InnerArea;
    }

    public void setInnerArea(Double InnerArea) {
        this.InnerArea = InnerArea;
    }

    public Double getOuterArea() {
        return OuterArea;
    }

    public void setOuterArea(Double OuterArea) {
        this.OuterArea = OuterArea;
    }
    String status ;

    

  
    ArrayList<Point> InnerHolePoints = new ArrayList<Point>();
    ArrayList<Point> OuterHolePoints = new ArrayList<Point>();

    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Point getBarycenter() {
        return barycenter;
    }

    public void setBarycenter(Point barycenter) {
        this.barycenter = barycenter;
    }

    public ArrayList<Point> getInnerHolePoints() {
        return InnerHolePoints;
    }

    public void setInnerHolePoints(ArrayList<Point> InnerHolePoints) {
        this.InnerHolePoints = InnerHolePoints;
    }

    public ArrayList<Point> getOuterHolePoints() {
        return OuterHolePoints;
    }

    public void setOuterHolePoints(ArrayList<Point> OuterHolePoints) {
        this.OuterHolePoints = OuterHolePoints;
    }
    
    
    public Point getInnerPoint() {
        return innerPoint;
    }

    public void setInnerPoint(Point innerPoint) {
        this.innerPoint = innerPoint;
    }

    public Point getOuterPoint() {
        return outerPoint;
    }

    public void setOuterPoint(Point outerPoint) {
        this.outerPoint = outerPoint;
    }
 

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public ArrayList<Point> getInnerHole() {
        return InnerHolePoints ;
    }

    public void setInnerHole(ArrayList<Point> InnerHole) {
        this.InnerHolePoints  = InnerHole;
    }

   

    

    
}
