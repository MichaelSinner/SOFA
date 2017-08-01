/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uan.OS;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import SOFA.Sofa;


/**
 *
 * @author CA
 */
public class ConfigFileRW {
   
String Roudness,Concentricity,InnerRadius,OutterRadius, Angle, Pitch, DistanceInner,DistanceOuter,Cores,ThresholdValue;
String DefaultPathImages;

    
String InnerColor,OuterColor;

    public String getRoudness() {
        return Roudness;
    }

    public void setRoudness(String Roudness) {
        this.Roudness = Roudness;
    }

    public String getConcentricity() {
        return Concentricity;
    }

    public void setConcentricity(String Concentricity) {
        this.Concentricity = Concentricity;
    }

    public String getInnerRadius() {
        return InnerRadius;
    }

    public void setInnerRadius(String InnerRadius) {
        this.InnerRadius = InnerRadius;
    }

    public String getOutterRadius() {
        return OutterRadius;
    }

    public void setOutterRadius(String OutterRadius) {
        this.OutterRadius = OutterRadius;
    }

    public String getAngle() {
        return Angle;
    }

    public void setAngle(String Angle) {
        this.Angle = Angle;
    }

    public String getPitch() {
        return Pitch;
    }

    public void setPitch(String Pitch) {
        this.Pitch = Pitch;
    }

    public String getDistanceInner() {
        return DistanceInner;
    }

    public void setDistanceInner(String DistanceInner) {
        this.DistanceInner = DistanceInner;
    }

    public String getDistanceOuter() {
        return DistanceOuter;
    }

    public void setDistanceOuter(String DistanceOuter) {
        this.DistanceOuter = DistanceOuter;
    }

    public String getCores() {
        return Cores;
    }

    public void setCores(String Cores) {
        this.Cores = Cores;
    }

    public String getThresholdValue() {
        return ThresholdValue;
    }

    public void setThresholdValue(String ThresholdValue) {
        this.ThresholdValue = ThresholdValue;
    }

   

    public String getInnerColor() {
        return InnerColor;
    }

    public void setInnerColor(String InnerColor) {
        this.InnerColor = InnerColor;
    }

    public String getOuterColor() {
        return OuterColor;
    }

    public void setOuterColor(String OuterColor) {
        this.OuterColor = OuterColor;
    }

public String getDefaultPathImages() {
        return DefaultPathImages;
    }

    public void setDefaultPathImages(String DefaultPathImages) {
        this.DefaultPathImages = DefaultPathImages;
    }
    
    public void SavePropertiesConfig() throws IOException
    {
                File configFile;
                configFile = new File("C:\\SOFA\\Config.properties");
                FileWriter writer = new FileWriter(configFile);
                Properties props = new Properties();
                props.setProperty("Roudness", this.getRoudness());
                props.setProperty("Concentricity", this.getConcentricity());
                
                props.setProperty("Cores",  this.getCores());
                
                props.setProperty("DistanceInner", this.getDistanceInner());
                props.setProperty("DistanceOuter", this.getDistanceOuter());
                
                props.setProperty("InnerColor", this.getInnerColor());
                props.setProperty("OuterColor", this.getOuterColor());
                
                props.setProperty("InnerRadius", this.getInnerRadius());
                props.setProperty("OutterRadius", this.getOutterRadius());
                
                props.setProperty("Pitch", this.getPitch());
                props.setProperty("Angle", this.getAngle());
                props.setProperty("ThresholdValue", "0");

                 props.setProperty("DefaultPathImages",this.getDefaultPathImages());

                props.store(writer, "SOFA Configuration");
               writer.close();
               
    }
    
    
    
    public void LoadPropertiesConfig() throws IOException
    {
    
        

        
          
      
                Properties props = new Properties();
                String fileName = "C:\\SOFA\\Config.properties";
                InputStream is = new FileInputStream(fileName);
               
                props.load(is);
                
                this.setAngle(props.getProperty("Angle"));
                this.setConcentricity(props.getProperty("Concentricity"));
                this.setCores(props.getProperty("Cores"));
                this.setDistanceInner(props.getProperty("DistanceInner"));
                this.setDistanceOuter(props.getProperty("DistanceOuter"));
                this.setInnerColor(props.getProperty("InnerColor"));
                this.setOuterColor(props.getProperty("OuterColor"));
                this.setInnerRadius(props.getProperty("InnerRadius"));
                this.setOutterRadius(props.getProperty("OutterRadius"));
                this.setPitch(props.getProperty("Pitch"));
                this.setRoudness(props.getProperty("Roudness"));
                this.setThresholdValue(props.getProperty("ThresholdValue"));
                this.setThresholdValue(props.getProperty("Cores"));
                this.setDefaultPathImages(props.getProperty("DefaultPathImages"));
                
               // inputStream.close();
                props.clear();
          
     
      }
    
            
    }
