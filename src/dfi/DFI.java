package dfi;

public class DFI {
    
    public static void main(String[] args) {
        //creates new interface within a new thread and starts it.
        (new Thread(new DFI_Interface())).start();
    }
}
