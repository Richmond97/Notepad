/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coursework;

import java.io.FileNotFoundException;

/**
 * hello Rich /**
 *
 * @author ry9102q
 */
public class Note extends CommonCode {

    private int noteID = 0;
    private String course = "";
    private String dayte = "";
    private String note = "";
    Coursework cww;
    

    

    public Note()   {
     
        

    }
    
    public Note(int max, String crs, String nt)  {
       
        setNoteID(max);
        setCourse(crs);
        setDayte();
        setNote(nt);
    }
     
    public Note(int nid, String crs, String dt, String nt) {
       
        setNoteID(nid);
        setCourse(crs);
        setDayte(dt);
        setNote(nt);
    }

   
    //Note(int nid, String string, String string0, String string1) {
    //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
    public int getNoteID() {
        // Any checking goes here.
        return noteID;
    }
    
    public final void setNoteID(int n) {
        int nid = n;
        // Any validation goes here.
        noteID = nid;
    }
    public String getCourse(){
        return course;
    }
    
    public final void setCourse(String c) {
        String crse = c;
        // Any validation goes here.
        course = crse;
    }
    
    public String getDayte() {
        return dayte;

    }
    
    public final void setDayte() {
        dayte = orderedDate;
    }

    public final void setDayte(String dt) {
        dayte = dt;

    }

    public final void setNote(String n) {
        // Any validation goes here.
        note = n;

    }

    public String getNote() {
        // Any checking goes here.
        return note;   
        
    }
      
}
