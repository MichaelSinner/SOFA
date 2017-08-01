/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uan.processing;


import java.awt.image.BufferedImage;



/**
 *
 * @author DCA_3
 */
public class CorrelationImage {

   int Xini, Yini, Xend, Yend, XCorrelation, YCorrelation, pixelAverage;

    public void setXCorrelation(int XCorrelation) {
        this.XCorrelation = XCorrelation;
    }

    public void setYCorrelation(int YCorrelation) {
        this.YCorrelation = YCorrelation;
    }

    public void setPixelAverage(int pixelAverage) {
        this.pixelAverage = pixelAverage;
    }
  
   
  
   
    public int getXCorrelation() {
        return XCorrelation;
    }

    public int getYCorrelation() {
        return YCorrelation;
    }
   

    public int getXini() {
        return Xini;
    }

    public void setXini(int Xini) {
        this.Xini = Xini;
    }

    public int getYini() {
        return Yini;
    }

    public void setYini(int Yini) {
        this.Yini = Yini;
    }

    public int getXend() {
        return Xend;
    }

    public void setXend(int Xend) {
        this.Xend = Xend;
    }

    public int getYend() {
        return Yend;
    }

    public void setYend(int Yend) {
        this.Yend = Yend;
    }
    
    
    public int getpixelAverage() {
        return pixelAverage;
    }

    
    
    
  /* Finds the a region in one image that best matches another, smaller, image.
     * @param im1
     * @param im2
     * @return
 */
 public void findSubimage(BufferedImage im1, BufferedImage subImage){

   int w1 = this.Xend;
   int h1 = this.Yend;
   
   int w2 = subImage.getWidth(); 
   int h2 = subImage.getHeight();
   
   assert(w2 <= w1 && h2 <= h1);
   // will keep track of best position found
   int bestX = 0; 
   int bestY = 0; 
   double lowestDiff = Double.POSITIVE_INFINITY;
   // brute-force search through whole image (slow...)
 
   for(int x = this.Xini;x < w1-w2;x++){
     for(int y = this.Yini;y < h1-h2;y++){
       double comp = compareImages(im1.getSubimage(x,y,w2,h2),subImage);
        
       if( comp < lowestDiff ){
         bestX = x; bestY = y; lowestDiff = comp;
 
       }
     }
   }

   subImage.flush();
   subImage = null;
   im1.flush();
   im1 = null;
   
   
   this.XCorrelation = bestX;
   this.YCorrelation = bestY;
 }
 
 
 
 
 
 /* Determines how different two identically sized regions are.
     * @param im1
     * @param im2
     * @return
*/
    
 public double compareImages(BufferedImage im1, BufferedImage im2){
 
   //im1 = pedazo extraido de la imagen que se está analizando
   //im2 = plantilla  
   assert(im1.getHeight() == im2.getHeight() && im1.getWidth() == im2.getWidth());
   double variation = 0.0;
   int rgb,r,g,b,gray = 0;
   
   
   for(int x = 0; x < im1.getWidth(); x++){
     for(int y = 0; y < im1.getHeight(); y++){
   
         variation += compareARGB(im1.getRGB(x,y),im2.getRGB(x,y))/Math.sqrt(3);
      
        
        //Extracción promedio anillo interno
        if (((x >= 7)&&(y>= 7))&& ((x<=im1.getWidth()-7)&& (y<=im1.getHeight()-7)))
        {
        rgb = im1.getRGB(x, y);
        r = (rgb >> 16) & 0xFF;
        g = (rgb >> 8) & 0xFF;
        b = (rgb & 0xFF);
        gray =gray + ((r + g + b)/3);
        }
        
     
     
   
     }
     
      
   }

  //Promedio de pixeles de la ventana
  this.pixelAverage = (int) (gray/(im1.getWidth()* im1.getHeight()));
  return variation/(im1.getWidth()*im1.getHeight());
 }

 
 
 
 /*  
     Calculates the difference between two ARGB colours (BufferedImage.TYPE_INT_ARGB).
     * @param rgb1
     * @param rgb2
     * @return
 */
 public static double compareARGB(int rgb1, int rgb2){
   double r1 = ((rgb1 >> 16) & 0xFF)/255.0; double r2 = ((rgb2 >> 16) & 0xFF)/255.0;
   double g1 = ((rgb1 >> 8) & 0xFF)/255.0;  double g2 = ((rgb2 >> 8) & 0xFF)/255.0;
   double b1 = (rgb1 & 0xFF)/255.0;         double b2 = (rgb2 & 0xFF)/255.0;
   double a1 = ((rgb1 >> 24) & 0xFF)/255.0; double a2 = ((rgb2 >> 24) & 0xFF)/255.0;
   // if there is transparency, the alpha values will make difference smaller
   return a1*a2*Math.sqrt((r1-r2)*(r1-r2) + (g1-g2)*(g1-g2) + (b1-b2)*(b1-b2));
 }    
    
    
    

 
    
  

    
}
