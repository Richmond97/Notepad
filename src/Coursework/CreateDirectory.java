package Coursework;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateDirectory {
   // Note Note = new Note();
    
    
     public CreateDirectory(String title)  {
   // public static void main(String[] args)throws IOException {
   
        File folder = new File("Courses");
        folder.mkdir();
    }
       
}
