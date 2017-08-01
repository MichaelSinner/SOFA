/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uan.processing;


import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.WritableRaster;
import java.awt.image.renderable.ParameterBlock;
import java.util.ArrayList;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.RenderedOp;

/**
 *
 * @author DCA_3
 */
public class ImagetoBWparameterbyWindow1 {
    
    BufferedImage BIColor, BIBW, BIErode;
    BufferedImage BIGray = new BufferedImage(3488, 2616, BufferedImage.TYPE_BYTE_GRAY);

 
    
    public void setBIBW(BufferedImage BIBW) {
        
        if (this.BIBW!=null)
        {
            this.BIBW.flush();
            this.BIBW = null;
        }
        
        this.BIBW = BIBW;
        
        BIBW.flush();
        BIBW = null;
        
        
        
        
    }
    

    public BufferedImage getBIGray() {
        return BIGray;
    }

     public void setBIGray()  {
        this.BIGray.getGraphics().drawImage(BIColor, 0, 0, null);
    }

    public BufferedImage getBIColor() {
        return BIColor;
    }

    public void setBIColor(BufferedImage BIColor) {
       
        if (this.BIColor!=null)
        {
            this.BIColor.flush();
            this.BIColor = null;
        }
        this.BIColor = BIColor;

    
    }

    public BufferedImage getBIBW() {
        return BIBW;
    }

    public BufferedImage getBIErode() {
        return BIErode;
    }

 
    

    

    
    
public BufferedImage thresholdImage( ArrayList<Point> InitPoints, ArrayList<Point> EndPoints, ArrayList<Integer> threshold) {

if (BIBW!=null)
{
    BIBW.flush();
    BIBW = null;
}


BIBW=this.BIGray;
WritableRaster raster = BIBW.getRaster();


    
    
  for(int Index = 0;  Index < InitPoints.size(); Index++)
  {
  
/*  System.out.println("Coordenada inicial " + InitPoints.get(Index).x  + "," + InitPoints.get(Index).y);
  System.out.println("Coordenada Final "   + EndPoints.get(Index).x   + "," + EndPoints.get(Index).y);
  System.out.println("Umbral " + threshold.get(Index));
  System.out.println("---------------------------------------");*/
  
  int max = 0;
  int min = 255;
 
  int[] pixels = new int[1];
  
 int umbral=0;
 int elementos = 0;
for (int y = InitPoints.get(Index).y ; y < EndPoints.get(Index).y; y++) 
  
    {
           for (int x = InitPoints.get(Index).x ; x < EndPoints.get(Index).x; x++) 

            {

                elementos++;
                raster.getPixel(x, y,pixels);
                if ( pixels[0]> max)
                    max = pixels[0];
                
                if ( pixels[0]< min)
                    min = pixels[0];
                
                umbral = umbral + pixels[0];
             }  
        
     }  


System.out.println("Min "+ min);
System.out.println("Max "+ max);  
System.out.println("Maximos / Minimos "+ (max+min)/2);  



System.out.println("Umbral general x ventana "+ (umbral/elementos));
System.out.println("No. de elementos "+ elementos);  
System.out.println("-------------------------------------");    
  
   
   // System.out.println("Threshold " + threshold.get(Index));
  // System.out.println("Umbralizando puntos entre (" + InitPoints.get(Index).x + "," + InitPoints.get(Index).y + ")"+ "(" + EndPoints.get(Index).x + "," + EndPoints.get(Index).y +")" );
      for (int y = InitPoints.get(Index).y ; y < EndPoints.get(Index).y; y++) 
  
    {
           for (int x = InitPoints.get(Index).x ; x < EndPoints.get(Index).x; x++) 

            {


                raster.getPixel(x, y,pixels );
                if (pixels[0] < (max+min)/2) 
                    pixels[0] = 0;
                         else pixels[0] = 255;
                raster.setPixels(x, y, 1, 1, pixels);
             }  
        
     }  

     }  
    


    
    return BIBW;
}    
    
    
/*public BufferedImage thresholdImage(int threshold,int threshold1,int threshold2) {
   
  
     if (BIBW!=null)
        {
            BIBW.flush();
            BIBW = null;
        }
    
    BIBW=this.BIGray;
    
    
    WritableRaster raster = BIBW.getRaster();
    int[] pixels = new int[BIColor.getWidth()];
    for (int y = 0; y < BIColor.getHeight(); y++) 
    {
        raster.getPixels(0, y, BIColor.getWidth(), 1, pixels);
        
        for (int i = 0; i < pixels.length; i++) 
        {
            
           if ((i>=0)&&(i<pixels.length/3) )     
                {        
                 if (pixels[i] < threshold) pixels[i] = 0;
                 else pixels[i] = 255;
                }
           if ((i>=pixels.length/3)&&(i<(2*(pixels.length/3))))     
                {        
                 if (pixels[i] < threshold1) pixels[i] = 0;
                 else pixels[i] = 255;
                }
           
            if ((i>=(2*(pixels.length/3)))&&(i<=pixels.length) )    
            {
                if (pixels[i] < threshold2) pixels[i] = 0;
                else pixels[i] = 255;
            }
            
            
        }
    
        
        raster.setPixels(0, y, BIColor.getWidth(), 1, pixels);
    }
    
   raster=null;
    
    return BIBW;
} */ 
    

public BufferedImage ErodingImage() {
    
  if (this.BIErode!=null)
        {
            this.BIErode.flush();
            this.BIErode = null;
        }
        
     BufferedImage src = BIBW;
     Kernel kernel = new Kernel(3, 3, new float[] { //
                        1.00f, 1.00f, 1.00f, //
                        1.00f, 1.00f, 1.00f, //
                        1.00f, 1.00f, 1.00f, //
                });
      ConvolveOp imgOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
      this.BIErode = imgOp.filter(src, null);
      imgOp.filter(src, null).flush();
      src.flush();
      src=null;
      imgOp = null;
      kernel = null;
      return this.BIErode;
}    

@SuppressWarnings("empty-statement")


public BufferedImage MorfologicalImage() {

PlanarImage src1= PlanarImage.wrapRenderedImage(BIBW);
PlanarImage src2= PlanarImage.wrapRenderedImage(BIErode);
ParameterBlock pb = new ParameterBlock();
pb.addSource(src2);
pb.addSource(src1);
RenderedOp subtractedImage = JAI.create("subtract",pb); 

src1.getAsBufferedImage().flush();
src2.getAsBufferedImage().flush();
pb = null;


return subtractedImage.getAsBufferedImage();
}
 


}
