/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SOFA;


import co.edu.uan.OS.ConfigFileRW;
import co.edu.uan.controls.TabbedPaneConf;
import co.edu.uan.parallelization.ImageParallelization;
import co.edu.uan.processing.CorrelationImage;
import co.edu.uan.processing.ImagetoBWparameter;
import static java.awt.Adjustable.VERTICAL;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author DCA_3
 */

public class Sofa extends JFrame 
{
    private JPanel pColorIndicators,pPreview,pCheckfiles,pImage,pResults, pResultsDown, pCores, pThread, pConfDefects,pSettingList;
    private Container cntMain;
    ConfigFileRW LoadSettingValue = new ConfigFileRW();
    JMenuBar menuBar;
    JTextArea  JTResult ;

    ImagetoBWparameter ImgtoConvert;
    private CorrelationImage ImgtoCorr;
    JCheckBox FileSelected[] ;
    JScrollPane scroll, scrollCheckFiles ;
    JScrollBar vbar;
    JLabel LabelSelected[][] ;
    
    public Sofa()
    {
        super("Quality Assurance in GEM Foils ");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponents();
    }
    
    private void initComponents() 
    {   
       // ConfigFileRW LoadSettingValue = new ConfigFileRW();
        try {
                LoadSettingValue.LoadPropertiesConfig();
            } catch (IOException ex) {
                Logger.getLogger(Sofa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setBounds(0,0,screenSize.width, screenSize.height);
        setIconImage(Toolkit.getDefaultToolkit().getImage(Sofa.class.getResource("/co/edu/uan/images/IconApplication.png")));
        
        ImageIcon iconZoomin  = new ImageIcon(this.getClass().getResource("/co/edu/uan/images/ZoomIn.png"));
        ImageIcon iconZoomout = new ImageIcon(this.getClass().getResource("/co/edu/uan/images/Zoomout.png"));
        ImageIcon iconScreen  = new ImageIcon(this.getClass().getResource("/co/edu/uan/images/ResizeImage.png"));
        ImageIcon iconExecute = new ImageIcon(this.getClass().getResource("/co/edu/uan/images/Run.png"));
        
        JButton btnZoomin   = new JButton(null,iconZoomin);
        JButton btnZoomout  = new JButton(null,iconZoomout);
        JButton btnScreen   = new JButton(null,iconScreen);
        JButton btnRun      = new JButton(null,iconExecute);
        
        btnZoomout.setEnabled(false);
        btnZoomin.setEnabled(false);
        btnScreen.setEnabled(false);
        
        pThread          = new JPanel();
        pCheckfiles      = new JPanel();
        pSettingList     = new JPanel();
        pPreview         = new JPanel();
        pColorIndicators = new JPanel();
        
        pImage           = new JPanel();
        pResults         = new JPanel();
        pResultsDown     = new JPanel();
        pCores           = new JPanel();
        pConfDefects     = new JPanel();

       
        
        JLabel lblColorNoImage = new JLabel("Not Load");
        lblColorNoImage.setOpaque(true);
        lblColorNoImage.setBackground(Color.ORANGE);
        
        JLabel lblColorToAnalize = new JLabel("to analize");
        lblColorToAnalize.setOpaque(true);
        lblColorToAnalize.setBackground(Color.CYAN);
        
        JLabel lblColorNoFinished = new JLabel("Unsuccessfully");
        lblColorNoFinished.setOpaque(true);
        lblColorNoFinished.setBackground(Color.RED);

        JLabel lblColorsuccessfully = new JLabel("Successfully");
        lblColorsuccessfully.setOpaque(true);
        lblColorsuccessfully.setBackground(Color.GREEN);
        
        JLabel lbltitleConcentricity = new JLabel("Concentricity (<) : ");
        JLabel lblValueConcentricity = new JLabel(LoadSettingValue.getConcentricity());
        JLabel lbltitleRoudness      = new JLabel("Roudness      (>) : ");
        JLabel lblValueRoudness      = new JLabel(LoadSettingValue.getRoudness());
            
        lbltitleConcentricity.setFont(new Font("sans-serif", Font.PLAIN, 12));
        lblValueConcentricity.setFont(new Font("sans-serif", Font.PLAIN, 12));
        lbltitleRoudness.setFont     (new Font("sans-serif", Font.PLAIN, 12));
        lblValueRoudness.setFont     (new Font("sans-serif", Font.PLAIN, 12));
       
        
        String[] NumofCPU        = getCpuNumbers(); 
        SpinnerListModel ListCPU = new SpinnerListModel(NumofCPU);
        JSpinner JSCPUs          = addLabeledSpinner(pThread,"Number of Cores",ListCPU);
        
    
        setUI();
        LoadMenu();
   
        JToolBar tBarVertical = new JToolBar();
                 tBarVertical.setOrientation(VERTICAL);
                 tBarVertical.setFloatable( false);
                 tBarVertical.addSeparator();
                 tBarVertical.add(btnScreen);
                 tBarVertical.add(btnZoomout);
                 tBarVertical.add(btnZoomin);
                 tBarVertical.addSeparator();
                 tBarVertical.add(btnRun);

        JCheckBox chboxConcentricity = new JCheckBox("Concentricity");
        chboxConcentricity.setSelected(true);  
        
        JCheckBox chboxRoudness = new JCheckBox("Roundness");
        //chboxRoudness.setSelected(true);  

        
       //********************SEARCH DEFECTIVE PANEL*******************************           
      pSettingList.setBorder(BorderFactory.createTitledBorder("Configured Values"));
      pSettingList.setLayout(new GridLayout(2, 2));
      pSettingList.setVisible(false);
      pSettingList.add(lbltitleConcentricity);
      pSettingList.add(lblValueConcentricity);
      pSettingList.add(lbltitleRoudness);
      pSettingList.add(lblValueRoudness);
      
      //********************SEARCH DEFECTIVE PANEL*******************************  
        
      //********************SEARCH DEFECTIVE PANEL*******************************           
      pConfDefects.setBorder(BorderFactory.createTitledBorder("Search Defectives Configurations"));
      pConfDefects.setLayout(new GridLayout(1, 1));
      pConfDefects.add(chboxConcentricity);
      pConfDefects.add(chboxRoudness);
      pConfDefects.setVisible(false);
     
      //********************SEARCH DEFECTIVE PANEL*******************************
      
      
      
      //********************CHECK FILES TO PROJECT *******************************           
     
         pCheckfiles.setBorder(new TitledBorder(new EtchedBorder(),"Files in Project"));
         
         JScrollPane scrollCheckbox = new JScrollPane(pCheckfiles);
         scrollCheckbox.setBorder(null);


      //********************SEARCH DEFECTIVE PANEL*******************************
  
      
      
        //********************CORES PANEL*******************************
        //pCores.setLayout(new BoxLayout(pCores, BoxLayout.PAGE_AXIS));
        pCores.setLayout(new GridLayout(1, 4));
        pCores.setBorder(BorderFactory.createTitledBorder("Thread"));
        pCores.setVisible(false);
        //********************FIN CORES PANEL*******************************
     
        
        
         //********************IMAGE PANEL*******************************
        pPreview.setBorder(new TitledBorder(new EtchedBorder(),"GEM Foil (Preview)"));
        pPreview.setLayout(new BoxLayout(pPreview,BoxLayout.PAGE_AXIS));
        pPreview.setPreferredSize(new Dimension((80*screenSize.width)/100, (80*screenSize.height)/100));
        
        
        
        //********************IMAGE PANEL*******************************
        //pImage.setBorder(new TitledBorder(new EtchedBorder(),"GEM Foil (Preview)"));
        pImage.setLayout(new GridLayout(24, 18));
        pImage.setPreferredSize(new Dimension((80*screenSize.width)/100, (80*screenSize.height)/100));
        
        LabelSelected = new JLabel[24][18]; 
        Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);
      
        for (int row=23;row>=0;row--)
        {
          for (int Column=17;Column>=0;Column--)
            {
                pImage.add(LabelSelected[row][Column] = new JLabel(""));
                LabelSelected[row][Column].setBorder(border);
                LabelSelected[row][Column].setOpaque(true);
                LabelSelected[row][Column].setBackground(Color.ORANGE);
                //LabelSelected[row][Column].setText(Integer.toString(row)+","+Integer.toString(Column));
                LabelSelected[row][Column].setText(": : :");
                LabelSelected[row][Column].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                LabelSelected[row][Column].setName(Integer.toString(row));
                LabelSelected[row][Column].setHorizontalAlignment(SwingConstants.CENTER);
                LabelSelected[row][Column].setVerticalAlignment(SwingConstants.CENTER);
                LabelSelected[row][Column].addMouseListener(new java.awt.event.MouseAdapter() {
                
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                       
                       JLabel label = (JLabel)evt.getSource();
                       JTResult.append(label.getName()+"\n");
                        
                    }
                } );              

           
        

            }}
  
        pPreview.add(pImage);
        pColorIndicators.add(lblColorNoImage);
        pColorIndicators.add(lblColorToAnalize);
        pColorIndicators.add(lblColorNoFinished);
        pColorIndicators.add(lblColorsuccessfully);
        
       
        pPreview.add(pColorIndicators);

//********************FIN IMAGE PANEL*******************************


        //********************RESULT PANEL*******************************
        pResults.setLayout(new BoxLayout(pResults, BoxLayout.PAGE_AXIS));
        pResults.add(scrollCheckbox);
        pResults.add(pSettingList);
        pResults.add(pConfDefects);
        pResults.add(pThread);
        pResults.add(pCores);
        pResults.setPreferredSize(new Dimension((20*screenSize.width)/100, screenSize.height - tBarVertical.getHeight()));
        //pResults.setBorder(BorderFactory.createTitledBorder("Settings and Results Panel "));
//********************RESULT PANEL*******************************
        

        JTResult = new JTextArea ();
        DefaultCaret caret = (DefaultCaret)JTResult.getCaret();
        caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
        JTResult.setEditable(false);
        JTResult.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        JTResult.setBackground(Color.GRAY);
        JTResult.setForeground(Color.WHITE);
        scroll = new JScrollPane();
        scroll.add(JTResult);
        scroll.setViewportView(JTResult);
        pResultsDown.setPreferredSize(new Dimension(screenSize.width, (15*screenSize.height)/100));
        pResultsDown.setLayout(new BorderLayout());
        pResultsDown.setBorder(new TitledBorder(new EtchedBorder(), "Output "));
        pResultsDown.add(scroll);

        pThread.setBorder(BorderFactory.createTitledBorder("Performance"));
        pThread.add(JSCPUs);
        JSCPUs.setEditor(new JSpinner.DefaultEditor(JSCPUs));
        pThread.setVisible(false);
    
//*********************CONTAINER PRINCIPAL******************************
       cntMain = getContentPane();
       cntMain.setLayout(new BorderLayout());
       cntMain.add(menuBar, BorderLayout.PAGE_START);
       cntMain.add(tBarVertical, BorderLayout.LINE_START);
       cntMain.add(pPreview, BorderLayout.CENTER);
       cntMain.add(pResults, BorderLayout.LINE_END);
       cntMain.add(pResultsDown, BorderLayout.PAGE_END);
       
//********************* END PRINCIPAL******************************

        iconZoomin.getImage().flush();
        iconZoomout.getImage().flush();
        iconExecute.getImage().flush();
        setVisible(true);
    
//******************LISTENERS EVENTS*******************************************
    
        JSCPUs.addChangeListener(new javax.swing.event.ChangeListener() 
        {
            
            @Override
            public void stateChanged(ChangeEvent e) 
            {
                pCores.removeAll();
                JProgressBar m[]; 
                m = new JProgressBar[Integer.parseInt(JSCPUs.getValue().toString())];
                for (int i = 0; i < m.length; i++) 
                {
                    m[i]=new JProgressBar();
                    JLabel lblProgress = new JLabel (" "+(i+1) + " (Ready)" );
                    pCores.add(lblProgress);
                    pCores.add(m[i]);
                    setVisible(true);
                }
             }
        });
        
        btnRun.addActionListener(new java.awt.event.ActionListener()
        {
                   
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                   try {
                       RunActionPerformed(evt);
                   } catch (IOException ex) {
                       Logger.getLogger(Sofa.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (InterruptedException ex) {
                       Logger.getLogger(Sofa.class.getName()).log(Level.SEVERE, null, ex);
                   }
            }
       });
        
    } 
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    protected void setUI()
    {
        Font f = new Font("sans-serif", Font.PLAIN, 12);
        UIManager.put("Button.font", f);
        UIManager.put("ToggleButton.font", f);
        UIManager.put("RadioButton.font", f);
        UIManager.put("CheckBox.font", f);
        UIManager.put("ColorChooser.font", f);
        UIManager.put("ComboBox.font", f);
        UIManager.put("Label.font", f);
        UIManager.put("List.font", f);
        UIManager.put("MenuBar.font", f);
        UIManager.put("MenuItem.font", f);
        UIManager.put("RadioButtonMenuItem.font", f);
        UIManager.put("CheckBoxMenuItem.font", f);
        UIManager.put("Menu.font", f);
        UIManager.put("PopupMenu.font", f);
        UIManager.put("OptionPane.font", f);
        UIManager.put("Panel.font", f);
        UIManager.put("ProgressBar.font", f);
        UIManager.put("ScrollPane.font", f);
        UIManager.put("Viewport.font", f);
        UIManager.put("TabbedPane.font", f);
        UIManager.put("Table.font", f);
        UIManager.put("TableHeader.font", f);
        UIManager.put("TextField.font", f);
        UIManager.put("PasswordField.font", f);
        UIManager.put("TextArea.font", f);
        UIManager.put("TextPane.font", f);
        UIManager.put("EditorPane.font", f);
        UIManager.put("TitledBorder.font", f);
        UIManager.put("ToolBar.font", f);
        UIManager.put("ToolTip.font", f);
        UIManager.put("Tree.font", f);
    }
    

    public void LoadMenu()
    {   
        JMenu menu;
        JMenuItem menuItem;
        JCheckBoxMenuItem cbMenuItem;
        menuBar = new JMenuBar();

//*****************************MENU FILE**************************************************//       
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(menu);

        menuItem = new JMenuItem("New Project...",new ImageIcon(this.getClass().getResource("/co/edu/uan/images/OpenFile.png")));
        menuItem.setMnemonic(KeyEvent.VK_F);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        menu.add(menuItem);
        menu.addSeparator();
        menuItem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            try {
                FileOpen(evt);
            } catch (IOException ex) {
                Logger.getLogger(Sofa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }});
       //----------------------------------------------------------------------------------
        menuItem = new JMenuItem("Open Project...", new ImageIcon(this.getClass().getResource("/co/edu/uan/images/OpenFile.png")));
        menuItem.setMnemonic(KeyEvent.VK_F);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        menu.add(menuItem);
       //----------------------------------------------------------------------
        menu.addSeparator();
       //----------------------------------------------------------------------
        menuItem = new JMenuItem("Save",new ImageIcon(this.getClass().getResource("/co/edu/uan/images/SaveResult.png")));
        menuItem.setEnabled(false);
        menuItem.setMnemonic(KeyEvent.VK_F2);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
            private void SaveActionPerformed(ActionEvent evt) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        menu.add(menuItem);
            
//----------------------------------------------------------------------
        menuItem = new JMenuItem("Save As...",new ImageIcon(this.getClass().getResource("/co/edu/uan/images/LoadResult.png")));
        menuItem.setEnabled(false);
        menuItem.setMnemonic(KeyEvent.VK_F3);
            //menuItem.setAccelerator(KeyStroke.getKeyStroke (KeyEvent.VK_F3, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(new java.awt.event.ActionListener() {    
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                SaveasActionPerformed(evt);
            }
            private void SaveasActionPerformed(ActionEvent evt) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        menu.add(menuItem);
        menu.addSeparator();
//----------------------------------------------------------------------
        menuItem = new JMenuItem("Exit",new ImageIcon(this.getClass().getResource("/co/edu/uan/images/Exit.png")));
        menuItem.setMnemonic(KeyEvent.VK_F10);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10, ActionEvent.ALT_MASK));
        menuItem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            try {
                    ExitActionPerformed(evt);
                } catch (IOException ex) {
                    Logger.getLogger(Sofa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        menu.add(menuItem);

 //******************************FIN MENU FILE*************************************************//

//*******************************MENU EDIT************************************************//
        menu = new JMenu("Edit");
        menu.setMnemonic(KeyEvent.VK_E);
        menu.getAccessibleContext().setAccessibleDescription("");
        menuBar.add(menu);
//*****************************FIN MENU EDIT**************************************************//


//********************************MENU VIEW***********************************************//
        menu = new JMenu("View");
        menu.setMnemonic(KeyEvent.VK_V);
//----------------------------------------------------------------------
        cbMenuItem = new JCheckBoxMenuItem("Show Settings & Results Panel");
        cbMenuItem.setMnemonic(KeyEvent.VK_C);
        cbMenuItem.addItemListener(new java.awt.event.ItemListener() 
        {
           @Override
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()) {
                    case 1:  pResults.setVisible(true);
                             break;
                    case 2:  pResults.setVisible(false);
                             break;}
                }});

        menu.add(cbMenuItem);
//----------------------------------------------------------------------
        cbMenuItem = new JCheckBoxMenuItem("Show Cores Status");
        cbMenuItem.setSelected(false);
        cbMenuItem.setMnemonic(KeyEvent.VK_C);
        cbMenuItem.addItemListener(new java.awt.event.ItemListener() 
         {
            @Override
             public void itemStateChanged(ItemEvent e) {
                 switch (e.getStateChange()) {
                     case 1:  pCores.setVisible(true);
                              break;
                     case 2:  pCores.setVisible(false);
                              break;}
                 }});
        menu.add(cbMenuItem);
             
//----------------------------------------------------------------------
           
//----------------------------------------------------------------------
        cbMenuItem = new JCheckBoxMenuItem("Show Defective Search");
        cbMenuItem.setSelected(true);
        cbMenuItem.setMnemonic(KeyEvent.VK_C);
        cbMenuItem.addItemListener(new java.awt.event.ItemListener() 
        {
            @Override
             public void itemStateChanged(ItemEvent e) {
                 switch (e.getStateChange()) {
                     case 1:  pConfDefects.setVisible(true);
                              break;
                     case 2:  pConfDefects.setVisible(false);
                              break;}
                 }
        });
        menu.add(cbMenuItem);
             
//---------------------------------------------------------------------- 
             
//----------------------------------------------------------------------
        cbMenuItem = new JCheckBoxMenuItem("Show Configured Values");
        cbMenuItem.setSelected(false);
        cbMenuItem.addItemListener(new java.awt.event.ItemListener() 
        {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()) {
                    case 1:  pSettingList.setVisible(true);
                            break;
                    case 2:  pSettingList.setVisible(false);
                            break;}
                }
        });
        menu.add(cbMenuItem);
         
//----------------------------------------------------------------------  

        cbMenuItem = new JCheckBoxMenuItem("Show Cores");
        cbMenuItem.setSelected(true);
        cbMenuItem.addItemListener(new java.awt.event.ItemListener() 
        {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()) {
                    case 1:  pThread.setVisible(true);
                            break;
                    case 2:  pThread.setVisible(false);
                            break;}
                }
        });
            
        menu.add(cbMenuItem);
        menuBar.add(menu);
//********************************FIN MENU VIEW***********************************************//


//**************************MENU TOOLS *****************************************************//
        menu = new JMenu("Tools");
        menu.setMnemonic(KeyEvent.VK_T);
        menuBar.add(menu);

//----------------------------------------------------------------------
        menuItem = new JMenuItem("Options", null);
        menuItem.setMnemonic(KeyEvent.VK_O);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10, ActionEvent.ALT_MASK));
        menu.add(menuItem);
        menuItem.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    OptionActionPerformed(evt);
                } catch (IOException ex) {
                    Logger.getLogger(Sofa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
//**************************FIN MENU TOOLS********************************************//

//**************************MENU HELP *****************************************************//
        menu = new JMenu("Help");
        menu.setMnemonic(KeyEvent.VK_H);
        menuBar.add(menu);
//**************************FIN MENU HELP *****************************************************//

    }
     
//---------------------- Get CPU Numbers Cores ----------------------------------------------------
    
    protected String[] getCpuNumbers() 
    {
        int processors = Runtime.getRuntime().availableProcessors();
        String[] AmountCPU = new String[processors] ;
        for (int i=1;i<=processors;i++)
        {
            AmountCPU[i-1]= Integer.toString(i);
        }
        return AmountCPU;
    }
    
    static protected JSpinner addLabeledSpinner(Container c, String label, SpinnerModel model) 
    {
        JLabel l = new JLabel(label);
        l.setFont((new Font("Crystal", Font.PLAIN, 12)));
        c.add(l);
        JSpinner spinner = new JSpinner(model);
        spinner.setFont((new Font("Crystal", Font.BOLD, 12)));

        l.setLabelFor(spinner);
        c.add(spinner);
        return spinner;
    }
//-------------------------------- Boton de Salir 
    private void ExitActionPerformed(java.awt.event.ActionEvent evt) throws IOException 
    {                                         
        System.exit(0);
    } 
    
    private void OptionActionPerformed(java.awt.event.ActionEvent evt) throws IOException 
    {                                         
        TabbedPaneConf PanelConf = new TabbedPaneConf();
    } 
    
    
    private void RunActionPerformed(java.awt.event.ActionEvent evt) throws IOException, InterruptedException 
    {                                         
    // TODO add your handling code heroute:

        long startTime   = System.currentTimeMillis();
        long elapsedTime = 0 ;
        JTResult.setText("");
        JScrollBar vbar = scroll.getVerticalScrollBar();
        long init = System.currentTimeMillis(); 
        BufferedImage BIimage = new BufferedImage(3488, 2616,BufferedImage.TYPE_INT_RGB); 
        
        if ((FileSelected.length<=0)||(FileSelected==null))
        {
            JOptionPane.showMessageDialog(null, "No existen archivos seleccionados", "SOFA", 1);
        } else{
    
            for (int checkIndex=0;checkIndex<=FileSelected.length-1;checkIndex++)
            {
                if (FileSelected[checkIndex].isSelected())
                {
                    JTResult.append("Processing Image " + (checkIndex+1) + "/" + FileSelected.length + "\n");
                    vbar.setValue(vbar.getMaximum()); 
                    vbar.paint(vbar.getGraphics());
                    JTResult.scrollRectToVisible(JTResult.getVisibleRect());
                    JTResult.paint(JTResult.getGraphics());
                    FileInputStream fin = new FileInputStream(LoadSettingValue.getDefaultPathImages()+"\\"+FileSelected[checkIndex].getText());
                    ImageInputStream stream = ImageIO.createImageInputStream(fin);
                    ImageReader reader = ImageIO.getImageReaders(stream).next(); // TODO: Test hasNext()
                    reader.setInput(stream);
                    ImageReadParam param = reader.getDefaultReadParam();
                    param.setDestination(BIimage);
                    BIimage = reader.read(0, param);  
                    Thread thread = new Thread(new ImageParallelization(BIimage, init, FileSelected[checkIndex].getText()));
                    thread.start();
                    stream.close();
                    fin.close();
                    FileSelected[checkIndex].setForeground(Color.BLUE);
                } else {
                    JTResult.append("Unprocessing Image " + (checkIndex+1) + "\n");
                    FileSelected[checkIndex].setForeground(Color.RED);
                }
            
                FileSelected[checkIndex].setSelected(false);
            }
        }

        long EndTime = System.currentTimeMillis();
        elapsedTime = EndTime - startTime;
        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        JTResult.append("\n");   
        JTResult.append("JOB SUCCESSFUL  (Elapsed time: " + df.format(new Date(elapsedTime))+" )");   
    }  
   
  
    Point FindCenterPointbyImageCorrelation (BufferedImage biImg, int Xini, int Yini, int Xend, int Yend )
    {
        Point Center;
        ImgtoCorr = new CorrelationImage();
        BufferedImage Subimage = null;

        //Cargando en Memoria la plantilla...
        try {
            Subimage = ImageIO.read(new File("C:\\SOFA\\Plantilla18.png"));
        } catch (IOException ex) {
            Logger.getLogger(Sofa.class.getName()).log(Level.SEVERE, null, ex);
        } 
          
        /*Hole encontrado*/
        ImgtoCorr.setXini(Xini);
        ImgtoCorr.setYini(Yini);
        ImgtoCorr.setXend(Xend);
        ImgtoCorr.setYend(Yend);
        ImgtoCorr.findSubimage(biImg,Subimage);
        Center = new Point (ImgtoCorr.getXCorrelation()+24,ImgtoCorr.getYCorrelation()+24); 
        biImg.flush();
        Subimage.flush();
        return Center;
    }
  
  
    private Point ExtractImageCordinate(String FileName, String ext, String subStrFile, String strSeprator )  
    {
        String result;
        result = FileName.replace(ext,"");
        result= result.replace(subStrFile, "");
        result = result.replace(strSeprator, ",");
        String[] parts = result.split(",");
        Point FoilCoord = new Point (Integer.valueOf(parts[0]),Integer.valueOf(parts[1]));
        JTResult.append(FoilCoord+"\n");
        return FoilCoord;
    }

  
    private void FileOpen(java.awt.event.ActionEvent evt) throws IOException 
    {                                         
    //  TODO add your handling code here:
        /*NewProjectFrame PanelConf = new NewProjectFrame();
        final JDialog frame = new JDialog(this, "Project Info", true);
        frame.getContentPane().add(PanelConf);
        Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        frame.setLocationRelativeTo(null); 
        frame.setLocation(screenSize.width/3,screenSize.height/3);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Sofa.class.getResource("/images/Settings.png")));
        frame.pack();
        frame.setVisible(true);*/
        File[] paths = null;
        int result = 0;
        int Index=0;
        Point FoilCoordinates;
        JTResult.setText("");
        JFileChooser fileChooser = new JFileChooser(LoadSettingValue.getDefaultPathImages());
        fileChooser.addChoosableFileFilter(new FilterPNG());
        fileChooser.addChoosableFileFilter(new FilterJPG());
        fileChooser.addChoosableFileFilter(new FilterTIFF());
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        result = fileChooser.showOpenDialog(this);
        paths  = fileChooser.getSelectedFiles();

        FileSelected = new JCheckBox[paths.length];
        pCheckfiles.removeAll();
        pCheckfiles.setLayout(new GridLayout(paths.length, 1));
  
        if (result == JFileChooser.APPROVE_OPTION) 
        {
            Arrays.sort( paths, new Comparator(){ 
                @Override
                public int compare(final Object o1, final Object o2){
                    return new Long(((File)o2).lastModified()).compareTo( new Long(((File)o1).lastModified()));
                }
            }); 

            for(File path:paths)
            {
                FileSelected[Index] = new JCheckBox(path.getName());
                FoilCoordinates = ExtractImageCordinate(path.getName(),".png","WE-042-","-");
                LabelSelected[FoilCoordinates.x][FoilCoordinates.y].setBackground(Color.CYAN);
                FileSelected[Index].setVisible(true);
                pCheckfiles.add(FileSelected[Index]);
                Index++;
             }
            
            setVisible(true);
        } 
    } 
    
    public static void main(String[] args)
    {
        new Sofa();
    }

}    

/*
    Filters PNG, TIFF, JPG
*/

class FilterPNG extends javax.swing.filechooser.FileFilter {
    @Override
    public boolean accept(File file) {
        String filename = file.getName();
        return filename.endsWith(".png");
    }
    @Override
    public String getDescription() {
        return "*.png";
    }
}

class FilterTIFF extends javax.swing.filechooser.FileFilter {
    @Override
    public boolean accept(File file) {
        String filename = file.getName();
        return filename.endsWith(".tiff");
    }
    @Override
    public String getDescription() {
        return "*.tiff";
    }
}


class FilterJPG extends javax.swing.filechooser.FileFilter {
    @Override
    public boolean accept(File file) {
        String filename = file.getName();
        return filename.endsWith(".jpg");
    }
    @Override
    public String getDescription() {
        return "*.jpg";
    }
}