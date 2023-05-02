package gui;

import store.Store;
import store.Customer;
import store.Option;
import store.Computer;
import store.Order;

import javax.swing.JFrame;           // for main window
import javax.swing.JOptionPane;      // for standard dialogs
// import javax.swing.JDialog;          // for custom dialogs (for alternate About dialog)
import javax.swing.JMenuBar;         // row of menu selections
import javax.swing.JMenu;            // menu selection that offers another menu
import javax.swing.JMenuItem;        // menu selection that does something
import javax.swing.JToolBar;         // row of buttons under the menu
import javax.swing.JButton;          // regular button
import javax.swing.JToggleButton;    // 2-state button
import javax.swing.BorderFactory;    // manufacturers Border objects around buttons
import javax.swing.Box;              // to create toolbar spacer
import javax.swing.UIManager;        // to access default icons
import javax.swing.JLabel;           // text or image holder
import javax.swing.JTextField;       // text input box
import javax.swing.ImageIcon;        // holds a custom icon
import javax.swing.JComboBox;        // for selecting from lists
import javax.swing.SwingConstants;   // useful values for Swing method calls

import javax.imageio.ImageIO;        // loads an image from a file

import javax.swing.JFileChooser;     // File selection dialog
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;                 // opens a file
import java.io.FileReader;           // opens a file for reading
import java.io.BufferedReader;       // adds buffering to a FileReader
import java.io.FileWriter;           // opens a file for writing
import java.io.BufferedWriter;       // adds buffering to a FileWriter
import java.io.IOException;          // reports an error reading from a file

import java.awt.BorderLayout;        // layout manager for main window
import java.awt.FlowLayout;          // layout manager for About dialog

import java.awt.Color;               // the color of widgets, text, or borders
import java.awt.Font;                // rich text in a JLabel or similar widget
import java.awt.image.BufferedImage; // holds an image loaded from a file

import java.util.Arrays;             // for setAll

//Besides the added changes for P13, the rest of the code is made by Professor Rice (excluding the images)

public class MainWin extends JFrame {
    private final String NAME = "ELSA";
    private final String EXTENSION = "elsa";
    private final String VERSION = "0.5";
    private final String FILE_VERSION = "1.1";
    private final String MAGIC_COOKIE = "⮚Ě1şà⮘";
    private final String DEFAULT_STORE_NAME = "New " + NAME + " Store";

    public MainWin(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        
        // /////// ////////////////////////////////////////////////////////////////
        // M E N U

        JMenuBar menubar = new JMenuBar();
        
        JMenu     file       = new JMenu("File");
        JMenuItem anew       = new JMenuItem("New");
        JMenuItem open       = new JMenuItem("Open");
                  save       = new JMenuItem("Save");
                  saveAs     = new JMenuItem("Save As");
        JMenuItem quit       = new JMenuItem("Quit");

        JMenu     insert     = new JMenu("Insert");
        JMenuItem iCustomer  = new JMenuItem("Customer");
        JMenuItem iOption    = new JMenuItem("Option");
        JMenuItem iComputer  = new JMenuItem("Computer");
        JMenuItem iOrder     = new JMenuItem("Order");
        
        JMenu     view       = new JMenu("View");
        JMenuItem vCustomer  = new JMenuItem("Customers");
        JMenuItem vOption    = new JMenuItem("Options");
        JMenuItem vComputer  = new JMenuItem("Computers");
        JMenuItem vOrder     = new JMenuItem("Orders");
        
        JMenu     help       = new JMenu("Help");
        JMenuItem about      = new JMenuItem("About");
        
        anew     .addActionListener(event -> onNewClick());
        open     .addActionListener(event -> onOpenClick());
        save     .addActionListener(event -> onSaveClick());
        saveAs   .addActionListener(event -> onSaveAsClick());
        quit     .addActionListener(event -> onQuitClick());

        iCustomer.addActionListener(event -> onInsertCustomerClick());
        iOption  .addActionListener(event -> onInsertOptionClick());
        iComputer.addActionListener(event -> onInsertComputerClick());
        iOrder   .addActionListener(event -> onInsertOrderClick());

        vCustomer.addActionListener(event -> onViewClick(Record.CUSTOMER));
        vOption  .addActionListener(event -> onViewClick(Record.OPTION));
        vComputer.addActionListener(event -> onViewClick(Record.COMPUTER));
        vOrder   .addActionListener(event -> onViewClick(Record.ORDER));

        about.addActionListener(event -> onAboutClick());

        file.add(anew);
        file.add(open);
        file.add(save);
        file.add(saveAs);
        file.add(quit);
        insert.add(iCustomer);
        insert.add(iOption);
        insert.add(iComputer);
        insert.add(iOrder);
        view.add(vCustomer);
        view.add(vOption);
        view.add(vComputer);
        view.add(vOrder);
        help.add(about);
        
        menubar.add(file);
        menubar.add(insert);
        menubar.add(view);
        menubar.add(help);
        setJMenuBar(menubar);
 
        // ///////////// //////////////////////////////////////////////////////////
        // T O O L B A R
        JToolBar toolbar = new JToolBar("Store Controls");

        JButton anewButton  = new JButton(new ImageIcon("gui/photos/ANew.png"));
          anewButton.setActionCommand("Create a new ELSA file");
          anewButton.setToolTipText("Create a new ELSA file");
          anewButton.addActionListener(event -> onNewClick());
          toolbar.add(anewButton);
        
        JButton openButton  = new JButton(new ImageIcon("gui/photos/Open.png"));
          openButton.setActionCommand("Load data from selected file");
          openButton.setToolTipText("Load data from selected file");
          openButton.addActionListener(event -> onOpenClick());
          toolbar.add(openButton);
        
        saveButton  = new JButton(new ImageIcon("gui/photos/Save.png"));
          saveButton.setActionCommand("Save data to default file");
          saveButton.setToolTipText("Save data to default file");
          saveButton.addActionListener(event -> onSaveClick());
          toolbar.add(saveButton);
        
        saveAsButton  = new JButton(new ImageIcon("gui/photos/Save_as.png"));
          saveAsButton.setActionCommand("Save data to selected file");
          saveAsButton.setToolTipText("Save data to selected file");
          saveAsButton.addActionListener(event -> onSaveAsClick());
          toolbar.add(saveAsButton);
        
        toolbar.add(Box.createHorizontalStrut(25));

        JButton bAddCustomer = new JButton(new ImageIcon("gui/photos/Customer.png"));
          bAddCustomer.setActionCommand("Insert Customer");
          bAddCustomer.setToolTipText("Insert Customer");
          toolbar.add(bAddCustomer);
          bAddCustomer.addActionListener(event ->onInsertCustomerClick());

        JButton bAddOption = new JButton(new ImageIcon("gui/photos/Option.png"));
          bAddOption.setActionCommand("Insert Option");
          bAddOption.setToolTipText("Insert Option");
          toolbar.add(bAddOption);
          bAddOption.addActionListener(event -> onInsertOptionClick());

        JButton bAddComputer = new JButton(new ImageIcon("gui/photos/Computer.png"));
          bAddComputer.setActionCommand("Insert Computer");
          bAddComputer.setToolTipText("Insert Computer");
          toolbar.add(bAddComputer);
          bAddComputer.addActionListener(event -> onInsertComputerClick());
        
        JButton bAddOrder = new JButton(new ImageIcon("gui/photos/Order.png"));
          bAddOrder.setActionCommand("Insert Order");
          bAddOrder.setToolTipText("Insert Order");
          toolbar.add(bAddOrder);
          bAddOrder.addActionListener(event -> onInsertOrderClick());        
        toolbar.add(Box.createHorizontalStrut(25));

        JButton bViewCustomers = new JButton(new ImageIcon("gui/photos/AllCustomers.png"));
          bViewCustomers.setActionCommand("View Customer");
          bViewCustomers.setToolTipText("View Customers");
          toolbar.add(bViewCustomers);
          bViewCustomers.addActionListener(event ->onViewClick(Record.CUSTOMER));

        JButton bViewOptions = new JButton(new ImageIcon("gui/photos/AllOptions.png"));
          bViewOptions.setActionCommand("View Options");
          bViewOptions.setToolTipText("View Options");
          toolbar.add(bViewOptions);
          bViewOptions.addActionListener(event -> onViewClick(Record.OPTION));

        JButton bViewComputers = new JButton(new ImageIcon("gui/photos/AllComputers.png"));
          bViewComputers.setActionCommand("View Computers");
          bViewComputers.setToolTipText("View Computers");
          toolbar.add(bViewComputers);
          bViewComputers.addActionListener(event -> onViewClick(Record.COMPUTER));
        
        JButton bViewOrders = new JButton(new ImageIcon("gui/photos/AllOrders.png"));
          bViewOrders.setActionCommand("View Orders");
          bViewOrders.setToolTipText("View Orders");
          toolbar.add(bViewOrders);
          bViewOrders.addActionListener(event -> onViewClick(Record.ORDER));

        getContentPane().add(toolbar, BorderLayout.PAGE_START);
       
        
        // /////////////////////////// ////////////////////////////////////////////
        // D I S P L A Y
        JLabel display = new JLabel();
        display.setFont(new Font("SansSerif", Font.BOLD, 14));
        display.setVerticalAlignment(JLabel.TOP);

        JScrollPane scrollPane = new JScrollPane(display);

        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
        
        onNewClick(DEFAULT_STORE_NAME);
    }
    
    // Listeners

    // File I/O Methods

   protected void onNewClick() {onNewClick("");}
   protected void onNewClick(String name) { 
       if(name.isEmpty()) {
           name = JOptionPane.showInputDialog(this, "Store Name", DEFAULT_STORE_NAME);
           if(name.isEmpty()) name = DEFAULT_STORE_NAME;
       }
       store = new Store(name);
       onViewClick(Record.CUSTOMER);
       setDirty(false);
   }
   protected void onOpenClick() { 
        final JFileChooser fc = new JFileChooser(filename);  
        FileFilter elsaFiles = new FileNameExtensionFilter("ELSA files", EXTENSION);
        fc.addChoosableFileFilter(elsaFiles);            
        fc.setFileFilter(elsaFiles);                         
        
        int result = fc.showOpenDialog(this);               
        if (result == JFileChooser.APPROVE_OPTION) {         
            filename = fc.getSelectedFile();                 
            
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String magicCookie = br.readLine();
                if(!magicCookie.equals(MAGIC_COOKIE)) throw new RuntimeException("Not an ELSA file");
                String fileVersion = br.readLine();
                if(!fileVersion.equals(FILE_VERSION)) throw new RuntimeException("Incompatible ELSA file format");
                
                store = new Store(br);                      
                onViewClick(Record.CUSTOMER);                
                setDirty(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,"Unable to load " + filename + '\n' + e, 
                    "Failed", JOptionPane.ERROR_MESSAGE); 
             }
        }
    }

    protected void onSaveClick() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(MAGIC_COOKIE + '\n');
            bw.write(FILE_VERSION + '\n');
            store.save(bw);
            setDirty(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to write " + filename + '\n' + e,
                "Failed", JOptionPane.ERROR_MESSAGE); 
        }
        setDirty(false);
    }

    protected void onSaveAsClick() {
        final JFileChooser fc = new JFileChooser(filename);
        FileFilter elsaFiles = new FileNameExtensionFilter("ELSA files", EXTENSION);
        fc.addChoosableFileFilter(elsaFiles);        
        fc.setFileFilter(elsaFiles);                
        
        int result = fc.showSaveDialog(this);         
        if (result == JFileChooser.APPROVE_OPTION) {  
            filename = fc.getSelectedFile();          
            if(!filename.getAbsolutePath().endsWith("." + EXTENSION)) 
                filename = new File(filename.getAbsolutePath() + "." + EXTENSION);
            onSaveClick();                        
        }
    }

    protected void onQuitClick() {System.exit(0);}   
    
    protected String[] UnifiedDialog(String[] fields, String title, String iconFilename) {
        String[] result = null;
        
        ImageIcon icon = null;
        try {
            if(iconFilename != null) 
                icon = new ImageIcon(iconFilename);
        } catch(Exception e) {
        }

        Object[] widgets = new Object[3*fields.length];
                   
        for(int i=0; i<fields.length; ++i) {
            widgets[3*i] = new JLabel("<html><br>" + fields[i] + "</html>");
            widgets[3*i+1] = new JTextField();
            widgets[3*i+2] = new JButton("Select Image");
        }
        
        int button = JOptionPane.showConfirmDialog(this, widgets, title,
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, icon);
            
        if(button == JOptionPane.OK_OPTION) {
            result = new String[fields.length];
            for(int i=0; i<fields.length; ++i) {
                JTextField textField = (JTextField) widgets[3*i+1];
                result[i] = textField.getText();
            }
        } 
        return result;
    }
    
    protected void onInsertCustomerClick() {
        try {
            String[] result = UnifiedDialog(new String[]{"Name", "Email"},
                "New Customer", "gui/photos/Customer.png");
            
            if(result != null) {
                store.add(new Customer(result[0], result[1]));
                setDirty(true);
                onViewClick(Record.CUSTOMER);
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, e, "Customer Not Created", JOptionPane.ERROR_MESSAGE);
        }    
    }
            
    protected void onInsertOptionClick() { 
       try {
            String[] result = UnifiedDialog(new String[]{"Name", "Cost"},
                "New Option", "gui/photos/Option.png");
            
            if(result != null) {
                store.add(new Option(result[0], (long) (100.0 * Double.parseDouble(result[1]))));
                setDirty(true);
                onViewClick(Record.OPTION);
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, e, "Option Not Created", JOptionPane.ERROR_MESSAGE);
        }    
    }
            
    protected void onInsertComputerClick() {         
        ImageIcon icon = null;
        try {
             icon = new ImageIcon("gui/photos/Computer.png");
        } catch(Exception e) {
        }
        try {
            String[] result = UnifiedDialog(new String[]{"Computer Name", "Computer Model"},
                "New Computer", "gui/photos/Computer.png");
            if(result == null) return;

            Computer c = new Computer(result[0], result[1]);
 
            JComboBox<Object> cb = new JComboBox<>(store.options());
            int optionsAdded = 0; 
            while(true) {
                int button = JOptionPane.showConfirmDialog(this, cb, "Another Option?", 
                    JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, icon);
                if(button != JOptionPane.YES_OPTION) break;
                c.addOption((Option) cb.getSelectedItem());
                ++optionsAdded;
            }
            if(optionsAdded > 0) {
                store.add(c);
                onViewClick(Record.COMPUTER);
                setDirty(true);
            }
        } catch(NullPointerException e) {
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, e, "Computer Not Created", JOptionPane.ERROR_MESSAGE);
        }    
    }
                        
    protected void onInsertOrderClick() { 
        ImageIcon icon = null;
        try {
            icon = new ImageIcon("gui/photos/Order.png");
        } catch(Exception e) {
        }

        try {
            Object[] customers = store.customers();
            if(customers.length == 0) {
                onInsertCustomerClick();
                customers = store.customers();
                if(customers.length == 0) return;
            }
            Customer customer = (Customer) customers[0];
            if(customers.length > 1) {
                JLabel label = new JLabel("Order for which Customer?");
                JComboBox<Object> cb = new JComboBox<>(customers);
                int button = JOptionPane.showConfirmDialog(this, new Object[]{label, cb}, "New Order", 
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, icon);
                if(button != JOptionPane.OK_OPTION) return;
                customer = (Customer) cb.getSelectedItem();
            }
            
            Order o = new Order(customer);
            JComboBox<Object> cb = new JComboBox<>(store.computers());
            int computersAdded = 0; 
            while(true) {
                JLabel label = new JLabel("<html><p>" + o.toString().replaceAll("\n", "<br/>") + "</p></html>");
                int button = JOptionPane.showConfirmDialog(this, new Object[]{label, cb}, "Another Computer?", 
                    JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, icon);
                    
                if(button != JOptionPane.YES_OPTION) break;
                
                o.addComputer((Computer) cb.getSelectedItem());
                ++computersAdded;
            }
            
            if(computersAdded > 0) {
                store.add(o);
                onViewClick(Record.ORDER);
                setDirty(true);
            }
        } catch(NullPointerException e) {
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, e, "Order Not Created", JOptionPane.ERROR_MESSAGE);
        }    
    }
                        
    protected void onViewClick(Record record) { 
        String header = null;
        Object[] list = null;
        if(record == Record.CUSTOMER) {
            header = "Our Beloved Customers";
            list = store.customers();
        }
        if(record == Record.OPTION) {
           header = "Options for our SuperComputers";
           list = store.options();
        }
        if(record == Record.COMPUTER) {
            header = "Computers for Sale - Cheap!";
            list = store.computers();
        }
        if(record == Record.ORDER) {
            header = "Orders Placed to Date";
            list = store.orders();
        }
        
        StringBuilder sb = new StringBuilder("<html><p><font size=+2>" + header + "</font></p><br/>\n<ol>\n");
        for(Object i : list) sb.append("<li>" + i.toString().replaceAll("<","&lt;")
                                                            .replaceAll(">", "&gt;")
                                                            .replaceAll("\n", "<br/>") + "</li>\n");
        sb.append("</ol></html>");
        display.setText(sb.toString());
    }
            
    protected void onAboutClick() {                 
        Canvas logo = new Canvas("gui/photos/Logo.png");

        JLabel title = new JLabel("<html>"
          + "<p><font size=+4>" + NAME + "</font></p>"
          + "</html>",
          SwingConstants.CENTER);

        JLabel subtitle = new JLabel("<html>"
          + "<p>Exceptional Laptops and Supercomputers Always</p>"
          + "</html>",
          SwingConstants.CENTER);

        JLabel version = new JLabel("<html>"
          + "<p>Version " + VERSION + "</p>"
          + "</html>",
          SwingConstants.CENTER);

        JLabel artists = new JLabel("<html>"
          + "<br/><p>Copyright 2017-2023 by Raul Trevino</p>"
          + "<p>Licensed under Gnu GPL 4.0</p><br/>"

          + "<br/><p>Logo based on work by catkuro per the Flaticon License</p>"
          + "<p><font size=-2>https://www.flaticon.com/free-icon/store_3028492</font></p>"
          
          + "<br/><p>Add Customer icon based on work by Anggara Putra per the Icon-Icons License</p>"
          + "<p><font size=-2>https://www.icon-icons.com/icon/user-person-customer/196942</font></p>"

          + "<br/><p>View Customers icon image was deleted on the website but per the VeryIcon License</p>"
          + "<p><font size=-2>https://www.veryicon.com/icons/miscellaneous/905-system/customer-management-4.html</font></p>"

          + "<br/><p>Add Option icon based on work by Freepik per the Flaticon License</p>"
          + "<p><font size=-2>https://www.flaticon.com/free-icon/options-lines_82122</font></p>"

          + "<br/><p>View Options icon based on work by Adioma per the Adioma License</p>"
          + "<p><font size=-2>https://adioma.com/icons/options</font></p>"

          + "<br/><p>Add Computer icon based on work by Rewat Wannasuk per the Vecteezy License</p>"
          + "<p><font size=-2>https://www.vecteezy.com/vector-art/570073-desktop-computer-icon</font></p>"

          + "<br/><p>View Computers icon based on work by Freepik per the Flaticon License</p>"
          + "<p><font size=-2>https://www.flaticon.com/free-icon/multiple-computers-connected_70930</font></p>"

          + "<br/><p>Add Orders icon based on work by Icons8 per the Iconfinder License</p>"
          + "<p><font size=-2>https://www.iconfinder.com/icons/2639879/order_icon</font></p>"

          + "<br/><p>View Orders icon based on work by Freepik per the Flaticon License</p>"
          + "<p><font size=-2>https://www.flaticon.com/free-icon/order_3496155</font></p>"
 
          + "<br/><p>New icon based on work by Arthur Shlain per the Noun Project License</p>"
          + "<p><font size=-2>https://thenounproject.com/icon/new-file-992729/</font></p>"

          + "<br/><p>Open icon based on work by UXWing per the UXWing License</p>"
          + "<p><font size=-2>https://uxwing.com/open-file-folder-icon/</font></p>"

          + "<br/><p>Save icon based on work by Freepik per the Flaticon License</p>"
          + "<p><font size=-2>https://www.flaticon.com/free-icon/save-file_69960</font></p>"

          + "<br/><p>Save As icon based on work by iconoci per the Noun Project License</p>"
          + "<p><font size=-2>https://thenounproject.com/icon/save-file-10092/</font></p>"

          + "</html>");
          
        JOptionPane.showMessageDialog(this, 
            new Object[]{logo, title, subtitle, version, artists},
            "ELSA",
            JOptionPane.PLAIN_MESSAGE
        );
    }

    private void setDirty(boolean isDirty) {
        save.setEnabled(isDirty);
        //saveAs.setEnabled(isDirty);
        saveButton.setEnabled(isDirty);
        //saveAsButton.setEnabled(isDirty);
    };


    private Store store;                    
    private JLabel display;                 

    private File filename;
    
    private JMenuItem save;
    private JMenuItem saveAs;
    private JButton saveButton;
    private JButton saveAsButton;

}