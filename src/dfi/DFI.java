package dfi;

public class DFI {
    
    public static void main(String[] args) {
        //creates new interface within a new thread and starts it.
        (new Thread(new DFI_Interface())).start();
        
        //old way to do the above:
        //DFI_Interface ForensicsTool = new DFI_Interface();
        //Thread FT = new Thread(ForensicsTool).start();
        //FT.start();
    }
}
