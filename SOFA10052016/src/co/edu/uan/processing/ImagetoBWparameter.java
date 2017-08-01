/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uan.processing;

import java.awt.Graphics2D;
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
public class ImagetoBWparameter {
    
    BufferedImage BIColor, BIBW, BIErode;
    BufferedImage BIGray        = new BufferedImage(3488, 2616, BufferedImage.TYPE_BYTE_GRAY);
    BufferedImage BIBWHistogram = new BufferedImage(3488, 2616, BufferedImage.TYPE_BYTE_BINARY); 
 
    
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

    
public BufferedImage setBIBWHistogram()  {

  // Read an image. 
  BufferedImage input = this.BIColor;
  // Create a black-and-white image of the same size.
  BufferedImage im =    new BufferedImage(input.getWidth(),input.getHeight(),BufferedImage.TYPE_BYTE_BINARY);
  // Get the graphics context for the black-and-white image.
  Graphics2D g2d = im.createGraphics();
  // Render the input image on it.
  g2d.drawImage(input,0,0,null);
  // Store the resulting image using the PNG format.
 //  this.BIBWHistogram = im; 
  
 
  
    
    
 
 
 
 return BIBW = im;

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

    

public BufferedImage BWHistogram() {    
        return null;
        
    
}

    
    
public BufferedImage thresholdImage(int threshold) {

    if (BIBW!=null)
        {
            BIBW.flush();
            BIBW = null;
        }
    
    BIBW=this.BIGray;
    WritableRaster raster = BIBW.getRaster();
    int[] pixels = new int[BIColor.getWidth()];
    for (int y = 0; y < BIColor.getHeight(); y++) {
        raster.getPixels(0, y, BIColor.getWidth(), 1, pixels);
        for (int i = 0; i < pixels.length; i++) {
            if (pixels[i] < threshold) pixels[i] = 0;
            else pixels[i] = 255;
        }
        raster.setPixels(0, y, BIColor.getWidth(), 1, pixels);
    }
   raster=null;
    return BIBW;
}    
    

//Calcula valor Umbral x Promedio del Valor Maximo con El Valor Minimo
public int Threshold_MaxMin_Media(Point StartWindow, Point EndWindow, BufferedImage bImage)
 {
     Double G = 0.0 ;
     Double U;
     Double M = 0.0;
     
     int Maxvalue = 0;
     int Minvalue = 255;
     
   


 
 WritableRaster raster = this.BIGray.getRaster();
 int[] pixels = new int[60];

     for (int y = StartWindow.y; y < EndWindow.y ; y++) 
    {
        raster.getPixels(StartWindow.x, y, 60, 1, pixels);
        for (int i = 0; i < pixels.length; i++) {
            
            G=G+pixels[i];
            
            if     (pixels[i] > Maxvalue)
           {
              Maxvalue = pixels[i];           
           }
            
            else if (pixels[i] < Minvalue)
                
           {
                   Minvalue = pixels[i];           
           }
           
        }

    }
 
     U = G/3600;
     
     
    
   
     for (int y = StartWindow.y; y < EndWindow.y ; y++) 
    {
        raster.getPixels(StartWindow.x, y, 60, 1, pixels);
        for (int i = 0; i < pixels.length; i++) {
            
            M = M + (Math.pow((pixels[i]-U), 3));
            
            if     (pixels[i] > Maxvalue)
           {
              Maxvalue = pixels[i];           
           }
            
            else if (pixels[i] < Minvalue)
                
           {
                   Minvalue = pixels[i];           
           }
           
        }

    }
     
  /*   
     for(int x = StartWindow.x; x < EndWindow.x; x++)
     {
        for(int y = StartWindow.y ; y < EndWindow.y; y++)
        {
           rgb  = bImage.getRGB(x, y);
           r    = (rgb >> 16) & 0xFF;
           g    = (rgb >> 8) & 0xFF;
           b    = (rgb & 0xFF);
           gray =  ((r + g + b)/3);
             
           if     (gray > Maxvalue)
           {
              Maxvalue = gray;           
           }
           
           else if (gray < Minvalue)
                
           {
                   Minvalue = gray;           
           }
        }

     }   */
             System.out.println("Tercer Momento " + Math.abs((M/3600)/255)); 
             System.out.println("Valor Max " + Maxvalue); 
             System.out.println("Valor Min " + Minvalue); 
             
 return ((Minvalue+Maxvalue)/2);
 }


//Calcula valor Umbral x Promedio del Valor Maximo con El Valor Minimo
public int Threshold_Media(Point StartWindow, Point EndWindow, BufferedImage bImage)
 {
 int TotalValue = 0;
 
 WritableRaster raster = this.BIGray.getRaster();
 int[] pixels = new int[60];

     for (int y = StartWindow.y; y < EndWindow.y ; y++) 
    {
        raster.getPixels(StartWindow.x, y, 60, 1, pixels);
        for (int i = 0; i < pixels.length; i++) {
            
            TotalValue = TotalValue + pixels[i];
           
        }

    }
     
    return (TotalValue/3600);
 }




//Calcula valor Umbral x Promedio del Valor Maximo con El Valor Minimo
public int Media(Point StartWindow, Point EndWindow, BufferedImage bImage)
 {
     int rgb,r,g,b,gray = 0;
     int Media = 0;
     int NumValues = 0;

     
     for(int x = StartWindow.x; x < EndWindow.x; x++)
     {
        for(int y = StartWindow.y ; y < EndWindow.y; y++)
        {
           rgb  = bImage.getRGB(x, y);
           r    = (rgb >> 16) & 0xFF;
           g    = (rgb >> 8) & 0xFF;
           b    = (rgb & 0xFF);
           gray =  ((r + g + b)/3);
    
           
           Media = Media + gray;
           NumValues++;
        }

     }   
 
 return ( Media/NumValues);
 }



//Convierte a B&W una ventana de Tamaño WindowSize y Centro ROICenter
public BufferedImage SegmentationbyROI(ArrayList <Point> ROICenter, int WindowSize) {

 
    
    
    
 for (int indice =0;indice<ROICenter.size();indice++)
 
 {
             
             
 //System.out.println("punto "+ ROICenter.get(indice));     
 this.BIBW=this.BIGray;
 WritableRaster raster = BIBW.getRaster();
 int[] pixels = new int[WindowSize];
 int threshold = 0, threshold2 = 0, threshold3 = 0;
 
 
 Point StartWindow = new Point ()  ;
 Point EndWindow   = new Point ()  ;
 
 
  if ((((ROICenter.get(indice).x-(WindowSize/2))>0 && (ROICenter.get(indice).y-(WindowSize/2))>0))&&
    (((ROICenter.get(indice).x+(WindowSize/2))<this.BIBW.getWidth()&& (ROICenter.get(indice).y+(WindowSize/2))<this.BIBW.getHeight())))
  {            
    StartWindow = new Point (ROICenter.get(indice).x-(WindowSize/2),ROICenter.get(indice).y-(WindowSize/2))  ;
    EndWindow   = new Point (ROICenter.get(indice).x+(WindowSize/2),ROICenter.get(indice).y+(WindowSize/2))  ;
  }

 


//threshold = Threshold_MaxMin_Media(StartWindow,EndWindow,this.BIBW);
 //threshold2 = Media(StartWindow,EndWindow,this.BIBW);
 threshold3 = Threshold_MaxMin_Media(StartWindow,EndWindow,this.BIBW);
 System.out.println("Threshold Max/Min:Media "+ threshold3);
 System.out.println("****************************************************");
 
   for (int y = StartWindow.y; y < EndWindow.y ; y++) 
    {
        
        raster.getPixels(StartWindow.x, y, WindowSize, 1, pixels);
        for (int i = 0; i < pixels.length; i++) {

            if (pixels[i] < threshold3) pixels[i] = 0;
            else pixels[i] = 255;
        }
        raster.setPixels(StartWindow.x, y, WindowSize, 1, pixels);
    }
         }

    return this.BIBW;
}    








@SuppressWarnings("empty-statement")


public BufferedImage MorfologicalImage() 

{
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
        
      

/*BufferedImage im =    new BufferedImage(this.BIErode.getWidth(),this.BIErode.getHeight(),BufferedImage.TYPE_BYTE_BINARY);
  // Get the graphics context for the black-and-white image.
  Graphics2D g2d = im.createGraphics();
  // Render the input image on it.
  g2d.drawImage(this.BIErode,0,0,null);
  // Store the resulting image using the PNG format.
 //  this.BIBWHistogram = im; 
  
     this.BIErode = im;  */ 
      
      
        return this.BIErode;
  
       
    }
 


}
