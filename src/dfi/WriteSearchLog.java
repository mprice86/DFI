package dfi;

//Import libraries
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import javax.swing.JOptionPane;

public class WriteSearchLog {
    //class to write the search log to file
    //create new file with the name SearchLog
    public static File aFile = new File("SearchLog.txt");
    //Method to write to the log
    public static void Write(String Log) throws IOException {
        //Check if SearchLog already exists
        try {
            checkFileExists(aFile.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred \n" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        //Write log to file
        setContents(aFile, Log);
    }

    public static void checkFileExists(String fileName) throws IOException {
        //New file class with name searchlog
        File file = new File(aFile.toString());
        //if file doesn't exist then new file with name searchlog is created
        if (!aFile.exists()) {
            file.createNewFile();
        }
    }

    public static String getContents(File aFile) {
        //Get contents of the log file using string builder
        StringBuilder contents = new StringBuilder();
        //while the line in the file is not null, append it to the string
        try (BufferedReader input = new BufferedReader(new FileReader(aFile))) {
            String line;
            while ((line = input.readLine()) != null) {
                contents.append(line);
                contents.append(System.getProperty("line.separator"));
            }
            //catch exception
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred \n" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        //return the contents of the file
        return contents.toString();
    }

    public static void setContents(File aFile, String aContents) throws FileNotFoundException, IOException {
        //Error prevention
        if (aFile == null) {
            throw new IllegalArgumentException("File should not be null.");
        }
        if (!aFile.exists()) {
            throw new FileNotFoundException("File does not exist: " + aFile);
        }
        if (!aFile.isFile()) {
            throw new IllegalArgumentException("Should not be a directory: " + aFile);
        }
        if (!aFile.canWrite()) {
            throw new IllegalArgumentException("File cannot be written: " + aFile);
        }

        FileOutputStream out = null;
        boolean append = true;

        try {
            //try to append recieved string to file
            out = new FileOutputStream(aFile.getName(), append);
            PrintStream addSomething = new PrintStream(out);
            addSomething.println(aContents);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "A FileNotFoundException error occurred. \n" + e);
        } finally {
            //finally close the output stream
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "An IOException error occurred. \n" + e);
                }
            }
        }
    }
}
