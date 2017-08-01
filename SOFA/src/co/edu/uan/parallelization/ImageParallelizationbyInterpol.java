/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uan.parallelization;

import Foil.Holes;
import Math.PixelOperation;

import co.edu.uan.processing.CorrelationImage;
import co.edu.uan.processing.ImagetoBWparameter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.io.IOException;


import static java.lang.Math.cos;
import static java.lang.Math.round;
import static java.lang.Math.sin;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;

import javax.imageio.stream.ImageInputStream;








/**
 *
 * @author DCA_3
 */
public class ImageParallelizationbyInterpol implements Runnable{
    
    private CorrelationImage ImgtoCorr; 
    private BufferedImage bImage;
    private long initialTime;
    private String filename;
    double [][] radius2form = new double [38][44];

    BufferedImage Subimage = new BufferedImage(47, 47,BufferedImage.TYPE_INT_RGB);  


	public ImageParallelizationbyInterpol(BufferedImage bImage, long initialTime, String filename) {
		
                this.bImage = bImage;
		this.initialTime = initialTime;
                this.filename = filename;
	}
	
  
    
        
        
Point customizedInterpolation (Point ptoInterpolate, double newWidth, double newHeight, double Width, double Height)        
{
    Double x,y;
    int xfinal, yfinal;
    x = (double)ptoInterpolate.x;
    y = (double)ptoInterpolate.y;
    
  
    
    xfinal = (int) Math.round((x*newWidth)/Width);
    yfinal = (int) Math.round((y*newHeight)/Height);
    
    if (xfinal >= newWidth)
    { xfinal = (int) (newWidth-1);}
    if (yfinal >= newHeight)
    {yfinal = (int) (newHeight-1);}
    
    Point NewPoint = new Point (xfinal,yfinal);
 
    return NewPoint;
}



@Override
 









public void run() {
 
double [][] radius = new double [38][44];

System.out.println("Procesando...");
Point FirstCenterFound = new Point();
Point pToInterpolate  = new Point();
ArrayList<Holes> HolesbyImage;
HolesbyImage                    = new ArrayList ();
BufferedImage ImgMorpho =null;
ImagetoBWparameter ImgtoConvert = new ImagetoBWparameter();
Graphics2D g ;


int WindowSize, Threshold , Threshold1, Threshold2 ;




int Xsize = 1 ;
int Ysize = 1;

int DeltaX = ImgtoConvert.getBIGray().getWidth()/Xsize;
int DeltaY = ImgtoConvert.getBIGray().getHeight()/Ysize;

int XInicial = 0;
int Yinicial = 0;

 ArrayList<Point> InitPoints  = new  ArrayList <Point> () ;
 ArrayList<Point> EndPoints  = new  ArrayList <Point> () ;
 ArrayList<Integer> threshold  = new  ArrayList <Integer> () ;

 
 /*
 for (int X = XInicial; X<=ImgtoConvert.getBIGray().getWidth()-DeltaX; X=X+DeltaX)
 {
     for (int Y = Yinicial; Y<=ImgtoConvert.getBIGray().getHeight()-DeltaY; Y=Y+DeltaY)
    {
             InitPoints.add(new Point (X,Y) );
             EndPoints.add(new Point ((X+DeltaX),(Y+DeltaY)));
                try {
                 } catch (IOException ex) {
                    Logger.getLogger(ImageParallelizationbyInterpol.class.getName()).log(Level.SEVERE, null, ex);
                }

             System.out.println("desde : (" + X +"," + Y+")"+ " hasta" +"("+(X+DeltaX)+","+(Y+DeltaY)+")");
             System.out.println("Centro encontrado en  : " + FirstCenterFound);
            
         
         
     Yinicial = Y+DeltaY;
    }
    Yinicial = 0;
   
 }*/



 ImgtoConvert.setBIColor(this.bImage); 
 ImgtoConvert.setBIGray();
        try {
            FirstCenterFound  =  FindCenterPointbyImageCorrelation(ImgtoConvert.getBIGray(), round(ImgtoConvert.getBIGray().getWidth()/2), round(ImgtoConvert.getBIGray().getHeight()/2), round(ImgtoConvert.getBIGray().getWidth()/2)+120, round(ImgtoConvert.getBIGray().getHeight()/2)+120);
        } catch (IOException ex) {
            Logger.getLogger(ImageParallelizationbyInterpol.class.getName()).log(Level.SEVERE, null, ex);
        }

            ImgtoConvert.thresholdImage(ImgtoCorr.getpixelAverage());
 
            //ImgtoConvert.thresholdImage(InitPoints,EndPoints,threshold);

            
            ImgtoConvert.ErodingImage() ;
            ImgtoConvert.getBIErode();
            ImgMorpho =  ImgtoConvert.MorfologicalImage();
            g = ImgMorpho.createGraphics();
            g.setColor(Color.GREEN);
            
            String BW, FileEroding,Morpholical;
            BW =  this.filename;
            
            
          
                    
        
            
             File Y = new File("C:\\Doctorado\\prueba\\"+  BW);
             Y.mkdir();
             
             File E = new File(Y.getParent()+"\\W.png");
             File M = new File(Y.getParent()+"\\E.png");
             System.out.println(Y.getParent());
             
            
            try {
            ImageIO.write(ImgtoConvert.getBIBW(), "png",Y );
            ImageIO.write(ImgtoConvert.getBIErode(), "png",E );
            ImageIO.write(ImgtoConvert.MorfologicalImage(), "png",M );
            
            } catch (IOException ex) {
            Logger.getLogger(ImageParallelizationbyInterpol.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           
            HolesbyImage.addAll(DiagonalGeometryReplication (ImgMorpho, g, FirstCenterFound,1));
            HolesbyImage.addAll(DiagonalGeometryReplication (ImgMorpho, g, FirstCenterFound,0));
             
            
          
            /*
            
            
            
            String string =  this.filename;
            string = string.replace(".png", "");
            
            
            
            int rgb = ImgtoConvert.getBIBW().getRGB(1, 1);
            int rojo    = 0xFFFF0000;
            int verde  = 0xFF00FF00;
            int blanco = 0xFFFFFFFF;
            int magenta = 0xFFFF00FF;
            
            
            //System.out.println("Cantidad de Centros "+ HolesbyImage.size());
            
            
            for (int i = 0; i< HolesbyImage.size();i++)
            {
                
            
             pToInterpolate = customizedInterpolation(HolesbyImage.get(i).getBarycenter(),44,38,3488,2616);
            
      
            
             if (HolesbyImage.get(i).getBarycenter().distance(HolesbyImage.get(i).getInnerPoint())>20)
                radius[pToInterpolate.y][pToInterpolate.x] = 0;
             else  
             radius[pToInterpolate.y][pToInterpolate.x] = HolesbyImage.get(i).getBarycenter().distance(HolesbyImage.get(i).getInnerPoint());
            
             
             ArrayList<Point> AllContourPoints  = new  ArrayList <Point> () ;
   
            ImgtoConvert.getBIColor().setRGB(HolesbyImage.get(i).getBarycenter().x, HolesbyImage.get(i).getBarycenter().y, magenta);
   
            for(int x=0;x< HolesbyImage.get(i).getInnerHolePoints().size();x++)
            {
            
            ImgtoConvert.getBIColor().setRGB(HolesbyImage.get(i).getInnerHolePoints().get(x).x, HolesbyImage.get(i).getInnerHolePoints().get(x).y, blanco);
            
            
            }
            
            for(int y=0;y< HolesbyImage.get(i).getOuterHolePoints().size();y++)
            {
            
            ImgtoConvert.getBIColor().setRGB(HolesbyImage.get(i).getOuterHolePoints().get(y).x, HolesbyImage.get(i).getOuterHolePoints().get(y).y, rojo);
            
            
            }
            }
            
         
            try
            {
            FileWriter writer = new FileWriter("C:\\Fotos_Foils\\resultados\\" + string +".csv");
            
            
            Double promedio = 0.0;
            int cont = 0;
            
            for(int i = 0; i < 38; i++)
            {
               
            for (int j = 0; j< 44; j++)
            {
               
                if (radius[i][j]>0)
                {
                    cont=cont+1;
                    promedio=(promedio+radius[i][j]);
                }
               
            }
            }
            
            promedio = promedio/cont;
            
            
            
            
            for(int i = 0; i < 38; i++)
            {
               
            for (int j = 0; j< 44; j++)
            {
               
               
                if (radius[i][j]==0)
                    radius[i][j] = promedio;
                   
                    
                writer.append(Double.toString(radius[i][j]));
                writer.append(',');
            }
            
            writer.append('\n');
            writer.flush();
            }
            writer.close();
            }
            catch(Exception e)
            {
            e.printStackTrace();
            }
            
           
            
           
            
            
          
       
     ImgMorpho = null;
     ImgtoConvert = null;
     ImgtoCorr = null;*/
System.out.println("Proceso terminado...");
      
 }         

 



   
Point FindCenterPointbyImageCorrelation (BufferedImage biImg, int Xini, int Yini, int Xend, int Yend ) throws FileNotFoundException, IOException {
    
             Point Center = null;
             ImgtoCorr = new CorrelationImage();
             ImageInputStream stream = null;
             FileInputStream fin = new FileInputStream("C:\\SOFA\\Plantilla18.jpg");
             stream = ImageIO.createImageInputStream(fin);
             ImageReader reader = ImageIO.getImageReaders(stream).next(); // TODO: Test hasNext()
             reader.setInput(stream);
             ImageReadParam param = reader.getDefaultReadParam();
             param.setDestination(Subimage);
             Subimage = reader.read(0, param);  
 
              /*Hole encontrado*/
              ImgtoCorr.setXini(Xini);
              ImgtoCorr.setYini(Yini);
              ImgtoCorr.setXend(Xend);
              ImgtoCorr.setYend(Yend);
              ImgtoCorr.findSubimage(this.bImage,Subimage);
              
              Center = new Point (ImgtoCorr.getXCorrelation()+24,ImgtoCorr.getYCorrelation()+24); 
     
              fin.close();
               
              this.bImage.flush();
  
              Subimage.flush();
 
              return Center;
}   




Holes HoleEvaluate (Point NewPoint, BufferedImage BImg )
{
    PixelOperation PixelOperationCalc = new PixelOperation ();
    Holes Hole;
    Hole = new Holes();
    Hole.setCenter(NewPoint);
    Hole.setInnerPoint(GettingInitContourPointRight(NewPoint, BImg, 1, 15));

    //Si no encuentra el punto inicial del contorno interno a la Izquierda o Derecha anula el Hola
    if ((Hole.getInnerPoint().x==0) && (Hole.getInnerPoint().y==0))
        {
        Hole.setOuterPoint(new Point());
        Hole.setBarycenter(Hole.getCenter());
        Hole.setInnerHolePoints(new ArrayList ());
        Hole.setOuterHolePoints(new ArrayList ());
        Hole.setInnerArea(0.0);
        Hole.setOuterArea(0.0);
        Hole.setInnerPerimeter(0.0);
        Hole.setOuterPerimeter(0.0);
        Hole.setStatus("D");
        }
    
    else    
        {   
         Hole.setInnerHolePoints(ExtractContour(Hole.getInnerPoint(), BImg));
         Hole.setOuterPoint     (GettingInitContourPointLeft(NewPoint, BImg, (Math.abs(Hole.getCenter().x - Hole.getInnerPoint().x))+1, (Math.abs(Hole.getCenter().x - Hole.getInnerPoint().x))+11));
         Hole.setOuterHolePoints(ExtractContour(Hole.getOuterPoint(), BImg));
         Hole.setBarycenter     (PixelOperationCalc.BarycenterCalculate(Hole.getInnerHolePoints()));
       //  Hole.setInnerPerimeter (PixelOperationCalc.PerimeterCalculate(Hole.getBarycenter().distance(Hole.getInnerPoint())));  
         Hole.setRoundness      (PixelOperationCalc.RoundnessCalculate(Hole.getInnerHolePoints().size(), Hole.getInnerPerimeter()));       
        }  

return Hole;
}


ArrayList<Holes> DiagonalGeometryReplication (BufferedImage BImg, Graphics2D gRef, Point PointtoReply, int OptOrientation) 
    {

ArrayList<Holes> HolesbyImage;
HolesbyImage = new ArrayList ();
PixelOperation PixelOperationCalc = new PixelOperation ();
Point NewPoint = new Point(); 

int Down = BImg.getHeight() - 20; // 20 px es la distancia minimima del radio hasta el borde inferior de la imagen
switch (OptOrientation)
        {
    case 0 : // Diagonal Hacia Arriba
            {
             NewPoint = new Point(); 
             NewPoint.x = (int) (PointtoReply.x  +  (cos (60 * (Math.PI/180.0F))*(80)));
             NewPoint.y = (int) (PointtoReply.y  -  (sin (60 * (Math.PI/180.0F))*(80)));
             break;
            }
    
    case 1 : // Diagonal Hacia Abajo
           {
            NewPoint = new Point(PointtoReply);  
            break;
           }
}


// 1 es orientacion hacia abajo
while( (OptOrientation==1 ? NewPoint.y <= Down : NewPoint.y >= 20)&&(OptOrientation==1 ? NewPoint.x > 20 : NewPoint.x <= BImg.getWidth()-20) ) 
{           
    Holes Hole;
    Hole = new Holes();
    Hole = HoleEvaluate (NewPoint,  BImg );
    HolesbyImage.add(Hole);
    HolesbyImage.addAll(HorizontalGeometryReplication(BImg, gRef, Hole.getBarycenter(), 80, BImg.getWidth(), BImg.getHeight(),0));
    HolesbyImage.addAll(HorizontalGeometryReplication(BImg, gRef, Hole.getBarycenter(), 80, BImg.getWidth(), BImg.getHeight(),1));
    
    
    switch (OptOrientation)
        {
        case 0 : // Diagonal Hacia Arriba
                { 
                 NewPoint = new Point();
                 NewPoint.x = (int) (Hole.getBarycenter().x  + (cos (60 * (Math.PI/180.0F))*(80)));
                 NewPoint.y = (int) (Hole.getBarycenter().y  -  (sin (60 * (Math.PI/180.0F))*(80)));
                 break;
                }
        case 1 : // Diagonal Hacia Abajo
                {   
                 NewPoint = new Point();
                 NewPoint.x = (int) (Hole.getBarycenter().x  -  (cos (60 * (Math.PI/180.0F))*(80)));
                 NewPoint.y = (int) (Hole.getBarycenter().y  +  (sin (60 * (Math.PI/180.0F))*(80)));  
                 break;
      }
    }   
 }
    return  HolesbyImage;   
    }




Point CalculateOrientationHorizontal (Point LastCenterFound, double CentersDistance, int OptOrientation)
{

 Point NewPoint = new Point(); 
 switch (OptOrientation)
                    {
                case 0 : // Hacia la Derecha
                        { 
                         NewPoint = new Point();
                         NewPoint = new Point( (int) (LastCenterFound.x  +  CentersDistance ), (int) (LastCenterFound.y));
                         break;
                        }
                case 1 : //  Hacia la izquierda
                        {   
                          NewPoint = new Point();
                          NewPoint = new Point( (int) (LastCenterFound.x  -  CentersDistance ), (int) (LastCenterFound.y)); 
                         break;
                        }
 }
         return NewPoint;
}



ArrayList<Holes> HorizontalGeometryReplication (BufferedImage BImg, Graphics2D gRef, Point LastCenterFound, double CentersDistance, int WidthImage, int HeighImage, int OptOrientation) {

ArrayList<Holes> xHolesbyImage;
xHolesbyImage = new ArrayList ();
PixelOperation xPixelOperationCalc = new PixelOperation ();
Point NewPoint = new Point(); 
int Right = BImg.getWidth() - 20; // 20 px es la distancia minimima del radio hasta el borde inferior de la imagen

NewPoint = CalculateOrientationHorizontal (LastCenterFound, CentersDistance, OptOrientation);
while( (OptOrientation==1 ? NewPoint.x > 20 : NewPoint.x < Right) ) 
{ 
        Holes Hole;
        Hole = new Holes();
        Hole = HoleEvaluate (NewPoint,  BImg );
       // System.out.println(Hole.getBarycenter());
        if (Hole.getBarycenter().x <= (WidthImage-20))
          {
              xHolesbyImage.add(Hole);
              NewPoint = CalculateOrientationHorizontal (Hole.getBarycenter(), CentersDistance,OptOrientation);
          }
}

//System.out.println("**********************");
return xHolesbyImage;
}



Point GettingInitContourPointRight (Point Centroide, BufferedImage biImage, int xFrom, int xTo)
{   
ArrayList<Point> InitContournPoint = new ArrayList<Point> (); 
Point ContourInitPointRight = new Point() ;
Color pColorCentroid = null;        

for (int xHor = xFrom ; xHor <= xTo; xHor++)
{
    //Si el punto inicial del contorno a la derecha es mayor o igual al ancho de la imagen se sale
    if((Centroide.x + xHor)>=biImage.getWidth())
       {break;}
    
    else
    {
         pColorCentroid =  new Color(biImage.getRGB((Centroide.x + xHor),Centroide.y));
        if ((pColorCentroid.getBlue()==255))
        {
            ContourInitPointRight = new Point((Centroide.x + xHor),Centroide.y);
            InitContournPoint.add(ContourInitPointRight);
        }
    }  
}  

//si no encuentra punto inicial a la derecha busca punto inicial a la izquierda
if (ContourInitPointRight.x==0 && ContourInitPointRight.y==0)
    {
        ContourInitPointRight = GettingInitContourPointLeft(Centroide, biImage, 1, 15);
    }              

return ContourInitPointRight;
}     


Point GettingInitContourPointLeft (Point Centroide, BufferedImage biImage, int xFrom, int xTo)
{   
ArrayList<Point> InitContournPoint = new ArrayList<Point> (); 
Point ContourPointLeft = new Point() ;
Color pColorCentroid = null;        

for (int xHor = xFrom ; xHor <= xTo; xHor++)
{
    if((Centroide.x - xHor)<=0)
       {break;}
    
    pColorCentroid =  new Color(biImage.getRGB((Centroide.x - xHor),Centroide.y));
    if ((pColorCentroid.getBlue()==255))
    {
    ContourPointLeft = new Point((Centroide.x - xHor),Centroide.y);
    InitContournPoint.add(ContourPointLeft);
    }  
}  
return ContourPointLeft;
        }     



 ArrayList<Point> ExtractContour (Point Centroide, BufferedImage biImage)
 {
    
    ArrayList<Point> FatherList = new ArrayList<Point> ();  
    ArrayList<Point> SonList = new ArrayList<Point> ();
    ArrayList<Point> RetirementList = new ArrayList<Point> ();
    boolean isPoint = false;
    Point iniPointContour = new Point(); 
    iniPointContour.x =  Centroide.x  ;
    iniPointContour.y =  Centroide.y ;
    
    
if (Centroide.x != 0 && Centroide.y!=0)
    {
     FatherList.add(iniPointContour);
     biImage.setRGB(iniPointContour.x, iniPointContour.y, 0);
     for (int i = (iniPointContour.x-1); i <= (iniPointContour.x+1); i++ )
        {
            for (int j = (iniPointContour.y-1); j <= (iniPointContour.y+1); j++ )
            {
                Color pColorNeighbor =  new Color(biImage.getRGB(i,j));
                if ((pColorNeighbor.getBlue()==255)&& ((iniPointContour.x != i) || (iniPointContour.y != j)))
                {
                isPoint = true;
                Point Son = new Point();
                Son.x = i;
                Son.y = j;
                SonList.add(Son);
                biImage.setRGB(Son.x, Son.y, 0);
               } 
            }

        }

//Si encontró un punto inicial para recorrer el contorno y extraer los puntos        
    if (isPoint == true)        
    {
        do 
        { 
            Point ListiniPointContour = new Point(SonList.get(0)); 
            RetirementList.addAll(FatherList);
            FatherList.clear();
            FatherList.add(ListiniPointContour);
            biImage.setRGB(ListiniPointContour.x, ListiniPointContour.y, 0);
            for (int i = (ListiniPointContour.x-1); i <= (ListiniPointContour.x+1); i++ )
                {
                    for (int j = (ListiniPointContour.y-1); j <= (ListiniPointContour.y+1); j++ )
                    {
                        Color pColorNeighbor =  new Color(biImage.getRGB(i,j));
                        if ((pColorNeighbor.getBlue()==255)&& ((ListiniPointContour.x != i) || (ListiniPointContour.y != j)))
                        {   Point Son = new Point();
                            Son.x = i;
                            Son.y = j;
                            SonList.add(Son);
                            biImage.setRGB(Son.x, Son.y, 0);}
                    }
                  }
            SonList.remove(0);
          }
        while (SonList.size()> 0);  
        }
    }   
     return RetirementList;
     }
      
}
