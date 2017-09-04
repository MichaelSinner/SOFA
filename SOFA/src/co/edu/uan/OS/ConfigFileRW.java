package co.edu.uan.OS;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;


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
        //configFile = new File("co/edu/uan/resources/Config.properties");
        
        
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
        String fileName = "Config.properties";
        File configFile = new File("C:\\SOFA\\Config.properties");
        InputStream inputStream = new FileInputStream(configFile);
        
        try {
            props.load(inputStream);
            //props.load(getClass().getResourceAsStream("C:\\SOFA\\Config.properties"));
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
        } catch (Exception e) {
            System.err.print("Error loading profiles "+fileName);
        }
            
        //String fileName = "Config.properties";
       // InputStream iss = ConfigFileRW.class.getClassLoader().getResourceAsStream(fileName);
        //InputStream is = new FileInputStream(fileName);


        //props.load(is);

        
         
        /**
        //-- "/co/edu/uan/OS/ConfigFileRW
        Properties props = new Properties();
        String fileName = "Config.properties";
        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
        
        if(is!=null)
        {
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
            is.close();
            props.clear();
        }else{
            throw new FileNotFoundException("Cant Load "+fileName+" in SOFA");
        }
    */    
        /* 
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                    prop.load(inputStream);
            } else {
                    throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            Date time = new Date(System.currentTimeMillis());

            prop.load(inputStream);
            this.setAngle(prop.getProperty("Angle"));
            this.setConcentricity(prop.getProperty("Concentricity"));
            this.setCores(prop.getProperty("Cores"));
            this.setDistanceInner(prop.getProperty("DistanceInner"));
            this.setDistanceOuter(prop.getProperty("DistanceOuter"));
            this.setInnerColor(prop.getProperty("InnerColor"));
            this.setOuterColor(prop.getProperty("OuterColor"));
            this.setInnerRadius(prop.getProperty("InnerRadius"));
            this.setOutterRadius(prop.getProperty("OutterRadius"));
            this.setPitch(prop.getProperty("Pitch"));
            this.setRoudness(prop.getProperty("Roudness"));
            this.setThresholdValue(prop.getProperty("ThresholdValue"));
            this.setThresholdValue(prop.getProperty("Cores"));
            this.setDefaultPathImages(prop.getProperty("DefaultPathImages"));




            System.out.println("Results properties loaded : "+time);
            inputStream.close();
    } catch (Exception e) {
            System.out.println("Exception: " + e);
    } finally {
           
    }
        
        
    */
        

       
      }
    
            
    }
