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
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JTextArea;

/**
 *
 * @author DCA_3
 */
public class ImageParallelization implements Runnable
{
    private CorrelationImage ImgtoCorr; 
    private BufferedImage bImage;
    private long initialTime;
    private String filename, OnlyNameFile;
    BufferedImage Subimage = new BufferedImage(47, 47,BufferedImage.TYPE_INT_RGB);
    JTextArea console;
    
    public ImageParallelization(BufferedImage bImage, long initialTime, String filename,JTextArea jTextArea) 
    {
        this.bImage = bImage;
        this.initialTime = initialTime;
        this.filename = filename;
        this.console = jTextArea;
                
        int pos = filename.lastIndexOf(".");
        if (pos > 0) this.OnlyNameFile = filename.substring(0, pos);
    }


    private void createdirectory (String path, String filename)
    {
        File theDir = new File( path + filename);
        // if the directory does not exist, create it
        if (!theDir.exists()) 
        {
            //System.out.println("creating directory: " + this.filename);
            boolean result = false;
            try{
                theDir.mkdir();
                result = true;
            } catch(SecurityException se){}        
        
            if(result) {    
            //System.out.println("DIR created");  
            }
        }        
    }    


@Override
 public void run() 
 {
    Graphics2D g;
    Point FirstCenterFound = new Point();
    ArrayList <Holes> HolesbyImage;
    HolesbyImage                    = new ArrayList ();
    BufferedImage ImgMorpho = null;
    ImagetoBWparameter ImgtoConvert = new ImagetoBWparameter();
    ImgtoConvert.setBIColor(this.bImage);
    ImgtoConvert.setBIGray();
    int Rangor1 = 0, Rangor2 = 0, Rangor3 = 0, Rangor4 = 0; 
    int RangoR1 = 0, RangoR2 = 0, RangoR3 = 0, RangoR4 = 0; 
    int RangoC1 = 0, RangoC2 = 0, RangoC3 = 0; 
    int Rangoc1 = 0, Rangoc2 = 0, Rangoc3 = 0; 

    long time_start, time_end;
    time_start = System.currentTimeMillis();
    double DesvCirc, PromedioCirc, DesvRad, PromedioRad;
    int tipo_Binarizacion = 2;

    createdirectory("C:\\Resultados\\" ,this.OnlyNameFile);
            
  
    if (tipo_Binarizacion == 0)//UNICO ROI
    {
        try 
        {
            FirstCenterFound  =  new Point (FindCenterPointbyImageCorrelation(ImgtoConvert.getBIGray(), round(ImgtoConvert.getBIGray().getWidth()/2), round(ImgtoConvert.getBIGray().getHeight()/2), round(ImgtoConvert.getBIGray().getWidth()/2)+150, round(ImgtoConvert.getBIGray().getHeight()/2)+150));
            //FirstCenterFound  =  new Point (2343,1023);
            ImgtoConvert.thresholdImage(ImgtoCorr.getpixelAverage());
            
            
            ImgtoConvert.ErodingImage();
            ImgtoConvert.getBIErode();
            ImgMorpho =  ImgtoConvert.MorfologicalImage();  
        } catch (IOException ex) {
            Logger.getLogger(ImageParallelization.class.getName()).log(Level.SEVERE, null, ex);
            }
    }             

    if (tipo_Binarizacion == 1)//OTSU
    {
        try {
            FirstCenterFound  =  new Point (FindCenterPointbyImageCorrelation(ImgtoConvert.getBIGray(), round(ImgtoConvert.getBIGray().getWidth()/2), round(ImgtoConvert.getBIGray().getHeight()/2), round(ImgtoConvert.getBIGray().getWidth()/2)+150, round(ImgtoConvert.getBIGray().getHeight()/2)+150));
            ImgtoConvert.setBIBWHistogram();
            ImgtoConvert.ErodingImage();
            ImgtoConvert.getBIErode();
            ImgMorpho =  ImgtoConvert.MorfologicalImage();
        } catch (IOException ex) {
            Logger.getLogger(ImageParallelization.class.getName()).log(Level.SEVERE, null, ex);
        }
                                
    }
//ROI -----------------------------------------------------------------------------               
    if(tipo_Binarizacion == 2)
    {
        try {
            FirstCenterFound  =  new Point (FindCenterPointbyImageCorrelation(ImgtoConvert.getBIGray(), round(ImgtoConvert.getBIGray().getWidth()/2), round(ImgtoConvert.getBIGray().getHeight()/2), round(ImgtoConvert.getBIGray().getWidth()/2)+150, round(ImgtoConvert.getBIGray().getHeight()/2)+150));
        } catch (IOException ex) {
            Logger.getLogger(ImageParallelization.class.getName()).log(Level.SEVERE, null, ex);
        }

        ArrayList <Point> CentrosBorrosidad;
        CentrosBorrosidad = new ArrayList ();

        CentrosBorrosidad.add(new Point (1828,1364));
        CentrosBorrosidad.add(new Point (1907,1364));
        CentrosBorrosidad.add(new Point (1986,1365));
        CentrosBorrosidad.add(new Point (2065,1365));
        CentrosBorrosidad.add(new Point (2144,1365));
        CentrosBorrosidad.add(new Point (2223,1366));
        CentrosBorrosidad.add(new Point (2302,1366));
        CentrosBorrosidad.add(new Point (2381,1366));
        CentrosBorrosidad.add(new Point (2460,1366));
        CentrosBorrosidad.add(new Point (2539,1367));
        CentrosBorrosidad.add(new Point (2620,1367));
        CentrosBorrosidad.add(new Point (2698,1368));
        CentrosBorrosidad.add(new Point (2778,1368));
        CentrosBorrosidad.add(new Point (2856,1368));
        CentrosBorrosidad.add(new Point (2935,1369));
        CentrosBorrosidad.add(new Point (3014,1369));
        CentrosBorrosidad.add(new Point (3093,1369));
        CentrosBorrosidad.add(new Point (3172,1369));
        CentrosBorrosidad.add(new Point (3251,1370));
        CentrosBorrosidad.add(new Point (3329,1370));
        CentrosBorrosidad.add(new Point (3408,1371));
        CentrosBorrosidad.add(new Point (1747,1364));
        CentrosBorrosidad.add(new Point (1668,1364));
        CentrosBorrosidad.add(new Point (1589,1363));
        CentrosBorrosidad.add(new Point (1510,1363));
        CentrosBorrosidad.add(new Point (1431,1363));
        CentrosBorrosidad.add(new Point (1352,1362));
        CentrosBorrosidad.add(new Point (1273,1362));
        CentrosBorrosidad.add(new Point (1194,1361));
        CentrosBorrosidad.add(new Point (1115,1361));
        CentrosBorrosidad.add(new Point (1036,1360));
        CentrosBorrosidad.add(new Point (957,1360));
        CentrosBorrosidad.add(new Point (876,1360));
        CentrosBorrosidad.add(new Point (797,1359));
        CentrosBorrosidad.add(new Point (718,1359));
        CentrosBorrosidad.add(new Point (640,1358));
        CentrosBorrosidad.add(new Point (561,1358));
        CentrosBorrosidad.add(new Point (482,1357));
        CentrosBorrosidad.add(new Point (403,1357));
        CentrosBorrosidad.add(new Point (324,1357));
        CentrosBorrosidad.add(new Point (245,1356));
        CentrosBorrosidad.add(new Point (166,1356));
        CentrosBorrosidad.add(new Point (87,1355));
        CentrosBorrosidad.add(new Point (1788,1432));
        CentrosBorrosidad.add(new Point (1868,1432));
        CentrosBorrosidad.add(new Point (1946,1433));
        CentrosBorrosidad.add(new Point (2026,1433));
        CentrosBorrosidad.add(new Point (2104,1433));
        CentrosBorrosidad.add(new Point (2183,1434));
        CentrosBorrosidad.add(new Point (2262,1434));
        CentrosBorrosidad.add(new Point (2341,1434));
        CentrosBorrosidad.add(new Point (2420,1435));
        CentrosBorrosidad.add(new Point (2499,1435));
        CentrosBorrosidad.add(new Point (2580,1435));
        CentrosBorrosidad.add(new Point (2659,1435));
        CentrosBorrosidad.add(new Point (2738,1436));
        CentrosBorrosidad.add(new Point (2817,1436));
        CentrosBorrosidad.add(new Point (2895,1436));
        CentrosBorrosidad.add(new Point (2975,1437));
        CentrosBorrosidad.add(new Point (3053,1437));
        CentrosBorrosidad.add(new Point (3132,1437));
        CentrosBorrosidad.add(new Point (3211,1438));
        CentrosBorrosidad.add(new Point (3290,1438));
        CentrosBorrosidad.add(new Point (3368,1438));
        CentrosBorrosidad.add(new Point (3449,1438));
        CentrosBorrosidad.add(new Point (1707,1432));
        CentrosBorrosidad.add(new Point (1628,1431));
        CentrosBorrosidad.add(new Point (1549,1431));
        CentrosBorrosidad.add(new Point (1470,1431));
        CentrosBorrosidad.add(new Point (1391,1430));
        CentrosBorrosidad.add(new Point (1312,1430));
        CentrosBorrosidad.add(new Point (1233,1430));
        CentrosBorrosidad.add(new Point (1154,1430));
        CentrosBorrosidad.add(new Point (1075,1429));
        CentrosBorrosidad.add(new Point (996,1429));
        CentrosBorrosidad.add(new Point (917,1428));
        CentrosBorrosidad.add(new Point (836,1427));
        CentrosBorrosidad.add(new Point (757,1427));
        CentrosBorrosidad.add(new Point (679,1427));
        CentrosBorrosidad.add(new Point (600,1426));
        CentrosBorrosidad.add(new Point (521,1426));
        CentrosBorrosidad.add(new Point (442,1425));
        CentrosBorrosidad.add(new Point (363,1425));
        CentrosBorrosidad.add(new Point (284,1425));
        CentrosBorrosidad.add(new Point (205,1424));
        CentrosBorrosidad.add(new Point (126,1424));
        CentrosBorrosidad.add(new Point (45,1423));
        CentrosBorrosidad.add(new Point (1747,1500));
        CentrosBorrosidad.add(new Point (1827,1501));
        CentrosBorrosidad.add(new Point (1907,1501));
        CentrosBorrosidad.add(new Point (1985,1501));
        CentrosBorrosidad.add(new Point (2065,1502));
        CentrosBorrosidad.add(new Point (2144,1502));
        CentrosBorrosidad.add(new Point (2223,1502));
        CentrosBorrosidad.add(new Point (2302,1502));
        CentrosBorrosidad.add(new Point (2380,1503));
        CentrosBorrosidad.add(new Point (2459,1503));
        CentrosBorrosidad.add(new Point (2538,1503));
        CentrosBorrosidad.add(new Point (2619,1503));
        CentrosBorrosidad.add(new Point (2698,1504));
        CentrosBorrosidad.add(new Point (2776,1504));
        CentrosBorrosidad.add(new Point (2856,1504));
        CentrosBorrosidad.add(new Point (2935,1505));
        CentrosBorrosidad.add(new Point (3014,1505));
        CentrosBorrosidad.add(new Point (3092,1505));
        CentrosBorrosidad.add(new Point (3171,1506));
        CentrosBorrosidad.add(new Point (3250,1506));
        CentrosBorrosidad.add(new Point (3329,1506));
        CentrosBorrosidad.add(new Point (3408,1506));
        CentrosBorrosidad.add(new Point (1668,1500));
        CentrosBorrosidad.add(new Point (1589,1499));
        CentrosBorrosidad.add(new Point (1510,1499));
        CentrosBorrosidad.add(new Point (1431,1499));
        CentrosBorrosidad.add(new Point (1351,1498));
        CentrosBorrosidad.add(new Point (1273,1499));
        CentrosBorrosidad.add(new Point (1193,1498));
        CentrosBorrosidad.add(new Point (1114,1497));
        CentrosBorrosidad.add(new Point (1035,1497));
        CentrosBorrosidad.add(new Point (956,1496));
        CentrosBorrosidad.add(new Point (876,1496));
        CentrosBorrosidad.add(new Point (797,1495));
        CentrosBorrosidad.add(new Point (718,1495));
        CentrosBorrosidad.add(new Point (639,1495));
        CentrosBorrosidad.add(new Point (560,1494));
        CentrosBorrosidad.add(new Point (481,1494));
        CentrosBorrosidad.add(new Point (402,1494));
        CentrosBorrosidad.add(new Point (324,1493));
        CentrosBorrosidad.add(new Point (245,1492));
        CentrosBorrosidad.add(new Point (165,1492));
        CentrosBorrosidad.add(new Point (86,1492));
        CentrosBorrosidad.add(new Point (1707,1570));
        CentrosBorrosidad.add(new Point (1788,1570));
        CentrosBorrosidad.add(new Point (1867,1571));
        CentrosBorrosidad.add(new Point (1946,1570));
        CentrosBorrosidad.add(new Point (2025,1571));
        CentrosBorrosidad.add(new Point (2104,1572));
        CentrosBorrosidad.add(new Point (2183,1572));
        CentrosBorrosidad.add(new Point (2262,1572));
        CentrosBorrosidad.add(new Point (2340,1572));
        CentrosBorrosidad.add(new Point (2419,1573));
        CentrosBorrosidad.add(new Point (2498,1573));
        CentrosBorrosidad.add(new Point (2579,1573));
        CentrosBorrosidad.add(new Point (2658,1574));
        CentrosBorrosidad.add(new Point (2737,1574));
        CentrosBorrosidad.add(new Point (2816,1574));
        CentrosBorrosidad.add(new Point (2894,1575));
        CentrosBorrosidad.add(new Point (2974,1575));
        CentrosBorrosidad.add(new Point (3052,1575));
        CentrosBorrosidad.add(new Point (3131,1576));
        CentrosBorrosidad.add(new Point (3210,1576));
        CentrosBorrosidad.add(new Point (3289,1576));
        CentrosBorrosidad.add(new Point (3368,1577));
        CentrosBorrosidad.add(new Point (3448,1577));
        CentrosBorrosidad.add(new Point (1628,1570));
        CentrosBorrosidad.add(new Point (1549,1570));
        CentrosBorrosidad.add(new Point (1470,1569));
        CentrosBorrosidad.add(new Point (1390,1569));
        CentrosBorrosidad.add(new Point (1312,1568));
        CentrosBorrosidad.add(new Point (1232,1568));
        CentrosBorrosidad.add(new Point (1153,1567));
        CentrosBorrosidad.add(new Point (1075,1567));
        CentrosBorrosidad.add(new Point (996,1567));
        CentrosBorrosidad.add(new Point (917,1566));
        CentrosBorrosidad.add(new Point (835,1566));
        CentrosBorrosidad.add(new Point (757,1565));
        CentrosBorrosidad.add(new Point (678,1565));
        CentrosBorrosidad.add(new Point (599,1565));
        CentrosBorrosidad.add(new Point (521,1564));
        CentrosBorrosidad.add(new Point (441,1564));
        CentrosBorrosidad.add(new Point (362,1563));
        CentrosBorrosidad.add(new Point (284,1563));
        CentrosBorrosidad.add(new Point (205,1562));
        CentrosBorrosidad.add(new Point (126,1562));
        CentrosBorrosidad.add(new Point (46,1561));
        CentrosBorrosidad.add(new Point (1667,1638));
        CentrosBorrosidad.add(new Point (1746,1638));
        CentrosBorrosidad.add(new Point (1827,1639));
        CentrosBorrosidad.add(new Point (1906,1639));
        CentrosBorrosidad.add(new Point (1985,1639));
        CentrosBorrosidad.add(new Point (2064,1640));
        CentrosBorrosidad.add(new Point (2142,1640));
        CentrosBorrosidad.add(new Point (2222,1640));
        CentrosBorrosidad.add(new Point (2301,1641));
        CentrosBorrosidad.add(new Point (2380,1641));
        CentrosBorrosidad.add(new Point (2459,1641));
        CentrosBorrosidad.add(new Point (2538,1642));
        CentrosBorrosidad.add(new Point (2618,1642));
        CentrosBorrosidad.add(new Point (2697,1642));
        CentrosBorrosidad.add(new Point (2776,1643));
        CentrosBorrosidad.add(new Point (2855,1643));
        CentrosBorrosidad.add(new Point (2934,1643));
        CentrosBorrosidad.add(new Point (3013,1643));
        CentrosBorrosidad.add(new Point (3092,1644));
        CentrosBorrosidad.add(new Point (3171,1645));
        CentrosBorrosidad.add(new Point (3249,1644));
        CentrosBorrosidad.add(new Point (3328,1644));
        CentrosBorrosidad.add(new Point (3407,1645));
        CentrosBorrosidad.add(new Point (1588,1638));
        CentrosBorrosidad.add(new Point (1509,1637));
        CentrosBorrosidad.add(new Point (1430,1637));
        CentrosBorrosidad.add(new Point (1351,1637));
        CentrosBorrosidad.add(new Point (1273,1637));
        CentrosBorrosidad.add(new Point (1192,1636));
        CentrosBorrosidad.add(new Point (1113,1635));
        CentrosBorrosidad.add(new Point (1034,1635));
        CentrosBorrosidad.add(new Point (956,1634));
        CentrosBorrosidad.add(new Point (875,1634));
        CentrosBorrosidad.add(new Point (796,1634));
        CentrosBorrosidad.add(new Point (717,1634));
        CentrosBorrosidad.add(new Point (638,1633));
        CentrosBorrosidad.add(new Point (559,1632));
        CentrosBorrosidad.add(new Point (481,1632));
        CentrosBorrosidad.add(new Point (402,1631));
        CentrosBorrosidad.add(new Point (323,1631));
        CentrosBorrosidad.add(new Point (244,1630));
        CentrosBorrosidad.add(new Point (165,1630));
        CentrosBorrosidad.add(new Point (87,1629));
        CentrosBorrosidad.add(new Point (1627,1706));
        CentrosBorrosidad.add(new Point (1706,1707));
        CentrosBorrosidad.add(new Point (1786,1707));
        CentrosBorrosidad.add(new Point (1866,1707));
        CentrosBorrosidad.add(new Point (1945,1707));
        CentrosBorrosidad.add(new Point (2024,1708));
        CentrosBorrosidad.add(new Point (2103,1708));
        CentrosBorrosidad.add(new Point (2182,1708));
        CentrosBorrosidad.add(new Point (2261,1709));
        CentrosBorrosidad.add(new Point (2340,1709));
        CentrosBorrosidad.add(new Point (2419,1709));
        CentrosBorrosidad.add(new Point (2498,1709));
        CentrosBorrosidad.add(new Point (2578,1710));
        CentrosBorrosidad.add(new Point (2658,1710));
        CentrosBorrosidad.add(new Point (2736,1711));
        CentrosBorrosidad.add(new Point (2815,1711));
        CentrosBorrosidad.add(new Point (2894,1711));
        CentrosBorrosidad.add(new Point (2973,1711));
        CentrosBorrosidad.add(new Point (3052,1712));
        CentrosBorrosidad.add(new Point (3125,1703));
        CentrosBorrosidad.add(new Point (3209,1712));
        CentrosBorrosidad.add(new Point (3288,1712));
        CentrosBorrosidad.add(new Point (3367,1713));
        CentrosBorrosidad.add(new Point (3447,1713));
        CentrosBorrosidad.add(new Point (1548,1706));
        CentrosBorrosidad.add(new Point (1469,1706));
        CentrosBorrosidad.add(new Point (1390,1705));
        CentrosBorrosidad.add(new Point (1311,1705));
        CentrosBorrosidad.add(new Point (1230,1704));
        CentrosBorrosidad.add(new Point (1154,1703));

        CentrosBorrosidad.add(new Point (1074,1703));
        CentrosBorrosidad.add(new Point (995,1703));
        CentrosBorrosidad.add(new Point (916,1703));
        CentrosBorrosidad.add(new Point (835,1702));
        CentrosBorrosidad.add(new Point (756,1702));
        CentrosBorrosidad.add(new Point (677,1701));
        CentrosBorrosidad.add(new Point (598,1701));
        CentrosBorrosidad.add(new Point (520,1700));
        CentrosBorrosidad.add(new Point (441,1700));
        CentrosBorrosidad.add(new Point (362,1700));
        CentrosBorrosidad.add(new Point (284,1699));
        CentrosBorrosidad.add(new Point (205,1698));
        CentrosBorrosidad.add(new Point (125,1698));
        CentrosBorrosidad.add(new Point (45,1698));
        CentrosBorrosidad.add(new Point (1587,1774));
        CentrosBorrosidad.add(new Point (1667,1774));
        CentrosBorrosidad.add(new Point (1746,1775));
        CentrosBorrosidad.add(new Point (1826,1775));
        CentrosBorrosidad.add(new Point (1905,1776));
        CentrosBorrosidad.add(new Point (1984,1776));
        CentrosBorrosidad.add(new Point (2063,1776));
        CentrosBorrosidad.add(new Point (2142,1776));
        CentrosBorrosidad.add(new Point (2221,1776));
        CentrosBorrosidad.add(new Point (2300,1777));
        CentrosBorrosidad.add(new Point (2379,1778));
        CentrosBorrosidad.add(new Point (2457,1778));
        CentrosBorrosidad.add(new Point (2538,1778));
        CentrosBorrosidad.add(new Point (2617,1778));
        CentrosBorrosidad.add(new Point (2696,1779));
        CentrosBorrosidad.add(new Point (2775,1779));
        CentrosBorrosidad.add(new Point (2854,1779));
        CentrosBorrosidad.add(new Point (2933,1780));
        CentrosBorrosidad.add(new Point (3012,1780));
        CentrosBorrosidad.add(new Point (3091,1780));
        CentrosBorrosidad.add(new Point (3169,1780));
        CentrosBorrosidad.add(new Point (3248,1780));
        CentrosBorrosidad.add(new Point (3327,1781));
        CentrosBorrosidad.add(new Point (3406,1781));
        CentrosBorrosidad.add(new Point (1508,1774));
        CentrosBorrosidad.add(new Point (1429,1773));
        CentrosBorrosidad.add(new Point (1350,1773));
        CentrosBorrosidad.add(new Point (1272,1774));
        CentrosBorrosidad.add(new Point (1192,1770));
        CentrosBorrosidad.add(new Point (1113,1771));
        CentrosBorrosidad.add(new Point (1034,1771));
        CentrosBorrosidad.add(new Point (955,1771));
        CentrosBorrosidad.add(new Point (874,1770));
        CentrosBorrosidad.add(new Point (795,1770));
        CentrosBorrosidad.add(new Point (717,1769));
        CentrosBorrosidad.add(new Point (638,1769));
        CentrosBorrosidad.add(new Point (559,1769));
        CentrosBorrosidad.add(new Point (480,1768));
        CentrosBorrosidad.add(new Point (401,1768));
        CentrosBorrosidad.add(new Point (322,1767));
        CentrosBorrosidad.add(new Point (243,1767));
        CentrosBorrosidad.add(new Point (166,1766));
        CentrosBorrosidad.add(new Point (86,1766));
        CentrosBorrosidad.add(new Point (1547,1842));
        CentrosBorrosidad.add(new Point (1627,1843));
        CentrosBorrosidad.add(new Point (1705,1843));
        CentrosBorrosidad.add(new Point (1786,1843));
        CentrosBorrosidad.add(new Point (1865,1843));
        CentrosBorrosidad.add(new Point (1944,1844));
        CentrosBorrosidad.add(new Point (2023,1844));
        CentrosBorrosidad.add(new Point (2102,1844));
        CentrosBorrosidad.add(new Point (2182,1845));
        CentrosBorrosidad.add(new Point (2260,1845));
        CentrosBorrosidad.add(new Point (2340,1845));
        CentrosBorrosidad.add(new Point (2419,1846));
        CentrosBorrosidad.add(new Point (2497,1846));
        CentrosBorrosidad.add(new Point (2578,1846));
        CentrosBorrosidad.add(new Point (2657,1847));
        CentrosBorrosidad.add(new Point (2736,1847));
        CentrosBorrosidad.add(new Point (2815,1847));
        CentrosBorrosidad.add(new Point (2894,1847));
        CentrosBorrosidad.add(new Point (2972,1848));
        CentrosBorrosidad.add(new Point (3051,1848));
        CentrosBorrosidad.add(new Point (3130,1848));
        CentrosBorrosidad.add(new Point (3209,1849));
        CentrosBorrosidad.add(new Point (3288,1848));
        CentrosBorrosidad.add(new Point (3366,1849));
        CentrosBorrosidad.add(new Point (3447,1850));
        CentrosBorrosidad.add(new Point (1468,1842));
        CentrosBorrosidad.add(new Point (1389,1842));
        CentrosBorrosidad.add(new Point (1310,1841));
        CentrosBorrosidad.add(new Point (1234,1841));
        CentrosBorrosidad.add(new Point (1151,1841));
        CentrosBorrosidad.add(new Point (1073,1840));
        CentrosBorrosidad.add(new Point (994,1839));
        CentrosBorrosidad.add(new Point (915,1839));
        CentrosBorrosidad.add(new Point (835,1838));
        CentrosBorrosidad.add(new Point (756,1838));
        CentrosBorrosidad.add(new Point (677,1838));
        CentrosBorrosidad.add(new Point (598,1837));
        CentrosBorrosidad.add(new Point (519,1837));
        CentrosBorrosidad.add(new Point (440,1837));
        CentrosBorrosidad.add(new Point (361,1836));
        CentrosBorrosidad.add(new Point (282,1835));
        CentrosBorrosidad.add(new Point (205,1834));
        CentrosBorrosidad.add(new Point (125,1835));
        CentrosBorrosidad.add(new Point (45,1833));
        CentrosBorrosidad.add(new Point (1508,1910));
        CentrosBorrosidad.add(new Point (1587,1911));
        CentrosBorrosidad.add(new Point (1665,1911));
        CentrosBorrosidad.add(new Point (1745,1911));
        CentrosBorrosidad.add(new Point (1826,1911));
        CentrosBorrosidad.add(new Point (1904,1912));
        CentrosBorrosidad.add(new Point (1984,1912));
        CentrosBorrosidad.add(new Point (2063,1912));
        CentrosBorrosidad.add(new Point (2142,1913));
        CentrosBorrosidad.add(new Point (2220,1913));
        CentrosBorrosidad.add(new Point (2299,1913));
        CentrosBorrosidad.add(new Point (2378,1914));
        CentrosBorrosidad.add(new Point (2457,1914));
        CentrosBorrosidad.add(new Point (2536,1914));
        CentrosBorrosidad.add(new Point (2617,1914));
        CentrosBorrosidad.add(new Point (2695,1914));
        CentrosBorrosidad.add(new Point (2775,1915));
        CentrosBorrosidad.add(new Point (2854,1915));
        CentrosBorrosidad.add(new Point (2932,1916));
        CentrosBorrosidad.add(new Point (3011,1916));
        CentrosBorrosidad.add(new Point (3091,1916));
        CentrosBorrosidad.add(new Point (3169,1916));
        CentrosBorrosidad.add(new Point (3248,1917));
        CentrosBorrosidad.add(new Point (3327,1917));
        CentrosBorrosidad.add(new Point (3406,1918));
        CentrosBorrosidad.add(new Point (1429,1910));
        CentrosBorrosidad.add(new Point (1349,1909));
        CentrosBorrosidad.add(new Point (1270,1909));
        CentrosBorrosidad.add(new Point (1193,1908));
        CentrosBorrosidad.add(new Point (1112,1908));
        CentrosBorrosidad.add(new Point (1033,1908));
        CentrosBorrosidad.add(new Point (954,1907));
        CentrosBorrosidad.add(new Point (874,1907));
        CentrosBorrosidad.add(new Point (795,1906));
        CentrosBorrosidad.add(new Point (716,1906));
        CentrosBorrosidad.add(new Point (637,1906));
        CentrosBorrosidad.add(new Point (558,1905));
        CentrosBorrosidad.add(new Point (479,1905));
        CentrosBorrosidad.add(new Point (400,1904));
        CentrosBorrosidad.add(new Point (323,1902));
        CentrosBorrosidad.add(new Point (243,1903));
        CentrosBorrosidad.add(new Point (164,1903));
        CentrosBorrosidad.add(new Point (85,1902));
        CentrosBorrosidad.add(new Point (1468,1980));
        CentrosBorrosidad.add(new Point (1547,1980));
        CentrosBorrosidad.add(new Point (1626,1980));
        CentrosBorrosidad.add(new Point (1705,1981));
        CentrosBorrosidad.add(new Point (1786,1981));
        CentrosBorrosidad.add(new Point (1865,1982));
        CentrosBorrosidad.add(new Point (1944,1982));
        CentrosBorrosidad.add(new Point (2023,1982));
        CentrosBorrosidad.add(new Point (2102,1982));
        CentrosBorrosidad.add(new Point (2181,1983));
        CentrosBorrosidad.add(new Point (2260,1983));
        CentrosBorrosidad.add(new Point (2338,1983));
        CentrosBorrosidad.add(new Point (2417,1984));
        CentrosBorrosidad.add(new Point (2497,1984));
        CentrosBorrosidad.add(new Point (2577,1984));
        CentrosBorrosidad.add(new Point (2656,1984));
        CentrosBorrosidad.add(new Point (2735,1985));
        CentrosBorrosidad.add(new Point (2814,1985));
        CentrosBorrosidad.add(new Point (2893,1985));
        CentrosBorrosidad.add(new Point (2971,1985));
        CentrosBorrosidad.add(new Point (3051,1986));
        CentrosBorrosidad.add(new Point (3129,1986));
        CentrosBorrosidad.add(new Point (3208,1987));
        CentrosBorrosidad.add(new Point (3287,1987));
        CentrosBorrosidad.add(new Point (3366,1987));
        CentrosBorrosidad.add(new Point (3446,1987));
        CentrosBorrosidad.add(new Point (1389,1979));
        CentrosBorrosidad.add(new Point (1310,1979));
        CentrosBorrosidad.add(new Point (1231,1977));
        CentrosBorrosidad.add(new Point (1150,1978));
        CentrosBorrosidad.add(new Point (1074,1976));

        CentrosBorrosidad.add(new Point (994,1977));
        CentrosBorrosidad.add(new Point (915,1977));
        CentrosBorrosidad.add(new Point (834,1977));
        CentrosBorrosidad.add(new Point (755,1976));
        CentrosBorrosidad.add(new Point (676,1976));
        CentrosBorrosidad.add(new Point (597,1975));
        CentrosBorrosidad.add(new Point (518,1975));
        CentrosBorrosidad.add(new Point (440,1974));
        CentrosBorrosidad.add(new Point (361,1974));
        CentrosBorrosidad.add(new Point (282,1974));
        CentrosBorrosidad.add(new Point (205,1972));

        CentrosBorrosidad.add(new Point (124,1972));
        CentrosBorrosidad.add(new Point (44,1972));
        CentrosBorrosidad.add(new Point (1428,2048));
        CentrosBorrosidad.add(new Point (1507,2048));
        CentrosBorrosidad.add(new Point (1586,2049));
        CentrosBorrosidad.add(new Point (1665,2049));
        CentrosBorrosidad.add(new Point (1744,2049));
        CentrosBorrosidad.add(new Point (1825,2049));
        CentrosBorrosidad.add(new Point (1904,2050));
        CentrosBorrosidad.add(new Point (1983,2050));
        CentrosBorrosidad.add(new Point (2062,2051));
        CentrosBorrosidad.add(new Point (2141,2051));
        CentrosBorrosidad.add(new Point (2220,2051));
        CentrosBorrosidad.add(new Point (2299,2051));
        CentrosBorrosidad.add(new Point (2378,2052));
        CentrosBorrosidad.add(new Point (2457,2052));
        CentrosBorrosidad.add(new Point (2536,2052));
        CentrosBorrosidad.add(new Point (2616,2052));
        CentrosBorrosidad.add(new Point (2696,2053));
        CentrosBorrosidad.add(new Point (2774,2053));
        CentrosBorrosidad.add(new Point (2853,2053));
        CentrosBorrosidad.add(new Point (2931,2054));
        CentrosBorrosidad.add(new Point (3010,2054));
        CentrosBorrosidad.add(new Point (3089,2054));
        CentrosBorrosidad.add(new Point (3168,2054));
        CentrosBorrosidad.add(new Point (3247,2055));
        CentrosBorrosidad.add(new Point (3326,2054));
        CentrosBorrosidad.add(new Point (3405,2055));
        CentrosBorrosidad.add(new Point (1349,2047));
        CentrosBorrosidad.add(new Point (1270,2047));
        CentrosBorrosidad.add(new Point (1190,2047));

        CentrosBorrosidad.add(new Point (1112,2049));
        CentrosBorrosidad.add(new Point (1033,2046));
        CentrosBorrosidad.add(new Point (954,2045));
        CentrosBorrosidad.add(new Point (874,2044));
        CentrosBorrosidad.add(new Point (794,2044));
        CentrosBorrosidad.add(new Point (716,2043));
        CentrosBorrosidad.add(new Point (636,2044));
        CentrosBorrosidad.add(new Point (559,2042));

        CentrosBorrosidad.add(new Point (479,2043));
        CentrosBorrosidad.add(new Point (401,2042));
        CentrosBorrosidad.add(new Point (321,2042));
        CentrosBorrosidad.add(new Point (242,2041));
        CentrosBorrosidad.add(new Point (164,2040));
        CentrosBorrosidad.add(new Point (86,2040));
        CentrosBorrosidad.add(new Point (1388,2115));
        CentrosBorrosidad.add(new Point (1467,2116));
        CentrosBorrosidad.add(new Point (1546,2116));
        CentrosBorrosidad.add(new Point (1625,2117));
        CentrosBorrosidad.add(new Point (1704,2118));
        CentrosBorrosidad.add(new Point (1785,2118));
        CentrosBorrosidad.add(new Point (1864,2118));
        CentrosBorrosidad.add(new Point (1943,2118));
        CentrosBorrosidad.add(new Point (2022,2119));
        CentrosBorrosidad.add(new Point (2102,2119));
        CentrosBorrosidad.add(new Point (2180,2119));
        CentrosBorrosidad.add(new Point (2259,2120));
        CentrosBorrosidad.add(new Point (2337,2120));
        CentrosBorrosidad.add(new Point (2417,2120));
        CentrosBorrosidad.add(new Point (2496,2120));
        CentrosBorrosidad.add(new Point (2577,2121));
        CentrosBorrosidad.add(new Point (2656,2121));
        CentrosBorrosidad.add(new Point (2734,2121));
        CentrosBorrosidad.add(new Point (2813,2122));
        CentrosBorrosidad.add(new Point (2892,2122));
        CentrosBorrosidad.add(new Point (2971,2121));
        CentrosBorrosidad.add(new Point (3050,2122));
        CentrosBorrosidad.add(new Point (3129,2122));
        CentrosBorrosidad.add(new Point (3208,2122));
        CentrosBorrosidad.add(new Point (3286,2122));
        CentrosBorrosidad.add(new Point (3365,2123));
        CentrosBorrosidad.add(new Point (3445,2122));
        CentrosBorrosidad.add(new Point (1309,2115));
        CentrosBorrosidad.add(new Point (1230,2115));

        CentrosBorrosidad.add(new Point (1150,2115));

        CentrosBorrosidad.add(new Point (1072,2114));
        CentrosBorrosidad.add(new Point (992,2113));
        CentrosBorrosidad.add(new Point (916,2113));

        CentrosBorrosidad.add(new Point (834,2113));
        CentrosBorrosidad.add(new Point (755,2112));
        CentrosBorrosidad.add(new Point (676,2112));
        CentrosBorrosidad.add(new Point (597,2112));
        CentrosBorrosidad.add(new Point (518,2111));
        CentrosBorrosidad.add(new Point (439,2111));
        CentrosBorrosidad.add(new Point (360,2110));
        CentrosBorrosidad.add(new Point (281,2110));
        CentrosBorrosidad.add(new Point (203,2110));
        CentrosBorrosidad.add(new Point (123,2109));
        CentrosBorrosidad.add(new Point (43,2108));
        CentrosBorrosidad.add(new Point (1349,2184));
        CentrosBorrosidad.add(new Point (1427,2184));
        CentrosBorrosidad.add(new Point (1507,2184));
        CentrosBorrosidad.add(new Point (1585,2186));
        CentrosBorrosidad.add(new Point (1664,2185));
        CentrosBorrosidad.add(new Point (1743,2185));
        CentrosBorrosidad.add(new Point (1824,2186));
        CentrosBorrosidad.add(new Point (1903,2186));
        CentrosBorrosidad.add(new Point (1982,2186));
        CentrosBorrosidad.add(new Point (2061,2187));
        CentrosBorrosidad.add(new Point (2140,2187));
        CentrosBorrosidad.add(new Point (2219,2187));
        CentrosBorrosidad.add(new Point (2298,2188));
        CentrosBorrosidad.add(new Point (2377,2188));
        CentrosBorrosidad.add(new Point (2456,2188));
        CentrosBorrosidad.add(new Point (2535,2189));
        CentrosBorrosidad.add(new Point (2616,2189));
        CentrosBorrosidad.add(new Point (2695,2189));
        CentrosBorrosidad.add(new Point (2773,2189));
        CentrosBorrosidad.add(new Point (2852,2190));
        CentrosBorrosidad.add(new Point (2931,2190));
        CentrosBorrosidad.add(new Point (3010,2190));
        CentrosBorrosidad.add(new Point (3089,2190));
        CentrosBorrosidad.add(new Point (3168,2190));
        CentrosBorrosidad.add(new Point (3247,2190));
        CentrosBorrosidad.add(new Point (3325,2191));
        CentrosBorrosidad.add(new Point (3404,2191));
        CentrosBorrosidad.add(new Point (1270,2183));
        CentrosBorrosidad.add(new Point (1190,2185));
        CentrosBorrosidad.add(new Point (1110,2183));
        CentrosBorrosidad.add(new Point (1034,2181));

        CentrosBorrosidad.add(new Point (953,2182));
        CentrosBorrosidad.add(new Point (873,2181));
        CentrosBorrosidad.add(new Point (794,2181));
        CentrosBorrosidad.add(new Point (715,2180));
        CentrosBorrosidad.add(new Point (636,2180));
        CentrosBorrosidad.add(new Point (557,2180));
        CentrosBorrosidad.add(new Point (479,2178));

        CentrosBorrosidad.add(new Point (399,2179));
        CentrosBorrosidad.add(new Point (321,2178));
        CentrosBorrosidad.add(new Point (242,2178));
        CentrosBorrosidad.add(new Point (163,2177));
        CentrosBorrosidad.add(new Point (85,2176));
        CentrosBorrosidad.add(new Point (1309,2251));
        CentrosBorrosidad.add(new Point (1388,2252));
        CentrosBorrosidad.add(new Point (1466,2252));
        CentrosBorrosidad.add(new Point (1546,2253));
        CentrosBorrosidad.add(new Point (1624,2253));
        CentrosBorrosidad.add(new Point (1704,2253));
        CentrosBorrosidad.add(new Point (1785,2254));
        CentrosBorrosidad.add(new Point (1864,2254));
        CentrosBorrosidad.add(new Point (1942,2254));
        CentrosBorrosidad.add(new Point (2021,2255));
        CentrosBorrosidad.add(new Point (2100,2255));
        CentrosBorrosidad.add(new Point (2179,2255));
        CentrosBorrosidad.add(new Point (2258,2256));
        CentrosBorrosidad.add(new Point (2337,2256));
        CentrosBorrosidad.add(new Point (2416,2256));
        CentrosBorrosidad.add(new Point (2495,2257));
        CentrosBorrosidad.add(new Point (2576,2257));
        CentrosBorrosidad.add(new Point (2655,2257));
        CentrosBorrosidad.add(new Point (2733,2257));
        CentrosBorrosidad.add(new Point (2813,2257));
        CentrosBorrosidad.add(new Point (2892,2257));
        CentrosBorrosidad.add(new Point (2970,2258));
        CentrosBorrosidad.add(new Point (3049,2258));
        CentrosBorrosidad.add(new Point (3128,2258));
        CentrosBorrosidad.add(new Point (3207,2258));
        CentrosBorrosidad.add(new Point (3285,2259));
        CentrosBorrosidad.add(new Point (3364,2259));
        CentrosBorrosidad.add(new Point (3436,2270));
        CentrosBorrosidad.add(new Point (1229,2251));
        CentrosBorrosidad.add(new Point (1152,2252));
        CentrosBorrosidad.add(new Point (1071,2251));
        CentrosBorrosidad.add(new Point (993,2249));
        CentrosBorrosidad.add(new Point (914,2250));
        CentrosBorrosidad.add(new Point (832,2249));
        CentrosBorrosidad.add(new Point (756,2247));

        CentrosBorrosidad.add(new Point (676,2248));
        CentrosBorrosidad.add(new Point (596,2248));
        CentrosBorrosidad.add(new Point (517,2247));
        CentrosBorrosidad.add(new Point (441,2246));

        CentrosBorrosidad.add(new Point (360,2247));
        CentrosBorrosidad.add(new Point (281,2246));
        CentrosBorrosidad.add(new Point (202,2246));
        CentrosBorrosidad.add(new Point (124,2245));
        CentrosBorrosidad.add(new Point (44,2244));
        CentrosBorrosidad.add(new Point (1269,2322));
        CentrosBorrosidad.add(new Point (1348,2322));
        CentrosBorrosidad.add(new Point (1427,2322));
        CentrosBorrosidad.add(new Point (1506,2323));
        CentrosBorrosidad.add(new Point (1585,2323));
        CentrosBorrosidad.add(new Point (1664,2323));
        CentrosBorrosidad.add(new Point (1743,2323));
        CentrosBorrosidad.add(new Point (1824,2324));
        CentrosBorrosidad.add(new Point (1903,2324));
        CentrosBorrosidad.add(new Point (1982,2324));
        CentrosBorrosidad.add(new Point (2061,2325));
        CentrosBorrosidad.add(new Point (2140,2325));
        CentrosBorrosidad.add(new Point (2219,2325));
        CentrosBorrosidad.add(new Point (2298,2326));
        CentrosBorrosidad.add(new Point (2377,2326));
        CentrosBorrosidad.add(new Point (2455,2326));
        CentrosBorrosidad.add(new Point (2534,2326));
        CentrosBorrosidad.add(new Point (2615,2327));
        CentrosBorrosidad.add(new Point (2694,2327));
        CentrosBorrosidad.add(new Point (2773,2327));
        CentrosBorrosidad.add(new Point (2852,2328));
        CentrosBorrosidad.add(new Point (2930,2327));
        CentrosBorrosidad.add(new Point (3009,2328));
        CentrosBorrosidad.add(new Point (3088,2327));
        CentrosBorrosidad.add(new Point (3167,2328));
        CentrosBorrosidad.add(new Point (3246,2328));
        CentrosBorrosidad.add(new Point (3324,2328));
        CentrosBorrosidad.add(new Point (3403,2329));
        CentrosBorrosidad.add(new Point (1189,2321));
        CentrosBorrosidad.add(new Point (1111,2320));
        CentrosBorrosidad.add(new Point (1032,2320));
        CentrosBorrosidad.add(new Point (953,2320));
        CentrosBorrosidad.add(new Point (872,2319));
        CentrosBorrosidad.add(new Point (793,2319));
        CentrosBorrosidad.add(new Point (714,2319));
        CentrosBorrosidad.add(new Point (635,2318));
        CentrosBorrosidad.add(new Point (557,2317));
        CentrosBorrosidad.add(new Point (478,2317));
        CentrosBorrosidad.add(new Point (400,2316));
        CentrosBorrosidad.add(new Point (320,2316));
        CentrosBorrosidad.add(new Point (242,2315));
        CentrosBorrosidad.add(new Point (162,2315));
        CentrosBorrosidad.add(new Point (85,2313));

        CentrosBorrosidad.add(new Point (1229,2389));
        CentrosBorrosidad.add(new Point (1308,2389));
        CentrosBorrosidad.add(new Point (1387,2390));
        CentrosBorrosidad.add(new Point (1466,2390));
        CentrosBorrosidad.add(new Point (1545,2390));
        CentrosBorrosidad.add(new Point (1624,2391));
        CentrosBorrosidad.add(new Point (1703,2391));
        CentrosBorrosidad.add(new Point (1783,2392));
        CentrosBorrosidad.add(new Point (1863,2392));
        CentrosBorrosidad.add(new Point (1941,2392));
        CentrosBorrosidad.add(new Point (2021,2393));
        CentrosBorrosidad.add(new Point (2099,2393));
        CentrosBorrosidad.add(new Point (2179,2393));
        CentrosBorrosidad.add(new Point (2257,2394));
        CentrosBorrosidad.add(new Point (2337,2394));
        CentrosBorrosidad.add(new Point (2416,2394));
        CentrosBorrosidad.add(new Point (2495,2394));
        CentrosBorrosidad.add(new Point (2575,2395));
        CentrosBorrosidad.add(new Point (2654,2395));
        CentrosBorrosidad.add(new Point (2733,2396));
        CentrosBorrosidad.add(new Point (2812,2395));
        CentrosBorrosidad.add(new Point (2891,2396));
        CentrosBorrosidad.add(new Point (2970,2396));
        CentrosBorrosidad.add(new Point (3049,2396));
        CentrosBorrosidad.add(new Point (3127,2396));
        CentrosBorrosidad.add(new Point (3206,2396));
        CentrosBorrosidad.add(new Point (3285,2397));
        CentrosBorrosidad.add(new Point (3364,2397));
        CentrosBorrosidad.add(new Point (3444,2397));
        CentrosBorrosidad.add(new Point (1150,2389));
        CentrosBorrosidad.add(new Point (1071,2387));
        CentrosBorrosidad.add(new Point (992,2388));
        CentrosBorrosidad.add(new Point (913,2388));
        CentrosBorrosidad.add(new Point (833,2387));
        CentrosBorrosidad.add(new Point (754,2387));
        CentrosBorrosidad.add(new Point (675,2387));
        CentrosBorrosidad.add(new Point (597,2386));
        CentrosBorrosidad.add(new Point (518,2385));
        CentrosBorrosidad.add(new Point (439,2384));
        CentrosBorrosidad.add(new Point (361,2384));
        CentrosBorrosidad.add(new Point (281,2384));
        CentrosBorrosidad.add(new Point (203,2383));
        CentrosBorrosidad.add(new Point (124,2382));
        CentrosBorrosidad.add(new Point (44,2381));
        CentrosBorrosidad.add(new Point (1190,2457));
        CentrosBorrosidad.add(new Point (1268,2458));
        CentrosBorrosidad.add(new Point (1347,2458));
        CentrosBorrosidad.add(new Point (1426,2458));
        CentrosBorrosidad.add(new Point (1506,2458));
        CentrosBorrosidad.add(new Point (1584,2459));
        CentrosBorrosidad.add(new Point (1663,2459));
        CentrosBorrosidad.add(new Point (1742,2460));
        CentrosBorrosidad.add(new Point (1823,2461));
        CentrosBorrosidad.add(new Point (1902,2461));
        CentrosBorrosidad.add(new Point (1981,2461));
        CentrosBorrosidad.add(new Point (2060,2461));
        CentrosBorrosidad.add(new Point (2139,2462));
        CentrosBorrosidad.add(new Point (2218,2462));
        CentrosBorrosidad.add(new Point (2297,2462));
        CentrosBorrosidad.add(new Point (2376,2462));
        CentrosBorrosidad.add(new Point (2455,2462));
        CentrosBorrosidad.add(new Point (2533,2462));
        CentrosBorrosidad.add(new Point (2614,2463));
        CentrosBorrosidad.add(new Point (2693,2463));
        CentrosBorrosidad.add(new Point (2772,2463));
        CentrosBorrosidad.add(new Point (2851,2464));
        CentrosBorrosidad.add(new Point (2930,2464));
        CentrosBorrosidad.add(new Point (3009,2464));
        CentrosBorrosidad.add(new Point (3088,2464));
        CentrosBorrosidad.add(new Point (3166,2464));
        CentrosBorrosidad.add(new Point (3245,2465));
        CentrosBorrosidad.add(new Point (3324,2465));
        CentrosBorrosidad.add(new Point (3402,2464));
        CentrosBorrosidad.add(new Point (1110,2457));
        CentrosBorrosidad.add(new Point (1031,2457));
        CentrosBorrosidad.add(new Point (952,2456));
        CentrosBorrosidad.add(new Point (872,2456));
        CentrosBorrosidad.add(new Point (793,2455));
        CentrosBorrosidad.add(new Point (714,2455));
        CentrosBorrosidad.add(new Point (635,2454));
        CentrosBorrosidad.add(new Point (556,2454));
        CentrosBorrosidad.add(new Point (477,2454));
        CentrosBorrosidad.add(new Point (399,2452));
        CentrosBorrosidad.add(new Point (320,2452));
        CentrosBorrosidad.add(new Point (242,2451));
        CentrosBorrosidad.add(new Point (163,2451));
        CentrosBorrosidad.add(new Point (85,2450));
        CentrosBorrosidad.add(new Point (1149,2525));
        CentrosBorrosidad.add(new Point (1229,2525));
        CentrosBorrosidad.add(new Point (1308,2525));
        CentrosBorrosidad.add(new Point (1386,2527));
        CentrosBorrosidad.add(new Point (1466,2526));
        CentrosBorrosidad.add(new Point (1545,2526));
        CentrosBorrosidad.add(new Point (1623,2527));
        CentrosBorrosidad.add(new Point (1702,2528));
        CentrosBorrosidad.add(new Point (1783,2528));
        CentrosBorrosidad.add(new Point (1862,2528));
        CentrosBorrosidad.add(new Point (1941,2529));
        CentrosBorrosidad.add(new Point (2020,2529));
        CentrosBorrosidad.add(new Point (2099,2529));
        CentrosBorrosidad.add(new Point (2178,2529));
        CentrosBorrosidad.add(new Point (2257,2530));
        CentrosBorrosidad.add(new Point (2336,2530));
        CentrosBorrosidad.add(new Point (2415,2530));
        CentrosBorrosidad.add(new Point (2494,2530));
        CentrosBorrosidad.add(new Point (2574,2531));
        CentrosBorrosidad.add(new Point (2653,2531));
        CentrosBorrosidad.add(new Point (2733,2531));
        CentrosBorrosidad.add(new Point (2811,2532));
        CentrosBorrosidad.add(new Point (2890,2531));
        CentrosBorrosidad.add(new Point (2969,2531));
        CentrosBorrosidad.add(new Point (3048,2532));
        CentrosBorrosidad.add(new Point (3127,2532));
        CentrosBorrosidad.add(new Point (3206,2532));
        CentrosBorrosidad.add(new Point (3284,2532));
        CentrosBorrosidad.add(new Point (3364,2533));
        CentrosBorrosidad.add(new Point (3443,2533));
        CentrosBorrosidad.add(new Point (1071,2524));
        CentrosBorrosidad.add(new Point (992,2524));
        CentrosBorrosidad.add(new Point (913,2523));
        CentrosBorrosidad.add(new Point (832,2523));
        CentrosBorrosidad.add(new Point (754,2522));
        CentrosBorrosidad.add(new Point (675,2522));
        CentrosBorrosidad.add(new Point (595,2522));

        CentrosBorrosidad.add(new Point (517,2522));
        CentrosBorrosidad.add(new Point (439,2521));
        CentrosBorrosidad.add(new Point (359,2520));
        CentrosBorrosidad.add(new Point (280,2520));
        CentrosBorrosidad.add(new Point (202,2519));
        CentrosBorrosidad.add(new Point (123,2518));
        CentrosBorrosidad.add(new Point (43,2518));


        CentrosBorrosidad.add(new Point (1110,2593));

        CentrosBorrosidad.add(new Point (1189,2593));
        CentrosBorrosidad.add(new Point (1268,2594));
        CentrosBorrosidad.add(new Point (1347,2594));
        CentrosBorrosidad.add(new Point (1426,2594));
        CentrosBorrosidad.add(new Point (1505,2595));
        CentrosBorrosidad.add(new Point (1584,2594));
        CentrosBorrosidad.add(new Point (1662,2596));
        CentrosBorrosidad.add(new Point (1742,2596));
        CentrosBorrosidad.add(new Point (1823,2596));
        CentrosBorrosidad.add(new Point (1901,2596));
        CentrosBorrosidad.add(new Point (1980,2597));
        CentrosBorrosidad.add(new Point (2059,2597));
        CentrosBorrosidad.add(new Point (2138,2597));
        CentrosBorrosidad.add(new Point (2217,2598));
        CentrosBorrosidad.add(new Point (2296,2598));
        CentrosBorrosidad.add(new Point (2375,2598));
        CentrosBorrosidad.add(new Point (2454,2599));
        CentrosBorrosidad.add(new Point (2533,2599));
        CentrosBorrosidad.add(new Point (2614,2599));
        CentrosBorrosidad.add(new Point (2692,2599));
        CentrosBorrosidad.add(new Point (2771,2599));
        CentrosBorrosidad.add(new Point (2850,2597));
        CentrosBorrosidad.add(new Point (2929,2600));
        CentrosBorrosidad.add(new Point (3008,2597));
        CentrosBorrosidad.add(new Point (3087,2597));
        CentrosBorrosidad.add(new Point (3166,2597));
        CentrosBorrosidad.add(new Point (3245,2597));
        CentrosBorrosidad.add(new Point (3324,2597));
        CentrosBorrosidad.add(new Point (3403,2597));
        CentrosBorrosidad.add(new Point (1031,2593));
        CentrosBorrosidad.add(new Point (952,2592));
        CentrosBorrosidad.add(new Point (872,2591));
        CentrosBorrosidad.add(new Point (793,2591));
        CentrosBorrosidad.add(new Point (714,2590));
        CentrosBorrosidad.add(new Point (635,2589));
        CentrosBorrosidad.add(new Point (555,2590));
        CentrosBorrosidad.add(new Point (478,2589));

        CentrosBorrosidad.add(new Point (399,2588));
        CentrosBorrosidad.add(new Point (320,2587));
        CentrosBorrosidad.add(new Point (241,2587));
        CentrosBorrosidad.add(new Point (162,2587));
        CentrosBorrosidad.add(new Point (84,2586));
        CentrosBorrosidad.add(new Point (1868,1296));
        CentrosBorrosidad.add(new Point (1947,1296));
        CentrosBorrosidad.add(new Point (2026,1297));
        CentrosBorrosidad.add(new Point (2105,1297));
        CentrosBorrosidad.add(new Point (2184,1297));
        CentrosBorrosidad.add(new Point (2263,1297));
        CentrosBorrosidad.add(new Point (2342,1298));
        CentrosBorrosidad.add(new Point (2421,1298));
        CentrosBorrosidad.add(new Point (2499,1299));
        CentrosBorrosidad.add(new Point (2580,1299));
        CentrosBorrosidad.add(new Point (2660,1299));
        CentrosBorrosidad.add(new Point (2739,1300));
        CentrosBorrosidad.add(new Point (2817,1300));
        CentrosBorrosidad.add(new Point (2896,1300));
        CentrosBorrosidad.add(new Point (2975,1301));
        CentrosBorrosidad.add(new Point (3054,1301));
        CentrosBorrosidad.add(new Point (3133,1301));
        CentrosBorrosidad.add(new Point (3212,1302));
        CentrosBorrosidad.add(new Point (3290,1302));
        CentrosBorrosidad.add(new Point (3369,1302));
        CentrosBorrosidad.add(new Point (3450,1303));
        CentrosBorrosidad.add(new Point (1789,1296));
        CentrosBorrosidad.add(new Point (1708,1295));
        CentrosBorrosidad.add(new Point (1629,1295));
        CentrosBorrosidad.add(new Point (1550,1295));
        CentrosBorrosidad.add(new Point (1471,1294));
        CentrosBorrosidad.add(new Point (1392,1294));
        CentrosBorrosidad.add(new Point (1313,1294));
        CentrosBorrosidad.add(new Point (1234,1293));
        CentrosBorrosidad.add(new Point (1155,1293));
        CentrosBorrosidad.add(new Point (1076,1292));
        CentrosBorrosidad.add(new Point (997,1292));
        CentrosBorrosidad.add(new Point (918,1291));
        CentrosBorrosidad.add(new Point (837,1291));
        CentrosBorrosidad.add(new Point (758,1291));
        CentrosBorrosidad.add(new Point (680,1290));
        CentrosBorrosidad.add(new Point (601,1290));
        CentrosBorrosidad.add(new Point (522,1290));
        CentrosBorrosidad.add(new Point (442,1289));
        CentrosBorrosidad.add(new Point (364,1289));
        CentrosBorrosidad.add(new Point (285,1288));
        CentrosBorrosidad.add(new Point (206,1288));
        CentrosBorrosidad.add(new Point (127,1287));
        CentrosBorrosidad.add(new Point (46,1287));
        CentrosBorrosidad.add(new Point (1908,1228));
        CentrosBorrosidad.add(new Point (1987,1229));
        CentrosBorrosidad.add(new Point (2066,1229));
        CentrosBorrosidad.add(new Point (2144,1229));
        CentrosBorrosidad.add(new Point (2223,1229));
        CentrosBorrosidad.add(new Point (2303,1230));
        CentrosBorrosidad.add(new Point (2382,1230));
        CentrosBorrosidad.add(new Point (2460,1230));
        CentrosBorrosidad.add(new Point (2539,1231));
        CentrosBorrosidad.add(new Point (2620,1231));
        CentrosBorrosidad.add(new Point (2699,1231));
        CentrosBorrosidad.add(new Point (2778,1231));
        CentrosBorrosidad.add(new Point (2857,1232));
        CentrosBorrosidad.add(new Point (2936,1232));
        CentrosBorrosidad.add(new Point (3015,1233));
        CentrosBorrosidad.add(new Point (3093,1233));
        CentrosBorrosidad.add(new Point (3172,1233));
        CentrosBorrosidad.add(new Point (3251,1233));
        CentrosBorrosidad.add(new Point (3330,1234));
        CentrosBorrosidad.add(new Point (3409,1234));
        CentrosBorrosidad.add(new Point (1829,1228));
        CentrosBorrosidad.add(new Point (1748,1227));
        CentrosBorrosidad.add(new Point (1669,1227));
        CentrosBorrosidad.add(new Point (1590,1227));
        CentrosBorrosidad.add(new Point (1511,1226));
        CentrosBorrosidad.add(new Point (1432,1226));
        CentrosBorrosidad.add(new Point (1353,1225));
        CentrosBorrosidad.add(new Point (1274,1225));
        CentrosBorrosidad.add(new Point (1195,1225));
        CentrosBorrosidad.add(new Point (1116,1225));
        CentrosBorrosidad.add(new Point (1037,1224));
        CentrosBorrosidad.add(new Point (958,1223));
        CentrosBorrosidad.add(new Point (877,1223));
        CentrosBorrosidad.add(new Point (798,1223));
        CentrosBorrosidad.add(new Point (719,1222));
        CentrosBorrosidad.add(new Point (640,1222));
        CentrosBorrosidad.add(new Point (561,1221));
        CentrosBorrosidad.add(new Point (482,1221));
        CentrosBorrosidad.add(new Point (403,1220));
        CentrosBorrosidad.add(new Point (327,1219));

        CentrosBorrosidad.add(new Point (246,1220));
        CentrosBorrosidad.add(new Point (167,1219));
        CentrosBorrosidad.add(new Point (88,1219));
        CentrosBorrosidad.add(new Point (1947,1160));
        CentrosBorrosidad.add(new Point (2026,1160));
        CentrosBorrosidad.add(new Point (2106,1161));
        CentrosBorrosidad.add(new Point (2184,1161));
        CentrosBorrosidad.add(new Point (2264,1161));
        CentrosBorrosidad.add(new Point (2342,1162));
        CentrosBorrosidad.add(new Point (2421,1162));
        CentrosBorrosidad.add(new Point (2500,1162));
        CentrosBorrosidad.add(new Point (2581,1162));
        CentrosBorrosidad.add(new Point (2660,1163));
        CentrosBorrosidad.add(new Point (2739,1163));
        CentrosBorrosidad.add(new Point (2818,1164));
        CentrosBorrosidad.add(new Point (2897,1164));
        CentrosBorrosidad.add(new Point (2976,1164));
        CentrosBorrosidad.add(new Point (3054,1165));
        CentrosBorrosidad.add(new Point (3133,1165));
        CentrosBorrosidad.add(new Point (3212,1165));
        CentrosBorrosidad.add(new Point (3291,1165));
        CentrosBorrosidad.add(new Point (3370,1166));
        CentrosBorrosidad.add(new Point (3450,1166));
        CentrosBorrosidad.add(new Point (1868,1160));
        CentrosBorrosidad.add(new Point (1790,1159));
        CentrosBorrosidad.add(new Point (1709,1159));
        CentrosBorrosidad.add(new Point (1630,1159));
        CentrosBorrosidad.add(new Point (1551,1159));
        CentrosBorrosidad.add(new Point (1472,1158));
        CentrosBorrosidad.add(new Point (1393,1158));
        CentrosBorrosidad.add(new Point (1314,1157));
        CentrosBorrosidad.add(new Point (1234,1157));
        CentrosBorrosidad.add(new Point (1155,1157));
        CentrosBorrosidad.add(new Point (1076,1156));
        CentrosBorrosidad.add(new Point (998,1156));
        CentrosBorrosidad.add(new Point (918,1155));
        CentrosBorrosidad.add(new Point (838,1155));
        CentrosBorrosidad.add(new Point (759,1154));
        CentrosBorrosidad.add(new Point (680,1154));
        CentrosBorrosidad.add(new Point (601,1154));
        CentrosBorrosidad.add(new Point (522,1153));
        CentrosBorrosidad.add(new Point (443,1153));
        CentrosBorrosidad.add(new Point (364,1152));
        CentrosBorrosidad.add(new Point (285,1152));
        CentrosBorrosidad.add(new Point (209,1150));

        CentrosBorrosidad.add(new Point (128,1151));
        CentrosBorrosidad.add(new Point (47,1150));
        CentrosBorrosidad.add(new Point (1987,1090));
        CentrosBorrosidad.add(new Point (2066,1091));
        CentrosBorrosidad.add(new Point (2146,1091));
        CentrosBorrosidad.add(new Point (2224,1091));
        CentrosBorrosidad.add(new Point (2303,1092));
        CentrosBorrosidad.add(new Point (2382,1092));
        CentrosBorrosidad.add(new Point (2461,1092));
        CentrosBorrosidad.add(new Point (2540,1093));
        CentrosBorrosidad.add(new Point (2621,1093));
        CentrosBorrosidad.add(new Point (2700,1093));
        CentrosBorrosidad.add(new Point (2778,1094));
        CentrosBorrosidad.add(new Point (2858,1094));
        CentrosBorrosidad.add(new Point (2937,1094));
        CentrosBorrosidad.add(new Point (3016,1094));
        CentrosBorrosidad.add(new Point (3095,1095));
        CentrosBorrosidad.add(new Point (3173,1095));
        CentrosBorrosidad.add(new Point (3252,1095));
        CentrosBorrosidad.add(new Point (3331,1096));
        CentrosBorrosidad.add(new Point (3410,1096));
        CentrosBorrosidad.add(new Point (1908,1089));
        CentrosBorrosidad.add(new Point (1829,1090));
        CentrosBorrosidad.add(new Point (1749,1089));
        CentrosBorrosidad.add(new Point (1670,1089));
        CentrosBorrosidad.add(new Point (1591,1088));
        CentrosBorrosidad.add(new Point (1512,1088));
        CentrosBorrosidad.add(new Point (1433,1088));
        CentrosBorrosidad.add(new Point (1354,1087));
        CentrosBorrosidad.add(new Point (1274,1087));
        CentrosBorrosidad.add(new Point (1195,1086));
        CentrosBorrosidad.add(new Point (1116,1086));
        CentrosBorrosidad.add(new Point (1038,1086));
        CentrosBorrosidad.add(new Point (958,1085));
        CentrosBorrosidad.add(new Point (877,1085));
        CentrosBorrosidad.add(new Point (799,1085));
        CentrosBorrosidad.add(new Point (719,1084));
        CentrosBorrosidad.add(new Point (640,1084));
        CentrosBorrosidad.add(new Point (562,1083));
        CentrosBorrosidad.add(new Point (483,1083));
        CentrosBorrosidad.add(new Point (404,1082));
        CentrosBorrosidad.add(new Point (325,1082));
        CentrosBorrosidad.add(new Point (246,1081));
        CentrosBorrosidad.add(new Point (167,1081));
        CentrosBorrosidad.add(new Point (88,1081));
        CentrosBorrosidad.add(new Point (2028,1022));
        CentrosBorrosidad.add(new Point (2107,1022));
        CentrosBorrosidad.add(new Point (2185,1023));
        CentrosBorrosidad.add(new Point (2264,1023));
        CentrosBorrosidad.add(new Point (2343,1023));
        CentrosBorrosidad.add(new Point (2422,1024));
        CentrosBorrosidad.add(new Point (2501,1024));
        CentrosBorrosidad.add(new Point (2582,1024));
        CentrosBorrosidad.add(new Point (2661,1025));
        CentrosBorrosidad.add(new Point (2740,1025));
        CentrosBorrosidad.add(new Point (2819,1025));
        CentrosBorrosidad.add(new Point (2897,1026));
        CentrosBorrosidad.add(new Point (2976,1026));
        CentrosBorrosidad.add(new Point (3055,1026));

        CentrosBorrosidad.add(new Point (3134,1027));
        CentrosBorrosidad.add(new Point (3213,1027));
        CentrosBorrosidad.add(new Point (3292,1027));
        CentrosBorrosidad.add(new Point (3371,1028));
        CentrosBorrosidad.add(new Point (3451,1028));
        CentrosBorrosidad.add(new Point (1948,1022));
        CentrosBorrosidad.add(new Point (1869,1022));
        CentrosBorrosidad.add(new Point (1790,1021));
        CentrosBorrosidad.add(new Point (1709,1021));
        CentrosBorrosidad.add(new Point (1630,1021));
        CentrosBorrosidad.add(new Point (1551,1020));
        CentrosBorrosidad.add(new Point (1472,1020));
        CentrosBorrosidad.add(new Point (1393,1019));
        CentrosBorrosidad.add(new Point (1314,1019));
        CentrosBorrosidad.add(new Point (1235,1019));
        CentrosBorrosidad.add(new Point (1156,1018));
        CentrosBorrosidad.add(new Point (1077,1018));
        CentrosBorrosidad.add(new Point (998,1017));
        CentrosBorrosidad.add(new Point (919,1017));
        CentrosBorrosidad.add(new Point (839,1016));
        CentrosBorrosidad.add(new Point (759,1016));
        CentrosBorrosidad.add(new Point (680,1016));
        CentrosBorrosidad.add(new Point (601,1015));
        CentrosBorrosidad.add(new Point (522,1015));
        CentrosBorrosidad.add(new Point (445,1014));

        CentrosBorrosidad.add(new Point (365,1014));
        CentrosBorrosidad.add(new Point (286,1013));
        CentrosBorrosidad.add(new Point (207,1013));
        CentrosBorrosidad.add(new Point (128,1013));
        CentrosBorrosidad.add(new Point (47,1012));
        CentrosBorrosidad.add(new Point (2067,954));
        CentrosBorrosidad.add(new Point (2146,955));
        CentrosBorrosidad.add(new Point (2225,955));
        CentrosBorrosidad.add(new Point (2304,955));
        CentrosBorrosidad.add(new Point (2383,955));
        CentrosBorrosidad.add(new Point (2462,956));
        CentrosBorrosidad.add(new Point (2541,956));
        CentrosBorrosidad.add(new Point (2621,957));
        CentrosBorrosidad.add(new Point (2701,957));
        CentrosBorrosidad.add(new Point (2779,957));
        CentrosBorrosidad.add(new Point (2858,958));
        CentrosBorrosidad.add(new Point (2937,958));
        CentrosBorrosidad.add(new Point (3016,958));
        CentrosBorrosidad.add(new Point (3095,958));
        CentrosBorrosidad.add(new Point (3174,958));
        CentrosBorrosidad.add(new Point (3253,959));
        CentrosBorrosidad.add(new Point (3331,959));
        CentrosBorrosidad.add(new Point (3410,959));
        CentrosBorrosidad.add(new Point (1988,954));
        CentrosBorrosidad.add(new Point (1909,953));
        CentrosBorrosidad.add(new Point (1830,953));
        CentrosBorrosidad.add(new Point (1749,953));
        CentrosBorrosidad.add(new Point (1670,952));
        CentrosBorrosidad.add(new Point (1591,952));
        CentrosBorrosidad.add(new Point (1512,952));
        CentrosBorrosidad.add(new Point (1433,951));
        CentrosBorrosidad.add(new Point (1354,951));
        CentrosBorrosidad.add(new Point (1275,951));
        CentrosBorrosidad.add(new Point (1196,950));
        CentrosBorrosidad.add(new Point (1116,950));
        CentrosBorrosidad.add(new Point (1038,949));
        CentrosBorrosidad.add(new Point (959,949));
        CentrosBorrosidad.add(new Point (878,949));
        CentrosBorrosidad.add(new Point (799,948));
        CentrosBorrosidad.add(new Point (720,948));
        CentrosBorrosidad.add(new Point (641,947));
        CentrosBorrosidad.add(new Point (562,947));
        CentrosBorrosidad.add(new Point (484,946));
        CentrosBorrosidad.add(new Point (405,946));
        CentrosBorrosidad.add(new Point (326,946));
        CentrosBorrosidad.add(new Point (247,945));
        CentrosBorrosidad.add(new Point (168,944));
        CentrosBorrosidad.add(new Point (89,944));
        CentrosBorrosidad.add(new Point (2107,886));
        CentrosBorrosidad.add(new Point (2186,887));
        CentrosBorrosidad.add(new Point (2265,887));
        CentrosBorrosidad.add(new Point (2344,887));
        CentrosBorrosidad.add(new Point (2422,887));
        CentrosBorrosidad.add(new Point (2502,887));
        CentrosBorrosidad.add(new Point (2583,888));
        CentrosBorrosidad.add(new Point (2661,888));
        CentrosBorrosidad.add(new Point (2740,888));
        CentrosBorrosidad.add(new Point (2819,889));
        CentrosBorrosidad.add(new Point (2898,889));
        CentrosBorrosidad.add(new Point (2977,890));
        CentrosBorrosidad.add(new Point (3056,890));
        CentrosBorrosidad.add(new Point (3135,890));
        CentrosBorrosidad.add(new Point (3214,891));
        CentrosBorrosidad.add(new Point (3293,891));
        CentrosBorrosidad.add(new Point (3371,891));
        CentrosBorrosidad.add(new Point (3452,892));
        CentrosBorrosidad.add(new Point (2028,886));
        CentrosBorrosidad.add(new Point (1949,886));
        CentrosBorrosidad.add(new Point (1870,885));
        CentrosBorrosidad.add(new Point (1791,885));
        CentrosBorrosidad.add(new Point (1710,884));
        CentrosBorrosidad.add(new Point (1631,884));
        CentrosBorrosidad.add(new Point (1551,884));
        CentrosBorrosidad.add(new Point (1472,883));
        CentrosBorrosidad.add(new Point (1394,883));
        CentrosBorrosidad.add(new Point (1315,882));
        CentrosBorrosidad.add(new Point (1236,882));
        CentrosBorrosidad.add(new Point (1157,882));
        CentrosBorrosidad.add(new Point (1078,881));
        CentrosBorrosidad.add(new Point (999,881));
        CentrosBorrosidad.add(new Point (920,880));
        CentrosBorrosidad.add(new Point (839,880));
        CentrosBorrosidad.add(new Point (760,880));
        CentrosBorrosidad.add(new Point (681,879));
        CentrosBorrosidad.add(new Point (602,879));
        CentrosBorrosidad.add(new Point (523,878));
        CentrosBorrosidad.add(new Point (444,878));
        CentrosBorrosidad.add(new Point (366,877));
        CentrosBorrosidad.add(new Point (287,877));
        CentrosBorrosidad.add(new Point (207,877));
        CentrosBorrosidad.add(new Point (131,875));

        CentrosBorrosidad.add(new Point (48,875));
        CentrosBorrosidad.add(new Point (2147,818));
        CentrosBorrosidad.add(new Point (2226,818));
        CentrosBorrosidad.add(new Point (2304,819));
        CentrosBorrosidad.add(new Point (2384,819));
        CentrosBorrosidad.add(new Point (2463,819));
        CentrosBorrosidad.add(new Point (2541,819));
        CentrosBorrosidad.add(new Point (2622,820));
        CentrosBorrosidad.add(new Point (2701,820));
        CentrosBorrosidad.add(new Point (2780,821));
        CentrosBorrosidad.add(new Point (2859,821));
        CentrosBorrosidad.add(new Point (2938,821));
        CentrosBorrosidad.add(new Point (3017,822));
        CentrosBorrosidad.add(new Point (3095,822));
        CentrosBorrosidad.add(new Point (3174,822));
        CentrosBorrosidad.add(new Point (3253,823));
        CentrosBorrosidad.add(new Point (3332,823));
        CentrosBorrosidad.add(new Point (3411,823));
        CentrosBorrosidad.add(new Point (2068,817));
        CentrosBorrosidad.add(new Point (1989,817));
        CentrosBorrosidad.add(new Point (1910,817));
        CentrosBorrosidad.add(new Point (1831,817));
        CentrosBorrosidad.add(new Point (1750,816));
        CentrosBorrosidad.add(new Point (1671,816));
        CentrosBorrosidad.add(new Point (1592,816));
        CentrosBorrosidad.add(new Point (1513,815));
        CentrosBorrosidad.add(new Point (1434,815));
        CentrosBorrosidad.add(new Point (1355,814));
        CentrosBorrosidad.add(new Point (1276,814));
        CentrosBorrosidad.add(new Point (1197,813));
        CentrosBorrosidad.add(new Point (1117,813));
        CentrosBorrosidad.add(new Point (1038,813));
        CentrosBorrosidad.add(new Point (959,813));
        CentrosBorrosidad.add(new Point (879,812));
        CentrosBorrosidad.add(new Point (800,811));
        CentrosBorrosidad.add(new Point (721,811));
        CentrosBorrosidad.add(new Point (642,811));
        CentrosBorrosidad.add(new Point (563,810));
        CentrosBorrosidad.add(new Point (484,810));
        CentrosBorrosidad.add(new Point (405,809));
        CentrosBorrosidad.add(new Point (326,809));
        CentrosBorrosidad.add(new Point (247,808));
        CentrosBorrosidad.add(new Point (168,808));
        CentrosBorrosidad.add(new Point (91,806));

        CentrosBorrosidad.add(new Point (2186,750));
        CentrosBorrosidad.add(new Point (2265,750));
        CentrosBorrosidad.add(new Point (2344,751));
        CentrosBorrosidad.add(new Point (2423,751));
        CentrosBorrosidad.add(new Point (2502,751));
        CentrosBorrosidad.add(new Point (2583,752));
        CentrosBorrosidad.add(new Point (2662,752));
        CentrosBorrosidad.add(new Point (2741,752));
        CentrosBorrosidad.add(new Point (2819,753));
        CentrosBorrosidad.add(new Point (2899,753));
        CentrosBorrosidad.add(new Point (2978,753));
        CentrosBorrosidad.add(new Point (3056,754));
        CentrosBorrosidad.add(new Point (3135,754));
        CentrosBorrosidad.add(new Point (3214,754));
        CentrosBorrosidad.add(new Point (3293,755));
        CentrosBorrosidad.add(new Point (3372,755));
        CentrosBorrosidad.add(new Point (3453,755));
        CentrosBorrosidad.add(new Point (2107,749));
        CentrosBorrosidad.add(new Point (2028,749));
        CentrosBorrosidad.add(new Point (1949,749));
        CentrosBorrosidad.add(new Point (1870,749));
        CentrosBorrosidad.add(new Point (1791,748));
        CentrosBorrosidad.add(new Point (1711,747));
        CentrosBorrosidad.add(new Point (1632,747));
        CentrosBorrosidad.add(new Point (1552,747));
        CentrosBorrosidad.add(new Point (1473,747));
        CentrosBorrosidad.add(new Point (1394,746));
        CentrosBorrosidad.add(new Point (1315,746));
        CentrosBorrosidad.add(new Point (1236,745));
        CentrosBorrosidad.add(new Point (1157,745));
        CentrosBorrosidad.add(new Point (1078,745));
        CentrosBorrosidad.add(new Point (999,745));
        CentrosBorrosidad.add(new Point (920,744));
        CentrosBorrosidad.add(new Point (840,744));
        CentrosBorrosidad.add(new Point (761,743));
        CentrosBorrosidad.add(new Point (682,743));
        CentrosBorrosidad.add(new Point (603,742));
        CentrosBorrosidad.add(new Point (524,742));
        CentrosBorrosidad.add(new Point (445,742));
        CentrosBorrosidad.add(new Point (366,741));
        CentrosBorrosidad.add(new Point (287,740));
        CentrosBorrosidad.add(new Point (208,740));
        CentrosBorrosidad.add(new Point (129,740));
        CentrosBorrosidad.add(new Point (49,739));
        CentrosBorrosidad.add(new Point (2226,680));
        CentrosBorrosidad.add(new Point (2305,680));
        CentrosBorrosidad.add(new Point (2384,681));
        CentrosBorrosidad.add(new Point (2463,681));
        CentrosBorrosidad.add(new Point (2542,681));
        CentrosBorrosidad.add(new Point (2623,682));
        CentrosBorrosidad.add(new Point (2702,682));
        CentrosBorrosidad.add(new Point (2781,682));
        CentrosBorrosidad.add(new Point (2859,683));
        CentrosBorrosidad.add(new Point (2939,683));
        CentrosBorrosidad.add(new Point (3017,683));
        CentrosBorrosidad.add(new Point (3096,684));
        CentrosBorrosidad.add(new Point (3175,684));
        CentrosBorrosidad.add(new Point (3253,684));
        CentrosBorrosidad.add(new Point (3333,685));
        CentrosBorrosidad.add(new Point (3412,685));
        CentrosBorrosidad.add(new Point (2147,680));
        CentrosBorrosidad.add(new Point (2068,679));
        CentrosBorrosidad.add(new Point (1989,679));
        CentrosBorrosidad.add(new Point (1910,679));
        CentrosBorrosidad.add(new Point (1831,678));
        CentrosBorrosidad.add(new Point (1751,678));
        CentrosBorrosidad.add(new Point (1672,678));
        CentrosBorrosidad.add(new Point (1592,677));
        CentrosBorrosidad.add(new Point (1513,677));
        CentrosBorrosidad.add(new Point (1434,677));
        CentrosBorrosidad.add(new Point (1355,676));
        CentrosBorrosidad.add(new Point (1276,676));
        CentrosBorrosidad.add(new Point (1197,675));
        CentrosBorrosidad.add(new Point (1118,675));
        CentrosBorrosidad.add(new Point (1039,674));
        CentrosBorrosidad.add(new Point (960,674));
        CentrosBorrosidad.add(new Point (879,674));
        CentrosBorrosidad.add(new Point (800,673));
        CentrosBorrosidad.add(new Point (721,673));
        CentrosBorrosidad.add(new Point (643,672));
        CentrosBorrosidad.add(new Point (564,672));
        CentrosBorrosidad.add(new Point (485,672));
        CentrosBorrosidad.add(new Point (406,671));
        CentrosBorrosidad.add(new Point (327,670));
        CentrosBorrosidad.add(new Point (248,670));
        CentrosBorrosidad.add(new Point (169,670));
        CentrosBorrosidad.add(new Point (90,669));
        CentrosBorrosidad.add(new Point (2266,612));
        CentrosBorrosidad.add(new Point (2345,612));
        CentrosBorrosidad.add(new Point (2424,613));
        CentrosBorrosidad.add(new Point (2503,613));
        CentrosBorrosidad.add(new Point (2584,614));
        CentrosBorrosidad.add(new Point (2663,614));
        CentrosBorrosidad.add(new Point (2742,614));
        CentrosBorrosidad.add(new Point (2820,614));
        CentrosBorrosidad.add(new Point (2899,615));
        CentrosBorrosidad.add(new Point (2978,616));
        CentrosBorrosidad.add(new Point (3057,615));
        CentrosBorrosidad.add(new Point (3136,616));
        CentrosBorrosidad.add(new Point (3215,616));
        CentrosBorrosidad.add(new Point (3294,616));
        CentrosBorrosidad.add(new Point (3373,617));
        CentrosBorrosidad.add(new Point (3453,617));
        CentrosBorrosidad.add(new Point (2187,611));
        CentrosBorrosidad.add(new Point (2108,612));
        CentrosBorrosidad.add(new Point (2029,611));
        CentrosBorrosidad.add(new Point (1950,610));
        CentrosBorrosidad.add(new Point (1871,610));
        CentrosBorrosidad.add(new Point (1792,610));
        CentrosBorrosidad.add(new Point (1711,610));
        CentrosBorrosidad.add(new Point (1632,609));
        CentrosBorrosidad.add(new Point (1553,609));
        CentrosBorrosidad.add(new Point (1474,608));
        CentrosBorrosidad.add(new Point (1395,608));
        CentrosBorrosidad.add(new Point (1316,607));
        CentrosBorrosidad.add(new Point (1237,607));
        CentrosBorrosidad.add(new Point (1158,607));
        CentrosBorrosidad.add(new Point (1079,607));
        CentrosBorrosidad.add(new Point (1000,606));
        CentrosBorrosidad.add(new Point (921,605));
        CentrosBorrosidad.add(new Point (840,605));
        CentrosBorrosidad.add(new Point (761,605));
        CentrosBorrosidad.add(new Point (682,604));
        CentrosBorrosidad.add(new Point (604,604));
        CentrosBorrosidad.add(new Point (525,603));
        CentrosBorrosidad.add(new Point (446,603));
        CentrosBorrosidad.add(new Point (367,603));
        CentrosBorrosidad.add(new Point (288,602));
        CentrosBorrosidad.add(new Point (209,602));
        CentrosBorrosidad.add(new Point (130,601));
        CentrosBorrosidad.add(new Point (49,601));
        CentrosBorrosidad.add(new Point (2306,544));
        CentrosBorrosidad.add(new Point (2385,544));
        CentrosBorrosidad.add(new Point (2464,545));
        CentrosBorrosidad.add(new Point (2543,545));
        CentrosBorrosidad.add(new Point (2624,545));
        CentrosBorrosidad.add(new Point (2702,546));
        CentrosBorrosidad.add(new Point (2782,546));
        CentrosBorrosidad.add(new Point (2860,546));
        CentrosBorrosidad.add(new Point (2939,547));
        CentrosBorrosidad.add(new Point (3018,547));
        CentrosBorrosidad.add(new Point (3097,547));
        CentrosBorrosidad.add(new Point (3176,548));
        CentrosBorrosidad.add(new Point (3255,548));
        CentrosBorrosidad.add(new Point (3334,548));
        CentrosBorrosidad.add(new Point (3412,549));
        CentrosBorrosidad.add(new Point (2227,543));
        CentrosBorrosidad.add(new Point (2148,543));
        CentrosBorrosidad.add(new Point (2069,543));
        CentrosBorrosidad.add(new Point (1990,542));
        CentrosBorrosidad.add(new Point (1911,542));
        CentrosBorrosidad.add(new Point (1832,542));
        CentrosBorrosidad.add(new Point (1751,542));
        CentrosBorrosidad.add(new Point (1672,541));
        CentrosBorrosidad.add(new Point (1593,541));
        CentrosBorrosidad.add(new Point (1514,540));
        CentrosBorrosidad.add(new Point (1435,540));
        CentrosBorrosidad.add(new Point (1356,539));
        CentrosBorrosidad.add(new Point (1276,539));
        CentrosBorrosidad.add(new Point (1198,539));
        CentrosBorrosidad.add(new Point (1119,538));
        CentrosBorrosidad.add(new Point (1040,538));
        CentrosBorrosidad.add(new Point (961,537));
        CentrosBorrosidad.add(new Point (880,537));
        CentrosBorrosidad.add(new Point (801,537));
        CentrosBorrosidad.add(new Point (722,536));
        CentrosBorrosidad.add(new Point (643,536));
        CentrosBorrosidad.add(new Point (564,536));
        CentrosBorrosidad.add(new Point (485,535));
        CentrosBorrosidad.add(new Point (406,535));
        CentrosBorrosidad.add(new Point (328,534));
        CentrosBorrosidad.add(new Point (249,534));
        CentrosBorrosidad.add(new Point (170,533));
        CentrosBorrosidad.add(new Point (91,533));
        CentrosBorrosidad.add(new Point (2346,476));
        CentrosBorrosidad.add(new Point (2424,476));
        CentrosBorrosidad.add(new Point (2503,477));
        CentrosBorrosidad.add(new Point (2584,477));
        CentrosBorrosidad.add(new Point (2663,477));
        CentrosBorrosidad.add(new Point (2742,478));
        CentrosBorrosidad.add(new Point (2821,478));
        CentrosBorrosidad.add(new Point (2900,478));
        CentrosBorrosidad.add(new Point (2979,479));
        CentrosBorrosidad.add(new Point (3058,479));
        CentrosBorrosidad.add(new Point (3137,479));
        CentrosBorrosidad.add(new Point (3216,479));
        CentrosBorrosidad.add(new Point (3294,480));
        CentrosBorrosidad.add(new Point (3373,480));
        CentrosBorrosidad.add(new Point (3454,481));
        CentrosBorrosidad.add(new Point (2266,475));
        CentrosBorrosidad.add(new Point (2187,475));
        CentrosBorrosidad.add(new Point (2108,475));
        CentrosBorrosidad.add(new Point (2029,474));
        CentrosBorrosidad.add(new Point (1950,474));
        CentrosBorrosidad.add(new Point (1872,474));
        CentrosBorrosidad.add(new Point (1793,473));
        CentrosBorrosidad.add(new Point (1712,472));
        CentrosBorrosidad.add(new Point (1633,473));
        CentrosBorrosidad.add(new Point (1554,472));
        CentrosBorrosidad.add(new Point (1475,472));
        CentrosBorrosidad.add(new Point (1396,471));
        CentrosBorrosidad.add(new Point (1317,471));
        CentrosBorrosidad.add(new Point (1238,471));
        CentrosBorrosidad.add(new Point (1159,470));
        CentrosBorrosidad.add(new Point (1080,470));
        CentrosBorrosidad.add(new Point (1001,469));
        CentrosBorrosidad.add(new Point (922,469));
        CentrosBorrosidad.add(new Point (841,469));
        CentrosBorrosidad.add(new Point (762,468));
        CentrosBorrosidad.add(new Point (683,468));
        CentrosBorrosidad.add(new Point (604,467));
        CentrosBorrosidad.add(new Point (525,467));
        CentrosBorrosidad.add(new Point (446,467));
        CentrosBorrosidad.add(new Point (367,467));
        CentrosBorrosidad.add(new Point (288,466));
        CentrosBorrosidad.add(new Point (209,465));
        CentrosBorrosidad.add(new Point (130,465));
        CentrosBorrosidad.add(new Point (50,465));
        CentrosBorrosidad.add(new Point (2385,408));
        CentrosBorrosidad.add(new Point (2464,408));
        CentrosBorrosidad.add(new Point (2543,408));
        CentrosBorrosidad.add(new Point (2624,409));
        CentrosBorrosidad.add(new Point (2702,409));
        CentrosBorrosidad.add(new Point (2782,410));
        CentrosBorrosidad.add(new Point (2861,410));
        CentrosBorrosidad.add(new Point (2940,410));
        CentrosBorrosidad.add(new Point (3018,411));
        CentrosBorrosidad.add(new Point (3097,411));
        CentrosBorrosidad.add(new Point (3176,411));
        CentrosBorrosidad.add(new Point (3255,412));
        CentrosBorrosidad.add(new Point (3334,412));
        CentrosBorrosidad.add(new Point (3413,412));
        CentrosBorrosidad.add(new Point (2306,408));
        CentrosBorrosidad.add(new Point (2227,407));
        CentrosBorrosidad.add(new Point (2148,407));
        CentrosBorrosidad.add(new Point (2069,406));
        CentrosBorrosidad.add(new Point (1990,407));
        CentrosBorrosidad.add(new Point (1911,406));
        CentrosBorrosidad.add(new Point (1832,405));
        CentrosBorrosidad.add(new Point (1752,405));
        CentrosBorrosidad.add(new Point (1673,405));
        CentrosBorrosidad.add(new Point (1594,404));
        CentrosBorrosidad.add(new Point (1515,404));
        CentrosBorrosidad.add(new Point (1436,404));
        CentrosBorrosidad.add(new Point (1357,403));
        CentrosBorrosidad.add(new Point (1277,403));
        CentrosBorrosidad.add(new Point (1198,402));
        CentrosBorrosidad.add(new Point (1119,402));
        CentrosBorrosidad.add(new Point (1040,402));
        CentrosBorrosidad.add(new Point (961,401));
        CentrosBorrosidad.add(new Point (881,401));
        CentrosBorrosidad.add(new Point (802,400));
        CentrosBorrosidad.add(new Point (723,400));
        CentrosBorrosidad.add(new Point (644,400));
        CentrosBorrosidad.add(new Point (565,399));
        CentrosBorrosidad.add(new Point (486,399));
        CentrosBorrosidad.add(new Point (407,399));
        CentrosBorrosidad.add(new Point (328,398));
        CentrosBorrosidad.add(new Point (250,398));
        CentrosBorrosidad.add(new Point (170,397));
        CentrosBorrosidad.add(new Point (91,397));
        CentrosBorrosidad.add(new Point (2424,338));
        CentrosBorrosidad.add(new Point (2504,339));
        CentrosBorrosidad.add(new Point (2585,339));
        CentrosBorrosidad.add(new Point (2664,339));
        CentrosBorrosidad.add(new Point (2742,339));
        CentrosBorrosidad.add(new Point (2821,340));
        CentrosBorrosidad.add(new Point (2901,340));
        CentrosBorrosidad.add(new Point (2979,340));
        CentrosBorrosidad.add(new Point (3058,341));
        CentrosBorrosidad.add(new Point (3137,341));
        CentrosBorrosidad.add(new Point (3216,341));
        CentrosBorrosidad.add(new Point (3295,342));
        CentrosBorrosidad.add(new Point (3374,342));
        CentrosBorrosidad.add(new Point (3455,343));
        CentrosBorrosidad.add(new Point (2346,338));
        CentrosBorrosidad.add(new Point (2267,337));
        CentrosBorrosidad.add(new Point (2188,337));
        CentrosBorrosidad.add(new Point (2109,337));
        CentrosBorrosidad.add(new Point (2030,337));
        CentrosBorrosidad.add(new Point (1951,336));
        CentrosBorrosidad.add(new Point (1872,336));
        CentrosBorrosidad.add(new Point (1793,335));
        CentrosBorrosidad.add(new Point (1713,335));
        CentrosBorrosidad.add(new Point (1633,335));
        CentrosBorrosidad.add(new Point (1554,334));
        CentrosBorrosidad.add(new Point (1475,333));
        CentrosBorrosidad.add(new Point (1397,333));
        CentrosBorrosidad.add(new Point (1317,333));
        CentrosBorrosidad.add(new Point (1238,333));
        CentrosBorrosidad.add(new Point (1159,332));
        CentrosBorrosidad.add(new Point (1080,332));
        CentrosBorrosidad.add(new Point (1002,332));
        CentrosBorrosidad.add(new Point (923,331));
        CentrosBorrosidad.add(new Point (842,331));
        CentrosBorrosidad.add(new Point (763,330));
        CentrosBorrosidad.add(new Point (684,330));
        CentrosBorrosidad.add(new Point (605,329));
        CentrosBorrosidad.add(new Point (526,329));
        CentrosBorrosidad.add(new Point (447,329));
        CentrosBorrosidad.add(new Point (368,328));
        CentrosBorrosidad.add(new Point (289,328));
        CentrosBorrosidad.add(new Point (210,327));
        CentrosBorrosidad.add(new Point (131,327));
        CentrosBorrosidad.add(new Point (51,327));
        CentrosBorrosidad.add(new Point (2465,270));
        CentrosBorrosidad.add(new Point (2544,271));
        CentrosBorrosidad.add(new Point (2625,271));
        CentrosBorrosidad.add(new Point (2704,271));
        CentrosBorrosidad.add(new Point (2783,271));
        CentrosBorrosidad.add(new Point (2861,272));
        CentrosBorrosidad.add(new Point (2940,272));
        CentrosBorrosidad.add(new Point (3019,273));
        CentrosBorrosidad.add(new Point (3098,273));
        CentrosBorrosidad.add(new Point (3177,273));
        CentrosBorrosidad.add(new Point (3255,273));
        CentrosBorrosidad.add(new Point (3335,274));
        CentrosBorrosidad.add(new Point (3414,274));
        CentrosBorrosidad.add(new Point (2386,270));
        CentrosBorrosidad.add(new Point (2307,269));
        CentrosBorrosidad.add(new Point (2228,269));
        CentrosBorrosidad.add(new Point (2149,268));
        CentrosBorrosidad.add(new Point (2070,269));
        CentrosBorrosidad.add(new Point (1991,268));
        CentrosBorrosidad.add(new Point (1912,268));
        CentrosBorrosidad.add(new Point (1833,267));
        CentrosBorrosidad.add(new Point (1752,267));
        CentrosBorrosidad.add(new Point (1673,267));
        CentrosBorrosidad.add(new Point (1594,266));
        CentrosBorrosidad.add(new Point (1515,266));
        CentrosBorrosidad.add(new Point (1436,265));
        CentrosBorrosidad.add(new Point (1357,265));
        CentrosBorrosidad.add(new Point (1278,265));
        CentrosBorrosidad.add(new Point (1199,264));
        CentrosBorrosidad.add(new Point (1121,264));
        CentrosBorrosidad.add(new Point (1041,263));
        CentrosBorrosidad.add(new Point (962,263));
        CentrosBorrosidad.add(new Point (882,263));
        CentrosBorrosidad.add(new Point (803,262));
        CentrosBorrosidad.add(new Point (723,262));
        CentrosBorrosidad.add(new Point (645,261));
        CentrosBorrosidad.add(new Point (566,261));
        CentrosBorrosidad.add(new Point (487,261));
        CentrosBorrosidad.add(new Point (408,260));
        CentrosBorrosidad.add(new Point (329,260));
        CentrosBorrosidad.add(new Point (250,259));
        CentrosBorrosidad.add(new Point (171,259));
        CentrosBorrosidad.add(new Point (92,258));
        CentrosBorrosidad.add(new Point (2505,202));
        CentrosBorrosidad.add(new Point (2586,202));
        CentrosBorrosidad.add(new Point (2664,203));
        CentrosBorrosidad.add(new Point (2743,203));
        CentrosBorrosidad.add(new Point (2822,203));
        CentrosBorrosidad.add(new Point (2901,204));
        CentrosBorrosidad.add(new Point (2980,204));
        CentrosBorrosidad.add(new Point (3059,204));
        CentrosBorrosidad.add(new Point (3138,205));
        CentrosBorrosidad.add(new Point (3217,205));
        CentrosBorrosidad.add(new Point (3296,206));
        CentrosBorrosidad.add(new Point (3375,206));
        CentrosBorrosidad.add(new Point (3455,206));
        CentrosBorrosidad.add(new Point (2426,202));
        CentrosBorrosidad.add(new Point (2347,201));
        CentrosBorrosidad.add(new Point (2268,201));
        CentrosBorrosidad.add(new Point (2189,200));
        CentrosBorrosidad.add(new Point (2110,200));
        CentrosBorrosidad.add(new Point (2031,200));
        CentrosBorrosidad.add(new Point (1952,199));
        CentrosBorrosidad.add(new Point (1873,199));
        CentrosBorrosidad.add(new Point (1794,199));
        CentrosBorrosidad.add(new Point (1713,198));
        CentrosBorrosidad.add(new Point (1634,198));
        CentrosBorrosidad.add(new Point (1555,197));
        CentrosBorrosidad.add(new Point (1476,197));
        CentrosBorrosidad.add(new Point (1397,197));
        CentrosBorrosidad.add(new Point (1319,197));
        CentrosBorrosidad.add(new Point (1239,196));
        CentrosBorrosidad.add(new Point (1160,196));
        CentrosBorrosidad.add(new Point (1081,195));
        CentrosBorrosidad.add(new Point (1002,195));
        CentrosBorrosidad.add(new Point (923,195));
        CentrosBorrosidad.add(new Point (842,194));
        CentrosBorrosidad.add(new Point (764,193));
        CentrosBorrosidad.add(new Point (685,193));
        CentrosBorrosidad.add(new Point (606,193));
        CentrosBorrosidad.add(new Point (527,192));
        CentrosBorrosidad.add(new Point (448,192));
        CentrosBorrosidad.add(new Point (369,192));
        CentrosBorrosidad.add(new Point (290,191));
        CentrosBorrosidad.add(new Point (211,191));
        CentrosBorrosidad.add(new Point (132,190));
        CentrosBorrosidad.add(new Point (51,190));
        CentrosBorrosidad.add(new Point (2545,134));
        CentrosBorrosidad.add(new Point (2626,134));
        CentrosBorrosidad.add(new Point (2704,135));
        CentrosBorrosidad.add(new Point (2783,135));
        CentrosBorrosidad.add(new Point (2862,135));
        CentrosBorrosidad.add(new Point (2941,135));
        CentrosBorrosidad.add(new Point (3020,136));
        CentrosBorrosidad.add(new Point (3099,136));
        CentrosBorrosidad.add(new Point (3178,137));
        CentrosBorrosidad.add(new Point (3257,137));
        CentrosBorrosidad.add(new Point (3335,137));
        CentrosBorrosidad.add(new Point (3414,138));
        CentrosBorrosidad.add(new Point (2465,134));
        CentrosBorrosidad.add(new Point (2386,133));
        CentrosBorrosidad.add(new Point (2307,133));
        CentrosBorrosidad.add(new Point (2228,132));
        CentrosBorrosidad.add(new Point (2149,132));
        CentrosBorrosidad.add(new Point (2070,132));
        CentrosBorrosidad.add(new Point (1991,131));
        CentrosBorrosidad.add(new Point (1912,131));
        CentrosBorrosidad.add(new Point (1833,131));
        CentrosBorrosidad.add(new Point (1753,130));
        CentrosBorrosidad.add(new Point (1674,130));
        CentrosBorrosidad.add(new Point (1595,129));
        CentrosBorrosidad.add(new Point (1516,129));
        CentrosBorrosidad.add(new Point (1437,129));
        CentrosBorrosidad.add(new Point (1358,128));
        CentrosBorrosidad.add(new Point (1279,128));
        CentrosBorrosidad.add(new Point (1199,128));
        CentrosBorrosidad.add(new Point (1120,127));
        CentrosBorrosidad.add(new Point (1041,127));
        CentrosBorrosidad.add(new Point (962,127));
        CentrosBorrosidad.add(new Point (882,126));
        CentrosBorrosidad.add(new Point (803,126));
        CentrosBorrosidad.add(new Point (724,125));
        CentrosBorrosidad.add(new Point (645,125));
        CentrosBorrosidad.add(new Point (566,125));
        CentrosBorrosidad.add(new Point (487,124));
        CentrosBorrosidad.add(new Point (408,124));
        CentrosBorrosidad.add(new Point (330,123));
        CentrosBorrosidad.add(new Point (251,123));
        CentrosBorrosidad.add(new Point (172,122));
        CentrosBorrosidad.add(new Point (93,122));
        CentrosBorrosidad.add(new Point (2586,66));
        CentrosBorrosidad.add(new Point (2665,66));
        CentrosBorrosidad.add(new Point (2744,67));
        CentrosBorrosidad.add(new Point (2823,67));
        CentrosBorrosidad.add(new Point (2902,67));
        CentrosBorrosidad.add(new Point (2981,68));
        CentrosBorrosidad.add(new Point (3060,68));
        CentrosBorrosidad.add(new Point (3138,68));
        CentrosBorrosidad.add(new Point (3217,69));
        CentrosBorrosidad.add(new Point (3296,69));
        CentrosBorrosidad.add(new Point (3375,69));
        CentrosBorrosidad.add(new Point (3456,70));
        CentrosBorrosidad.add(new Point (2505,66));
        CentrosBorrosidad.add(new Point (2426,65));
        CentrosBorrosidad.add(new Point (2347,65));
        CentrosBorrosidad.add(new Point (2268,64));
        CentrosBorrosidad.add(new Point (2189,64));
        CentrosBorrosidad.add(new Point (2111,63));
        CentrosBorrosidad.add(new Point (2032,63));
        CentrosBorrosidad.add(new Point (1952,63));
        CentrosBorrosidad.add(new Point (1873,63));
        CentrosBorrosidad.add(new Point (1795,62));
        CentrosBorrosidad.add(new Point (1714,62));
        CentrosBorrosidad.add(new Point (1635,61));
        CentrosBorrosidad.add(new Point (1556,61));
        CentrosBorrosidad.add(new Point (1477,61));
        CentrosBorrosidad.add(new Point (1398,60));
        CentrosBorrosidad.add(new Point (1319,60));
        CentrosBorrosidad.add(new Point (1240,60));
        CentrosBorrosidad.add(new Point (1161,59));
        CentrosBorrosidad.add(new Point (1082,59));
        CentrosBorrosidad.add(new Point (1003,58));
        CentrosBorrosidad.add(new Point (924,58));
        CentrosBorrosidad.add(new Point (843,58));
        CentrosBorrosidad.add(new Point (764,57));
        CentrosBorrosidad.add(new Point (685,57));
        CentrosBorrosidad.add(new Point (606,57));
        CentrosBorrosidad.add(new Point (527,56));
        CentrosBorrosidad.add(new Point (448,55));
        CentrosBorrosidad.add(new Point (369,55));
        CentrosBorrosidad.add(new Point (290,55));
        CentrosBorrosidad.add(new Point (211,54));
        CentrosBorrosidad.add(new Point (133,54));
        CentrosBorrosidad.add(new Point (52,54));

        ImgtoConvert.SegmentationbyROI(CentrosBorrosidad, 60);
        ImgtoConvert.ErodingImage();
        ImgtoConvert.getBIErode();
        ImgMorpho =  ImgtoConvert.MorfologicalImage();
    }
            
        File BW_File      =     new File("C:\\Resultados\\"+ this.OnlyNameFile+"\\" + "BW_" + this.OnlyNameFile + ".png");
        File Eroding_File =     new File("C:\\Resultados\\"+ this.OnlyNameFile+"\\" + "Eroding" + this.OnlyNameFile + ".png");
        File Morpho_File  =     new File("C:\\Resultados\\"+ this.OnlyNameFile+"\\" + "Morpho_" + this.OnlyNameFile + ".png");
        //RescaleOp op = new RescaleOp(-1.0f, 255f, null);
            
        try 
        { 
            ImageIO.write(ImgtoConvert.getBIBW(),"png",BW_File);
            ImageIO.write(ImgtoConvert.getBIErode(),"png",Eroding_File);
            ImageIO.write(ImgMorpho, "png",Morpho_File );
          
        } catch (IOException ex) {
            Logger.getLogger(ImageParallelization.class.getName()).log(Level.SEVERE, null, ex);
        }                               
                                
        g = ImgMorpho.createGraphics();
        g.setColor(Color.GREEN);

        // int rgb = ImgtoConvert.getBIBW().getRGB(1, 1);
        int rojo    = 0xFFFF0000;
        int verde  = 0xFF00FF00;
        int blanco = 0xFFFFFFFF;
        int magenta = 0xFFFF00FF;
            
        // System.out.println("FirstCenterFound : " + FirstCenterFound);
            
        HolesbyImage.addAll(DiagonalGeometryReplication (ImgMorpho, g, FirstCenterFound,1));
        HolesbyImage.addAll(DiagonalGeometryReplication (ImgMorpho, g, FirstCenterFound,0));
            
        time_end = System.currentTimeMillis();
        System.out.println("the task has taken "+ ( time_end - time_start ) +" milliseconds");
        System.out.println("Cantidad de Centros "+ HolesbyImage.size());
            
        FileWriter writer = null;
        try {
            writer = new FileWriter("C:\\Resultados\\" + this.OnlyNameFile+ "\\"+ this.OnlyNameFile +".csv");
            writer.append("Hole No.");
            writer.append(',');
            writer.append("Baricentro Interno");
            writer.append(',');
            writer.append("Radio Interno");
            writer.append(',');
            writer.append("Perimetro Interno ");
            writer.append(',');
            writer.append("Area Interna ");
            writer.append(',');
            writer.append("Circularidad Interna");      
            writer.append(',');
            writer.append("Baricentro Externo");
            writer.append(',');
            writer.append("Radio Externo");
            writer.append(',');
            writer.append("Perimetro Externo ");
            writer.append(',');
            writer.append("Area Externa ");
            writer.append(',');
            writer.append("Circularidad Externa");            
            writer.append(',');
            writer.append("Dist Baricentros");            
            writer.append('\n');            
        } catch (IOException ex) {
            Logger.getLogger(ImageParallelization.class.getName()).log(Level.SEVERE, null, ex);
        }
          
           
        for (int i = 0; i< HolesbyImage.size();i++)
        {
            //System.out.println ("C= " + HolesbyImage.get(i).getRoundness()+ "P= " + HolesbyImage.get(i).getBarycenter() + " D =  "+ HolesbyImage.get(i).getBarycenter().distance(HolesbyImage.get(i).getInnerPoint()));
            
            console.append("InnerPoint Holes("+i+"): "+ HolesbyImage.get(i).getInnerPoint()+"\n");
            console.append("BaryCenter Distance Holes("+i+"): "+ HolesbyImage.get(i).getBarycenter().distance(HolesbyImage.get(i).getInnerPoint())+"\n");
            console.append("Barycenter Holes("+i+") : "+HolesbyImage.get(i).getBarycenter()+"\n");
            console.append("Roundness Holes("+i+") : "+HolesbyImage.get(i).getRoundness()+"\n");
            
            
            //System.out.println (HolesbyImage.get(i).getInnerPoint());
            //System.out.println (HolesbyImage.get(i).getBarycenter().distance(HolesbyImage.get(i).getInnerPoint()));
            //System.out.println (HolesbyImage.get(i).getBarycenter());
            //System.out.println (HolesbyImage.get(i).getRoundness());
              
            if (HolesbyImage.get(i).getRoundness()==0)
                console.append("*******************************"+"\n");
            console.append("___________________"+"\n");
                
              //  System.out.println("CentrosBorrosidad.add(new Point ("+ HolesbyImage.get(i).getBarycenter().x+","+ HolesbyImage.get(i).getBarycenter().y+"));");
            if (HolesbyImage.get(i).getBarycenter().distance(HolesbyImage.get(i).getInnerPoint())>(15.71))
                   //System.out.println(HolesbyImage.get(i).getBarycenter());
                try {
                    writer.append(Integer.toString(i));
                    writer.append(',');
                    writer.append(Integer.toString(HolesbyImage.get(i).getBarycenter().x) +"-"+ Integer.toString(HolesbyImage.get(i).getBarycenter().y));
                    writer.append(',');
                    writer.append(Double.toString(HolesbyImage.get(i).getBarycenter().distance(HolesbyImage.get(i).getInnerPoint())));
                    writer.append(',');
                    writer.append(Double.toString(HolesbyImage.get(i).getInnerPerimeter()));
                    writer.append(',');
                    writer.append(Double.toString(HolesbyImage.get(i).getInnerArea()));
                    writer.append(',');
                    writer.append(Double.toString(HolesbyImage.get(i).getRoundness()));
                    writer.append(',');
                    writer.append(Integer.toString(HolesbyImage.get(i).getBarycenterouter().x) +"-"+ Integer.toString(HolesbyImage.get(i).getBarycenterouter().y));
                    writer.append(',');
                    writer.append(Double.toString(HolesbyImage.get(i).getBarycenterouter().distance(HolesbyImage.get(i).getOuterPoint())));
                    writer.append(',');
                    writer.append(Double.toString(HolesbyImage.get(i).getOuterPerimeter()));
                    writer.append(',');
                    writer.append(Double.toString(HolesbyImage.get(i).getOuterArea()));
                    writer.append(',');
                    writer.append(Double.toString(HolesbyImage.get(i).getRoundnessOuter()));
                    writer.append(',');
                    writer.append(Double.toString(HolesbyImage.get(i).getBarycenterouter().distance(HolesbyImage.get(i).getBarycenter())));
                    writer.append('\n');
                } catch (IOException ex) {
                    Logger.getLogger(ImageParallelization.class.getName()).log(Level.SEVERE, null, ex);
                }
           
            DesvCirc = 0.017205732 ;
            PromedioCirc= 0.636608296;
          
            DesvRad= 0.5231785;
            PromedioRad=12.35468031;
                 
       /*    if ((HolesbyImage.get(i).getBarycenter().distance(HolesbyImage.get(i).getInnerPoint())<(12.85)))
                        {
                        Rangor1 = Rangor1+1;
                        }
           if ((HolesbyImage.get(i).getBarycenter().distance(HolesbyImage.get(i).getOuterPoint())<(18.57)))
                        {
                        RangoR1 = RangoR1+1;
                        }             

            if (HolesbyImage.get(i).getRoundness()<0.47)
                        {
                        Rangoc1 = Rangoc1+1;
                        }
           if (HolesbyImage.get(i).getRoundnessOuter()<0.47)
                        {
                        RangoC1 = RangoC1+1;
                        }             
           
           
           

                 
           if (((HolesbyImage.get(i).getBarycenter().distance(HolesbyImage.get(i).getInnerPoint())>=(12.85)))
               && ((HolesbyImage.get(i).getBarycenter().distance(HolesbyImage.get(i).getInnerPoint())<=(14.28))))
                        {
                        Rangor2 = Rangor2+1;
                        }
           if (((HolesbyImage.get(i).getBarycenter().distance(HolesbyImage.get(i).getOuterPoint())>=(18.57)))
                   && ((HolesbyImage.get(i).getBarycenter().distance(HolesbyImage.get(i).getOuterPoint())<=(20))))
                        {
                        RangoR2 = RangoR2+1;
                        }             

            if ((HolesbyImage.get(i).getRoundness()>=0.47)&&(HolesbyImage.get(i).getRoundness()<=0.65))
                        {
                        Rangoc2 = Rangoc2+1;
                        }
           if ((HolesbyImage.get(i).getRoundnessOuter()>=0.47)&&(HolesbyImage.get(i).getRoundnessOuter()<=0.65))
                        {
                        RangoC2 = RangoC2+1;
                        }             
           
           
           
           //Rango entre
            if (((HolesbyImage.get(i).getBarycenter().distance(HolesbyImage.get(i).getInnerPoint())>(14.28)))
               && ((HolesbyImage.get(i).getBarycenter().distance(HolesbyImage.get(i).getInnerPoint())<=(15.71))))
                        {
                        Rangor3 = Rangor3+1;
                        }
           if (((HolesbyImage.get(i).getBarycenter().distance(HolesbyImage.get(i).getOuterPoint())>(20)))
                   && ((HolesbyImage.get(i).getBarycenter().distance(HolesbyImage.get(i).getOuterPoint())<=(21.42))))
                        {
                        RangoR3 = RangoR3+1;
                        }             

            if (HolesbyImage.get(i).getRoundness()>0.65)
                        {
                        Rangoc3 = Rangoc3+1;
                        }
           if (HolesbyImage.get(i).getRoundnessOuter()>0.65)
                        {
                        RangoC3 = RangoC3+1;
                        }             
           
         
           
           
           
           //Rango Mayor a 
           if ((HolesbyImage.get(i).getBarycenter().distance(HolesbyImage.get(i).getInnerPoint())>(15.71)))
                                       {
                        Rangor4 = Rangor4+1;
                        }
           if ((HolesbyImage.get(i).getBarycenter().distance(HolesbyImage.get(i).getOuterPoint())>(21.42)))
    
                        {
                        RangoR4 = RangoR4+1;
                        }  */
          
           
           
            
            ImgtoConvert.getBIColor().setRGB(HolesbyImage.get(i).getBarycenter().x, HolesbyImage.get(i).getBarycenter().y, magenta);
   
            
       
         //   System.out.println("Cantidad de puntos "  + HolesbyImage.get(i).getInnerHolePoints().size());
               
            
            
            for(int x=0;x< HolesbyImage.get(i).getInnerHolePoints().size();x++)
            {
               //  System.out.println("COORDENADAS "+HolesbyImage.get(i).getInnerHolePoints().get(x).x+","+ HolesbyImage.get(i).getInnerHolePoints().get(x).y);
                 
                //  if  (HolesbyImage.get(i).getRoundness()<0.628165591)
                if ((((HolesbyImage.get(i).getBarycenter().distance(HolesbyImage.get(i).getInnerPoint())>(12)))
                     || (HolesbyImage.get(i).getBarycenter().distance(HolesbyImage.get(i).getInnerPoint())<(10.1)))   
                     || ((HolesbyImage.get(i).getRoundness())<(0.55)))
                        ImgtoConvert.getBIColor().setRGB(HolesbyImage.get(i).getInnerHolePoints().get(x).x, HolesbyImage.get(i).getInnerHolePoints().get(x).y, rojo);
                else 
                      ImgtoConvert.getBIColor().setRGB(HolesbyImage.get(i).getInnerHolePoints().get(x).x, HolesbyImage.get(i).getInnerHolePoints().get(x).y, blanco);
            }
 
           /* Radio Interno Distancia
            for(int x=0;x< HolesbyImage.get(i).getInnerHolePoints().size();x++)
                {
                   
                    if (HolesbyImage.get(i).getBarycenter().distance(HolesbyImage.get(i).getInnerPoint())<(11.3))
                       {ImgtoConvert.getBIColor().setRGB(HolesbyImage.get(i).getInnerHolePoints().get(x).x, HolesbyImage.get(i).getInnerHolePoints().get(x).y, rojo);
                                    
                   }
                   else
                      ImgtoConvert.getBIColor().setRGB(HolesbyImage.get(i).getInnerHolePoints().get(x).x, HolesbyImage.get(i).getInnerHolePoints().get(x).y, blanco);
                }*/

            /*     for(int x=0;x< HolesbyImage.get(i).getOuterHolePoints().size();x++)
                {
                    if (((HolesbyImage.get(i).getBarycenterouter().distance(HolesbyImage.get(i).getOuterPoint())<(21.0)))||  ((HolesbyImage.get(i).getBarycenterouter().distance(HolesbyImage.get(i).getOuterPoint())>(24.0))))                 
//if ((HolesbyImage.get(i).getRoundnessOuter()< 0.445681608)||(HolesbyImage.get(i).getRoundnessOuter()< 0.600942623 ))
                      ImgtoConvert.getBIColor().setRGB(HolesbyImage.get(i).getOuterHolePoints().get(x).x, HolesbyImage.get(i).getOuterHolePoints().get(x).y, rojo);
                   else 
                      ImgtoConvert.getBIColor().setRGB(HolesbyImage.get(i).getOuterHolePoints().get(x).x, HolesbyImage.get(i).getOuterHolePoints().get(x).y, blanco);
                }*/

        }
        
        try {
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ImageParallelization.class.getName()).log(Level.SEVERE, null, ex);
        }

       File Y= new File("C:\\Resultados\\"+ this.OnlyNameFile+"\\" + "Contornos" + this.OnlyNameFile + ".png");
        try { 
            ImageIO.write(ImgtoConvert.getBIColor(),"png",Y );
        } catch (IOException ex) {
            Logger.getLogger(ImageParallelization.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
  
   /*System.out.println("Radios Internos < 45 Um = " + Rangor1);
    System.out.println("Radios Internos [45 - 50] Um = " + Rangor2);
    System.out.println("Radios Internos [50 - 55] Um = " + Rangor3);
    System.out.println("Radios Internos > 55 Um = " + Rangor4);
    
    System.out.println("-----------------------------------------------------------");
    
    System.out.println("Radios Externos < 65 micrometros = " + RangoR1);
    System.out.println("Radios Externos [65 - 70] Um = " + RangoR2);
    System.out.println("Radios Externos [70 - 75] Um = " + RangoR3);
    System.out.println("Radios Externos > 75 Um = " + RangoR4);
     
    System.out.println("-----------------------------------------------------------");   
    
   
    System.out.println("Circularidad Interna < 0.47 Um = " + Rangoc1);
    System.out.println("Circularidad Interna [0.47 - 0.65] Um = " + Rangoc2);
    System.out.println("Circularidad Interna > 0.65 Um = " + Rangoc3);
    
    System.out.println("-----------------------------------------------------------");
    
    System.out.println("Circularidad Externa < 0.6 Um = " + RangoC1);
    System.out.println("Circularidad Externa [0.47 - 0.65] Um = " + RangoC2);
    System.out.println("Circularidad Externa > 0.65 Um " + RangoC3);*/
}         

 
Point FindCenterPointbyImageCorrelation (BufferedImage biImg, int Xini, int Yini, int Xend, int Yend ) throws FileNotFoundException, IOException 
{
    Point Center = null;
    ImgtoCorr = new CorrelationImage();
    ImageInputStream stream = null;
    FileInputStream fin = new FileInputStream("src/co/edu/uan/images/Plantilla18.jpg");
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
    ImgtoCorr.findSubimage(biImg,Subimage);
    Center = new Point();
    Center = new Point (ImgtoCorr.getXCorrelation()+24,ImgtoCorr.getYCorrelation()+24); 
    System.out.println(Center.x + ","+ Center.y);
              
    ImgtoCorr.setXCorrelation(0);
    ImgtoCorr.setYCorrelation(0);
              
    fin.close();

    return Center;
}   


//Hole evaluate recibe el centro preliminar y busca el contorno del inner and outer hole
Holes HoleEvaluate (Point NewPoint, BufferedImage BImg )
{
    PixelOperation PixelOperationCalc = new PixelOperation ();
    Holes Hole;
    Hole = new Holes();
    Hole.setCenter(NewPoint);
    Hole.setInnerPoint(GettingInitContourPointRight(NewPoint, BImg, 1, 15));
    
    //Si no encuentra el punto inicial del contorno interno a la Izquierda o Derecha coloca en ceros los datos
    if ((Hole.getInnerPoint().x==0) && (Hole.getInnerPoint().y==0))
    {
        // System.out.println("Agujero en Coordenadas ( "+ NewPoint.x +","+ NewPoint.y+") no encontrado");
        Hole.setInnerHolePoints(new ArrayList ());
        Hole.setBarycenter(Hole.getCenter());
        Hole.setInnerPerimeter(0.0);
        Hole.setInnerArea(0.0);
        Hole.setRoundness(0.0);
    } else{   
        Hole.setInnerHolePoints(ExtractContour(Hole.getInnerPoint(), Hole.getCenter(),BImg));
        Hole.setBarycenter     (PixelOperationCalc.BarycenterCalculate(Hole.getInnerHolePoints()));
        Hole.setInnerPerimeter (PixelOperationCalc.PerimeterCalculate2(Hole.getInnerHolePoints()));  
        Hole.setInnerArea      (PixelOperationCalc.PolygonArea(Hole.getInnerHolePoints()));
        Hole.setRoundness      (PixelOperationCalc.RoundnessCalculate (Hole.getInnerArea(),Hole.getInnerPerimeter()));
    }  
            //Despues de sacar los datos del hole interno, busca los datos del hole externo
    Hole.setOuterPoint     (GettingInitContourPointRight(NewPoint, BImg, (Math.abs(Hole.getCenter().x - Hole.getInnerPoint().x))+1, (Math.abs(Hole.getCenter().x - Hole.getInnerPoint().x))+15));
    Hole.setOuterHolePoints(ExtractContour(Hole.getOuterPoint(), Hole.getCenter(), BImg));
    Hole.setBarycenterouter(PixelOperationCalc.BarycenterCalculate(Hole.getOuterHolePoints()));
    Hole.setOuterPerimeter (PixelOperationCalc.PerimeterCalculate2(Hole.getOuterHolePoints()));  
    Hole.setOuterArea      (PixelOperationCalc.PolygonArea(Hole.getOuterHolePoints()));
    Hole.setRoundnessOuter (PixelOperationCalc.RoundnessCalculate (Hole.getOuterArea(),Hole.getOuterPerimeter()));
 
    return Hole;
}



/*      Replicación del Centro en Diagonal
        Point PointtoReply : Punto Inicial 
        int OptOrientation : Hacia donde se va a replicar 0: arriba 1 : abajo     */
ArrayList<Holes> DiagonalGeometryReplication (BufferedImage BImg, Graphics2D gRef, Point PointtoReply, int OptOrientation) 
{

    ArrayList<Holes> HolesbyImage;
    HolesbyImage = new ArrayList ();
    PixelOperation PixelOperationCalc = new PixelOperation ();
    Point NewPoint = new Point(); 

    int Down = BImg.getHeight() - 20; // 20 px es la distancia minimima del radio hasta el borde inferior de la imagen

    switch (OptOrientation)
    {
        case 0 : // Diagonal Hacia Arriba : Cuando es hacia arriba se calcula el siguiente en diagonal
        {
            NewPoint = new Point(); 
            NewPoint.x = (int) (PointtoReply.x  +  (cos (60 * (Math.PI/180.0F))*(80)));
            NewPoint.y = (int) (PointtoReply.y  -  (sin (60 * (Math.PI/180.0F))*(80)));
            break;
        }
    
        case 1 : // Diagonal Hacia Abajo : Cuando es hacia abajo se utiliza el primer punto como referencia
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
// System.out.println("New Point " + NewPoint);
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



ArrayList<Holes> HorizontalGeometryReplication (BufferedImage BImg, Graphics2D gRef, Point LastCenterFound, double CentersDistance, int WidthImage, int HeighImage, int OptOrientation) 
{

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
        
        if (Hole.getBarycenter().x <= (WidthImage-20))
        {
            xHolesbyImage.add(Hole);
            NewPoint = CalculateOrientationHorizontal (Hole.getBarycenter(), CentersDistance,OptOrientation);
        }
    }
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
        {
            break;
        } else{
            pColorCentroid =  new Color(biImage.getRGB((Centroide.x + xHor),Centroide.y));
            
            if ((pColorCentroid.getBlue()==255))
            {
                ContourInitPointRight = new Point((Centroide.x + xHor),Centroide.y);
                InitContournPoint.add(ContourInitPointRight);
            }
        }  
    }  

    if (ContourInitPointRight.x==0 && ContourInitPointRight.y==0)
    {
        ContourInitPointRight = GettingInitContourPointLeft(Centroide, biImage, xFrom, xTo);
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
        {
            break;
        }
    
        pColorCentroid =  new Color(biImage.getRGB((Centroide.x - xHor),Centroide.y));
        if ((pColorCentroid.getBlue()==255))
        {
            ContourPointLeft = new Point((Centroide.x - xHor),Centroide.y);
            InitContournPoint.add(ContourPointLeft);
        }  
    }  
    
    return ContourPointLeft;
}     

Comparator<Point> compQ1 = new Comparator<Point>()
{
    //Comparacion para el 1er cuadrante, cuando es X e Y son mayores que el centro preliminar
    @Override
    public int compare(Point P1, Point P2)
    {
        int result = new Integer(P2.x).compareTo(P1.x);
        if ( result == 0 ) 
        {
            result = new Integer(P2.y).compareTo(P1.y);
        } 
       
        return result;
     
    }
};

Comparator<Point> compQ2 = new Comparator<Point>()
{
    @Override
    public int compare(Point P1, Point P2)
    {
        int result = new Integer(P2.x).compareTo(P1.x);
        if (result==0)
        {
            result = new Integer(P1.y).compareTo(P2.y);
        }
            return result;
     
    }
};


Comparator<Point> compQ3 = new Comparator<Point>()
{
    @Override
    public int compare(Point P1, Point P2)
    {
        int result = new Integer(P1.x).compareTo(P2.x);
        if (result==0)
        {
            result = new Integer(P1.y).compareTo(P2.y);
        }
        
        return result;
     
    }
};



Comparator<Point> compQ4 = new Comparator<Point>()
{
    @Override
    public int compare(Point P1, Point P2)
    {
        int result = new Integer(P1.x).compareTo(P2.x);
        if (result==0)
        {
            result = new Integer(P2.y).compareTo(P1.y);
        }

        return result;
    }
};


 ArrayList<Point> ExtractContour (Point Centroide, Point PrelimCenter, BufferedImage biImage)
 {
    ArrayList<Point> FatherList = new ArrayList<Point> ();  
    ArrayList<Point> SonList = new ArrayList<Point> ();
    ArrayList<Point> RetirementList = new ArrayList<Point> ();
    ArrayList<Point> Quadrant1 =  new ArrayList<Point> (); 
    ArrayList<Point> Quadrant2 =  new ArrayList<Point> (); 
    ArrayList<Point> Quadrant3 =  new ArrayList<Point> (); 
    ArrayList<Point> Quadrant4 =  new ArrayList<Point> (); 
    //ArrayList<Point> TotalQuadrant =  new ArrayList<Point> (); 
           
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
            do{
                Point ListiniPointContour = new Point(SonList.get(0)); 
                RetirementList.addAll(FatherList);
                FatherList.clear();
                FatherList.add(ListiniPointContour);
                biImage.setRGB(ListiniPointContour.x, ListiniPointContour.y, 0);
                for(int i = (ListiniPointContour.x-1); i <= (ListiniPointContour.x+1); i++ )
                {
                    for(int j = (ListiniPointContour.y-1); j <= (ListiniPointContour.y+1); j++ )
                    {
                        Color pColorNeighbor =  new Color(biImage.getRGB(i,j));
                        if ((pColorNeighbor.getBlue()==255)&& ((ListiniPointContour.x != i) || (ListiniPointContour.y != j)))
                        {   
                            Point Son = new Point();
                            Son.x = i;
                            Son.y = j;
                            SonList.add(Son);
                            biImage.setRGB(Son.x, Son.y, 0);
                        }

                    }
                }
            
                SonList.remove(0);
            }while (SonList.size()> 0);  
        }
    }


            //System.out.println("preliminar center: " + PrelimCenter);
    for(int x=0;x< RetirementList.size();x++)
    {
        if((RetirementList.get(x).x >= PrelimCenter.x )&&((RetirementList.get(x).y <= PrelimCenter.y)))
        {
            Quadrant1.add(RetirementList.get(x));
        }
                
        if((RetirementList.get(x).x < PrelimCenter.x )&&((RetirementList.get(x).y <= PrelimCenter.y)))
        {
            Quadrant2.add(RetirementList.get(x));
        }
                
        if((RetirementList.get(x).x < PrelimCenter.x )&&((RetirementList.get(x).y > PrelimCenter.y)))
        {
            Quadrant3.add(RetirementList.get(x));
        }
                
        if((RetirementList.get(x).x >= PrelimCenter.x )&&((RetirementList.get(x).y > PrelimCenter.y)))
        {
            Quadrant4.add(RetirementList.get(x));
        //  System.out.println(RetirementList.get(x).x + "," + RetirementList.get(x).y );
        }
    }

    Collections.sort(Quadrant1,compQ1);
    Collections.sort(Quadrant2,compQ2);
    Collections.sort(Quadrant3,compQ3);
    Collections.sort(Quadrant4,compQ4);
/*   for(int x=0;x< Quadrant1.size();x++)
            {
                 System.out.println(Quadrant1.get(x).x + "," + Quadrant1.get(x).y );
            }
            System.out.println("******************************" ); 
           for(int x=0;x< Quadrant2.size();x++)
            {
                 System.out.println(Quadrant2.get(x).x + "," + Quadrant2.get(x).y );
            }
           System.out.println("******************************" ); 
           for(int x=0;x< Quadrant3.size();x++)
            {
                 System.out.println(Quadrant3.get(x).x + "," + Quadrant3.get(x).y );
            }
           System.out.println("******************************" ); 
           for(int x=0;x< Quadrant4.size();x++)
            {
                 System.out.println(Quadrant4.get(x).x + "," + Quadrant4.get(x).y );
            }*/
     
    RetirementList.clear();
    RetirementList.addAll(Quadrant1);
    RetirementList.addAll(Quadrant2);
    RetirementList.addAll(Quadrant3);
    RetirementList.addAll(Quadrant4);
     
   /*  System.out.println("-----------------------------------------" ); 
     for(int x=0;x< RetirementList.size();x++)
            {
                 System.out.println(RetirementList.get(x).x + "," + RetirementList.get(x).y );
            }
     */
     
           
     return RetirementList;
    }
 }