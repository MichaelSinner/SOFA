/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;




import java.awt.GridLayout;

import java.io.IOException;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextField;

/**
 *
 * @author DCA_3
 */
public class NewProjectFrame extends JPanel {

     JTextField   edtNameProject,edtPathProject;
     JLabel       lblSpace,lblNameProject,lblPathProject;
     JButton      btnOpenPath;
     
    public NewProjectFrame() throws IOException {
        {
         setLayout(new GridLayout(2, 3));
         lblSpace       =          new JLabel(" ");
         lblNameProject =          new JLabel(" Project Name ");
         lblPathProject =          new JLabel(" Path ");
         btnOpenPath    =          new JButton("...");
         edtNameProject =          new JTextField("");
         edtPathProject =          new JTextField("");
        
         
         add(lblNameProject);
         add(edtNameProject);
         add(lblSpace);
         
         add(lblPathProject);
         add(edtPathProject);
         add(btnOpenPath);
         
         
           
         
     
        
    }
     
   
     
    }    
 
 
}