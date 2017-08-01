/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uan.controls;


import co.edu.uan.OS.ConfigFileRW;
import co.edu.uan.OS.OSSpecification;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;


import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;


import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import javax.swing.JTextField;

import SOFA.Sofa;



/**
 *
 * @author DCA_3
 */
public class TabbedPaneConf extends JFrame {
    
     
     JTextField   edtRoudness,edtConcentricity,edtInnerRadius,edtOutterRadius,edtInnerContourColor,edtAngle,edtCores;
     JTextField   edtPitch,edtDistanceInner,edtDistanceOuter,edtOuterContourColor,edtDefaultPath;
     JLabel       lblRoudness,lblConcentricity ,lblInnerRadius,lblOutterRadius,lblAngle ,lblPitch,lblDistanceInner;
     JLabel       lblInnerContourColor,lblOuterContourColor,lblDistanceOuter,lblCores, lblDefaultPath;
     ImageIcon    iconDefective,iconGeometry,iconContour,iconPerformance, iconOS;
     JPanel       pnlDefective,pnlGeometry,pnlContour,pnlPerformance, pnlOSConfiguration;   
     
     
     private void LoadValues() throws IOException
     {
         ConfigFileRW ConfigFile = new ConfigFileRW();
         ConfigFile.LoadPropertiesConfig();
         edtRoudness.setText(ConfigFile.getRoudness());
         edtConcentricity.setText(ConfigFile.getConcentricity());
         edtInnerRadius.setText(ConfigFile.getInnerRadius());
         edtOutterRadius.setText(ConfigFile.getOutterRadius());
         edtAngle.setText(ConfigFile.getAngle());
         edtPitch.setText(ConfigFile.getPitch());
         edtDistanceInner.setText(ConfigFile.getDistanceInner());
         edtDistanceOuter.setText(ConfigFile.getDistanceOuter());
         edtCores.setText(ConfigFile.getCores());
         edtOuterContourColor.setBackground(new Color(Integer.parseInt(ConfigFile.getOuterColor())));
         edtInnerContourColor.setBackground(new Color(Integer.parseInt(ConfigFile.getInnerColor())));
         edtDefaultPath.setText((ConfigFile.getDefaultPathImages()));
         
         ConfigFile = null;
     }
    
     
      void initComponents()
     {
         OSSpecification OSCores = new OSSpecification();
         
         iconDefective = new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sofa.class.getResource("/images/Defects.png")));
         iconGeometry = new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sofa.class.getResource("/images/Geometry.png")));
         iconContour = new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sofa.class.getResource("/images/Contour.png")));
         iconPerformance = new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sofa.class.getResource("/images/Performance.png")));
         iconOS = new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sofa.class.getResource("/images/Settings.png")));

  
         JButton btnInnerColor = new JButton("...");
         JButton btnOuterColor = new JButton("...");
         
         lblRoudness =          new JLabel(" Roudness ");
         lblConcentricity =     new JLabel(" Concentricity ");
         lblInnerRadius =       new JLabel(" InnerRadius ");
         lblOutterRadius =      new JLabel(" OutterRadius ");
         lblAngle =             new JLabel(" Angle ");
         lblPitch =             new JLabel(" Pitch ");
         lblDistanceInner =     new JLabel(" Distance to Inner ");
         lblDistanceOuter =     new JLabel(" Distance to Outer ");
         lblInnerContourColor = new JLabel(" Inner Contour Color ");
         lblOuterContourColor = new JLabel(" Outer Contour Color ");
         lblCores =             new JLabel(" Cores " );
         lblDefaultPath =       new JLabel(" Image Default Path " );
         JLabel lblMaxCores =   new JLabel(" (Availables Cores)  " + Integer.toString(Runtime.getRuntime().availableProcessors()));
                
         edtRoudness =          new JTextField();
         edtConcentricity =     new JTextField();
         edtInnerRadius =       new JTextField();
         edtOutterRadius =      new JTextField();
         edtAngle =             new JTextField();
         edtPitch =             new JTextField();
         edtDistanceInner =     new JTextField();
         edtDistanceOuter =     new JTextField();
         edtOuterContourColor = new JTextField();
         edtInnerContourColor = new JTextField();
         edtCores             = new JTextField();
         edtDefaultPath       = new JTextField();
                 
         edtOuterContourColor.setEnabled(false);
         edtInnerContourColor.setEnabled(false);
         
         pnlDefective = new JPanel();
         pnlDefective.setLayout(new GridLayout(2, 1));
         pnlDefective.add(lblRoudness);
         pnlDefective.add(edtRoudness);
         pnlDefective.add(lblConcentricity);
         pnlDefective.add(edtConcentricity);
         pnlDefective.add(lblInnerRadius);
         pnlDefective.add(edtInnerRadius);
         pnlDefective.add(lblOutterRadius);
         pnlDefective.add(edtOutterRadius);
         
         pnlGeometry = new JPanel();
         pnlGeometry.setLayout(new GridLayout(2, 1));
         pnlGeometry.add(lblAngle);
         pnlGeometry.add(edtAngle);
         pnlGeometry.add(lblPitch);
         pnlGeometry.add(edtPitch);
         pnlGeometry.add(lblDistanceInner);
         pnlGeometry.add(edtDistanceInner);
         pnlGeometry.add(lblDistanceOuter);
         pnlGeometry.add(edtDistanceOuter);
         
         pnlContour= new JPanel();
         pnlContour.setLayout(new GridLayout(2, 1));
         pnlContour.add(lblInnerContourColor);
         pnlContour.add(edtInnerContourColor);
         pnlContour.add(btnInnerColor);
         pnlContour.add(lblOuterContourColor);
         pnlContour.add(edtOuterContourColor);
         pnlContour.add(btnOuterColor);
         
         pnlPerformance= new JPanel();
         pnlPerformance.setLayout(new GridLayout(2, 1));
         pnlPerformance.add(lblCores);
         pnlPerformance.add(edtCores);
         pnlPerformance.add(lblMaxCores);
         
         pnlOSConfiguration = new JPanel();
         pnlOSConfiguration.setLayout(new GridLayout(2, 1));
         pnlOSConfiguration.add(lblDefaultPath);
         pnlOSConfiguration.add(edtDefaultPath);
         
         OSCores=null;
         
         /*Button Click()*/
         btnInnerColor.addActionListener(new java.awt.event.ActionListener() 
        { public void actionPerformed(java.awt.event.ActionEvent evt) {
                    innerColorActionPerformed(evt);}});
         
         btnOuterColor.addActionListener(new java.awt.event.ActionListener() 
        { public void actionPerformed(java.awt.event.ActionEvent evt) {
                    OuterColorActionPerformed(evt);}});
         
      }
     
     
    public TabbedPaneConf() throws IOException {
       
        initComponents();
        LoadValues();
        
        JButton btnSave = new JButton("Save");
        JButton btnDiscard = new JButton("Discard");
         
        setLayout(new BorderLayout());
        
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Defect", iconDefective, pnlDefective,"Change Defective Criterias");
        tabbedPane.addTab("Geometry", iconGeometry, pnlGeometry,"Change Geometry Criterias");
        tabbedPane.addTab("Contour", iconContour, pnlContour,"Change Configuration Contour");
        tabbedPane.addTab("Performance", iconPerformance, pnlPerformance,"Computer Performance");
        tabbedPane.addTab("OS configuration", iconOS, pnlOSConfiguration,"OS Configuration");

        
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
        tabbedPane.setMnemonicAt(4, KeyEvent.VK_5);
        
        JPanel pnlButtons = new JPanel();
        pnlButtons.setLayout(new FlowLayout());
        pnlButtons.add(btnSave);
        pnlButtons.add(btnDiscard);
   
        add(tabbedPane,BorderLayout.CENTER);
        add(pnlButtons,BorderLayout.PAGE_END);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setLocationRelativeTo(null); 
        setLocation(screenSize.width/3,screenSize.height/3);
        setIconImage(Toolkit.getDefaultToolkit().getImage(Sofa.class.getResource("/images/Settings.png")));
        setTitle("Settings");
        pack();
        setResizable(false);
        setVisible(true);
        

        btnSave.addActionListener(new java.awt.event.ActionListener() 
        {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    try {SaveActionPerformed(evt);
                    } catch (IOException ex) {
                        Logger.getLogger(TabbedPaneConf.class.getName()).log(Level.SEVERE, null, ex);
                    }}
                   });
        
          btnDiscard.addActionListener(new java.awt.event.ActionListener() 
        {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    try {DiscardActionPerformed(evt);
                    } catch (IOException ex) {
                        Logger.getLogger(TabbedPaneConf.class.getName()).log(Level.SEVERE, null, ex);
                    }}
                   });
    }
     
   
private void DiscardActionPerformed(ActionEvent evt) throws IOException {
       LoadValues();
        }            
     
 private void SaveActionPerformed(ActionEvent evt) throws IOException {
        ConfigFileRW ConfigFile = new ConfigFileRW();
        ConfigFile.setRoudness( edtRoudness.getText()=="" ? "0.0" : edtRoudness.getText() );
        ConfigFile.setConcentricity(edtConcentricity.getText()=="" ? "0.0" : edtConcentricity.getText() );
        ConfigFile.setDistanceInner(edtDistanceInner.getText()=="" ? "0.0" : edtDistanceInner.getText() );
        ConfigFile.setDistanceOuter(edtDistanceOuter.getText()=="" ? "0.0" : edtDistanceOuter.getText() );
        ConfigFile.setInnerColor(Integer.toString(edtInnerContourColor.getBackground().getRGB()) =="" ? "(0,0,0)" : Integer.toString(edtInnerContourColor.getBackground().getRGB()) );
        ConfigFile.setOuterColor(Integer.toString(edtOuterContourColor.getBackground().getRGB()) =="" ? "(0,0,0)" : Integer.toString(edtOuterContourColor.getBackground().getRGB()) );
        ConfigFile.setInnerRadius(edtInnerRadius.getText()=="" ? "0.0" : edtInnerRadius.getText() );
        ConfigFile.setOutterRadius(edtOutterRadius.getText()=="" ? "0.0" : edtOutterRadius.getText() );
        ConfigFile.setPitch(edtPitch.getText()=="" ? "0.0" : edtPitch.getText() );
        ConfigFile.setAngle(edtAngle.getText()=="" ? "0.0" : edtAngle.getText() );
        ConfigFile.setCores(edtAngle.getText()=="" ? "1"   : edtCores.getText() );
        ConfigFile.setDefaultPathImages(edtDefaultPath .getText()=="" ? ""   : edtDefaultPath.getText() );
        
        ConfigFile.SavePropertiesConfig();
        ConfigFile = null;
        setVisible(false); 
        dispose(); 
        }         
    
 
 private void OuterColorActionPerformed(ActionEvent evt) {

        Color background = JColorChooser.showDialog(null, "Outer Color Configuration", Color.BLUE); 
        edtOuterContourColor.setBackground(background);                }    
 
  private void innerColorActionPerformed(ActionEvent evt) {
               
        Color backgroundInner = JColorChooser.showDialog(null, "Inner Color Configuration", Color.BLUE); 
        edtInnerContourColor.setBackground(backgroundInner);      }
}