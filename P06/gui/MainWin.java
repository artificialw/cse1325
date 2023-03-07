package store;

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
import javax.swing.ImageIcon;        // holds a custom icon
import javax.swing.SwingConstants;   // useful values for Swing method calls

import javax.imageio.ImageIO;        // loads an image from a file

import java.io.File;                 // opens a file
import java.io.IOException;          // reports an error reading from a file

import java.awt.BorderLayout;        // layout manager for main window
import java.awt.FlowLayout;          // layout manager for About dialog

import java.awt.Color;               // the color of widgets, text, or borders
import java.awt.Font;                // rich text in a JLabel or similar widget
import java.awt.image.BufferedImage; // holds an image loaded from a file

//Images are not made by me, links for the images are below:
//con-icons.com/icon/user-person-customer/196942
//https://www.flaticon.com/free-icon/options-lines_82122
//https://www.vecteezy.com/vector-art/570073-desktop-computer-icon
//https://www.veryicon.com/icons/miscellaneous/905-system/customer-management-4.html
//https://adioma.com/icons/options
//https://www.flaticon.com/free-icon/multiple-computers-connected_70930

public class MainWin extends JFrame {
    public MainWin(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        
        // /////// ////////////////////////////////////////////////////////////////
        // M E N U

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem quitMenuItem = new JMenuItem("Quit");
        JMenu insertMenu = new JMenu("Insert");
        JMenuItem customerMenuItem = new JMenuItem("Customer");
        JMenuItem optionMenuItem = new JMenuItem("Option");
        JMenuItem computerMenuItem = new JMenuItem("Computer");
        JMenu viewMenu = new JMenu("View");
        JMenuItem customersMenuItem = new JMenuItem("Customers");
        JMenuItem optionsMenuItem = new JMenuItem("Options");
        JMenuItem computersMenuItem = new JMenuItem("Computers");
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutMenuItem = new JMenuItem("About");

        quitMenuItem.addActionListener(e -> onQuitClick());
        customerMenuItem.addActionListener(e -> onInsertCustomerClick());
        optionMenuItem.addActionListener(e -> onInsertOptionClick());
        computerMenuItem.addActionListener(e -> onInsertComputerClick());
        customersMenuItem.addActionListener(e -> onViewClick(Record.CUSTOMER));
        optionsMenuItem.addActionListener(e -> onViewClick(Record.OPTION));
        computersMenuItem.addActionListener(e -> onViewClick(Record.COMPUTER));
        aboutMenuItem.addActionListener(e -> onAboutClick());

        fileMenu.add(quitMenuItem);
        menuBar.add(fileMenu);

        insertMenu.add(customerMenuItem);
        insertMenu.add(optionMenuItem);
        insertMenu.add(computerMenuItem);
        menuBar.add(insertMenu);

        viewMenu.add(customersMenuItem);
        viewMenu.add(optionsMenuItem);
        viewMenu.add(computersMenuItem);
        menuBar.add(viewMenu);

        helpMenu.add(aboutMenuItem);
        menuBar.add(helpMenu);
        
        // ///////////// //////////////////////////////////////////////////////////
        // T O O L B A R

        JToolBar toolbar = new JToolBar("Store choices");

        JButton newStoreB = new JButton(UIManager.getIcon("FileView.fileIcon"));
        newStoreB.setActionCommand("New Store");
        newStoreB.setToolTipText("Create a new store");
        newStoreB.setBorder(null);
        toolbar.add(newStoreB);
        newStoreB.addActionListener(event -> onNewStoreClick());

        toolbar.add(Box.createHorizontalStrut(25));

        ImageIcon insertIcon1 = new ImageIcon("Customer.png");
        JButton insertButton1 = new JButton(insertIcon1);
        insertButton1.setActionCommand("Add a customer");
        insertButton1.setToolTipText("Add a customer");
        toolbar.add(insertButton1);
        insertButton1.addActionListener(event -> onInsertCustomerClick());

        ImageIcon insertIcon2 = new ImageIcon("Option.png");
        JButton insertButton2 = new JButton(insertIcon2);
        insertButton2.setActionCommand("Add a option");
        insertButton2.setToolTipText("Add a option");
        toolbar.add(insertButton2);
        insertButton2.addActionListener(event -> onInsertOptionClick());

        ImageIcon insertIcon3 = new ImageIcon("Computer.png");
        JButton insertButton3 = new JButton(insertIcon3);
        insertButton3.setActionCommand("Add a computer");
        insertButton3.setToolTipText("Add a computer");
        toolbar.add(insertButton3);
        insertButton3.addActionListener(event -> onInsertComputerClick());

        toolbar.add(Box.createHorizontalStrut(25));

        ImageIcon viewIcon1 = new ImageIcon("AllCustomers.png");
        JButton viewButton1 = new JButton(viewIcon1);
        viewButton1.setActionCommand("View customers");
        viewButton1.setToolTipText("View all customers registered");
        toolbar.add(viewButton1);
        viewButton1.addActionListener(event -> onViewClick(Record.CUSTOMER));

        ImageIcon viewIcon2 = new ImageIcon("AllOptions.png");
        JButton viewButton2 = new JButton(viewIcon2);
        viewButton2.setActionCommand("View options");
        viewButton2.setToolTipText("View all options registered");
        toolbar.add(viewButton2);
        viewButton2.addActionListener(event -> onViewClick(Record.OPTION));

        ImageIcon viewIcon3 = new ImageIcon("AllComputers.png");
        JButton viewButton3 = new JButton(viewIcon3);
        viewButton3.setActionCommand("View computers");
        viewButton3.setToolTipText("View all computers in store");
        toolbar.add(viewButton3);
        viewButton3.addActionListener(event -> onViewClick(Record.COMPUTER));

        toolbar.add(Box.createHorizontalStrut(25));

        toolbar.add(Box.createHorizontalGlue());

        JButton quitB = new JButton("X");
        quitB.setActionCommand("Quit");
        quitB.setToolTipText("Exit application");
        quitB.setBorder(null);
        toolbar.add(quitB);
        quitB.addActionListener(event -> onQuitClick());

        getContentPane().add(toolbar, BorderLayout.PAGE_START);
        
        
        // /////////////////////////// ////////////////////////////////////////////
        // D I S P L A Y
        computers = new JLabel();
        computers.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(computers, BorderLayout.CENTER);

        // S T A T U S   B A R   D I S P L A Y ////////////////////////////////////
        msg = new JLabel();
        add(msg, BorderLayout.PAGE_END);
        
        setVisible(true);
        
        onNewStoreClick();
    }
    
    // Listeners
    
    protected void onQuitClick() {System.exit(0);}
    
    protected void onInsertCustomerClick() {  
        String name = JOptionPane.showInputDialog(null, "Enter customer name:");

        String email = JOptionPane.showInputDialog(null, "Enter customer email address:");

        try {
            Customer customer = new Customer(name, email);

            store.add(customer);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Email address is invalid", "Warning", JOptionPane.WARNING_MESSAGE);
        } catch (NullPointerException e) {

        }
    }
            
    protected void onInsertOptionClick() {
        String name = JOptionPane.showInputDialog(null, "Enter option name:");

        String costString = JOptionPane.showInputDialog(null, "Enter option cost:");

        try {
            double costDouble = Double.parseDouble(costString);
            long cost = (long) (costDouble * 100);

            Option option = new Option(name, cost);

            int confirm = JOptionPane.showConfirmDialog(null, "Add option " + option + "?");

            if (confirm == JOptionPane.OK_OPTION) {
                store.add(option);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Cost is a invalid number", "Warning", JOptionPane.WARNING_MESSAGE);
        } catch (NullPointerException e) {

        }    
    }

    protected void onInsertComputerClick() {
        String name = JOptionPane.showInputDialog(null, "Enter computer name:");

        String model = JOptionPane.showInputDialog(null, "Enter computer model:");

        Object[] optionsArray = store.getOptions().toArray();

        JComboBox<Object> optionsComboBox = new JComboBox<>(optionsArray);

        int confirm = JOptionPane.showConfirmDialog(null, optionsComboBox, "Select options", JOptionPane.OK_CANCEL_OPTION);

        if (confirm == JOptionPane.OK_OPTION) {
            List<Option> selectedOptions = new ArrayList<>();
            for (Object option : optionsComboBox.getSelectedObjects()) {
                selectedOptions.add((Option) option);
            }

            Computer computer = new Computer(name, model, selectedOptions);

            confirm = JOptionPane.showConfirmDialog(null, "Add computer " + computer + "?");

            if (confirm == JOptionPane.OK_OPTION) {
                store.add(computer);
            }
        }
    }

    protected void onViewClick(Record record) {                
        String header = "";
        Object[] records = null;
    
        switch(record) {
            case "Customer":
                header = "Customers";
                records = store.getCustomer();
                break;
            case "Option":
                header = "Options";
                records = store.getOption();
                break;
            case "Computer":
                header = "Computers";
                records = store.getComputer();
                break;
            case "Order":
                header = "Orders";
                records = store.getOrder();
                break;
            default:
                break;
        }
    
        if(records == null || records.length == 0) {
            display.setText("No records to display.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<html><p><font size=+2>" + header + "</font></p><br><ol>");

        for(int i = 0; i < records.length; i++) {
            sb.append("<li>" + records[i].toString() + "</li>");
        }

        sb.append("</ol></html>");
        display.setText(sb.toString());
     }

     protected void onAboutClick() {
        JLabel title = new JLabel("<html>"
            + "<p><font size=+4>ELSA</font></p>"
            + "<p>Version 0.2</p>"
            + "</html>",
            SwingConstants.CENTER);

        JLabel licensers = new JLabel("<html>"
            + "<br/><p>Copyright 2017-2023 by Raul Trevino</p>"
            + "<p>Licensed under Gnu GPL 3.0</p><br/>"
            + "</html>");

        JOptionPane.showMessageDialog(this, 
            new Object[]{title, licensers},
            "About ELSA",
            JOptionPane.PLAIN_MESSAGE
        );
    } 
    private JLabel computers;
    private JLabel msg;
    private JLabel title;
    private JLabel licensers;
    private JButton newStoreB;
    private JButton insertButton1;
    private JButton insertButton2;
    private JButton insertButton3;
    private JButton viewButton1;
    private JButton viewButton2;
    private JButton viewButton3;
    private JButton quitB;

}
