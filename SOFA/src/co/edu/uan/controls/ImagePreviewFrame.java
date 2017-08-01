/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uan.controls;

import java.awt.BorderLayout;

import java.awt.Container;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import SOFA.Sofa;

/**
 *
 * @author CA
 */
public class ImagePreviewFrame extends JFrame{
    
    private BufferedImage biToPeview;

    


    public ImagePreviewFrame(BufferedImage img) throws IOException {
        biToPeview = img;
        initComponents();
        Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setBounds(0,0,screenSize.width, screenSize.height);
        setIconImage(Toolkit.getDefaultToolkit().getImage(Sofa.class.getResource("/images/52.png")));
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void initComponents() throws IOException {
                            Container cp = getContentPane();
                            JPanel ImgPreview = new JPanel();
                            ImageIcon imageIcon = new ImageIcon(biToPeview); 
                               JLabel imagelabel = new JLabel(imageIcon);
                              JScrollPane JSPanel = new JScrollPane();
                              imagelabel.setIcon(imageIcon);
                              JSPanel.getViewport().add(imagelabel);
       
          
                              ImgPreview.removeAll();
                              ImgPreview.setLayout(new BorderLayout());
                              ImgPreview.add(JSPanel,BorderLayout.CENTER);
        
        
        
        
        
        
        
        
     /*   ImgPreview.setBackground(Color.RED);
             ImageIcon imageIcon = new ImageIcon(ImageIO.read(new File("C:\\Fotos_Foils\\contourn0.png"))); 
        JLabel imagelabel = new JLabel(imageIcon);
        imagelabel.setIcon(imageIcon);
        
       ImgPreview.removeAll();
       ImgPreview.add(imagelabel,BorderLayout.CENTER);
        
        */
        
        
        
        
        
        
                             
            
        cp.add(ImgPreview);
    }
}



