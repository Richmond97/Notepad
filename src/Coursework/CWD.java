/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coursework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

/**
 *
 * @author ry9102q
 */
public class CWD extends JFrame implements ActionListener, KeyListener {
    
    Note nt = new Note();
    JTextArea txtDisplayNotes = new JTextArea();
    CommonCode cc = new CommonCode();
    JToggleButton toggleBtn = null; 
    String courseName;
    JComboBox courseList = new JComboBox();
    ArrayList<String> course = new ArrayList<>();
    JTextArea txtNewNote = new JTextArea();

    // Using MVC
    public CWD(String crse) throws FileNotFoundException {
        courseName = crse;
        model();     
        view();
        controller();
    }
    
    private void model() throws FileNotFoundException {
        String listCourse = System.getProperty("user.dir") + cc.fileSeparator + "listcourse.txt";
        Scanner s = new Scanner(new File(listCourse));
        while (s.hasNextLine()) {
            course.add(s.nextLine());
        }
    }

    private void view() {

        Font fnt = new Font("Palatino Linotype", Font.PLAIN, 18);
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu();

        file = new JMenu("file");
        file.setToolTipText("file tasks");
        file.setFont(fnt);

        file.add(makeMenuItem("New", "NewNote", "Create a new note.", fnt));
        file.addSeparator();
        file.add(makeMenuItem("Close", "Close", "Clear the current note.", fnt));

        menuBar.add(file);
        menuBar.add(makeMenuItem("Exit", "Exit", "Close this program", fnt));

        this.setJMenuBar(menuBar);

        JToolBar toolBar = new JToolBar();
        JButton button = null;
        button = makeButton("Create", "Return2Notes",
                "Create a new note.",
                "New");
                toolBar.add(button);

        toolBar.addSeparator();
        button = makeButton("exit", "Exit",
                "Exit from this program.",
                "Exit");
                toolBar.add(button);
                
        add(toolBar, BorderLayout.NORTH);
        
        for (String crse : course) {
            courseList.addItem(crse);
        }

        courseList.setFont(fnt);
        courseList.setMaximumSize(courseList.getPreferredSize());
        courseList.addActionListener(this);
        courseList.setActionCommand("courseList");
        menuBar.add(courseList);
        
        addRequirement(courseName);
        
        setTitle("Coursework - Richmond Yeboah");
        setSize(1200, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel pnlWest = new JPanel();
        pnlWest.setLayout(new BoxLayout(pnlWest, BoxLayout.Y_AXIS));
        pnlWest.setBorder(BorderFactory.createLineBorder(Color.black));

        txtNewNote.setFont(fnt);
        pnlWest.add(txtNewNote);

        txtNewNote.setColumns(20);
        txtNewNote.setLineWrap(true);
        txtNewNote.setWrapStyleWord(true);

        //add(toolBar, BorderLayout.NORTH);
        JButton btnAddNote = new JButton("Add note");
        btnAddNote.setActionCommand("NewNote");
        btnAddNote.addActionListener(this);
        pnlWest.add(btnAddNote);

        add(pnlWest, BorderLayout.WEST);

        JPanel cen = new JPanel();
        cen.setLayout(new BoxLayout(cen, BoxLayout.Y_AXIS));
        cen.setBorder(BorderFactory.createLineBorder(Color.black));
        txtDisplayNotes.setFont(fnt);
        cen.add(txtDisplayNotes);

        add(cen, BorderLayout.CENTER);

        setSize(1024, 768);
        setTitle("Coursework - Richmond Yeboah");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        txtDisplayNotes.setEditable(false);

        setVisible(true); // Needed to ensure that the items can be seen.

    }

    private void controller() {
        System.out.println("Controller() Activated");
    }

    protected JMenuItem makeMenuItem(
            String txt,
            String actionCommand,
            String toolTipText,
            Font fnt) {

        JMenuItem mnuItem = new JMenuItem();
        mnuItem.setText(txt);
        mnuItem.setActionCommand(actionCommand);
        mnuItem.setToolTipText(toolTipText);
        mnuItem.setFont(fnt);
        mnuItem.addActionListener(this);

        return mnuItem;
    }

    protected JButton makeButton1(
            String imageName,
            String actionCommand,
            String toolTipText,
            String altText) {

        //Create and initialize the button.
        JButton button1 = new JButton();
        button1.setToolTipText(toolTipText);
        button1.setActionCommand(actionCommand);
        button1.addActionListener(this);

        //Look for the image.
        String imgLocation = System.getProperty("user.dir")
                + cc.fileSeparator
                + "icons"
                + cc.fileSeparator
                + imageName
                + ".png";

        File fyle = new File(imgLocation);
        if (fyle.exists() && !fyle.isDirectory()) {
            // image found
            Icon img;
            img = new ImageIcon(imgLocation);
            button1.setIcon(img);
        } else {
            // image NOT found
            button1.setText(altText);
            System.err.println("Resource not found: " + imgLocation);
        }

        return button1;
    }

    protected JButton makeButton(
            String imageName,
            String actionCommand,
            String toolTipText,
            String altText) {

        //Create and initialize the button.
        JButton button = new JButton();
        button.setToolTipText(toolTipText);
        button.setActionCommand(actionCommand);
        button.addActionListener(this);

        //Look for the image.
        String imgLocation = System.getProperty("user.dir")
                + cc.fileSeparator
                + "icons"
                + cc.fileSeparator
                + imageName
                + ".png";

        File fyle = new File(imgLocation);
        if (fyle.exists() && !fyle.isDirectory()) {
            // image found
            Icon img;
            img = new ImageIcon(imgLocation);
            button.setIcon(img);
        } else {
            // image NOT found
            button.setText(altText);
            System.err.println("Resource not found: " + imgLocation);
        }

        return button;
    }
    
     protected JToggleButton makeToogleButton(
            String imageName,
            String actionCommand,
            String toolTipText,
            String altText) {

        //Create and initialize the button.
        JToggleButton toggleBtn = new JToggleButton();
  
        toggleBtn.setToolTipText(toolTipText);
        toggleBtn.setActionCommand(actionCommand);
        toggleBtn.addActionListener(this);

        //Look for the image.
        String imgLocation = System.getProperty("user.dir")
                + cc.fileSeparator
                + "icons"
                + cc.fileSeparator
                + imageName
                + ".png";

        File fyle = new File(imgLocation);
        if (fyle.exists() && !fyle.isDirectory()) {
            // image found
            Icon img;
            img = new ImageIcon(imgLocation);
            toggleBtn.setIcon(img);
        } else {
            // image NOT found
            toggleBtn.setText(altText);
            System.err.println("Resource not found: " + imgLocation);
        }

        return toggleBtn;
    }
     
     private void addRequirement(String crse) {
        
        String ntPath = cc.appDir + cc.fileSeparator + "Courses" + cc.fileSeparator + crse + cc.fileSeparator + "Requirements.txt";
        ArrayList<String> noteDispld = cc.readTextFile(ntPath);
        System.out.println(ntPath + " because of addNotes");

        for (int i=0; i < noteDispld.size(); i++) {
            String text = noteDispld.get(i);
            System.out.println(noteDispld.get(i));
            txtDisplayNotes.append(text + "\n");
        }

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if("NewNote".equals(ae.getActionCommand()))
        {
            txtDisplayNotes.append(txtNewNote.getText());
            try {
                FileWriter saveFile = new FileWriter(cc.appDir + cc.fileSeparator + "Courses" + cc.fileSeparator + courseName + cc.fileSeparator + "Requirements.txt");
                System.out.println(courseName);
                txtDisplayNotes.write(saveFile);
            } catch (IOException ex) {
                Logger.getLogger(Coursework.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if ("Return2Notes".equals(ae.getActionCommand())) {
            dispose();
        }
        if ("Exit".equals(ae.getActionCommand())) {
            dispose();
        }
        if ("courseList".equals(ae.getActionCommand())) {
                courseName = courseList.getSelectedItem().toString();
                txtDisplayNotes.setText("");
                System.out.println(courseName + " Please show");
                addRequirement(courseName);
            }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //private JButton makeButton(String create, String return2Notes, String return_to_the_Note_window, String notes) {
    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
}
