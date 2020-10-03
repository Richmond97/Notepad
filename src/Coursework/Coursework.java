package Coursework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class Coursework extends JFrame implements ActionListener, KeyListener {

    CommonCode cc = new CommonCode();
    JPanel pnl = new JPanel(new BorderLayout());
    JTextArea txtNewNote = new JTextArea();
    JTextArea txtDisplayNotes = new JTextArea();
    ArrayList<String> note = new ArrayList<>();
    ArrayList<String> note1 = new ArrayList<>();
    ArrayList<String> reqrmnt = new ArrayList<>();
    JTextField search = new JTextField();
    ArrayList<String> course = new ArrayList<>();
    ArrayList<String> crseAdded = new ArrayList<>();
    JComboBox courseList = new JComboBox();
    String crse = "";
    AllNotes allNotes = new AllNotes();
    String newCrse = "";
    Scanner s;
    Scanner s2;

    public static void main(String[] args) throws FileNotFoundException {

        //JOptionPane.showMessageDialog(null, "RIchmond Yeboah - 000996904");
        Coursework prg = new Coursework();
    }

    // using MVC
    public Coursework() throws FileNotFoundException {
        model();
        view();
        controller();
    }
    
    private void model() throws FileNotFoundException {
        String listCourse = System.getProperty("user.dir") + cc.fileSeparator + "listcourse.txt";
        s = new Scanner(new File(listCourse));
        while (s.hasNextLine()) {
            course.add(s.nextLine());
        }
    }

    private void view() {
        Font fnt = new Font("Georgia", Font.PLAIN, 24);

        JMenuBar menuBar = new JMenuBar();
        JMenu note = new JMenu();

        note = new JMenu("Note");
        note.setToolTipText("Note tasks");
        note.setFont(fnt);

        note.add(makeMenuItem("New", "NewNote", "Create a new note.", fnt));
        note.addSeparator();
        note.add(makeMenuItem("Close", "Close", "Clear the current note.", fnt));

        menuBar.add(note);

        JMenu CrsOp = new JMenu("options");
        CrsOp.setToolTipText("Add new Course");
        CrsOp.setFont(fnt);

        CrsOp.add(makeMenuItem("Add a new course", "NewCourse", "Add a new course.", fnt));
        CrsOp.addSeparator();
        CrsOp.add(makeMenuItem("Delete Course", "DeleteCourse", "Delete course.", fnt));
        CrsOp.addSeparator();
        CrsOp.add(makeMenuItem("Amend Course", "AmendCourse", "Amend course.", fnt));
        CrsOp.addSeparator();
        CrsOp.add(makeMenuItem("Coursework Window", "Coursework", "Open coursework Window.", fnt));

        menuBar.add(CrsOp);
        menuBar.add(makeMenuItem("Exit", "Exit", "Close this program", fnt));

        // This will add each course to the combobox
        for (String crse : course) {
            courseList.addItem(crse);
        }

        courseList.setFont(fnt);
        courseList.setMaximumSize(courseList.getPreferredSize());
        courseList.addActionListener(this);
        courseList.setActionCommand("courseList");
        menuBar.add(courseList);

        this.setJMenuBar(menuBar);

        JToolBar toolBar = new JToolBar();
        // Setting up the ButtonBar
        JButton button = null;
        button = makeButton("Create", "NewNote",
                "Create a new note.",
                "New");
        toolBar.add(button);
        button = makeButton("Document", "Coursework",
                "Open the courwork window.",
                "Courwork");
        toolBar.add(button);
        button = makeButton("closed door", "Close",
                "Close this note.",
                "Close");
        toolBar.add(button);
        toolBar.addSeparator();

        button = makeButton("exit", "Exit",
                "Exit from this program.",
                "Exit");
        toolBar.add(button);
        toolBar.addSeparator();

        // This forces anything after it to the right.
        toolBar.add(Box.createHorizontalGlue());
        search.setMaximumSize(new Dimension(6900, 30));
        search.setFont(fnt);
        toolBar.add(search);
        toolBar.addSeparator();
        button = makeButton("search", "SearchKeyword",
                "Search for this text.",
                "Search");
        toolBar.add(button);

        add(toolBar, BorderLayout.NORTH);

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

        setVisible(true);  // Needed to ensure that the items can be seen.
    }

    private void controller() {
        courseList.setSelectedItem("COMP2434");
        crse = courseList.getSelectedItem().toString();
        System.out.println(crse);
        addNotes(crse);
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
                + "//icons//"
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

    private void addNotes(String crse) {
        
        String ntPath = cc.appDir + cc.fileSeparator + "Courses" + cc.fileSeparator + crse + cc.fileSeparator + "Note.txt";
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
        if ("Close".equals(ae.getActionCommand())) {
            System.exit(0);
        }
        if ("Exit".equals(ae.getActionCommand())) {
            System.exit(0);
        }
        if ("NewNote".equals(ae.getActionCommand())) {
            txtDisplayNotes.append(txtNewNote.getText());
            
            try {
                FileWriter saveFile = new FileWriter(cc.appDir + cc.fileSeparator + "Courses" + cc.fileSeparator + crse + cc.fileSeparator + "Note.txt");
                txtDisplayNotes.write(saveFile);
            } catch (IOException ex) {
                Logger.getLogger(Coursework.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if ("NewCourse".equals(ae.getActionCommand())) {
            String newCrse = JOptionPane.showInputDialog(null, "Please type in your new course");
            course.add(newCrse);
            courseList.addItem(newCrse);
            cc.createDirectory(newCrse);

            //Create file for notes
            String noteCreator = cc.appDir + cc.fileSeparator + "Courses" + cc.fileSeparator + newCrse + cc.fileSeparator + "Note.txt";
            try {
                cc.writeTextFile(noteCreator, note1);
            } catch (IOException ex) {
                Logger.getLogger(Coursework.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Create file for requiremnts
            String reqCreator = cc.appDir + cc.fileSeparator + "Courses" + cc.fileSeparator + newCrse + cc.fileSeparator + "Requirements.txt";
            try {
                cc.writeTextFile(reqCreator, reqrmnt);
            } catch (IOException ex) {
                Logger.getLogger(Coursework.class.getName()).log(Level.SEVERE, null, ex);
            }
            //add new courses to course list file 
            String list = cc.appDir + cc.fileSeparator + "listcourse.txt";
            try {
                cc.writeTextFile(list, course);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Doesn't work");
            }
        }
        if ("courseList".equals(ae.getActionCommand())) {
                crse = courseList.getSelectedItem().toString();
                txtDisplayNotes.setText("");
                System.out.println(crse + " Please show");
                addNotes(crse);
            }
        if ("SearchKeyword".equals(ae.getActionCommand())) {
            String lyst = allNotes.searchAllNotesByKeyword("", 0, search.getText());
            txtDisplayNotes.setText(lyst);
        }
        if ("Coursework".equals(ae.getActionCommand())) {
            try {
                CWD cwd = new CWD(crse);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Coursework.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        System.out.println("Not been coded yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        System.out.println("Not been coded yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        System.out.println("Not been coded yet."); //To change body of generated methods, choose Tools | Templates.
    }


}