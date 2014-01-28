package dfi;

//Import libraries
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;
import javax.swing.JOptionPane;

//Class implements Runnable for multi-threading
public class FileSearch implements Runnable {
    //creates new date time class

    private getDateTime timeStamp = new getDateTime();
    //Variable declaration
    private String ROOT, Search;
    //Counters
    private int FileCount, DirectoryCount, ResultCount = 0;

    //Constructor
    public FileSearch(String Directory, String SearchTerm) {
        ROOT = Directory;
        Search = SearchTerm;
    }

    //Override for the run method
    @Override
    public void run() {
        try {
            //Checks that a value has been entered for the directory
            if (ROOT.isEmpty()) {
                //if not then error message is shown
                JOptionPane.showMessageDialog(null, "Please enter a directory before continuing.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                DFI_Interface.searchStarted = false;
                //Checks that a value has been entered for the search term
            } else if (Search.isEmpty()) {
                //if not then error message is shown
                JOptionPane.showMessageDialog(null, "Please enter a search term before continuing.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                DFI_Interface.searchStarted = false;
            } else {
                // Add start information to the log
                WriteSearchLog.Write("");
                WriteSearchLog.Write("---------------------------------------------------------------------------------------");
                WriteSearchLog.Write(timeStamp.get() + " - SEARCH STARTED FOR PHRASE '" + Search + "' IN DIRECTORY '" + ROOT + "'");
                WriteSearchLog.Write("---------------------------------------------------------------------------------------");
                WriteSearchLog.Write("");

                //Otherwise start scanning files in the given directory for the search term
                FileVisitor<Path> fileProcessor = new ProcessFile();
                try {
                    Files.walkFileTree(Paths.get(ROOT), fileProcessor);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "An error occurred:\n" + e,
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                //Once the end of the directory is reached, output a summary to log
                searchComplete();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An IOException occurred:\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void searchComplete() {
        //write a search completion summary to the log and show message to the user.
        try {
            WriteSearchLog.Write("");
            WriteSearchLog.Write("---------------------------------------------------------------------------------------");
            WriteSearchLog.Write(timeStamp.get() + " - SEARCH COMPLETED FOR PHRASE '" + Search + "' IN DIRECTORY '" + ROOT + "'");
            WriteSearchLog.Write("DIRECTORIES SCANNED: " + DirectoryCount + " | FILES SCANNED: " + FileCount + " | RESULTS FOUND: " + ResultCount);
            WriteSearchLog.Write("---------------------------------------------------------------------------------------");
            WriteSearchLog.Write("");
            JOptionPane.showMessageDialog(null, "Search Complete! \n\n"
                    + "DIRECTORIES SCANNED: " + DirectoryCount + "\nFILES SCANNED: " + FileCount + "\nRESULTS FOUND: " + ResultCount, "Search Complete", JOptionPane.DEFAULT_OPTION);
            DFI_Interface.searchStarted = false;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An IOException occurred:\n"
                    + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Class to process any txt files that are found during the search
    private final class ProcessFile extends SimpleFileVisitor<Path> {
        //Overrides visit file method
        @Override
        public FileVisitResult visitFile(Path aFile, BasicFileAttributes aAttrs) throws IOException {
            //Write file visit to log and update file count
            WriteSearchLog.Write(timeStamp.get() + " - Processing File: " + aFile);
            FileCount++;

            //Check whether the file is a .txt file - breaks if filename is fewer than 4 characters
            String fileName = aFile.toString();
            int nameLength = fileName.length();

            if (nameLength < 4 && (!fileName.contains("."))) {
            } else {
                nameLength = nameLength - 4;
            }

            if (fileName.substring(nameLength, nameLength + 4).equals(".txt")) {
                scanFile(aFile, Search);
            }

            //Additional search on image filenames
            if (fileName.substring(nameLength, nameLength + 4).equals(".jpg")
                    || (fileName.substring(nameLength, nameLength + 4).equals(".gif")
                    || (fileName.substring(nameLength, nameLength + 4).equals(".png")
                    || (fileName.substring(nameLength, nameLength + 4).equals(".bmp"))))) {
                if (fileName.toLowerCase().contains(Search.toLowerCase())) {
                    String result = timeStamp.get() + " - RESULT! - SEARCH TERM: " + Search + " FOUND ON IMAGE FILENAME: " + aFile;
                    WriteSearchLog.Write(result);
                    ResultCount++;
                }
            }

            return FileVisitResult.CONTINUE;
        }

        //Override for failed file visit method
        @Override
        public FileVisitResult visitFileFailed(Path file, IOException e) throws IOException {
            //Write visit failure to log
            WriteSearchLog.Write(timeStamp.get() + " - VISITING FAILED FOR: " + file);

            return FileVisitResult.SKIP_SUBTREE;
        }

        //Override for pre visit directory method
        @Override
        public FileVisitResult preVisitDirectory(Path aDir, BasicFileAttributes aAttrs) throws IOException {
            //Write directory visit to log and update count
            WriteSearchLog.Write(timeStamp.get() + " - PROCESSING DIRECTORY: " + aDir);
            DirectoryCount++;

            return FileVisitResult.CONTINUE;
        }
    }

    private void scanFile(Path aFile, String searchTerm) throws FileNotFoundException, IOException {
        //Opens .txt file and scan line by line for search term
        final Scanner scanner = new Scanner(aFile);
        while (scanner.hasNextLine()) {
            final String lineFromFile = scanner.nextLine().toLowerCase();
            if (lineFromFile.contains(searchTerm.toLowerCase())) {
                //If search term is found, print result then break
                String result = timeStamp.get() + " - RESULT! - SEARCH TERM: " + searchTerm + " FOUND IN FILE: " + aFile;
                WriteSearchLog.Write(result);
                ResultCount++;
                break;
            }
        }
    }
}
